package app.cantas.jcversa

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.CopyStatus
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class CopyFileUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    @Test fun stageNavigateConfirmCopyAndRefuseExistingName() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val commands = if (Build.VERSION.SDK_INT >= 30) listOf("appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow")
            else listOf("pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE", "pm grant ${context.packageName} android.permission.WRITE_EXTERNAL_STORAGE")
        commands.forEach { command -> instrumentation.uiAutomation.executeShellCommand(command).use {
            android.os.ParcelFileDescriptor.AutoCloseInputStream(it).use { stream -> stream.readBytes() }
        } }
        compose.activityRule.scenario.recreate()
        val root = File(context.getExternalFilesDir(null), "copy-ui-${System.nanoTime()}")
        assertTrue(root.mkdir())
        lateinit var model: BrowserModel
        var modelReady = false
        try {
            val source = File(root, "source.txt").apply { writeText("original synthetic content") }
            val dest = File(root, "Destination").apply { mkdir() }
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; modelReady = true; model.navigate(root.path) }
            compose.onNodeWithTag("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.entries.size == 2 && model.copyReady.value }
            fun stageAndPaste() {
                compose.onNodeWithTag("entry-source.txt").performTouchInput { longClick() }
                compose.onNodeWithTag("copy-selection").performClick()
                assertEquals(1, model.clipboard.value.size)
                compose.onNodeWithTag("entry-Destination").performClick()
                compose.waitUntil(10000) { !model.state.value.loading && model.state.value.path == dest.canonicalPath }
                compose.onNodeWithTag("paste-here").performClick()
                compose.onNodeWithTag("copy-confirm").performClick()
                compose.waitUntil(10000) { model.copy.value?.results?.size == 1 && model.copy.value?.busy == false }
            }
            stageAndPaste()
            assertEquals(CopyStatus.COPIED, model.copy.value!!.results.single().status)
            assertEquals(source.readText(), File(dest, source.name).readText())
            compose.onNodeWithTag("copy-report-close").performClick()
            compose.waitUntil(10000) { model.copy.value == null && model.copyReady.value && !model.state.value.loading }
            File(dest, source.name).writeText("KEEP EXISTING")
            compose.onNodeWithContentDescription(context.getString(R.string.parent_folder)).performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.path == root.canonicalPath }
            stageAndPaste()
            assertEquals(CopyStatus.EXISTS, model.copy.value!!.results.single().status)
            assertFalse(model.copy.value!!.results.single().created)
            assertEquals("KEEP EXISTING", File(dest, source.name).readText())
            assertEquals("original synthetic content", source.readText())
            compose.onNodeWithTag("copy-report-close").performClick()
            compose.waitUntil(10000) { model.copy.value == null && model.copyReady.value }
            compose.activityRule.scenario.onActivity { model.clearClipboard() }
        } finally {
            // Fixture only. Finish any test operation before deleting its uniquely owned directory.
            if (modelReady) {
                compose.activityRule.scenario.onActivity { model.cancelCopy() }
                runCatching { compose.waitUntil(10000) { model.copy.value?.busy != true } }
                compose.activityRule.scenario.onActivity { model.dismissCopy(); model.clearClipboard() }
                runCatching { compose.waitUntil(5000) { model.copy.value == null } }
            }
            root.deleteRecursively()
        }
    }
}
