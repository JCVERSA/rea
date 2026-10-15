package com.jcraft.jsch;

import ax.p052d1.h;
import ax.p130i.s;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;
import javax.crypto.AEADBadTagException;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class Session {
    static Random x0;
    private static final byte[] y0 = Util.v("keepalive@jcraft.com");
    private static final byte[] z0 = Util.v("no-more-sessions@openssh.com");
    private UserInfo T;
    private HostKeyConf U;
    private byte[] a;
    private byte[] c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    String g0;
    private byte[] h;
    String h0;
    private byte[] i;
    int i0;
    private byte[] j;
    String j0;
    private byte[] k;
    JSch l0;
    Logger m0;
    private Cipher o;
    private Cipher p;
    private MAC q;
    private MAC r;
    private byte[] s;
    private byte[] t;
    private Compression u;
    Runnable u0;
    private Compression v;
    private IO w;
    private Socket x;
    private byte[] b = Util.v("SSH-2.0-JSCH_" + JSch.g);
    private int l = 0;
    private int m = 0;
    String[] n = null;
    private int y = 0;
    private volatile boolean z = false;
    private volatile boolean A = false;
    private boolean B = true;
    private boolean C = true;
    private volatile boolean D = true;
    private volatile boolean E = false;
    private boolean F = true;
    private boolean G = false;
    private volatile boolean H = false;
    private Thread I = null;
    private Object J = new Object();
    boolean K = false;
    boolean L = false;
    InputStream M = null;
    OutputStream N = null;
    SocketFactory Q = null;
    private Hashtable<String, String> R = null;
    private Proxy S = null;
    private String V = null;
    private int W = 0;
    private int X = 1;
    private IdentityRepository Y = null;
    private HostKeyRepository Z = null;
    private volatile String[] a0 = null;
    private volatile boolean b0 = false;
    protected boolean c0 = false;
    private long d0 = 0;
    int e0 = 6;
    int f0 = 0;
    byte[] k0 = null;
    private volatile boolean n0 = false;
    private volatile boolean o0 = false;
    private volatile String[] p0 = null;
    int[] q0 = new int[1];
    int[] r0 = new int[1];
    private int s0 = 8;
    private int t0 = 8;
    private GlobalRequestReply v0 = new GlobalRequestReply();
    private HostKey w0 = null;
    Buffer O = new Buffer();
    Packet P = new Packet(this.O);

    static class Forwarding {
        String a = null;
        int b = -1;
        String c = null;
        int d = -1;
        String e = null;

        Forwarding() {
        }
    }

    private static class GlobalRequestReply {
        private Thread a;
        private int b;
        private int c;

        private GlobalRequestReply() {
            this.a = null;
            this.b = -1;
            this.c = 0;
        }

        int a() {
            return this.c;
        }

        int b() {
            return this.b;
        }

        Thread c() {
            return this.a;
        }

        void d(int i) {
            this.c = i;
        }

        void e(int i) {
            this.b = i;
        }

        void f(Thread thread) {
            this.a = thread;
            this.b = -1;
        }
    }

    Session(JSch jSch, String str, String str2, int i) throws Throwable {
        this.g0 = "127.0.0.1";
        this.h0 = "127.0.0.1";
        this.i0 = 22;
        this.j0 = null;
        this.l0 = jSch;
        this.j0 = str;
        this.g0 = str2;
        this.h0 = str2;
        this.i0 = i;
        d();
        if (this.j0 == null) {
            this.j0 = Util.o("user.name");
        }
        if (this.j0 == null) {
            throw new JSchException("username is not given.");
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x004e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void H(String str) throws JSchException {
        int i;
        Compression compression = this.u;
        if (str.equals("none")) {
            this.u = null;
            if (compression != null) {
                compression.a();
                return;
            }
            return;
        }
        String strW = w(str);
        if (strW != null) {
            if (str.equals("zlib") || (this.H && str.equals("zlib@openssh.com"))) {
                try {
                    this.u = (Compression) Class.forName(strW).asSubclass(Compression.class).getDeclaredConstructor(null).newInstance(null);
                    try {
                        i = Integer.parseInt(w("compression_level"));
                    } catch (Exception unused) {
                        i = 6;
                    }
                    this.u.d(1, i, this);
                    if (compression != null) {
                        compression.a();
                    }
                } catch (Exception e) {
                    throw new JSchException(e.toString(), e);
                }
            }
        }
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x004d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void I(String str) throws JSchException {
        Compression compression = this.v;
        if (str.equals("none")) {
            this.v = null;
            if (compression != null) {
                compression.a();
                return;
            }
            return;
        }
        String strW = w(str);
        if (strW != null) {
            if (str.equals("zlib") || (this.H && str.equals("zlib@openssh.com"))) {
                try {
                    Compression compression2 = (Compression) Class.forName(strW).asSubclass(Compression.class).getDeclaredConstructor(null).newInstance(null);
                    this.v = compression2;
                    compression2.d(0, 0, this);
                    if (compression != null) {
                        compression.a();
                    }
                } catch (Exception e) {
                    throw new JSchException(e.toString(), e);
                }
            }
        }
    }

    private KeyExchange N(Buffer buffer) throws Exception {
        int i = buffer.i();
        if (i != buffer.j()) {
            buffer.c();
            this.d = new byte[buffer.c - 5];
        } else {
            this.d = new byte[(i - 1) - buffer.c()];
        }
        byte[] bArr = buffer.b;
        int i2 = buffer.d;
        byte[] bArr2 = this.d;
        System.arraycopy(bArr, i2, bArr2, 0, bArr2.length);
        if (this.D) {
            if (this.F || this.G) {
                this.E = o();
                if (this.E) {
                    if (A().isEnabled(1)) {
                        A().a(1, "Doing strict KEX");
                    }
                    if (this.l != 1) {
                        throw new JSchStrictKexException("KEXINIT not first packet from server");
                    }
                } else if (this.G) {
                    throw new JSchStrictKexException("Strict KEX not supported by server");
                }
            }
            if (this.B) {
                this.A = n();
                if (this.A && A().isEnabled(1)) {
                    A().a(1, "ext-info messaging supported by server");
                }
            }
        }
        if (!this.n0) {
            T();
        }
        String[] strArrL = KeyExchange.l(this, this.d, this.c);
        this.n = strArrL;
        if (strArrL[0].equals("ext-info-c") || this.n[0].equals("ext-info-s") || this.n[0].equals("kex-strict-c-v00@openssh.com") || this.n[0].equals("kex-strict-s-v00@openssh.com")) {
            throw new JSchException("Invalid Kex negotiated: " + this.n[0]);
        }
        if (!this.H && (this.n[2].equals("none") || this.n[3].equals("none"))) {
            throw new JSchException("NONE Cipher should not be chosen before authentification is successed.");
        }
        try {
            KeyExchange keyExchange = (KeyExchange) Class.forName(w(this.n[0])).asSubclass(KeyExchange.class).getDeclaredConstructor(null).newInstance(null);
            keyExchange.b(this, this.a, this.b, this.d, this.c);
            return keyExchange;
        } catch (Exception | NoClassDefFoundError e) {
            throw new JSchException(e.toString(), e);
        }
    }

    private void O(Buffer buffer, KeyExchange keyExchange) throws Exception {
        try {
            l0(keyExchange);
            keyExchange.a();
            this.n0 = false;
            if (this.E) {
                this.l = 0;
                if (A().isEnabled(1)) {
                    A().a(1, "Reset incoming sequence number after receiving SSH_MSG_NEWKEYS for strict KEX");
                }
            }
        } catch (Throwable th) {
            keyExchange.a();
            throw th;
        }
    }

    private void P() throws JSchException {
        ConfigRepository configRepositoryG;
        if (w("ClearAllForwardings").equals("yes") || (configRepositoryG = this.l0.g()) == null) {
            return;
        }
        ConfigRepository.Config configA = configRepositoryG.a(this.h0);
        String[] strArrB = configA.b("LocalForward");
        if (strArrB != null) {
            for (String str : strArrB) {
                b0(str);
            }
        }
        String[] strArrB2 = configA.b("RemoteForward");
        if (strArrB2 != null) {
            for (String str2 : strArrB2) {
                f0(str2);
            }
        }
    }

    private void S() throws Exception {
        this.P.c();
        this.O.s((byte) 7);
        this.O.v(1);
        this.O.y(Util.v("ext-info-in-auth@openssh.com"));
        this.O.y(Util.v("0"));
        m0(this.P);
        if (A().isEnabled(1)) {
            A().a(1, "SSH_MSG_EXT_INFO sent");
        }
    }

    private void T() throws Exception {
        if (this.n0) {
            return;
        }
        String strW = w("cipher.c2s");
        String strW2 = w("cipher.s2c");
        String[] strArrG = g(w("CheckCiphers"));
        if (strArrG != null && strArrG.length > 0) {
            if (A().isEnabled(0)) {
                A().a(0, "cipher.c2s proposal before removing unavailable algos is: " + strW);
                A().a(0, "cipher.s2c proposal before removing unavailable algos is: " + strW2);
            }
            strW = Util.j(strW, strArrG);
            strW2 = Util.j(strW2, strArrG);
            if (strW == null || strW2 == null) {
                throw new JSchException("There are not any available ciphers.");
            }
            if (A().isEnabled(0)) {
                A().a(0, "cipher.c2s proposal after removing unavailable algos is: " + strW);
                A().a(0, "cipher.s2c proposal after removing unavailable algos is: " + strW2);
            }
        }
        String strW3 = w("mac.c2s");
        String strW4 = w("mac.s2c");
        String[] strArrM = m(w("CheckMacs"));
        if (strArrM != null && strArrM.length > 0) {
            if (A().isEnabled(0)) {
                A().a(0, "mac.c2s proposal before removing unavailable algos is: " + strW3);
                A().a(0, "mac.s2c proposal before removing unavailable algos is: " + strW4);
            }
            String strJ = Util.j(strW3, strArrM);
            String strJ2 = Util.j(strW4, strArrM);
            if (strJ == null || strJ2 == null) {
                throw new JSchException("There are not any available macs.");
            }
            if (A().isEnabled(0)) {
                A().a(0, "mac.c2s proposal after removing unavailable algos is: " + strJ);
                A().a(0, "mac.s2c proposal after removing unavailable algos is: " + strJ2);
            }
        }
        String strW5 = w("kex");
        String[] strArrK = k(w("CheckKexes"));
        if (strArrK != null && strArrK.length > 0) {
            if (A().isEnabled(0)) {
                A().a(0, "kex proposal before removing unavailable algos is: " + strW5);
            }
            strW5 = Util.j(strW5, strArrK);
            if (strW5 == null) {
                throw new JSchException("There are not any available kexes.");
            }
            if (A().isEnabled(0)) {
                A().a(0, "kex proposal after removing unavailable algos is: " + strW5);
            }
        }
        if (this.B && !this.H) {
            strW5 = strW5 + ",ext-info-c";
        }
        if ((this.F || this.G) && this.D) {
            strW5 = strW5 + ",kex-strict-c-v00@openssh.com";
        }
        String strW6 = w("server_host_key");
        String[] strArrP = p(w("CheckSignatures"));
        this.p0 = strArrP;
        if (strArrP != null && strArrP.length > 0) {
            if (A().isEnabled(0)) {
                A().a(0, "server_host_key proposal before removing unavailable algos is: " + strW6);
            }
            strW6 = Util.j(strW6, strArrP);
            if (strW6 == null) {
                throw new JSchException("There are not any available sig algorithm.");
            }
            if (A().isEnabled(0)) {
                A().a(0, "server_host_key proposal after removing unavailable algos is: " + strW6);
            }
        }
        if (w("prefer_known_host_key_types").equals("yes")) {
            if (A().isEnabled(0)) {
                A().a(0, "server_host_key proposal before known_host reordering is: " + strW6);
            }
            HostKeyRepository hostKeyRepositoryY = y();
            String str = this.g0;
            String str2 = this.V;
            if (str2 != null) {
                str = str2;
            }
            if (str2 == null && this.i0 != 22) {
                str = "[" + str + "]:" + this.i0;
            }
            HostKey[] hostKeyArrE = hostKeyRepositoryY.e(str, null);
            if (hostKeyArrE != null && hostKeyArrE.length > 0) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList(Arrays.asList(Util.u(strW6, ",")));
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    String str4 = (str3.equals("rsa-sha2-256") || str3.equals("rsa-sha2-512") || str3.equals("ssh-rsa-sha224@ssh.com") || str3.equals("ssh-rsa-sha256@ssh.com") || str3.equals("ssh-rsa-sha384@ssh.com") || str3.equals("ssh-rsa-sha512@ssh.com")) ? "ssh-rsa" : str3;
                    for (HostKey hostKey : hostKeyArrE) {
                        if (hostKey.f().equals(str4)) {
                            arrayList.add(str3);
                            it.remove();
                            break;
                        }
                    }
                }
                if (arrayList.size() > 0) {
                    arrayList.addAll(arrayList2);
                    strW6 = h.a(",", arrayList);
                }
            }
            if (A().isEnabled(0)) {
                A().a(0, "server_host_key proposal after known_host reordering is: " + strW6);
            }
        }
        this.n0 = true;
        this.d0 = System.currentTimeMillis();
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 20);
        synchronized (x0) {
            x0.a(buffer.b, buffer.c, 16);
            buffer.E(16);
        }
        buffer.y(Util.v(strW5));
        buffer.y(Util.v(strW6));
        buffer.y(Util.v(strW));
        buffer.y(Util.v(strW2));
        buffer.y(Util.v(w("mac.c2s")));
        buffer.y(Util.v(w("mac.s2c")));
        buffer.y(Util.v(w("compression.c2s")));
        buffer.y(Util.v(w("compression.s2c")));
        buffer.y(Util.v(w("lang.c2s")));
        buffer.y(Util.v(w("lang.s2c")));
        buffer.s((byte) 0);
        buffer.v(0);
        buffer.C(5);
        byte[] bArr = new byte[buffer.j()];
        this.c = bArr;
        buffer.e(bArr);
        m0(packet);
        if (A().isEnabled(1)) {
            A().a(1, "SSH_MSG_KEXINIT sent");
        }
    }

    private void U() throws Exception {
        this.P.c();
        this.O.s((byte) 21);
        m0(this.P);
        if (A().isEnabled(1)) {
            A().a(1, "SSH_MSG_NEWKEYS sent");
        }
    }

    private int a(String str, int i) throws JSchException {
        int iA;
        synchronized (this.v0) {
            Buffer buffer = new Buffer(200);
            Packet packet = new Packet(buffer);
            String strL = ChannelForwardedTCPIP.L(str);
            this.v0.f(Thread.currentThread());
            this.v0.d(i);
            try {
                packet.c();
                buffer.s((byte) 80);
                buffer.y(Util.v("tcpip-forward"));
                buffer.s((byte) 1);
                buffer.y(Util.v(strL));
                buffer.v(i);
                m0(packet);
                int iB = this.v0.b();
                int i2 = 0;
                while (i2 < 10 && iB == -1) {
                    try {
                        Thread.sleep(1000L);
                    } catch (Exception unused) {
                    }
                    i2++;
                    iB = this.v0.b();
                }
                this.v0.f(null);
                if (iB != 1) {
                    throw new JSchException("remote port forwarding failed for listen port " + i);
                }
                iA = this.v0.a();
            } catch (Exception e) {
                this.v0.f(null);
                throw new JSchException(e.toString(), e);
            }
        }
        return iA;
    }

    private void b(Packet packet) throws Exception {
        boolean z = this.D;
        boolean z2 = this.E;
        boolean z3 = this.F;
        boolean z4 = this.G;
        boolean z5 = packet.a.h() == 21 && z2;
        synchronized (this.J) {
            try {
                t(packet);
                IO io = this.w;
                if (io != null) {
                    io.e(packet);
                    int i = this.m + 1;
                    this.m = i;
                    if (i == 0 && ((z3 || z4) && z)) {
                        throw new JSchStrictKexException("outgoing sequence number wrapped during initial KEX");
                    }
                    if (z5) {
                        this.m = 0;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (z5 && this.w != null && A().isEnabled(1)) {
            A().a(1, "Reset outgoing sequence number after sending SSH_MSG_NEWKEYS for strict KEX");
        }
    }

    private void d() throws Throwable {
        String strC;
        ConfigRepository configRepositoryG = this.l0.g();
        if (configRepositoryG == null) {
            return;
        }
        ConfigRepository.Config configA = configRepositoryG.a(this.h0);
        if (this.j0 == null && (strC = configA.c()) != null) {
            this.j0 = strC;
        }
        String strD = configA.d();
        if (strD != null) {
            this.g0 = strD;
        }
        int iA = configA.a();
        if (iA != -1) {
            this.i0 = iA;
        }
        h(configA, "kex");
        h(configA, "server_host_key");
        h(configA, "prefer_known_host_key_types");
        h(configA, "enable_server_sig_algs");
        h(configA, "enable_ext_info_in_auth");
        h(configA, "enable_strict_kex");
        h(configA, "require_strict_kex");
        h(configA, "enable_pubkey_auth_query");
        h(configA, "try_additional_pubkey_algorithms");
        h(configA, "enable_auth_none");
        h(configA, "use_sftp_write_flush_workaround");
        h(configA, "cipher.c2s");
        h(configA, "cipher.s2c");
        h(configA, "mac.c2s");
        h(configA, "mac.s2c");
        h(configA, "compression.c2s");
        h(configA, "compression.s2c");
        h(configA, "compression_level");
        h(configA, "StrictHostKeyChecking");
        h(configA, "HashKnownHosts");
        h(configA, "PreferredAuthentications");
        h(configA, "PubkeyAcceptedAlgorithms");
        h(configA, "FingerprintHash");
        h(configA, "MaxAuthTries");
        h(configA, "ClearAllForwardings");
        String value = configA.getValue("HostKeyAlias");
        if (value != null) {
            W(value);
        }
        String value2 = configA.getValue("UserKnownHostsFile");
        if (value2 != null) {
            KnownHosts knownHosts = new KnownHosts(this.l0);
            knownHosts.o(value2);
            Y(knownHosts);
        }
        String[] strArrB = configA.b("IdentityFile");
        if (strArrB != null) {
            String[] strArrB2 = configRepositoryG.a(HttpUrl.FRAGMENT_ENCODE_SET).b("IdentityFile");
            if (strArrB2 != null) {
                for (String str : strArrB2) {
                    this.l0.b(str);
                }
            } else {
                strArrB2 = new String[0];
            }
            if (strArrB.length - strArrB2.length > 0) {
                IdentityRepositoryWrapper identityRepositoryWrapper = new IdentityRepositoryWrapper(this.l0.i(), true);
                for (String str2 : strArrB) {
                    for (String str3 : strArrB2) {
                        if (str2.equals(str3)) {
                            str2 = null;
                            break;
                        }
                    }
                    if (str2 != null) {
                        identityRepositoryWrapper.e(IdentityFile.h(str2, null, this.l0.a));
                    }
                }
                Z(identityRepositoryWrapper);
            }
        }
        String value3 = configA.getValue("ServerAliveInterval");
        if (value3 != null) {
            try {
                g0(Integer.parseInt(value3));
            } catch (NumberFormatException unused) {
            }
        }
        String value4 = configA.getValue("ConnectTimeout");
        if (value4 != null) {
            try {
                i0(Integer.parseInt(value4));
            } catch (NumberFormatException unused2) {
            }
        }
        String value5 = configA.getValue("MaxAuthTries");
        if (value5 != null) {
            V("MaxAuthTries", value5);
        }
        String value6 = configA.getValue("ClearAllForwardings");
        if (value6 != null) {
            V("ClearAllForwardings", value6);
        }
    }

    private void e(ChannelSession channelSession) throws JSchException {
        ConfigRepository configRepositoryG = this.l0.g();
        if (configRepositoryG == null) {
            return;
        }
        ConfigRepository.Config configA = configRepositoryG.a(this.h0);
        String value = configA.getValue("ForwardAgent");
        if (value != null) {
            channelSession.H(value.equals("yes"));
        }
        String value2 = configA.getValue("RequestTTY");
        if (value2 != null) {
            channelSession.I(value2.equals("yes"));
        }
    }

    static boolean f(String str) {
        try {
            Cipher cipher = (Cipher) Class.forName(str).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
            cipher.l(0, new byte[cipher.d()], new byte[cipher.m()]);
            return true;
        } catch (Exception | NoClassDefFoundError unused) {
            return false;
        }
    }

    private String[] g(String str) {
        String[] strArr = null;
        if (str != null && str.length() != 0) {
            if (A().isEnabled(1)) {
                A().a(1, "CheckCiphers: " + str);
            }
            String strW = w("cipher.c2s");
            String strW2 = w("cipher.s2c");
            Vector vector = new Vector();
            for (String str2 : Util.u(str, ",")) {
                if ((strW2.indexOf(str2) != -1 || strW.indexOf(str2) != -1) && !f(w(str2))) {
                    vector.addElement(str2);
                }
            }
            if (vector.size() == 0) {
                return null;
            }
            int size = vector.size();
            strArr = new String[size];
            System.arraycopy(vector.toArray(), 0, strArr, 0, vector.size());
            if (A().isEnabled(1)) {
                for (int i = 0; i < size; i++) {
                    A().a(1, strArr[i] + " is not available.");
                }
            }
        }
        return strArr;
    }

    private void h(ConfigRepository.Config config, String str) {
        String value = config.getValue(str);
        if (value == null && str.equals("PubkeyAcceptedAlgorithms")) {
            value = config.getValue("PubkeyAcceptedKeyTypes");
        }
        if (value != null) {
            V(str, value);
        }
    }

    private void i(String str, int i, KeyExchange keyExchange) throws JSchException {
        int iC;
        String strA;
        boolean zG;
        boolean z;
        HostKeyConf hostKeyConf = this.U;
        String strC = hostKeyConf != null ? hostKeyConf.c() : w("StrictHostKeyChecking");
        String str2 = this.V;
        if (str2 != null) {
            str = str2;
        }
        byte[] bArrG = keyExchange.g();
        String strJ = keyExchange.j();
        String strD = keyExchange.d();
        if (this.V == null && i != 22) {
            str = "[" + str + "]:" + i;
        }
        HostKeyRepository hostKeyRepositoryY = y();
        if (w("HashKnownHosts").equals("yes") && (hostKeyRepositoryY instanceof KnownHosts)) {
            this.w0 = ((KnownHosts) hostKeyRepositoryY).i(str, bArrG);
        } else {
            this.w0 = new HostKey(str, bArrG);
        }
        HostKeyConf hostKeyConf2 = this.U;
        if (hostKeyConf2 != null) {
            hostKeyConf2.a(this.w0, strJ, strD);
            if (this.U.b()) {
                return;
            }
        }
        synchronized (hostKeyRepositoryY) {
            iC = hostKeyRepositoryY.c(str, bArrG);
        }
        if ((strC.equals("ask") || strC.equals("yes")) && iC == 2) {
            synchronized (hostKeyRepositoryY) {
                strA = hostKeyRepositoryY.a();
            }
            if (strA == null) {
                strA = "known_hosts";
            }
            if (this.T != null) {
                String str3 = "WARNING: REMOTE HOST IDENTIFICATION HAS CHANGED!\nIT IS POSSIBLE THAT SOMEONE IS DOING SOMETHING NASTY!\nSomeone could be eavesdropping on you right now (man-in-the-middle attack)!\nIt is also possible that the " + strJ + " host key has just been changed.\nThe fingerprint for the " + strJ + " key sent by the remote host " + str + " is\n" + strD + ".\nPlease contact your system administrator.\nAdd correct host key in " + strA + " to get rid of this message.";
                if (strC.equals("ask")) {
                    zG = this.T.g(str3 + "\nDo you want to delete the old key and insert the new key?");
                } else {
                    this.T.d(str3);
                    zG = false;
                }
            } else {
                zG = false;
            }
            if (!zG) {
                throw new JSchChangedHostKeyException("HostKey has been changed: " + str);
            }
            synchronized (hostKeyRepositoryY) {
                hostKeyRepositoryY.d(str, keyExchange.i(), null);
            }
            z = true;
        } else {
            z = false;
        }
        if ((strC.equals("ask") || strC.equals("yes")) && iC != 0 && !z) {
            if (strC.equals("yes")) {
                if (iC != 1) {
                    throw new JSchChangedHostKeyException("HostKey has been changed: " + str);
                }
                throw new JSchUnknownHostKeyException("UnknownHostKey: " + str + ". " + strJ + " key fingerprint is " + strD);
            }
            UserInfo userInfo = this.T;
            if (userInfo == null) {
                if (iC != 1) {
                    throw new JSchChangedHostKeyException("HostKey has been changed: " + str);
                }
                throw new JSchUnknownHostKeyException("UnknownHostKey: " + str + ". " + strJ + " key fingerprint is " + strD);
            }
            if (!userInfo.g("The authenticity of host '" + str + "' can't be established.\n" + strJ + " key fingerprint is " + strD + ".\nAre you sure you want to continue connecting?")) {
                throw new JSchException("reject HostKey: " + str);
            }
            z = true;
        }
        if (strC.equals("no") && 1 == iC) {
            z = true;
        }
        if (iC == 0) {
            HostKey[] hostKeyArrE = hostKeyRepositoryY.e(str, keyExchange.i());
            String strC2 = Util.c(Util.x(bArrG, 0, bArrG.length, true));
            for (int i2 = 0; i2 < hostKeyArrE.length; i2++) {
                if (hostKeyArrE[i2].d().equals(strC2) && hostKeyArrE[i2].e().equals("@revoked")) {
                    UserInfo userInfo2 = this.T;
                    if (userInfo2 != null) {
                        userInfo2.d("The " + strJ + " host key for " + str + " is marked as revoked.\nThis could mean that a stolen key is being used to impersonate this host.");
                    }
                    if (A().isEnabled(1)) {
                        A().a(1, "Host '" + str + "' has provided revoked key.");
                    }
                    throw new JSchRevokedHostKeyException("revoked HostKey: " + str);
                }
            }
        }
        if (iC == 0 && A().isEnabled(1)) {
            A().a(1, "Host '" + str + "' is known and matches the " + strJ + " host key");
        }
        if (z && A().isEnabled(2)) {
            A().a(2, "Permanently added '" + str + "' (" + strJ + ") to the list of known hosts.");
        }
        if (z) {
            synchronized (hostKeyRepositoryY) {
                hostKeyRepositoryY.b(this.w0, this.T);
            }
        }
    }

    static boolean j(Session session, String str) {
        try {
            ((KeyExchange) Class.forName(str).asSubclass(KeyExchange.class).getDeclaredConstructor(null).newInstance(null)).b(session, null, null, null, null);
            return true;
        } catch (Exception | NoClassDefFoundError unused) {
            return false;
        }
    }

    private String[] k(String str) {
        String[] strArr = null;
        if (str != null && str.length() != 0) {
            if (A().isEnabled(1)) {
                A().a(1, "CheckKexes: " + str);
            }
            Vector vector = new Vector();
            String[] strArrU = Util.u(str, ",");
            for (int i = 0; i < strArrU.length; i++) {
                if (!j(this, w(strArrU[i]))) {
                    vector.addElement(strArrU[i]);
                }
            }
            if (vector.size() == 0) {
                return null;
            }
            int size = vector.size();
            strArr = new String[size];
            System.arraycopy(vector.toArray(), 0, strArr, 0, vector.size());
            if (A().isEnabled(1)) {
                for (int i2 = 0; i2 < size; i2++) {
                    A().a(1, strArr[i2] + " is not available.");
                }
            }
        }
        return strArr;
    }

    private void k0(Buffer buffer, Cipher cipher, MAC mac, int i, int i2) throws JSchException {
        IOException e;
        if (!cipher.j() || (mac != null && mac.f())) {
            throw new JSchException("Packet corrupt");
        }
        if (mac != null) {
            mac.e(this.l);
            mac.b(buffer.b, 0, i);
        }
        while (i2 > 0) {
            try {
                buffer.A();
                byte[] bArr = buffer.b;
                int length = i2 > bArr.length ? bArr.length : i2;
                this.w.c(bArr, 0, length);
                if (mac != null) {
                    mac.b(buffer.b, 0, length);
                }
                i2 -= length;
            } catch (IOException e2) {
                e = e2;
                if (A().isEnabled(3)) {
                    A().b(3, "start_discard finished early due to " + e.getMessage(), e);
                }
            }
        }
        e = null;
        if (mac != null) {
            mac.c(buffer.b, 0);
        }
        JSchException jSchException = new JSchException("Packet corrupt");
        if (e == null) {
            throw jSchException;
        }
        jSchException.addSuppressed(e);
        throw jSchException;
    }

    static boolean l(String str) {
        try {
            MAC mac = (MAC) Class.forName(str).asSubclass(MAC.class).getDeclaredConstructor(null).newInstance(null);
            mac.a(new byte[mac.d()]);
            return true;
        } catch (Exception | NoClassDefFoundError unused) {
            return false;
        }
    }

    private void l0(KeyExchange keyExchange) throws Exception {
        Throwable th;
        byte[] bArr;
        Session session;
        byte[] bArr2;
        byte[] bArrH = keyExchange.h();
        byte[] bArrE = keyExchange.e();
        HASH hashF = keyExchange.f();
        if (this.e == null) {
            byte[] bArr3 = new byte[bArrE.length];
            this.e = bArr3;
            System.arraycopy(bArrE, 0, bArr3, 0, bArrE.length);
        }
        this.O.A();
        this.O.t(bArrH);
        this.O.t(bArrE);
        this.O.s((byte) 65);
        this.O.t(this.e);
        Buffer buffer = this.O;
        hashF.b(buffer.b, 0, buffer.c);
        this.f = hashF.c();
        Buffer buffer2 = this.O;
        int i = buffer2.c;
        int length = (i - this.e.length) - 1;
        byte[] bArr4 = buffer2.b;
        bArr4[length] = (byte) (bArr4[length] + 1);
        hashF.b(bArr4, 0, i);
        this.g = hashF.c();
        Buffer buffer3 = this.O;
        byte[] bArr5 = buffer3.b;
        bArr5[length] = (byte) (bArr5[length] + 1);
        hashF.b(bArr5, 0, buffer3.c);
        this.h = hashF.c();
        Buffer buffer4 = this.O;
        byte[] bArr6 = buffer4.b;
        bArr6[length] = (byte) (bArr6[length] + 1);
        hashF.b(bArr6, 0, buffer4.c);
        this.i = hashF.c();
        Buffer buffer5 = this.O;
        byte[] bArr7 = buffer5.b;
        bArr7[length] = (byte) (bArr7[length] + 1);
        hashF.b(bArr7, 0, buffer5.c);
        this.j = hashF.c();
        Buffer buffer6 = this.O;
        byte[] bArr8 = buffer6.b;
        bArr8[length] = (byte) (bArr8[length] + 1);
        hashF.b(bArr8, 0, buffer6.c);
        this.k = hashF.c();
        try {
            this.o = (Cipher) Class.forName(w(this.n[3])).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
            while (true) {
                int iD = this.o.d();
                bArr = this.i;
                if (iD <= bArr.length) {
                    break;
                }
                try {
                    this.O.A();
                    this.O.t(bArrH);
                    this.O.t(bArrE);
                    this.O.t(this.i);
                    Buffer buffer7 = this.O;
                    hashF.b(buffer7.b, 0, buffer7.c);
                    byte[] bArrC = hashF.c();
                    byte[] bArr9 = this.i;
                    byte[] bArr10 = new byte[bArr9.length + bArrC.length];
                    System.arraycopy(bArr9, 0, bArr10, 0, bArr9.length);
                    System.arraycopy(bArrC, 0, bArr10, this.i.length, bArrC.length);
                    this.i = bArr10;
                } catch (Exception | NoClassDefFoundError e) {
                    th = e;
                }
                th = e;
                if (!(th instanceof JSchException)) {
                    throw new JSchException(th.toString(), th);
                }
                throw th;
            }
            this.o.l(1, bArr, this.g);
            this.s0 = this.o.m();
            if (this.o.n()) {
                session = this;
            } else {
                MAC mac = (MAC) Class.forName(w(this.n[5])).asSubclass(MAC.class).getDeclaredConstructor(null).newInstance(null);
                this.q = mac;
                session = this;
                try {
                    byte[] bArrU = session.u(this.O, bArrH, bArrE, this.k, hashF, mac.d());
                    session.k = bArrU;
                    session.q.a(bArrU);
                    session.s = new byte[session.q.d()];
                    session.t = new byte[session.q.d()];
                } catch (Exception e2) {
                    e = e2;
                } catch (NoClassDefFoundError e3) {
                    e = e3;
                }
            }
            session.p = (Cipher) Class.forName(w(session.n[2])).asSubclass(Cipher.class).getDeclaredConstructor(null).newInstance(null);
            while (true) {
                int iD2 = session.p.d();
                bArr2 = session.h;
                if (iD2 <= bArr2.length) {
                    break;
                }
                session.O.A();
                session.O.t(bArrH);
                session.O.t(bArrE);
                session.O.t(session.h);
                Buffer buffer8 = session.O;
                hashF.b(buffer8.b, 0, buffer8.c);
                byte[] bArrC2 = hashF.c();
                byte[] bArr11 = session.h;
                byte[] bArr12 = new byte[bArr11.length + bArrC2.length];
                System.arraycopy(bArr11, 0, bArr12, 0, bArr11.length);
                System.arraycopy(bArrC2, 0, bArr12, session.h.length, bArrC2.length);
                session.h = bArr12;
            }
            session.p.l(0, bArr2, session.f);
            session.t0 = session.p.m();
            if (!session.p.n()) {
                MAC mac2 = (MAC) Class.forName(w(session.n[4])).asSubclass(MAC.class).getDeclaredConstructor(null).newInstance(null);
                session.r = mac2;
                byte[] bArrU2 = session.u(session.O, bArrH, bArrE, session.j, hashF, mac2.d());
                session.j = bArrU2;
                session.r.a(bArrU2);
            }
            H(session.n[6]);
            I(session.n[7]);
        } catch (Exception e4) {
            e = e4;
        } catch (NoClassDefFoundError e5) {
            e = e5;
        }
    }

    private String[] m(String str) {
        String[] strArr = null;
        if (str != null && str.length() != 0) {
            if (A().isEnabled(1)) {
                A().a(1, "CheckMacs: " + str);
            }
            String strW = w("mac.c2s");
            String strW2 = w("mac.s2c");
            Vector vector = new Vector();
            for (String str2 : Util.u(str, ",")) {
                if ((strW2.indexOf(str2) != -1 || strW.indexOf(str2) != -1) && !l(w(str2))) {
                    vector.addElement(str2);
                }
            }
            if (vector.size() == 0) {
                return null;
            }
            int size = vector.size();
            strArr = new String[size];
            System.arraycopy(vector.toArray(), 0, strArr, 0, vector.size());
            if (A().isEnabled(1)) {
                for (int i = 0; i < size; i++) {
                    A().a(1, strArr[i] + " is not available.");
                }
            }
        }
        return strArr;
    }

    private boolean n() {
        Buffer buffer = new Buffer(this.d);
        buffer.C(17);
        byte[] bArrP = buffer.p();
        int i = 0;
        int i2 = 0;
        while (i < bArrP.length) {
            while (i < bArrP.length && bArrP[i] != 44) {
                i++;
            }
            if (i2 != i) {
                if ("ext-info-s".equals(Util.d(bArrP, i2, i - i2))) {
                    return true;
                }
                i2 = i + 1;
                i = i2;
            }
        }
        return false;
    }

    private boolean o() {
        Buffer buffer = new Buffer(this.d);
        buffer.C(17);
        byte[] bArrP = buffer.p();
        int i = 0;
        int i2 = 0;
        while (i < bArrP.length) {
            while (i < bArrP.length && bArrP[i] != 44) {
                i++;
            }
            if (i2 != i) {
                if ("kex-strict-s-v00@openssh.com".equals(Util.d(bArrP, i2, i - i2))) {
                    return true;
                }
                i2 = i + 1;
                i = i2;
            }
        }
        return false;
    }

    private String[] p(String str) {
        String[] strArr = null;
        if (str != null && str.length() != 0) {
            if (A().isEnabled(1)) {
                A().a(1, "CheckSignatures: " + str);
            }
            Vector vector = new Vector();
            String[] strArrU = Util.u(str, ",");
            for (int i = 0; i < strArrU.length; i++) {
                try {
                    ((Signature) Class.forName(JSch.f(strArrU[i])).asSubclass(Signature.class).getDeclaredConstructor(null).newInstance(null)).a();
                } catch (Exception | NoClassDefFoundError unused) {
                    vector.addElement(strArrU[i]);
                }
            }
            if (vector.size() == 0) {
                return null;
            }
            int size = vector.size();
            strArr = new String[size];
            System.arraycopy(vector.toArray(), 0, strArr, 0, vector.size());
            if (A().isEnabled(1)) {
                for (int i2 = 0; i2 < size; i2++) {
                    A().a(1, strArr[i2] + " is not available.");
                }
            }
        }
        return strArr;
    }

    private byte[] u(Buffer buffer, byte[] bArr, byte[] bArr2, byte[] bArr3, HASH hash, int i) throws Exception {
        int iD = hash.d();
        while (bArr3.length < i) {
            buffer.A();
            buffer.t(bArr);
            buffer.t(bArr2);
            buffer.t(bArr3);
            hash.b(buffer.b, 0, buffer.c);
            byte[] bArr4 = new byte[bArr3.length + iD];
            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
            System.arraycopy(hash.c(), 0, bArr4, bArr3.length, iD);
            Util.g(bArr3);
            bArr3 = bArr4;
        }
        return bArr3;
    }

    public Logger A() {
        Logger logger = this.m0;
        return logger != null ? logger : this.l0.j();
    }

    String[] B() {
        return this.a0;
    }

    byte[] C() {
        return this.e;
    }

    public int D() {
        return this.y;
    }

    public String[] E() {
        return this.p0;
    }

    public UserInfo F() {
        return this.T;
    }

    public String G() {
        return this.j0;
    }

    public boolean J() {
        return this.z;
    }

    public Channel K(String str) throws JSchException {
        if (!this.z) {
            throw new JSchException("session is down");
        }
        try {
            Channel channelL = Channel.l(str, this);
            c(channelL);
            channelL.q();
            if (channelL instanceof ChannelSession) {
                e((ChannelSession) channelL);
            }
            return channelL;
        } catch (Exception unused) {
            return null;
        }
    }

    Forwarding L(String str) throws JSchException {
        String[] strArrSplit = str.split(" ");
        if (strArrSplit.length > 1) {
            Vector vector = new Vector();
            for (int i = 0; i < strArrSplit.length; i++) {
                if (strArrSplit[i].length() != 0) {
                    vector.addElement(strArrSplit[i].trim());
                }
            }
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (i2 < vector.size()) {
                sb.append((String) vector.elementAt(i2));
                i2++;
                if (i2 < vector.size()) {
                    sb.append(":");
                }
            }
            str = sb.toString();
        }
        Forwarding forwarding = new Forwarding();
        try {
            if (str.lastIndexOf(":") == -1) {
                throw new JSchException("parseForwarding: " + str);
            }
            try {
                forwarding.d = Integer.parseInt(str.substring(str.lastIndexOf(":") + 1));
                String strSubstring = str.substring(0, str.lastIndexOf(":"));
                try {
                    if (strSubstring.lastIndexOf(":") == -1) {
                        throw new JSchException("parseForwarding: " + str);
                    }
                    forwarding.c = strSubstring.substring(strSubstring.lastIndexOf(":") + 1);
                    String strSubstring2 = strSubstring.substring(0, strSubstring.lastIndexOf(":"));
                    String str2 = "127.0.0.1";
                    if (strSubstring2.lastIndexOf(":") != -1) {
                        forwarding.b = Integer.parseInt(strSubstring2.substring(strSubstring2.lastIndexOf(":") + 1));
                        String strSubstring3 = strSubstring2.substring(0, strSubstring2.lastIndexOf(":"));
                        if (strSubstring3.length() == 0 || strSubstring3.equals("*")) {
                            strSubstring3 = "0.0.0.0";
                        }
                        if (!strSubstring3.equals("localhost")) {
                            str2 = strSubstring3;
                        }
                        forwarding.a = str2;
                    } else {
                        forwarding.b = Integer.parseInt(strSubstring2);
                        forwarding.a = "127.0.0.1";
                    }
                    return forwarding;
                } catch (NumberFormatException unused) {
                    str = strSubstring;
                    forwarding.e = str.substring(str.lastIndexOf(":") + 1);
                    strSubstring = str;
                }
            } catch (NumberFormatException unused2) {
            }
        } catch (NumberFormatException e) {
            throw new JSchException("parseForwarding: " + e.toString(), e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:124:0x0382  */
    /* JADX WARN: Code duplicated, block: B:126:0x0394  */
    /* JADX WARN: Code duplicated, block: B:129:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:130:0x03b4  */
    /* JADX WARN: Code duplicated, block: B:132:0x03bd  */
    /* JADX WARN: Code duplicated, block: B:134:0x03c1  */
    /* JADX WARN: Code duplicated, block: B:137:0x03c7  */
    /* JADX WARN: Code duplicated, block: B:140:0x03cc  */
    /* JADX WARN: Code duplicated, block: B:142:0x03cf  */
    /* JADX WARN: Code duplicated, block: B:144:0x03e7  */
    /* JADX WARN: Code duplicated, block: B:145:0x0401 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:146:0x0403  */
    /* JADX WARN: Code duplicated, block: B:147:0x040e  */
    /* JADX WARN: Code duplicated, block: B:149:0x0412  */
    /* JADX WARN: Code duplicated, block: B:152:0x0427  */
    /* JADX WARN: Code duplicated, block: B:153:0x0430  */
    /* JADX WARN: Code duplicated, block: B:155:0x0433  */
    /* JADX WARN: Code duplicated, block: B:157:0x0440  */
    /* JADX WARN: Code duplicated, block: B:159:0x044b  */
    /* JADX WARN: Code duplicated, block: B:160:0x0455  */
    /* JADX WARN: Code duplicated, block: B:162:0x045a  */
    /* JADX WARN: Code duplicated, block: B:164:0x0464  */
    /* JADX WARN: Code duplicated, block: B:165:0x046e  */
    /* JADX WARN: Code duplicated, block: B:167:0x0472  */
    /* JADX WARN: Code duplicated, block: B:169:0x047c  */
    /* JADX WARN: Code duplicated, block: B:170:0x0486  */
    /* JADX WARN: Code duplicated, block: B:172:0x0490  */
    /* JADX WARN: Code duplicated, block: B:177:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:179:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:198:0x052d  */
    /* JADX WARN: Code duplicated, block: B:200:0x0534  */
    /* JADX WARN: Code duplicated, block: B:216:0x039e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:217:0x054b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:0x0529 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:225:0x0520 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x021c  */
    /* JADX WARN: Instruction removed from duplicated block: B:144:0x03e7, please report this as an issue */
    Buffer M(Buffer buffer) throws Exception {
        Session session;
        Buffer buffer2;
        int i;
        int i2;
        Compression compression;
        int iH;
        boolean z;
        long jR;
        long j;
        Channel channelK;
        int i3;
        byte[] bArrC;
        int i4;
        int i5;
        MAC mac;
        Session session2 = this;
        Buffer buffer3 = buffer;
        Cipher cipher = session2.o;
        char c = 1;
        boolean z2 = cipher != null && cipher.k();
        Cipher cipher2 = session2.o;
        boolean z3 = cipher2 != null && cipher2.n();
        boolean z4 = (z2 || z3 || session2.o == null || (mac = session2.q) == null || !mac.f()) ? false : true;
        while (true) {
            buffer3.A();
            if (z2) {
                session2.w.c(buffer3.b, buffer3.c, 4);
                buffer3.c += 4;
                session2.o.e(session2.l);
                byte[] bArr = new byte[4];
                session2.o.g(buffer3.b, 0, 4, bArr, 0);
                int i6 = ((bArr[0] << 24) & (-16777216)) | ((bArr[c] << 16) & 16711680) | ((bArr[2] << 8) & 65280) | (bArr[3] & 255);
                if (i6 < 5 || i6 > 262144) {
                    i4 = i6;
                    session2.k0(buffer3, session2.o, session2.q, 0, 262144);
                } else {
                    i4 = i6;
                }
                int iH2 = i4 + session2.o.h();
                int i7 = buffer3.c;
                int i8 = i7 + iH2;
                byte[] bArr2 = buffer3.b;
                if (i8 > bArr2.length) {
                    byte[] bArr3 = new byte[i7 + iH2];
                    System.arraycopy(bArr2, 0, bArr3, 0, i7);
                    buffer3.b = bArr3;
                }
                if (iH2 % session2.s0 != 0) {
                    String str = "Bad packet length " + iH2;
                    if (session2.A().isEnabled(4)) {
                        session2.A().a(4, str);
                    }
                    i5 = iH2;
                    session2.k0(buffer3, session2.o, session2.q, 0, 262144 - session2.s0);
                } else {
                    i5 = iH2;
                }
                session2.w.c(buffer3.b, buffer3.c, i5);
                int iH3 = i5 - session2.o.h();
                buffer3.c += iH3;
                try {
                    Cipher cipher3 = session2.o;
                    byte[] bArr4 = buffer3.b;
                    cipher3.i(bArr4, 0, iH3 + 4, bArr4, 0);
                    System.arraycopy(bArr, 0, buffer3.b, 0, 4);
                } catch (AEADBadTagException e) {
                    throw new JSchException("Packet corrupt", e);
                }
            } else {
                if (z3 || z4) {
                    session2.w.c(buffer3.b, buffer3.c, 4);
                    buffer3.c += 4;
                    byte[] bArr5 = buffer3.b;
                    int iH4 = ((bArr5[0] << 24) & (-16777216)) | ((bArr5[1] << 16) & 16711680) | ((bArr5[2] << 8) & 65280) | (bArr5[3] & 255);
                    if (iH4 < 5 || iH4 > 262144) {
                        session2.k0(buffer3, session2.o, session2.q, 0, 262144);
                    }
                    if (z3) {
                        iH4 += session2.o.h();
                    }
                    int i9 = buffer3.c;
                    int i10 = i9 + iH4;
                    byte[] bArr6 = buffer3.b;
                    if (i10 > bArr6.length) {
                        byte[] bArr7 = new byte[i9 + iH4];
                        System.arraycopy(bArr6, 0, bArr7, 0, i9);
                        buffer3.b = bArr7;
                    }
                    if (iH4 % session2.s0 != 0) {
                        String str2 = "Bad packet length " + iH4;
                        if (session2.A().isEnabled(4)) {
                            session2.A().a(4, str2);
                        }
                        session2.k0(buffer3, session2.o, session2.q, 0, 262144 - session2.s0);
                    }
                    session = session2;
                    buffer2 = buffer3;
                    session.w.c(buffer2.b, buffer2.c, iH4);
                    buffer2.c += iH4;
                    if (z3) {
                        try {
                            session.o.f(buffer2.b, 0, 4);
                            Cipher cipher4 = session.o;
                            byte[] bArr8 = buffer2.b;
                            cipher4.i(bArr8, 4, iH4, bArr8, 4);
                            buffer2.c -= session.o.h();
                        } catch (AEADBadTagException e2) {
                            throw new JSchException("Packet corrupt", e2);
                        }
                    } else {
                        int i11 = iH4;
                        session.q.e(session.l);
                        session.q.b(buffer2.b, 0, buffer2.c);
                        session.q.c(session.s, 0);
                        IO io = session.w;
                        byte[] bArr9 = session.t;
                        io.c(bArr9, 0, bArr9.length);
                        if (!Util.b(session.s, session.t)) {
                            throw new JSchException("Packet corrupt");
                        }
                        Cipher cipher5 = session.o;
                        byte[] bArr10 = buffer2.b;
                        cipher5.g(bArr10, 4, i11, bArr10, 4);
                    }
                    i2 = session.l + 1;
                    session.l = i2;
                    if (i2 != 0 && ((session.F || session.G) && session.D)) {
                        throw new JSchStrictKexException("incoming sequence number wrapped during initial KEX");
                    }
                    compression = session.v;
                    if (compression != null) {
                        byte[] bArr11 = buffer2.b;
                        byte b = bArr11[4];
                        int[] iArr = session.q0;
                        iArr[0] = (buffer2.c - 5) - b;
                        bArrC = compression.c(bArr11, 5, iArr);
                        if (bArrC != null) {
                            buffer2.b = bArrC;
                            buffer2.c = session.q0[0] + 5;
                            iH = buffer2.h() & 255;
                            if (iH != 1) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                int i12 = buffer2.i();
                                byte[] bArrP = buffer2.p();
                                byte[] bArrP2 = buffer2.p();
                                String strC = Util.c(bArrP);
                                String strC2 = Util.c(bArrP2);
                                throw new JSchSessionDisconnectException("SSH_MSG_DISCONNECT: " + i12 + " " + strC + " " + strC2, i12, strC, strC2);
                            }
                            if (session.D || !session.E) {
                                if (iH != 2) {
                                    if (iH == 3) {
                                        buffer2.B();
                                        buffer2.i();
                                        buffer2.o();
                                        i3 = buffer2.i();
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                                        }
                                    } else if (iH == 4) {
                                        buffer2.B();
                                        buffer2.i();
                                        buffer2.o();
                                    } else if (iH == 93) {
                                        buffer2.B();
                                        buffer2.i();
                                        buffer2.o();
                                        channelK = Channel.k(buffer2.i(), session);
                                        if (channelK != null) {
                                            channelK.a(buffer2.r());
                                        }
                                    } else if (iH == 7) {
                                        buffer2.B();
                                        buffer2.i();
                                        buffer2.o();
                                        if (session.B) {
                                            z = true;
                                            if (session.H) {
                                                if (session.A().isEnabled(1)) {
                                                    session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                                }
                                            } else if (!session.n0) {
                                                if (session.A().isEnabled(1)) {
                                                    session.A().a(1, "SSH_MSG_EXT_INFO received");
                                                }
                                                z = false;
                                            } else if (session.A().isEnabled(1)) {
                                                session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                            }
                                        } else {
                                            z = true;
                                            if (session.A().isEnabled(1)) {
                                                session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                            }
                                        }
                                        jR = buffer2.r();
                                        for (j = 0; j < jR; j++) {
                                            byte[] bArrP3 = buffer2.p();
                                            byte[] bArrP4 = buffer2.p();
                                            if (z && Util.c(bArrP3).equals("server-sig-algs")) {
                                                String strC3 = Util.c(bArrP4);
                                                if (session.A().isEnabled(1)) {
                                                    session.A().a(1, "server-sig-algs=<" + strC3 + ">");
                                                }
                                                if (session.b0) {
                                                    strC3 = strC3.isEmpty() ? "rsa-sha2-256,rsa-sha2-512" : strC3 + ",rsa-sha2-256,rsa-sha2-512";
                                                    if (session.A().isEnabled(1)) {
                                                        session.A().a(1, "OpenSSH 7.4 detected: adding rsa-sha2-256 & rsa-sha2-512 to server-sig-algs");
                                                    }
                                                }
                                                session.a0 = Util.u(strC3, ",");
                                            }
                                        }
                                    } else if (iH == 52) {
                                        session.H = true;
                                        if (session.v == null && session.u == null) {
                                            session.H(session.n[6]);
                                            session.I(session.n[7]);
                                        }
                                    }
                                }
                                session2 = session;
                                buffer3 = buffer2;
                            }
                        } else if (session.A().isEnabled(3)) {
                            session.A().a(3, "fail in inflater");
                        }
                    } else {
                        iH = buffer2.h() & 255;
                        if (iH != 1) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            int i13 = buffer2.i();
                            byte[] bArrP5 = buffer2.p();
                            byte[] bArrP6 = buffer2.p();
                            String strC4 = Util.c(bArrP5);
                            String strC5 = Util.c(bArrP6);
                            throw new JSchSessionDisconnectException("SSH_MSG_DISCONNECT: " + i13 + " " + strC4 + " " + strC5, i13, strC4, strC5);
                        }
                        if (session.D) {
                            if (iH != 2) {
                                if (iH == 3) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    i3 = buffer2.i();
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                                    }
                                } else if (iH == 4) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                } else if (iH == 93) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    channelK = Channel.k(buffer2.i(), session);
                                    if (channelK != null) {
                                        channelK.a(buffer2.r());
                                    }
                                } else if (iH == 7) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    if (session.B) {
                                        z = true;
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                        }
                                    } else {
                                        z = true;
                                        if (session.H) {
                                            if (session.A().isEnabled(1)) {
                                                session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                            }
                                        } else if (!session.n0) {
                                            if (session.A().isEnabled(1)) {
                                                session.A().a(1, "SSH_MSG_EXT_INFO received");
                                            }
                                            z = false;
                                        } else if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                        }
                                    }
                                    jR = buffer2.r();
                                    while (j < jR) {
                                        byte[] bArrP7 = buffer2.p();
                                        byte[] bArrP8 = buffer2.p();
                                        if (z) {
                                        }
                                    }
                                } else if (iH == 52) {
                                    session.H = true;
                                    if (session.v == null) {
                                        session.H(session.n[6]);
                                        session.I(session.n[7]);
                                    }
                                }
                            }
                            session2 = session;
                            buffer3 = buffer2;
                        } else {
                            if (iH != 2) {
                                if (iH == 3) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    i3 = buffer2.i();
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                                    }
                                } else if (iH == 4) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                } else if (iH == 93) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    channelK = Channel.k(buffer2.i(), session);
                                    if (channelK != null) {
                                        channelK.a(buffer2.r());
                                    }
                                } else if (iH == 7) {
                                    buffer2.B();
                                    buffer2.i();
                                    buffer2.o();
                                    if (session.B) {
                                        z = true;
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                        }
                                    } else {
                                        z = true;
                                        if (session.H) {
                                            if (session.A().isEnabled(1)) {
                                                session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                            }
                                        } else if (!session.n0) {
                                            if (session.A().isEnabled(1)) {
                                                session.A().a(1, "SSH_MSG_EXT_INFO received");
                                            }
                                            z = false;
                                        } else if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                        }
                                    }
                                    jR = buffer2.r();
                                    while (j < jR) {
                                        byte[] bArrP9 = buffer2.p();
                                        byte[] bArrP10 = buffer2.p();
                                        if (z) {
                                        }
                                    }
                                } else if (iH == 52) {
                                    session.H = true;
                                    if (session.v == null) {
                                        session.H(session.n[6]);
                                        session.I(session.n[7]);
                                    }
                                }
                            }
                            session2 = session;
                            buffer3 = buffer2;
                        }
                    }
                    buffer2.B();
                    return buffer2;
                }
                session2.w.c(buffer3.b, buffer3.c, session2.s0);
                int i14 = buffer3.c;
                int i15 = session2.s0;
                buffer3.c = i14 + i15;
                Cipher cipher6 = session2.o;
                if (cipher6 != null) {
                    byte[] bArr12 = buffer3.b;
                    cipher6.g(bArr12, 0, i15, bArr12, 0);
                }
                byte[] bArr13 = buffer3.b;
                int i16 = ((bArr13[0] << 24) & (-16777216)) | ((bArr13[1] << 16) & 16711680) | ((bArr13[2] << 8) & 65280) | (bArr13[3] & 255);
                if (i16 < 5 || i16 > 262144) {
                    session2.k0(buffer3, session2.o, session2.q, 0, 262144);
                }
                int i17 = (i16 + 4) - session2.s0;
                int i18 = buffer3.c;
                int i19 = i18 + i17;
                byte[] bArr14 = buffer3.b;
                if (i19 > bArr14.length) {
                    byte[] bArr15 = new byte[i18 + i17];
                    System.arraycopy(bArr14, 0, bArr15, 0, i18);
                    buffer3.b = bArr15;
                }
                if (i17 % session2.s0 != 0) {
                    String str3 = "Bad packet length " + i17;
                    if (session2.A().isEnabled(4)) {
                        session2.A().a(4, str3);
                    }
                    session2.k0(buffer3, session2.o, session2.q, 0, 262144 - session2.s0);
                }
                if (i17 > 0) {
                    session2.w.c(buffer3.b, buffer3.c, i17);
                    buffer3.c += i17;
                    Cipher cipher7 = session2.o;
                    if (cipher7 != null) {
                        byte[] bArr16 = buffer3.b;
                        int i20 = session2.s0;
                        i = i17;
                        cipher7.g(bArr16, i20, i, bArr16, i20);
                    } else {
                        i = i17;
                    }
                } else {
                    i = i17;
                }
                MAC mac2 = session2.q;
                if (mac2 != null) {
                    mac2.e(session2.l);
                    session2.q.b(buffer3.b, 0, buffer3.c);
                    session2.q.c(session2.s, 0);
                    IO io2 = session2.w;
                    byte[] bArr17 = session2.t;
                    io2.c(bArr17, 0, bArr17.length);
                    if (!Util.b(session2.s, session2.t)) {
                        int i21 = session2.s0;
                        if (i + i21 > 262144) {
                            throw new IOException("MAC Error");
                        }
                        session2.k0(buffer3, session2.o, session2.q, buffer3.c, (262144 - i) - i21);
                    }
                }
                c = 1;
            }
            session = session2;
            buffer2 = buffer3;
            i2 = session.l + 1;
            session.l = i2;
            if (i2 != 0) {
            }
            compression = session.v;
            if (compression != null) {
                byte[] bArr18 = buffer2.b;
                byte b2 = bArr18[4];
                int[] iArr2 = session.q0;
                iArr2[0] = (buffer2.c - 5) - b2;
                bArrC = compression.c(bArr18, 5, iArr2);
                if (bArrC != null) {
                    buffer2.b = bArrC;
                    buffer2.c = session.q0[0] + 5;
                    iH = buffer2.h() & 255;
                    if (iH != 1) {
                        buffer2.B();
                        buffer2.i();
                        buffer2.o();
                        int i110 = buffer2.i();
                        byte[] bArrP11 = buffer2.p();
                        byte[] bArrP12 = buffer2.p();
                        String strC6 = Util.c(bArrP11);
                        String strC7 = Util.c(bArrP12);
                        throw new JSchSessionDisconnectException("SSH_MSG_DISCONNECT: " + i110 + " " + strC6 + " " + strC7, i110, strC6, strC7);
                    }
                    if (session.D) {
                        if (iH != 2) {
                            if (iH == 3) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                i3 = buffer2.i();
                                if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                                }
                            } else if (iH == 4) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                            } else if (iH == 93) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                channelK = Channel.k(buffer2.i(), session);
                                if (channelK != null) {
                                    channelK.a(buffer2.r());
                                }
                            } else if (iH == 7) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                if (session.B) {
                                    z = true;
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                    }
                                } else {
                                    z = true;
                                    if (session.H) {
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                        }
                                    } else if (!session.n0) {
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "SSH_MSG_EXT_INFO received");
                                        }
                                        z = false;
                                    } else if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                    }
                                }
                                jR = buffer2.r();
                                while (j < jR) {
                                    byte[] bArrP13 = buffer2.p();
                                    byte[] bArrP14 = buffer2.p();
                                    if (z) {
                                    }
                                }
                            } else if (iH == 52) {
                                session.H = true;
                                if (session.v == null) {
                                    session.H(session.n[6]);
                                    session.I(session.n[7]);
                                }
                            }
                        }
                        session2 = session;
                        buffer3 = buffer2;
                        c = 1;
                    } else {
                        if (iH != 2) {
                            if (iH == 3) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                i3 = buffer2.i();
                                if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                                }
                            } else if (iH == 4) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                            } else if (iH == 93) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                channelK = Channel.k(buffer2.i(), session);
                                if (channelK != null) {
                                    channelK.a(buffer2.r());
                                }
                            } else if (iH == 7) {
                                buffer2.B();
                                buffer2.i();
                                buffer2.o();
                                if (session.B) {
                                    z = true;
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                    }
                                } else {
                                    z = true;
                                    if (session.H) {
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                        }
                                    } else if (!session.n0) {
                                        if (session.A().isEnabled(1)) {
                                            session.A().a(1, "SSH_MSG_EXT_INFO received");
                                        }
                                        z = false;
                                    } else if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                    }
                                }
                                jR = buffer2.r();
                                while (j < jR) {
                                    byte[] bArrP15 = buffer2.p();
                                    byte[] bArrP16 = buffer2.p();
                                    if (z) {
                                    }
                                }
                            } else if (iH == 52) {
                                session.H = true;
                                if (session.v == null) {
                                    session.H(session.n[6]);
                                    session.I(session.n[7]);
                                }
                            }
                        }
                        session2 = session;
                        buffer3 = buffer2;
                        c = 1;
                    }
                } else if (session.A().isEnabled(3)) {
                    session.A().a(3, "fail in inflater");
                }
            } else {
                iH = buffer2.h() & 255;
                if (iH != 1) {
                    buffer2.B();
                    buffer2.i();
                    buffer2.o();
                    int i111 = buffer2.i();
                    byte[] bArrP17 = buffer2.p();
                    byte[] bArrP18 = buffer2.p();
                    String strC8 = Util.c(bArrP17);
                    String strC9 = Util.c(bArrP18);
                    throw new JSchSessionDisconnectException("SSH_MSG_DISCONNECT: " + i111 + " " + strC8 + " " + strC9, i111, strC8, strC9);
                }
                if (session.D) {
                    if (iH != 2) {
                        if (iH == 3) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            i3 = buffer2.i();
                            if (session.A().isEnabled(1)) {
                                session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                            }
                        } else if (iH == 4) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                        } else if (iH == 93) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            channelK = Channel.k(buffer2.i(), session);
                            if (channelK != null) {
                                channelK.a(buffer2.r());
                            }
                        } else if (iH == 7) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            if (session.B) {
                                z = true;
                                if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                }
                            } else {
                                z = true;
                                if (session.H) {
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                    }
                                } else if (!session.n0) {
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "SSH_MSG_EXT_INFO received");
                                    }
                                    z = false;
                                } else if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                }
                            }
                            jR = buffer2.r();
                            while (j < jR) {
                                byte[] bArrP19 = buffer2.p();
                                byte[] bArrP110 = buffer2.p();
                                if (z) {
                                }
                            }
                        } else if (iH == 52) {
                            session.H = true;
                            if (session.v == null) {
                                session.H(session.n[6]);
                                session.I(session.n[7]);
                            }
                        }
                    }
                    session2 = session;
                    buffer3 = buffer2;
                    c = 1;
                } else {
                    if (iH != 2) {
                        if (iH == 3) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            i3 = buffer2.i();
                            if (session.A().isEnabled(1)) {
                                session.A().a(1, "Received SSH_MSG_UNIMPLEMENTED for " + i3);
                            }
                        } else if (iH == 4) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                        } else if (iH == 93) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            channelK = Channel.k(buffer2.i(), session);
                            if (channelK != null) {
                                channelK.a(buffer2.r());
                            }
                        } else if (iH == 7) {
                            buffer2.B();
                            buffer2.i();
                            buffer2.o();
                            if (session.B) {
                                z = true;
                                if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Ignoring SSH_MSG_EXT_INFO while enable_server_sig_algs != yes");
                                }
                            } else {
                                z = true;
                                if (session.H) {
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received after SSH_MSG_USERAUTH_SUCCESS");
                                    }
                                } else if (!session.n0) {
                                    if (session.A().isEnabled(1)) {
                                        session.A().a(1, "SSH_MSG_EXT_INFO received");
                                    }
                                    z = false;
                                } else if (session.A().isEnabled(1)) {
                                    session.A().a(1, "Ignoring SSH_MSG_EXT_INFO received before SSH_MSG_NEWKEYS");
                                }
                            }
                            jR = buffer2.r();
                            while (j < jR) {
                                byte[] bArrP111 = buffer2.p();
                                byte[] bArrP112 = buffer2.p();
                                if (z) {
                                }
                            }
                        } else if (iH == 52) {
                            session.H = true;
                            if (session.v == null) {
                                session.H(session.n[6]);
                                session.I(session.n[7]);
                            }
                        }
                    }
                    session2 = session;
                    buffer3 = buffer2;
                    c = 1;
                }
            }
            buffer2.B();
            return buffer2;
        }
    }

    void Q() {
        int i;
        int i2;
        byte b;
        this.u0 = new f(this);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        while (true) {
            KeyExchange keyExchangeN = null;
            while (true) {
                int i3 = 0;
                while (true) {
                    try {
                        if (this.z && this.u0 != null) {
                            try {
                                buffer = M(buffer);
                                int iH = buffer.h() & 255;
                                if (keyExchangeN != null && keyExchangeN.k() == iH) {
                                    this.d0 = System.currentTimeMillis();
                                    boolean zN = keyExchangeN.n(buffer);
                                    if (!zN) {
                                        throw new JSchException("verify: " + zN);
                                    }
                                } else if (iH == 20) {
                                    keyExchangeN = N(buffer);
                                } else if (iH != 21) {
                                    switch (iH) {
                                        case 80:
                                            buffer.i();
                                            buffer.o();
                                            buffer.p();
                                            if (buffer.c() != 0) {
                                                packet.c();
                                                buffer.s((byte) 82);
                                                m0(packet);
                                            }
                                            break;
                                        case 81:
                                        case 82:
                                            Thread threadC = this.v0.c();
                                            if (threadC != null) {
                                                this.v0.e(iH == 81 ? 1 : 0);
                                                if (iH == 81 && this.v0.a() == 0) {
                                                    buffer.i();
                                                    buffer.o();
                                                    this.v0.d(buffer.i());
                                                }
                                                threadC.interrupt();
                                            }
                                            break;
                                        default:
                                            switch (iH) {
                                                case SubsamplingScaleImageView.ORIENTATION_90 /* 90 */:
                                                    buffer.i();
                                                    buffer.o();
                                                    String strC = Util.c(buffer.p());
                                                    if ("forwarded-tcpip".equals(strC) || (("x11".equals(strC) && this.K) || ("auth-agent@openssh.com".equals(strC) && this.L))) {
                                                        final Channel channelL = Channel.l(strC, this);
                                                        c(channelL);
                                                        channelL.m(buffer);
                                                        channelL.q();
                                                        Thread thread = new Thread(new Runnable() { // from class: com.jcraft.jsch.g
                                                            @Override // java.lang.Runnable
                                                            public final void run() {
                                                                channelL.t();
                                                            }
                                                        });
                                                        thread.setName("Channel " + strC + " " + this.g0);
                                                        boolean z = this.c0;
                                                        if (z) {
                                                            thread.setDaemon(z);
                                                        }
                                                        thread.start();
                                                    } else {
                                                        packet.c();
                                                        buffer.s((byte) 92);
                                                        buffer.v(buffer.i());
                                                        buffer.v(1);
                                                        byte[] bArr = Util.c;
                                                        buffer.y(bArr);
                                                        buffer.y(bArr);
                                                        m0(packet);
                                                    }
                                                    break;
                                                case 91:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK = Channel.k(buffer.i(), this);
                                                    int i4 = buffer.i();
                                                    long jR = buffer.r();
                                                    int i5 = buffer.i();
                                                    if (channelK != null) {
                                                        channelK.B(jR);
                                                        channelK.A(i5);
                                                        channelK.o = true;
                                                        channelK.z(i4);
                                                    }
                                                    break;
                                                case 92:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK2 = Channel.k(buffer.i(), this);
                                                    if (channelK2 != null) {
                                                        channelK2.x(buffer.i());
                                                        channelK2.m = true;
                                                        channelK2.l = true;
                                                        channelK2.z(0);
                                                    }
                                                    break;
                                                case 93:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK3 = Channel.k(buffer.i(), this);
                                                    if (channelK3 != null) {
                                                        channelK3.a(buffer.r());
                                                    }
                                                    break;
                                                case 94:
                                                    buffer.i();
                                                    buffer.c();
                                                    buffer.c();
                                                    Channel channelK4 = Channel.k(buffer.i(), this);
                                                    byte[] bArrQ = buffer.q(iArr, iArr2);
                                                    if (channelK4 != null && (i = iArr2[0]) != 0) {
                                                        try {
                                                            try {
                                                                channelK4.E(bArrQ, iArr[0], i);
                                                                channelK4.y(channelK4.e - iArr2[0]);
                                                                if (channelK4.e >= channelK4.d / 2) {
                                                                    continue;
                                                                } else {
                                                                    packet.c();
                                                                    buffer.s((byte) 93);
                                                                    buffer.v(channelK4.o());
                                                                    buffer.v(channelK4.d - channelK4.e);
                                                                    synchronized (channelK4) {
                                                                        try {
                                                                            if (!channelK4.m) {
                                                                                m0(packet);
                                                                            }
                                                                        } catch (Throwable th) {
                                                                            throw th;
                                                                        }
                                                                    }
                                                                    channelK4.y(channelK4.d);
                                                                }
                                                            } catch (Exception unused) {
                                                                channelK4.f();
                                                            }
                                                        } catch (Exception unused2) {
                                                            continue;
                                                        }
                                                    }
                                                    break;
                                                case 95:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK5 = Channel.k(buffer.i(), this);
                                                    buffer.i();
                                                    byte[] bArrQ2 = buffer.q(iArr, iArr2);
                                                    if (channelK5 != null && (i2 = iArr2[0]) != 0) {
                                                        channelK5.F(bArrQ2, iArr[0], i2);
                                                        channelK5.y(channelK5.e - iArr2[0]);
                                                        if (channelK5.e >= channelK5.d / 2) {
                                                            continue;
                                                        } else {
                                                            packet.c();
                                                            buffer.s((byte) 93);
                                                            buffer.v(channelK5.o());
                                                            buffer.v(channelK5.d - channelK5.e);
                                                            synchronized (channelK5) {
                                                                try {
                                                                    if (!channelK5.m) {
                                                                        m0(packet);
                                                                    }
                                                                } catch (Throwable th2) {
                                                                    throw th2;
                                                                }
                                                            }
                                                            channelK5.y(channelK5.d);
                                                        }
                                                    }
                                                    break;
                                                case 96:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK6 = Channel.k(buffer.i(), this);
                                                    if (channelK6 != null) {
                                                        channelK6.i();
                                                    }
                                                    break;
                                                case 97:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK7 = Channel.k(buffer.i(), this);
                                                    if (channelK7 != null) {
                                                        channelK7.f();
                                                    }
                                                    break;
                                                case 98:
                                                    buffer.i();
                                                    buffer.o();
                                                    int i6 = buffer.i();
                                                    byte[] bArrP = buffer.p();
                                                    boolean z2 = buffer.c() != 0;
                                                    Channel channelK8 = Channel.k(i6, this);
                                                    if (channelK8 != null) {
                                                        if (Util.c(bArrP).equals("exit-status")) {
                                                            channelK8.x(buffer.i());
                                                            b = 99;
                                                        } else {
                                                            b = 100;
                                                        }
                                                        if (z2) {
                                                            packet.c();
                                                            buffer.s(b);
                                                            buffer.v(channelK8.o());
                                                            m0(packet);
                                                        }
                                                    }
                                                    break;
                                                case 99:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK9 = Channel.k(buffer.i(), this);
                                                    if (channelK9 != null) {
                                                        channelK9.q = 1;
                                                    }
                                                    break;
                                                case 100:
                                                    buffer.i();
                                                    buffer.o();
                                                    Channel channelK10 = Channel.k(buffer.i(), this);
                                                    if (channelK10 != null) {
                                                        channelK10.q = 0;
                                                    }
                                                    break;
                                                default:
                                                    throw new IOException("Unknown SSH message type " + iH);
                                            }
                                            break;
                                    }
                                } else {
                                    U();
                                    O(buffer, keyExchangeN);
                                }
                            } catch (InterruptedIOException e) {
                                if (!this.n0 && i3 < this.X) {
                                    R();
                                } else if (!this.n0 || i3 >= this.X) {
                                    throw e;
                                }
                                i3++;
                            }
                        }
                    } catch (Exception e2) {
                        this.n0 = false;
                        if (A().isEnabled(1)) {
                            A().a(1, "Caught an exception, leaving main loop due to " + e2.getMessage());
                        }
                    }
                }
            }
        }
        try {
            s();
        } catch (NullPointerException | Exception unused3) {
        }
        this.z = false;
    }

    public void R() throws Exception {
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 80);
        buffer.y(y0);
        buffer.s((byte) 1);
        m0(packet);
    }

    public void V(String str, String str2) {
        synchronized (this.J) {
            try {
                if (this.R == null) {
                    this.R = new Hashtable<>();
                }
                if (str.equals("PubkeyAcceptedKeyTypes")) {
                    this.R.put("PubkeyAcceptedAlgorithms", str2);
                } else {
                    this.R.put(str, str2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void W(String str) {
        this.V = str;
    }

    public void X(HostKeyConf hostKeyConf) {
        this.U = hostKeyConf;
    }

    public void Y(HostKeyRepository hostKeyRepository) {
        this.Z = hostKeyRepository;
    }

    public void Z(IdentityRepository identityRepository) {
        this.Y = identityRepository;
    }

    public void a0(String str) {
        if (str != null) {
            this.k0 = Util.v(str);
        }
    }

    public int b0(String str) throws JSchException {
        Forwarding forwardingL = L(str);
        return c0(forwardingL.a, forwardingL.b, forwardingL.c, forwardingL.d);
    }

    void c(Channel channel) {
        channel.C(this);
    }

    public int c0(String str, int i, String str2, int i2) throws JSchException {
        return d0(str, i, str2, i2, null);
    }

    public int d0(String str, int i, String str2, int i2, ServerSocketFactory serverSocketFactory) throws JSchException {
        return e0(str, i, str2, i2, serverSocketFactory, 0);
    }

    public int e0(String str, int i, String str2, int i2, ServerSocketFactory serverSocketFactory, int i3) throws JSchException {
        PortWatcher portWatcherA = PortWatcher.a(this, str, i, str2, i2, serverSocketFactory);
        portWatcherA.h(i3);
        Thread thread = new Thread(new e(portWatcherA));
        thread.setName("PortWatcher Thread for " + str2);
        boolean z = this.c0;
        if (z) {
            thread.setDaemon(z);
        }
        thread.start();
        return portWatcherA.b;
    }

    public int f0(String str) throws JSchException {
        Forwarding forwardingL = L(str);
        int iA = a(forwardingL.a, forwardingL.b);
        ChannelForwardedTCPIP.G(this, forwardingL.a, forwardingL.b, iA, forwardingL.c, forwardingL.d, null);
        return iA;
    }

    public void g0(int i) throws JSchException {
        i0(i);
        this.W = i;
    }

    public void h0(SocketFactory socketFactory) {
        this.Q = socketFactory;
    }

    public void i0(int i) throws JSchException {
        Socket socket = this.x;
        if (socket == null) {
            if (i < 0) {
                throw new JSchException("invalid timeout value");
            }
            this.y = i;
        } else {
            try {
                socket.setSoTimeout(i);
                this.y = i;
            } catch (Exception e) {
                throw new JSchException(e.toString(), e);
            }
        }
    }

    public void j0(UserInfo userInfo) {
        this.T = userInfo;
    }

    void m0(Packet packet) throws Exception {
        long jD = D();
        while (this.n0) {
            if (jD > 0 && System.currentTimeMillis() - this.d0 > jD && !this.o0) {
                throw new JSchException("timeout in waiting for rekeying process.");
            }
            byte bH = packet.a.h();
            if (bH == 20 || bH == 21 || bH == 30 || bH == 31 || bH == 31 || bH == 32 || bH == 33 || bH == 34 || bH == 1) {
                break;
            } else {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException unused) {
                }
            }
        }
        b(packet);
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0068, code lost:
    
        if (r13.m != false) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x006e, code lost:
    
        if (r13.s() == false) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0070, code lost:
    
        monitor-enter(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0071, code lost:
    
        r9 = 0;
        r9 = 0;
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0076, code lost:
    
        if (r13.g <= 0) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0078, code lost:
    
        r3 = r13.g;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x007c, code lost:
    
        if (r3 <= r7) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x007e, code lost:
    
        r3 = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0081, code lost:
    
        if (r3 == r7) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0083, code lost:
    
        r5 = (int) r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0086, code lost:
    
        if (r11.p == null) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0088, code lost:
    
        r6 = r11.t0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x008b, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008d, code lost:
    
        r6 = 8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x008f, code lost:
    
        r7 = r11.r;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0091, code lost:
    
        if (r7 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0093, code lost:
    
        r9 = r7.d();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0097, code lost:
    
        r9 = r12.e(r5, r6, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009b, code lost:
    
        r5 = r12.a.h();
        r6 = r13.o();
        r14 = r14 - ((int) r3);
        r13.g -= r3;
        r3 = r9;
        r9 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00af, code lost:
    
        r6 = -1;
        r2 = false;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00b2, code lost:
    
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b3, code lost:
    
        if (r2 == false) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b5, code lost:
    
        b(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00b8, code lost:
    
        if (r14 != 0) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00ba, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00bb, code lost:
    
        r12.f(r9, r6, r3, r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00be, code lost:
    
        monitor-enter(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00c1, code lost:
    
        if (r11.n0 == false) goto L110;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00c3, code lost:
    
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00c6, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00c8, code lost:
    
        r4 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00cd, code lost:
    
        if (r13.g < r4) goto L82;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00cf, code lost:
    
        r13.g -= r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x00d4, code lost:
    
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x00d9, code lost:
    
        monitor-exit(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00dd, code lost:
    
        throw r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00df, code lost:
    
        throw r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x00e7, code lost:
    
        throw new java.io.IOException("channel is broken");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void n0(Packet packet, Channel channel, int i) throws Exception {
        int i2;
        long jD = D();
        while (true) {
            if (!this.n0) {
                synchronized (channel) {
                    try {
                        long j = i;
                        boolean z = true;
                        if (channel.g < j) {
                            try {
                                try {
                                    channel.t++;
                                    channel.wait(100L);
                                    i2 = channel.t;
                                } catch (Throwable th) {
                                    channel.t--;
                                    throw th;
                                }
                            } catch (InterruptedException unused) {
                                i2 = channel.t;
                            }
                            channel.t = i2 - 1;
                        }
                        if (!this.n0) {
                            if (channel.g >= j) {
                                channel.g -= j;
                            }
                            b(packet);
                            return;
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
            } else {
                if (jD > 0 && System.currentTimeMillis() - this.d0 > jD) {
                    throw new JSchException("timeout in waiting for rekeying process.");
                }
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException unused2) {
                }
            }
        }
    }

    public void q() throws JSchException {
        r(this.y);
    }

    /* JADX WARN: Code restructure failed: missing block: B:222:0x0581, code lost:
    
        r16.n0 = false;
        r16.o0 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:223:0x0585, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x028e, code lost:
    
        r0 = move-exception;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void r(int i) throws JSchException {
        int i2;
        byte[] bArr;
        boolean z;
        UserAuth userAuth;
        Socket socket;
        InputStream inputStreamA;
        OutputStream outputStreamB;
        if (this.z) {
            throw new JSchException("session is already connected");
        }
        this.D = true;
        this.w = new IO();
        if (x0 == null) {
            try {
                x0 = (Random) Class.forName(w("random")).asSubclass(Random.class).getDeclaredConstructor(null).newInstance(null);
            } catch (Exception e) {
                throw new JSchException(e.toString(), e);
            }
        }
        Packet.d(x0);
        if (A().isEnabled(1)) {
            A().a(1, "Connecting to " + this.g0 + " port " + this.i0);
        }
        try {
            try {
                Proxy proxy = this.S;
                if (proxy == null) {
                    SocketFactory socketFactory = this.Q;
                    if (socketFactory == null) {
                        Socket socketI = Util.i(this.g0, this.i0, i);
                        this.x = socketI;
                        inputStreamA = socketI.getInputStream();
                        outputStreamB = this.x.getOutputStream();
                    } else {
                        Socket socketC = socketFactory.c(this.g0, this.i0);
                        this.x = socketC;
                        inputStreamA = this.Q.a(socketC);
                        outputStreamB = this.Q.b(this.x);
                    }
                    this.x.setTcpNoDelay(true);
                    this.w.h(inputStreamA);
                    this.w.j(outputStreamB);
                } else {
                    synchronized (proxy) {
                        this.S.d(this.Q, this.g0, this.i0, i);
                        this.w.h(this.S.c());
                        this.w.j(this.S.b());
                        this.x = this.S.a();
                    }
                }
                if (i > 0 && (socket = this.x) != null) {
                    socket.setSoTimeout(i);
                }
                this.z = true;
                if (A().isEnabled(1)) {
                    A().a(1, "Connection established");
                }
                this.l0.e(this);
                byte[] bArr2 = this.b;
                int length = bArr2.length;
                int i3 = length + 2;
                byte[] bArr3 = new byte[i3];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                bArr3[length] = 13;
                bArr3[length + 1] = 10;
                this.w.f(bArr3, 0, i3);
                while (true) {
                    i2 = 0;
                    int iB = 0;
                    while (i2 < this.O.b.length && (iB = this.w.b()) >= 0) {
                        this.O.b[i2] = (byte) iB;
                        i2++;
                        if (iB == 10) {
                            break;
                        }
                    }
                    if (iB < 0) {
                        throw new JSchException("connection is closed by foreign host");
                    }
                    bArr = this.O.b;
                    if (bArr[i2 - 1] == 10) {
                        int i4 = i2 - 1;
                        i2 = (i4 <= 0 || bArr[i2 + (-2)] != 13) ? i4 : i2 - 2;
                    }
                    if (i2 > 3 && (i2 == bArr.length || (bArr[0] == 83 && bArr[1] == 83 && bArr[2] == 72 && bArr[3] == 45))) {
                        break;
                    }
                }
                if (i2 == bArr.length || i2 < 7 || (bArr[4] == 49 && bArr[6] != 57)) {
                    throw new JSchException("invalid server's version string");
                }
                byte[] bArr4 = new byte[i2];
                this.a = bArr4;
                System.arraycopy(bArr, 0, bArr4, 0, i2);
                String strC = Util.c(this.a);
                this.b0 = strC.startsWith("SSH-2.0-OpenSSH_7.4");
                if (A().isEnabled(1)) {
                    A().a(1, "Remote version string: " + strC);
                    A().a(1, "Local version string: " + Util.c(this.b));
                }
                this.B = w("enable_server_sig_algs").equals("yes");
                this.C = w("enable_ext_info_in_auth").equals("yes");
                this.F = w("enable_strict_kex").equals("yes");
                this.G = w("require_strict_kex").equals("yes");
                T();
                Buffer bufferM = M(this.O);
                this.O = bufferM;
                if (bufferM.h() != 20) {
                    this.n0 = false;
                    throw new JSchException("invalid protocol: " + ((int) this.O.h()));
                }
                if (A().isEnabled(1)) {
                    A().a(1, "SSH_MSG_KEXINIT received");
                }
                KeyExchange keyExchangeN = N(this.O);
                do {
                    this.O = M(this.O);
                    if (keyExchangeN.k() != this.O.h()) {
                        this.n0 = false;
                        throw new JSchException("invalid protocol(kex): " + ((int) this.O.h()));
                    }
                    this.d0 = System.currentTimeMillis();
                    boolean zN = keyExchangeN.n(this.O);
                    if (!zN) {
                        this.n0 = false;
                        throw new JSchException("verify: " + zN);
                    }
                } while (keyExchangeN.k() != 0);
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.o0 = true;
                i(this.g0, this.i0, keyExchangeN);
                HostKeyConf hostKeyConf = this.U;
                if (hostKeyConf != null && hostKeyConf.b()) {
                    Util.g(this.k0);
                    this.k0 = null;
                    return;
                }
                this.o0 = false;
                this.d0 += System.currentTimeMillis() - jCurrentTimeMillis;
                U();
                Buffer bufferM2 = M(this.O);
                this.O = bufferM2;
                if (bufferM2.h() != 21) {
                    this.n0 = false;
                    throw new JSchException("invalid protocol(newkeys): " + ((int) this.O.h()));
                }
                if (A().isEnabled(1)) {
                    A().a(1, "SSH_MSG_NEWKEYS received");
                }
                O(this.O, keyExchangeN);
                this.D = false;
                if (this.B && this.C && this.A) {
                    S();
                }
                try {
                    String strW = w("MaxAuthTries");
                    if (strW != null) {
                        this.e0 = Integer.parseInt(strW);
                    }
                    try {
                        UserAuthNone userAuthNone = (UserAuthNone) Class.forName(w("userauth.none")).asSubclass(UserAuthNone.class).getDeclaredConstructor(null).newInstance(null);
                        boolean zA = userAuthNone.a(this);
                        String strW2 = w("PreferredAuthentications");
                        String[] strArrU = Util.u(strW2, ",");
                        if (zA) {
                            strW2 = null;
                        } else {
                            String strB = userAuthNone.b();
                            if (strB != null) {
                                strW2 = strB.toLowerCase(Locale.ROOT);
                            }
                        }
                        String[] strArrU2 = Util.u(strW2, ",");
                        int i5 = 0;
                        loop3: while (true) {
                            z = false;
                            while (!zA && strArrU != null && i5 < strArrU.length) {
                                int i6 = i5 + 1;
                                String str = strArrU[i5];
                                int i7 = 0;
                                while (true) {
                                    if (i7 < strArrU2.length) {
                                        if (strArrU2[i7].equals(str)) {
                                            if (A().isEnabled(1)) {
                                                String str2 = "Authentications that can continue: ";
                                                while (i5 < strArrU.length) {
                                                    str2 = str2 + strArrU[i5];
                                                    i5++;
                                                    if (i5 < strArrU.length) {
                                                        str2 = str2 + ",";
                                                    }
                                                }
                                                A().a(1, str2);
                                                A().a(1, "Next authentication method: " + str);
                                            }
                                            try {
                                                if (w("userauth." + str) != null) {
                                                    userAuth = (UserAuth) Class.forName(w("userauth." + str)).asSubclass(UserAuth.class).getDeclaredConstructor(null).newInstance(null);
                                                } else {
                                                    userAuth = null;
                                                }
                                            } catch (Exception unused) {
                                                if (A().isEnabled(2)) {
                                                    A().a(2, "failed to load " + str + " method");
                                                }
                                            }
                                            if (userAuth != null) {
                                                try {
                                                    zA = userAuth.a(this);
                                                    if (zA && A().isEnabled(1)) {
                                                        A().a(1, "Authentication succeeded (" + str + ").");
                                                    }
                                                    z = false;
                                                } catch (JSchAuthCancelException unused2) {
                                                    z = true;
                                                } catch (JSchPartialAuthException e2) {
                                                    String strA = e2.a();
                                                    String[] strArrU3 = Util.u(strA, ",");
                                                    i5 = !strW2.equals(strA) ? 0 : i6;
                                                    strW2 = strA;
                                                    strArrU2 = strArrU3;
                                                } catch (JSchException e3) {
                                                    throw e3;
                                                } catch (RuntimeException e4) {
                                                    throw e4;
                                                } catch (Exception e5) {
                                                    if (A().isEnabled(2)) {
                                                        A().a(2, "an exception during authentication\n" + e5.toString());
                                                    }
                                                    z = false;
                                                }
                                            }
                                        } else {
                                            i7++;
                                        }
                                    }
                                    i5 = i6;
                                }
                            }
                            break loop3;
                        }
                        if (!zA) {
                            if (this.f0 >= this.e0 && A().isEnabled(1)) {
                                A().a(1, "Login trials exceeds " + this.e0);
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append(z ? "Auth cancel" : "Auth fail");
                            sb.append(" for methods '");
                            sb.append(strW2);
                            sb.append("'");
                            throw new JSchException(sb.toString());
                        }
                        Socket socket2 = this.x;
                        if (socket2 != null && (i > 0 || this.y > 0)) {
                            socket2.setSoTimeout(this.y);
                        }
                        this.H = true;
                        synchronized (this.J) {
                            try {
                                if (this.z) {
                                    Thread thread = new Thread(new f(this));
                                    this.I = thread;
                                    thread.setName("Connect thread " + this.g0 + " session");
                                    boolean z2 = this.c0;
                                    if (z2) {
                                        this.I.setDaemon(z2);
                                    }
                                    this.I.start();
                                    P();
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        Util.g(this.k0);
                        this.k0 = null;
                    } catch (Exception e6) {
                        throw new JSchException(e6.toString(), e6);
                    }
                } catch (NumberFormatException e7) {
                    throw new JSchException("MaxAuthTries: " + w("MaxAuthTries"), e7);
                }
            } catch (Exception e8) {
                this.n0 = false;
                try {
                    if (this.z) {
                        String string = e8.toString();
                        this.P.c();
                        this.O.a(string.length() + 15 + v());
                        this.O.s((byte) 1);
                        this.O.v(3);
                        this.O.y(Util.v(string));
                        this.O.y(Util.v("en"));
                        m0(this.P);
                    }
                } catch (Exception unused3) {
                }
                try {
                    s();
                } catch (Exception unused4) {
                }
                this.z = false;
                if (e8 instanceof RuntimeException) {
                    throw ((RuntimeException) e8);
                }
                if (e8 instanceof JSchException) {
                    throw ((JSchException) e8);
                }
                throw new JSchException("Session.connect: " + e8, e8);
            }
        } catch (Throwable th2) {
            Util.g(this.k0);
            this.k0 = null;
            throw th2;
        }
    }

    public void s() {
        if (this.z) {
            if (A().isEnabled(1)) {
                A().a(1, "Disconnecting from " + this.g0 + " port " + this.i0);
            }
            Channel.g(this);
            this.z = false;
            PortWatcher.c(this);
            ChannelForwardedTCPIP.H(this);
            ChannelX11.J(this);
            synchronized (this.J) {
                try {
                    if (this.I != null) {
                        Thread.yield();
                        this.I.interrupt();
                        this.I = null;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.u0 = null;
            try {
                IO io = this.w;
                if (io != null) {
                    InputStream inputStream = io.a;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    OutputStream outputStream = this.w.b;
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    OutputStream outputStream2 = this.w.c;
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                }
                Proxy proxy = this.S;
                if (proxy == null) {
                    Socket socket = this.x;
                    if (socket != null) {
                        socket.close();
                    }
                } else {
                    synchronized (proxy) {
                        this.S.close();
                    }
                    this.S = null;
                }
            } catch (Exception unused) {
            }
            this.w = null;
            this.x = null;
            this.l = 0;
            this.m = 0;
            this.D = true;
            this.E = false;
            this.A = false;
            this.a0 = null;
            this.l0.m(this);
        }
    }

    void t(Packet packet) throws Exception {
        MAC mac;
        Compression compression = this.u;
        if (compression != null) {
            int[] iArr = this.r0;
            Buffer buffer = packet.a;
            iArr[0] = buffer.c;
            buffer.b = compression.b(buffer.b, 5, iArr);
            packet.a.c = this.r0[0];
        }
        Cipher cipher = this.p;
        int i = cipher != null ? this.t0 : 8;
        boolean z = cipher != null && cipher.k();
        Cipher cipher2 = this.p;
        boolean z2 = cipher2 != null && cipher2.n();
        boolean z3 = (z || z2 || this.p == null || (mac = this.r) == null || !mac.f()) ? false : true;
        packet.b(i, (z || z2 || z3) ? false : true);
        Buffer buffer2 = packet.a;
        byte[] bArr = buffer2.b;
        if (z) {
            this.p.e(this.m);
            this.p.g(bArr, 0, 4, bArr, 0);
            this.p.i(bArr, 0, packet.a.c, bArr, 0);
            packet.a.E(this.p.h());
            return;
        }
        if (z2) {
            this.p.f(bArr, 0, 4);
            this.p.i(bArr, 4, packet.a.c - 4, bArr, 4);
            packet.a.E(this.p.h());
            return;
        }
        if (z3) {
            this.p.g(bArr, 4, buffer2.c - 4, bArr, 4);
            this.r.e(this.m);
            MAC mac2 = this.r;
            Buffer buffer3 = packet.a;
            mac2.b(buffer3.b, 0, buffer3.c);
            MAC mac3 = this.r;
            Buffer buffer4 = packet.a;
            mac3.c(buffer4.b, buffer4.c);
            packet.a.E(this.r.d());
            return;
        }
        MAC mac4 = this.r;
        if (mac4 != null) {
            mac4.e(this.m);
            MAC mac5 = this.r;
            Buffer buffer5 = packet.a;
            mac5.b(buffer5.b, 0, buffer5.c);
            MAC mac6 = this.r;
            Buffer buffer6 = packet.a;
            mac6.c(buffer6.b, buffer6.c);
        }
        Cipher cipher3 = this.p;
        if (cipher3 != null) {
            cipher3.g(bArr, 0, packet.a.c, bArr, 0);
        }
        MAC mac7 = this.r;
        if (mac7 != null) {
            packet.a.E(mac7.d());
        }
    }

    int v() {
        Cipher cipher = this.p;
        MAC mac = this.r;
        int iD = 20;
        if (cipher == null || !(cipher.k() || cipher.n())) {
            if (mac != null && mac.d() > 20) {
                iD = mac.d();
            }
        } else if (cipher.h() > 20) {
            iD = cipher.h();
        }
        return 64 + iD;
    }

    public String w(String str) {
        if (str.equals("PubkeyAcceptedKeyTypes")) {
            str = "PubkeyAcceptedAlgorithms";
        }
        Hashtable<String, String> hashtable = this.R;
        if (hashtable != null) {
            String str2 = hashtable.get(str);
            if (str2 instanceof String) {
                return str2;
            }
        }
        String strF = JSch.f(str);
        if (s.a(strF)) {
            return strF;
        }
        return null;
    }

    public String x() {
        return this.g0;
    }

    public HostKeyRepository y() {
        HostKeyRepository hostKeyRepository = this.Z;
        return hostKeyRepository == null ? this.l0.h() : hostKeyRepository;
    }

    IdentityRepository z() {
        IdentityRepository identityRepository = this.Y;
        return identityRepository == null ? this.l0.i() : identityRepository;
    }
}
