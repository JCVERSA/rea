package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0712c;
import ax.I1.C0716g;
import ax.I1.C0718i;
import ax.J1.C0744z;
import ax.J1.U;
import ax.J1.V;
import ax.J1.Y;
import ax.J1.b0;
import ax.Ta.C4736a0;
import ax.Ta.C4738b0;
import ax.Ta.i0;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class L extends AbstractC5837k {
    public static final String n = null;
    private static final Logger o = Logger.getLogger("FileManager.SmbFileHelper");
    private static b p;
    private K h;
    private boolean i;
    private String j;
    M k;
    I l;
    J m;

    public static class b extends Y {
        Context a;
        U b = new U(ax.p338y1.f.L0);

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
                this.q.b(ax.p338y1.f.L0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
            public void f0(boolean z, Object obj) {
                if (!z) {
                    this.q.a(ax.p338y1.f.L0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                b.this.o(this.X, this.Y);
                ax.p338y1.f fVar = ax.p338y1.f.L0;
                C5839m c5839mE = ax.J1.r.e(fVar, this.X);
                if (c5839mE.a()) {
                    ((L) c5839mE.t()).e0(false);
                    ((L) c5839mE.t()).S();
                    ax.G1.b.k().s(c5839mE.z(), c5839mE.B());
                }
                this.q.d(fVar, this.X);
            }
        }

        b(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("SMBPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("domain_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("created_" + i).remove("sortindex_" + i).remove("protocol_" + i).remove("lastResolvedIp_" + i).remove("hostConcatDomain_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            return new ax.G1.p(ax.p338y1.f.L0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("SMBPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.J1.Y
        public ax.G1.n k(int i) {
            ax.G1.n nVar = new ax.G1.n();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            nVar.r(sharedPreferences.getString("domain_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            nVar.s(string);
            nVar.z(sharedPreferences.getInt("port_" + i, 0));
            nVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            nVar.y(this.b.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            nVar.v(sharedPreferences.getString("initialPath_" + i, null));
            nVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            nVar.B(sharedPreferences.getString("protocol_" + i, L.n));
            return nVar;
        }

        @Override // ax.J1.Y
        public void l(int i, ax.G1.n nVar, ax.P1.j jVar, boolean z) {
            if (i == -100) {
                i = m();
            }
            if (z) {
                new a(this.a, nVar, new a(jVar, i, nVar)).i(new String[0]);
            } else {
                o(i, nVar);
                jVar.d(ax.p338y1.f.L0, i);
            }
        }

        int m() {
            return this.a.getSharedPreferences("SMBPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> n() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void o(int i, ax.G1.n nVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SMBPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("domain_" + i, nVar.c()).putString("host_" + i, nVar.d()).putString("username_" + i, nVar.k()).putString("password_" + i, this.b.e(nVar.d(), nVar.g())).putString("initialPath_" + i, nVar.f()).putString("protocol_" + i, nVar.j()).putString("name_" + i, nVar.b());
            if (nVar.h() > 0) {
                editorEdit.putInt("port_" + i, nVar.h());
            } else {
                editorEdit.remove("port_" + i);
            }
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

    public enum c {
        SMB1,
        SMB1AND2,
        SMB2AND3
    }

    private C0718i R(String str, IOException iOException) {
        Throwable cause = iOException.getCause();
        while (cause != null) {
            if (cause instanceof TimeoutException) {
                return new ax.I1.p(str, iOException);
            }
            cause = cause != cause.getCause() ? cause.getCause() : null;
        }
        return C0712c.b(str, iOException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S() {
        J j = this.m;
        if (j != null) {
            j.l();
        }
    }

    public static b V(Context context) {
        if (p == null) {
            p = new b(context.getApplicationContext());
        }
        return p;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String X() {
        return n().getSharedPreferences("SMBPrefs", 0).getString("lastResolvedIp_" + q(), null);
    }

    private static String Y(String str) {
        int iIndexOf = str.indexOf("/", 1);
        return iIndexOf == -1 ? str.substring(1) : str.substring(1, iIndexOf);
    }

    static String b0(String str, String str2) {
        return str + str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c0() {
        return n().getSharedPreferences("SMBPrefs", 0).getBoolean("hostConcatDomain_" + q(), false);
    }

    public static boolean d0(String str) {
        return !"/".equals(str) && str.startsWith("/") && str.indexOf("/", 1) < 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f0(boolean z) {
        SharedPreferences.Editor editorEdit = n().getSharedPreferences("SMBPrefs", 0).edit();
        editorEdit.putBoolean("hostConcatDomain_" + q(), z);
        editorEdit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i0(String str) {
        SharedPreferences.Editor editorEdit = n().getSharedPreferences("SMBPrefs", 0).edit();
        editorEdit.putString("lastResolvedIp_" + q(), str);
        editorEdit.apply();
    }

    private boolean k0() {
        c cVar = this.h.a;
        return cVar == c.SMB1 || cVar == c.SMB1AND2;
    }

    private boolean l0() {
        c cVar = this.h.a;
        return cVar == c.SMB2AND3 || cVar == c.SMB1AND2;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:67:0x00ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:? A[SYNTHETIC] */
    private void m0(AbstractC5838l abstractC5838l, ax.J1.A a2, long j, Long l, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        InputStream inputStream;
        OutputStream outputStream = null;
        inputStreamB = null;
        InputStream inputStreamB = null;
        outputStream = null;
        try {
            OutputStream outputStreamH = k0() ? this.l.h(abstractC5838l, z) : this.m.q(abstractC5838l, z);
            try {
                inputStreamB = a2.b();
                C0744z.e(inputStreamB, outputStreamH, j, cVar, iVar);
                if (outputStreamH != null) {
                    try {
                        outputStreamH.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException unused) {
                    }
                }
                if (l != null) {
                    try {
                        if (l.longValue() >= 0) {
                            if (k0()) {
                                this.l.p(abstractC5838l, l.longValue());
                            } else {
                                this.m.E(abstractC5838l, l.longValue());
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e = e3;
                inputStream = inputStreamB;
                outputStream = outputStreamH;
                try {
                    if (e instanceof C4736a0) {
                        C0718i c0718iA = I.a("smb1 writeFile", (C4736a0) e);
                        if (!C0718i.class.equals(c0718iA.getClass())) {
                            throw c0718iA;
                        }
                    }
                    if (!k0() && (e.getCause() instanceof ax.J8.F)) {
                        throw J.g(e);
                    }
                    throw R("smb write file", e);
                } catch (Throwable th) {
                    th = th;
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        throw th;
                    }
                    try {
                        inputStream.close();
                        throw th;
                    } catch (IOException unused2) {
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStreamB;
                outputStream = outputStreamH;
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    throw th;
                }
                inputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void B(Context context, ax.J1.F f) {
        super.B(context, f);
        this.l = new I(this);
        if (ax.E1.P.i()) {
            this.m = new J(this);
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean F(AbstractC5838l abstractC5838l) {
        return (abstractC5838l == null || V.B(abstractC5838l)) ? false : true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean G() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public ax.U1.a H(String str, int i) throws C0718i {
        if (!a()) {
            throw new C0716g("Not connected to server");
        }
        if (!k0()) {
            return this.m.D(str, i);
        }
        ax.p045d2.b.g("SMB1 does not support proxy file :" + this.h.a + "," + V.f(str));
        throw new ax.I1.s("SMB1 does not support proxy file.");
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean I() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean J() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        m0(abstractC5838l, a2, j, l, true, cVar, iVar);
    }

    String T() {
        return this.h.d;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public M V0(String str) throws C0718i {
        M m;
        if (str == null) {
            ax.p221pa.c.h().d("SMBGFI!!!:").k().h("smbtype:" + this.h.a.name()).i();
        }
        boolean zEquals = str.equals(this.j);
        if (zEquals && (m = this.k) != null) {
            return m;
        }
        if (Z() != null) {
            M mE = k0() ? this.l.e(str) : this.m.o(str);
            if (zEquals) {
                this.k = mE;
            }
            return mE;
        }
        String strF = V.f(str);
        ax.p221pa.c.h().g().b("NOT CONNECT CALL GET FILE INFO").k().h("ext:" + strF).i();
        throw new C0716g("Not connected to server");
    }

    public b0 W(AbstractC5838l abstractC5838l) throws C0718i {
        return k0() ? this.l.i(abstractC5838l) : this.m.s(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream W0(String str, String str2, String str3) {
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

    K Z() {
        return this.h;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public String Z0(AbstractC5838l abstractC5838l) {
        if (E(abstractC5838l)) {
            return AbstractC5837k.x(abstractC5838l);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean a() {
        return this.i;
    }

    String a0(String str) {
        return b0(T(), str);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        e0(false);
        S();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j, Long l, C5840n c5840n, boolean z, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p045d2.b.a(abstractC5838l.n());
        m0(abstractC5838l, a2, j, l, false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j) throws C0718i {
        if (Z() == null) {
            throw new C0716g("Not connected to server");
        }
        if (!l0()) {
            return this.l.f(abstractC5838l, j);
        }
        try {
            return this.m.p(abstractC5838l, j);
        } catch (C0718i e) {
            if (k0()) {
                return this.l.f(abstractC5838l, j);
            }
            throw e;
        }
    }

    void e0(boolean z) {
        this.i = z;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        try {
            new a(n(), this, aVar, q()).i(new String[0]);
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

    public void g0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.j = u();
            this.k = null;
        } else {
            this.j = str;
            this.k = null;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        return k0() ? this.l.n(abstractC5838l) : this.m.w(abstractC5838l);
    }

    void h0(String str, boolean z) {
        J j = this.m;
        if (j != null) {
            j.F(str, z);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        return k0() ? this.l.c(abstractC5838l) : this.m.i(abstractC5838l);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean i1(AbstractC5838l abstractC5838l) {
        return k0() ? this.l.b(abstractC5838l) : this.m.h(abstractC5838l);
    }

    void j0(K k) {
        e0(true);
        this.h = k;
        c cVar = k.a;
        if (cVar == c.SMB1 || cVar == c.SMB1AND2) {
            this.l.q(k.c, k.d);
        }
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
        ax.p045d2.b.a(abstractC5838l2.n());
        long jO = abstractC5838l.o();
        if (k0()) {
            this.l.o(abstractC5838l, abstractC5838l2);
        } else {
            this.m.x(abstractC5838l, abstractC5838l2);
        }
        if (iVar != null) {
            iVar.a(jO, jO);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        if (k0()) {
            this.l.d(abstractC5838l);
        } else {
            this.m.k(abstractC5838l);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        ax.p338y1.f fVarP = abstractC5838l.P();
        ax.p338y1.f fVar = ax.p338y1.f.L0;
        if (fVarP == fVar && abstractC5838l2.P() == fVar) {
            String strS = abstractC5838l.S();
            String strS2 = abstractC5838l2.S();
            if (!"/".equals(strS) && !"/".equals(strS2)) {
                return Y(strS).equals(Y(strS2));
            }
        }
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public String o() {
        return this.j;
    }

    private static class a extends ax.p045d2.n<String, Void, Boolean> {
        Context h;
        WifiManager i;
        String j;
        String k;
        int l;
        String m;
        String n;
        InterfaceC5828b.a o;
        L p;
        String q;
        String r;
        String s;

        public a(Context context, L l, InterfaceC5828b.a aVar, int i) {
            super(ax.d2.n.f.CONNECT);
            this.h = context;
            this.p = l;
            this.o = aVar;
            D(L.V(context).k(i));
            this.i = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }

        private ax.Na.b B(L l, String str, String str2) throws UnknownHostException {
            String str3;
            DhcpInfo dhcpInfo;
            WifiManager wifiManager = this.i;
            boolean z = wifiManager == null || wifiManager.getWifiState() != 3 || (dhcpInfo = this.i.getDhcpInfo()) == null || dhcpInfo.ipAddress == 0 || z(dhcpInfo.dns1);
            ax.Ra.g.A(str);
            ax.Na.b.l(z);
            if (TextUtils.isEmpty(str2)) {
                return C(str, true);
            }
            if (l == null || !l.c0()) {
                str3 = str + "." + str2;
            } else {
                str3 = str;
                str = str + "." + str2;
            }
            try {
                return C(str, true);
            } catch (UnknownHostException unused) {
                ax.Na.b bVarC = C(str3, true);
                if (l != null) {
                    l.f0(!l.c0());
                }
                return bVarC;
            }
        }

        /* JADX WARN: Code duplicated, block: B:16:0x002d  */
        /* JADX WARN: Code duplicated, block: B:35:0x0063  */
        /* JADX WARN: Code duplicated, block: B:37:0x0066  */
        /* JADX WARN: Code duplicated, block: B:60:0x0087 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        private ax.Na.b C(String str, boolean z) throws UnknownHostException {
            boolean z2;
            ax.Na.b[] bVarArrC = ax.Na.b.c(str, z);
            if (bVarArrC.length == 0) {
                ax.p045d2.b.f();
                throw new UnknownHostException("No matched ip address");
            }
            if (bVarArrC.length > 1) {
                byte[] bArrF = ax.R1.a.f(this.h);
                if (bArrF.length < 4 || bArrF[0] == 0) {
                    z2 = false;
                } else {
                    InetAddress byAddress = InetAddress.getByAddress(bArrF);
                    if (byAddress.isAnyLocalAddress() || byAddress.isLoopbackAddress()) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                }
                ax.Na.b bVar = null;
                ax.Na.b bVar2 = null;
                for (int length = bVarArrC.length - 1; length >= 0; length--) {
                    ax.Na.b bVar3 = bVarArrC[length];
                    if (bVar3 != null && !(bVar3.b() instanceof Inet6Address)) {
                        Object objB = bVar3.b();
                        if (bVar != null) {
                            if (!z2 && (objB instanceof ax.Ra.g)) {
                                byte[] bArrH = ((ax.Ra.g) objB).h();
                                if (bArrH.length >= 4 && bArrH[0] == bArrF[0] && bArrH[1] == bArrF[1] && bArrH[2] == bArrF[2]) {
                                    return bVar3;
                                }
                            }
                        } else if (objB instanceof ax.Ra.g) {
                            InetAddress inetAddressS = ((ax.Ra.g) objB).s();
                            if (inetAddressS.isLoopbackAddress()) {
                                continue;
                            } else {
                                if (!inetAddressS.isLinkLocalAddress()) {
                                    bVar = bVar3;
                                } else if (bVar2 == null) {
                                    bVar2 = bVar3;
                                }
                                if (!z2) {
                                    continue;
                                }
                            }
                        } else {
                            bVar = bVar3;
                            if (!z2) {
                                continue;
                            }
                        }
                    }
                }
                if (bVar != null) {
                    return bVar;
                }
                if (bVar2 != null) {
                    return bVar2;
                }
            }
            return bVarArrC[0];
        }

        private void D(ax.G1.n nVar) {
            this.j = nVar.c();
            this.k = nVar.d();
            this.l = nVar.h();
            this.m = nVar.k();
            this.n = nVar.g();
            this.q = nVar.f();
            this.r = nVar.j();
        }

        /* JADX WARN: Code duplicated, block: B:58:0x00bb A[Catch: IOException -> 0x0073, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x0073, blocks: (B:40:0x006b, B:41:0x006f, B:50:0x0094, B:58:0x00bb), top: B:84:0x0045 }] */
        /* JADX WARN: Code duplicated, block: B:62:0x00c4 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TRY_ENTER, TryCatch #11 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:67:0x00cc A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TryCatch #11 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:69:0x00d1 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TryCatch #11 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:71:0x00d7 A[Catch: RuntimeException -> 0x00c8, a0 -> 0x00ca, TRY_LEAVE, TryCatch #11 {a0 -> 0x00ca, RuntimeException -> 0x00c8, blocks: (B:62:0x00c4, B:69:0x00d1, B:71:0x00d7, B:67:0x00cc), top: B:90:0x00c2 }] */
        /* JADX WARN: Code duplicated, block: B:76:0x010c  */
        /* JADX WARN: Code duplicated, block: B:78:0x0114 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:86:0x0117 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:90:0x00c2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Multi-variable type inference failed */
        private K E(c cVar, ax.Z8.c cVar2, ax.Na.b bVar, String str, int i, String str2, String str3, String str4) throws Throwable {
            ax.p010a9.b bVarN;
            ax.p122i9.b bVarD;
            ax.p038c9.a aVarK;
            ax.Ta.r rVar = (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4)) ? ax.Ta.r.s0 : new ax.Ta.r(str2, str3, str4);
            ax.p122i9.b bVar2 = 0;
            boolean z = false;
            if (cVar == c.SMB1AND2 || cVar == c.SMB2AND3) {
                if ("?".equals(str2)) {
                    if ("GUEST".equals(str3)) {
                        str3 = "SMB2GUESTTESTACCOUNT";
                    }
                    bVarN = J.n(HttpUrl.FRAGMENT_ENCODE_SET, str3, str4);
                } else {
                    bVarN = J.n(str2, str3, str4);
                }
                try {
                    try {
                        try {
                            bVarD = J.d(cVar2, str, i, bVarN);
                            z = true;
                            try {
                                if (cVar == c.SMB2AND3) {
                                    K k = new K(bVarN, str, i);
                                    k.c = rVar;
                                    if (bVarD != null) {
                                        try {
                                            bVarD.k().close();
                                        } catch (IOException unused) {
                                        }
                                    }
                                    return k;
                                }
                                if (bVarD != null) {
                                    aVarK = bVarD.k();
                                    aVarK.close();
                                }
                            } catch (ax.p024b9.d e) {
                                e = e;
                                w("SMB2 : " + e.getMessage());
                                e.printStackTrace();
                                if (bVarD != null) {
                                    aVarK = bVarD.k();
                                    aVarK.close();
                                }
                            } catch (IOException e2) {
                                e = e2;
                                w("SMB2 : " + e.getMessage());
                                e.printStackTrace();
                                if (bVarD != null) {
                                    aVarK = bVarD.k();
                                    aVarK.close();
                                }
                            } catch (RuntimeException e3) {
                                e = e3;
                                e.printStackTrace();
                                ax.p221pa.c.h().g().b("SMB2 AUTH RUNTIME EXCEPTION").m(e).i();
                                if (bVarD != null) {
                                    aVarK = bVarD.k();
                                    aVarK.close();
                                }
                            }
                        } catch (IOException unused2) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        bVar2 = cVar2;
                        if (bVar2 != 0) {
                            try {
                                bVar2.k().close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (ax.p024b9.d e4) {
                    e = e4;
                    bVarD = null;
                    w("SMB2 : " + e.getMessage());
                    e.printStackTrace();
                    if (bVarD != null) {
                        aVarK = bVarD.k();
                        aVarK.close();
                    }
                    if (bVar != null) {
                        try {
                            if (i > 0) {
                                i0.c(bVar, i, rVar);
                            } else {
                                i0.d(bVar, rVar);
                            }
                            return z ? new K(bVarN, str, i, rVar) : new K(rVar);
                        } catch (C4736a0 e5) {
                            w("SMB1 : " + e5.getMessage());
                            e5.printStackTrace();
                        } catch (RuntimeException e6) {
                            ax.p221pa.c.h().d("SMB1 LOGON!!").m(e6).i();
                        }
                    }
                    if (!z) {
                        return null;
                    }
                    K k2 = new K(bVarN, str, i);
                    k2.c = rVar;
                    return k2;
                } catch (IOException e7) {
                    e = e7;
                    bVarD = null;
                    w("SMB2 : " + e.getMessage());
                    e.printStackTrace();
                    if (bVarD != null) {
                        aVarK = bVarD.k();
                        aVarK.close();
                    }
                    if (bVar != null) {
                        if (i > 0) {
                            i0.c(bVar, i, rVar);
                        } else {
                            i0.d(bVar, rVar);
                        }
                        if (z) {
                        }
                    }
                    if (!z) {
                        return null;
                    }
                    K k3 = new K(bVarN, str, i);
                    k3.c = rVar;
                    return k3;
                } catch (RuntimeException e8) {
                    e = e8;
                    bVarD = null;
                } catch (Throwable th2) {
                    th = th2;
                    if (bVar2 != 0) {
                        bVar2.k().close();
                    }
                    throw th;
                }
            } else {
                bVarN = null;
            }
            if (bVar != null) {
                if (i > 0) {
                    i0.c(bVar, i, rVar);
                } else {
                    i0.d(bVar, rVar);
                }
                if (z) {
                }
            }
            if (!z) {
                return null;
            }
            K k4 = new K(bVarN, str, i);
            k4.c = rVar;
            return k4;
        }

        private void w(String str) {
            if (this.s == null) {
                this.s = str;
                return;
            }
            this.s += "," + str;
        }

        /* JADX WARN: Code duplicated, block: B:111:0x019d  */
        /* JADX WARN: Code duplicated, block: B:114:0x01bc  */
        /* JADX WARN: Code duplicated, block: B:116:0x01d5  */
        /* JADX WARN: Code duplicated, block: B:118:0x01eb  */
        /* JADX WARN: Code duplicated, block: B:120:0x0201  */
        /* JADX WARN: Code duplicated, block: B:121:0x0216  */
        /* JADX WARN: Code duplicated, block: B:123:0x021f  */
        /* JADX WARN: Code duplicated, block: B:124:0x0224  */
        /* JADX WARN: Code duplicated, block: B:127:0x022e  */
        /* JADX WARN: Code duplicated, block: B:130:0x023a  */
        /* JADX WARN: Code duplicated, block: B:131:0x0240  */
        /* JADX WARN: Code duplicated, block: B:134:0x0258 A[ADDED_TO_REGION] */
        /* JADX WARN: Code duplicated, block: B:142:0x027a  */
        /* JADX WARN: Code duplicated, block: B:149:0x026f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:153:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:32:0x0058  */
        /* JADX WARN: Code duplicated, block: B:37:0x0070 A[PHI: r7
          0x0070: PHI (r7v2 ax.Z8.c) = (r7v1 ax.Z8.c), (r7v1 ax.Z8.c), (r7v4 ax.Z8.c) binds: [B:31:0x0056, B:33:0x0060, B:36:0x006f] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:39:0x0075  */
        /* JADX WARN: Code duplicated, block: B:41:0x0079  */
        /* JADX WARN: Code duplicated, block: B:42:0x007f  */
        /* JADX WARN: Code duplicated, block: B:44:0x009b  */
        /* JADX WARN: Code duplicated, block: B:90:0x0129  */
        /* JADX WARN: Code duplicated, block: B:95:0x0142 A[PHI: r4
          0x0142: PHI (r4v5 ax.Z8.c) = (r4v4 ax.Z8.c), (r4v4 ax.Z8.c), (r4v7 ax.Z8.c) binds: [B:89:0x0127, B:91:0x0131, B:94:0x0141] A[DONT_GENERATE, DONT_INLINE]] */
        /* JADX WARN: Code duplicated, block: B:97:0x0146  */
        /* JADX WARN: Code duplicated, block: B:98:0x0149  */
        /* JADX WARN: Instruction removed from duplicated block: B:111:0x019d, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:42:0x007f, please report this as an issue */
        private K x(String str, String str2, String str3, ax.Na.b bVar, boolean z) throws Throwable {
            ax.Z8.c cVarJ;
            ax.p038c9.a aVarF;
            boolean z2;
            boolean z3;
            ax.p038c9.a aVarF2;
            c cVar;
            ax.Z8.c cVar2;
            ax.J8.g gVarA;
            c cVar3;
            ax.Z8.c cVarJ2;
            ax.p038c9.a aVarF3;
            boolean z4;
            c cVar4;
            boolean z5;
            String str4;
            String str5;
            String str6;
            boolean z6;
            K kE;
            if (!"SMB1".equals(str)) {
                if (("SMB2".equals(str) || "SMB3".equals(str)) && ax.E1.P.i() && !TextUtils.isEmpty(str3)) {
                    try {
                        cVarJ = J.j(str, true);
                        try {
                            aVarF2 = J.f(cVarJ, str3, this.l);
                            z3 = true;
                        } catch (ax.p024b9.d e) {
                            e = e;
                            if (e.getMessage() == null && e.getMessage().contains("EOF while reading packet")) {
                                try {
                                    cVarJ = J.j(str, false);
                                    aVarF = J.f(cVarJ, str3, this.l);
                                    z2 = true;
                                    z3 = false;
                                } catch (ax.p024b9.d | IOException | IllegalArgumentException unused) {
                                    aVarF = null;
                                    z2 = false;
                                    z3 = true;
                                    if (!z2) {
                                        if (e instanceof SocketTimeoutException) {
                                            w("SMB2 timeout");
                                        } else {
                                            e.printStackTrace();
                                            w("Protocol not supported : " + e.getMessage());
                                        }
                                        return null;
                                    }
                                    aVarF2 = aVarF;
                                    if (aVarF2 == null) {
                                    }
                                    cVar = c.SMB2AND3;
                                    cVar2 = cVarJ;
                                    if (cVar == c.SMB1) {
                                        try {
                                            I.r(bVar, this.l);
                                        } catch (IOException e2) {
                                            if (e2 instanceof ax.Va.d) {
                                                ax.Va.d dVar = (ax.Va.d) e2;
                                                if (dVar.a() != null) {
                                                    w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage() + " : " + dVar.a().getMessage());
                                                } else {
                                                    w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage());
                                                }
                                            } else {
                                                w("Cannot connect to the SMB1 server : " + bVar + " : " + e2.getMessage());
                                            }
                                            return null;
                                        }
                                    }
                                    if (z) {
                                        z5 = z3;
                                        kE = E(cVar, cVar2, bVar, str3, this.l, "?", "GUEST", HttpUrl.FRAGMENT_ENCODE_SET);
                                        if (kE == null) {
                                            kE = E(cVar, cVar2, bVar, str3, this.l, "?", "Guest", HttpUrl.FRAGMENT_ENCODE_SET);
                                            if (kE == null) {
                                                kE = E(cVar, cVar2, bVar, str3, this.l, "?", "guest", HttpUrl.FRAGMENT_ENCODE_SET);
                                                if (kE == null) {
                                                    kE = E(cVar, cVar2, bVar, str3, this.l, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                                                }
                                            }
                                        }
                                    } else {
                                        z5 = z3;
                                        if (this.m.length() > 0) {
                                            str4 = this.m;
                                        } else {
                                            str4 = null;
                                        }
                                        str5 = this.n.length() > 0 ? this.n : null;
                                        if (this.j.length() > 0) {
                                            str6 = this.j;
                                            z6 = false;
                                        } else {
                                            str6 = str2;
                                            z6 = true;
                                        }
                                        kE = E(cVar, cVar2, bVar, str3, this.l, str6, str4, str5);
                                        if (kE == null) {
                                            kE = E(cVar, cVar2, bVar, str3, this.l, null, str4, str5);
                                        }
                                    }
                                    if (aVarF2 != null) {
                                        try {
                                            aVarF2.close();
                                        } catch (Exception e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    if (kE != null) {
                                        kE.g = z5;
                                        kE.h = str;
                                    }
                                    return kE;
                                }
                            } else {
                                aVarF = null;
                                z2 = false;
                                z3 = true;
                            }
                            if (!z2) {
                                if (e instanceof SocketTimeoutException) {
                                    w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                        } catch (IOException e4) {
                            e = e4;
                            if (e.getMessage() == null) {
                                aVarF = null;
                                z2 = false;
                                z3 = true;
                            } else {
                                aVarF = null;
                                z2 = false;
                                z3 = true;
                            }
                            if (!z2) {
                                if (e instanceof SocketTimeoutException) {
                                    w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                        } catch (IllegalArgumentException e5) {
                            e = e5;
                            if (e.getMessage() == null) {
                                aVarF = null;
                                z2 = false;
                                z3 = true;
                            } else {
                                aVarF = null;
                                z2 = false;
                                z3 = true;
                            }
                            if (!z2) {
                                if (e instanceof SocketTimeoutException) {
                                    w("SMB2 timeout");
                                } else {
                                    e.printStackTrace();
                                    w("Protocol not supported : " + e.getMessage());
                                }
                                return null;
                            }
                            aVarF2 = aVarF;
                        }
                    } catch (ax.p024b9.d | IOException | IllegalArgumentException e6) {
                        e = e6;
                        cVarJ = null;
                    }
                    if (aVarF2 == null && aVarF2.M() != null && (gVarA = aVarF2.M().a()) != null && ((!gVarA.m() && "SMB3".equals(str)) || (!gVarA.k() && "SMB2".equals(str)))) {
                        try {
                            aVarF2.close();
                        } catch (Exception unused2) {
                        }
                        w("Protocol does not match (" + str + " != " + gVarA + ")");
                        return null;
                    }
                    cVar = c.SMB2AND3;
                    cVar2 = cVarJ;
                } else {
                    if (!ax.E1.P.i() || TextUtils.isEmpty(str3)) {
                        cVar3 = c.SMB1;
                    } else {
                        try {
                            cVarJ2 = J.j(str, true);
                            try {
                                aVarF3 = J.f(cVarJ2, str3, this.l);
                                try {
                                    cVar = c.SMB2AND3;
                                    cVar2 = cVarJ2;
                                    aVarF2 = aVarF3;
                                } catch (ax.p024b9.d e7) {
                                    e = e7;
                                    if (e.getMessage() == null && e.getMessage().contains("EOF while reading packet")) {
                                        try {
                                            cVarJ2 = J.j(str, false);
                                            aVarF3 = J.f(cVarJ2, str3, this.l);
                                            z4 = true;
                                            z3 = false;
                                        } catch (ax.p024b9.d | IOException | IllegalArgumentException unused3) {
                                            z4 = false;
                                            z3 = true;
                                            if (z4) {
                                                cVar4 = c.SMB2AND3;
                                            } else {
                                                e.printStackTrace();
                                                cVar4 = c.SMB1;
                                            }
                                            cVar = cVar4;
                                            cVar2 = cVarJ2;
                                            aVarF2 = aVarF3;
                                            if (cVar == c.SMB1) {
                                                I.r(bVar, this.l);
                                            }
                                            if (z) {
                                                z5 = z3;
                                                kE = E(cVar, cVar2, bVar, str3, this.l, "?", "GUEST", HttpUrl.FRAGMENT_ENCODE_SET);
                                                if (kE == null) {
                                                    kE = E(cVar, cVar2, bVar, str3, this.l, "?", "Guest", HttpUrl.FRAGMENT_ENCODE_SET);
                                                    if (kE == null) {
                                                        kE = E(cVar, cVar2, bVar, str3, this.l, "?", "guest", HttpUrl.FRAGMENT_ENCODE_SET);
                                                        if (kE == null) {
                                                            kE = E(cVar, cVar2, bVar, str3, this.l, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                                                        }
                                                    }
                                                }
                                            } else {
                                                z5 = z3;
                                                if (this.m.length() > 0) {
                                                    str4 = this.m;
                                                } else {
                                                    str4 = null;
                                                }
                                                str5 = this.n.length() > 0 ? this.n : null;
                                                if (this.j.length() > 0) {
                                                    str6 = this.j;
                                                    z6 = false;
                                                } else {
                                                    str6 = str2;
                                                    z6 = true;
                                                }
                                                kE = E(cVar, cVar2, bVar, str3, this.l, str6, str4, str5);
                                                if (kE == null) {
                                                    kE = E(cVar, cVar2, bVar, str3, this.l, null, str4, str5);
                                                }
                                            }
                                            if (aVarF2 != null) {
                                                aVarF2.close();
                                            }
                                            if (kE != null) {
                                                kE.g = z5;
                                                kE.h = str;
                                            }
                                            return kE;
                                        }
                                    } else {
                                        z4 = false;
                                        z3 = true;
                                    }
                                    if (z4) {
                                        cVar4 = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar4 = c.SMB1;
                                    }
                                    cVar = cVar4;
                                    cVar2 = cVarJ2;
                                    aVarF2 = aVarF3;
                                } catch (IOException e8) {
                                    e = e8;
                                    if (e.getMessage() == null) {
                                        z4 = false;
                                        z3 = true;
                                    } else {
                                        z4 = false;
                                        z3 = true;
                                    }
                                    if (z4) {
                                        cVar4 = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar4 = c.SMB1;
                                    }
                                    cVar = cVar4;
                                    cVar2 = cVarJ2;
                                    aVarF2 = aVarF3;
                                } catch (IllegalArgumentException e9) {
                                    e = e9;
                                    if (e.getMessage() == null) {
                                        z4 = false;
                                        z3 = true;
                                    } else {
                                        z4 = false;
                                        z3 = true;
                                    }
                                    if (z4) {
                                        cVar4 = c.SMB2AND3;
                                    } else {
                                        e.printStackTrace();
                                        cVar4 = c.SMB1;
                                    }
                                    cVar = cVar4;
                                    cVar2 = cVarJ2;
                                    aVarF2 = aVarF3;
                                }
                            } catch (ax.p024b9.d | IOException | IllegalArgumentException e10) {
                                e = e10;
                                aVarF3 = null;
                            }
                        } catch (ax.p024b9.d | IOException | IllegalArgumentException e11) {
                            e = e11;
                            cVarJ2 = null;
                            aVarF3 = null;
                        }
                    }
                    z3 = true;
                }
                if (cVar == c.SMB1) {
                    I.r(bVar, this.l);
                }
                if (z) {
                    z5 = z3;
                    kE = E(cVar, cVar2, bVar, str3, this.l, "?", "GUEST", HttpUrl.FRAGMENT_ENCODE_SET);
                    if (kE == null) {
                        kE = E(cVar, cVar2, bVar, str3, this.l, "?", "Guest", HttpUrl.FRAGMENT_ENCODE_SET);
                        if (kE == null) {
                            kE = E(cVar, cVar2, bVar, str3, this.l, "?", "guest", HttpUrl.FRAGMENT_ENCODE_SET);
                            if (kE == null) {
                                kE = E(cVar, cVar2, bVar, str3, this.l, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                            }
                        }
                    }
                } else {
                    z5 = z3;
                    if (this.m.length() > 0) {
                        str4 = this.m;
                    } else {
                        str4 = null;
                    }
                    str5 = this.n.length() > 0 ? this.n : null;
                    if (this.j.length() > 0) {
                        str6 = this.j;
                        z6 = false;
                    } else {
                        str6 = str2;
                        z6 = true;
                    }
                    kE = E(cVar, cVar2, bVar, str3, this.l, str6, str4, str5);
                    if (kE == null && z6) {
                        kE = E(cVar, cVar2, bVar, str3, this.l, null, str4, str5);
                    }
                }
                if (aVarF2 != null) {
                    aVarF2.close();
                }
                if (kE != null) {
                    kE.g = z5;
                    kE.h = str;
                }
                return kE;
            }
            cVar3 = c.SMB1;
            cVar = cVar3;
            aVarF2 = null;
            cVar2 = null;
            z3 = true;
            if (cVar == c.SMB1) {
                I.r(bVar, this.l);
            }
            if (z) {
                z5 = z3;
                kE = E(cVar, cVar2, bVar, str3, this.l, "?", "GUEST", HttpUrl.FRAGMENT_ENCODE_SET);
                if (kE == null) {
                    kE = E(cVar, cVar2, bVar, str3, this.l, "?", "Guest", HttpUrl.FRAGMENT_ENCODE_SET);
                    if (kE == null) {
                        kE = E(cVar, cVar2, bVar, str3, this.l, "?", "guest", HttpUrl.FRAGMENT_ENCODE_SET);
                        if (kE == null) {
                            kE = E(cVar, cVar2, bVar, str3, this.l, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET, HttpUrl.FRAGMENT_ENCODE_SET);
                        }
                    }
                }
            } else {
                z5 = z3;
                if (this.m.length() > 0) {
                    str4 = this.m;
                } else {
                    str4 = null;
                }
                str5 = this.n.length() > 0 ? this.n : null;
                if (this.j.length() > 0) {
                    str6 = this.j;
                    z6 = false;
                } else {
                    str6 = str2;
                    z6 = true;
                }
                kE = E(cVar, cVar2, bVar, str3, this.l, str6, str4, str5);
                if (kE == null) {
                    kE = E(cVar, cVar2, bVar, str3, this.l, null, str4, str5);
                }
            }
            if (aVarF2 != null) {
                aVarF2.close();
            }
            if (kE != null) {
                kE.g = z5;
                kE.h = str;
            }
            return kE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: A, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.o != null) {
                if (!bool.booleanValue()) {
                    this.o.f0(false, this.s);
                    return;
                }
                if (!TextUtils.isEmpty(this.q)) {
                    this.q = V.Q(this.q);
                }
                this.o.f0(true, this.q);
            }
        }

        @Override // ax.p045d2.n
        protected void r() {
            InterfaceC5828b.a aVar = this.o;
            if (aVar != null) {
                aVar.N();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code duplicated, block: B:104:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:106:0x00ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:56:0x00b3  */
        /* JADX WARN: Code duplicated, block: B:59:0x00b8 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:61:0x00c5 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:62:0x00d1 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:64:0x00dc A[Catch: all -> 0x0024, TRY_LEAVE, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:67:0x00e2 A[DONT_GENERATE] */
        /* JADX WARN: Code duplicated, block: B:69:0x00e6 A[Catch: all -> 0x0024, TRY_ENTER, TRY_LEAVE, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:74:0x00f4 A[Catch: all -> 0x0024, Exception -> 0x0118, TRY_ENTER, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:76:0x011a A[Catch: all -> 0x0024, Exception -> 0x0118, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:81:0x013f A[Catch: all -> 0x0024, TRY_ENTER, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:83:0x0145 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:86:0x014c A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:87:0x0163 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:90:0x0176 A[Catch: all -> 0x0024, TryCatch #1 {all -> 0x0024, blocks: (B:5:0x0009, B:7:0x001a, B:13:0x0028, B:15:0x002d, B:18:0x0033, B:19:0x003d, B:21:0x0047, B:23:0x004b, B:25:0x0051, B:42:0x0090, B:44:0x0096, B:54:0x00ad, B:57:0x00b4, B:59:0x00b8, B:61:0x00c5, B:64:0x00dc, B:69:0x00e6, B:71:0x00ec, B:74:0x00f4, B:77:0x0133, B:81:0x013f, B:83:0x0145, B:76:0x011a, B:84:0x0148, B:86:0x014c, B:88:0x0172, B:90:0x0176, B:91:0x0189, B:87:0x0163, B:62:0x00d1, B:46:0x009a, B:48:0x00a0, B:50:0x00a3, B:28:0x0059, B:35:0x0065, B:37:0x006b), top: B:103:0x0009 }] */
        /* JADX WARN: Code duplicated, block: B:94:0x018f A[DONT_GENERATE] */
        /* JADX WARN: Instruction removed from duplicated block: B:74:0x00f4, please report this as an issue */
        /* JADX WARN: Instruction removed from duplicated block: B:76:0x011a, please report this as an issue */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public Boolean g(String... strArr) throws UnknownHostException {
            ax.Na.b bVarD;
            String str;
            String str2;
            String str3;
            ax.Na.b bVarD2;
            String str4;
            K kX;
            C4738b0 c4738b0;
            int i;
            L l;
            L l2 = this.p;
            if (l2 != null) {
                l2.h();
            }
            try {
                ax.Na.a.j("jcifs.smb.client.disablePlainTextPasswords", "false");
                boolean z = "anonymous".equals(this.m) && TextUtils.isEmpty(this.n);
                L l3 = this.p;
                Boolean boolValueOf = null;
                String strX = l3 != null ? l3.X() : null;
                try {
                    bVarD = B(this.p, this.k, this.j);
                    try {
                        String strF = bVarD.f();
                        if (TextUtils.isEmpty(strF)) {
                            if (!TextUtils.isEmpty(strF)) {
                            }
                            str = strX;
                            if (!TextUtils.isEmpty(str) && ax.p045d2.x.K(str)) {
                                try {
                                    bVarD = ax.Na.b.d(str);
                                } catch (UnknownHostException unused) {
                                }
                                str2 = str;
                            } else {
                                str3 = this.k;
                                str2 = str3;
                            }
                            if (bVarD == null) {
                                try {
                                    bVarD2 = ax.Na.b.d(str);
                                } catch (UnknownHostException unused2) {
                                    bVarD2 = bVarD;
                                }
                            } else {
                                bVarD2 = bVarD;
                            }
                            str4 = this.r;
                            if (str4 == null) {
                                kX = x("SMB2", str2, str, bVarD2, z);
                                if (kX == null) {
                                    kX = x(L.n, str2, str, bVarD2, z);
                                }
                            } else {
                                kX = x(str4, str2, str, bVarD2, z);
                            }
                            if (kX == null) {
                                return Boolean.FALSE;
                            }
                            if (kX.a == c.SMB1) {
                                try {
                                    if (this.l > 0) {
                                        c4738b0 = new C4738b0("smb://" + str2 + ":" + this.l + "/", kX.c);
                                    } else {
                                        c4738b0 = new C4738b0("smb://" + str2 + "/", kX.c);
                                    }
                                    boolValueOf = Boolean.valueOf(c4738b0.s());
                                } catch (Exception unused3) {
                                    str2 = null;
                                }
                                if (boolValueOf != null || !boolValueOf.booleanValue()) {
                                    str2 = this.k;
                                }
                            }
                            i = this.l;
                            if (i > 0) {
                                kX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                            } else {
                                kX.a(String.format(Locale.US, "smb://%s", str2));
                            }
                            l = this.p;
                            if (l != null) {
                                l.j0(kX);
                                this.p.g0(this.q);
                                this.p.h0(kX.h, kX.g);
                            }
                            return Boolean.TRUE;
                        }
                        if (this.p != null && !strF.equals(strX)) {
                            this.p.i0(strF);
                        }
                        strX = strF;
                    } catch (UnknownHostException e) {
                        e = e;
                        if (TextUtils.isEmpty(strX)) {
                            e.printStackTrace();
                            w("Unknown Host : " + e.getMessage());
                            return Boolean.FALSE;
                        }
                    }
                } catch (UnknownHostException e2) {
                    e = e2;
                    bVarD = null;
                }
                str = strX;
                if (!TextUtils.isEmpty(str)) {
                    bVarD = ax.Na.b.d(str);
                    str2 = str;
                    if (bVarD == null) {
                        bVarD2 = ax.Na.b.d(str);
                    } else {
                        bVarD2 = bVarD;
                    }
                    str4 = this.r;
                    if (str4 == null) {
                        kX = x("SMB2", str2, str, bVarD2, z);
                        if (kX == null) {
                            kX = x(L.n, str2, str, bVarD2, z);
                        }
                    } else {
                        kX = x(str4, str2, str, bVarD2, z);
                    }
                    if (kX == null) {
                        return Boolean.FALSE;
                    }
                    if (kX.a == c.SMB1) {
                        if (this.l > 0) {
                            c4738b0 = new C4738b0("smb://" + str2 + ":" + this.l + "/", kX.c);
                        } else {
                            c4738b0 = new C4738b0("smb://" + str2 + "/", kX.c);
                        }
                        boolValueOf = Boolean.valueOf(c4738b0.s());
                        if (boolValueOf != null) {
                            str2 = this.k;
                        } else {
                            str2 = this.k;
                        }
                    }
                    i = this.l;
                    if (i > 0) {
                        kX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                    } else {
                        kX.a(String.format(Locale.US, "smb://%s", str2));
                    }
                    l = this.p;
                    if (l != null) {
                        l.j0(kX);
                        this.p.g0(this.q);
                        this.p.h0(kX.h, kX.g);
                    }
                    return Boolean.TRUE;
                }
                str3 = this.k;
                str2 = str3;
                if (bVarD == null) {
                    bVarD2 = ax.Na.b.d(str);
                } else {
                    bVarD2 = bVarD;
                }
                str4 = this.r;
                if (str4 == null) {
                    kX = x("SMB2", str2, str, bVarD2, z);
                    if (kX == null) {
                        kX = x(L.n, str2, str, bVarD2, z);
                    }
                } else {
                    kX = x(str4, str2, str, bVarD2, z);
                }
                if (kX == null) {
                    return Boolean.FALSE;
                }
                if (kX.a == c.SMB1) {
                    if (this.l > 0) {
                        c4738b0 = new C4738b0("smb://" + str2 + ":" + this.l + "/", kX.c);
                    } else {
                        c4738b0 = new C4738b0("smb://" + str2 + "/", kX.c);
                    }
                    boolValueOf = Boolean.valueOf(c4738b0.s());
                    if (boolValueOf != null) {
                        str2 = this.k;
                    } else {
                        str2 = this.k;
                    }
                }
                i = this.l;
                if (i > 0) {
                    kX.a(String.format(Locale.US, "smb://%s:%s", str2, Integer.valueOf(i)));
                } else {
                    kX.a(String.format(Locale.US, "smb://%s", str2));
                }
                l = this.p;
                if (l != null) {
                    l.j0(kX);
                    this.p.g0(this.q);
                    this.p.h0(kX.h, kX.g);
                }
                return Boolean.TRUE;
            } finally {
                L l4 = this.p;
                if (l4 != null) {
                    l4.i();
                }
            }
        }

        boolean z(int i) {
            try {
                InetAddress byAddress = InetAddress.getByAddress(new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255)});
                return byAddress.isSiteLocalAddress() || byAddress.isAnyLocalAddress() || byAddress.isLoopbackAddress();
            } catch (UnknownHostException unused) {
                return false;
            }
        }

        public a(Context context, ax.G1.n nVar, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.h = context;
            D(nVar);
            this.o = aVar;
            this.i = (WifiManager) context.getApplicationContext().getSystemService("wifi");
        }
    }
}
