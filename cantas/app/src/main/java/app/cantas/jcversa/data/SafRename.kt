package app.cantas.jcversa.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.IOException

enum class RenameError { INVALID_NAME, EXISTS, ACCESS_DENIED, UNAVAILABLE, UNSUPPORTED, UNCERTAIN }
sealed interface RenameResult {
    data class Renamed(val path: String) : RenameResult
    data class Failed(val error: RenameError) : RenameResult
}

/** Single discovered child only. No overwrite/delete/rollback; provider rename is not a transaction. */
class SafRename(private val resolver: ContentResolver) {
    private data class Metadata(val name: String, val mime: String, val size: Long?)
    fun rename(source: SafFiles, parentPath: String, entry: FileEntry, name: String, active: () -> Boolean = { true }): RenameResult {
        if (!LocalFolders.validName(name) || name == entry.name) return RenameResult.Failed(RenameError.INVALID_NAME)
        var invoked = false
        fun running() { if (!active()) throw IOException("Cancelled") }
        fun granted() = resolver.persistedUriPermissions.any { it.uri == source.tree && it.isReadPermission && it.isWritePermission }
        val columns = arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_SIZE, Document.COLUMN_FLAGS)
        try {
            running()
            if (!granted()) return RenameResult.Failed(RenameError.ACCESS_DENIED)
            val parent = source.creationParent(parentPath)
            require(source.parent(entry.path) == parentPath)
            val selected = Uri.parse(entry.path)
            val originalId = DocumentsContract.getDocumentId(selected)
            require(selected == DocumentsContract.buildDocumentUriUsingTree(source.tree, originalId))
            require(originalId != DocumentsContract.getTreeDocumentId(source.tree) && originalId != DocumentsContract.getDocumentId(parent))
            fun scan(): Map<String, Metadata> {
                val found = linkedMapOf<String, Metadata>()
                val uri = DocumentsContract.buildChildDocumentsUriUsingTree(source.tree, DocumentsContract.getDocumentId(parent))
                resolver.query(uri, columns, null, null, null)?.use { c ->
                    var units = 0L
                    while (c.moveToNext()) {
                        running()
                        require(found.size < SafFolders.MAX_ROWS)
                        val id = c.getString(0) ?: throw IOException("Missing ID")
                        val title = c.getString(1) ?: throw IOException("Missing title")
                        val mime = c.getString(2) ?: throw IOException("Missing type")
                        units += id.length.toLong() + title.length + mime.length
                        require(id.length <= 4096 && title.length <= 4096 && mime.length <= 4096 && units <= 4 * 1024 * 1024)
                        require(found.put(id, Metadata(title, mime, if (c.isNull(3)) null else c.getLong(3))) == null)
                    }
                    require(!c.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false))
                } ?: throw IOException("No listing")
                return found
            }
            val before = scan()
            val original = before[originalId] ?: throw IOException("Missing source")
            require(original.name == entry.name && (original.mime == Document.MIME_TYPE_DIR) == entry.directory)
            if (!entry.directory && original.size != null) require(original.size == entry.bytes)
            if (before.any { (id, metadata) -> id != originalId && metadata.name.equals(name, ignoreCase = true) })
                return RenameResult.Failed(RenameError.EXISTS)
            resolver.query(selected, columns, null, null, null)?.use { c ->
                require(c.moveToFirst() && c.getString(0) == originalId && c.getString(1) == original.name && c.getString(2) == original.mime)
                require(!c.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false))
                if (c.isNull(4) || c.getInt(4) and Document.FLAG_SUPPORTS_RENAME == 0) return RenameResult.Failed(RenameError.UNSUPPORTED)
            } ?: throw IOException("No source")
            running()
            if (!granted()) return RenameResult.Failed(RenameError.ACCESS_DENIED)
            invoked = true
            val renamed = DocumentsContract.renameDocument(resolver, selected, name) ?: throw IOException("No result")
            require(renamed.toString().length <= 16384)
            val id = DocumentsContract.getDocumentId(renamed)
            require(id.length <= 4096 && renamed == DocumentsContract.buildDocumentUriUsingTree(source.tree, id))
            require(id != DocumentsContract.getTreeDocumentId(source.tree) && id != DocumentsContract.getDocumentId(parent))
            require(id == originalId || id !in before)
            running()
            val after = scan()
            require(after[id] == original.copy(name = name))
            require(id == originalId || originalId !in after)
            require(after.values.count { it.name.equals(name, ignoreCase = true) } == 1)
            require(before.filterKeys { it != originalId }.all { (key, value) -> after[key] == value })
            return RenameResult.Renamed(renamed.toString())
        } catch (_: SecurityException) {
            return RenameResult.Failed(if (invoked) RenameError.UNCERTAIN else RenameError.ACCESS_DENIED)
        } catch (_: Exception) {
            return RenameResult.Failed(if (invoked) RenameError.UNCERTAIN else RenameError.UNAVAILABLE)
        }
    }
}
