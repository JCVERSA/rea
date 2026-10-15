package com.alphainventor.filemanager.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import ax.E1.P;
import ax.G1.i;
import ax.I1.C0710a;
import ax.I1.C0713d;
import ax.I1.C0718i;
import ax.I1.C0719j;
import ax.I1.s;
import ax.J1.C0733n;
import ax.J1.C0740v;
import ax.J1.F;
import ax.J1.V;
import ax.J1.r;
import ax.p221pa.c;
import ax.p338y1.f;
import ax.p338y1.g;
import com.alphainventor.filemanager.file.AbstractC5830d;
import com.alphainventor.filemanager.file.AbstractC5837k;
import com.alphainventor.filemanager.file.AbstractC5838l;
import com.alphainventor.filemanager.file.C5839m;
import com.alphainventor.filemanager.file.C5845t;
import com.alphainventor.filemanager.file.u;
import j$.util.DesugarTimeZone;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"DefaultLocale", "SimpleDateFormat"})
public class a {
    private static final Logger B = g.a(a.class);
    private static final String[] C = {"CWD", "CDUP", "SMNT", "PORT", "PASV", "MODE", "TYPE", "STRU", "ALL0", "REST", "STOR", "STOU", "RETR", "LIST", "NLST", "APPE", "RNFR", "RNT0", "DELE", "RMD", "MKD", "STAT", "SITE", "MLST", "MLST"};
    private long A;
    private Context a;
    private Socket b;
    private BufferedOutputStream c;
    private AbstractC5838l d;
    private AbstractC5838l e;
    private String f;
    private ServerSocket h;
    private InetAddress i;
    private int j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private AbstractC5838l p;
    private SimpleDateFormat q;
    private SimpleDateFormat r;
    private SimpleDateFormat s;
    private SimpleDateFormat t;
    private Map<F, C5839m> u;
    private Map<F, AbstractC5838l> v;
    private List<F> w;
    private Map<F, String> x;
    private boolean y;
    private boolean z;
    private boolean g = false;
    private HashSet<String> o = new HashSet<>();

    /* JADX INFO: renamed from: com.alphainventor.filemanager.service.a$a, reason: collision with other inner class name */
    static class C0510a {
        String a;
        boolean b;

        C0510a() {
        }

