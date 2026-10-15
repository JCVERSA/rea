package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.TransactionTooLargeException;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0718i;
import ax.I1.C0719j;
import ax.J1.C0727h;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.C0744z;
import ax.J1.EnumC0738t;
import ax.J1.V;
import ax.J1.b0;
import ax.J1.e0;
import com.alphainventor.filemanager.service.ScanService;
import j$.nio.file.CopyOption;
import j$.nio.file.Files;
import j$.nio.file.Paths;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.logging.Logger;
import okhttp3.HttpUrl;
import okhttp3.internal.ws.RealWebSocket;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5845t extends AbstractC5830d {
    private static final Logger r = Logger.getLogger("FileManager.LocalFileHelper");
    private static int s = 327680;
    private static final List<String> t = Arrays.asList("acct", "cache", "charger", "config", "d", "data", "dev", "etc", "mnt", "oem", "proc", "property_contexts", "root", "sbin", "sdcard", "storage", "sys", "system", "vendor");
    private static final List<String> u = Arrays.asList("0", "999", "95", "96");
    private static final List<String> v = Arrays.asList("/DCIM/Camera", "/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images");
    private static final List<String> w = Arrays.asList("/DCIM/Camera");
    private static final List<String> x = Arrays.asList("/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Voice Notes", "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses");
    private static final String[] y = {"_id", "_data", "date_modified", "_size", "mime_type", "date_added"};
    private C5832f l;
    private HashSet<String> n;
    private HashSet<String> o;
    private Boolean p;
    private HashMap<String, Boolean> h = new HashMap<>();
    private ArrayList<ax.X1.d> i = new ArrayList<>();
    private boolean j = false;
    private ConcurrentHashMap<String, ax.X1.d> k = new ConcurrentHashMap<>();
    private final Object m = new Object();
    private boolean q = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$a */
    class a implements ax.P1.h {
        final /* synthetic */ ax.P1.h X;
        final /* synthetic */ List q;

        a(List list, ax.P1.h hVar) {
            this.q = list;
            this.X = hVar;
        }

        @Override // ax.P1.h
        public void B() {
        }

        @Override // ax.P1.h
        public void l0(List<AbstractC5838l> list, boolean z) {
            ArrayList arrayList = null;
            for (AbstractC5838l abstractC5838l : list) {
                if (!this.q.contains(abstractC5838l.C())) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(abstractC5838l);
                }
            }
            if (arrayList != null) {
                this.X.l0(arrayList, z);
            }
        }

        @Override // ax.P1.h
        public void t() {
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$b */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.p338y1.f.values().length];
            a = iArr;
            try {
                iArr[ax.p338y1.f.q0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.p338y1.f.r0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.p338y1.f.z0.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ax.p338y1.f.x0.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ax.p338y1.f.y0.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ax.p338y1.f.w0.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ax.p338y1.f.v0.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ax.p338y1.f.t0.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ax.p338y1.f.s0.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ax.p338y1.f.H0.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$c */
    static class c extends ax.J1.S {
        u X;
        C5845t Y;

        c(C5845t c5845t, u uVar, boolean z) throws FileNotFoundException {
            super(new FileOutputStream(uVar.y0(), z));
            this.Y = c5845t;
            this.X = uVar;
        }

        @Override // ax.J1.S, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this.Y.D1(this.X, false, false);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$d */
    private class d extends ax.p045d2.n<Long, Long, Long> {
        private ArrayList<ax.X1.d> h;
        private long i;
        private long j;

        public d(ArrayList<ax.X1.d> arrayList, long j) {
            super(ax.d2.n.f.HIGH);
            this.i = System.currentTimeMillis();
            this.j = j;
            this.h = arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Long g(Long... lArr) {
            if (this.j > 0) {
                long jCurrentTimeMillis = System.currentTimeMillis() - this.i;
                long j = this.j;
                if (jCurrentTimeMillis < j) {
                    try {
                        Thread.sleep(j - jCurrentTimeMillis);
                    } catch (Exception unused) {
                    }
                }
            }
            C5845t.this.i0(this.h, 0, null);
            return null;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$e */
    public interface e {
        void a();

        void b(boolean z);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.t$f */
    public static class f extends ax.p045d2.n<Void, Void, Boolean> {
        C5845t h;
        AbstractC5838l i;
        e j;
        boolean k;
        C5839m l;

        public f(C5839m c5839m, AbstractC5838l abstractC5838l, boolean z, e eVar) {
            super(ax.d2.n.f.HIGH);
            this.l = c5839m;
            this.h = (C5845t) c5839m.t();
            this.i = abstractC5838l;
            this.j = eVar;
            this.k = z;
            this.l.S();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) throws Throwable {
            boolean zN;
            boolean z = true;
            boolean z2 = ax.p338y1.f.c0(this.i.P()) || ax.p338y1.f.d0(this.i.P());
            if (this.k) {
                zN = z2 ? ax.G1.f.h().n(this.i, true) : this.h.U(this.i);
            } else {
                boolean zN2 = z2 ? ax.G1.f.h().n(this.i, false) : false;
                boolean zA1 = this.h.A1(this.i);
                if (!zN2 && !zA1) {
                    z = false;
                }
                zN = z;
            }
            if (ax.E1.P.G1() && this.l.E()) {
                this.l.m(null);
            }
            return Boolean.valueOf(zN);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.j != null) {
                if (bool.booleanValue()) {
                    this.j.b(this.k);
                } else {
                    this.j.a();
                }
            }
            this.l.P(true);
        }
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0073  */
    private b0 A0(String str, long j) throws C0718i {
        try {
            ax.E1.L l = ax.E1.u.l(str);
            long j2 = j > 0 ? j : l.b * l.a;
            long j3 = l.c;
            long j4 = l.a;
            Long.signum(j3);
            long j5 = j2 - (j3 * j4);
            if (ax.E1.I.B() && r() == ax.p338y1.f.q0 && j2 > 1000000000000L && (j5 * 100) / j2 >= 95) {
                String strM = V.M(str, "DCIM");
                if (new File(strM).exists()) {
                    try {
                        b0 b0VarZ0 = z0(strM, j);
                        long j6 = b0VarZ0.b;
                        if (j6 != 0) {
                            long j7 = b0VarZ0.a;
                            if (j7 != 0 && (j7 * 100) / j6 < 95) {
                                return b0VarZ0;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        throw new C0718i(e);
                    }
                }
            }
            return new b0(j2, j5, 0);
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static long B1(long j) {
        long j2;
        long j3 = 1;
        long j4 = 1;
        while (true) {
            j2 = j3 * j4;
            if (j2 >= j || j - j2 <= j2 / 2) {
                break;
            }
            j3 <<= 1;
            if (j3 > 512) {
                j4 *= ax.E1.P.K1() ? 1000L : RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
                j3 = 1;
            }
        }
        return j2 > j ? j2 : j;
    }

    private void C1(AbstractC5838l abstractC5838l, boolean z) {
        if (e0(abstractC5838l, z)) {
            return;
        }
        T(abstractC5838l, true, z, false, false);
    }

    private InputStream D0(String str) {
        if (new File(str).length() == 0) {
            return ax.p003a2.b.n();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return (options.outWidth <= 0 || options.outHeight <= 0) ? ax.p003a2.b.n() : q1(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D1(AbstractC5838l abstractC5838l, boolean z, boolean z2) {
        if (e0(abstractC5838l, abstractC5838l.isDirectory())) {
            return;
        }
        if (abstractC5838l.isDirectory()) {
            S(abstractC5838l, z, z2);
        } else {
            T(abstractC5838l, false, false, z, z2);
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.alphainventor.filemanager.file.u] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v7, types: [android.os.ParcelFileDescriptor] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.os.ParcelFileDescriptor] */
    private InputStream E0(String str) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptorC0;
        Bitmap bitmapG;
        String strQ0 = q0(str, true);
        if (strQ0 != null) {
            return q1(strQ0);
        }
        ?? r0 = (u) V0(str);
        ?? r3 = 0;
        try {
            if (r0.g0()) {
                try {
                    parcelFileDescriptorC0 = r0.c0();
                    try {
                        bitmapG = ax.p045d2.x.g(n(), null, parcelFileDescriptorC0.getFileDescriptor(), null, 512);
                        r0 = parcelFileDescriptorC0;
                    } catch (C0718i e2) {
                        e = e2;
                        e.printStackTrace();
                        bitmapG = ax.p045d2.x.g(n(), str, null, null, 512);
                        r0 = parcelFileDescriptorC0;
                        if (parcelFileDescriptorC0 != null) {
                        }
                        if (bitmapG == null) {
                            bitmapG = ax.p017b2.a.b();
                        }
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
                        bitmapG.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    }
                } catch (C0718i e3) {
                    e = e3;
                    parcelFileDescriptorC0 = null;
                } catch (Throwable th) {
                    th = th;
                    if (r3 != 0) {
                        try {
                            r3.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
                try {
                    r0.close();
                } catch (IOException unused2) {
                }
            } else {
                bitmapG = ax.p045d2.x.g(n(), str, null, null, 512);
            }
            if (bitmapG == null) {
                bitmapG = ax.p017b2.a.b();
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(32768);
            bitmapG.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream2);
            return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
        } catch (Throwable th2) {
            th = th2;
            r3 = r0;
        }
    }

    private void E1(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) {
        if (cVar == null || !cVar.isCancelled()) {
            List<String> listC = ax.J1.H.c(n(), abstractC5838l, str);
            ArrayList arrayList = new ArrayList();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
            for (String str3 : listC) {
                String strR = V.r(str3);
                if (!str2.equals(strR)) {
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    if (arrayList.size() > 0 && jCurrentTimeMillis2 - jCurrentTimeMillis > 100) {
                        j0(abstractC5838l, arrayList, false, str, z, hVar);
                        arrayList.clear();
                        jCurrentTimeMillis = jCurrentTimeMillis2;
                    }
                    if (cVar != null && cVar.isCancelled()) {
                        return;
                    } else {
                        str2 = strR;
                    }
                }
                AbstractC5838l abstractC5838lV0 = V0(str3);
                if (abstractC5838lV0.n()) {
                    arrayList.add(abstractC5838lV0);
                }
            }
            if (arrayList.size() > 0) {
                j0(abstractC5838l, arrayList, true, str, z, hVar);
                arrayList.clear();
            }
            m(abstractC5838l, str, z, new a(listC, hVar), cVar);
        }
    }

    public static void G1(Context context, ArrayList<ax.X1.d> arrayList, boolean z, int i) {
        Intent intent = new Intent(context, (Class<?>) ScanService.class);
        intent.putExtra("PENDING_SCAN_ARRAY", (Parcelable[]) arrayList.toArray(new ax.X1.d[0]));
        intent.putExtra("HAS_FOLLOWING_LIST", z);
        try {
            ax.p045d2.x.f0(context, intent, true, true);
        } catch (IllegalStateException e2) {
            ax.p221pa.c.i(context).g().b("START SCAN SERVICE FOREGROUND").m(e2).i();
        }
    }

    static boolean H0(AbstractC5838l abstractC5838l) {
        if (!ax.E1.P.L() || !C0740v.H(abstractC5838l)) {
            return false;
        }
        try {
            u uVar = (u) abstractC5838l;
            if (uVar.v0() != ax.J1.F.e && uVar.v0() != ax.J1.F.f) {
                return false;
            }
            return V.F("/Android/data", uVar.w0(), true);
        } catch (IllegalArgumentException e2) {
            ax.p221pa.c.h().g().b("SUBDIRECTORY ERROR").m(e2).i();
            return false;
        }
    }

    private void H1(ArrayList<ax.X1.d> arrayList, boolean z, int i) {
        G1(n(), arrayList, z, i);
    }

    static boolean I0(AbstractC5838l abstractC5838l) {
        if (!C0740v.H(abstractC5838l)) {
            return false;
        }
        try {
            u uVar = (u) abstractC5838l;
            if (uVar.v0() != ax.J1.F.e && (uVar.v0() != ax.J1.F.f || !ax.E1.P.L())) {
                return false;
            }
            return V.F("/Android/obb", uVar.w0(), true);
        } catch (IllegalArgumentException e2) {
            ax.p221pa.c.h().g().b("SUBDIRECTORY ERROR").m(e2).i();
            return false;
        }
    }

    private boolean J0() {
        if (!ax.E1.P.F1()) {
            return false;
        }
        return !ax.G1.i.D().d0(s());
    }

    private boolean J1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        u uVar = (u) abstractC5838l;
        u uVar2 = (u) abstractC5838l2;
        boolean zX0 = uVar.X0();
        if (!zX0 && (uVar.v0() != ax.J1.F.f || !uVar.I0())) {
            return false;
        }
        if (uVar.v0() == ax.J1.F.e) {
            return zX0 && uVar2.X0();
        }
        return uVar.v0() != ax.J1.F.f || zX0 || uVar2.X0();
    }

    private boolean K0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l.P() == ax.p338y1.f.s0) {
            return true;
        }
        String strV = abstractC5838l.V();
        return strV != null && strV.startsWith("/Download");
    }

    private boolean K1() {
        return this.q && r() == ax.p338y1.f.v0 && ax.G1.i.D().q();
    }

    private boolean L0(String str) {
        return str == null || "vnd.android.document/directory".equals(str);
    }

    private boolean M0(Exception exc) {
        if ("AccessDeniedException".equals(exc.getClass().getSimpleName()) || "DirectoryNotEmptyException".equals(exc.getClass().getSimpleName())) {
            return true;
        }
        return exc.getMessage() != null && exc.getMessage().endsWith("Operation not permitted");
    }

    private boolean N0() {
        if (this.p == null) {
            this.p = Boolean.valueOf(ax.p338y1.f.o0(r()));
        }
        return this.p.booleanValue();
    }

    private boolean O0(u uVar) {
        return (!ax.p338y1.f.h0(uVar.P()) || uVar.Q0() || E.b2(uVar) || uVar.C().endsWith(".tmp")) ? false : true;
    }

    public static boolean P0(Context context, ax.J1.F f2, Uri uri) {
        String string = uri.toString();
        if (string == null) {
            ax.p221pa.c.h().d("DOCUMENT TREE URI ERROR 1").h(uri.toString()).i();
            return false;
        }
        if (string.lastIndexOf("/") < 0) {
            ax.p221pa.c.h().d("DOCUMENT TREE URI ERROR 2").h(uri.toString()).i();
            return false;
        }
        String strSubstring = string.substring(string.lastIndexOf("/"));
        String strH = V.h(f2.e());
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(strH);
        return strSubstring.startsWith(sb.toString());
    }

    private void Q(u uVar, ax.J1.F f2, File[] fileArr, List<AbstractC5838l> list, boolean z) {
        boolean zK0 = uVar.K0();
        for (File file : fileArr) {
            String name = file.getName();
            if (!V.z(name)) {
                if (r() == ax.p338y1.f.v0 && file.isDirectory()) {
                    list.add(new u(this, uVar.y0(), file, (ax.J1.F) null, z));
                } else if (zK0 && ax.J1.E.a(uVar.R()) && ("data".equals(name) || "obb".equals(name))) {
                    list.add(V0(file.getAbsolutePath()));
                } else {
                    list.add(new u(this, uVar.y0(), file, f2, z));
                }
            }
        }
    }

    private boolean Q0(AbstractC5838l abstractC5838l) {
        u uVar = (u) abstractC5838l;
        ax.p338y1.f fVarR = r();
        ax.p338y1.f fVar = ax.p338y1.f.v0;
        return fVarR == fVar || uVar.v0().d() == fVar;
    }

    private void R(AbstractC5838l abstractC5838l) {
        T(abstractC5838l, false, true, false, false);
    }

    private void S(AbstractC5838l abstractC5838l, boolean z, boolean z2) {
        Stack stack = new Stack();
        stack.push(abstractC5838l);
        while (stack.size() > 0) {
            AbstractC5838l abstractC5838l2 = (AbstractC5838l) stack.pop();
            T(abstractC5838l2, false, true, z, z2);
            try {
                for (AbstractC5838l abstractC5838l3 : g1(abstractC5838l2)) {
                    if (abstractC5838l3.isDirectory()) {
                        stack.push(abstractC5838l3);
                    } else {
                        T(abstractC5838l3, false, false, z, z2);
                    }
                }
            } catch (C0718i unused) {
            }
        }
    }

    private List<AbstractC5838l> S0(AbstractC5838l abstractC5838l) throws C0718i {
        File[] fileArrListFiles;
        Collection<? extends AbstractC5838l> collectionE;
        Collection<? extends AbstractC5838l> collectionO;
        u uVar = (u) abstractC5838l;
        if (K1()) {
            fileArrListFiles = null;
        } else {
            try {
                fileArrListFiles = uVar.y0().listFiles();
            } catch (ArrayStoreException e2) {
                ax.p221pa.c.h().g().d("LLISTCH2 ArrayStoreException").i();
                throw new C0718i(e2);
            } catch (OutOfMemoryError e3) {
                ax.p221pa.c.h().g().d("LLISTCH2 OutOfMemoryError").i();
                throw new C0718i(e3);
            }
        }
        File[] fileArrListFiles2 = fileArrListFiles;
        List<AbstractC5838l> arrayList = new ArrayList<>();
        ax.J1.F fV0 = uVar.v0();
        if (fileArrListFiles2 != null) {
            Q(uVar, fV0, fileArrListFiles2, arrayList, uVar.V0());
        }
        if ((fileArrListFiles2 == null || arrayList.size() == 0) && ax.p338y1.f.P(fV0) && uVar.Q0()) {
            if (ax.J1.E.a(uVar.R())) {
                File fileB = ax.J1.E.b(uVar.R(), uVar.C());
                if (fileB != null && (fileArrListFiles2 = fileB.listFiles()) != null) {
                    Q(uVar, fV0, fileArrListFiles2, arrayList, true);
                }
            } else if (com.alphainventor.filemanager.shizuku.c.t().l() && (collectionE = com.alphainventor.filemanager.shizuku.c.t().E(this, fV0, uVar.C())) != null) {
                uVar.b1(true);
                arrayList.addAll(collectionE);
                return arrayList;
            }
        }
        if (fileArrListFiles2 == null || (arrayList.size() == 0 && !uVar.y0().canRead())) {
            if (Q0(uVar)) {
                if (W() && ((ax.E1.P.J1() || !uVar.Q0()) && (collectionO = v.Y().O(this, fV0, uVar.C())) != null)) {
                    this.q = true;
                    arrayList.addAll(collectionO);
                    return arrayList;
                }
                if (Build.VERSION.SDK_INT >= 24 && arrayList.size() == 0) {
                    if ("/".equals(abstractC5838l.C())) {
                        n1(arrayList, fV0);
                    } else if ("/storage/emulated".equals(abstractC5838l.C())) {
                        p1(arrayList, fV0);
                    } else if ("/storage".equals(abstractC5838l.C())) {
                        o1(arrayList, fV0);
                    }
                }
            }
            if (ax.G1.i.D().u0(uVar.v0()) && ax.p338y1.f.P(s()) && uVar.R0() && !com.alphainventor.filemanager.shizuku.c.t().z()) {
                U0(abstractC5838l, arrayList, fV0);
                return arrayList;
            }
            if (arrayList.size() == 0) {
                uVar.f1();
                if (uVar.n()) {
                    throw new C0713d();
                }
                if (ax.E1.P.j() && uVar.P0()) {
                    throw new C0713d();
                }
                throw new ax.I1.s();
            }
        }
        return arrayList;
    }

    private void T(AbstractC5838l abstractC5838l, boolean z, boolean z2, boolean z3, boolean z4) {
        HashSet<String> hashSet;
        ax.X1.d dVar;
        synchronized (this.m) {
            try {
                String strC = abstractC5838l.C();
                if (this.k.containsKey(strC) && (dVar = this.k.get(strC)) != null) {
                    boolean z5 = dVar.q;
                    if (!z5 && z) {
                        this.i.remove(dVar);
                        this.k.remove(strC);
                        return;
                    } else if (z5 && z) {
                        this.i.remove(dVar);
                        this.k.remove(strC);
                    } else {
                        this.i.remove(dVar);
                        this.k.remove(strC);
                    }
                }
                if (z2 && z) {
                    Iterator<ax.X1.d> it = this.i.iterator();
                    while (it.hasNext()) {
                        ax.X1.d next = it.next();
                        if (V.F(strC, next.X, true)) {
                            it.remove();
                            this.k.remove(next.X);
                        }
                    }
                }
                ax.X1.d dVar2 = new ax.X1.d(strC, abstractC5838l.P().J(), abstractC5838l.L(), abstractC5838l.q(), z, z2, z3, z4);
                this.k.put(strC, dVar2);
                this.i.add(dVar2);
                if (ax.E1.P.G1() && (hashSet = this.n) != null && hashSet.contains(abstractC5838l.S())) {
                    dVar2.m0 = true;
                    this.j = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private List<AbstractC5838l> T0(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            Cursor cursorQuery = n().getContentResolver().query(e0.c("external"), y, "bucket_id=? AND bucket_display_name=?", new String[]{String.valueOf(abstractC5838l.C().toLowerCase(Locale.ROOT).hashCode()), abstractC5838l.w()}, "_data asc");
            if (cursorQuery == null) {
                return null;
            }
            if (cursorQuery.getCount() <= 0) {
                cursorQuery.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ax.J1.F fV0 = ((u) abstractC5838l).v0();
            ax.E1.K k = new ax.E1.K();
            long j = 0;
            u uVar = null;
            while (cursorQuery.moveToNext()) {
                w wVarC0 = c0(fV0, cursorQuery, k);
                u uVarB0 = b0(fV0, wVarC0);
                long j2 = wVarC0.e;
                if (uVar != null && uVar.C().equalsIgnoreCase(uVarB0.C())) {
                    if (j2 > j) {
                        arrayList.remove(uVar);
                    }
                }
                arrayList.add(uVarB0);
                uVar = uVarB0;
                j = j2;
            }
            cursorQuery.close();
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    private void U0(AbstractC5838l abstractC5838l, List<AbstractC5838l> list, ax.J1.F f2) {
        if (ax.p338y1.f.P(f2)) {
            for (ax.B1.f fVar : ax.B1.d.F(n()).B()) {
                if (!TextUtils.isEmpty(fVar.n())) {
                    File file = new File(V.M(abstractC5838l.C(), fVar.n()));
                    if (file.exists()) {
                        list.add(new u(this, file, f2));
                    } else if (ax.E1.P.j() && fVar.x()) {
                        u uVar = new u(this, file, f2, true, false, true, false, 0L, 0L);
                        if (uVar.I0()) {
                            uVar.f1();
                            if (uVar.n()) {
                                list.add(uVar);
                            } else {
                                ax.G1.m.b(n()).e(n());
                            }
                        } else {
                            uVar.Z0(true);
                            list.add(uVar);
                        }
                    }
                }
            }
        }
    }

    public static boolean V(Context context, u uVar) {
        if (uVar.R0()) {
            if (!uVar.k() && !uVar.T0()) {
                List<AbstractC5838l> listP = ax.D1.c.q().p();
                if (listP != null && listP.size() == 1) {
                    AbstractC5838l abstractC5838l = listP.get(0);
                    if (abstractC5838l.isDirectory() && abstractC5838l.w().contains(".")) {
                        if (C5832f.z(context, uVar.v0(), V.M(uVar.C0(), abstractC5838l.w()))) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean W() {
        return ax.G1.i.D().q();
    }

    private void X(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            ax.p221pa.c.h().g().d("LLISTCH0").k().h("Location = " + r().H()).i();
            throw new ax.I1.s();
        }
        if (abstractC5838l.isDirectory()) {
            return;
        }
        ax.p221pa.c.h().d("LLISTCH1").h(abstractC5838l.isDirectory() + ":" + abstractC5838l.n()).i();
        throw new C0718i("file is not directory");
    }

    private void Y() {
        this.i.clear();
        this.k.clear();
        this.j = false;
    }

    private boolean a0(u uVar) throws C0718i {
        Uri uriD = C5832f.d(this, uVar, true);
        if (uriD == null) {
            return false;
        }
        if (".$recycle_bin$".equals(uVar.w())) {
            E.A = uriD;
        }
        return true;
    }

    private w c0(ax.J1.F f2, Cursor cursor, ax.E1.K k) {
        long j;
        boolean zIsDirectory;
        long j2;
        boolean z = true;
        String string = cursor.getString(1);
        long j3 = cursor.getLong(2) * 1000;
        long j4 = cursor.getLong(3);
        boolean zL0 = L0(cursor.getString(4));
        if (!cursor.isNull(2) && (zL0 || !cursor.isNull(3))) {
            z = false;
        }
        long j5 = cursor.getLong(5);
        if (zL0 || j3 == 0 || z) {
            try {
                ax.E1.u.k(string, k);
                long j6 = k.c;
                zL0 = k.b;
                if (!zL0) {
                    j4 = k.a;
                }
                j = j6;
            } catch (IOException unused) {
                File file = new File(string);
                long jLastModified = file.lastModified();
                String strF = V.f(string);
                if (TextUtils.isEmpty(strF) || C0739u.e(strF) == null) {
                    j = jLastModified;
                } else {
                    zIsDirectory = file.isDirectory();
                    j = jLastModified;
                    j2 = j4;
                }
                return new w(string, zIsDirectory, j, j5, j2);
            }
        } else {
            j = j3;
        }
        j2 = j4;
        zIsDirectory = zL0;
        return new w(string, zIsDirectory, j, j5, j2);
    }

    private boolean e0(AbstractC5838l abstractC5838l, boolean z) {
        if (!N0() || !C0740v.H(abstractC5838l) || ((u) abstractC5838l).v0().d() == ax.p338y1.f.v0) {
            return true;
        }
        if ((!ax.E1.P.u0() && !z && !C0739u.G(abstractC5838l.v())) || "tmp".equals(abstractC5838l.v())) {
            return true;
        }
        String strV = abstractC5838l.V();
        if (strV.contains(".$recycle_bin$")) {
            return true;
        }
        if (ax.E1.P.L()) {
            return strV.startsWith("/.localcache") || strV.startsWith("/Android/media/com.alphainventor.filemanager/.localcache");
        }
        return !ax.E1.P.L() && strV.contains("/localcache");
    }

    private void g0(ArrayList<ax.X1.d> arrayList) {
        ArrayList<ax.X1.d> arrayList2 = new ArrayList<>();
        boolean z = false;
        int iA = 5000;
        do {
            if (z) {
                arrayList.addAll(arrayList2);
                arrayList2.clear();
                z = false;
                iA = 5000;
            }
            try {
                Iterator<ax.X1.d> it = arrayList.iterator();
                while (it.hasNext()) {
                    ax.X1.d next = it.next();
                    if (next != null) {
                        arrayList2.add(next);
                        iA += next.a();
                    }
                    it.remove();
                    if (iA >= s) {
                        H1(arrayList2, it.hasNext(), iA);
                        return;
                    }
                }
                if (arrayList2.size() > 0) {
                    H1(arrayList2, false, iA);
                    return;
                }
            } catch (RuntimeException e2) {
                if ((e2.getCause() instanceof TransactionTooLargeException) && s == 327680) {
                    s = 163840;
                    z = true;
                } else {
                    ax.p338y1.d.c("executePendingScan", e2);
                }
            }
        } while (z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0(ArrayList<ax.X1.d> arrayList, int i, ax.P1.i iVar) {
        while (arrayList.size() > 0) {
            if (iVar != null) {
                iVar.a(i - arrayList.size(), i);
            }
            g0(arrayList);
            if (arrayList.size() > 0) {
                try {
                    Thread.sleep(500L);
                } catch (Exception unused) {
                }
            }
        }
        if (iVar != null) {
            iVar.a(i - arrayList.size(), i);
        }
    }

    public static AbstractC5838l k0(List<AbstractC5838l> list) {
        for (AbstractC5838l abstractC5838l : list) {
            if (".hidden".equals(abstractC5838l.w())) {
                return abstractC5838l;
            }
        }
        return null;
    }

    public static u o0(File file) {
        try {
            return (u) ax.J1.r.f(file).V0(file.getAbsolutePath());
        } catch (C0718i unused) {
            ax.p045d2.b.f();
            return null;
        }
    }

    private void o1(List<AbstractC5838l> list, ax.J1.F f2) {
        File file = new File("/storage/emulated");
        if (file.exists()) {
            list.add(new u(this, file, f2));
        }
    }

    private InputStream p0(u uVar, long j) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(uVar.y0());
        if (j != 0) {
            fileInputStream.skip(j);
        }
        return fileInputStream;
    }

    private void p1(List<AbstractC5838l> list, ax.J1.F f2) {
        Iterator<String> it = u.iterator();
        while (it.hasNext()) {
            File file = new File("/storage/emulated/", it.next());
            if (file.exists()) {
                list.add(new u(this, file, f2));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x005d  */
    private String q0(String str, boolean z) throws Throwable {
        Uri uri;
        Uri uri2;
        String str2;
        String strY1;
        if (!ax.E1.P.b()) {
            return null;
        }
        if (z) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            uri2 = MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI;
            str2 = "video_id";
        } else {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri2 = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
            str2 = "image_id";
        }
        String strY2 = y1(uri, new String[]{"_id", "_data"}, "_data = ?", new String[]{str}, 0);
        if (strY2 != null) {
            strY1 = y1(uri2, new String[]{"_id", "_data"}, str2 + " = ?", new String[]{strY2}, 1);
        } else {
            strY1 = null;
        }
        if (strY1 != null) {
            AbstractC5838l abstractC5838lV0 = V0(strY1);
            if (abstractC5838lV0.n()) {
                AbstractC5838l abstractC5838lV1 = V0(str);
                if (abstractC5838lV1.n() && abstractC5838lV1.q() > abstractC5838lV0.q() && abstractC5838lV1.q() < System.currentTimeMillis()) {
                    strY1 = null;
                }
            } else {
                strY1 = null;
            }
        }
        if (strY1 == null || strY1.isEmpty()) {
            return null;
        }
        return strY1;
    }

    private InputStream q1(String str) {
        try {
            u uVar = (u) V0(str);
            return new ax.J9.a(new BufferedInputStream(d1(uVar, 0L), 32768), (int) uVar.o());
        } catch (C0718i unused) {
            return null;
        }
    }

    public static C5835i r0(Context context, List<AbstractC5838l> list) {
        u uVarA0;
        String strC0 = null;
        Object obj = null;
        for (AbstractC5838l abstractC5838l : list) {
            if (C0740v.H(abstractC5838l)) {
                if ((abstractC5838l instanceof F) && ((F) abstractC5838l).r1()) {
                    try {
                        uVarA0 = u.A0(abstractC5838l.C());
                    } catch (C0718i unused) {
                        uVarA0 = (u) abstractC5838l;
                    }
                } else {
                    uVarA0 = (u) abstractC5838l;
                }
                ax.J1.F fV0 = uVarA0.v0();
                if (fV0.equals(obj)) {
                    continue;
                } else {
                    if (u1(context, fV0, uVarA0)) {
                        if (ax.p338y1.f.P(fV0) && uVarA0.Q0()) {
                            strC0 = uVarA0.C0();
                        }
                        return new C5835i(fV0, strC0);
                    }
                    obj = fV0;
                }
            }
        }
        return null;
    }

    private void r1(AbstractC5837k abstractC5837k, AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        Context contextN = abstractC5837k.n();
        boolean z = !abstractC5838l.S().equals(abstractC5838l2.S());
        boolean zIsDirectory = abstractC5838l.isDirectory();
        boolean zK0 = K0(abstractC5838l);
        if (!z) {
            u uVar = (u) abstractC5838l;
            if (ax.G1.i.D().u0(uVar.v0()) && uVar.P0()) {
                throw new C0713d("/Android/data package folders cannot be renamed from Android 13");
            }
            C5832f.H(contextN, abstractC5838l, abstractC5838l2, cVar, iVar);
            if (ax.E1.P.Y()) {
                C1(abstractC5838l, zIsDirectory);
                D1(V0(abstractC5838l2.C()), true, zK0);
                return;
            }
            return;
        }
        if (ax.E1.P.B0() && m1(abstractC5838l, abstractC5838l2)) {
            C5832f.F(contextN, abstractC5838l, abstractC5838l2, cVar, iVar);
            if (ax.E1.P.Y()) {
                C1(abstractC5838l, zIsDirectory);
                D1(V0(abstractC5838l2.C()), true, zK0);
                return;
            }
            return;
        }
        if (!abstractC5838l.isDirectory()) {
            s1(abstractC5838l, abstractC5838l2, cVar, iVar);
            return;
        }
        u uVar2 = (u) abstractC5838l;
        u uVar3 = (u) abstractC5838l2;
        if (!ax.E1.P.L() || uVar2.F0() == uVar3.F0()) {
            ax.p221pa.c.h().g().b("MOVE ERROR").k().h("location:" + uVar2.P() + "," + uVar3.P()).i();
        }
        throw new C0718i("treespace problem : doesSupportMoveFileToDifferentParent() == false");
    }

    private static synchronized Bitmap s0(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) {
        PdfRenderer.Page pageOpenPage;
        PdfRenderer pdfRenderer;
        PdfRenderer pdfRenderer2 = null;
        try {
            try {
                pdfRenderer = new PdfRenderer(parcelFileDescriptor);
                try {
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_4444);
                    bitmapCreateBitmap.eraseColor(-1);
                    pageOpenPage = pdfRenderer.openPage(0);
                    try {
                        pageOpenPage.render(bitmapCreateBitmap, null, null, 1);
                        C0744z.a(pageOpenPage);
                        C0744z.a(pdfRenderer);
                        return bitmapCreateBitmap;
                    } catch (Exception unused) {
                        if (pdfRenderer != null) {
                            C0744z.a(pageOpenPage);
                            C0744z.a(pdfRenderer);
                        } else {
                            C0744z.a(parcelFileDescriptor);
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        pdfRenderer2 = pdfRenderer;
                        if (pdfRenderer2 != null) {
                            C0744z.a(pageOpenPage);
                            C0744z.a(pdfRenderer2);
                        } else {
                            C0744z.a(parcelFileDescriptor);
                        }
                        throw th;
                    }
                } catch (Exception unused2) {
                    pageOpenPage = null;
                } catch (Throwable th2) {
                    th = th2;
                    pageOpenPage = null;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        } catch (Exception unused3) {
            pageOpenPage = null;
            pdfRenderer = null;
        } catch (Throwable th4) {
            th = th4;
            pageOpenPage = null;
        }
    }

    private void s1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.c(!abstractC5838l.isDirectory());
        try {
            a1(abstractC5838l, abstractC5838l2, cVar, iVar);
            long jO = abstractC5838l.o();
            l1(abstractC5838l);
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (C0710a e2) {
            l1(abstractC5838l2);
            throw e2;
        } catch (C0718i e3) {
            l1(abstractC5838l2);
            throw e3;
        }
    }

    private void t1(u uVar, u uVar2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        if (!uVar.isDirectory()) {
            s1(uVar, uVar2, cVar, iVar);
        } else {
            Z(uVar, uVar2, cVar, iVar);
            d0(uVar);
        }
    }

    public static boolean u1(Context context, ax.J1.F f2, u uVar) {
        if (uVar == null || !(uVar.k0() || uVar.T0() || uVar.V0())) {
            return v1(context, f2, uVar == null ? null : uVar.C0());
        }
        return false;
    }

    private u v0(u uVar) throws C0718i {
        String strC = uVar.C();
        Uri uriD0 = uVar.D0();
        ax.J1.F fV0 = uVar.v0();
        try {
            try {
                Cursor cursorQuery = n().getContentResolver().query(C5832f.e(s(), uriD0, strC), C0727h.g, null, null, null);
                if (cursorQuery == null) {
                    throw new C0718i("query return null");
                }
                if (!cursorQuery.moveToFirst()) {
                    throw new ax.I1.s();
                }
                u uVar2 = new u(this, uriD0, fV0, V.r(strC), cursorQuery);
                C0744z.a(cursorQuery);
                return uVar2;
            } catch (IllegalArgumentException e2) {
                throw new C0718i(e2);
            } catch (Exception e3) {
                throw C0712c.a("getFileInfo : " + e3.getClass().getSimpleName() + ":" + e3.getMessage(), e3);
            }
        } catch (Throwable th) {
            C0744z.a(null);
            throw th;
        }
    }

    public static boolean v1(Context context, ax.J1.F f2, String str) {
        if (!ax.E1.P.F1()) {
            return false;
        }
        if (!ax.p338y1.f.P(f2)) {
            return (!ax.p338y1.f.f0(f2.d()) || C5832f.z(context, f2, null) || ax.G1.i.D().d0(f2)) ? false : true;
        }
        if (str != null) {
            return !C5832f.z(context, f2, str);
        }
        if (ax.G1.i.D().d0(f2)) {
            return false;
        }
        return !C5832f.z(context, f2, null);
    }

    private b0 w0() throws C0718i {
        long j;
        int i;
        ax.p338y1.f fVarR = r();
        AbstractC5838l abstractC5838lV0 = V0(u());
        if (!abstractC5838lV0.n()) {
            throw new ax.I1.s("StorageSpaceIteration root file not found");
        }
        long j2 = 0;
        if (!abstractC5838lV0.isDirectory()) {
            if (fVarR != ax.p338y1.f.s0) {
                ax.p221pa.c.h().g().d("RootPath is File").k().h(fVarR.H() + ":" + abstractC5838lV0.o()).i();
            } else if (abstractC5838lV0.o() == 0) {
                l1(abstractC5838lV0);
                ax.p221pa.c.h().c("Download root path file size 0 deleted").i();
            }
            throw new C0718i("Root path is file");
        }
        List<AbstractC5838l> listG = ax.G1.b.k().g(abstractC5838lV0);
        if (listG == null) {
            listG = g1(abstractC5838lV0);
        }
        C5843q.a aVarQ1 = abstractC5838lV0 instanceof y ? ((y) abstractC5838lV0).q1() : null;
        int iQ = 0;
        if (listG != null) {
            Iterator it = new ArrayList(listG).iterator();
            long jL = 0;
            while (it.hasNext()) {
                File fileY0 = ((u) ((AbstractC5838l) it.next())).y0();
                if (fileY0.exists()) {
                    iQ += C0740v.q(fileY0, aVarQ1);
                    jL += C0740v.l(fileY0, aVarQ1);
                }
            }
            i = iQ;
            j = jL;
        } else {
            j = 0;
            i = 0;
        }
        if (!TextUtils.isEmpty(u()) && !"/".equals(u())) {
            j2 = x0().d;
        }
        return new b0(j, j, j2, i);
    }

    public static boolean w1(Context context, AbstractC5838l abstractC5838l, boolean z) {
        if (!C0740v.H(abstractC5838l)) {
            return false;
        }
        u uVar = (u) abstractC5838l;
        if (uVar.T0() || uVar.k0() || uVar.V0()) {
            return false;
        }
        if (z && ax.G1.i.D().u0(uVar.v0()) && V(context, uVar)) {
            return false;
        }
        return u1(context, uVar.v0(), uVar);
    }

    private b0 x0() throws C0718i {
        String strU = u();
        if (TextUtils.isEmpty(strU)) {
            ax.p221pa.c.h().g().d("LOCAL STORAGE SPACE").h(r().H() + " " + u() + " (" + ax.p338y1.e.A() + ")").i();
            throw new C0718i("no root path");
        }
        if (r() == ax.p338y1.f.q0 && !ax.G1.i.D().g0() && strU.equals("/storage/emulated/0")) {
            long totalSpace = Environment.getRootDirectory().getTotalSpace();
            long totalSpace2 = Environment.getDataDirectory().getTotalSpace();
            return y0(strU, totalSpace > 1000000000000L ? B1(totalSpace2) : B1(totalSpace2 + totalSpace));
        }
        if ((r() != ax.p338y1.f.r0 && r() != ax.p338y1.f.x0 && r() != ax.p338y1.f.z0 && r() != ax.p338y1.f.y0) || !J0()) {
            return y0(strU, 0L);
        }
        if (!ax.p338y1.e.J()) {
            String strU2 = C5832f.u(n(), s(), null);
            if (!TextUtils.isEmpty(strU2)) {
                try {
                    return C5832f.k(n(), this, s(), Uri.parse(strU2));
                } catch (C0718i unused) {
                }
            }
        }
        return y0(strU, 0L);
    }

    private void x1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, Throwable th) throws C0718i {
        boolean zP = false;
        if (W() && (Q0(abstractC5838l) || Q0(abstractC5838l2))) {
            zP = v.Y().P(abstractC5838l.C(), abstractC5838l2.C(), false);
        }
        if (zP) {
            return;
        }
        ((u) abstractC5838l).f1();
        if (th == null) {
            if (!abstractC5838l.n()) {
                throw new ax.I1.s("move source not exist");
            }
            throw new C0718i("File.renameTo failed");
        }
        if (!abstractC5838l.n()) {
            throw new ax.I1.s(th);
        }
        throw new C0718i("Files.move failed", th);
    }

    private b0 y0(String str, long j) throws C0718i {
        return ax.E1.P.z1() ? A0(str, j) : z0(str, j);
    }

    private String y1(Uri uri, String[] strArr, String str, String[] strArr2, int i) throws Throwable {
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        try {
            Cursor cursorQuery = n().getContentResolver().query(uri, strArr, str, strArr2, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(i);
                        cursorQuery.close();
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private b0 z0(String str, long j) {
        File file = new File(str);
        if (j <= 0) {
            j = file.getTotalSpace();
        }
        long j2 = j;
        return new b0(j2, j2 - file.getUsableSpace(), 0);
    }

    public boolean A1(AbstractC5838l abstractC5838l) throws Throwable {
        List<String> listZ1;
        String strM = V.M(abstractC5838l.S(), ".hidden");
        AbstractC5838l abstractC5838lV0 = V0(strM);
        if (!abstractC5838lV0.n() || (listZ1 = z1(abstractC5838lV0)) == null) {
            return false;
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(strM, false));
                try {
                    boolean z = true;
                    for (String str : listZ1) {
                        if (!z) {
                            outputStreamWriter2.write("\n");
                        }
                        if (!str.equals(abstractC5838l.w())) {
                            outputStreamWriter2.write(str);
                            if (z) {
                                z = false;
                            }
                        }
                    }
                    try {
                        outputStreamWriter2.close();
                    } catch (IOException unused) {
                    }
                    return true;
                } catch (IOException e2) {
                    e = e2;
                    outputStreamWriter = outputStreamWriter2;
                    e.printStackTrace();
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused2) {
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (IOException unused3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void B(Context context, ax.J1.F f2) {
        super.B(context, f2);
        if (ax.E1.P.F1()) {
            this.l = new C5832f(context);
        }
        if (f2 == ax.J1.F.e || ax.p338y1.f.d0(f2.d())) {
            this.n = new HashSet<>();
            Iterator<String> it = v.iterator();
            while (it.hasNext()) {
                this.n.add(V.L(ax.J1.F.e.e(), it.next()));
            }
            this.o = new HashSet<>();
            Iterator<String> it2 = x.iterator();
            while (it2.hasNext()) {
                this.o.add(V.L(ax.J1.F.e.e(), it2.next()));
            }
        }
        ax.J1.F f3 = ax.J1.F.f;
        if (f2 == f3 || ax.p338y1.f.d0(f2.d())) {
            if (this.n == null) {
                this.n = new HashSet<>();
            }
            if (!ax.G1.i.D().l0() || TextUtils.isEmpty(f3.e())) {
                return;
            }
            Iterator<String> it3 = w.iterator();
            while (it3.hasNext()) {
                this.n.add(V.L(ax.J1.F.f.e(), it3.next()));
            }
        }
    }

    protected int B0(String str, String str2, String str3) {
        Boolean bool;
        try {
            if (str3.startsWith("image") && (bool = this.h.get(str2)) != null && bool.booleanValue()) {
                return new ax.p323x0.a(str2).g("Orientation", 0);
            }
            return -1;
        } catch (FileNotFoundException | IOException unused) {
            return -1;
        }
    }

    protected InputStream C0(String str, String str2, String str3) throws Throwable {
        InputStream inputStreamL0;
        EnumC0738t enumC0738tE = C0739u.e(str2);
        if (EnumC0738t.AUDIO == enumC0738tE || str3.startsWith("audio")) {
            inputStreamL0 = l0(str);
        } else if (EnumC0738t.VIDEO == enumC0738tE || str3.startsWith("video")) {
            inputStreamL0 = E0(str);
        } else if (EnumC0738t.IMAGE == enumC0738tE || str3.startsWith("image")) {
            inputStreamL0 = n0(str);
        } else if (EnumC0738t.APK == enumC0738tE || str3.equals("application/vnd.android.package-archive")) {
            inputStreamL0 = ax.B1.d.s(n(), str);
        } else {
            inputStreamL0 = (EnumC0738t.PDF == enumC0738tE && ax.E1.P.d1()) ? t0(str) : D0(str);
        }
        return inputStreamL0 == null ? ax.p003a2.b.n() : inputStreamL0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return true;
    }

    public boolean F0() {
        boolean z;
        synchronized (this.m) {
            try {
                z = this.i.size() > 0 && this.j;
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public boolean F1(u uVar, long j) {
        if (j < 0) {
            return false;
        }
        if (uVar.T0()) {
            boolean zI = com.alphainventor.filemanager.shizuku.c.t().I(uVar.C(), j);
            if (zI) {
                uVar.c1(j);
            }
            return zI;
        }
        try {
            uVar.y0().setLastModified(j);
            uVar.k1();
            return uVar.q() / 1000 == j / 1000;
        } catch (Exception e2) {
            ax.p221pa.c.h().g().d("set last modified").m(e2).h("base:" + uVar.v0().j()).i();
            return false;
        }
    }

    public boolean G0() {
        boolean z;
        synchronized (this.m) {
            z = this.i.size() > 0;
        }
        return z;
    }

    public void I1(AbstractC5838l abstractC5838l) {
        ArrayList<ax.X1.d> arrayList = new ArrayList<>();
        arrayList.add(new ax.X1.d(abstractC5838l.C(), abstractC5838l.P().J(), abstractC5838l.L(), abstractC5838l.q(), true, false, false, false));
        arrayList.add(new ax.X1.d(abstractC5838l.C(), abstractC5838l.P().J(), abstractC5838l.L(), abstractC5838l.q(), false, false, false, false));
        H1(arrayList, false, 10000);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5830d
    public void N(String str, long j) throws IOException {
        u uVar = (u) V0(str);
        if (uVar.X0()) {
            try {
                C5832f.K(this, uVar, j);
            } catch (C0718i e2) {
                throw new IOException(e2);
            }
        } else {
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
            try {
                randomAccessFile.setLength(j);
            } finally {
                randomAccessFile.close();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x00b7 A[Catch: Exception -> 0x00b4, TryCatch #1 {Exception -> 0x00b4, blocks: (B:17:0x0096, B:21:0x00a0, B:27:0x00b7, B:29:0x00c5, B:33:0x00d1, B:36:0x00de, B:48:0x00fb, B:54:0x010b, B:59:0x0117, B:63:0x011e, B:40:0x00e8, B:42:0x00ed, B:65:0x012b), top: B:69:0x0096 }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00e8 A[Catch: Exception -> 0x00b4, TryCatch #1 {Exception -> 0x00b4, blocks: (B:17:0x0096, B:21:0x00a0, B:27:0x00b7, B:29:0x00c5, B:33:0x00d1, B:36:0x00de, B:48:0x00fb, B:54:0x010b, B:59:0x0117, B:63:0x011e, B:40:0x00e8, B:42:0x00ed, B:65:0x012b), top: B:69:0x0096 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x00ed A[Catch: Exception -> 0x00b4, TryCatch #1 {Exception -> 0x00b4, blocks: (B:17:0x0096, B:21:0x00a0, B:27:0x00b7, B:29:0x00c5, B:33:0x00d1, B:36:0x00de, B:48:0x00fb, B:54:0x010b, B:59:0x0117, B:63:0x011e, B:40:0x00e8, B:42:0x00ed, B:65:0x012b), top: B:69:0x0096 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:54:0x010b A[Catch: Exception -> 0x00b4, TryCatch #1 {Exception -> 0x00b4, blocks: (B:17:0x0096, B:21:0x00a0, B:27:0x00b7, B:29:0x00c5, B:33:0x00d1, B:36:0x00de, B:48:0x00fb, B:54:0x010b, B:59:0x0117, B:63:0x011e, B:40:0x00e8, B:42:0x00ed, B:65:0x012b), top: B:69:0x0096 }] */
    /* JADX WARN: Code duplicated, block: B:56:0x0112  */
    /* JADX WARN: Code duplicated, block: B:57:0x0114  */
    /* JADX WARN: Code duplicated, block: B:63:0x011e A[Catch: Exception -> 0x00b4, TryCatch #1 {Exception -> 0x00b4, blocks: (B:17:0x0096, B:21:0x00a0, B:27:0x00b7, B:29:0x00c5, B:33:0x00d1, B:36:0x00de, B:48:0x00fb, B:54:0x010b, B:59:0x0117, B:63:0x011e, B:40:0x00e8, B:42:0x00ed, B:65:0x012b), top: B:69:0x0096 }] */
    public List<w> R0(AbstractC5838l abstractC5838l, boolean z, long j, long j2) {
        w wVarC0;
        ax.E1.K k;
        long j3;
        HashSet<String> hashSet;
        boolean z2;
        boolean z3;
        int i;
        String str = null;
        try {
            Context contextN = n();
            String str2 = "_data LIKE '" + abstractC5838l.C() + "/%'";
            long j4 = 0;
            int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
            if (i2 > 0) {
                str2 = str2 + " AND (_size > " + j2 + " OR _size IS NULL)";
            }
            Cursor cursorQuery = contextN.getContentResolver().query(e0.c("external"), y, str2, null, "_data asc");
            if (cursorQuery == null) {
                return null;
            }
            if (cursorQuery.getCount() <= 0) {
                cursorQuery.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ax.J1.F fV0 = ((u) abstractC5838l).v0();
            ax.E1.K k2 = new ax.E1.K();
            long j5 = j / 1000;
            w wVar = null;
            long j6 = 0;
            while (cursorQuery.moveToNext()) {
                try {
                    if (j5 > j4) {
                        long j7 = cursorQuery.getLong(2);
                        if (j7 == 0 || j7 >= j5) {
                            wVarC0 = c0(fV0, cursorQuery, k2);
                            k = k2;
                            j3 = j5;
                            long j8 = wVarC0.e;
                            String str3 = wVarC0.a;
                            if (wVar == null && wVar.a.equalsIgnoreCase(str3)) {
                                if (j8 > j6) {
                                    arrayList.remove(wVar);
                                }
                            }
                            if (str == null && str3.startsWith(str)) {
                                z2 = true;
                            } else {
                                hashSet = this.o;
                                z2 = false;
                                if (hashSet == null && hashSet.contains(str3)) {
                                    str = str3;
                                } else {
                                    str = null;
                                }
                            }
                            z3 = (j > 0 || wVarC0.c >= j) ? z2 : true;
                            if (i2 > 0) {
                                i = i2;
                                if (wVarC0.d < j2) {
                                    z3 = true;
                                }
                            } else {
                                i = i2;
                            }
                            if (!z && wVarC0.b) {
                                z3 = true;
                            }
                            if (!z3) {
                                arrayList.add(wVarC0);
                            }
                            wVar = wVarC0;
                            i2 = i;
                            j4 = 0;
                            j6 = j8;
                            j5 = j3;
                            k2 = k;
                        } else {
                            k = k2;
                            j3 = j5;
                        }
                        k2 = k;
                        j5 = j3;
                        j4 = 0;
                    } else {
                        wVarC0 = c0(fV0, cursorQuery, k2);
                        k = k2;
                        j3 = j5;
                        long j9 = wVarC0.e;
                        String str4 = wVarC0.a;
                        if (wVar == null) {
                        }
                        if (str == null) {
                            hashSet = this.o;
                            z2 = false;
                            if (hashSet == null) {
                                str = null;
                            } else {
                                str = null;
                            }
                        } else {
                            hashSet = this.o;
                            z2 = false;
                            if (hashSet == null) {
                                str = null;
                            } else {
                                str = null;
                            }
                        }
                        if (j > 0) {
                        }
                        if (i2 > 0) {
                            i = i2;
                            if (wVarC0.d < j2) {
                                z3 = true;
                            }
                        } else {
                            i = i2;
                        }
                        if (!z) {
                            z3 = true;
                        }
                        if (!z3) {
                            arrayList.add(wVarC0);
                        }
                        wVar = wVarC0;
                        i2 = i;
                        j4 = 0;
                        j6 = j9;
                        j5 = j3;
                        k2 = k;
                    }
                } catch (Exception unused) {
                    return null;
                }
            }
            cursorQuery.close();
            return arrayList;
        } catch (Exception unused2) {
            return null;
        }
    }

    public boolean U(AbstractC5838l abstractC5838l) throws Throwable {
        String strM = V.M(abstractC5838l.S(), ".hidden");
        AbstractC5838l abstractC5838lV0 = V0(strM);
        boolean zN = abstractC5838lV0.n();
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(strM, true));
                if (zN) {
                    try {
                        outputStreamWriter2.write("\n");
                    } catch (IOException unused) {
                        outputStreamWriter = outputStreamWriter2;
                        if (abstractC5838lV0.isDirectory()) {
                            ax.p221pa.c.h().g().d(".hidden foder exists").h(abstractC5838lV0.C()).i();
                        }
                        if (outputStreamWriter == null) {
                            return false;
                        }
                        try {
                            outputStreamWriter.close();
                            return false;
                        } catch (IOException unused2) {
                            return false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        outputStreamWriter = outputStreamWriter2;
                        if (outputStreamWriter != null) {
                            try {
                                outputStreamWriter.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                }
                outputStreamWriter2.write(abstractC5838l.w());
                try {
                    outputStreamWriter2.close();
                } catch (IOException unused4) {
                }
                return true;
            } catch (IOException unused5) {
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) {
        AbstractC5838l abstractC5838lR;
        u uVar = new u(this, new File(str), (ax.J1.F) null);
        if (ax.E1.P.L() && uVar.Q0()) {
            if (uVar.I0()) {
                try {
                    return v0(uVar);
                } catch (C0718i unused) {
                }
            } else if (ax.J1.E.a(uVar.R())) {
                File fileB = ax.J1.E.b(uVar.R(), str);
                if (fileB != null) {
                    return new u(this, fileB, uVar.v0(), true);
                }
            } else if (com.alphainventor.filemanager.shizuku.c.t().l() && (abstractC5838lR = com.alphainventor.filemanager.shizuku.c.t().r(this, null, str)) != null) {
                return abstractC5838lR;
            }
        }
        return uVar;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        u uVar = (u) V0(str2);
        return C0(uVar.B0(), uVar.v(), uVar.s());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean X0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l != null) {
            return !((u) abstractC5838l).X0() || ax.E1.P.B0();
        }
        ax.p045d2.b.f();
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public int Y0(String str, String str2) {
        return B0(str, str2, ((u) V0(str2)).s());
    }

    public void Z(u uVar, u uVar2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        if (!uVar.isDirectory()) {
            a1(uVar, uVar2, cVar, iVar);
            return;
        }
        if (!uVar2.n()) {
            if (uVar2.C().endsWith("com.alphainventor.filemanager") && "/Android/data/com.alphainventor.filemanager".equals(uVar2.w0())) {
                ax.p338y1.e.q(n());
                uVar2 = (u) V0(uVar2.C());
                if (!uVar2.n()) {
                    h1(uVar2);
                }
            } else {
                h1(uVar2);
            }
        }
        for (AbstractC5838l abstractC5838l : g1(uVar)) {
            Z((u) abstractC5838l, (u) V0(V.M(uVar2.C(), abstractC5838l.w())), cVar, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (abstractC5838l == null) {
            return null;
        }
        if (r() == ax.p338y1.f.v0 && !abstractC5838l.h()) {
            if (EnumC0738t.IMAGE != abstractC5838l.E()) {
                return null;
            }
        }
        return C0740v.Q(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
    }

    public u b0(ax.J1.F f2, w wVar) {
        return new u(this, new File(wVar.a), f2, wVar.b, false, true, true, wVar.d, wVar.c);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    @Override // ax.J1.Q
    public OutputStream c(String str, boolean z) throws IOException {
        u uVar = (u) V0(str);
        if (ax.E1.P.p1() && uVar.T0()) {
            return com.alphainventor.filemanager.shizuku.c.t().v(str, z);
        }
        if (uVar.X0()) {
            try {
                return C5832f.r(this, uVar, z);
            } catch (C0718i e2) {
                throw new IOException(e2);
            }
        }
        if (!W() || !Q0(uVar)) {
            return new c(this, uVar, z);
        }
        try {
            return new c(this, uVar, z);
        } catch (IOException unused) {
            FileOutputStream fileOutputStreamD = v.Y().D(uVar, z);
            if (fileOutputStreamD != null) {
                return fileOutputStreamD;
            }
            throw new FileNotFoundException("failed to open outputstream using root");
        }
    }

    /* JADX WARN: Code duplicated, block: B:124:0x0256  */
    /* JADX WARN: Code duplicated, block: B:127:0x0261 A[Catch: all -> 0x01ba, TryCatch #16 {all -> 0x01ba, blocks: (B:64:0x014f, B:78:0x0189, B:81:0x0193, B:83:0x01a8, B:91:0x01c5, B:93:0x01cd, B:94:0x01d7, B:99:0x01ee, B:100:0x01f4, B:111:0x0222, B:113:0x022a, B:115:0x023b, B:125:0x0257, B:127:0x0261, B:128:0x0268, B:129:0x0269, B:130:0x026f), top: B:179:0x00f9 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x0269 A[Catch: all -> 0x01ba, TryCatch #16 {all -> 0x01ba, blocks: (B:64:0x014f, B:78:0x0189, B:81:0x0193, B:83:0x01a8, B:91:0x01c5, B:93:0x01cd, B:94:0x01d7, B:99:0x01ee, B:100:0x01f4, B:111:0x0222, B:113:0x022a, B:115:0x023b, B:125:0x0257, B:127:0x0261, B:128:0x0268, B:129:0x0269, B:130:0x026f), top: B:179:0x00f9 }] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        if (abstractC5838l.n()) {
            ax.p221pa.c.h().g().d("!! WRITE FILE ALREADY EXISTS !!").k().h("location:" + abstractC5838l.P().H()).i();
            throw new C0718i("WriteFile file already exists");
        }
        AbstractC5838l abstractC5838lV0 = V0(abstractC5838l.C());
        if (abstractC5838lV0.n()) {
            throw new C0714e(abstractC5838lV0.isDirectory() != abstractC5838l.isDirectory());
        }
        u uVar = (u) abstractC5838l;
        OutputStream outputStream = 0;
        BufferedOutputStream bufferedOutputStream2 = null;
        if (ax.E1.P.p1() && uVar.T0()) {
            InputStream inputStreamB = a2.b();
            try {
                if (inputStreamB == null) {
                    throw new C0718i("null inputstream");
                }
                try {
                    BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(com.alphainventor.filemanager.shizuku.c.t().v(abstractC5838l.C(), false), 8192);
                    try {
                        C0744z.e(inputStreamB, bufferedOutputStream3, j, cVar, iVar);
                        bufferedOutputStream3.close();
                        if (l != null && l.longValue() >= 0) {
                            F1((u) abstractC5838l, l.longValue());
                        }
                        if (O0(uVar)) {
                            ax.p045d2.b.e("what case is this? : " + uVar.C());
                            D1(V0(abstractC5838l.C()), false, false);
                        }
                        try {
                            inputStreamB.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    } catch (IOException e3) {
                        e = e3;
                        if (!V.I(abstractC5838l.w())) {
                            throw new ax.I1.m("shizuku write file error 1", e);
                        }
                        throw C0712c.a("shizuku write file error 2", e);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream2 = bufferedOutputStream3;
                        Throwable th3 = th;
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException unused) {
                            }
                        }
                        try {
                            inputStreamB.close();
                            throw th3;
                        } catch (IOException e4) {
                            e4.printStackTrace();
                            throw th3;
                        }
                    }
                } catch (IOException e5) {
                    e = e5;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        } else {
            if (uVar.X0()) {
                C5832f.L(this, abstractC5838l, a2, j, l, z, cVar, iVar);
                if (r() == ax.p338y1.f.r0 && z && !m0(abstractC5838l.C(), uVar.v0()).n()) {
                    AbstractC5838l abstractC5838lM0 = m0(V.M(abstractC5838l.S(), V.b(abstractC5838l.w())), uVar.v0());
                    if (!abstractC5838lM0.n()) {
                        ax.p221pa.c.h().g().b("written file not found 2").h("validfat:" + V.I(abstractC5838lM0.w())).i();
                    }
                }
                if (l != null && l.longValue() >= 0) {
                    F1(uVar, l.longValue());
                }
                if (ax.E1.I.l()) {
                    D1(V0(abstractC5838l.C()), false, false);
                    return;
                }
                return;
            }
            InputStream inputStreamB2 = a2.b();
            try {
                try {
                    if (inputStreamB2 == null) {
                        if (a2 instanceof AbstractC5837k.d) {
                            ax.p221pa.c.h().g().b("NULL INPUTSTREAM").h("location:" + ((AbstractC5837k.d) a2).d().j()).i();
                        } else {
                            ax.p221pa.c.h().g().b("NULL INPUTSTREAM").h(a2.getClass().getName()).i();
                        }
                        throw new C0718i("null inputstream");
                    }
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(uVar.y0());
                        bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 8192);
                        try {
                            C0744z.e(inputStreamB2, bufferedOutputStream, j, cVar, iVar);
                            if (z && M()) {
                                fileOutputStream.getFD().sync();
                            }
                            bufferedOutputStream.close();
                            if (r() == ax.p338y1.f.r0 && z && !m0(abstractC5838l.C(), ((u) abstractC5838l).v0()).n()) {
                                ax.p221pa.c.h().g().b("written file not found 1").i();
                            }
                            if (l != null && l.longValue() >= 0) {
                                F1((u) abstractC5838l, l.longValue());
                            }
                            D1(V0(abstractC5838l.C()), false, false);
                            try {
                                inputStreamB2.close();
                            } catch (IOException e6) {
                                e6.printStackTrace();
                            }
                        } catch (FileNotFoundException e7) {
                            e = e7;
                            if (W() && Q0(abstractC5838l)) {
                                try {
                                    FileOutputStream fileOutputStreamD = v.Y().D(abstractC5838l, false);
                                    if (fileOutputStreamD != null) {
                                        BufferedOutputStream bufferedOutputStream4 = new BufferedOutputStream(fileOutputStreamD, 8192);
                                        try {
                                            C0744z.e(inputStreamB2, bufferedOutputStream4, j, cVar, iVar);
                                            bufferedOutputStream4.close();
                                            if (l != null) {
                                                try {
                                                    if (l.longValue() >= 0) {
                                                        v.Y().U(abstractC5838l.C(), l.longValue());
                                                    }
                                                } catch (IOException unused2) {
                                                    if (V.I(abstractC5838l.w())) {
                                                        throw new ax.I1.m("write file error 1", e);
                                                    }
                                                    throw C0712c.a("write file error 2", e);
                                                }
                                            }
                                            if (c5840n != null) {
                                                v.Y().V(abstractC5838l.C(), c5840n);
                                            }
                                            try {
                                                inputStreamB2.close();
                                                return;
                                            } catch (IOException e8) {
                                                e8.printStackTrace();
                                                return;
                                            }
                                        } catch (IOException unused3) {
                                        } catch (Throwable th5) {
                                            th = th5;
                                            outputStream = bufferedOutputStream4;
                                            if (outputStream != 0) {
                                                try {
                                                    outputStream.close();
                                                } catch (IOException unused4) {
                                                }
                                            }
                                            try {
                                                inputStreamB2.close();
                                                throw th;
                                            } catch (IOException e9) {
                                                e9.printStackTrace();
                                                throw th;
                                            }
                                        }
                                    }
                                } catch (IOException unused5) {
                                }
                            }
                            if (V.I(abstractC5838l.w())) {
                                throw C0712c.a("write file error 2", e);
                            }
                            throw new ax.I1.m("write file error 1", e);
                        } catch (IOException e10) {
                            e = e10;
                            throw C0712c.a("write file error 3", e);
                        }
                    } catch (FileNotFoundException e11) {
                        e = e11;
                        bufferedOutputStream = null;
                    } catch (IOException e12) {
                        e = e12;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    outputStream = "location:";
                }
            } catch (Throwable th7) {
                th = th7;
            }
        }
    }

    public void d0(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.isDirectory()) {
            l1(abstractC5838l);
            return;
        }
        Iterator<AbstractC5838l> it = g1(abstractC5838l).iterator();
        while (it.hasNext()) {
            d0(it.next());
        }
        l1(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        u uVar = (u) abstractC5838l;
        try {
            if (!uVar.i0()) {
                return uVar.T0() ? com.alphainventor.filemanager.shizuku.c.t().s(abstractC5838l.C(), j) : p0(uVar, j);
            }
            try {
                return C5832f.q(n(), abstractC5838l, j);
            } catch (C0718i unused) {
                return p0(uVar, j);
            }
        } catch (FileNotFoundException e2) {
            if (ax.G1.i.D().i0() && v.Y().m(abstractC5838l.C())) {
                FileInputStream fileInputStreamW = ax.p031c2.i.j(n()) ? v.Y().w(abstractC5838l, j) : v.Y().z(abstractC5838l, j);
                if (fileInputStreamW != null) {
                    return fileInputStreamW;
                }
            }
            C0718i c0718iC = C0712c.c("getInputStream", e2, false);
            if (c0718iC != null) {
                throw c0718iC;
            }
            throw new ax.I1.s(e2);
        } catch (IOException e3) {
            throw new C0718i(e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        aVar.N();
        aVar.f0(true, null);
    }

    public void f0(ax.P1.i iVar) {
        synchronized (this.m) {
            try {
                if (this.i.size() == 0) {
                    return;
                }
                ArrayList<ax.X1.d> arrayList = new ArrayList<>(this.i);
                Y();
                int size = arrayList.size();
                ax.J1.G.b(n(), arrayList, iVar);
                ax.J1.G.c(n(), arrayList, true, iVar);
                i0(arrayList, size, iVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        HashSet<String> hashSet;
        List<AbstractC5838l> listT0;
        X(abstractC5838l);
        u uVar = (u) abstractC5838l;
        if (!uVar.i0() || uVar.d1()) {
            return (!ax.E1.P.W0() || !ax.p338y1.f.d0(abstractC5838l.P()) || (hashSet = this.n) == null || !hashSet.contains(abstractC5838l.C()) || (listT0 = T0(abstractC5838l)) == null || listT0.size() == 0) ? S0(abstractC5838l) : listT0;
        }
        try {
            return C5832f.E(this, abstractC5838l);
        } catch (C0718i e2) {
            if (ax.G1.i.D().u0(uVar.v0()) && uVar.P0() && ax.G1.m.b(n()).c(n(), uVar)) {
                try {
                    ax.G1.m.b(n()).e(n());
                    uVar.m1();
                } catch (ax.I1.q unused) {
                }
            }
            e2.printStackTrace();
            return S0(abstractC5838l);
        } catch (RuntimeException e3) {
            ax.p221pa.c.h().b("listchildren local document runtime").m(e3).i();
            return S0(abstractC5838l);
        }
    }

    public void h0() {
        synchronized (this.m) {
            try {
                if (this.i.size() == 0) {
                    return;
                }
                ArrayList<ax.X1.d> arrayList = new ArrayList<>(this.i);
                Y();
                if (ax.E1.P.S() || ax.E1.P.R()) {
                    ax.J1.G.d(n(), arrayList);
                    if (arrayList.size() == 0) {
                        return;
                    }
                }
                g0(arrayList);
                if (arrayList.size() > 0) {
                    new d(arrayList, 500L).h(new Long[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        if (abstractC5838l.n()) {
            return false;
        }
        u uVar = (u) abstractC5838l;
        if (uVar.T0()) {
            return com.alphainventor.filemanager.shizuku.c.t().o(abstractC5838l.C());
        }
        if (uVar.X0()) {
            try {
                return a0((u) abstractC5838l);
            } catch (C0718i unused) {
                return false;
            }
        }
        boolean zMkdir = uVar.y0().mkdir();
        if (zMkdir) {
            R(abstractC5838l);
        }
        return (!zMkdir && W() && Q0(abstractC5838l)) ? v.Y().i(abstractC5838l.C()) : zMkdir;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        if (abstractC5838l.n()) {
            return false;
        }
        u uVar = (u) abstractC5838l;
        if (uVar.T0()) {
            return com.alphainventor.filemanager.shizuku.c.t().p(abstractC5838l.C());
        }
        if (uVar.X0()) {
            try {
                if (C5832f.d(this, abstractC5838l, false) != null) {
                    D1(V0(abstractC5838l.C()), false, false);
                    return true;
                }
            } catch (C0718i unused) {
            }
            return false;
        }
        try {
            boolean zCreateNewFile = ((u) abstractC5838l).y0().createNewFile();
            if (zCreateNewFile) {
                D1(V0(abstractC5838l.C()), false, false);
            }
            return zCreateNewFile;
        } catch (IOException e2) {
            if (W() && Q0(abstractC5838l)) {
                return v.Y().h(abstractC5838l.C());
            }
            e2.printStackTrace();
            return false;
        }
    }

    protected void j0(AbstractC5838l abstractC5838l, List<AbstractC5838l> list, boolean z, String str, boolean z2, ax.P1.h hVar) {
        hVar.l0(C0740v.e(list, str, z2, true), z);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) {
        E1(abstractC5838l, str, z, hVar, cVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        boolean z;
        ax.p045d2.b.a(abstractC5838l2.n());
        u uVar = (u) abstractC5838l;
        if (uVar.T0()) {
            u uVar2 = (u) abstractC5838l2;
            long jO = abstractC5838l.o();
            boolean zIsDirectory = abstractC5838l.isDirectory();
            if (!com.alphainventor.filemanager.shizuku.c.t().H(abstractC5838l.C(), abstractC5838l2.C())) {
                x1(abstractC5838l, abstractC5838l2, null);
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
            boolean zK0 = K0(abstractC5838l);
            if (O0(uVar)) {
                C1(abstractC5838l, zIsDirectory);
            }
            if (O0(uVar2)) {
                D1(V0(abstractC5838l2.C()), false, zK0);
                return;
            }
            return;
        }
        if (J1(abstractC5838l, abstractC5838l2)) {
            r1(this, abstractC5838l, abstractC5838l2, cVar, iVar);
            return;
        }
        long jO2 = abstractC5838l.o();
        boolean zIsDirectory2 = abstractC5838l.isDirectory();
        u uVar3 = (u) abstractC5838l;
        u uVar4 = (u) abstractC5838l2;
        if (ax.E1.P.H0()) {
            try {
                try {
                    Files.move(Paths.get(uVar3.B0(), new String[0]), Paths.get(uVar4.B0(), new String[0]), new CopyOption[0]);
                } catch (IOException | SecurityException e2) {
                    if ((!ax.E1.P.e() || !M0(e2) || (!I0(abstractC5838l2) && !I0(abstractC5838l))) && ((!ax.E1.P.L() || !M0(e2) || (!uVar4.Q0() && !uVar3.Q0())) && (!ax.E1.P.L() || !M0(e2) || ((!H0(abstractC5838l) && !H0(abstractC5838l2)) || !abstractC5838l.isDirectory())))) {
                        e2.printStackTrace();
                        if (!uVar3.y0().renameTo(uVar4.y0())) {
                            x1(abstractC5838l, abstractC5838l2, e2);
                        }
                    }
                    if (abstractC5838l.isDirectory()) {
                        t1(uVar3, uVar4, cVar, iVar);
                        return;
                    } else {
                        s1(abstractC5838l, abstractC5838l2, cVar, iVar);
                        return;
                    }
                }
            } catch (RuntimeException e3) {
                if (uVar3.y0().renameTo(uVar4.y0())) {
                    z = true;
                } else {
                    x1(abstractC5838l, abstractC5838l2, null);
                    z = false;
                }
                ax.p221pa.c.h().g().b("moveFile InvalidPath").m(e3).h("retry success=" + z).i();
            }
        } else if (!uVar3.y0().renameTo(uVar4.y0())) {
            x1(abstractC5838l, abstractC5838l2, null);
        }
        if (iVar != null) {
            iVar.a(jO2, jO2);
        }
        boolean zK1 = K0(abstractC5838l);
        C1(abstractC5838l, zIsDirectory2);
        D1(V0(abstractC5838l2.C()), false, zK1);
    }

    protected InputStream l0(String str) {
        byte[] bArrJ = ax.p045d2.x.j(n(), str, null);
        if (bArrJ != null) {
            return new ByteArrayInputStream(bArrJ);
        }
        String strY1 = y1(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"album_id", "_data"}, "_data = ?", new String[]{str}, 0);
        String strY2 = strY1 != null ? y1(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]{"album_art", "album"}, "_id = ? AND album_art IS NOT NULL", new String[]{strY1}, 0) : null;
        if (strY2 == null || strY2.isEmpty() || !((u) V0(strY2)).n()) {
            return null;
        }
        return q1(strY2);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        boolean zDelete;
        String[] list;
        u uVar = (u) abstractC5838l;
        if (uVar.T0()) {
            boolean zIsDirectory = abstractC5838l.isDirectory();
            if (com.alphainventor.filemanager.shizuku.c.t().q(abstractC5838l.C())) {
                if (O0(uVar)) {
                    C1(abstractC5838l, zIsDirectory);
                    return;
                }
                return;
            } else {
                uVar.f1();
                if (!uVar.n()) {
                    throw new ax.I1.s("Shizuku.delete failed : not exist");
                }
                throw new C0718i("Shizuku.delete failed.");
            }
        }
        if (uVar.X0()) {
            C5832f.g(this, abstractC5838l);
            if (ax.E1.P.z0()) {
                return;
            }
            C1(abstractC5838l, abstractC5838l.isDirectory());
            return;
        }
        boolean zIsDirectory2 = abstractC5838l.isDirectory();
        if (zIsDirectory2 && (list = uVar.y0().list()) != null && list.length > 0) {
            throw new C0719j("File.delete directory that has children");
        }
        Throwable e2 = null;
        if (ax.E1.P.H0()) {
            zDelete = false;
            try {
                Files.delete(Paths.get(uVar.B0(), new String[0]));
                zDelete = true;
            } catch (IOException e3) {
                e2 = e3;
            } catch (RuntimeException e4) {
                e2 = e4;
            }
        } else {
            zDelete = uVar.y0().delete();
        }
        if (ax.E1.I.e() && zDelete) {
            File fileY0 = uVar.y0();
            if (fileY0.exists()) {
                File fileP = C0740v.p(fileY0);
                if (fileY0.renameTo(fileP)) {
                    fileP.delete();
                }
            }
        }
        if (zDelete) {
            C1(abstractC5838l, zIsDirectory2);
            return;
        }
        if (W() && Q0(abstractC5838l) && v.Y().j(abstractC5838l.C())) {
            return;
        }
        uVar.f1();
        if (!uVar.n()) {
            throw new ax.I1.s("File.delete failed : File not exist");
        }
        if (abstractC5838l.P() == ax.p338y1.f.v0 && !abstractC5838l.k()) {
            throw new ax.I1.v("Read only system file.delete failed");
        }
        if (e2 == null) {
            throw new C0718i("File.delete failed dir=" + zIsDirectory2 + ",exists=true");
        }
        throw new C0718i("File.delete failed dir=:" + zIsDirectory2 + ":" + e2.getMessage(), e2);
    }

    protected AbstractC5838l m0(String str, ax.J1.F f2) {
        return new u(this, new File(str), f2);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        if (C0740v.H(abstractC5838l) && C0740v.H(abstractC5838l2)) {
            return u.S0((u) abstractC5838l, (u) abstractC5838l2);
        }
        ax.p045d2.b.f();
        return abstractC5838l.P() == abstractC5838l2.P();
    }

    protected InputStream n0(String str) throws Throwable {
        String strQ0 = q0(str, false);
        if (strQ0 == null) {
            this.h.put(str, Boolean.FALSE);
            return q1(str);
        }
        this.h.put(str, Boolean.TRUE);
        return q1(strQ0);
    }

    void n1(List<AbstractC5838l> list, ax.J1.F f2) {
        Iterator<String> it = t.iterator();
        while (it.hasNext()) {
            File file = new File("/", it.next());
            if (file.exists()) {
                list.add(new u(this, file, f2));
            }
        }
    }

    protected InputStream t0(String str) {
        Bitmap bitmapS0;
        int i = (int) (n().getResources().getDisplayMetrics().density * 128.0f);
        try {
            ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(str), 268435456);
            if (parcelFileDescriptorOpen == null || (bitmapS0 = s0(parcelFileDescriptorOpen, i, i)) == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
            bitmapS0.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            bitmapS0.recycle();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception unused) {
            return null;
        }
    }

    public ParcelFileDescriptor u0(u uVar) throws C0718i {
        try {
            return !uVar.X0() ? ParcelFileDescriptor.open(uVar.y0(), 805306368) : C5832f.s(n(), uVar);
        } catch (IOException e2) {
            throw new C0718i(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        switch (b.a[r().ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return x0();
            case 8:
            case 9:
            case 10:
                return w0();
            default:
                ax.p045d2.b.g("not reachable");
                r.severe("INVALID GETSTORAGESPACE CALL FOR LOCATOIN : " + r());
                throw new C0718i();
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0027: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]) (LINE:40), block:B:11:0x0027 */
    public List<String> z1(AbstractC5838l abstractC5838l) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(d1(abstractC5838l, 0L)));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            try {
                                break;
                            } catch (IOException unused) {
                            }
                        } else if (!line.isEmpty()) {
                            arrayList.add(line);
                        }
                    } catch (C0718i e2) {
                        e = e2;
                        ax.p221pa.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return null;
                    } catch (IOException e3) {
                        e = e3;
                        ax.p221pa.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        return null;
                    }
                }
                bufferedReader.close();
                return arrayList;
            } catch (Throwable th) {
                th = th;
                bufferedReader3 = bufferedReader2;
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (IOException unused3) {
                    }
                }
                throw th;
            }
        } catch (C0718i e4) {
            e = e4;
            bufferedReader = null;
            ax.p221pa.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (IOException e5) {
            e = e5;
            bufferedReader = null;
            ax.p221pa.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            throw th;
        }
    }
}
