package app.cantas.jcversa

import android.content.Intent
import android.os.CancellationSignal
import android.provider.DocumentsContract
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import org.junit.*
import org.junit.Assert.*
import java.io.ByteArrayInputStream

class SafFileCopyTest {
    private val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
    private val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
    private val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    private lateinit var target: SafFiles
    @Before fun setup() {
        resolver.call(tree, "write-reset", null, null)
        resolver.call(tree, "grant-write", "write-root", null)
        resolver.takePersistableUriPermission(tree, flags)
        target = SafFiles(resolver, tree)
    }
    @After fun cleanup() {
        runCatching { resolver.releasePersistableUriPermission(tree, flags) }
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "write-reset", null, null)
    }
    private fun source(directory: Boolean = false, unknown: Boolean = false, unchanged: Boolean = true) = object : ImportSource {
        override fun describe(path: String) = ImportEntry(path, path, directory, if (unknown) null else 7L)
        override fun children(path: String, limit: Int): List<ImportEntry> = error("No recursion")
        override fun input(path: String) = CopyInput(ByteArrayInputStream("fixture".toByteArray()), if (unknown) SafImportSource.UNKNOWN_SIZE_LIMIT else 7L, { unchanged }, !unknown)
    }
    private fun run(name: String, source: ImportSource = source()): CopyOutcome {
        val engine = SafFileCopy(resolver, target, source, CancellationSignal())
        val plan = engine.prepare(listOf(CopyRequest(name, name)), target.root) { true }
        return engine.copy(plan, target.root, { true }, {}).single()
    }
    @Test fun verifiesKnownAndUnknownFilesAndRefusesExistingNames() {
        val known = run("known.txt")
        assertEquals(CopyStatus.COPIED, known.status)
        assertEquals(7L, known.bytes)
        assertEquals("fixture", resolver.openInputStream(android.net.Uri.parse(known.target))!!.bufferedReader().use { it.readText() })
        assertEquals(CopyStatus.COPIED, run("unknown.txt", source(unknown = true)).status)
        assertEquals(CopyStatus.EXISTS, run("known.txt").status)
        assertEquals(CopyStatus.EXISTS, run("KEEP.txt").status)
        assertEquals(2, resolver.call(tree, "write-state", null, null)!!.getInt("calls"))
        assertEquals("fixture", resolver.openInputStream(DocumentsContract.buildDocumentUriUsingTree(tree, "keep"))!!.bufferedReader().use { it.readText() })
    }
    @Test fun uncertainOutputsAreRetainedAndCorruptionNeverReportsSuccess() {
        for (name in listOf("auto-rename", "throw-after-create", "corrupt-output")) {
            val result = run(name)
            assertEquals(CopyStatus.UNCERTAIN, result.status)
            assertTrue(result.created)
        }
        assertEquals(3, resolver.call(tree, "write-state", null, null)!!.getInt("folders"))
    }
    @Test fun readOnlyDirectoryBatchAndCancelledPreflightNeverMutate() {
        resolver.releasePersistableUriPermission(tree, flags)
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "grant-read", "write-root", null)
        resolver.takePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        val engine = SafFileCopy(resolver, target, source(), CancellationSignal())
        assertThrows(Exception::class.java) { engine.prepare(listOf(CopyRequest("x", "x")), target.root) { true } }
        resolver.call(tree, "grant-write", "write-root", null)
        resolver.takePersistableUriPermission(tree, flags)
        val mixed = object : ImportSource by source() {
            override fun describe(path: String) = ImportEntry(path, path, path == "folder", 7L)
        }
        val folders = SafFileCopy(resolver, target, mixed, CancellationSignal())
        assertThrows(Exception::class.java) { folders.prepare(listOf(CopyRequest("file", "file"), CopyRequest("folder", "folder")), target.root) { true } }
        assertThrows(Exception::class.java) { engine.prepare(listOf(CopyRequest("x", "x")), target.root) { false } }
        assertEquals(0, resolver.call(tree, "write-state", null, null)!!.getInt("calls"))
    }
    @Test fun realReadOnlySafSourceAndPartialCancellationPreserveOriginals() {
        val sourceTree = TreeFixtureProvider.TREE
        try {
            resolver.call(sourceTree, "grant-read", null, null)
            resolver.takePersistableUriPermission(sourceTree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            val request = CopyRequest(DocumentsContract.buildDocumentUriUsingTree(sourceTree, "text").toString(), "Résumé.txt")
            val input = SafImportSource(resolver, sourceTree, listOf(request), CancellationSignal()) { true }
            val engine = SafFileCopy(resolver, target, input, CancellationSignal())
            val plan = engine.prepare(listOf(request), target.root) { true }
            assertEquals(CopyStatus.COPIED, engine.copy(plan, target.root, { true }, {}).single().status)
            assertFalse(resolver.persistedUriPermissions.single { it.uri == sourceTree }.isWritePermission)
            assertEquals("fixture", resolver.openInputStream(android.net.Uri.parse(request.path))!!.bufferedReader().use { it.readText() })
            var running = true
            val cancel = SafFileCopy(resolver, target, source(), CancellationSignal())
            val p = cancel.prepare(listOf(CopyRequest("partial.txt", "partial.txt")), target.root) { running }
            val partial = cancel.copy(p, target.root, { running }, { running = false }).single()
            assertEquals(CopyStatus.CANCELLED, partial.status)
            assertTrue(partial.created)
            assertEquals(7L, partial.bytes)
            assertEquals(CopyStatus.SOURCE_CHANGED, run("changed.txt", source(unchanged = false)).status)
        } finally {
            resolver.releasePersistableUriPermission(sourceTree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            resolver.call(sourceTree, "revoke-read", null, null)
        }
    }
}