        void a() {
            this.a = null;
            this.b = false;
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

    private void a(StringBuilder sb, boolean z, AbstractC5838l abstractC5838l, String str) {
        if (z) {
            sb.append(" ");
        }
        if (abstractC5838l.isDirectory()) {
            sb.append("Type=dir;Modify=");
            sb.append(this.s.format(new Date(abstractC5838l.q())));
            sb.append(";Perm=el; ");
            if (str == null) {
                sb.append(abstractC5838l.w());
            } else {
                sb.append(str);
            }
        } else {
            sb.append("Type=file;Size=");
            sb.append(abstractC5838l.o());
            sb.append(";Modify=");
            sb.append(this.s.format(new Date(abstractC5838l.q())));
            sb.append(";Perm=");
            sb.append("r");
            if (abstractC5838l.P() == f.q0 || abstractC5838l.k()) {
                sb.append("w");
            }
            sb.append("; ");
            if (str == null) {
                sb.append(abstractC5838l.w());
            } else {
                sb.append(str);
            }
        }
        sb.append("\r\n");
    }

    private void b(F f, String str) {
        if (!f.X(f.d())) {
            ax.p045d2.b.g("Not local file location in FTP!");
            return;
        }
        C5839m c5839mD = r.d(f);
        if (!c5839mD.a()) {
            c5839mD.g(null);
            if (!c5839mD.a()) {
                return;
            }
        }
        try {
            c5839mD.S();
            AbstractC5838l abstractC5838lV0 = c5839mD.V0(f.e());
            this.u.put(f, c5839mD);
            this.v.put(f, abstractC5838lV0);
            this.x.put(f, str);
            this.w.add(f);
        } catch (C0718i unused) {
            c5839mD.P(false);
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

    private String g(AbstractC5838l abstractC5838l, boolean z, boolean z2) {
        if (abstractC5838l.w().contains("*") || abstractC5838l.w().contains(File.separator)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        String strK = k(abstractC5838l);
        if (z) {
            a(sb, false, abstractC5838l, strK);
        } else {
            if (!z2) {
                if (abstractC5838l.isDirectory()) {
                    sb.append("drwxr-xr-x 1 owner group");
                } else {
                    sb.append("-rw-r--r-- 1 owner group");
                }
                sb.append(String.format(Locale.US, "%13d", Long.valueOf(abstractC5838l.o())));
                sb.append((System.currentTimeMillis() - abstractC5838l.q() > 15552000000L ? this.r : this.q).format(new Date(abstractC5838l.q())));
            }
            sb.append(strK);
            sb.append("\r\n");
        }
        return sb.toString();
    }

    private String h(String str) throws IllegalArgumentException {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Paramater is empty");
        }
        if (n(str)) {
            return "/";
        }
        String str2 = File.separator;
        if (str.endsWith(str2)) {
            str = str.substring(0, str.length() - 1);
        }
        return str.startsWith(str2) ? str : V.M(this.f, str);
    }

    private AbstractC5838l i(String str) throws C0718i {
        F key;
        String strReplaceFirst;
        if (n(str)) {
            return this.d;
        }
        Iterator<Map.Entry<F, String>> it = this.x.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                key = null;
                strReplaceFirst = null;
                break;
            }
            Map.Entry<F, String> next = it.next();
            key = next.getKey();
            String value = next.getValue();
            AbstractC5838l abstractC5838l = this.v.get(key);
            if (abstractC5838l == null) {
                str = ":" + i.D().O() + ":" + i.D().l0();
                ax.p045d2.b.g(str);
            } else if (V.E(value, str) || V.D(value, str)) {
                String strC = abstractC5838l.C();
                strReplaceFirst = "/";
                if (!"/".equals(strC)) {
                    strReplaceFirst = str.replaceFirst(value, strC);
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
            throw new s("File not exist : " + str);
        }
        C5839m c5839m = this.u.get(key);
        if (c5839m != null) {
            return c5839m.V0(strReplaceFirst);
        }
        throw new C0718i("No file operator available : " + key.j());
    }

    private C5839m j(AbstractC5838l abstractC5838l) {
        return this.u.get(abstractC5838l.R());
    }

    private String k(AbstractC5838l abstractC5838l) {
        if (!V.B(abstractC5838l)) {
            return abstractC5838l.w();
        }
        String str = this.x.get(abstractC5838l.R());
        if (str != null) {
            return V.h(str);
        }
        ax.p045d2.b.g("root path is not available");
        return abstractC5838l.w();
    }

    private boolean m(AbstractC5838l abstractC5838l) {
        return abstractC5838l.isDirectory() && (abstractC5838l.R() == F.h ? true : abstractC5838l.h()) && o(abstractC5838l);
    }

    private boolean n(String str) {
        return "/".equals(str);
    }

    private boolean o(AbstractC5838l abstractC5838l) {
        return abstractC5838l.C().startsWith(abstractC5838l.R().e()) || "/".equals(abstractC5838l.C());
    }

    private String p(String str) {
        return String.format(Locale.US, "550 %s: No such file or directory.\r\n", str);
    }

    private boolean q(String str) {
        return this.o.contains(str);
    }

    private Socket r() {
        ServerSocket serverSocket = this.h;
        if (serverSocket != null) {
            try {
                Socket socketAccept = serverSocket.accept();
                d();
                return socketAccept;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        try {
            Socket socket = new Socket(this.i, this.j);
            socket.setSoTimeout(30000);
            return socket;
        } catch (Exception e2) {
            e2.printStackTrace();
            d();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:111:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r3v14 */
    private void t(AbstractC5838l abstractC5838l, boolean z, long j) throws Throwable {
        OutputStream outputStreamC;
        Socket socketR = r();
        if (socketR == null) {
            A("425 Error opening data socket\r\n");
            f(socketR);
            return;
        }
        if (j > 0 && abstractC5838l.o() < j) {
            A("451 File IO error\r\n");
            return;
        }
        A(String.format(Locale.US, "150 Opening %s mode data connection for writing\r\n", this.g ? "BINARY" : "ASCII"));
        InputStream inputStream = null;
        ?? r11 = 0;
        inputStream = null;
        inputStream = null;
        try {
            try {
                AbstractC5830d abstractC5830d = (AbstractC5830d) j(abstractC5838l).t();
                InputStream inputStream2 = socketR.getInputStream();
                try {
                    if (j > 0) {
                        abstractC5830d.N(abstractC5838l.C(), j);
                        outputStreamC = abstractC5830d.c(abstractC5838l.C(), true);
                    } else {
                        outputStreamC = abstractC5830d.c(abstractC5838l.C(), z);
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
                        A("226 Data transmission for writing succeeded\r\n");
                    } else {
                        A("451 Permission denied\r\n");
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
                        A("550 No such file or directory.\r\n");
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
                        A("451 File IO error\r\n");
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

    private boolean u(Socket socket, byte[] bArr) {
        return v(socket, bArr, 0, bArr.length);
    }

    private boolean v(Socket socket, byte[] bArr, int i, int i2) {
        try {
            socket.getOutputStream().write(bArr, i, i2);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void w(AbstractC5838l abstractC5838l, long j) {
        C5839m c5839mJ = j(abstractC5838l);
        Socket socketR = r();
        if (socketR == null) {
            A("425 Error opening data socket\r\n");
            f(socketR);
            return;
        }
        A("150 Sending file\r\n");
        InputStream inputStreamD1 = null;
        try {
            try {
                try {
                    inputStreamD1 = c5839mJ.d1(abstractC5838l, j);
                    byte[] bArr = new byte[65536];
                    while (true) {
                        int i = inputStreamD1.read(bArr);
                        if (i < 0) {
                            A("226 File transmission succeeded\r\n");
                            break;
                        } else if (!v(socketR, bArr, 0, i)) {
                            A("426 Data socket or network error\r\n");
                            break;
                        }
                    }
                } catch (Throwable th) {
                    if (inputStreamD1 != null) {
                        try {
                            inputStreamD1.close();
                        } catch (IOException unused) {
                        }
                    }
                    throw th;
                }
            } catch (C0718i | FileNotFoundException unused2) {
                A("550 Operation on invalid file\r\n");
                if (inputStreamD1 != null) {
                }
                f(socketR);
            }
        } catch (IOException unused3) {
            A("426 Data socket or network error\r\n");
            if (inputStreamD1 != null) {
            }
            f(socketR);
        }
        try {
            inputStreamD1.close();
        } catch (IOException unused4) {
        }
        f(socketR);
    }

    private void x(String str, String str2) {
        Socket socketR = r();
        if (socketR == null) {
            A("425 Error opening data socket\r\n");
            f(socketR);
            return;
        }
        A(String.format(Locale.US, "150 Opening %s mode data connection for %s\r\n", this.g ? "BINARY" : "ASCII", str));
        if (u(socketR, str2.getBytes())) {
            A("226 Data transmission succeeded\r\n");
        } else {
            A("426 Data socket or network error\r\n");
        }
        f(socketR);
    }

    private int y() {
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

    private void z(AbstractC5838l abstractC5838l, String str) {
        this.e = abstractC5838l;
        this.f = str;
    }

    public void A(String str) {
        B(str.getBytes());
    }

    public void B(byte[] bArr) {
        try {
            this.c.write(bArr);
            this.c.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void e() {
        d();
        for (C5839m c5839m : this.u.values()) {
            if (P.G1() && c5839m.E()) {
                c5839m.m(null);
            }
            c5839m.P(true);
        }
        this.u.clear();
        this.x.clear();
        this.v.clear();
        this.w.clear();
    }

    public void l() {
        this.o.addAll(Arrays.asList(C));
        i.D().y0();
        b(F.e, "/device");
        F f = F.h;
        u uVar = new u((C5845t) r.d(f).t(), new File("/"), f, true, false, true, false, 0L, 0L);
        this.d = uVar;
        z(uVar, "/");
        boolean zL0 = i.D().l0();
        this.y = zL0;
        if (zL0) {
            b(F.f, "/sdcard");
        }
        if (this.z && ax.p031c2.i.C(this.a)) {
            b(f, "/system");
        }
        List<F> listY = i.D().y();
        if (listY != null) {
            int i = 2;
            for (F f2 : listY) {
                if (f.g0(f2.d())) {
                    b(f2, "/sdcard" + i);
                    i++;
                }
            }
        }
        List<F> listZ = i.D().z();
        HashSet hashSet = new HashSet();
        if (listZ != null) {
            for (F f3 : listZ) {
                if (f.X(f3.d())) {
                    String strF = f3.f(this.a);
                    String str = strF;
                    int i2 = 2;
                    while (hashSet.contains(str)) {
                        str = strF + " " + i2;
                        i2++;
                    }
                    b(f3, "/" + str);
                    hashSet.add(str);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:543:0x09e0 A[Catch: all -> 0x0070, TRY_ENTER, TryCatch #41 {all -> 0x0070, blocks: (B:14:0x0062, B:16:0x0068, B:24:0x007f, B:30:0x0092, B:32:0x009e, B:35:0x00ab, B:38:0x00b3, B:39:0x00b7, B:40:0x00d0, B:45:0x00e0, B:47:0x00e4, B:48:0x00e8, B:53:0x00fa, B:54:0x0100, B:56:0x0108, B:57:0x0110, B:61:0x0120, B:65:0x012f, B:68:0x013b, B:74:0x014f, B:76:0x0157, B:79:0x0161, B:81:0x0169, B:84:0x0173, B:85:0x017a, B:86:0x0183, B:90:0x0194, B:92:0x019c, B:95:0x01a3, B:98:0x01cb, B:101:0x01d7, B:105:0x01e6, B:108:0x01f0, B:111:0x01f5, B:114:0x01fb, B:116:0x0205, B:117:0x0207, B:120:0x0210, B:121:0x0222, B:125:0x0234, B:126:0x023e, B:127:0x0246, B:132:0x0257, B:134:0x0261, B:136:0x0267, B:138:0x0272, B:141:0x0279, B:144:0x028b, B:147:0x0297, B:155:0x02b9, B:157:0x02c3, B:159:0x02c9, B:161:0x02d4, B:164:0x02db, B:167:0x02f4, B:170:0x0300, B:179:0x0321, B:181:0x0328, B:182:0x032d, B:183:0x033b, B:185:0x0345, B:187:0x034b, B:189:0x0356, B:190:0x035b, B:192:0x0365, B:194:0x0369, B:195:0x037d, B:197:0x038b, B:199:0x0399, B:202:0x03a6, B:205:0x03b2, B:206:0x03b7, B:207:0x03bf, B:209:0x03c4, B:210:0x03c9, B:211:0x03d2, B:215:0x03df, B:217:0x03ed, B:218:0x03f4, B:226:0x0417, B:228:0x0422, B:230:0x0447, B:232:0x0451, B:233:0x045a, B:229:0x042a, B:242:0x0487, B:244:0x048f, B:247:0x0497, B:249:0x049f, B:250:0x04a6, B:252:0x04ae, B:255:0x04b8, B:256:0x04bd, B:258:0x04c9, B:260:0x04d5, B:262:0x04e1, B:265:0x04e8, B:266:0x04f0, B:268:0x04f5, B:269:0x04fa, B:270:0x0503, B:272:0x050b, B:273:0x0512, B:276:0x051c, B:278:0x0526, B:281:0x0531, B:283:0x053f, B:284:0x0547, B:286:0x054d, B:289:0x0554, B:291:0x0561, B:292:0x0567, B:293:0x056b, B:299:0x0585, B:301:0x058d, B:303:0x0597, B:306:0x05a2, B:308:0x05b0, B:309:0x05b4, B:311:0x05ba, B:312:0x05c0, B:314:0x05c7, B:315:0x05cd, B:316:0x05d7, B:321:0x05ee, B:323:0x05f6, B:325:0x0600, B:328:0x060b, B:330:0x0619, B:331:0x0622, B:333:0x0628, B:336:0x062f, B:338:0x063d, B:339:0x0644, B:340:0x064b, B:341:0x0650, B:346:0x0668, B:348:0x0670, B:350:0x067a, B:353:0x0685, B:355:0x068f, B:356:0x0694, B:358:0x069a, B:359:0x06a3, B:360:0x06ac, B:365:0x06c4, B:367:0x06cc, B:369:0x06d2, B:371:0x06d8, B:374:0x06e4, B:376:0x06e8, B:377:0x06ef, B:379:0x070b, B:380:0x0710, B:382:0x0716, B:383:0x071b, B:385:0x0721, B:386:0x0728, B:389:0x0733, B:401:0x0790, B:403:0x07d0, B:391:0x0737, B:393:0x0741, B:395:0x0747, B:398:0x0757, B:400:0x078d, B:402:0x079b, B:404:0x07d5, B:409:0x07ed, B:411:0x07f5, B:413:0x07ff, B:416:0x080a, B:418:0x0814, B:419:0x081d, B:421:0x0823, B:423:0x0829, B:426:0x0830, B:427:0x0837, B:428:0x083c, B:433:0x0854, B:435:0x085c, B:447:0x0881, B:449:0x0889, B:452:0x0890, B:454:0x0897, B:456:0x08a1, B:459:0x08ac, B:461:0x08b6, B:464:0x08bd, B:467:0x08cf, B:469:0x08d9, B:470:0x08de, B:472:0x08e4, B:475:0x08ec, B:476:0x08ef, B:478:0x08f6, B:479:0x08fd, B:480:0x0902, B:482:0x0907, B:483:0x090c, B:484:0x0915, B:430:0x0841, B:431:0x0846, B:432:0x084f, B:406:0x07da, B:407:0x07df, B:408:0x07e8, B:362:0x06b1, B:363:0x06b6, B:364:0x06bf, B:343:0x0655, B:344:0x065a, B:345:0x0663, B:318:0x05db, B:319:0x05e0, B:320:0x05e9, B:296:0x0572, B:297:0x0577, B:298:0x0580, B:489:0x0923, B:491:0x0929, B:494:0x092d, B:500:0x093d, B:505:0x094d, B:512:0x0969, B:517:0x0983, B:519:0x0989, B:520:0x098e, B:521:0x0994, B:523:0x099a, B:525:0x09aa, B:543:0x09e0, B:545:0x09e6, B:562:0x0a40, B:568:0x0a52, B:569:0x0a55, B:565:0x0a4a, B:571:0x0a58, B:572:0x0a5f, B:533:0x09bb, B:535:0x09c3, B:575:0x0a6b, B:579:0x0a76, B:581:0x0a7c, B:582:0x0a80, B:599:0x0aad, B:603:0x0aba, B:235:0x046c, B:236:0x0471, B:237:0x047a, B:220:0x03fe, B:221:0x0403, B:222:0x040c, B:172:0x0305, B:173:0x030a, B:174:0x030f, B:149:0x029e, B:150:0x02a3, B:151:0x02a8, B:143:0x0286, B:548:0x09f3, B:555:0x0a05, B:556:0x0a1c, B:558:0x0a22, B:560:0x0a2e, B:561:0x0a38, B:201:0x039e, B:564:0x0a45, B:166:0x02ef), top: B:635:0x0062, inners: #1, #19, #24, #42, #43, #44, #46, #50, #51, #52, #55, #60, #65, #67, #69, #72, #71, #69, #65, #63, #62, #61, #59, #58, #57, #56, #55, #54 }] */
    /* JADX WARN: Code duplicated, block: B:545:0x09e6 A[Catch: all -> 0x0070, TRY_LEAVE, TryCatch #41 {all -> 0x0070, blocks: (B:14:0x0062, B:16:0x0068, B:24:0x007f, B:30:0x0092, B:32:0x009e, B:35:0x00ab, B:38:0x00b3, B:39:0x00b7, B:40:0x00d0, B:45:0x00e0, B:47:0x00e4, B:48:0x00e8, B:53:0x00fa, B:54:0x0100, B:56:0x0108, B:57:0x0110, B:61:0x0120, B:65:0x012f, B:68:0x013b, B:74:0x014f, B:76:0x0157, B:79:0x0161, B:81:0x0169, B:84:0x0173, B:85:0x017a, B:86:0x0183, B:90:0x0194, B:92:0x019c, B:95:0x01a3, B:98:0x01cb, B:101:0x01d7, B:105:0x01e6, B:108:0x01f0, B:111:0x01f5, B:114:0x01fb, B:116:0x0205, B:117:0x0207, B:120:0x0210, B:121:0x0222, B:125:0x0234, B:126:0x023e, B:127:0x0246, B:132:0x0257, B:134:0x0261, B:136:0x0267, B:138:0x0272, B:141:0x0279, B:144:0x028b, B:147:0x0297, B:155:0x02b9, B:157:0x02c3, B:159:0x02c9, B:161:0x02d4, B:164:0x02db, B:167:0x02f4, B:170:0x0300, B:179:0x0321, B:181:0x0328, B:182:0x032d, B:183:0x033b, B:185:0x0345, B:187:0x034b, B:189:0x0356, B:190:0x035b, B:192:0x0365, B:194:0x0369, B:195:0x037d, B:197:0x038b, B:199:0x0399, B:202:0x03a6, B:205:0x03b2, B:206:0x03b7, B:207:0x03bf, B:209:0x03c4, B:210:0x03c9, B:211:0x03d2, B:215:0x03df, B:217:0x03ed, B:218:0x03f4, B:226:0x0417, B:228:0x0422, B:230:0x0447, B:232:0x0451, B:233:0x045a, B:229:0x042a, B:242:0x0487, B:244:0x048f, B:247:0x0497, B:249:0x049f, B:250:0x04a6, B:252:0x04ae, B:255:0x04b8, B:256:0x04bd, B:258:0x04c9, B:260:0x04d5, B:262:0x04e1, B:265:0x04e8, B:266:0x04f0, B:268:0x04f5, B:269:0x04fa, B:270:0x0503, B:272:0x050b, B:273:0x0512, B:276:0x051c, B:278:0x0526, B:281:0x0531, B:283:0x053f, B:284:0x0547, B:286:0x054d, B:289:0x0554, B:291:0x0561, B:292:0x0567, B:293:0x056b, B:299:0x0585, B:301:0x058d, B:303:0x0597, B:306:0x05a2, B:308:0x05b0, B:309:0x05b4, B:311:0x05ba, B:312:0x05c0, B:314:0x05c7, B:315:0x05cd, B:316:0x05d7, B:321:0x05ee, B:323:0x05f6, B:325:0x0600, B:328:0x060b, B:330:0x0619, B:331:0x0622, B:333:0x0628, B:336:0x062f, B:338:0x063d, B:339:0x0644, B:340:0x064b, B:341:0x0650, B:346:0x0668, B:348:0x0670, B:350:0x067a, B:353:0x0685, B:355:0x068f, B:356:0x0694, B:358:0x069a, B:359:0x06a3, B:360:0x06ac, B:365:0x06c4, B:367:0x06cc, B:369:0x06d2, B:371:0x06d8, B:374:0x06e4, B:376:0x06e8, B:377:0x06ef, B:379:0x070b, B:380:0x0710, B:382:0x0716, B:383:0x071b, B:385:0x0721, B:386:0x0728, B:389:0x0733, B:401:0x0790, B:403:0x07d0, B:391:0x0737, B:393:0x0741, B:395:0x0747, B:398:0x0757, B:400:0x078d, B:402:0x079b, B:404:0x07d5, B:409:0x07ed, B:411:0x07f5, B:413:0x07ff, B:416:0x080a, B:418:0x0814, B:419:0x081d, B:421:0x0823, B:423:0x0829, B:426:0x0830, B:427:0x0837, B:428:0x083c, B:433:0x0854, B:435:0x085c, B:447:0x0881, B:449:0x0889, B:452:0x0890, B:454:0x0897, B:456:0x08a1, B:459:0x08ac, B:461:0x08b6, B:464:0x08bd, B:467:0x08cf, B:469:0x08d9, B:470:0x08de, B:472:0x08e4, B:475:0x08ec, B:476:0x08ef, B:478:0x08f6, B:479:0x08fd, B:480:0x0902, B:482:0x0907, B:483:0x090c, B:484:0x0915, B:430:0x0841, B:431:0x0846, B:432:0x084f, B:406:0x07da, B:407:0x07df, B:408:0x07e8, B:362:0x06b1, B:363:0x06b6, B:364:0x06bf, B:343:0x0655, B:344:0x065a, B:345:0x0663, B:318:0x05db, B:319:0x05e0, B:320:0x05e9, B:296:0x0572, B:297:0x0577, B:298:0x0580, B:489:0x0923, B:491:0x0929, B:494:0x092d, B:500:0x093d, B:505:0x094d, B:512:0x0969, B:517:0x0983, B:519:0x0989, B:520:0x098e, B:521:0x0994, B:523:0x099a, B:525:0x09aa, B:543:0x09e0, B:545:0x09e6, B:562:0x0a40, B:568:0x0a52, B:569:0x0a55, B:565:0x0a4a, B:571:0x0a58, B:572:0x0a5f, B:533:0x09bb, B:535:0x09c3, B:575:0x0a6b, B:579:0x0a76, B:581:0x0a7c, B:582:0x0a80, B:599:0x0aad, B:603:0x0aba, B:235:0x046c, B:236:0x0471, B:237:0x047a, B:220:0x03fe, B:221:0x0403, B:222:0x040c, B:172:0x0305, B:173:0x030a, B:174:0x030f, B:149:0x029e, B:150:0x02a3, B:151:0x02a8, B:143:0x0286, B:548:0x09f3, B:555:0x0a05, B:556:0x0a1c, B:558:0x0a22, B:560:0x0a2e, B:561:0x0a38, B:201:0x039e, B:564:0x0a45, B:166:0x02ef), top: B:635:0x0062, inners: #1, #19, #24, #42, #43, #44, #46, #50, #51, #52, #55, #60, #65, #67, #69, #72, #71, #69, #65, #63, #62, #61, #59, #58, #57, #56, #55, #54 }] */
    /* JADX WARN: Code duplicated, block: B:550:0x09fb  */
    /* JADX WARN: Code duplicated, block: B:554:0x0a02  */
    /* JADX WARN: Code duplicated, block: B:558:0x0a22 A[Catch: all -> 0x09ff, IOException -> 0x0a45, TryCatch #44 {IOException -> 0x0a45, blocks: (B:548:0x09f3, B:555:0x0a05, B:556:0x0a1c, B:558:0x0a22, B:560:0x0a2e, B:561:0x0a38), top: B:640:0x09f3, outer: #41 }] */
    /* JADX WARN: Code duplicated, block: B:570:0x0a56 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:571:0x0a58 A[Catch: all -> 0x0070, TryCatch #41 {all -> 0x0070, blocks: (B:14:0x0062, B:16:0x0068, B:24:0x007f, B:30:0x0092, B:32:0x009e, B:35:0x00ab, B:38:0x00b3, B:39:0x00b7, B:40:0x00d0, B:45:0x00e0, B:47:0x00e4, B:48:0x00e8, B:53:0x00fa, B:54:0x0100, B:56:0x0108, B:57:0x0110, B:61:0x0120, B:65:0x012f, B:68:0x013b, B:74:0x014f, B:76:0x0157, B:79:0x0161, B:81:0x0169, B:84:0x0173, B:85:0x017a, B:86:0x0183, B:90:0x0194, B:92:0x019c, B:95:0x01a3, B:98:0x01cb, B:101:0x01d7, B:105:0x01e6, B:108:0x01f0, B:111:0x01f5, B:114:0x01fb, B:116:0x0205, B:117:0x0207, B:120:0x0210, B:121:0x0222, B:125:0x0234, B:126:0x023e, B:127:0x0246, B:132:0x0257, B:134:0x0261, B:136:0x0267, B:138:0x0272, B:141:0x0279, B:144:0x028b, B:147:0x0297, B:155:0x02b9, B:157:0x02c3, B:159:0x02c9, B:161:0x02d4, B:164:0x02db, B:167:0x02f4, B:170:0x0300, B:179:0x0321, B:181:0x0328, B:182:0x032d, B:183:0x033b, B:185:0x0345, B:187:0x034b, B:189:0x0356, B:190:0x035b, B:192:0x0365, B:194:0x0369, B:195:0x037d, B:197:0x038b, B:199:0x0399, B:202:0x03a6, B:205:0x03b2, B:206:0x03b7, B:207:0x03bf, B:209:0x03c4, B:210:0x03c9, B:211:0x03d2, B:215:0x03df, B:217:0x03ed, B:218:0x03f4, B:226:0x0417, B:228:0x0422, B:230:0x0447, B:232:0x0451, B:233:0x045a, B:229:0x042a, B:242:0x0487, B:244:0x048f, B:247:0x0497, B:249:0x049f, B:250:0x04a6, B:252:0x04ae, B:255:0x04b8, B:256:0x04bd, B:258:0x04c9, B:260:0x04d5, B:262:0x04e1, B:265:0x04e8, B:266:0x04f0, B:268:0x04f5, B:269:0x04fa, B:270:0x0503, B:272:0x050b, B:273:0x0512, B:276:0x051c, B:278:0x0526, B:281:0x0531, B:283:0x053f, B:284:0x0547, B:286:0x054d, B:289:0x0554, B:291:0x0561, B:292:0x0567, B:293:0x056b, B:299:0x0585, B:301:0x058d, B:303:0x0597, B:306:0x05a2, B:308:0x05b0, B:309:0x05b4, B:311:0x05ba, B:312:0x05c0, B:314:0x05c7, B:315:0x05cd, B:316:0x05d7, B:321:0x05ee, B:323:0x05f6, B:325:0x0600, B:328:0x060b, B:330:0x0619, B:331:0x0622, B:333:0x0628, B:336:0x062f, B:338:0x063d, B:339:0x0644, B:340:0x064b, B:341:0x0650, B:346:0x0668, B:348:0x0670, B:350:0x067a, B:353:0x0685, B:355:0x068f, B:356:0x0694, B:358:0x069a, B:359:0x06a3, B:360:0x06ac, B:365:0x06c4, B:367:0x06cc, B:369:0x06d2, B:371:0x06d8, B:374:0x06e4, B:376:0x06e8, B:377:0x06ef, B:379:0x070b, B:380:0x0710, B:382:0x0716, B:383:0x071b, B:385:0x0721, B:386:0x0728, B:389:0x0733, B:401:0x0790, B:403:0x07d0, B:391:0x0737, B:393:0x0741, B:395:0x0747, B:398:0x0757, B:400:0x078d, B:402:0x079b, B:404:0x07d5, B:409:0x07ed, B:411:0x07f5, B:413:0x07ff, B:416:0x080a, B:418:0x0814, B:419:0x081d, B:421:0x0823, B:423:0x0829, B:426:0x0830, B:427:0x0837, B:428:0x083c, B:433:0x0854, B:435:0x085c, B:447:0x0881, B:449:0x0889, B:452:0x0890, B:454:0x0897, B:456:0x08a1, B:459:0x08ac, B:461:0x08b6, B:464:0x08bd, B:467:0x08cf, B:469:0x08d9, B:470:0x08de, B:472:0x08e4, B:475:0x08ec, B:476:0x08ef, B:478:0x08f6, B:479:0x08fd, B:480:0x0902, B:482:0x0907, B:483:0x090c, B:484:0x0915, B:430:0x0841, B:431:0x0846, B:432:0x084f, B:406:0x07da, B:407:0x07df, B:408:0x07e8, B:362:0x06b1, B:363:0x06b6, B:364:0x06bf, B:343:0x0655, B:344:0x065a, B:345:0x0663, B:318:0x05db, B:319:0x05e0, B:320:0x05e9, B:296:0x0572, B:297:0x0577, B:298:0x0580, B:489:0x0923, B:491:0x0929, B:494:0x092d, B:500:0x093d, B:505:0x094d, B:512:0x0969, B:517:0x0983, B:519:0x0989, B:520:0x098e, B:521:0x0994, B:523:0x099a, B:525:0x09aa, B:543:0x09e0, B:545:0x09e6, B:562:0x0a40, B:568:0x0a52, B:569:0x0a55, B:565:0x0a4a, B:571:0x0a58, B:572:0x0a5f, B:533:0x09bb, B:535:0x09c3, B:575:0x0a6b, B:579:0x0a76, B:581:0x0a7c, B:582:0x0a80, B:599:0x0aad, B:603:0x0aba, B:235:0x046c, B:236:0x0471, B:237:0x047a, B:220:0x03fe, B:221:0x0403, B:222:0x040c, B:172:0x0305, B:173:0x030a, B:174:0x030f, B:149:0x029e, B:150:0x02a3, B:151:0x02a8, B:143:0x0286, B:548:0x09f3, B:555:0x0a05, B:556:0x0a1c, B:558:0x0a22, B:560:0x0a2e, B:561:0x0a38, B:201:0x039e, B:564:0x0a45, B:166:0x02ef), top: B:635:0x0062, inners: #1, #19, #24, #42, #43, #44, #46, #50, #51, #52, #55, #60, #65, #67, #69, #72, #71, #69, #65, #63, #62, #61, #59, #58, #57, #56, #55, #54 }] */
    /* JADX WARN: Code duplicated, block: B:572:0x0a5f A[Catch: all -> 0x0070, TryCatch #41 {all -> 0x0070, blocks: (B:14:0x0062, B:16:0x0068, B:24:0x007f, B:30:0x0092, B:32:0x009e, B:35:0x00ab, B:38:0x00b3, B:39:0x00b7, B:40:0x00d0, B:45:0x00e0, B:47:0x00e4, B:48:0x00e8, B:53:0x00fa, B:54:0x0100, B:56:0x0108, B:57:0x0110, B:61:0x0120, B:65:0x012f, B:68:0x013b, B:74:0x014f, B:76:0x0157, B:79:0x0161, B:81:0x0169, B:84:0x0173, B:85:0x017a, B:86:0x0183, B:90:0x0194, B:92:0x019c, B:95:0x01a3, B:98:0x01cb, B:101:0x01d7, B:105:0x01e6, B:108:0x01f0, B:111:0x01f5, B:114:0x01fb, B:116:0x0205, B:117:0x0207, B:120:0x0210, B:121:0x0222, B:125:0x0234, B:126:0x023e, B:127:0x0246, B:132:0x0257, B:134:0x0261, B:136:0x0267, B:138:0x0272, B:141:0x0279, B:144:0x028b, B:147:0x0297, B:155:0x02b9, B:157:0x02c3, B:159:0x02c9, B:161:0x02d4, B:164:0x02db, B:167:0x02f4, B:170:0x0300, B:179:0x0321, B:181:0x0328, B:182:0x032d, B:183:0x033b, B:185:0x0345, B:187:0x034b, B:189:0x0356, B:190:0x035b, B:192:0x0365, B:194:0x0369, B:195:0x037d, B:197:0x038b, B:199:0x0399, B:202:0x03a6, B:205:0x03b2, B:206:0x03b7, B:207:0x03bf, B:209:0x03c4, B:210:0x03c9, B:211:0x03d2, B:215:0x03df, B:217:0x03ed, B:218:0x03f4, B:226:0x0417, B:228:0x0422, B:230:0x0447, B:232:0x0451, B:233:0x045a, B:229:0x042a, B:242:0x0487, B:244:0x048f, B:247:0x0497, B:249:0x049f, B:250:0x04a6, B:252:0x04ae, B:255:0x04b8, B:256:0x04bd, B:258:0x04c9, B:260:0x04d5, B:262:0x04e1, B:265:0x04e8, B:266:0x04f0, B:268:0x04f5, B:269:0x04fa, B:270:0x0503, B:272:0x050b, B:273:0x0512, B:276:0x051c, B:278:0x0526, B:281:0x0531, B:283:0x053f, B:284:0x0547, B:286:0x054d, B:289:0x0554, B:291:0x0561, B:292:0x0567, B:293:0x056b, B:299:0x0585, B:301:0x058d, B:303:0x0597, B:306:0x05a2, B:308:0x05b0, B:309:0x05b4, B:311:0x05ba, B:312:0x05c0, B:314:0x05c7, B:315:0x05cd, B:316:0x05d7, B:321:0x05ee, B:323:0x05f6, B:325:0x0600, B:328:0x060b, B:330:0x0619, B:331:0x0622, B:333:0x0628, B:336:0x062f, B:338:0x063d, B:339:0x0644, B:340:0x064b, B:341:0x0650, B:346:0x0668, B:348:0x0670, B:350:0x067a, B:353:0x0685, B:355:0x068f, B:356:0x0694, B:358:0x069a, B:359:0x06a3, B:360:0x06ac, B:365:0x06c4, B:367:0x06cc, B:369:0x06d2, B:371:0x06d8, B:374:0x06e4, B:376:0x06e8, B:377:0x06ef, B:379:0x070b, B:380:0x0710, B:382:0x0716, B:383:0x071b, B:385:0x0721, B:386:0x0728, B:389:0x0733, B:401:0x0790, B:403:0x07d0, B:391:0x0737, B:393:0x0741, B:395:0x0747, B:398:0x0757, B:400:0x078d, B:402:0x079b, B:404:0x07d5, B:409:0x07ed, B:411:0x07f5, B:413:0x07ff, B:416:0x080a, B:418:0x0814, B:419:0x081d, B:421:0x0823, B:423:0x0829, B:426:0x0830, B:427:0x0837, B:428:0x083c, B:433:0x0854, B:435:0x085c, B:447:0x0881, B:449:0x0889, B:452:0x0890, B:454:0x0897, B:456:0x08a1, B:459:0x08ac, B:461:0x08b6, B:464:0x08bd, B:467:0x08cf, B:469:0x08d9, B:470:0x08de, B:472:0x08e4, B:475:0x08ec, B:476:0x08ef, B:478:0x08f6, B:479:0x08fd, B:480:0x0902, B:482:0x0907, B:483:0x090c, B:484:0x0915, B:430:0x0841, B:431:0x0846, B:432:0x084f, B:406:0x07da, B:407:0x07df, B:408:0x07e8, B:362:0x06b1, B:363:0x06b6, B:364:0x06bf, B:343:0x0655, B:344:0x065a, B:345:0x0663, B:318:0x05db, B:319:0x05e0, B:320:0x05e9, B:296:0x0572, B:297:0x0577, B:298:0x0580, B:489:0x0923, B:491:0x0929, B:494:0x092d, B:500:0x093d, B:505:0x094d, B:512:0x0969, B:517:0x0983, B:519:0x0989, B:520:0x098e, B:521:0x0994, B:523:0x099a, B:525:0x09aa, B:543:0x09e0, B:545:0x09e6, B:562:0x0a40, B:568:0x0a52, B:569:0x0a55, B:565:0x0a4a, B:571:0x0a58, B:572:0x0a5f, B:533:0x09bb, B:535:0x09c3, B:575:0x0a6b, B:579:0x0a76, B:581:0x0a7c, B:582:0x0a80, B:599:0x0aad, B:603:0x0aba, B:235:0x046c, B:236:0x0471, B:237:0x047a, B:220:0x03fe, B:221:0x0403, B:222:0x040c, B:172:0x0305, B:173:0x030a, B:174:0x030f, B:149:0x029e, B:150:0x02a3, B:151:0x02a8, B:143:0x0286, B:548:0x09f3, B:555:0x0a05, B:556:0x0a1c, B:558:0x0a22, B:560:0x0a2e, B:561:0x0a38, B:201:0x039e, B:564:0x0a45, B:166:0x02ef), top: B:635:0x0062, inners: #1, #19, #24, #42, #43, #44, #46, #50, #51, #52, #55, #60, #65, #67, #69, #72, #71, #69, #65, #63, #62, #61, #59, #58, #57, #56, #55, #54 }] */
    /* JADX WARN: Code duplicated, block: B:586:0x0a8a  */
    /* JADX WARN: Code duplicated, block: B:640:0x09f3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:709:0x0a2e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:711:0x0a1c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:718:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v30, types: [com.alphainventor.filemanager.file.l] */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v6 */
    /* JADX WARN: Type inference failed for: r6v8 */
    public void s(String str, C0510a c0510a, boolean z) throws Throwable {
        Throwable th;
        boolean z2;
        String strH;
        String str2;
        long j;
        long j2;
        String strH2;
        C5839m c5839mJ;
        AbstractC5838l abstractC5838l;
        List<AbstractC5838l> list;
        List<AbstractC5838l> arrayList;
        boolean z3;
        Socket socketR;
        String str3;
        OutputStream outputStream;
        Iterator<AbstractC5838l> it;
        String strG;
        Object objS;
        String str4;
        int iY;
        String str5 = "550 MDTM request on an invalid file\r\n";
        String[] strArrSplit = str.split(" ", 2);
        if (strArrSplit == null || strArrSplit.length == 0) {
            A("502 Command not recognized\r\n");
            return;
        }
        B.fine("FTP INPUT : " + str);
        String upperCase = strArrSplit[0].trim().toUpperCase();
        String strTrim = strArrSplit.length > 1 ? strArrSplit[1].trim() : HttpUrl.FRAGMENT_ENCODE_SET;
        try {
            if (!this.k) {
                try {
                    if (q(upperCase)) {
                        A("530 Login incorrect\r\n");
                        this.A = 0L;
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if ("NOOP".equals(upperCase)) {
                A("200 NOOP OK\r\n");
            } else if ("OPTS".equals(upperCase)) {
                if ("UTF8 ON".equals(strTrim.toUpperCase()) || "UTF-8 ON".equals(strTrim.toUpperCase())) {
                    A("200 UTF8 OK\r\n");
                } else if (strTrim.startsWith("MLST")) {
                    A("501 Unsupported Options\r\n");
                } else {
                    c.h().g().c("UNSUPPORTED FTP OPTIONS").h(str).i();
                    A("501 Unsupported Options\r\n");
                }
            } else if ("USER".equals(upperCase)) {
                if (this.k) {
                    A("230 Access granted\r\n");
                } else {
                    this.l = strTrim;
                    A("331 Password required\r\n");
                }
            } else if (!"PASS".equals(upperCase)) {
                if (!"SYST".equals(upperCase)) {
                    if ("PWD".equals(upperCase)) {
                        try {
                            A(String.format(Locale.US, "257 \"%s\"\r\n", this.f));
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } else if ("TYPE".equals(upperCase)) {
                        if ("I".equals(strTrim) || "L 8".equals(strTrim)) {
                            this.g = true;
                            A("200 Binary type set\r\n");
                        } else if ("A".equals(strTrim) || "A N".equals(strTrim)) {
                            this.g = false;
                            A("200 ASCII type set\r\n");
                        } else {
                            A("503 Unknown TYPE command\r\n");
                        }
                    } else if ("PASV".equals(upperCase)) {
                        InetAddress localAddress = this.b.getLocalAddress();
                        if (localAddress == null || (iY = y()) <= 0) {
                            A("502 Cannot open a port\r\n");
                        } else {
                            try {
                                A(String.format(Locale.US, "227 Entering Passive Mode (%s,%d,%d).\r\n", localAddress.getHostAddress().replace('.', ','), Integer.valueOf(iY / 256), Integer.valueOf(iY % 256)));
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                    } else if ("PORT".equals(upperCase)) {
                        String[] strArrSplit2 = strTrim.split(",");
                        if (strArrSplit2 == null || strArrSplit2.length != 6) {
                            A("550 Invalid PORT arguments\r\n");
                        } else {
                            byte[] bArr = new byte[4];
                            for (int i = 0; i < 4; i++) {
                                try {
                                    int i2 = Integer.parseInt(strArrSplit2[i]);
                                    if (i2 >= 128) {
                                        i2 -= 256;
                                    }
                                    bArr[i] = (byte) i2;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    A("550 Invalid PORT arguments\r\n");
                                }
                            }
                            this.j = (Integer.parseInt(strArrSplit2[4]) * 256) + Integer.parseInt(strArrSplit2[5]);
                            try {
                                this.i = InetAddress.getByAddress(bArr);
                                A("200 PORT OK\r\n");
                                d();
                            } catch (UnknownHostException e2) {
                                e2.printStackTrace();
                                A("550 Unknown host\r\n");
                            }
                        }
                    } else {
                        String str6 = "550 Unknown error.\r\n";
                        String strI = "501 Syntax error\r\n";
                        if ("SIZE".equals(upperCase)) {
                            try {
                                try {
                                    AbstractC5838l abstractC5838lI = i(h(strTrim));
                                    if (abstractC5838lI != null && !abstractC5838lI.n()) {
                                        A(p(strTrim));
                                    } else if (abstractC5838lI == null || abstractC5838lI.isDirectory()) {
                                        A("550 SIZE request on an invalid file\r\n");
                                    } else {
                                        try {
                                            A(String.format(Locale.US, "213 %d\r\n", Long.valueOf(abstractC5838lI.o())));
                                        } catch (Throwable th5) {
                                            th = th5;
                                        }
                                    }
                                } catch (s unused) {
                                    A(p(strTrim));
                                } catch (IllegalArgumentException unused2) {
                                    A(strI);
                                }
                            } catch (C0718i unused3) {
                                A("550 Unknown error.\r\n");
                            }
                        } else if ("MDTM".equals(upperCase)) {
                            try {
                                try {
                                    AbstractC5838l abstractC5838lI2 = i(h(strTrim));
                                    if (abstractC5838lI2 != null && !abstractC5838lI2.n()) {
                                        A(p(strTrim));
                                    } else if (abstractC5838lI2 == null || abstractC5838lI2.isDirectory()) {
                                        A("550 MDTM request on an invalid file\r\n");
                                    } else {
                                        str6 = "213 %s\r\n";
                                        try {
                                            A(String.format(Locale.US, "213 %s\r\n", this.s.format(new Date(abstractC5838lI2.q()))));
                                        } catch (Throwable th6) {
                                            th = th6;
                                        }
                                    }
                                } catch (IllegalArgumentException unused4) {
                                    A("501 Syntax error\r\n");
                                }
                            } catch (s unused5) {
                                A(p(strTrim));
                            } catch (C0718i unused6) {
                                A(str5);
                            }
                        } else if ("MFMT".equals(upperCase)) {
                            String[] strArrSplit3 = strTrim.split(" ", 2);
                            if (strArrSplit3.length < 2) {
                                A("501 Syntax error\r\n");
                            } else {
                                String strTrim2 = strArrSplit3[0].trim();
                                String strTrim3 = strArrSplit3[1].trim();
                                try {
                                    try {
                                        AbstractC5838l abstractC5838lI3 = i(h(strTrim3));
                                        if (abstractC5838lI3 != null && !abstractC5838lI3.n()) {
                                            A(p(strTrim));
                                        } else if (abstractC5838lI3 == null) {
                                            A("550 MFMT failed to set time\r\n");
                                        } else {
                                            C5839m c5839mJ2 = j(abstractC5838lI3);
                                            if (C0740v.H(abstractC5838lI3)) {
                                                if (this.t == null) {
                                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
                                                    this.t = simpleDateFormat;
                                                    simpleDateFormat.setTimeZone(DesugarTimeZone.getTimeZone("UTC"));
                                                }
                                                Date date = this.t.parse(strTrim2);
                                                AbstractC5837k abstractC5837kT = c5839mJ2.t();
                                                if (!(abstractC5837kT instanceof C5845t)) {
                                                    ax.p045d2.b.f();
                                                    A("550 MFMT failed to set time\r\n");
                                                } else if (((C5845t) abstractC5837kT).F1((u) abstractC5838lI3, date.getTime())) {
                                                    try {
                                                        A(String.format(Locale.US, "213 Modify=%s; %s\r\n", strTrim2, strTrim3));
                                                    } catch (Throwable th7) {
                                                        th = th7;
                                                    }
                                                } else {
                                                    A("550 MFMT failed to set time\r\n");
                                                }
                                            } else {
                                                A("550 MFMT failed to set time\r\n");
                                            }
                                        }
                                    } catch (s unused7) {
                                        A(p(strTrim));
                                    } catch (C0718i unused8) {
                                        A("550 MFMT failed to set time\r\n");
                                    }
                                } catch (IllegalArgumentException | ParseException unused9) {
                                    A("501 Syntax error\r\n");
                                }
                            }
                        } else if ("CWD".equals(upperCase)) {
                            try {
                                try {
                                    String strH3 = h(strTrim);
                                    AbstractC5838l abstractC5838lI4 = i(strH3);
                                    if (m(abstractC5838lI4)) {
                                        z(abstractC5838lI4, strH3);
                                        A("250 CWD OK\r\n");
                                    } else {
                                        A("550 CWD to the invalid path\r\n");
                                    }
                                } catch (IllegalArgumentException unused10) {
                                    A("501 Syntax error\r\n");
                                }
                            } catch (s unused11) {
                                A(p(strTrim));
                            } catch (C0718i unused12) {
                                A(str6);
                            }
                        } else {
                            if (!"MLST".equals(upperCase)) {
                                str5 = "NLST";
                                if ("MLSD".equals(upperCase) || "LIST".equals(upperCase) || "NLST".equals(upperCase)) {
                                    while (true) {
                                        if (!strTrim.startsWith("-")) {
                                            str2 = strTrim;
                                            break;
                                        }
                                        String[] strArrSplit4 = strTrim.split(" ", 2);
                                        if (strArrSplit4 != null && strArrSplit4.length >= 2) {
                                            strTrim = strArrSplit4[1];
                                        }
                                        str2 = HttpUrl.FRAGMENT_ENCODE_SET;
                                        break;
                                    }
                                    if (str2.contains("*")) {
                                        A("550 LIST/NLST does not support wildcards\r\n");
                                        this.A = 0L;
                                        return;
                                    }
                                    if (str2.length() == 0) {
                                        strH2 = this.f;
                                        abstractC5838l = this.e;
                                        c5839mJ = j(abstractC5838l);
                                    } else {
                                        try {
                                            try {
                                                strH2 = h(str2);
                                                try {
                                                    AbstractC5838l abstractC5838lI5 = i(strH2);
                                                    c5839mJ = j(abstractC5838lI5);
                                                    abstractC5838l = abstractC5838lI5;
                                                } catch (s unused13) {
                                                    j2 = 0;
                                                    A(p(str2));
                                                    this.A = j2;
                                                    return;
                                                } catch (IllegalArgumentException unused14) {
                                                    j = 0;
                                                    A("501 Syntax error\r\n");
                                                    this.A = j;
                                                    return;
                                                }
                                            } catch (s unused15) {
                                                j2 = 0;
                                            } catch (IllegalArgumentException unused16) {
                                                j = 0;
                                            }
                                        } catch (C0718i unused17) {
                                            try {
                                                A("550 Unknown error.\r\n");
                                                this.A = 0L;
                                                return;
                                            } catch (Throwable th8) {
                                                th = th8;
                                            }
                                        }
                                    }
                                    if (!abstractC5838l.n()) {
                                        A(p(str2));
                                        this.A = 0L;
                                        return;
                                    }
                                    boolean zEquals = "NLST".equals(upperCase);
                                    boolean zEquals2 = "MLSD".equals(upperCase);
                                    if (abstractC5838l.isDirectory()) {
                                        try {
                                            if (n(strH2)) {
                                                try {
                                                    arrayList = new ArrayList<>();
                                                    try {
                                                        Iterator<F> it2 = this.w.iterator();
                                                        while (it2.hasNext()) {
                                                            AbstractC5838l abstractC5838l2 = this.v.get(it2.next());
                                                            if (abstractC5838l2 != null) {
                                                                arrayList.add(abstractC5838l2);
                                                            }
                                                        }
                                                    } catch (C0713d unused18) {
                                                        list = null;
                                                        z3 = true;
                                                        if (arrayList != null) {
                                                            socketR = r();
                                                            if (socketR == null) {
                                                                f(socketR);
                                                                A("425 Error opening data socket\r\n");
                                                                this.A = 0L;
                                                                return;
                                                            }
                                                            try {
                                                                try {
                                                                    Locale locale = Locale.US;
                                                                    if (this.g) {
                                                                        str3 = "BINARY";
                                                                    } else {
                                                                        str3 = "ASCII";
                                                                    }
                                                                    A(String.format(locale, "150 Opening %s mode data connection for %s\r\n", str3, upperCase));
                                                                    outputStream = socketR.getOutputStream();
                                                                    it = arrayList.iterator();
                                                                    while (it.hasNext()) {
                                                                        strG = g(it.next(), zEquals2, zEquals);
                                                                        if (strG != null) {
                                                                            byte[] bytes = strG.getBytes();
                                                                            outputStream.write(bytes, 0, bytes.length);
                                                                        }
                                                                    }
                                                                    outputStream.flush();
                                                                    A("226 Data transmission succeeded\r\n");
                                                                    f(socketR);
                                                                } catch (IOException unused19) {
                                                                    A("426 Data socket or network error\r\n");
                                                                    f(socketR);
                                                                    this.A = 0L;
                                                                    return;
                                                                }
                                                            } catch (Throwable th9) {
                                                                f(socketR);
                                                                throw th9;
                                                            }
                                                        } else if (z3) {
                                                            A("550 Permission denied\r\n");
                                                        } else {
                                                            A("550 Failed to list files\r\n");
                                                        }
                                                        objS = list;
                                                        z2 = false;
                                                        str4 = objS;
                                                        c0510a.a = str4;
                                                        if (z2) {
                                                        }
                                                        this.A = 0L;
                                                    } catch (C0718i unused20) {
                                                    }
                                                } catch (C0713d unused21) {
                                                    arrayList = null;
                                                } catch (C0718i unused22) {
                                                    arrayList = null;
                                                }
                                                list = null;
                                            } else {
                                                List<AbstractC5838l> listG1 = c5839mJ.g1(abstractC5838l);
                                                list = null;
                                                try {
                                                    arrayList = C0733n.e(C0740v.e(listG1, null, this.z, true), C0733n.b("NameUp"));
                                                } catch (C0713d unused23) {
                                                    arrayList = list;
                                                    z3 = true;
                                                    if (arrayList != null) {
                                                        socketR = r();
                                                        if (socketR == null) {
                                                            f(socketR);
                                                            A("425 Error opening data socket\r\n");
                                                            this.A = 0L;
                                                            return;
                                                        }
                                                        Locale locale2 = Locale.US;
                                                        if (this.g) {
                                                            str3 = "BINARY";
                                                        } else {
                                                            str3 = "ASCII";
                                                        }
                                                        A(String.format(locale2, "150 Opening %s mode data connection for %s\r\n", str3, upperCase));
                                                        outputStream = socketR.getOutputStream();
                                                        it = arrayList.iterator();
                                                        while (it.hasNext()) {
                                                            strG = g(it.next(), zEquals2, zEquals);
                                                            if (strG != null) {
                                                                byte[] bytes2 = strG.getBytes();
                                                                outputStream.write(bytes2, 0, bytes2.length);
                                                            }
                                                        }
                                                        outputStream.flush();
                                                        A("226 Data transmission succeeded\r\n");
                                                        f(socketR);
                                                    } else if (z3) {
                                                        A("550 Permission denied\r\n");
                                                    } else {
                                                        A("550 Failed to list files\r\n");
                                                    }
                                                    objS = list;
                                                    z2 = false;
                                                    str4 = objS;
                                                    c0510a.a = str4;
                                                    if (z2) {
                                                        this.A = 0L;
                                                    }
                                                } catch (C0718i unused24) {
                                                    arrayList = list;
                                                }
                                            }
                                        } catch (C0713d unused25) {
                                            list = null;
                                        } catch (C0718i unused26) {
                                            list = null;
                                        }
                                        z3 = false;
                                        if (arrayList != null) {
                                            socketR = r();
                                            if (socketR == null) {
                                                f(socketR);
                                                A("425 Error opening data socket\r\n");
                                                this.A = 0L;
                                                return;
                                            }
                                            Locale locale3 = Locale.US;
                                            if (this.g) {
                                                str3 = "BINARY";
                                            } else {
                                                str3 = "ASCII";
                                            }
                                            A(String.format(locale3, "150 Opening %s mode data connection for %s\r\n", str3, upperCase));
                                            outputStream = socketR.getOutputStream();
                                            it = arrayList.iterator();
                                            while (it.hasNext()) {
                                                strG = g(it.next(), zEquals2, zEquals);
                                                if (strG != null) {
                                                    byte[] bytes3 = strG.getBytes();
                                                    outputStream.write(bytes3, 0, bytes3.length);
                                                }
                                            }
                                            outputStream.flush();
                                            A("226 Data transmission succeeded\r\n");
                                            f(socketR);
                                        } else if (z3) {
                                            A("550 Permission denied\r\n");
                                        } else {
                                            A("550 Failed to list files\r\n");
                                        }
                                    } else {
                                        list = null;
                                        if (zEquals2) {
                                            A("501 Not a directory\r\n");
                                            this.A = 0L;
                                            return;
                                        } else {
                                            String strG2 = g(abstractC5838l, false, zEquals);
                                            if (strG2 == null) {
                                                A("550 Failed to list files\r\n");
                                            } else {
                                                x(upperCase, strG2);
                                            }
                                        }
                                    }
                                } else if ("QUIT".equals(upperCase)) {
                                    A("221 Goodbye\r\n");
                                } else if (!"CDUP".equals(upperCase)) {
                                    if (!"FEAT".equals(upperCase)) {
                                        if ("DELE".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strH4 = h(strTrim);
                                                    if (n(strH4) || n(V.r(strH4))) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC5838l abstractC5838lI6 = i(strH4);
                                                        C5839m c5839mJ3 = j(abstractC5838lI6);
                                                        if (!abstractC5838lI6.n()) {
                                                            A(p(strTrim));
                                                        } else if (abstractC5838lI6.isDirectory() || !o(abstractC5838lI6)) {
                                                            A("550 Operation on invalid file\r\n");
                                                        } else {
                                                            try {
                                                                c5839mJ3.l1(abstractC5838lI6);
                                                                A("250 File successfully deleted\r\n");
                                                                objS = abstractC5838lI6.S();
                                                            } catch (C0718i unused27) {
                                                                A("450 Error deleting file\r\n");
                                                                objS = null;
                                                            }
                                                        }
                                                    }
                                                    objS = null;
                                                } catch (IllegalArgumentException unused28) {
                                                    A("501 Syntax error\r\n");
                                                    list = null;
                                                    objS = list;
                                                }
                                            } catch (s unused29) {
                                                A(p(strTrim));
                                                list = null;
                                                objS = list;
                                            } catch (C0718i unused30) {
                                                A("550 Unknown error.\r\n");
                                                list = null;
                                                objS = list;
                                            }
                                        } else if ("MKD".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strH5 = h(strTrim);
                                                    if (n(strH5) || n(V.r(strH5))) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC5838l abstractC5838lI7 = i(strH5);
                                                        C5839m c5839mJ4 = j(abstractC5838lI7);
                                                        if (!o(abstractC5838lI7)) {
                                                            A("550 Operation on invalid file\r\n");
                                                        } else if (abstractC5838lI7.n()) {
                                                            A("550 Already exists\r\n");
                                                        } else if (c5839mJ4.j(abstractC5838lI7, false)) {
                                                            A("250 Directory created\r\n");
                                                            objS = abstractC5838lI7.S();
                                                        } else {
                                                            A("550 MKD denied\r\n");
                                                        }
                                                    }
                                                    objS = null;
                                                } catch (s unused31) {
                                                    A(p(strTrim));
                                                    list = null;
                                                    objS = list;
                                                } catch (C0718i unused32) {
                                                    A("550 Unknown error.\r\n");
                                                    list = null;
                                                    objS = list;
                                                }
                                            } catch (IllegalArgumentException unused33) {
                                                A("501 Syntax error\r\n");
                                                list = null;
                                                objS = list;
                                            }
                                        } else if ("RMD".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strH6 = h(strTrim);
                                                    if (n(strH6) || n(V.r(strH6))) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC5838l abstractC5838lI8 = i(strH6);
                                                        C5839m c5839mJ5 = j(abstractC5838lI8);
                                                        if (!abstractC5838lI8.n()) {
                                                            A(p(strTrim));
                                                        } else if (abstractC5838lI8.isDirectory() && o(abstractC5838lI8)) {
                                                            try {
                                                                c5839mJ5.l1(abstractC5838lI8);
                                                                A("250 Directory removed\r\n");
                                                                objS = abstractC5838lI8.S();
                                                            } catch (C0719j unused34) {
                                                                A("550 Directory not empty\r\n");
                                                                objS = null;
                                                            } catch (C0718i unused35) {
                                                                A("550 RMD failed\r\n");
                                                                objS = null;
                                                            }
                                                        } else {
                                                            A("550 Operation on invalid file\r\n");
                                                        }
                                                    }
                                                    objS = null;
                                                } catch (s unused36) {
                                                    A(p(strTrim));
                                                    list = null;
                                                    objS = list;
                                                } catch (C0718i unused37) {
                                                    A("550 Unknown error.\r\n");
                                                    list = null;
                                                    objS = list;
                                                }
                                            } catch (IllegalArgumentException unused38) {
                                                A("501 Syntax error\r\n");
                                                list = null;
                                                objS = list;
                                            }
                                        } else if ("RNFR".equals(upperCase)) {
                                            try {
                                                String strH7 = h(strTrim);
                                                if (n(strH7) || n(V.r(strH7))) {
                                                    A("550 Operation on invalid file\r\n");
                                                } else {
                                                    AbstractC5838l abstractC5838lI9 = i(strH7);
                                                    if (!o(abstractC5838lI9)) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else if (abstractC5838lI9.n()) {
                                                        this.p = abstractC5838lI9;
                                                        A("350 Waiting for RNTO\r\n");
                                                    } else {
                                                        A(p(strTrim));
                                                    }
                                                }
                                            } catch (s unused39) {
                                                A(p(strTrim));
                                            } catch (C0718i unused40) {
                                                A("550 Unknown error.\r\n");
                                            } catch (IllegalArgumentException unused41) {
                                                A("501 Syntax error\r\n");
                                            }
                                        } else if ("RNTO".equals(upperCase)) {
                                            try {
                                                try {
                                                    String strH8 = h(strTrim);
                                                    if (strH8 == null || n(strH8) || n(V.r(strH8))) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else if (this.p == null) {
                                                        A("550 RNFR was not processed\r\n");
                                                    } else {
                                                        AbstractC5838l abstractC5838lI10 = i(strH8);
                                                        C5839m c5839mJ6 = j(abstractC5838lI10);
                                                        AbstractC5838l abstractC5838l3 = this.p;
                                                        C5839m c5839mJ7 = j(abstractC5838l3);
                                                        AbstractC5838l abstractC5838lV0 = c5839mJ6.V0(abstractC5838lI10.S());
                                                        if (!o(abstractC5838lI10)) {
                                                            A("550 Operation on invalid file\r\n");
                                                        } else if (abstractC5838lI10.n()) {
                                                            A("550 Rename failed\r\n");
                                                        } else if (abstractC5838lV0.n()) {
                                                            try {
                                                                if (abstractC5838l3.isDirectory()) {
                                                                    if (abstractC5838lI10.P() != abstractC5838l3.P()) {
                                                                        c.h().c("FTP MOVE 2").h(abstractC5838l3.P().H() + "->" + abstractC5838lI10.P().H()).i();
                                                                    } else if (c5839mJ7.X0(abstractC5838l3) || abstractC5838l3.S().equals(abstractC5838lI10.S())) {
                                                                        c5839mJ7.k1(abstractC5838l3, abstractC5838lI10, null, null);
                                                                    } else {
                                                                        c.h().c("FTP MOVE 1").h(abstractC5838l3.P().H() + "->" + abstractC5838lI10.P().H()).i();
                                                                    }
                                                                    A("550 Rename failed\r\n");
                                                                } else {
                                                                    c5839mJ7.M(abstractC5838l3, c5839mJ6, abstractC5838lI10, true, null, null);
                                                                }
                                                                A("250 Rename succeeded\r\n");
                                                                objS = abstractC5838lI10.S();
                                                            } catch (C0710a | C0718i unused42) {
                                                            }
                                                        } else {
                                                            A("553 File name not allowed\r\n");
                                                        }
                                                    }
                                                    objS = null;
                                                } catch (s unused43) {
                                                    A(p(strTrim));
                                                    list = null;
                                                    objS = list;
                                                } catch (C0718i unused44) {
                                                    A("550 Unknown error.\r\n");
                                                    list = null;
                                                    objS = list;
                                                }
                                            } catch (IllegalArgumentException unused45) {
                                                A("501 Syntax error\r\n");
                                                list = null;
                                                objS = list;
                                            }
                                        } else {
                                            if (!"RETR".equals(upperCase)) {
                                                if ("REST".equals(upperCase)) {
                                                    try {
                                                        this.A = Long.valueOf(strTrim).longValue();
                                                        try {
                                                            A("350 REST set\r\n");
                                                            z2 = true;
                                                        } catch (NumberFormatException unused46) {
                                                            z2 = true;
                                                            try {
                                                                A("501 Invalid REST option\r\n");
                                                            } catch (Throwable th10) {
                                                                th = th10;
                                                            }
                                                        } catch (Throwable th11) {
                                                            th = th11;
                                                            z2 = true;
                                                        }
                                                    } catch (NumberFormatException unused47) {
                                                        z2 = false;
                                                    }
                                                    str4 = 0;
                                                } else if ("STOR".equals(upperCase) || "APPE".equals(upperCase)) {
                                                    try {
                                                        try {
                                                            String strH9 = h(strTrim);
                                                            if (n(strH9) || n(V.r(strH9))) {
                                                                A("550 Operation on invalid file\r\n");
                                                            } else {
                                                                AbstractC5838l abstractC5838lI11 = i(strH9);
                                                                if (abstractC5838lI11.isDirectory() || !o(abstractC5838lI11)) {
                                                                    A("550 Operation on invalid file\r\n");
                                                                } else {
                                                                    boolean zEquals3 = r5.equals(upperCase);
                                                                    C5839m c5839mJ8 = j(abstractC5838lI11);
                                                                    if (this.A <= 0 || zEquals3) {
                                                                        try {
                                                                            if (abstractC5838lI11.n() && !zEquals3) {
                                                                                c5839mJ8.l1(abstractC5838lI11);
                                                                            }
                                                                            t(abstractC5838lI11, zEquals3, 0L);
                                                                        } catch (C0718i unused48) {
                                                                            A("451 Cannot overwrite the file\r\n");
                                                                        }
                                                                    } else {
                                                                        long jO = abstractC5838lI11.o();
                                                                        long j3 = this.A;
                                                                        if (jO < j3) {
                                                                            A("550 Operation on invalid file\r\n");
                                                                        } else {
                                                                            t(abstractC5838lI11, false, j3);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } catch (s unused49) {
                                                            A(p(strTrim));
                                                        } catch (C0718i unused50) {
                                                            A(str6);
                                                        }
                                                    } catch (IllegalArgumentException unused51) {
                                                        A("501 Syntax error\r\n");
                                                    }
                                                } else {
                                                    A("550 Unsupported command\r\n");
                                                }
                                                c0510a.a = str4;
                                                if (z2) {
                                                    this.A = 0L;
                                                }
                                            }
                                            try {
                                                try {
                                                    String strH10 = h(strTrim);
                                                    if (n(strH10) || n(V.r(strH10))) {
                                                        A("550 Operation on invalid file\r\n");
                                                    } else {
                                                        AbstractC5838l abstractC5838lI12 = i(strH10);
                                                        if (!abstractC5838lI12.n()) {
                                                            A(p(strTrim));
                                                        } else if (!abstractC5838lI12.isDirectory() && abstractC5838lI12.h() && o(abstractC5838lI12)) {
                                                            w(abstractC5838lI12, this.A);
                                                        } else {
                                                            A("550 Operation on invalid file\r\n");
                                                        }
                                                    }
                                                } catch (s unused52) {
                                                    A(p(strTrim));
                                                } catch (IllegalArgumentException unused53) {
                                                    A(strI);
                                                }
                                            } catch (C0718i unused54) {
                                                A("550 Unknown error.\r\n");
                                            }
                                        }
                                        z2 = false;
                                        str4 = objS;
                                        c0510a.a = str4;
                                        if (z2) {
                                            this.A = 0L;
                                        }
                                    }
                                    A("211- Extensions supported:\r\n UTF8\r\n MLST\r\n MLSD\r\n MFMT\r\n REST STREAM\r\n211 End.\r\n");
                                } else if (n(this.f)) {
                                    A("550 Appropriate parent directory does not exist\r\n");
                                } else if (n(V.r(this.f))) {
                                    z(this.d, "/");
                                    A("200 CDUP OK\r\n");
                                } else {
                                    try {
                                        String strR = V.r(this.f);
                                        strI = i(strR);
                                        if (strI == 0 || !m(strI)) {
                                            A("550 Appropriate parent directory does not exist\r\n");
                                        } else {
                                            z(strI, strR);
                                            A("200 CDUP OK\r\n");
                                        }
                                    } catch (s unused55) {
                                        A(p(strTrim));
                                    } catch (C0718i unused56) {
                                        A("550 Unknown error.\r\n");
                                    }
                                }
                                th = th2;
                                th = th;
                                z2 = false;
                                if (z2) {
                                    throw th;
                                }
                                this.A = 0L;
                                throw th;
                            }
                            try {
                                try {
                                    StringBuilder sb = new StringBuilder();
                                    if (TextUtils.isEmpty(strTrim)) {
                                        strH = this.f;
                                        sb.append("250- Listing .\r\n");
                                    } else {
                                        strH = h(strTrim);
                                        sb.append("250- Listing " + strTrim + "\r\n");
                                    }
                                    AbstractC5838l abstractC5838lI13 = i(strH);
                                    if (abstractC5838lI13.n()) {
                                        a(sb, true, abstractC5838lI13, strH);
                                        sb.append("250 End\r\n");
                                        A(sb.toString());
                                    } else {
                                        A(p(strTrim));
                                    }
                                } catch (s unused57) {
                                    A(p(strTrim));
                                } catch (C0718i unused58) {
                                    A("550 Unknown error.\r\n");
                                }
                            } catch (IllegalArgumentException unused59) {
                                A("501 Syntax error\r\n");
                            }
                        }
                    }
                    objS = list;
                    z2 = false;
                    str4 = objS;
                    c0510a.a = str4;
                    if (z2) {
                        this.A = 0L;
                    }
                }
                A("215 UNIX Type: L8\r\n");
            } else if (z) {
                A("530 Maximum number of login attempts exceeded\r\n");
            } else if (c(this.l, strTrim)) {
                A("230 Access granted\r\n");
                this.k = true;
            } else {
                c0510a.b = true;
                A("530 Login incorrect\r\n");
            }
            list = null;
            objS = list;
            z2 = false;
            str4 = objS;
            c0510a.a = str4;
            if (z2) {
                this.A = 0L;
            }
        } catch (Throwable th12) {
            th = th12;
        }
    }
}
