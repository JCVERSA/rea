package app.cantas.jcversa

import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class BrowserUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    @Test fun realSearchHiddenAndSortControls() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val command = if(Build.VERSION.SDK_INT >= 30) "appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow"
            else "pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE"
        instrumentation.uiAutomation.executeShellCommand(command).use { descriptor ->
            android.os.ParcelFileDescriptor.AutoCloseInputStream(descriptor).use { it.readBytes() }
        }
        compose.activityRule.scenario.recreate()
        val root = File(context.getExternalFilesDir(null), "ui-fixture-${System.nanoTime()}")
        assertTrue(root.mkdir())
        try {
            File(root,"Alpha.txt").writeText("a"); File(root,"Beta.txt").writeText("bb")
            File(root,".hidden.txt").writeText("synthetic"); File(root,"Nested").mkdir()
            lateinit var model: BrowserModel
            compose.activityRule.scenario.onActivity { activity ->
                model = ViewModelProvider(activity)[BrowserModel::class.java]
                model.navigate(root.path)
            }
            node("nav-1").performClick()
            compose.waitUntil(5000) { compose.onAllNodesWithText("Beta.txt").fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
            node("folder-search").performTextInput("ALPHA")
            node("folder-search").performImeAction()
            compose.waitUntil(5000) { compose.onAllNodesWithText("Beta.txt").fetchSemanticsNodes(atLeastOneRootRequired = false).isEmpty() &&
                compose.onAllNodesWithText("Alpha.txt").fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
            node("folder-search").performTextClearance()
            node("show-hidden").performClick()
            compose.waitUntil(5000) { compose.onAllNodesWithText(".hidden.txt").fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
            node("sort-menu").performClick()
            node("sort-NAME_DESC").performClick()
            compose.waitUntil(5000) { !model.state.value.loading && model.state.value.options.sort == app.cantas.jcversa.data.FileSort.NAME_DESC }
            assertEquals(listOf("Nested","Beta.txt","Alpha.txt",".hidden.txt"),model.state.value.entries.map { it.name })
            compose.waitUntil(10000) { !model.preferences.value.busy && model.preferences.value.data.sort == app.cantas.jcversa.data.FileSort.NAME_DESC && model.preferences.value.data.showHidden }
            kotlinx.coroutines.runBlocking {
                val fresh = app.cantas.jcversa.data.BrowserPreferencesStore(File(context.filesDir, "browser-preferences.json"))
                fresh.ensureLoaded()
                assertEquals(app.cantas.jcversa.data.FileSort.NAME_DESC, fresh.state.value.data.sort)
                assertTrue(fresh.state.value.data.showHidden)
            }
        } catch (failure: Throwable) {
            runCatching {
                val active = instrumentation.uiAutomation.rootInActiveWindow
                File(context.cacheDir, "browse-failure-window.txt").writeText("package=${active?.packageName} class=${active?.className} lifecycle=${compose.activityRule.scenario.state}")
                val bitmap = instrumentation.uiAutomation.takeScreenshot()
                File(context.cacheDir, "browse-failure.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
                bitmap.recycle()
            }
            throw failure
        } finally {
            kotlinx.coroutines.runBlocking { app.cantas.jcversa.data.BrowserPreferencesStore.shared(File(context.filesDir, "browser-preferences.json")).reset() }
            root.deleteRecursively()
        } // Uniquely named app-owned fixture only.
    }
}
