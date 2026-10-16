package app.cantas.jcversa

import android.app.Application
import android.content.Intent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.ui.CantasApp
import app.cantas.jcversa.ui.CantasTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

/** Real OS URI grants and UI, with a test-only provider, not the system picker or real SD/USB. */
class SafGrantUiTest {
    @get:Rule val compose = createComposeRule()
    @Test fun persistedReadGrantRestoresBrowsesWithoutBroadPermissionAndDisconnects() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as Application
        val resolver = app.contentResolver
        val tree = TreeFixtureProvider.TREE
        val store = ViewModelStore()
        val restoredStore = ViewModelStore()
        lateinit var model: BrowserModel
        try {
            resolver.call(tree, "grant-read", null, null)
            resolver.takePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val permission = resolver.persistedUriPermissions.single { it.uri == tree }
            assertTrue(permission.isReadPermission)
            assertFalse(permission.isWritePermission)
            instrumentation.runOnMainSync {
                val factory = ViewModelProvider.AndroidViewModelFactory(app)
                model = ViewModelProvider(store, factory)[BrowserModel::class.java]
                model.reloadRoots()
            }
            compose.setContent {
                CantasTheme("light") {
                    // SAF must remain usable even when broad local storage access is unavailable.
                    CantasApp(model, false, {}, {}, {}, "light", {})
                }
            }
            compose.waitUntil(10000) { model.roots.value.any { it.uri == tree.toString() } }
            compose.onNodeWithTag("home-list").performScrollToNode(hasText("Synthetic tree"))
            compose.onNodeWithText("Synthetic tree").performClick()
            compose.waitUntil(10000) { model.state.value.external && !model.state.value.loading && model.state.value.entries.isNotEmpty() }
            compose.onNodeWithTag("file-list").assertIsDisplayed()
            compose.onNodeWithText("Nested").performClick()
            compose.waitUntil(10000) { model.state.value.entries.singleOrNull()?.name == "inner.txt" }
            compose.onNodeWithText("inner.txt").assertIsDisplayed()
            compose.onNodeWithContentDescription(app.getString(R.string.parent_folder)).performClick()
            compose.waitUntil(10000) { model.state.value.path == model.state.value.root && !model.state.value.loading }
            lateinit var restored: BrowserModel
            instrumentation.runOnMainSync {
                restored = ViewModelProvider(restoredStore, ViewModelProvider.AndroidViewModelFactory(app))[BrowserModel::class.java]
                restored.reloadRoots()
            }
            compose.waitUntil(10000) { restored.roots.value.any { it.uri == tree.toString() } }
            instrumentation.runOnMainSync { model.forgetTree(tree.toString()) }
            compose.waitUntil(10000) { model.roots.value.none { it.uri == tree.toString() } && !model.state.value.external }
            assertFalse(resolver.persistedUriPermissions.any { it.uri == tree })
        } finally {
            runCatching { resolver.releasePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            runCatching { resolver.call(tree, "revoke-read", null, null) }
            instrumentation.runOnMainSync { store.clear(); restoredStore.clear() }
        }
    }
}
