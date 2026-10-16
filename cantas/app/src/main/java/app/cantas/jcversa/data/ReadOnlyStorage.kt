package app.cantas.jcversa.data

import java.util.Locale
import java.util.PriorityQueue
import java.util.concurrent.CancellationException

/** Locations are opaque to callers: local paths and SAF document URIs are not interchangeable.
 * No mutation API or implied write capability in this milestone. Call all IO methods off main.
 */
interface ReadOnlyStorage {
    val root: String
    fun list(path: String, options: BrowseOptions = BrowseOptions(), active: () -> Boolean = { true }): FolderSnapshot
    fun parent(path: String): String?
    fun readTarget(path: String): ReadTarget
    fun label(path: String): String? = null
}
data class ReadTarget(val location: String, val mime: String? = null)

/** Shared filter/order/retention policy, independent of either filesystem or document provider. */
internal class EntrySelection(private val options: BrowseOptions) {
    init { require(options.limit in 1..100000) }
    private val query = options.query.trim().lowercase(Locale.ROOT)
    private val names = compareBy<FileEntry> { it.name.lowercase(Locale.ROOT) }.thenBy { it.name }.thenBy { it.path }
    private val order = compareByDescending<FileEntry> { it.directory }.then(when (options.sort) {
        FileSort.NAME -> names
        FileSort.NAME_DESC -> names.reversed()
        FileSort.SIZE_DESC -> compareByDescending<FileEntry> { it.bytes }.then(names)
    })
    private val entries = PriorityQueue<FileEntry>(minOf(options.limit + 1, 1024), order.reversed())
    fun matches(name: String): Boolean = (options.showHidden || !name.startsWith('.')) &&
        (query.isEmpty() || name.lowercase(Locale.ROOT).contains(query))
    private val retained = HashSet<Pair<String, String>>()
    fun add(entry: FileEntry) {
        if (!retained.add(entry.path to entry.name)) return
        entries.add(entry)
        if (entries.size > options.limit + 1) {
            val removed = entries.poll()
            retained.remove(removed.path to removed.name)
        }
    }
    fun snapshot(path: String, title: String? = null): FolderSnapshot =
        FolderSnapshot(path, entries.toList().sortedWith(order).take(options.limit), entries.size > options.limit, title)
}
internal fun checkListingActive(active: () -> Boolean) {
    if (!active()) throw CancellationException("Listing superseded")
}
