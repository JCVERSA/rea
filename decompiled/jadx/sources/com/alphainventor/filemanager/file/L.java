package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.p070e3.C1957a;
import ax.p070e3.C1960d;
import ax.p070e3.C1961e;
import ax.p070e3.C1966j;
import ax.p086f3.C2017x;
import ax.p086f3.c0;
import ax.p086f3.d0;
import ax.p086f3.g0;
import ax.p086f3.j0;
import ax.p406z3.C2461b;
import ax.p406z3.InterfaceC2462c;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.HostKey;
import com.jcraft.jsch.HostKeyConf;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchChangedHostKeyException;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.JSchUnknownHostKeyException;
import com.jcraft.jsch.KeyPair;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.SftpProgressMonitor;
import com.jcraft.jsch.SocketFactory;
import com.jcraft.jsch.UIKeyboardInteractive;
import com.jcraft.jsch.UserInfo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class L extends AbstractC6238m {
    static j z;
    private boolean i;
    private Session j;
    private Session k;
    private AtomicInteger l;
    private AtomicInteger m;
    private h n;
    private h o;
    private String p;
    private String q;
    private String r;
    private String s;
    private int t;
    private String u;
    private String v;
    private static final Logger y = Logger.getLogger("FileManager.SftpFileHelper");
    static SocketFactory A = new b();
    private final Object h = new Object();
    private Handler w = new Handler(Looper.getMainLooper());
    private Runnable x = new a();

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (L.this.h) {
                try {
                    if (L.this.n0()) {
                        L.y.fine("SFTP session idle timeout disconnect");
                        L.this.d0();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    class b implements SocketFactory {
        b() {
        }

        @Override // com.jcraft.jsch.SocketFactory
        public InputStream a(Socket socket) throws IOException {
            return socket.getInputStream();
        }

        @Override // com.jcraft.jsch.SocketFactory
        public OutputStream b(Socket socket) throws IOException {
            return socket.getOutputStream();
        }

        @Override // com.jcraft.jsch.SocketFactory
        public Socket c(String str, int i) throws Exception {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName == null || allByName.length == 0) {
                throw new SocketException("Impossible: empty address list");
            }
            for (int i2 = 0; i2 < allByName.length; i2++) {
                Socket socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress(allByName[i2], i), 15000);
                    return socket;
                } catch (Exception e) {
                    try {
                        socket.close();
                    } catch (Exception unused) {
                    }
                    if (i2 == allByName.length - 1) {
                        if (e instanceof IOException) {
                            throw e;
                        }
                        throw new IOException(e);
                    }
                }
            }
            throw new SocketException("not reachable");
        }
    }

    class c implements SftpProgressMonitor {
        long a = 0;
        final /* synthetic */ ax.p182l3.i b;
        final /* synthetic */ long c;
        final /* synthetic */ InterfaceC2462c d;

        c(ax.p182l3.i iVar, long j, InterfaceC2462c interfaceC2462c) {
            this.b = iVar;
            this.c = j;
            this.d = interfaceC2462c;
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void a() {
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public boolean b(long j) {
            long j2 = this.a + j;
            this.a = j2;
            ax.p182l3.i iVar = this.b;
            if (iVar != null) {
                iVar.a(j2, this.c);
            }
            InterfaceC2462c interfaceC2462c = this.d;
            return interfaceC2462c == null || !interfaceC2462c.isCancelled();
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void c(int i, String str, String str2, long j) {
            this.a = 0L;
        }
    }

    private static class d extends ax.p406z3.q<Object, Void, Boolean> {
        String h;
        int i;
        String j;
        String k;
        L l;
        InterfaceC6228c.a m;
        String n;
        String o;
        String p;
        boolean q;
        f r;
        String s;

        public d(Context context, L l, InterfaceC6228c.a aVar, int i) {
            super(ax.z3.q.e.CONNECT);
            this.l = l;
            this.m = aVar;
            y(L.i0(context).k(i));
            this.q = false;
        }

        public d(ax.p038c3.o oVar, InterfaceC6228c.a aVar) {
            super(ax.z3.q.e.CONNECT);
            this.m = aVar;
            y(oVar);
            this.q = true;
        }

        private void y(ax.p038c3.o oVar) {
            this.h = oVar.d();
            this.i = oVar.h();
            this.j = oVar.k();
            this.k = oVar.g();
            this.n = oVar.f();
            this.o = oVar.i();
            this.p = oVar.e();
        }

        @Override // ax.p406z3.q
        protected void r() {
            super.r();
            InterfaceC6228c.a aVar = this.m;
            if (aVar != null) {
                aVar.J();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Not initialized variable reg: 9, insn: 0x0049: MOVE (r11 I:??[OBJECT, ARRAY]) = (r9 I:??[OBJECT, ARRAY]), block:B:23:0x0049 */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            f fVar;
            Session sessionK0;
            L l = this.l;
            if (l != null) {
                l.i();
            }
            f fVar2 = null;
            try {
                try {
                    L.m0();
                    try {
                        if (!this.q) {
                            sessionK0 = L.k0(this.j, this.k, this.h, this.i, this.o, this.p, new f(this.p != null, false));
                        } else {
                            if (this.p == null) {
                                this.r = L.b0(this.j, this.h, this.i);
                                return Boolean.FALSE;
                            }
                            f fVar3 = new f(true, false);
                            try {
                                sessionK0 = L.k0(this.j, this.k, this.h, this.i, this.o, this.p, fVar3);
                            } catch (JSchChangedHostKeyException | JSchUnknownHostKeyException unused) {
                                this.r = fVar3;
                                return Boolean.FALSE;
                            }
                        }
                        L l2 = this.l;
                        if (l2 != null) {
                            l2.s0(sessionK0);
                            this.l.q0(this.j, this.k, this.h, this.i, this.o, this.p);
                            this.l.p0(true);
                            if (this.n == null) {
                                try {
                                    this.n = this.l.h0();
                                } catch (C1966j unused2) {
                                }
                            }
                            this.l.r0(this.n);
                        } else {
                            sessionK0.s();
                        }
                        return Boolean.TRUE;
                    } catch (JSchException e) {
                        e = e;
                        fVar2 = fVar;
                        this.s = e.getMessage();
                        if (((e instanceof JSchUnknownHostKeyException) || (e instanceof JSchChangedHostKeyException)) && this.p != null) {
                            this.r = fVar2;
                            return Boolean.FALSE;
                        }
                        e.printStackTrace();
                        return Boolean.FALSE;
                    }
                } catch (JSchException e2) {
                    e = e2;
                }
            } finally {
                L l3 = this.l;
                if (l3 != null) {
                    l3.j();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.q
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.m != null) {
                if (!TextUtils.isEmpty(this.n)) {
                    this.n = d0.U(this.n);
                }
                if (bool.booleanValue()) {
                    this.m.d0(bool.booleanValue(), this.n);
                } else if (this.r != null) {
                    this.m.d0(bool.booleanValue(), this.r);
                } else {
                    this.m.d0(bool.booleanValue(), this.s);
                }
            }
        }
    }

    class e {
        h a;
        ChannelSftp b;
        AtomicInteger c;

        e(h hVar, ChannelSftp channelSftp, AtomicInteger atomicInteger) {
            this.a = hVar;
            this.b = channelSftp;
            this.c = atomicInteger;
        }

        void a() {
            this.b.f();
        }

        InputStream b(String str, long j) throws SftpException {
            return this.b.i0(str, null, j);
        }

        String c() throws SftpException {
            return this.b.k0();
        }

        Vector d(String str) throws SftpException {
            return this.b.s0(i(str));
        }

        SftpATTRS e(String str) throws SftpException {
            return this.b.u0(i(str));
        }

        void f(String str) throws SftpException {
            this.b.v0(str);
        }

        void g(InputStream inputStream, String str, SftpProgressMonitor sftpProgressMonitor) throws SftpException {
            this.b.w0(inputStream, i(str), sftpProgressMonitor, 0);
        }

        void h(InputStream inputStream, String str, SftpProgressMonitor sftpProgressMonitor) throws SftpException {
            this.b.w0(inputStream, i(str), sftpProgressMonitor, 4);
        }

        String i(String str) {
            int i = 0;
            for (int i2 = 0; i2 < str.length(); i2++) {
                char cCharAt = str.charAt(i2);
                if (cCharAt == '\\' || cCharAt == '?' || cCharAt == '*') {
                    i++;
                }
            }
            if (i == 0) {
                return str;
            }
            StringBuffer stringBuffer = new StringBuffer(str.length() + i);
            for (int i3 = 0; i3 < str.length(); i3++) {
                char cCharAt2 = str.charAt(i3);
                if (cCharAt2 == '\\' || cCharAt2 == '?' || cCharAt2 == '*') {
                    stringBuffer.append('\\');
                }
                stringBuffer.append(cCharAt2);
            }
            return stringBuffer.toString();
        }

        void j() {
            int iDecrementAndGet = this.c.decrementAndGet();
            this.a.i(this.b);
            if (iDecrementAndGet <= 0) {
                L.this.u0();
            }
        }

        void k(String str, String str2) throws SftpException {
            this.b.A0(i(str), i(str2));
        }

        void l(String str) throws SftpException {
            this.b.B0(i(str));
        }

        void m(String str) throws SftpException {
            this.b.C0(i(str));
        }

        void n(String str, int i) throws SftpException {
            this.b.Z0(i(str), i);
        }

        SftpATTRS o(String str) throws SftpException {
            return this.b.c1(i(str));
        }
    }

    public static class f implements HostKeyConf {
        private String a;
        private boolean b;
        private String c;
        private String d;
        private HostKey e;

        f(boolean z, boolean z2) {
            this.a = z ? "yes" : "no";
            this.b = z2;
        }

        @Override // com.jcraft.jsch.HostKeyConf
        public void a(HostKey hostKey, String str, String str2) {
            this.e = hostKey;
            this.c = str;
            this.d = str2;
        }

        @Override // com.jcraft.jsch.HostKeyConf
        public boolean b() {
            return this.b;
        }

        @Override // com.jcraft.jsch.HostKeyConf
        public String c() {
            return this.a;
        }

        public String e() {
            return j0.f(this.e);
        }

        public String f() {
            return this.d;
        }

        public String g() {
            return this.c;
        }
    }

    static class g implements UserInfo, UIKeyboardInteractive {
        private String a;
        private String b;

        g(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // com.jcraft.jsch.UserInfo
        public boolean a(String str) {
            return !TextUtils.isEmpty(this.b);
        }

        @Override // com.jcraft.jsch.UserInfo
        public String b() {
            return this.b;
        }

        @Override // com.jcraft.jsch.UserInfo
        public boolean c(String str) {
            L.y.fine("sftp prompt : " + str);
            return !TextUtils.isEmpty(this.a);
        }

        @Override // com.jcraft.jsch.UserInfo
        public void d(String str) {
            L.y.fine("sftp prompt : " + str);
        }

        @Override // com.jcraft.jsch.UIKeyboardInteractive
        public String[] e(String str, String str2, String str3, String[] strArr, boolean[] zArr) {
            if (strArr != null) {
                for (String str4 : strArr) {
                    L.y.fine("sftp prompt KI : " + str4);
                }
            }
            if (strArr == null || strArr.length != 1) {
                return null;
            }
            return new String[]{this.a};
        }

        @Override // com.jcraft.jsch.UserInfo
        public String f() {
            return this.a;
        }

        @Override // com.jcraft.jsch.UserInfo
        public boolean g(String str) {
            L.y.fine("sftp prompt : " + str);
            return true;
        }
    }

    static class h extends ax.p406z3.t<ChannelSftp> {
        Session b;

        public h(Session session) {
            this.b = session;
        }

        public ChannelSftp f() throws ax.p070e3.K {
            try {
                ChannelSftp channelSftpD = (ChannelSftp) super.a();
                if (channelSftpD.r()) {
                    channelSftpD = d();
                }
                if (!channelSftpD.s()) {
                    channelSftpD.c();
                }
                return channelSftpD;
            } catch (JSchException e) {
                e.printStackTrace();
                ax.p302sb.c.h().g().d("SFTP CHANNEL OPEN ERROR 1").m(e).i();
                throw new ax.p070e3.K("Could not acquire channel", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.t
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void c(ChannelSftp channelSftp) {
            channelSftp.f();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p406z3.t
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public ChannelSftp d() throws ax.p070e3.K {
            try {
                return (ChannelSftp) this.b.K("sftp");
            } catch (JSchException e) {
                ax.p302sb.c.h().g().d("SFTP CHANNEL OPEN ERROR 2").m(e).i();
                throw new ax.p070e3.K("Could not open channel", e);
            }
        }

        public void i(ChannelSftp channelSftp) {
            if (channelSftp == null) {
                L.y.severe("Null channel object error");
            } else if (channelSftp.s()) {
                super.e(channelSftp);
            }
        }
    }

    class i extends ax.p086f3.H {
        e X;
        boolean Y;

        i(e eVar, InputStream inputStream) {
            super(inputStream);
            this.Y = false;
            this.X = eVar;
        }

        @Override // ax.p086f3.H, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.Y) {
                return;
            }
            super.close();
            this.X.j();
            this.Y = true;
        }
    }

    public static class j extends g0 {
        Context a;
        c0 b = new c0(ax.U2.f.N0);

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
                this.q.a(ax.U2.f.N0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC6228c.a
            public void d0(boolean z, Object obj) {
                if (z) {
                    j.this.o(this.X, this.Y);
                    ax.U2.f fVar = ax.U2.f.N0;
                    C6240o c6240oD = C2017x.d(fVar, this.X);
                    new AbstractC6238m.d(c6240oD.u()).i(new Long[0]);
                    ax.p038c3.b.k().s(c6240oD.B(), c6240oD.D());
                    this.q.d(fVar, this.X);
                    return;
                }
                if (!(obj instanceof f)) {
                    this.q.c(ax.U2.f.N0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                f fVar2 = (f) obj;
                HashMap map = new HashMap();
                map.put("host_key", fVar2.e());
                map.put("key_type", fVar2.g());
                map.put("key_fingerprint", fVar2.f());
                this.q.b(ax.U2.f.N0, this.X, map);
            }
        }

        j(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.T
        public void a(int i) {
            this.a.getSharedPreferences("SFTPPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("name_" + i).remove("hostkey_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
            this.a.getSharedPreferences("SFTPKeyPrefs", 0).edit().remove("privateKey_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.T
        public ax.p038c3.s f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            return new ax.p038c3.s(ax.U2.f.N0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.T
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("SFTPPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.p086f3.g0
        public ax.p038c3.o k(int i) {
            ax.p038c3.o oVar = new ax.p038c3.o();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            oVar.s(string);
            oVar.z(sharedPreferences.getInt("port_" + i, 22));
            oVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            oVar.y(this.b.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            oVar.v(sharedPreferences.getString("initialPath_" + i, null));
            oVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            oVar.t(sharedPreferences.getString("hostkey_" + i, null));
            oVar.A(this.a.getSharedPreferences("SFTPKeyPrefs", 0).getString("privateKey_" + i, null));
            return oVar;
        }

        @Override // ax.p086f3.g0
        public void l(int i, ax.p038c3.o oVar, ax.p182l3.k kVar, boolean z) {
            if (i == -100) {
                i = m();
            }
            if (z) {
                new d(oVar, new a(kVar, i, oVar)).h(new Object[0]);
            } else {
                o(i, oVar);
                kVar.d(ax.U2.f.N0, i);
            }
        }

        int m() {
            return this.a.getSharedPreferences("SFTPPrefs", 0).getInt("count", 0);
        }

        public List<ax.p038c3.s> n() {
            ArrayList arrayList = new ArrayList();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            int i = sharedPreferences.getInt("count", 0);
            for (int i2 = 0; i2 < i; i2++) {
                if (sharedPreferences.getString("host_" + i2, null) != null) {
                    arrayList.add(f(i2));
                }
            }
            return arrayList;
        }

        void o(int i, ax.p038c3.o oVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, oVar.d()).putInt("port_" + i, oVar.h()).putString("username_" + i, oVar.k()).putString("password_" + i, this.b.e(oVar.d(), oVar.g())).putString("initialPath_" + i, oVar.f()).putString("name_" + i, oVar.b());
            if (oVar.e() != null) {
                editorEdit.putString("hostkey_" + i, oVar.e());
            }
            if (z) {
                editorEdit.putLong("created_" + i, System.currentTimeMillis());
                editorEdit.putLong("sortindex_" + i, System.currentTimeMillis());
            }
            if (z) {
                editorEdit.putInt("count", i + 1);
            }
            editorEdit.commit();
            SharedPreferences.Editor editorEdit2 = this.a.getSharedPreferences("SFTPKeyPrefs", 0).edit();
            editorEdit2.putString("privateKey_" + i, oVar.i());
            editorEdit2.commit();
        }

        public void p(int i, String str) {
            this.a.getSharedPreferences("SFTPPrefs", 0).edit().putString("hostkey_" + i, str).commit();
        }
    }

    public L() {
        m0();
    }

    private void a0() {
        this.w.removeCallbacks(this.x);
    }

    static f b0(String str, String str2, int i2) throws JSchException {
        f fVar = new f(true, true);
        try {
            Session sessionL = new JSch().l(str, str2, i2);
            sessionL.X(fVar);
            sessionL.q();
            sessionL.s();
            return fVar;
        } catch (JSchException e2) {
            if (fVar.e == null) {
                throw e2;
            }
            return fVar;
        } catch (NullPointerException e3) {
            if (fVar.e == null) {
                throw new JSchException("unsupported key : " + e3.getMessage(), e3);
            }
            return fVar;
        } catch (RuntimeException e4) {
            if (fVar.e == null) {
                throw new JSchException("sftp session error : " + e4.getMessage(), e4);
            }
            return fVar;
        }
    }

    private C1966j c0(String str, SftpException sftpException) {
        int i2 = sftpException.q;
        if (i2 == 3) {
            return new C1961e(str, sftpException);
        }
        return i2 == 2 ? new ax.p070e3.t(sftpException) : C1960d.b(str, sftpException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0() {
        Session session = this.j;
        if (session != null && session.J()) {
            this.j.s();
        }
        Session session2 = this.k;
        if (session2 == null || !session2.J()) {
            return;
        }
        this.k.s();
    }

    public static String e0(String str) {
        try {
            String line = new BufferedReader(new StringReader(str)).readLine();
            if (line == null) {
                return "########";
            }
            String strTrim = line.replaceAll("-", HttpUrl.FRAGMENT_ENCODE_SET).trim();
            return strTrim.startsWith("BEGIN") ? strTrim.substring(5).trim() : strTrim;
        } catch (IOException unused) {
            return "########";
        }
    }

    private e f0(boolean z2) throws C1966j {
        try {
            synchronized (this.h) {
                try {
                    v0(z2);
                    h hVar = z2 ? this.n : this.o;
                    ChannelSftp channelSftpF = hVar.f();
                    if (channelSftpF == null) {
                        throw new C1966j("open channel returns null");
                    }
                    a0();
                    if (z2) {
                        this.l.incrementAndGet();
                        return new e(hVar, channelSftpF, this.l);
                    }
                    this.m.incrementAndGet();
                    return new e(hVar, channelSftpF, this.m);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (ax.p070e3.K e2) {
            throw new C1966j(e2.getMessage(), e2);
        } catch (JSchException e3) {
            throw C1960d.b("getChannelWrapper", e3);
        }
    }

    public static int g0() {
        return 22;
    }

    public static j i0(Context context) {
        if (z == null) {
            z = new j(context.getApplicationContext());
        }
        return z;
    }

    private e j0() throws C1966j {
        return f0(true);
    }

    static Session k0(String str, String str2, String str3, int i2, String str4, String str5, f fVar) throws Throwable {
        boolean z2 = str4 != null;
        try {
            JSch jSch = new JSch();
            if (str5 != null) {
                jSch.o(new j0(str5));
            }
            if (z2) {
                jSch.d("name", !TextUtils.isEmpty(str4) ? str4.getBytes("UTF-8") : null, null, !TextUtils.isEmpty(str2) ? str2.getBytes("UTF-8") : null);
            }
            Session sessionL = jSch.l(str, str3, i2);
            if (!z2) {
                sessionL.a0(str2);
                sessionL.j0(new g(str2, null));
            }
            if (fVar != null) {
                sessionL.X(fVar);
            }
            sessionL.h0(A);
            sessionL.q();
            return sessionL;
        } catch (UnsupportedEncodingException e2) {
            throw new JSchException("unsupported encoding", e2);
        } catch (NullPointerException e3) {
            throw new JSchException("unsupported key", e3);
        } catch (RuntimeException e4) {
            throw new JSchException("sftp session error", e4);
        }
    }

    private e l0() throws C1966j {
        return f0(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void m0() {
        JSch.n("PreferredAuthentications", "gssapi-with-mic,publickey,password,keyboard-interactive");
        JSch.n("MaxAuthTries", "3");
        boolean z2 = false;
        try {
            if (AlgorithmParameters.getInstance("EC") != null) {
                z2 = true;
            }
        } catch (NoSuchAlgorithmException unused) {
        }
        JSch.n("kex", "curve25519-sha256,curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha256,diffie-hellman-group16-sha512,diffie-hellman-group18-sha512,diffie-hellman-group14-sha256,diffie-hellman-group14-sha1");
        JSch.n("cipher.s2c", "aes128-ctr,aes192-ctr,aes256-ctr,aes128-gcm@openssh.com,aes256-gcm@openssh.com,chacha20-poly1305@openssh.com");
        JSch.n("cipher.c2s", "aes128-ctr,aes192-ctr,aes256-ctr,aes128-gcm@openssh.com,aes256-gcm@openssh.com,chacha20-poly1305@openssh.com");
        if (z2) {
            JSch.n("server_host_key", "ssh-ed25519,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521,rsa-sha2-512,rsa-sha2-256,ssh-rsa,ssh-dss");
            JSch.n("PubkeyAcceptedAlgorithms", "ssh-ed25519,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521,rsa-sha2-512,rsa-sha2-256,ssh-rsa,ssh-dss");
            JSch.n("PubkeyAcceptedAlgorithmsOldServer", "ssh-ed25519,ecdsa-sha2-nistp256,ecdsa-sha2-nistp384,ecdsa-sha2-nistp521,ssh-rsa,rsa-sha2-256,rsa-sha2-512,ssh-dss");
        } else {
            JSch.n("server_host_key", "ssh-ed25519,rsa-sha2-512,rsa-sha2-256,ssh-rsa,ssh-dss");
            JSch.n("PubkeyAcceptedAlgorithms", "ssh-ed25519,rsa-sha2-512,rsa-sha2-256,ssh-rsa,ssh-dss");
            JSch.n("PubkeyAcceptedAlgorithmsOldServer", "ssh-ed25519,ssh-rsa,rsa-sha2-256,rsa-sha2-512,ssh-dss");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean n0() {
        AtomicInteger atomicInteger = this.l;
        boolean z2 = atomicInteger == null || atomicInteger.get() == 0;
        AtomicInteger atomicInteger2 = this.m;
        return z2 && (atomicInteger2 == null || atomicInteger2.get() == 0);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0083  */
    public static boolean o0(C6240o c6240o, AbstractC6239n abstractC6239n) throws Throwable {
        if (abstractC6239n.p() > 524288) {
            return false;
        }
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamH1 = c6240o.h1(abstractC6239n, 0L);
                try {
                    boolean z2 = KeyPair.v(new JSch(), ax.p086f3.F.h(inputStreamH1, (int) abstractC6239n.p()), null) != null;
                    if (inputStreamH1 != null) {
                        ax.p086f3.F.a(inputStreamH1);
                    }
                    return z2;
                } catch (C1966j e2) {
                    e = e2;
                    inputStream = inputStreamH1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    return false;
                } catch (JSchException e3) {
                    e = e3;
                    inputStream = inputStreamH1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    return false;
                } catch (IOException e4) {
                    e = e4;
                    inputStream = inputStreamH1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    return false;
                } catch (OutOfMemoryError e5) {
                    e = e5;
                    inputStream = inputStreamH1;
                    ax.p302sb.c.h().g().b("SSH KEY OOM").m(e).i();
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    return false;
                } catch (RuntimeException e6) {
                    e = e6;
                    inputStream = inputStreamH1;
                    ax.p302sb.c.h().g().b("SSH KEY RUNTIME").m(e).i();
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamH1;
                    if (inputStream != null) {
                        ax.p086f3.F.a(inputStream);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (C1966j e7) {
            e = e7;
        } catch (JSchException e8) {
            e = e8;
        } catch (IOException e9) {
            e = e9;
        } catch (OutOfMemoryError e10) {
            e = e10;
        } catch (RuntimeException e11) {
            e = e11;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p0(boolean z2) {
        this.i = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s0(Session session) {
        synchronized (this.h) {
            this.j = session;
            this.l = new AtomicInteger(0);
            try {
                this.j.g0(15000);
            } catch (JSchException unused) {
            }
            this.n = new h(this.j);
            u0();
        }
    }

    private void t0(Session session) {
        synchronized (this.h) {
            this.k = session;
            this.m = new AtomicInteger(0);
            try {
                this.k.g0(15000);
            } catch (JSchException unused) {
            }
            this.o = new h(this.k);
            u0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u0() {
        this.w.removeCallbacks(this.x);
        if (n0()) {
            this.w.postDelayed(this.x, 180000L);
        }
    }

    private void v0(boolean z2) throws Throwable {
        Session session = z2 ? this.j : this.k;
        if (session == null || !session.J()) {
            y.fine("SFTP session created");
            Session sessionK0 = k0(this.q, this.r, this.s, this.t, this.u, this.v, new f(this.v != null, false));
            if (z2) {
                s0(sessionK0);
            } else {
                t0(sessionK0);
            }
        }
    }

    private void w0(AbstractC6239n abstractC6239n, ax.p086f3.G g2, long j2, Long l, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        e eVarL0 = l0();
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamB = g2.b();
                c cVar = new c(iVar, j2, interfaceC2462c);
                if (z2) {
                    eVarL0.g(inputStreamB, abstractC6239n.E(), cVar);
                } else {
                    eVarL0.h(inputStreamB, abstractC6239n.E(), cVar);
                }
                if (interfaceC2462c != null && interfaceC2462c.isCancelled()) {
                    throw new C1957a();
                }
                if (l != null && l.longValue() > 0) {
                    try {
                        eVarL0.n(abstractC6239n.E(), (int) (l.longValue() / 1000));
                    } catch (Exception unused) {
                    }
                }
                if (inputStreamB != null) {
                    try {
                        inputStreamB.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                eVarL0.j();
            } catch (SftpException e3) {
                throw c0("sftp writeFile", e3);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            eVarL0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean K() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean O() {
        return false;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public boolean P() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public void R(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l, C6241p c6241p, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        w0(abstractC6239n, g2, j2, l, true, interfaceC2462c, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean a() {
        return this.i;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public AbstractC6239n a1(String str) throws C1966j {
        e eVarJ0 = j0();
        try {
            try {
                M m = new M(this, eVarJ0, eVarJ0.e(str), str);
                eVarJ0.j();
                return m;
            } catch (SftpException e2) {
                e2.printStackTrace();
                int i2 = e2.q;
                if (i2 == 2) {
                    M m2 = new M(this, str);
                    eVarJ0.j();
                    return m2;
                }
                if (i2 != 4) {
                    throw c0("getFileInfo", e2);
                }
                eVarJ0.a();
                throw new C1966j(e2);
            }
        } catch (Throwable th) {
            eVarJ0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void b() {
        a0();
        p0(false);
        d0();
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream b1(String str, String str2, String str3) {
        return w(str, str2);
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
        String strE = abstractC6239n.E();
        e eVarJ0 = j0();
        ArrayList arrayList = new ArrayList();
        try {
            try {
                for (Object obj : eVarJ0.d(strE)) {
                    if (obj instanceof ChannelSftp.LsEntry) {
                        ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) obj;
                        String strK = lsEntry.k();
                        if (!d0.C(strK)) {
                            if (TextUtils.isEmpty(strK) || strK.startsWith("/") || strK.endsWith("/")) {
                                ax.p302sb.c.h().d("SFTP CHILD NAME:").k().h(strE + ":" + strK).i();
                            } else {
                                arrayList.add(new M(this, eVarJ0, lsEntry.h(), d0.Q(strE, strK)));
                            }
                        }
                    }
                }
                eVarJ0.j();
                return arrayList;
            } catch (SftpException e2) {
                int i2 = e2.q;
                if (i2 == 3) {
                    throw new C1961e(e2);
                }
                if (i2 == 4) {
                    eVarJ0.a();
                    throw new ax.p070e3.E(e2);
                }
                eVarJ0.a();
                throw c0("listChildren", e2);
            }
        } catch (Throwable th) {
            eVarJ0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public int e1(String str, String str2) {
        return -1;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public String f1(AbstractC6239n abstractC6239n) {
        if (H(abstractC6239n)) {
            return AbstractC6238m.A(abstractC6239n);
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void g1(AbstractC6239n abstractC6239n) throws C1966j {
        C2461b.g("not support delete file recursively");
    }

    public String h0() throws C1966j {
        e eVarJ0 = j0();
        try {
            try {
                String strC = eVarJ0.c();
                eVarJ0.j();
                return strC;
            } catch (SftpException e2) {
                throw c0("getHomePath", e2);
            }
        } catch (Throwable th) {
            eVarJ0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public InputStream h1(AbstractC6239n abstractC6239n, long j2) throws C1966j {
        e eVarJ0 = j0();
        try {
            return new i(eVarJ0, eVarJ0.b(abstractC6239n.E(), j2));
        } catch (SftpException e2) {
            throw c0("sftp getInputStream", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void i1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n2.o());
        e eVarJ0 = j0();
        try {
            try {
                long jP = abstractC6239n.p();
                eVarJ0.k(abstractC6239n.E(), abstractC6239n2.E());
                if (iVar != null) {
                    iVar.a(jP, jP);
                }
                eVarJ0.j();
            } catch (SftpException e2) {
                e2.printStackTrace();
                throw c0("moveFile 2:" + e2.q, e2);
            }
        } catch (Throwable th) {
            eVarJ0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void j1(Activity activity, Fragment fragment, InterfaceC6228c.a aVar) {
        try {
            new d(p(), this, aVar, t()).i(new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (aVar != null) {
                aVar.J();
                aVar.d0(false, e2.getMessage());
            }
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean k1() {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void l1(AbstractC6239n abstractC6239n, ax.p086f3.G g2, String str, long j2, Long l, C6241p c6241p, boolean z2, InterfaceC2462c interfaceC2462c, ax.p182l3.i iVar) throws C1957a, C1966j {
        C2461b.a(abstractC6239n.o());
        w0(abstractC6239n, g2, j2, l, false, interfaceC2462c, iVar);
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x000c */
    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    synchronized void m(AbstractC6239n abstractC6239n, String str, boolean z2, boolean z3, ax.p182l3.h hVar, InterfaceC2462c interfaceC2462c) throws Throwable {
        try {
            o(abstractC6239n, str, z2, z3, hVar, interfaceC2462c);
        } catch (Throwable th) {
            th = th;
            while (true) {
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
        e eVarJ0 = null;
        try {
            try {
                eVarJ0 = j0();
                eVarJ0.f(abstractC6239n.E());
                eVarJ0.j();
                return true;
            } catch (Throwable th) {
                if (0 != 0) {
                    eVarJ0.j();
                }
                throw th;
            }
        } catch (C1966j | SftpException e2) {
            e2.printStackTrace();
            if (eVarJ0 == null) {
                return false;
            }
            eVarJ0.j();
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean o1(AbstractC6239n abstractC6239n) {
        return l(abstractC6239n);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean p1() {
        return false;
    }

    void q0(String str, String str2, String str3, int i2, String str4, String str5) {
        this.q = str;
        this.r = str2;
        this.s = str3;
        this.t = i2;
        this.u = str4;
        this.v = str5;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public void q1(AbstractC6239n abstractC6239n) throws C1966j {
        e eVarJ0 = j0();
        try {
            try {
                if (eVarJ0.o(abstractC6239n.E()).k()) {
                    eVarJ0.m(abstractC6239n.E());
                } else {
                    eVarJ0.l(abstractC6239n.E());
                }
                eVarJ0.j();
            } catch (SftpException e2) {
                e2.printStackTrace();
                throw c0("delete", e2);
            }
        } catch (Throwable th) {
            eVarJ0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6238m
    public String r() {
        return this.p;
    }

    public void r0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.p = x();
        } else {
            this.p = str;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC6228c
    public boolean r1(AbstractC6239n abstractC6239n, AbstractC6239n abstractC6239n2) {
        return true;
    }
}
