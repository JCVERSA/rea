package app.cantas.jcversa

import android.app.Application
import app.cantas.jcversa.data.LocalTreeCopy
import app.cantas.jcversa.data.LocalCopy
import app.cantas.jcversa.data.AndroidCopyStreams
import app.cantas.jcversa.data.CopyRequest
import app.cantas.jcversa.data.CopyJournal
import app.cantas.jcversa.data.CopySession
import app.cantas.jcversa.data.CopyStatus
import java.util.concurrent.atomic.AtomicBoolean
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import app.cantas.jcversa.data.LocalFolders
import app.cantas.jcversa.data.FolderCreation
import app.cantas.jcversa.data.FolderError
import app.cantas.jcversa.data.EntryKey
import app.cantas.jcversa.data.key
import android.os.Environment
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import app.cantas.jcversa.data.ReadOnlyStorage
import app.cantas.jcversa.data.ReadTarget
import app.cantas.jcversa.data.SafFiles
import app.cantas.jcversa.data.cancellableProviderCall
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
    private val folders = LocalFolders(boundary)
    private val _creation = MutableStateFlow<FolderOperation?>(null)
    val creation = _creation.asStateFlow()
    private val copier = LocalTreeCopy(boundary, AndroidCopyStreams())
    private val journal = CopyJournal(java.io.File(application.filesDir, "copy-journal.json"))
    private val _clipboard = MutableStateFlow<List<CopyRequest>>(emptyList())
    val clipboard = _clipboard.asStateFlow()
    private val _copy = MutableStateFlow<CopySession?>(null)
    val copy = _copy.asStateFlow()
    private val _copyReady = MutableStateFlow(false)
    val copyReady = _copyReady.asStateFlow()
    private var copyCancelled = AtomicBoolean(false)
    init {
        viewModelScope.launch {
            _copy.value = withContext(Dispatchers.IO) {
                try { journal.read() } catch (_: Exception) { CopySession("", interrupted = true, journalError = true) }
            }
            _copyReady.value = true
        }
    }
    fun stageCopy() {
        val state = _state.value
        if (current !== files || state.external || state.loading || state.error || _copy.value != null) return
        val entries = state.entries.filter { it.key() in state.selected }
        if (entries.isEmpty() || entries.size > LocalCopy.MAX_FILES) return
        _clipboard.value = entries.map { CopyRequest(java.io.File(state.path, it.name).path, it.name) }
        clearSelection()
    }
    fun clearClipboard() { if (_copy.value?.busy != true) _clipboard.value = emptyList() }
    fun cancelCopy() { copyCancelled.set(true) }
    fun dismissCopy() {
        if (_copy.value?.busy == true || !_copyReady.value) return
        _copyReady.value = false
        viewModelScope.launch {
            withContext(Dispatchers.IO) { journal.clear() }
            _copy.value = null
            _copyReady.value = true
        }
    }
    fun paste(destination: String) {
        val requests = _clipboard.value.toList()
        val state = _state.value
        if (_creation.value?.busy == true || !_copyReady.value || _copy.value != null || current !== files || state.external ||
            state.loading || state.error || state.path != destination || requests.isEmpty()) return
        copyCancelled = AtomicBoolean(false)
        val cancelled = copyCancelled
        _copy.value = CopySession(destination, busy = true)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                ioMutex.withLock {
                    val context = currentCoroutineContext()
                    var started = false
                    try {
                        val app = getApplication<Application>()
                        val allowed = if (Build.VERSION.SDK_INT >= 30) Environment.isExternalStorageManager()
                            else ContextCompat.checkSelfPermission(app, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        check(allowed)
                        context.ensureActive()
                        val running = { context.isActive && !cancelled.get() }
                        val plan = copier.prepare(requests, destination, running)
                        journal.start(destination, requests)
                        started = true
                        val outcomes = copier.copy(plan, running) { progress ->
                            _copy.value = CopySession(destination, busy = true, progress = progress)
                        }
                        var journalError = false
                        try { journal.finish(destination, outcomes) } catch (_: Exception) { journalError = true }
                        CopySession(destination, results = outcomes, journalError = journalError)
                    } catch (error: Exception) {
                        CopySession(destination, interrupted = started, names = requests.map { it.name }, journalError = started, preparationStopped = !started)
                    }
                }
            }
            ensureActive()
            _copy.value = result
            if (result.results.isNotEmpty() && result.results.all { it.status == CopyStatus.COPIED || it.status == CopyStatus.DIRECTORY_CREATED }) _clipboard.value = emptyList()
            if (current === files && _state.value.path == destination) refresh()
        }
    }
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
            _state.value = if(sameSource && previous.path == path) previous.copy(loading = true, error = false, options = options, selecting = false, selected = emptySet())
                else base(path, options, source).copy(loading = true)
            try {
                if(previous.options.query != options.query) delay(180)
                val snapshot = withContext(Dispatchers.IO) {
                    val context = currentCoroutineContext()
                    ioMutex.withLock {
                        context.ensureActive()
                        if (source is SafFiles) {
                            requireTreeGrant(source.tree)
                            source.listCancellable(path, options) { context.isActive }
                        } else source.list(path, options) { context.isActive }
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
    fun startSelection() {
        val state = _state.value
        if (!state.loading && !state.error) _state.value = state.copy(selecting = true)
    }
    fun clearSelection() { _state.value = _state.value.copy(selecting = false, selected = emptySet()) }
    fun toggle(entry: FileEntry) {
        val state = _state.value
        if (state.loading || state.error || entry !in state.entries) return
        val key = entry.key()
        _state.value = state.copy(selecting = true, selected = if (key in state.selected) state.selected - key else state.selected + key)
    }
    fun selectDisplayed() {
        val state = _state.value
        if (!state.loading && !state.error) _state.value = state.copy(selecting = true, selected = state.entries.map { it.key() }.toSet())
    }
    fun dismissCreation() { if (_creation.value?.busy != true) _creation.value = null }
    fun createFolder(parent: String, name: String) {
        if (_copy.value?.busy == true || _creation.value?.busy == true || _creation.value?.result is FolderCreation.Created) return
        val state = _state.value
        if (current !== files || state.external) {
            _creation.value = FolderOperation(parent, name, result = FolderCreation.Failed(FolderError.UNSUPPORTED)); return
        }
        if (state.loading || state.error || state.path != parent) {
            _creation.value = FolderOperation(parent, name, result = FolderCreation.Failed(FolderError.UNAVAILABLE)); return
        }
        _creation.value = FolderOperation(parent, name, busy = true)
        viewModelScope.launch {
            val result = try {
                withContext(Dispatchers.IO) {
                    ioMutex.withLock {
                        ensureActive()
                        val app = getApplication<Application>()
                        val allowed = if (Build.VERSION.SDK_INT >= 30) Environment.isExternalStorageManager()
                            else ContextCompat.checkSelfPermission(app, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        if (allowed) folders.create(parent, name) else FolderCreation.Failed(FolderError.ACCESS_DENIED)
                    }
                }
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { FolderCreation.Failed(FolderError.FAILED) }
            ensureActive()
            _creation.value = FolderOperation(parent, name, result = result)
            if (result is FolderCreation.Created && current === files && _state.value.path == parent) refresh()
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
                            cancellableProviderCall { signal ->
                                resolver.query(document, arrayOf(Document.COLUMN_DISPLAY_NAME), null, null, null, signal)?.use {
                                    if (it.moveToFirst()) it.getString(0) else null
                                }
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
                    _state.value = _state.value.copy(loading = false, error = true, entries = emptyList(), selecting = false, selected = emptySet())
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
    val root: String = "", val title: String? = null, val external: Boolean = false,
    val selecting: Boolean = false, val selected: Set<EntryKey> = emptySet())

data class FolderOperation(val parent: String, val name: String, val busy: Boolean = false, val result: FolderCreation? = null)
