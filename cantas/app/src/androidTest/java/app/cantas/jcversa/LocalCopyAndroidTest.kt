package app.cantas.jcversa

import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.FilterOutputStream
import java.util.concurrent.atomic.AtomicBoolean

class LocalCopyAndroidTest {
    private fun fixture(block: (File, File, File) -> Unit) {
        val root = File(InstrumentationRegistry.getInstrumentation().targetContext.cacheDir, "native-copy-${System.nanoTime()}")
        assertTrue(root.mkdir())
        try {
            val source = File(root, "source.bin").apply { writeBytes(ByteArray(200000) { (it % 251).toByte() }) }
            val dest = File(root, "dest").apply { mkdir() }
            block(root, source, dest)
        } finally { root.deleteRecursively() }
    }
    @Test fun exclusiveNativeCopyDoesNotReplaceFilesOrSymlinks() = fixture { root, source, dest ->
        val engine = LocalCopy(StorageBoundary(root), AndroidCopyStreams())
        val request = listOf(CopyRequest(source.path, source.name))
        assertEquals(CopyStatus.COPIED, engine.copy(request, dest.path).single().status)
        assertArrayEquals(source.readBytes(), File(dest, source.name).readBytes())
        File(dest, source.name).writeText("KEEP")
        assertEquals(CopyStatus.EXISTS, engine.copy(request, dest.path).single().status)
        assertEquals("KEEP", File(dest, source.name).readText())
        android.system.Os.symlink(source.path, File(dest, "alias").path)
        assertEquals(CopyStatus.EXISTS, engine.copy(listOf(CopyRequest(source.path, "alias")), dest.path).single().status)
        assertEquals(200000L, source.length())
        val directory = engine.copy(listOf(CopyRequest(root.path, "not-a-file")), dest.path).single()
        assertEquals(CopyStatus.FAILED, directory.status)
        assertFalse(directory.created)
        assertFalse(File(dest, "not-a-file").exists())
    }
    @Test fun nativeCancellationRetainsReportedPartial() = fixture { root, source, dest ->
        val active = AtomicBoolean(true)
        val native = AndroidCopyStreams()
        val streams = object : CopyStreams {
            override fun input(file: File) = native.input(file)
            override fun exclusiveOutput(file: File): CopyOutput {
                val output = native.exclusiveOutput(file)
                return CopyOutput(object : FilterOutputStream(output.stream) {
                    override fun write(bytes: ByteArray, offset: Int, length: Int) { out.write(bytes, offset, length); active.set(false) }
                }, output.sync)
            }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path, active::get).single()
        assertEquals(CopyStatus.CANCELLED, result.status)
        assertTrue(result.created)
        assertEquals(65536L, File(dest, source.name).length())
        assertEquals(200000L, source.length())
    }
    @Test fun sourceGrowthAfterNativeOpenDoesNotExtendOutput() = fixture { root, source, dest ->
        val original = source.readBytes()
        var appended = false
        val result = LocalCopy(StorageBoundary(root), AndroidCopyStreams()).copy(
            listOf(CopyRequest(source.path, source.name)), dest.path, progress = {
                if (!appended) {
                    // Simulate an independent writer after the native descriptor size was captured.
                    source.appendBytes(byteArrayOf(9, 8, 7))
                    appended = true
                }
            }).single()
        assertTrue(appended)
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertTrue(result.created)
        assertEquals(original.size.toLong(), result.bytes)
        assertArrayEquals(original, File(dest, source.name).readBytes())
        assertEquals(original.size.toLong() + 3, source.length())
    }
    @Test fun nativeTreeCopyAndJournalKeepDirectoryOutcomes() = fixture { root, _, dest ->
        val folder = File(root.canonicalFile, "Folder").apply { mkdir() }
        File(folder, "Nested").mkdir()
        File(folder, "Empty").mkdir()
        File(folder, "Nested/data.txt").writeText("ORIGINAL")
        val engine = LocalTreeCopy(StorageBoundary(root), AndroidCopyStreams())
        val requests = listOf(CopyRequest(folder.path, folder.name))
        val plan = engine.prepare(requests, dest.canonicalPath)
        val journal = CopyJournal(File(root, "tree-journal.json"))
        journal.start(plan.destination, requests)
        val results = engine.copy(plan)
        assertEquals(4, results.size)
        assertEquals(3, results.count { it.status == CopyStatus.DIRECTORY_CREATED })
        assertEquals("ORIGINAL", File(dest, "Folder/Nested/data.txt").readText())
        assertTrue(File(dest, "Folder/Empty").isDirectory)
        journal.finish(plan.destination, results)
        assertEquals(results, journal.read()!!.results)
        File(dest, "Folder/Nested/data.txt").writeText("KEEP")
        val collision = engine.copy(engine.prepare(requests, dest.canonicalPath))
        assertEquals(CopyStatus.EXISTS, collision.first().status)
        assertTrue(collision.drop(1).all { it.status == CopyStatus.NOT_ATTEMPTED })
        assertEquals("KEEP", File(dest, "Folder/Nested/data.txt").readText())
        assertEquals("ORIGINAL", File(folder, "Nested/data.txt").readText())
        journal.clear()
    }
    @Test fun nativeSymbolicTreeChildRefusesWholeBatchBeforeWrites() = fixture { root, _, dest ->
        val folder = File(root.canonicalFile, "Folder").apply { mkdir() }
        android.system.Os.symlink(dest.canonicalPath, File(folder, "alias").path)
        val engine = LocalTreeCopy(StorageBoundary(root), AndroidCopyStreams())
        assertThrows(IllegalArgumentException::class.java) { engine.prepare(listOf(CopyRequest(folder.path, folder.name)), dest.canonicalPath) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun privateJournalRestoresInterruptedAndFinishedReports() = fixture { root, source, dest ->
        val file = File(root, "journal.json")
        val requests = listOf(CopyRequest(source.path, source.name))
        CopyJournal(file).start(dest.path, requests)
        val interrupted = CopyJournal(file).read()!!
        assertTrue(interrupted.interrupted)
        assertEquals(listOf(source.name), interrupted.names)
        val outcomes = LocalCopy(StorageBoundary(root), AndroidCopyStreams()).copy(requests, dest.path)
        CopyJournal(file).finish(dest.path, outcomes)
        assertEquals(outcomes, CopyJournal(file).read()!!.results)
        CopyJournal(file).clear()
        assertNull(CopyJournal(file).read())
        assertArrayEquals(source.readBytes(), File(dest, source.name).readBytes())
    }
}
