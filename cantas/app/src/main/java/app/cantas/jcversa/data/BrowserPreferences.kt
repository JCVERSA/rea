package app.cantas.jcversa.data

/** Metadata only. Neither removal nor rename of a favorite mutates a user file or URI grant. */
data class FavoriteFolder(val location: String, val title: String, val tree: String? = null,
    val ancestry: List<String> = emptyList()) {
    val key: String get() = tree.orEmpty() + '\u0000' + location
}
data class BrowserPreferences(val favorites: List<FavoriteFolder> = emptyList(),
    val sort: FileSort = FileSort.NAME, val showHidden: Boolean = false) {
    fun browseOptions() = BrowseOptions(sort = sort, showHidden = showHidden)
}
object FavoritePolicy {
    const val MAX_FAVORITES = 50
    const val MAX_TITLE = 128
    const val MAX_PATH = 4096
    const val MAX_ANCESTRY = 33
    fun validTitle(value: String) = value.isNotBlank() && value == value.trim() && value.length <= MAX_TITLE &&
        value.none { Character.isISOControl(it) } && Charsets.UTF_8.newEncoder().canEncode(value)
    fun defaultTitle(value: String, fallback: String): String {
        var title = value.filterNot { Character.isISOControl(it) }.trim().take(MAX_TITLE)
        if (title.lastOrNull()?.isHighSurrogate() == true) title = title.dropLast(1)
        return if (validTitle(title)) title else fallback.also { require(validTitle(it)) }
    }
    private fun validPath(value: String) = value.isNotEmpty() && value.length <= MAX_PATH &&
        value.none { Character.isISOControl(it) } && Charsets.UTF_8.newEncoder().canEncode(value)
    fun validate(favorite: FavoriteFolder) {
        require(validTitle(favorite.title) && validPath(favorite.location))
        if (favorite.tree == null) require(favorite.location.startsWith('/') && favorite.ancestry.isEmpty())
        else {
            require(validPath(favorite.tree) && favorite.tree.startsWith("content://") && favorite.location.startsWith("content://"))
            require(favorite.ancestry.size in 1..MAX_ANCESTRY && favorite.ancestry.last() == favorite.location)
            require(favorite.ancestry.all { validPath(it) && it.startsWith("content://") })
            require(favorite.ancestry.distinct().size == favorite.ancestry.size)
        }
    }
    fun validate(preferences: BrowserPreferences) {
        require(preferences.favorites.size <= MAX_FAVORITES)
        preferences.favorites.forEach(::validate)
        require(preferences.favorites.map { it.key }.distinct().size == preferences.favorites.size)
    }
    fun add(data: BrowserPreferences, favorite: FavoriteFolder): BrowserPreferences {
        validate(favorite)
        val old = data.favorites.indexOfFirst { it.key == favorite.key }
        val result = if (old < 0) data.copy(favorites = data.favorites + favorite)
            else data.copy(favorites = data.favorites.mapIndexed { index, entry -> if (index == old) favorite else entry })
        validate(result)
        return result
    }
    fun remove(data: BrowserPreferences, key: String) = data.copy(favorites = data.favorites.filterNot { it.key == key })
    fun rename(data: BrowserPreferences, key: String, title: String): BrowserPreferences {
        require(validTitle(title))
        require(data.favorites.any { it.key == key })
        return data.copy(favorites = data.favorites.map { if (it.key == key) it.copy(title = title) else it })
    }
}
