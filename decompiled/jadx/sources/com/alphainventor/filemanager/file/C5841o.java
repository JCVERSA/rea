package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0714e;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.I1.C0719j;
import ax.J1.C0732m;
import ax.J1.C0742x;
import ax.J1.C0744z;
import ax.J1.U;
import ax.J1.V;
import ax.J1.Y;
import j$.util.DesugarTimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C5841o extends AbstractC5837k {
    static g v;
    private SimpleDateFormat h;
    private d i;
    private ax.p293uc.e j;
    private e l;
    private String m;
    private String n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private int s;
    private static final Logger u = Logger.getLogger("FileManager.FtpFileHelper");
    private static int w = 0;
    private static int x = 1;
    private static int y = 2;
    private final Object k = new Object();
    private boolean t = false;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$a */
    class a implements ax.p293uc.l {
        final /* synthetic */ String a;

        a(String str) {
            this.a = str;
        }

        @Override // ax.p293uc.l
        public boolean a(ax.p293uc.i iVar) {
            if (iVar == null) {
                return false;
            }
            return iVar.b().trim().equals(this.a);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$b */
    class b implements ax.P1.i {
        final /* synthetic */ ax.P1.i a;

        b(ax.P1.i iVar) {
            this.a = iVar;
        }

        @Override // ax.P1.i
        public void a(long j, long j2) {
            ax.P1.i iVar = this.a;
            if (iVar != null) {
                iVar.a(j / 2, j2);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$c */
    class c implements ax.P1.i {
        final /* synthetic */ ax.P1.i a;

        c(ax.P1.i iVar) {
            this.a = iVar;
        }

        @Override // ax.P1.i
        public void a(long j, long j2) {
            ax.P1.i iVar = this.a;
            if (iVar != null) {
                if (j == j2) {
                    iVar.a(j2, j2);
                } else {
                    iVar.a((j2 / 2) + (j / 2), j2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$e */
    class e extends ax.p045d2.q<ax.p293uc.e> {
        e() {
        }

        public ax.p293uc.e f() throws ax.I1.I {
            ax.p293uc.e eVar = (ax.p293uc.e) super.a();
            return (eVar == null || eVar.w()) ? eVar : d();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.q
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void c(ax.p293uc.e eVar) {
            C5841o.X(eVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.q
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public ax.p293uc.e d() throws ax.I1.I {
            try {
                d dVar = new d(C5841o.this.n(), null, C5841o.this.q(), null);
                if (dVar.h(new Object[0]).l().booleanValue()) {
                    return dVar.z();
                }
                if (!dVar.B()) {
                    ax.p221pa.c.h().g().c("FTP CHANNEL OPEN ERROR 1").h("msg:" + dVar.y()).i();
                }
                throw new ax.I1.I("Could not create transfer connection");
            } catch (ax.I1.I e) {
                throw e;
            } catch (Exception e2) {
                ax.p221pa.c.h().g().d("FTP CHANNEL OPEN ERROR 2").m(e2).i();
                e2.printStackTrace();
                throw new ax.I1.I("Could not create transfer connection", e2);
            }
        }

        public void i(ax.p293uc.e eVar) {
            if (eVar == null) {
                C5841o.u.severe("Try to release null ftpclient");
            } else if (eVar.w()) {
                super.e(eVar);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$f */
    class f extends ax.J1.B {
        ax.p293uc.e X;
        boolean Y;

        f(ax.p293uc.e eVar, InputStream inputStream) {
            super(inputStream);
            this.X = eVar;
        }

        @Override // ax.J1.B, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.Y) {
                return;
            }
            super.close();
            this.Y = true;
            if (this.X.w()) {
                this.X.A0();
                this.X.C0();
                if (this.X != C5841o.this.j) {
                    C5841o.this.l.i(this.X);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$g */
    public static class g extends Y {
        Context a;
        d b;
        int d = 100000000;
        HashMap<Integer, k> e = new HashMap<>();
        U c = new U(ax.p338y1.f.J0);

        /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$g$a */
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
                this.q.b(ax.p338y1.f.J0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
            public void f0(boolean z, Object obj) {
                if (!z) {
                    this.q.a(ax.p338y1.f.J0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                g.this.s(this.X, this.Y);
                ax.p338y1.f fVar = ax.p338y1.f.J0;
                C5839m c5839mE = ax.J1.r.e(fVar, this.X);
                new AbstractC5837k.c(c5839mE.t()).i(new Long[0]);
                ax.G1.b.k().s(c5839mE.z(), c5839mE.B());
                this.q.d(fVar, this.X);
            }
        }

        g(Context context) {
            this.a = context;
        }

        private int o(ax.X1.g gVar) {
            String strE = gVar.e();
            InetAddress inetAddressG = gVar.g();
            int i = gVar.i();
            for (Map.Entry<Integer, k> entry : this.e.entrySet()) {
                ax.G1.n nVar = entry.getValue().a;
                if (ax.p045d2.x.i(nVar.b(), strE) && ax.p045d2.x.i(nVar.d(), inetAddressG.getHostAddress()) && nVar.h() == i) {
                    return entry.getKey().intValue();
                }
            }
            return 0;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("FTPPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("mode_" + i).remove("security_" + i).remove("charset_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            if (C5841o.f0(i)) {
                ax.G1.n nVarK = k(i);
                return nVarK == null ? new ax.G1.p(ax.p338y1.f.J0, i, null, HttpUrl.FRAGMENT_ENCODE_SET, null, null, 0L, 0L) : new ax.G1.p(ax.p338y1.f.J0, i, nVarK.b(), HttpUrl.FRAGMENT_ENCODE_SET, null, null, 0L, 0L);
            }
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            return new ax.G1.p(ax.p338y1.f.J0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("FTPPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.J1.Y
        public ax.G1.n k(int i) {
            if (C5841o.f0(i)) {
                k kVar = this.e.get(Integer.valueOf(i));
                if (kVar != null) {
                    return kVar.a;
                }
                return null;
            }
            ax.G1.n nVar = new ax.G1.n();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            nVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            nVar.s(string);
            nVar.z(sharedPreferences.getInt("port_" + i, 21));
            nVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            nVar.y(this.c.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            nVar.v(sharedPreferences.getString("initialPath_" + i, null));
            nVar.w(sharedPreferences.getBoolean("mode_" + i, false));
            nVar.p(sharedPreferences.getString("charset_" + i, null));
            int i3 = sharedPreferences.getInt("security_" + i, -1);
            if (i3 < 0) {
                nVar.C(false);
            } else {
                nVar.C(true);
                nVar.x(i3 > 0);
            }
            return nVar;
        }

        @Override // ax.J1.Y
        public void l(int i, ax.G1.n nVar, ax.P1.j jVar, boolean z) {
            if (i == -100) {
                i = n();
            }
            if (!z) {
                s(i, nVar);
                jVar.d(ax.p338y1.f.J0, i);
            } else {
                d dVar = new d(this.a, nVar, new a(jVar, i, nVar));
                this.b = dVar;
                dVar.h(new Object[0]);
            }
        }

        public void m(int i) {
            k kVar = this.e.get(Integer.valueOf(i));
            if (kVar != null) {
                kVar.c = false;
            }
        }

        int n() {
            return this.a.getSharedPreferences("FTPPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> p() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        public int q(ax.X1.g gVar) {
            k kVar;
            String strH = gVar.h();
            int iO = o(gVar);
            if (iO == 0 || (kVar = this.e.get(Integer.valueOf(iO))) == null || !ax.p045d2.x.i(strH, kVar.b) || !kVar.c) {
                return 0;
            }
            return iO;
        }

        public int r(ax.X1.g gVar, String str, String str2) {
            k kVar;
            String strE = gVar.e();
            InetAddress inetAddressG = gVar.g();
            int i = gVar.i();
            String strH = gVar.h();
            int iO = o(gVar);
            if (iO != 0 && (kVar = this.e.get(Integer.valueOf(iO))) != null) {
                if (!ax.p045d2.x.i(kVar.a.k(), str) || !ax.p045d2.x.i(kVar.a.g(), str2)) {
                    kVar.a.D(str);
                    kVar.a.y(str2);
                    kVar.b = strH;
                    kVar.c = false;
                } else if (!ax.p045d2.x.i(strH, kVar.b)) {
                    kVar.b = strH;
                    kVar.c = false;
                }
                return iO;
            }
            int i2 = this.d;
            this.d = i2 + 1;
            ax.G1.n nVar = new ax.G1.n();
            nVar.q(strE);
            nVar.s(inetAddressG.getHostAddress());
            nVar.z(i);
            nVar.D(str);
            nVar.y(str2);
            nVar.w(false);
            nVar.p(null);
            nVar.C(false);
            this.e.put(Integer.valueOf(i2), new k(nVar, strH));
            return i2;
        }

        void s(int i, ax.G1.n nVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("FTPPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, nVar.d()).putInt("port_" + i, nVar.h()).putString("username_" + i, nVar.k()).putString("password_" + i, this.c.e(nVar.d(), nVar.g())).putString("name_" + i, nVar.b()).putString("initialPath_" + i, nVar.f()).putString("charset_" + i, nVar.a()).putBoolean("mode_" + i, nVar.m());
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (nVar.o()) {
                editorEdit.putInt("security_" + i, nVar.n() ? 1 : 0);
            } else {
                editorEdit.putInt("security_" + i, -1);
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
        }

        public void t(int i) {
            k kVar = this.e.get(Integer.valueOf(i));
            if (kVar != null) {
                kVar.c = true;
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$h */
    interface h {
        void a(int i);

        void b(int i);

        int c();

        String e();

        boolean f();

        void g() throws IOException;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$i */
    static class i extends ax.p293uc.e implements h {
        boolean g0;
        int h0;
        String i0;
        int j0;

        i() {
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void a(int i) {
            this.g0 = true;
            this.h0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void b(int i) {
            this.j0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public int c() {
            return this.j0;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public String e() {
            return this.i0;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public boolean f() {
            try {
                E();
                return true;
            } catch (SocketTimeoutException | IOException unused) {
                return false;
            }
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void g() throws IOException {
            if (Z0("UTF8") || Z0("UTF-8")) {
                m0("UTF-8");
                this.x = new ax.p321wc.a(new InputStreamReader(this.e, O()));
                this.y = new BufferedWriter(new OutputStreamWriter(this.f, O()));
            }
        }

        @Override // ax.p293uc.e, ax.p279tc.e
        protected void i() throws IOException {
            super.i();
            if (R() == 220) {
                this.i0 = S();
            }
        }

        @Override // ax.p293uc.e
        protected Socket v0(String str, String str2) throws IOException {
            int i;
            try {
                Socket socketV0 = super.v0(str, str2);
                if (socketV0 == null) {
                    return null;
                }
                if (socketV0.getSendBufferSize() > 1048576) {
                    socketV0.setSendBufferSize(524288);
                }
                if (this.g0 && (i = this.h0) > 0) {
                    socketV0.setSoLinger(true, i);
                    C5841o.u.fine("set so linger:" + socketV0.getSoLinger());
                }
                return socketV0;
            } catch (StringIndexOutOfBoundsException e) {
                throw new IOException(e);
            }
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$j */
    static class j extends ax.p293uc.u implements h {
        Object A0;
        Object B0;
        String w0;
        boolean x0;
        int y0;
        int z0;

        j(boolean z) {
            super(z);
        }

        private boolean S1() {
            if (Build.VERSION.SDK_INT <= 28) {
                return true;
            }
            Socket socket = this.b;
            if (socket == null) {
                return false;
            }
            String name = ((SSLSocket) socket).getSession().getClass().getName();
            if ("com.android.org.conscrypt.Java8ExtendedSSLSession".equals(name)) {
                return false;
            }
            if ("com.google.android.gms.org.conscrypt.Java8ExtendedSSLSession".equals(name)) {
                return true;
            }
            ax.p045d2.b.e("new session class detected");
            return true;
        }

        private boolean T1(SSLSocket sSLSocket) {
            Object obj;
            Socket socket = this.b;
            if (socket == null) {
                return false;
            }
            SSLSession session = ((SSLSocket) socket).getSession();
            if (!session.isValid()) {
                return false;
            }
            SSLSessionContext sessionContext = session.getSessionContext();
            try {
                Field declaredField = sessionContext.getClass().getDeclaredField("sessionsByHostAndPort");
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(sessionContext);
                int peerPort = session.getPeerPort();
                Set setKeySet = ((HashMap) obj2).keySet();
                if (setKeySet.size() == 0) {
                    C5841o.u.severe("invalid SSL session 1");
                    return false;
                }
                Field declaredField2 = setKeySet.toArray()[0].getClass().getDeclaredField("port");
                declaredField2.setAccessible(true);
                int i = 0;
                while (i < setKeySet.size() && ((Integer) declaredField2.get(setKeySet.toArray()[i])).intValue() != peerPort) {
                    i++;
                }
                if (i >= setKeySet.size()) {
                    if (this.A0 == null || (obj = this.B0) == null) {
                        C5841o.u.severe("invalid SSL session 2");
                        return false;
                    }
                    Constructor<?> declaredConstructor = obj.getClass().getDeclaredConstructor(String.class, Integer.TYPE);
                    declaredConstructor.setAccessible(true);
                    Object objNewInstance = declaredConstructor.newInstance(sSLSocket.getInetAddress().getHostName(), Integer.valueOf(sSLSocket.getPort()));
                    Object objNewInstance2 = declaredConstructor.newInstance(sSLSocket.getInetAddress().getHostAddress(), Integer.valueOf(sSLSocket.getPort()));
                    Method declaredMethod = obj2.getClass().getDeclaredMethod("put", Object.class, Object.class);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(obj2, objNewInstance, this.A0);
                    declaredMethod.invoke(obj2, objNewInstance2, this.A0);
                    return true;
                }
                Object obj3 = setKeySet.toArray()[i];
                Constructor<?> declaredConstructor2 = obj3.getClass().getDeclaredConstructor(String.class, Integer.TYPE);
                declaredConstructor2.setAccessible(true);
                Object objNewInstance3 = declaredConstructor2.newInstance(sSLSocket.getInetAddress().getHostName(), Integer.valueOf(sSLSocket.getPort()));
                Object objNewInstance4 = declaredConstructor2.newInstance(sSLSocket.getInetAddress().getHostAddress(), Integer.valueOf(sSLSocket.getPort()));
                Object obj4 = ((HashMap) obj2).get(obj3);
                if (obj4 instanceof ArrayList) {
                    this.B0 = obj3;
                    this.A0 = new ArrayList((ArrayList) obj4);
                }
                Method declaredMethod2 = obj2.getClass().getDeclaredMethod("put", Object.class, Object.class);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(obj2, objNewInstance3, obj4);
                declaredMethod2.invoke(obj2, objNewInstance4, obj4);
                return true;
            } catch (NoSuchFieldException unused) {
                C5841o.u.severe("This device is not supported!!!! : api " + Build.VERSION.SDK_INT);
                return false;
            } catch (Exception e) {
                C5841o.u.severe("Session reuse : unknown exception");
                e.printStackTrace();
                return false;
            }
        }

        @Override // ax.p293uc.u
        protected void F1(Socket socket) throws IOException {
            int i;
            if (socket == null) {
                return;
            }
            if ((socket instanceof SSLSocket) && S1()) {
                T1((SSLSocket) socket);
            }
            if (socket.getSendBufferSize() > 1048576) {
                socket.setSendBufferSize(524288);
            }
            if (!this.x0 || (i = this.y0) <= 0) {
                return;
            }
            socket.setSoLinger(true, i);
        }

        @Override // ax.p293uc.u
        protected void J1() throws IOException {
            if (R() == 220) {
                this.w0 = S();
            }
            super.J1();
        }

        @Override // ax.p293uc.e
        public String Q0() {
            String str = this.c;
            return str != null ? str : super.Q0();
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void a(int i) {
            this.x0 = true;
            this.y0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void b(int i) {
            this.z0 = i;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public int c() {
            return this.z0;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public String e() {
            return this.w0;
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public boolean f() {
            try {
                E();
                return true;
            } catch (SocketTimeoutException | IOException unused) {
                return false;
            }
        }

        @Override // com.alphainventor.filemanager.file.C5841o.h
        public void g() throws IOException {
            if (Z0("UTF8") || Z0("UTF-8")) {
                m0("UTF-8");
                this.x = new ax.p321wc.a(new InputStreamReader(this.b.getInputStream(), O()));
                this.y = new BufferedWriter(new OutputStreamWriter(this.b.getOutputStream(), O()));
            }
        }

        j(boolean z, SSLContext sSLContext) {
            super(z, sSLContext);
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$k */
    public static class k {
        final ax.G1.n a;
        String b;
        boolean c;

        public k(ax.G1.n nVar, String str) {
            this.a = nVar;
            this.b = str;
        }
    }

    private void Q() {
        try {
            new d(n(), this, q(), null).h(new Object[0]).l();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private boolean R(String str) throws IOException {
        if (str.equals(this.n)) {
            return true;
        }
        return ax.p293uc.t.c(S(this.j, str));
    }

    private int S(ax.p293uc.e eVar, String str) throws IOException {
        boolean zB0 = eVar.B0(V.K(str));
        int iR = eVar.R();
        if (zB0) {
            if (eVar == this.j) {
                this.n = str;
            }
        } else if (iR == 550 && "/".equals(str)) {
            do {
                try {
                    String strR1 = eVar.r1();
                    if (strR1 == null) {
                        break;
                    }
                    if ("/".equals(strR1)) {
                        return 250;
                    }
                } catch (IOException unused) {
                }
            } while (ax.p293uc.t.c(eVar.I()));
        }
        return iR;
    }

    private boolean T(String str) throws IOException {
        if ("/".equals(str)) {
            return true;
        }
        return str != null && str.equals(this.m) && ax.p293uc.t.c(S(this.j, str));
    }

    private C0718i U(String str, IOException iOException) {
        return iOException instanceof ax.p293uc.h ? new ax.I1.p(iOException) : C0712c.b(str, iOException);
    }

    private C0718i V(String str, int i2, String str2, boolean z) {
        String str3 = str + " (" + str2 + ")";
        if (i2 == 550) {
            if (str2 == null) {
                return z ? new ax.I1.s(str3) : new C0713d(str3);
            }
            String lowerCase = str2.toLowerCase();
            if (lowerCase.contains("no such") || lowerCase.contains("not found")) {
                return new ax.I1.s(str3);
            }
            if (lowerCase.contains("access") || lowerCase.contains("permission")) {
                return new C0713d(str3);
            }
            if (lowerCase.contains("not empty")) {
                return new C0719j(str3);
            }
            if (lowerCase.contains("already exist")) {
                return new C0714e(false);
            }
            ax.p045d2.b.e(str2);
            return z ? new ax.I1.s(str3) : new C0713d(str3);
        }
        if (i2 == 452) {
            return new ax.I1.r(str3);
        }
        if (i2 == 552) {
            return new ax.I1.r(str3);
        }
        if (i2 != 553) {
            if (i2 == 425) {
                return new ax.I1.p(str3);
            }
            return i2 == 426 ? new ax.I1.p(str3) : new C0718i(str3);
        }
        if (str2 != null) {
            String lowerCase2 = str2.toLowerCase();
            if (lowerCase2.contains("access") || lowerCase2.contains("permission")) {
                return new C0713d(str3);
            }
            if (lowerCase2.contains("name")) {
                return new ax.I1.n(str3);
            }
        }
        return new C0713d(str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void X(ax.p293uc.e eVar) {
        if (eVar == null || !eVar.w()) {
            return;
        }
        try {
            eVar.o();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public static int Y() {
        return 21;
    }

    private ax.p293uc.i Z(String str) throws IOException {
        if ("/".equals(str)) {
            return null;
        }
        String strH = V.h(str);
        int iS = S(this.j, V.r(str));
        if (!ax.p293uc.t.c(iS)) {
            if (iS == 550) {
                return null;
            }
            throw new IOException("ChangeWorkingDirectory Error :" + this.j.R());
        }
        try {
            ax.p293uc.i[] iVarArrL1 = this.j.l1(null, new a(strH));
            if (iVarArrL1.length == 0) {
                return null;
            }
            return iVarArrL1[0];
        } catch (IllegalStateException e2) {
            ax.p221pa.c.h().g().d("FTP ILLEGALSTATE").m(e2).h("mode: " + this.j.K0()).i();
            throw new IOException("IllegalState : " + e2.getMessage());
        }
    }

    private String a0(long j2) {
        if (this.h == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
            this.h = simpleDateFormat;
            simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
        }
        return this.h.format(new Date(j2));
    }

    public static g b0(Context context) {
        if (v == null) {
            v = new g(context.getApplicationContext());
        }
        return v;
    }

    protected static void c0(Context context) {
        if (i0()) {
            AbstractC5837k.C(context);
        }
    }

    private boolean e0(int i2) {
        return ax.p293uc.t.a(i2) || ax.p293uc.t.b(i2);
    }

    public static boolean f0(int i2) {
        return i2 >= 100000000;
    }

    private static boolean i0() {
        return Build.VERSION.SDK_INT > 28;
    }

    public static ax.p293uc.i j0(String str, String str2) throws IOException {
        if (str.charAt(0) != ' ') {
            String lowerCase = str.toLowerCase(Locale.ROOT);
            if (str.startsWith("250-modify")) {
                return ax.p307vc.g.g(str.substring(4));
            }
            if (lowerCase.startsWith("250 end")) {
                return null;
            }
            if (str.length() >= 2) {
                if (str.endsWith("; ") && str.startsWith("Size=") && "/".equals(str2)) {
                    String[] strArrSplit = str.split(" ", 2);
                    if (strArrSplit.length == 2 && strArrSplit[1].length() == 0) {
                        str = str + str2;
                    }
                }
                ax.p293uc.i iVarG = ax.p307vc.g.g(str);
                if (iVarG != null) {
                    return iVarG;
                }
            }
        }
        if (str.equals(" /") && "/".equals(str2)) {
            ax.p293uc.i iVar = new ax.p293uc.i();
            iVar.q(1);
            iVar.l(str2);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(0L);
            iVar.n(str);
            iVar.p(calendar);
            return iVar;
        }
        if (str.charAt(0) != ' ') {
            str = " " + str;
        }
        if (str.length() >= 3) {
            return ax.p307vc.g.g(str.replaceAll("^\\s+", HttpUrl.FRAGMENT_ENCODE_SET));
        }
        throw new ax.p279tc.a("Invalid server reply (MLST): '" + str + "'");
    }

    private void k0() {
        b();
        Q();
    }

    private boolean o0() {
        return this.s != x;
    }

    private boolean p0() throws IOException {
        return this.j.Z0("MFMT");
    }

    private boolean q0() throws IOException {
        return this.j.Z0("MLSD") || this.j.Z0("MLST");
    }

    private boolean r0() throws IOException {
        return this.j.Z0("MLST");
    }

    private void s0() {
        boolean zW1;
        boolean z;
        synchronized (this.k) {
            try {
                zW1 = true;
                z = (a() && this.j.v()) ? false : true;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z) {
            Q();
            return;
        }
        synchronized (this.k) {
            try {
                zW1 = true ^ this.j.w1();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            if (zW1) {
                k0();
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean A() {
        return this.t;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean K() {
        return !this.q;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        ax.p293uc.i iVarH0;
        s0();
        try {
            synchronized (this.k) {
                if (r0()) {
                    try {
                        try {
                            iVarH0 = h0(this.j, str);
                        } catch (ax.p279tc.a e2) {
                            e2.printStackTrace();
                            if (!"/".equals(str)) {
                                ax.p221pa.c.h().g().d("FTP INVALID REPLY!!!! FIX CODE FOR THIS CASE").m(e2).h(this.j.S() + ",path:" + str + " ,welcomeMessage:" + this.o).i();
                            }
                            iVarH0 = null;
                        }
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        iVarH0 = null;
                    } catch (RuntimeException e4) {
                        e4.printStackTrace();
                        ax.p221pa.c.h().g().d("FTP MLST ERROR").m(e4).h(this.j.S()).i();
                        iVarH0 = null;
                    }
                } else {
                    iVarH0 = null;
                }
                if (iVarH0 == null) {
                    iVarH0 = Z(str);
                } else {
                    String strTrim = iVarH0.b().trim();
                    if (!V.x(strTrim)) {
                        strTrim = V.h(V.Q(strTrim));
                        iVarH0.l(strTrim);
                    }
                    if (!strTrim.equals(V.h(str))) {
                        iVarH0 = Z(str);
                    }
                }
                ax.p293uc.i iVar = iVarH0;
                if (iVar == null) {
                    return new C0742x(this, str, T(str));
                }
                return new C0742x(this, this.j, iVar, str, true);
            }
        } catch (ax.p307vc.n e5) {
            ax.p221pa.c.h().d("PARSER ERROR").m(e5).i();
            throw new C0718i(e5);
        } catch (IOException e6) {
            throw U("FTP getfileinfo", e6);
        } catch (ArrayIndexOutOfBoundsException e7) {
            throw new C0718i(e7);
        } catch (NullPointerException e8) {
            throw new C0718i(e8);
        }
    }

    void W(String str, String str2) {
        this.o = str;
        this.p = str2;
        this.s = w;
        if (str != null) {
            if (str.contains("FileZilla Server")) {
                this.s = x;
                return;
            }
            if (str.contains("ESP8266")) {
                this.s = y;
                n0(true);
            } else if (str.contains("lima-city.de")) {
                this.t = true;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
        if (this.q) {
            return null;
        }
        return t(str, str2);
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
        if (!this.q && E(abstractC5838l)) {
            return AbstractC5837k.x(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        ax.p293uc.e eVar = this.j;
        return eVar != null && eVar.w();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        if (!this.q) {
            c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
            return;
        }
        File fileY = abstractC5838l.Y();
        C5839m c5839mF = ax.J1.r.f(fileY);
        AbstractC5838l abstractC5838lV0 = c5839mF.V0(fileY.getAbsolutePath());
        boolean zN = abstractC5838lV0.n();
        c5839mF.c1(abstractC5838lV0, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, new b(iVar));
        AbstractC5838l abstractC5838lV1 = c5839mF.V0(fileY.getAbsolutePath());
        c1(abstractC5838l2, c5839mF.v(abstractC5838lV1), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, new c(iVar));
        if (zN) {
            return;
        }
        try {
            c5839mF.l1(abstractC5838lV1);
        } catch (C0718i unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        synchronized (this.k) {
            try {
                this.n = null;
                X(this.j);
                e eVar = this.l;
                if (eVar != null) {
                    eVar.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    /* JADX WARN: Code duplicated, block: B:114:0x01a0 A[Catch: all -> 0x010e, TRY_ENTER, TryCatch #12 {all -> 0x010e, blocks: (B:56:0x00d8, B:59:0x00df, B:98:0x0169, B:100:0x0173, B:102:0x0179, B:104:0x017f, B:105:0x018e, B:62:0x00e7, B:63:0x010d, B:67:0x0112, B:68:0x011c, B:74:0x0126, B:75:0x012d, B:76:0x012e, B:81:0x013d, B:84:0x0144, B:85:0x0149, B:86:0x014a, B:92:0x0154, B:93:0x015b, B:94:0x015c, B:114:0x01a0, B:115:0x01a7, B:122:0x01b9), top: B:162:0x0056, inners: #24 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x01ae A[Catch: all -> 0x01ab, TRY_ENTER, TryCatch #9 {all -> 0x01ab, blocks: (B:37:0x0057, B:39:0x0066, B:120:0x01ae, B:121:0x01b8), top: B:159:0x0057 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:149:0x0169 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:159:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x01e0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x01d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:182:0x00e5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:189:? A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0046 A[Catch: all -> 0x004a, IOException -> 0x004f, TryCatch #22 {IOException -> 0x004f, all -> 0x004a, blocks: (B:26:0x0042, B:28:0x0046, B:36:0x0056), top: B:176:0x0042 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x0055  */
    /* JADX WARN: Code duplicated, block: B:39:0x0066 A[Catch: all -> 0x01ab, TRY_LEAVE, TryCatch #9 {all -> 0x01ab, blocks: (B:37:0x0057, B:39:0x0066, B:120:0x01ae, B:121:0x01b8), top: B:159:0x0057 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x007f A[Catch: all -> 0x00b9, TryCatch #17 {all -> 0x00b9, blocks: (B:40:0x0076, B:42:0x007f, B:44:0x00ab, B:46:0x00b5, B:51:0x00be, B:52:0x00c4, B:53:0x00c5), top: B:167:0x0076 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00c5 A[Catch: all -> 0x00b9, TRY_LEAVE, TryCatch #17 {all -> 0x00b9, blocks: (B:40:0x0076, B:42:0x007f, B:44:0x00ab, B:46:0x00b5, B:51:0x00be, B:52:0x00c4, B:53:0x00c5), top: B:167:0x0076 }] */
    /* JADX WARN: Code duplicated, block: B:58:0x00de A[LOOP:0: B:58:0x00de->B:185:0x00de, LOOP_START, PHI: r12
      0x00de: PHI (r12v1 int) = (r12v0 int), (r12v2 int) binds: [B:57:0x00dc, B:185:0x00de] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:62:0x00e7 A[Catch: all -> 0x010e, IOException -> 0x0111, SocketTimeoutException -> 0x012e, TryCatch #24 {SocketTimeoutException -> 0x012e, IOException -> 0x0111, blocks: (B:59:0x00df, B:62:0x00e7, B:63:0x010d), top: B:174:0x00df, outer: #12 }] */
    /* JADX WARN: Code duplicated, block: B:94:0x015c A[Catch: all -> 0x010e, TRY_LEAVE, TryCatch #12 {all -> 0x010e, blocks: (B:56:0x00d8, B:59:0x00df, B:98:0x0169, B:100:0x0173, B:102:0x0179, B:104:0x017f, B:105:0x018e, B:62:0x00e7, B:63:0x010d, B:67:0x0112, B:68:0x011c, B:74:0x0126, B:75:0x012d, B:76:0x012e, B:81:0x013d, B:84:0x0144, B:85:0x0149, B:86:0x014a, B:92:0x0154, B:93:0x015b, B:94:0x015c, B:114:0x01a0, B:115:0x01a7, B:122:0x01b9), top: B:162:0x0056, inners: #24 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:42:0x007f, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:62:0x00e7, please report this as an issue */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p293uc.e eVarF;
        InputStream inputStreamB;
        OutputStream outputStream;
        ax.p293uc.e eVar;
        ax.p293uc.e eVar2;
        Object obj;
        int iS;
        int i2;
        OutputStream outputStreamE1;
        long jE;
        s0();
        ax.p045d2.b.a(abstractC5838l.n());
        OutputStream outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        outputStream2 = null;
        ax.p293uc.e eVar3 = null;
        try {
            try {
                try {
                    if (this.q) {
                        eVar = this.j;
                    } else {
                        try {
                            eVarF = this.l.f();
                            try {
                                try {
                                    this.r = true;
                                    eVar2 = eVarF;
                                } catch (ax.I1.I e2) {
                                    e = e2;
                                    if (this.r) {
                                        throw new C0718i(e.getMessage(), e);
                                    }
                                    n0(true);
                                    eVar = this.j;
                                    eVar2 = eVar;
                                }
                                if (eVar2 == this.j) {
                                    obj = this.k;
                                } else {
                                    obj = eVar2;
                                }
                                synchronized (obj) {
                                    try {
                                        iS = S(eVar2, abstractC5838l.S());
                                        i2 = 0;
                                        if (ax.p293uc.t.c(iS)) {
                                            throw V("FTP writeFile CWD", iS, eVar2.S(), false);
                                        }
                                        ((h) eVar2).a(30);
                                        outputStreamE1 = eVar2.E1(abstractC5838l.w());
                                        try {
                                            ((h) eVar2).a(-1);
                                            if (outputStreamE1 == null) {
                                                int iR = eVar2.R();
                                                String strS = eVar2.S();
                                                u.severe("StoreFileStream returns null : reply : " + iR + ":" + strS);
                                                if (eVar2.w() && ax.p293uc.t.a(eVar2.R())) {
                                                    try {
                                                        eVar2.o();
                                                    } catch (IOException unused) {
                                                    }
                                                }
                                                throw V("FTP writeFile STOR", iR, strS, false);
                                            }
                                            inputStreamB = a2.b();
                                            try {
                                                jE = C0744z.e(inputStreamB, outputStreamE1, j2, cVar, iVar);
                                                outputStreamE1.close();
                                                if (eVar2.w()) {
                                                    while (true) {
                                                        i2++;
                                                        try {
                                                            if (eVar2.C0()) {
                                                                break;
                                                            }
                                                            throw new C0718i("FTP ERROR : " + eVar2.R() + ":" + eVar2.S());
                                                        } catch (SocketTimeoutException unused2) {
                                                            if (!((h) eVar2).f() || i2 >= 20) {
                                                                try {
                                                                    eVar2.o();
                                                                } catch (IOException unused3) {
                                                                }
                                                                if (jE >= j2) {
                                                                    break;
                                                                } else {
                                                                    throw new C0718i("FTP ERROR : completePendingCommand error 1");
                                                                }
                                                            } else if (cVar != null && cVar.isCancelled()) {
                                                                throw new C0710a();
                                                            }
                                                        } catch (IOException e3) {
                                                            e3.printStackTrace();
                                                            u.fine("FTP completePendingCommand Error");
                                                            try {
                                                                eVar2.o();
                                                            } catch (IOException unused4) {
                                                            }
                                                            if (jE < j2) {
                                                                throw new C0718i("FTP ERROR : completePendingCommand error 2");
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    u.fine("FTP client is disconnected");
                                                    if (jE < j2) {
                                                        throw new C0718i("FTP ERROR : Socket is disconnected");
                                                    }
                                                }
                                                if (l != null) {
                                                    try {
                                                        if (l.longValue() > 0 && p0() && eVar2.w()) {
                                                            eVar2.X(abstractC5838l.w(), a0(l.longValue()));
                                                        }
                                                    } catch (Exception unused5) {
                                                    }
                                                }
                                            } catch (Throwable th) {
                                                th = th;
                                                outputStream2 = outputStreamE1;
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            inputStreamB = null;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        inputStreamB = null;
                                    }
                                }
                                try {
                                    throw th;
                                } catch (IOException e4) {
                                    e = e4;
                                    outputStream = outputStream2;
                                    eVar3 = eVar2;
                                    try {
                                        throw U("FTP writefile", e);
                                    } catch (Throwable th4) {
                                        th = th4;
                                        ax.p293uc.e eVar4 = eVar3;
                                        outputStream2 = outputStream;
                                        eVarF = eVar4;
                                        if (outputStream2 != null) {
                                            try {
                                                outputStream2.close();
                                            } catch (IOException unused6) {
                                            }
                                        }
                                        if (inputStreamB != null) {
                                            try {
                                                inputStreamB.close();
                                            } catch (IOException unused7) {
                                            }
                                        }
                                        if (eVarF != null || eVarF == this.j) {
                                            throw th;
                                        }
                                        this.l.i(eVarF);
                                        throw th;
                                    }
                                } catch (Throwable th5) {
                                    th = th5;
                                    eVarF = eVar2;
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (inputStreamB != null) {
                                        inputStreamB.close();
                                    }
                                    if (eVarF != null) {
                                        throw th;
                                    }
                                    throw th;
                                }
                            } catch (IOException e5) {
                                e = e5;
                                inputStreamB = null;
                                eVar3 = eVarF;
                                outputStream = null;
                                throw U("FTP writefile", e);
                            } catch (Throwable th6) {
                                th = th6;
                                inputStreamB = null;
                                if (outputStream2 != null) {
                                    outputStream2.close();
                                }
                                if (inputStreamB != null) {
                                    inputStreamB.close();
                                }
                                if (eVarF != null) {
                                    throw th;
                                }
                                throw th;
                            }
                        } catch (ax.I1.I e6) {
                            e = e6;
                        }
                    }
                    synchronized (obj) {
                        iS = S(eVar2, abstractC5838l.S());
                        i2 = 0;
                        if (ax.p293uc.t.c(iS)) {
                            throw V("FTP writeFile CWD", iS, eVar2.S(), false);
                        }
                        ((h) eVar2).a(30);
                        outputStreamE1 = eVar2.E1(abstractC5838l.w());
                        ((h) eVar2).a(-1);
                        if (outputStreamE1 == null) {
                            int iR2 = eVar2.R();
                            String strS2 = eVar2.S();
                            u.severe("StoreFileStream returns null : reply : " + iR2 + ":" + strS2);
                            if (eVar2.w()) {
                                eVar2.o();
                            }
                            throw V("FTP writeFile STOR", iR2, strS2, false);
                        }
                        inputStreamB = a2.b();
                        jE = C0744z.e(inputStreamB, outputStreamE1, j2, cVar, iVar);
                        outputStreamE1.close();
                        if (eVar2.w()) {
                            while (true) {
                                i2++;
                                if (eVar2.C0()) {
                                    break;
                                    break;
                                }
                                throw new C0718i("FTP ERROR : " + eVar2.R() + ":" + eVar2.S());
                            }
                        }
                        u.fine("FTP client is disconnected");
                        if (jE < j2) {
                            throw new C0718i("FTP ERROR : Socket is disconnected");
                        }
                        if (l != null) {
                            if (l.longValue() > 0) {
                                eVar2.X(abstractC5838l.w(), a0(l.longValue()));
                            }
                        }
                        if (inputStreamB != null) {
                            try {
                                inputStreamB.close();
                            } catch (IOException unused8) {
                            }
                        }
                        if (eVar2 != this.j) {
                            this.l.i(eVar2);
                            return;
                        }
                        return;
                        throw th;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
                if (eVar2 == this.j) {
                    obj = this.k;
                } else {
                    obj = eVar2;
                }
            } catch (IOException e7) {
                e = e7;
                outputStream = null;
                inputStreamB = null;
            } catch (Throwable th8) {
                th = th8;
                inputStreamB = null;
            }
            eVar2 = eVar;
        } catch (IOException e8) {
            e = e8;
            outputStream = null;
            inputStreamB = null;
        } catch (Throwable th9) {
            th = th9;
            eVarF = null;
            inputStreamB = null;
        }
    }

    boolean d0() {
        return this.j instanceof j;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j2) throws C0718i {
        ax.p293uc.e eVarF;
        f fVar;
        e eVar = this.l;
        if (eVar == null) {
            throw new C0716g("Not connected : ftp");
        }
        try {
            if (this.q) {
                s0();
                eVarF = this.j;
            } else {
                try {
                    eVarF = eVar.f();
                    this.r = true;
                } catch (ax.I1.I e2) {
                    if (this.r) {
                        throw new C0718i(e2.getMessage(), e2);
                    }
                    n0(true);
                    s0();
                    eVarF = this.j;
                }
            }
            synchronized ((eVarF == this.j ? this.k : eVarF)) {
                try {
                    if (((h) eVarF).c() != 2) {
                        if (eVarF.z1(2)) {
                            ((h) eVarF).b(2);
                        } else {
                            ((h) eVarF).b(0);
                        }
                    }
                    eVarF.C1(j2);
                    if (!eVarF.w()) {
                        throw new ax.I1.p("FTP client is not connected");
                    }
                    InputStream inputStreamV1 = eVarF.v1(abstractC5838l.C());
                    if (inputStreamV1 == null) {
                        throw V("FTP getInputStream", eVarF.R(), eVarF.S(), true);
                    }
                    fVar = new f(eVarF, inputStreamV1);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return fVar;
        } catch (IOException e3) {
            throw U("FTP getinputstream", e3);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        try {
            d dVar = this.i;
            if (dVar != null && !dVar.isCancelled()) {
                this.i.e();
            }
            d dVar2 = new d(n(), this, q(), aVar);
            dVar2.i(new Object[0]);
            this.i = dVar2;
        } catch (Exception e2) {
            e2.printStackTrace();
            if (aVar != null) {
                aVar.N();
                aVar.f0(false, e2.getMessage());
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean f1() {
        return true;
    }

    List<AbstractC5838l> g0(String str, String str2, boolean z) throws C0718i, IOException {
        ArrayList arrayList = new ArrayList();
        if (z) {
            ax.p293uc.i[] iVarArrO1 = this.j.o1();
            int iR = this.j.R();
            if (!ax.p293uc.t.c(iR)) {
                if (iR == 425 || iR == 426) {
                    k0();
                }
                return null;
            }
            for (ax.p293uc.i iVar : iVarArrO1) {
                if (iVar != null && !V.z(iVar.b()) && !iVar.b().startsWith("/")) {
                    if (TextUtils.isEmpty(iVar.b().trim())) {
                        ax.p221pa.c.h().g().d("FTP EMPTY FILE NAME 1").h("name:" + iVar.b() + ",size:" + iVar.d() + ",dir:" + iVar.g()).i();
                    } else {
                        arrayList.add(new C0742x(this, this.j, iVar, V.M(str2, iVar.b()), false));
                    }
                }
            }
            return arrayList;
        }
        ax.p293uc.s sVarD1 = this.j.d1(null, null);
        int iR2 = this.j.R();
        if (iR2 == 425 || iR2 == 426) {
            k0();
            throw V(d0() ? "FTP List data channel: Cannot support session reuse" : "FTP List data channel", iR2, this.j.S(), false);
        }
        ArrayList<ax.p293uc.i> arrayList2 = new ArrayList();
        boolean z2 = false;
        while (sVarD1.g()) {
            if (!this.j.w()) {
                throw new IOException("FTP disconnected while operation");
            }
            ax.p293uc.i[] iVarArrF = sVarD1.f(1);
            if (iVarArrF.length > 0) {
                ax.p293uc.i iVar2 = iVarArrF[0];
                if (iVar2 != null) {
                    arrayList2.add(iVar2);
                } else {
                    z2 = true;
                }
            }
        }
        for (ax.p293uc.i iVar3 : arrayList2) {
            if (iVar3 != null && !V.z(iVar3.b())) {
                if (TextUtils.isEmpty(iVar3.b().trim())) {
                    ax.p221pa.c.h().g().d("FTP EMPTY FILE NAME 2").h("name:" + iVar3.b() + ",size:" + iVar3.d() + ",dir:" + iVar3.g()).i();
                } else {
                    arrayList.add(new C0742x(this, this.j, iVar3, V.M(str2, iVar3.b().trim()), true));
                }
            }
        }
        if (arrayList.size() == 0) {
            if (e0(iR2)) {
                throw V("FTP List", iR2, this.j.S(), false);
            }
            if (z2) {
                try {
                    Field declaredField = ax.p293uc.s.class.getDeclaredField("a");
                    declaredField.setAccessible(true);
                    List<String> list = (List) declaredField.get(sVarD1);
                    StringBuilder sb = new StringBuilder();
                    if (list != null) {
                        for (String str3 : list) {
                            if (str3 != null && !str3.endsWith(" .") && !str3.endsWith(" ..") && !str3.equals("..") && !str3.equals("\ufeff")) {
                                sb.append(str3);
                                sb.append("\n");
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(sb.toString().trim())) {
                        ax.p221pa.c.h().g().b("FTP PARSE ERROR").h(((Object) sb) + ",welcome:" + this.o + ",system:" + this.p).i();
                        throw new C0718i("FTP Parse error");
                    }
                } catch (Exception unused) {
                }
            }
        }
        return arrayList;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        s0();
        String strW = abstractC5838l.w();
        String strC = abstractC5838l.C();
        try {
            synchronized (this.k) {
                try {
                    if (o0()) {
                        this.j.A1(true);
                    }
                    int iS = S(this.j, strC);
                    if (!ax.p293uc.t.c(iS)) {
                        throw V("FTP listChildren CWD", iS, this.j.S(), false);
                    }
                    if (q0()) {
                        try {
                            List<AbstractC5838l> listG0 = g0(strW, strC, true);
                            if (listG0 != null) {
                                return listG0;
                            }
                        } catch (C0718i | ax.p307vc.n | IllegalArgumentException | IllegalStateException | NullPointerException | StringIndexOutOfBoundsException unused) {
                        }
                    }
                    return g0(strW, strC, false);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (ax.p307vc.n e2) {
            ax.p221pa.c.h().d("PARSER ERROR").m(e2).i();
            throw new C0718i(e2);
        } catch (IOException e3) {
            throw U("FTP listchildren FTPS=" + d0(), e3);
        } catch (IllegalArgumentException e4) {
            throw new C0718i(e4);
        } catch (IllegalStateException e5) {
            throw new C0718i(e5);
        } catch (NullPointerException e6) {
            throw new C0718i(e6);
        } catch (StringIndexOutOfBoundsException e7) {
            throw new C0718i(e7);
        }
    }

    public ax.p293uc.i h0(ax.p293uc.e eVar, String str) throws IOException {
        if (!ax.p293uc.t.c(eVar.k0(ax.p293uc.g.MLST, str))) {
            return null;
        }
        String[] strArrT = eVar.T();
        if (strArrT.length < 2) {
            u.severe("invalid reply : " + eVar.S());
            return null;
        }
        String str2 = strArrT[1];
        if (!str2.startsWith("550")) {
            return j0(str2, str);
        }
        try {
            String strX0 = eVar.X0();
            if (eVar.R() == 250) {
                u.severe("Invalid getStatus reply : " + strX0);
                eVar.P();
            }
        } catch (IOException unused) {
            k0();
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        boolean zN1;
        s0();
        try {
            synchronized (this.k) {
                zN1 = this.j.n1(abstractC5838l.C());
            }
            return zN1;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return j(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean j1() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    synchronized void k(AbstractC5838l abstractC5838l, String str, boolean z, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        m(abstractC5838l, str, z, hVar, cVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        s0();
        ax.p045d2.b.a(abstractC5838l2.n());
        try {
            synchronized (this.k) {
                try {
                    long jO = abstractC5838l.o();
                    if (!this.j.t1(abstractC5838l.C(), abstractC5838l2.C())) {
                        throw V("FTP moveFile", this.j.R(), this.j.S(), true);
                    }
                    if (iVar != null) {
                        iVar.a(jO, jO);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IOException e2) {
            throw U("FTP moveFile", e2);
        }
    }

    void l0(ax.p293uc.e eVar) {
        this.j = eVar;
        this.l = new e();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        s0();
        try {
            synchronized (this.k) {
                try {
                    if (!R(abstractC5838l.S())) {
                        throw new C0718i("deleteFile - CWD not successful");
                    }
                    if (abstractC5838l.isDirectory()) {
                        if (!this.j.s1(abstractC5838l.w())) {
                            throw V("FTP deleteFile", this.j.R(), this.j.S(), true);
                        }
                    } else if (!this.j.E0(abstractC5838l.w())) {
                        throw V("FTP deleteFile", this.j.R(), this.j.S(), true);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
            throw U("FTP deleteFile", e2);
        }
    }

    public void m0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.m = u();
        } else {
            this.m = str;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    void n0(boolean z) {
        this.q = z;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public String o() {
        return this.m;
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.o$d */
    private static class d extends ax.p045d2.n<Object, Void, Boolean> {
        InterfaceC5828b.a h;
        String i;
        int j;
        String k;
        String l;
        boolean m;
        boolean n;
        boolean o;
        ax.p293uc.e p;
        boolean q;
        String r;
        String s;
        C5841o t;
        ax.G1.n u;
        String v;
        boolean w;
        Context x;

        public d(Context context, C5841o c5841o, int i, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.x = context;
            this.t = c5841o;
            this.h = aVar;
            this.q = false;
            ax.G1.n nVarK = C5841o.b0(context).k(i);
            if (nVarK != null) {
                E(nVarK);
                return;
            }
            throw new IllegalArgumentException("No remote info for index : " + i);
        }

        private SSLContext A() {
            try {
                SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
                sSLContext.init(null, new TrustManager[]{ax.p335xc.g.a()}, null);
                return sSLContext;
            } catch (GeneralSecurityException | Exception unused) {
                return null;
            }
        }

        private void E(ax.G1.n nVar) {
            this.i = nVar.d();
            this.j = nVar.h();
            this.k = nVar.k();
            this.l = nVar.g();
            this.r = nVar.f();
            this.m = nVar.m();
            this.n = nVar.o();
            this.o = nVar.n();
            this.s = nVar.a();
        }

        /* JADX WARN: Code duplicated, block: B:100:0x01df A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:102:0x01f6 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:104:0x0207 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:108:0x022a A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:111:0x024f A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TRY_LEAVE, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:122:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:29:0x008b  */
        /* JADX WARN: Code duplicated, block: B:49:0x00cc  */
        /* JADX WARN: Code duplicated, block: B:51:0x00cf A[Catch: Exception -> 0x00c9, TryCatch #1 {Exception -> 0x00c9, blocks: (B:34:0x00a8, B:36:0x00b1, B:38:0x00b4, B:41:0x00bc, B:43:0x00bf, B:46:0x00c6, B:51:0x00cf, B:53:0x00e0, B:55:0x00ec, B:52:0x00d7), top: B:120:0x00a8 }] */
        /* JADX WARN: Code duplicated, block: B:52:0x00d7 A[Catch: Exception -> 0x00c9, TryCatch #1 {Exception -> 0x00c9, blocks: (B:34:0x00a8, B:36:0x00b1, B:38:0x00b4, B:41:0x00bc, B:43:0x00bf, B:46:0x00c6, B:51:0x00cf, B:53:0x00e0, B:55:0x00ec, B:52:0x00d7), top: B:120:0x00a8 }] */
        /* JADX WARN: Code duplicated, block: B:55:0x00ec A[Catch: Exception -> 0x00c9, TRY_LEAVE, TryCatch #1 {Exception -> 0x00c9, blocks: (B:34:0x00a8, B:36:0x00b1, B:38:0x00b4, B:41:0x00bc, B:43:0x00bf, B:46:0x00c6, B:51:0x00cf, B:53:0x00e0, B:55:0x00ec, B:52:0x00d7), top: B:120:0x00a8 }] */
        /* JADX WARN: Code duplicated, block: B:59:0x0117 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:65:0x013c  */
        /* JADX WARN: Code duplicated, block: B:69:0x0144 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:72:0x0153 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:75:0x015d A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:78:0x016f A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:79:0x0177 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:82:0x0182 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:83:0x0188 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:85:0x018f A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:94:0x01b7 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:96:0x01c3 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Code duplicated, block: B:98:0x01d1 A[Catch: IllegalStateException -> 0x0136, IOException -> 0x0139, TryCatch #3 {IOException -> 0x0139, IllegalStateException -> 0x0136, blocks: (B:57:0x010b, B:59:0x0117, B:69:0x0144, B:70:0x014b, B:72:0x0153, B:76:0x0166, B:78:0x016f, B:80:0x017e, B:82:0x0182, B:85:0x018f, B:86:0x01a1, B:88:0x01a5, B:90:0x01a9, B:92:0x01ad, B:94:0x01b7, B:96:0x01c3, B:98:0x01d1, B:100:0x01df, B:102:0x01f6, B:104:0x0207, B:106:0x0226, B:108:0x022a, B:109:0x024b, B:111:0x024f, B:83:0x0188, B:79:0x0177, B:75:0x015d), top: B:122:0x010b }] */
        /* JADX WARN: Instruction removed from duplicated block: B:104:0x0207, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:55:0x00ec, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:59:0x0117, please report this as an issue */
        private boolean w(boolean z, boolean z2) {
            boolean z3;
            boolean z4;
            boolean z5;
            InetAddress[] allByName;
            InetAddress inetAddress;
            String strR1;
            String strTrim;
            String strD;
            String strO;
            System.setProperty("org.apache.commons.net.ftp.systemType.default", "UNKNOWN_SYSTEM_TYPE");
            if (z) {
                C5841o.c0(this.x);
                SSLContext sSLContextA = A();
                if (sSLContextA != null) {
                    this.p = new j(this.o, sSLContextA);
                } else {
                    j jVar = new j(this.o);
                    jVar.Q1(ax.p335xc.g.a());
                    this.p = jVar;
                }
            } else {
                this.p = new i();
            }
            this.p.n0(false);
            this.p.B1(new C0732m());
            String str = this.s;
            try {
                if (str != null) {
                    this.p.m0(str);
                    if ("UTF8".equals(this.s) || "UTF-8".equals(this.s)) {
                        z3 = false;
                        z4 = true;
                    } else {
                        z3 = false;
                    }
                    if (!z3 && ax.p045d2.x.H(this.k) && ax.p045d2.x.H(this.l)) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (z3 && !z5) {
                        this.p.x1(true);
                    }
                    this.p.x(15000);
                    this.p.z(15000);
                    this.p.y1(30000);
                    allByName = InetAddress.getAllByName(this.i);
                    if (allByName == null && allByName.length > 1 && (allByName[0] instanceof Inet6Address)) {
                        inetAddress = null;
                        for (InetAddress inetAddress2 : allByName) {
                            if (inetAddress2 instanceof Inet4Address) {
                                inetAddress = inetAddress2;
                            }
                        }
                    } else {
                        inetAddress = null;
                    }
                    if (inetAddress != null) {
                        this.p.n(inetAddress, this.j);
                    } else {
                        this.p.m(this.i, this.j);
                    }
                    if (!ax.p293uc.t.c(this.p.R())) {
                        this.v = "connect error : " + this.p.S();
                        this.p.o();
                        return false;
                    }
                    try {
                        if (!this.p.m1(this.k, this.l)) {
                            this.v = "login error : " + this.p.S();
                            C5841o.X(this.p);
                            return false;
                        }
                        if ((z3 && z5) || z4) {
                            if (z3) {
                                ((h) this.p).g();
                            }
                            if (this.p.Z0("UTF8") || this.p.Z0("UTF-8") || z4) {
                                this.p.l0("OPTS", "UTF8 ON");
                            }
                        }
                        if (this.p.z1(2)) {
                            ((h) this.p).b(2);
                        } else {
                            ((h) this.p).b(0);
                        }
                        if (this.m) {
                            this.p.F0();
                        } else {
                            this.p.G0();
                        }
                        if (z) {
                            ((ax.p293uc.u) this.p).K1(0L);
                            ((ax.p293uc.u) this.p).L1("P");
                        }
                        if (this.h != null && this.t != null && this.r == null) {
                            strR1 = this.p.r1();
                            this.r = strR1;
                            if (strR1 != null) {
                                strTrim = strR1.trim();
                                this.r = strTrim;
                                if (!V.y(strTrim)) {
                                    strD = D(this.r);
                                    this.r = strD;
                                    if (!V.y(strD)) {
                                        strO = V.O(this.r);
                                        this.r = strO;
                                        if (!V.y(strO)) {
                                            ax.p221pa.c.h().c("INVALID FTP INITIAL PATH").h(this.r).i();
                                            this.r = null;
                                        }
                                    }
                                }
                            }
                        }
                        if (z2) {
                            this.p.j1();
                            if (!ax.p293uc.t.c(this.p.R())) {
                                this.v = "list error : " + this.p.S();
                                C5841o.X(this.p);
                                return false;
                            }
                        }
                        if (this.t != null) {
                            String strY0 = this.p.Y0();
                            String strE = ((h) this.p).e();
                            this.t.m0(this.r);
                            this.t.l0(this.p);
                            this.t.W(strE, strY0);
                        }
                        if (this.q) {
                            C5841o.X(this.p);
                        }
                        return true;
                    } catch (IOException e) {
                        e = e;
                        this.v = "exception error 2 : " + e.getMessage();
                        e.printStackTrace();
                        C5841o.X(this.p);
                        return false;
                    } catch (IllegalStateException e2) {
                        e = e2;
                        this.v = "exception error 2 : " + e.getMessage();
                        e.printStackTrace();
                        C5841o.X(this.p);
                        return false;
                    }
                }
                String strA = ax.p045d2.f.a();
                if (strA != null) {
                    this.p.m0(strA);
                }
                z3 = true;
                allByName = InetAddress.getAllByName(this.i);
                if (allByName == null) {
                    inetAddress = null;
                } else {
                    inetAddress = null;
                }
                if (inetAddress != null) {
                    this.p.n(inetAddress, this.j);
                } else {
                    this.p.m(this.i, this.j);
                }
                if (!ax.p293uc.t.c(this.p.R())) {
                    this.v = "connect error : " + this.p.S();
                    this.p.o();
                    return false;
                }
                if (!this.p.m1(this.k, this.l)) {
                    this.v = "login error : " + this.p.S();
                    C5841o.X(this.p);
                    return false;
                }
                if (z3) {
                    if (z3) {
                        ((h) this.p).g();
                    }
                    if (this.p.Z0("UTF8")) {
                        this.p.l0("OPTS", "UTF8 ON");
                    } else {
                        this.p.l0("OPTS", "UTF8 ON");
                    }
                } else {
                    if (z3) {
                        ((h) this.p).g();
                    }
                    if (this.p.Z0("UTF8")) {
                        this.p.l0("OPTS", "UTF8 ON");
                    } else {
                        this.p.l0("OPTS", "UTF8 ON");
                    }
                }
                if (this.p.z1(2)) {
                    ((h) this.p).b(2);
                } else {
                    ((h) this.p).b(0);
                }
                if (this.m) {
                    this.p.F0();
                } else {
                    this.p.G0();
                }
                if (z) {
                    ((ax.p293uc.u) this.p).K1(0L);
                    ((ax.p293uc.u) this.p).L1("P");
                }
                if (this.h != null) {
                    strR1 = this.p.r1();
                    this.r = strR1;
                    if (strR1 != null) {
                        strTrim = strR1.trim();
                        this.r = strTrim;
                        if (!V.y(strTrim)) {
                            strD = D(this.r);
                            this.r = strD;
                            if (!V.y(strD)) {
                                strO = V.O(this.r);
                                this.r = strO;
                                if (!V.y(strO)) {
                                    ax.p221pa.c.h().c("INVALID FTP INITIAL PATH").h(this.r).i();
                                    this.r = null;
                                }
                            }
                        }
                    }
                }
                if (z2) {
                    this.p.j1();
                    if (!ax.p293uc.t.c(this.p.R())) {
                        this.v = "list error : " + this.p.S();
                        C5841o.X(this.p);
                        return false;
                    }
                }
                if (this.t != null) {
                    String strY1 = this.p.Y0();
                    String strE2 = ((h) this.p).e();
                    this.t.m0(this.r);
                    this.t.l0(this.p);
                    this.t.W(strE2, strY1);
                }
                if (this.q) {
                    C5841o.X(this.p);
                }
                return true;
            } catch (Exception e3) {
                if (C0712c.d(e3)) {
                    this.w = true;
                }
                this.v = "exception error 1 : " + e3.getMessage();
                e3.printStackTrace();
                return false;
            }
            z4 = false;
            if (!z3) {
                z5 = false;
            } else {
                z5 = false;
            }
            if (z3) {
                this.p.x1(true);
            }
            this.p.x(15000);
            this.p.z(15000);
            this.p.y1(30000);
        }

        boolean B() {
            return this.w;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.h != null) {
                if (!bool.booleanValue()) {
                    C5841o c5841o = this.t;
                    if (c5841o != null && C5841o.f0(c5841o.q())) {
                        C5841o.b0(this.x).m(this.t.q());
                    }
                    this.h.f0(bool.booleanValue(), this.v);
                    return;
                }
                C5841o c5841o2 = this.t;
                if (c5841o2 != null && C5841o.f0(c5841o2.q())) {
                    C5841o.b0(this.x).t(this.t.q());
                }
                if (!TextUtils.isEmpty(this.r)) {
                    this.r = V.Q(this.r);
                }
                this.h.f0(bool.booleanValue(), this.r);
            }
        }

        String D(String str) {
            int iIndexOf = str.indexOf("\"");
            if (iIndexOf < 0) {
                return str;
            }
            try {
                StringBuilder sb = new StringBuilder();
                boolean z = false;
                for (int i = iIndexOf + 1; i < str.length(); i++) {
                    char cCharAt = str.charAt(i);
                    if (cCharAt != '\"') {
                        if (z) {
                            return sb.toString();
                        }
                        sb.append(cCharAt);
                    } else if (z) {
                        sb.append(cCharAt);
                        z = false;
                    } else {
                        z = true;
                    }
                }
                return z ? sb.toString() : str;
            } catch (IndexOutOfBoundsException unused) {
                return str;
            }
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
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            C5841o c5841o = this.t;
            if (c5841o != null) {
                c5841o.h();
            }
            try {
                if (this.q && !this.n) {
                    if (w(true, true) && this.u != null) {
                        C5841o.u.fine("FTPS detected!!!");
                        this.u.C(true);
                        return Boolean.TRUE;
                    }
                    C5841o.u.fine("FTPS not detected, try FTP");
                }
                return Boolean.valueOf(w(this.n, false));
            } finally {
                C5841o c5841o2 = this.t;
                if (c5841o2 != null) {
                    c5841o2.i();
                }
            }
        }

        String y() {
            return this.v;
        }

        public ax.p293uc.e z() {
            return this.p;
        }

        public d(Context context, ax.G1.n nVar, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.x = context;
            this.h = aVar;
            this.q = true;
            this.u = nVar;
            E(nVar);
        }
    }
}
