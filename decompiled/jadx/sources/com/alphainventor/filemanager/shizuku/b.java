package com.alphainventor.filemanager.shizuku;

import android.os.ParcelFileDescriptor;
import android.os.Process;
import ax.p006a3.L;
import ax.p006a3.v;
import ax.p086f3.B;
import com.alphainventor.filemanager.file.J;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class b {
    private static final int a;
    private static ThreadPoolExecutor b;
    public static int c;
    public static int d;

    class a implements ThreadFactory {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.b$a$a, reason: collision with other inner class name */
        class RunnableC0547a implements Runnable {
            final /* synthetic */ Runnable q;

            RunnableC0547a(Runnable runnable) {
                this.q = runnable;
            }

            @Override // java.lang.Runnable
            public void run() {
                Process.setThreadPriority(2);
                this.q.run();
            }
        }

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(null, new RunnableC0547a(runnable), "Binder TaskExecutor", 32768L);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.b$b, reason: collision with other inner class name */
    class C0548b implements FilenameFilter {
        final /* synthetic */ boolean q;

        C0548b(boolean z) {
            this.q = z;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str == null) {
                return true;
            }
            boolean zStartsWith = str.startsWith(".");
            if (this.q) {
                return !J.l2(str);
            }
            return !zStartsWith;
        }
    }

    public static class c {
        int a;
        String b;

        c(int i, String str) {
            this.a = i;
            this.b = str;
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        a = iAvailableProcessors;
        b = new ThreadPoolExecutor(iAvailableProcessors + 1, (iAvailableProcessors * 4) + 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), new a());
        c = 100;
        d = 110;
    }

    public static IllegalStateException a(int i, String str) {
        return new IllegalStateException("error=" + i + ";msg=" + str);
    }

    public static boolean b(String str) {
        try {
            return new File(str).createNewFile();
        } catch (IOException unused) {
            return false;
        }
    }

    private static ax.p326u3.a c(File file) {
        ax.p326u3.a aVar = new ax.p326u3.a();
        aVar.q = file.getAbsolutePath();
        aVar.X = file.getName();
        boolean zExists = file.exists();
        aVar.Y = zExists;
        if (!zExists) {
            aVar.n0 = -1L;
            aVar.o0 = -1L;
            return aVar;
        }
        aVar.l0 = file.canRead();
        aVar.m0 = file.canWrite();
        aVar.Z = file.isDirectory();
        try {
            aVar.n0 = file.length();
        } catch (IllegalArgumentException unused) {
            aVar.n0 = -1L;
        }
        aVar.o0 = file.lastModified();
        try {
            aVar.k0 = B.R(file);
        } catch (IOException unused2) {
            aVar.k0 = false;
        }
        return aVar;
    }

    public static boolean d(String str) {
        return new File(str).delete();
    }

    public static c e(IllegalStateException illegalStateException) {
        String message;
        int iIndexOf;
        try {
            if (illegalStateException.getMessage() == null || !illegalStateException.getMessage().startsWith("error=") || (iIndexOf = (message = illegalStateException.getMessage()).indexOf(";")) <= 0) {
                return null;
            }
            String strSubstring = message.substring(0, iIndexOf);
            String strSubstring2 = message.substring(iIndexOf + 1);
            return new c(Integer.parseInt(strSubstring.substring(6)), strSubstring2.substring(4));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    public static ax.p326u3.a f(String str) {
        return h(str);
    }

    private static ax.p326u3.a g(String str) {
        return c(new File(str));
    }

    public static ax.p326u3.a h(String str) {
        try {
            ax.p326u3.a aVarG = g(str);
            L l = v.l(str, new L());
            aVarG.Y = l.e;
            aVarG.n0 = l.a;
            aVarG.Z = l.b;
            aVarG.o0 = l.c;
            return aVarG;
        } catch (IOException e) {
            throw a(c, e.getMessage());
        }
    }

    public static ParcelFileDescriptor i(String str) {
        try {
            return ParcelFileDescriptor.open(new File(str), 268435456);
        } catch (FileNotFoundException e) {
            throw a(d, e.getMessage());
        }
    }

    public static int j(String str, boolean z) {
        String[] list = new File(str).list(new C0548b(z));
        if (list == null) {
            return -2;
        }
        return list.length;
    }

    public static ParcelFileDescriptor k(String str, boolean z) {
        try {
            return ParcelFileDescriptor.open(new File(str), z ? 704643072 : 671088640);
        } catch (IOException e) {
            throw a(c, e.getMessage());
        }
    }

    public static ParcelFileDescriptor l(String str) {
        try {
            return ParcelFileDescriptor.open(new File(str), 805306368);
        } catch (FileNotFoundException e) {
            throw a(d, e.getMessage());
        }
    }

    public static List<ax.p326u3.a> m(String str) {
        File[] fileArrListFiles = new File(str).listFiles();
        if (fileArrListFiles == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : fileArrListFiles) {
            arrayList.add(c(file));
        }
        return arrayList;
    }

    public static boolean n(String str) {
        return new File(str).mkdir();
    }

    public static boolean o(String str, String str2) {
        try {
            return new File(str).renameTo(new File(str2));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean p(String str, long j) {
        if (j < 0) {
            return false;
        }
        try {
            File file = new File(str);
            file.setLastModified(j);
            return file.lastModified() / 1000 == j / 1000;
        } catch (Exception unused) {
        }
    }
}
