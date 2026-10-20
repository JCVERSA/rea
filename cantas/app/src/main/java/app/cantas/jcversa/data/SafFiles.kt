package app.cantas.jcversa.data

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.IOException
import android.os.CancellationSignal
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/** Read-only SAF tree. Document IDs are opaque; never converted to paths or split to find parents.
 * Only the root and documents actually returned by its child queries are accepted as locations.
 * Instances are session-scoped; the browser serializes listing calls.
 */
class SafFiles(private val resolver: ContentResolver, val tree: Uri) : ReadOnlyStorage {
    init { require(tree.scheme == "content" && !tree.authority.isNullOrBlank() && DocumentsContract.isTreeUri(tree)) }
    override val root = DocumentsContract.buildDocumentUriUsingTree(tree, DocumentsContract.getTreeDocumentId(tree)).toString()
    private data class Node(val parent: String?, val name: String, val directory: Boolean, val mime: String)
    private val nodes = mutableMapOf(root to Node(null, DocumentsContract.getTreeDocumentId(tree), true, Document.MIME_TYPE_DIR))
    private val columns = arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_SIZE)
    @Synchronized private fun node(path: String): Node = nodes[path] ?: throw SecurityException("Unknown document")
    internal fun creationParent(path: String): Uri {
        require(node(path).directory)
        return Uri.parse(path)
    }
    override fun label(path: String): String = node(path).name
    override fun parent(path: String): String? = node(path).parent
    override fun readTarget(path: String): ReadTarget {
        val entry = node(path)
        require(!entry.directory)
        val mime = resolver.getType(Uri.parse(path)) ?: entry.mime
        require(mime != Document.MIME_TYPE_DIR)
        return ReadTarget(path, mime)
    }
    override fun list(path: String, options: BrowseOptions, active: () -> Boolean): FolderSnapshot =
        scan(path, options, active, null)
    /** Run off main. Cancellation reaches ContentResolver queries as well as the row loop. */
    suspend fun listCancellable(path: String, options: BrowseOptions, active: () -> Boolean): FolderSnapshot =
        cancellableProviderCall { signal -> scan(path, options, active, signal) }
    private fun scan(path: String, options: BrowseOptions, active: () -> Boolean, signal: CancellationSignal?): FolderSnapshot {
        checkListingActive(active)
        val selected = EntrySelection(options)
        require(node(path).directory)
        val ancestors = linkedMapOf<String, Node>()
        var ancestor: String? = path
        while (ancestor != null) {
            val current = node(ancestor)
            ancestors[ancestor] = current
            ancestor = current.parent
        }
        // Refresh the current name and detect removed/replaced folders, including the root.
        val title = resolver.query(Uri.parse(path), columns, null, null, null, signal)?.use { cursor ->
            if (!cursor.moveToFirst()) throw IOException("Document unavailable")
            val mime = cursor.required(Document.COLUMN_MIME_TYPE)
            if (mime != Document.MIME_TYPE_DIR) throw IOException("Not a folder")
            cursor.required(Document.COLUMN_DISPLAY_NAME)
        } ?: throw IOException("Provider unavailable")
        val children = DocumentsContract.buildChildDocumentsUriUsingTree(tree, DocumentsContract.getDocumentId(Uri.parse(path)))
        resolver.query(children, columns, null, null, null, signal)?.use { cursor ->
            while (cursor.moveToNext()) {
                checkListingActive(active)
                val name = cursor.required(Document.COLUMN_DISPLAY_NAME)
                if (!selected.matches(name)) continue
                val id = cursor.required(Document.COLUMN_DOCUMENT_ID)
                val child = DocumentsContract.buildDocumentUriUsingTree(tree, id).toString()
                if (child in ancestors) throw IOException("Cyclic document provider")
                val mime = cursor.required(Document.COLUMN_MIME_TYPE)
                val directory = mime == Document.MIME_TYPE_DIR
                val sizeColumn = cursor.getColumnIndex(Document.COLUMN_SIZE)
                val size = if (directory || sizeColumn < 0 || cursor.isNull(sizeColumn)) 0 else cursor.getLong(sizeColumn).coerceAtLeast(0)
                selected.add(FileEntry(child, name, directory, size))
            }
            if (cursor.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false)) {
                throw IOException("Provider is still loading; refresh required")
            }
        } ?: throw IOException("Provider unavailable")
        checkListingActive(active)
        val snapshot = selected.snapshot(path, title)
        synchronized(this) {
            // Retain only ancestry and visible children, not an ever-growing directory index.
            nodes.clear()
            nodes.putAll(ancestors)
            nodes[path] = nodes.getValue(path).copy(name = title)
            snapshot.entries.forEach { entry ->
                // getType is deliberately deferred until opening; no per-child binder call here.
                nodes[entry.path] = Node(path, entry.name, entry.directory, "application/octet-stream")
            }
        }
        return snapshot
    }
    /** Rebuild a saved folder's ancestry by checking real child membership, never URI prefixes.
     * Retains only <=33 ancestors. Each membership scan stops at100,000 rows or cancellation.
     */
    suspend fun restoreFolderCancellable(chain: List<String>, active: () -> Boolean) {
        cancellableProviderCall { signal ->
            require(chain.size in 1..FavoritePolicy.MAX_ANCESTRY && chain.first() == root && chain.distinct().size == chain.size)
            chain.forEach { location ->
                val uri = Uri.parse(location)
                require(uri.authority == tree.authority && DocumentsContract.isTreeUri(uri))
                require(DocumentsContract.getTreeDocumentId(uri) == DocumentsContract.getTreeDocumentId(tree))
                require(DocumentsContract.buildDocumentUriUsingTree(tree, DocumentsContract.getDocumentId(uri)).toString() == location)
            }
            val restored = linkedMapOf<String, Node>()
            for ((index, location) in chain.withIndex()) {
                checkListingActive(active)
                val entry = if (index == 0) {
                    resolver.query(Uri.parse(root), columns, null, null, null, signal)?.use {
                        if (!it.moveToFirst() || it.required(Document.COLUMN_MIME_TYPE) != Document.MIME_TYPE_DIR) throw IOException("Missing root")
                        Node(null, it.required(Document.COLUMN_DISPLAY_NAME), true, Document.MIME_TYPE_DIR)
                    } ?: throw IOException("Provider unavailable")
                } else {
                    val parent = chain[index - 1]
                    val children = DocumentsContract.buildChildDocumentsUriUsingTree(tree, DocumentsContract.getDocumentId(Uri.parse(parent)))
                    var match: Node? = null
                    resolver.query(children, columns, null, null, null, signal)?.use { cursor ->
                        var count = 0
                        while (cursor.moveToNext()) {
                            checkListingActive(active)
                            if (++count > 100000) throw IOException("Membership scan limit")
                            val child = DocumentsContract.buildDocumentUriUsingTree(tree, cursor.required(Document.COLUMN_DOCUMENT_ID)).toString()
                            if (child == location) {
                                if (match != null || cursor.required(Document.COLUMN_MIME_TYPE) != Document.MIME_TYPE_DIR) throw IOException("Invalid folder membership")
                                match = Node(parent, cursor.required(Document.COLUMN_DISPLAY_NAME), true, Document.MIME_TYPE_DIR)
                            }
                        }
                        if (cursor.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false)) throw IOException("Provider loading")
                    } ?: throw IOException("Provider unavailable")
                    match ?: throw IOException("Saved folder no longer belongs to this parent")
                }
                restored[location] = entry
            }
            checkListingActive(active)
            synchronized(this) { nodes.clear(); nodes.putAll(restored) }
        }
    }
    private fun Cursor.required(column: String): String {
        val index = getColumnIndex(column)
        if (index < 0 || isNull(index)) throw IOException("Incomplete document metadata")
        return getString(index)
    }
}

/** Register cancellation before entering potentially blocking binder/provider work.
 * Providers must cooperate with CancellationSignal; a misbehaving provider cannot be forcibly killed.
 */
internal suspend fun <T> cancellableProviderCall(block: (CancellationSignal) -> T): T =
    suspendCancellableCoroutine { continuation ->
        val signal = CancellationSignal()
        continuation.invokeOnCancellation { signal.cancel() }
        try { continuation.resume(block(signal)) }
        catch (error: Exception) { continuation.resumeWithException(error) }
    }
