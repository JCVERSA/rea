package app.cantas.jcversa.data

import java.io.File

/** Separate from ReadOnlyStorage: only mkdir, never mkdirs, overwrite, rename or deletion. */
class LocalFolders(private val boundary: StorageBoundary) {
    fun create(parentPath: String, name: String): FolderCreation {
        if (!validName(name)) return FolderCreation.Failed(FolderError.INVALID_NAME)
        return try {
            val parent = boundary.resolve(parentPath)
            if (!parent.isDirectory) return FolderCreation.Failed(FolderError.UNAVAILABLE)
            val target = File(parent, name)
            if (target.exists()) return FolderCreation.Failed(FolderError.EXISTS)
            if (!parent.canWrite()) return FolderCreation.Failed(FolderError.ACCESS_DENIED)
            // mkdir is the single creation attempt. Existing files/directories/symlinks are never replaced.
            if (target.mkdir()) FolderCreation.Created(target.path)
            else FolderCreation.Failed(if (target.exists()) FolderError.EXISTS else FolderError.FAILED)
        } catch (_: SecurityException) { FolderCreation.Failed(FolderError.ACCESS_DENIED) }
        catch (_: IllegalArgumentException) { FolderCreation.Failed(FolderError.UNAVAILABLE) }
        catch (_: java.io.IOException) { FolderCreation.Failed(FolderError.UNAVAILABLE) }
    }
    companion object {
        fun validName(name: String): Boolean = name.isNotBlank() && name == name.trim() &&
            name != "." && name != ".." && name.none { it == '/' || it == '\\' || Character.isISOControl(it) } &&
            Charsets.UTF_8.newEncoder().canEncode(name) && name.toByteArray(Charsets.UTF_8).size <= 255
    }
}
enum class FolderError { INVALID_NAME, EXISTS, ACCESS_DENIED, UNAVAILABLE, UNSUPPORTED, FAILED }
sealed interface FolderCreation {
    data class Created(val path: String) : FolderCreation
    data class Failed(val error: FolderError) : FolderCreation
}
/** A path alone does not distinguish local symlink aliases with different displayed names. */
data class EntryKey(val path: String, val name: String)
fun FileEntry.key() = EntryKey(path, name)
