package app.cantas.jcversa.data

import java.io.File

/** Read-only preflight. No destination writes until the entire bounded plan is accepted. */
data class TreeCopyNode(val source: String, val relative: String, val directory: Boolean)
data class TreeCopyPlan(val destination: String, val nodes: List<TreeCopyNode>)
class CopyPreparationStopped : java.io.IOException()

class LocalTreeCopy(private val boundary: StorageBoundary, private val streams: CopyStreams) {
    companion object { const val MAX_ENTRIES = 1000; const val MAX_DEPTH = 32 }
    private fun active(check: () -> Boolean) { if (!check()) throw CopyPreparationStopped() }
    private fun checked(path: String): File {
        val raw = File(path).absoluteFile
        val safe = boundary.resolve(path)
        require(raw == safe) { "Symbolic or noncanonical path" }
        return safe
    }
    fun prepare(requests: List<CopyRequest>, destination: String, running: () -> Boolean = { true }): TreeCopyPlan {
        require(requests.size in 1..LocalCopy.MAX_FILES)
        active(running)
        val parent = checked(destination)
        require(parent.isDirectory)
        val names = mutableSetOf<String>()
        val roots = requests.map {
            active(running)
            require(LocalFolders.validName(it.name) && names.add(it.name))
            val file = checked(it.path)
            require(file.isFile || file.isDirectory) { "Unsupported source type" }
            if (file.isDirectory) require(parent != file && !parent.path.startsWith(file.path + File.separator)) {
                "Destination inside source"
            }
            it to file
        }
        val nodes = mutableListOf<TreeCopyNode>()
        fun walk(file: File, relative: String, depth: Int) {
            active(running)
            require(depth <= MAX_DEPTH && nodes.size < MAX_ENTRIES) { "Tree limit" }
            val source = checked(file.path)
            val directory = source.isDirectory
            require(directory || source.isFile) { "Unsupported source type" }
            nodes.add(TreeCopyNode(source.path, relative, directory))
            if (directory) {
                // File.listFiles allocates the full immediate directory on API24. The plan is
                // bounded, not this platform enumeration allocation. Never claim streaming.
                val children = source.listFiles() ?: throw java.io.IOException("Unreadable folder")
                require(children.size <= MAX_ENTRIES - nodes.size) { "Tree limit" }
                for (child in children.sortedBy { it.name }) {
                    require(LocalFolders.validName(child.name))
                    walk(child, relative + "/" + child.name, depth + 1)
                }
            }
        }
        roots.forEach { (request, source) -> walk(source, request.name, 0) }
        active(running)
        return TreeCopyPlan(parent.path, nodes.toList())
    }
    /** Plans are revalidated before any writes; never trust a caller-supplied relative path. */
    fun copy(plan: TreeCopyPlan, running: () -> Boolean = { true }, progress: (CopyProgress) -> Unit = {}): List<CopyOutcome> {
        require(plan.nodes.size in 1..MAX_ENTRIES)
        val parent = checked(plan.destination)
        require(parent.isDirectory)
        val paths = mutableMapOf<String, Boolean>()
        plan.nodes.forEach { node ->
            active(running)
            val parts = node.relative.split('/')
            require(parts.size <= MAX_DEPTH + 1 && parts.all { LocalFolders.validName(it) })
            require(node.relative !in paths)
            val parentName = node.relative.substringBeforeLast('/', "")
            require(parentName.isEmpty() || paths[parentName] == true)
            val source = checked(node.source)
            require(if (node.directory) source.isDirectory else source.isFile)
            if (node.directory) require(parent != source && !parent.path.startsWith(source.path + File.separator))
            paths[node.relative] = node.directory
        }
        val created = mutableSetOf<String>()
        val results = mutableListOf<CopyOutcome>()
        val folders = LocalFolders(boundary)
        val files = LocalCopy(boundary, streams)
        plan.nodes.forEachIndexed { index, node ->
            val target = File(parent, node.relative)
            val parentName = node.relative.substringBeforeLast('/', "")
            if (!running() || (parentName.isNotEmpty() && parentName !in created)) {
                results.add(CopyOutcome(node.relative, target.path, CopyStatus.NOT_ATTEMPTED))
                return@forEachIndexed
            }
            try {
                val source = checked(node.source)
                require(if (node.directory) source.isDirectory else source.isFile)
                val targetParent = checked(target.parent!!)
                progress(CopyProgress(node.relative, index + 1, plan.nodes.size, 0, if (node.directory) 0 else source.length()))
                if (!running()) {
                    results.add(CopyOutcome(node.relative, target.path, CopyStatus.NOT_ATTEMPTED))
                } else if (node.directory) {
                    val result = folders.create(targetParent.path, target.name)
                    if (result is FolderCreation.Created) {
                        created.add(node.relative)
                        results.add(CopyOutcome(node.relative, target.path, CopyStatus.DIRECTORY_CREATED, created = true))
                    } else results.add(CopyOutcome(node.relative, target.path,
                        if ((result as FolderCreation.Failed).error == FolderError.EXISTS) CopyStatus.EXISTS else CopyStatus.FAILED))
                } else {
                    val outcome = files.copy(listOf(CopyRequest(source.path, target.name)), targetParent.path, running) {
                        progress(it.copy(name = node.relative, index = index + 1, total = plan.nodes.size))
                    }.single()
                    results.add(outcome.copy(name = node.relative))
                }
            } catch (_: Exception) {
                results.add(CopyOutcome(node.relative, target.path, CopyStatus.FAILED))
            }
        }
        return results
    }
}
