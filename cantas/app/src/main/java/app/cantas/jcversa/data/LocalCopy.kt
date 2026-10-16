package app.cantas.jcversa.data

import java.io.File
import java.io.InputStream
import java.io.OutputStream

/** Platform adapter must open new outputs atomically/exclusively, never truncate existing paths. */
interface CopyStreams {
    fun input(file: File): CopyInput
    fun exclusiveOutput(file: File): CopyOutput
}
data class CopyInput(val stream: InputStream, val size: Long, val unchanged: () -> Boolean)
data class CopyOutput(val stream: OutputStream, val sync: () -> Unit)
class CopyCollision : java.io.IOException()
class CopyCreatedFailure(cause: Exception) : java.io.IOException(cause)
data class CopyRequest(val path: String, val name: String)
enum class CopyStatus { COPIED, EXISTS, CANCELLED, FAILED, SOURCE_CHANGED, INVALID, NOT_ATTEMPTED }
data class CopyOutcome(val name: String, val target: String, val status: CopyStatus, val bytes: Long = 0, val created: Boolean = false)
data class CopyProgress(val name: String, val index: Int, val total: Int, val bytes: Long, val size: Long)

/** Bounded-buffer, files-only local copy. No source mutation, deletion, rename or rollback API. */
class LocalCopy(private val boundary: StorageBoundary, private val streams: CopyStreams) {
    companion object { const val MAX_FILES = 100 }
    fun copy(requests: List<CopyRequest>, destination: String, active: () -> Boolean = { true },
             progress: (CopyProgress) -> Unit = {}): List<CopyOutcome> {
        require(requests.size in 1..MAX_FILES)
        val parent = boundary.resolve(destination)
        require(parent.isDirectory)
        val buffer = ByteArray(64 * 1024)
        val outcomes = mutableListOf<CopyOutcome>()
        for ((index, request) in requests.withIndex()) {
            val safeName = LocalFolders.validName(request.name)
            val target = if (safeName) File(parent, request.name).path else ""
            if (!active()) {
                outcomes.add(CopyOutcome(request.name, target, CopyStatus.NOT_ATTEMPTED)); continue
            }
            if (!safeName) {
                outcomes.add(CopyOutcome(request.name, "", CopyStatus.INVALID)); continue
            }
            var created = false
            var bytes = 0L
            var lastProgress = 0L
            var status = CopyStatus.FAILED
            try {
                val source = boundary.resolve(request.path)
                val input = streams.input(source)
                input.stream.use { reader ->
                    checkActive(active)
                    progress(CopyProgress(request.name, index + 1, requests.size, 0, input.size))
                    val output = streams.exclusiveOutput(File(parent, request.name))
                    created = true
                    output.stream.use { writer ->
                        while (true) {
                            checkActive(active)
                            val read = reader.read(buffer)
                            if (read < 0) break
                            checkActive(active)
                            writer.write(buffer, 0, read)
                            bytes += read
                            val now = System.nanoTime()
                            if (now - lastProgress >= 100_000_000L) {
                                progress(CopyProgress(request.name, index + 1, requests.size, bytes, input.size)); lastProgress = now
                            }
                        }
                        checkActive(active)
                        writer.flush()
                        output.sync()
                        status = if (bytes == input.size && input.unchanged()) CopyStatus.COPIED else CopyStatus.SOURCE_CHANGED
                    }
                    progress(CopyProgress(request.name, index + 1, requests.size, bytes, input.size))
                }
            } catch (_: Stopped) { status = CopyStatus.CANCELLED }
            catch (_: CopyCollision) { status = CopyStatus.EXISTS }
            catch (_: CopyCreatedFailure) { created = true; status = CopyStatus.FAILED }
            catch (_: Exception) { status = CopyStatus.FAILED }
            // Never delete a path after an error: another actor could have replaced it.
            // Incomplete/unverified outputs remain for explicit manual inspection.
            outcomes.add(CopyOutcome(request.name, target, status, bytes, created))
        }
        return outcomes
    }
    private class Stopped : RuntimeException()
    private fun checkActive(active: () -> Boolean) { if (!active()) throw Stopped() }
}
