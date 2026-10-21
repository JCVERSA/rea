package app.cantas.jcversa

import android.content.Intent
import android.provider.DocumentsContract
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.RenameResult
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class SafRenameUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    @Test fun explicitSelectionCancelThenConfirmedRenamePreservesContents() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val resolver = context.contentResolver
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
        val root = DocumentsContract.buildDocumentUriUsingTree(tree, "write-root")
        val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        lateinit var model: BrowserModel
        try {
            resolver.call(tree, "write-reset", null, null)
            resolver.call(tree, "grant-write", "write-root", null)
            resolver.takePersistableUriPermission(tree, flags)
            val file = DocumentsContract.createDocument(resolver, root, "text/plain", "source.txt")!!
            resolver.openOutputStream(file, "w")!!.use { it.write("original".toByteArray()) }
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; model.selectTree(tree.toString()); model.reloadRoots() }
            node("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.canCreateSaf() && model.copyReady.value }
            node("entry-source.txt").performTouchInput { longClick() }
            node("rename-selected").performClick()
            node("rename-name").performScrollTo().performTextReplacement("Cancelled.txt")
            node("rename-name").performImeAction()
            node("rename-close").performClick()
            assertEquals(0, resolver.call(tree, "write-state", null, null)!!.getInt("renames"))
            node("rename-selected").performClick()
            node("rename-name").performScrollTo().performTextReplacement("Renamed.txt")
            node("rename-name").performImeAction()
            compose.activityRule.scenario.recreate()
            node("rename-name").performScrollTo().assertTextContains("Renamed.txt")
            compose.waitUntil(10000) { !model.state.value.loading }
            node("rename-confirm").performClick()
            compose.waitUntil(10000) { model.rename.value?.result is RenameResult.Renamed && !model.state.value.loading }
            node("rename-success").performScrollTo().assertIsDisplayed()
            compose.waitForIdle()
            val bitmap = instrumentation.uiAutomation.takeScreenshot()
            File(context.cacheDir, "alpha12-saf-rename.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
            node("rename-close").performClick()
            node("entry-Renamed.txt").assertIsDisplayed()
            assertFalse(model.state.value.entries.any { it.name == "source.txt" })
            assertEquals("original", resolver.openInputStream(file)!!.bufferedReader().use { it.readText() })
            assertEquals(1, resolver.call(tree, "write-state", null, null)!!.getInt("renames"))
        } finally {
            runCatching { resolver.releasePersistableUriPermission(tree, flags) }
            resolver.call(tree, "revoke-write", "write-root", null)
            resolver.call(tree, "write-reset", null, null)
        }
    }
}
