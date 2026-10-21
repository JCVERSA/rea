package app.cantas.jcversa.data

import java.util.Locale

data class SafTreeNode(val entry: ImportEntry, val relative: String)
data class SafTreePlan(val nodes: List<SafTreeNode>)

/** Pure, bounded read-only plan. Paths are opaque identities, never split to find source parents. */
class SafTreePlanner(private val source: ImportSource) {
    fun prepare(requests: List<CopyRequest>, active: () -> Boolean): SafTreePlan {
        require(requests.size in 1..LocalCopy.MAX_FILES)
        val nodes = mutableListOf<SafTreeNode>()
        val seen = mutableSetOf<String>()
        var units = 0L
        fun running() { if (!active()) throw CopyPreparationStopped() }
        fun walk(entry: ImportEntry, relative: String, depth: Int) {
            running()
            require(depth <= LocalTreeCopy.MAX_DEPTH && nodes.size < LocalTreeCopy.MAX_ENTRIES)
            require(entry.path.length <= 16384 && LocalFolders.validName(entry.name) && seen.add(entry.path))
            units += entry.path.length.toLong() + relative.length
            require(units <= 4 * 1024 * 1024)
            nodes.add(SafTreeNode(entry, relative))
            if (entry.directory) {
                val children = source.children(entry.path, LocalTreeCopy.MAX_ENTRIES - nodes.size)
                require(children.size <= LocalTreeCopy.MAX_ENTRIES - nodes.size)
                val names = mutableSetOf<String>()
                children.forEach {
                    require(names.add(it.name.lowercase(Locale.ROOT)))
                    walk(it, "$relative/${it.name}", depth + 1)
                }
            }
        }
        val roots = mutableSetOf<String>()
        requests.forEach {
            running()
            require(LocalFolders.validName(it.name) && roots.add(it.name.lowercase(Locale.ROOT)))
            val entry = source.describe(it.path)
            require(entry.name == it.name)
            walk(entry, it.name, 0)
        }
        running()
        return SafTreePlan(nodes.toList()).also { validate(it, active) }
    }
    /** Revalidate all sources and structure before any destination mutation, including forged plans. */
    fun validate(plan: SafTreePlan, active: () -> Boolean) {
        require(plan.nodes.size in 1..LocalTreeCopy.MAX_ENTRIES)
        val known = mutableMapOf<String, Boolean>()
        val identities = mutableSetOf<String>()
        val siblings = mutableMapOf<String, MutableSet<String>>()
        var roots = 0
        var units = 0L
        plan.nodes.forEach { node ->
            if (!active()) throw CopyPreparationStopped()
            val parts = node.relative.split('/')
            require(parts.size <= LocalTreeCopy.MAX_DEPTH + 1 && parts.all { LocalFolders.validName(it) })
            require(parts.last() == node.entry.name && node.entry.path.length <= 16384 && identities.add(node.entry.path))
            units += node.entry.path.length.toLong() + node.relative.length
            require(units <= 4 * 1024 * 1024 && node.relative !in known)
            val parent = node.relative.substringBeforeLast('/', "")
            if (parent.isEmpty()) require(++roots <= LocalCopy.MAX_FILES) else require(known[parent] == true)
            require(siblings.getOrPut(parent) { mutableSetOf() }.add(node.entry.name.lowercase(Locale.ROOT)))
            require(source.describe(node.entry.path) == node.entry)
            known[node.relative] = node.entry.directory
        }
    }
}
