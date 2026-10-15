package com.alphainventor.filemanager.shizuku;

import android.os.ParcelFileDescriptor;
import android.os.Process;
import ax.E1.K;
import ax.E1.u;
import ax.J1.C0740v;
import com.alphainventor.filemanager.file.E;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    static Set<Long> e;

    class a implements ThreadFactory {

        /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.b$a$a, reason: collision with other inner class name */
        class RunnableC0513a implements Runnable {
            final /* synthetic */ Runnable q;

            RunnableC0513a(Runnable runnable) {
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
            return new Thread(null, new RunnableC0513a(runnable), "Binder TaskExecutor", 32768L);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.shizuku.b$b, reason: collision with other inner class name */
    class C0514b implements FilenameFilter {
        final /* synthetic */ boolean q;

        C0514b(boolean z) {
            this.q = z;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str == null) {
                return true;
            }
            boolean zStartsWith = str.startsWith(".");
            if (this.q) {
                return !E.c2(str);
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

    static class d implements Runnable {
        final OutputStream X;
        final long Y;
        final InputStream q;

        d(InputStream inputStream, OutputStream outputStream, long j) {
            this.q = inputStream;
            this.X = outputStream;
            this.Y = j;
            b.e.add(Long.valueOf(j));
        }

        @Override // java.lang.Runnable
        public void run() {
            OutputStream outputStream;
            byte[] bArr = new byte[8192];
            while (true) {
                try {
                    try {
                        try {
                            int i = this.q.read(bArr);
                            if (i > 0) {
                                this.X.write(bArr, 0, i);
                            } else {
                                try {
                                    break;
                                } catch (IOException unused) {
                                }
                            }
                        } catch (IOException unused2) {
                            outputStream = this.X;
                        }
                    } catch (IOException unused3) {
                    }
                } catch (IOException unused4) {
                    this.q.close();
                    outputStream = this.X;
                    outputStream.close();
                    b.e.remove(Long.valueOf(this.Y));
                } catch (Throwable th) {
                    try {
                        this.q.close();
                    } catch (IOException unused5) {
                    }
                    try {
                        this.X.close();
                    } catch (IOException unused6) {
                    }
                    b.e.remove(Long.valueOf(this.Y));
                    throw th;
                }
                outputStream = this.X;
                outputStream.close();
                b.e.remove(Long.valueOf(this.Y));
            }
            this.q.close();
            outputStream = this.X;
            outputStream.close();
            b.e.remove(Long.valueOf(this.Y));
        }
    }

    static {
        int iAvailableProcessors = Runtime.getRuntime().availableProcessors();
        a = iAvailableProcessors;
        b = new ThreadPoolExecutor(iAvailableProcessors + 1, (iAvailableProcessors * 4) + 1, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue(256), new a());
        c = 100;
        d = 110;
        e = Collections.synchronizedSet(new HashSet());
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

    private static ax.Y1.a c(File file) {
        ax.Y1.a aVar = new ax.Y1.a();
        aVar.q = file.getAbsolutePath();
        aVar.X = file.getName();
        boolean zExists = file.exists();
        aVar.Y = zExists;
        if (zExists) {
            aVar.i0 = file.canRead();
            aVar.j0 = file.canWrite();
            aVar.Z = file.isDirectory();
            try {
                aVar.k0 = file.length();
            } catch (IllegalArgumentException unused) {
                aVar.k0 = -1L;
            }
            aVar.l0 = file.lastModified();
            try {
                aVar.h0 = C0740v.J(file);
            } catch (IOException unused2) {
                aVar.h0 = false;
            }
        } else {
            aVar.k0 = -1L;
            aVar.l0 = -1L;
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

    public static ax.Y1.a f(String str) {
        return h(str);
    }

    private static ax.Y1.a g(String str) {
        return c(new File(str));
    }

    public static ax.Y1.a h(String str) {
        try {
            ax.Y1.a aVarG = g(str);
            K k = u.k(str, new K());
            aVarG.Y = k.d;
            aVarG.k0 = k.a;
            aVarG.Z = k.b;
            aVarG.l0 = k.c;
            return aVarG;
        } catch (IOException e2) {
            throw a(c, e2.getMessage());
        }
    }

    public static ParcelFileDescriptor i(String str, long j, long j2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            if (j != 0) {
                fileInputStream.skip(j);
            }
            return o(fileInputStream, j2);
        } catch (FileNotFoundException e2) {
            throw a(d, e2.getMessage());
        } catch (IOException e3) {
            throw a(c, e3.getMessage());
        }
    }

    public static int j(String str, boolean z) {
        String[] list = new File(str).list(new C0514b(z));
        if (list == null) {
            return -2;
        }
        return list.length;
    }

    public static ParcelFileDescriptor k(String str, boolean z, long j) {
        try {
            return p(new FileOutputStream(str, z), j);
        } catch (IOException e2) {
            throw a(c, e2.getMessage());
        }
    }

    public static boolean l(long j) {
        return !e.contains(Long.valueOf(j));
    }

    public static List<ax.Y1.a> m(String str) {
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

    public static ParcelFileDescriptor o(InputStream inputStream, long j) throws IOException {
        ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArrCreatePipe[0];
        b.execute(new d(inputStream, new ParcelFileDescriptor.AutoCloseOutputStream(parcelFileDescriptorArrCreatePipe[1]), j));
        return parcelFileDescriptor;
    }

    public static ParcelFileDescriptor p(OutputStream outputStream, long j) throws IOException {
        ParcelFileDescriptor[] parcelFileDescriptorArrCreatePipe = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor = parcelFileDescriptorArrCreatePipe[0];
        ParcelFileDescriptor parcelFileDescriptor2 = parcelFileDescriptorArrCreatePipe[1];
        b.execute(new d(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor), outputStream, j));
        return parcelFileDescriptor2;
    }

    public static boolean q(String str, String str2) {
        try {
            return new File(str).renameTo(new File(str2));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean r(String str, long j) {
        if (j < 0) {
            return false;
        }
        try {
            File file = new File(str);
            file.setLastModified(j);
            return file.lastModified() / 1000 == j / 1000;
        } catch (Exception unused) {
            return false;
        }
    }
}
