package app.cantas.jcversa

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
        compose.onNodeWithTag(tag).assertIsSelected()
    }
    private fun awaitTag(tag: String) {
        compose.waitUntil(5000) {
            compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty()
        }
    }
    private fun node(tag: String): SemanticsNodeInteraction {
        awaitTag(tag)
        return compose.onNodeWithTag(tag)
    }
    private fun awaitTitle() {
        compose.waitUntil(10000) { compose.onAllNodesWithText("Cantas").fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        compose.onNodeWithText("Cantas").assertIsDisplayed()
    }
    @Test fun homeAndThemeNavigationWorkWithoutStorageAccess() {
        try {
        awaitTitle()
        node("browse-storage").assertExists()
        node("nav-2").performClick()
        node("theme-dark").performClick()
        awaitSelected("theme-dark")
        awaitTag("theme-light")
        node("theme-light").performClick()
        awaitSelected("theme-light")
        awaitTag("theme-system")
        node("theme-system").performClick()
        awaitSelected("theme-system")
        node("nav-1").performClick()
        node("nav-0").performClick()
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
