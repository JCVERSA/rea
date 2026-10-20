package app.cantas.jcversa.data

import org.junit.Assert.*
import org.junit.Test

class FavoritePolicyTest {
    @Test fun localFavoriteDeduplicatesAndUpdatesTitleWithoutChangingIdentity() {
        val favorite = FavoriteFolder("/storage/test/Folder", "Folder")
        val first = FavoritePolicy.add(BrowserPreferences(), favorite)
        val next = FavoritePolicy.add(first, favorite.copy(title = "Renamed shortcut"))
        assertEquals(1, next.favorites.size)
        assertEquals(favorite.location, next.favorites.single().location)
        assertEquals("Renamed shortcut", next.favorites.single().title)
        assertEquals("Folder", first.favorites.single().title)
    }
    @Test fun removeAndRenameChangeMetadataOnlyAndPreservePreferences() {
        val original = BrowserPreferences(listOf(FavoriteFolder("/storage/a", "A")), FileSort.SIZE_DESC, true)
        val renamed = FavoritePolicy.rename(original, original.favorites.single().key, "B")
        assertEquals("/storage/a", renamed.favorites.single().location)
        assertEquals("A", original.favorites.single().title)
        val removed = FavoritePolicy.remove(renamed, renamed.favorites.single().key)
        assertTrue(removed.favorites.isEmpty())
        assertEquals(FileSort.SIZE_DESC, removed.sort)
        assertTrue(removed.showHidden)
    }
    @Test fun invalidTitlesAndMalformedUtf16Refused() {
        for (title in listOf("", " ", " A", "A ", "a\nb", "a\u0000b", "x".repeat(129), "\uD800"))
            assertFalse(title, FavoritePolicy.validTitle(title))
        assertTrue(FavoritePolicy.validTitle("Données 📁"))
        assertThrows(IllegalArgumentException::class.java) { FavoritePolicy.rename(BrowserPreferences(), "missing", "A") }
    }
    @Test fun defaultTitlesDoNotSplitSurrogatePairs() {
        val title = FavoritePolicy.defaultTitle("x".repeat(127) + "📁", "Folder")
        assertEquals("x".repeat(127), title)
        assertTrue(FavoritePolicy.validTitle(title))
        assertEquals("Folder", FavoritePolicy.defaultTitle(" \n ", "Folder"))
    }
    @Test fun favoriteLimitRejectsInsteadOfEvictingAnExistingFolder() {
        val data = BrowserPreferences((0 until FavoritePolicy.MAX_FAVORITES).map { FavoriteFolder("/storage/$it", "Folder $it") })
        assertThrows(IllegalArgumentException::class.java) { FavoritePolicy.add(data, FavoriteFolder("/storage/extra", "Extra")) }
        assertEquals(50, data.favorites.size)
        assertEquals(50, FavoritePolicy.add(data, data.favorites.first().copy(title = "Updated")).favorites.size)
    }
    @Test fun safAncestryPreservesOpaqueIdsAndGrantIdentity() {
        val root = "content://test/tree/root/document/root"
        val child = "content://test/tree/root/document/opaque%3Anot%2Fa%2Fpath"
        val favorite = FavoriteFolder(child, "Nested", "content://test/tree/root", listOf(root, child))
        FavoritePolicy.validate(favorite)
        assertEquals(child, favorite.location)
        assertNotEquals(favorite.key, favorite.copy(tree = "content://test/tree/other").key)
    }
    @Test fun invalidOrCyclicAncestryAndRelativeLocalPathsRejected() {
        val root = "content://test/tree/root/document/root"
        val favorite = FavoriteFolder(root, "Root", "content://test/tree/root", listOf(root))
        for (invalid in listOf(favorite.copy(ancestry = emptyList()), favorite.copy(ancestry = listOf(root, root)),
            favorite.copy(ancestry = listOf("content://other/target")), FavoriteFolder("relative/path", "Folder"),
            FavoriteFolder("/storage/folder", "Folder", ancestry = listOf(root))))
            assertThrows(IllegalArgumentException::class.java) { FavoritePolicy.validate(invalid) }
    }
    @Test fun browseDefaultsNeverPersistQueryOrExpandedDisplayLimit() {
        val data = BrowserPreferences(sort = FileSort.NAME_DESC, showHidden = true)
        val options = data.browseOptions()
        assertEquals("", options.query)
        assertEquals(1000, options.limit)
        assertEquals(FileSort.NAME_DESC, options.sort)
        assertTrue(options.showHidden)
    }
}
