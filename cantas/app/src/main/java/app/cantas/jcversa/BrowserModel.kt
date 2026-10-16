package app.cantas.jcversa

import android.app.Application
import android.os.Environment
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import app.cantas.jcversa.data.ReadOnlyStorage
import app.cantas.jcversa.data.ReadTarget
import app.cantas.jcversa.data.SafFiles
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.cantas.jcversa.data.FileEntry
import app.cantas.jcversa.data.BrowseOptions
import app.cantas.jcversa.data.FileSort
import app.cantas.jcversa.data.LocalFiles
import app.cantas.jcversa.data.StorageBoundary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.isActive
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("DEPRECATION")
class BrowserModel(application: Application) : AndroidViewModel(application) {
    val boundary = StorageBoundary(Environment.getExternalStorageDirectory())
    private val files = LocalFiles(boundary)
    private var current: ReadOnlyStorage = files
    private val ioMutex = Mutex()
    private var rootsRequest: Job? = null
    private val _roots = MutableStateFlow<List<GrantedTree>>(emptyList())
    val roots = _roots.asStateFlow()
    private val _connectionError = MutableStateFlow(false)
    val connectionError = _connectionError.asStateFlow()
    private var request: Job? = null
    private val _state = MutableStateFlow(BrowserState(path = boundary.root.path, root = boundary.root.path))
    val state = _state.asStateFlow()
    fun navigate(path: String) {
        load(path, _state.value.options.copy(query = "", limit = 1000), if (path.startsWith("/")) files else current)
    }
    fun refresh() { load(_state.value.path, _state.value.options) }
    fun query(value: String) { load(_state.value.path, _state.value.options.copy(query = value, limit = 1000)) }
    fun sort(value: FileSort) { load(_state.value.path, _state.value.options.copy(sort = value, limit = 1000)) }
    fun hidden(value: Boolean) { load(_state.value.path, _state.value.options.copy(showHidden = value, limit = 1000)) }
    fun more() { load(_state.value.path, _state.value.options.copy(limit = (_state.value.options.limit + 1000).coerceAtMost(100000))) }
    fun selectTree(tree: String) {
        val source = SafFiles(getApplication<Application>().contentResolver, Uri.parse(tree))
        load(source.root, _state.value.options.copy(query = "", limit = 1000), source)
    }
    private fun base(path: String, options: BrowseOptions, source: ReadOnlyStorage) =
        BrowserState(path = path, root = source.root, external = source is SafFiles,
            title = runCatching { source.label(path) }.getOrNull(), options = options)
    private fun load(path: String, options: BrowseOptions, source: ReadOnlyStorage = current) {
        val sameSource = current === source
        request?.cancel()
        current = source
        request = viewModelScope.launch {
            val previous = _state.value
            _state.value = if(sameSource && previous.path == path) previous.copy(loading = true, error = false, options = options)
                else base(path, options, source).copy(loading = true)
            try {
                if(previous.options.query != options.query) delay(180)
                val snapshot = withContext(Dispatchers.IO) {
                    val context = currentCoroutineContext()
                    ioMutex.withLock {
                        context.ensureActive()
                        if (source is SafFiles) requireTreeGrant(source.tree)
                        source.list(path, options) { context.isActive }
                    }
                }
                ensureActive()
                _state.value = base(snapshot.path, options, source).copy(entries = snapshot.entries, truncated = snapshot.truncated, title = snapshot.title)
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) {
                // An IO exception arriving after cancellation must not overwrite newer results.
                ensureActive()
                _state.value = base(path, options, source).copy(error = true)
            }
        }
    }
    fun up() {
        val source = current
        val path = _state.value.path
        viewModelScope.launch {
            val parent = withContext(Dispatchers.IO) { runCatching { source.parent(path) }.getOrNull() }
            if (current === source && _state.value.path == path) parent?.let { navigate(it) }
        }
    }
    suspend fun readTarget(path: String): ReadTarget {
        val source = current
        return withContext(Dispatchers.IO) {
            if (source is SafFiles) requireTreeGrant(source.tree)
            source.readTarget(path)
        }
    }
    private fun requireTreeGrant(tree: Uri) {
        check(getApplication<Application>().contentResolver.persistedUriPermissions.any { it.uri == tree && it.isReadPermission }) {
            "Tree permission unavailable"
        }
    }
    fun reloadRoots() {
        rootsRequest?.cancel()
        rootsRequest = viewModelScope.launch {
            try {
            val roots = withContext(Dispatchers.IO) {
                val resolver = getApplication<Application>().contentResolver
                resolver.persistedUriPermissions.filter { it.isReadPermission && DocumentsContract.isTreeUri(it.uri) }.map { permission ->
                    ensureActive()
                    val uri = permission.uri
                    val name = runCatching {
                        val document = DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri))
                        resolver.query(document, arrayOf(Document.COLUMN_DISPLAY_NAME), null, null, null)?.use {
                            if (it.moveToFirst()) it.getString(0) else null
                        }
                    }.getOrNull() ?: DocumentsContract.getTreeDocumentId(uri)
                    GrantedTree(uri.toString(), name)
                }
            }
            ensureActive()
            _roots.value = roots
            _connectionError.value = false
            val source = current
            if (source is SafFiles && roots.none { it.uri == source.tree.toString() }) {
                request?.cancel()
                _state.value = _state.value.copy(loading = false, error = true, entries = emptyList())
            }
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { ensureActive(); _connectionError.value = true }
        }
    }
    fun forgetTree(tree: String) {
        viewModelScope.launch {
            val removed = withContext(Dispatchers.IO) {
                runCatching {
                    val resolver = getApplication<Application>().contentResolver
                    val uri = Uri.parse(tree)
                    val permission = resolver.persistedUriPermissions.firstOrNull { it.uri == uri }
                    if (permission != null) {
                        val flags = (if (permission.isReadPermission) android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION else 0) or
                            (if (permission.isWritePermission) android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION else 0)
                        resolver.releasePersistableUriPermission(uri, flags)
                    }
                }.isSuccess
            }
            ensureActive()
            _connectionError.value = !removed
            if (removed) {
                if ((current as? SafFiles)?.tree?.toString() == tree) navigate(files.root)
                reloadRoots()
            }
        }
    }
}
data class GrantedTree(val uri: String, val name: String)
data class BrowserState(val path: String, val entries: List<FileEntry> = emptyList(),
    val loading: Boolean = false, val error: Boolean = false, val truncated: Boolean = false, val options: BrowseOptions = BrowseOptions(),
    val root: String = "", val title: String? = null, val external: Boolean = false)
