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

class SafImportUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    @Test fun readOnlySafFolderImportsLocallyAndNeverMergesExistingFolder() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val resolver = context.contentResolver
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "import-root")
        val commands = if (Build.VERSION.SDK_INT >= 30) listOf("appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow")
            else listOf("pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE", "pm grant ${context.packageName} android.permission.WRITE_EXTERNAL_STORAGE")
        commands.forEach { instrumentation.uiAutomation.executeShellCommand(it).use { fd ->
            android.os.ParcelFileDescriptor.AutoCloseInputStream(fd).use { stream -> stream.readBytes() }
        } }
        compose.activityRule.scenario.recreate()
        val dest = File(context.getExternalFilesDir(null), "saf-import-ui-${System.nanoTime()}").apply { mkdir() }.canonicalFile
        lateinit var model: BrowserModel
        var ready = false
        try {
            resolver.call(tree, "grant-read", "import-root", null)
            resolver.takePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; ready = true }
            fun importFolder() {
                compose.activityRule.scenario.onActivity { model.selectTree(tree.toString()) }
                node("nav-1").performClick()
                compose.waitUntil(10000) { !model.state.value.loading && model.state.value.external && model.state.value.entries.singleOrNull()?.name == "ImportFolder" && model.copyReady.value }
                node("entry-ImportFolder").performTouchInput { longClick() }
                node("copy-selection").performClick()
                assertEquals(1, model.clipboard.value.size)
                compose.activityRule.scenario.onActivity { model.navigate(dest.path) }
                compose.waitUntil(10000) { !model.state.value.loading && !model.state.value.external && model.state.value.path == dest.path }
                node("paste-here").performClick()
                node("copy-confirm").performClick()
                compose.waitUntil(10000) { model.copy.value?.busy == false && model.copy.value?.results?.size == 4 }
            }
            importFolder()
            assertEquals(2, model.copy.value!!.results.count { it.status == CopyStatus.COPIED })
            assertEquals(2, model.copy.value!!.results.count { it.status == CopyStatus.DIRECTORY_CREATED })
            assertEquals("fixture", File(dest, "ImportFolder/Résumé.txt").readText())
            assertEquals("fixture", File(dest, "ImportFolder/unknown.txt").readText())
            assertTrue(File(dest, "ImportFolder/Empty").isDirectory)
            val grant = resolver.persistedUriPermissions.single { it.uri == tree }
            assertTrue(grant.isReadPermission)
            assertFalse(grant.isWritePermission)
            compose.waitForIdle()
            val bitmap = instrumentation.uiAutomation.takeScreenshot()
            File(context.cacheDir, "alpha07-saf-import.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
            node("copy-report-close").performClick()
            compose.waitUntil(10000) { model.copy.value == null && model.copyReady.value }
            File(dest, "ImportFolder/Résumé.txt").writeText("KEEP")
            importFolder()
            assertEquals(CopyStatus.EXISTS, model.copy.value!!.results.first().status)
            assertTrue(model.copy.value!!.results.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
            assertEquals("KEEP", File(dest, "ImportFolder/Résumé.txt").readText())
            node("copy-report-close").performClick()
            compose.waitUntil(10000) { model.copy.value == null && model.copyReady.value }
        } finally {
            if (ready) {
                compose.activityRule.scenario.onActivity { model.cancelCopy() }
                runCatching { compose.waitUntil(10000) { model.copy.value?.busy != true } }
                compose.activityRule.scenario.onActivity { model.dismissCopy(); model.clearClipboard() }
                runCatching { compose.waitUntil(5000) { model.copy.value == null } }
            }
            runCatching { resolver.releasePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            runCatching { resolver.call(tree, "revoke-read", "import-root", null) }
            dest.deleteRecursively() // Unique app-owned emulator fixture only.
        }
    }
}
