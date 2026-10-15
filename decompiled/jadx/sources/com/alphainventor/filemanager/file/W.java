package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1964h;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.e0;
import ax.p086f3.g0;
import ax.p086f3.k0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
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
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class W extends AbstractC6238m {
    private static final Logger u = Logger.getLogger("FileManager.WebDAVFileHelper");
    private static c v;
    boolean h;
    String i;
    String j;
    boolean k;
    String l;
    String m;
    ax.p350vb.a n;
    protected ax.p406z3.q o;
    AbstractC6239n p;
    int q;
    private boolean r;
    private Integer s;
    private Boolean t;

    public static class a {
        public boolean a;
        public X509Certificate b;

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.W$a$a, reason: collision with other inner class name */
        class C0536a implements X509TrustManager {
            C0536a() {
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

        public void a(Context context, ax.p038c3.o oVar) {
            W.r0(context);
            String str = W.q0(oVar) + "/";
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
                } catch (SSLHandshakeException | SSLPeerUnverifiedException unused) {
                    this.a = false;
                    TrustManager[] trustManagerArr = {new C0536a()};
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

    private static class b extends ax.p406z3.q<Object, Void, Boolean> {
        InterfaceC6228c.a h;
        String i;
        int j;
        String k;
        String l;
        boolean m;
        String n;
        boolean o;
        W p;
        Context q;
        ax.p366wb.a r;
        ax.p038c3.o s;

        public b(Context context, ax.p038c3.o oVar, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.h = aVar;
            y(oVar);
            this.q = context;
        }

        public b(Context context, W w, int i, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.p = w;
            this.h = aVar;
            y(W.h0(context).k(i));
            this.q = context;
        }

        private void y(ax.p038c3.o oVar) {
            this.s = oVar;
            this.i = oVar.d();
            this.j = oVar.h();
            this.k = oVar.k();
            this.l = oVar.g();
            this.n = oVar.f();
            this.m = oVar.o();
            this.o = oVar.l();
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
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            String strU;
            W w = this.p;
            if (w != null) {
                w.i();
            }
            W.r0(this.q);
            try {
                ax.p382xb.a aVarB = ax.p350vb.b.b(ServerType.TYPE_COMMON, this.k, this.l, this.o, 12000L);
                String strV = W.V(this.s);
                if (TextUtils.isEmpty(this.n)) {
                    strU = null;
                } else {
                    strU = d0.U(this.n);
                    if (!"/".equals(strU)) {
                        strV = strV + strU;
                    }
                }
                String str = strV + "/";
                try {
                    try {
                        if (HttpUrl.parse(str) == null) {
                            Boolean bool = Boolean.FALSE;
                            W w2 = this.p;
                            if (w2 != null) {
                                w2.j();
                            }
                            return bool;
                        }
                        int iG = aVarB.g(str);
                        aVarB.b(iG);
                        W.u.fine("detected server type : " + iG);
                        W w3 = this.p;
                        if (w3 != null) {
                            w3.z0(strV, strU);
                            this.p.x0(aVarB);
                            this.p.y0(iG);
                        }
                        Boolean bool2 = Boolean.TRUE;
                        W w4 = this.p;
                        if (w4 != null) {
                            w4.j();
                        }
                        return bool2;
                    } catch (ax.p366wb.a e) {
                        this.r = e;
                        e.printStackTrace();
                        Boolean bool3 = Boolean.FALSE;
                        W w5 = this.p;
                        if (w5 != null) {
                            w5.j();
                        }
                        return bool3;
                    }
                } catch (RuntimeException e2) {
                    this.r = new ax.p366wb.a(e2);
                    Boolean bool4 = Boolean.FALSE;
                    W w6 = this.p;
                    if (w6 != null) {
                        w6.j();
                    }
                    return bool4;
                }
            } catch (Throwable th) {
                W w7 = this.p;
                if (w7 == null) {
                    throw th;
                }
                w7.j();
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            InterfaceC6228c.a aVar = this.h;
            if (aVar != null) {
                aVar.d0(bool.booleanValue(), this.r);
            }
        }
    }

    public static class c extends g0 {
        Context a;
        b b;
        c0 c = new c0(ax.U2.f.P0);

        class a implements InterfaceC6228c.a {
            final /* synthetic */ int X;
            final /* synthetic */ ax.p038c3.o Y;
            final /* synthetic */ ax.p182l3.k q;

            a(ax.p182l3.k kVar, int i, ax.p038c3.o oVar) {
                this.q = kVar;
                this.X = i;
                this.Y = oVar;
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void J() {
                this.q.a(ax.U2.f.P0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void d0(boolean z, Object obj) {
                if (!z) {
                    this.q.c(ax.U2.f.P0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof Exception ? ((Exception) obj).getMessage() : null);
                    return;
                }
                c.this.p(this.X, this.Y);
                ax.U2.f fVar = ax.U2.f.P0;
                C6240o c6240oD = C2017x.d(fVar, this.X);
                if (c6240oD.a()) {
                    ((W) c6240oD.u()).w0(false);
                    ((W) c6240oD.u()).W();
                    ax.p038c3.b.k().s(c6240oD.B(), c6240oD.D());
                }
                this.q.d(fVar, this.X);
            }
        }

        c(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("WebDAVPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("encryption_" + i).remove("ignorecert_" + i).remove("created_" + i).remove("sortindex_" + i).remove("expectcontinue_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            return new ax.p038c3.s(ax.U2.f.P0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), null, sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("WebDAVPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.p086f3.g0
        public ax.p038c3.o k(int i) {
            ax.p038c3.o oVar = new ax.p038c3.o();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            oVar.s(string);
            oVar.z(sharedPreferences.getInt("port_" + i, 443));
            oVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            oVar.y(this.c.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            oVar.v(sharedPreferences.getString("initialPath_" + i, null));
            if (sharedPreferences.getInt("encryption_" + i, 1) == 1) {
                oVar.C(true);
            } else {
                oVar.C(false);
            }
            oVar.u(sharedPreferences.getBoolean("ignorecert_" + i, false));
            oVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            return oVar;
        }

        @Override // ax.p086f3.g0
        public void l(int i, ax.p038c3.o oVar, ax.p182l3.k kVar, boolean z) {
            int iM = m(i);
            if (!z) {
                p(iM, oVar);
                kVar.d(ax.U2.f.P0, iM);
            } else {
                b bVar = new b(this.a, oVar, new a(kVar, iM, oVar));
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

        public List<ax.p038c3.s> o() {
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

        void p(int i, ax.p038c3.o oVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("WebDAVPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, oVar.d()).putInt("port_" + i, oVar.h()).putString("username_" + i, oVar.k()).putString("password_" + i, this.c.e(oVar.d(), oVar.g())).putString("name_" + i, oVar.b()).putString("initialPath_" + i, oVar.f()).putInt("encryption_" + i, oVar.o() ? 1 : 0).putBoolean("ignorecert_" + i, oVar.l());
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

    private void A0(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar, boolean z2) throws C1957a, C1966j {
        boolean z3;
        C2461b.a(abstractC6239n.o());
        ax.p350vb.a aVarN0 = n0(j);
        if (aVarN0 == null) {
            aVarN0 = c0();
        }
        try {
            z3 = z2;
            try {
                aVarN0.c(o0(abstractC6239n), new e0(g.b(), 0L, j, interfaceC2462c, iVar), str, z3, j, l);
                if (l0() != z3) {
                    v0(z3);
                }
                this.r = true;
            } catch (ax.p366wb.a e) {
                e = e;
                if (e.getCause() instanceof C1957a) {
                    throw ((C1957a) e.getCause());
                }
                if ((e.b() == 408 || (e.getCause() instanceof SocketTimeoutException)) && !this.r) {
                    this.r = true;
                    A0(abstractC6239n, g, str, j, l, z, interfaceC2462c, iVar, !z3);
                } else {
                    if (e.getCause() != null && (e.getCause().getCause() instanceof C1957a)) {
                        throw ((C1957a) e.getCause().getCause());
                    }
                    throw X("webdav writeFile", e);
                }
            }
        } catch (ax.p366wb.a e2) {
            e = e2;
            z3 = z2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String V(ax.p038c3.o oVar) {
        if (oVar.o()) {
            return "https://" + oVar.d() + ":" + oVar.h();
        }
        return "http://" + oVar.d() + ":" + oVar.h();
    }

    private int b0(String str) {
        return str.indexOf("/", str.indexOf("://") + 3);
    }

    private boolean d0() {
        String str = this.i;
        return str == null || !str.startsWith("https://magentacloud.de");
    }

    public static int e0() {
        return 443;
    }

    public static c h0(Context context) {
        if (v == null) {
            v = new c(context.getApplicationContext());
        }
        return v;
    }

    private boolean l0() {
        return p().getSharedPreferences("WebDAVPrefs", 0).getBoolean("expectcontinue_" + t(), d0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String q0(ax.p038c3.o oVar) {
        String strV = V(oVar);
        if (TextUtils.isEmpty(oVar.f())) {
            return strV;
        }
        return strV + d0.U(oVar.f());
    }

    protected static void r0(Context context) {
        if (Build.VERSION.SDK_INT < 29) {
            AbstractC6238m.F(context);
        }
    }

    private boolean u0(int i) {
        return i == ServerType.TYPE_COMMON || i == ServerType.TYPE_NGINX;
    }

    private void v0(boolean z) {
        p().getSharedPreferences("WebDAVPrefs", 0).edit().putBoolean("expectcontinue_" + t(), z).apply();
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean G() {
        return this.q == ServerType.TYPE_NEXTCLOUD;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean K() {
        return false;
    }

    public void W() {
        p().getSharedPreferences("WebDAVPrefs", 0).edit().remove("expectcontinue_" + t()).apply();
        this.r = false;
    }

    protected C1966j X(String str, ax.p366wb.a aVar) {
        int iB = aVar.b();
        if (iB == 0) {
            return C1960d.b(str, aVar);
        }
        if (iB == 413) {
            return new ax.p070e3.D(aVar);
        }
        if (iB == 507) {
            return new ax.p070e3.s(aVar);
        }
        switch (iB) {
            case 401:
            case 402:
            case 403:
                return new C1961e(aVar);
            case 404:
                return new ax.p070e3.t(aVar);
            default:
                return new C1966j(aVar);
        }
    }

    protected String Y(String str) throws C1966j {
        String path;
        try {
            path = new URI(str).getPath();
        } catch (URISyntaxException unused) {
            path = Uri.parse(str).getPath();
        }
        if (path == null) {
            ax.p302sb.c.h().g().b("WEBDAV PATH NULL").h("url:" + str + ",prefix:" + this.i + "," + this.j);
            throw new C1966j();
        }
        String str2 = this.m;
        if (str2 != null) {
            if (!path.startsWith(str2)) {
                ax.p302sb.c.h().g().d("WEBDAV PATH ERROR 1").h("url:" + str + ",prefix:" + this.j + ",rawPath:" + path);
                throw new C1966j("Unknown URL");
            }
            path = path.substring(this.m.length());
        } else if (!TextUtils.isEmpty(this.j)) {
            if (!path.startsWith(this.j)) {
                ax.p302sb.c.h().g().d("WEBDAV PATH ERROR 2").h("url:" + str + ",prefix:" + this.j + ",rawPath:" + path);
                throw new C1966j("Unknown URL");
            }
            path = path.substring(this.j.length());
        }
        return d0.U(path);
    }

    protected ax.p406z3.q<Object, Void, Boolean> Z(Activity activity, Fragment fragment, W w, int i, InterfaceC6228c.a aVar) {
        return new b(p(), this, t(), aVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.h;
    }

    protected void a0(String str, String str2, String str3) {
        String path;
        try {
            path = new URI(str).getPath();
        } catch (URISyntaxException unused) {
            path = Uri.parse(str).getPath();
        }
        if (path == null) {
            ax.p302sb.c.h().g().b("WEBDAV PATH NULL DETCT PROXY").h("url:" + str + ",prefix:" + this.i + "," + this.j);
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

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        String strK;
        AbstractC6239n abstractC6239n;
        boolean zEquals = str.equals("/");
        if (zEquals && (abstractC6239n = this.p) != null) {
            return abstractC6239n;
        }
        try {
            ax.p350vb.a aVarC0 = c0();
            if (aVarC0 == null) {
                throw new C1964h("Not connected : webdav");
            }
            List<ax.p350vb.c> listT0 = t0(aVarC0, str, 0, false);
            if (listT0.size() == 0) {
                return new X(this, str);
            }
            if (!this.k) {
                if (u0(this.q) && (strK = listT0.get(0).k()) != null) {
                    a0(strK, this.j, str);
                }
                this.k = true;
            }
            X x = new X(this, listT0.get(0));
            if (zEquals) {
                this.p = x;
            }
            return x;
        } catch (ax.p366wb.a e) {
            if (e.b() == 404) {
                return new X(this, str);
            }
            throw X("webdav getfileinfo", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        if (this.q != ServerType.TYPE_NEXTCLOUD || str3 == null || !str3.equalsIgnoreCase("haspreview=true")) {
            return w(str, str2);
        }
        try {
            String strN0 = D.N0(i0(), m0(), str2);
            if (strN0 != null) {
                return g0(strN0, 0L);
            }
            return null;
        } catch (C1966j unused) {
            return null;
        }
    }

    protected ax.p350vb.a c0() {
        return this.n;
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
        C2461b.c(abstractC6239n.isDirectory());
        try {
            ArrayList arrayList = new ArrayList();
            ax.p350vb.a aVarC0 = c0();
            if (aVarC0 == null) {
                ax.p302sb.c.h().g().b("NULL DAV CLIENT!").k().h("connected:" + a()).i();
                throw new C1964h("No dav client is available");
            }
            for (ax.p350vb.c cVar : t0(aVarC0, abstractC6239n.E(), 1, true)) {
                if (!abstractC6239n.E().equals(Y(cVar.k()))) {
                    try {
                        arrayList.add(new X(this, cVar));
                    } catch (C1966j unused) {
                    }
                }
            }
            return arrayList;
        } catch (ax.p366wb.a e) {
            throw X("webdav listChildren", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    protected String f0(String str) {
        int iB0 = b0(str);
        String strSubstring = str.substring(0, iB0);
        String strSubstring2 = str.substring(iB0);
        Uri uri = Uri.parse(strSubstring);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.encodedAuthority(uri.getEncodedAuthority());
        builder.path(strSubstring2);
        return builder.build().toString();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (this.q != ServerType.TYPE_NEXTCLOUD) {
            if (H(abstractC6239n)) {
                return AbstractC6238m.A(abstractC6239n);
            }
            return null;
        }
        if (!ax.p086f3.A.J(abstractC6239n)) {
            return null;
        }
        if (((X) abstractC6239n).h0()) {
            return ax.p086f3.B.a0(abstractC6239n, "haspreview=true");
        }
        if (ax.p214n3.a.e(p()).m()) {
            return ax.p086f3.B.Y(abstractC6239n);
        }
        return null;
    }

    protected InputStream g0(String str, long j) throws C1966j {
        HashMap map;
        if (j != 0) {
            map = new HashMap();
            map.put("Range", "bytes=" + j + "-");
        } else {
            map = null;
        }
        ax.p350vb.a aVarC0 = c0();
        if (aVarC0 == null) {
            throw new C1964h("Not connected : webdav");
        }
        try {
            return map != null ? aVarC0.h(str, map) : aVarC0.a(str);
        } catch (ax.p366wb.a e) {
            throw X("webdav getinputstream", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        try {
            c0().S(o0(abstractC6239n));
        } catch (ax.p366wb.a e) {
            if ((e.getCause() instanceof ProtocolException) && e.getCause().getMessage() != null && (e.getCause().getMessage().contains("HTTP 204") || e.getCause().getMessage().contains("HTTP 205"))) {
                return;
            }
            e.printStackTrace();
            throw X("webdav deleteRecursively", e);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j) throws C1966j {
        return g0(o0(abstractC6239n), j);
    }

    protected String i0() {
        return D.G0(this.i);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1966j {
        C2461b.a(abstractC6239n2.o());
        try {
            long jP = abstractC6239n.p();
            ax.p350vb.a aVarC0 = c0();
            if (this.m == null) {
                aVarC0.e(o0(abstractC6239n), p0(abstractC6239n2.E(), abstractC6239n.isDirectory()), false);
            } else {
                aVarC0.e(o0(abstractC6239n), k0(abstractC6239n2.E(), abstractC6239n.isDirectory()), false);
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (ax.p366wb.a e) {
            throw X("webdav moveFile", e);
        }
    }

    protected String j0(AbstractC6239n abstractC6239n) {
        return k0(abstractC6239n.E(), abstractC6239n.isDirectory());
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0037  */
    /* JADX WARN: Code duplicated, block: B:29:? A[RETURN, SYNTHETIC] */
    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        InterfaceC6228c.a aVar2;
        Exception exc;
        try {
            ax.p406z3.q qVar = this.o;
            if (qVar != null) {
                try {
                    if (!qVar.isCancelled()) {
                        this.o.e();
                    }
                } catch (Exception e) {
                    exc = e;
                    aVar2 = aVar;
                    exc.printStackTrace();
                    if (aVar2 != null) {
                        aVar2.J();
                        aVar2.d0(false, exc.getMessage());
                    }
                }
            }
            aVar2 = aVar;
            try {
                ax.p406z3.q<Object, Void, Boolean> qVarZ = Z(activity, fragment, this, t(), aVar2);
                qVarZ.i(new Object[0]);
                this.o = qVarZ;
            } catch (Exception e2) {
                e = e2;
                exc = e;
                exc.printStackTrace();
                if (aVar2 != null) {
                    aVar2.J();
                    aVar2.d0(false, exc.getMessage());
                }
            }
        } catch (Exception e3) {
            e = e3;
            aVar2 = aVar;
        }
    }

    protected String k0(String str, boolean z) {
        String strO = this.l + str;
        if (z) {
            strO = d0.O(strO);
        }
        return f0(strO);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    protected boolean l(AbstractC6239n abstractC6239n) {
        return super.l(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g, String str, long j, Long l, C6241p c6241p, boolean z, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        A0(abstractC6239n, g, str, j, l, z, interfaceC2462c, iVar, l0());
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    void m(AbstractC6239n abstractC6239n, String str, boolean z, boolean z2, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws C1966j {
        o(abstractC6239n, str, z, z2, hVar, interfaceC2462c);
    }

    protected int m0() {
        if (this.s == null) {
            this.s = Integer.valueOf((int) (ax.p406z3.B.s(p()) * 128.0f));
        }
        return this.s.intValue();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void m1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        try {
            long jP = abstractC6239n.p();
            ax.p350vb.a aVarC0 = c0();
            if (this.m == null) {
                aVarC0.d(o0(abstractC6239n), o0(abstractC6239n2), abstractC6239n.q(), false);
            } else {
                aVarC0.d(o0(abstractC6239n), j0(abstractC6239n2), abstractC6239n.q(), false);
            }
            if (iVar != null) {
                iVar.a(jP, jP);
            }
        } catch (ax.p366wb.a e) {
            throw X("webdav copyFile", e);
        }
    }

    protected ax.p350vb.a n0(long j) {
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean n1(AbstractC6239n abstractC6239n) {
        try {
            c0().o(p0(abstractC6239n.E(), true));
            return true;
        } catch (ax.p366wb.a e) {
            e.printStackTrace();
            return false;
        }
    }

    protected String o0(AbstractC6239n abstractC6239n) {
        return p0(abstractC6239n.E(), abstractC6239n.isDirectory());
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return l(abstractC6239n);
    }

    protected String p0(String str, boolean z) {
        String strO = this.i + str;
        if (z) {
            strO = d0.O(strO);
        }
        return f0(strO);
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

    public boolean s0() {
        String str = this.i;
        if (str == null) {
            return false;
        }
        if (this.t == null) {
            this.t = Boolean.valueOf(str.startsWith("https://webdav.pcloud.com") || this.i.startsWith("https://ewebdav.pcloud.com"));
        }
        return this.t.booleanValue();
    }

    protected List<ax.p350vb.c> t0(ax.p350vb.a aVar, String str, int i, boolean z) throws ax.p366wb.a {
        if (i != 0 || !s0() || "/".equals(str)) {
            return aVar.f(p0(str, z), i);
        }
        ArrayList arrayList = new ArrayList();
        List<ax.p350vb.c> listF = aVar.f(p0(d0.r(str), z), 1);
        if (listF != null) {
            for (ax.p350vb.c cVar : listF) {
                if (cVar.k() != null) {
                    try {
                        String strY = Y(cVar.k());
                        if (strY != null && strY.equals(str)) {
                            arrayList.add(cVar);
                        }
                    } catch (C1966j e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return arrayList;
    }

    void w0(boolean z) {
        this.h = z;
    }

    void x0(ax.p350vb.a aVar) {
        w0(true);
        this.n = aVar;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public k0 y() throws C1966j {
        if (this.q != ServerType.TYPE_NEXTCLOUD) {
            return null;
        }
        ax.p350vb.a aVarC0 = c0();
        if (aVarC0 != null) {
            return D.J0(aVarC0, i0());
        }
        throw new C1964h("Not connected : webdav");
    }

    void y0(int i) {
        this.q = i;
    }

    void z0(String str, String str2) {
        this.i = str;
        this.j = str2;
    }
}
