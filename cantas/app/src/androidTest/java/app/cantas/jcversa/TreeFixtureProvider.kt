package app.cantas.jcversa

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.FileNotFoundException
import java.util.Locale

/** Test APK only. Implements document URI queries with synthetic opaque IDs, no personal files. */
class TreeFixtureProvider : ContentProvider() {
    companion object {
        const val AUTHORITY = "app.cantas.jcversa.test.treefixture"
        const val ROOT = "root:opaque/id"
        const val FOLDER = "folder:not/a/path"
        const val INNER = "child:unrelated!"
        val TREE: Uri = DocumentsContract.buildTreeDocumentUri(AUTHORITY, ROOT)
    }
    @Volatile private var slowStarted = false
    @Volatile private var slowCancelled = false
    override fun onCreate() = true
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?, cancellationSignal: android.os.CancellationSignal?): Cursor? {
        if (uri.lastPathSegment == "children" && DocumentsContract.getDocumentId(uri) == "slow-root") {
            slowStarted = true
            slowCancelled = false
            try {
                repeat(1000) { cancellationSignal?.throwIfCanceled(); android.os.SystemClock.sleep(10) }
            } catch (cancelled: android.os.OperationCanceledException) {
                slowCancelled = true
                throw cancelled
            }
        }
        return query(uri, projection, selection, selectionArgs, sortOrder)
    }
    override fun query(uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor? {
        val columns = projection ?: arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_SIZE)
        val result = MatrixCursor(columns)
        fun row(id: String, name: String, directory: Boolean = false, size: Long = 7) {
            val data = mapOf(Document.COLUMN_DOCUMENT_ID to id, Document.COLUMN_DISPLAY_NAME to name,
                Document.COLUMN_MIME_TYPE to if (directory) Document.MIME_TYPE_DIR else "text/plain", Document.COLUMN_SIZE to size)
            result.addRow(columns.map { data[it] }.toTypedArray())
        }
        val id = DocumentsContract.getDocumentId(uri)
        if (uri.lastPathSegment == "children") {
            when (id) {
                ROOT -> {
                    row(FOLDER, "Nested", true)
                    row("text", "Résumé.txt")
                    row("hidden", ".secret")
                    for (i in 0..1004) row("file-$i", String.format(Locale.ROOT, "%04d.txt", i), size = i.toLong() + 10)
                }
                FOLDER -> row(INNER, "inner.txt")
                "null-root" -> { result.close(); return null }
            }
        } else when (id) {
            ROOT, "null-root", "slow-root" -> row(id, "Synthetic tree", true)
            FOLDER -> row(id, "Nested", true)
            INNER -> row(id, "inner.txt")
            "text" -> row(id, "Résumé.txt")
        }
        return result
    }
    override fun call(method: String, arg: String?, extras: android.os.Bundle?): android.os.Bundle? {
        if (method == "slow-state") return android.os.Bundle().apply {
            putBoolean("started", slowStarted); putBoolean("cancelled", slowCancelled)
        }
        if (method !in listOf("grant-read", "revoke-read")) return super.call(method, arg, extras)
        val identity = android.os.Binder.clearCallingIdentity()
        try {
            if (method == "revoke-read") {
                context!!.revokeUriPermission(TREE, android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION)
                return android.os.Bundle()
            }
            context!!.grantUriPermission("app.cantas.jcversa", TREE,
                android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    android.content.Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION or
                    android.content.Intent.FLAG_GRANT_PREFIX_URI_PERMISSION)
        } finally { android.os.Binder.restoreCallingIdentity(identity) }
        return android.os.Bundle()
    }
    override fun getType(uri: Uri): String = if (DocumentsContract.getDocumentId(uri) in listOf(ROOT, FOLDER)) Document.MIME_TYPE_DIR else "text/plain"
    override fun openFile(uri: Uri, mode: String): ParcelFileDescriptor {
        if (mode != "r" || DocumentsContract.getDocumentId(uri) !in listOf(INNER, "text")) throw FileNotFoundException()
        val pipe = ParcelFileDescriptor.createPipe()
        ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]).use { it.write("fixture".toByteArray()) }
        return pipe[0]
    }
    override fun insert(uri: Uri, values: ContentValues?): Uri? = throw UnsupportedOperationException()
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int = throw UnsupportedOperationException()
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = throw UnsupportedOperationException()
}
