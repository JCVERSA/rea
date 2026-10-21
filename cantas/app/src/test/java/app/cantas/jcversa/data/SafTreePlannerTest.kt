package app.cantas.jcversa.data

import org.junit.Test
import org.junit.Assert.*

class SafTreePlannerTest {
    private class Source : ImportSource {
        val entries = linkedMapOf<String, ImportEntry>()
        val children = mutableMapOf<String, List<String>>()
        var opens = 0
        fun add(id: String, name: String, directory: Boolean = false): ImportEntry = ImportEntry(id, name, directory, if (directory) null else 7L).also { entries[id] = it }
        override fun describe(path: String) = entries.getValue(path)
        override fun children(path: String, limit: Int) = children[path].orEmpty().map { describe(it) }
        override fun input(path: String): CopyInput { opens++; error("Planning cannot open streams") }
    }
    private fun fixture(): Source = Source().apply {
        add("opaque/root", "Folder", true); add("unrelated!", ".hidden"); add("other/empty", "Empty", true)
        children["opaque/root"] = listOf("unrelated!", "other/empty")
    }
    @Test fun opaqueHiddenAndEmptyPlanNeverOpensStreams() {
        val source = fixture()
        val plan = SafTreePlanner(source).prepare(listOf(CopyRequest("opaque/root", "Folder"))) { true }
        assertEquals(listOf("Folder", "Folder/.hidden", "Folder/Empty"), plan.nodes.map { it.relative })
        assertEquals(0, source.opens)
    }
    @Test fun rejectsCyclesAndCaseCollidingSiblings() {
        val source = fixture()
        source.children["opaque/root"] = listOf("opaque/root")
        assertThrows(Exception::class.java) { SafTreePlanner(source).prepare(listOf(CopyRequest("opaque/root", "Folder"))) { true } }
        source.add("second", ".HIDDEN")
        source.children["opaque/root"] = listOf("unrelated!", "second")
        assertThrows(Exception::class.java) { SafTreePlanner(source).prepare(listOf(CopyRequest("opaque/root", "Folder"))) { true } }
    }
    @Test fun enforcesDepthAndEntryLimitsAtExactBoundary() {
        val source = Source()
        for (i in 0..33) { source.add("id$i", "D$i", true); source.children["id$i"] = if (i < 32) listOf("id${i + 1}") else emptyList() }
        assertEquals(33, SafTreePlanner(source).prepare(listOf(CopyRequest("id0", "D0"))) { true }.nodes.size)
        source.children["id32"] = listOf("id33")
        assertThrows(Exception::class.java) { SafTreePlanner(source).prepare(listOf(CopyRequest("id0", "D0"))) { true } }
        val wide = Source().apply { add("root", "Root", true); for (i in 0..999) add("f$i", "f$i") }
        wide.children["root"] = (0..998).map { "f$it" }
        assertEquals(1000, SafTreePlanner(wide).prepare(listOf(CopyRequest("root", "Root"))) { true }.nodes.size)
        wide.children["root"] = (0..999).map { "f$it" }
        assertThrows(Exception::class.java) { SafTreePlanner(wide).prepare(listOf(CopyRequest("root", "Root"))) { true } }
    }
    @Test fun rejectsForgedTraversalMissingParentAndDuplicateIdentities() {
        val source = fixture()
        val planner = SafTreePlanner(source)
        val hidden = source.describe("unrelated!")
        for (nodes in listOf(listOf(SafTreeNode(hidden, "../.hidden")), listOf(SafTreeNode(hidden, "Missing/.hidden")),
            listOf(SafTreeNode(hidden, ".hidden"), SafTreeNode(hidden, ".hidden")))) {
            assertThrows(Exception::class.java) { planner.validate(SafTreePlan(nodes)) { true } }
        }
    }
    @Test fun changedSourceAndMalformedNameAreRefusedBeforeStreams() {
        val source = fixture()
        val planner = SafTreePlanner(source)
        val plan = planner.prepare(listOf(CopyRequest("opaque/root", "Folder"))) { true }
        source.entries["unrelated!"] = source.describe("unrelated!").copy(size = 8L)
        assertThrows(Exception::class.java) { planner.validate(plan) { true } }
        source.add("bad", "../escape")
        assertThrows(Exception::class.java) { planner.prepare(listOf(CopyRequest("bad", "../escape"))) { true } }
        assertEquals(0, source.opens)
    }
    @Test fun cancellationAndTooManyRootsAreRefused() {
        val source = fixture()
        assertThrows(Exception::class.java) { SafTreePlanner(source).prepare(listOf(CopyRequest("opaque/root", "Folder"))) { false } }
        assertThrows(Exception::class.java) { SafTreePlanner(source).prepare(List(101) { CopyRequest("opaque/root", "Folder") }) { true } }
        assertEquals(0, source.opens)
    }
}
