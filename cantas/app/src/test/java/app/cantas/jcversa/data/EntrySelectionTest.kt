package app.cantas.jcversa.data

import org.junit.Assert.*
import org.junit.Test
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import java.io.File

class EntrySelectionTest {
    @get:Rule val temp = TemporaryFolder()
    @Test fun sharedFilterAndFoldersFirst() {
        val selected = EntrySelection(BrowseOptions(query = " TXT ", sort = FileSort.SIZE_DESC))
        val entries = listOf(FileEntry("opaque:1", "a.TXT", false, 10),
            FileEntry("opaque:2", "z.txt", true, 0), FileEntry("opaque:3", ".secret.txt", false, 100))
        entries.filter { selected.matches(it.name) }.forEach(selected::add)
        assertEquals(listOf("opaque:2", "opaque:1"), selected.snapshot("root").entries.map { it.path })
        assertFalse(selected.snapshot("root").truncated)
    }
    @Test fun duplicateIdsAndGloballySortedPrefix() {
        val selected = EntrySelection(BrowseOptions(limit = 2))
        listOf("z", "a", "c", "a", "b").forEach { selected.add(FileEntry(it, it, false, 0)) }
        val result = selected.snapshot("opaque-root", "Display name")
        assertEquals(listOf("a", "b"), result.entries.map { it.name })
        assertTrue(result.truncated)
        assertEquals("Display name", result.title)
    }
    @Test fun invalidLimitsAreRejected() {
        assertThrows(IllegalArgumentException::class.java) { EntrySelection(BrowseOptions(limit = 0)) }
        assertThrows(IllegalArgumentException::class.java) { EntrySelection(BrowseOptions(limit = 100001)) }
    }
    @Test fun localReadTargetUsesSameBoundary() {
        val root = temp.newFolder("root")
        val file = File(root, "document.txt").apply { writeText("synthetic") }
        val source: ReadOnlyStorage = LocalFiles(StorageBoundary(root))
        assertEquals(file.canonicalPath, source.readTarget(file.path).location)
        assertNull(source.parent(source.root))
        assertThrows(IllegalArgumentException::class.java) { source.readTarget(root.path) }
        assertThrows(IllegalArgumentException::class.java) { source.readTarget(temp.newFile("outside.txt").path) }
    }
}
