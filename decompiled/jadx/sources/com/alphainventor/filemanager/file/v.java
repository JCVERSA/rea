package com.alphainventor.filemanager.file;

import android.content.Context;
import android.os.Build;
import android.system.Os;
import android.system.StructStat;
import ax.J1.V;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.http2.Http2;
import okhttp3.internal.ws.RealWebSocket;

/* JADX INFO: loaded from: classes.dex */
public class v {
    private static final Logger c = Logger.getLogger("FileManager.LocalFileRootAccess");
    private static final SimpleDateFormat d;
    private static final SimpleDateFormat e;
    private static final SimpleDateFormat f;
    private static final SimpleDateFormat g;
    private static final SimpleDateFormat h;
    private static v i;
    private static v j;
    private static final HashSet<String> k;
    private boolean a;
    private Boolean b;

    static class a {
        int a;
        int b;
        int c;
        int d;

        a(int i, int i2, int i3, int i4) {
            this.a = i;
            this.b = i2;
            this.d = i3;
            this.c = i4;
        }

        static int c(int i) {
            return i & 61440;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean d() {
            return this.d == 16384;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean e() {
            return this.d == 4096;
        }
    }

    private static class b {
        String a;
        boolean b;
        boolean c;
        boolean d;
        boolean e;
        long f;
        long g;

        b(String str, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2) {
            this.a = str;
            this.b = z;
            this.c = z2;
            this.d = z3;
            this.e = z4;
            this.f = j;
            this.g = j2;
        }
    }

    class c extends FileOutputStream {
        private String X;
        private a Y;
        private boolean Z;
        private String q;

        c(String str, String str2, a aVar) throws IOException {
            super(str2);
            this.Z = false;
            this.X = str;
            this.q = str2;
            this.Y = aVar;
        }

        @Override // java.io.FileOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            super.close();
            if (this.Z) {
                return;
            }
            this.Z = true;
            if (!v.this.P(this.q, this.X, true)) {
                throw new IOException("Move temp file to the path failed");
            }
            a aVar = this.Y;
            if (aVar == null) {
                v.this.W(this.X, false);
                return;
            }
            v.this.d(this.X, aVar.c);
            v vVar = v.this;
            String str = this.X;
            a aVar2 = this.Y;
            vVar.e(str, aVar2.a, aVar2.b);
        }
    }

    static {
        Locale locale = Locale.US;
        d = new SimpleDateFormat("yyyy-MM-dd HH:mm", locale);
        e = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z", locale);
        f = new SimpleDateFormat("yyyyMMdd.HHmmss", locale);
        g = new SimpleDateFormat("yyyyMMddHHmm.ssSSS", locale);
        h = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", locale);
        k = new HashSet<>(Arrays.asList("ext_data_rw", "ext_obb_rw", "media_rw", "external_storage"));
    }

    v(boolean z) {
        this.a = z;
    }

    private static String B(int i2) {
        return String.format("%o", Integer.valueOf(i2));
    }

