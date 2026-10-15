package com.alphainventor.filemanager.file;

import ax.p070e3.C1966j;
import ax.p086f3.d0;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class K extends C6249y {
    private static final Logger g1 = Logger.getLogger("FileManager.RecycleBinFileInfo");
    private boolean Y0;
    private String Z0;
    private String a1;
    private String b1;
    private String c1;
    private boolean d1;
    private long e1;
    private long f1;

    public K(J j, C6249y c6249y, ax.p086f3.K k, boolean z) throws Throwable {
        super(j, c6249y.G0(), k);
        this.Y0 = z;
        this.Z0 = c6249y.E();
        F1(c6249y);
    }

    private void F1(AbstractC6239n abstractC6239n) throws Throwable {
        String str;
        String strB = abstractC6239n.B();
        int iIndexOf = strB.indexOf("_");
        String strSubstring = strB.substring(0, iIndexOf);
        String strSubstring2 = strB.substring(iIndexOf + 1);
        int iIndexOf2 = strSubstring2.indexOf("_");
        String strSubstring3 = strSubstring2.substring(0, iIndexOf2);
        String strSubstring4 = strSubstring2.substring(iIndexOf2 + 1);
        String strSubstring5 = strSubstring4.substring(0, strSubstring4.indexOf("_"));
        try {
            this.e1 = Long.valueOf(strSubstring3).longValue();
        } catch (NumberFormatException unused) {
        }
        try {
            this.f1 = Long.valueOf(strSubstring5).longValue();
        } catch (NumberFormatException unused2) {
        }
        if ("D".equals(strSubstring)) {
            this.d1 = true;
        } else {
            this.d1 = false;
        }
        J.c cVarP2 = J.p2(abstractC6239n, C0());
        if (cVarP2.a()) {
            String str2 = cVarP2.b;
            this.b1 = str2;
            this.c1 = cVarP2.c;
            if (str2 != null) {
                this.a1 = d0.h(str2);
                return;
            }
            return;
        }
        g1.fine("INVALID INDEX : " + cVarP2.a + "," + cVarP2.b);
        if (C0() != null) {
            str = C0().toString() + "," + abstractC6239n.p();
        } else {
            str = HttpUrl.FRAGMENT_ENCODE_SET + abstractC6239n.p();
        }
        throw new C1966j("Invalid RecycleBin Index File : " + str);
    }

    public String B1() {
        return this.Z0;
    }

    @Override // com.alphainventor.filemanager.file.C6249y, com.alphainventor.filemanager.file.AbstractC6239n
    public String C() {
        return this.Y0 ? this.a1 : super.C();
    }

    public String C1() {
        return this.b1;
    }

    public String D1() {
        return this.c1;
    }

    public boolean E1() {
        return this.Y0;
    }

    @Override // com.alphainventor.filemanager.file.C6249y, com.alphainventor.filemanager.file.AbstractC6239n
    public String F() {
        return this.Y0 ? this.b1 : super.F();
    }

    @Override // com.alphainventor.filemanager.file.C6249y, com.alphainventor.filemanager.file.AbstractC6239n
    public String V() {
        return this.Y0 ? d0.r(this.b1) : super.V();
    }

    @Override // com.alphainventor.filemanager.file.C6249y, ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        return this.Y0 ? this.d1 : super.isDirectory();
    }

    @Override // com.alphainventor.filemanager.file.C6249y, ax.p086f3.InterfaceC1996b
    public long p() {
        return this.Y0 ? this.f1 : super.p();
    }

    @Override // com.alphainventor.filemanager.file.C6249y, ax.p086f3.InterfaceC1996b
    public long q() {
        return this.Y0 ? this.e1 : super.q();
    }

    @Override // com.alphainventor.filemanager.file.C6249y, ax.p086f3.InterfaceC1996b
    public int s(boolean z) {
        if (this.Y0) {
            return this.f1 > 0 ? 1 : -1;
        }
        return super.s(z);
    }
}
