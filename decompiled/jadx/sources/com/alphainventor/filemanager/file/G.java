package com.alphainventor.filemanager.file;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import ax.I1.C0710a;
import ax.I1.C0712c;
import ax.I1.C0713d;
import ax.I1.C0718i;
import ax.J1.C0744z;
import ax.J1.U;
import ax.J1.V;
import ax.J1.Y;
import ax.J1.a0;
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
public class G extends AbstractC5837k {
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
            synchronized (G.this.h) {
                try {
                    if (G.this.h0()) {
                        G.y.fine("SFTP session idle timeout disconnect");
                        G.this.X();
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
        final /* synthetic */ ax.P1.i b;
        final /* synthetic */ long c;
        final /* synthetic */ ax.p045d2.c d;

        c(ax.P1.i iVar, long j, ax.p045d2.c cVar) {
            this.b = iVar;
            this.c = j;
            this.d = cVar;
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void a() {
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public boolean b(long j) {
            long j2 = this.a + j;
            this.a = j2;
            ax.P1.i iVar = this.b;
            if (iVar != null) {
                iVar.a(j2, this.c);
            }
            ax.p045d2.c cVar = this.d;
            return cVar == null || !cVar.isCancelled();
        }

        @Override // com.jcraft.jsch.SftpProgressMonitor
        public void c(int i, String str, String str2, long j) {
            this.a = 0L;
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
                G.this.o0();
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
        public boolean a(HostKey hostKey, String str, String str2) {
            this.e = hostKey;
            this.c = str;
            this.d = str2;
            return this.b;
        }

        @Override // com.jcraft.jsch.HostKeyConf
        public String b() {
            return this.a;
        }

        public String d() {
            return a0.f(this.e);
        }

        public String e() {
            return this.d;
        }

        public String f() {
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
            G.y.fine("sftp prompt : " + str);
            return !TextUtils.isEmpty(this.a);
        }

        @Override // com.jcraft.jsch.UserInfo
        public void d(String str) {
            G.y.fine("sftp prompt : " + str);
        }

        @Override // com.jcraft.jsch.UIKeyboardInteractive
        public String[] e(String str, String str2, String str3, String[] strArr, boolean[] zArr) {
            if (strArr != null) {
                for (String str4 : strArr) {
                    G.y.fine("sftp prompt KI : " + str4);
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
            G.y.fine("sftp prompt : " + str);
            return true;
        }
    }

    static class h extends ax.p045d2.q<ChannelSftp> {
        Session b;

        public h(Session session) {
            this.b = session;
        }

        public ChannelSftp f() throws ax.I1.I {
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
                ax.p221pa.c.h().g().d("SFTP CHANNEL OPEN ERROR 1").m(e).i();
                throw new ax.I1.I("Could not acquire channel", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.q
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public void c(ChannelSftp channelSftp) {
            channelSftp.f();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.q
        /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
        public ChannelSftp d() throws ax.I1.I {
            try {
                return (ChannelSftp) this.b.K("sftp");
            } catch (JSchException e) {
                ax.p221pa.c.h().g().d("SFTP CHANNEL OPEN ERROR 2").m(e).i();
                throw new ax.I1.I("Could not open channel", e);
            }
        }

        public void i(ChannelSftp channelSftp) {
            if (channelSftp == null) {
                G.y.severe("Null channel object error");
            } else if (channelSftp.s()) {
                super.e(channelSftp);
            }
        }
    }

    class i extends ax.J1.B {
        e X;
        boolean Y;

        i(e eVar, InputStream inputStream) {
            super(inputStream);
            this.Y = false;
            this.X = eVar;
        }

        @Override // ax.J1.B, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.Y) {
                return;
            }
            super.close();
            this.X.j();
            this.Y = true;
        }
    }

    public static class j extends Y {
        Context a;
        U b = new U(ax.p338y1.f.K0);

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
                this.q.b(ax.p338y1.f.K0);
            }

            @Override // com.alphainventor.filemanager.file.InterfaceC5828b.a
            public void f0(boolean z, Object obj) {
                if (z) {
                    j.this.o(this.X, this.Y);
                    ax.p338y1.f fVar = ax.p338y1.f.K0;
                    C5839m c5839mE = ax.J1.r.e(fVar, this.X);
                    new AbstractC5837k.c(c5839mE.t()).i(new Long[0]);
                    ax.G1.b.k().s(c5839mE.z(), c5839mE.B());
                    this.q.d(fVar, this.X);
                    return;
                }
                if (!(obj instanceof f)) {
                    this.q.a(ax.p338y1.f.K0, this.Y.d(), this.Y.h(), this.Y.k(), obj instanceof String ? (String) obj : null);
                    return;
                }
                f fVar2 = (f) obj;
                HashMap map = new HashMap();
                map.put("host_key", fVar2.d());
                map.put("key_type", fVar2.f());
                map.put("key_fingerprint", fVar2.e());
                this.q.c(ax.p338y1.f.K0, this.X, map);
            }
        }

        j(Context context) {
            this.a = context;
        }

        @Override // com.alphainventor.filemanager.file.N
        public void a(int i) {
            this.a.getSharedPreferences("SFTPPrefs", 0).edit().remove("version_" + i).remove("name_" + i).remove("host_" + i).remove("port_" + i).remove("username_" + i).remove("password_" + i).remove("initialPath_" + i).remove("name_" + i).remove("hostkey_" + i).remove("created_" + i).remove("sortindex_" + i).commit();
            this.a.getSharedPreferences("SFTPKeyPrefs", 0).edit().remove("privateKey_" + i).commit();
        }

        @Override // com.alphainventor.filemanager.file.N
        public ax.G1.p f(int i) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            return new ax.G1.p(ax.p338y1.f.K0, i, sharedPreferences.getString("name_" + i, null), sharedPreferences.getString("username_" + i, "anonymous"), sharedPreferences.getString("host_" + i, null), sharedPreferences.getString("initialPath_" + i, null), sharedPreferences.getLong("created_" + i, 0L), sharedPreferences.getLong("sortindex_" + i, 0L));
        }

        @Override // com.alphainventor.filemanager.file.N
        public void j(int i, long j) {
            SharedPreferences.Editor editorEdit = this.a.getSharedPreferences("SFTPPrefs", 0).edit();
            editorEdit.putLong("sortindex_" + i, j);
            editorEdit.apply();
        }

        @Override // ax.J1.Y
        public ax.G1.n k(int i) {
            ax.G1.n nVar = new ax.G1.n();
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            int i2 = sharedPreferences.getInt("version_" + i, 0);
            String string = sharedPreferences.getString("host_" + i, HttpUrl.FRAGMENT_ENCODE_SET);
            nVar.s(string);
            nVar.z(sharedPreferences.getInt("port_" + i, 22));
            nVar.D(sharedPreferences.getString("username_" + i, "anonymous"));
            nVar.y(this.b.a(i2, string, sharedPreferences.getString("password_" + i, HttpUrl.FRAGMENT_ENCODE_SET)));
            nVar.v(sharedPreferences.getString("initialPath_" + i, null));
            nVar.q(sharedPreferences.getString("name_" + i, HttpUrl.FRAGMENT_ENCODE_SET));
            nVar.t(sharedPreferences.getString("hostkey_" + i, null));
            nVar.A(this.a.getSharedPreferences("SFTPKeyPrefs", 0).getString("privateKey_" + i, null));
            return nVar;
        }

        @Override // ax.J1.Y
        public void l(int i, ax.G1.n nVar, ax.P1.j jVar, boolean z) {
            if (i == -100) {
                i = m();
            }
            if (z) {
                new d(nVar, new a(jVar, i, nVar)).h(new Object[0]);
            } else {
                o(i, nVar);
                jVar.d(ax.p338y1.f.K0, i);
            }
        }

        int m() {
            return this.a.getSharedPreferences("SFTPPrefs", 0).getInt("count", 0);
        }

        public List<ax.G1.p> n() {
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

        void o(int i, ax.G1.n nVar) {
            SharedPreferences sharedPreferences = this.a.getSharedPreferences("SFTPPrefs", 0);
            boolean z = i >= sharedPreferences.getInt("count", 0);
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putInt("version_" + i, 3).putString("host_" + i, nVar.d()).putInt("port_" + i, nVar.h()).putString("username_" + i, nVar.k()).putString("password_" + i, this.b.e(nVar.d(), nVar.g())).putString("initialPath_" + i, nVar.f()).putString("name_" + i, nVar.b());
            if (nVar.e() != null) {
                editorEdit.putString("hostkey_" + i, nVar.e());
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
            editorEdit2.putString("privateKey_" + i, nVar.i());
            editorEdit2.commit();
        }

        public void p(int i, String str) {
            this.a.getSharedPreferences("SFTPPrefs", 0).edit().putString("hostkey_" + i, str).commit();
        }
    }

    public G() {
        g0();
    }

    private void U() {
        this.w.removeCallbacks(this.x);
    }

    static f V(String str, String str2, int i2) throws JSchException {
        f fVar = new f(true, true);
        try {
            Session sessionL = new JSch().l(str, str2, i2);
            sessionL.X(fVar);
            sessionL.q();
            sessionL.s();
            return fVar;
        } catch (JSchException e2) {
            if (fVar.e != null) {
                return fVar;
            }
            throw e2;
        } catch (NullPointerException e3) {
            throw new JSchException("unsupported key : " + e3.getMessage(), e3);
        } catch (RuntimeException e4) {
            throw new JSchException("sftp session error : " + e4.getMessage(), e4);
        }
    }

    private C0718i W(String str, SftpException sftpException) {
        int i2 = sftpException.q;
        if (i2 == 3) {
            return new C0713d(str, sftpException);
        }
        return i2 == 2 ? new ax.I1.s(sftpException) : C0712c.b(str, sftpException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
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

    public static String Y(String str) {
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

    private e Z(boolean z2) throws C0718i {
        try {
            synchronized (this.h) {
                try {
                    p0(z2);
                    h hVar = z2 ? this.n : this.o;
                    ChannelSftp channelSftpF = hVar.f();
                    if (channelSftpF == null) {
                        throw new C0718i("open channel returns null");
                    }
                    U();
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
        } catch (ax.I1.I e2) {
            throw new C0718i(e2.getMessage(), e2);
        } catch (JSchException e3) {
            throw C0712c.b("getChannelWrapper", e3);
        }
    }

    public static int a0() {
        return 22;
    }

    public static j c0(Context context) {
        if (z == null) {
            z = new j(context.getApplicationContext());
        }
        return z;
    }

    private e d0() throws C0718i {
        return Z(true);
    }

    static Session e0(String str, String str2, String str3, int i2, String str4, String str5, f fVar) throws Throwable {
        boolean z2 = str4 != null;
        try {
            JSch jSch = new JSch();
            if (str5 != null) {
                jSch.o(new a0(str5));
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

    private e f0() throws C0718i {
        return Z(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g0() {
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
    public boolean h0() {
        AtomicInteger atomicInteger = this.l;
        boolean z2 = atomicInteger == null || atomicInteger.get() == 0;
        AtomicInteger atomicInteger2 = this.m;
        return z2 && (atomicInteger2 == null || atomicInteger2.get() == 0);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0083  */
    public static boolean i0(C5839m c5839m, AbstractC5838l abstractC5838l) throws Throwable {
        if (abstractC5838l.o() > 524288) {
            return false;
        }
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamD1 = c5839m.d1(abstractC5838l, 0L);
                try {
                    boolean z2 = KeyPair.v(new JSch(), C0744z.j(inputStreamD1, (int) abstractC5838l.o()), null) != null;
                    if (inputStreamD1 != null) {
                        C0744z.a(inputStreamD1);
                    }
                    return z2;
                } catch (C0718i e2) {
                    e = e2;
                    inputStream = inputStreamD1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    return false;
                } catch (JSchException e3) {
                    e = e3;
                    inputStream = inputStreamD1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    return false;
                } catch (IOException e4) {
                    e = e4;
                    inputStream = inputStreamD1;
                    e.printStackTrace();
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    return false;
                } catch (OutOfMemoryError e5) {
                    e = e5;
                    inputStream = inputStreamD1;
                    ax.p221pa.c.h().g().b("SSH KEY OOM").m(e).i();
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    return false;
                } catch (RuntimeException e6) {
                    e = e6;
                    inputStream = inputStreamD1;
                    ax.p221pa.c.h().g().b("SSH KEY RUNTIME").m(e).i();
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamD1;
                    if (inputStream != null) {
                        C0744z.a(inputStream);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (C0718i e7) {
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
    public void j0(boolean z2) {
        this.i = z2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m0(Session session) {
        synchronized (this.h) {
            this.j = session;
            this.l = new AtomicInteger(0);
            try {
                this.j.g0(15000);
            } catch (JSchException unused) {
            }
            this.n = new h(this.j);
            o0();
        }
    }

    private void n0(Session session) {
        synchronized (this.h) {
            this.k = session;
            this.m = new AtomicInteger(0);
            try {
                this.k.g0(15000);
            } catch (JSchException unused) {
            }
            this.o = new h(this.k);
            o0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o0() {
        this.w.removeCallbacks(this.x);
        if (h0()) {
            this.w.postDelayed(this.x, 180000L);
        }
    }

    private void p0(boolean z2) throws Throwable {
        Session session = z2 ? this.j : this.k;
        if (session == null || !session.J()) {
            y.fine("SFTP session created");
            Session sessionE0 = e0(this.q, this.r, this.s, this.t, this.u, this.v, new f(this.v != null, false));
            if (z2) {
                m0(sessionE0);
            } else {
                n0(sessionE0);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0073 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void q0(AbstractC5838l abstractC5838l, ax.J1.A a2, long j2, Long l, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        e eVarF0 = f0();
        InputStream inputStream = null;
        try {
            try {
                InputStream inputStreamB = a2.b();
                c cVar2 = new c(iVar, j2, cVar);
                if (z2) {
                    eVarF0.g(inputStreamB, abstractC5838l.C(), cVar2);
                } else {
                    eVarF0.h(inputStreamB, abstractC5838l.C(), cVar2);
                }
                if (cVar != null && cVar.isCancelled()) {
                    throw new C0710a();
                }
                if (l != null && l.longValue() > 0) {
                    try {
                        eVarF0.n(abstractC5838l.C(), (int) (l.longValue() / 1000));
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
                eVarF0.j();
            } catch (SftpException e3) {
                try {
                    throw W("sftp writeFile", e3);
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    if (0 != 0) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    eVarF0.j();
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            Throwable th4 = th;
            if (0 != 0) {
                inputStream.close();
            }
            eVarF0.j();
            throw th4;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public boolean G() {
        return false;
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
    public void L(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l, C5840n c5840n, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        q0(abstractC5838l, a2, j2, l, true, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public AbstractC5838l V0(String str) throws C0718i {
        e eVarD0 = d0();
        try {
            try {
                H h2 = new H(this, eVarD0, eVarD0.e(str), str);
                eVarD0.j();
                return h2;
            } catch (SftpException e2) {
                e2.printStackTrace();
                int i2 = e2.q;
                if (i2 == 2) {
                    H h3 = new H(this, str);
                    eVarD0.j();
                    return h3;
                }
                if (i2 != 4) {
                    throw W("getFileInfo", e2);
                }
                eVarD0.a();
                throw new C0718i(e2);
            }
        } catch (Throwable th) {
            eVarD0.j();
            throw th;
        }
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

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void a1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        c1(abstractC5838l2, p(abstractC5838l), abstractC5838l.s(), abstractC5838l.o(), Long.valueOf(abstractC5838l.q()), abstractC5838l.B(), false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b() {
        U();
        j0(false);
        X();
    }

    public String b0() throws C0718i {
        e eVarD0 = d0();
        try {
            try {
                String strC = eVarD0.c();
                eVarD0.j();
                return strC;
            } catch (SftpException e2) {
                throw W("getHomePath", e2);
            }
        } catch (Throwable th) {
            eVarD0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void b1(AbstractC5838l abstractC5838l) throws C0718i {
        ax.p045d2.b.g("not support delete file recursively");
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void c1(AbstractC5838l abstractC5838l, ax.J1.A a2, String str, long j2, Long l, C5840n c5840n, boolean z2, ax.p045d2.c cVar, ax.P1.i iVar) throws Throwable {
        ax.p045d2.b.a(abstractC5838l.n());
        q0(abstractC5838l, a2, j2, l, false, cVar, iVar);
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public InputStream d1(AbstractC5838l abstractC5838l, long j2) throws C0718i {
        e eVarD0 = d0();
        try {
            return new i(eVarD0, eVarD0.b(abstractC5838l.C(), j2));
        } catch (SftpException e2) {
            throw W("sftp getInputStream", e2);
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void e1(Activity activity, Fragment fragment, InterfaceC5828b.a aVar) {
        try {
            new d(n(), this, aVar, q()).i(new Object[0]);
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

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public List<AbstractC5838l> g1(AbstractC5838l abstractC5838l) throws C0718i {
        if (!abstractC5838l.n()) {
            throw new ax.I1.s();
        }
        ax.p045d2.b.c(abstractC5838l.isDirectory());
        String strC = abstractC5838l.C();
        e eVarD0 = d0();
        ArrayList arrayList = new ArrayList();
        try {
            try {
                for (Object obj : eVarD0.d(strC)) {
                    if (obj instanceof ChannelSftp.LsEntry) {
                        ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) obj;
                        String strK = lsEntry.k();
                        if (!V.z(strK)) {
                            if (TextUtils.isEmpty(strK) || strK.startsWith("/")) {
                                ax.p221pa.c.h().d("SFTP CHILD NAME:").k().h(strC + ":" + strK).i();
                            } else {
                                arrayList.add(new H(this, eVarD0, lsEntry.h(), V.M(strC, strK)));
                            }
                        }
                    }
                }
                eVarD0.j();
                return arrayList;
            } catch (SftpException e2) {
                int i2 = e2.q;
                if (i2 == 3) {
                    throw new C0713d(e2);
                }
                if (i2 == 4) {
                    eVarD0.a();
                    throw new ax.I1.C(e2);
                }
                eVarD0.a();
                throw W("listChildren", e2);
            }
        } catch (Throwable th) {
            eVarD0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean h1(AbstractC5838l abstractC5838l) {
        e eVarD0 = null;
        try {
            try {
                eVarD0 = d0();
                eVarD0.f(abstractC5838l.C());
                eVarD0.j();
                return true;
            } catch (Throwable th) {
                if (0 != 0) {
                    eVarD0.j();
                }
                throw th;
            }
        } catch (C0718i | SftpException e2) {
            e2.printStackTrace();
            if (eVarD0 == null) {
                return false;
            }
            eVarD0.j();
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
    synchronized void k(AbstractC5838l abstractC5838l, String str, boolean z2, ax.P1.h hVar, ax.p045d2.c cVar) throws C0718i {
        m(abstractC5838l, str, z2, hVar, cVar);
    }

    void k0(String str, String str2, String str3, int i2, String str4, String str5) {
        this.q = str;
        this.r = str2;
        this.s = str3;
        this.t = i2;
        this.u = str4;
        this.v = str5;
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void k1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2, ax.p045d2.c cVar, ax.P1.i iVar) throws C0718i {
        ax.p045d2.b.a(abstractC5838l2.n());
        e eVarD0 = d0();
        try {
            try {
                long jO = abstractC5838l.o();
                eVarD0.k(abstractC5838l.C(), abstractC5838l2.C());
                if (iVar != null) {
                    iVar.a(jO, jO);
                }
                eVarD0.j();
            } catch (SftpException e2) {
                e2.printStackTrace();
                throw W("moveFile 2", e2);
            }
        } catch (Throwable th) {
            eVarD0.j();
            throw th;
        }
    }

    public void l0(String str) {
        if (TextUtils.isEmpty(str)) {
            this.p = u();
        } else {
            this.p = str;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public void l1(AbstractC5838l abstractC5838l) throws C0718i {
        e eVarD0 = d0();
        try {
            try {
                if (eVarD0.o(abstractC5838l.C()).k()) {
                    eVarD0.m(abstractC5838l.C());
                } else {
                    eVarD0.l(abstractC5838l.C());
                }
                eVarD0.j();
            } catch (SftpException e2) {
                e2.printStackTrace();
                throw W("delete", e2);
            }
        } catch (Throwable th) {
            eVarD0.j();
            throw th;
        }
    }

    @Override // com.alphainventor.filemanager.file.InterfaceC5828b
    public boolean m1(AbstractC5838l abstractC5838l, AbstractC5838l abstractC5838l2) {
        return true;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5837k
    public String o() {
        return this.p;
    }

    private static class d extends ax.p045d2.n<Object, Void, Boolean> {
        String h;
        int i;
        String j;
        String k;
        G l;
        InterfaceC5828b.a m;
        String n;
        String o;
        String p;
        boolean q;
        f r;
        String s;

        public d(Context context, G g, InterfaceC5828b.a aVar, int i) {
            super(ax.d2.n.f.CONNECT);
            this.l = g;
            this.m = aVar;
            y(G.c0(context).k(i));
            this.q = false;
        }

        private void y(ax.G1.n nVar) {
            this.h = nVar.d();
            this.i = nVar.h();
            this.j = nVar.k();
            this.k = nVar.g();
            this.n = nVar.f();
            this.o = nVar.i();
            this.p = nVar.e();
        }

        @Override // ax.p045d2.n
        protected void r() {
            super.r();
            InterfaceC5828b.a aVar = this.m;
            if (aVar != null) {
                aVar.N();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v5, types: [boolean] */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: w, reason: merged with bridge method [inline-methods] */
        public Boolean g(Object... objArr) {
            Session sessionE0;
            G g = this.l;
            if (g != null) {
                g.h();
            }
            f fVar = 0;
            try {
                try {
                    G.g0();
                    ?? r0 = this.q;
                    try {
                        if (r0 == 0) {
                            sessionE0 = G.e0(this.j, this.k, this.h, this.i, this.o, this.p, new f(this.p != null, false));
                        } else {
                            if (this.p == null) {
                                this.r = G.V(this.j, this.h, this.i);
                                return Boolean.FALSE;
                            }
                            f fVar2 = new f(true, false);
                            try {
                                sessionE0 = G.e0(this.j, this.k, this.h, this.i, this.o, this.p, fVar2);
                            } catch (JSchChangedHostKeyException | JSchUnknownHostKeyException unused) {
                                this.r = fVar2;
                                return Boolean.FALSE;
                            }
                        }
                        G g2 = this.l;
                        if (g2 != null) {
                            g2.m0(sessionE0);
                            this.l.k0(this.j, this.k, this.h, this.i, this.o, this.p);
                            this.l.j0(true);
                            if (this.n == null) {
                                try {
                                    this.n = this.l.b0();
                                } catch (C0718i unused2) {
                                }
                            }
                            this.l.l0(this.n);
                        } else {
                            sessionE0.s();
                        }
                        return Boolean.TRUE;
                    } catch (JSchException e) {
                        e = e;
                        fVar = r0;
                        this.s = e.getMessage();
                        if (((e instanceof JSchUnknownHostKeyException) || (e instanceof JSchChangedHostKeyException)) && this.p != null) {
                            this.r = fVar;
                            return Boolean.FALSE;
                        }
                        e.printStackTrace();
                        return Boolean.FALSE;
                    }
                } catch (JSchException e2) {
                    e = e2;
                }
            } finally {
                G g3 = this.l;
                if (g3 != null) {
                    g3.i();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ax.p045d2.n
        /* JADX INFO: renamed from: x, reason: merged with bridge method [inline-methods] */
        public void q(Boolean bool) {
            if (this.m != null) {
                if (!TextUtils.isEmpty(this.n)) {
                    this.n = V.Q(this.n);
                }
                if (bool.booleanValue()) {
                    this.m.f0(bool.booleanValue(), this.n);
                } else if (this.r != null) {
                    this.m.f0(bool.booleanValue(), this.r);
                } else {
                    this.m.f0(bool.booleanValue(), this.s);
                }
            }
        }

        public d(ax.G1.n nVar, InterfaceC5828b.a aVar) {
            super(ax.d2.n.f.CONNECT);
            this.m = aVar;
            y(nVar);
            this.q = true;
        }
    }
}
