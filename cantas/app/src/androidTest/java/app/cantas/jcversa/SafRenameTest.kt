package app.cantas.jcversa

import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.DocumentsContract.Document
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import org.junit.*
import org.junit.Assert.*

class SafRenameTest {
    private val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
    private val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "write-root")
    private val root = DocumentsContract.buildDocumentUriUsingTree(tree, "write-root").toString()
    private val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
    @Before fun setup() {
        resolver.call(tree, "write-reset", null, null)
        resolver.call(tree, "grant-write", "write-root", null)
        resolver.takePersistableUriPermission(tree, flags)
    }
    @After fun cleanup() {
        runCatching { resolver.releasePersistableUriPermission(tree, flags) }
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "write-reset", null, null)
    }
    private fun file(name: String): Uri {
        val uri = DocumentsContract.createDocument(resolver, Uri.parse(root), "text/plain", name)!!
        resolver.openOutputStream(uri, "w")!!.use { it.write("original".toByteArray()) }
        return uri
    }
    private fun snapshot(): Pair<SafFiles, List<FileEntry>> {
        val source = SafFiles(resolver, tree)
        return source to source.list(root).entries
    }
    private fun read(uri: Uri) = resolver.openInputStream(uri)!!.bufferedReader().use { it.readText() }
    private fun calls() = resolver.call(tree, "write-state", null, null)!!.getInt("renames")
    @Test fun verifiedFileRenameSameAndChangedIdsPreserveBytes() {
        file("source.txt")
        val (source, entries) = snapshot()
        val first = SafRename(resolver).rename(source, root, entries.single { it.name == "source.txt" }, "Nouveau é.txt")
        assertTrue(first is RenameResult.Renamed)
        assertEquals("original", read(Uri.parse((first as RenameResult.Renamed).path)))
        val (fresh, renamed) = snapshot()
        val changed = SafRename(resolver).rename(fresh, root, renamed.single { it.name == "Nouveau é.txt" }, "change-id.txt") as RenameResult.Renamed
        assertNotEquals(first.path, changed.path)
        assertEquals("original", read(Uri.parse(changed.path)))
        assertEquals("fixture", read(DocumentsContract.buildDocumentUriUsingTree(tree, "keep")))
        assertEquals(2, calls())
    }
    @Test fun directoryRenamePreservesNestedChildAndRejectsExistingName() {
        val directory = DocumentsContract.createDocument(resolver, Uri.parse(root), Document.MIME_TYPE_DIR, "Folder")!!
        val child = DocumentsContract.createDocument(resolver, directory, "text/plain", "child.txt")!!
        resolver.openOutputStream(child, "w")!!.use { it.write("child".toByteArray()) }
        val (source, entries) = snapshot()
        val entry = entries.single { it.name == "Folder" }
        assertEquals(RenameResult.Failed(RenameError.EXISTS), SafRename(resolver).rename(source, root, entry, "keep.TXT"))
        assertEquals(0, calls())
        assertTrue(SafRename(resolver).rename(source, root, entry, "Renamed folder") is RenameResult.Renamed)
        assertEquals("child", read(child))
        assertEquals(1, calls())
    }
    @Test fun readOnlyRevokedUnsupportedStaleAndInvalidCannotInvokeRename() {
        file("source.txt")
        val (source, entries) = snapshot()
        val entry = entries.single { it.name == "source.txt" }
        val engine = SafRename(resolver)
        assertEquals(RenameResult.Failed(RenameError.INVALID_NAME), engine.rename(source, root, entry, "../escape"))
        assertEquals(RenameResult.Failed(RenameError.UNAVAILABLE), engine.rename(source, root, entry.copy(name = "stale"), "Target"))
        assertEquals(RenameResult.Failed(RenameError.UNSUPPORTED), engine.rename(source, root, entries.single { it.name == "KEEP.txt" }, "Other"))
        resolver.releasePersistableUriPermission(tree, flags)
        resolver.call(tree, "revoke-write", "write-root", null)
        resolver.call(tree, "grant-read", "write-root", null)
        resolver.takePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        assertEquals(RenameResult.Failed(RenameError.ACCESS_DENIED), engine.rename(source, root, entry, "Denied"))
        resolver.releasePersistableUriPermission(tree, Intent.FLAG_GRANT_READ_URI_PERMISSION)
        resolver.call(tree, "revoke-write", "write-root", null)
        assertEquals(RenameResult.Failed(RenameError.ACCESS_DENIED), engine.rename(source, root, entry, "Revoked"))
        assertEquals(0, calls())
    }
    @Test fun providerChangedNameAndPostRenameExceptionAreUncertainWithoutRollback() {
        for (name in listOf("auto-rename", "throw-after-rename")) {
            val uri = file("source-${calls()}.txt")
            val (source, entries) = snapshot()
            val result = SafRename(resolver).rename(source, root, entries.single { it.path == uri.toString() }, name)
            assertEquals(RenameResult.Failed(RenameError.UNCERTAIN), result)
            assertEquals("original", read(uri))
        }
        val names = snapshot().second.map { it.name }
        assertTrue("auto-rename (1)" in names && "throw-after-rename" in names)
        assertEquals(2, calls())
    }
}
