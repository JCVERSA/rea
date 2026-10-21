package app.cantas.jcversa

import android.app.Application
import app.cantas.jcversa.data.BrowserPreferencesStore
import app.cantas.jcversa.data.FavoriteFolder
import app.cantas.jcversa.data.FavoritePolicy
import app.cantas.jcversa.data.ImportCopy
import app.cantas.jcversa.data.SafImportSource
import android.os.CancellationSignal
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
import app.cantas.jcversa.data.SafFolders
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
    private val preferenceStore = BrowserPreferencesStore.shared(java.io.File(application.filesDir, "browser-preferences.json"))
    val preferences = preferenceStore.state
    private var browsePreferencesTouched = false
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
    private var clipboardTree: String? = null
    private var copySignal: CancellationSignal? = null
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
        if (state.loading || state.error || _copy.value != null) return
        val entries = state.entries.filter { it.key() in state.selected }
        if (entries.isEmpty() || entries.size > LocalCopy.MAX_FILES) return
        clipboardTree = (current as? SafFiles)?.tree?.toString()
        _clipboard.value = entries.map { CopyRequest(if (state.external) it.path else java.io.File(state.path, it.name).path, it.name) }
        clearSelection()
    }
    fun clearClipboard() { if (_copy.value?.busy != true) { _clipboard.value = emptyList(); clipboardTree = null } }
    fun cancelCopy() {
        copyCancelled.set(true)
        val signal = copySignal
        if (signal != null) viewModelScope.launch(Dispatchers.IO) { signal.cancel() }
    }
    override fun onCleared() {
        copyCancelled.set(true)
        // Provider cancellation is potentially blocking; never execute it on the UI thread.
        val signal = copySignal
        if (signal != null) java.util.concurrent.Executors.newSingleThreadExecutor().let { executor ->
            executor.execute { try { signal.cancel() } finally { executor.shutdown() } }
        }
        super.onCleared()
    }
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
        if (_state.value.external) { pasteToSaf(destination); return }
        val requests = _clipboard.value.toList()
        val sourceTree = clipboardTree
        val state = _state.value
        if (_creation.value?.busy == true || !_copyReady.value || _copy.value != null || current !== files || state.external ||
            state.loading || state.error || state.path != destination || requests.isEmpty()) return
        copyCancelled = AtomicBoolean(false)
        val cancelled = copyCancelled
        val signal = CancellationSignal()
        copySignal = signal
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
                        val importer = sourceTree?.let {
                            val tree = Uri.parse(it)
                            requireTreeGrant(tree)
                            ImportCopy(boundary, AndroidCopyStreams(), SafImportSource(app.contentResolver, tree, requests, signal, running))
                        }
                        val plan = importer?.prepare(requests, destination, running) ?: copier.prepare(requests, destination, running)
                        journal.start(destination, requests)
                        started = true
                        val progress: (app.cantas.jcversa.data.CopyProgress) -> Unit = {
                            _copy.value = CopySession(destination, busy = true, progress = it)
                        }
                        val outcomes = importer?.copy(plan, running, progress) ?: copier.copy(plan, running, progress)
                        var journalError = false
                        try { journal.finish(destination, outcomes) } catch (_: Exception) { journalError = true }
                        CopySession(destination, results = outcomes, journalError = journalError)
                    } catch (error: Exception) {
                        CopySession(destination, interrupted = started, names = requests.map { it.name }, journalError = started, preparationStopped = !started)
                    }
                }
            }
            ensureActive()
            copySignal = null
            _copy.value = result
            if (result.results.isNotEmpty() && result.results.all { it.status == CopyStatus.COPIED || it.status == CopyStatus.DIRECTORY_CREATED }) _clipboard.value = emptyList()
            if (current === files && _state.value.path == destination) refresh()
        }
    }
    private fun pasteToSaf(path: String) {
        val target = current as? SafFiles ?: return
        val requests = _clipboard.value.toList()
        val sourceTree = clipboardTree
        val state = _state.value
        if (_creation.value?.busy == true || !_copyReady.value || _copy.value != null || state.loading || state.error ||
            state.path != path || requests.isEmpty()) return
        copyCancelled = AtomicBoolean(false)
        val cancelled = copyCancelled
        val signal = CancellationSignal()
        copySignal = signal
        _copy.value = CopySession(path, busy = true)
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                ioMutex.withLock {
                    val context = currentCoroutineContext()
                    val running = { context.isActive && !cancelled.get() }
                    var started = false
                    try {
                        val resolver = getApplication<Application>().contentResolver
                        val source = if (sourceTree != null) {
                            val tree = Uri.parse(sourceTree)
                            requireTreeGrant(tree)
                            SafImportSource(resolver, tree, requests, signal, running)
                        } else app.cantas.jcversa.data.LocalImportSource(boundary)
                        val engine = app.cantas.jcversa.data.SafTreeCopy(resolver, target, path, source, signal)
                        val plan = engine.prepare(requests, running)
                        journal.start(path, requests)
                        started = true
                        val outcomes = engine.copy(plan, running) {
                            _copy.value = CopySession(path, busy = true, progress = it)
                        }
                        var journalError = false
                        try { journal.finish(path, outcomes) } catch (_: Exception) { journalError = true }
                        CopySession(path, results = outcomes, journalError = journalError)
                    } catch (_: Exception) {
                        CopySession(path, interrupted = started, names = requests.map { it.name }, journalError = started, preparationStopped = !started)
                    }
                }
            }
            ensureActive()
            copySignal = null
            _copy.value = result
            if (result.results.isNotEmpty() && result.results.all { it.status == CopyStatus.COPIED || it.status == CopyStatus.DIRECTORY_CREATED }) _clipboard.value = emptyList()
            if (current === target && _state.value.path == path) refresh()
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
    private var pendingAncestry: Pair<ReadOnlyStorage, List<String>>? = null
    private val _state = MutableStateFlow(BrowserState(path = boundary.root.path, root = boundary.root.path,
        options = preferenceStore.state.value.data.browseOptions()))
    val state = _state.asStateFlow()
    init {
        viewModelScope.launch {
            preferenceStore.ensureLoaded()
            if (!browsePreferencesTouched && !preferences.value.error) {
                val saved = preferences.value.data
                val now = _state.value
                if (now.options.sort != saved.sort || now.options.showHidden != saved.showHidden)
                    load(now.path, now.options.copy(sort = saved.sort, showHidden = saved.showHidden))
            }
        }
    }
    fun isCurrentFavorite(favorite: FavoriteFolder): Boolean = favorite.location == _state.value.path &&
        favorite.tree == (current as? SafFiles)?.tree?.toString()
    fun toggleFavorite(title: String) {
        val snapshot = _state.value
        val source = current
        if (snapshot.loading || snapshot.error || !preferences.value.ready || preferences.value.busy || preferences.value.error) return
        viewModelScope.launch {
            preferenceStore.update { data ->
                val tree = (source as? SafFiles)?.tree?.toString()
                val key = tree.orEmpty() + '\u0000' + snapshot.path
                if (data.favorites.any { it.key == key }) FavoritePolicy.remove(data, key)
                else {
                    val chain = if (source is SafFiles) {
                        val ancestry = mutableListOf<String>()
                        var location: String? = snapshot.path
                        while (location != null) {
                            require(ancestry.size < FavoritePolicy.MAX_ANCESTRY && location !in ancestry)
                            ancestry.add(location)
                            location = source.parent(location)
                        }
                        requireTreeGrant(source.tree)
                        ancestry.reversed()
                    } else {
                        require(boundary.resolve(snapshot.path).isDirectory)
                        emptyList()
                    }
                    FavoritePolicy.add(data, FavoriteFolder(snapshot.path, title, tree, chain))
                }
            }
        }
    }
    fun removeFavorite(key: String) { viewModelScope.launch { preferenceStore.update { FavoritePolicy.remove(it, key) } } }
    fun renameFavorite(key: String, title: String) { viewModelScope.launch { preferenceStore.update { FavoritePolicy.rename(it, key, title) } } }
    fun reloadPreferences() { viewModelScope.launch { preferenceStore.reload() } }
    fun resetPreferences() {
        viewModelScope.launch {
            preferenceStore.reset()
            if (!preferences.value.error) {
                browsePreferencesTouched = true
                load(_state.value.path, _state.value.options.copy(sort = FileSort.NAME, showHidden = false))
            }
        }
    }
    fun openFavorite(key: String) {
        val favorite = preferences.value.data.favorites.firstOrNull { it.key == key } ?: return
        if (favorite.tree == null) load(favorite.location, _state.value.options.copy(query = "", limit = 1000), files)
        else {
            val source = SafFiles(getApplication<Application>().contentResolver, Uri.parse(favorite.tree))
            load(favorite.location, _state.value.options.copy(query = "", limit = 1000), source, favorite.ancestry)
        }
    }
    fun navigate(path: String) {
        load(path, _state.value.options.copy(query = "", limit = 1000), if (path.startsWith("/")) files else current)
    }
    fun refresh() { load(_state.value.path, _state.value.options) }
    fun query(value: String) { load(_state.value.path, _state.value.options.copy(query = value, limit = 1000)) }
    fun sort(value: FileSort) {
        browsePreferencesTouched = true
        load(_state.value.path, _state.value.options.copy(sort = value, limit = 1000))
        viewModelScope.launch { preferenceStore.update { it.copy(sort = value) } }
    }
    fun hidden(value: Boolean) {
        browsePreferencesTouched = true
        load(_state.value.path, _state.value.options.copy(showHidden = value, limit = 1000))
        viewModelScope.launch { preferenceStore.update { it.copy(showHidden = value) } }
    }
    fun more() { load(_state.value.path, _state.value.options.copy(limit = (_state.value.options.limit + 1000).coerceAtMost(100000))) }
    fun selectTree(tree: String) {
        val source = SafFiles(getApplication<Application>().contentResolver, Uri.parse(tree))
        load(source.root, _state.value.options.copy(query = "", limit = 1000), source)
    }
    private fun base(path: String, options: BrowseOptions, source: ReadOnlyStorage) =
        BrowserState(path = path, root = source.root, external = source is SafFiles,
            title = runCatching { source.label(path) }.getOrNull(), options = options)
    private fun load(path: String, options: BrowseOptions, source: ReadOnlyStorage = current, ancestry: List<String>? = null) {
        val sameSource = current === source
        val restore = ancestry ?: pendingAncestry?.takeIf { it.first === source && it.second.lastOrNull() == path }?.second
        pendingAncestry = restore?.let { source to it }
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
                            if (restore != null) source.restoreFolderCancellable(restore) { context.isActive }
                            source.listCancellable(path, options) { context.isActive }
                        } else source.list(path, options) { context.isActive }
                    }
                }
                ensureActive()
                pendingAncestry = null
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
    fun canCreateSaf(): Boolean = (current as? SafFiles)?.let { source -> _roots.value.any { it.uri == source.tree.toString() && it.writable } } == true
    fun dismissCreation() { if (_creation.value?.busy != true) _creation.value = null }
    fun createFolder(parent: String, name: String) {
        if (_copy.value?.busy == true || _creation.value?.busy == true || _creation.value?.result is FolderCreation.Created) return
        val state = _state.value
        val source = current
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
                        if (source is SafFiles) return@withLock SafFolders(app.contentResolver).create(source, parent, name) { isActive }
                        val allowed = if (Build.VERSION.SDK_INT >= 30) Environment.isExternalStorageManager()
                            else ContextCompat.checkSelfPermission(app, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        if (allowed) folders.create(parent, name) else FolderCreation.Failed(FolderError.ACCESS_DENIED)
                    }
                }
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { FolderCreation.Failed(FolderError.FAILED) }
            ensureActive()
            _creation.value = FolderOperation(parent, name, result = result)
            if (result is FolderCreation.Created && current === source && _state.value.path == parent) refresh()
        }
    }
    fun up() {
        val source = current
        val path = _state.value.path
        viewModelScope.launch {
            val parent = withContext(Dispatchers.IO) { runCatching { source.parent(path) }.getOrNull() }
            if (current === source && _state.value.path == path) {
                if (parent != null) navigate(parent)
                else if (path != source.root) navigate(source.root)
            }
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
                        GrantedTree(uri.toString(), name, permission.isWritePermission)
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
data class GrantedTree(val uri: String, val name: String, val writable: Boolean = false)
data class BrowserState(val path: String, val entries: List<FileEntry> = emptyList(),
    val loading: Boolean = false, val error: Boolean = false, val truncated: Boolean = false, val options: BrowseOptions = BrowseOptions(),
    val root: String = "", val title: String? = null, val external: Boolean = false,
    val selecting: Boolean = false, val selected: Set<EntryKey> = emptySet())

data class FolderOperation(val parent: String, val name: String, val busy: Boolean = false, val result: FolderCreation? = null)
