package app.cantas.jcversa

import android.content.Intent
import android.provider.DocumentsContract
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.FolderCreation
import app.cantas.jcversa.data.FolderError
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class SafFolderUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    @Test fun writableConnectionCreatesThenReportsCollisionWithoutReplacing() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val context = instrumentation.targetContext
        val resolver = context.contentResolver
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
        val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        lateinit var model: BrowserModel
        try {
            resolver.call(tree, "write-reset", null, null)
            resolver.call(tree, "grant-write", "write-root", null)
            resolver.takePersistableUriPermission(tree, flags)
            compose.activityRule.scenario.onActivity {
                model = ViewModelProvider(it)[BrowserModel::class.java]
                model.selectTree(tree.toString()); model.reloadRoots()
            }
            node("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.canCreateSaf() }
            node("new-folder").performClick()
            node("folder-name").performTextInput("UI dossier")
            node("folder-name").performImeAction()
            node("folder-create-confirm").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.entries.any { it.name == "UI dossier" && it.directory } }
            node("new-folder").performClick()
            node("folder-name").performTextInput("KEEP.txt")
            node("folder-name").performImeAction()
            node("folder-create-confirm").performClick()
            compose.waitUntil(10000) { model.creation.value?.result == FolderCreation.Failed(FolderError.EXISTS) }
            node("folder-create-error").assertIsDisplayed()
            val bitmap = instrumentation.uiAutomation.takeScreenshot()
            File(context.cacheDir, "alpha09-saf-folder.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
            bitmap.recycle()
            assertEquals(1, resolver.call(tree, "write-state", null, null)!!.getInt("calls"))
            assertEquals(7L, model.state.value.entries.single { it.name == "KEEP.txt" }.bytes)
        } finally {
            runCatching { resolver.releasePersistableUriPermission(tree, flags) }
            resolver.call(tree, "revoke-write", "write-root", null)
            resolver.call(tree, "write-reset", null, null)
        }
    }
}
