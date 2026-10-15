package com.alphainventor.filemanager.file;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import ax.I1.C0718i;
import ax.J1.AbstractC0726g;
import ax.J1.C0727h;
import ax.J1.C0735p;
import ax.J1.V;
import ax.P5.C1452Re;
import com.alphainventor.filemanager.FileManagerApp;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(C1452Re.zzm)
public class C5834h extends AbstractC0726g {
    private String p0;
    private String q0;
    private String r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private boolean v0;
    private long w0;
    private long x0;
    private Uri y0;

    public C5834h(C5833g c5833g, String str, Cursor cursor) {
        boolean z;
        String strM;
        super(c5833g);
        this.y0 = c5833g.Q();
        C0727h c0727h = new C0727h(cursor);
        String strReplaceAll = c0727h.a;
        if (strReplaceAll == null || !strReplaceAll.endsWith("/")) {
            z = false;
        } else {
            strReplaceAll = strReplaceAll.replaceAll(".$", "_");
            z = true;
        }
        String strP = C5832f.p(R().e(), strReplaceAll);
        if (V.E(str, strP)) {
            String strSubstring = V.n(str, strP).substring(1);
            if (strSubstring.contains("/")) {
                String strReplaceAll2 = strSubstring.replaceAll("/", "_");
                String strM2 = V.M(str, strReplaceAll2);
                if (strReplaceAll2.length() > 12) {
                    ax.p221pa.c.h().g().b("DOCUMENT FILE NAME FIXED UNUSUAL").k().h("file:" + str + "," + c0727h.b + "," + c0727h.a + "," + this.y0).i();
                }
                strP = strM2;
                z = true;
            }
        } else {
            ax.p221pa.c.h().b("invalid file document file path").h("parentPath:" + str + ",docId:" + c0727h.a);
        }
        if (TextUtils.isEmpty(c0727h.b)) {
            ax.p221pa.c.h().g().b("empty document displayname").h("docid:" + c0727h.a + ",root:" + this.y0).i();
            strM = null;
        } else {
            strM = c0727h.b.contains("/") ? V.M(str, c0727h.b.replaceAll("/", "_")) : V.M(str, c0727h.b);
        }
        if (strM != null && !strM.equals(strP) && !"/".equals(strP)) {
            FileManagerApp.b("DOCUMENT FILE NAME CONFLICT:" + str + "," + c0727h.b + "," + strM + "," + strP);
            if (!z) {
                ax.p221pa.c.h().g().b("DOCUMENT FILE NAME CONFLICT 1").k().h("file:" + str + "," + c0727h.b + "," + c0727h.a + "," + this.y0).i();
            }
        }
        if (strP == null) {
            ax.p221pa.c.h().g().b("DOCUMENT FILE PATH NULL").h("file:" + str + "," + c0727h.b + "," + strM + "," + strP).i();
        }
        this.q0 = strP;
        this.r0 = V.h(strP);
        this.s0 = true;
        this.t0 = c0727h.d();
        this.v0 = c0727h.b();
        this.u0 = c0727h.a();
        this.w0 = c0727h.d;
        this.x0 = c0727h.e;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String D() {
        return this.q0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String T() {
        return V.r(this.q0);
    }

    @Override // ax.J1.AbstractC0726g
    public ParcelFileDescriptor c0() throws C0718i {
        return C5832f.o(u(), C5832f.e(R(), this.y0, C()), "r");
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        return y().startsWith(".");
    }

    @Override // ax.J1.AbstractC0726g
    public boolean g0() {
        return true;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        return this.u0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: h0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC5838l abstractC5838l) {
        try {
            return this.q0.compareTo(((C5834h) abstractC5838l).q0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public Uri i0() {
        return this.y0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        return this.t0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        return this.v0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        return false;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        return this.s0;
    }

    @Override // ax.J1.InterfaceC0722c
    public long o() {
        return this.x0;
    }

    @Override // ax.J1.InterfaceC0722c
    public long q() {
        return this.w0;
    }

    @Override // ax.J1.InterfaceC0722c
    public int r(boolean z) {
        if (this.t0) {
            return W();
        }
        return -2;
    }

    @Override // ax.J1.InterfaceC0722c
    public String s() {
        if (this.p0 == null) {
            this.p0 = C0735p.e(this, "application/octet-stream");
        }
        return this.p0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.q0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    protected String y() {
        return this.r0;
    }

    public C5834h(C5833g c5833g, String str) throws C0718i {
        super(c5833g);
        this.y0 = c5833g.Q();
        this.q0 = str;
        this.r0 = V.h(str);
        this.s0 = false;
        this.t0 = false;
        this.v0 = true;
        this.u0 = true;
        this.w0 = -1L;
        this.x0 = 0L;
    }
}
