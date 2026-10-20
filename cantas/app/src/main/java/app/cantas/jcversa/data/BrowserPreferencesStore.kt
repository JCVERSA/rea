package app.cantas.jcversa.data

import android.net.Uri
import android.provider.DocumentsContract
import android.util.AtomicFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File

/** App-private bounded metadata. Serialized read-modify-write; never silently overwrite corrupt data. */
class BrowserPreferencesStore(file: File) {
    companion object {
        const val MAX_BYTES = 256 * 1024
        private val mutex = Mutex()
        private var instance: BrowserPreferencesStore? = null
        @Synchronized fun shared(file: File): BrowserPreferencesStore {
            return instance ?: BrowserPreferencesStore(file).also { instance = it }
        }
    }
    private val file = AtomicFile(file)
    private val _state = MutableStateFlow(PreferencesStatus())
    val state = _state.asStateFlow()
    suspend fun ensureLoaded() { if (!_state.value.ready) reload() }
    suspend fun reload() = operation { read() }
    suspend fun update(change: (BrowserPreferences) -> BrowserPreferences) = operation {
        val next = change(read())
        write(next)
        next
    }
    /** Explicit UI confirmation required. Resets only this metadata file, never files or grants. */
    suspend fun reset() = operation { BrowserPreferences().also { write(it) } }
    private suspend fun operation(block: () -> BrowserPreferences): Unit = mutex.withLock {
        _state.value = _state.value.copy(busy = true)
        try {
            val next = withContext(Dispatchers.IO) { block() }
            _state.value = PreferencesStatus(next, ready = true)
        } catch (cancelled: kotlinx.coroutines.CancellationException) {
            _state.value = _state.value.copy(busy = false)
            throw cancelled
        } catch (_: Exception) {
            _state.value = _state.value.copy(ready = true, busy = false, error = true)
        }
    }
    private fun validate(data: BrowserPreferences) {
        FavoritePolicy.validate(data)
        data.favorites.filter { it.tree != null }.forEach { favorite ->
            val tree = Uri.parse(favorite.tree!!)
            require(tree.scheme == "content" && !tree.authority.isNullOrBlank() && DocumentsContract.isTreeUri(tree))
            val root = DocumentsContract.buildDocumentUriUsingTree(tree, DocumentsContract.getTreeDocumentId(tree))
            require(favorite.ancestry.first() == root.toString())
            favorite.ancestry.forEach { location ->
                val uri = Uri.parse(location)
                require(uri.authority == tree.authority && DocumentsContract.isTreeUri(uri))
                require(DocumentsContract.getTreeDocumentId(uri) == DocumentsContract.getTreeDocumentId(tree))
                require(DocumentsContract.buildDocumentUriUsingTree(tree, DocumentsContract.getDocumentId(uri)) == uri)
            }
        }
    }
    private fun readBytes(): ByteArray? {
        val input = try { file.openRead() } catch (_: java.io.FileNotFoundException) { return null }
        return input.use {
            val out = ByteArrayOutputStream()
            val buffer = ByteArray(4096)
            while (true) {
                val n = it.read(buffer)
                if (n < 0) break
                check(n > 0 && out.size() + n <= MAX_BYTES)
                out.write(buffer, 0, n)
            }
            out.toByteArray()
        }
    }
    private fun read(): BrowserPreferences {
        val bytes = readBytes() ?: return BrowserPreferences()
        val json = JSONObject(Charsets.UTF_8.newDecoder().decode(java.nio.ByteBuffer.wrap(bytes)).toString())
        require(json.getInt("version") == 1)
        val list = json.getJSONArray("favorites")
        require(list.length() <= FavoritePolicy.MAX_FAVORITES)
        val favorites = (0 until list.length()).map { index ->
            val entry = list.getJSONObject(index)
            val chain = entry.getJSONArray("ancestry")
            require(chain.length() <= FavoritePolicy.MAX_ANCESTRY)
            FavoriteFolder(entry.getString("location"), entry.getString("title"),
                if (entry.isNull("tree")) null else entry.getString("tree"),
                (0 until chain.length()).map { chain.getString(it) })
        }
        return BrowserPreferences(favorites, FileSort.valueOf(json.getString("sort")), json.getBoolean("hidden")).also(::validate)
    }
    private fun write(data: BrowserPreferences) {
        validate(data)
        val entries = JSONArray()
        data.favorites.forEach { favorite -> entries.put(JSONObject().put("location", favorite.location).put("title", favorite.title)
            .put("tree", favorite.tree ?: JSONObject.NULL).put("ancestry", JSONArray(favorite.ancestry))) }
        val bytes = JSONObject().put("version", 1).put("sort", data.sort.name).put("hidden", data.showHidden)
            .put("favorites", entries).toString().toByteArray(Charsets.UTF_8)
        require(bytes.size <= MAX_BYTES)
        val stream = file.startWrite()
        try {
            stream.write(bytes)
            android.system.Os.fsync(stream.fd)
            file.finishWrite(stream)
            check(readBytes()?.contentEquals(bytes) == true)
        } catch (error: Exception) { file.failWrite(stream); throw error }
    }
}
data class PreferencesStatus(val data: BrowserPreferences = BrowserPreferences(), val ready: Boolean = false,
    val busy: Boolean = false, val error: Boolean = false)
