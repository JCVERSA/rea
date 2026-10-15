package com.alphainventor.filemanager.file;

import ax.p063dc.C1925a0;
import ax.p063dc.C1927b0;
import ax.p086f3.C2015v;
import ax.p086f3.d0;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class S extends AbstractC6239n {
    private long A0;
    private C1927b0 B0;
    private final boolean C0;
    private String q0;
    private String r0;
    private String s0;
    private boolean t0;
    private boolean u0;
    private boolean v0;
    private boolean w0;
    private boolean x0;
    private boolean y0;
    private long z0;

    public S(Q q, C1927b0 c1927b0) throws C1925a0 {
        super(q);
        this.y0 = false;
        this.C0 = true;
        this.B0 = c1927b0;
        this.r0 = N.g(q, c1927b0);
        this.s0 = c1927b0.getURL().toString();
        this.w0 = c1927b0.s();
        i0();
        if (this.w0) {
            this.t0 = c1927b0.G();
            this.u0 = c1927b0.b();
            this.v0 = c1927b0.c();
            this.x0 = c1927b0.H();
            this.z0 = c1927b0.K();
            this.A0 = c1927b0.L();
            return;
        }
        this.t0 = false;
        this.u0 = true;
        this.v0 = true;
        this.x0 = false;
        this.z0 = -1L;
        this.A0 = 0L;
    }

    public S(Q q, String str, ax.p332u9.c cVar) throws ax.R9.d {
        super(q);
        this.y0 = false;
        this.C0 = false;
        ax.p332u9.e eVarA = cVar.a();
        this.r0 = str;
        this.s0 = q.g0(str);
        this.w0 = true;
        this.u0 = true;
        j0(eVarA.c());
        if (eVarA.e() != null) {
            this.z0 = eVarA.e().g();
        }
        this.A0 = cVar.b().a();
    }

    public S(Q q, String str, ax.p332u9.m mVar) {
        super(q);
        this.y0 = false;
        this.C0 = false;
        this.r0 = str;
        this.s0 = q.g0(str);
        this.w0 = true;
        this.u0 = true;
        j0(mVar.d());
        if (mVar.e() != null) {
            this.z0 = mVar.e().g();
        }
        this.A0 = mVar.c();
    }

    public S(Q q, String str, boolean z, boolean z2, boolean z3, boolean z4) {
        super(q);
        this.y0 = false;
        this.C0 = z4;
        this.r0 = str;
        this.s0 = q.g0(str);
        this.w0 = z;
        this.t0 = z2;
        this.u0 = true;
        this.v0 = true;
        this.x0 = z3;
        this.z0 = -1L;
        this.A0 = 0L;
    }

    public static S g0(Q q, String str, boolean z) {
        return new S(q, str, false, false, false, z);
    }

    public static S h0(Q q, String str) {
        return new S(q, str, true, true, str.endsWith("$"), false);
    }

    private void i0() {
        this.q0 = C2015v.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    private void j0(long j) {
        this.t0 = (ax.p300s9.a.FILE_ATTRIBUTE_DIRECTORY.getValue() & j) != 0;
        this.v0 = (ax.p300s9.a.FILE_ATTRIBUTE_READONLY.getValue() & j) == 0;
        this.x0 = (ax.p300s9.a.FILE_ATTRIBUTE_HIDDEN.getValue() & j) != 0;
        this.y0 = (j & ax.p300s9.a.FILE_ATTRIBUTE_REPARSE_POINT.getValue()) != 0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String C() {
        return d0.h(this.r0);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String F() {
        return this.r0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public String V() {
        return d0.r(this.r0);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC6239n
    public boolean c0() {
        if (ax.p006a3.Q.t1()) {
            return !this.C0;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC6239n abstractC6239n) {
        try {
            return this.r0.compareTo(((S) abstractC6239n).r0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean g() {
        return this.x0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean h() {
        return this.u0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean isDirectory() {
        return this.t0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean k() {
        return this.v0;
    }

    public C1927b0 k0() {
        return this.B0;
    }

    public void l0(C1927b0 c1927b0) {
        this.B0 = c1927b0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean m() {
        return this.y0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public boolean o() {
        return this.w0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long p() {
        return this.A0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public long q() {
        return this.z0;
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
        if (this.q0 == null) {
            i0();
        }
        return this.q0;
    }

    @Override // ax.p086f3.InterfaceC1996b
    public String u() {
        return this.s0;
    }
}
