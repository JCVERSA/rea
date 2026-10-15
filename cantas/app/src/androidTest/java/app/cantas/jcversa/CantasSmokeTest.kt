package app.cantas.jcversa

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule
import org.junit.Test

class CantasSmokeTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    @Test fun homeAndThemeNavigationWorkWithoutStorageAccess() {
        compose.onNodeWithText("Cantas").assertIsDisplayed()
        compose.onNodeWithTag("browse-storage").assertExists()
        compose.onNodeWithTag("nav-2").performClick()
        compose.onNodeWithTag("theme-dark").performClick()
        compose.onNodeWithTag("theme-dark").assertIsSelected()
        compose.onNodeWithTag("theme-light").performClick()
        compose.onNodeWithTag("theme-light").assertIsSelected()
        compose.onNodeWithTag("theme-system").performClick()
        compose.onNodeWithTag("nav-1").performClick()
        compose.onNodeWithTag("nav-0").performClick()
        compose.onNodeWithText("Cantas").assertIsDisplayed()
    }
}
