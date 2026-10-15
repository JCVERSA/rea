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
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.Z2.C1719c;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1962f;
import ax.p070e3.C1966j;
import ax.p070e3.C1967k;
import ax.p086f3.C2006l;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p086f3.i0;
import ax.p086f3.k0;
import ax.p086f3.n0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.logging.Logger;
import okhttp3.HttpUrl;
import okhttp3.internal.ws.RealWebSocket;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6248x extends AbstractC6230e implements i0 {
    private static final Logger r = Logger.getLogger("FileManager.LocalFileHelper");
    private static int s = 327680;
    private static final List<String> t = Arrays.asList("acct", "cache", "charger", "config", "d", "data", "debug_ramdisk", "dev", "etc", "mnt", "oem", "proc", "property_contexts", "root", "sbin", "sdcard", "storage", "sys", "system", "vendor");
    private static final List<String> u = Arrays.asList("0", "999", "95", "96");
    private static final List<String> v = Arrays.asList("/DCIM/Camera", "/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Images");
    private static final List<String> w = Arrays.asList("/DCIM/Camera");
    private static final List<String> x = Arrays.asList("/Android/media/com.whatsapp/WhatsApp/Media/WhatsApp Voice Notes", "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses");
    private static final String[] y = {"_id", "_data", "date_modified", "_size", "mime_type", "date_added", "datetaken"};
    private C6232g l;
    private HashSet<String> n;
    private HashSet<String> o;
    private Boolean p;
    private HashMap<String, Boolean> h = new HashMap<>();
    private ArrayList<ax.p310t3.f> i = new ArrayList<>();
    private boolean j = false;
    private ConcurrentHashMap<String, ax.p310t3.f> k = new ConcurrentHashMap<>();
    private final Object m = new Object();
    private boolean q = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$a */
    class a implements ax.p182l3.h {
        final /* synthetic */ ax.p182l3.h X;
        final /* synthetic */ List q;

        a(List list, ax.p182l3.h hVar) {
            this.q = list;
            this.X = hVar;
        }

        @Override // ax.p182l3.h
        public void i0(List<AbstractC6239n> list, boolean z) {
            ArrayList arrayList = null;
            for (AbstractC6239n abstractC6239n : list) {
                if (!this.q.contains(abstractC6239n.E())) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(abstractC6239n);
                }
            }
            if (arrayList != null) {
                this.X.i0(arrayList, z);
            }
        }

        @Override // ax.p182l3.h
        public void s() {
        }

        @Override // ax.p182l3.h
        public void x() {
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$b */
    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[ax.U2.f.values().length];
            a = iArr;
            try {
                iArr[ax.U2.f.K0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ax.U2.f.t0.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ax.U2.f.x0.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ax.U2.f.u0.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ax.U2.f.C0.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[ax.U2.f.A0.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[ax.U2.f.B0.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[ax.U2.f.z0.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[ax.U2.f.y0.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[ax.U2.f.w0.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                a[ax.U2.f.v0.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$c */
    static class c extends ax.p086f3.Y {
        C6249y X;
        C6248x Y;
        boolean Z;

        c(C6248x c6248x, C6249y c6249y, boolean z, boolean z2) throws FileNotFoundException {
            super(new FileOutputStream(c6249y.G0(), z));
            this.Y = c6248x;
            this.X = c6249y;
            this.Z = z2;
        }

        @Override // ax.p086f3.Y, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            this.Y.N1(this.X, false, false, false, this.Z);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$d */
    private class d extends ax.p406z3.q<Long, Long, Long> {
        private ArrayList<ax.p310t3.f> h;
        private long i;
        private long j;

        public d(ArrayList<ax.p310t3.f> arrayList, long j) {
            super(ax.z3.q.e.HIGH);
            this.i = System.currentTimeMillis();
            this.j = j;
            this.h = arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
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
            C6248x.this.p0(this.h, 0, null);
            return null;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$e */
    static class e extends ax.p086f3.H {
        long X;

        public e(InputStream inputStream, long j, long j2) {
            super(inputStream);
            this.X = j - j2;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read() throws IOException {
            int i = super.read();
            if (i != -1) {
                this.X--;
            }
            return i;
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        @Override // ax.p086f3.H, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3;
            try {
                i3 = super.read(bArr, i, i2);
            } catch (IOException e) {
                String message = e.getMessage();
                if (message == null || !message.contains("ENOENT")) {
                    C2461b.e("io utils error :" + message);
                    throw e;
                }
                int iAvailable = available();
                if (iAvailable <= 0) {
                    if (iAvailable == 0) {
                        if (this.X == 0) {
                            return -1;
                        }
                        throw e;
                    }
                    C2461b.e("io utils error :" + message);
                    throw e;
                }
                i3 = super.read(bArr, i, available());
            }
            if (i3 != -1) {
                this.X -= (long) i3;
            }
            return i3;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$f */
    public interface f {
        void a();

        void b(boolean z);
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.x$g */
    public static class g extends ax.p406z3.q<Void, Void, Boolean> {
        C6248x h;
        AbstractC6239n i;
        f j;
        boolean k;
        C6240o l;

        public g(C6240o c6240o, AbstractC6239n abstractC6239n, boolean z, f fVar) {
            super(ax.z3.q.e.HIGH);
            this.l = c6240o;
            this.h = (C6248x) c6240o.u();
            this.i = abstractC6239n;
            this.j = fVar;
            this.k = z;
            this.l.V();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Void... voidArr) throws Throwable {
            boolean zN;
            boolean z = true;
            boolean z2 = ax.U2.f.d0(this.i.Q()) || ax.U2.f.e0(this.i.Q());
            if (this.k) {
                zN = z2 ? ax.p038c3.g.h().n(this.i, true) : this.h.a0(this.i);
            } else {
                boolean zN2 = z2 ? ax.p038c3.g.h().n(this.i, false) : false;
                boolean zJ1 = this.h.J1(this.i);
                if (!zN2 && !zJ1) {
                    z = false;
                }
                zN = z;
            }
            if (ax.p006a3.Q.X1() && this.l.G()) {
                this.l.n(null);
            }
            return Boolean.valueOf(zN);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.j != null) {
                if (bool.booleanValue()) {
                    this.j.b(this.k);
                } else {
                    this.j.a();
                }
            }
            this.l.S(true);
        }
    }

    private static synchronized Bitmap A0(ParcelFileDescriptor parcelFileDescriptor, int i, int i2) {
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
                        ax.p086f3.F.a(pageOpenPage);
                        ax.p086f3.F.a(pdfRenderer);
                        return bitmapCreateBitmap;
                    } catch (Exception unused) {
                        if (pdfRenderer != null) {
                            ax.p086f3.F.a(pageOpenPage);
                            ax.p086f3.F.a(pdfRenderer);
                        } else {
                            ax.p086f3.F.a(parcelFileDescriptor);
                        }
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        pdfRenderer2 = pdfRenderer;
                        if (pdfRenderer2 != null) {
                            ax.p086f3.F.a(pageOpenPage);
                            ax.p086f3.F.a(pdfRenderer2);
                        } else {
                            ax.p086f3.F.a(parcelFileDescriptor);
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

    private void A1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.c(!abstractC6239n.isDirectory());
        try {
            m1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            long jP = abstractC6239n.p();
            q1(abstractC6239n);
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (C1957a e2) {
            q1(abstractC6239n2);
            throw e2;
        } catch (C1966j e3) {
            q1(abstractC6239n2);
            throw e3;
        }
    }

    private void B1(C6249y c6249y, C6249y c6249y2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        if (!c6249y.isDirectory()) {
            A1(c6249y, c6249y2, interfaceC2462c, iVar);
        } else {
            f0(c6249y, c6249y2, interfaceC2462c, iVar);
            k0(c6249y);
        }
    }

    private boolean C1(AbstractC6239n abstractC6239n) {
        return !ax.p006a3.J.y(p()) && ax.p086f3.B.M(abstractC6239n);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [android.content.ContentResolver] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.lang.AutoCloseable] */
    private C6249y D0(C6249y c6249y) throws Throwable {
        String strE = c6249y.E();
        Uri uriO0 = c6249y.O0();
        ax.p086f3.K kC0 = c6249y.C0();
        ?? E = C6232g.e(v(), uriO0, strE);
        try {
            try {
                Cursor cursorQuery = p().getContentResolver().query(E, C2006l.g, null, null, null);
                try {
                    if (cursorQuery == null) {
                        throw new C1966j("query return null");
                    }
                    if (!cursorQuery.moveToFirst()) {
                        throw new ax.p070e3.t();
                    }
                    C6249y c6249y2 = new C6249y(this, uriO0, kC0, d0.r(strE), cursorQuery);
                    ax.p086f3.F.a(cursorQuery);
                    return c6249y2;
                } catch (IllegalArgumentException e2) {
                    e = e2;
                    throw new C1966j(e);
                } catch (Exception e3) {
                    e = e3;
                    Exception exc = e;
                    throw C1960d.a("getFileInfo : " + exc.getClass().getSimpleName() + ":" + exc.getMessage(), exc);
                }
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                ax.p086f3.F.a(E);
                throw th2;
            }
        } catch (IllegalArgumentException e4) {
            e = e4;
        } catch (Exception e5) {
            e = e5;
        } catch (Throwable th3) {
            th = th3;
            E = 0;
            Throwable th4 = th;
            ax.p086f3.F.a(E);
            throw th4;
        }
    }

    public static boolean D1(Context context, ax.p086f3.K k, C6249y c6249y) {
        if (c6249y == null || !(c6249y.p0() || c6249y.f1() || c6249y.h1())) {
            return E1(context, k, c6249y == null ? null : c6249y.N0());
        }
        return false;
    }

    private k0 E0(String str, long j) throws C1966j {
        try {
            ax.p006a3.M mO = ax.p006a3.v.o(str);
            if (j <= 0) {
                j = mO.b * mO.a;
            }
            long j2 = j;
            StorageManager storageManager = (StorageManager) p().getSystemService("storage");
            return new k0(j2, j2 - storageManager.getAllocatableBytes(storageManager.getUuidForPath(new File(str))), 0);
        } catch (Exception e2) {
            throw new C1966j(e2);
        }
    }

    public static boolean E1(Context context, ax.p086f3.K k, String str) {
        if (!ax.p006a3.Q.V1()) {
            return false;
        }
        if (!ax.U2.f.P(k)) {
            return (!ax.U2.f.g0(k.d()) || ax.p038c3.j.F().l0(k) || C6232g.C(context, k, null) || ax.p038c3.j.F().k0(k)) ? false : true;
        }
        if (str != null) {
            return !C6232g.C(context, k, str);
        }
        if (ax.p038c3.j.F().k0(k)) {
            return false;
        }
        return !C6232g.C(context, k, null);
    }

    private k0 F0() throws C1966j {
        long j;
        int i;
        ax.U2.f fVarU = u();
        AbstractC6239n abstractC6239nA1 = a1(x());
        if (!abstractC6239nA1.o()) {
            throw new ax.p070e3.t("StorageSpaceIteration root file not found");
        }
        long j2 = 0;
        if (!abstractC6239nA1.isDirectory()) {
            if (fVarU != ax.U2.f.v0) {
                ax.p302sb.c.h().g().d("RootPath is File").k().h(fVarU.H() + ":" + abstractC6239nA1.p()).i();
            } else if (abstractC6239nA1.p() == 0) {
                q1(abstractC6239nA1);
                ax.p302sb.c.h().c("Download root path file size 0 deleted").i();
            }
            throw new C1966j("Root path is file");
        }
        List<AbstractC6239n> listG = ax.p038c3.b.k().g(abstractC6239nA1);
        if (listG == null) {
            listG = J(abstractC6239nA1);
        }
        C6245u.a aVarE1 = abstractC6239nA1 instanceof C ? ((C) abstractC6239nA1).E1() : null;
        int iV = 0;
        if (listG != null) {
            ArrayList arrayList = new ArrayList(listG);
            int size = arrayList.size();
            long jP = 0;
            int i2 = 0;
            while (i2 < size) {
                Object obj = arrayList.get(i2);
                i2++;
                File fileG0 = ((C6249y) ((AbstractC6239n) obj)).G0();
                if (fileG0.exists()) {
                    iV += ax.p086f3.B.v(fileG0, aVarE1);
                    jP += ax.p086f3.B.p(fileG0, aVarE1);
                }
            }
            i = iV;
            j = jP;
        } else {
            j = 0;
            i = 0;
        }
        if (!TextUtils.isEmpty(x()) && !"/".equals(x())) {
            j2 = G0().d;
        }
        return new k0(j, j, j2, i);
    }

    public static boolean F1(Context context, AbstractC6239n abstractC6239n, boolean z) {
        if (!ax.p086f3.B.P(abstractC6239n)) {
            return false;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.f1() || c6249y.p0() || c6249y.h1()) {
            return false;
        }
        if (z && ax.p038c3.j.F().F0(c6249y.C0()) && b0(context, c6249y)) {
            return false;
        }
        return D1(context, c6249y.C0(), c6249y);
    }

    private k0 G0() throws C1966j {
        String strX = x();
        if (TextUtils.isEmpty(strX)) {
            ax.p302sb.c.h().g().d("LOCAL STORAGE SPACE").h(u().H() + " " + x() + " (" + ax.U2.e.A() + ")").i();
            throw new C1966j("no root path");
        }
        if (u() == ax.U2.f.t0 && !ax.p038c3.j.F().p0() && strX.equals("/storage/emulated/0")) {
            long totalSpace = Environment.getRootDirectory().getTotalSpace();
            long totalSpace2 = Environment.getDataDirectory().getTotalSpace();
            return H0(strX, totalSpace > 1000000000000L ? L1(totalSpace2) : L1(totalSpace2 + totalSpace));
        }
        if ((u() != ax.U2.f.u0 && u() != ax.U2.f.A0 && u() != ax.U2.f.C0 && u() != ax.U2.f.B0) || !S0()) {
            return H0(strX, 0L);
        }
        if (!ax.U2.e.J()) {
            String strX2 = C6232g.x(p(), v(), null);
            if (!TextUtils.isEmpty(strX2)) {
                try {
                    return C6232g.k(p(), this, v(), Uri.parse(strX2));
                } catch (C1966j unused) {
                }
            }
        }
        return H0(strX, 0L);
    }

    private void G1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, Throwable th) throws C1966j {
        boolean zP = false;
        if (c0() && (Y0(abstractC6239n) || Y0(abstractC6239n2))) {
            zP = C6250z.Y().P(abstractC6239n.E(), abstractC6239n2.E(), false);
        }
        if (zP) {
            return;
        }
        ((C6249y) abstractC6239n).s1();
        if (th == null) {
            if (!abstractC6239n.o()) {
                throw new ax.p070e3.t("move source not exist");
            }
            throw new C1966j("File.renameTo failed");
        }
        if (!abstractC6239n.o()) {
            throw new ax.p070e3.t(th);
        }
        throw new C1966j("Files.move failed", th);
    }

    private k0 H0(String str, long j) throws C1966j {
        return ax.p006a3.Q.P1() ? J0(str, j) : I0(str, j);
    }

    private String H1(Uri uri, String[] strArr, String str, String[] strArr2, int i) throws Throwable {
        Throwable th;
        Cursor cursor = null;
        if (uri == null) {
            return null;
        }
        try {
            Cursor cursorQuery = p().getContentResolver().query(uri, strArr, str, strArr2, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(i);
                        cursorQuery.close();
                        return string;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = cursorQuery;
                    if (cursor == null) {
                        throw th;
                    }
                    cursor.close();
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    private k0 I0(String str, long j) {
        File file = new File(str);
        if (j <= 0) {
            j = file.getTotalSpace();
        }
        long j2 = j;
        return new k0(j2, j2 - file.getUsableSpace(), 0);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00b0  */
    private k0 J0(String str, long j) throws C1966j {
        try {
            ax.p006a3.M mO = ax.p006a3.v.o(str);
            long j2 = j > 0 ? j : mO.b * mO.a;
            long j3 = mO.c;
            long j4 = mO.a;
            Long.signum(j3);
            long j5 = j2 - (j3 * j4);
            if (ax.p006a3.J.G() && u() == ax.U2.f.t0 && j2 > 1000000000000L && (j5 * 100) / j2 >= 95) {
                long j6 = j2 - (mO.d * mO.a);
                if (j6 > 0 && (j6 * 100) / j2 < 95) {
                    return new k0(j2, j6, 0);
                }
                if (ax.p006a3.Q.T0()) {
                    k0 k0VarE0 = E0(str, j);
                    long j7 = k0VarE0.b;
                    if (j7 != 0) {
                        long j8 = k0VarE0.a;
                        if (j8 > 0 && (j8 * 100) / j7 < 95) {
                            return k0VarE0;
                        }
                    }
                }
                String strQ = d0.Q(str, "DCIM");
                if (new File(strQ).exists()) {
                    try {
                        k0 k0VarI0 = I0(strQ, j);
                        long j9 = k0VarI0.b;
                        if (j9 != 0) {
                            long j10 = k0VarI0.a;
                            if (j10 > 0 && (j10 * 100) / j9 < 95) {
                                return k0VarI0;
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        throw new C1966j(e);
                    }
                }
            }
            return new k0(j2, j5, 0);
        } catch (Exception e3) {
            e = e3;
        }
    }

    private static long L1(long j) {
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
                j4 *= ax.p006a3.Q.b2() ? 1000L : RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
                j3 = 1;
            }
        }
        return j2 > j ? j2 : j;
    }

    private InputStream M0(String str) {
        if (new File(str).length() == 0) {
            return ax.p358w3.b.n();
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return (options.outWidth <= 0 || options.outHeight <= 0) ? ax.p358w3.b.n() : y1(str);
    }

    private void M1(AbstractC6239n abstractC6239n, boolean z, boolean z2) {
        if (l0(abstractC6239n, z)) {
            return;
        }
        Z(abstractC6239n, true, z, false, false, z2, false);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0053  */
    /* JADX WARN: Code duplicated, block: B:35:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private InputStream N0(String str) throws Throwable {
        ParcelFileDescriptor parcelFileDescriptorF0;
        Bitmap bitmapG;
        String strY0 = y0(str, true);
        if (strY0 != null) {
            return y1(strY0);
        }
        C6249y c6249y = (C6249y) a1(str);
        ParcelFileDescriptor parcelFileDescriptor = null;
        if (c6249y.l0()) {
            try {
                parcelFileDescriptorF0 = c6249y.f0(true);
                try {
                    try {
                        bitmapG = ax.p406z3.B.g(p(), null, parcelFileDescriptorF0.getFileDescriptor(), null, 512);
                    } catch (C1966j e2) {
                        e = e2;
                        e.printStackTrace();
                        bitmapG = ax.p406z3.B.g(p(), str, null, null, 512);
                        if (parcelFileDescriptorF0 != null) {
                        }
                        if (bitmapG == null) {
                            bitmapG = ax.p374x3.a.b();
                        }
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
                        bitmapG.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                    }
                } catch (Throwable th) {
                    th = th;
                    parcelFileDescriptor = parcelFileDescriptorF0;
                    if (parcelFileDescriptor != null) {
                        try {
                            parcelFileDescriptor.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (C1966j e3) {
                e = e3;
                parcelFileDescriptorF0 = null;
            } catch (Throwable th2) {
                th = th2;
                if (parcelFileDescriptor != null) {
                    parcelFileDescriptor.close();
                }
                throw th;
            }
            try {
                parcelFileDescriptorF0.close();
            } catch (IOException unused2) {
            }
        } else {
            bitmapG = ax.p406z3.B.g(p(), str, null, null, 512);
        }
        if (bitmapG == null) {
            bitmapG = ax.p374x3.a.b();
        }
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(32768);
        bitmapG.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream2);
        return new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void N1(AbstractC6239n abstractC6239n, boolean z, boolean z2, boolean z3, boolean z4) {
        if (l0(abstractC6239n, abstractC6239n.isDirectory())) {
            return;
        }
        if (abstractC6239n.isDirectory()) {
            Y(abstractC6239n, z, z2, z3);
        } else {
            Z(abstractC6239n, false, false, z, z2, z3, z4);
        }
    }

    private void O1(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        C6248x c6248x;
        C6248x c6248x2;
        if (interfaceC2462c == null || !interfaceC2462c.isCancelled()) {
            AbstractC6239n abstractC6239n2 = abstractC6239n;
            String str2 = str;
            List<String> listD = ax.p086f3.N.d(p(), abstractC6239n2, str2, z2);
            ArrayList arrayList = new ArrayList();
            long jCurrentTimeMillis = System.currentTimeMillis();
            String str3 = HttpUrl.FRAGMENT_ENCODE_SET;
            for (String str4 : listD) {
                String strR = d0.r(str4);
                if (str3.equals(strR)) {
                    c6248x2 = this;
                } else {
                    long jCurrentTimeMillis2 = System.currentTimeMillis();
                    if (arrayList.size() <= 0 || jCurrentTimeMillis2 - jCurrentTimeMillis <= 100) {
                        c6248x2 = this;
                    } else {
                        q0(abstractC6239n2, arrayList, false, str2, z, hVar);
                        c6248x2 = this;
                        arrayList.clear();
                        jCurrentTimeMillis = jCurrentTimeMillis2;
                    }
                    if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                        return;
                    } else {
                        str3 = strR;
                    }
                }
                AbstractC6239n abstractC6239nA1 = c6248x2.a1(str4);
                if (abstractC6239nA1.o() && !J.k2(abstractC6239nA1)) {
                    arrayList.add(abstractC6239nA1);
                }
                abstractC6239n2 = abstractC6239n;
                str2 = str;
            }
            if (arrayList.size() > 0) {
                c6248x = this;
                c6248x.q0(abstractC6239n, arrayList, true, str, z, hVar);
                arrayList.clear();
            } else {
                c6248x = this;
            }
            c6248x.o(abstractC6239n, str, z, z2, c6248x.new a(listD, hVar), interfaceC2462c);
        }
    }

    public static void P1(Context context, ArrayList<ax.p310t3.f> arrayList, boolean z, int i) {
        Intent intent = new Intent(context, (Class<?>) ScanService.class);
        intent.putExtra("PENDING_SCAN_ARRAY", (Parcelable[]) arrayList.toArray(new ax.p310t3.f[0]));
        intent.putExtra("HAS_FOLLOWING_LIST", z);
        try {
            ax.p406z3.B.f0(context, intent, true, true);
        } catch (IllegalStateException e2) {
            ax.p302sb.c.i(context).g().b("START SCAN SERVICE FOREGROUND").m(e2).i();
        }
    }

    static boolean Q0(AbstractC6239n abstractC6239n) {
        if (!ax.p006a3.Q.L() || !ax.p086f3.B.P(abstractC6239n)) {
            return false;
        }
        try {
            C6249y c6249y = (C6249y) abstractC6239n;
            if (c6249y.C0() != ax.p086f3.K.e && c6249y.C0() != ax.p086f3.K.f) {
                return false;
            }
            return d0.J("/Android/data", c6249y.D0(), true);
        } catch (IllegalArgumentException e2) {
            ax.p302sb.c.h().g().b("SUBDIRECTORY ERROR").m(e2).i();
            return false;
        }
    }

    private void Q1(ArrayList<ax.p310t3.f> arrayList, boolean z, int i) {
        P1(p(), arrayList, z, i);
    }

    static boolean R0(AbstractC6239n abstractC6239n) {
        if (!ax.p086f3.B.P(abstractC6239n)) {
            return false;
        }
        try {
            C6249y c6249y = (C6249y) abstractC6239n;
            if (c6249y.C0() != ax.p086f3.K.e && (c6249y.C0() != ax.p086f3.K.f || !ax.p006a3.Q.L())) {
                return false;
            }
            return d0.J("/Android/obb", c6249y.D0(), true);
        } catch (IllegalArgumentException e2) {
            ax.p302sb.c.h().g().b("SUBDIRECTORY ERROR").m(e2).i();
            return false;
        }
    }

    private boolean S0() {
        if (!ax.p006a3.Q.V1()) {
            return false;
        }
        return !ax.p038c3.j.F().k0(v());
    }

    private boolean S1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        C6249y c6249y = (C6249y) abstractC6239n;
        C6249y c6249y2 = (C6249y) abstractC6239n2;
        boolean zJ0 = c6249y.j0();
        if (!zJ0 && (c6249y.C0() != ax.p086f3.K.f || !c6249y.U0())) {
            return false;
        }
        if (c6249y.C0() == ax.p086f3.K.e) {
            return zJ0 && c6249y2.j0();
        }
        return c6249y.C0() != ax.p086f3.K.f || zJ0 || c6249y2.j0();
    }

    private boolean T0(String str) {
        return str == null || "vnd.android.document/directory".equals(str);
    }

    private boolean T1() {
        return this.q && u() == ax.U2.f.y0 && ax.p038c3.j.F().q();
    }

    private boolean U0(Exception exc) {
        if ("AccessDeniedException".equals(exc.getClass().getSimpleName()) || "DirectoryNotEmptyException".equals(exc.getClass().getSimpleName())) {
            return true;
        }
        return exc.getMessage() != null && exc.getMessage().endsWith("Operation not permitted");
    }

    private boolean V0() {
        if (this.p == null) {
            this.p = Boolean.valueOf(ax.U2.f.q0(u()));
        }
        return this.p.booleanValue();
    }

    private void W(C6249y c6249y, ax.p086f3.K k, File[] fileArr, List<AbstractC6239n> list, boolean z) {
        boolean z2;
        ax.p086f3.K k2;
        boolean zW0 = c6249y.W0();
        int length = fileArr.length;
        int i = 0;
        while (i < length) {
            File file = fileArr[i];
            String name = file.getName();
            if (d0.C(name)) {
                k2 = k;
                z2 = z;
            } else {
                if (u() == ax.U2.f.y0 && file.isDirectory()) {
                    z2 = z;
                    list.add(new C6249y(this, c6249y.G0(), file, (ax.p086f3.K) null, z2));
                } else {
                    z2 = z;
                    if (zW0 && ax.p086f3.I.a(c6249y.S()) && ("data".equals(name) || "obb".equals(name))) {
                        list.add(a1(file.getAbsolutePath()));
                    } else {
                        k2 = k;
                        list.add(new C6249y(this, c6249y.G0(), file, k2, z2));
                    }
                }
                k2 = k;
            }
            i++;
            k = k2;
            z = z2;
        }
    }

    private boolean W0(C6249y c6249y) {
        return (!ax.U2.f.i0(c6249y.Q()) || c6249y.c1() || J.k2(c6249y) || c6249y.E().endsWith(".tmp")) ? false : true;
    }

    private void X(AbstractC6239n abstractC6239n, boolean z, boolean z2) {
        if (l0(abstractC6239n, true)) {
            return;
        }
        Z(abstractC6239n, false, true, false, false, z, z2);
    }

    public static boolean X0(Context context, ax.p086f3.K k, Uri uri) {
        String string = uri.toString();
        if (string == null) {
            ax.p302sb.c.h().d("DOCUMENT TREE URI ERROR 1").h(uri.toString()).i();
            return false;
        }
        if (string.lastIndexOf("/") < 0) {
            ax.p302sb.c.h().d("DOCUMENT TREE URI ERROR 2").h(uri.toString()).i();
            return false;
        }
        String strSubstring = string.substring(string.lastIndexOf("/"));
        String strH = d0.h(k.e());
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        sb.append(strH);
        return strSubstring.startsWith(sb.toString());
    }

    private void Y(AbstractC6239n abstractC6239n, boolean z, boolean z2, boolean z3) {
        Stack stack = new Stack();
        stack.push(abstractC6239n);
        while (stack.size() > 0) {
            AbstractC6239n abstractC6239n2 = (AbstractC6239n) stack.pop();
            C6248x c6248x = this;
            c6248x.Z(abstractC6239n2, false, true, z, z2, z3, false);
            try {
                for (AbstractC6239n abstractC6239n3 : c6248x.J(abstractC6239n2)) {
                    if (abstractC6239n3.isDirectory()) {
                        stack.push(abstractC6239n3);
                    } else {
                        c6248x.Z(abstractC6239n3, false, false, z, z2, z3, false);
                    }
                    c6248x = this;
                }
            } catch (C1966j unused) {
            }
        }
    }

    private boolean Y0(AbstractC6239n abstractC6239n) {
        C6249y c6249y = (C6249y) abstractC6239n;
        ax.U2.f fVarU = u();
        ax.U2.f fVar = ax.U2.f.y0;
        return fVarU == fVar || c6249y.C0().d() == fVar;
    }

    private void Z(AbstractC6239n abstractC6239n, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        HashSet<String> hashSet;
        ax.p310t3.f fVar;
        synchronized (this.m) {
            try {
                String strE = abstractC6239n.E();
                if (this.k.containsKey(strE) && (fVar = this.k.get(strE)) != null) {
                    this.i.remove(fVar);
                    this.k.remove(strE);
                    if (!fVar.q && z && fVar.n0) {
                        return;
                    }
                }
                if (z2 && z) {
                    Iterator<ax.p310t3.f> it = this.i.iterator();
                    while (it.hasNext()) {
                        ax.p310t3.f next = it.next();
                        if (d0.J(strE, next.X, true)) {
                            it.remove();
                            this.k.remove(next.X);
                        }
                    }
                }
                ax.p310t3.f fVar2 = new ax.p310t3.f(strE, abstractC6239n.Q().J(), abstractC6239n.M(), abstractC6239n.q(), z, z2, z3, z4, z5, z6);
                this.k.put(strE, fVar2);
                this.i.add(fVar2);
                if (ax.p006a3.Q.X1() && (hashSet = this.n) != null && hashSet.contains(abstractC6239n.U())) {
                    fVar2.s0 = true;
                    this.j = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static boolean b0(Context context, C6249y c6249y) {
        if (c6249y.d1()) {
            if (!c6249y.k() && !c6249y.f1()) {
                List<AbstractC6239n> listP = C1719c.q().p();
                if (listP != null && listP.size() == 1) {
                    AbstractC6239n abstractC6239n = listP.get(0);
                    if (abstractC6239n.isDirectory() && abstractC6239n.B().contains(".")) {
                        if (C6232g.C(context, c6249y.C0(), d0.Q(c6249y.N0(), abstractC6239n.B()))) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean c0() {
        return ax.p038c3.j.F().q();
    }

    private void d0(AbstractC6239n abstractC6239n) throws C1966j {
        if (!abstractC6239n.o()) {
            ax.p302sb.c.h().g().d("LLISTCH0").k().h("Location = " + u().H()).i();
            throw new ax.p070e3.t();
        }
        if (abstractC6239n.isDirectory()) {
            return;
        }
        ax.p302sb.c.h().d("LLISTCH1").h(abstractC6239n.isDirectory() + ":" + abstractC6239n.o()).i();
        throw new C1966j("file is not directory");
    }

    private void e0() {
        this.i.clear();
        this.k.clear();
        this.j = false;
    }

    private boolean g0(C6249y c6249y) throws Throwable {
        Uri uriD = C6232g.d(this, c6249y, true);
        if (uriD == null) {
            return false;
        }
        if (".$recycle_bin$".equals(c6249y.B())) {
            J.A = uriD;
        }
        return true;
    }

    private A j0(ax.p086f3.K k, Cursor cursor, ax.p006a3.L l) {
        boolean z = true;
        String string = cursor.getString(1);
        long jLastModified = cursor.getLong(2) * 1000;
        long j = cursor.getLong(3);
        boolean zT0 = T0(cursor.getString(4));
        if (!cursor.isNull(2) && (zT0 || !cursor.isNull(3))) {
            z = false;
        }
        long j2 = cursor.getLong(5);
        if (zT0 || jLastModified == 0 || z) {
            try {
                ax.p006a3.v.l(string, l);
                jLastModified = l.c;
                zT0 = l.b;
                if (!zT0) {
                    j = l.a;
                }
            } catch (IOException unused) {
                File file = new File(string);
                jLastModified = file.lastModified();
                String strF = d0.f(string);
                if (!TextUtils.isEmpty(strF) && ax.p086f3.A.g(strF) != null) {
                    zT0 = file.isDirectory();
                }
            }
        }
        long j3 = jLastModified;
        boolean z2 = zT0;
        Long lValueOf = null;
        if (!cursor.isNull(6)) {
            try {
                lValueOf = Long.valueOf(cursor.getLong(6));
            } catch (Exception unused2) {
            }
        }
        return new A(string, z2, j3, lValueOf, j2, j);
    }

    private boolean l0(AbstractC6239n abstractC6239n, boolean z) {
        if (!V0() || !ax.p086f3.B.P(abstractC6239n)) {
            return true;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.C0().d() == ax.U2.f.y0) {
            return true;
        }
        if (c6249y.D0().startsWith("/Android") && (Q0(abstractC6239n) || R0(abstractC6239n))) {
            return true;
        }
        if ((!ax.p006a3.Q.B0() && !z && !ax.p086f3.A.L(abstractC6239n.y())) || "tmp".equals(abstractC6239n.y())) {
            return true;
        }
        String strD0 = c6249y.D0();
        if (strD0.contains(".$recycle_bin$")) {
            return true;
        }
        if (ax.p006a3.Q.L()) {
            return d0.H("/.localcache", strD0) || d0.H("/Android/media/com.alphainventor.filemanager/.localcache", strD0);
        }
        return !ax.p006a3.Q.L() && strD0.contains("/localcache");
    }

    private void n0(ArrayList<ax.p310t3.f> arrayList) {
        ArrayList<ax.p310t3.f> arrayList2 = new ArrayList<>();
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
                Iterator<ax.p310t3.f> it = arrayList.iterator();
                while (it.hasNext()) {
                    ax.p310t3.f next = it.next();
                    if (next != null) {
                        arrayList2.add(next);
                        iA += next.a();
                    }
                    it.remove();
                    if (iA >= s) {
                        Q1(arrayList2, it.hasNext(), iA);
                        return;
                    }
                }
                if (arrayList2.size() > 0) {
                    Q1(arrayList2, false, iA);
                    return;
                }
            } catch (RuntimeException e2) {
                if ((e2.getCause() instanceof TransactionTooLargeException) && s == 327680) {
                    s = 163840;
                    z = true;
                } else {
                    ax.U2.d.c("executePendingScan", e2);
                }
            }
        } while (z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0(ArrayList<ax.p310t3.f> arrayList, int i, ax.p182l3.i iVar) {
        while (arrayList.size() > 0) {
            if (iVar != null) {
                iVar.a(i - arrayList.size(), i);
            }
            n0(arrayList);
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

    public static AbstractC6239n r0(List<AbstractC6239n> list) {
        for (AbstractC6239n abstractC6239n : list) {
            if (".hidden".equals(abstractC6239n.B())) {
                return abstractC6239n;
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:107:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:22:0x006c  */
    /* JADX WARN: Code duplicated, block: B:33:0x0093  */
    /* JADX WARN: Code duplicated, block: B:35:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:39:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:59:0x010a  */
    /* JADX WARN: Code duplicated, block: B:61:0x0110  */
    /* JADX WARN: Code duplicated, block: B:63:0x0116  */
    /* JADX WARN: Code duplicated, block: B:73:0x013c  */
    /* JADX WARN: Code duplicated, block: B:77:0x014e  */
    /* JADX WARN: Code duplicated, block: B:78:0x0152  */
    /* JADX WARN: Code duplicated, block: B:80:0x015e  */
    /* JADX WARN: Code duplicated, block: B:81:0x0162  */
    /* JADX WARN: Code duplicated, block: B:83:0x016e  */
    /* JADX WARN: Code duplicated, block: B:86:0x017f  */
    /* JADX WARN: Code duplicated, block: B:97:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:99:0x01ad  */
    private List<AbstractC6239n> s1(AbstractC6239n abstractC6239n) throws C1966j {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        C6248x c6248x;
        List<C6249y> listE;
        File fileC;
        ArrayList<C6249y> arrayListO;
        C6249y c6249y = (C6249y) abstractC6239n;
        ax.p086f3.K kC0 = c6249y.C0();
        ArrayList arrayList = new ArrayList();
        if (!c6249y.f1()) {
            if (!T1()) {
                try {
                    fileArrListFiles = c6249y.G0().listFiles();
                } catch (ArrayStoreException e2) {
                    ax.p302sb.c.h().g().d("LLISTCH2 ArrayStoreException").i();
                    throw new C1966j(e2);
                } catch (OutOfMemoryError e3) {
                    ax.p302sb.c.h().g().d("LLISTCH2 OutOfMemoryError").i();
                    throw new C1966j(e3);
                }
            }
            fileArrListFiles2 = fileArrListFiles;
            if (fileArrListFiles2 != null) {
                W(c6249y, kC0, fileArrListFiles2, arrayList, c6249y.h1());
            }
            if ((fileArrListFiles2 != null || arrayList.size() == 0) && ax.U2.f.P(kC0) && c6249y.c1()) {
                if (ax.p086f3.I.b(c6249y.S())) {
                    fileC = ax.p086f3.I.c(c6249y.S(), c6249y.E());
                    if (fileC != null && (fileArrListFiles2 = fileC.listFiles()) != null) {
                        W(c6249y, kC0, fileArrListFiles2, arrayList, true);
                    }
                } else if (ax.p086f3.I.a(c6249y.C0()) || !c6249y.d1()) {
                    c6248x = this;
                    if (com.alphainventor.filemanager.shizuku.c.t().l() && (listE = com.alphainventor.filemanager.shizuku.c.t().E(this, kC0, c6249y.E())) != null) {
                        c6249y.n1(true);
                        arrayList.addAll(listE);
                        return arrayList;
                    }
                } else {
                    File fileC2 = ax.p086f3.I.c(c6249y.S(), c6249y.E());
                    if (fileC2 != null && (fileArrListFiles2 = fileC2.listFiles()) != null) {
                        c6248x = this;
                        c6248x.W(c6249y, kC0, fileArrListFiles2, arrayList, true);
                    }
                }
                c6248x = this;
            } else {
                c6248x = this;
            }
            if (fileArrListFiles2 != null || (arrayList.size() == 0 && !c6249y.G0().canRead())) {
                if (Y0(c6249y)) {
                    if (!c0() && ((ax.p006a3.Q.a2() || !c6249y.c1()) && (arrayListO = C6250z.Y().O(this, kC0, c6249y.E())) != null)) {
                        c6248x.q = true;
                        arrayList.addAll(arrayListO);
                        return arrayList;
                    }
                    if (Build.VERSION.SDK_INT >= 24 && arrayList.size() == 0) {
                        if ("/".equals(abstractC6239n.E())) {
                            v1(arrayList, kC0);
                        } else if ("/storage/emulated".equals(abstractC6239n.E())) {
                            x1(arrayList, kC0);
                        } else if ("/storage".equals(abstractC6239n.E())) {
                            w1(arrayList, kC0);
                        }
                    }
                }
                if (!ax.p038c3.j.F().F0(c6249y.C0()) && ax.U2.f.P(v()) && c6249y.d1() && !com.alphainventor.filemanager.shizuku.c.t().C()) {
                    u1(abstractC6239n, arrayList, kC0);
                    return arrayList;
                }
                if (arrayList.size() == 0) {
                    c6249y.s1();
                    if (c6249y.o()) {
                        throw new C1961e();
                    }
                    if (ax.p006a3.Q.j() || !c6249y.b1()) {
                        throw new ax.p070e3.t();
                    }
                    throw new C1961e();
                }
            }
            return arrayList;
        }
        List<C6249y> listE2 = com.alphainventor.filemanager.shizuku.c.t().E(this, kC0, c6249y.E());
        if (listE2 != null) {
            arrayList.addAll(listE2);
            return arrayList;
        }
        fileArrListFiles = null;
        fileArrListFiles2 = fileArrListFiles;
        if (fileArrListFiles2 != null) {
            W(c6249y, kC0, fileArrListFiles2, arrayList, c6249y.h1());
        }
        if (fileArrListFiles2 != null) {
            if (ax.p086f3.I.b(c6249y.S())) {
                fileC = ax.p086f3.I.c(c6249y.S(), c6249y.E());
                if (fileC != null) {
                    W(c6249y, kC0, fileArrListFiles2, arrayList, true);
                }
            } else if (ax.p086f3.I.a(c6249y.C0())) {
                c6248x = this;
                if (com.alphainventor.filemanager.shizuku.c.t().l()) {
                    c6249y.n1(true);
                    arrayList.addAll(listE);
                    return arrayList;
                }
            } else {
                c6248x = this;
                if (com.alphainventor.filemanager.shizuku.c.t().l()) {
                    c6249y.n1(true);
                    arrayList.addAll(listE);
                    return arrayList;
                }
            }
            c6248x = this;
        } else {
            if (ax.p086f3.I.b(c6249y.S())) {
                fileC = ax.p086f3.I.c(c6249y.S(), c6249y.E());
                if (fileC != null) {
                    W(c6249y, kC0, fileArrListFiles2, arrayList, true);
                }
            } else if (ax.p086f3.I.a(c6249y.C0())) {
                c6248x = this;
                if (com.alphainventor.filemanager.shizuku.c.t().l()) {
                    c6249y.n1(true);
                    arrayList.addAll(listE);
                    return arrayList;
                }
            } else {
                c6248x = this;
                if (com.alphainventor.filemanager.shizuku.c.t().l()) {
                    c6249y.n1(true);
                    arrayList.addAll(listE);
                    return arrayList;
                }
            }
            c6248x = this;
        }
        if (fileArrListFiles2 != null) {
            if (Y0(c6249y)) {
                if (!c0()) {
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    if ("/".equals(abstractC6239n.E())) {
                        v1(arrayList, kC0);
                    } else if ("/storage/emulated".equals(abstractC6239n.E())) {
                        x1(arrayList, kC0);
                    } else if ("/storage".equals(abstractC6239n.E())) {
                        w1(arrayList, kC0);
                    }
                }
            }
            if (!ax.p038c3.j.F().F0(c6249y.C0())) {
            }
            if (arrayList.size() == 0) {
                c6249y.s1();
                if (c6249y.o()) {
                    throw new C1961e();
                }
                if (ax.p006a3.Q.j()) {
                }
                throw new ax.p070e3.t();
            }
        } else {
            if (Y0(c6249y)) {
                if (!c0()) {
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    if ("/".equals(abstractC6239n.E())) {
                        v1(arrayList, kC0);
                    } else if ("/storage/emulated".equals(abstractC6239n.E())) {
                        x1(arrayList, kC0);
                    } else if ("/storage".equals(abstractC6239n.E())) {
                        w1(arrayList, kC0);
                    }
                }
            }
            if (!ax.p038c3.j.F().F0(c6249y.C0())) {
            }
            if (arrayList.size() == 0) {
                c6249y.s1();
                if (c6249y.o()) {
                    throw new C1961e();
                }
                if (ax.p006a3.Q.j()) {
                }
                throw new ax.p070e3.t();
            }
        }
        return arrayList;
    }

    private List<AbstractC6239n> t1(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            Cursor cursorQuery = p().getContentResolver().query(n0.c("external"), y, "bucket_id=? AND bucket_display_name=?", new String[]{String.valueOf(abstractC6239n.E().toLowerCase(Locale.ROOT).hashCode()), abstractC6239n.B()}, "_data asc");
            if (cursorQuery == null) {
                return null;
            }
            if (cursorQuery.getCount() <= 0) {
                cursorQuery.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ax.p086f3.K kC0 = ((C6249y) abstractC6239n).C0();
            ax.p006a3.L l = new ax.p006a3.L();
            long j = 0;
            C6249y c6249y = null;
            while (cursorQuery.moveToNext()) {
                A aJ0 = j0(kC0, cursorQuery, l);
                C6249y c6249yI0 = i0(kC0, aJ0);
                long j2 = aJ0.e;
                if (c6249y != null && c6249y.E().equalsIgnoreCase(c6249yI0.E())) {
                    if (j2 > j) {
                        arrayList.remove(c6249y);
                    }
                }
                arrayList.add(c6249yI0);
                c6249y = c6249yI0;
                j = j2;
            }
            cursorQuery.close();
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    private AbstractC6239n u0(C6249y c6249y, String str) {
        AbstractC6239n abstractC6239nR;
        if (c6249y.U0()) {
            try {
                return D0(c6249y);
            } catch (C1966j unused) {
            }
        } else if (ax.p086f3.I.b(c6249y.S())) {
            File fileC = ax.p086f3.I.c(c6249y.S(), str);
            if (fileC != null) {
                return new C6249y(this, fileC, c6249y.C0(), true);
            }
        } else if (ax.p086f3.I.a(c6249y.C0()) && c6249y.d1()) {
            File fileC2 = ax.p086f3.I.c(c6249y.S(), str);
            if (fileC2 != null) {
                return new C6249y(this, fileC2, c6249y.C0(), true);
            }
        } else if (com.alphainventor.filemanager.shizuku.c.t().l() && (abstractC6239nR = com.alphainventor.filemanager.shizuku.c.t().r(this, null, str)) != null) {
            return abstractC6239nR;
        }
        return null;
    }

    private void u1(AbstractC6239n abstractC6239n, List<AbstractC6239n> list, ax.p086f3.K k) {
        if (ax.U2.f.P(k)) {
            for (ax.X2.f fVar : ax.X2.d.F(p()).B()) {
                if (!TextUtils.isEmpty(fVar.t())) {
                    File file = new File(d0.Q(abstractC6239n.E(), fVar.t()));
                    if (file.exists()) {
                        list.add(new C6249y(this, file, k));
                    } else if (ax.p006a3.Q.j() && fVar.D()) {
                        C6249y c6249y = new C6249y(this, file, k, true, false, true, false, 0L, 0L);
                        if (c6249y.U0()) {
                            c6249y.s1();
                            if (c6249y.o()) {
                                list.add(c6249y);
                            } else {
                                ax.p038c3.n.b(p()).e(p());
                            }
                        } else {
                            c6249y.l1(true);
                            list.add(c6249y);
                        }
                    }
                }
            }
        }
    }

    public static C6249y w0(File file) {
        try {
            return (C6249y) C2017x.f(file).a1(file.getAbsolutePath());
        } catch (C1966j unused) {
            C2461b.f();
            return null;
        }
    }

    private void w1(List<AbstractC6239n> list, ax.p086f3.K k) {
        File file = new File("/storage/emulated");
        if (file.exists()) {
            list.add(new C6249y(this, file, k));
        }
    }

    private InputStream x0(C6249y c6249y, long j) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(c6249y.G0());
        if (j != 0) {
            fileInputStream.skip(j);
        }
        return fileInputStream;
    }

    private void x1(List<AbstractC6239n> list, ax.p086f3.K k) {
        Iterator<String> it = u.iterator();
        while (it.hasNext()) {
            File file = new File("/storage/emulated/", it.next());
            if (file.exists()) {
                list.add(new C6249y(this, file, k));
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x005f  */
    private String y0(String str, boolean z) throws Throwable {
        Uri uri;
        Uri uri2;
        String str2;
        String strH1;
        if (!ax.p006a3.Q.b()) {
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
        String strH2 = H1(uri, new String[]{"_id", "_data"}, "_data = ?", new String[]{str}, 0);
        if (strH2 != null) {
            strH1 = H1(uri2, new String[]{"_id", "_data"}, str2 + " = ?", new String[]{strH2}, 1);
        } else {
            strH1 = null;
        }
        if (strH1 != null) {
            AbstractC6239n abstractC6239nA1 = a1(strH1);
            if (abstractC6239nA1.o()) {
                AbstractC6239n abstractC6239nA2 = a1(str);
                if (abstractC6239nA2.o() && abstractC6239nA2.q() > abstractC6239nA1.q() && abstractC6239nA2.q() < System.currentTimeMillis()) {
                    strH1 = null;
                }
            } else {
                strH1 = null;
            }
        }
        if (strH1 == null || strH1.isEmpty()) {
            return null;
        }
        return strH1;
    }

    private InputStream y1(String str) {
        try {
            C6249y c6249y = (C6249y) a1(str);
            return new ax.Ja.a(new BufferedInputStream(h1(c6249y, 0L), 32768), (int) c6249y.p());
        } catch (C1966j unused) {
            return null;
        }
    }

    public static C6235j z0(Context context, List<AbstractC6239n> list) {
        C6249y c6249yJ0;
        String strN0 = null;
        Object obj = null;
        for (AbstractC6239n abstractC6239n : list) {
            if (ax.p086f3.B.P(abstractC6239n)) {
                if ((abstractC6239n instanceof K) && ((K) abstractC6239n).E1()) {
                    try {
                        c6249yJ0 = C6249y.J0(abstractC6239n.E());
                    } catch (C1966j unused) {
                        c6249yJ0 = (C6249y) abstractC6239n;
                    }
                } else {
                    c6249yJ0 = (C6249y) abstractC6239n;
                }
                ax.p086f3.K kC0 = c6249yJ0.C0();
                if (kC0.equals(obj)) {
                    continue;
                } else {
                    if (D1(context, kC0, c6249yJ0)) {
                        if (ax.U2.f.P(kC0) && c6249yJ0.c1()) {
                            strN0 = c6249yJ0.N0();
                        }
                        return new C6235j(kC0, strN0);
                    }
                    obj = kC0;
                }
            }
        }
        return null;
    }

    private void z1(AbstractC6238m abstractC6238m, AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws Throwable {
        Context contextP = abstractC6238m.p();
        boolean zEquals = abstractC6239n.U().equals(abstractC6239n2.U());
        boolean zIsDirectory = abstractC6239n.isDirectory();
        boolean zC1 = C1(abstractC6239n);
        if (zEquals) {
            C6249y c6249y = (C6249y) abstractC6239n;
            if (ax.p038c3.j.F().F0(c6249y.C0()) && c6249y.b1()) {
                throw new C1961e("/Android/data package folders cannot be renamed from Android 13");
            }
            C6232g.M(contextP, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            if (ax.p006a3.Q.d0()) {
                M1(abstractC6239n, zIsDirectory, true);
                N1(a1(abstractC6239n2.E()), true, zC1, true, false);
                return;
            }
            return;
        }
        if (ax.p006a3.Q.J0() && r1(abstractC6239n, abstractC6239n2)) {
            C6232g.K(contextP, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            if (ax.p006a3.Q.d0()) {
                M1(abstractC6239n, zIsDirectory, true);
                N1(a1(abstractC6239n2.E()), true, zC1, true, false);
                return;
            }
            return;
        }
        if (!abstractC6239n.isDirectory()) {
            A1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            return;
        }
        C6249y c6249y2 = (C6249y) abstractC6239n;
        C6249y c6249y3 = (C6249y) abstractC6239n2;
        if (!ax.p006a3.Q.L() || c6249y2.Q0() == c6249y3.Q0()) {
            ax.p302sb.c.h().g().b("MOVE ERROR").k().h("location:" + c6249y2.Q() + "," + c6249y3.Q()).i();
        }
        throw new C1966j("treespace problem : doesSupportMoveFileToDifferentParent() == false");
    }

    protected InputStream B0(String str) {
        Bitmap bitmapA0;
        int iS = (int) (ax.p406z3.B.s(p()) * 128.0f);
        try {
            ParcelFileDescriptor parcelFileDescriptorOpen = ParcelFileDescriptor.open(new File(str), 268435456);
            if (parcelFileDescriptorOpen == null || (bitmapA0 = A0(parcelFileDescriptorOpen, iS, iS)) == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(32768);
            bitmapA0.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
            bitmapA0.recycle();
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception unused) {
            return null;
        }
    }

    public ParcelFileDescriptor C0(C6249y c6249y) throws C1966j {
        try {
            if (ax.p006a3.Q.F1() && c6249y.f1()) {
                return com.alphainventor.filemanager.shizuku.c.t().x(c6249y.E());
            }
            return !c6249y.j0() ? ParcelFileDescriptor.open(c6249y.G0(), 805306368) : C6232g.w(p(), c6249y);
        } catch (IOException e2) {
            throw new C1966j(e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void E(Context context, ax.p086f3.K k) {
        super.E(context, k);
        if (ax.p006a3.Q.V1()) {
            this.l = new C6232g(context);
        }
        if (k == ax.p086f3.K.e || ax.U2.f.e0(k.d())) {
            this.n = new HashSet<>();
            Iterator<String> it = v.iterator();
            while (it.hasNext()) {
                this.n.add(d0.P(ax.p086f3.K.e.e(), it.next()));
            }
            this.o = new HashSet<>();
            Iterator<String> it2 = x.iterator();
            while (it2.hasNext()) {
                this.o.add(d0.P(ax.p086f3.K.e.e(), it2.next()));
            }
        }
        ax.p086f3.K k2 = ax.p086f3.K.f;
        if (k == k2 || ax.U2.f.e0(k.d())) {
            if (this.n == null) {
                this.n = new HashSet<>();
            }
            if (!ax.p038c3.j.F().u0() || TextUtils.isEmpty(k2.e())) {
                return;
            }
            Iterator<String> it3 = w.iterator();
            while (it3.hasNext()) {
                this.n.add(d0.P(ax.p086f3.K.f.e(), it3.next()));
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0055 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 2, insn: 0x0027: MOVE (r1 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:11:0x0027 */
    public List<String> I1(AbstractC6239n abstractC6239n) throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader3 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(h1(abstractC6239n, 0L)));
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
                    } catch (C1966j e2) {
                        e = e2;
                        ax.p302sb.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return null;
                    } catch (IOException e3) {
                        e = e3;
                        ax.p302sb.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
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
        } catch (C1966j e4) {
            e = e4;
            bufferedReader = null;
            ax.p302sb.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            return null;
        } catch (IOException e5) {
            e = e5;
            bufferedReader = null;
            ax.p302sb.c.h().g().d("DOT_HIDDEN READ ERROR").m(e).i();
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

    public boolean J1(AbstractC6239n abstractC6239n) throws Throwable {
        List<String> listI1;
        String strQ = d0.Q(abstractC6239n.U(), ".hidden");
        AbstractC6239n abstractC6239nA1 = a1(strQ);
        if (!abstractC6239nA1.o() || (listI1 = I1(abstractC6239nA1)) == null) {
            return false;
        }
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(strQ, false));
                try {
                    boolean z = true;
                    for (String str : listI1) {
                        if (!z) {
                            outputStreamWriter2.write("\n");
                        }
                        if (!str.equals(abstractC6239n.B())) {
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

    protected int K0(String str, String str2, String str3) {
        Boolean bool;
        try {
            if (str3.startsWith("image") && (bool = this.h.get(str2)) != null && bool.booleanValue()) {
                return new ax.p387y0.a(str2).l("Orientation", 0);
            }
            return -1;
        } catch (FileNotFoundException | IOException unused) {
            return -1;
        }
    }

    protected AbstractC6239n K1(C6249y c6249y) {
        AbstractC6239n abstractC6239nU0;
        String strF = c6249y.F();
        C6249y c6249yH0 = h0(new File(strF), c6249y.C0(), c6249y.h1());
        c6249yH0.u0(c6249y);
        return (ax.p006a3.Q.L() && c6249yH0.c1() && (abstractC6239nU0 = u0(c6249yH0, strF)) != null) ? abstractC6239nU0 : c6249yH0;
    }

    protected InputStream L0(String str, String str2, String str3) throws Throwable {
        InputStream inputStreamS0;
        String lowerCase = str2 != null ? str2.toLowerCase() : null;
        EnumC2019z enumC2019zG = ax.p086f3.A.g(lowerCase);
        if (EnumC2019z.AUDIO == enumC2019zG || str3.startsWith("audio")) {
            inputStreamS0 = s0(str);
        } else if (EnumC2019z.VIDEO == enumC2019zG || str3.startsWith("video")) {
            inputStreamS0 = N0(str);
        } else if (EnumC2019z.IMAGE == enumC2019zG || str3.startsWith("image")) {
            if (ax.p086f3.A.S(lowerCase)) {
                inputStreamS0 = ax.p358w3.c.e(v0(str), (int) (ax.p406z3.B.s(p()) * 128.0f));
                if (inputStreamS0 == null) {
                    inputStreamS0 = ax.p358w3.b.n();
                }
            } else {
                inputStreamS0 = v0(str);
            }
        } else if (EnumC2019z.APK == enumC2019zG || str3.equals("application/vnd.android.package-archive")) {
            inputStreamS0 = ax.X2.d.r(p(), str);
        } else if (EnumC2019z.PDF == enumC2019zG && ax.p006a3.Q.r1()) {
            inputStreamS0 = B0(str);
        } else if ("epub".equals(lowerCase)) {
            inputStreamS0 = ax.p358w3.c.c(p(), str);
            if (inputStreamS0 == null) {
                inputStreamS0 = ax.p358w3.b.n();
            }
        } else {
            inputStreamS0 = M0(str);
        }
        return inputStreamS0 == null ? ax.p358w3.b.n() : inputStreamS0;
    }

    public boolean O0() {
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

    public boolean P0() {
        boolean z;
        synchronized (this.m) {
            z = this.i.size() > 0;
        }
        return z;
    }

    public void R1(AbstractC6239n abstractC6239n) {
        ArrayList<ax.p310t3.f> arrayList = new ArrayList<>();
        arrayList.add(new ax.p310t3.f(abstractC6239n.E(), abstractC6239n.Q().J(), abstractC6239n.M(), abstractC6239n.q(), false, false, false, true, false, false));
        Q1(arrayList, false, 10000);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6230e
    public void T(String str, long j) throws IOException {
        C6249y c6249y = (C6249y) a1(str);
        if (c6249y.j0()) {
            try {
                C6232g.P(this, c6249y, j);
            } catch (C1966j e2) {
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

    public List<A> Z0(AbstractC6239n abstractC6239n, boolean z, long j, long j2) {
        long j3;
        long j4;
        boolean z2;
        String str = null;
        try {
            Context contextP = p();
            String str2 = "_data LIKE '" + abstractC6239n.E() + "/%'";
            long j5 = 0;
            if (j2 > 0) {
                str2 = str2 + " AND (_size > " + j2 + " OR _size IS NULL)";
            }
            Cursor cursorQuery = contextP.getContentResolver().query(n0.c("external"), y, str2, null, "_data asc");
            if (cursorQuery == null) {
                return null;
            }
            if (cursorQuery.getCount() <= 0) {
                cursorQuery.close();
                return null;
            }
            ArrayList arrayList = new ArrayList();
            ax.p086f3.K kC0 = ((C6249y) abstractC6239n).C0();
            ax.p006a3.L l = new ax.p006a3.L();
            long j6 = j / 1000;
            A a2 = null;
            long j7 = 0;
            while (cursorQuery.moveToNext()) {
                try {
                    if (j6 > j5) {
                        j3 = j5;
                        long j8 = cursorQuery.getLong(2);
                        if (j8 == j3 || j8 >= j6) {
                        }
                        j5 = j3;
                    } else {
                        j3 = j5;
                    }
                    A aJ0 = j0(kC0, cursorQuery, l);
                    long j9 = aJ0.e;
                    String str3 = aJ0.a;
                    if (a2 != null) {
                        j4 = j9;
                        if (a2.a.equalsIgnoreCase(str3)) {
                            if (j4 > j7) {
                                arrayList.remove(a2);
                            } else {
                                j5 = j3;
                            }
                        }
                    } else {
                        j4 = j9;
                    }
                    boolean z3 = true;
                    if (str == null || !d0.H(str, str3)) {
                        HashSet<String> hashSet = this.o;
                        str = (hashSet == null || !hashSet.contains(str3)) ? null : str3;
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    if (j > j3 && aJ0.c < j) {
                        z2 = true;
                    }
                    if (j2 > 0 && aJ0.d < j2) {
                        z2 = true;
                    }
                    if (z || !aJ0.b) {
                        z3 = z2;
                    }
                    if (!z3) {
                        arrayList.add(aJ0);
                    }
                    a2 = aJ0;
                    j5 = j3;
                    j7 = j4;
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

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return true;
    }

    public boolean a0(AbstractC6239n abstractC6239n) throws Throwable {
        String strQ = d0.Q(abstractC6239n.U(), ".hidden");
        AbstractC6239n abstractC6239nA1 = a1(strQ);
        boolean zO = abstractC6239nA1.o();
        OutputStreamWriter outputStreamWriter = null;
        try {
            try {
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(c(strQ, true));
                if (zO) {
                    try {
                        outputStreamWriter2.write("\n");
                    } catch (IOException unused) {
                        outputStreamWriter = outputStreamWriter2;
                        if (abstractC6239nA1.isDirectory()) {
                            ax.p302sb.c.h().g().d(".hidden foder exists").h(abstractC6239nA1.E()).i();
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
                outputStreamWriter2.write(abstractC6239n.B());
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

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) {
        AbstractC6239n abstractC6239nU0;
        C6249y c6249y = new C6249y(this, new File(str), (ax.p086f3.K) null);
        return (ax.p006a3.Q.L() && c6249y.c1() && (abstractC6239nU0 = u0(c6249y, str)) != null) ? abstractC6239nU0 : c6249y;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        C6249y c6249y = (C6249y) a1(str2);
        return L0(c6249y.L0(), c6249y.y(), c6249y.t());
    }

    @Override // ax.p086f3.X
    public OutputStream c(String str, boolean z) throws IOException {
        C6249y c6249y = (C6249y) a1(str);
        if (ax.p006a3.Q.F1() && c6249y.f1()) {
            return com.alphainventor.filemanager.shizuku.c.t().v(str, z);
        }
        if (c6249y.j0()) {
            try {
                return C6232g.u(this, c6249y, z);
            } catch (C1966j e2) {
                throw new IOException(e2);
            }
        }
        if (!c0() || !Y0(c6249y)) {
            return new c(this, c6249y, z, !c6249y.o());
        }
        try {
            return new c(this, c6249y, z, !c6249y.o());
        } catch (IOException unused) {
            FileOutputStream fileOutputStreamD = C6250z.Y().D(c6249y, z);
            if (fileOutputStreamD != null) {
                return fileOutputStreamD;
            }
            throw new FileNotFoundException("failed to open outputstream using root");
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean c1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n != null) {
            return !((C6249y) abstractC6239n).j0() || ax.p006a3.Q.J0();
        }
        C2461b.f();
        return true;
    }

    @Override // ax.p086f3.i0
    public boolean d(AbstractC6239n abstractC6239n, long j) {
        if (j < 0 || !(abstractC6239n instanceof C6249y)) {
            return false;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.f1()) {
            boolean zI = com.alphainventor.filemanager.shizuku.c.t().I(c6249y.E(), j);
            if (zI) {
                c6249y.o1(j);
            }
            return zI;
        }
        try {
            boolean lastModified = c6249y.G0().setLastModified(j);
            if (!lastModified) {
                return lastModified;
            }
            c6249y.s0();
            return lastModified;
        } catch (Exception e2) {
            ax.p302sb.c.h().g().d("set last modified").m(e2).h("base:" + c6249y.C0().k()).i();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public List<AbstractC6239n> d1(AbstractC6239n abstractC6239n, AbstractC6238m.f fVar) throws C1966j {
        HashSet<String> hashSet;
        List<AbstractC6239n> listT1;
        d0(abstractC6239n);
        C6249y c6249y = (C6249y) abstractC6239n;
        if (!c6249y.n0() || c6249y.q1()) {
            return (ax.p006a3.Q.h1() && ax.U2.f.e0(abstractC6239n.Q()) && (((hashSet = this.n) != null && hashSet.contains(abstractC6239n.E())) || fVar == AbstractC6238m.f.USE_MEDIA_STORE) && (listT1 = t1(abstractC6239n)) != null && listT1.size() != 0) ? listT1 : s1(abstractC6239n);
        }
        try {
            return C6232g.J(this, abstractC6239n);
        } catch (C1966j e2) {
            if (ax.p038c3.j.F().F0(c6249y.C0()) && c6249y.b1() && ax.p038c3.n.b(p()).c(p(), c6249y)) {
                try {
                    ax.p038c3.n.b(p()).e(p());
                    c6249y.z1();
                } catch (ax.p070e3.r unused) {
                }
            }
            e2.printStackTrace();
            return s1(abstractC6239n);
        } catch (RuntimeException e3) {
            ax.p302sb.c.h().b("listchildren local document runtime").m(e3).i();
            return s1(abstractC6239n);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return K0(str, str2, ((C6249y) a1(str2)).t());
    }

    public void f0(C6249y c6249y, C6249y c6249y2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        if (!c6249y.isDirectory()) {
            m1(c6249y, c6249y2, interfaceC2462c, iVar);
            return;
        }
        if (!c6249y2.o()) {
            if (c6249y2.E().endsWith("com.alphainventor.filemanager") && "/Android/data/com.alphainventor.filemanager".equals(c6249y2.D0())) {
                ax.U2.e.q(p());
                c6249y2 = (C6249y) a1(c6249y2.E());
                if (!c6249y2.o()) {
                    n1(c6249y2);
                }
            } else {
                n1(c6249y2);
            }
        }
        for (AbstractC6239n abstractC6239n : J(c6249y)) {
            f0((C6249y) abstractC6239n, (C6249y) a1(d0.Q(c6249y2.E(), abstractC6239n.B())), interfaceC2462c, iVar);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return null;
        }
        if (u() == ax.U2.f.y0 && !abstractC6239n.h()) {
            if (EnumC2019z.IMAGE != abstractC6239n.G()) {
                return null;
            }
        }
        return ax.p086f3.B.Y(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    public C6249y h0(File file, ax.p086f3.K k, boolean z) {
        return new C6249y(this, file, k, z);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        long j2;
        long j3;
        C6249y c6249y = (C6249y) abstractC6239n;
        try {
            try {
                try {
                    if (!c6249y.n0()) {
                        if (!c6249y.f1()) {
                            return x0(c6249y, j);
                        }
                        InputStream inputStreamS = com.alphainventor.filemanager.shizuku.c.t().s(abstractC6239n.E(), j);
                        return c6249y.c1() ? new e(inputStreamS, c6249y.p(), j) : inputStreamS;
                    }
                    try {
                        InputStream inputStreamT = C6232g.t(p(), abstractC6239n, j);
                        if (!c6249y.c1()) {
                            return inputStreamT;
                        }
                        try {
                            return new e(inputStreamT, c6249y.p(), j);
                        } catch (C1966j unused) {
                            j3 = j;
                        } catch (FileNotFoundException e2) {
                            e = e2;
                            j2 = j;
                        }
                    } catch (C1966j unused2) {
                        j3 = j;
                    }
                    return x0(c6249y, j3);
                } catch (FileNotFoundException e3) {
                    e = e3;
                }
            } catch (IOException e4) {
                throw new C1966j(e4);
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            j2 = j;
        }
        FileNotFoundException fileNotFoundException = e;
        if (ax.p038c3.j.F().r0() && C6250z.Y().m(abstractC6239n.E())) {
            FileInputStream fileInputStreamW = ax.p390y3.k.l(p()) ? C6250z.Y().w(abstractC6239n, j2) : C6250z.Y().z(abstractC6239n, j2);
            if (fileInputStreamW != null) {
                return fileInputStreamW;
            }
        }
        C1966j c1966jC = C1960d.c("getInputStream", fileNotFoundException, false);
        if (c1966jC != null) {
            throw c1966jC;
        }
        throw new ax.p070e3.t(fileNotFoundException);
    }

    public C6249y i0(ax.p086f3.K k, A a2) {
        C6249y c6249y = new C6249y(this, new File(a2.a), k, a2.b, false, true, true, a2.d, a2.c);
        Long l = a2.f;
        if (l != null) {
            c6249y.k1(l.longValue());
        }
        return c6249y;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        C6249y c6249y = (C6249y) abstractC6239n;
        C6249y c6249y2 = (C6249y) abstractC6239n2;
        if (c6249y.f1()) {
            long jP = abstractC6239n.p();
            boolean zIsDirectory = abstractC6239n.isDirectory();
            if (!com.alphainventor.filemanager.shizuku.c.t().H(abstractC6239n.E(), abstractC6239n2.E())) {
                G1(abstractC6239n, abstractC6239n2, null);
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
            if (W0(c6249y)) {
                M1(abstractC6239n, zIsDirectory, true);
            }
            if (W0(c6249y2)) {
                N1(a1(abstractC6239n2.E()), false, C1(abstractC6239n), true, false);
                return;
            }
            return;
        }
        if (S1(abstractC6239n, abstractC6239n2)) {
            z1(this, abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
            return;
        }
        long jP2 = abstractC6239n.p();
        boolean zIsDirectory2 = abstractC6239n.isDirectory();
        C6249y c6249y3 = (C6249y) abstractC6239n;
        if (ax.p006a3.Q.Q0()) {
            boolean z = false;
            try {
                try {
                    Files.move(Paths.get(c6249y3.L0(), new String[0]), Paths.get(c6249y2.L0(), new String[0]), new CopyOption[0]);
                } catch (IOException | SecurityException e2) {
                    if ((!ax.p006a3.Q.e() || !U0(e2) || (!R0(abstractC6239n2) && !R0(abstractC6239n))) && ((!ax.p006a3.Q.L() || !U0(e2) || (!c6249y2.c1() && !c6249y3.c1())) && (!ax.p006a3.Q.L() || !U0(e2) || ((!Q0(abstractC6239n) && !Q0(abstractC6239n2)) || !abstractC6239n.isDirectory())))) {
                        e2.printStackTrace();
                        if (!c6249y3.G0().renameTo(c6249y2.G0())) {
                            G1(abstractC6239n, abstractC6239n2, e2);
                        }
                    }
                    if (abstractC6239n.isDirectory()) {
                        B1(c6249y3, c6249y2, interfaceC2462c, iVar);
                        return;
                    } else {
                        A1(abstractC6239n, abstractC6239n2, interfaceC2462c, iVar);
                        return;
                    }
                }
            } catch (RuntimeException e3) {
                if (c6249y3.G0().renameTo(c6249y2.G0())) {
                    z = true;
                } else {
                    G1(abstractC6239n, abstractC6239n2, null);
                }
                ax.p302sb.c.h().g().b("moveFile InvalidPath").m(e3).h("retry success=" + z).i();
            }
        } else if (!c6249y3.G0().renameTo(c6249y2.G0())) {
            G1(abstractC6239n, abstractC6239n2, null);
        }
        if (iVar != null) {
            iVar.a(jP2, jP2);
        }
        boolean zC1 = C1(abstractC6239n);
        M1(abstractC6239n, zIsDirectory2, true);
        N1((C6249y) K1(c6249y2), false, zC1, true, false);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        aVar.J();
        aVar.d0(true, null);
    }

    public void k0(AbstractC6239n abstractC6239n) throws C1966j {
        if (!abstractC6239n.isDirectory()) {
            q1(abstractC6239n);
            return;
        }
        Iterator<AbstractC6239n> it = J(abstractC6239n).iterator();
        while (it.hasNext()) {
            k0(it.next());
        }
        q1(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:132:0x0260  */
    /* JADX WARN: Code duplicated, block: B:136:0x026c A[Catch: all -> 0x01bf, TryCatch #23 {all -> 0x01bf, blocks: (B:71:0x0163, B:85:0x0197, B:88:0x01a1, B:90:0x01ad, B:99:0x01d1, B:101:0x01d9, B:102:0x01e3, B:97:0x01c8, B:105:0x01f7, B:106:0x01fd, B:118:0x0228, B:120:0x0230, B:122:0x0241, B:134:0x0262, B:136:0x026c, B:137:0x0273, B:138:0x0274, B:139:0x027a), top: B:177:0x010c }] */
    /* JADX WARN: Code duplicated, block: B:138:0x0274 A[Catch: all -> 0x01bf, TryCatch #23 {all -> 0x01bf, blocks: (B:71:0x0163, B:85:0x0197, B:88:0x01a1, B:90:0x01ad, B:99:0x01d1, B:101:0x01d9, B:102:0x01e3, B:97:0x01c8, B:105:0x01f7, B:106:0x01fd, B:118:0x0228, B:120:0x0230, B:122:0x0241, B:134:0x0262, B:136:0x026c, B:137:0x0273, B:138:0x0274, B:139:0x027a), top: B:177:0x010c }] */
    /* JADX WARN: Code duplicated, block: B:174:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Not initialized variable reg: 15, insn: 0x018c: MOVE (r13 I:??[OBJECT, ARRAY]) = (r15 I:??[OBJECT, ARRAY]), block:B:79:0x018b */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        Throwable th;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        BufferedOutputStream bufferedOutputStream3;
        Throwable th2;
        if (abstractC6239n.o()) {
            ax.p302sb.c.h().g().d("!! WRITE FILE ALREADY EXISTS !!").k().h("location:" + abstractC6239n.Q().H()).i();
            throw new C1966j("WriteFile file already exists");
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        C6249y c6249y2 = (C6249y) K1(c6249y);
        if (c6249y2.o()) {
            throw new C1962f(c6249y2.isDirectory() != abstractC6239n.isDirectory());
        }
        BufferedOutputStream bufferedOutputStream4 = null;
        if (ax.p006a3.Q.F1() && c6249y.f1()) {
            InputStream inputStreamB = g2.b();
            if (inputStreamB == null) {
                throw new C1966j("null inputstream");
            }
            try {
                try {
                    BufferedOutputStream bufferedOutputStream5 = new BufferedOutputStream(com.alphainventor.filemanager.shizuku.c.t().v(abstractC6239n.E(), false), 8192);
                    try {
                        ax.p086f3.F.c(inputStreamB, bufferedOutputStream5, j, interfaceC2462c, iVar);
                        bufferedOutputStream5.close();
                        if (l != null && l.longValue() >= 0) {
                            d((C6249y) abstractC6239n, l.longValue());
                        }
                        if (W0(c6249y)) {
                            C2461b.e("what case is this? : " + c6249y.E());
                            try {
                                N1(a1(abstractC6239n.E()), false, false, false, true);
                            } catch (IOException e2) {
                                e = e2;
                                if (!d0.M(abstractC6239n.B())) {
                                    throw new ax.p070e3.n("shizuku write file error 1", e);
                                }
                                throw C1960d.a("shizuku write file error 2", e);
                            } catch (Throwable th3) {
                                th = th3;
                                th2 = th;
                                if (bufferedOutputStream4 != null) {
                                    try {
                                        bufferedOutputStream4.close();
                                    } catch (IOException unused) {
                                    }
                                }
                                try {
                                    inputStreamB.close();
                                    throw th2;
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    throw th2;
                                }
                            }
                        }
                        try {
                            inputStreamB.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    } catch (IOException e5) {
                        e = e5;
                    } catch (Throwable th4) {
                        th2 = th4;
                        bufferedOutputStream4 = bufferedOutputStream5;
                        if (bufferedOutputStream4 != null) {
                            bufferedOutputStream4.close();
                        }
                        inputStreamB.close();
                        throw th2;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (Throwable th5) {
                th = th5;
            }
        } else {
            if (c6249y.j0()) {
                C6232g.Q(this, abstractC6239n, g2, j, l, z, interfaceC2462c, iVar);
                if (u() == ax.U2.f.u0 && z && !((C6249y) K1(c6249y)).o()) {
                    C6249y c6249y3 = (C6249y) t0(d0.Q(abstractC6239n.U(), d0.b(abstractC6239n.B())), c6249y.C0());
                    if (!c6249y3.o()) {
                        ax.p302sb.c.h().g().b("written file not found 2").h("validfat:" + d0.M(c6249y3.B())).i();
                    }
                }
                if (l != null && l.longValue() >= 0) {
                    d(c6249y, l.longValue());
                }
                if (ax.p006a3.J.n()) {
                    N1(a1(abstractC6239n.E()), false, false, false, true);
                    return;
                }
                return;
            }
            InputStream inputStreamB2 = g2.b();
            try {
                try {
                    try {
                        if (inputStreamB2 == null) {
                            if (g2 instanceof AbstractC6238m.e) {
                                ax.p302sb.c.h().g().b("NULL INPUTSTREAM").h("location:" + ((AbstractC6238m.e) g2).e().k()).i();
                            } else {
                                ax.p302sb.c.h().g().b("NULL INPUTSTREAM").h(g2.getClass().getName()).i();
                            }
                            throw new C1966j("null inputstream");
                        }
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(c6249y.G0());
                            bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream, 8192);
                            try {
                                ax.p086f3.F.c(inputStreamB2, bufferedOutputStream2, j, interfaceC2462c, iVar);
                                if (z && S()) {
                                    fileOutputStream.getFD().sync();
                                }
                                bufferedOutputStream2.close();
                                if (u() != ax.U2.f.u0 || !z) {
                                    ax.U2.f.x0(u());
                                } else if (!((C6249y) K1(c6249y)).o()) {
                                    ax.p302sb.c.h().g().b("written file not found 1").i();
                                }
                                if (l != null && l.longValue() >= 0) {
                                    d((C6249y) abstractC6239n, l.longValue());
                                }
                                N1(a1(abstractC6239n.E()), false, false, false, true);
                                inputStreamB2.close();
                            } catch (FileNotFoundException e7) {
                                e = e7;
                                try {
                                    if (c0() && Y0(abstractC6239n)) {
                                        try {
                                            FileOutputStream fileOutputStreamD = C6250z.Y().D(abstractC6239n, false);
                                            if (fileOutputStreamD != null) {
                                                bufferedOutputStream3 = bufferedOutputStream2;
                                                try {
                                                    BufferedOutputStream bufferedOutputStream6 = new BufferedOutputStream(fileOutputStreamD, 8192);
                                                    try {
                                                        ax.p086f3.F.c(inputStreamB2, bufferedOutputStream6, j, interfaceC2462c, iVar);
                                                        bufferedOutputStream6.close();
                                                        if (l != null) {
                                                            try {
                                                                if (l.longValue() >= 0) {
                                                                    C6250z.Y().U(abstractC6239n.E(), l.longValue());
                                                                }
                                                            } catch (IOException unused2) {
                                                                if (!d0.M(abstractC6239n.B())) {
                                                                    throw new ax.p070e3.n("write file error 1", e);
                                                                }
                                                                throw C1960d.a("write file error 2", e);
                                                            }
                                                        }
                                                        if (c6241p != null) {
                                                            C6250z.Y().V(abstractC6239n.E(), c6241p);
                                                        }
                                                        inputStreamB2.close();
                                                    } catch (IOException unused3) {
                                                    }
                                                } catch (IOException unused4) {
                                                    if (!d0.M(abstractC6239n.B())) {
                                                        throw C1960d.a("write file error 2", e);
                                                    }
                                                    throw new ax.p070e3.n("write file error 1", e);
                                                } catch (Throwable th6) {
                                                    th = th6;
                                                    th = th;
                                                    bufferedOutputStream4 = bufferedOutputStream3;
                                                    if (bufferedOutputStream4 != null) {
                                                        try {
                                                            bufferedOutputStream4.close();
                                                        } catch (IOException unused5) {
                                                        }
                                                    }
                                                    try {
                                                        inputStreamB2.close();
                                                        throw th;
                                                    } catch (IOException e8) {
                                                        e8.printStackTrace();
                                                        throw th;
                                                    }
                                                }
                                            } else {
                                                bufferedOutputStream3 = bufferedOutputStream2;
                                            }
                                        } catch (IOException unused6) {
                                        }
                                    } else {
                                        bufferedOutputStream3 = bufferedOutputStream2;
                                    }
                                    if (!d0.M(abstractC6239n.B())) {
                                        throw C1960d.a("write file error 2", e);
                                    }
                                    throw new ax.p070e3.n("write file error 1", e);
                                } catch (Throwable th7) {
                                    th = th7;
                                    bufferedOutputStream3 = bufferedOutputStream2;
                                }
                            } catch (IOException e9) {
                                e = e9;
                                throw C1960d.a("write file error 3", e);
                            }
                        } catch (FileNotFoundException e10) {
                            e = e10;
                            bufferedOutputStream2 = null;
                        } catch (IOException e11) {
                            e = e11;
                        }
                    } catch (IOException e12) {
                        e12.printStackTrace();
                    }
                } catch (Throwable th8) {
                    th = th8;
                    bufferedOutputStream4 = bufferedOutputStream;
                }
            } catch (Throwable th9) {
                th = th9;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        O1(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    public void m0(ax.p182l3.i iVar) {
        synchronized (this.m) {
            try {
                if (this.i.size() == 0) {
                    return;
                }
                ArrayList<ax.p310t3.f> arrayList = new ArrayList<>(this.i);
                e0();
                int size = arrayList.size();
                ax.p086f3.M.b(p(), arrayList, iVar);
                ax.p086f3.M.c(p(), arrayList, true, iVar);
                p0(arrayList, size, iVar);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        l1(abstractC6239n2, s(abstractC6239n), abstractC6239n.t(), abstractC6239n.p(), Long.valueOf(abstractC6239n.q()), abstractC6239n.D(), false, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n.o()) {
            return false;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.f1()) {
            return com.alphainventor.filemanager.shizuku.c.t().o(abstractC6239n.E());
        }
        if (c6249y.j0()) {
            try {
                return g0((C6249y) abstractC6239n);
            } catch (C1966j unused) {
                return false;
            }
        }
        boolean zMkdir = c6249y.G0().mkdir();
        if (zMkdir) {
            X(abstractC6239n, false, true);
        }
        return (!zMkdir && c0() && Y0(abstractC6239n)) ? C6250z.Y().i(abstractC6239n.E()) : zMkdir;
    }

    public void o0() {
        synchronized (this.m) {
            try {
                if (this.i.size() == 0) {
                    return;
                }
                ArrayList<ax.p310t3.f> arrayList = new ArrayList<>(this.i);
                e0();
                if (ax.p006a3.Q.U() || ax.p006a3.Q.T()) {
                    ax.p086f3.M.f(p(), arrayList);
                    if (arrayList.size() == 0) {
                        return;
                    }
                }
                n0(arrayList);
                if (arrayList.size() > 0) {
                    new d(arrayList, 500L).h(new Long[0]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        if (abstractC6239n.o()) {
            return false;
        }
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.f1()) {
            return com.alphainventor.filemanager.shizuku.c.t().p(abstractC6239n.E());
        }
        if (c6249y.j0()) {
            try {
                if (C6232g.d(this, abstractC6239n, false) != null) {
                    N1(a1(abstractC6239n.E()), false, false, false, true);
                    return true;
                }
            } catch (C1966j unused) {
            }
            return false;
        }
        try {
            boolean zCreateNewFile = ((C6249y) abstractC6239n).G0().createNewFile();
            if (!zCreateNewFile) {
                return zCreateNewFile;
            }
            N1(a1(abstractC6239n.E()), false, false, false, true);
            return zCreateNewFile;
        } catch (IOException e2) {
            if (c0() && Y0(abstractC6239n)) {
                return C6250z.Y().h(abstractC6239n.E());
            }
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public long q() throws C1966j {
        k0 k0VarG0;
        switch (b.a[u().ordinal()]) {
            case 1:
                return 0L;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                if (TextUtils.isEmpty(x()) || (k0VarG0 = G0()) == null || k0VarG0.b == 0) {
                    return -1L;
                }
                long j = k0VarG0.d;
                if (j >= 0) {
                    return j;
                }
                return -1L;
            default:
                C2461b.g("freespace not reachable : " + u().H());
                r.severe("INVALID GETFREESPACE CALL FOR LOCATOIN : " + u());
                throw new C1966j();
        }
    }

    protected void q0(AbstractC6239n abstractC6239n, List<AbstractC6239n> list, boolean z, String str, boolean z2, ax.p182l3.h hVar) {
        hVar.i0(ax.p086f3.B.h(list, str, z2, true), z);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        boolean zDelete;
        String[] list;
        C6249y c6249y = (C6249y) abstractC6239n;
        if (c6249y.f1()) {
            boolean zIsDirectory = abstractC6239n.isDirectory();
            if (com.alphainventor.filemanager.shizuku.c.t().q(abstractC6239n.E())) {
                if (W0(c6249y)) {
                    M1(abstractC6239n, zIsDirectory, false);
                    return;
                }
                return;
            } else {
                c6249y.s1();
                if (!c6249y.o()) {
                    throw new ax.p070e3.t("Shizuku.delete failed : not exist");
                }
                throw new C1966j("Shizuku.delete failed.");
            }
        }
        if (c6249y.j0()) {
            C6232g.g(this, abstractC6239n);
            if (ax.p006a3.Q.H0()) {
                return;
            }
            M1(abstractC6239n, abstractC6239n.isDirectory(), false);
            return;
        }
        boolean zIsDirectory2 = abstractC6239n.isDirectory();
        if (zIsDirectory2 && (list = c6249y.G0().list()) != null && list.length > 0) {
            throw new C1967k("File.delete directory that has children");
        }
        Throwable th = null;
        if (ax.p006a3.Q.Q0()) {
            try {
                Files.delete(Paths.get(c6249y.L0(), new String[0]));
                zDelete = true;
            } catch (IOException | RuntimeException e2) {
                th = e2;
                zDelete = false;
            }
        } else {
            zDelete = c6249y.G0().delete();
        }
        if (ax.p006a3.J.f() && zDelete) {
            File fileG0 = c6249y.G0();
            if (fileG0.exists()) {
                File fileU = ax.p086f3.B.u(fileG0);
                if (fileG0.renameTo(fileU)) {
                    fileU.delete();
                }
            }
        }
        if (zDelete) {
            M1(abstractC6239n, zIsDirectory2, false);
            return;
        }
        if (c0() && Y0(abstractC6239n) && C6250z.Y().j(abstractC6239n.E())) {
            return;
        }
        c6249y.s1();
        if (!c6249y.o()) {
            throw new ax.p070e3.t("File.delete failed : File not exist");
        }
        if (abstractC6239n.Q() == ax.U2.f.y0 && !abstractC6239n.k()) {
            throw new ax.p070e3.w("Read only system file.delete failed");
        }
        if (th == null) {
            throw new C1966j("File.delete failed dir=" + zIsDirectory2 + ",exists=true");
        }
        throw new C1966j("File.delete failed dir=:" + zIsDirectory2 + ":" + th.getMessage(), th);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        if (ax.p086f3.B.P(abstractC6239n) && ax.p086f3.B.P(abstractC6239n2)) {
            return C6249y.e1((C6249y) abstractC6239n, (C6249y) abstractC6239n2);
        }
        C2461b.f();
        return abstractC6239n.Q() == abstractC6239n2.Q();
    }

    protected InputStream s0(String str) {
        byte[] bArrK = ax.p406z3.B.k(p(), str, null);
        if (bArrK != null) {
            return new ByteArrayInputStream(bArrK);
        }
        String strH1 = H1(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"album_id", "_data"}, "_data = ?", new String[]{str}, 0);
        String strH2 = strH1 != null ? H1(MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI, new String[]{"album_art", "album"}, "_id = ? AND album_art IS NOT NULL", new String[]{strH1}, 0) : null;
        if (strH2 == null || strH2.isEmpty() || !((C6249y) a1(strH2)).o()) {
            return null;
        }
        return y1(strH2);
    }

    protected AbstractC6239n t0(String str, ax.p086f3.K k) {
        return h0(new File(str), k, false);
    }

    protected InputStream v0(String str) throws Throwable {
        String strY0 = y0(str, false);
        if (strY0 == null) {
            this.h.put(str, Boolean.FALSE);
            return y1(str);
        }
        this.h.put(str, Boolean.TRUE);
        return y1(strY0);
    }

    void v1(List<AbstractC6239n> list, ax.p086f3.K k) {
        Iterator<String> it = t.iterator();
        while (it.hasNext()) {
            File file = new File("/", it.next());
            if (file.exists()) {
                list.add(new C6249y(this, file, k));
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        switch (b.a[u().ordinal()]) {
            case 1:
            case 10:
            case 11:
                return F0();
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return G0();
            default:
                C2461b.g("not reachable storage space : " + u().H());
                r.severe("INVALID GETSTORAGESPACE CALL FOR LOCATOIN : " + u());
                throw new C1966j();
        }
    }
}
