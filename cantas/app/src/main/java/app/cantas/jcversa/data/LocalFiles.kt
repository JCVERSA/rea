package app.cantas.jcversa.data

import java.io.File

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
data class FolderSnapshot(val path: String, val entries: List<FileEntry>, val truncated: Boolean, val title: String? = null)
enum class FileSort { NAME, NAME_DESC, SIZE_DESC }
data class BrowseOptions(val query: String = "", val showHidden: Boolean = false,
    val sort: FileSort = FileSort.NAME, val limit: Int = 1000)

class LocalFiles(private val boundary: StorageBoundary) : ReadOnlyStorage {
    companion object { const val MAX_VISIBLE_ENTRIES = 1000 }
    override val root: String get() = boundary.root.path
    override fun parent(path: String): String? = boundary.parent(path)?.path
    override fun readTarget(path: String): ReadTarget {
        val file = boundary.resolve(path)
        require(file.isFile && file.canRead())
        return ReadTarget(file.path)
    }
    /** Full name enumeration is still required by File.listFiles; not streaming pagination. */
    override fun list(path: String, options: BrowseOptions, active: () -> Boolean): FolderSnapshot {
        checkListingActive(active)
        val selected = EntrySelection(options)
        val folder = boundary.resolve(path)
        require(folder.isDirectory) { "Not a directory" }
        val files = folder.listFiles() ?: throw java.io.IOException("Folder unavailable")
        for (child in files) {
            checkListingActive(active)
            if (!selected.matches(child.name)) continue
            val entry = runCatching {
                val safe = boundary.resolve(child.path)
                FileEntry(safe.path, child.name, safe.isDirectory, if (safe.isFile) safe.length() else 0)
            }.getOrNull() ?: continue
            selected.add(entry)
        }
        checkListingActive(active)
        return selected.snapshot(folder.path)
    }
}
