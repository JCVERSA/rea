package com.alphainventor.filemanager.file;

import android.text.TextUtils;
import ax.p086f3.C2015v;
import ax.p086f3.d0;

/* JADX INFO: loaded from: classes.dex */
public class Z extends AbstractC6239n {
    private final ax.Ib.e q0;
    private final String r0;

    Z(Y y, ax.Ib.e eVar, String str) {
        super(y);
        this.q0 = eVar;
        if (eVar == null || eVar.g() == null) {
            this.r0 = d0.U(str);
        } else {
            this.r0 = d0.U(eVar.g().a());
        }
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    protected String C() {
        ax.Ib.e eVar = this.q0;
        return (eVar == null || TextUtils.isEmpty(eVar.e())) ? d0.h(this.r0) : this.q0.e();
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
        return this.r0.compareTo(abstractC6239n.E());
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        return B().startsWith(".");
    }

    public String g0() {
        ax.Ib.e eVar = this.q0;
        if (eVar == null) {
            return null;
        }
        return eVar.h();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        return o();
    }

    boolean h0() {
        ax.Ib.e eVar = this.q0;
        return (eVar == null || TextUtils.isEmpty(eVar.h())) ? false : true;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        if (d0.a.equals(this.r0)) {
            return true;
        }
        ax.Ib.e eVar = this.q0;
        return eVar != null && eVar.k();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        return o();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return false;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return d0.a.equals(this.r0) || this.q0 != null;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        if (this.q0 == null || isDirectory()) {
            return 0L;
        }
        return this.q0.j();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        ax.Ib.e eVar = this.q0;
        if (eVar == null || eVar.d() == null) {
            return -1L;
        }
        return this.q0.d().getTime();
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
        ax.Ib.e eVar = this.q0;
        if (eVar == null || TextUtils.isEmpty(eVar.c())) {
            return C2015v.e(this, "application/octet-stream");
        }
        String strC = this.q0.c();
        return ("text/plain".equals(strC) && p() == 0) ? C2015v.e(this, strC) : this.q0.c();
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.r0;
    }
}
