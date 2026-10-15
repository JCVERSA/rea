package com.alphainventor.filemanager.file;

import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import ax.I1.C0718i;
import ax.J1.AbstractC0726g;
import ax.J1.C0727h;
import ax.J1.C0735p;
import ax.J1.C0740v;
import ax.J1.V;
import com.alphainventor.filemanager.FileManagerApp;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class u extends AbstractC0726g {
    private static final Logger Q0 = Logger.getLogger("FileManager.LocalFileInfo");
    private Boolean A0;
    private Boolean B0;
    private Uri C0;
    private Boolean D0;
    private Boolean E0;
    private Boolean F0;
    private int G0;
    private String H0;
    private ax.J1.F I0;
    private boolean J0;
    private Boolean K0;
    private b L0;
    File M0;
    private String N0;
    private boolean O0;
    private boolean P0;
    protected C5845t p0;
    protected File q0;
    private String r0;
    private String s0;
    private String t0;
    private String u0;
    private Boolean v0;
    private Long w0;
    private Long x0;
    private Boolean y0;
    private Boolean z0;

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
                    u.this.J0 = true;
                }
                if (this.q) {
                    if (E.c2(str)) {
                        return false;
                    }
                } else if (zStartsWith) {
                    return false;
                }
            }
            return true;
        }
    }

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

    public u(C5845t c5845t, File file, File file2, ax.J1.F f, boolean z) {
        this(c5845t, file2, f, z);
        this.M0 = file;
    }

    public static u A0(String str) throws C0718i {
        return (u) ax.J1.r.g(str).V0(str);
    }

    private boolean G0() {
        if (!ax.E1.P.F1() || P() == ax.p338y1.f.q0 || !X0()) {
            return false;
        }
        if (this.x0 == null) {
            o();
        }
        return this.q0.length() == 0 && this.x0.longValue() != 0;
    }

    public static boolean J0(ax.J1.F f, String str) {
        return V.u(f, str, Boolean.TRUE).split("/").length == 3;
    }

    public static boolean O0(String str) {
        return V.D("/Android", str) || V.D("/Android/obb", str) || V.D("/Android/data", str);
    }

    public static boolean S0(u uVar, u uVar2) {
        if (uVar.v0() != uVar2.v0()) {
            return false;
        }
        return !ax.p338y1.f.P(uVar.v0()) || uVar.F0() == uVar2.F0();
    }

    private boolean U0() {
        String str = this.s0;
        return str != null && str.equals("/");
    }

    private void e1() {
        this.z0 = Boolean.valueOf(this.q0.isDirectory());
        t0();
    }

    private boolean h1() throws C0718i {
        C0727h c0727hJ = C5832f.j(u(), this);
        if (c0727hJ == null) {
            this.x0 = 0L;
            Boolean bool = Boolean.FALSE;
            this.B0 = bool;
            this.A0 = bool;
            return false;
        }
        this.x0 = Long.valueOf(c0727hJ.e);
        this.B0 = Boolean.valueOf(c0727hJ.a());
        this.A0 = Boolean.valueOf(c0727hJ.b());
        this.z0 = Boolean.valueOf(c0727hJ.d());
        this.w0 = Long.valueOf(c0727hJ.d);
        return true;
    }

    private boolean j0() {
        return ax.p031c2.i.j(u()) && ax.G1.i.D().i0();
    }

    private void j1(boolean z, boolean z2, boolean z3, boolean z4) throws IOException {
        ax.E1.K kW = T0() ? com.alphainventor.filemanager.shizuku.c.t().w(C()) : E0();
        if (kW.d) {
            if (z3) {
                this.x0 = Long.valueOf(kW.a);
            }
            if (z) {
                this.z0 = Boolean.valueOf(kW.b);
            }
            if (z2) {
                this.w0 = Long.valueOf(kW.c);
            }
            if (z4) {
                this.y0 = Boolean.TRUE;
                return;
            }
            return;
        }
        if (z4) {
            this.y0 = Boolean.FALSE;
        }
        if (z3) {
            this.x0 = 0L;
        }
        if (z2) {
            this.w0 = 0L;
        }
        if (z && this.z0 == null) {
            this.z0 = Boolean.FALSE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x029c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:105:0x0244 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:52:0x01e7 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:61:0x023e  */
    /* JADX WARN: Code duplicated, block: B:66:0x0266  */
    /* JADX WARN: Code duplicated, block: B:68:0x026e  */
    /* JADX WARN: Code duplicated, block: B:72:0x0286  */
    /* JADX WARN: Code duplicated, block: B:74:0x028c A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:81:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:84:0x02b5  */
    /* JADX WARN: Code duplicated, block: B:89:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:92:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:95:0x0317  */
    /* JADX WARN: Code duplicated, block: B:97:0x0327  */
    private boolean l0(boolean z) {
        boolean zExists;
        boolean z2;
        String strC0;
        String str;
        String[] list;
        String[] list2;
        if (U0()) {
            return true;
        }
        if (ax.E1.P.z1()) {
            try {
                zExists = E0().d;
            } catch (Exception unused) {
                zExists = this.q0.exists();
            }
        } else {
            zExists = this.q0.exists();
        }
        Boolean boolValueOf = null;
        if (X0() && (!z || !zExists)) {
            try {
                try {
                    boolValueOf = Boolean.valueOf(h1());
                } catch (Exception e) {
                    ax.p221pa.c.h().g().d("SDCARD EXIST RETRY ERROR").m(e).h("exist:" + this.q0.exists()).i();
                }
            } catch (ax.I1.A unused2) {
                if (Build.VERSION.SDK_INT >= 23) {
                    String strE = v0().e();
                    if (strE != null) {
                        String strF = ax.p338y1.e.F(u(), strE);
                        if (!ax.E1.J.N(strF) && !ax.E1.J.K(strF) && strF != null) {
                            ax.p221pa.c.h().g().b("CLEAR ROOT URI OVER 23").h("location:" + R().d().H() + ",base:" + v0() + ",path:" + strE + ",exist:" + this.q0.exists()).i();
                        }
                        strC0 = C0();
                        if (boolValueOf == null) {
                            if (zExists) {
                                C5832f.b(u(), v0(), strC0);
                                ax.p221pa.c.h().g().d("CLEARING ROOT URI").h(this.q0.getAbsolutePath() + "," + v0().toString()).i();
                            }
                            if (!zExists) {
                                if (T0()) {
                                    try {
                                        zExists = com.alphainventor.filemanager.shizuku.c.t().w(this.s0).d;
                                    } catch (IOException unused3) {
                                        ax.p045d2.b.e("?" + zExists);
                                    }
                                } else if (P() == ax.p338y1.f.v0) {
                                    zExists = v.Y().m(C());
                                }
                            }
                            boolValueOf = Boolean.valueOf(zExists);
                        } else if (!boolValueOf.booleanValue()) {
                            if (this.q0.isDirectory()) {
                                try {
                                    if (C5832f.l(this).toString().contains(this.q0.getName())) {
                                        str = "URI HAS NAME";
                                    } else {
                                        str = "URI DO NOT HAVE NAME";
                                    }
                                } catch (C0718i e2) {
                                    str = "EX:" + e2.getMessage();
                                }
                                list = this.q0.list();
                                if (list != null) {
                                    ax.p221pa.b bVarD = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(str);
                                    sb.append(",not null:");
                                    sb.append(list != null);
                                    bVarD.h(sb.toString()).i();
                                } else {
                                    ax.p221pa.b bVarD2 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append(str);
                                    sb2.append(",not null:");
                                    sb2.append(list != null);
                                    bVarD2.h(sb2.toString()).i();
                                }
                            } else {
                                ax.p221pa.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.q0.getAbsolutePath()).i();
                            }
                            boolValueOf = Boolean.TRUE;
                        }
                        return boolValueOf.booleanValue();
                    }
                }
                z2 = true;
                strC0 = C0();
                if (boolValueOf == null) {
                    if (zExists) {
                        C5832f.b(u(), v0(), strC0);
                        ax.p221pa.c.h().g().d("CLEARING ROOT URI").h(this.q0.getAbsolutePath() + "," + v0().toString()).i();
                    }
                    if (!zExists) {
                        if (T0()) {
                            zExists = com.alphainventor.filemanager.shizuku.c.t().w(this.s0).d;
                        } else if (P() == ax.p338y1.f.v0) {
                            zExists = v.Y().m(C());
                        }
                    }
                    boolValueOf = Boolean.valueOf(zExists);
                } else if (!boolValueOf.booleanValue()) {
                    if (this.q0.isDirectory()) {
                        if (C5832f.l(this).toString().contains(this.q0.getName())) {
                            str = "URI HAS NAME";
                        } else {
                            str = "URI DO NOT HAVE NAME";
                        }
                        list = this.q0.list();
                        if (list != null) {
                            ax.p221pa.b bVarD3 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(str);
                            sb3.append(",not null:");
                            sb3.append(list != null);
                            bVarD3.h(sb3.toString()).i();
                        } else {
                            ax.p221pa.b bVarD4 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(str);
                            sb4.append(",not null:");
                            sb4.append(list != null);
                            bVarD4.h(sb4.toString()).i();
                        }
                    } else {
                        ax.p221pa.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.q0.getAbsolutePath()).i();
                    }
                    boolValueOf = Boolean.TRUE;
                }
                return boolValueOf.booleanValue();
            } catch (ax.I1.o unused4) {
                z2 = true;
                strC0 = C0();
                if (boolValueOf == null) {
                    if (zExists) {
                        C5832f.b(u(), v0(), strC0);
                        ax.p221pa.c.h().g().d("CLEARING ROOT URI").h(this.q0.getAbsolutePath() + "," + v0().toString()).i();
                    }
                    if (!zExists) {
                        if (T0()) {
                            zExists = com.alphainventor.filemanager.shizuku.c.t().w(this.s0).d;
                        } else if (P() == ax.p338y1.f.v0) {
                            zExists = v.Y().m(C());
                        }
                    }
                    boolValueOf = Boolean.valueOf(zExists);
                } else if (!boolValueOf.booleanValue()) {
                    if (this.q0.isDirectory()) {
                        if (C5832f.l(this).toString().contains(this.q0.getName())) {
                            str = "URI HAS NAME";
                        } else {
                            str = "URI DO NOT HAVE NAME";
                        }
                        list = this.q0.list();
                        if (list != null) {
                            ax.p221pa.b bVarD5 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(str);
                            sb5.append(",not null:");
                            sb5.append(list != null);
                            bVarD5.h(sb5.toString()).i();
                        } else {
                            ax.p221pa.b bVarD6 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(str);
                            sb6.append(",not null:");
                            sb6.append(list != null);
                            bVarD6.h(sb6.toString()).i();
                        }
                    } else {
                        ax.p221pa.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.q0.getAbsolutePath()).i();
                    }
                    boolValueOf = Boolean.TRUE;
                }
                return boolValueOf.booleanValue();
            } catch (ax.I1.q unused5) {
            } catch (ax.I1.s e3) {
                ax.p221pa.c.h().g().d("SDCARD FILE NOT EXIST").m(e3).h("exist:" + this.q0.exists()).i();
            } catch (ax.I1.u unused6) {
                boolValueOf = Boolean.valueOf(h1());
                ax.p221pa.c.h().g().d("SDCARD EXIST RETRY SUCCESS").h("exist:" + this.q0.exists()).i();
            } catch (Exception e4) {
                e4.printStackTrace();
                String str2 = "exist:" + this.q0.exists() + ",root:" + R().e();
                if (P() != null) {
                    str2 = str2 + ",loc:" + P().toString();
                }
                if (v0() != null) {
                    str2 = str2 + ",base:" + v0().j();
                }
                ax.p221pa.c.h().g().d("SDCARD EXISTS UNKNOWN").m(e4).h(str2).i();
            }
        }
        z2 = false;
        strC0 = C0();
        if (boolValueOf == null) {
            if (zExists && z2 && V.B(this) && (list2 = this.q0.list()) != null && list2.length > 0) {
                C5832f.b(u(), v0(), strC0);
                ax.p221pa.c.h().g().d("CLEARING ROOT URI").h(this.q0.getAbsolutePath() + "," + v0().toString()).i();
            }
            if (!zExists) {
                if (T0()) {
                    zExists = com.alphainventor.filemanager.shizuku.c.t().w(this.s0).d;
                } else if (P() == ax.p338y1.f.v0 && j0()) {
                    zExists = v.Y().m(C());
                }
            }
            boolValueOf = Boolean.valueOf(zExists);
        } else if (!boolValueOf.booleanValue() && zExists && V.B(this)) {
            if (this.q0.isDirectory()) {
                if (C5832f.l(this).toString().contains(this.q0.getName())) {
                    str = "URI HAS NAME";
                } else {
                    str = "URI DO NOT HAVE NAME";
                }
                list = this.q0.list();
                if (list != null || list.length <= 0) {
                    ax.p221pa.b bVarD7 = ax.p221pa.c.h().g().d("SDCARD COULD NOT ACCESS ROOT");
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append(str);
                    sb7.append(",not null:");
                    sb7.append(list != null);
                    bVarD7.h(sb7.toString()).i();
                } else {
                    C5832f.b(u(), v0(), strC0);
                    ax.p221pa.c.h().g().d("SDCARD HAS CHANGED. CLEARING ROOT URI").h(str).i();
                }
            } else {
                ax.p221pa.c.h().g().d("SDCARD NOT DIRECTORY?!").h(this.q0.getAbsolutePath()).i();
            }
            boolValueOf = Boolean.TRUE;
        }
        return boolValueOf.booleanValue();
    }

    private void l1() {
        this.w0 = Long.valueOf(this.q0.lastModified());
        u0();
    }

    private void n1() {
        try {
            this.x0 = Long.valueOf(this.q0.length());
            s0();
        } catch (IllegalArgumentException unused) {
            this.x0 = -1L;
        }
    }

    private void o0() {
        if (V.y(this.s0)) {
            this.I0 = ax.G1.i.D().H(this.q0.getAbsolutePath(), R());
            return;
        }
        ax.p221pa.c.h().b("GLFLFI 2:").g().k().h("location:" + P() + ",path:" + this.s0 + ",name:" + this.q0.getName() + "," + this.q0.getAbsolutePath()).i();
        this.I0 = R();
    }

    private void p0() {
        this.r0 = C0735p.e(this, "application/octet-stream");
    }

    private void s0() {
        if (this.x0.longValue() != 0 || isDirectory()) {
            return;
        }
        if (ax.E1.P.H() && X0()) {
            try {
                h1();
                this.x0.longValue();
            } catch (Exception unused) {
            }
        }
        if (this.x0.longValue() == 0 && P() == ax.p338y1.f.v0 && !y0().exists() && j0()) {
            this.x0 = Long.valueOf(v.Y().s(C()));
        }
    }

    private void t0() {
        if (ax.E1.P.G() && R() == ax.J1.F.f && Q0()) {
            if (this.z0.booleanValue()) {
                return;
            }
            f1();
        } else {
            if (this.z0.booleanValue() || P() != ax.p338y1.f.v0 || this.q0.exists() || !j0()) {
                return;
            }
            this.z0 = Boolean.valueOf(v.Y().K(C()));
        }
    }

    private void u0() {
        if (this.w0.longValue() == 0 && P() == ax.p338y1.f.v0 && !y0().exists() && j0()) {
            this.w0 = Long.valueOf(v.Y().n(C()));
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public C5840n B() {
        if (R() == ax.J1.F.h && ax.G1.i.D().q()) {
            return v.Y().o(C());
        }
        return null;
    }

    public String B0() {
        if (this.t0 == null) {
            this.t0 = this.q0.getAbsolutePath();
        }
        return this.t0;
    }

    public String C0() {
        if (ax.p338y1.f.P(v0()) && Q0()) {
            return this.H0;
        }
        return null;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String D() {
        return this.s0;
    }

    public Uri D0() throws ax.I1.q {
        Uri uriN;
        if (this.C0 == null && ax.E1.P.F1()) {
            String strU = null;
            if (!Q0()) {
                strU = C5832f.u(u(), v0(), null);
            } else if (ax.G1.i.D().u0(v0())) {
                String strC0 = C0();
                if (strC0 != null && strC0.length() >= 14 && (uriN = C5832f.n(v0(), strC0)) != null && C5832f.a(u(), uriN)) {
                    strU = uriN.toString();
                }
            } else {
                strU = C5832f.u(u(), v0(), C0());
            }
            if (TextUtils.isEmpty(strU)) {
                throw new ax.I1.q("RootUri is empty");
            }
            this.C0 = Uri.parse(strU);
        }
        return this.C0;
    }

    public ax.E1.K E0() throws IOException {
        return ax.E1.u.k(B0(), new ax.E1.K());
    }

    public int F0() {
        if (this.G0 == 0) {
            if (!ax.E1.P.L() || !ax.p338y1.f.P(v0())) {
                this.G0 = 1;
            } else if (ax.G1.i.D().u0(v0())) {
                String strC0 = C0();
                if (strC0 == null) {
                    this.G0 = 1;
                } else if (strC0.startsWith("/Android/data")) {
                    this.G0 = strC0.hashCode() + 268435456;
                } else if (strC0.startsWith("/Android/obb")) {
                    this.G0 = strC0.hashCode() + 536870912;
                } else {
                    this.G0 = 1;
                }
            } else {
                String strW0 = w0();
                if (strW0.startsWith("/Android/data")) {
                    this.G0 = 268435456;
                } else if (strW0.startsWith("/Android/obb")) {
                    this.G0 = 536870912;
                } else {
                    this.G0 = 1;
                }
            }
        }
        return this.G0;
    }

    public boolean H0() {
        return this.J0;
    }

    public boolean I0() {
        try {
            return D0() != null;
        } catch (ax.I1.q unused) {
            return false;
        }
    }

    public boolean K0() {
        String strW0 = w0();
        if (strW0 != null) {
            return V.D("/Android", strW0);
        }
        return false;
    }

    public boolean L0() {
        ax.p338y1.f fVarP = P();
        if (isDirectory()) {
            return (this.z0 == null || this.B0 == null || this.A0 == null) ? false : true;
        }
        if (fVarP == ax.p338y1.f.B0) {
            return this.n0 != null;
        }
        if (fVarP == ax.p338y1.f.D0 || fVarP == ax.p338y1.f.C0) {
            return this.o0 != null;
        }
        return (this.z0 == null || this.B0 == null || this.A0 == null) ? false : true;
    }

    public boolean M0() {
        if (isDirectory()) {
            return (W() == -1 || W() == -3) ? false : true;
        }
        return this.x0 != null;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public File N() {
        return y0();
    }

    public boolean N0() {
        return this.I0.d() == ax.p338y1.f.r0;
    }

    public boolean P0() {
        if (this.F0 == null) {
            if (Q0()) {
                this.F0 = Boolean.valueOf(w0().split("/").length == 4);
            } else {
                this.F0 = Boolean.FALSE;
            }
        }
        return this.F0.booleanValue();
    }

    public boolean Q0() {
        String strW0;
        if (this.D0 == null) {
            if (ax.E1.P.L() && ax.p338y1.f.Q(v0()) && (strW0 = w0()) != null) {
                if (strW0.startsWith("/Android/data")) {
                    if (!V.E("/Android/data/com.alphainventor.filemanager", strW0)) {
                        this.D0 = Boolean.TRUE;
                        this.H0 = "/Android/data";
                    }
                } else if (strW0.startsWith("/Android/obb") && !V.E("/Android/obb/com.alphainventor.filemanager", strW0)) {
                    this.D0 = Boolean.TRUE;
                    this.H0 = "/Android/obb";
                }
                Boolean bool = this.D0;
                if (bool != null && bool.booleanValue() && ax.G1.i.D().u0(v0())) {
                    String[] strArrSplit = strW0.split("/");
                    if (strArrSplit.length >= 4) {
                        this.H0 += "/" + strArrSplit[3];
                    }
                }
            }
            if (this.D0 == null) {
                this.D0 = Boolean.FALSE;
            }
        }
        return this.D0.booleanValue();
    }

    public boolean R0() {
        if (this.E0 == null) {
            if (Q0()) {
                this.E0 = Boolean.valueOf(w0().split("/").length == 3);
            } else {
                this.E0 = Boolean.FALSE;
            }
        }
        return this.E0.booleanValue();
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String T() {
        return V.r(this.s0);
    }

    public boolean T0() {
        return this.O0;
    }

    public boolean V0() {
        return this.P0;
    }

    public void W0(boolean z) {
        int iU;
        if (W() == -1) {
            try {
                if (T0() && (iU = com.alphainventor.filemanager.shizuku.c.t().u(C(), z)) != -2) {
                    b0(iU);
                    return;
                }
                String[] list = this.q0.list(new a(z));
                if (list != null) {
                    b0(list.length);
                    return;
                }
                if ((P() == ax.p338y1.f.v0 || Q0()) && j0()) {
                    int iC = v.Y().C(C(), z);
                    if (iC < 0) {
                        b0(-2);
                        return;
                    } else {
                        b0(iC);
                        return;
                    }
                }
                if (!i0()) {
                    b0(-2);
                    return;
                }
                try {
                    try {
                        List<AbstractC5838l> listE = C5832f.E(this.p0, this);
                        if (listE != null) {
                            b0(listE.size());
                        } else {
                            b0(-2);
                        }
                    } catch (NullPointerException unused) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("NullPointer helper==null:");
                        sb.append(this.p0 == null);
                        sb.append(",");
                        sb.append(R().j());
                        ax.p338y1.d.c("HELPERNULL", new Exception(sb.toString()));
                        b0(-2);
                    }
                } catch (C0718i unused2) {
                    b0(-2);
                }
            } catch (OutOfMemoryError unused3) {
                b0(-2);
            }
        }
    }

    public boolean X0() {
        return Y0(true);
    }

    public boolean Y0(boolean z) {
        if (!ax.E1.P.F1() || k0() || V0()) {
            return false;
        }
        ax.J1.F fV0 = v0();
        if (fV0 == ax.J1.F.e) {
            return Q0();
        }
        if (fV0 != ax.J1.F.f) {
            return !ax.G1.i.D().d0(fV0);
        }
        if (ax.G1.i.D().d0(fV0)) {
            return Q0();
        }
        if (z) {
            return true;
        }
        return Q0();
    }

    public void Z0(boolean z) {
        this.y0 = Boolean.valueOf(z);
    }

    public void a1(b bVar) {
        this.K0 = Boolean.valueOf(bVar != b.VISIBLE);
        this.L0 = bVar;
    }

    public void b1(boolean z) {
        this.O0 = z;
    }

    @Override // ax.J1.AbstractC0726g
    public ParcelFileDescriptor c0() throws C0718i {
        return C5832f.o(u(), C5832f.e(v0(), D0(), C()), "r");
    }

    public void c1(long j) {
        this.w0 = Long.valueOf(j);
    }

    public boolean d1() {
        return false;
    }

    public void f1() {
        this.y0 = Boolean.valueOf(l0(false));
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        if (this.K0 == null) {
            this.K0 = Boolean.valueOf(y().startsWith("."));
        }
        return this.K0.booleanValue();
    }

    @Override // ax.J1.AbstractC0726g
    public boolean g0() {
        if (i0()) {
            return v0() == ax.J1.F.e ? Q0() : G0();
        }
        return false;
    }

    public void g1() {
        this.y0 = Boolean.valueOf(l0(true));
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        if (this.B0 == null) {
            Boolean boolValueOf = Boolean.valueOf(this.q0.canRead());
            this.B0 = boolValueOf;
            if (!boolValueOf.booleanValue() && X0()) {
                if (ax.G1.i.D().u0(v0()) && R0()) {
                    return true;
                }
                try {
                    h1();
                } catch (ax.I1.q unused) {
                } catch (Exception e) {
                    ax.p221pa.c.h().g().d("DOCUMENT FILE CANREAD").m(e).h(P().H()).i();
                }
            }
        }
        return this.B0.booleanValue();
    }

    public boolean i0() {
        if (!X0()) {
            return false;
        }
        if (ax.E1.I.k() && ax.E1.I.y(v0())) {
            return false;
        }
        return I0();
    }

    public void i1() throws IOException {
        j1(true, true, true, false);
        t0();
        u0();
        s0();
    }

    public boolean isDirectory() {
        if (this.z0 == null) {
            if (U0()) {
                this.z0 = Boolean.TRUE;
            } else {
                if (ax.E1.P.z1()) {
                    try {
                        i1();
                    } catch (Exception unused) {
                    }
                }
                if (this.z0 == null) {
                    e1();
                }
            }
        }
        return this.z0.booleanValue();
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        if (this.A0 == null) {
            Boolean boolValueOf = Boolean.valueOf(this.q0.canWrite());
            this.A0 = boolValueOf;
            if (!boolValueOf.booleanValue() && X0()) {
                try {
                    h1();
                } catch (ax.I1.q unused) {
                } catch (Exception e) {
                    ax.p221pa.c.h().g().d("DOCUMENT FILE CANWRITE").m(e).h("loc:" + R().toString()).i();
                }
            }
        }
        return this.A0.booleanValue();
    }

    public boolean k0() {
        return ax.E1.P.J1() && ax.G1.i.D().q() && R() == ax.J1.F.h;
    }

    public void k1() {
        if (!ax.E1.P.z1()) {
            l1();
            return;
        }
        try {
            i1();
        } catch (IOException unused) {
            l1();
        }
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        if (this.v0 == null) {
            if (P() == ax.p338y1.f.v0) {
                try {
                    this.v0 = Boolean.valueOf(C0740v.J(this.q0));
                } catch (IOException unused) {
                    this.v0 = Boolean.FALSE;
                }
            } else {
                this.v0 = Boolean.FALSE;
            }
        }
        return this.v0.booleanValue();
    }

    public boolean m0() {
        Long l = this.w0;
        if (l == null) {
            k1();
            return false;
        }
        long jLongValue = l.longValue();
        k1();
        return this.w0.longValue() != jLongValue;
    }

    public Uri m1() throws ax.I1.q {
        this.C0 = null;
        return D0();
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        if (this.y0 == null) {
            f1();
        }
        return this.y0.booleanValue();
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: n0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC5838l abstractC5838l) {
        if (abstractC5838l == null) {
            return -1;
        }
        try {
            return this.q0.compareTo(((u) abstractC5838l).q0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public long o() {
        if (this.x0 == null) {
            if (ax.E1.P.z1()) {
                try {
                    i1();
                } catch (Exception unused) {
                }
            }
            if (this.x0 == null) {
                n1();
            }
        }
        return this.x0.longValue();
    }

    public long q() {
        if (this.w0 == null) {
            k1();
        }
        return this.w0.longValue();
    }

    public boolean q0() {
        if (this.y0 == null) {
            g1();
        }
        return this.y0.booleanValue();
    }

    public int r(boolean z) {
        W0(z);
        return W();
    }

    public boolean r0() {
        return this.q0.exists();
    }

    @Override // ax.J1.InterfaceC0722c
    public String s() {
        if (this.r0 == null) {
            p0();
        }
        return this.r0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.q0.getAbsolutePath();
    }

    public ax.J1.F v0() {
        if (this.I0 == null) {
            o0();
            ax.p221pa.b bVarB = ax.p221pa.c.h().b("BASE LOCATION UNIT NULL 2");
            StringBuilder sb = new StringBuilder();
            sb.append("loc:");
            sb.append(R());
            sb.append(",path:");
            sb.append(D());
            sb.append(",constructor:");
            sb.append(this.N0);
            sb.append(",retry:");
            sb.append(this.I0 != null);
            bVarB.h(sb.toString()).i();
            if (this.I0 == null) {
                this.I0 = R();
            }
        }
        return this.I0;
    }

    public String w0() {
        if (!ax.p338y1.f.d0(v0().d())) {
            return V.u(v0(), C(), null);
        }
        ax.p045d2.b.e("library:?" + v0());
        return V.u(v0(), C(), Boolean.valueOf(isDirectory()));
    }

    public String x0() {
        ax.p338y1.f fVarP = P();
        if (fVarP == ax.p338y1.f.B0) {
            return !isDirectory() ? d0() : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        if (fVarP == ax.p338y1.f.D0 || fVarP == ax.p338y1.f.C0) {
            return !isDirectory() ? e0() : HttpUrl.FRAGMENT_ENCODE_SET;
        }
        return U();
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String y() {
        if (this.u0 == null) {
            this.u0 = this.q0.getName();
        }
        return this.u0;
    }

    public File y0() {
        return this.q0;
    }

    public b z0() {
        return this.L0;
    }

    public u(C5845t c5845t, File file, ax.J1.F f) {
        this(c5845t, file, f, false);
    }

    public u(C5845t c5845t, File file, ax.J1.F f, boolean z) {
        super(c5845t);
        this.L0 = b.VISIBLE;
        this.N0 = "no_constructor";
        this.p0 = c5845t;
        ax.p045d2.b.b(file);
        this.q0 = file;
        this.P0 = z;
        String absolutePath = file.getAbsolutePath();
        if (z) {
            this.s0 = ax.J1.E.d(absolutePath);
            this.t0 = absolutePath;
        } else {
            this.s0 = absolutePath;
            this.t0 = absolutePath;
        }
        this.I0 = f;
        if (f == null) {
            o0();
        }
        this.N0 = "constructor 1";
    }

    public u(C5845t c5845t, File file, ax.J1.F f, boolean z, boolean z2, boolean z3, boolean z4, long j, long j2) {
        this(c5845t, file, f);
        this.z0 = Boolean.valueOf(z);
        this.v0 = Boolean.valueOf(z2);
        this.B0 = Boolean.valueOf(z3);
        this.A0 = Boolean.valueOf(z4);
        this.x0 = Long.valueOf(j);
        this.w0 = Long.valueOf(j2);
    }

    public u(C5845t c5845t, u uVar) {
        super(c5845t);
        this.L0 = b.VISIBLE;
        this.N0 = "no_constructor";
        this.p0 = c5845t;
        this.C0 = uVar.C0;
        this.q0 = uVar.q0;
        this.s0 = uVar.s0;
        this.z0 = uVar.z0;
        this.v0 = uVar.v0;
        this.B0 = uVar.B0;
        this.A0 = uVar.A0;
        this.x0 = uVar.x0;
        this.w0 = uVar.w0;
        ax.J1.F f = uVar.I0;
        this.I0 = f;
        this.O0 = uVar.O0;
        this.P0 = uVar.P0;
        if (f == null) {
            o0();
        }
        this.N0 = "constructor 2";
    }

    public u(C5845t c5845t, ax.J1.F f, ax.Y1.a aVar) {
        super(c5845t);
        this.L0 = b.VISIBLE;
        this.N0 = "no_constructor";
        this.p0 = c5845t;
        this.O0 = true;
        this.q0 = new File(aVar.q);
        this.z0 = Boolean.valueOf(aVar.Z);
        this.v0 = Boolean.valueOf(aVar.h0);
        this.B0 = Boolean.valueOf(aVar.i0);
        this.A0 = Boolean.valueOf(aVar.j0);
        this.x0 = Long.valueOf(aVar.k0);
        this.w0 = Long.valueOf(aVar.l0);
        this.y0 = Boolean.valueOf(aVar.Y);
        this.u0 = aVar.X;
        this.s0 = aVar.q;
        this.I0 = f;
        if (f == null) {
            o0();
        }
    }

    public u(C5845t c5845t, Uri uri, ax.J1.F f, String str, Cursor cursor) throws C0718i {
        boolean z;
        String strM;
        super(c5845t);
        this.L0 = b.VISIBLE;
        this.N0 = "no_constructor";
        this.p0 = c5845t;
        C0727h c0727h = new C0727h(cursor);
        String strReplaceAll = c0727h.a;
        boolean z2 = true;
        if (strReplaceAll == null || !strReplaceAll.endsWith("/")) {
            z = false;
        } else {
            strReplaceAll = strReplaceAll.replaceAll(".$", "_");
            z = true;
        }
        String strP = C5832f.p(f.e(), strReplaceAll);
        if (V.F(str, strP, true)) {
            String strSubstring = V.o(str, strP, true).substring(1);
            if (strSubstring.contains("/")) {
                String strReplaceAll2 = strSubstring.replaceAll("/", "_");
                String strM2 = V.M(str, strReplaceAll2);
                if (strReplaceAll2.length() > 12) {
                    ax.p221pa.c.h().g().b("LOCAL DOCUMENT FILE NAME FIXED UNUSUAL").k().h("file:" + str + "," + c0727h.b + "," + c0727h.a + ",root:" + uri).i();
                }
                strP = strM2;
            } else {
                z2 = z;
            }
            if (TextUtils.isEmpty(c0727h.b)) {
                ax.p221pa.c.h().g().b("empty local document displayname").h("id:" + c0727h.a + ",root:" + uri + ",parent:" + str).i();
                strM = null;
            } else if (c0727h.b.contains("/")) {
                strM = V.M(str, c0727h.b.replaceAll("/", "_"));
            } else {
                strM = V.M(str, c0727h.b);
            }
            if (strM != null && !strM.equalsIgnoreCase(strP) && !"/".equals(strP)) {
                FileManagerApp.b("LOCAL DOCUMENT FILE NAME CONFLICT:" + str + "," + c0727h.b + "," + strM + "," + strP);
                if (z2) {
                    ax.p045d2.b.e("DOCUMENT PATH CONFLICT");
                } else {
                    ax.p221pa.c.h().g().b("LOCAL DOCUMENT FILE NAME CONFLICT 1").k().h("parent:" + str + "," + c0727h.b + "," + c0727h.a + ",root:" + uri).i();
                    throw new C0718i("path conflict");
                }
            }
            if (strP == null) {
                ax.p221pa.c.h().b("LOCAL DOCUMENT FILE PATH NULL").h("file:" + str + "," + c0727h.b + "," + strM + "," + strP).i();
            }
            this.C0 = uri;
            this.q0 = new File(strP);
            this.s0 = strP;
            this.z0 = Boolean.valueOf(c0727h.d());
            this.v0 = Boolean.FALSE;
            this.A0 = Boolean.valueOf(c0727h.b());
            this.B0 = Boolean.valueOf(c0727h.a());
            this.w0 = Long.valueOf(c0727h.d);
            this.x0 = Long.valueOf(c0727h.e);
            this.I0 = f;
            this.N0 = "constructor 3";
            return;
        }
        ax.p221pa.c.h().b("invalid file local document file path").h("parentPath:" + str + ",docId:" + c0727h.a);
        throw new C0718i("path problem");
    }
}
