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
