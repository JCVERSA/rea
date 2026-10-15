package com.alphainventor.filemanager.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import ax.E1.P;
import ax.G1.j;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.I1.q;
import ax.I1.s;
import ax.J1.C0735p;
import ax.J1.F;
import ax.J1.V;
import ax.J1.r;
import ax.p045d2.n;
import ax.p045d2.x;
import ax.p338y1.f;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5832f;
import com.alphainventor.filemanager.file.C5834h;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.u;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public class MyFileProvider extends ContentProvider {
    private static final Logger Z = Logger.getLogger("FileManager.MyFileProvider");
    private static final String[] h0 = {"_display_name", "_size", "_data"};
    private static final String[] i0 = {"_display_name", "_size"};
    private static boolean j0;
    private static MyFileProvider k0;
    private com.alphainventor.filemanager.provider.a X;
    private Handler q = new Handler(Looper.getMainLooper());
    private final HashMap<Uri, AbstractC5838l> Y = new HashMap<>();

    class a implements ParcelFileDescriptor.OnCloseListener {
        final /* synthetic */ ax.X1.a.d a;
        final /* synthetic */ File b;

        a(ax.X1.a.d dVar, File file) {
            this.a = dVar;
            this.b = file;
        }

        @Override // android.os.ParcelFileDescriptor.OnCloseListener
        public void onClose(IOException iOException) {
            this.a.a(this.b, iOException);
        }
    }

    static class b extends n<Void, Void, AbstractC5838l> {
        c h;
        CountDownLatch i;
        AbstractC5838l j;
        boolean k;

        b(c cVar, CountDownLatch countDownLatch) {
            super(n.f.HIGHER);
            this.k = x.J();
            this.h = cVar;
            this.i = countDownLatch;
        }

        @Override // ax.p045d2.n
        protected void o() {
            this.i.countDown();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public AbstractC5838l g(Void... voidArr) {
            boolean zH;
            try {
                F fE = this.h.e();
                if (fE == null) {
                    return null;
                }
                C5839m c5839mD = r.d(fE);
                if (!c5839mD.a()) {
                    if (this.k) {
                        ax.p221pa.c.h().g().b("PROXY FILE OPERATOR NOT CONNECTED").i();
                        zH = false;
                    } else {
                        zH = c5839mD.h(0L);
                    }
                    if (!zH) {
                        return null;
                    }
                }
                try {
                    c5839mD.S();
                    return c5839mD.V0(this.h.d);
                } finally {
                    c5839mD.P(false);
                }
            } catch (C0718i unused) {
                return null;
            }
        }

        AbstractC5838l x() {
            return this.j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void q(AbstractC5838l abstractC5838l) {
            this.j = abstractC5838l;
            this.i.countDown();
        }
    }

    static class c {
        private F a;
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
                    this.a = r.g(this.d).z();
                } else {
                    this.a = F.h(this.c);
                }
            }
        }

        private static String a(F f, String str) {
            return Uri.encode(f.j()) + Uri.encode(str, "/");
        }

        static c b(Uri uri) {
            return new c(uri);
        }

        static Uri g(AbstractC5838l abstractC5838l) {
            return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(a(abstractC5838l.R(), abstractC5838l.C())).build();
        }

        static Uri h(u uVar) {
            return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(a(uVar.v0(), uVar.C())).build();
        }

        File c() {
            return new File(this.d).getAbsoluteFile();
        }

        j d() {
            F fE = e();
            if (fE != null) {
                return new j(fE, this.d);
            }
            ax.p045d2.b.f();
            return null;
        }

        F e() {
            return this.a;
        }

        String f() {
            String strF;
            String str = this.d;
            return (str == null || (strF = C0735p.f(V.k(str))) == null) ? "application/octet-stream" : strF;
        }

        boolean i() {
            F f;
            if (this.b || (f = this.a) == null) {
                return false;
            }
            return f.a0(f.d());
        }

        boolean j() {
            F f;
            if (this.b || (f = this.a) == null) {
                return false;
            }
            return f.g0(f.d());
        }

        boolean k() {
            return this.b;
        }

        boolean l() {
            F f;
            if (this.b || (f = this.a) == null) {
                return false;
            }
            return f.k0(f.d());
        }
    }

    private ParcelFileDescriptor A(c cVar, String str) throws FileNotFoundException {
        int iY = y(str);
        j jVarD = cVar.d();
        if (jVarD == null) {
            ax.p045d2.b.g("location uri is not valid");
            throw new FileNotFoundException("location uri is not valid");
        }
        try {
            return m().b(jVarD, iY);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private MatrixCursor b(String[] strArr) {
        if (strArr == null) {
            strArr = i0;
        }
        return new MatrixCursor(strArr, 0);
    }

    private MatrixCursor c(String[] strArr, AbstractC5838l abstractC5838l) {
        int i;
        if (strArr == null) {
            strArr = i0;
        }
        String[] strArr2 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str : strArr) {
            if ("_display_name".equals(str)) {
                strArr2[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = abstractC5838l.w();
            } else if ("_size".equals(str)) {
                strArr2[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(abstractC5838l.o());
            } else if ("mime_type".equals(str)) {
                strArr2[i2] = "mime_type";
                i = i2 + 1;
                objArr[i2] = abstractC5838l.s();
            } else {
                if ("_data".equals(str)) {
                    strArr2[i2] = "_data";
                    i = i2 + 1;
                    objArr[i2] = null;
                }
            }
            i2 = i;
        }
        String[] strArrF = f(strArr2, i2);
        Object[] objArrE = e(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArrF, 1);
        matrixCursor.addRow(objArrE);
        return matrixCursor;
    }

    public static j d(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).d();
        }
        return null;
    }

    private static Object[] e(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    private static String[] f(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    private void g(c cVar) throws C0718i {
        F fE = cVar.e();
        if (fE == null) {
            throw new C0718i("Bad Uri");
        }
        C5839m c5839mD = r.d(fE);
        if (!c5839mD.a()) {
            throw new C0718i("Not connected");
        }
        try {
            c5839mD.S();
            c5839mD.l1(c5839mD.V0(cVar.d));
        } finally {
            c5839mD.P(true);
        }
    }

    public static Uri h(Uri uri) {
        c cVarB = c.b(uri);
        ax.p045d2.b.c(cVarB.k());
        return p("external_files", cVarB.d);
    }

    private ParcelFileDescriptor i(c cVar, String str) throws C0718i, FileNotFoundException {
        F fE = cVar.e();
        if (fE == null) {
            throw new C0718i("Bad Uri");
        }
        C5839m c5839mD = r.d(fE);
        if (!c5839mD.a()) {
            if (!f.b0(c5839mD.y())) {
                ax.p045d2.b.g("not document location?" + c5839mD.y().H());
                throw new C0716g("Not connected");
            }
            c5839mD.g(null);
            if (!c5839mD.a()) {
                throw new C0716g("Not connected");
            }
        }
        try {
            c5839mD.S();
            return C5832f.o(getContext(), C5832f.l(c5839mD.V0(cVar.d)), str);
        } finally {
            c5839mD.P(false);
        }
    }

    private static AbstractC5838l j(c cVar) throws C0718i {
        ax.p045d2.b.c(cVar.j() || cVar.i());
        F fE = cVar.e();
        if (fE == null) {
            throw new C0718i("Bad Uri");
        }
        C5839m c5839mD = r.d(fE);
        if (!c5839mD.a()) {
            throw new C0718i("Not connected");
        }
        try {
            c5839mD.S();
            return c5839mD.V0(cVar.d);
        } finally {
            c5839mD.P(false);
        }
    }

    public static MyFileProvider k() {
        return k0;
    }

    public static File l(Uri uri) {
        return c.b(uri).c();
    }

    private com.alphainventor.filemanager.provider.a m() {
        t();
        return this.X;
    }

    public static Uri n(C5834h c5834h) {
        return c.g(c5834h);
    }

    public static Uri o(File file) {
        return p("root", file.getAbsolutePath());
    }

    private static Uri p(String str, String str2) {
        return new Uri.Builder().scheme("content").authority("com.alphainventor.filemanager.fileprovider").encodedPath(Uri.encode(str) + Uri.encode(str2, "/")).build();
    }

    public static Uri q(u uVar) {
        return c.h(uVar);
    }

    public static Uri r(AbstractC5838l abstractC5838l) {
        Uri uriG = c.g(abstractC5838l);
        MyFileProvider myFileProvider = k0;
        if (myFileProvider != null) {
            myFileProvider.a(uriG, abstractC5838l);
        }
        return uriG;
    }

    public static void s(Context context) {
        if (context == null || j0) {
            return;
        }
        try {
            context.grantUriPermission("com.android.systemui", Uri.parse("content://com.alphainventor.filemanager.fileprovider/"), 129);
            j0 = true;
        } catch (Exception unused) {
        }
    }

    public static boolean u(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).i();
        }
        return false;
    }

    public static boolean v(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).k();
        }
        return false;
    }

    public static boolean w(Uri uri) {
        if (uri == null) {
            return false;
        }
        return "com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority());
    }

    public static boolean x(Uri uri) {
        if ("com.alphainventor.filemanager.fileprovider".equals(uri.getAuthority())) {
            return c.b(uri).l();
        }
        return false;
    }

    private static int y(String str) {
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

    private ParcelFileDescriptor z(File file, String str) throws FileNotFoundException {
        int iY = y(str);
        try {
            ax.X1.a.d dVarK = ax.X1.a.j().k();
            dVarK.b(file);
            return ParcelFileDescriptor.open(file, iY, this.q, new a(dVarK, file));
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    void a(Uri uri, AbstractC5838l abstractC5838l) {
        this.Y.put(uri, abstractC5838l);
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
            ax.p221pa.c.i(getContext()).g().b("PROXY FILE DELETE REQUESTED").i();
            return 0;
        }
        try {
            g(cVarB);
            return 1;
        } catch (C0718i unused) {
            return 0;
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return c.b(uri).f();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        ax.p338y1.b.k(getContext());
        k0 = this;
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        ax.p338y1.b.f(getContext(), false);
        getContext();
        c cVarB = c.b(uri);
        if (!P.F1()) {
            return z(cVarB.c(), str);
        }
        if (cVarB.k()) {
            File fileC = cVarB.c();
            try {
                u uVar = (u) r.f(fileC).V0(fileC.getAbsolutePath());
                if (uVar != null) {
                    try {
                        if (uVar.X0() && getContext() != null) {
                            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContext().getContentResolver().openFileDescriptor(C5832f.l(uVar), str);
                            if (parcelFileDescriptorOpenFileDescriptor != null) {
                                return parcelFileDescriptorOpenFileDescriptor.dup();
                            }
                            return null;
                        }
                    } catch (q | IOException unused) {
                        return z(fileC, str);
                    }
                }
                return z(fileC, str);
            } catch (C0718i e) {
                e.printStackTrace();
                throw new FileNotFoundException();
            } catch (SecurityException unused2) {
                throw new FileNotFoundException();
            }
        }
        if (cVarB.l()) {
            if (P.f1()) {
                return A(cVarB, str);
            }
            ax.p045d2.b.g("API VERSION NOT SUPPORTED");
            throw new FileNotFoundException("API VERSION NOT SUPPORTED");
        }
        try {
            ParcelFileDescriptor parcelFileDescriptorI = i(cVarB, str);
            if (parcelFileDescriptorI != null) {
                return parcelFileDescriptorI.dup();
            }
            return null;
        } catch (C0718i e2) {
            if (!(e2 instanceof s) && !(e2 instanceof C0716g)) {
                if (e2 instanceof q) {
                    ax.p221pa.c.h().g().b("MyFileProvider error 1").m(e2).h("uri:" + cVarB.d).i();
                } else {
                    ax.p221pa.c.h().g().b("MyFileProvider error 2").m(e2).i();
                }
            }
            if (cVarB.j()) {
                return z(cVarB.c(), str);
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
        ax.p338y1.b.f(getContext(), false);
        c cVarB = c.b(uri);
        if (!cVarB.k()) {
            if (!cVarB.l()) {
                try {
                    return c(strArr, j(cVarB));
                } catch (C0718i e) {
                    throw new IllegalStateException(e);
                }
            }
            AbstractC5838l abstractC5838l = this.Y.get(uri);
            if (abstractC5838l != null) {
                return c(strArr, abstractC5838l);
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            b bVar = new b(cVarB, countDownLatch);
            bVar.i(new Void[0]);
            try {
                countDownLatch.await();
            } catch (InterruptedException unused) {
            }
            AbstractC5838l abstractC5838lX = bVar.x();
            return abstractC5838lX != null ? c(strArr, abstractC5838lX) : b(strArr);
        }
        File fileC = cVarB.c();
        try {
            z = !((u) r.f(fileC).V0(fileC.getAbsolutePath())).Q0();
        } catch (C0718i unused2) {
            z = true;
        }
        if (strArr == null) {
            strArr = z ? h0 : i0;
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
        String[] strArrF = f(strArr3, i2);
        Object[] objArrE = e(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(strArrF, 1);
        matrixCursor.addRow(objArrE);
        return matrixCursor;
    }

    public synchronized void t() {
        if (this.X == null) {
            this.X = new com.alphainventor.filemanager.provider.a(getContext());
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
