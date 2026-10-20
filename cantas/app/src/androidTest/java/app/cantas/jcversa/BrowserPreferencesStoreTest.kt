package app.cantas.jcversa

import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class BrowserPreferencesStoreTest {
    private fun fixture(test: (File) -> Unit) {
        val root = File(InstrumentationRegistry.getInstrumentation().targetContext.cacheDir, "preferences-${System.nanoTime()}").canonicalFile
        assertTrue(root.mkdir())
        try { test(root) } finally { root.deleteRecursively() }
    }
    @Test fun freshStoreRestoresFavoritesAndOptionsWithoutChangingUserFiles() = fixture { root ->
        runBlocking {
            val folder = File(root, "Données").apply { mkdir() }
            val marker = File(folder, "marker").apply { writeText("KEEP") }
            val file = File(root, "metadata.json")
            val first = BrowserPreferencesStore(file)
            first.update { FavoritePolicy.add(it.copy(sort = FileSort.NAME_DESC, showHidden = true), FavoriteFolder(folder.path, "Données 📁")) }
            assertFalse(first.state.value.error)
            val restored = BrowserPreferencesStore(file)
            restored.ensureLoaded()
            assertEquals(first.state.value.data, restored.state.value.data)
            val key = restored.state.value.data.favorites.single().key
            restored.update { FavoritePolicy.rename(it, key, "Shortcut only") }
            restored.update { FavoritePolicy.remove(it, key) }
            assertEquals("KEEP", marker.readText())
            assertTrue(folder.isDirectory)
            assertTrue(restored.state.value.data.favorites.isEmpty())
            assertEquals(FileSort.NAME_DESC, restored.state.value.data.sort)
            assertTrue(restored.state.value.data.showHidden)
        }
    }
    @Test fun concurrentStoresPreserveBothMetadataUpdates() = fixture { root ->
        runBlocking {
            val file = File(root, "metadata.json")
            val a = BrowserPreferencesStore(file); val b = BrowserPreferencesStore(file)
            coroutineScope {
                launch(Dispatchers.Default) { a.update { FavoritePolicy.add(it.copy(showHidden = true), FavoriteFolder("/storage/a", "A")) } }
                launch(Dispatchers.Default) { b.update { FavoritePolicy.add(it.copy(sort = FileSort.SIZE_DESC), FavoriteFolder("/storage/b", "B")) } }
            }
            val restored = BrowserPreferencesStore(file).also { it.ensureLoaded() }
            assertFalse(restored.state.value.error)
            assertEquals(setOf("A", "B"), restored.state.value.data.favorites.map { it.title }.toSet())
            assertTrue(restored.state.value.data.showHidden)
            assertEquals(FileSort.SIZE_DESC, restored.state.value.data.sort)
        }
    }
    @Test fun corruptOrOversizedMetadataRequiresExplicitReset() = fixture { root ->
        runBlocking {
            val marker = File(root, "marker").apply { writeText("KEEP") }
            val file = File(root, "metadata.json").apply { writeText("not valid JSON") }
            val store = BrowserPreferencesStore(file)
            store.ensureLoaded()
            assertTrue(store.state.value.error)
            store.update { it.copy(showHidden = true) }
            assertEquals("not valid JSON", file.readText())
            store.reset()
            assertFalse(store.state.value.error)
            assertEquals(BrowserPreferences(), store.state.value.data)
            file.writeBytes(ByteArray(BrowserPreferencesStore.MAX_BYTES + 1) { 32 })
            store.reload()
            assertTrue(store.state.value.error)
            store.update { it.copy(showHidden = true) }
            assertEquals((BrowserPreferencesStore.MAX_BYTES + 1).toLong(), file.length())
            store.reset()
            assertFalse(store.state.value.error)
            assertEquals("KEEP", marker.readText())
        }
    }
    @Test fun differentSafTreeCannotBeStoredAsOneFavorite() = fixture { root ->
        runBlocking {
            val tree = TreeFixtureProvider.TREE
            val other = android.provider.DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "other")
            val rootUri = android.provider.DocumentsContract.buildDocumentUriUsingTree(tree, TreeFixtureProvider.ROOT).toString()
            val child = android.provider.DocumentsContract.buildDocumentUriUsingTree(other, "child").toString()
            val file = File(root, "metadata.json")
            val store = BrowserPreferencesStore(file)
            store.update { FavoritePolicy.add(it, FavoriteFolder(child, "Forged", tree.toString(), listOf(rootUri, child))) }
            assertTrue(store.state.value.error)
            assertFalse(file.exists())
            val fresh = BrowserPreferencesStore(file).also { it.ensureLoaded() }
            assertTrue(fresh.state.value.data.favorites.isEmpty())
        }
    }
}
