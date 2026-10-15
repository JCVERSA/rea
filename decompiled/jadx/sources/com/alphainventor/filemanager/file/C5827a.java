package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0714e;
import ax.I1.C0718i;
import ax.J1.C0721b;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.p097gc.C5415e;
import ax.p097gc.U;
import j$.nio.channels.DesugarChannels;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5827a extends AbstractC5837k {
    private static final Logger x = Logger.getLogger("FileManager.ArchiveFileHelper");
    private static HashMap<Integer, String> y = new HashMap<>();
    private static HashMap<String, Integer> z = new HashMap<>();
    private String h;
    private u i;
    private Uri j;
    private int k;
    private ParcelFileDescriptor l;
    private Closeable m;
    private ax.p097gc.T n;
    private boolean o;
    private C0721b p;
    private int r;
    private boolean s;
    private String t;
    private File u;
    private File v;
    private Object w = new Object();
    private boolean q = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.a$b */
    private class b extends ax.p045d2.n<String, Void, Boolean> {
        InterfaceC5828b.a h;
        IOException i;

        b(InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = aVar;
        }

        private void y(ParcelFileDescriptor parcelFileDescriptor) throws IOException {
            Charset charsetD = C5415e.d(parcelFileDescriptor.getFileDescriptor());
            FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptor.getFileDescriptor());
            C5827a.this.m = fileInputStream;
            FileChannel fileChannelConvertMaybeLegacyFileChannelFromLibrary = DesugarChannels.convertMaybeLegacyFileChannelFromLibrary(fileInputStream.getChannel());
            C5827a.this.n = ax.p097gc.T.e0(fileChannelConvertMaybeLegacyFileChannelFromLibrary, charsetD.name());
        }

        @Override // ax.p045d2.n
        protected void r() {
            InterfaceC5828b.a aVar = this.h;
            if (aVar != null) {
                aVar.N();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) {
            try {
                if (C5827a.this.k != 0) {
                    if (C5827a.this.l == null) {
                        throw new IOException("parcel file descriptor not created from fd");
                    }
                    y(C5827a.this.l);
                } else {
                    if (C5827a.this.i == null) {
                        return Boolean.FALSE;
                    }
                    if (C5827a.this.i.g0()) {
                        try {
                            C5827a c5827a = C5827a.this;
                            c5827a.l = c5827a.i.c0();
                            y(C5827a.this.l);
                        } catch (C0718i e) {
                            throw new IOException("get parcel file descriptor error", e);
                        }
                    } else {
                        Charset charsetA = C5415e.a(C5827a.this.i.y0());
                        C5827a c5827a2 = C5827a.this;
                        c5827a2.n = ax.p097gc.T.Y(c5827a2.i.y0(), charsetA.name());
                    }
                }
                C5827a c5827a3 = C5827a.this;
                c5827a3.u = c5827a3.n0();
                try {
                    C5827a c5827a4 = C5827a.this;
                    c5827a4.s = C5827a.x0(c5827a4.n, null);
                } catch (C0710a unused) {
                }
                C5827a c5827a5 = C5827a.this;
                c5827a5.f0(c5827a5.n);
                C5827a c5827a6 = C5827a.this;
                c5827a6.v = C5827a.q0(c5827a6.n(), C5827a.this.r(), C5827a.this.q());
                if (C5827a.this.v != null) {
                    C5827a.this.v.mkdirs();
                }
                C5827a.this.o = true;
                return Boolean.TRUE;
            } catch (IOException e2) {
                this.i = e2;
                return Boolean.FALSE;
            } catch (ArrayIndexOutOfBoundsException e3) {
                this.i = new IOException(e3);
                return Boolean.FALSE;
            } catch (IllegalArgumentException e4) {
                this.i = new IOException(e4);
                return Boolean.FALSE;
            } catch (RuntimeException e5) {
                this.i = new IOException(e5);
                return Boolean.FALSE;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            InterfaceC5828b.a aVar = this.h;
            if (aVar != null) {
                aVar.f0(bool.booleanValue(), this.i);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.a$c */
    static class c extends ax.p045d2.n<Long, Integer, Boolean> {
        Context h;
        ax.J1.F i;
        ax.p097gc.S j;
        u k;
        File l;
        File m;
        int n;
        Closeable o;
        ParcelFileDescriptor p;

        public c(Context context, ax.J1.F f, ParcelFileDescriptor parcelFileDescriptor, Closeable closeable, u uVar, ax.p097gc.S s, File file, File file2, int i) {
            super(ax.d2.n.f.NORMAL);
            this.h = context;
            this.i = f;
            this.p = parcelFileDescriptor;
            this.o = closeable;
            this.k = uVar;
            this.j = s;
            this.l = file;
            this.m = file2;
            this.n = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Long... lArr) {
            ax.p003a2.c.i(this.h, this.i);
            C5827a.e0(this.m);
            try {
                ax.p097gc.S s = this.j;
                if (s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            C5827a.h0(this.l);
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
            u uVar = this.k;
            if (uVar != null && this.n == 2) {
                C5839m c5839mF = ax.J1.r.f(uVar.y0());
                try {
                    c5839mF.l1(this.k);
                    c5839mF.l1(c5839mF.V0(this.k.S()));
                } catch (C0718i unused3) {
                }
            }
            return Boolean.TRUE;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.a$d */
    public enum d {
        SUCCESS,
        WRONG_PASSWORD,
        ERROR
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.a$e */
    private class e extends ax.p045d2.n<String, Void, d> {
        private String h;
        private ax.p001a0.a<d> i;

        e(String str, ax.p001a0.a<d> aVar) {
            super(ax.d2.n.f.HIGHER);
            this.h = str;
            this.i = aVar;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public d g(String... strArr) {
            Enumeration<ax.p097gc.I> enumerationI = C5827a.this.n.i();
            while (enumerationI.hasMoreElements()) {
                ax.p097gc.I iNextElement = enumerationI.nextElement();
                if (iNextElement.p().m()) {
                    try {
                        C5827a.m0(C5827a.this.n, iNextElement, this.h);
                        return d.SUCCESS;
                    } catch (C0718i | IOException unused) {
                    } catch (ax.Rb.a e) {
                        if (e.a() == ax.Rb.a.EnumC0227a.WRONG_PASSWORD) {
                            return d.WRONG_PASSWORD;
                        }
                    }
                }
            }
            return d.WRONG_PASSWORD;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(d dVar) {
            this.i.a(dVar);
        }
    }

    public static void D0(ax.p097gc.I i, long j) {
        ax.p097gc.D d2 = new ax.p097gc.D();
        d2.s(new U(j / 1000));
        i.e(d2);
    }

    private void G0(C0721b c0721b, ax.p097gc.J j, Integer[] numArr, ax.P1.i iVar) throws C0718i {
        if (c0721b == null) {
            throw new C0718i("fileinfo == null");
        }
        ax.p097gc.I iJ0 = c0721b.j0();
        if (iJ0 == null) {
            throw new C0718i("zipEntry == null");
        }
        InputStream inputStreamU0 = null;
        try {
            try {
                ax.p097gc.I i = new ax.p097gc.I(c0721b.t());
                if (c0721b.q() >= 0) {
                    i.setTime(c0721b.q());
                    D0(i, c0721b.q());
                }
                j.Y(i);
                if (!c0721b.isDirectory()) {
                    inputStreamU0 = u0(iJ0);
                    if (inputStreamU0 == null) {
                        inputStreamU0 = new FileInputStream(o0(c0721b));
                    }
                    C0744z.d(inputStreamU0, j);
                    if (iVar != null) {
                        int iIntValue = numArr[0].intValue() + 1;
                        numArr[0] = Integer.valueOf(iIntValue);
                        iVar.a(iIntValue, numArr[1].intValue());
                    }
                }
                j.g();
                if (inputStreamU0 != null) {
                    try {
                        inputStreamU0.close();
                    } catch (IOException unused) {
                    }
                }
                if (c0721b.isDirectory()) {
                    Iterator<C0721b> it = c0721b.g0().iterator();
                    while (it.hasNext()) {
                        G0(it.next(), j, numArr, iVar);
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
                throw C0712c.a("write entry error", e2);
            } catch (ArrayIndexOutOfBoundsException e3) {
                ax.p221pa.c.h().d("AFWE:").h(iJ0.getSize() + ":" + iJ0.getName()).i();
                throw new C0718i(e3);
            }
        } catch (Throwable th) {
            if (inputStreamU0 != null) {
                try {
                    inputStreamU0.close();
                } catch (IOException unused2) {
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e0(File file) {
        if (file != null && file.exists()) {
            if (!file.isDirectory()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length != 0) {
                return;
            }
            h0(file);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(ax.p097gc.S s) {
        String str;
        Enumeration<ax.p097gc.I> enumeration;
        String str2;
        Enumeration<ax.p097gc.I> enumeration2;
        this.p = new C0721b(this, new ax.p097gc.I("/"), null);
        Enumeration<ax.p097gc.I> enumerationI = s.i();
        while (true) {
            str = "//";
            if (!enumerationI.hasMoreElements()) {
                break;
            }
            ax.p097gc.I iNextElement = enumerationI.nextElement();
            if (iNextElement.isDirectory()) {
                String name = iNextElement.getName();
                String[] strArrSplit = name.split(File.separator);
                C0721b c0721b = this.p;
                StringBuilder sb = new StringBuilder();
                int i = 0;
                while (i < strArrSplit.length - 1) {
                    if (TextUtils.isEmpty(strArrSplit[i])) {
                        ax.p221pa.c.h().g().d("ARCHIVE INVALID SEGMENT 1").h("Entry:" + iNextElement.getName() + ",Length:" + iNextElement.getName().length()).i();
                    } else {
                        sb.append(strArrSplit[i]);
                        sb.append(File.separator);
                        C0721b c0721bF0 = c0721b.f0(strArrSplit[i]);
                        if (c0721bF0 == null) {
                            c0721bF0 = new C0721b(this, new ax.p097gc.I(sb.toString()), c0721b);
                            c0721b.c0(c0721bF0);
                        }
                        c0721b = c0721bF0;
                    }
                    i++;
                    enumerationI = enumerationI;
                }
                enumeration2 = enumerationI;
                if (!name.endsWith("//") && !name.endsWith("///")) {
                    C0721b c0721b2 = new C0721b(this, iNextElement, c0721b);
                    if (!TextUtils.isEmpty(c0721b2.i0())) {
                        c0721b.c0(c0721b2);
                    } else if (!"/".equals(name)) {
                        ax.p338y1.d.c("archivefileinfo name", new Exception("Invalid entry:" + name));
                    }
                }
            } else {
                enumeration2 = enumerationI;
            }
            enumerationI = enumeration2;
        }
        Enumeration<ax.p097gc.I> enumerationI2 = s.i();
        while (enumerationI2.hasMoreElements()) {
            ax.p097gc.I iNextElement2 = enumerationI2.nextElement();
            if (iNextElement2.isDirectory()) {
                enumeration = enumerationI2;
                str2 = str;
            } else {
                String name2 = iNextElement2.getName();
                String[] strArrSplit2 = name2.split(File.separator);
                C0721b c0721b3 = this.p;
                StringBuilder sb2 = new StringBuilder();
                int i2 = 0;
                while (i2 < strArrSplit2.length - 1) {
                    if (TextUtils.isEmpty(strArrSplit2[i2])) {
                        ax.p221pa.c.h().g().d("ARCHIVE INVALID SEGMENT 2").h("Entry:" + name2 + ",Length:" + name2.length()).i();
                        enumerationI2 = enumerationI2;
                        str = str;
                    } else {
                        sb2.append(strArrSplit2[i2]);
                        sb2.append(File.separator);
                        C0721b c0721bF1 = c0721b3.f0(strArrSplit2[i2]);
                        if (c0721bF1 == null) {
                            String string = sb2.toString();
                            if (string.endsWith(str)) {
                                ax.p221pa.c.h().g().d("ARCHIVE ENTRY ERROR").h("Entry:" + name2 + "," + name2.length() + "," + string).i();
                            }
                            C0721b c0721b4 = new C0721b(this, new ax.p097gc.I(string), c0721b3);
                            c0721b3.c0(c0721b4);
                            c0721bF1 = c0721b4;
                        } else {
                            enumerationI2 = enumerationI2;
                            str = str;
                        }
                        c0721b3 = c0721bF1;
                    }
                    i2++;
                    enumerationI2 = enumerationI2;
                    str = str;
                }
                enumeration = enumerationI2;
                str2 = str;
                C0721b c0721b5 = new C0721b(this, iNextElement2, c0721b3);
                if (TextUtils.isEmpty(c0721b5.i0())) {
                    ax.p338y1.d.c("archivefileinfo name", new Exception("Invalid entry:" + name2));
                } else {
                    c0721b3.c0(c0721b5);
                }
            }
            enumerationI2 = enumeration;
            str = str2;
        }
    }

    private int g0(C0721b c0721b) {
        if (!c0721b.isDirectory()) {
            return 1;
        }
        Stack stack = new Stack();
        stack.push(c0721b);
        int i = 0;
        while (!stack.isEmpty()) {
            C0721b c0721b2 = (C0721b) stack.pop();
            if (c0721b2.isDirectory()) {
                for (C0721b c0721b3 : c0721b2.g0()) {
                    if (c0721b3.isDirectory()) {
                        stack.push(c0721b3);
                    } else {
                        i++;
                    }
                }
            }
        }
        return i;
    }

    public static boolean h0(File file) {
        String[] list;
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String str : list) {
                if (!h0(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static int j0(Uri uri) {
        if (uri == null) {
            ax.p045d2.b.f();
            return 0;
        }
        String string = uri.toString();
        Integer num = z.get(string);
        if (num != null) {
            return num.intValue();
        }
        Integer numValueOf = Integer.valueOf(Math.abs(string.hashCode()));
        while (y.containsKey(numValueOf)) {
            numValueOf = Integer.valueOf(numValueOf.intValue() + 1);
        }
        z.put(string, numValueOf);
        y.put(numValueOf, string);
        return numValueOf.intValue();
    }

    public static InputStream m0(ax.p097gc.T t, ax.p097gc.I i, String str) throws C0718i, IOException {
        ax.Tb.h hVar = new ax.Tb.h(t.j0(i), str.toCharArray());
        ax.Ub.e eVar = new ax.Ub.e();
        eVar.u(i.getCrc());
        eVar.w(i.isDirectory());
        eVar.s(i.getCompressedSize());
        eVar.G(i.getSize());
        if (hVar.j(eVar, false) != null) {
            return hVar;
        }
        throw new ax.I1.D("Could not locate local file header for encrypted entry");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File n0() {
        if (this.h == null) {
            return null;
        }
        return new File(ax.G1.a.l(n(), "archive-edit"), this.h.replace(".", "_") + System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static File q0(Context context, ax.p338y1.f fVar, int i) {
        return ax.G1.a.h(context, fVar, i);
    }

    private C0721b t0(String str) {
        String[] strArrSplit = str.split(File.separator);
        if (strArrSplit == null) {
            return null;
        }
        C0721b c0721bF0 = this.p;
        for (int i = 0; i < strArrSplit.length - 1; i++) {
            String str2 = strArrSplit[i];
            if (str2 != null && str2.length() != 0 && (c0721bF0 = c0721bF0.f0(str2)) == null) {
                return null;
            }
        }
        return c0721bF0;
    }

    private InputStream u0(ax.p097gc.I i) throws C0718i, IOException {
        return (!this.s || TextUtils.isEmpty(this.t)) ? this.n.k(i) : m0(this.n, i, this.t);
    }

    public static boolean x0(ax.p097gc.S s, ax.p045d2.c cVar) throws C0710a {
        Enumeration<ax.p097gc.I> enumerationI = s.i();
        while (enumerationI.hasMoreElements()) {
            if (cVar != null && cVar.isCancelled()) {
                throw new C0710a();
            }
            if (enumerationI.nextElement().p().m()) {
                return true;
            }
        }
        return false;
    }

    public static String z0(String str, boolean z2) {
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

    public void A0(Uri uri, u uVar, int i) {
        this.j = uri;
        this.r = i;
        this.i = uVar;
        this.h = uVar.w();
    }

    public void B0(Uri uri, String str, int i, int i2) {
        this.j = uri;
        this.r = i2;
        this.h = str;
        this.k = i;
        try {
            this.l = ParcelFileDescriptor.fromFd(i);
        } catch (IOException unused) {
        }
    }

    public void C0(String str) {
        this.t = str;
    }

    public void E0(String str, ax.p001a0.a<d> aVar) {
        new e(str, aVar).i(new String[0]);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean F(AbstractC5838l abstractC5838l) {
        return d0();
    }

    public boolean F0(ax.P1.i iVar) throws C0718i {
        int i = this.r;
        if (i == 3 || i == 2) {
            ax.p045d2.b.g("not reachable : " + this.r + "," + v0());
            return false;
        }
        u uVar = this.i;
        if (uVar == null) {
            ax.p045d2.b.g("not reachable");
            return false;
        }
        try {
            String strC = uVar.C();
            File file = new File(V.r(strC), V.h(strC) + ".tmp.zip");
            C5839m c5839mF = ax.J1.r.f(file);
            C5845t c5845t = (C5845t) c5839mF.t();
            AbstractC5838l abstractC5838lV0 = c5839mF.V0(strC);
            AbstractC5838l abstractC5838lV1 = c5839mF.V0(file.getAbsolutePath());
            ax.p097gc.J j = new ax.p097gc.J(c5845t.c(file.getAbsolutePath(), false));
            j.C0(Charset.defaultCharset().name());
            List<C0721b> listG0 = this.p.g0();
            if (listG0 != null) {
                int iG0 = g0(this.p);
                if (iVar != null) {
                    iVar.a(0L, iG0);
                }
                Integer[] numArr = {0, Integer.valueOf(iG0)};
                for (C0721b c0721b : listG0) {
                    G0(c0721b, j, numArr, iVar);
                    if (!c0721b.isDirectory() && iVar != null) {
                        int iIntValue = numArr[0].intValue() + 1;
                        numArr[0] = Integer.valueOf(iIntValue);
                        iVar.a(iIntValue, numArr[1].intValue());
                    }
                }
            }
            j.r();
            j.close();
            c5839mF.l1(abstractC5838lV0);
            try {
                c5839mF.R(abstractC5838lV1, abstractC5838lV0, null, null);
                return true;
            } catch (C0710a | C0718i unused) {
                return false;
            }
        } catch (IOException e2) {
            throw C0712c.a("update archive error", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        if (this.p == null) {
            throw new C0718i("no root");
        }
        String[] strArrSplit = str.split(File.separator);
        C0721b c0721bF0 = this.p;
        for (int i = 0; i < strArrSplit.length; i++) {
            String str2 = strArrSplit[i];
            if (str2 != null && !HttpUrl.FRAGMENT_ENCODE_SET.equals(str2)) {
                if (c0721bF0 == null) {
                    ax.p221pa.c.h().d("ARFI").k().i();
                }
                c0721bF0 = c0721bF0.f0(strArrSplit[i]);
                if (c0721bF0 == null) {
                    return new C0721b(this, str);
                }
            }
        }
        return c0721bF0;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        try {
            C0721b c0721b = (C0721b) V0(str2);
            return new ax.J9.a(new BufferedInputStream(d1(c0721b, 0L), 32768), (int) c0721b.o());
        } catch (C0718i unused) {
            return null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (EnumC0738t.IMAGE != abstractC5838l.E()) {
            return null;
        }
        return C0740v.Q(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.n != null && this.o;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public synchronized void b() {
        ax.J1.r.h(r(), q());
        i0();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        String[] strArrSplit = abstractC5838l.t().split(File.separator);
        if (strArrSplit != null) {
            C0721b c0721bF0 = this.p;
            C0721b c0721b = c0721bF0;
            for (String str : strArrSplit) {
                if (str.length() != 0) {
                    if (c0721bF0 == null) {
                        throw new ax.I1.s("Can not found fileinfo");
                    }
                    c0721b = c0721bF0;
                    c0721bF0 = c0721bF0.f0(str);
                }
            }
            if (c0721bF0 != null) {
                c0721b.k0(c0721bF0);
                this.q = true;
                if (d0()) {
                    return;
                }
                ax.p221pa.c.h().g().b("Read only archvie file write 4").k().h("type:" + this.r).i();
                return;
            }
        }
        throw new ax.I1.s("Could not delete...");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a, String str, long j, Long l, C5840n c5840n, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p045d2.b.a(abstractC5838l.n());
        if (!d0()) {
            throw new C0718i("zip file is not writeable");
        }
        AbstractC5838l abstractC5838lV0 = V0(abstractC5838l.C());
        if (abstractC5838lV0.n()) {
            throw new C0714e(abstractC5838lV0.isDirectory() != abstractC5838l.isDirectory());
        }
        InputStream inputStreamB = a.b();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                String strC = abstractC5838l.C();
                C0721b c0721bT0 = t0(strC);
                if (c0721bT0 == null) {
                    throw new C0718i("Parent not found");
                }
                C0721b c0721b = new C0721b(this, new ax.p097gc.I(z0(strC, false)), c0721bT0);
                File fileO0 = o0(c0721b);
                c0721b.m0(fileO0);
                FileOutputStream fileOutputStream2 = new FileOutputStream(fileO0);
                try {
                    C0744z.e(inputStreamB, fileOutputStream2, j, cVar, iVar);
                    fileOutputStream2.close();
                    c0721bT0.c0(c0721b);
                    if (l != null && l.longValue() >= 0) {
                        try {
                            fileO0.setLastModified(l.longValue());
                        } catch (Exception e2) {
                            ax.p221pa.c.h().g().d("set last modified 1").m(e2).i();
                        }
                    }
                    this.q = true;
                    if (!d0()) {
                        ax.p221pa.c.h().g().b("Read only archvie file write 5").k().h("type:" + this.r).i();
                    }
                    if (inputStreamB != null) {
                        try {
                            inputStreamB.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    throw C0712c.a("archive write file", e);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    Throwable th2 = th;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                            throw th2;
                        }
                    }
                    if (inputStreamB == null) {
                        throw th2;
                    }
                    inputStreamB.close();
                    throw th2;
                }
            } catch (IOException e6) {
                e = e6;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public boolean d0() {
        if (this.s) {
            return false;
        }
        int i = this.r;
        if (i == 1) {
            u uVar = this.i;
            if (uVar != null) {
                return uVar.k();
            }
            return false;
        }
        if (i != 2 && i != 3) {
            ax.p045d2.b.g("type:" + this.r + ",uri:" + this.j);
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        ax.p097gc.I i = null;
        try {
            ax.p097gc.I iJ0 = ((C0721b) abstractC5838l).j0();
            if (iJ0 == null) {
                throw new ax.I1.s("Zip entry is null");
            }
            File fileO0 = o0(abstractC5838l);
            if (fileO0.exists()) {
                FileInputStream fileInputStream = new FileInputStream(fileO0);
                if (j != 0) {
                    fileInputStream.skip(j);
                }
                return fileInputStream;
            }
            InputStream inputStreamU0 = u0(iJ0);
            if (inputStreamU0 == null) {
                throw new IOException("ZipFile getInputStream == null");
            }
            C0502a c0502a = new C0502a(inputStreamU0);
            if (j != 0) {
                c0502a.skip(j);
            }
            return c0502a;
        } catch (IOException e2) {
            throw C0712c.a("archive getinputstream", e2);
        } catch (ArrayIndexOutOfBoundsException unused) {
            ax.p221pa.c.h().d("AFGIS").h(i.getSize() + ":" + i.getName()).i();
            throw new C0718i("Zip entry read error");
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public synchronized void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        if (this.k == 0 && this.i == null) {
            ax.p221pa.c.h().g().b("ArchiveFileHelper invalid auth").k().i();
        }
        try {
            new b(aVar).i(new String[0]);
        } catch (Exception unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        if (abstractC5838l.isDirectory()) {
            return ((C0721b) abstractC5838l).h0();
        }
        throw new C0718i("file is not directory");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        C0721b c0721bT0 = t0(abstractC5838l.C());
        String strW = abstractC5838l.w();
        boolean z2 = false;
        if (c0721bT0 != null && c0721bT0.n() && c0721bT0.f0(strW) == null) {
            z2 = true;
            C0721b c0721b = new C0721b(this, new ax.p097gc.I(z0(abstractC5838l.C(), true)), c0721bT0);
            File fileO0 = o0(c0721b);
            fileO0.mkdir();
            c0721b.m0(fileO0);
            c0721bT0.c0(c0721b);
            this.q = true;
            if (!d0()) {
                ax.p221pa.c.h().g().b("Read only archvie file write 2").k().h("type:" + this.r).i();
            }
        }
        return z2;
    }

    public ax.p045d2.n i0() {
        this.o = false;
        return new c(n(), s(), this.l, this.m, this.i, this.n, this.u, this.v, this.r).i(new Long[0]);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        C0721b c0721bT0 = t0(abstractC5838l.C());
        String strW = abstractC5838l.w();
        if (c0721bT0 != null && c0721bT0.n() && c0721bT0.f0(strW) == null) {
            try {
                C0721b c0721b = new C0721b(this, new ax.p097gc.I(z0(abstractC5838l.C(), false)), c0721bT0);
                File fileO0 = o0(c0721b);
                fileO0.createNewFile();
                c0721b.m0(fileO0);
                c0721bT0.c0(c0721b);
                this.q = true;
                if (!d0()) {
                    ax.p221pa.c.h().g().b("Read only archvie file write 1").k().h("type:" + this.r).i();
                }
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    public int k0() {
        return this.r;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        long jO = abstractC5838l.o();
        C0721b c0721b = (C0721b) V0(abstractC5838l.C());
        File fileO0 = o0(c0721b);
        C0721b c0721b2 = (C0721b) abstractC5838l2;
        File fileO1 = o0(c0721b2);
        C0721b c0721bT0 = t0(abstractC5838l.C());
        C0721b c0721bT1 = t0(abstractC5838l2.C());
        if (c0721bT1 == null) {
            throw new C0718i("Target parent does not exist");
        }
        if (c0721bT0 == null) {
            throw new C0718i("Source parent does not exist");
        }
        if (abstractC5838l.isDirectory()) {
            c0721b2.l0();
        }
        if (!c0721b.n()) {
            ax.p045d2.b.g("no zip entry source");
            throw new ax.I1.s("Source file entry is null");
        }
        try {
            c0721bT0.k0(c0721b);
            C0721b c0721b3 = new C0721b(this, c0721bT1, c0721b.j0(), c0721b.g0(), abstractC5838l2.w());
            c0721b3.o0();
            c0721bT1.c0(c0721b3);
            this.q = true;
            if (!d0()) {
                ax.p221pa.c.h().g().b("Read only archvie file write 3").k().h("type:" + this.r).i();
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
            if (fileO0.exists()) {
                fileO0.renameTo(fileO1);
                c0721b3.m0(fileO1);
            }
        } catch (IllegalArgumentException e2) {
            ax.p221pa.c.h().g().b("ARCHIVE FILE NO NAME").h("dst:" + c0721b2.i0()).m(e2).i();
            throw e2;
        }
    }

    public Uri l0() {
        return this.j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        b1(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    public File o0(AbstractC5838l abstractC5838l) {
        File file = this.u;
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file.getAbsolutePath(), abstractC5838l.S());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return new File(file.getAbsolutePath(), abstractC5838l.C());
    }

    public int p0() {
        return this.k;
    }

    public u r0() {
        return this.i;
    }

    public String s0() {
        return this.h;
    }

    public boolean v0() {
        return this.q;
    }

    public boolean w0() {
        return this.s;
    }

    public boolean y0() {
        return (this.r == 0 || this.j == null) ? false : true;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.a$a, reason: collision with other inner class name */
    class C0502a extends ax.J1.B {
        C0502a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // ax.J1.B, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (C5827a.this.w) {
                super.close();
            }
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read() throws IOException {
            int i;
            synchronized (C5827a.this.w) {
                i = super.read();
            }
            return i;
        }

        @Override // ax.J1.B, java.io.InputStream
        public synchronized void reset() throws IOException {
            synchronized (C5827a.this.w) {
                super.reset();
            }
        }

        @Override // ax.J1.B, java.io.InputStream
        public long skip(long j) throws IOException {
            int i;
            if (j <= 0) {
                return 0L;
            }
            int iMin = (int) Math.min(2048, j);
            byte[] bArr = new byte[iMin];
            long j2 = j;
            while (j2 > 0 && (i = read(bArr, 0, (int) Math.min(iMin, j2))) >= 0) {
                j2 -= (long) i;
            }
            return j - j2;
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            int i;
            synchronized (C5827a.this.w) {
                i = super.read(bArr);
            }
            return i;
        }

        @Override // ax.J1.B, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            synchronized (C5827a.this.w) {
                i3 = super.read(bArr, i, i2);
            }
            return i3;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z2, ax.P1.h hVar, ax.p045d2.c cVar) {
    }
}
