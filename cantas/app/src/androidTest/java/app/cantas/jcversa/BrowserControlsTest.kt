package app.cantas.jcversa

import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import org.junit.Assert.*
import org.junit.Test
import java.io.File

class BrowserControlsTest {
    @Test fun filterSortAndPagingOnAndroidFilesystem() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val root = File(context.cacheDir, "browser-controls-${System.nanoTime()}")
        assertTrue(root.mkdir())
        try {
            File(root,"Nested").mkdir(); File(root,"z-large.txt").writeText("synthetic payload")
            File(root,"a-small.txt").writeText("x"); File(root,".secret.txt").writeText("fixture")
            val repo = LocalFiles(StorageBoundary(root))
            assertEquals(3, repo.list(root.path).entries.size)
            assertEquals(4, repo.list(root.path, BrowseOptions(showHidden = true)).entries.size)
            assertEquals(listOf("a-small.txt"),repo.list(root.path, BrowseOptions(query = "SMALL")).entries.map { it.name })
            assertEquals(listOf("Nested","z-large.txt","a-small.txt"),repo.list(root.path,
                BrowseOptions(sort = FileSort.SIZE_DESC)).entries.map { it.name })
            val first = repo.list(root.path,BrowseOptions(limit = 1)); assertTrue(first.truncated)
            assertEquals(first.entries,repo.list(root.path).entries.take(1))
        } finally { root.deleteRecursively() } // Own uniquely named instrumentation fixture only.
    }
}
