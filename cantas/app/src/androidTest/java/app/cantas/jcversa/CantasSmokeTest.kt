package app.cantas.jcversa

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import org.junit.Rule
import org.junit.Test
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File

class CantasSmokeTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private fun awaitSelected(tag: String) {
        compose.waitUntil(5000) {
            compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).any {
                it.config.getOrNull(SemanticsProperties.Selected) == true
            }
        }
        withAttachedRoot { compose.onNodeWithTag(tag).assertIsSelected() }
    }
    private fun awaitTag(tag: String) {
        compose.waitUntil(5000) {
            compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty()
        }
    }
    // Only transient owner detachment is retried, never assertion failures. Theme/navigation
    // actions are idempotent. This helper must not be used for filesystem confirmations.
    private fun withAttachedRoot(action: () -> Unit) {
        compose.waitUntil(10000) {
            try { action(); true }
            catch (missing: IllegalStateException) {
                if (missing.message?.contains("No compose hierarchies found") == true) false else throw missing
            }
        }
    }
    private fun clickTag(tag: String) {
        awaitTag(tag)
        withAttachedRoot { compose.onNodeWithTag(tag).performSemanticsAction(SemanticsActions.OnClick) { it() } }
    }
    private fun node(tag: String): SemanticsNodeInteraction {
        awaitTag(tag)
        return compose.onNodeWithTag(tag)
    }
    private fun awaitTitle() {
        compose.waitUntil(10000) { compose.onAllNodesWithText("Cantas").fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        withAttachedRoot { compose.onNodeWithText("Cantas").assertIsDisplayed() }
    }
    @Test fun homeAndThemeNavigationWorkWithoutStorageAccess() {
        try {
        awaitTitle()
        node("browse-storage").assertExists()
        clickTag("nav-2")
        clickTag("theme-dark")
        awaitSelected("theme-dark")
        awaitTag("theme-light")
        clickTag("theme-light")
        awaitSelected("theme-light")
        awaitTag("theme-system")
        clickTag("theme-system")
        awaitSelected("theme-system")
        org.junit.Assert.assertEquals("system", compose.activity.getSharedPreferences("appearance", android.content.Context.MODE_PRIVATE).getString("theme", null))
        clickTag("nav-1")
        clickTag("nav-0")
        awaitSelected("nav-0")
        awaitTitle()
        } catch (failure: Throwable) {
            val instrumentation = InstrumentationRegistry.getInstrumentation()
            val cache = instrumentation.targetContext.cacheDir
            runCatching {
                val root = instrumentation.uiAutomation.rootInActiveWindow
                File(cache, "theme-failure-window.txt").writeText("package=${root?.packageName} class=${root?.className} text=${root?.text} lifecycle=${compose.activityRule.scenario.state}")
                val bitmap = instrumentation.uiAutomation.takeScreenshot()
                File(cache, "theme-failure.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
                bitmap.recycle()
            }
            throw failure
        }
    }
}
