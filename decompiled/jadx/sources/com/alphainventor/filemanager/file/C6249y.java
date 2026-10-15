package com.alphainventor.filemanager.file;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import ax.p070e3.C1966j;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.C2006l;
import ax.p086f3.C2015v;
import ax.p086f3.C2017x;
import ax.p086f3.EnumC2019z;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import com.alphainventor.filemanager.FileManagerApp;
import j$.io.FileRetargetClass;
import j$.nio.file.Files;
import j$.nio.file.LinkOption;
import j$.nio.file.Path;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C6249y extends AbstractC2005k {
    private static final Logger X0 = Logger.getLogger("FileManager.LocalFileInfo");
    private Long A0;
    private Long B0;
    public Boolean C0;
    public Boolean D0;
    private Boolean E0;
    private Boolean F0;
    private Boolean G0;
    private Uri H0;
    private Boolean I0;
    private Boolean J0;
    private Boolean K0;
    private int L0;
    private String M0;
    private String N0;
    private ax.p086f3.K O0;
    private boolean P0;
    private Boolean Q0;
    private b R0;
    File S0;
    private String T0;
    private boolean U0;
    private boolean V0;
    String W0;
    protected C6248x s0;
    protected File t0;
    private String u0;
    private String v0;
    private String w0;
    private String x0;
    private Boolean y0;
    private Long z0;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.y$a */
    class a implements FilenameFilter {
        final /* synthetic */ boolean q;

        a(boolean z) {
            this.q = z;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (str != null) {
                boolean zStartsWith = str.startsWith(".");
                if (zStartsWith) {
                    C6249y.this.P0 = true;
                }
                if (this.q) {
                    if (J.l2(str)) {
                        return false;
                    }
                } else if (zStartsWith) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: com.alphainventor.filemanager.file.y$b */
    public enum b {
        VISIBLE,
        HIDDEN_OS,
        HIDDEN_DOTHIDDEN,
        HIDDEN_LIBRARY,
        HIDDEN_NOMEDIA,
        HIDDEN_DATA,
        HIDDEN_HIDELIST,
        HIDDEN_PARENT
    }

    public C6249y(C6248x c6248x, Uri uri, ax.p086f3.K k, String str, Cursor cursor) throws C1966j {
        String strReplaceAll;
        boolean z;
        String strQ;
        super(c6248x);
        this.R0 = b.VISIBLE;
        this.T0 = "no_constructor";
        this.s0 = c6248x;
        C2006l c2006l = new C2006l(cursor);
        String str2 = c2006l.a;
        boolean z2 = true;
        if (str2 == null || !str2.endsWith("/")) {
            strReplaceAll = str2;
            z = false;
        } else {
            strReplaceAll = str2.replaceAll(".$", "_");
            z = true;
        }
        String strQ2 = C6232g.q(x(), k, uri, strReplaceAll, str, c2006l);
        if (!d0.J(str, strQ2, true)) {
            ax.p302sb.c.h().b("invalid file local document file path").h("parentPath:" + str + ",docId:" + c2006l.a);
            throw new C1966j("path problem");
        }
        String strSubstring = d0.o(str, strQ2, true).substring(1);
        if (strSubstring.contains("/")) {
            String strReplaceAll2 = strSubstring.replaceAll("/", "_");
            strQ2 = d0.Q(str, strReplaceAll2);
            if (strReplaceAll2.length() > 12) {
                ax.p302sb.c.h().g().b("LOCAL DOCUMENT FILE NAME FIXED UNUSUAL").k().h("file:" + str + "," + c2006l.b + "," + c2006l.a + ",root:" + uri).i();
            }
        } else {
            z2 = z;
        }
        if (TextUtils.isEmpty(c2006l.b)) {
            ax.p302sb.c.h().g().b("empty local document displayname").h("id:" + c2006l.a + ",root:" + uri + ",parent:" + str).i();
            strQ = null;
        } else {
            strQ = c2006l.b.contains("/") ? d0.Q(str, c2006l.b.replaceAll("/", "_")) : d0.Q(str, c2006l.b);
        }
        if (strQ != null && !strQ.equalsIgnoreCase(strQ2) && !"/".equals(strQ2)) {
            FileManagerApp.b("LOCAL DOCUMENT FILE NAME CONFLICT:" + str + "," + c2006l.b + "," + strQ + "," + strQ2);
            if (!z2) {
                ax.p302sb.c.h().g().b("LOCAL DOCUMENT FILE NAME CONFLICT 1").k().h("parent:" + str + "," + c2006l.b + "," + c2006l.a + ",root:" + uri).i();
                throw new C1966j("path conflict");
            }
            C2461b.e("DOCUMENT PATH CONFLICT");
        }
        if (strQ2 == null) {
            ax.p302sb.c.h().b("LOCAL DOCUMENT FILE PATH NULL").h("file:" + str + "," + c2006l.b + "," + strQ + "," + strQ2).i();
        }
        this.H0 = uri;
        this.t0 = new File(strQ2);
        this.v0 = strQ2;
        this.E0 = Boolean.valueOf(c2006l.d());
        this.y0 = Boolean.FALSE;
        this.F0 = Boolean.valueOf(c2006l.b());
        this.G0 = Boolean.valueOf(c2006l.a());
        this.z0 = Long.valueOf(c2006l.d);
        this.B0 = Long.valueOf(c2006l.e);
        this.O0 = k;
        if (k == null) {
            v0();
        }
        this.T0 = "constructor 3";
    }

    public C6249y(C6248x c6248x, ax.p086f3.K k, ax.p326u3.a aVar) {
        super(c6248x);
        this.R0 = b.VISIBLE;
        this.T0 = "no_constructor";
        this.s0 = c6248x;
        this.U0 = true;
        this.t0 = new File(aVar.q);
        this.E0 = Boolean.valueOf(aVar.Z);
        this.y0 = Boolean.valueOf(aVar.k0);
        this.G0 = Boolean.valueOf(aVar.l0);
        this.F0 = Boolean.valueOf(aVar.m0);
        this.B0 = Long.valueOf(aVar.n0);
        this.z0 = Long.valueOf(aVar.o0);
        this.D0 = Boolean.valueOf(aVar.Y);
        this.x0 = aVar.X;
        this.v0 = aVar.q;
        this.O0 = k;
        if (k == null) {
            v0();
        }
    }

    public C6249y(C6248x c6248x, C6249y c6249y) {
        super(c6248x);
        this.R0 = b.VISIBLE;
        this.T0 = "no_constructor";
        this.s0 = c6248x;
        this.H0 = c6249y.H0;
        this.t0 = c6249y.t0;
        this.v0 = c6249y.v0;
        this.E0 = c6249y.E0;
        this.y0 = c6249y.y0;
        this.G0 = c6249y.G0;
        this.F0 = c6249y.F0;
        this.B0 = c6249y.B0;
        this.z0 = c6249y.z0;
        ax.p086f3.K k = c6249y.O0;
        this.O0 = k;
        this.U0 = c6249y.U0;
        this.V0 = c6249y.V0;
        this.w0 = c6249y.w0;
        this.A0 = c6249y.A0;
        if (k == null) {
            v0();
        }
        this.T0 = "constructor 2";
    }

    public C6249y(C6248x c6248x, File file, ax.p086f3.K k) {
        this(c6248x, file, k, false);
    }

    public C6249y(C6248x c6248x, File file, ax.p086f3.K k, boolean z) {
        super(c6248x);
        this.R0 = b.VISIBLE;
        this.T0 = "no_constructor";
        this.s0 = c6248x;
        C2461b.b(file);
        this.t0 = file;
        this.V0 = z;
        String absolutePath = file.getAbsolutePath();
        if (z) {
            this.v0 = ax.p086f3.I.f(absolutePath);
            this.w0 = absolutePath;
        } else {
            this.v0 = absolutePath;
            this.w0 = absolutePath;
        }
        this.O0 = k;
        if (k == null) {
            v0();
        }
        this.T0 = "constructor 1";
    }

    public C6249y(C6248x c6248x, File file, ax.p086f3.K k, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2) {
        this(c6248x, file, k);
        this.E0 = Boolean.valueOf(z);
        this.y0 = Boolean.valueOf(z2);
        this.G0 = Boolean.valueOf(z3);
        this.F0 = Boolean.valueOf(z4);
        this.B0 = Long.valueOf(j);
        this.z0 = Long.valueOf(j2);
    }

    public C6249y(C6248x c6248x, File file, File file2, ax.p086f3.K k, boolean z) {
        this(c6248x, file2, k, z);
        this.S0 = file;
    }

    private void A0() {
        if (ax.p006a3.Q.G() && S() == ax.p086f3.K.f && c1()) {
            if (this.E0.booleanValue()) {
                return;
            }
            s1();
        } else {
            if (this.E0.booleanValue() || Q() != ax.U2.f.y0 || this.t0.exists() || !o0()) {
                return;
            }
            this.E0 = Boolean.valueOf(C6250z.Y().K(E()));
        }
    }

    private void A1() {
        try {
            this.B0 = Long.valueOf(this.t0.length());
            z0();
        } catch (IllegalArgumentException unused) {
            this.B0 = -1L;
        }
    }

    private void B0() {
        if (this.z0.longValue() == 0 && Q() == ax.U2.f.y0 && !G0().exists() && o0()) {
            this.z0 = Long.valueOf(C6250z.Y().n(E()));
        }
    }

    public static C6249y J0(String str) throws C1966j {
        return (C6249y) C2017x.g(str).a1(str);
    }

    private static int K0(String str) {
        if (str == null) {
            return 0;
        }
        int length = str.length();
        int i = 1;
        if (length == 0) {
            return 1;
        }
        while (length > 0 && str.charAt(length - 1) == d0.b) {
            length--;
        }
        if (length == 0) {
            return 0;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (str.charAt(i2) == d0.b) {
                i++;
            }
        }
        return i;
    }

    private static String M0(String str, String str2) {
        int i;
        int length = str2.length();
        if (str.length() > length) {
            char cCharAt = str.charAt(length);
            char c = d0.b;
            if (cCharAt != c || str.length() <= (i = length + 1)) {
                return null;
            }
            int iIndexOf = str.indexOf(c, i);
            return iIndexOf < 0 ? str : str.substring(0, iIndexOf);
        }
        return null;
    }

    private boolean R0() {
        try {
            Path absolutePath = FileRetargetClass.toPath(G0()).toAbsolutePath();
            if (!Files.isDirectory(absolutePath, new LinkOption[0])) {
                return false;
            }
            DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(absolutePath);
            try {
                boolean zHasNext = directoryStreamNewDirectoryStream.iterator().hasNext();
                directoryStreamNewDirectoryStream.close();
                return zHasNext;
            } catch (Throwable th) {
                if (directoryStreamNewDirectoryStream != null) {
                    try {
                        directoryStreamNewDirectoryStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException | DirectoryIteratorException unused) {
            return s(true) > 0;
        }
    }

    private boolean S0() {
        if (!ax.p006a3.Q.V1() || Q() == ax.U2.f.t0 || !j0()) {
            return false;
        }
        if (this.B0 == null) {
            p();
        }
        return this.t0.length() == 0 && this.B0.longValue() != 0;
    }

    public static boolean V0(ax.p086f3.K k, String str) {
        return K0(d0.v(k, str, Boolean.TRUE)) == 3;
    }

    public static boolean a1(String str) {
        return d0.G("/Android", str) || d0.G("/Android/obb", str) || d0.G("/Android/data", str);
    }

    public static boolean e1(C6249y c6249y, C6249y c6249y2) {
        if (c6249y.C0() != c6249y2.C0()) {
            return false;
        }
        return !ax.U2.f.P(c6249y.C0()) || c6249y.Q0() == c6249y2.Q0();
    }

    private boolean g1() {
        String str = this.v0;
        return str != null && str.equals("/");
    }

    private boolean o0() {
        return ax.p390y3.k.l(x()) && ax.p038c3.j.F().r0();
    }

    private void p1(ax.p006a3.L l, boolean z, boolean z2, boolean z3, boolean z4) {
        if (l.e) {
            if (z3) {
                this.B0 = Long.valueOf(l.a);
            }
            if (z) {
                this.E0 = Boolean.valueOf(l.b);
            }
            if (z2) {
                this.z0 = Long.valueOf(l.c);
            }
            if (z4) {
                this.D0 = Boolean.TRUE;
            }
            this.C0 = Boolean.TRUE;
            return;
        }
        if (z4) {
            this.D0 = Boolean.FALSE;
        }
        Boolean bool = Boolean.FALSE;
        this.C0 = bool;
        if (z3) {
            this.B0 = 0L;
        }
        if (z2) {
            this.z0 = 0L;
        }
        if (z && this.E0 == null) {
            this.E0 = bool;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0329  */
    /* JADX WARN: Code duplicated, block: B:102:0x0339  */
    /* JADX WARN: Code duplicated, block: B:108:0x02ae A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:110:0x0256 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:56:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:57:0x01f9 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:66:0x0250  */
    /* JADX WARN: Code duplicated, block: B:71:0x0278  */
    /* JADX WARN: Code duplicated, block: B:73:0x0280  */
    /* JADX WARN: Code duplicated, block: B:77:0x0298  */
    /* JADX WARN: Code duplicated, block: B:79:0x029e A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:86:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:89:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:94:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:97:0x030b  */
    private boolean q0(boolean z, boolean z2) {
        boolean zExists;
        boolean z3;
        String strN0;
        String str;
        String[] list;
        String[] list2;
        if (g1()) {
            return true;
        }
        if (ax.p006a3.Q.P1()) {
            Boolean bool = this.C0;
            if (bool == null || z2) {
                try {
                    zExists = P0().e;
                    this.C0 = Boolean.valueOf(zExists);
                } catch (Exception unused) {
                    zExists = this.t0.exists();
                }
            } else {
                zExists = bool.booleanValue();
            }
        } else {
            zExists = this.t0.exists();
        }
        Boolean boolValueOf = null;
        if (j0() && (!z || !zExists)) {
            try {
                try {
                    boolValueOf = Boolean.valueOf(u1());
                } catch (Exception e) {
                    ax.p302sb.c.h().g().d("SDCARD EXIST RETRY ERROR").m(e).h("exist:" + this.t0.exists()).i();
                }
            } catch (ax.p070e3.C unused2) {
                if (Build.VERSION.SDK_INT >= 23) {
                    String strE = C0().e();
                    if (strE != null) {
                        String strF = ax.U2.e.F(x(), strE);
                        if (!ax.p006a3.K.N(strF) && !ax.p006a3.K.K(strF) && strF != null) {
                            ax.p302sb.c.h().g().b("CLEAR ROOT URI OVER 23").h("location:" + S().d().H() + ",base:" + C0() + ",path:" + strE + ",exist:" + this.t0.exists()).i();
                        }
                        strN0 = N0();
                        if (boolValueOf == null) {
                            if (zExists) {
                                C6232g.b(x(), C0(), strN0);
                                ax.p302sb.c.h().g().d("CLEARING ROOT URI").h(this.t0.getAbsolutePath() + "," + C0().toString()).i();
                            }
                            if (!zExists) {
                                if (f1()) {
                                    try {
                                        zExists = com.alphainventor.filemanager.shizuku.c.t().y(this.v0).e;
                                    } catch (IOException unused3) {
                                        C2461b.e("?" + zExists);
                                    }
                                } else if (Q() == ax.U2.f.y0) {
                                    zExists = C6250z.Y().m(E());
                                }
                            }
                            boolValueOf = Boolean.valueOf(zExists);
                        } else if (!boolValueOf.booleanValue()) {
                            if (this.t0.isDirectory()) {
                                try {
                                    if (C6232g.l(this).toString().contains(this.t0.getName())) {
                                        str = "URI HAS NAME";
                                    } else {
                                        str = "URI DO NOT HAVE NAME";
                                    }
                                } catch (C1966j e2) {
                                    str = "EX:" + e2.getMessage();
                                }
                                list = this.t0.list();
                                if (list != null) {
                                    ax.p302sb.b bVarD = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append(",not null:");
                                    sb.append(list != null);
                                    bVarD.h(sb.toString()).i();
                                } else {
                                    ax.p302sb.b bVarD2 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str);
                                    sb2.append(",not null:");
                                    sb2.append(list != null);
                                    bVarD2.h(sb2.toString()).i();
                                }
                            } else {
                                ax.p302sb.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.t0.getAbsolutePath()).i();
                            }
                            boolValueOf = Boolean.TRUE;
                        }
                        return boolValueOf.booleanValue();
                    }
                }
                z3 = true;
                strN0 = N0();
                if (boolValueOf == null) {
                    if (zExists) {
                        C6232g.b(x(), C0(), strN0);
                        ax.p302sb.c.h().g().d("CLEARING ROOT URI").h(this.t0.getAbsolutePath() + "," + C0().toString()).i();
                    }
                    if (!zExists) {
                        if (f1()) {
                            zExists = com.alphainventor.filemanager.shizuku.c.t().y(this.v0).e;
                        } else if (Q() == ax.U2.f.y0) {
                            zExists = C6250z.Y().m(E());
                        }
                    }
                    boolValueOf = Boolean.valueOf(zExists);
                } else if (!boolValueOf.booleanValue()) {
                    if (this.t0.isDirectory()) {
                        if (C6232g.l(this).toString().contains(this.t0.getName())) {
                            str = "URI HAS NAME";
                        } else {
                            str = "URI DO NOT HAVE NAME";
                        }
                        list = this.t0.list();
                        if (list != null) {
                            ax.p302sb.b bVarD3 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str);
                            sb3.append(",not null:");
                            sb3.append(list != null);
                            bVarD3.h(sb3.toString()).i();
                        } else {
                            ax.p302sb.b bVarD4 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str);
                            sb4.append(",not null:");
                            sb4.append(list != null);
                            bVarD4.h(sb4.toString()).i();
                        }
                    } else {
                        ax.p302sb.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.t0.getAbsolutePath()).i();
                    }
                    boolValueOf = Boolean.TRUE;
                }
                return boolValueOf.booleanValue();
            } catch (ax.p070e3.p unused4) {
                z3 = true;
                strN0 = N0();
                if (boolValueOf == null) {
                    if (zExists) {
                        C6232g.b(x(), C0(), strN0);
                        ax.p302sb.c.h().g().d("CLEARING ROOT URI").h(this.t0.getAbsolutePath() + "," + C0().toString()).i();
                    }
                    if (!zExists) {
                        if (f1()) {
                            zExists = com.alphainventor.filemanager.shizuku.c.t().y(this.v0).e;
                        } else if (Q() == ax.U2.f.y0) {
                            zExists = C6250z.Y().m(E());
                        }
                    }
                    boolValueOf = Boolean.valueOf(zExists);
                } else if (!boolValueOf.booleanValue()) {
                    if (this.t0.isDirectory()) {
                        if (C6232g.l(this).toString().contains(this.t0.getName())) {
                            str = "URI HAS NAME";
                        } else {
                            str = "URI DO NOT HAVE NAME";
                        }
                        list = this.t0.list();
                        if (list != null) {
                            ax.p302sb.b bVarD5 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(str);
                            sb5.append(",not null:");
                            sb5.append(list != null);
                            bVarD5.h(sb5.toString()).i();
                        } else {
                            ax.p302sb.b bVarD6 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(str);
                            sb6.append(",not null:");
                            sb6.append(list != null);
                            bVarD6.h(sb6.toString()).i();
                        }
                    } else {
                        ax.p302sb.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.t0.getAbsolutePath()).i();
                    }
                    boolValueOf = Boolean.TRUE;
                }
                return boolValueOf.booleanValue();
            } catch (ax.p070e3.r unused5) {
            } catch (ax.p070e3.t e3) {
                ax.p302sb.c.h().g().d("SDCARD FILE NOT EXIST").m(e3).h("exist:" + this.t0.exists()).i();
            } catch (ax.p070e3.v unused6) {
                boolValueOf = Boolean.valueOf(u1());
                ax.p302sb.c.h().g().d("SDCARD EXIST RETRY SUCCESS").h("exist:" + this.t0.exists()).i();
            } catch (Exception e4) {
                e4.printStackTrace();
                String str2 = "exist:" + this.t0.exists() + ",root:" + S().e();
                if (Q() != null) {
                    str2 = str2 + ",loc:" + Q().toString();
                }
                if (C0() != null) {
                    str2 = str2 + ",base:" + C0().k();
                }
                ax.p302sb.c.h().g().d("SDCARD EXISTS UNKNOWN").m(e4).h(str2).i();
            }
        }
        z3 = false;
        strN0 = N0();
        if (boolValueOf == null) {
            if (zExists && z3 && d0.E(this) && (list2 = this.t0.list()) != null && list2.length > 0) {
                C6232g.b(x(), C0(), strN0);
                ax.p302sb.c.h().g().d("CLEARING ROOT URI").h(this.t0.getAbsolutePath() + "," + C0().toString()).i();
            }
            if (!zExists) {
                if (f1()) {
                    zExists = com.alphainventor.filemanager.shizuku.c.t().y(this.v0).e;
                } else if (Q() == ax.U2.f.y0 && o0()) {
                    zExists = C6250z.Y().m(E());
                }
            }
            boolValueOf = Boolean.valueOf(zExists);
        } else if (!boolValueOf.booleanValue() && zExists && d0.E(this)) {
            if (this.t0.isDirectory()) {
                if (C6232g.l(this).toString().contains(this.t0.getName())) {
                    str = "URI HAS NAME";
                } else {
                    str = "URI DO NOT HAVE NAME";
                }
                list = this.t0.list();
                if (list != null || list.length <= 0) {
                    ax.p302sb.b bVarD7 = ax.p302sb.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str);
                    sb7.append(",not null:");
                    sb7.append(list != null);
                    bVarD7.h(sb7.toString()).i();
                } else {
                    C6232g.b(x(), C0(), strN0);
                    ax.p302sb.c.h().g().d("SDCARD HAS CHANGED. CLEARING ROOT URI").h(str).i();
                }
            } else {
                ax.p302sb.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.t0.getAbsolutePath()).i();
            }
            boolValueOf = Boolean.TRUE;
        }
        return boolValueOf.booleanValue();
    }

    private void r1() {
        this.E0 = Boolean.valueOf(this.t0.isDirectory());
        A0();
    }

    private boolean u1() throws C1966j {
        C2006l c2006lJ = C6232g.j(x(), this);
        if (c2006lJ == null) {
            this.B0 = 0L;
            Boolean bool = Boolean.FALSE;
            this.G0 = bool;
            this.F0 = bool;
            return false;
        }
        this.B0 = Long.valueOf(c2006lJ.e);
        this.G0 = Boolean.valueOf(c2006lJ.a());
        this.F0 = Boolean.valueOf(c2006lJ.b());
        this.E0 = Boolean.valueOf(c2006lJ.d());
        this.z0 = Long.valueOf(c2006lJ.d);
        return true;
    }

    private void v0() {
        if (d0.B(this.v0)) {
            this.O0 = ax.p038c3.j.F().J(this.t0.getAbsolutePath(), S());
            return;
        }
        ax.p302sb.c.h().b("GLFLFI 2:").g().k().h("location:" + Q() + ",path:" + this.v0 + ",name:" + this.t0.getName() + "," + this.t0.getAbsolutePath()).i();
        this.O0 = S();
    }

    private void w0() {
        this.u0 = C2015v.e(this, "application/octet-stream");
    }

    private void w1(boolean z, boolean z2, boolean z3, boolean z4) throws IOException {
        p1(f1() ? com.alphainventor.filemanager.shizuku.c.t().y(E()) : P0(), z, z2, z3, z4);
    }

    private void y1() {
        this.z0 = Long.valueOf(this.t0.lastModified());
        B0();
    }

    private void z0() {
        if (this.B0.longValue() != 0 || isDirectory()) {
            return;
        }
        if (ax.p006a3.Q.H() && j0()) {
            try {
                u1();
                this.B0.longValue();
            } catch (Exception unused) {
            }
        }
        if (this.B0.longValue() == 0 && Q() == ax.U2.f.y0 && !G0().exists() && o0()) {
            this.B0 = Long.valueOf(C6250z.Y().s(E()));
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String C() {
        if (this.x0 == null) {
            this.x0 = this.t0.getName();
        }
        return this.x0;
    }

    public ax.p086f3.K C0() {
        if (this.O0 == null) {
            v0();
            ax.p302sb.b bVarB = ax.p302sb.c.h().b("BASE LOCATION UNIT NULL 2");
            StringBuilder sb = new StringBuilder();
            sb.append("loc:");
            sb.append(S());
            sb.append(",path:");
            sb.append(F());
            sb.append(",constructor:");
            sb.append(this.T0);
            sb.append(",retry:");
            sb.append(this.O0 != null);
            bVarB.h(sb.toString()).i();
            if (this.O0 == null) {
                this.O0 = S();
            }
        }
        return this.O0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public C6241p D() {
        if (S() == ax.p086f3.K.h && ax.p038c3.j.F().q()) {
            return C6250z.Y().o(E());
        }
        return null;
    }

    public String D0() {
        if (this.N0 == null) {
            if (ax.U2.f.e0(C0().d())) {
                C2461b.e("library:?" + C0());
                this.N0 = d0.v(C0(), E(), Boolean.valueOf(isDirectory()));
            } else {
                this.N0 = d0.v(C0(), E(), null);
            }
        }
        return this.N0;
    }

    public long E0() {
        if (this.A0 == null) {
            if (!isDirectory()) {
                if (ax.p387y0.a.S(t())) {
                    try {
                        ax.p387y0.a aVar = new ax.p387y0.a(E());
                        try {
                            this.A0 = aVar.o();
                        } catch (Exception unused) {
                        }
                        if (this.A0 == null) {
                            try {
                                this.A0 = aVar.n();
                            } catch (Exception unused2) {
                            }
                        }
                        if (this.A0 == null) {
                            this.A0 = aVar.m();
                        }
                    } catch (IOException | Exception unused3) {
                    }
                } else {
                    G();
                    EnumC2019z enumC2019z = EnumC2019z.AUDIO;
                }
            }
            if (this.A0 == null) {
                this.A0 = Long.valueOf(q());
            }
        }
        return this.A0.longValue();
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String F() {
        return this.v0;
    }

    public String F0() {
        ax.U2.f fVarQ = Q();
        if (fVarQ == ax.U2.f.E0) {
            return !isDirectory() ? g0() : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (fVarQ == ax.U2.f.G0 || fVarQ == ax.U2.f.F0) {
            return !isDirectory() ? h0() : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        return W();
    }

    public File G0() {
        return this.t0;
    }

    public String H0(int i) {
        v(i);
        String str = this.W0;
        if (str != null) {
            return str;
        }
        String strW = w(E0(), i);
        this.W0 = strW;
        return strW;
    }

    public b I0() {
        return this.R0;
    }

    public String L0() {
        if (this.w0 == null) {
            this.w0 = this.t0.getAbsolutePath();
        }
        return this.w0;
    }

    public String N0() {
        if (ax.U2.f.P(C0()) && c1()) {
            return this.M0;
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public File O() {
        return G0();
    }

    public Uri O0() throws ax.p070e3.r {
        Uri uriN;
        if (this.H0 == null && ax.p006a3.Q.V1()) {
            String strX = null;
            if (!c1()) {
                strX = C6232g.x(x(), C0(), null);
            } else if (ax.p038c3.j.F().F0(C0())) {
                String strN0 = N0();
                if (strN0 != null && strN0.length() >= 14 && (uriN = C6232g.n(C0(), strN0)) != null && C6232g.a(x(), uriN)) {
                    strX = uriN.toString();
                }
            } else {
                strX = C6232g.x(x(), C0(), N0());
            }
            if (TextUtils.isEmpty(strX)) {
                throw new ax.p070e3.r("RootUri is empty");
            }
            this.H0 = Uri.parse(strX);
        }
        return this.H0;
    }

    public ax.p006a3.L P0() throws IOException {
        return ax.p006a3.v.l(L0(), new ax.p006a3.L());
    }

    public int Q0() {
        if (this.L0 == 0) {
            if (!ax.p006a3.Q.L() || !ax.U2.f.P(C0())) {
                this.L0 = 1;
            } else if (ax.p038c3.j.F().F0(C0())) {
                String strN0 = N0();
                if (strN0 == null) {
                    this.L0 = 1;
                } else if (d0.H("/Android/data", strN0)) {
                    this.L0 = strN0.hashCode() + 268435456;
                } else if (d0.H("/Android/obb", strN0)) {
                    this.L0 = strN0.hashCode() + 536870912;
                } else {
                    this.L0 = 1;
                }
            } else {
                String strD0 = D0();
                if (d0.H("/Android/data", strD0)) {
                    this.L0 = 268435456;
                } else if (d0.H("/Android/obb", strD0)) {
                    this.L0 = 536870912;
                } else {
                    this.L0 = 1;
                }
            }
        }
        return this.L0;
    }

    public boolean T0() {
        return this.P0;
    }

    public boolean U0() {
        try {
            return O0() != null;
        } catch (ax.p070e3.r unused) {
            return false;
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String V() {
        return d0.r(this.v0);
    }

    public boolean W0() {
        String strD0 = D0();
        if (strD0 == null) {
            return false;
        }
        if (!d0.B(strD0)) {
            ax.U2.d.c("ISFP2", new Exception("Invalid path 2: " + strD0 + ":" + F()));
        }
        return d0.G("/Android", strD0);
    }

    public boolean X0() {
        ax.U2.f fVarQ = Q();
        if (isDirectory()) {
            return (this.E0 == null || this.G0 == null || this.F0 == null) ? false : true;
        }
        if (fVarQ == ax.U2.f.E0) {
            return this.q0 != null;
        }
        if (fVarQ == ax.U2.f.G0 || fVarQ == ax.U2.f.F0) {
            return this.r0 != null;
        }
        return (this.E0 == null || this.G0 == null || this.F0 == null) ? false : true;
    }

    public boolean Y0() {
        if (isDirectory()) {
            return (Y() == -1 || Y() == -3) ? false : true;
        }
        return this.B0 != null;
    }

    public boolean Z0() {
        return this.O0.d() == ax.U2.f.u0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public boolean b0() {
        if (Y() >= 0) {
            return Y() > 0;
        }
        return ax.p006a3.Q.Q0() ? R0() : super.b0();
    }

    public boolean b1() {
        if (this.K0 == null) {
            if (c1()) {
                this.K0 = Boolean.valueOf(K0(D0()) == 4);
            } else {
                this.K0 = Boolean.FALSE;
            }
        }
        return this.K0.booleanValue();
    }

    public boolean c1() {
        String strD0;
        String strM0;
        if (this.I0 == null) {
            if (ax.p006a3.Q.L() && ax.U2.f.Q(C0()) && (strD0 = D0()) != null) {
                if (d0.H("/Android/data", strD0)) {
                    if (!d0.I("/Android/data/com.alphainventor.filemanager", strD0)) {
                        this.I0 = Boolean.TRUE;
                        this.M0 = "/Android/data";
                    }
                } else if (d0.H("/Android/obb", strD0) && !d0.I("/Android/obb/com.alphainventor.filemanager", strD0)) {
                    this.I0 = Boolean.TRUE;
                    this.M0 = "/Android/obb";
                }
                Boolean bool = this.I0;
                if (bool != null && bool.booleanValue() && ax.p038c3.j.F().F0(C0()) && (strM0 = M0(strD0, this.M0)) != null) {
                    this.M0 = strM0;
                }
            }
            if (this.I0 == null) {
                this.I0 = Boolean.FALSE;
            }
        }
        return this.I0.booleanValue();
    }

    public boolean d1() {
        if (this.J0 == null) {
            if (c1()) {
                this.J0 = Boolean.valueOf(K0(D0()) == 3);
            } else {
                this.J0 = Boolean.FALSE;
            }
        }
        return this.J0.booleanValue();
    }

    @Override // ax.p086f3.AbstractC2005k
    public ParcelFileDescriptor f0(boolean z) throws C1966j {
        Uri uriE = C6232g.e(C0(), O0(), E());
        return z ? C6232g.o(x(), uriE, "r") : C6232g.o(x(), uriE, "rw");
    }

    public boolean f1() {
        return this.U0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        if (this.Q0 == null) {
            this.Q0 = Boolean.valueOf(C().startsWith("."));
        }
        return this.Q0.booleanValue();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        if (this.G0 == null) {
            Boolean boolValueOf = Boolean.valueOf(this.t0.canRead());
            this.G0 = boolValueOf;
            if (!boolValueOf.booleanValue() && j0()) {
                if (ax.p038c3.j.F().F0(C0()) && d1()) {
                    return true;
                }
                try {
                    u1();
                } catch (ax.p070e3.r unused) {
                } catch (Exception e) {
                    ax.p302sb.c.h().g().d("DOCUMENT FILE CANREAD").m(e).h(Q().H()).i();
                }
            }
        }
        return this.G0.booleanValue();
    }

    public boolean h1() {
        return this.V0;
    }

    public void i1(boolean z) {
        int iU;
        if (Y() == -1) {
            try {
                if (f1() && (iU = com.alphainventor.filemanager.shizuku.c.t().u(E(), z)) != -2) {
                    e0(iU);
                    return;
                }
                String[] list = this.t0.list(new a(z));
                if (list != null) {
                    e0(list.length);
                    return;
                }
                if ((Q() == ax.U2.f.y0 || c1()) && o0()) {
                    int iC = C6250z.Y().C(E(), z);
                    if (iC < 0) {
                        e0(-2);
                        return;
                    } else {
                        e0(iC);
                        return;
                    }
                }
                if (!n0()) {
                    e0(-2);
                    return;
                }
                try {
                    try {
                        List<AbstractC6239n> listJ = C6232g.J(this.s0, this);
                        if (listJ != null) {
                            e0(listJ.size());
                        } else {
                            e0(-2);
                        }
                    } catch (NullPointerException unused) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("NullPointer helper==null:");
                        sb.append(this.s0 == null);
                        sb.append(",");
                        sb.append(S().k());
                        ax.U2.d.c("HELPERNULL", new Exception(sb.toString()));
                        e0(-2);
                    }
                } catch (C1966j unused2) {
                    e0(-2);
                }
            } catch (OutOfMemoryError unused3) {
                e0(-2);
            }
        }
    }

    public boolean isDirectory() {
        if (this.E0 == null) {
            Boolean bool = this.D0;
            if (bool != null && !bool.booleanValue()) {
                return false;
            }
            if (g1()) {
                this.E0 = Boolean.TRUE;
            } else {
                if (ax.p006a3.Q.P1()) {
                    try {
                        v1();
                    } catch (Exception unused) {
                    }
                }
                if (this.E0 == null) {
                    r1();
                }
            }
        }
        return this.E0.booleanValue();
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean j0() {
        return j1(true);
    }

    public boolean j1(boolean z) {
        if (!ax.p006a3.Q.V1() || p0() || h1()) {
            return false;
        }
        ax.p086f3.K kC0 = C0();
        if (kC0 == ax.p086f3.K.e) {
            return c1();
        }
        if (kC0 != ax.p086f3.K.f) {
            return !ax.p038c3.j.F().k0(kC0);
        }
        if (!ax.p038c3.j.F().k0(kC0) && z) {
            return true;
        }
        return c1();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        if (this.F0 == null) {
            Boolean boolValueOf = Boolean.valueOf(this.t0.canWrite());
            this.F0 = boolValueOf;
            if (!boolValueOf.booleanValue() && j0()) {
                try {
                    u1();
                } catch (ax.p070e3.r unused) {
                } catch (Exception e) {
                    ax.p302sb.c.h().g().d("DOCUMENT FILE CANWRITE").m(e).h("loc:" + S().toString()).i();
                }
            }
        }
        return this.F0.booleanValue();
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean k0() {
        return ax.p006a3.Q.F1() && f1();
    }

    public void k1(long j) {
        this.A0 = Long.valueOf(j);
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean l0() {
        if (n0()) {
            return C0() == ax.p086f3.K.e ? c1() : S0();
        }
        return false;
    }

    public void l1(boolean z) {
        this.D0 = Boolean.valueOf(z);
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        if (this.y0 == null) {
            if (Q() == ax.U2.f.y0) {
                try {
                    this.y0 = Boolean.valueOf(ax.p086f3.B.R(this.t0));
                } catch (IOException unused) {
                    this.y0 = Boolean.FALSE;
                }
            } else {
                this.y0 = Boolean.FALSE;
            }
        }
        return this.y0.booleanValue();
    }

    public void m1(b bVar) {
        this.Q0 = Boolean.valueOf(bVar != b.VISIBLE);
        this.R0 = bVar;
    }

    public boolean n0() {
        if (!j0()) {
            return false;
        }
        if (ax.p006a3.J.l() && ax.p006a3.J.D(C0())) {
            return false;
        }
        return U0();
    }

    public void n1(boolean z) {
        this.U0 = z;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        if (this.D0 == null) {
            s1();
        }
        return this.D0.booleanValue();
    }

    public void o1(long j) {
        this.z0 = Long.valueOf(j);
    }

    public long p() {
        if (this.B0 == null) {
            if (ax.p006a3.Q.P1()) {
                try {
                    v1();
                } catch (Exception unused) {
                }
            }
            if (this.B0 == null) {
                A1();
            }
        }
        return this.B0.longValue();
    }

    public boolean p0() {
        return ax.p006a3.Q.a2() && ax.p038c3.j.F().q() && S() == ax.p086f3.K.h;
    }

    public long q() {
        if (this.z0 == null) {
            x1();
        }
        return this.z0.longValue();
    }

    public boolean q1() {
        return false;
    }

    public boolean r0() {
        Long l = this.z0;
        if (l == null) {
            x1();
            return false;
        }
        long jLongValue = l.longValue();
        x1();
        return this.z0.longValue() != jLongValue;
    }

    public int s(boolean z) {
        i1(z);
        return Y();
    }

    public void s0() {
        this.z0 = null;
    }

    public void s1() {
        this.D0 = Boolean.valueOf(q0(false, true));
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String t() {
        if (this.u0 == null) {
            w0();
        }
        return this.u0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: t0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC6239n abstractC6239n) {
        if (abstractC6239n == null) {
            return -1;
        }
        try {
            return this.t0.compareTo(((C6249y) abstractC6239n).t0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public void t1() {
        Boolean bool = this.C0;
        if (bool != null) {
            this.D0 = bool;
        } else {
            this.D0 = Boolean.valueOf(q0(true, false));
        }
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.t0.getAbsolutePath();
    }

    public void u0(C6249y c6249y) {
        this.I0 = c6249y.I0;
        this.M0 = c6249y.M0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected boolean v(int i) {
        boolean zV = super.v(i);
        if (zV) {
            this.W0 = null;
        }
        return zV;
    }

    public void v1() throws IOException {
        w1(true, true, true, false);
        A0();
        B0();
        z0();
    }

    public boolean x0() {
        if (this.D0 == null) {
            t1();
        }
        return this.D0.booleanValue();
    }

    public void x1() {
        if (!ax.p006a3.Q.P1()) {
            y1();
            return;
        }
        try {
            v1();
        } catch (IOException unused) {
            y1();
        }
    }

    public boolean y0() {
        return this.t0.exists();
    }

    public Uri z1() throws ax.p070e3.r {
        this.H0 = null;
        return O0();
    }
}
