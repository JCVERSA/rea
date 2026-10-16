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

class SelectionCreationUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun fixture(test: (File, BrowserModel) -> Unit) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val commands = if (Build.VERSION.SDK_INT >= 30) listOf("appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow")
            else listOf("pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE", "pm grant ${context.packageName} android.permission.WRITE_EXTERNAL_STORAGE")
        commands.forEach { command -> instrumentation.uiAutomation.executeShellCommand(command).use {
            android.os.ParcelFileDescriptor.AutoCloseInputStream(it).use { stream -> stream.readBytes() }
        } }
        compose.activityRule.scenario.recreate()
        val root = File(context.getExternalFilesDir(null), "selection-create-${System.nanoTime()}")
        assertTrue(root.mkdir())
        try {
            File(root, "Alpha.txt").writeText("original alpha")
            File(root, "Beta.txt").writeText("original beta")
            File(root, "Nested").mkdir()
            lateinit var model: BrowserModel
            compose.activityRule.scenario.onActivity {
                model = ViewModelProvider(it)[BrowserModel::class.java]
                model.navigate(root.path)
            }
            compose.onNodeWithTag("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.entries.size == 3 }
            test(root, model)
        } finally { root.deleteRecursively() } // Only this uniquely named app-owned emulator fixture.
    }
    private fun capture(name: String) {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val bitmap = instrumentation.uiAutomation.takeScreenshot()
        File(instrumentation.targetContext.cacheDir, name).outputStream().use {
            bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        bitmap.recycle()
    }
    @Test fun selectionCountsBackAndQueryResetWithoutOpeningFiles() = fixture { root, model ->
        compose.onNodeWithTag("selection-start").performClick()
        compose.onNodeWithTag("select-displayed").performClick()
        assertEquals(3, model.state.value.selected.size)
        compose.onNodeWithTag("entry-Nested").performClick()
        assertEquals(root.canonicalPath, model.state.value.path)
        assertEquals(2, model.state.value.selected.size)
        capture("alpha04-selection.png")
        compose.activityRule.scenario.onActivity { it.onBackPressedDispatcher.onBackPressed() }
        compose.waitUntil(5000) { !model.state.value.selecting && model.state.value.selected.isEmpty() }
        compose.onNodeWithTag("entry-Nested").performTouchInput { longClick() }
        assertEquals(1, model.state.value.selected.size)
        compose.onNodeWithTag("folder-search").performTextInput("ALPHA")
        compose.onNodeWithTag("folder-search").performImeAction()
        compose.waitUntil(5000) { !model.state.value.loading && model.state.value.entries.singleOrNull()?.name == "Alpha.txt" }
        assertFalse(model.state.value.selecting)
        assertTrue(model.state.value.selected.isEmpty())
        compose.onNodeWithTag("selection-start").performClick()
        compose.onNodeWithTag("select-displayed").performClick()
        assertEquals(1, model.state.value.selected.size)
        compose.onNodeWithTag("nav-0").performClick()
        assertTrue(model.state.value.selected.isEmpty())
        assertEquals("original alpha", File(root, "Alpha.txt").readText())
        assertEquals("original beta", File(root, "Beta.txt").readText())
    }
    @Test fun createConfirmCollisionValidationAndCancel() = fixture { root, model ->
        compose.onNodeWithTag("new-folder").performClick()
        compose.onNodeWithTag("folder-create-confirm").assertIsNotEnabled()
        compose.onNodeWithTag("folder-name").performTextInput("../escape")
        compose.onNodeWithTag("folder-create-confirm").assertIsNotEnabled()
        assertFalse(File(root.parentFile, "escape").exists())
        compose.onNodeWithTag("folder-name").performTextReplacement("Created")
        capture("alpha04-create.png")
        compose.onNodeWithTag("folder-create-confirm").performClick()
        compose.waitUntil(10000) { File(root, "Created").isDirectory && model.creation.value == null && !model.state.value.loading }
        compose.onNodeWithTag("entry-Created").assertIsDisplayed()
        val marker = File(root, "Created/marker").apply { writeText("keep me") }
        compose.onNodeWithTag("new-folder").performClick()
        compose.onNodeWithTag("folder-name").performTextInput("Created")
        compose.onNodeWithTag("folder-create-confirm").performClick()
        compose.waitUntil(5000) { model.creation.value?.result is app.cantas.jcversa.data.FolderCreation.Failed }
        compose.onNodeWithTag("folder-create-error").assertIsDisplayed()
        assertEquals("keep me", marker.readText())
        compose.onNodeWithTag("folder-name").performTextReplacement("Alpha.txt")
        compose.onNodeWithTag("folder-create-confirm").performClick()
        compose.waitUntil(5000) { model.creation.value?.result is app.cantas.jcversa.data.FolderCreation.Failed }
        assertEquals("original alpha", File(root, "Alpha.txt").readText())
        compose.onNodeWithText("Cancel").performClick()
        compose.onNodeWithTag("new-folder").performClick()
        compose.onNodeWithTag("folder-name").performTextInput("NotCreated")
        compose.onNodeWithText("Cancel").performClick()
        assertFalse(File(root, "NotCreated").exists())
    }
}
