package app.cantas.jcversa.data

import android.content.ContentResolver
import android.net.Uri
import android.os.CancellationSignal
import android.provider.DocumentsContract

/** No merge, deletion or rollback. Only verified newly-created directories become capabilities. */
class SafTreeCopy(private val resolver: ContentResolver, destination: SafFiles, private val path: String,
    private val source: ImportSource, private val signal: CancellationSignal) {
    private val scope = destination.creationScope(path)
    private val planner = SafTreePlanner(source)
    fun prepare(requests: List<CopyRequest>, active: () -> Boolean): SafTreePlan {
        SafFileCopy(resolver, scope, source, signal).checkDestination(path, active)
        return planner.prepare(requests, active)
    }
    fun copy(plan: SafTreePlan, active: () -> Boolean, progress: (CopyProgress) -> Unit): List<CopyOutcome> {
        planner.validate(plan, active)
        SafFileCopy(resolver, scope, source, signal).checkDestination(path, active)
        val sourcePaths = plan.nodes.map { it.entry.path }
        val forbidden = sourcePaths.filter { it.startsWith("content://") }.map { Uri.parse(it) }
            .filter { it.authority == scope.tree.authority }.map { DocumentsContract.getDocumentId(it) }.toSet()
        val directories = mutableMapOf("" to path)
        val files = SafFileCopy(resolver, scope, source, signal, forbidden)
        return plan.nodes.mapIndexed { index, node ->
            val parentName = node.relative.substringBeforeLast('/', "")
            val parent = directories[parentName]
            if (!active() || signal.isCanceled || parent == null) {
                CopyOutcome(node.relative, parent ?: path, CopyStatus.NOT_ATTEMPTED)
            } else try {
                require(source.describe(node.entry.path) == node.entry)
                progress(CopyProgress(node.relative, index + 1, plan.nodes.size, 0, node.entry.size ?: 0))
                if (node.entry.directory) {
                    val created = SafFolders(resolver).create(scope, parent, node.entry.name, forbidden) { active() && !signal.isCanceled }
                    when (created) {
                        is FolderCreation.Created -> {
                            directories[node.relative] = created.path
                            CopyOutcome(node.relative, created.path, CopyStatus.DIRECTORY_CREATED, created = true)
                        }
                        is FolderCreation.Failed -> CopyOutcome(node.relative, parent, when (created.error) {
                            FolderError.EXISTS -> CopyStatus.EXISTS
                            FolderError.UNCERTAIN -> CopyStatus.UNCERTAIN
                            else -> if (!active() || signal.isCanceled) CopyStatus.CANCELLED else CopyStatus.FAILED
                        }, created = created.error == FolderError.UNCERTAIN)
                    }
                } else {
                    files.copy(listOf(node.entry), parent, active) {
                        progress(it.copy(name = node.relative, index = index + 1, total = plan.nodes.size))
                    }.single().copy(name = node.relative)
                }
            } catch (_: Exception) {
                CopyOutcome(node.relative, parent, if (!active() || signal.isCanceled) CopyStatus.CANCELLED else CopyStatus.FAILED)
            }
        }
    }
}
