package com.alphainventor.filemanager.file;

import ax.p070e3.C1966j;
import ax.p086f3.C2015v;
import ax.p086f3.d0;

/* JADX INFO: loaded from: classes.dex */
public class X extends AbstractC6239n {
    ax.p350vb.c q0;
    String r0;
    String s0;

    public X(W w, ax.p350vb.c cVar) throws C1966j {
        super(w);
        this.q0 = cVar;
        this.r0 = w.Y(cVar.k());
    }

    public X(W w, String str) {
        super(w);
        this.r0 = str;
    }

    private boolean g0() {
        ax.p350vb.c cVar = this.q0;
        if (cVar == null || cVar.q() == null) {
            return true;
        }
        return i0(this.q0.q());
    }

    private static boolean i0(String str) {
        try {
            return Integer.parseInt(str) == 1;
        } catch (NumberFormatException unused) {
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String C() {
        return d0.h(this.r0);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String F() {
        return this.r0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String V() {
        return d0.r(this.r0);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC6239n abstractC6239n) {
        try {
            return this.r0.compareTo(((X) abstractC6239n).r0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        if (B().startsWith(".")) {
            return true;
        }
        return !g0();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        return true;
    }

    public boolean h0() {
        ax.p350vb.c cVar = this.q0;
        if (cVar == null) {
            return false;
        }
        return cVar.i();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        if ("/".equals(this.r0)) {
            return true;
        }
        ax.p350vb.c cVar = this.q0;
        return cVar != null && cVar.s();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        ax.p350vb.c cVar = this.q0;
        if (cVar == null || cVar.n() == null) {
            return true;
        }
        return !Boolean.parseBoolean(this.q0.n());
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return false;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return "/".equals(this.r0) || this.q0 != null;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        ax.p350vb.c cVar = this.q0;
        if (cVar == null) {
            return 0L;
        }
        return cVar.c().longValue();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        ax.p350vb.c cVar = this.q0;
        if (cVar == null || cVar.l() == null) {
            return -1L;
        }
        return this.q0.l().getTime();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public int s(boolean z) {
        if (isDirectory()) {
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
        return this.r0;
    }
}
