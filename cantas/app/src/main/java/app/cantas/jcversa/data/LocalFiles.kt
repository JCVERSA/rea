package app.cantas.jcversa.data

import java.io.File
import java.util.Locale

/** Canonical boundary, including symlinks. No mutation API in this milestone. */
class StorageBoundary(root: File) {
    val root: File = root.canonicalFile
    fun resolve(path: String): File {
        val file = File(path).canonicalFile
        require(file == root || file.path.startsWith(root.path.trimEnd(File.separatorChar) + File.separator)) {
            "Outside selected storage"
        }
        return file
    }
    fun parent(path: String): File? = resolve(path).let { if (it == root) null else resolve(it.parent!!) }
}

data class FileEntry(val path: String, val name: String, val directory: Boolean, val bytes: Long)
data class FolderSnapshot(val path: String, val entries: List<FileEntry>, val truncated: Boolean)

class LocalFiles(private val boundary: StorageBoundary) {
    companion object { const val MAX_VISIBLE_ENTRIES = 1000 }
    /** Call on Dispatchers.IO. File.listFiles still enumerates the folder; not true pagination. */
    fun list(path: String): FolderSnapshot {
        val folder = boundary.resolve(path)
        require(folder.isDirectory) { "Not a directory" }
        val files = folder.listFiles() ?: throw java.io.IOException("Folder unavailable")
        val entries = files.asSequence().filter { !it.name.startsWith('.') }
            .take(MAX_VISIBLE_ENTRIES + 1).mapNotNull { child ->
                // Ignore an inaccessible or out-of-bound symlink, not the entire directory.
                runCatching {
                    val safe = boundary.resolve(child.path)
                    FileEntry(safe.path, child.name, safe.isDirectory, if (safe.isFile) safe.length() else 0)
                }.getOrNull()
            }.toList()
        return FolderSnapshot(folder.path, entries.take(MAX_VISIBLE_ENTRIES)
            .sortedWith(compareByDescending<FileEntry> { it.directory }.thenBy { it.name.lowercase(Locale.ROOT) }),
            entries.size > MAX_VISIBLE_ENTRIES)
    }
}
