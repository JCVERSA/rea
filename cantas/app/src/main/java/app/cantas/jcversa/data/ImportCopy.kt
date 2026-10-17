package app.cantas.jcversa.data

import java.io.File

/** Read capabilities only. Destination mutation is always delegated to the local exclusive backend. */
data class ImportEntry(val path: String, val name: String, val directory: Boolean, val size: Long?)
interface ImportSource {
    fun describe(path: String): ImportEntry
    fun children(path: String, limit: Int): List<ImportEntry>
    fun input(path: String): CopyInput
}
class ImportCopy(private val boundary: StorageBoundary, private val output: CopyStreams, private val source: ImportSource) {
    private fun active(running: () -> Boolean) { if (!running()) throw CopyPreparationStopped() }
    private fun parent(path: String): File {
        val file = boundary.resolve(path)
        require(file == File(path).absoluteFile && file.isDirectory)
        return file
    }
    fun prepare(requests: List<CopyRequest>, destination: String, running: () -> Boolean): TreeCopyPlan {
        require(requests.size in 1..LocalCopy.MAX_FILES)
        val dest = parent(destination)
        val nodes = mutableListOf<TreeCopyNode>()
        val seen = mutableSetOf<String>()
        val names = mutableSetOf<String>()
        fun walk(entry: ImportEntry, relative: String, depth: Int) {
            active(running)
            require(depth <= LocalTreeCopy.MAX_DEPTH && nodes.size < LocalTreeCopy.MAX_ENTRIES)
            require(LocalFolders.validName(entry.name) && seen.add(entry.path)) { "Duplicate/cyclic source" }
            nodes.add(TreeCopyNode(entry.path, relative, entry.directory))
            if (entry.directory) {
                val children = source.children(entry.path, LocalTreeCopy.MAX_ENTRIES - nodes.size)
                val siblings = mutableSetOf<String>()
                for (child in children) {
                    require(LocalFolders.validName(child.name) && siblings.add(child.name))
                    walk(child, relative + "/" + child.name, depth + 1)
                }
            }
        }
        for (request in requests) {
            active(running)
            require(LocalFolders.validName(request.name) && names.add(request.name))
            val entry = source.describe(request.path)
            require(entry.name == request.name) { "Source name changed" }
            walk(entry, request.name, 0)
        }
        active(running)
        return TreeCopyPlan(dest.path, nodes)
    }
    fun copy(plan: TreeCopyPlan, running: () -> Boolean, progress: (CopyProgress) -> Unit): List<CopyOutcome> {
        require(plan.nodes.size in 1..LocalTreeCopy.MAX_ENTRIES)
        val dest = parent(plan.destination)
        val known = mutableMapOf<String, Boolean>()
        plan.nodes.forEach {
            active(running)
            val parts = it.relative.split('/')
            require(parts.size <= LocalTreeCopy.MAX_DEPTH + 1 && parts.all { name -> LocalFolders.validName(name) })
            require(it.relative !in known)
            val parentName = it.relative.substringBeforeLast('/', "")
            require(parentName.isEmpty() || known[parentName] == true)
            val current = source.describe(it.source)
            require(current.name == parts.last() && current.directory == it.directory)
            known[it.relative] = it.directory
        }
        val created = mutableSetOf<String>()
        return plan.nodes.mapIndexed { index, node ->
            val target = File(dest, node.relative)
            val parentName = node.relative.substringBeforeLast('/', "")
            if (!running() || (parentName.isNotEmpty() && parentName !in created)) {
                CopyOutcome(node.relative, target.path, CopyStatus.NOT_ATTEMPTED)
            } else try {
                val directory = parent(target.parent!!)
                val entry = source.describe(node.source)
                require(entry.name == target.name && entry.directory == node.directory)
                progress(CopyProgress(node.relative, index + 1, plan.nodes.size, 0, entry.size ?: 0))
                if (!running()) CopyOutcome(node.relative, target.path, CopyStatus.NOT_ATTEMPTED)
                else if (node.directory) {
                    val result = LocalFolders(boundary).create(directory.path, target.name)
                    if (result is FolderCreation.Created) {
                        created.add(node.relative)
                        CopyOutcome(node.relative, target.path, CopyStatus.DIRECTORY_CREATED, created = true)
                    } else CopyOutcome(node.relative, target.path,
                        if ((result as FolderCreation.Failed).error == FolderError.EXISTS) CopyStatus.EXISTS else CopyStatus.FAILED)
                } else {
                    val streams = object : CopyStreams {
                        // LocalCopy's source path is a capability placeholder, NEVER opened locally.
                        override fun input(file: File) = source.input(node.source)
                        override fun exclusiveOutput(file: File) = output.exclusiveOutput(file)
                    }
                    LocalCopy(boundary, streams).copy(listOf(CopyRequest(boundary.root.path, target.name)), directory.path, running) {
                        progress(it.copy(name = node.relative, index = index + 1, total = plan.nodes.size))
                    }.single().copy(name = node.relative)
                }
            } catch (_: Exception) { CopyOutcome(node.relative, target.path, CopyStatus.FAILED) }
        }
    }
}
