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
enum class FileSort { NAME, NAME_DESC, SIZE_DESC }
data class BrowseOptions(val query: String = "", val showHidden: Boolean = false,
    val sort: FileSort = FileSort.NAME, val limit: Int = 1000)

class LocalFiles(private val boundary: StorageBoundary) {
    companion object { const val MAX_VISIBLE_ENTRIES = 1000 }
    /** IO only. Full name enumeration is still required by File.listFiles; not streaming pagination. */
    fun list(path: String, options: BrowseOptions = BrowseOptions(), active: () -> Boolean = { true }): FolderSnapshot {
        if (!active()) throw java.util.concurrent.CancellationException("Listing superseded")
        require(options.limit in 1..100000)
        val folder = boundary.resolve(path)
        require(folder.isDirectory) { "Not a directory" }
        val files = folder.listFiles() ?: throw java.io.IOException("Folder unavailable")
        val query = options.query.trim().lowercase(Locale.ROOT)
        val names = compareBy<FileEntry> { it.name.lowercase(Locale.ROOT) }.thenBy { it.name }
        val order = compareByDescending<FileEntry> { it.directory }.then(
            when(options.sort) {
                FileSort.NAME -> names
                FileSort.NAME_DESC -> names.reversed()
                FileSort.SIZE_DESC -> compareByDescending<FileEntry> { it.bytes }.then(names)
            })
        // Keep only the best limit+1 entries, without retaining metadata for the entire folder.
        // Java still allocates the complete File[] during enumeration; this is not streaming IO.
        val selected = java.util.PriorityQueue<FileEntry>(minOf(options.limit + 1, 1024), order.reversed())
        for (child in files) {
            if (!active()) throw java.util.concurrent.CancellationException("Listing superseded")
            if ((!options.showHidden && child.name.startsWith('.')) ||
                (query.isNotEmpty() && !child.name.lowercase(Locale.ROOT).contains(query))) continue
            val entry = runCatching {
                val safe = boundary.resolve(child.path)
                FileEntry(safe.path, child.name, safe.isDirectory, if (safe.isFile) safe.length() else 0)
            }.getOrNull() ?: continue
            selected.add(entry)
            if (selected.size > options.limit + 1) selected.poll()
        }
        val result = selected.toList().sortedWith(order)
        return FolderSnapshot(folder.path, result.take(options.limit), result.size > options.limit)
    }
}
