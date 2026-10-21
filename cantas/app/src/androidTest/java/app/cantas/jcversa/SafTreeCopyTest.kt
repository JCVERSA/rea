package app.cantas.jcversa

import android.content.Intent
import android.net.Uri
import android.os.CancellationSignal
import android.provider.DocumentsContract
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import org.junit.*
import org.junit.Assert.*
import java.io.File

class SafTreeCopyTest {
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val resolver = context.contentResolver
    private val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
    private val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    private lateinit var root: File
    @Before fun setup() {
        root = File(context.getExternalFilesDir(null), "saf-tree-${System.nanoTime()}").apply { check(mkdir()) }
        resolver.call(tree, "write-reset", null, null)
        resolver.call(tree, "grant-write", "write-root", null)
        resolver.takePersistableUriPermission(tree, flags)
    }
    @After fun cleanup() {
        runCatching { resolver.releasePersistableUriPermission(tree, flags) }
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "write-reset", null, null)
        root.deleteRecursively() // Unique app-owned disposable-emulator fixture only.
    }
    private fun local(name: String = "Folder"): File = File(root, name).apply {
        mkdir(); File(this, "Nested").mkdir(); File(this, "Empty").mkdir(); File(this, "Nested/.hidden.txt").writeText("original")
    }
    private fun engine(source: ImportSource): SafTreeCopy {
        val dest = SafFiles(resolver, tree)
        return SafTreeCopy(resolver, dest, dest.root, source, CancellationSignal())
    }
    private fun calls() = resolver.call(tree, "write-state", null, null)!!.getInt("calls")
    private fun read(path: String) = resolver.openInputStream(Uri.parse(path))!!.bufferedReader().use { it.readText() }
    @Test fun localNestedHiddenAndEmptyCopyAndCollisionNeverMerge() {
        val folder = local()
        val e = engine(LocalImportSource(StorageBoundary(root)))
        val p = e.prepare(listOf(CopyRequest(folder.path, folder.name))) { true }
        val copied = e.copy(p, { true }, {})
        assertEquals(3, copied.count { it.status == CopyStatus.DIRECTORY_CREATED })
        assertEquals("original", read(copied.single { it.status == CopyStatus.COPIED }.target))
        assertEquals("original", File(folder, "Nested/.hidden.txt").readText())
        val before = calls()
        val collisionEngine = engine(LocalImportSource(StorageBoundary(root)))
        val collision = collisionEngine.copy(collisionEngine.prepare(listOf(CopyRequest(folder.path, folder.name))) { true }, { true }, {})
        assertEquals(CopyStatus.EXISTS, collision.first().status)
        assertTrue(collision.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED && !it.created })
        assertEquals(before, calls())
        assertEquals("fixture", read(DocumentsContract.buildDocumentUriUsingTree(tree, "keep").toString()))
    }
    @Test fun readOnlySafNestedImportCopiesUnknownSizeAndEmptyFolder() {
        val sourceTree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "import-root")
        try {
            resolver.call(sourceTree, "grant-read", "import-root", null)
            resolver.takePersistableUriPermission(sourceTree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val request = CopyRequest(DocumentsContract.buildDocumentUriUsingTree(sourceTree, "import-folder").toString(), "ImportFolder")
            val source = SafImportSource(resolver, sourceTree, listOf(request), CancellationSignal()) { true }
            val e = engine(source)
            val result = e.copy(e.prepare(listOf(request)) { true }, { true }, {})
            assertEquals(2, result.count { it.status == CopyStatus.DIRECTORY_CREATED })
            assertEquals(2, result.count { it.status == CopyStatus.COPIED && it.bytes == 7L })
            result.filter { it.status == CopyStatus.COPIED }.forEach { assertEquals("fixture", read(it.target)) }
            assertFalse(resolver.persistedUriPermissions.single { it.uri == sourceTree }.isWritePermission)
        } finally {
            resolver.releasePersistableUriPermission(sourceTree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            resolver.call(sourceTree, "revoke-read", "import-root", null)
        }
    }
    @Test fun uncertainDirectorySkipsDescendantsAndCancellationRetainsCreatedRoot() {
        val folder = local("auto-rename")
        val e = engine(LocalImportSource(StorageBoundary(root)))
        val result = e.copy(e.prepare(listOf(CopyRequest(folder.path, folder.name))) { true }, { true }, {})
        assertEquals(CopyStatus.UNCERTAIN, result.first().status)
        assertTrue(result.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
        assertEquals(1, calls())
        val another = local("Partial")
        val cancel = engine(LocalImportSource(StorageBoundary(root)))
        val plan = cancel.prepare(listOf(CopyRequest(another.path, another.name))) { true }
        var running = true
        val partial = cancel.copy(plan, { running }, { if (it.index == 2) running = false })
        assertEquals(CopyStatus.DIRECTORY_CREATED, partial.first().status)
        assertTrue(partial.drop(1).none { it.status == CopyStatus.COPIED || it.status == CopyStatus.DIRECTORY_CREATED })
        assertEquals(2, calls())
    }
    @Test fun forgedPlanAndRevokedGrantCannotCreate() {
        val folder = local()
        val source = LocalImportSource(StorageBoundary(root))
        val e = engine(source)
        val p = e.prepare(listOf(CopyRequest(folder.path, folder.name))) { true }
        val forged = SafTreePlan(p.nodes.mapIndexed { i, n -> if (i == 0) n.copy(relative = "../Folder") else n })
        assertThrows(Exception::class.java) { e.copy(forged, { true }, {}) }
        resolver.releasePersistableUriPermission(tree, flags)
        resolver.call(tree, "revoke-write", "write-root", null)
        assertThrows(Exception::class.java) { e.copy(p, { true }, {}) }
        assertEquals(0, calls())
    }
}
