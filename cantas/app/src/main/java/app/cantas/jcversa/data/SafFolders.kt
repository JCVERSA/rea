package app.cantas.jcversa.data

import android.content.ContentResolver
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.IOException

/** Single-folder creation only. No rename, replacement, deletion or rollback of provider data. */
class SafFolders(private val resolver: ContentResolver) {
    companion object { const val MAX_ROWS = 100000 }
    fun create(source: SafFiles, path: String, name: String, forbiddenIds: Set<String> = emptySet(), active: () -> Boolean = { true }): FolderCreation {
        if (!LocalFolders.validName(name)) return FolderCreation.Failed(FolderError.INVALID_NAME)
        var invoked = false
        fun checkActive() { if (!active()) throw IOException("Cancelled") }
        try {
            checkActive()
            if (resolver.persistedUriPermissions.none { it.uri == source.tree && it.isReadPermission && it.isWritePermission })
                return FolderCreation.Failed(FolderError.ACCESS_DENIED)
            val parent = source.creationParent(path) // Only a directory discovered by this scoped browser.
            val fields = arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_FLAGS)
            resolver.query(parent, fields, null, null, null)?.use { c ->
                require(c.moveToFirst() && c.getString(0) == DocumentsContract.getDocumentId(parent) && c.getString(2) == Document.MIME_TYPE_DIR)
                if (c.isNull(3) || c.getInt(3) and Document.FLAG_DIR_SUPPORTS_CREATE == 0) return FolderCreation.Failed(FolderError.UNSUPPORTED)
            } ?: throw IOException("Parent unavailable")
            val children = DocumentsContract.buildChildDocumentsUriUsingTree(source.tree, DocumentsContract.getDocumentId(parent))
            fun scan(): Map<String, Pair<String, String>> {
                val found = linkedMapOf<String, Pair<String, String>>()
                resolver.query(children, fields, null, null, null)?.use { c ->
                    var rows = 0
                    var units = 0L
                    while (c.moveToNext()) {
                        checkActive()
                        if (++rows > MAX_ROWS) throw IOException("Scan limit")
                        val id = c.getString(0) ?: throw IOException("Missing ID")
                        val title = c.getString(1) ?: throw IOException("Missing title")
                        val mime = c.getString(2) ?: throw IOException("Missing type")
                        units += id.length.toLong() + title.length + mime.length
                        if (id.length > 4096 || title.length > 4096 || mime.length > 4096 || units > 4 * 1024 * 1024) throw IOException("Metadata limit")
                        if (found.put(id, title to mime) != null) throw IOException("Duplicate ID")
                    }
                    if (c.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false)) throw IOException("Loading")
                } ?: throw IOException("Children unavailable")
                return found
            }
            val before = scan()
            if (before.values.any { it.first.equals(name, ignoreCase = true) }) return FolderCreation.Failed(FolderError.EXISTS)
            checkActive()
            // Recheck access immediately before invoking the mutation. Never request it implicitly.
            if (resolver.persistedUriPermissions.none { it.uri == source.tree && it.isReadPermission && it.isWritePermission })
                return FolderCreation.Failed(FolderError.ACCESS_DENIED)
            invoked = true
            val created = DocumentsContract.createDocument(resolver, parent, Document.MIME_TYPE_DIR, name)
                ?: throw IOException("No creation result")
            val id = DocumentsContract.getDocumentId(created)
            require(id.length <= 4096 && created.toString().length <= 16384 && created == DocumentsContract.buildDocumentUriUsingTree(source.tree, id))
            require(id != DocumentsContract.getDocumentId(parent) && id !in before && id !in forbiddenIds && id != DocumentsContract.getTreeDocumentId(source.tree))
            checkActive()
            val after = scan()
            require(after[id] == (name to Document.MIME_TYPE_DIR))
            require(after.values.count { it.first.equals(name, ignoreCase = true) } == 1)
            // Detect observed changes, not a transaction or proof against hostile/concurrent providers.
            require(before.all { (key, value) -> after[key] == value })
            source.rememberCreatedFolder(path, created, name)
            return FolderCreation.Created(created.toString())
        } catch (_: SecurityException) {
            return FolderCreation.Failed(if (invoked) FolderError.UNCERTAIN else FolderError.ACCESS_DENIED)
        } catch (_: Exception) {
            return FolderCreation.Failed(if (invoked) FolderError.UNCERTAIN else FolderError.UNAVAILABLE)
        }
    }
}
