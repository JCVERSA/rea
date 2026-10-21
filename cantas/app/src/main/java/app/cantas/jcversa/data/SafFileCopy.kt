package app.cantas.jcversa.data

import android.content.ContentResolver
import android.net.Uri
import android.os.CancellationSignal
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import java.io.IOException
import java.security.MessageDigest

/** Files only. Provider create is not an exclusive-create transaction: uncertain outputs stay. */
class SafFileCopy(private val resolver: ContentResolver, private val destination: SafFiles,
    private val source: ImportSource, private val signal: CancellationSignal, private val forbiddenSources: List<String> = emptyList()) {
    private val columns = arrayOf(Document.COLUMN_DOCUMENT_ID, Document.COLUMN_DISPLAY_NAME, Document.COLUMN_MIME_TYPE, Document.COLUMN_FLAGS)
    internal fun checkDestination(path: String, running: () -> Boolean) {
        checkRunning(running); grant()
        val parent = destination.creationParent(path)
        resolver.query(parent, columns, null, null, null, signal)?.use { c ->
            require(c.moveToFirst() && c.getString(0) == DocumentsContract.getDocumentId(parent) && c.getString(2) == Document.MIME_TYPE_DIR)
            require(!c.isNull(3) && c.getInt(3) and Document.FLAG_DIR_SUPPORTS_CREATE != 0)
        } ?: throw IOException("No parent")
        children(parent, running)
    }
    private fun grant() = check(resolver.persistedUriPermissions.any { it.uri == destination.tree && it.isReadPermission && it.isWritePermission })
    private fun children(parent: Uri, running: () -> Boolean): Map<String, Pair<String, String>> {
        val uri = DocumentsContract.buildChildDocumentsUriUsingTree(destination.tree, DocumentsContract.getDocumentId(parent))
        val found = linkedMapOf<String, Pair<String, String>>()
        resolver.query(uri, columns, null, null, null, signal)?.use { c ->
            var units = 0L
            while (c.moveToNext()) {
                checkRunning(running)
                require(found.size < SafFolders.MAX_ROWS)
                val id = c.getString(0) ?: throw IOException("Missing ID")
                val name = c.getString(1) ?: throw IOException("Missing name")
                val mime = c.getString(2) ?: throw IOException("Missing MIME")
                units += id.length.toLong() + name.length + mime.length
                require(id.length <= 4096 && name.length <= 4096 && mime.length <= 4096 && units <= 4 * 1024 * 1024)
                require(found.put(id, name to mime) == null)
            }
            require(!c.extras.getBoolean(DocumentsContract.EXTRA_LOADING, false))
        } ?: throw IOException("No listing")
        return found
    }
    private fun checkRunning(running: () -> Boolean) { signal.throwIfCanceled(); if (!running()) throw CopyPreparationStopped() }
    /** Whole-batch read-only preflight. No directory or virtual-source fallback. */
    fun prepare(requests: List<CopyRequest>, path: String, running: () -> Boolean): List<ImportEntry> {
        require(requests.size in 1..LocalCopy.MAX_FILES)
        checkRunning(running); grant()
        val parent = destination.creationParent(path)
        resolver.query(parent, columns, null, null, null, signal)?.use { c ->
            require(c.moveToFirst() && c.getString(0) == DocumentsContract.getDocumentId(parent) && c.getString(2) == Document.MIME_TYPE_DIR)
            require(!c.isNull(3) && c.getInt(3) and Document.FLAG_DIR_SUPPORTS_CREATE != 0)
        } ?: throw IOException("No parent")
        children(parent, running)
        val paths = mutableSetOf<String>()
        val names = mutableSetOf<String>()
        return requests.map {
            checkRunning(running)
            require(it.path.length <= 16384 && LocalFolders.validName(it.name) && paths.add(it.path) && names.add(it.name.lowercase(java.util.Locale.ROOT)))
            source.describe(it.path).also { entry -> require(!entry.directory && entry.name == it.name) }
        }
    }
    fun copy(entries: List<ImportEntry>, path: String, running: () -> Boolean, progress: (CopyProgress) -> Unit): List<CopyOutcome> {
        require(entries.size in 1..LocalCopy.MAX_FILES)
        val parent = destination.creationParent(path)
        val buffer = ByteArray(64 * 1024)
        return entries.mapIndexed { index, entry ->
            var attempted = false
            var target = path
            var bytes = 0L
            var status = CopyStatus.FAILED
            try {
                checkRunning(running); grant()
                require(source.describe(entry.path) == entry && !entry.directory && LocalFolders.validName(entry.name))
                val before = children(parent, running)
                if (before.values.any { it.first.equals(entry.name, ignoreCase = true) }) throw CopyCollision()
                source.input(entry.path).streamAndMetadata { input ->
                    require(input.size >= 0)
                    checkRunning(running); grant()
                    attempted = true
                    val mime = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(entry.name.substringAfterLast('.', "").lowercase(java.util.Locale.ROOT)) ?: "application/octet-stream"
                    val uri = DocumentsContract.createDocument(resolver, parent, mime, entry.name) ?: throw IOException("No output URI")
                    require(uri.toString().length <= 16384)
                    target = uri.toString()
                    val id = DocumentsContract.getDocumentId(uri)
                    require(id.length <= 4096 && uri == DocumentsContract.buildDocumentUriUsingTree(destination.tree, id) && id != DocumentsContract.getDocumentId(parent) && id !in before)
                    require((entries.map { it.path } + forbiddenSources).none { original -> original.startsWith("content://") && Uri.parse(original).authority == uri.authority && DocumentsContract.getDocumentId(Uri.parse(original)) == id })
                    val after = children(parent, running)
                    require(after[id]?.first == entry.name && after[id]?.second != Document.MIME_TYPE_DIR)
                    require(after.values.count { it.first.equals(entry.name, ignoreCase = true) } == 1 && before.all { (key, value) -> after[key] == value })
                    val digest = MessageDigest.getInstance("SHA-256")
                    var lastProgress = 0L
                    resolver.openOutputStream(uri, "w")?.use { writer ->
                        while (bytes < input.size) {
                            checkRunning(running)
                            val count = input.stream.read(buffer, 0, minOf(buffer.size.toLong(), input.size - bytes).toInt())
                            if (count < 0) break
                            if (count == 0) throw IOException("No progress")
                            checkRunning(running)
                            writer.write(buffer, 0, count); digest.update(buffer, 0, count); bytes += count
                            val now = System.nanoTime()
                            if (now - lastProgress >= 100_000_000L) {
                                progress(CopyProgress(entry.name, index + 1, entries.size, bytes, input.size)); lastProgress = now
                            }
                        }
                        checkRunning(running)
                        val exact = input.stream.read() == -1 && (!input.exactSize || bytes == input.size)
                        writer.flush()
                        status = if (exact && input.unchanged()) CopyStatus.COPIED else CopyStatus.SOURCE_CHANGED
                    } ?: throw IOException("No output stream")
                    if (status == CopyStatus.COPIED) {
                        val verify = MessageDigest.getInstance("SHA-256")
                        var read = 0L
                        resolver.openInputStream(uri)?.use { reader ->
                            while (read < bytes) {
                                checkRunning(running)
                                val count = reader.read(buffer, 0, minOf(buffer.size.toLong(), bytes - read).toInt())
                                if (count <= 0) throw IOException("Truncated verification")
                                verify.update(buffer, 0, count); read += count
                            }
                            checkRunning(running)
                            require(reader.read() == -1)
                        } ?: throw IOException("No verification stream")
                        require(digest.digest().contentEquals(verify.digest()))
                        val final = children(parent, running)
                        require(final[id]?.first == entry.name && final[id]?.second != Document.MIME_TYPE_DIR && before.all { (key, value) -> final[key] == value })
                    }
                }
            } catch (_: CopyCollision) { status = CopyStatus.EXISTS }
            catch (_: Exception) { status = if (!running() || signal.isCanceled) CopyStatus.CANCELLED else if (attempted) CopyStatus.UNCERTAIN else CopyStatus.FAILED }
            CopyOutcome(entry.name, target, status, bytes, attempted)
        }
    }
    private inline fun <T> CopyInput.streamAndMetadata(block: (CopyInput) -> T): T = stream.use { block(this) }
}

/** Read-only local capability adapter. Canonical aliases/symlinks are refused before descriptor open. */
class LocalImportSource(private val boundary: StorageBoundary) : ImportSource {
    override fun describe(path: String): ImportEntry {
        val file = boundary.resolve(path)
        require(file == java.io.File(path).absoluteFile && (file.isFile || file.isDirectory))
        return ImportEntry(path, file.name, file.isDirectory, if (file.isDirectory) null else file.length())
    }
    override fun children(path: String, limit: Int): List<ImportEntry> {
        require(describe(path).directory)
        // Native listFiles allocates the whole immediate directory, not bounded-memory pagination.
        val children = boundary.resolve(path).listFiles() ?: throw IOException("No listing")
        require(children.size <= limit)
        return children.sortedBy { it.name }.map { describe(it.path) }
    }
    override fun input(path: String): CopyInput { require(!describe(path).directory); return AndroidCopyStreams().input(boundary.resolve(path)) }
}
