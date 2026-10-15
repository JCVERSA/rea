package com.alphainventor.filemanager.file;

import ax.I1.C0718i;
import ax.J1.V;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class F extends u {
    private static final Logger Z0 = Logger.getLogger("FileManager.RecycleBinFileInfo");
    private boolean R0;
    private String S0;
    private String T0;
    private String U0;
    private String V0;
    private boolean W0;
    private long X0;
    private long Y0;

    public F(E e, u uVar, ax.J1.F f, boolean z) throws Throwable {
        super(e, uVar.y0(), f);
        this.R0 = z;
        this.S0 = uVar.C();
        s1(uVar);
    }

    private void s1(AbstractC5838l abstractC5838l) throws Throwable {
        String str;
        String strW = abstractC5838l.w();
        int iIndexOf = strW.indexOf("_");
        String strSubstring = strW.substring(0, iIndexOf);
        String strSubstring2 = strW.substring(iIndexOf + 1);
        int iIndexOf2 = strSubstring2.indexOf("_");
        String strSubstring3 = strSubstring2.substring(0, iIndexOf2);
        String strSubstring4 = strSubstring2.substring(iIndexOf2 + 1);
        String strSubstring5 = strSubstring4.substring(0, strSubstring4.indexOf("_"));
        try {
            this.X0 = Long.valueOf(strSubstring3).longValue();
        } catch (NumberFormatException unused) {
        }
        try {
            this.Y0 = Long.valueOf(strSubstring5).longValue();
        } catch (NumberFormatException unused2) {
        }
        if ("D".equals(strSubstring)) {
            this.W0 = true;
        } else {
            this.W0 = false;
        }
        E.c cVarF2 = E.f2(abstractC5838l, v0());
        if (cVarF2.a()) {
            String str2 = cVarF2.b;
            this.U0 = str2;
            this.V0 = cVarF2.c;
            if (str2 != null) {
                this.T0 = V.h(str2);
                return;
            }
            return;
        }
        Z0.fine("INVALID INDEX : " + cVarF2.a + "," + cVarF2.b);
        if (v0() != null) {
            str = v0().toString() + "," + abstractC5838l.o();
        } else {
            str = HttpUrl.FRAGMENT_ENCODE_SET + abstractC5838l.o();
        }
        throw new C0718i("Invalid RecycleBin Index File : " + str);
    }

    @Override // com.alphainventor.filemanager.file.u, com.alphainventor.filemanager.file.AbstractC5838l
    public String D() {
        return this.R0 ? this.U0 : super.D();
    }

    @Override // com.alphainventor.filemanager.file.u, com.alphainventor.filemanager.file.AbstractC5838l
    public String T() {
        return this.R0 ? V.r(this.U0) : super.T();
    }

    @Override // com.alphainventor.filemanager.file.u, ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        return this.R0 ? this.W0 : super.isDirectory();
    }

    @Override // com.alphainventor.filemanager.file.u, ax.J1.InterfaceC0722c
    public long o() {
        return this.R0 ? this.Y0 : super.o();
    }

    public String o1() {
        return this.S0;
    }

    public String p1() {
        return this.U0;
    }

    @Override // com.alphainventor.filemanager.file.u, ax.J1.InterfaceC0722c
    public long q() {
        return this.R0 ? this.X0 : super.q();
    }

    public String q1() {
        return this.V0;
    }

    @Override // com.alphainventor.filemanager.file.u, ax.J1.InterfaceC0722c
    public int r(boolean z) {
        if (this.R0) {
            return this.Y0 > 0 ? 1 : -1;
        }
        return super.r(z);
    }

    public boolean r1() {
        return this.R0;
    }

    @Override // com.alphainventor.filemanager.file.u, com.alphainventor.filemanager.file.AbstractC5838l
    public String y() {
        return this.R0 ? this.T0 : super.y();
    }
}
