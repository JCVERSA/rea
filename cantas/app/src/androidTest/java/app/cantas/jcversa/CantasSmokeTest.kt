package app.cantas.jcversa

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import org.junit.Rule
import org.junit.Test

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
    @Test fun homeAndThemeNavigationWorkWithoutStorageAccess() {
        compose.onNodeWithText("Cantas").assertIsDisplayed()
        compose.onNodeWithTag("browse-storage").assertExists()
        compose.onNodeWithTag("nav-2").performClick()
        compose.onNodeWithTag("theme-dark").performClick()
        awaitSelected("theme-dark")
        compose.onNodeWithTag("theme-light").performClick()
        awaitSelected("theme-light")
        compose.onNodeWithTag("theme-system").performClick()
        awaitSelected("theme-system")
        compose.onNodeWithTag("nav-1").performClick()
        compose.onNodeWithTag("nav-0").performClick()
        compose.onNodeWithText("Cantas").assertIsDisplayed()
    }
}
