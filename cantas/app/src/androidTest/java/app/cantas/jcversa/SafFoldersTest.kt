package app.cantas.jcversa

import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cantas.jcversa.data.*
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SafFoldersTest {
    private val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
    private val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
    private val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    private fun grant(write: Boolean = true) {
        resolver.call(tree, if (write) "grant-write" else "grant-read", "write-root", null)
        resolver.takePersistableUriPermission(tree, if (write) flags else Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    @Before fun setup() { resolver.call(tree, "write-reset", null, null) }
    @After fun cleanup() {
        runCatching { resolver.releasePersistableUriPermission(tree, flags) }
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "write-reset", null, null)
    }
    private fun calls() = resolver.call(tree, "write-state", null, null)!!.getInt("calls")
    @Test fun createsOpaqueFolderAndRefusesFileFolderAndCaseCollisions(): Unit = runBlocking {
        grant()
        val source = SafFiles(resolver, tree)
        source.listCancellable(source.root, BrowseOptions()) { true }
        val engine = SafFolders(resolver)
        assertTrue(engine.create(source, source.root, "Dossier é") is FolderCreation.Created)
        assertEquals(FolderCreation.Failed(FolderError.EXISTS), engine.create(source, source.root, "dossier É"))
        assertEquals(FolderCreation.Failed(FolderError.EXISTS), engine.create(source, source.root, "KEEP.txt"))
        val entries = source.listCancellable(source.root, BrowseOptions()) { true }.entries
        assertTrue(entries.single { it.name == "Dossier é" }.directory)
        assertEquals(7L, entries.single { it.name == "KEEP.txt" }.size)
        assertEquals(1, calls())
    }
    @Test fun readOnlyAndInvalidNameCannotInvokeProvider(): Unit = runBlocking {
        grant(false)
        val source = SafFiles(resolver, tree)
        assertEquals(FolderCreation.Failed(FolderError.ACCESS_DENIED), SafFolders(resolver).create(source, source.root, "Denied"))
        assertEquals(FolderCreation.Failed(FolderError.INVALID_NAME), SafFolders(resolver).create(source, source.root, "../escape"))
        assertEquals(0, calls())
    }
    @Test fun renamedAndPostCreationFailureAreUncertainAndRetained(): Unit = runBlocking {
        grant()
        val source = SafFiles(resolver, tree)
        val engine = SafFolders(resolver)
        assertEquals(FolderCreation.Failed(FolderError.UNCERTAIN), engine.create(source, source.root, "auto-rename"))
        assertEquals(FolderCreation.Failed(FolderError.UNCERTAIN), engine.create(source, source.root, "throw-after-create"))
        val entries = source.listCancellable(source.root, BrowseOptions()) { true }.entries
        assertTrue(entries.any { it.name == "auto-rename (1)" })
        assertTrue(entries.any { it.name == "throw-after-create" })
        assertTrue(entries.any { it.name == "KEEP.txt" })
        assertEquals(2, calls())
    }
    @Test fun unknownParentCancelledAndRevokedCannotCreate(): Unit = runBlocking {
        grant()
        val source = SafFiles(resolver, tree)
        val engine = SafFolders(resolver)
        val unknown = DocumentsContract.buildDocumentUriUsingTree(tree, "unknown").toString()
        assertTrue(engine.create(source, unknown, "Unknown") is FolderCreation.Failed)
        assertTrue(engine.create(source, source.root, "Cancelled") { false } is FolderCreation.Failed)
        resolver.releasePersistableUriPermission(tree, flags)
        resolver.call(tree, "revoke-write", "write-root", null)
        assertEquals(FolderCreation.Failed(FolderError.ACCESS_DENIED), engine.create(source, source.root, "Revoked"))
        assertEquals(0, calls())
    }
}
