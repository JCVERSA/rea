package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0739u;
import ax.J1.C0740v;
import ax.J1.U;
import ax.J1.V;
import ax.J1.W;
import ax.J1.Y;
import ax.J1.b0;
import com.socialnmobile.dav.gson.ServerType;
import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class Q extends AbstractC5837k {
    private static final Logger u = Logger.getLogger("FileManager.WebDAVFileHelper");
    private static c v;
    boolean h;
    String i;
    String j;
    boolean k;
    String l;
    String m;
    ax.p263sa.a n;
    protected ax.p045d2.n o;
    AbstractC5838l p;
    int q;
    private boolean r;
    private Integer s;
    private Boolean t;

    public static class a {
        public boolean a;
        public X509Certificate b;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.Q$a$a, reason: collision with other inner class name */
        class C0499a implements X509TrustManager {
            C0499a() {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                a aVar = a.this;
                X509Certificate x509Certificate = x509CertificateArr[0];
                aVar.b = x509Certificate;
                try {
                    x509Certificate.checkValidity();
                } catch (Exception unused) {
                    throw new CertificateException("Certificate not valid or trusted.");
                }
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }

        public void a(Context context, ax.G1.n nVar) {
            Q.l0(context);
            String str = Q.k0(nVar) + "/";
            try {
                try {
                    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                    httpsURLConnection.connect();
                    this.a = true;
                    for (Certificate certificate : httpsURLConnection.getServerCertificates()) {
                        if (certificate instanceof X509Certificate) {
                            this.b = (X509Certificate) certificate;
                            break;
                        }
                    }
                    httpsURLConnection.disconnect();
                } catch (SSLHandshakeException unused) {
                    this.a = false;
                    TrustManager[] trustManagerArr = {new C0499a()};
                    SSLContext sSLContext = Build.VERSION.SDK_INT <= 28 ? SSLContext.getInstance("TLSv1.2") : SSLContext.getInstance("TLSv1.3");
                    sSLContext.init(null, trustManagerArr, new SecureRandom());
                    HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL(str).openConnection();
                    httpsURLConnection2.setSSLSocketFactory(sSLContext.getSocketFactory());
                    httpsURLConnection2.connect();
                    httpsURLConnection2.disconnect();
                } catch (IOException unused2) {
                    this.a = false;
                }
            } catch (Exception unused3) {
            }
        }
    }

    public static class c extends Y {
        Context a;
        b b;
        U c = new U(ax.p338y1.f.M0);

        class a implements InterfaceC5828b.a {
            final /* synthetic */ int X;
            final /* synthetic */ ax.G1.n Y;
            final /* synthetic */ ax.P1.j q;

            a(ax.P1.j jVar, int i, ax.G1.n nVar) {
                this.q = jVar;
                this.X = i;
                this.Y = nVar;
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
            public void N() {
                this.q.b(ax.p338y1.f.M0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
            public void f0(boolean z, Object obj) {
                if (!z) {
                    this.q.a(ax.p338y1.f.M0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof Exception ? ((Exception) obj).getMessage() : null);
                    return;
                }
                c.this.p(this.X, this.Y);
                ax.p338y1.f fVar = ax.p338y1.f.M0;
                C5839m c5839mE = ax.J1.r.e(fVar, this.X);
                if (c5839mE.a()) {
                    ((Q) c5839mE.t()).q0(false);
                    ((Q) c5839mE.t()).Q();
                    ax.G1.b.k().s(c5839mE.z(), c5839mE.B());
                }
                this.q.d(fVar, this.X);
            }
        }

        c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("WebDAVPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("encryption_" + i).remove("ignorecert_" + i).remove("created_" + i).remove("sortindex_" + i).remove("expectcontinue_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            return new ax.G1.p(ax.p338y1.f.M0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("WebDAVPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.J1.Y
        public ax.G1.n k(int i) {
            ax.G1.n nVar = new ax.G1.n();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            nVar.s(string);
            nVar.z(sharedPreferences.getInt("port_" + i, 443));
            nVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            nVar.y(this.c.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            nVar.v(sharedPreferences.getString("initialPath_" + i, null));
            if (sharedPreferences.getInt("encryption_" + i, 1) == 1) {
                nVar.C(true);
            } else {
                nVar.C(false);
            }
            nVar.u(sharedPreferences.getBoolean("ignorecert_" + i, false));
            nVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            return nVar;
        }

        @Override // ax.J1.Y
        public void l(int i, ax.G1.n nVar, ax.P1.j jVar, boolean z) {
            int iM = m(i);
            if (!z) {
                p(iM, nVar);
                jVar.d(ax.p338y1.f.M0, iM);
            } else {
                b bVar = new b(this.a, nVar, new a(jVar, iM, nVar));
                this.b = bVar;
                bVar.h(new Object[0]);
            }
        }

        int m(int i) {
            return i == -100 ? n() : i;
        }

        int n() {
            return this.a.getSharedPreferences("WebDAVPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> o() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void p(int i, ax.G1.n nVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, nVar.d()).putInt("port_" + i, nVar.h()).putString("username_" + i, nVar.k()).putString("password_" + i, this.c.e(nVar.d(), nVar.g())).putString("name_" + i, nVar.b()).putString("initialPath_" + i, nVar.f()).putInt("encryption_" + i, nVar.o() ? 1 : 0).putBoolean("ignorecert_" + i, nVar.l());
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String P(ax.G1.n nVar) {
        if (nVar.o()) {
            return "https://" + nVar.d() + ":" + nVar.h();
        }
        return "http://" + nVar.d() + ":" + nVar.h();
    }

    private int V(String str) {
        return str.indexOf("/", str.indexOf("://") + 3);
    }

    private boolean X() {
        String str = this.i;
        return str == null || !str.startsWith("https://magentacloud.de");
    }

    public static int Y() {
        return 443;
    }

    public static c b0(Context context) {
        if (v == null) {
            v = new c(context.getApplicationContext());
        }
        return v;
    }

    private boolean f0() {
        return n().getSharedPreferences("WebDAVPrefs", 0).getBoolean("expectcontinue_" + q(), X());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String k0(ax.G1.n nVar) {
        String strP = P(nVar);
        if (TextUtils.isEmpty(nVar.f())) {
            return strP;
        }
        return strP + V.Q(nVar.f());
    }

    protected static void l0(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            AbstractC5837k.C(context);
        }
    }

    private boolean o0(int i) {
        return i == ServerType.TYPE_COMMON || i == ServerType.TYPE_NGINX;
    }

    private void p0(boolean z) {
        n().getSharedPreferences("WebDAVPrefs", 0).edit().putBoolean("expectcontinue_" + q(), z).apply();
    }

    private void u0(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, boolean z, ax.p045d2.c cVar, ax.P1.i iVar, boolean z2) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l.n());
        ax.p263sa.a aVarH0 = h0(j);
        if (aVarH0 == null) {
            aVarH0 = W();
        }
        try {
            aVarH0.R(i0(abstractC5838l), new W(a2.b(), 0L, j, cVar, iVar), str, z2, j, l);
            if (f0() != z2) {
                p0(z2);
            }
            this.r = true;
        } catch (ax.p277ta.a e) {
            if (e.getCause() instanceof C0710a) {
                throw ((C0710a) e.getCause());
            }
            if ((e.b() == 408 || (e.getCause() instanceof SocketTimeoutException)) && !this.r) {
                this.r = true;
                u0(abstractC5838l, a2, str, j, l, z, cVar, iVar, !z2);
            } else {
                if (e.getCause() != null && (e.getCause().getCause() instanceof C0710a)) {
                    throw ((C0710a) e.getCause().getCause());
                }
                throw R("webdav writeFile", e);
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean D() {
        return this.q == ServerType.TYPE_NEXTCLOUD;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean G() {
        return false;
    }

    public void Q() {
        n().getSharedPreferences("WebDAVPrefs", 0).edit().remove("expectcontinue_" + q()).apply();
        this.r = false;
    }

    protected C0718i R(String str, ax.p277ta.a aVar) {
        int iB = aVar.b();
        if (iB == 0) {
            return C0712c.b(str, aVar);
        }
        if (iB == 413) {
            return new ax.I1.B(aVar);
        }
        if (iB == 507) {
            return new ax.I1.r(aVar);
        }
        switch (iB) {
            case 401:
            case 402:
            case 403:
                return new C0713d(aVar);
            case 404:
                return new ax.I1.s(aVar);
            default:
                return new C0718i(aVar);
        }
    }

    protected String S(String str) throws C0718i {
        String path;
        try {
            path = new URI(str).getPath();
        } catch (URISyntaxException unused) {
            path = Uri.parse(str).getPath();
        }
        if (path == null) {
            ax.p221pa.c.h().g().b("WEBDAV PATH NULL").h("url:" + str + ",prefix:" + this.i + "," + this.j);
            throw new C0718i();
        }
        String str2 = this.m;
        if (str2 != null) {
            if (!path.startsWith(str2)) {
                ax.p221pa.c.h().g().d("WEBDAV PATH ERROR 1").h("url:" + str + ",prefix:" + this.j + ",rawPath:" + path);
                throw new C0718i("Unknown URL");
            }
            path = path.substring(this.m.length());
        } else if (!TextUtils.isEmpty(this.j)) {
            if (!path.startsWith(this.j)) {
                ax.p221pa.c.h().g().d("WEBDAV PATH ERROR 2").h("url:" + str + ",prefix:" + this.j + ",rawPath:" + path);
                throw new C0718i("Unknown URL");
            }
            path = path.substring(this.j.length());
        }
        return V.Q(path);
    }

    protected ax.p045d2.n<Object, Void, Boolean> T(Activity activity, Fragment fragment, Q q, int i, InterfaceC5828b.a aVar) {
        return new b(n(), this, q(), aVar);
    }

    protected void U(String str, String str2, String str3) {
        String path;
        try {
            path = new URI(str).getPath();
        } catch (URISyntaxException unused) {
            path = Uri.parse(str).getPath();
        }
        if (path == null) {
            ax.p221pa.c.h().g().b("WEBDAV PATH NULL DETCT PROXY").h("url:" + str + ",prefix:" + this.i + "," + this.j);
            return;
        }
        int iLastIndexOf = path.lastIndexOf(str3);
        if (iLastIndexOf < 0) {
            return;
        }
        if (str2 == null || !path.startsWith(str2)) {
            String strSubstring = path.substring(0, iLastIndexOf);
            if (TextUtils.isEmpty(strSubstring)) {
                if (TextUtils.isEmpty(str2)) {
                    u.fine("case 1");
                    this.m = null;
                } else {
                    u.fine("case 2");
                    this.m = HttpUrl.FRAGMENT_ENCODE_SET;
                }
            } else if (strSubstring.equals(str2)) {
                u.fine("case 3");
                this.m = null;
            } else {
                u.fine("case 4");
                this.m = strSubstring;
            }
            if (this.m != null) {
                u.fine("WebDAV proxy detected : " + str2 + " -> " + this.m);
                if (TextUtils.isEmpty(this.j)) {
                    this.l = this.i + this.m;
                    return;
                }
                this.l = this.i.substring(0, this.i.lastIndexOf(this.j)) + this.m;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        String strL;
        AbstractC5838l abstractC5838l;
        boolean zEquals = str.equals("/");
        if (zEquals && (abstractC5838l = this.p) != null) {
            return abstractC5838l;
        }
        try {
            ax.p263sa.a aVarW = W();
            if (aVarW == null) {
                throw new C0716g("Not connected : webdav");
            }
            List<ax.p263sa.c> listN0 = n0(aVarW, str, 0, false);
            if (listN0.size() == 0) {
                return new S(this, str);
            }
            if (!this.k) {
                if (o0(this.q) && (strL = listN0.get(0).l()) != null) {
                    U(strL, this.j, str);
                }
                this.k = true;
            }
            S s = new S(this, listN0.get(0));
            if (zEquals) {
                this.p = s;
            }
            return s;
        } catch (ax.p277ta.a e) {
            if (e.b() == 404) {
                return new S(this, str);
            }
            throw R("webdav getfileinfo", e);
        }
    }

    protected ax.p263sa.a W() {
        return this.n;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        if (this.q != ServerType.TYPE_NEXTCLOUD || str3 == null || !str3.equalsIgnoreCase("haspreview=true")) {
            return t(str, str2);
        }
        try {
            String strH0 = z.H0(c0(), g0(), str2);
            if (strH0 != null) {
                return a0(strH0, 0L);
            }
            return null;
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

    protected String Z(String str) {
        int iV = V(str);
        String strSubstring = str.substring(0, iV);
        String strSubstring2 = str.substring(iV);
        Uri uri = Uri.parse(strSubstring);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.encodedAuthority(uri.getEncodedAuthority());
        builder.path(strSubstring2);
        return builder.build().toString();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (this.q != ServerType.TYPE_NEXTCLOUD) {
            if (E(abstractC5838l)) {
                return AbstractC5837k.x(abstractC5838l);
            }
            return null;
        }
        if (!C0739u.F(abstractC5838l)) {
            return null;
        }
        if (((S) abstractC5838l).e0()) {
            return C0740v.S(abstractC5838l, "haspreview=true");
        }
        if (ax.R1.a.e(n()).m()) {
            return C0740v.Q(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.h;
    }

    protected InputStream a0(String str, long j) throws C0718i {
        HashMap map;
        if (j != 0) {
            map = new HashMap();
            map.put("Range", "bytes=" + j + "-");
        } else {
            map = null;
        }
        ax.p263sa.a aVarW = W();
        if (aVarW == null) {
            throw new C0716g("Not connected : webdav");
        }
        try {
            return map != null ? aVarW.X(str, map) : aVarW.a(str);
        } catch (ax.p277ta.a e) {
            throw R("webdav getinputstream", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            long jO = abstractC5838l.o();
            ax.p263sa.a aVarW = W();
            if (this.m == null) {
                aVarW.S(i0(abstractC5838l), i0(abstractC5838l2), abstractC5838l.q(), false);
            } else {
                aVarW.S(i0(abstractC5838l), d0(abstractC5838l2), abstractC5838l.q(), false);
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (ax.p277ta.a e) {
            throw R("webdav copyFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        try {
            W().O(i0(abstractC5838l));
        } catch (ax.p277ta.a e) {
            if ((e.getCause() instanceof ProtocolException) && e.getCause().getMessage() != null && (e.getCause().getMessage().contains("HTTP 204") || e.getCause().getMessage().contains("HTTP 205"))) {
                return;
            }
            e.printStackTrace();
            throw R("webdav deleteRecursively", e);
        }
    }

    protected String c0() {
        return z.A0(this.i);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i, C0710a {
        u0(abstractC5838l, a2, str, j, l, z, cVar, iVar, f0());
    }

    protected String d0(AbstractC5838l abstractC5838l) {
        return e0(abstractC5838l.C(), abstractC5838l.isDirectory());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        return a0(i0(abstractC5838l), j);
    }

    protected String e0(String str, boolean z) {
        String strK = this.l + str;
        if (z) {
            strK = V.K(strK);
        }
        return Z(strK);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        try {
            ax.p045d2.n nVar = this.o;
            if (nVar != null && !nVar.isCancelled()) {
                this.o.e();
            }
            ax.p045d2.n<Object, Void, Boolean> nVarT = T(activity, fragment, this, q(), aVar);
            nVarT.i(new Object[0]);
            this.o = nVarT;
        } catch (Exception e) {
            e.printStackTrace();
            if (aVar != null) {
                aVar.N();
                aVar.f0(false, e.getMessage());
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return false;
    }

    protected int g0() {
        if (this.s == null) {
            this.s = Integer.valueOf((int) (n().getResources().getDisplayMetrics().density * 128.0f));
        }
        return this.s.intValue();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        try {
            ArrayList arrayList = new ArrayList();
            ax.p263sa.a aVarW = W();
            if (aVarW == null) {
                ax.p221pa.c.h().g().b("NULL DAV CLIENT!").k().h("connected:" + a()).i();
                throw new C0716g("No dav client is available");
            }
            for (ax.p263sa.c cVar : n0(aVarW, abstractC5838l.C(), 1, true)) {
                if (!abstractC5838l.C().equals(S(cVar.l()))) {
                    try {
                        arrayList.add(new S(this, cVar));
                    } catch (C0718i unused) {
                    }
                }
            }
            return arrayList;
        } catch (ax.p277ta.a e) {
            throw R("webdav listChildren", e);
        }
    }

    protected ax.p263sa.a h0(long j) {
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        try {
            W().q(j0(abstractC5838l.C(), true));
            return true;
        } catch (ax.p277ta.a e) {
            e.printStackTrace();
            return false;
        }
    }

    protected String i0(AbstractC5838l abstractC5838l) {
        return j0(abstractC5838l.C(), abstractC5838l.isDirectory());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return j(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    protected boolean j(AbstractC5838l abstractC5838l) {
        return super.j(abstractC5838l);
    }

    protected String j0(String str, boolean z) {
        String strK = this.i + str;
        if (z) {
            strK = V.K(strK);
        }
        return Z(strK);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        m(abstractC5838l, str, z, hVar, cVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            long jO = abstractC5838l.o();
            ax.p263sa.a aVarW = W();
            if (this.m == null) {
                aVarW.U(i0(abstractC5838l), j0(abstractC5838l2.C(), abstractC5838l.isDirectory()), false);
            } else {
                aVarW.U(i0(abstractC5838l), e0(abstractC5838l2.C(), abstractC5838l.isDirectory()), false);
            }
            if (iVar != null) {
                iVar.a(jO, jO);
            }
        } catch (ax.p277ta.a e) {
            throw R("webdav moveFile", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        b1(abstractC5838l);
    }

    public boolean m0() {
        String str = this.i;
        if (str == null) {
            return false;
        }
        if (this.t == null) {
            this.t = Boolean.valueOf(str.startsWith("https://webdav.pcloud.com") || this.i.startsWith("https://ewebdav.pcloud.com"));
        }
        return this.t.booleanValue();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    protected List<ax.p263sa.c> n0(ax.p263sa.a aVar, String str, int i, boolean z) throws ax.p277ta.a {
        if (i != 0 || !m0() || "/".equals(str)) {
            return aVar.V(j0(str, z), i);
        }
        ArrayList arrayList = new ArrayList();
        List<ax.p263sa.c> listV = aVar.V(j0(V.r(str), z), 1);
        if (listV != null) {
            for (ax.p263sa.c cVar : listV) {
                if (cVar.l() != null) {
                    try {
                        String strS = S(cVar.l());
                        if (strS != null && strS.equals(str)) {
                            arrayList.add(cVar);
                        }
                    } catch (C0718i e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }

    void q0(boolean z) {
        this.h = z;
    }

    void r0(ax.p263sa.a aVar) {
        q0(true);
        this.n = aVar;
    }

    void s0(int i) {
        this.q = i;
    }

    void t0(String str, String str2) {
        this.i = str;
        this.j = str2;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public b0 v() throws C0718i {
        if (this.q != ServerType.TYPE_NEXTCLOUD) {
            return null;
        }
        ax.p263sa.a aVarW = W();
        if (aVarW != null) {
            return z.D0(aVarW, c0());
        }
        throw new C0716g("Not connected : webdav");
    }

    private static class b extends ax.p045d2.n<Object, Void, Boolean> {
        InterfaceC5828b.a h;
        String i;
        int j;
        String k;
        String l;
        boolean m;
        String n;
        boolean o;
        Q p;
        Context q;
        ax.p277ta.a r;
        ax.G1.n s;

        public b(Context context, Q q, int i, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.p = q;
            this.h = aVar;
            y(Q.b0(context).k(i));
            this.q = context;
        }

        private void y(ax.G1.n nVar) {
            this.s = nVar;
            this.i = nVar.d();
            this.j = nVar.h();
            this.k = nVar.k();
            this.l = nVar.g();
            this.n = nVar.f();
            this.m = nVar.o();
            this.o = nVar.l();
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
        public Boolean g(Object... objArr) {
            String strQ;
            Q q = this.p;
            if (q != null) {
                q.h();
            }
            Q.l0(this.q);
            try {
                ax.p291ua.a aVarC = ax.p263sa.b.c(ServerType.TYPE_COMMON, this.k, this.l, this.o, 12000L);
                String strP = Q.P(this.s);
                if (TextUtils.isEmpty(this.n)) {
                    strQ = null;
                } else {
                    strQ = V.Q(this.n);
                    strP = strP + strQ;
                }
                String str = strP + "/";
                try {
                    if (HttpUrl.parse(str) == null) {
                        Boolean bool = Boolean.FALSE;
                        Q q2 = this.p;
                        if (q2 != null) {
                            q2.i();
                        }
                        return bool;
                    }
                    int iW = aVarC.W(str);
                    aVarC.Q(iW);
                    Q.u.fine("detected server type : " + iW);
                    Q q3 = this.p;
                    if (q3 != null) {
                        q3.t0(strP, strQ);
                        this.p.r0(aVarC);
                        this.p.s0(iW);
                    }
                    Boolean bool2 = Boolean.TRUE;
                    Q q4 = this.p;
                    if (q4 != null) {
                        q4.i();
                    }
                    return bool2;
                } catch (ax.p277ta.a e) {
                    this.r = e;
                    e.printStackTrace();
                    Boolean bool3 = Boolean.FALSE;
                    Q q5 = this.p;
                    if (q5 != null) {
                        q5.i();
                    }
                    return bool3;
                } catch (RuntimeException e2) {
                    this.r = new ax.p277ta.a(e2);
                    Boolean bool4 = Boolean.FALSE;
                    Q q6 = this.p;
                    if (q6 != null) {
                        q6.i();
                    }
                    return bool4;
                }
            } catch (Throwable th) {
                Q q7 = this.p;
                if (q7 != null) {
                    q7.i();
                }
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            InterfaceC5828b.a aVar = this.h;
            if (aVar != null) {
                aVar.f0(bool.booleanValue(), this.r);
            }
        }

        public b(Context context, ax.G1.n nVar, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = aVar;
            y(nVar);
            this.q = context;
        }
    }
}
