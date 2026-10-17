package app.cantas.jcversa

import android.os.CancellationSignal
import android.os.OperationCanceledException
import android.provider.DocumentsContract
import androidx.test.platform.app.InstrumentationRegistry
import app.cantas.jcversa.data.*
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class SafImportAndroidTest {
    private fun fixture(test: (File) -> Unit) {
        val root = File(InstrumentationRegistry.getInstrumentation().targetContext.cacheDir, "import-${System.nanoTime()}").canonicalFile
        assertTrue(root.mkdir())
        try { test(root) } finally { root.deleteRecursively() }
    }
    @Test fun revokedReadGrantPreventsPreparationAndWrites() = fixture { dest ->
        val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
        val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "import-root")
        val path = DocumentsContract.buildDocumentUriUsingTree(tree, "import-folder").toString()
        resolver.call(tree, "grant-read", "import-root", null)
        val requests = listOf(CopyRequest(path, "ImportFolder"))
        val source = SafImportSource(resolver, tree, requests, CancellationSignal()) { true }
        resolver.call(tree, "revoke-read", "import-root", null)
        val engine = ImportCopy(StorageBoundary(dest), AndroidCopyStreams(), source)
        assertThrows(SecurityException::class.java) { engine.prepare(requests, dest.path) { true } }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun forgedDifferentTreeIsRejectedWithoutProviderAccess() = fixture { dest ->
        val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
        val other = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "other-tree")
        val request = CopyRequest(DocumentsContract.buildDocumentUriUsingTree(other, "text").toString(), "Résumé.txt")
        assertThrows(IllegalArgumentException::class.java) { SafImportSource(resolver, TreeFixtureProvider.TREE, listOf(request), CancellationSignal()) { true } }
        assertTrue(dest.listFiles()!!.isEmpty())
    }
    @Test fun importCancellationReachesBlockingProviderBeforeAnyWrites() = fixture { dest ->
        runBlocking {
            val resolver = InstrumentationRegistry.getInstrumentation().targetContext.contentResolver
            val tree = DocumentsContract.buildTreeDocumentUri(TreeFixtureProvider.AUTHORITY, "slow-root")
            val request = CopyRequest(DocumentsContract.buildDocumentUriUsingTree(tree, "slow-root").toString(), "Synthetic tree")
            val signal = CancellationSignal()
            val executor = Executors.newSingleThreadExecutor()
            resolver.call(tree, "grant-read", "slow-root", null)
            try {
                resolver.call(tree, "slow-reset", null, null)
                val job = executor.submit<TreeCopyPlan> {
                    ImportCopy(StorageBoundary(dest), AndroidCopyStreams(), SafImportSource(resolver, tree, listOf(request), signal) { true })
                        .prepare(listOf(request), dest.path) { true }
                }
                withTimeout(10000) { while (resolver.call(tree, "slow-state", null, null)?.getBoolean("started") != true) delay(20) }
                signal.cancel()
                val failure = assertThrows(ExecutionException::class.java) { job.get(5, TimeUnit.SECONDS) }
                assertTrue(failure.cause is OperationCanceledException)
                assertTrue(dest.listFiles()!!.isEmpty())
                withTimeout(5000) { while (resolver.call(tree, "slow-state", null, null)?.getBoolean("cancelled") != true) delay(20) }
            } finally {
                signal.cancel(); executor.shutdownNow()
                resolver.call(tree, "revoke-read", "slow-root", null)
            }
        }
    }
}
