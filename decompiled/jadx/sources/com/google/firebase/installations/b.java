package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import j$.nio.channels.DesugarChannels;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/* JADX INFO: loaded from: classes3.dex */
class b {
    private final FileChannel a;
    private final FileLock b;

    private b(FileChannel fileChannel, FileLock fileLock) {
        this.a = fileChannel;
        this.b = fileLock;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static b a(Context context, String str) {
        FileChannel fileChannelConvertMaybeLegacyFileChannelFromLibrary;
        FileLock fileLockLock;
        try {
            fileChannelConvertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(new RandomAccessFile(new File(context.getFilesDir(), str), "rw").getChannel());
            try {
                fileLockLock = fileChannelConvertMaybeLegacyFileChannelFromLibrary.lock();
                try {
                    return new b(fileChannelConvertMaybeLegacyFileChannelFromLibrary, fileLockLock);
                } catch (IOException e) {
                    e = e;
                    Log.e("CrossProcessLock", "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        try {
                            fileLockLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    if (fileChannelConvertMaybeLegacyFileChannelFromLibrary != null) {
                        try {
                            fileChannelConvertMaybeLegacyFileChannelFromLibrary.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return null;
                } catch (Error e2) {
                    e = e2;
                    Log.e("CrossProcessLock", "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        fileLockLock.release();
                    }
                    if (fileChannelConvertMaybeLegacyFileChannelFromLibrary != null) {
                        fileChannelConvertMaybeLegacyFileChannelFromLibrary.close();
                    }
                    return null;
                } catch (OverlappingFileLockException e3) {
                    e = e3;
                    Log.e("CrossProcessLock", "encountered error while creating and acquiring the lock, ignoring", e);
                    if (fileLockLock != null) {
                        fileLockLock.release();
                    }
                    if (fileChannelConvertMaybeLegacyFileChannelFromLibrary != null) {
                        fileChannelConvertMaybeLegacyFileChannelFromLibrary.close();
                    }
                    return null;
                }
            } catch (IOException | Error | OverlappingFileLockException e4) {
                e = e4;
                fileLockLock = null;
            }
        } catch (IOException | Error | OverlappingFileLockException e5) {
            e = e5;
            fileChannelConvertMaybeLegacyFileChannelFromLibrary = null;
            fileLockLock = null;
        }
    }

    void b() {
        try {
            this.b.release();
            this.a.close();
        } catch (IOException e) {
            Log.e("CrossProcessLock", "encountered error while releasing, ignoring", e);
        }
    }
}
