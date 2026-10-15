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
            compose.onNodeWithTag("nav-1").performClick()
            compose.waitUntil(5000) { compose.onAllNodesWithText("Beta.txt").fetchSemanticsNodes().isNotEmpty() }
            compose.onNodeWithTag("folder-search").performTextInput("ALPHA")
            compose.onNodeWithTag("folder-search").performImeAction()
            compose.waitUntil(5000) { compose.onAllNodesWithText("Beta.txt").fetchSemanticsNodes().isEmpty() &&
                compose.onAllNodesWithText("Alpha.txt").fetchSemanticsNodes().isNotEmpty() }
            compose.onNodeWithTag("folder-search").performTextClearance()
            compose.onNodeWithTag("show-hidden").performClick()
            compose.waitUntil(5000) { compose.onAllNodesWithText(".hidden.txt").fetchSemanticsNodes().isNotEmpty() }
            compose.onNodeWithTag("sort-menu").performClick()
            compose.onNodeWithTag("sort-NAME_DESC").performClick()
            compose.waitUntil(5000) { !model.state.value.loading && model.state.value.options.sort == app.cantas.jcversa.data.FileSort.NAME_DESC }
            assertEquals(listOf("Nested","Beta.txt","Alpha.txt",".hidden.txt"),model.state.value.entries.map { it.name })
        } finally { root.deleteRecursively() } // Uniquely named app-owned fixture only.
    }
}