    private FileOutputStream E(AbstractC5838l abstractC5838l) {
        String strC = abstractC5838l.C();
        try {
            return new c(strC, I(abstractC5838l.u()).getAbsolutePath(), m(strC) ? t(strC) : null);
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private FileOutputStream F(String str, boolean z) {
        int iP = p(str);
        if (iP < 0) {
            return null;
        }
        boolean zN = N(iP, 2);
        if (!zN && !d(str, iP | 2)) {
            return null;
        }
        try {
            return new FileOutputStream(str, z);
        } catch (IOException unused) {
            return null;
        } finally {
            if (!zN) {
                d(str, iP);
            }
        }
    }

    private static File I(Context context) {
        File file;
        File cacheDir = context.getCacheDir();
        do {
            file = new File(cacheDir, String.valueOf(new Random().nextLong()));
        } while (file.exists());
        return file;
    }

    public static long J(String str) {
        try {
            return d.parse(str).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private static boolean L(String str) {
        return "sh".equals(V.k(str));
    }

    private boolean M(Context context) {
        if (this.b == null) {
            if (ax.E1.P.I0()) {
                this.b = Boolean.valueOf(l("ls -lan --full-time /", 10000L) != null);
            } else {
                this.b = Boolean.FALSE;
            }
        }
        return this.b.booleanValue();
    }

    private static boolean N(int i2, int i3) {
        return (i2 & i3) == i3;
    }

    private static C5840n Q(ArrayList<String> arrayList) {
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            String str = arrayList.get(i2);
            if (!str.startsWith("total")) {
                String[] strArrSplit = str.split("\\s+");
                if (strArrSplit.length < 4) {
                    continue;
                } else {
                    try {
                        return new C5840n(strArrSplit[2], strArrSplit[3], strArrSplit[0]);
                    } catch (Exception unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x009e  */
    /* JADX WARN: Code duplicated, block: B:37:0x00a8 A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:13:0x002d, B:16:0x0052, B:18:0x005c, B:35:0x00a0, B:37:0x00a8, B:41:0x010b, B:46:0x011b, B:48:0x0123, B:53:0x0138, B:55:0x0140, B:57:0x0146, B:65:0x016e, B:59:0x014e, B:61:0x0158, B:63:0x0162, B:52:0x0132, B:51:0x012e, B:43:0x0112, B:38:0x00ce, B:39:0x00f0, B:26:0x006e, B:27:0x007a, B:29:0x0080, B:30:0x008d, B:67:0x0179, B:69:0x0185), top: B:73:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:38:0x00ce A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:13:0x002d, B:16:0x0052, B:18:0x005c, B:35:0x00a0, B:37:0x00a8, B:41:0x010b, B:46:0x011b, B:48:0x0123, B:53:0x0138, B:55:0x0140, B:57:0x0146, B:65:0x016e, B:59:0x014e, B:61:0x0158, B:63:0x0162, B:52:0x0132, B:51:0x012e, B:43:0x0112, B:38:0x00ce, B:39:0x00f0, B:26:0x006e, B:27:0x007a, B:29:0x0080, B:30:0x008d, B:67:0x0179, B:69:0x0185), top: B:73:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x00f0 A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:13:0x002d, B:16:0x0052, B:18:0x005c, B:35:0x00a0, B:37:0x00a8, B:41:0x010b, B:46:0x011b, B:48:0x0123, B:53:0x0138, B:55:0x0140, B:57:0x0146, B:65:0x016e, B:59:0x014e, B:61:0x0158, B:63:0x0162, B:52:0x0132, B:51:0x012e, B:43:0x0112, B:38:0x00ce, B:39:0x00f0, B:26:0x006e, B:27:0x007a, B:29:0x0080, B:30:0x008d, B:67:0x0179, B:69:0x0185), top: B:73:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x010b A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:13:0x002d, B:16:0x0052, B:18:0x005c, B:35:0x00a0, B:37:0x00a8, B:41:0x010b, B:46:0x011b, B:48:0x0123, B:53:0x0138, B:55:0x0140, B:57:0x0146, B:65:0x016e, B:59:0x014e, B:61:0x0158, B:63:0x0162, B:52:0x0132, B:51:0x012e, B:43:0x0112, B:38:0x00ce, B:39:0x00f0, B:26:0x006e, B:27:0x007a, B:29:0x0080, B:30:0x008d, B:67:0x0179, B:69:0x0185), top: B:73:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x0112 A[Catch: Exception -> 0x0068, TryCatch #0 {Exception -> 0x0068, blocks: (B:13:0x002d, B:16:0x0052, B:18:0x005c, B:35:0x00a0, B:37:0x00a8, B:41:0x010b, B:46:0x011b, B:48:0x0123, B:53:0x0138, B:55:0x0140, B:57:0x0146, B:65:0x016e, B:59:0x014e, B:61:0x0158, B:63:0x0162, B:52:0x0132, B:51:0x012e, B:43:0x0112, B:38:0x00ce, B:39:0x00f0, B:26:0x006e, B:27:0x007a, B:29:0x0080, B:30:0x008d, B:67:0x0179, B:69:0x0185), top: B:73:0x0029 }] */
    /* JADX WARN: Code duplicated, block: B:64:0x016b  */
    /* JADX WARN: Instruction removed from duplicated block: B:37:0x00a8, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:38:0x00ce, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:39:0x00f0, please report this as an issue */
    private b R(String str, String str2, boolean z, boolean z2) {
        int iEnd;
        int iStart;
        String strSubstring;
        String str3;
        String str4;
        String str5;
        long J;
        boolean z3;
        long j2;
        File file;
        boolean zK;
        int i2;
        int iIndexOf;
        Matcher matcher = (z2 ? Pattern.compile("\\s\\d\\d:\\d\\d:\\d\\d(\\.\\d{9})?\\s[+-]\\d\\d\\d\\d\\s") : Pattern.compile("\\s\\d\\d:\\d\\d\\s")).matcher(str2);
        if (matcher.find()) {
            iEnd = matcher.end();
            iStart = matcher.start();
        } else {
            iEnd = -1;
            iStart = -1;
        }
        try {
            if (iStart <= 0 || iEnd <= 0) {
                if (!str2.toLowerCase().startsWith("total")) {
                    c.severe("Invalid line of ls  :" + str2);
                }
                return null;
            }
            String strSubstring2 = str2.substring(iEnd);
            String[] strArrSplit = str2.substring(0, iEnd).split("\\s+");
            int length = strArrSplit.length;
            String str6 = strArrSplit[0];
            boolean zStartsWith = str6.startsWith("d");
            boolean zStartsWith2 = str6.startsWith("l");
            long jLongValue = -1;
            if (zStartsWith) {
                strSubstring = str2.substring(iEnd);
                if (!strSubstring.equals(".")) {
                    if (strSubstring.equals("..")) {
                    }
                }
                return null;
            }
            if (zStartsWith2) {
                String[] strArrSplit2 = strSubstring2.split("\\s->\\s", 2);
                str3 = strArrSplit2[0];
                str4 = strArrSplit2[1];
            } else {
                strSubstring = str2.substring(iEnd);
                jLongValue = z2 ? Long.valueOf(strArrSplit[length - 4]).longValue() : Long.valueOf(strArrSplit[length - 3]).longValue();
            }
            if (z2) {
                i2 = length - 2;
                iIndexOf = strArrSplit[i2].indexOf(".");
                if (iIndexOf >= 0) {
                    str5 = strArrSplit[length - 3] + " " + strArrSplit[i2].substring(0, iIndexOf) + " " + strArrSplit[length - 1];
                } else {
                    str5 = strArrSplit[length - 3] + " " + strArrSplit[i2] + " " + strArrSplit[length - 1];
                }
            } else {
                str5 = strArrSplit[length - 2] + " " + strArrSplit[length - 1];
            }
            if (z2) {
                J = v(str5);
            } else {
                J = J(str5);
            }
            long j3 = J;
            if (zStartsWith2 || str4 == null) {
                z3 = zStartsWith;
                j2 = jLongValue;
            } else {
                if (str4.startsWith("/")) {
                    file = new File(str4);
                } else {
                    file = new File(z ? str : V.r(str), str4);
                }
                long jS = 0;
                if (file.exists()) {
                    zK = file.isDirectory();
                    if (!zK) {
                        jS = file.length();
                    }
                } else if (m(file.getAbsolutePath())) {
                    zK = K(file.getAbsolutePath());
                    if (!zK) {
                        jS = s(file.getAbsolutePath());
                    }
                } else {
                    z3 = zStartsWith;
                    j2 = jLongValue;
                }
                j2 = jS;
                z3 = zK;
            }
            return new b(str3, z3, zStartsWith2, true, false, j2, j3);
            str3 = strSubstring;
            str4 = null;
            if (z2) {
                i2 = length - 2;
                iIndexOf = strArrSplit[i2].indexOf(".");
                if (iIndexOf >= 0) {
                    str5 = strArrSplit[length - 3] + " " + strArrSplit[i2].substring(0, iIndexOf) + " " + strArrSplit[length - 1];
                } else {
                    str5 = strArrSplit[length - 3] + " " + strArrSplit[i2] + " " + strArrSplit[length - 1];
                }
            } else {
                str5 = strArrSplit[length - 2] + " " + strArrSplit[length - 1];
            }
            if (z2) {
                J = v(str5);
            } else {
                J = J(str5);
            }
            long j4 = J;
            if (zStartsWith2) {
                z3 = zStartsWith;
                j2 = jLongValue;
            } else {
                z3 = zStartsWith;
                j2 = jLongValue;
            }
            return new b(str3, z3, zStartsWith2, true, false, j2, j4);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static int S(String str) {
        if (str.length() != 10) {
            return -1;
        }
        int i2 = str.charAt(1) == 'r' ? 256 : 0;
        if (str.charAt(2) == 'w') {
            i2 |= 128;
        }
        if (str.charAt(3) == 'x') {
            i2 |= 64;
        } else if (str.charAt(3) == 's') {
            i2 |= 2112;
        } else if (str.charAt(3) == 'S') {
            i2 |= 2048;
        }
        if (str.charAt(4) == 'r') {
            i2 |= 32;
        }
        if (str.charAt(5) == 'w') {
            i2 |= 16;
        }
        if (str.charAt(6) == 'x') {
            i2 |= 8;
        } else if (str.charAt(6) == 's') {
            i2 |= 1032;
        } else if (str.charAt(6) == 'S') {
            i2 |= 1024;
        }
        if (str.charAt(7) == 'r') {
            i2 |= 4;
        }
        if (str.charAt(8) == 'w') {
            i2 |= 2;
        }
        return str.charAt(9) == 'x' ? i2 | 1 : i2;
    }

    private static int T(String str) {
        if (str.length() != 10) {
            return 0;
        }
        if (str.charAt(0) == 'd') {
            return Http2.INITIAL_MAX_FRAME_SIZE;
        }
        if (str.charAt(0) == 'l') {
            return 40960;
        }
        return str.charAt(0) == 'p' ? 4096 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W(String str, boolean z) {
        String str2;
        C5840n c5840nO = o(V.r(str));
        if (c5840nO == null || (str2 = c5840nO.b) == null) {
            return;
        }
        int i2 = 1528;
        if (k.contains(str2)) {
            String str3 = c5840nO.a;
            if (str3 != null) {
                f(str, str3, c5840nO.b);
                if (!z || !c5840nO.a.startsWith("u0_a")) {
                    i2 = z ? 504 : 432;
                }
                d(str, i2);
                return;
            }
            return;
        }
        if (!c5840nO.b.startsWith("u0_a")) {
            f(str, "root", "root");
            d(str, (z || L(str)) ? 504 : 432);
            return;
        }
        String str4 = c5840nO.a;
        if (str4 != null) {
            f(str, str4, c5840nO.b);
            if (!z || !c5840nO.b.endsWith("_cache")) {
                i2 = z ? 504 : 432;
            }
            d(str, i2);
        }
    }

    public static v X() {
        if (j == null) {
            j = new v(false);
        }
        return j;
    }

    public static v Y() {
        if (i == null) {
            i = new v(true);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(String str, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append("chmod ");
        sb.append(B(i2));
        sb.append(" ");
        sb.append(k(str));
        return l(sb.toString(), 2000L) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(String str, int i2, int i3) {
        StringBuilder sb = new StringBuilder();
        sb.append("chown ");
        sb.append(i2);
        sb.append(":");
        sb.append(i3);
        sb.append(" ");
        sb.append(k(str));
        return l(sb.toString(), 2000L) != null;
    }

    private boolean f(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append("chown ");
        sb.append(str2);
        sb.append(":");
        sb.append(str3);
        sb.append(" ");
        sb.append(k(str));
        return l(sb.toString(), 2000L) != null;
    }

    private static String k(String str) {
        return "'" + str.replaceAll("'", "\"'\"") + "'";
    }

    private static long v(String str) {
        try {
            return e.parse(str).getTime();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0L;
        }
    }

    private FileInputStream x(String str, long j2, a aVar) {
        int iP = aVar != null ? aVar.c : p(str);
        if (iP < 0) {
            return null;
        }
        boolean zN = N(iP, 4);
        int i2 = iP | 4;
        if (!zN && !d(str, i2)) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            if (j2 > 0) {
                fileInputStream.skip(j2);
            }
            return fileInputStream;
        } catch (IOException unused) {
            return null;
        } finally {
            if (!zN) {
                d(str, iP);
            }
        }
    }

    public FileInputStream A(AbstractC5838l abstractC5838l, long j2) {
        File file;
        String strC = abstractC5838l.C();
        File cacheDir = abstractC5838l.u().getCacheDir();
        do {
            file = new File(cacheDir, String.valueOf(new Random().nextLong()));
        } while (file.exists());
        String absolutePath = file.getAbsolutePath();
        if (l("ln -f " + k(strC) + " " + k(absolutePath), 2000L) == null) {
            return null;
        }
        FileInputStream fileInputStreamX = x(absolutePath, j2, null);
        l("rm " + k(absolutePath), 2000L);
        return fileInputStreamX;
    }

    public int C(String str, boolean z) {
        ArrayList<String> arrayListL;
        if (ax.E1.P.Q0()) {
            StringBuilder sb = new StringBuilder();
            sb.append("ls -1A ");
            sb.append(k(str + "/"));
            arrayListL = l(sb.toString(), 10000L);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("ls -lan ");
            sb2.append(k(str + "/"));
            arrayListL = l(sb2.toString(), 10000L);
        }
        if (arrayListL == null) {
            return -1;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < arrayListL.size(); i3++) {
            String str2 = arrayListL.get(i3);
            if (!V.z(str2) && (((!str2.endsWith(" .") && !str2.endsWith(" ..")) || R(str, str2, true, false) != null) && ((z || !str2.startsWith(".")) && (ax.E1.P.Q0() || !str2.startsWith("total"))))) {
                i2++;
            }
        }
        return i2;
    }

    public FileOutputStream D(AbstractC5838l abstractC5838l, boolean z) {
        FileOutputStream fileOutputStreamH = H(abstractC5838l, z);
        return fileOutputStreamH == null ? G(abstractC5838l, z) : fileOutputStreamH;
    }

    public FileOutputStream G(AbstractC5838l abstractC5838l, boolean z) {
        String strS;
        int iP;
        if (z || (iP = p((strS = abstractC5838l.S()))) < 0) {
            return null;
        }
        boolean zN = N(iP, 3);
        if (!zN && !d(strS, iP | 3)) {
            return null;
        }
        FileOutputStream fileOutputStreamE = E(abstractC5838l);
        if (!zN) {
            d(strS, iP);
        }
        return fileOutputStreamE;
    }

    public FileOutputStream H(AbstractC5838l abstractC5838l, boolean z) {
        String strC = abstractC5838l.C();
        boolean z2 = !m(strC);
        if (z2 && !h(strC)) {
            return null;
        }
        String absolutePath = I(abstractC5838l.u()).getAbsolutePath();
        if (l("ln -f " + k(strC) + " " + k(absolutePath), 2000L) == null) {
            if (z2) {
                l("rm " + k(strC), 2000L);
            }
            return null;
        }
        FileOutputStream fileOutputStreamF = F(absolutePath, z);
        l("rm " + k(absolutePath), 2000L);
        return fileOutputStreamF;
    }

    public boolean K(String str) {
        if (ax.E1.P.A1()) {
            ArrayList<String> arrayListL = l("test -d " + k(str) + " && echo YES || echo NO", 10000L);
            if (arrayListL == null || arrayListL.size() <= 0) {
                return false;
            }
            return "YES".equals(arrayListL.get(0));
        }
        ArrayList<String> arrayListL2 = l("ls -land " + k(str), 10000L);
        if (arrayListL2 == null) {
            return false;
        }
        for (int i2 = 0; i2 < arrayListL2.size(); i2++) {
            String str2 = arrayListL2.get(i2);
            if (!str2.startsWith("total")) {
                if (str2.length() <= 0 || str2.charAt(0) != 'd') {
                    break;
                }
                return true;
            }
        }
        return false;
    }

    public ArrayList<u> O(C5845t c5845t, ax.J1.F f2, String str) {
        String str2;
        boolean z;
        b bVarR;
        v vVar = this;
        ArrayList<u> arrayList = new ArrayList<>();
        String str3 = "/";
        if (!"/".equals(str)) {
            str3 = str + "/";
        }
        int i2 = 0;
        boolean z2 = true;
        if (vVar.M(c5845t.n())) {
            str2 = "ls -lan --full-time " + k(str3);
            z = true;
        } else {
            str2 = "ls -lan " + k(str3);
            z = false;
        }
        ArrayList<String> arrayListL = vVar.l(str2, 10000L);
        if (arrayListL == null) {
            return null;
        }
        while (i2 < arrayListL.size()) {
            String str4 = arrayListL.get(i2);
            if (!str4.startsWith("total") && (bVarR = vVar.R(str, str4, z2, z)) != null) {
                arrayList.add(new u(c5845t, new File(str, bVarR.a), f2, bVarR.b, bVarR.c, bVarR.d, bVarR.e, bVarR.f, bVarR.g));
            }
            i2++;
            z2 = true;
            vVar = this;
        }
        return arrayList;
    }

    public boolean P(String str, String str2, boolean z) {
        String str3 = z ? "mv -f" : "mv";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(" ");
            sb.append(k(str));
            sb.append(" ");
            sb.append(k(str2));
            return l(sb.toString(), 2000L) != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void U(String str, long j2) {
        if (Build.VERSION.SDK_INT >= 23) {
            l("touch -t " + g.format(new Date(j2)) + " " + k(str), 10000L);
            return;
        }
        l("touch -t " + f.format(new Date(j2)) + " " + k(str), 10000L);
    }

    public void V(String str, C5840n c5840n) {
        String str2;
        int iS;
        String str3 = c5840n.c;
        if (str3 != null && (iS = S(str3)) >= 0) {
            d(str, iS);
        }
        String str4 = c5840n.a;
        if (str4 == null || (str2 = c5840n.b) == null) {
            return;
        }
        f(str, str4, str2);
    }

    public boolean g(String str, String str2, long j2) {
        try {
            long j3 = (j2 / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE) / RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
            long j4 = 2000;
            if (j3 > 0) {
                j4 = 2000 * j3;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("cp ");
            sb.append(k(str));
            sb.append(" ");
            sb.append(k(str2));
            return l(sb.toString(), j4) != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean h(String str) {
        try {
            if (l("touch " + k(str), 2000L) == null) {
                return false;
            }
            W(str, false);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean i(String str) {
        try {
            if (l("mkdir " + k(str), 2000L) == null) {
                return false;
            }
            W(str, true);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean j(String str) {
        String str2 = K(str) ? "rmdir" : "rm";
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(" ");
            sb.append(k(str));
            return l(sb.toString(), 2000L) != null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    ArrayList<String> l(String str, long j2) {
        return this.a ? ax.p045d2.o.d(str, j2) : ax.p045d2.o.h(str, j2);
    }

    public boolean m(String str) {
        if (!ax.E1.P.A1()) {
            StringBuilder sb = new StringBuilder();
            sb.append("ls -land ");
            sb.append(k(str));
            return l(sb.toString(), 10000L) != null;
        }
        ArrayList<String> arrayListL = l("test -e " + k(str) + " && echo YES || echo NO", 10000L);
        if (arrayListL == null || arrayListL.size() <= 0) {
            return false;
        }
        return "YES".equals(arrayListL.get(0));
    }

    public long n(String str) {
        b bVarR;
        if (ax.E1.P.s1()) {
            ArrayList<String> arrayListL = l("stat -c\"%y|%Y\" " + k(str), 10000L);
            if (arrayListL != null && arrayListL.size() > 0) {
                String[] strArrSplit = arrayListL.get(0).split("\\|");
                try {
                    try {
                        return h.parse(strArrSplit[0]).getTime();
                    } catch (NumberFormatException unused) {
                    }
                } catch (ParseException unused2) {
                    if (strArrSplit.length > 1) {
                        return Long.valueOf(strArrSplit[1]).longValue() * 1000;
                    }
                    return 0L;
                }
            }
            return 0L;
        }
        ArrayList<String> arrayListL2 = l("ls -land " + k(str), 10000L);
        if (arrayListL2 == null) {
            return 0L;
        }
        for (int i2 = 0; i2 < arrayListL2.size(); i2++) {
            String str2 = arrayListL2.get(i2);
            if (!str2.startsWith("total") && (bVarR = R(str, str2, false, false)) != null) {
                return bVarR.g;
            }
        }
        return 0L;
    }

    public C5840n o(String str) {
        ArrayList<String> arrayListL = l("ls -lad " + k(str), 10000L);
        if (arrayListL == null) {
            return null;
        }
        return Q(arrayListL);
    }

    public int p(String str) {
        if (ax.E1.P.z1()) {
            try {
                return Os.stat(str).st_mode & 511;
            } catch (Exception unused) {
            }
        }
        return ax.E1.P.s1() ? r(str) : q(str);
    }

    public int q(String str) {
        ArrayList<String> arrayListL = l("ls -land " + k(str), 10000L);
        if (arrayListL == null) {
            return -1;
        }
        for (int i2 = 0; i2 < arrayListL.size(); i2++) {
            String str2 = arrayListL.get(i2);
            if (!str2.startsWith("total")) {
                return S(str2.split("\\s+")[0]);
            }
        }
        return -1;
    }

    public int r(String str) {
        ArrayList<String> arrayListL = l("stat -c%a " + k(str), 10000L);
        if (arrayListL != null && arrayListL.size() > 0) {
            try {
                return Integer.valueOf(arrayListL.get(0), 8).intValue() & 511;
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    public long s(String str) {
        b bVarR;
        if (ax.E1.P.s1()) {
            ArrayList<String> arrayListL = l("stat -c%s " + k(str), 10000L);
            if (arrayListL != null && arrayListL.size() > 0) {
                try {
                    return Long.valueOf(arrayListL.get(0)).longValue();
                } catch (NumberFormatException unused) {
                }
            }
            return 0L;
        }
        ArrayList<String> arrayListL2 = l("ls -land " + k(str), 10000L);
        if (arrayListL2 == null) {
            return 0L;
        }
        for (int i2 = 0; i2 < arrayListL2.size(); i2++) {
            String str2 = arrayListL2.get(i2);
            if (!str2.startsWith("total") && (bVarR = R(str, str2, false, false)) != null) {
                return bVarR.f;
            }
        }
        return 0L;
    }

    public a t(String str) {
        if (ax.E1.P.z1()) {
            try {
                StructStat structStatStat = Os.stat(str);
                int i2 = structStatStat.st_mode;
                return new a(structStatStat.st_uid, structStatStat.st_gid, a.c(i2), i2 & 511);
            } catch (Exception unused) {
            }
        }
        return u(str);
    }

    public a u(String str) {
        ArrayList<String> arrayListL = l("ls -land " + k(str), 10000L);
        if (arrayListL == null) {
            return null;
        }
        for (int i2 = 0; i2 < arrayListL.size(); i2++) {
            String str2 = arrayListL.get(i2);
            if (!str2.startsWith("total")) {
                String[] strArrSplit = str2.split("\\s+");
                if (strArrSplit.length < 4) {
                    continue;
                } else {
                    try {
                        String str3 = strArrSplit[0];
                        return new a(Integer.parseInt(strArrSplit[2]), Integer.parseInt(strArrSplit[3]), T(str3), S(str3));
                    } catch (Exception unused) {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    public FileInputStream w(AbstractC5838l abstractC5838l, long j2) throws Throwable {
        a aVarT = t(abstractC5838l.C());
        if (aVarT == null || aVarT.e() || aVarT.d()) {
            return null;
        }
        FileInputStream fileInputStreamA = A(abstractC5838l, j2);
        if (fileInputStreamA == null) {
            fileInputStreamA = y(abstractC5838l, j2, aVarT);
        }
        return fileInputStreamA == null ? z(abstractC5838l, j2) : fileInputStreamA;
    }

    /* JADX WARN: Code duplicated, block: B:43:0x006d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0072  */
    public FileInputStream y(AbstractC5838l abstractC5838l, long j2, a aVar) throws Throwable {
        boolean z;
        String strS = abstractC5838l.S();
        String strR = V.r(strS);
        int iP = p(strS);
        if (iP < 0) {
            return null;
        }
        int iP2 = "/".equals(strS) ? 0 : p(strR);
        boolean zN = true;
        try {
            boolean zN2 = N(iP, 1);
            if (!zN2) {
                try {
                    if (!d(strS, iP | 1)) {
                        if (!zN2) {
                            d(strS, iP);
                        }
                        return null;
                    }
                } catch (Throwable th) {
                    th = th;
                    zN = zN2;
                    z = true;
                    if (!zN) {
                        d(strS, iP);
                    }
                    if (!z) {
                        d(strR, iP2);
                    }
                    throw th;
                }
            }
            if (iP2 > 0 && !(zN = N(iP2, 1))) {
                try {
                    if (!d(strR, iP2 | 1)) {
                        if (!zN2) {
                            d(strS, iP);
                        }
                        if (!zN) {
                            d(strR, iP2);
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    z = zN;
                    zN = zN2;
                    if (!zN) {
                        d(strS, iP);
                    }
                    if (!z) {
                        d(strR, iP2);
                    }
                    throw th;
                }
            }
            FileInputStream fileInputStreamX = x(abstractC5838l.C(), j2, aVar);
            if (!zN2) {
                d(strS, iP);
            }
            if (!zN) {
                d(strR, iP2);
            }
            return fileInputStreamX;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public FileInputStream z(AbstractC5838l abstractC5838l, long j2) {
        FileInputStream fileInputStream;
        File fileY = abstractC5838l.Y();
        String absolutePath = fileY.getAbsolutePath();
        if (g(abstractC5838l.C(), absolutePath, abstractC5838l.o())) {
            try {
                d(absolutePath, 511);
                fileInputStream = new FileInputStream(absolutePath);
                if (j2 != 0) {
                    try {
                        fileInputStream.skip(j2);
                    } catch (IOException unused) {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        return null;
                    }
                }
                ax.G1.q.b().c(fileY);
                ax.G1.q.b().a();
                return fileInputStream;
            } catch (IOException unused3) {
                fileInputStream = null;
            }
        }
        return null;
    }
}
