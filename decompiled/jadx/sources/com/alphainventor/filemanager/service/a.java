package com.alphainventor.filemanager.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import ax.U2.f;
import ax.U2.g;
import ax.p006a3.Q;
import ax.p065de.c;
import ax.p070e3.C1957a;
import ax.p070e3.C1961e;
import ax.p070e3.C1966j;
import ax.p070e3.C1967k;
import ax.p070e3.t;
import ax.p086f3.B;
import ax.p086f3.C2011q;
import ax.p086f3.C2017x;
import ax.p086f3.K;
import ax.p086f3.d0;
import ax.p097fe.e;
import ax.p097fe.i;
import ax.p097fe.j;
import ax.p097fe.k;
import ax.p145ie.d;
import ax.p310t3.h;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.file.AbstractC6230e;
import com.alphainventor.filemanager.file.AbstractC6238m;
import com.alphainventor.filemanager.file.AbstractC6239n;
import com.alphainventor.filemanager.file.C6240o;
import com.alphainventor.filemanager.file.C6248x;
import com.alphainventor.filemanager.file.C6249y;
import j$.util.DesugarTimeZone;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"DefaultLocale", "SimpleDateFormat"})
public class a {
    private static final Logger E = g.a(a.class);
    private static final String[] F = {"CWD", "CDUP", "SMNT", "PORT", "PASV", "MODE", "TYPE", "STRU", "ALL0", "REST", "STOR", "STOU", "RETR", "LIST", "NLST", "APPE", "RNFR", "RNT0", "DELE", "RMD", "MKD", "STAT", "SITE", "MLST", "MLST", "PBSZ", "PROT"};
    private static final Map<String, SSLContext> G = new ConcurrentHashMap();
    private long A;
    private boolean B;
    private boolean C;
    private SSLContext D;
    private Context a;
    private Socket b;
    private BufferedOutputStream c;
    private AbstractC6239n d;
    private AbstractC6239n e;
    private String f;
    private ServerSocket h;
    private InetAddress i;
    private int j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private AbstractC6239n p;
    private SimpleDateFormat q;
    private SimpleDateFormat r;
    private SimpleDateFormat s;
    private SimpleDateFormat t;
    private Map<K, C6240o> u;
    private Map<K, AbstractC6239n> v;
    private List<K> w;
    private Map<K, String> x;
    private boolean y;
    private boolean z;
    private boolean g = false;
    private HashSet<String> o = new HashSet<>();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.service.a$a, reason: collision with other inner class name */
    static class C0544a {
        String a;
        boolean b;
        boolean c;

        C0544a() {
        }

        void a() {
            this.a = null;
            this.b = false;
            this.c = false;
        }
    }

    public a(Context context, Socket socket, BufferedOutputStream bufferedOutputStream, boolean z, String str) throws IOException {
        Locale locale = Locale.US;
        this.q = new SimpleDateFormat(" MMM dd HH:mm ", locale);
        this.r = new SimpleDateFormat(" MMM dd  yyyy ", locale);
        this.s = new SimpleDateFormat("yyyyMMddHHmmss.SSS", locale);
        this.u = new ConcurrentHashMap();
        this.v = new ConcurrentHashMap();
        this.w = new ArrayList();
        this.x = new ConcurrentHashMap();
        this.a = context;
        this.b = socket;
        this.z = z;
        this.c = bufferedOutputStream;
        this.s.setTimeZone(DesugarTimeZone.getTimeZone("GMT"));
        this.m = str;
        if (TextUtils.isEmpty(str)) {
            this.k = true;
        }
    }

    private static String A(String str) {
        if (TextUtils.isEmpty(str)) {
            return "127.0.0.1";
        }
        int iIndexOf = str.indexOf(37);
        return iIndexOf >= 0 ? str.substring(0, iIndexOf) : str;
    }

    private Socket B() {
        ServerSocket serverSocket = this.h;
        if (serverSocket != null) {
            try {
                Socket socketAccept = serverSocket.accept();
                d();
                return E(socketAccept);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Socket socket = new Socket(this.i, this.j);
            socket.setSoTimeout(30000);
            return E(socket);
        } catch (Exception e2) {
            e2.printStackTrace();
            d();
            return null;
        }
    }

    private void D(String str, C0544a c0544a) {
        String upperCase = str.toUpperCase(Locale.US);
        if (!"TLS".equals(upperCase) && !"SSL".equals(upperCase)) {
            O("504 Unsupported AUTH type\r\n");
            return;
        }
        if (this.B) {
            O("503 Control connection is already protected\r\n");
            return;
        }
        SSLContext sSLContextS = s(this.a, r());
        this.D = sSLContextS;
        if (sSLContextS == null) {
            O("431 TLS is not available\r\n");
            return;
        }
        O("234 AUTH TLS OK\r\n");
        try {
            SSLSocketFactory socketFactory = this.D.getSocketFactory();
            Socket socket = this.b;
            SSLSocket sSLSocket = (SSLSocket) socketFactory.createSocket(socket, socket.getInetAddress().getHostAddress(), this.b.getPort(), false);
            sSLSocket.setUseClientMode(false);
            sSLSocket.setNeedClientAuth(false);
            sSLSocket.startHandshake();
            this.b = sSLSocket;
            this.c = new BufferedOutputStream(sSLSocket.getOutputStream());
            this.B = true;
            c0544a.c = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Socket E(Socket socket) throws IOException {
        if (!this.C) {
            return socket;
        }
        SSLContext sSLContext = this.D;
        if (sSLContext == null) {
            f(socket);
            return null;
        }
        SSLSocket sSLSocket = (SSLSocket) sSLContext.getSocketFactory().createSocket(socket, socket.getInetAddress().getHostAddress(), socket.getPort(), false);
        sSLSocket.setUseClientMode(false);
        sSLSocket.setNeedClientAuth(false);
        return sSLSocket;
    }

    /* JADX WARN: Code duplicated, block: B:115:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x00f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x00ec A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r3v15 */
    private void F(AbstractC6239n abstractC6239n, boolean z, long j) throws Throwable {
        OutputStream outputStreamC;
        Socket socketB = B();
        if (socketB == null) {
            O("425 Error opening data socket\r\n");
            f(socketB);
            return;
        }
        if (j > 0 && abstractC6239n.p() < j) {
            O("451 File IO error\r\n");
            return;
        }
        O(String.format(Locale.US, "150 Opening %s mode data connection for writing\r\n", this.g ? "BINARY" : "ASCII"));
        if (!N(socketB)) {
            O("426 Data socket or network error\r\n");
            f(socketB);
            return;
        }
        InputStream inputStream = null;
        ?? r11 = 0;
        inputStream = null;
        inputStream = null;
        try {
            try {
                AbstractC6230e abstractC6230e = (AbstractC6230e) p(abstractC6239n).u();
                InputStream inputStream2 = socketB.getInputStream();
                try {
                    if (j > 0) {
                        abstractC6230e.T(abstractC6239n.E(), j);
                        outputStreamC = abstractC6230e.c(abstractC6239n.E(), true);
                    } else {
                        outputStreamC = abstractC6230e.c(abstractC6239n.E(), z);
                    }
                    OutputStream outputStream = outputStreamC;
                    if (outputStream != null) {
                        byte[] bArr = new byte[65536];
                        while (true) {
                            int i = inputStream2.read(bArr);
                            if (i < 0) {
                                break;
                            } else if (i == 0) {
                                try {
                                    Thread.sleep(5L);
                                } catch (InterruptedException unused) {
                                }
                            } else {
                                outputStream.write(bArr, 0, i);
                            }
                        }
                        outputStream.flush();
                        O("226 Data transmission for writing succeeded\r\n");
                    } else {
                        O("451 Permission denied\r\n");
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException unused2) {
                        }
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (FileNotFoundException unused3) {
                    inputStream = inputStream2;
                    try {
                        O("550 No such file or directory.\r\n");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused4) {
                            }
                        }
                        if (inputStream == 0) {
                        } else {
                            inputStream.close();
                        }
                    } catch (Throwable th) {
                        r11 = inputStream;
                        th = th;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused5) {
                            }
                        }
                        if (r11 == 0) {
                            throw th;
                        }
                        try {
                            r11.close();
                            throw th;
                        } catch (IOException unused6) {
                            throw th;
                        }
                    }
                } catch (IOException e) {
                    e = e;
                    inputStream = inputStream2;
                    try {
                        e.printStackTrace();
                        O("451 File IO error\r\n");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused7) {
                            }
                        }
                        if (inputStream == 0) {
                        } else {
                            inputStream.close();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (r11 == 0) {
                            throw th;
                        }
                        r11.close();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    r11 = 0;
                    inputStream = inputStream2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (r11 == 0) {
                        throw th;
                    }
                    r11.close();
                    throw th;
                }
            } catch (IOException unused8) {
            }
        } catch (FileNotFoundException unused9) {
        } catch (IOException e2) {
            e = e2;
        } catch (Throwable th4) {
            th = th4;
            r11 = 0;
        }
    }

