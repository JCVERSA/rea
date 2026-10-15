package app.cantas.jcversa

import app.cantas.jcversa.data.LocalFiles
import app.cantas.jcversa.data.StorageBoundary
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.nio.file.Files

class LocalFilesTest {
    @get:Rule val temp = TemporaryFolder()
    @Test fun rootAndChildrenAllowed() {
        val root = temp.newFolder("root"); val boundary = StorageBoundary(root)
        assertEquals(root.canonicalFile,boundary.resolve(root.path))
        assertEquals(File(root,"folder").canonicalFile,boundary.resolve(File(root,"folder").path))
        assertNull(boundary.parent(root.path))
    }
    @Test fun neighboringPrefixDenied() {
        val root = temp.newFolder("root"); val neighbor = temp.newFolder("root-private")
        assertThrows(IllegalArgumentException::class.java) { StorageBoundary(root).resolve(neighbor.path) }
    }
    @Test fun traversalAndSymlinkDenied() {
        val root = temp.newFolder("root"); val outside = temp.newFolder("outside")
        val boundary = StorageBoundary(root)
        assertThrows(IllegalArgumentException::class.java) { boundary.resolve(File(root,"../outside").path) }
        Files.createSymbolicLink(File(root,"link").toPath(),outside.toPath())
        assertThrows(IllegalArgumentException::class.java) { boundary.resolve(File(root,"link").path) }
    }
    @Test fun foldersFirstHiddenExcludedNoMutation() {
        val root = temp.newFolder("root"); File(root,"z-folder").mkdir(); File(root,"a.txt").writeText("sample")
        File(root,".hidden").writeText("private")
        val before = root.list()!!.toSet(); val list = LocalFiles(StorageBoundary(root)).list(root.path)
        assertEquals(listOf("z-folder","a.txt"),list.entries.map { it.name })
        assertEquals(before,root.list()!!.toSet()); assertEquals("sample",File(root,"a.txt").readText())
        assertFalse(list.truncated)
    }
    @Test fun visibleMetadataCapped() {
        val root = temp.newFolder("root"); repeat(1002) { File(root,"file-$it").writeText("") }
        val list = LocalFiles(StorageBoundary(root)).list(root.path)
        assertEquals(1000,list.entries.size); assertTrue(list.truncated)
    }
    @Test fun unavailableDirectoryIsNotEmptySuccess() {
        val root = temp.newFolder("root")
        assertThrows(IllegalArgumentException::class.java) { LocalFiles(StorageBoundary(root)).list(File(root,"missing").path) }
    }

    @Test fun searchHiddenAndCaseInsensitive() {
        val root = temp.newFolder("root")
        File(root,"Alpha.txt").writeText("a"); File(root,".alpha-secret").writeText("s"); File(root,"beta").writeText("b")
        val repo = LocalFiles(StorageBoundary(root))
        assertEquals(listOf("Alpha.txt"), repo.list(root.path, app.cantas.jcversa.data.BrowseOptions(query = "ALPHA")).entries.map { it.name })
        assertEquals(2, repo.list(root.path, app.cantas.jcversa.data.BrowseOptions(query = "alpha", showHidden = true)).entries.size)
    }
    @Test fun sortedPagesAreStableAndSizeDescending() {
        val root = temp.newFolder("root")
        File(root,"z-folder").mkdir(); File(root,"b.txt").writeText("bbbb"); File(root,"a.txt").writeText("a")
        val repo = LocalFiles(StorageBoundary(root))
        val first = repo.list(root.path, app.cantas.jcversa.data.BrowseOptions(limit = 1))
        val more = repo.list(root.path, app.cantas.jcversa.data.BrowseOptions(limit = 3))
        assertTrue(first.truncated); assertEquals(first.entries, more.entries.take(1))
        assertEquals(listOf("z-folder", "b.txt", "a.txt"), repo.list(root.path,
            app.cantas.jcversa.data.BrowseOptions(sort = app.cantas.jcversa.data.FileSort.SIZE_DESC)).entries.map { it.name })
        assertEquals(listOf("z-folder", "b.txt", "a.txt"), repo.list(root.path,
            app.cantas.jcversa.data.BrowseOptions(sort = app.cantas.jcversa.data.FileSort.NAME_DESC)).entries.map { it.name })
    }
    @Test fun cancellationAndInvalidLimitsFailExplicitly() {
        val root = temp.newFolder("root"); File(root,"file").writeText("x")
        val repo = LocalFiles(StorageBoundary(root))
        assertThrows(java.util.concurrent.CancellationException::class.java) { repo.list(root.path, active = { false }) }
        assertThrows(IllegalArgumentException::class.java) { repo.list(root.path, app.cantas.jcversa.data.BrowseOptions(limit = 0)) }
    }

    @Test fun cancelledMissingFolderDoesNotBecomeIoError() {
        val root = temp.newFolder("root")
        assertThrows(java.util.concurrent.CancellationException::class.java) {
            LocalFiles(StorageBoundary(root)).list(File(root,"missing").path, active = { false })
        }
    }
}
