package app.cantas.jcversa

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
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
    private val storagePermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        updatePermission()
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
                CantasApp(browser, permitted, ::requestStorage, ::openFile, mode) { choice ->
                    preferences.edit().putString("theme", choice).apply(); mode = choice
                }
            }
        }
    }
    override fun onResume() { super.onResume(); updatePermission() }
    private fun updatePermission() {
        val now = if(Build.VERSION.SDK_INT >= 30) Environment.isExternalStorageManager()
            else ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
        if(now && !permitted) browser.navigate(browser.state.value.path)
        permitted = now
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
        try {
            val file = browser.boundary.resolve(path)
            require(file.isFile && file.canRead())
            val uri = FileProvider.getUriForFile(this, "$packageName.files", file)
            val mime = MimeTypeMap.getSingleton().getMimeTypeFromExtension(file.extension.lowercase(Locale.ROOT)) ?: "application/octet-stream"
            startActivity(Intent.createChooser(Intent(Intent.ACTION_VIEW).setDataAndType(uri,mime)
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION), getString(R.string.open_with)))
        } catch (_: Exception) { message(R.string.cannot_open) }
    }
    private fun message(id: Int) { Toast.makeText(this,id,Toast.LENGTH_LONG).show() }
}