    private static void G(Context context, String str, KeyStore keyStore) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            keyStore.store(byteArrayOutputStream, "filemanager".toCharArray());
            h.h(context, str, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean H(Socket socket, byte[] bArr) {
        return I(socket, bArr, 0, bArr.length);
    }

    private boolean I(Socket socket, byte[] bArr, int i, int i2) {
        try {
            socket.getOutputStream().write(bArr, i, i2);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0051 -> B:35:0x0048). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0057 -> B:35:0x0048). Please report as a decompilation issue!!! */
    private void J(AbstractC6239n abstractC6239n, long j) {
        C6240o c6240oP = p(abstractC6239n);
        Socket socketB = B();
        if (socketB == null) {
            O("425 Error opening data socket\r\n");
            f(socketB);
            return;
        }
        O("150 Sending file\r\n");
        if (!N(socketB)) {
            O("426 Data socket or network error\r\n");
            f(socketB);
            return;
        }
        InputStream inputStreamH1 = null;
        try {
            try {
                try {
                    try {
                        inputStreamH1 = c6240oP.h1(abstractC6239n, j);
                        byte[] bArr = new byte[65536];
                        while (true) {
                            int i = inputStreamH1.read(bArr);
                            if (i < 0) {
                                O("226 File transmission succeeded\r\n");
                                break;
                            } else if (!I(socketB, bArr, 0, i)) {
                                O("426 Data socket or network error\r\n");
                                break;
                            }
                        }
                    } catch (Throwable th) {
                        if (inputStreamH1 != null) {
                            try {
                                inputStreamH1.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (C1966j | FileNotFoundException unused2) {
                    O("550 Operation on invalid file\r\n");
                    if (inputStreamH1 != null) {
                        break;
                    }
                    f(socketB);
                }
            } catch (IOException unused3) {
                O("426 Data socket or network error\r\n");
                if (inputStreamH1 != null) {
                }
                f(socketB);
            }
            inputStreamH1.close();
        } catch (IOException unused4) {
        }
        f(socketB);
    }

    private void K(String str, String str2) {
        Socket socketB = B();
        if (socketB == null) {
            O("425 Error opening data socket\r\n");
            f(socketB);
            return;
        }
        O(String.format(Locale.US, "150 Opening %s mode data connection for %s\r\n", this.g ? "BINARY" : "ASCII", str));
        if (!N(socketB)) {
            O("426 Data socket or network error\r\n");
            f(socketB);
        } else {
            if (H(socketB, str2.getBytes())) {
                O("226 Data transmission succeeded\r\n");
            } else {
                O("426 Data socket or network error\r\n");
            }
            f(socketB);
        }
    }

    private int L() {
        d();
        try {
            ServerSocket serverSocket = new ServerSocket(0, 5);
            this.h = serverSocket;
            return serverSocket.getLocalPort();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void M(AbstractC6239n abstractC6239n, String str) {
        this.e = abstractC6239n;
        this.f = str;
    }

    private boolean N(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            return true;
        }
        try {
            ((SSLSocket) socket).startHandshake();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void a(StringBuilder sb, boolean z, AbstractC6239n abstractC6239n, String str) {
        if (z) {
            sb.append(" ");
        }
        if (abstractC6239n.isDirectory()) {
            sb.append("Type=dir;Modify=");
            sb.append(this.s.format(new Date(abstractC6239n.q())));
            sb.append(";Perm=el; ");
            if (str == null) {
                sb.append(abstractC6239n.B());
            } else {
                sb.append(str);
            }
        } else {
            sb.append("Type=file;Size=");
            sb.append(abstractC6239n.p());
            sb.append(";Modify=");
            sb.append(this.s.format(new Date(abstractC6239n.q())));
            sb.append(";Perm=");
            sb.append("r");
            if (abstractC6239n.Q() == f.t0 || abstractC6239n.k()) {
                sb.append("w");
            }
            sb.append("; ");
            if (str == null) {
                sb.append(abstractC6239n.B());
            } else {
                sb.append(str);
            }
        }
        sb.append("\r\n");
    }

    private void b(K k, String str) {
        if (!f.X(k.d())) {
            C2461b.g("Not local file location in FTP!");
            return;
        }
        C6240o c6240oE = C2017x.e(k);
        if (!c6240oE.a()) {
            c6240oE.h(null);
            if (!c6240oE.a()) {
                return;
            }
        }
        try {
            c6240oE.V();
            AbstractC6239n abstractC6239nA1 = c6240oE.a1(k.e());
            this.u.put(k, c6240oE);
            this.v.put(k, abstractC6239nA1);
            this.x.put(k, str);
            this.w.add(k);
        } catch (C1966j unused) {
            c6240oE.S(false);
        }
    }

    private boolean c(String str, String str2) {
        if (TextUtils.isEmpty(this.n) || this.n.equals(str)) {
            return TextUtils.isEmpty(this.m) || this.m.equals(str2);
        }
        return false;
    }

    private void d() {
        ServerSocket serverSocket = this.h;
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.h = null;
        }
    }

    private void f(Socket socket) {
        if (socket == null || socket.isClosed()) {
            return;
        }
        try {
            socket.getOutputStream().close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String g(AbstractC6239n abstractC6239n, boolean z, boolean z2) {
        if (abstractC6239n.B().contains("*") || abstractC6239n.B().contains(File.separator)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strQ = q(abstractC6239n);
        if (z) {
            a(sb, false, abstractC6239n, strQ);
        } else {
            if (!z2) {
                if (abstractC6239n.isDirectory()) {
                    sb.append("drwxr-xr-x 1 owner group");
                } else {
                    sb.append("-rw-r--r-- 1 owner group");
                }
                sb.append(String.format(Locale.US, "%13d", Long.valueOf(abstractC6239n.p())));
                sb.append((System.currentTimeMillis() - abstractC6239n.q() > 15552000000L ? this.r : this.q).format(new Date(abstractC6239n.q())));
            }
            sb.append(strQ);
            sb.append("\r\n");
        }
        return sb.toString();
    }

    private static X509Certificate h(String str, KeyPair keyPair) throws Exception {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Date date = new Date(jCurrentTimeMillis - 86400000);
        Date date2 = new Date(jCurrentTimeMillis + 315360000000L);
        c cVar = new c("CN=File Manager Plus FTPS Server");
        d dVar = new d(cVar, new BigInteger(160, new SecureRandom()), date, date2, cVar, keyPair.getPublic());
        dVar.a(e.p0, true, new ax.p097fe.b(false));
        dVar.a(e.l0, true, new k(160));
        dVar.a(e.D0, false, new ax.p097fe.d(j.Z));
        dVar.a(e.n0, false, k(str));
        return new ax.p145ie.c().a(dVar.b(new ax.Ae.a("SHA256withRSA").b(keyPair.getPrivate())));
    }

    private static KeyStore i(String str) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
        X509Certificate x509CertificateH = h(str, keyPairGenerateKeyPair);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setKeyEntry("ftp_server", keyPairGenerateKeyPair.getPrivate(), "filemanager".toCharArray(), new Certificate[]{x509CertificateH});
        return keyStore;
    }

    private static SSLContext j(Context context, String str) throws Exception {
        KeyStore keyStoreX = x(context, str);
        if (keyStoreX == null) {
            keyStoreX = i(str);
            G(context, str, keyStoreX);
        }
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStoreX, "filemanager".toCharArray());
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());
        return sSLContext;
    }

    private static i k(String str) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && !"127.0.0.1".equals(str)) {
            arrayList.add(new ax.p097fe.h(7, str));
        }
        return new i((ax.p097fe.h[]) arrayList.toArray(new ax.p097fe.h[arrayList.size()]));
    }

    private String l(String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Paramater is empty");
        }
        if (v(str)) {
            return "/";
        }
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            str = str.substring(0, str.length() - 1);
        }
        return str.startsWith(str2) ? str : d0.Q(this.f, str);
    }

    private AbstractC6239n o(String str) throws C1966j {
        K key;
        String strReplaceFirst;
        if (v(str)) {
            return this.d;
        }
        Iterator<Map.Entry<K, String>> it = this.x.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                key = null;
                strReplaceFirst = null;
                break;
            }
            Map.Entry<K, String> next = it.next();
            key = next.getKey();
            String value = next.getValue();
            AbstractC6239n abstractC6239n = this.v.get(key);
            if (abstractC6239n == null) {
                str = ":" + ax.p038c3.j.F().U() + ":" + ax.p038c3.j.F().u0();
                C2461b.g(str);
            } else if (d0.I(value, str) || d0.G(value, str)) {
                String strE = abstractC6239n.E();
                strReplaceFirst = "/";
                if (!"/".equals(strE)) {
                    strReplaceFirst = str.replaceFirst(value, strE);
                    break;
                }
                String strReplaceFirst2 = str.replaceFirst(value, HttpUrl.FRAGMENT_ENCODE_SET);
                if (!TextUtils.isEmpty(strReplaceFirst2)) {
                    strReplaceFirst = strReplaceFirst2;
                    break;
                }
                break;
            }
        }
        if (strReplaceFirst == null) {
            throw new t("File not exist : " + str);
        }
        C6240o c6240o = this.u.get(key);
        if (c6240o != null) {
            return c6240o.a1(strReplaceFirst);
        }
        throw new C1966j("No file operator available : " + key.k());
    }

    private C6240o p(AbstractC6239n abstractC6239n) {
        return this.u.get(abstractC6239n.S());
    }

    private String q(AbstractC6239n abstractC6239n) {
        if (!d0.E(abstractC6239n)) {
            return abstractC6239n.B();
        }
        String str = this.x.get(abstractC6239n.S());
        if (str != null) {
            return d0.h(str);
        }
        C2461b.g("root path is not available");
        return abstractC6239n.B();
    }

    private String r() {
        InetAddress localAddress = this.b.getLocalAddress();
        return localAddress != null ? A(localAddress.getHostAddress()) : "127.0.0.1";
    }

    private static SSLContext s(Context context, String str) {
        SSLContext sSLContextJ;
        String strA = A(str);
        Map<String, SSLContext> map = G;
        SSLContext sSLContext = map.get(strA);
        if (sSLContext != null) {
            return sSLContext;
        }
        synchronized (a.class) {
            sSLContextJ = map.get(strA);
            if (sSLContextJ == null) {
                try {
                    sSLContextJ = j(context, strA);
                    map.put(strA, sSLContextJ);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return sSLContextJ;
    }

    private boolean u(AbstractC6239n abstractC6239n) {
        return abstractC6239n.isDirectory() && (abstractC6239n.S() == K.h ? true : abstractC6239n.h()) && w(abstractC6239n);
    }

    private boolean v(String str) {
        return "/".equals(str);
    }

    private boolean w(AbstractC6239n abstractC6239n) {
        return abstractC6239n.E().startsWith(abstractC6239n.S().e()) || "/".equals(abstractC6239n.E());
    }

    private static KeyStore x(Context context, String str) {
        String strC = h.c(context, str);
        if (TextUtils.isEmpty(strC)) {
            return null;
        }
        try {
            byte[] bArrDecode = Base64.decode(strC, 2);
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new ByteArrayInputStream(bArrDecode), "filemanager".toCharArray());
            if (keyStore.containsAlias("ftp_server")) {
                return keyStore;
            }
            h.e(context, str);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            h.e(context, str);
            return null;
        }
    }

    private String y(String str) {
        return String.format(Locale.US, "550 %s: No such file or directory.\r\n", str);
    }

    private boolean z(String str) {
        return this.o.contains(str);
    }

    /* JADX WARN: Code duplicated, block: B:545:0x0a78 A[Catch: all -> 0x0075, TRY_ENTER, TryCatch #41 {all -> 0x0075, blocks: (B:14:0x0067, B:16:0x006d, B:24:0x0083, B:29:0x0094, B:34:0x00a4, B:36:0x00a8, B:37:0x00ae, B:42:0x00bc, B:44:0x00c0, B:45:0x00c4, B:47:0x00d2, B:48:0x00db, B:50:0x00e7, B:51:0x00f0, B:56:0x0100, B:58:0x010c, B:61:0x0119, B:64:0x0121, B:65:0x0126, B:66:0x0140, B:71:0x0151, B:73:0x0155, B:74:0x015a, B:79:0x016d, B:80:0x0174, B:82:0x017c, B:83:0x0184, B:87:0x0194, B:91:0x01a3, B:95:0x01c1, B:97:0x01c9, B:100:0x01d3, B:102:0x01db, B:105:0x01e5, B:106:0x01ec, B:107:0x01f5, B:111:0x0206, B:113:0x020e, B:116:0x0215, B:117:0x0247, B:121:0x0256, B:124:0x0260, B:127:0x0265, B:130:0x026b, B:132:0x0275, B:133:0x0277, B:136:0x0281, B:137:0x0293, B:140:0x02a4, B:141:0x02ae, B:142:0x02b6, B:147:0x02c7, B:149:0x02d1, B:151:0x02d7, B:153:0x02e2, B:156:0x02e9, B:157:0x0305, B:165:0x0327, B:167:0x0331, B:169:0x0337, B:171:0x0342, B:174:0x0349, B:175:0x036c, B:184:0x038d, B:186:0x0394, B:187:0x0399, B:188:0x03a9, B:190:0x03b3, B:192:0x03b9, B:194:0x03c4, B:195:0x03c9, B:197:0x03d3, B:199:0x03d7, B:200:0x03eb, B:202:0x03f9, B:204:0x0405, B:205:0x041d, B:206:0x0422, B:207:0x042a, B:209:0x042f, B:210:0x0434, B:211:0x043d, B:215:0x044a, B:217:0x0458, B:218:0x045f, B:220:0x0465, B:221:0x046c, B:229:0x048f, B:231:0x049a, B:233:0x04bf, B:235:0x04c9, B:236:0x04d2, B:232:0x04a2, B:245:0x04ff, B:247:0x0507, B:250:0x050f, B:252:0x0517, B:253:0x051e, B:255:0x0526, B:257:0x052e, B:258:0x0533, B:260:0x053f, B:262:0x054b, B:264:0x0557, B:267:0x055e, B:268:0x0566, B:270:0x056b, B:271:0x0570, B:272:0x0579, B:274:0x0581, B:275:0x0588, B:278:0x0592, B:280:0x059c, B:283:0x05a7, B:285:0x05b5, B:286:0x05bd, B:288:0x05c3, B:291:0x05ca, B:293:0x05d7, B:294:0x05dd, B:295:0x05e1, B:301:0x05fc, B:303:0x0604, B:305:0x060e, B:308:0x0619, B:310:0x0627, B:311:0x062b, B:313:0x0631, B:314:0x0637, B:316:0x063e, B:317:0x0644, B:318:0x064e, B:323:0x0665, B:325:0x066d, B:327:0x0677, B:330:0x0682, B:332:0x0690, B:333:0x0699, B:335:0x069f, B:338:0x06a6, B:340:0x06b4, B:341:0x06bb, B:342:0x06c2, B:343:0x06c7, B:348:0x06df, B:350:0x06e7, B:352:0x06f1, B:355:0x06fc, B:357:0x0706, B:358:0x070b, B:360:0x0711, B:361:0x071a, B:362:0x0723, B:367:0x073b, B:369:0x0743, B:371:0x0749, B:373:0x074f, B:376:0x075b, B:378:0x075f, B:379:0x0766, B:381:0x0782, B:382:0x0787, B:384:0x078d, B:385:0x0792, B:387:0x0798, B:388:0x079f, B:391:0x07b1, B:404:0x080f, B:406:0x084d, B:394:0x07b8, B:396:0x07c2, B:398:0x07c8, B:401:0x07d8, B:403:0x080c, B:405:0x081a, B:407:0x0852, B:412:0x086a, B:414:0x0872, B:416:0x087c, B:419:0x0887, B:421:0x0891, B:422:0x089a, B:424:0x08a0, B:426:0x08a6, B:429:0x08ad, B:430:0x08b4, B:431:0x08b9, B:436:0x08d1, B:438:0x08d9, B:449:0x0901, B:451:0x0909, B:457:0x091b, B:459:0x0925, B:462:0x0930, B:464:0x093a, B:467:0x0941, B:470:0x0953, B:472:0x095d, B:473:0x0962, B:475:0x0968, B:478:0x0970, B:479:0x0973, B:481:0x097a, B:482:0x0981, B:483:0x0986, B:485:0x098b, B:486:0x0990, B:487:0x0999, B:454:0x0912, B:433:0x08be, B:434:0x08c3, B:435:0x08cc, B:409:0x0857, B:410:0x085c, B:411:0x0865, B:364:0x0728, B:365:0x072d, B:366:0x0736, B:345:0x06cc, B:346:0x06d1, B:347:0x06da, B:320:0x0652, B:321:0x0657, B:322:0x0660, B:298:0x05e9, B:299:0x05ee, B:300:0x05f7, B:492:0x09a7, B:494:0x09ad, B:497:0x09b1, B:502:0x09c0, B:507:0x09d0, B:514:0x09f0, B:519:0x0a0a, B:521:0x0a10, B:522:0x0a15, B:523:0x0a1b, B:525:0x0a21, B:527:0x0a31, B:545:0x0a78, B:547:0x0a7e, B:562:0x0abc, B:572:0x0af0, B:576:0x0af9, B:577:0x0afc, B:579:0x0aff, B:580:0x0b06, B:534:0x0a4c, B:536:0x0a54, B:583:0x0b12, B:587:0x0b1d, B:589:0x0b23, B:590:0x0b27, B:607:0x0b56, B:609:0x0b62, B:238:0x04e4, B:239:0x04e9, B:240:0x04f2, B:223:0x0476, B:224:0x047b, B:225:0x0484, B:177:0x0371, B:178:0x0376, B:179:0x037b, B:159:0x030c, B:160:0x0311, B:161:0x0316, B:550:0x0a8c, B:558:0x0aa0, B:561:0x0ab9, B:574:0x0af5, B:565:0x0ac4, B:566:0x0acc, B:568:0x0ad2, B:570:0x0ade, B:571:0x0ae8), top: B:641:0x0067, inners: #4, #22, #26, #32, #39, #42, #50, #53, #55, #58, #59, #60, #65, #66, #67, #68, #67, #66, #65, #64, #62, #61, #60, #59, #57, #55, #54, #53 }] */
    /* JADX WARN: Code duplicated, block: B:547:0x0a7e A[Catch: all -> 0x0075, TRY_LEAVE, TryCatch #41 {all -> 0x0075, blocks: (B:14:0x0067, B:16:0x006d, B:24:0x0083, B:29:0x0094, B:34:0x00a4, B:36:0x00a8, B:37:0x00ae, B:42:0x00bc, B:44:0x00c0, B:45:0x00c4, B:47:0x00d2, B:48:0x00db, B:50:0x00e7, B:51:0x00f0, B:56:0x0100, B:58:0x010c, B:61:0x0119, B:64:0x0121, B:65:0x0126, B:66:0x0140, B:71:0x0151, B:73:0x0155, B:74:0x015a, B:79:0x016d, B:80:0x0174, B:82:0x017c, B:83:0x0184, B:87:0x0194, B:91:0x01a3, B:95:0x01c1, B:97:0x01c9, B:100:0x01d3, B:102:0x01db, B:105:0x01e5, B:106:0x01ec, B:107:0x01f5, B:111:0x0206, B:113:0x020e, B:116:0x0215, B:117:0x0247, B:121:0x0256, B:124:0x0260, B:127:0x0265, B:130:0x026b, B:132:0x0275, B:133:0x0277, B:136:0x0281, B:137:0x0293, B:140:0x02a4, B:141:0x02ae, B:142:0x02b6, B:147:0x02c7, B:149:0x02d1, B:151:0x02d7, B:153:0x02e2, B:156:0x02e9, B:157:0x0305, B:165:0x0327, B:167:0x0331, B:169:0x0337, B:171:0x0342, B:174:0x0349, B:175:0x036c, B:184:0x038d, B:186:0x0394, B:187:0x0399, B:188:0x03a9, B:190:0x03b3, B:192:0x03b9, B:194:0x03c4, B:195:0x03c9, B:197:0x03d3, B:199:0x03d7, B:200:0x03eb, B:202:0x03f9, B:204:0x0405, B:205:0x041d, B:206:0x0422, B:207:0x042a, B:209:0x042f, B:210:0x0434, B:211:0x043d, B:215:0x044a, B:217:0x0458, B:218:0x045f, B:220:0x0465, B:221:0x046c, B:229:0x048f, B:231:0x049a, B:233:0x04bf, B:235:0x04c9, B:236:0x04d2, B:232:0x04a2, B:245:0x04ff, B:247:0x0507, B:250:0x050f, B:252:0x0517, B:253:0x051e, B:255:0x0526, B:257:0x052e, B:258:0x0533, B:260:0x053f, B:262:0x054b, B:264:0x0557, B:267:0x055e, B:268:0x0566, B:270:0x056b, B:271:0x0570, B:272:0x0579, B:274:0x0581, B:275:0x0588, B:278:0x0592, B:280:0x059c, B:283:0x05a7, B:285:0x05b5, B:286:0x05bd, B:288:0x05c3, B:291:0x05ca, B:293:0x05d7, B:294:0x05dd, B:295:0x05e1, B:301:0x05fc, B:303:0x0604, B:305:0x060e, B:308:0x0619, B:310:0x0627, B:311:0x062b, B:313:0x0631, B:314:0x0637, B:316:0x063e, B:317:0x0644, B:318:0x064e, B:323:0x0665, B:325:0x066d, B:327:0x0677, B:330:0x0682, B:332:0x0690, B:333:0x0699, B:335:0x069f, B:338:0x06a6, B:340:0x06b4, B:341:0x06bb, B:342:0x06c2, B:343:0x06c7, B:348:0x06df, B:350:0x06e7, B:352:0x06f1, B:355:0x06fc, B:357:0x0706, B:358:0x070b, B:360:0x0711, B:361:0x071a, B:362:0x0723, B:367:0x073b, B:369:0x0743, B:371:0x0749, B:373:0x074f, B:376:0x075b, B:378:0x075f, B:379:0x0766, B:381:0x0782, B:382:0x0787, B:384:0x078d, B:385:0x0792, B:387:0x0798, B:388:0x079f, B:391:0x07b1, B:404:0x080f, B:406:0x084d, B:394:0x07b8, B:396:0x07c2, B:398:0x07c8, B:401:0x07d8, B:403:0x080c, B:405:0x081a, B:407:0x0852, B:412:0x086a, B:414:0x0872, B:416:0x087c, B:419:0x0887, B:421:0x0891, B:422:0x089a, B:424:0x08a0, B:426:0x08a6, B:429:0x08ad, B:430:0x08b4, B:431:0x08b9, B:436:0x08d1, B:438:0x08d9, B:449:0x0901, B:451:0x0909, B:457:0x091b, B:459:0x0925, B:462:0x0930, B:464:0x093a, B:467:0x0941, B:470:0x0953, B:472:0x095d, B:473:0x0962, B:475:0x0968, B:478:0x0970, B:479:0x0973, B:481:0x097a, B:482:0x0981, B:483:0x0986, B:485:0x098b, B:486:0x0990, B:487:0x0999, B:454:0x0912, B:433:0x08be, B:434:0x08c3, B:435:0x08cc, B:409:0x0857, B:410:0x085c, B:411:0x0865, B:364:0x0728, B:365:0x072d, B:366:0x0736, B:345:0x06cc, B:346:0x06d1, B:347:0x06da, B:320:0x0652, B:321:0x0657, B:322:0x0660, B:298:0x05e9, B:299:0x05ee, B:300:0x05f7, B:492:0x09a7, B:494:0x09ad, B:497:0x09b1, B:502:0x09c0, B:507:0x09d0, B:514:0x09f0, B:519:0x0a0a, B:521:0x0a10, B:522:0x0a15, B:523:0x0a1b, B:525:0x0a21, B:527:0x0a31, B:545:0x0a78, B:547:0x0a7e, B:562:0x0abc, B:572:0x0af0, B:576:0x0af9, B:577:0x0afc, B:579:0x0aff, B:580:0x0b06, B:534:0x0a4c, B:536:0x0a54, B:583:0x0b12, B:587:0x0b1d, B:589:0x0b23, B:590:0x0b27, B:607:0x0b56, B:609:0x0b62, B:238:0x04e4, B:239:0x04e9, B:240:0x04f2, B:223:0x0476, B:224:0x047b, B:225:0x0484, B:177:0x0371, B:178:0x0376, B:179:0x037b, B:159:0x030c, B:160:0x0311, B:161:0x0316, B:550:0x0a8c, B:558:0x0aa0, B:561:0x0ab9, B:574:0x0af5, B:565:0x0ac4, B:566:0x0acc, B:568:0x0ad2, B:570:0x0ade, B:571:0x0ae8), top: B:641:0x0067, inners: #4, #22, #26, #32, #39, #42, #50, #53, #55, #58, #59, #60, #65, #66, #67, #68, #67, #66, #65, #64, #62, #61, #60, #59, #57, #55, #54, #53 }] */
    /* JADX WARN: Code duplicated, block: B:552:0x0a94  */
    /* JADX WARN: Code duplicated, block: B:557:0x0a9d  */
    /* JADX WARN: Code duplicated, block: B:560:0x0ab7  */
    /* JADX WARN: Code duplicated, block: B:564:0x0ac2  */
    /* JADX WARN: Code duplicated, block: B:568:0x0ad2 A[Catch: all -> 0x0a98, IOException -> 0x0af5, TryCatch #0 {IOException -> 0x0af5, blocks: (B:561:0x0ab9, B:565:0x0ac4, B:566:0x0acc, B:568:0x0ad2, B:570:0x0ade, B:571:0x0ae8), top: B:621:0x0ab5 }] */
    /* JADX WARN: Code duplicated, block: B:578:0x0afd A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:579:0x0aff A[Catch: all -> 0x0075, TryCatch #41 {all -> 0x0075, blocks: (B:14:0x0067, B:16:0x006d, B:24:0x0083, B:29:0x0094, B:34:0x00a4, B:36:0x00a8, B:37:0x00ae, B:42:0x00bc, B:44:0x00c0, B:45:0x00c4, B:47:0x00d2, B:48:0x00db, B:50:0x00e7, B:51:0x00f0, B:56:0x0100, B:58:0x010c, B:61:0x0119, B:64:0x0121, B:65:0x0126, B:66:0x0140, B:71:0x0151, B:73:0x0155, B:74:0x015a, B:79:0x016d, B:80:0x0174, B:82:0x017c, B:83:0x0184, B:87:0x0194, B:91:0x01a3, B:95:0x01c1, B:97:0x01c9, B:100:0x01d3, B:102:0x01db, B:105:0x01e5, B:106:0x01ec, B:107:0x01f5, B:111:0x0206, B:113:0x020e, B:116:0x0215, B:117:0x0247, B:121:0x0256, B:124:0x0260, B:127:0x0265, B:130:0x026b, B:132:0x0275, B:133:0x0277, B:136:0x0281, B:137:0x0293, B:140:0x02a4, B:141:0x02ae, B:142:0x02b6, B:147:0x02c7, B:149:0x02d1, B:151:0x02d7, B:153:0x02e2, B:156:0x02e9, B:157:0x0305, B:165:0x0327, B:167:0x0331, B:169:0x0337, B:171:0x0342, B:174:0x0349, B:175:0x036c, B:184:0x038d, B:186:0x0394, B:187:0x0399, B:188:0x03a9, B:190:0x03b3, B:192:0x03b9, B:194:0x03c4, B:195:0x03c9, B:197:0x03d3, B:199:0x03d7, B:200:0x03eb, B:202:0x03f9, B:204:0x0405, B:205:0x041d, B:206:0x0422, B:207:0x042a, B:209:0x042f, B:210:0x0434, B:211:0x043d, B:215:0x044a, B:217:0x0458, B:218:0x045f, B:220:0x0465, B:221:0x046c, B:229:0x048f, B:231:0x049a, B:233:0x04bf, B:235:0x04c9, B:236:0x04d2, B:232:0x04a2, B:245:0x04ff, B:247:0x0507, B:250:0x050f, B:252:0x0517, B:253:0x051e, B:255:0x0526, B:257:0x052e, B:258:0x0533, B:260:0x053f, B:262:0x054b, B:264:0x0557, B:267:0x055e, B:268:0x0566, B:270:0x056b, B:271:0x0570, B:272:0x0579, B:274:0x0581, B:275:0x0588, B:278:0x0592, B:280:0x059c, B:283:0x05a7, B:285:0x05b5, B:286:0x05bd, B:288:0x05c3, B:291:0x05ca, B:293:0x05d7, B:294:0x05dd, B:295:0x05e1, B:301:0x05fc, B:303:0x0604, B:305:0x060e, B:308:0x0619, B:310:0x0627, B:311:0x062b, B:313:0x0631, B:314:0x0637, B:316:0x063e, B:317:0x0644, B:318:0x064e, B:323:0x0665, B:325:0x066d, B:327:0x0677, B:330:0x0682, B:332:0x0690, B:333:0x0699, B:335:0x069f, B:338:0x06a6, B:340:0x06b4, B:341:0x06bb, B:342:0x06c2, B:343:0x06c7, B:348:0x06df, B:350:0x06e7, B:352:0x06f1, B:355:0x06fc, B:357:0x0706, B:358:0x070b, B:360:0x0711, B:361:0x071a, B:362:0x0723, B:367:0x073b, B:369:0x0743, B:371:0x0749, B:373:0x074f, B:376:0x075b, B:378:0x075f, B:379:0x0766, B:381:0x0782, B:382:0x0787, B:384:0x078d, B:385:0x0792, B:387:0x0798, B:388:0x079f, B:391:0x07b1, B:404:0x080f, B:406:0x084d, B:394:0x07b8, B:396:0x07c2, B:398:0x07c8, B:401:0x07d8, B:403:0x080c, B:405:0x081a, B:407:0x0852, B:412:0x086a, B:414:0x0872, B:416:0x087c, B:419:0x0887, B:421:0x0891, B:422:0x089a, B:424:0x08a0, B:426:0x08a6, B:429:0x08ad, B:430:0x08b4, B:431:0x08b9, B:436:0x08d1, B:438:0x08d9, B:449:0x0901, B:451:0x0909, B:457:0x091b, B:459:0x0925, B:462:0x0930, B:464:0x093a, B:467:0x0941, B:470:0x0953, B:472:0x095d, B:473:0x0962, B:475:0x0968, B:478:0x0970, B:479:0x0973, B:481:0x097a, B:482:0x0981, B:483:0x0986, B:485:0x098b, B:486:0x0990, B:487:0x0999, B:454:0x0912, B:433:0x08be, B:434:0x08c3, B:435:0x08cc, B:409:0x0857, B:410:0x085c, B:411:0x0865, B:364:0x0728, B:365:0x072d, B:366:0x0736, B:345:0x06cc, B:346:0x06d1, B:347:0x06da, B:320:0x0652, B:321:0x0657, B:322:0x0660, B:298:0x05e9, B:299:0x05ee, B:300:0x05f7, B:492:0x09a7, B:494:0x09ad, B:497:0x09b1, B:502:0x09c0, B:507:0x09d0, B:514:0x09f0, B:519:0x0a0a, B:521:0x0a10, B:522:0x0a15, B:523:0x0a1b, B:525:0x0a21, B:527:0x0a31, B:545:0x0a78, B:547:0x0a7e, B:562:0x0abc, B:572:0x0af0, B:576:0x0af9, B:577:0x0afc, B:579:0x0aff, B:580:0x0b06, B:534:0x0a4c, B:536:0x0a54, B:583:0x0b12, B:587:0x0b1d, B:589:0x0b23, B:590:0x0b27, B:607:0x0b56, B:609:0x0b62, B:238:0x04e4, B:239:0x04e9, B:240:0x04f2, B:223:0x0476, B:224:0x047b, B:225:0x0484, B:177:0x0371, B:178:0x0376, B:179:0x037b, B:159:0x030c, B:160:0x0311, B:161:0x0316, B:550:0x0a8c, B:558:0x0aa0, B:561:0x0ab9, B:574:0x0af5, B:565:0x0ac4, B:566:0x0acc, B:568:0x0ad2, B:570:0x0ade, B:571:0x0ae8), top: B:641:0x0067, inners: #4, #22, #26, #32, #39, #42, #50, #53, #55, #58, #59, #60, #65, #66, #67, #68, #67, #66, #65, #64, #62, #61, #60, #59, #57, #55, #54, #53 }] */
    /* JADX WARN: Code duplicated, block: B:580:0x0b06 A[Catch: all -> 0x0075, TryCatch #41 {all -> 0x0075, blocks: (B:14:0x0067, B:16:0x006d, B:24:0x0083, B:29:0x0094, B:34:0x00a4, B:36:0x00a8, B:37:0x00ae, B:42:0x00bc, B:44:0x00c0, B:45:0x00c4, B:47:0x00d2, B:48:0x00db, B:50:0x00e7, B:51:0x00f0, B:56:0x0100, B:58:0x010c, B:61:0x0119, B:64:0x0121, B:65:0x0126, B:66:0x0140, B:71:0x0151, B:73:0x0155, B:74:0x015a, B:79:0x016d, B:80:0x0174, B:82:0x017c, B:83:0x0184, B:87:0x0194, B:91:0x01a3, B:95:0x01c1, B:97:0x01c9, B:100:0x01d3, B:102:0x01db, B:105:0x01e5, B:106:0x01ec, B:107:0x01f5, B:111:0x0206, B:113:0x020e, B:116:0x0215, B:117:0x0247, B:121:0x0256, B:124:0x0260, B:127:0x0265, B:130:0x026b, B:132:0x0275, B:133:0x0277, B:136:0x0281, B:137:0x0293, B:140:0x02a4, B:141:0x02ae, B:142:0x02b6, B:147:0x02c7, B:149:0x02d1, B:151:0x02d7, B:153:0x02e2, B:156:0x02e9, B:157:0x0305, B:165:0x0327, B:167:0x0331, B:169:0x0337, B:171:0x0342, B:174:0x0349, B:175:0x036c, B:184:0x038d, B:186:0x0394, B:187:0x0399, B:188:0x03a9, B:190:0x03b3, B:192:0x03b9, B:194:0x03c4, B:195:0x03c9, B:197:0x03d3, B:199:0x03d7, B:200:0x03eb, B:202:0x03f9, B:204:0x0405, B:205:0x041d, B:206:0x0422, B:207:0x042a, B:209:0x042f, B:210:0x0434, B:211:0x043d, B:215:0x044a, B:217:0x0458, B:218:0x045f, B:220:0x0465, B:221:0x046c, B:229:0x048f, B:231:0x049a, B:233:0x04bf, B:235:0x04c9, B:236:0x04d2, B:232:0x04a2, B:245:0x04ff, B:247:0x0507, B:250:0x050f, B:252:0x0517, B:253:0x051e, B:255:0x0526, B:257:0x052e, B:258:0x0533, B:260:0x053f, B:262:0x054b, B:264:0x0557, B:267:0x055e, B:268:0x0566, B:270:0x056b, B:271:0x0570, B:272:0x0579, B:274:0x0581, B:275:0x0588, B:278:0x0592, B:280:0x059c, B:283:0x05a7, B:285:0x05b5, B:286:0x05bd, B:288:0x05c3, B:291:0x05ca, B:293:0x05d7, B:294:0x05dd, B:295:0x05e1, B:301:0x05fc, B:303:0x0604, B:305:0x060e, B:308:0x0619, B:310:0x0627, B:311:0x062b, B:313:0x0631, B:314:0x0637, B:316:0x063e, B:317:0x0644, B:318:0x064e, B:323:0x0665, B:325:0x066d, B:327:0x0677, B:330:0x0682, B:332:0x0690, B:333:0x0699, B:335:0x069f, B:338:0x06a6, B:340:0x06b4, B:341:0x06bb, B:342:0x06c2, B:343:0x06c7, B:348:0x06df, B:350:0x06e7, B:352:0x06f1, B:355:0x06fc, B:357:0x0706, B:358:0x070b, B:360:0x0711, B:361:0x071a, B:362:0x0723, B:367:0x073b, B:369:0x0743, B:371:0x0749, B:373:0x074f, B:376:0x075b, B:378:0x075f, B:379:0x0766, B:381:0x0782, B:382:0x0787, B:384:0x078d, B:385:0x0792, B:387:0x0798, B:388:0x079f, B:391:0x07b1, B:404:0x080f, B:406:0x084d, B:394:0x07b8, B:396:0x07c2, B:398:0x07c8, B:401:0x07d8, B:403:0x080c, B:405:0x081a, B:407:0x0852, B:412:0x086a, B:414:0x0872, B:416:0x087c, B:419:0x0887, B:421:0x0891, B:422:0x089a, B:424:0x08a0, B:426:0x08a6, B:429:0x08ad, B:430:0x08b4, B:431:0x08b9, B:436:0x08d1, B:438:0x08d9, B:449:0x0901, B:451:0x0909, B:457:0x091b, B:459:0x0925, B:462:0x0930, B:464:0x093a, B:467:0x0941, B:470:0x0953, B:472:0x095d, B:473:0x0962, B:475:0x0968, B:478:0x0970, B:479:0x0973, B:481:0x097a, B:482:0x0981, B:483:0x0986, B:485:0x098b, B:486:0x0990, B:487:0x0999, B:454:0x0912, B:433:0x08be, B:434:0x08c3, B:435:0x08cc, B:409:0x0857, B:410:0x085c, B:411:0x0865, B:364:0x0728, B:365:0x072d, B:366:0x0736, B:345:0x06cc, B:346:0x06d1, B:347:0x06da, B:320:0x0652, B:321:0x0657, B:322:0x0660, B:298:0x05e9, B:299:0x05ee, B:300:0x05f7, B:492:0x09a7, B:494:0x09ad, B:497:0x09b1, B:502:0x09c0, B:507:0x09d0, B:514:0x09f0, B:519:0x0a0a, B:521:0x0a10, B:522:0x0a15, B:523:0x0a1b, B:525:0x0a21, B:527:0x0a31, B:545:0x0a78, B:547:0x0a7e, B:562:0x0abc, B:572:0x0af0, B:576:0x0af9, B:577:0x0afc, B:579:0x0aff, B:580:0x0b06, B:534:0x0a4c, B:536:0x0a54, B:583:0x0b12, B:587:0x0b1d, B:589:0x0b23, B:590:0x0b27, B:607:0x0b56, B:609:0x0b62, B:238:0x04e4, B:239:0x04e9, B:240:0x04f2, B:223:0x0476, B:224:0x047b, B:225:0x0484, B:177:0x0371, B:178:0x0376, B:179:0x037b, B:159:0x030c, B:160:0x0311, B:161:0x0316, B:550:0x0a8c, B:558:0x0aa0, B:561:0x0ab9, B:574:0x0af5, B:565:0x0ac4, B:566:0x0acc, B:568:0x0ad2, B:570:0x0ade, B:571:0x0ae8), top: B:641:0x0067, inners: #4, #22, #26, #32, #39, #42, #50, #53, #55, #58, #59, #60, #65, #66, #67, #68, #67, #66, #65, #64, #62, #61, #60, #59, #57, #55, #54, #53 }] */
    /* JADX WARN: Code duplicated, block: B:594:0x0b31  */
    /* JADX WARN: Code duplicated, block: B:627:0x0a8c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:710:0x0ade A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:712:0x0acc A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:719:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v22, types: [boolean] */
    public void C(String str, C0544a c0544a, boolean z) throws Throwable {
        boolean z2;
        String strL;
        long j;
        String strL2;
        C6240o c6240oP;
        AbstractC6239n abstractC6239n;
        ArrayList arrayList;
        ArrayList arrayList2;
        List<AbstractC6239n> listG;
        ArrayList arrayList3;
        boolean z3;
        Socket socketB;
        String strN;
        String str2;
        OutputStream outputStream;
        Iterator<AbstractC6239n> it;
        String strG;
        Object objU;
        boolean z4;
        String str3;
        String str4;
        int iL;
        String[] strArrSplit = str.split(" ", 2);
        if (strArrSplit == null || strArrSplit.length == 0) {
            O("502 Command not recognized\r\n");
            return;
        }
        E.fine("FTP INPUT : " + str);
        String upperCase = strArrSplit[0].trim().toUpperCase();
        String strTrim = strArrSplit.length > 1 ? strArrSplit[1].trim() : HttpUrl.FRAGMENT_ENCODE_SET;
        try {
            if (!this.k) {
                try {
                    if (z(upperCase)) {
                        O("530 Login incorrect\r\n");
                        this.A = 0L;
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    z2 = false;
                }
            }
            try {
                if ("NOOP".equals(upperCase)) {
                    O("200 NOOP OK\r\n");
                } else if ("AUTH".equals(upperCase)) {
                    D(strTrim, c0544a);
                } else if ("PBSZ".equals(upperCase)) {
                    if (this.B) {
                        O("200 PBSZ=0\r\n");
                    } else {
                        O("503 TLS is required\r\n");
                    }
                } else if ("PROT".equals(upperCase)) {
                    if (this.B) {
                        Locale locale = Locale.US;
                        if ("P".equals(strTrim.toUpperCase(locale))) {
                            this.C = true;
                            O("200 Protection set to Private\r\n");
                        } else if ("C".equals(strTrim.toUpperCase(locale))) {
                            this.C = false;
                            O("200 Protection set to Clear\r\n");
                        } else {
                            O("536 Unsupported PROT level\r\n");
                        }
                    } else {
                        O("503 TLS is required\r\n");
                    }
                } else if ("OPTS".equals(upperCase)) {
                    if ("UTF8 ON".equals(strTrim.toUpperCase()) || "UTF-8 ON".equals(strTrim.toUpperCase())) {
                        O("200 UTF8 OK\r\n");
                    } else if (strTrim.startsWith("MLST")) {
                        O("501 Unsupported Options\r\n");
                    } else {
                        ax.p302sb.c.h().g().c("UNSUPPORTED FTP OPTIONS").h(str).i();
                        O("501 Unsupported Options\r\n");
                    }
                } else if ("USER".equals(upperCase)) {
                    if (this.k) {
                        O("230 Access granted\r\n");
                    } else {
                        this.l = strTrim;
                        O("331 Password required\r\n");
                    }
                } else if ("PASS".equals(upperCase)) {
                    if (z) {
                        O("530 Maximum number of login attempts exceeded\r\n");
                    } else if (c(this.l, strTrim)) {
                        O("230 Access granted\r\n");
                        this.k = true;
                    } else {
                        c0544a.b = true;
                        O("530 Login incorrect\r\n");
                    }
                } else if ("SYST".equals(upperCase)) {
                    O("215 UNIX Type: L8\r\n");
                } else if ("PWD".equals(upperCase)) {
                    O(String.format(Locale.US, "257 \"%s\"\r\n", this.f));
                } else if ("TYPE".equals(upperCase)) {
                    if ("I".equals(strTrim) || "L 8".equals(strTrim)) {
                        this.g = true;
                        O("200 Binary type set\r\n");
                    } else if ("A".equals(strTrim) || "A N".equals(strTrim)) {
                        this.g = false;
                        O("200 ASCII type set\r\n");
                    } else {
                        O("503 Unknown TYPE command\r\n");
                    }
                } else if ("PASV".equals(upperCase)) {
                    InetAddress localAddress = this.b.getLocalAddress();
                    if (localAddress == null || (iL = L()) <= 0) {
                        O("502 Cannot open a port\r\n");
                    } else {
                        O(String.format(Locale.US, "227 Entering Passive Mode (%s,%d,%d).\r\n", localAddress.getHostAddress().replace('.', ','), Integer.valueOf(iL / 256), Integer.valueOf(iL % 256)));
                    }
                } else {
                    if (!"PORT".equals(upperCase)) {
                        String str5 = "550 Unknown error.\r\n";
                        if ("SIZE".equals(upperCase)) {
                            try {
                                try {
                                    AbstractC6239n abstractC6239nO = o(l(strTrim));
                                    if (abstractC6239nO != null && !abstractC6239nO.o()) {
                                        O(y(strTrim));
                                    } else if (abstractC6239nO == null || abstractC6239nO.isDirectory()) {
                                        O("550 SIZE request on an invalid file\r\n");
                                    } else {
                                        O(String.format(Locale.US, "213 %d\r\n", Long.valueOf(abstractC6239nO.p())));
                                    }
                                } catch (IllegalArgumentException unused) {
                                    O("501 Syntax error\r\n");
                                }
                            } catch (t unused2) {
                                O(y(strTrim));
                            } catch (C1966j unused3) {
                                O("550 Unknown error.\r\n");
                            }
                        } else if ("MDTM".equals(upperCase)) {
                            try {
                                try {
                                    AbstractC6239n abstractC6239nO2 = o(l(strTrim));
                                    if (abstractC6239nO2 != null && !abstractC6239nO2.o()) {
                                        O(y(strTrim));
                                    } else if (abstractC6239nO2 == null || abstractC6239nO2.isDirectory()) {
                                        O("550 MDTM request on an invalid file\r\n");
                                    } else {
                                        str5 = "213 %s\r\n";
                                        O(String.format(Locale.US, "213 %s\r\n", this.s.format(new Date(abstractC6239nO2.q()))));
                                    }
                                } catch (C1966j unused4) {
                                    O("550 MDTM request on an invalid file\r\n");
                                } catch (IllegalArgumentException unused5) {
                                    O("501 Syntax error\r\n");
                                }
                            } catch (t unused6) {
                                O(y(strTrim));
                            }
                        } else if ("MFMT".equals(upperCase)) {
                            String[] strArrSplit2 = strTrim.split(" ", 2);
                            if (strArrSplit2.length < 2) {
                                O("501 Syntax error\r\n");
                            } else {
                                String strTrim2 = strArrSplit2[0].trim();
                                String strTrim3 = strArrSplit2[1].trim();
                                try {
                                    try {
                                        AbstractC6239n abstractC6239nO3 = o(l(strTrim3));
                                        if (abstractC6239nO3 != null && !abstractC6239nO3.o()) {
                                            O(y(strTrim));
                                        } else if (abstractC6239nO3 == null) {
                                            O("550 MFMT failed to set time\r\n");
                                        } else {
                                            C6240o c6240oP2 = p(abstractC6239nO3);
                                            if (B.P(abstractC6239nO3)) {
                                                if (this.t == null) {
                                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
                                                    this.t = simpleDateFormat;
                                                    simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
                                                }
                                                Date date = this.t.parse(strTrim2);
                                                AbstractC6238m abstractC6238mU = c6240oP2.u();
                                                if (!(abstractC6238mU instanceof C6248x)) {
                                                    C2461b.f();
                                                    O("550 MFMT failed to set time\r\n");
                                                } else if (((C6248x) abstractC6238mU).d(abstractC6239nO3, date.getTime())) {
                                                    O(String.format(Locale.US, "213 Modify=%s; %s\r\n", strTrim2, strTrim3));
                                                } else {
                                                    O("550 MFMT failed to set time\r\n");
                                                }
                                            } else {
                                                O("550 MFMT failed to set time\r\n");
                                            }
                                        }
                                    } catch (t unused7) {
                                        O(y(strTrim));
                                    } catch (IllegalArgumentException | ParseException unused8) {
                                        O("501 Syntax error\r\n");
                                    }
                                } catch (C1966j unused9) {
                                    O("550 MFMT failed to set time\r\n");
                                }
                            }
                        } else if ("CWD".equals(upperCase)) {
                            try {
                                try {
                                    String strL3 = l(strTrim);
                                    AbstractC6239n abstractC6239nO4 = o(strL3);
                                    if (!abstractC6239nO4.o()) {
                                        O("550 No such file or directory.\r\n");
                                    } else if (u(abstractC6239nO4)) {
                                        M(abstractC6239nO4, strL3);
                                        O("250 CWD OK\r\n");
                                    } else {
                                        O("550 CWD to the invalid path\r\n");
                                    }
                                } catch (t unused10) {
                                    O(y(strTrim));
                                } catch (C1966j unused11) {
                                    O(str5);
                                }
                            } catch (IllegalArgumentException unused12) {
                                O("501 Syntax error\r\n");
                            }
                        } else {
                            if (!"MLST".equals(upperCase)) {
                                if ("MLSD".equals(upperCase) || "LIST".equals(upperCase) || "NLST".equals(upperCase)) {
                                    while (strTrim.startsWith("-")) {
                                        String[] strArrSplit3 = strTrim.split(" ", 2);
                                        if (strArrSplit3 != null && strArrSplit3.length >= 2) {
                                            strTrim = strArrSplit3[1];
                                        }
                                        strTrim = HttpUrl.FRAGMENT_ENCODE_SET;
                                    }
                                    if (strTrim.contains("*")) {
                                        O("550 LIST/NLST does not support wildcards\r\n");
                                        this.A = 0L;
                                        return;
                                    }
                                    if (strTrim.length() == 0) {
                                        strL2 = this.f;
                                        abstractC6239n = this.e;
                                        c6240oP = p(abstractC6239n);
                                    } else {
                                        try {
                                            try {
                                                strL2 = l(strTrim);
                                                try {
                                                    AbstractC6239n abstractC6239nO5 = o(strL2);
                                                    c6240oP = p(abstractC6239nO5);
                                                    abstractC6239n = abstractC6239nO5;
                                                } catch (t unused13) {
                                                    j = 0;
                                                    O(y(strTrim));
                                                } catch (IllegalArgumentException unused14) {
                                                    j = 0;
                                                    O("501 Syntax error\r\n");
                                                }
                                            } catch (C1966j unused15) {
                                                try {
                                                    O("550 Unknown error.\r\n");
                                                    j = 0;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                }
                                            }
                                        } catch (t unused16) {
                                            j = 0;
                                        } catch (IllegalArgumentException unused17) {
                                            j = 0;
                                        }
                                    }
                                    if (!abstractC6239n.o()) {
                                        O(y(strTrim));
                                        this.A = 0L;
                                        return;
                                    }
                                    boolean zEquals = "NLST".equals(upperCase);
                                    boolean zEquals2 = "MLSD".equals(upperCase);
                                    if (abstractC6239n.isDirectory()) {
                                        try {
                                            if (v(strL2)) {
                                                try {
                                                    ArrayList arrayList4 = new ArrayList();
                                                    try {
                                                        Iterator<K> it2 = this.w.iterator();
                                                        while (it2.hasNext()) {
                                                            AbstractC6239n abstractC6239n2 = this.v.get(it2.next());
                                                            if (abstractC6239n2 != null) {
                                                                arrayList4.add(abstractC6239n2);
                                                            }
                                                        }
                                                        listG = arrayList4;
                                                        arrayList = null;
                                                    } catch (C1961e unused18) {
                                                        arrayList3 = arrayList4;
                                                        arrayList = null;
                                                        listG = arrayList3;
                                                        z3 = true;
                                                        if (listG != null) {
                                                            socketB = B();
                                                            if (socketB == null) {
                                                                f(socketB);
                                                                O("425 Error opening data socket\r\n");
                                                                j = 0;
                                                            } else {
                                                                try {
                                                                    try {
                                                                        Locale locale2 = Locale.US;
                                                                        if (this.g) {
                                                                            str2 = "BINARY";
                                                                        } else {
                                                                            str2 = "ASCII";
                                                                        }
                                                                        O(String.format(locale2, "150 Opening %s mode data connection for %s\r\n", str2, upperCase));
                                                                        strN = N(socketB);
                                                                        try {
                                                                            if (strN == 0) {
                                                                                O("426 Data socket or network error\r\n");
                                                                                f(socketB);
                                                                                j = 0;
                                                                            } else {
                                                                                outputStream = socketB.getOutputStream();
                                                                                it = listG.iterator();
                                                                                while (it.hasNext()) {
                                                                                    strG = g(it.next(), zEquals2, zEquals);
                                                                                    if (strG != null) {
                                                                                        byte[] bytes = strG.getBytes();
                                                                                        outputStream.write(bytes, 0, bytes.length);
                                                                                    }
                                                                                }
                                                                                outputStream.flush();
                                                                                O("226 Data transmission succeeded\r\n");
                                                                                f(socketB);
                                                                            }
                                                                        } catch (IOException unused19) {
                                                                            O(strN);
                                                                        }
                                                                    } catch (IOException unused20) {
                                                                        strN = "426 Data socket or network error\r\n";
                                                                    }
                                                                } catch (Throwable th3) {
                                                                    f(socketB);
                                                                    throw th3;
                                                                }
                                                            }
                                                            this.A = j;
                                                            return;
                                                        }
                                                        if (z3) {
                                                            O("550 Permission denied\r\n");
                                                        } else {
                                                            O("550 Failed to list files\r\n");
                                                        }
                                                        objU = arrayList;
                                                        z4 = false;
                                                        str3 = objU;
                                                        c0544a.a = str3;
                                                        if (z4) {
                                                            return;
                                                        }
                                                        j = 0;
                                                        this.A = j;
                                                        return;
                                                    } catch (C1966j unused21) {
                                                        arrayList2 = arrayList4;
                                                        arrayList = null;
                                                        listG = arrayList2;
                                                    }
                                                } catch (C1961e unused22) {
                                                    arrayList = null;
                                                    arrayList3 = null;
                                                } catch (C1966j unused23) {
                                                    arrayList = null;
                                                    arrayList2 = null;
                                                }
                                            } else {
                                                List<AbstractC6239n> listN = c6240oP.N(abstractC6239n);
                                                arrayList = null;
                                                try {
                                                    listG = C2011q.g(B.h(listN, null, this.z, true), C2011q.c("NameUp"));
                                                } catch (C1961e unused24) {
                                                    arrayList3 = arrayList;
                                                    listG = arrayList3;
                                                    z3 = true;
                                                    if (listG != null) {
                                                        socketB = B();
                                                        if (socketB == null) {
                                                            f(socketB);
                                                            O("425 Error opening data socket\r\n");
                                                            j = 0;
                                                        } else {
                                                            Locale locale3 = Locale.US;
                                                            if (this.g) {
                                                                str2 = "BINARY";
                                                            } else {
                                                                str2 = "ASCII";
                                                            }
                                                            O(String.format(locale3, "150 Opening %s mode data connection for %s\r\n", str2, upperCase));
                                                            strN = N(socketB);
                                                            if (strN == 0) {
                                                                O("426 Data socket or network error\r\n");
                                                                f(socketB);
                                                                j = 0;
                                                            } else {
                                                                outputStream = socketB.getOutputStream();
                                                                it = listG.iterator();
                                                                while (it.hasNext()) {
                                                                    strG = g(it.next(), zEquals2, zEquals);
                                                                    if (strG != null) {
                                                                        byte[] bytes2 = strG.getBytes();
                                                                        outputStream.write(bytes2, 0, bytes2.length);
                                                                    }
                                                                }
                                                                outputStream.flush();
                                                                O("226 Data transmission succeeded\r\n");
                                                                f(socketB);
                                                            }
                                                        }
                                                        this.A = j;
                                                        return;
                                                    }
                                                    if (z3) {
                                                        O("550 Permission denied\r\n");
                                                    } else {
                                                        O("550 Failed to list files\r\n");
                                                    }
                                                    objU = arrayList;
                                                    z4 = false;
                                                    str3 = objU;
                                                    c0544a.a = str3;
                                                    if (z4) {
                                                        j = 0;
                                                        this.A = j;
                                                        return;
                                                    }
                                                    return;
                                                } catch (C1966j unused25) {
                                                    arrayList2 = arrayList;
                                                    listG = arrayList2;
                                                }
                                            }
                                        } catch (C1961e unused26) {
                                            arrayList = null;
                                        } catch (C1966j unused27) {
                                            arrayList = null;
                                        }
                                        z3 = false;
                                        if (listG != null) {
                                            socketB = B();
                                            if (socketB == null) {
                                                f(socketB);
                                                O("425 Error opening data socket\r\n");
                                                j = 0;
                                            } else {
                                                Locale locale4 = Locale.US;
                                                if (this.g) {
                                                    str2 = "BINARY";
                                                } else {
                                                    str2 = "ASCII";
                                                }
                                                O(String.format(locale4, "150 Opening %s mode data connection for %s\r\n", str2, upperCase));
                                                strN = N(socketB);
                                                if (strN == 0) {
                                                    O("426 Data socket or network error\r\n");
                                                    f(socketB);
                                                    j = 0;
                                                } else {
                                                    outputStream = socketB.getOutputStream();
                                                    it = listG.iterator();
                                                    while (it.hasNext()) {
                                                        strG = g(it.next(), zEquals2, zEquals);
                                                        if (strG != null) {
                                                            byte[] bytes3 = strG.getBytes();
                                                            outputStream.write(bytes3, 0, bytes3.length);
                                                        }
                                                    }
                                                    outputStream.flush();
                                                    O("226 Data transmission succeeded\r\n");
                                                    f(socketB);
                                                }
                                            }
                                        } else if (z3) {
                                            O("550 Permission denied\r\n");
                                        } else {
                                            O("550 Failed to list files\r\n");
                                        }
                                        objU = arrayList;
                                    } else {
                                        arrayList = null;
                                        if (zEquals2) {
                                            O("501 Not a directory\r\n");
                                            this.A = 0L;
                                            return;
                                        } else {
                                            String strG2 = g(abstractC6239n, false, zEquals);
                                            if (strG2 == null) {
                                                O("550 Failed to list files\r\n");
                                            } else {
                                                K(upperCase, strG2);
                                            }
                                            objU = arrayList;
                                        }
                                    }
                                    this.A = j;
                                    return;
                                }
                                if ("QUIT".equals(upperCase)) {
                                    O("221 Goodbye\r\n");
                                } else if (!"CDUP".equals(upperCase)) {
                                    if (!"FEAT".equals(upperCase)) {
                                        if ("DELE".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strL4 = l(strTrim);
                                                    if (v(strL4) || v(d0.r(strL4))) {
                                                        O("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC6239n abstractC6239nO6 = o(strL4);
                                                        C6240o c6240oP3 = p(abstractC6239nO6);
                                                        if (!abstractC6239nO6.o()) {
                                                            O(y(strTrim));
                                                        } else if (abstractC6239nO6.isDirectory() || !w(abstractC6239nO6)) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else {
                                                            try {
                                                                c6240oP3.q1(abstractC6239nO6);
                                                                O("250 File successfully deleted\r\n");
                                                                objU = abstractC6239nO6.U();
                                                            } catch (C1966j unused28) {
                                                                O("450 Error deleting file\r\n");
                                                                objU = null;
                                                            }
                                                        }
                                                    }
                                                    objU = null;
                                                } catch (t unused29) {
                                                    O(y(strTrim));
                                                    arrayList = null;
                                                    objU = arrayList;
                                                } catch (C1966j unused30) {
                                                    O("550 Unknown error.\r\n");
                                                    arrayList = null;
                                                    objU = arrayList;
                                                }
                                            } catch (IllegalArgumentException unused31) {
                                                O("501 Syntax error\r\n");
                                                arrayList = null;
                                                objU = arrayList;
                                            }
                                        } else if ("MKD".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strL5 = l(strTrim);
                                                    if (v(strL5) || v(d0.r(strL5))) {
                                                        O("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC6239n abstractC6239nO7 = o(strL5);
                                                        C6240o c6240oP4 = p(abstractC6239nO7);
                                                        if (!w(abstractC6239nO7)) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else if (abstractC6239nO7.o()) {
                                                            O("550 Already exists\r\n");
                                                        } else if (c6240oP4.k(abstractC6239nO7, false)) {
                                                            O("250 Directory created\r\n");
                                                            objU = abstractC6239nO7.U();
                                                        } else {
                                                            O("550 MKD denied\r\n");
                                                        }
                                                    }
                                                    objU = null;
                                                } catch (t unused32) {
                                                    O(y(strTrim));
                                                    arrayList = null;
                                                    objU = arrayList;
                                                } catch (C1966j unused33) {
                                                    O("550 Unknown error.\r\n");
                                                    arrayList = null;
                                                    objU = arrayList;
                                                }
                                            } catch (IllegalArgumentException unused34) {
                                                O("501 Syntax error\r\n");
                                                arrayList = null;
                                                objU = arrayList;
                                            }
                                        } else if ("RMD".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strL6 = l(strTrim);
                                                    if (v(strL6) || v(d0.r(strL6))) {
                                                        O("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC6239n abstractC6239nO8 = o(strL6);
                                                        C6240o c6240oP5 = p(abstractC6239nO8);
                                                        if (!abstractC6239nO8.o()) {
                                                            O(y(strTrim));
                                                        } else if (abstractC6239nO8.isDirectory() && w(abstractC6239nO8)) {
                                                            try {
                                                                c6240oP5.q1(abstractC6239nO8);
                                                                O("250 Directory removed\r\n");
                                                                objU = abstractC6239nO8.U();
                                                            } catch (C1967k unused35) {
                                                                O("550 Directory not empty\r\n");
                                                                objU = null;
                                                            } catch (C1966j unused36) {
                                                                O("550 RMD failed\r\n");
                                                                objU = null;
                                                            }
                                                        } else {
                                                            O("550 Operation on invalid file\r\n");
                                                        }
                                                    }
                                                    objU = null;
                                                } catch (t unused37) {
                                                    O(y(strTrim));
                                                    arrayList = null;
                                                    objU = arrayList;
                                                } catch (C1966j unused38) {
                                                    O("550 Unknown error.\r\n");
                                                    arrayList = null;
                                                    objU = arrayList;
                                                }
                                            } catch (IllegalArgumentException unused39) {
                                                O("501 Syntax error\r\n");
                                                arrayList = null;
                                                objU = arrayList;
                                            }
                                        } else if ("RNFR".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strL7 = l(strTrim);
                                                    if (v(strL7) || v(d0.r(strL7))) {
                                                        O("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC6239n abstractC6239nO9 = o(strL7);
                                                        if (!w(abstractC6239nO9)) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else if (abstractC6239nO9.o()) {
                                                            this.p = abstractC6239nO9;
                                                            O("350 Waiting for RNTO\r\n");
                                                        } else {
                                                            O(y(strTrim));
                                                        }
                                                    }
                                                } catch (t unused40) {
                                                    O(y(strTrim));
                                                } catch (IllegalArgumentException unused41) {
                                                    O("501 Syntax error\r\n");
                                                }
                                            } catch (C1966j unused42) {
                                                O("550 Unknown error.\r\n");
                                            }
                                        } else if ("RNTO".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strL8 = l(strTrim);
                                                    if (strL8 == null || v(strL8) || v(d0.r(strL8))) {
                                                        O("550 Operation on invalid file\r\n");
                                                    } else if (this.p == null) {
                                                        O("550 RNFR was not processed\r\n");
                                                    } else {
                                                        AbstractC6239n abstractC6239nO10 = o(strL8);
                                                        C6240o c6240oP6 = p(abstractC6239nO10);
                                                        AbstractC6239n abstractC6239n3 = this.p;
                                                        C6240o c6240oP7 = p(abstractC6239n3);
                                                        AbstractC6239n abstractC6239nA1 = c6240oP6.a1(abstractC6239nO10.U());
                                                        if (!w(abstractC6239nO10)) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else if (abstractC6239nO10.o()) {
                                                            O("550 Rename failed\r\n");
                                                        } else if (abstractC6239nA1.o()) {
                                                            try {
                                                                if (abstractC6239n3.isDirectory()) {
                                                                    if (abstractC6239nO10.Q() != abstractC6239n3.Q()) {
                                                                        ax.p302sb.c.h().c("FTP MOVE 2").h(abstractC6239n3.Q().H() + "->" + abstractC6239nO10.Q().H()).i();
                                                                    } else if (c6240oP7.c1(abstractC6239n3) || abstractC6239n3.U().equals(abstractC6239nO10.U())) {
                                                                        c6240oP7.i1(abstractC6239n3, abstractC6239nO10, null, null);
                                                                    } else {
                                                                        ax.p302sb.c.h().c("FTP MOVE 1").h(abstractC6239n3.Q().H() + "->" + abstractC6239nO10.Q().H()).i();
                                                                    }
                                                                    O("550 Rename failed\r\n");
                                                                } else {
                                                                    c6240oP7.P(abstractC6239n3, c6240oP6, abstractC6239nO10, true, null, null);
                                                                }
                                                                O("250 Rename succeeded\r\n");
                                                                objU = abstractC6239nO10.U();
                                                            } catch (C1957a | C1966j unused43) {
                                                            }
                                                        } else {
                                                            O("553 File name not allowed\r\n");
                                                        }
                                                    }
                                                    objU = null;
                                                } catch (t unused44) {
                                                    O(y(strTrim));
                                                    arrayList = null;
                                                    objU = arrayList;
                                                } catch (C1966j unused45) {
                                                    O("550 Unknown error.\r\n");
                                                    arrayList = null;
                                                    objU = arrayList;
                                                }
                                            } catch (IllegalArgumentException unused46) {
                                                O("501 Syntax error\r\n");
                                                arrayList = null;
                                                objU = arrayList;
                                            }
                                        } else {
                                            if ("RETR".equals(upperCase)) {
                                                try {
                                                    try {
                                                        String strL9 = l(strTrim);
                                                        if (v(strL9) || v(d0.r(strL9))) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else {
                                                            AbstractC6239n abstractC6239nO11 = o(strL9);
                                                            if (!abstractC6239nO11.o()) {
                                                                O(y(strTrim));
                                                            } else if (!abstractC6239nO11.isDirectory() && abstractC6239nO11.h() && w(abstractC6239nO11)) {
                                                                J(abstractC6239nO11, this.A);
                                                            } else {
                                                                O("550 Operation on invalid file\r\n");
                                                            }
                                                        }
                                                    } catch (C1966j unused47) {
                                                        O("550 Unknown error.\r\n");
                                                    }
                                                } catch (t unused48) {
                                                    O(y(strTrim));
                                                } catch (IllegalArgumentException unused49) {
                                                    O("501 Syntax error\r\n");
                                                }
                                            } else if ("REST".equals(upperCase)) {
                                                try {
                                                    this.A = Long.valueOf(strTrim).longValue();
                                                    try {
                                                        O("350 REST set\r\n");
                                                        str3 = 0;
                                                        z4 = true;
                                                    } catch (NumberFormatException unused50) {
                                                        z2 = true;
                                                        try {
                                                            O("501 Invalid REST option\r\n");
                                                            z4 = z2;
                                                            str3 = 0;
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                        }
                                                    } catch (Throwable th5) {
                                                        th = th5;
                                                        z2 = true;
                                                    }
                                                } catch (NumberFormatException unused51) {
                                                    z2 = false;
                                                }
                                            } else {
                                                if ("STOR".equals(upperCase)) {
                                                    str4 = "APPE";
                                                } else {
                                                    str4 = "APPE";
                                                    if (!str4.equals(upperCase)) {
                                                        O("550 Unsupported command\r\n");
                                                    }
                                                }
                                                try {
                                                    try {
                                                        String strL10 = l(strTrim);
                                                        if (v(strL10) || v(d0.r(strL10))) {
                                                            O("550 Operation on invalid file\r\n");
                                                        } else {
                                                            AbstractC6239n abstractC6239nO12 = o(strL10);
                                                            if (abstractC6239nO12.isDirectory() || !w(abstractC6239nO12)) {
                                                                O("550 Operation on invalid file\r\n");
                                                            } else {
                                                                boolean zEquals3 = str4.equals(upperCase);
                                                                C6240o c6240oP8 = p(abstractC6239nO12);
                                                                if (this.A <= 0 || zEquals3) {
                                                                    try {
                                                                        if (abstractC6239nO12.o() && !zEquals3) {
                                                                            c6240oP8.q1(abstractC6239nO12);
                                                                        }
                                                                        F(abstractC6239nO12, zEquals3, 0L);
                                                                    } catch (C1966j unused52) {
                                                                        O("451 Cannot overwrite the file\r\n");
                                                                    }
                                                                } else {
                                                                    long jP = abstractC6239nO12.p();
                                                                    long j2 = this.A;
                                                                    if (jP < j2) {
                                                                        O("550 Operation on invalid file\r\n");
                                                                    } else {
                                                                        F(abstractC6239nO12, false, j2);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } catch (t unused53) {
                                                        O(y(strTrim));
                                                    } catch (C1966j unused54) {
                                                        O("550 Unknown error.\r\n");
                                                    }
                                                } catch (IllegalArgumentException unused55) {
                                                    O("501 Syntax error\r\n");
                                                }
                                            }
                                            objU = arrayList;
                                        }
                                        c0544a.a = str3;
                                        if (z4) {
                                            j = 0;
                                            this.A = j;
                                            return;
                                        }
                                        return;
                                    }
                                    O("211- Extensions supported:\r\n AUTH TLS\r\n PBSZ\r\n PROT\r\n UTF8\r\n MLST\r\n MLSD\r\n MFMT\r\n REST STREAM\r\n211 End.\r\n");
                                } else if (v(this.f)) {
                                    O("550 Appropriate parent directory does not exist\r\n");
                                } else if (v(d0.r(this.f))) {
                                    M(this.d, "/");
                                    O("200 CDUP OK\r\n");
                                } else {
                                    try {
                                        String strR = d0.r(this.f);
                                        AbstractC6239n abstractC6239nO13 = o(strR);
                                        if (abstractC6239nO13 == null || !u(abstractC6239nO13)) {
                                            O("550 Appropriate parent directory does not exist\r\n");
                                        } else {
                                            M(abstractC6239nO13, strR);
                                            O("200 CDUP OK\r\n");
                                        }
                                    } catch (t unused56) {
                                        O(y(strTrim));
                                    } catch (C1966j unused57) {
                                        O("550 Unknown error.\r\n");
                                    }
                                }
                                th = th;
                                z2 = false;
                                if (!z2) {
                                    this.A = 0L;
                                }
                                throw th;
                            }
                            try {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    if (TextUtils.isEmpty(strTrim)) {
                                        strL = this.f;
                                        sb.append("250- Listing .\r\n");
                                    } else {
                                        strL = l(strTrim);
                                        sb.append("250- Listing " + strTrim + "\r\n");
                                    }
                                    AbstractC6239n abstractC6239nO14 = o(strL);
                                    if (abstractC6239nO14.o()) {
                                        a(sb, true, abstractC6239nO14, strL);
                                        sb.append("250 End\r\n");
                                        O(sb.toString());
                                    } else {
                                        O(y(strTrim));
                                    }
                                } catch (C1966j unused58) {
                                    O("550 Unknown error.\r\n");
                                } catch (IllegalArgumentException unused59) {
                                    O("501 Syntax error\r\n");
                                }
                            } catch (t unused60) {
                                O(y(strTrim));
                            }
                        }
                        z4 = false;
                        str3 = objU;
                        c0544a.a = str3;
                        if (z4) {
                            j = 0;
                            this.A = j;
                            return;
                        }
                        return;
                    }
                    String[] strArrSplit4 = strTrim.split(",");
                    if (strArrSplit4 == null || strArrSplit4.length != 6) {
                        O("550 Invalid PORT arguments\r\n");
                    } else {
                        byte[] bArr = new byte[4];
                        for (int i = 0; i < 4; i++) {
                            try {
                                int i2 = Integer.parseInt(strArrSplit4[i]);
                                if (i2 >= 128) {
                                    i2 -= 256;
                                }
                                bArr[i] = (byte) i2;
                            } catch (Exception e) {
                                e.printStackTrace();
                                O("550 Invalid PORT arguments\r\n");
                            }
                        }
                        this.j = (Integer.parseInt(strArrSplit4[4]) * 256) + Integer.parseInt(strArrSplit4[5]);
                        try {
                            this.i = InetAddress.getByAddress(bArr);
                            O("200 PORT OK\r\n");
                            d();
                        } catch (UnknownHostException e2) {
                            e2.printStackTrace();
                            O("550 Unknown host\r\n");
                        }
                    }
                }
                c0544a.a = str3;
                if (z4) {
                    j = 0;
                    this.A = j;
                    return;
                }
                return;
            } catch (Throwable th6) {
                th = th6;
                z2 = z4;
            }
            arrayList = null;
            objU = arrayList;
            z4 = false;
            str3 = objU;
        } catch (Throwable th7) {
            th = th7;
        }
    }

    public void O(String str) {
        P(str.getBytes());
    }

    public void P(byte[] bArr) {
        try {
            this.c.write(bArr);
            this.c.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void e() {
        d();
        for (C6240o c6240o : this.u.values()) {
            if (Q.X1() && c6240o.G()) {
                c6240o.n(null);
            }
            c6240o.S(true);
        }
        this.u.clear();
        this.x.clear();
        this.v.clear();
        this.w.clear();
    }

    InputStream m() throws IOException {
        return this.b.getInputStream();
    }

    BufferedOutputStream n() {
        return this.c;
    }

    public void t() {
        this.o.addAll(Arrays.asList(F));
        ax.p038c3.j.F().L0();
        b(K.e, "/device");
        K k = K.h;
        C6249y c6249y = new C6249y((C6248x) C2017x.e(k).u(), new File("/"), k, true, false, true, false, 0L, 0L);
        this.d = c6249y;
        M(c6249y, "/");
        boolean zU0 = ax.p038c3.j.F().u0();
        this.y = zU0;
        if (zU0) {
            b(K.f, "/sdcard");
        }
        if (this.z && ax.p390y3.k.F(this.a)) {
            b(k, "/system");
        }
        List<K> listA = ax.p038c3.j.F().A();
        if (listA != null) {
            int i = 2;
            for (K k2 : listA) {
                if (f.h0(k2.d())) {
                    b(k2, "/sdcard" + i);
                    i++;
                }
            }
        }
        List<K> listB = ax.p038c3.j.F().B();
        HashSet hashSet = new HashSet();
        if (listB != null) {
            for (K k3 : listB) {
                if (f.X(k3.d())) {
                    String strF = k3.f(this.a);
                    String str = strF;
                    int i2 = 2;
                    while (hashSet.contains(str)) {
                        str = strF + " " + i2;
                        i2++;
                    }
                    b(k3, "/" + str);
                    hashSet.add(str);
                }
            }
        }
    }
}
