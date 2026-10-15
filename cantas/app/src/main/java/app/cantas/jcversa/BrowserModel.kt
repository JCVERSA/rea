package app.cantas.jcversa

import android.app.Application
import android.os.Environment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.cantas.jcversa.data.FileEntry
import app.cantas.jcversa.data.LocalFiles
import app.cantas.jcversa.data.StorageBoundary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
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
        request?.cancel()
        request = viewModelScope.launch {
            val safe = runCatching { boundary.resolve(path).path }.getOrNull()
            if (safe == null) { _state.value = _state.value.copy(error = true, loading = false); return@launch }
            _state.value = BrowserState(path = safe, loading = true)
            try {
                val snapshot = withContext(Dispatchers.IO) { files.list(safe) }
                ensureActive()
                _state.value = BrowserState(snapshot.path, snapshot.entries, truncated = snapshot.truncated)
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { _state.value = BrowserState(path = safe, error = true) }
        }
    }
    fun up() { boundary.parent(_state.value.path)?.let { navigate(it.path) } }
}
data class BrowserState(val path: String, val entries: List<FileEntry> = emptyList(),
    val loading: Boolean = false, val error: Boolean = false, val truncated: Boolean = false)
