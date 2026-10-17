package app.cantas.jcversa.data

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class ImportCopyTest {
    @get:Rule val temp = TemporaryFolder()
    private class Source : ImportSource {
        val folder = ImportEntry("opaque:folder/not-path", "Folder", true, null)
        val file = ImportEntry("opaque:child!", "file.txt", false, 7)
        val empty = ImportEntry("opaque:empty", "Empty", true, null)
        var entries = listOf(file, empty)
        var opened = 0
        var unknown = false
        override fun describe(path: String) = when (path) { folder.path -> folder; file.path -> file; empty.path -> empty; else -> throw SecurityException() }
        override fun children(path: String, limit: Int): List<ImportEntry> {
            val values = if (path == folder.path) entries else emptyList()
            require(values.size <= limit)
            return values
        }
        override fun input(path: String): CopyInput {
            require(path == file.path); opened++
            return CopyInput("fixture".byteInputStream(), if (unknown) 20 else 7, { true }, exactSize = !unknown)
        }
    }
    private val streams = object : CopyStreams {
        override fun input(file: File): CopyInput = error("A SAF import must never open a native source")
        override fun exclusiveOutput(file: File): CopyOutput {
            val out = try { Files.newOutputStream(file.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE) }
                catch (_: java.nio.file.FileAlreadyExistsException) { throw CopyCollision() }
            return CopyOutput(out) { out.flush() }
        }
    }
    private fun fixture(test: (Source, File, ImportCopy) -> Unit) {
        val root = temp.newFolder()
        val dest = File(root, "destination").apply { mkdir() }
        val source = Source()
        test(source, dest, ImportCopy(StorageBoundary(root), streams, source))
    }
    @Test fun opaqueFolderAndEmptyChildrenImportWithoutNativeSourceReads() = fixture { source, dest, engine ->
        val plan = engine.prepare(listOf(CopyRequest(source.folder.path, "Folder")), dest.path) { true }
        assertTrue(dest.listFiles()!!.isEmpty())
        val results = engine.copy(plan, { true }, {})
        assertEquals(2, results.count { it.status == CopyStatus.DIRECTORY_CREATED })
        assertEquals(1, results.count { it.status == CopyStatus.COPIED })
        assertEquals("fixture", File(dest, "Folder/file.txt").readText())
        assertTrue(File(dest, "Folder/Empty").isDirectory)
        assertEquals(1, source.opened)
    }
    @Test fun existingFolderNeverMergesOrOpensDescendantStreams() = fixture { source, dest, engine ->
        val existing = File(dest, "Folder").apply { mkdir() }
        File(existing, "marker").writeText("KEEP")
        val results = engine.copy(engine.prepare(listOf(CopyRequest(source.folder.path, "Folder")), dest.path) { true }, { true }, {})
        assertEquals(CopyStatus.EXISTS, results.first().status)
        assertTrue(results.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
        assertEquals("KEEP", File(existing, "marker").readText())
        assertEquals(0, source.opened)
    }
    @Test fun duplicateOrCyclicProviderRefusesPreparation() = fixture { source, dest, engine ->
        source.entries = listOf(source.folder)
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.folder.path, "Folder")), dest.path) { true } }
        source.entries = listOf(source.file, source.file)
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.folder.path, "Folder")), dest.path) { true } }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun cancelledAndRenamedRootsRefuseBeforeWrites() = fixture { source, dest, engine ->
        assertThrows(CopyPreparationStopped::class.java) { engine.prepare(listOf(CopyRequest(source.file.path, source.file.name)), dest.path) { false } }
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.file.path, "stale-name")), dest.path) { true } }
        assertTrue(dest.listFiles()!!.isEmpty())
        assertEquals(0, source.opened)
    }
    @Test fun unknownLengthStreamImportsOnlyUntilEof() = fixture { source, dest, engine ->
        source.unknown = true
        val results = engine.copy(engine.prepare(listOf(CopyRequest(source.file.path, source.file.name)), dest.path) { true }, { true }, {})
        assertEquals(CopyStatus.COPIED, results.single().status)
        assertEquals(7L, results.single().bytes)
        assertEquals("fixture", File(dest, "file.txt").readText())
    }
    @Test fun malformedPlanCannotEscapeDestination() = fixture { source, dest, engine ->
        assertThrows(IllegalArgumentException::class.java) {
            engine.copy(TreeCopyPlan(dest.path, listOf(TreeCopyNode(source.file.path, "../escape", false))), { true }, {})
        }
        assertFalse(File(dest.parentFile, "escape").exists())
        assertTrue(dest.listFiles()!!.isEmpty())
    }
}
