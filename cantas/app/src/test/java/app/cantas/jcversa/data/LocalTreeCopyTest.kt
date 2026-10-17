package app.cantas.jcversa.data

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LocalTreeCopyTest {
    @get:Rule val temp = TemporaryFolder()
    private val streams = object : CopyStreams {
        override fun input(file: File): CopyInput {
            val size = file.length(); val stamp = file.lastModified()
            return CopyInput(file.inputStream(), size) { file.length() == size && file.lastModified() == stamp }
        }
        override fun exclusiveOutput(file: File): CopyOutput {
            val output = try { Files.newOutputStream(file.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE) }
                catch (_: java.nio.file.FileAlreadyExistsException) { throw CopyCollision() }
            return CopyOutput(output) { output.flush() }
        }
    }
    private fun fixture(block: (File, File, File, LocalTreeCopy) -> Unit) {
        val root = temp.newFolder()
        val source = File(root, "Source").apply { mkdir() }
        File(source, "Nested").mkdir()
        File(source, "Nested/data.txt").writeText("ORIGINAL")
        File(source, "Empty").mkdir()
        File(source, ".hidden").writeText("HIDDEN")
        val dest = File(root, "Destination").apply { mkdir() }
        block(root, source, dest, LocalTreeCopy(StorageBoundary(root), streams))
    }
    @Test fun nestedEmptyAndHiddenEntriesAreCopiedWithoutSourceMutation() = fixture { _, source, dest, engine ->
        val plan = engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path)
        assertTrue(dest.listFiles()!!.isEmpty())
        val results = engine.copy(plan)
        assertEquals(5, results.size)
        assertEquals(3, results.count { it.status == CopyStatus.DIRECTORY_CREATED })
        assertEquals(2, results.count { it.status == CopyStatus.COPIED })
        assertEquals("ORIGINAL", File(dest, "Source/Nested/data.txt").readText())
        assertEquals("HIDDEN", File(dest, "Source/.hidden").readText())
        assertTrue(File(dest, "Source/Empty").isDirectory)
        assertEquals("ORIGINAL", File(source, "Nested/data.txt").readText())
    }
    @Test fun existingRootIsSkippedWithoutMergingAnyDescendant() = fixture { _, source, dest, engine ->
        val existing = File(dest, source.name).apply { mkdir() }
        File(existing, "marker").writeText("KEEP")
        val results = engine.copy(engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path))
        assertEquals(CopyStatus.EXISTS, results.first().status)
        assertTrue(results.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
        assertEquals(listOf("marker"), existing.list()!!.toList())
        assertEquals("KEEP", File(existing, "marker").readText())
    }
    @Test fun destinationInsideSourceIsRejectedBeforeWrites() = fixture { _, source, _, engine ->
        val dest = File(source, "Nested")
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path) }
        assertEquals(listOf("data.txt"), dest.list()!!.toList())
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.path, source.name)), source.path) }
    }
    @Test fun symbolicChildIsRefusedAndDestinationUntouched() = fixture { root, source, dest, engine ->
        Files.createSymbolicLink(File(source, "alias").toPath(), File(root, "Destination").toPath())
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun entryAndDepthLimitsRefusePreparationWithoutWrites() = fixture { _, source, dest, engine ->
        repeat(LocalTreeCopy.MAX_ENTRIES) { File(source, "file-$it").writeText("") }
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path) }
        assertTrue(dest.listFiles()!!.isEmpty())
        val deep = File(source.parentFile, "Deep").apply { mkdir() }
        var cursor = deep
        repeat(LocalTreeCopy.MAX_DEPTH + 1) { cursor = File(cursor, "d").apply { mkdir() } }
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(deep.path, deep.name)), dest.path) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun cancellationKeepsCreatedFolderAndSkipsItsChildren() = fixture { _, source, dest, engine ->
        val plan = engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path)
        val results = engine.copy(plan, { !File(dest, "Source").exists() })
        assertEquals(CopyStatus.DIRECTORY_CREATED, results.first().status)
        assertTrue(results.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
        assertTrue(File(dest, "Source").isDirectory)
        assertTrue(File(dest, "Source").listFiles()!!.isEmpty())
        assertEquals("ORIGINAL", File(source, "Nested/data.txt").readText())
    }
    @Test fun maliciousOrChangedPlansCannotWriteOutsideDestination() = fixture { root, source, dest, engine ->
        val bad = TreeCopyPlan(dest.path, listOf(TreeCopyNode(source.path, "../escape", true)))
        assertThrows(IllegalArgumentException::class.java) { engine.copy(bad) }
        assertFalse(File(root, "escape").exists())
        val plan = engine.prepare(listOf(CopyRequest(source.path, source.name)), dest.path)
        File(source, "Nested/data.txt").delete()
        assertThrows(IllegalArgumentException::class.java) { engine.copy(plan) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun cancelledPreparationAndDuplicateRootsCreateNothing() = fixture { _, source, dest, engine ->
        val requests = listOf(CopyRequest(source.path, source.name))
        assertThrows(CopyPreparationStopped::class.java) { engine.prepare(requests, dest.path) { false } }
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(requests + requests, dest.path) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
}
