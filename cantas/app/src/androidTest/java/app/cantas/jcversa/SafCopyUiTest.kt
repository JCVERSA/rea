package app.cantas.jcversa

import android.content.Intent
import android.os.Build
import android.provider.DocumentsContract
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.CopyStatus
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class SafCopyUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    @Test fun localFileStagesNavigatesConfirmsAndVerifiesSafOutput() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val resolver = context.contentResolver
        val command = if (Build.VERSION.SDK_INT >= 30) "appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow"
            else "pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE"
        instrumentation.uiAutomation.executeShellCommand(command).use { android.os.ParcelFileDescriptor.AutoCloseInputStream(it).use { stream -> stream.readBytes() } }
        compose.activityRule.scenario.recreate()
        val root = File(context.getExternalFilesDir(null), "saf-export-${System.nanoTime()}").apply { assertTrue(mkdir()) }
        val original = File(root, "local.txt").apply { writeText("original local file") }
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
        val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        lateinit var model: BrowserModel
        try {
            resolver.call(tree, "write-reset", null, null)
            resolver.call(tree, "grant-write", "write-root", null)
            resolver.takePersistableUriPermission(tree, flags)
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; model.navigate(root.path) }
            node("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.entries.size == 1 && model.copyReady.value }
            node("entry-local.txt").performTouchInput { longClick() }
            node("copy-selection").performClick()
            assertEquals(1, model.clipboard.value.size)
            compose.activityRule.scenario.onActivity { model.selectTree(tree.toString()); model.reloadRoots() }
            compose.waitUntil(10000) { !model.state.value.loading && model.canCreateSaf() }
            node("paste-here").performClick()
            node("copy-confirm").performClick()
            compose.waitUntil(10000) { model.copy.value?.busy == false && model.copy.value?.results?.size == 1 }
            val result = model.copy.value!!.results.single()
            assertEquals(CopyStatus.COPIED, result.status)
            assertEquals("original local file", resolver.openInputStream(android.net.Uri.parse(result.target))!!.bufferedReader().use { it.readText() })
            assertEquals("original local file", original.readText())
            val bitmap = instrumentation.uiAutomation.takeScreenshot()
            File(context.cacheDir, "alpha10-saf-copy.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
            node("copy-report-close").performClick()
            compose.waitUntil(10000) { model.copy.value == null && model.copyReady.value }
            assertTrue(model.clipboard.value.isEmpty())
        } finally {
            runCatching { resolver.releasePersistableUriPermission(tree, flags) }
            resolver.call(tree, "revoke-write", "write-root", null)
            resolver.call(tree, "write-reset", null, null)
            root.deleteRecursively() // Uniquely named app-owned emulator fixture only.
        }
    }
}
