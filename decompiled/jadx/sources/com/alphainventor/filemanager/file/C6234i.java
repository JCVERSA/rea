package com.alphainventor.filemanager.file;

import android.annotation.TargetApi;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import ax.p070e3.C1966j;
import ax.p086f3.AbstractC2005k;
import ax.p086f3.C2006l;
import ax.p086f3.C2015v;
import ax.p086f3.C2017x;
import ax.p086f3.d0;
import ax.p406z3.C2461b;
import ax.p409z6.C3772df;
import com.alphainventor.filemanager.FileManagerApp;

/* JADX INFO: renamed from: com.alphainventor.filemanager.file.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
@TargetApi(C3772df.zzm)
public class C6234i extends AbstractC2005k {
    private boolean A0;
    private long B0;
    private long C0;
    private Uri D0;
    private String s0;
    private String t0;
    private String u0;
    private String v0;
    private String w0;
    private boolean x0;
    private boolean y0;
    private boolean z0;

    public C6234i(C6233h c6233h, String str) throws C1966j {
        super(c6233h);
        this.D0 = c6233h.X();
        this.t0 = str;
        this.w0 = d0.h(str);
        this.x0 = false;
        this.y0 = false;
        this.A0 = true;
        this.z0 = true;
        this.B0 = -1L;
        this.C0 = 0L;
    }

    public C6234i(C6233h c6233h, String str, Cursor cursor) throws C1966j {
        String strReplaceAll;
        boolean z;
        String strQ;
        super(c6233h);
        this.D0 = c6233h.X();
        C2006l c2006l = new C2006l(cursor);
        String str2 = c2006l.a;
        this.u0 = str2;
        if (str2 == null || !str2.endsWith("/")) {
            strReplaceAll = str2;
            z = false;
        } else {
            strReplaceAll = str2.replaceAll(".$", "_");
            z = true;
        }
        String strQ2 = C6232g.q(x(), S(), this.D0, strReplaceAll, str, c2006l);
        if (d0.I(str, strQ2)) {
            String strSubstring = d0.n(str, strQ2).substring(1);
            if (strSubstring.contains("/")) {
                String strReplaceAll2 = strSubstring.replaceAll("/", "_");
                strQ2 = d0.Q(str, strReplaceAll2);
                if (strReplaceAll2.length() > 12) {
                    ax.p302sb.c.h().g().b("DOCUMENT FILE NAME FIXED UNUSUAL").k().h("file:" + str + "," + c2006l.b + "," + c2006l.a + "," + this.D0).i();
                }
                z = true;
            }
        } else {
            ax.p302sb.c.h().b("invalid file document file path").h("parentPath:" + str + ",docId:" + c2006l.a);
        }
        if (TextUtils.isEmpty(c2006l.b)) {
            ax.p302sb.c.h().g().b("empty document displayname").h("docid:" + c2006l.a + ",root:" + this.D0).i();
            strQ = null;
        } else {
            strQ = c2006l.b.contains("/") ? d0.Q(str, c2006l.b.replaceAll("/", "_")) : d0.Q(str, c2006l.b);
        }
        if (strQ != null && !strQ.equals(strQ2) && !"/".equals(strQ2)) {
            FileManagerApp.b("DOCUMENT FILE NAME CONFLICT:" + str + "," + c2006l.b + "," + strQ + "," + strQ2);
            if (!z) {
                ax.p302sb.c.h().g().b("DOCUMENT FILE NAME CONFLICT 1").k().h("file:" + str + "," + c2006l.b + "," + c2006l.a + "," + this.D0).i();
            }
        }
        if (strQ2 == null) {
            ax.p302sb.c.h().g().b("DOCUMENT FILE PATH NULL").h("file:" + str + "," + c2006l.b + "," + strQ + "," + strQ2).i();
        }
        this.t0 = strQ2;
        this.w0 = d0.h(strQ2);
        this.x0 = true;
        this.y0 = c2006l.d();
        this.A0 = c2006l.b();
        this.z0 = c2006l.a();
        this.B0 = c2006l.d;
        this.C0 = c2006l.e;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String C() {
        return this.w0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String F() {
        return this.t0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String V() {
        return d0.r(this.t0);
    }

    @Override // ax.p086f3.AbstractC2005k
    public ParcelFileDescriptor f0(boolean z) throws C1966j {
        return z ? C6232g.o(x(), o0(), "r") : C6232g.o(x(), o0(), "rw");
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        return C().startsWith(".");
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        return this.z0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        return this.y0;
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean j0() {
        return true;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        return this.A0;
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean k0() {
        return false;
    }

    @Override // ax.p086f3.AbstractC2005k
    public boolean l0() {
        return true;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: m0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC6239n abstractC6239n) {
        try {
            return this.t0.compareTo(((C6234i) abstractC6239n).t0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    public String n0() {
        return this.u0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return this.x0;
    }

    public Uri o0() throws C1966j {
        if (ax.U2.f.b0(Q())) {
            return C6232g.e(S(), this.D0, E());
        }
        if (Q() != ax.U2.f.m1) {
            C2461b.f();
            throw new C1966j("not reachable");
        }
        if (this.u0 != null) {
            return DocumentsContract.buildDocumentUriUsingTree(q0(), this.u0);
        }
        throw new ax.p070e3.t("no doc id");
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        return this.C0;
    }

    public Uri p0() throws C1966j {
        if (ax.U2.f.b0(Q())) {
            return C6232g.e(S(), this.D0, U());
        }
        if (Q() != ax.U2.f.m1) {
            C2461b.f();
            throw new C1966j("not reachable");
        }
        if (this.v0 == null) {
            this.v0 = ((C6234i) C2017x.e(S()).a1(U())).n0();
        }
        return DocumentsContract.buildDocumentUriUsingTree(q0(), this.v0);
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        return this.B0;
    }

    public Uri q0() {
        return this.D0;
    }

    public void r0(String str) {
        this.v0 = str;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public int s(boolean z) {
        if (this.y0) {
            return Y();
        }
        return -2;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String t() {
        if (this.s0 == null) {
            this.s0 = C2015v.e(this, "application/octet-stream");
        }
        return this.s0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.t0;
    }
}
