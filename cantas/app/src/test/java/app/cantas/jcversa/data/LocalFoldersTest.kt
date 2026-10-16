package app.cantas.jcversa.data

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.nio.file.Files
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LocalFoldersTest {
    @get:Rule val temp = TemporaryFolder()
    @Test fun createsOneFolderWithUnicodeName() {
        val root = temp.newFolder("root")
        val source = LocalFolders(StorageBoundary(root))
        val result = source.create(root.path, "Été 📁") as FolderCreation.Created
        assertEquals(File(root, "Été 📁").path, result.path)
        assertTrue(File(result.path).isDirectory)
        assertEquals(1, root.listFiles()!!.size)
    }
    @Test fun rejectsInvalidNamesBeforeAnyWrite() {
        val root = temp.newFolder("root")
        val source = LocalFolders(StorageBoundary(root))
        listOf("", " ", ".", "..", "../escape", "a/b", "a\\b", "name\n", "\u0000", " trim", "trim ", "x".repeat(256), "é".repeat(128), "\uD800").forEach {
            assertEquals(FolderCreation.Failed(FolderError.INVALID_NAME), source.create(root.path, it))
        }
        assertTrue(root.listFiles()!!.isEmpty())
        assertTrue(LocalFolders.validName("x".repeat(255)))
        assertTrue(LocalFolders.validName("é".repeat(127)))
    }
    @Test fun existingFilesAndFoldersAreNeverReplaced() {
        val root = temp.newFolder("root")
        val file = File(root, "kept.txt").apply { writeText("original") }
        val folder = File(root, "kept").apply { mkdir() }
        val marker = File(folder, "marker").apply { writeText("keep me") }
        val source = LocalFolders(StorageBoundary(root))
        assertEquals(FolderCreation.Failed(FolderError.EXISTS), source.create(root.path, file.name))
        assertEquals(FolderCreation.Failed(FolderError.EXISTS), source.create(root.path, folder.name))
        assertEquals("original", file.readText())
        assertEquals("keep me", marker.readText())
    }
    @Test fun rejectsOutsideMissingAndNonDirectoryParents() {
        val root = temp.newFolder("root")
        val outside = temp.newFolder("outside")
        val source = LocalFolders(StorageBoundary(root))
        val notDirectory = File(root, "file").apply { writeText("unchanged") }
        listOf(outside.path, File(root, "missing").path, notDirectory.path).forEach {
            assertEquals(FolderCreation.Failed(FolderError.UNAVAILABLE), source.create(it, "child"))
        }
        assertTrue(outside.listFiles()!!.isEmpty())
        assertFalse(File(root, "missing").exists())
        assertEquals("unchanged", notDirectory.readText())
    }
    @Test fun concurrentCollisionHasExactlyOneWinner() {
        val root = temp.newFolder("root")
        val source = LocalFolders(StorageBoundary(root))
        val pool = Executors.newFixedThreadPool(2)
        val start = CountDownLatch(1)
        try {
            val tasks = (1..2).map { pool.submit<FolderCreation> { start.await(); source.create(root.path, "same") } }
            start.countDown()
            val results = tasks.map { it.get(5, TimeUnit.SECONDS) }
            assertEquals(1, results.count { it is FolderCreation.Created })
            assertEquals(1, results.count { it == FolderCreation.Failed(FolderError.EXISTS) })
            assertTrue(File(root, "same").isDirectory)
        } finally { pool.shutdownNow() }
    }
    @Test fun existingSymlinksAndEscapingParentsAreNotModified() {
        val root = temp.newFolder("root")
        val outside = temp.newFolder("outside")
        val kept = File(outside, "kept").apply { writeText("unchanged") }
        Files.createSymbolicLink(File(root, "link").toPath(), outside.toPath())
        Files.createSymbolicLink(File(root, "broken").toPath(), File(outside, "absent").toPath())
        val source = LocalFolders(StorageBoundary(root))
        assertEquals(FolderCreation.Failed(FolderError.EXISTS), source.create(root.path, "link"))
        assertTrue(source.create(root.path, "broken") is FolderCreation.Failed)
        assertEquals(FolderCreation.Failed(FolderError.UNAVAILABLE), source.create(File(root, "link").path, "child"))
        assertTrue(Files.isSymbolicLink(File(root, "broken").toPath()))
        assertEquals("unchanged", kept.readText())
        assertFalse(File(outside, "child").exists())
    }
}
