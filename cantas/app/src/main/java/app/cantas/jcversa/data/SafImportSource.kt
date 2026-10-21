package app.cantas.jcversa.data

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.CancellationSignal
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.IOException

/** Read-only SAF source for local or SAF destinations. Never requests a write grant or calls provider mutation methods. */
class SafImportSource(private val resolver: ContentResolver, private val tree: Uri,
    requests: List<CopyRequest>, private val signal: CancellationSignal, private val active: () -> Boolean) : ImportSource {
    companion object { const val UNKNOWN_SIZE_LIMIT = 512L * 1024 * 1024 }
    private val discovered = requests.map { it.path }.toMutableSet()
    private val columns = arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME,
        Document.COLUMN_MIME_TYPE, Document.COLUMN_SIZE, Document.COLUMN_FLAGS)
    init {
        require(tree.scheme == "content" && DocumentsContract.isTreeUri(tree))
        requests.forEach { scoped(it.path) }
    }
    private fun running() { signal.throwIfCanceled(); if (!active()) throw CopyPreparationStopped() }
    private fun scoped(path: String): Uri {
        val uri = Uri.parse(path)
        require(uri.scheme == "content" && uri.authority == tree.authority && DocumentsContract.isTreeUri(uri))
        require(DocumentsContract.getTreeDocumentId(uri) == DocumentsContract.getTreeDocumentId(tree))
        require(DocumentsContract.buildDocumentUriUsingTree(tree, DocumentsContract.getDocumentId(uri)) == uri)
        return uri
    }
    private fun known(path: String): Uri { require(path in discovered); return scoped(path) }
    private fun Cursor.required(column: String): String {
        val index = getColumnIndex(column)
        if (index < 0 || isNull(index)) throw IOException("Incomplete metadata")
        return getString(index)
    }
    private fun entry(cursor: Cursor): ImportEntry {
        val id = cursor.required(Document.COLUMN_DOCUMENT_ID)
        val name = cursor.required(Document.COLUMN_DISPLAY_NAME)
        val mime = cursor.required(Document.COLUMN_MIME_TYPE)
        val flag = cursor.getColumnIndex(Document.COLUMN_FLAGS)
        require(flag < 0 || cursor.isNull(flag) || (cursor.getInt(flag) and Document.FLAG_VIRTUAL_DOCUMENT) == 0) { "Virtual document" }
        val size = cursor.getColumnIndex(Document.COLUMN_SIZE)
        val bytes = if (size < 0 || cursor.isNull(size)) null else cursor.getLong(size).takeIf { it >= 0 }
        return ImportEntry(DocumentsContract.buildDocumentUriUsingTree(tree, id).toString(), name, mime == Document.MIME_TYPE_DIR, bytes)
    }
    override fun describe(path: String): ImportEntry {
        running()
        val uri = known(path)
        return resolver.query(uri, columns, null, null, null, signal)?.use {
            if (!it.moveToFirst()) throw IOException("Document removed")
            val result = entry(it)
            require(result.path == path)
            if (it.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false)) throw IOException("Provider loading")
            result
        } ?: throw IOException("Provider unavailable")
    }
    override fun children(path: String, limit: Int): List<ImportEntry> {
        running()
        require(describe(path).directory)
        val uri = DocumentsContract.buildChildDocumentsUriUsingTree(tree, DocumentsContract.getDocumentId(known(path)))
        val entries = mutableListOf<ImportEntry>()
        resolver.query(uri, columns, null, null, null, signal)?.use {
            while (it.moveToNext()) {
                running()
                require(entries.size < limit) { "Tree limit" }
                entries.add(entry(it))
            }
            if (it.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false)) throw IOException("Provider loading")
        } ?: throw IOException("Provider unavailable")
        discovered.addAll(entries.map { it.path })
        return entries
    }
    override fun input(path: String): CopyInput {
        val before = describe(path)
        require(!before.directory)
        val asset = resolver.openAssetFileDescriptor(known(path), "r", signal) ?: throw IOException("No stream")
        try {
            val stream = asset.createInputStream()
            return CopyInput(stream, before.size ?: UNKNOWN_SIZE_LIMIT, {
                running()
                describe(path) == before
            }, exactSize = before.size != null)
        } catch (error: Exception) { asset.close(); throw error }
    }
}
