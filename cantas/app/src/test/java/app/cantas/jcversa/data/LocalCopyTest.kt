package app.cantas.jcversa.data

import java.io.File
import java.io.FilterOutputStream
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.concurrent.atomic.AtomicBoolean
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class LocalCopyTest {
    @get:Rule val temp = TemporaryFolder()
    private open class Streams : CopyStreams {
        override fun input(file: File): CopyInput {
            require(file.isFile)
            val size = file.length(); val stamp = file.lastModified()
            return CopyInput(file.inputStream(), size) { file.length() == size && file.lastModified() == stamp }
        }
        override fun exclusiveOutput(file: File): CopyOutput {
            val stream = try { Files.newOutputStream(file.toPath(), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE) }
                catch (_: java.nio.file.FileAlreadyExistsException) { throw CopyCollision() }
            return CopyOutput(stream) { stream.flush() }
        }
    }
    private fun setup(): Triple<File, File, File> {
        val root = temp.newFolder("root")
        val source = File(root, "source.bin").apply { writeBytes(ByteArray(200000) { (it % 251).toByte() }) }
        val dest = File(root, "dest").apply { mkdir() }
        return Triple(root, source, dest)
    }
    @Test fun copiesBytesWithoutChangingOriginal() {
        val (root, source, dest) = setup()
        val original = source.readBytes()
        val result = LocalCopy(StorageBoundary(root), Streams()).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.COPIED, result.status)
        assertTrue(result.created)
        assertEquals(original.size.toLong(), result.bytes)
        assertArrayEquals(original, source.readBytes())
        assertArrayEquals(original, File(dest, source.name).readBytes())
    }
    @Test fun collisionsNeverTruncateFilesOrDirectories() {
        val (root, source, dest) = setup()
        val existing = File(dest, source.name).apply { writeText("keep") }
        val engine = LocalCopy(StorageBoundary(root), Streams())
        assertEquals(CopyStatus.EXISTS, engine.copy(listOf(CopyRequest(source.path, source.name)), dest.path).single().status)
        assertEquals("keep", existing.readText())
        File(dest, "directory").mkdir()
        assertEquals(CopyStatus.EXISTS, engine.copy(listOf(CopyRequest(source.path, "directory")), dest.path).single().status)
        assertTrue(File(dest, "directory").isDirectory)
    }
    @Test fun cancellationRetainsAndReportsPartialAndSkipsRemaining() {
        val (root, source, dest) = setup()
        val active = AtomicBoolean(true)
        val streams = object : Streams() {
            override fun exclusiveOutput(file: File): CopyOutput {
                val output = super.exclusiveOutput(file)
                return CopyOutput(object : FilterOutputStream(output.stream) {
                    override fun write(bytes: ByteArray, offset: Int, length: Int) {
                        out.write(bytes, offset, length); active.set(false)
                    }
                }, output.sync)
            }
        }
        val results = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name), CopyRequest(source.path, "later.bin")), dest.path, active::get)
        assertEquals(CopyStatus.CANCELLED, results[0].status)
        assertTrue(results[0].created)
        assertEquals(65536, File(dest, source.name).length().toInt())
        assertEquals(200000, source.length().toInt())
        assertEquals(CopyStatus.NOT_ATTEMPTED, results[1].status)
        assertFalse(File(dest, "later.bin").exists())
    }
    @Test fun cancelledBeforeStartCreatesNothing() {
        val (root, source, dest) = setup()
        val result = LocalCopy(StorageBoundary(root), Streams()).copy(listOf(CopyRequest(source.path, source.name)), dest.path, { false }).single()
        assertEquals(CopyStatus.NOT_ATTEMPTED, result.status)
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun invalidNamesOutsideSourcesAndDirectorySourcesRefused() {
        val (root, source, dest) = setup()
        val outside = temp.newFile("outside")
        val engine = LocalCopy(StorageBoundary(root), Streams())
        val results = engine.copy(listOf(CopyRequest(source.path, "../escape"), CopyRequest(outside.path, "outside"), CopyRequest(root.path, "dir")), dest.path)
        assertEquals(listOf(CopyStatus.INVALID, CopyStatus.FAILED, CopyStatus.FAILED), results.map { it.status })
        assertTrue(dest.listFiles()!!.isEmpty())
        assertThrows(IllegalArgumentException::class.java) { engine.copy(listOf(CopyRequest(source.path, source.name)), temp.root.path) }
    }
    @Test fun writeFailureRetainsPartialWithoutTouchingSource() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun exclusiveOutput(file: File): CopyOutput {
                val output = super.exclusiveOutput(file)
                return CopyOutput(object : FilterOutputStream(output.stream) {
                    override fun write(bytes: ByteArray, offset: Int, length: Int) {
                        out.write(bytes, offset, 7); throw java.io.IOException("synthetic disk full")
                    }
                }, output.sync)
            }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.FAILED, result.status)
        assertTrue(result.created)
        assertEquals(7L, File(dest, source.name).length())
        assertEquals(200000L, source.length())
    }
    @Test fun metadataChangeIsNotReportedAsSuccess() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = super.input(file).copy(unchanged = { false })
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertTrue(File(dest, source.name).exists())
    }
    @Test fun emptyFileIsACompleteCopy() {
        val (root, source, dest) = setup()
        source.writeBytes(byteArrayOf())
        val result = LocalCopy(StorageBoundary(root), Streams()).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.COPIED, result.status)
        assertEquals(0L, File(dest, source.name).length())
        assertTrue(result.created)
    }
    @Test fun concurrentCreatorsCannotOverwriteEachOther() {
        val (root, source, dest) = setup()
        val start = java.util.concurrent.CountDownLatch(1)
        val pool = java.util.concurrent.Executors.newFixedThreadPool(2)
        try {
            val jobs = (1..2).map { pool.submit<CopyOutcome> {
                start.await()
                LocalCopy(StorageBoundary(root), Streams()).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
            } }
            start.countDown()
            val results = jobs.map { it.get(5, java.util.concurrent.TimeUnit.SECONDS) }
            assertEquals(1, results.count { it.status == CopyStatus.COPIED })
            assertEquals(1, results.count { it.status == CopyStatus.EXISTS && !it.created })
            assertArrayEquals(source.readBytes(), File(dest, source.name).readBytes())
        } finally { pool.shutdownNow() }
    }
    @Test fun growingStreamCannotWriteBeyondCapturedSizeEvenWithUnchangedMetadata() {
        val (root, source, dest) = setup()
        var reads = 0
        val input = object : java.io.InputStream() {
            override fun read(): Int { check(++reads <= 4); return 42 }
            override fun read(bytes: ByteArray, offset: Int, length: Int): Int {
                check(++reads <= 4) // Regression fails promptly rather than filling the test disk.
                java.util.Arrays.fill(bytes, offset, offset + length, 42.toByte())
                return length
            }
        }
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(input, 3L) { true }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertEquals(3L, result.bytes)
        assertArrayEquals(byteArrayOf(42, 42, 42), File(dest, source.name).readBytes())
        assertEquals(2, reads)
        assertEquals(200000L, source.length())
    }
    @Test fun growthFromEmptySourceIsNotReportedAsAnEmptySuccess() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(java.io.ByteArrayInputStream(byteArrayOf(7)), 0L) { true }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertTrue(result.created)
        assertEquals(0L, File(dest, source.name).length())
    }
    @Test fun earlyEndOfSourceRetainsAndReportsShortOutput() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(java.io.ByteArrayInputStream(byteArrayOf(1, 2, 3)), 100L) { true }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertEquals(3L, result.bytes)
        assertTrue(result.created)
        assertArrayEquals(byteArrayOf(1, 2, 3), File(dest, source.name).readBytes())
    }
    @Test fun nonProgressingReaderFailsAndClosesRatherThanSpinning() {
        val (root, source, dest) = setup()
        var closed = false
        var reads = 0
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(object : java.io.InputStream() {
                override fun read(): Int = 0
                override fun read(bytes: ByteArray, offset: Int, length: Int): Int { check(++reads <= 4); return 0 }
                override fun close() { closed = true }
            }, 10L) { true }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.FAILED, result.status)
        assertTrue(result.created)
        assertTrue(closed)
        assertEquals(1, reads)
        assertEquals(0L, File(dest, source.name).length())
    }
    @Test fun negativeCapturedSizeClosesInputWithoutCreatingOutput() {
        val (root, source, dest) = setup()
        var closed = false
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(object : java.io.ByteArrayInputStream(byteArrayOf(1)) {
                override fun close() { closed = true; super.close() }
            }, -1L) { true }
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.FAILED, result.status)
        assertFalse(result.created)
        assertTrue(closed)
        assertFalse(File(dest, source.name).exists())
    }
    @Test fun unknownLengthEofBeforeBudgetIsSuccessful() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(byteArrayOf(1, 2, 3).inputStream(), 10, { true }, exactSize = false)
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.COPIED, result.status)
        assertEquals(3L, result.bytes)
    }
    @Test fun unknownLengthCannotWriteBeyondBudget() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(byteArrayOf(1, 2, 3, 4).inputStream(), 3, { true }, exactSize = false)
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.SOURCE_CHANGED, result.status)
        assertEquals(3L, File(dest, source.name).length())
        assertArrayEquals(byteArrayOf(1, 2, 3), File(dest, source.name).readBytes())
    }
    @Test fun unknownLengthEmptyStreamCreatesEmptyCompleteFile() {
        val (root, source, dest) = setup()
        val streams = object : Streams() {
            override fun input(file: File) = CopyInput(byteArrayOf().inputStream(), 10, { true }, exactSize = false)
        }
        val result = LocalCopy(StorageBoundary(root), streams).copy(listOf(CopyRequest(source.path, source.name)), dest.path).single()
        assertEquals(CopyStatus.COPIED, result.status)
        assertTrue(result.created)
        assertEquals(0L, File(dest, source.name).length())
    }
    @Test fun batchLimitAndEmptyInputRejectedBeforeOutput() {
        val (root, source, dest) = setup()
        val engine = LocalCopy(StorageBoundary(root), Streams())
        assertThrows(IllegalArgumentException::class.java) { engine.copy(emptyList(), dest.path) }
        assertThrows(IllegalArgumentException::class.java) { engine.copy(List(101) { CopyRequest(source.path, "$it") }, dest.path) }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
}
