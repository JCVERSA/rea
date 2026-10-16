package app.cantas.jcversa.data

import android.util.AtomicFile
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/** App-private crash warning/report only. No automatic retry, cleanup or mutation of user files. */
class CopyJournal(file: File) {
    private val file = AtomicFile(file)
    fun start(destination: String, requests: List<CopyRequest>) {
        write(JSONObject().put("destination", destination).put("running", true)
            .put("names", JSONArray(requests.map { it.name })))
    }
    fun finish(destination: String, outcomes: List<CopyOutcome>) {
        val entries = JSONArray()
        outcomes.forEach { entries.put(JSONObject().put("name", it.name).put("target", it.target)
            .put("status", it.status.name).put("bytes", it.bytes).put("created", it.created)) }
        write(JSONObject().put("destination", destination).put("running", false).put("results", entries))
    }
    fun read(): CopySession? {
        val bytes = try { file.readFully() } catch (_: java.io.FileNotFoundException) { return null }
        val json = JSONObject(String(bytes, Charsets.UTF_8))
        val destination = json.getString("destination")
        if (json.getBoolean("running")) {
            val names = json.getJSONArray("names")
            return CopySession(destination, interrupted = true, names = (0 until names.length()).map { names.getString(it) })
        }
        val entries = json.getJSONArray("results")
        return CopySession(destination, results = (0 until entries.length()).map {
            val entry = entries.getJSONObject(it)
            CopyOutcome(entry.getString("name"), entry.getString("target"), CopyStatus.valueOf(entry.getString("status")), entry.getLong("bytes"), entry.getBoolean("created"))
        })
    }
    fun clear() { file.delete() } // Only this app-private journal, never a destination or source.
    private fun write(json: JSONObject) {
        val stream = file.startWrite()
        try { stream.write(json.toString().toByteArray(Charsets.UTF_8)); file.finishWrite(stream) }
        catch (error: Exception) { file.failWrite(stream); throw error }
    }
}
data class CopySession(val destination: String, val busy: Boolean = false, val progress: CopyProgress? = null,
    val results: List<CopyOutcome> = emptyList(), val interrupted: Boolean = false, val names: List<String> = emptyList(),
    val journalError: Boolean = false)
