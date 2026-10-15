package com.alphainventor.filemanager.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import ax.U2.f;
import ax.p006a3.Q;
import ax.p038c3.k;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p070e3.r;
import ax.p070e3.t;
import ax.p086f3.C2015v;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p406z3.B;
import ax.p406z3.C2461b;
import ax.p406z3.q;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6232g;
import com.alphainventor.filemanager.file.C6234i;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6249y;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class MyFileProvider extends ContentProvider {
    private static final Logger Z = Logger.getLogger("FileManager.MyFileProvider");
    private static final String[] k0 = {"_display_name", "_size", "_data"};
    private static final String[] l0 = {"_display_name", "_size"};
    private static boolean m0;
    private static MyFileProvider n0;
    private com.alphainventor.filemanager.provider.a X;
    private Handler q = new Handler(Looper.getMainLooper());
    private final HashMap<Uri, AbstractC6239n> Y = new HashMap<>();

    class a implements ParcelFileDescriptor.OnCloseListener {
        final /* synthetic */ ax.t3.b.d a;
        final /* synthetic */ File b;

        a(ax.t3.b.d dVar, File file) {
            this.a = dVar;
            this.b = file;
        }

        @Override // android.os.ParcelFileDescriptor.OnCloseListener
        public void onClose(IOException iOException) {
            this.a.a(this.b, iOException);
        }
    }

    static class b extends q<Void, Void, AbstractC6239n> {
        c h;
        CountDownLatch i;
        AbstractC6239n j;
        boolean k;

        b(c cVar, CountDownLatch countDownLatch) {
            super(q.e.HIGHER);
            this.k = B.H();
            this.h = cVar;
            this.i = countDownLatch;
        }

        @Override // ax.p406z3.q
        protected void o() {
            this.i.countDown();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public AbstractC6239n g(Void... voidArr) {
            boolean zI;
            try {
                K kE = this.h.e();
                if (kE == null) {
                    return null;
                }
                C6240o c6240oE = C2017x.e(kE);
                if (!c6240oE.a()) {
                    if (this.k) {
                        ax.p302sb.c.h().g().b("PROXY FILE OPERATOR NOT CONNECTED").i();
                        zI = false;
                    } else {
                        zI = c6240oE.i(0L);
                    }
                    if (!zI) {
                        return null;
                    }
                }
                try {
                    c6240oE.V();
                    return c6240oE.a1(this.h.d);
                } finally {
                    c6240oE.S(false);
                }
            } catch (C1966j unused) {
                return null;
            }
        }

        AbstractC6239n x() {
            return this.j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(AbstractC6239n abstractC6239n) {
            this.j = abstractC6239n;
            this.i.countDown();
        }
    }

    static class c {
        private K a;
        private boolean b;
        String c;
        String d;

        c(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            if (encodedPath != null) {
                boolean z = true;
                int iIndexOf = encodedPath.indexOf(47, 1);
                this.c = Uri.decode(encodedPath.substring(1, iIndexOf));
                this.d = Uri.decode(encodedPath.substring(iIndexOf));
                if (!"root".equals(this.c) && !"external_files".equals(this.c)) {
                    z = false;
                }
                this.b = z;
                if (z) {
                    this.a = C2017x.g(this.d).B();
                } else {
                    this.a = K.h(this.c);
                }
            }
        }

        private static String a(K k, String str) {
            return Uri.encode(k.k()) + Uri.encode(str, "/");
        }

        static c b(Uri uri) {
            return new c(uri);
        }

        static Uri g(AbstractC6239n abstractC6239n) {
            return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(a(abstractC6239n.S(), abstractC6239n.E())).build();
        }

        static Uri h(C6249y c6249y) {
            return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(a(c6249y.C0(), c6249y.E())).build();
        }

        File c() {
            return new File(this.d).getAbsoluteFile();
        }

        k d() {
            K kE = e();
            if (kE != null) {
                return new k(kE, this.d);
            }
            C2461b.f();
            return null;
        }

        K e() {
            return this.a;
        }

        String f() {
            String strF;
            String str = this.d;
            return (str == null || (strF = C2015v.f(d0.k(str))) == null) ? "application/octet-stream" : strF;
        }

        boolean i() {
            K k;
            if (this.b || (k = this.a) == null) {
                return false;
            }
            return f.a0(k.d()) || f.m0(this.a.d());
        }

        boolean j() {
            K k;
            if (this.b || (k = this.a) == null) {
                return false;
            }
            return f.h0(k.d());
        }

        boolean k() {
            return this.b;
        }

        boolean l() {
            K k;
            if (this.b || (k = this.a) == null) {
                return false;
            }
            return f.l0(k.d());
        }
    }

    public static boolean A(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).i();
        }
        return false;
    }

    public static boolean B(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).k();
        }
        return false;
    }

    public static boolean C(Uri uri) {
        return D(uri) && "1".equals(uri.getQueryParameter("motion_photo"));
    }

    public static boolean D(Uri uri) {
        if (uri == null) {
            return false;
        }
        return "com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority());
    }

    public static boolean E(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).l();
        }
        return false;
    }

    private static int F(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    private ParcelFileDescriptor G(File file, String str) throws FileNotFoundException {
        int iF = F(str);
        try {
            ax.t3.b.d dVarK = ax.p310t3.b.j().k();
            dVarK.b(file);
            return ParcelFileDescriptor.open(file, iF, this.q, new a(dVarK, file));
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private ParcelFileDescriptor H(c cVar, String str) throws FileNotFoundException {
        int iF = F(str);
        k kVarD = cVar.d();
        if (kVarD == null) {
            C2461b.g("location uri is not valid");
            throw new FileNotFoundException("location uri is not valid");
        }
        try {
            return q().b(kVarD, iF);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private MatrixCursor b(String[] strArr) {
        if (strArr == null) {
            strArr = l0;
        }
        return new MatrixCursor(strArr, 0);
    }

    private MatrixCursor c(String[] strArr, AbstractC6239n abstractC6239n) {
        int i;
        if (strArr == null) {
            strArr = l0;
        }
        String[] strArr2 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str : strArr) {
            if ("_display_name".equals(str)) {
                strArr2[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = abstractC6239n.B();
            } else if ("_size".equals(str)) {
                strArr2[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(abstractC6239n.p());
            } else if ("mime_type".equals(str)) {
                strArr2[i2] = "mime_type";
                i = i2 + 1;
                objArr[i2] = abstractC6239n.t();
            } else {
                if ("_data".equals(str)) {
                    strArr2[i2] = "_data";
                    i = i2 + 1;
                    objArr[i2] = null;
                }
            }
            i2 = i;
        }
        String[] strArrG = g(strArr2, i2);
        Object[] objArrF = f(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArrG, 1);
        matrixCursor.addRow(objArrF);
        return matrixCursor;
    }

    private MatrixCursor d(Uri uri, String[] strArr) {
        int i;
        if (strArr == null) {
            strArr = l0;
        }
        String[] strArr2 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        c cVarB = c.b(uri);
        long jO = o(uri, "length");
        int i2 = 0;
        for (String str : strArr) {
            if ("_display_name".equals(str)) {
                strArr2[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = new File(cVarB.d).getName() + ".mp4";
            } else if ("_size".equals(str)) {
                strArr2[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(jO);
            } else if ("mime_type".equals(str)) {
                strArr2[i2] = "mime_type";
                i = i2 + 1;
                objArr[i2] = n(uri);
            } else {
                if ("_data".equals(str)) {
                    strArr2[i2] = "_data";
                    i = i2 + 1;
                    objArr[i2] = null;
                }
            }
            i2 = i;
        }
        MatrixCursor matrixCursor = new MatrixCursor(g(strArr2, i2), 1);
        matrixCursor.addRow(f(objArr, i2));
        return matrixCursor;
    }

    public static k e(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).d();
        }
        return null;
    }

    private static Object[] f(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private static String[] g(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private void h(c cVar) throws C1966j {
        K kE = cVar.e();
        if (kE == null) {
            throw new C1966j("Bad Uri");
        }
        C6240o c6240oE = C2017x.e(kE);
        if (!c6240oE.a()) {
            throw new C1966j("Not connected");
        }
        try {
            c6240oE.V();
            c6240oE.q1(c6240oE.a1(cVar.d));
        } finally {
            c6240oE.S(true);
        }
    }

    public static Uri i(Uri uri) {
        c cVarB = c.b(uri);
        C2461b.c(cVarB.k());
        return u("external_files", cVarB.d);
    }

    private ParcelFileDescriptor j(c cVar, String str) throws C1966j, FileNotFoundException {
        K kE = cVar.e();
        if (kE == null) {
            throw new C1966j("Bad Uri");
        }
        C6240o c6240oE = C2017x.e(kE);
        if (!c6240oE.a()) {
            if (!f.c0(c6240oE.A()) && !f.m0(c6240oE.A())) {
                C2461b.g("not document location?" + c6240oE.A().H());
                throw new C1964h("Not connected");
            }
            c6240oE.h(null);
            if (!c6240oE.a()) {
                throw new C1964h("Not connected");
            }
        }
        try {
            c6240oE.V();
            return C6232g.o(getContext(), C6232g.l(c6240oE.a1(cVar.d)), str);
        } finally {
            c6240oE.S(false);
        }
    }

    private static AbstractC6239n k(c cVar) throws C1966j {
        if (!cVar.j() && !cVar.i()) {
            C2461b.g(cVar.c + ":" + cVar.d);
        }
        K kE = cVar.e();
        if (kE == null) {
            throw new C1966j("Bad Uri");
        }
        C6240o c6240oE = C2017x.e(kE);
        if (!c6240oE.a()) {
            throw new C1966j("Not connected");
        }
        try {
            c6240oE.V();
            return c6240oE.a1(cVar.d);
        } finally {
            c6240oE.S(false);
        }
    }

    public static MyFileProvider l() {
        return n0;
    }

    public static File m(Uri uri) {
        return c.b(uri).c();
    }

    private static String n(Uri uri) {
        String queryParameter = uri.getQueryParameter("mimetype");
        return !TextUtils.isEmpty(queryParameter) ? queryParameter : "video/mp4";
    }

    private static long o(Uri uri, String str) {
        String queryParameter = uri.getQueryParameter(str);
        if (queryParameter == null) {
            return -1L;
        }
        try {
            return Long.parseLong(queryParameter);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static Uri p(AbstractC6239n abstractC6239n, long j, long j2, String str) {
        if (!ax.p086f3.B.H(abstractC6239n) || j < 0 || j2 <= 0) {
            throw new IllegalArgumentException("Invalid Motion Photo video range");
        }
        return (abstractC6239n instanceof C6249y ? t((C6249y) abstractC6239n) : c.g(abstractC6239n)).buildUpon().appendQueryParameter("motion_photo", "1").appendQueryParameter("offset", Long.toString(j)).appendQueryParameter("length", Long.toString(j2)).appendQueryParameter("mimetype", str).build();
    }

    private com.alphainventor.filemanager.provider.a q() {
        z();
        return this.X;
    }

    public static Uri r(C6234i c6234i) {
        return c.g(c6234i);
    }

    public static Uri s(File file) {
        return u("root", file.getAbsolutePath());
    }

    public static Uri t(C6249y c6249y) {
        return s(c6249y.G0());
    }

    private static Uri u(String str, String str2) {
        return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(Uri.encode(str) + Uri.encode(str2, "/")).build();
    }

    public static Uri v(C6249y c6249y) {
        return c.h(c6249y);
    }

    public static Uri w(C6249y c6249y) {
        return s(c6249y.G0());
    }

    public static Uri x(AbstractC6239n abstractC6239n) {
        Uri uriG = c.g(abstractC6239n);
        MyFileProvider myFileProvider = n0;
        if (myFileProvider != null) {
            myFileProvider.a(uriG, abstractC6239n);
        }
        return uriG;
    }

    public static void y(Context context) {
        if (context == null || m0) {
            return;
        }
        try {
            context.grantUriPermission("com.android.systemui", Uri.parse("content://com.alphainventor.filemanager.fileprovider/"), 129);
            m0 = true;
        } catch (Exception unused) {
        }
    }

    void a(Uri uri, AbstractC6239n abstractC6239n) {
        this.Y.put(uri, abstractC6239n);
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        }
        if (!providerInfo.grantUriPermissions) {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        c cVarB = c.b(uri);
        if (cVarB.k()) {
            return cVarB.c().delete() ? 1 : 0;
        }
        if (cVarB.l()) {
            ax.p302sb.c.i(getContext()).g().b("PROXY FILE DELETE REQUESTED").i();
            return 0;
        }
        try {
            h(cVarB);
            return 1;
        } catch (C1966j unused) {
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return C(uri) ? n(uri) : c.b(uri).f();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        ax.U2.b.k(getContext());
        n0 = this;
        return true;
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openAssetFile(Uri uri, String str) throws FileNotFoundException {
        if (!C(uri)) {
            return super.openAssetFile(uri, str);
        }
        if (!"r".equals(str)) {
            throw new FileNotFoundException("Motion Photo is read-only");
        }
        long jO = o(uri, "offset");
        long jO2 = o(uri, "length");
        if (jO < 0 || jO2 <= 0 || jO > Long.MAX_VALUE - jO2) {
            throw new FileNotFoundException("Invalid Motion Photo video range");
        }
        ParcelFileDescriptor parcelFileDescriptorOpenFile = openFile(uri.buildUpon().clearQuery().build(), "r");
        if (parcelFileDescriptorOpenFile == null) {
            throw new FileNotFoundException("Unable to open Motion Photo");
        }
        long statSize = parcelFileDescriptorOpenFile.getStatSize();
        if (statSize < 0 || jO + jO2 <= statSize) {
            return new AssetFileDescriptor(parcelFileDescriptorOpenFile, jO, jO2);
        }
        try {
            parcelFileDescriptorOpenFile.close();
        } catch (IOException unused) {
        }
        throw new FileNotFoundException("Motion Photo video range exceeds file size");
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ax.U2.b.f(getContext(), false);
        if (C(uri)) {
            throw new FileNotFoundException("Motion Photo must be opened as an asset file");
        }
        getContext();
        c cVarB = c.b(uri);
        if (!Q.V1()) {
            return G(cVarB.c(), str);
        }
        if (cVarB.k()) {
            File fileC = cVarB.c();
            try {
                C6249y c6249y = (C6249y) C2017x.f(fileC).a1(fileC.getAbsolutePath());
                if (c6249y != null) {
                    try {
                        if (c6249y.f1()) {
                            return com.alphainventor.filemanager.shizuku.c.t().w(c6249y.E());
                        }
                    } catch (r | IOException unused) {
                        return G(fileC, str);
                    }
                }
                if (c6249y == null || !c6249y.j0() || getContext() == null) {
                    return G(fileC, str);
                }
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContext().getContentResolver().openFileDescriptor(C6232g.l(c6249y), str);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    return parcelFileDescriptorOpenFileDescriptor.dup();
                }
                return null;
            } catch (C1966j e) {
                e.printStackTrace();
                throw new FileNotFoundException();
            } catch (SecurityException unused2) {
                throw new FileNotFoundException();
            }
        }
        if (cVarB.l()) {
            if (Q.t1()) {
                return H(cVarB, str);
            }
            C2461b.g("API VERSION NOT SUPPORTED");
            throw new FileNotFoundException("API VERSION NOT SUPPORTED");
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorJ = j(cVarB, str);
            if (parcelFileDescriptorJ != null) {
                return parcelFileDescriptorJ.dup();
            }
            return null;
        } catch (C1966j e2) {
            if (!(e2 instanceof t) && !(e2 instanceof C1964h)) {
                if (e2 instanceof r) {
                    ax.p302sb.c.h().g().b("MyFileProvider error 1").m(e2).h("uri:" + cVarB.d).i();
                } else {
                    ax.p302sb.c.h().g().b("MyFileProvider error 2").m(e2).i();
                }
            }
            if (cVarB.j()) {
                return G(cVarB.c(), str);
            }
            throw new FileNotFoundException(e2.getMessage());
        } catch (IOException e3) {
            throw new FileNotFoundException(e3.getMessage());
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        boolean z;
        int i;
        ax.U2.b.f(getContext(), false);
        if (C(uri)) {
            return d(uri, strArr);
        }
        c cVarB = c.b(uri);
        if (!cVarB.k()) {
            if (!cVarB.l()) {
                try {
                    return c(strArr, k(cVarB));
                } catch (C1966j e) {
                    throw new IllegalStateException(e);
                }
            }
            AbstractC6239n abstractC6239n = this.Y.get(uri);
            if (abstractC6239n != null) {
                return c(strArr, abstractC6239n);
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            b bVar = new b(cVarB, countDownLatch);
            bVar.i(new Void[0]);
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
            }
            AbstractC6239n abstractC6239nX = bVar.x();
            return abstractC6239nX != null ? c(strArr, abstractC6239nX) : b(strArr);
        }
        File fileC = cVarB.c();
        try {
            z = !((C6249y) C2017x.f(fileC).a1(fileC.getAbsolutePath())).c1();
        } catch (C1966j unused2) {
            z = true;
        }
        if (strArr == null) {
            strArr = z ? k0 : l0;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = fileC.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(fileC.length());
            } else {
                if ("_data".equals(str3)) {
                    strArr3[i2] = "_data";
                    objArr[i2] = fileC.getAbsolutePath();
                    i2++;
                } else if ("mime_type".equals(str3)) {
                    strArr3[i2] = "mime_type";
                    i = i2 + 1;
                    objArr[i2] = cVarB.f();
                }
            }
            i2 = i;
        }
        String[] strArrG = g(strArr3, i2);
        Object[] objArrF = f(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArrG, 1);
        matrixCursor.addRow(objArrF);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public synchronized void z() {
        if (this.X == null) {
            this.X = new com.alphainventor.filemanager.provider.a(getContext());
        }
    }
}
