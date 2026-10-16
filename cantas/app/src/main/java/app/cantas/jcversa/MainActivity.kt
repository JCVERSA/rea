package app.cantas.jcversa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.provider.DocumentsContract
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.SystemBarStyle
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import app.cantas.jcversa.ui.CantasApp
import app.cantas.jcversa.ui.CantasTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val browser: BrowserModel by viewModels()
    private var permitted by mutableStateOf(false)
    private var writePermitted by mutableStateOf(false)
    private val writePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        updatePermission()
        if (!granted) message(R.string.write_access_denied)
    }
    private fun requestFolderWrite() {
        if (Build.VERSION.SDK_INT < 30) writePermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        else requestStorage()
    }
    private val storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        updatePermission()
    }
    private val treePicker = registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) { uri ->
        if (uri != null) lifecycleScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    require(DocumentsContract.isTreeUri(uri))
                    contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                browser.selectTree(uri.toString())
                browser.reloadRoots()
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { message(R.string.tree_permission_error) }
        }
    }
    private fun pickTree() {
        try { treePicker.launch(null) }
        catch (_: android.content.ActivityNotFoundException) { message(R.string.tree_picker_missing) }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val preferences = getSharedPreferences("appearance", MODE_PRIVATE)
        setContent {
            var mode by remember { mutableStateOf(preferences.getString("theme", "system") ?: "system") }
            val dark = mode == "dark" || (mode == "system" && isSystemInDarkTheme())
            SideEffect {
                val bars = SystemBarStyle.auto(android.graphics.Color.TRANSPARENT, android.graphics.Color.TRANSPARENT) { dark }
                enableEdgeToEdge(statusBarStyle = bars, navigationBarStyle = bars)
            }
            CantasTheme(mode) {
                CantasApp(browser, permitted, ::requestStorage, ::pickTree, ::openFile, mode,
                    changeMode = { choice -> preferences.edit().putString("theme", choice).apply(); mode = choice },
                    canCreateLocal = writePermitted, requestFolderWrite = ::requestFolderWrite)
            }
        }
    }
    override fun onResume() { super.onResume(); updatePermission(); browser.reloadRoots() }
    private fun updatePermission() {
        val now = if(Build.VERSION.SDK_INT >= 30) Environment.isExternalStorageManager()
            else ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if(now && !permitted) browser.refresh()
        permitted = now
        writePermitted = if (Build.VERSION.SDK_INT >= 30) now else
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestStorage() {
        if(Build.VERSION.SDK_INT < 30) { storagePermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE); return }
        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, Uri.parse("package:$packageName"))
        try { startActivity(intent) }
        catch (_: android.content.ActivityNotFoundException) {
            try { startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION)) }
            catch (_: android.content.ActivityNotFoundException) { message(R.string.storage_settings_missing) }
        }
    }
    private fun openFile(path: String) {
        lifecycleScope.launch {
            try {
                val (uri, mime) = withContext(Dispatchers.IO) {
                    val target = browser.readTarget(path)
                    if (target.location.startsWith("content://")) {
                        Uri.parse(target.location) to (target.mime ?: "application/octet-stream")
                    } else {
                        val file = browser.boundary.resolve(target.location)
                        FileProvider.getUriForFile(this@MainActivity, "$packageName.files", file) to
                            (MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension.lowercase(Locale.ROOT)) ?: "application/octet-stream")
                    }
                }
                val intent = Intent(Intent.ACTION_VIEW).setDataAndType(uri, mime)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.clipData = android.content.ClipData.newRawUri("", uri)
                startActivity(Intent.createChooser(intent, getString(R.string.open_with)))
            } catch (cancelled: kotlinx.coroutines.CancellationException) { throw cancelled }
            catch (_: Exception) { message(R.string.cannot_open) }
        }
    }
    private fun message(id: Int) { Toast.makeText(this,id,Toast.LENGTH_LONG).show() }
}
