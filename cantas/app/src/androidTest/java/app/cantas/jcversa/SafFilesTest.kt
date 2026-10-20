package app.cantas.jcversa

import android.provider.DocumentsContract
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.BrowseOptions
import app.cantas.jcversa.data.FileSort
import app.cantas.jcversa.data.SafFiles
import org.junit.Assert.*
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.runner.RunWith
import java.io.IOException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.cancelAndJoin
import java.util.concurrent.CancellationException

@RunWith(AndroidJUnit4::class)
class SafFilesTest {
    private val resolver get() = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
    @Before fun grantSyntheticTrees() {
        listOf(TreeFixtureProvider.ROOT, "null-root", "slow-root").forEach {
            resolver.call(TreeFixtureProvider.TREE, "grant-read", it, null)
        }
    }
    @After fun revokeSyntheticTrees() {
        listOf(TreeFixtureProvider.ROOT, "null-root", "slow-root").forEach {
            resolver.call(TreeFixtureProvider.TREE, "revoke-read", it, null)
        }
    }
    @Test fun filtersSortsAndLoadsMoreThroughRealCursor() {
        val source = SafFiles(resolver, TreeFixtureProvider.TREE)
        val first = source.list(source.root)
        assertEquals(1000, first.entries.size)
        assertTrue(first.truncated)
        assertEquals("Nested", first.entries.first().name)
        assertEquals("Synthetic tree", first.title)
        val all = source.list(source.root, BrowseOptions(limit = 2000))
        assertEquals(1007, all.entries.size)
        assertFalse(all.truncated)
        assertEquals(first.entries, all.entries.take(1000))
        assertTrue(source.list(source.root, BrowseOptions(query = "SECRET")).entries.isEmpty())
        assertEquals(".secret", source.list(source.root, BrowseOptions(query = "secret", showHidden = true)).entries.single().name)
        val sizes = source.list(source.root, BrowseOptions(sort = FileSort.SIZE_DESC, limit = 2))
        assertEquals(listOf("Nested", "1004.txt"), sizes.entries.map { it.name })
    }
    @Test fun opaqueParentsReadTargetAndUnknownUris() {
        val source = SafFiles(resolver, TreeFixtureProvider.TREE)
        assertNull(source.parent(source.root))
        val folder = source.list(source.root, BrowseOptions(query = "Nested")).entries.single()
        assertThrows(IllegalArgumentException::class.java) { source.readTarget(folder.path) }
        val child = source.list(folder.path).entries.single()
        assertEquals(source.root, source.parent(folder.path))
        assertEquals(folder.path, source.parent(child.path))
        assertEquals("text/plain", source.readTarget(child.path).mime)
        assertEquals("fixture", resolver.openInputStream(android.net.Uri.parse(child.path))!!.bufferedReader().use { it.readText() })
        val forged = DocumentsContract.buildDocumentUriUsingTree(TreeFixtureProvider.TREE, "not-discovered").toString()
        assertThrows(SecurityException::class.java) { source.list(forged) }
        assertThrows(SecurityException::class.java) { source.readTarget(forged) }
        source.list(source.root, BrowseOptions(query = "Résumé"))
        assertThrows(SecurityException::class.java) { source.readTarget(child.path) }
    }
    @Test fun savedNestedFolderRequiresRealParentMembership() = runBlocking {
        val original = SafFiles(resolver, TreeFixtureProvider.TREE)
        val folder = original.list(original.root, BrowseOptions(query = "Nested")).entries.single()
        val restored = SafFiles(resolver, TreeFixtureProvider.TREE)
        assertThrows(SecurityException::class.java) { restored.list(folder.path) }
        restored.restoreFolderCancellable(listOf(restored.root, folder.path)) { true }
        assertEquals("inner.txt", restored.list(folder.path).entries.single().name)
        assertEquals(restored.root, restored.parent(folder.path))
        val inner = DocumentsContract.buildDocumentUriUsingTree(TreeFixtureProvider.TREE, TreeFixtureProvider.INNER).toString()
        val forged = SafFiles(resolver, TreeFixtureProvider.TREE)
        assertThrows(IOException::class.java) { runBlocking { forged.restoreFolderCancellable(listOf(forged.root, inner)) { true } } }
        assertThrows(SecurityException::class.java) { forged.readTarget(inner) }
        assertThrows(IllegalArgumentException::class.java) { runBlocking { forged.restoreFolderCancellable(listOf(forged.root, forged.root)) { true } } }
    }
    @Test fun cancellationReachesBlockingProvider() = runBlocking {
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "slow-root")
        val source = SafFiles(resolver, tree)
        val request = launch(Dispatchers.IO) { source.listCancellable(source.root, BrowseOptions()) { true } }
        try {
            withTimeout(10000) {
                while (resolver.call(tree, "slow-state", null, null)?.getBoolean("started") != true) delay(20)
            }
            request.cancelAndJoin()
            withTimeout(5000) {
                while (resolver.call(tree, "slow-state", null, null)?.getBoolean("cancelled") != true) delay(20)
            }
        } finally { request.cancelAndJoin() }
    }
    @Test fun cancelledOrUnavailableIsNotAnEmptyFolder() {
        val source = SafFiles(resolver, TreeFixtureProvider.TREE)
        assertThrows(CancellationException::class.java) { source.list(source.root, active = { false }) }
        assertThrows(IllegalArgumentException::class.java) { source.list(source.root, BrowseOptions(limit = 0)) }
        val unavailable = SafFiles(resolver, DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "null-root"))
        assertThrows(IOException::class.java) { unavailable.list(unavailable.root) }
    }
}
