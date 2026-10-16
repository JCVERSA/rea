package app.cantas.jcversa.data

import android.os.ParcelFileDescriptor
import android.system.Os
import android.system.OsConstants
import android.system.ErrnoException
import java.io.File
import java.io.IOException

/** Android API21+ primitives, including API24: no CREATE/TRUNCATE race through FileOutputStream. */
class AndroidCopyStreams : CopyStreams {
    override fun input(file: File): CopyInput {
        val raw = Os.open(file.path, OsConstants.O_RDONLY or OsConstants.O_NOFOLLOW or OsConstants.O_NONBLOCK, 0)
        val parcel = try {
            val info = Os.fstat(raw)
            if (!OsConstants.S_ISREG(info.st_mode)) throw IOException("Not a regular file")
            ParcelFileDescriptor.dup(raw)
        } finally { Os.close(raw) }
        try {
            val before = Os.fstat(parcel.fileDescriptor)
            val input = ParcelFileDescriptor.AutoCloseInputStream(parcel)
            return CopyInput(input, before.st_size) {
                val after = Os.fstat(parcel.fileDescriptor)
                before.st_dev == after.st_dev && before.st_ino == after.st_ino &&
                    before.st_size == after.st_size && before.st_mtime == after.st_mtime
            }
        } catch (error: Exception) { parcel.close(); throw error }
    }
    override fun exclusiveOutput(file: File): CopyOutput {
        val raw = try {
            Os.open(file.path, OsConstants.O_WRONLY or OsConstants.O_CREAT or OsConstants.O_EXCL or OsConstants.O_NOFOLLOW, 384)
        } catch (error: ErrnoException) {
            if (error.errno == OsConstants.EEXIST) throw CopyCollision()
            throw error
        }
        try {
            val parcel = try { ParcelFileDescriptor.dup(raw) } finally { Os.close(raw) }
            val output = ParcelFileDescriptor.AutoCloseOutputStream(parcel)
            return CopyOutput(output) { Os.fsync(parcel.fileDescriptor) }
        } catch (error: Exception) { throw CopyCreatedFailure(error) }
    }
}
