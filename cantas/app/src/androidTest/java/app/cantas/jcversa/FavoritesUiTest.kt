package app.cantas.jcversa

import android.content.Intent
import android.os.Build
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.BrowserPreferencesStore
import app.cantas.jcversa.data.FileSort
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import java.io.File

class FavoritesUiTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()
    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext
    private fun node(tag: String): SemanticsNodeInteraction {
        compose.waitUntil(10000) { compose.onAllNodesWithTag(tag).fetchSemanticsNodes(atLeastOneRootRequired = false).isNotEmpty() }
        return compose.onNodeWithTag(tag)
    }
    private fun resetMetadata() = runBlocking { BrowserPreferencesStore.shared(File(context.filesDir, "browser-preferences.json")).reset() }
    private fun grantLocal() {
        val commands = if (Build.VERSION.SDK_INT >= 30) listOf("appops set ${context.packageName} MANAGE_EXTERNAL_STORAGE allow")
            else listOf("pm grant ${context.packageName} android.permission.READ_EXTERNAL_STORAGE")
        commands.forEach { InstrumentationRegistry.getInstrumentation().uiAutomation.executeShellCommand(it).use { fd ->
            android.os.ParcelFileDescriptor.AutoCloseInputStream(fd).use { stream -> stream.readBytes() }
        } }
        compose.activityRule.scenario.recreate()
    }
    private fun capture() {
        compose.waitForIdle()
        val bitmap = InstrumentationRegistry.getInstrumentation().uiAutomation.takeScreenshot()
        File(context.cacheDir, "alpha08-favorites.png").outputStream().use { bitmap.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it) }
        bitmap.recycle()
    }
    @Test fun localFavoriteRestoresRenamesAndRemovesOnlyShortcut() {
        resetMetadata(); grantLocal()
        val folder = File(context.getExternalFilesDir(null), "favorite-ui-${System.nanoTime()}").apply { mkdir() }.canonicalFile
        val marker = File(folder, "marker.txt").apply { writeText("KEEP") }
        lateinit var model: BrowserModel
        try {
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; model.navigate(folder.path) }
            node("nav-1").performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.preferences.value.ready && !model.preferences.value.busy }
            node("favorite-toggle").performClick()
            compose.waitUntil(10000) { model.preferences.value.data.favorites.size == 1 && !model.preferences.value.busy }
            compose.activityRule.scenario.recreate()
            compose.activityRule.scenario.onActivity { model = ViewModelProvider(it)[BrowserModel::class.java]; model.navigate(model.boundary.root.path) }
            node("favorites-open").performClick()
            node("favorite-open-0").performScrollTo().performClick()
            compose.waitUntil(10000) { !model.state.value.loading && model.state.value.path == folder.path }
            assertEquals("KEEP", marker.readText())
            node("favorites-open").performClick()
            node("favorite-rename-0").performScrollTo().performClick()
            node("favorite-title").performTextReplacement("My saved folder")
            node("favorite-title").performImeAction()
            node("favorite-rename-confirm").performClick()
            compose.waitUntil(10000) { model.preferences.value.data.favorites.singleOrNull()?.title == "My saved folder" && !model.preferences.value.busy }
            capture()
            assertTrue(folder.isDirectory)
            assertEquals("KEEP", marker.readText())
            node("favorite-remove-0").performScrollTo().performClick()
            node("favorite-remove-confirm").performClick()
            compose.waitUntil(10000) { model.preferences.value.data.favorites.isEmpty() && !model.preferences.value.busy }
            node("favorites-close").performClick()
            assertTrue(folder.isDirectory)
            assertEquals("KEEP", marker.readText())
        } finally { resetMetadata(); folder.deleteRecursively() }
    }
    @Test fun safNestedFavoriteRestoresInFreshModelAndSurvivesRevocation() {
        resetMetadata()
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val resolver = context.contentResolver
        val tree = TreeFixtureProvider.TREE
        val store = ViewModelStore()
        lateinit var original: BrowserModel
        lateinit var restored: BrowserModel
        try {
            resolver.call(tree, "grant-read", null, null)
            resolver.takePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            compose.activityRule.scenario.onActivity { original = ViewModelProvider(it)[BrowserModel::class.java]; original.selectTree(tree.toString()) }
            node("nav-1").performClick()
            compose.waitUntil(10000) { !original.state.value.loading && original.state.value.external && original.state.value.entries.any { it.name == "Nested" } }
            node("entry-Nested").performClick()
            compose.waitUntil(10000) { !original.state.value.loading && original.state.value.entries.singleOrNull()?.name == "inner.txt" }
            node("favorite-toggle").performClick()
            compose.waitUntil(10000) { original.preferences.value.data.favorites.size == 1 && !original.preferences.value.busy }
            val favorite = original.preferences.value.data.favorites.single()
            assertEquals(2, favorite.ancestry.size)
            instrumentation.runOnMainSync {
                restored = ViewModelProvider(store, ViewModelProvider.AndroidViewModelFactory(context.applicationContext as android.app.Application))[BrowserModel::class.java]
                restored.openFavorite(favorite.key)
            }
            compose.waitUntil(10000) { !restored.state.value.loading && restored.state.value.path == favorite.location && restored.state.value.entries.singleOrNull()?.name == "inner.txt" }
            assertFalse(restored.state.value.error)
            val permission = resolver.persistedUriPermissions.single { it.uri == tree }
            assertTrue(permission.isReadPermission); assertFalse(permission.isWritePermission)
            resolver.releasePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            resolver.call(tree, "revoke-read", null, null)
            instrumentation.runOnMainSync { restored.openFavorite(favorite.key) }
            compose.waitUntil(10000) { !restored.state.value.loading && restored.state.value.error }
            assertEquals(favorite, restored.preferences.value.data.favorites.single())
            instrumentation.runOnMainSync { restored.removeFavorite(favorite.key) }
            compose.waitUntil(10000) { restored.preferences.value.data.favorites.isEmpty() && !restored.preferences.value.busy }
            assertFalse(resolver.persistedUriPermissions.any { it.uri == tree })
        } finally {
            instrumentation.runOnMainSync { store.clear() }
            runCatching { resolver.releasePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION) }
            runCatching { resolver.call(tree, "revoke-read", null, null) }
            resetMetadata()
        }
    }
}
