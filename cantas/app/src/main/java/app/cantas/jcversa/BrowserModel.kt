package app.cantas.jcversa

import android.app.Application
import android.os.Environment
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
    private var request: Job? = null
    private val _state = MutableStateFlow(BrowserState(path = boundary.root.path))
    val state = _state.asStateFlow()
    fun navigate(path: String) {
        load(path, _state.value.options.copy(query = "", limit = 1000))
    }
    fun refresh() { load(_state.value.path, _state.value.options) }
    fun query(value: String) { load(_state.value.path, _state.value.options.copy(query = value, limit = 1000)) }
    fun sort(value: FileSort) { load(_state.value.path, _state.value.options.copy(sort = value, limit = 1000)) }
    fun hidden(value: Boolean) { load(_state.value.path, _state.value.options.copy(showHidden = value, limit = 1000)) }
    fun more() { load(_state.value.path, _state.value.options.copy(limit = (_state.value.options.limit + 1000).coerceAtMost(100000))) }
    private fun load(path: String, options: BrowseOptions) {
        request?.cancel()
        request = viewModelScope.launch {
            val previous = _state.value
            _state.value = if(previous.path == path) previous.copy(loading = true, error = false, options = options)
                else BrowserState(path = path, loading = true, options = options)
            try {
                if(previous.options.query != options.query) delay(180)
                val snapshot = withContext(Dispatchers.IO) {
                    val context = currentCoroutineContext()
                    files.list(path, options) { context.isActive }
                }
                ensureActive()
                _state.value = BrowserState(snapshot.path, snapshot.entries, truncated = snapshot.truncated, options = options)
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) {
                // An IO exception arriving after cancellation must not overwrite newer results.
                ensureActive()
                _state.value = BrowserState(path = path, error = true, options = options)
            }
        }
    }
    fun up() {
        viewModelScope.launch {
            val parent = withContext(Dispatchers.IO) { runCatching { boundary.parent(_state.value.path) }.getOrNull() }
            parent?.let { navigate(it.path) }
        }
    }
}
data class BrowserState(val path: String, val entries: List<FileEntry> = emptyList(),
    val loading: Boolean = false, val error: Boolean = false, val truncated: Boolean = false, val options: BrowseOptions = BrowseOptions())
