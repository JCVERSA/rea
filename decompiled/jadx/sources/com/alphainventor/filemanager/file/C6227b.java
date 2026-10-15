package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1962f;
import ax.p070e3.C1966j;
import ax.p086f3.C1995a;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p288rd.C2318e;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import j$.nio.channels.DesugarChannels;
import j$.util.DesugarCollections;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6227b extends AbstractC6238m {
    private String h;
    private C6249y i;
    private Uri j;
    private int k;
    private ParcelFileDescriptor l;
    private Closeable m;
    private ax.p288rd.T n;
    private boolean o;
    private C1995a p;
    private int r;
    private boolean s;
    private String t;
    private File u;
    private File v;
    private static final Logger x = Logger.getLogger("FileManager.ArchiveFileHelper");
    private static final Set<String> y = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList("jpg", "jpeg", "png", "gif", "webp", "heic", "heif", "avif", "mp4", "m4v", "mov", "mkv", "webm", "avi", "3gp", "3g2", "flv", "wmv", "mp3", "m4a", "aac", "ogg", "oga", "opus", "flac", "zip", "7z", "rar", "gz", "bz2", "xz", "tgz", "tbz", "tbz2", "txz", "aab", "jar")));
    private static HashMap<Integer, String> z = new HashMap<>();
    private static HashMap<String, Integer> A = new HashMap<>();
    private Object w = new Object();
    private boolean q = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$a */
    class a extends ax.p086f3.H {
        a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // ax.p086f3.H, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (C6227b.this.w) {
                super.close();
            }
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read() throws IOException {
            int i;
            synchronized (C6227b.this.w) {
                i = super.read();
            }
            return i;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            int i;
            synchronized (C6227b.this.w) {
                i = super.read(bArr);
            }
            return i;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            synchronized (C6227b.this.w) {
                i3 = super.read(bArr, i, i2);
            }
            return i3;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public synchronized void reset() throws IOException {
            synchronized (C6227b.this.w) {
                super.reset();
            }
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public long skip(long j) throws IOException {
            int i;
            long j2;
            if (j <= 0) {
                return 0L;
            }
            int iMin = (int) Math.min(2048, j);
            byte[] bArr = new byte[iMin];
            long j3 = j;
            while (j3 > 0 && (i = read(bArr, 0, (int) Math.min(iMin, j3))) >= 0) {
                if (i != 0) {
                    j2 = i;
                } else {
                    if (read() < 0) {
                        break;
                    }
                    j2 = 1;
                }
                j3 -= j2;
            }
            return j - j3;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$b, reason: collision with other inner class name */
    private class C0538b extends ax.p406z3.q<String, Void, Boolean> {
        InterfaceC6228c.a h;
        IOException i;

        C0538b(InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = aVar;
        }

        private void A(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            Charset charsetX = x(parcelFileDescriptor);
            try {
                ax.p086f3.B.d0(parcelFileDescriptor, 0L);
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
                C6227b.this.m = fileInputStream;
                FileChannel fileChannelConvertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(fileInputStream.getChannel());
                C6227b.this.n = ax.p288rd.T.m0(fileChannelConvertMaybeLegacyFileChannelFromLibrary, charsetX.name());
            } catch (ErrnoException e) {
                throw new IOException("reset parcel file descriptor position error", e);
            }
        }

        private void w() {
            C6227b.this.o = false;
            ax.p086f3.F.a(C6227b.this.n);
            C6227b.this.n = null;
            ax.p086f3.F.a(C6227b.this.m);
            C6227b.this.m = null;
            ax.p086f3.F.a(C6227b.this.l);
            C6227b.this.l = null;
        }

        private Charset x(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            ParcelFileDescriptor parcelFileDescriptorDup = null;
            try {
                parcelFileDescriptorDup = ParcelFileDescriptor.dup(parcelFileDescriptor.getFileDescriptor());
                return C2318e.d(parcelFileDescriptorDup.getFileDescriptor());
            } finally {
                ax.p086f3.F.a(parcelFileDescriptorDup);
            }
        }

        @Override // ax.p406z3.q
        protected void r() {
            InterfaceC6228c.a aVar = this.h;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) {
            try {
                if (C6227b.this.k != 0) {
                    if (C6227b.this.l == null) {
                        throw new IOException("parcel file descriptor not created from fd");
                    }
                    A(C6227b.this.l);
                } else {
                    if (C6227b.this.i == null) {
                        return Boolean.FALSE;
                    }
                    if (C6227b.this.i.l0()) {
                        try {
                            C6227b c6227b = C6227b.this;
                            c6227b.l = c6227b.i.f0(true);
                            A(C6227b.this.l);
                        } catch (C1966j e) {
                            throw new IOException("get parcel file descriptor error", e);
                        }
                    } else {
                        Charset charsetA = C2318e.a(C6227b.this.i.G0());
                        C6227b c6227b2 = C6227b.this;
                        c6227b2.n = ax.p288rd.T.c0(c6227b2.i.G0(), charsetA.name());
                    }
                }
                C6227b c6227b3 = C6227b.this;
                c6227b3.u = c6227b3.z0();
                try {
                    C6227b c6227b4 = C6227b.this;
                    c6227b4.s = C6227b.L0(c6227b4.n, null);
                } catch (C1957a unused) {
                }
                C6227b c6227b5 = C6227b.this;
                c6227b5.n0(c6227b5.n);
                C6227b c6227b6 = C6227b.this;
                c6227b6.v = C6227b.C0(c6227b6.p(), C6227b.this.u(), C6227b.this.t());
                if (C6227b.this.v != null) {
                    C6227b.this.v.mkdirs();
                }
                C6227b.this.o = true;
                return Boolean.TRUE;
            } catch (IOException e2) {
                this.i = e2;
                w();
                return Boolean.FALSE;
            } catch (ArrayIndexOutOfBoundsException e3) {
                this.i = new IOException(e3);
                w();
                return Boolean.FALSE;
            } catch (IllegalArgumentException e4) {
                this.i = new IOException(e4);
                w();
                return Boolean.FALSE;
            } catch (RuntimeException e5) {
                this.i = new IOException(e5);
                w();
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            InterfaceC6228c.a aVar = this.h;
            if (aVar != null) {
                aVar.d0(bool.booleanValue(), this.i);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$c */
    static class c extends ax.p406z3.q<Long, Integer, Boolean> {
        Context h;
        ax.p086f3.K i;
        ax.p288rd.S j;
        C6249y k;
        File l;
        File m;
        int n;
        Closeable o;
        ParcelFileDescriptor p;

        public c(Context context, ax.p086f3.K k, ParcelFileDescriptor parcelFileDescriptor, Closeable closeable, C6249y c6249y, ax.p288rd.S s, File file, File file2, int i) {
            super(ax.z3.q.e.NORMAL);
            this.h = context;
            this.i = k;
            this.p = parcelFileDescriptor;
            this.o = closeable;
            this.k = c6249y;
            this.j = s;
            this.l = file;
            this.m = file2;
            this.n = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Long... lArr) {
            ax.p358w3.d.i(this.h, this.i);
            C6227b.m0(this.m);
            try {
                ax.p288rd.S s = this.j;
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            C6227b.q0(this.l);
            Closeable closeable = this.o;
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                }
            }
            ParcelFileDescriptor parcelFileDescriptor = this.p;
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused2) {
                }
            }
            C6249y c6249y = this.k;
            if (c6249y != null && this.n == 2) {
                C6240o c6240oF = C2017x.f(c6249y.G0());
                try {
                    c6240oF.q1(this.k);
                    c6240oF.q1(c6240oF.a1(this.k.U()));
                } catch (C1966j unused3) {
                }
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$d */
    private static class d extends ax.p288rd.I {
        public d(String str) {
            super(str);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$e */
    public enum e {
        SUCCESS,
        WRONG_PASSWORD,
        ERROR
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.b$f */
    private class f extends ax.p406z3.q<String, Void, e> {
        private String h;
        private ax.p019b0.a<e> i;

        f(String str, ax.p019b0.a<e> aVar) {
            super(ax.z3.q.e.HIGHER);
            this.h = str;
            this.i = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public e g(String... strArr) {
            Enumeration<ax.p288rd.I> enumerationK = C6227b.this.n.k();
            while (enumerationK.hasMoreElements()) {
                ax.p288rd.I iNextElement = enumerationK.nextElement();
                if (iNextElement.q().m()) {
                    InputStream inputStreamY0 = null;
                    try {
                        inputStreamY0 = C6227b.y0(C6227b.this.n, iNextElement, this.h);
                        e eVar = e.SUCCESS;
                        ax.p086f3.F.a(inputStreamY0);
                        return eVar;
                    } catch (ax.p048cd.a e) {
                        try {
                            if (e.a() == ax.p048cd.a.EnumC0306a.WRONG_PASSWORD) {
                                e eVar2 = e.WRONG_PASSWORD;
                                ax.p086f3.F.a(inputStreamY0);
                                return eVar2;
                            }
                            ax.p086f3.F.a(inputStreamY0);
                        } catch (Throwable th) {
                            ax.p086f3.F.a(inputStreamY0);
                            throw th;
                        }
                    } catch (C1966j | IOException unused) {
                        ax.p086f3.F.a(inputStreamY0);
                    }
                }
            }
            return e.WRONG_PASSWORD;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(e eVar) {
            this.i.accept(eVar);
        }
    }

    private File A0(AbstractC6239n abstractC6239n, boolean z2) {
        File file = this.u;
        if (z2 && !file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, abstractC6239n.E());
        File parentFile = file2.getParentFile();
        if (z2 && parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        return file2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File C0(Context context, ax.U2.f fVar, int i) {
        return ax.p038c3.a.i(context, fVar, i);
    }

    private C1995a F0(String str) {
        String[] strArrSplit = str.split(File.separator);
        if (strArrSplit == null) {
            return null;
        }
        C1995a c1995aI0 = this.p;
        for (int i = 0; i < strArrSplit.length - 1; i++) {
            String str2 = strArrSplit[i];
            if (str2 != null && str2.length() != 0 && (c1995aI0 = c1995aI0.i0(str2)) == null) {
                return null;
            }
        }
        return c1995aI0;
    }

    private InputStream H0(C1995a c1995a) {
        Bitmap bitmapI = ax.p406z3.B.i(s(c1995a), 512);
        if (bitmapI == null) {
            bitmapI = ax.p374x3.a.b();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
        bitmapI.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    private InputStream I0(ax.p288rd.I i) throws C1966j, IOException {
        return (!this.s || TextUtils.isEmpty(this.t)) ? this.n.p(i) : y0(this.n, i, this.t);
    }

    public static boolean L0(ax.p288rd.S s, InterfaceC2462c interfaceC2462c) throws C1957a {
        Enumeration<ax.p288rd.I> enumerationK = s.k();
        while (enumerationK.hasMoreElements()) {
            if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                throw new C1957a();
            }
            if (enumerationK.nextElement().q().m()) {
                return true;
            }
        }
        return false;
    }

    public static String O0(String str, boolean z2) {
        String str2 = File.separator;
        if (str.startsWith(str2)) {
            str = str.substring(1);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        if (!z2) {
            str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        sb.append(str2);
        return sb.toString();
    }

    private boolean P0(File file, File file2) throws C1966j {
        if (!file.exists()) {
            return false;
        }
        if (file2.exists() && !q0(file2)) {
            throw new C1966j("Could not delete stale edit copy");
        }
        if (file.renameTo(file2)) {
            return true;
        }
        throw new C1966j("Could not move archive edit copy");
    }

    private void Q0(C1995a c1995a) {
        List<C1995a> listJ0;
        File fileT0 = t0(c1995a);
        if (fileT0.exists()) {
            c1995a.p0(fileT0);
        }
        if (!c1995a.isDirectory() || (listJ0 = c1995a.j0()) == null) {
            return;
        }
        Iterator<C1995a> it = listJ0.iterator();
        while (it.hasNext()) {
            Q0(it.next());
        }
    }

    private boolean R0(C6240o c6240o, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, File file) throws C1966j {
        AbstractC6239n abstractC6239nA1 = c6240o.a1(file.getAbsolutePath());
        try {
            if (abstractC6239nA1.o()) {
                c6240o.q1(abstractC6239nA1);
                abstractC6239nA1 = c6240o.a1(file.getAbsolutePath());
            }
            c6240o.U(abstractC6239n, abstractC6239nA1, null, null);
            try {
                c6240o.U(c6240o.a1(abstractC6239n2.E()), c6240o.a1(abstractC6239n.E()), null, null);
                AbstractC6239n abstractC6239nA2 = c6240o.a1(file.getAbsolutePath());
                if (abstractC6239nA2.o()) {
                    c6240o.q1(abstractC6239nA2);
                }
                this.q = false;
                return true;
            } catch (C1957a | C1966j unused) {
                S0(c6240o, abstractC6239n, file);
                return false;
            }
        } catch (C1957a | C1966j unused2) {
        }
    }

    private void S0(C6240o c6240o, AbstractC6239n abstractC6239n, File file) throws C1966j {
        AbstractC6239n abstractC6239nA1 = c6240o.a1(file.getAbsolutePath());
        if (abstractC6239nA1.o() && !c6240o.a1(abstractC6239n.E()).o()) {
            try {
                c6240o.U(abstractC6239nA1, c6240o.a1(abstractC6239n.E()), null, null);
            } catch (C1957a e2) {
                throw new C1966j(e2);
            }
        }
    }

    public static void W0(ax.p288rd.I i, long j) {
        ax.p288rd.D d2 = new ax.p288rd.D();
        d2.s(new ax.p288rd.U(j / 1000));
        i.d(d2);
    }

    public static boolean X0(AbstractC6239n abstractC6239n, long j) {
        if (j == -1) {
            return false;
        }
        return y.contains(abstractC6239n.y());
    }

    private static void Y0(InputStream inputStream, long j) throws IOException {
        while (j > 0) {
            long jSkip = inputStream.skip(j);
            if (jSkip > 0) {
                j -= jSkip;
            } else if (inputStream.read() < 0) {
                return;
            } else {
                j--;
            }
        }
    }

    private void k0(C1995a c1995a, ax.p288rd.I i, ax.p288rd.I i2) throws IOException {
        long crc;
        long jP = c1995a.p();
        if (X0(c1995a, jP)) {
            if (i instanceof d) {
                File fileT0 = t0(c1995a);
                if (!fileT0.exists() || fileT0.isDirectory()) {
                    return;
                } else {
                    crc = ax.p086f3.B.c(fileT0);
                }
            } else {
                crc = i.getCrc();
                if (crc < 0) {
                    return;
                }
            }
            i2.setMethod(0);
            i2.setSize(jP);
            i2.setCompressedSize(jP);
            i2.setCrc(crc);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m0(File file) {
        if (file != null && file.exists()) {
            if (!file.isDirectory()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length != 0) {
                return;
            }
            q0(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n0(ax.p288rd.S s) {
        String str;
        Enumeration<ax.p288rd.I> enumeration;
        String str2;
        Enumeration<ax.p288rd.I> enumeration2;
        this.p = new C1995a(this, p0("/"), null);
        Enumeration<ax.p288rd.I> enumerationK = s.k();
        while (true) {
            str = "//";
            if (!enumerationK.hasMoreElements()) {
                break;
            }
            ax.p288rd.I iNextElement = enumerationK.nextElement();
            if (iNextElement.isDirectory()) {
                String name = iNextElement.getName();
                String[] strArrSplit = name.split(File.separator);
                C1995a c1995a = this.p;
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < strArrSplit.length - 1) {
                    if (TextUtils.isEmpty(strArrSplit[i])) {
                        ax.p302sb.c.h().g().d("ARCHIVE INVALID SEGMENT 1").h("Entry:" + iNextElement.getName() + ",Length:" + iNextElement.getName().length()).i();
                    } else {
                        sb.append(strArrSplit[i]);
                        sb.append(File.separator);
                        C1995a c1995aI0 = c1995a.i0(strArrSplit[i]);
                        if (c1995aI0 == null) {
                            c1995aI0 = new C1995a(this, p0(sb.toString()), c1995a);
                            c1995a.f0(c1995aI0);
                        }
                        c1995a = c1995aI0;
                    }
                    i++;
                    enumerationK = enumerationK;
                }
                enumeration2 = enumerationK;
                if (!name.endsWith("//") && !name.endsWith("///")) {
                    C1995a c1995a2 = new C1995a(this, iNextElement, c1995a);
                    if (!TextUtils.isEmpty(c1995a2.l0())) {
                        c1995a.f0(c1995a2);
                    } else if (!"/".equals(name)) {
                        ax.U2.d.c("archivefileinfo name", new Exception("Invalid entry:" + name));
                    }
                }
            } else {
                enumeration2 = enumerationK;
            }
            enumerationK = enumeration2;
        }
        Enumeration<ax.p288rd.I> enumerationK2 = s.k();
        while (enumerationK2.hasMoreElements()) {
            ax.p288rd.I iNextElement2 = enumerationK2.nextElement();
            if (iNextElement2.isDirectory()) {
                enumeration = enumerationK2;
                str2 = str;
            } else {
                String name2 = iNextElement2.getName();
                String[] strArrSplit2 = name2.split(File.separator);
                C1995a c1995a3 = this.p;
                StringBuilder sb2 = new StringBuilder();
                int i2 = 0;
                while (i2 < strArrSplit2.length - 1) {
                    if (TextUtils.isEmpty(strArrSplit2[i2])) {
                        ax.p302sb.c.h().g().d("ARCHIVE INVALID SEGMENT 2").h("Entry:" + name2 + ",Length:" + name2.length()).i();
                        enumerationK2 = enumerationK2;
                        str = str;
                    } else {
                        sb2.append(strArrSplit2[i2]);
                        sb2.append(File.separator);
                        C1995a c1995aI1 = c1995a3.i0(strArrSplit2[i2]);
                        if (c1995aI1 == null) {
                            String string = sb2.toString();
                            if (string.endsWith(str)) {
                                ax.p302sb.c.h().g().d("ARCHIVE ENTRY ERROR").h("Entry:" + name2 + "," + name2.length() + "," + string).i();
                            }
                            C1995a c1995a4 = new C1995a(this, p0(string), c1995a3);
                            c1995a3.f0(c1995a4);
                            c1995aI1 = c1995a4;
                        } else {
                            enumerationK2 = enumerationK2;
                            str = str;
                        }
                        c1995a3 = c1995aI1;
                    }
                    i2++;
                    enumerationK2 = enumerationK2;
                    str = str;
                }
                enumeration = enumerationK2;
                str2 = str;
                C1995a c1995a5 = new C1995a(this, iNextElement2, c1995a3);
                if (TextUtils.isEmpty(c1995a5.l0())) {
                    ax.U2.d.c("archivefileinfo name", new Exception("Invalid entry:" + name2));
                } else {
                    c1995a3.f0(c1995a5);
                }
            }
            enumerationK2 = enumeration;
            str = str2;
        }
    }

    private int o0(C1995a c1995a) {
        if (!c1995a.isDirectory()) {
            return 1;
        }
        Stack stack = new Stack();
        stack.push(c1995a);
        int i = 0;
        while (!stack.isEmpty()) {
            C1995a c1995a2 = (C1995a) stack.pop();
            if (c1995a2.isDirectory()) {
                for (C1995a c1995a3 : c1995a2.j0()) {
                    if (c1995a3 == null) {
                        ax.U2.d.c("null archive child", new Exception("archive child null"));
                    } else if (c1995a3.isDirectory()) {
                        stack.push(c1995a3);
                    } else {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    private ax.p288rd.I p0(String str) {
        return new d(str);
    }

    public static boolean q0(File file) {
        String[] list;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!q0(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private void r0(C1995a c1995a) {
        File fileT0 = t0(c1995a);
        if (fileT0.exists()) {
            if (fileT0.isDirectory()) {
                q0(fileT0);
            } else {
                fileT0.delete();
            }
        }
    }

    private void t1(C1995a c1995a, ax.p288rd.J j, Integer[] numArr, ax.p182l3.i iVar) throws Throwable {
        if (c1995a == null) {
            throw new C1966j("fileinfo == null");
        }
        ax.p288rd.I iM0 = c1995a.m0();
        if (iM0 == null) {
            throw new C1966j("zipEntry == null");
        }
        boolean z2 = false;
        InputStream inputStreamI0 = null;
        try {
            try {
                ax.p288rd.I iP0 = p0(c1995a.u());
                if (c1995a.q() >= 0) {
                    iP0.setTime(c1995a.q());
                    W0(iP0, c1995a.q());
                }
                if (!c1995a.isDirectory()) {
                    k0(c1995a, iM0, iP0);
                }
                j.m0(iP0);
                try {
                    if (!c1995a.isDirectory()) {
                        if (iM0 instanceof d) {
                            File fileT0 = t0(c1995a);
                            if (!fileT0.exists()) {
                                C2461b.e("Edit copy does not exist");
                                throw new ax.p070e3.t("Archive entry input edit copy does not exist");
                            }
                            inputStreamI0 = new FileInputStream(fileT0);
                        } else {
                            inputStreamI0 = I0(iM0);
                            if (inputStreamI0 == null) {
                                throw new ax.p070e3.t("Archive entry input stream is null");
                            }
                        }
                        ax.p086f3.F.b(inputStreamI0, j);
                        if (iVar != null) {
                            int iIntValue = numArr[0].intValue() + 1;
                            numArr[0] = Integer.valueOf(iIntValue);
                            iVar.a(iIntValue, numArr[1].intValue());
                        }
                    }
                    j.h();
                    if (inputStreamI0 != null) {
                        try {
                            inputStreamI0.close();
                        } catch (IOException unused) {
                        }
                    }
                    if (c1995a.isDirectory()) {
                        Iterator<C1995a> it = c1995a.j0().iterator();
                        while (it.hasNext()) {
                            t1(it.next(), j, numArr, iVar);
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    throw C1960d.a("write entry error", e);
                } catch (ArrayIndexOutOfBoundsException e3) {
                    e = e3;
                    ax.p302sb.c.h().d("AFWE:").h(iM0.getSize() + ":" + iM0.getName()).i();
                    throw new C1966j(e);
                } catch (Throwable th) {
                    th = th;
                    z2 = true;
                    if (0 != 0) {
                        try {
                            inputStreamI0.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (!z2) {
                        throw th;
                    }
                    try {
                        j.h();
                        throw th;
                    } catch (IOException unused3) {
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            e = e4;
        } catch (ArrayIndexOutOfBoundsException e5) {
            e = e5;
        }
    }

    public static synchronized int u0(Uri uri) {
        try {
            if (uri == null) {
                C2461b.f();
                return 0;
            }
            String string = uri.toString();
            Integer num = A.get(string);
            if (num != null) {
                return num.intValue();
            }
            Integer numValueOf = Integer.valueOf(Math.abs(string.hashCode()));
            while (z.containsKey(numValueOf)) {
                numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
            }
            A.put(string, numValueOf);
            z.put(numValueOf, string);
            return numValueOf.intValue();
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized Uri x0(ax.p086f3.K k) {
        try {
            String str = z.get(Integer.valueOf(k.b()));
            if (str != null) {
                return Uri.parse(str);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static InputStream y0(ax.p288rd.T t, ax.p288rd.I i, String str) throws C1966j, IOException {
        ax.p080ed.h hVar = new ax.p080ed.h(t.y0(i), str.toCharArray());
        ax.p096fd.e eVar = new ax.p096fd.e();
        eVar.u(i.getCrc());
        eVar.w(i.isDirectory());
        eVar.s(i.getCompressedSize());
        eVar.G(i.getSize());
        if (hVar.l(eVar, false) != null) {
            return hVar;
        }
        throw new ax.p070e3.F("Could not locate local file header for encrypted entry");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File z0() {
        if (this.h == null) {
            return null;
        }
        return new File(ax.p038c3.a.m(p(), "archive-edit"), this.h.replace(".", "_") + System.currentTimeMillis());
    }

    public int B0() {
        return this.k;
    }

    public C6249y D0() {
        return this.i;
    }

    public String E0() {
        return this.h;
    }

    public File G0(AbstractC6239n abstractC6239n) {
        return A0(abstractC6239n, true);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean I(AbstractC6239n abstractC6239n) {
        return l0();
    }

    public boolean J0() {
        return this.q;
    }

    public boolean K0() {
        return this.s;
    }

    public boolean M0() {
        return (this.r == 0 || this.j == null) ? false : true;
    }

    public boolean N0() {
        return !TextUtils.isEmpty(this.t);
    }

    public void T0(Uri uri, C6249y c6249y, int i) {
        this.j = uri;
        this.r = i;
        this.i = c6249y;
        this.h = c6249y.B();
    }

    public void U0(Uri uri, String str, int i, int i2) {
        this.j = uri;
        this.r = i2;
        this.h = str;
        this.k = i;
        try {
            this.l = ParcelFileDescriptor.fromFd(i);
        } catch (IOException unused) {
        }
    }

    public void V0(String str) {
        this.t = str;
    }

    public void Z0(String str, ax.p019b0.a<e> aVar) {
        new f(str, aVar).i(new String[0]);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.n != null && this.o;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        if (this.p == null) {
            throw new C1966j("no root");
        }
        String[] strArrSplit = str.split(File.separator);
        C1995a c1995aI0 = this.p;
        for (int i = 0; i < strArrSplit.length; i++) {
            String str2 = strArrSplit[i];
            if (str2 != null && !HttpUrl.FRAGMENT_ENCODE_SET.equals(str2)) {
                if (c1995aI0 == null) {
                    ax.p302sb.c.h().d("ARFI").k().i();
                }
                c1995aI0 = c1995aI0.i0(strArrSplit[i]);
                if (c1995aI0 == null) {
                    return new C1995a(this, str);
                }
            }
        }
        return c1995aI0;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public synchronized void b() {
        C2017x.h(u(), t());
        s0();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        try {
            C1995a c1995a = (C1995a) a1(str2);
            EnumC2019z enumC2019zG = c1995a.G();
            if (EnumC2019z.IMAGE == enumC2019zG) {
                return new ax.Ja.a(new BufferedInputStream(h1(c1995a, 0L), 32768), (int) c1995a.p());
            }
            if (EnumC2019z.VIDEO != enumC2019zG) {
                C2461b.f();
                return null;
            }
            if (ax.p006a3.Q.e1()) {
                return H0(c1995a);
            }
            return null;
        } catch (C1966j unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        if (abstractC6239n.isDirectory()) {
            return ((C1995a) abstractC6239n).k0();
        }
        throw new C1966j("file is not directory");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        EnumC2019z enumC2019zG = abstractC6239n.G();
        if (EnumC2019z.IMAGE == enumC2019zG || EnumC2019z.VIDEO == enumC2019zG) {
            return ax.p086f3.B.Y(abstractC6239n);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        if (!l0()) {
            throw new C1966j("zip file is not writeable");
        }
        if (d0.E(abstractC6239n)) {
            throw new C1966j("Could not delete root");
        }
        String[] strArrSplit = abstractC6239n.u().split(File.separator);
        if (strArrSplit != null) {
            C1995a c1995aI0 = this.p;
            C1995a c1995a = c1995aI0;
            for (String str : strArrSplit) {
                if (str.length() != 0) {
                    if (c1995aI0 == null) {
                        throw new ax.p070e3.t("Can not found fileinfo");
                    }
                    c1995a = c1995aI0;
                    c1995aI0 = c1995aI0.i0(str);
                }
            }
            if (c1995aI0 != null) {
                c1995a.n0(c1995aI0);
                r0(c1995aI0);
                this.q = true;
                return;
            }
        }
        throw new ax.p070e3.t("Could not delete...");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        ax.p288rd.I i = null;
        try {
            ax.p288rd.I iM0 = ((C1995a) abstractC6239n).m0();
            if (iM0 == null) {
                throw new ax.p070e3.t("Zip entry is null");
            }
            File fileT0 = t0(abstractC6239n);
            if (fileT0.exists()) {
                FileInputStream fileInputStream = new FileInputStream(fileT0);
                if (j == 0) {
                    return fileInputStream;
                }
                Y0(fileInputStream, j);
                return fileInputStream;
            }
            InputStream inputStreamI0 = I0(iM0);
            if (inputStreamI0 == null) {
                throw new IOException("ZipFile getInputStream == null");
            }
            a aVar = new a(inputStreamI0);
            if (j != 0) {
                Y0(aVar, j);
            }
            return aVar;
        } catch (IOException e2) {
            throw C1960d.a("archive getinputstream", e2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            ax.p302sb.c.h().d("AFGIS").h(i.getSize() + ":" + i.getName()).i();
            throw new C1966j("Zip entry read error");
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        if (!l0()) {
            throw new C1966j("zip file is not writeable");
        }
        C2461b.a(abstractC6239n2.o());
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t();
        }
        long jP = abstractC6239n.p();
        C1995a c1995a = (C1995a) a1(abstractC6239n.E());
        File fileT0 = t0(c1995a);
        C1995a c1995a2 = (C1995a) abstractC6239n2;
        File fileG0 = G0(c1995a2);
        C1995a c1995aF0 = F0(abstractC6239n.E());
        C1995a c1995aF1 = F0(abstractC6239n2.E());
        if (c1995aF1 == null) {
            throw new C1966j("Target parent does not exist");
        }
        if (c1995aF0 == null) {
            throw new C1966j("Source parent does not exist");
        }
        if (abstractC6239n.isDirectory()) {
            c1995a2.o0();
        }
        if (!c1995a.o()) {
            C2461b.g("no zip entry source");
            throw new ax.p070e3.t("Source file entry is null");
        }
        try {
            boolean zP0 = P0(fileT0, fileG0);
            c1995aF0.n0(c1995a);
            C1995a c1995a3 = new C1995a(this, c1995aF1, c1995a.m0(), c1995a.j0(), abstractC6239n2.B());
            c1995a3.r0();
            if (zP0) {
                Q0(c1995a3);
            }
            c1995aF1.f0(c1995a3);
            this.q = true;
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (IllegalArgumentException e2) {
            ax.p302sb.c.h().g().b("ARCHIVE FILE NO NAME").h("dst:" + c1995a2.l0()).m(e2).i();
            throw e2;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public synchronized void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        if (this.k == 0 && this.i == null) {
            ax.p302sb.c.h().g().b("ArchiveFileHelper invalid auth").k().i();
        }
        try {
            new C0538b(aVar).i(new String[0]);
        } catch (Exception unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    public boolean l0() {
        if (this.s) {
            return false;
        }
        int i = this.r;
        if (i == 1) {
            C6249y c6249y = this.i;
            if (c6249y != null) {
                return c6249y.k();
            }
            return false;
        }
        if (i != 2 && i != 3) {
            C2461b.g("type:" + this.r + ",uri:" + this.j);
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:110:0x0150 A[Catch: IOException -> 0x014b, TRY_LEAVE, TryCatch #9 {IOException -> 0x014b, blocks: (B:106:0x0147, B:110:0x0150), top: B:119:0x0147 }] */
    /* JADX WARN: Code duplicated, block: B:119:0x0147 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:136:? A[SYNTHETIC] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        IOException iOException;
        C1966j c1966j;
        C1957a c1957a;
        Throwable th;
        InputStream inputStreamB;
        C2461b.a(abstractC6239n.o());
        if (!l0()) {
            throw new C1966j("zip file is not writeable");
        }
        AbstractC6239n abstractC6239nA1 = a1(abstractC6239n.E());
        if (abstractC6239nA1.o()) {
            throw new C1962f(abstractC6239nA1.isDirectory() != abstractC6239n.isDirectory());
        }
        File file = null;
        fileOutputStream = null;
        FileOutputStream fileOutputStream = null;
        File file2 = null;
        File file3 = null;
        try {
            try {
                try {
                    String strE = abstractC6239n.E();
                    C1995a c1995aF0 = F0(strE);
                    if (c1995aF0 == null) {
                        throw new C1966j("Parent not found");
                    }
                    C1995a c1995a = new C1995a(this, p0(O0(strE, false)), c1995aF0);
                    File fileG0 = G0(c1995a);
                    try {
                        c1995a.p0(fileG0);
                        if (fileG0.exists() && fileG0.isDirectory() && !q0(fileG0)) {
                            throw new IOException("Could not delete stale edit copy");
                        }
                        FileOutputStream fileOutputStream2 = new FileOutputStream(fileG0);
                        try {
                            inputStreamB = g.b();
                            try {
                                ax.p086f3.F.c(inputStreamB, fileOutputStream2, j, interfaceC2462c, iVar);
                                fileOutputStream2.close();
                                try {
                                    try {
                                        c1995aF0.f0(c1995a);
                                        if (l != null && l.longValue() >= 0) {
                                            try {
                                                fileG0.setLastModified(l.longValue());
                                            } catch (Exception e2) {
                                                ax.p302sb.c.h().g().d("set last modified 1").m(e2).i();
                                            }
                                        }
                                        this.q = true;
                                        if (inputStreamB != null) {
                                            try {
                                                inputStreamB.close();
                                            } catch (IOException e3) {
                                                e3.printStackTrace();
                                            }
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                                throw th;
                                            }
                                        }
                                        if (inputStreamB != null) {
                                            throw th;
                                        }
                                        inputStreamB.close();
                                        throw th;
                                    }
                                } catch (C1957a e5) {
                                    c1957a = e5;
                                    file2 = fileG0;
                                    if (file2 == null || !file2.exists()) {
                                        throw c1957a;
                                    }
                                    file2.delete();
                                    throw c1957a;
                                } catch (C1966j e6) {
                                    c1966j = e6;
                                    file3 = fileG0;
                                    if (file3 == null || !file3.exists()) {
                                        throw c1966j;
                                    }
                                    file3.delete();
                                    throw c1966j;
                                } catch (IOException e7) {
                                    iOException = e7;
                                    file = fileG0;
                                    if (file != null && file.exists()) {
                                        file.delete();
                                    }
                                    throw C1960d.a("archive write file", iOException);
                                }
                            } catch (C1957a e8) {
                                c1957a = e8;
                            } catch (C1966j e9) {
                                c1966j = e9;
                            } catch (IOException e10) {
                                iOException = e10;
                            }
                        } catch (C1957a e11) {
                            c1957a = e11;
                        } catch (C1966j e12) {
                            c1966j = e12;
                        } catch (IOException e13) {
                            iOException = e13;
                        } catch (Throwable th3) {
                            th = th3;
                            inputStreamB = null;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            if (inputStreamB != null) {
                                throw th;
                            }
                            inputStreamB.close();
                            throw th;
                        }
                    } catch (C1957a e14) {
                        c1957a = e14;
                    } catch (C1966j e15) {
                        c1966j = e15;
                    } catch (IOException e16) {
                        iOException = e16;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStreamB = null;
            }
        } catch (C1957a e17) {
            c1957a = e17;
        } catch (C1966j e18) {
            c1966j = e18;
        } catch (IOException e19) {
            iOException = e19;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z2, boolean z3, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        if (!l0()) {
            return false;
        }
        C1995a c1995aF0 = F0(abstractC6239n.E());
        String strB = abstractC6239n.B();
        if (c1995aF0 == null || !c1995aF0.o() || c1995aF0.i0(strB) != null) {
            return false;
        }
        C1995a c1995a = new C1995a(this, p0(O0(abstractC6239n.E(), true)), c1995aF0);
        File fileG0 = G0(c1995a);
        if (fileG0.exists() && !fileG0.isDirectory() && !fileG0.delete()) {
            return false;
        }
        if (!fileG0.exists() && !fileG0.mkdirs()) {
            return false;
        }
        c1995a.p0(fileG0);
        c1995aF0.f0(c1995a);
        this.q = true;
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        if (!l0()) {
            return false;
        }
        C1995a c1995aF0 = F0(abstractC6239n.E());
        String strB = abstractC6239n.B();
        if (c1995aF0 != null && c1995aF0.o() && c1995aF0.i0(strB) == null) {
            try {
                C1995a c1995a = new C1995a(this, p0(O0(abstractC6239n.E(), false)), c1995aF0);
                File fileG0 = G0(c1995a);
                if (fileG0.exists() && !fileG0.delete()) {
                    throw new IOException("Could not delete stale edit copy");
                }
                new FileOutputStream(fileG0, false).close();
                c1995a.p0(fileG0);
                c1995aF0.f0(c1995a);
                this.q = true;
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        g1(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }

    public ax.p406z3.q s0() {
        this.o = false;
        return new c(p(), v(), this.l, this.m, this.i, this.n, this.u, this.v, this.r).i(new Long[0]);
    }

    /* JADX WARN: Code duplicated, block: B:52:0x0110  */
    public boolean s1(ax.p182l3.i iVar) throws Throwable {
        File file;
        ax.p288rd.J j;
        int i;
        char c2;
        if (!this.q) {
            return true;
        }
        int i2 = this.r;
        if (i2 == 3 || i2 == 2) {
            C2461b.g("not reachable : " + this.r + "," + J0());
            return false;
        }
        if (this.i == null) {
            C2461b.g("not reachable");
            return false;
        }
        if (!l0()) {
            throw new C1966j("zip file is not writeable");
        }
        ax.p288rd.J j2 = null;
        j2 = null;
        File file2 = null;
        try {
            String strE = this.i.E();
            String strR = d0.r(strE);
            file = new File(strR, d0.h(strE) + ".tmp.zip");
            try {
                File file3 = new File(strR, d0.h(strE) + ".tmp.zip.bak");
                C6240o c6240oF = C2017x.f(file);
                C6248x c6248x = (C6248x) c6240oF.u();
                AbstractC6239n abstractC6239nA1 = c6240oF.a1(strE);
                AbstractC6239n abstractC6239nA2 = c6240oF.a1(file.getAbsolutePath());
                j = new ax.p288rd.J(c6248x.c(file.getAbsolutePath(), false));
                try {
                    j.O0(Charset.defaultCharset().name());
                    List<C1995a> listJ0 = this.p.j0();
                    if (listJ0 != null) {
                        int iO0 = o0(this.p);
                        if (iVar != null) {
                            i = 0;
                            c2 = 1;
                            iVar.a(0L, iO0);
                        } else {
                            i = 0;
                            c2 = 1;
                        }
                        Integer numValueOf = Integer.valueOf(i);
                        Integer numValueOf2 = Integer.valueOf(iO0);
                        Integer[] numArr = new Integer[2];
                        numArr[i] = numValueOf;
                        numArr[c2] = numValueOf2;
                        Iterator<C1995a> it = listJ0.iterator();
                        while (it.hasNext()) {
                            t1(it.next(), j, numArr, iVar);
                        }
                    }
                    j.x();
                    j.close();
                    boolean zR0 = R0(c6240oF, abstractC6239nA1, abstractC6239nA2, file3);
                    ax.p086f3.F.a(null);
                    if (file.exists()) {
                        file.delete();
                    }
                    return zR0;
                } catch (IOException e2) {
                    e = e2;
                    file2 = file;
                    try {
                        throw C1960d.a("update archive error", e);
                    } catch (Throwable th) {
                        th = th;
                        file = file2;
                        j2 = j;
                        ax.p086f3.F.a(j2);
                        if (file != null && file.exists()) {
                            file.delete();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    j2 = j;
                    ax.p086f3.F.a(j2);
                    if (file != null) {
                        file.delete();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e = e3;
                j = null;
            } catch (Throwable th3) {
                th = th3;
                ax.p086f3.F.a(j2);
                if (file != null) {
                    file.delete();
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            j = null;
        } catch (Throwable th4) {
            th = th4;
            file = null;
        }
    }

    public File t0(AbstractC6239n abstractC6239n) {
        return A0(abstractC6239n, false);
    }

    public int v0() {
        return this.r;
    }

    public Uri w0() {
        return this.j;
    }
}
