package com.alphainventor.filemanager.file;

import ax.J1.C0735p;
import ax.J1.V;
import ax.Ta.C4736a0;
import ax.Ta.C4738b0;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class M extends AbstractC5838l {
    private String n0;
    private String o0;
    private String p0;
    private boolean q0;
    private boolean r0;
    private boolean s0;
    private boolean t0;
    private boolean u0;
    private boolean v0;
    private long w0;
    private long x0;
    private C4738b0 y0;
    private final boolean z0;

    public M(L l, String str, boolean z, boolean z2, boolean z3, boolean z4) {
        super(l);
        this.v0 = false;
        this.z0 = z4;
        this.o0 = str;
        this.p0 = l.a0(str);
        this.t0 = z;
        this.q0 = z2;
        this.r0 = true;
        this.s0 = true;
        this.u0 = z3;
        this.w0 = -1L;
        this.x0 = 0L;
    }

    public static M d0(L l, String str, boolean z) {
        return new M(l, str, false, false, false, z);
    }

    public static M e0(L l, String str) {
        return new M(l, str, true, true, str.endsWith("$"), false);
    }

    private void f0() {
        this.n0 = C0735p.e(this, HttpUrl.FRAGMENT_ENCODE_SET);
    }

    private void g0(long j) {
        this.q0 = (ax.E8.a.FILE_ATTRIBUTE_DIRECTORY.getValue() & j) != 0;
        this.s0 = (ax.E8.a.FILE_ATTRIBUTE_READONLY.getValue() & j) == 0;
        this.u0 = (ax.E8.a.FILE_ATTRIBUTE_HIDDEN.getValue() & j) != 0;
        this.v0 = (j & ax.E8.a.FILE_ATTRIBUTE_REPARSE_POINT.getValue()) != 0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String D() {
        return this.o0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String T() {
        return V.r(this.o0);
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public boolean Z() {
        if (ax.E1.P.f1()) {
            return !this.z0;
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: c0, reason: merged with bridge method [inline-methods] */
    public int compareTo(AbstractC5838l abstractC5838l) {
        try {
            return this.o0.compareTo(((M) abstractC5838l).o0);
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean g() {
        return this.u0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean h() {
        return this.r0;
    }

    public C4738b0 h0() {
        return this.y0;
    }

    public void i0(C4738b0 c4738b0) {
        this.y0 = c4738b0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean isDirectory() {
        return this.q0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean k() {
        return this.s0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean m() {
        return this.v0;
    }

    @Override // ax.J1.InterfaceC0722c
    public boolean n() {
        return this.t0;
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
        if (isDirectory()) {
            return W();
        }
        return -2;
    }

    @Override // ax.J1.InterfaceC0722c
    public String s() {
        if (this.n0 == null) {
            f0();
        }
        return this.n0;
    }

    @Override // ax.J1.InterfaceC0722c
    public String t() {
        return this.p0;
    }

    @Override // com.alphainventor.filemanager.file.AbstractC5838l
    public String y() {
        return V.h(this.o0);
    }

    public M(L l, String str, ax.G8.m mVar) {
        super(l);
        this.v0 = false;
        this.z0 = false;
        this.o0 = str;
        this.p0 = l.a0(str);
        this.t0 = true;
        this.r0 = true;
        g0(mVar.d());
        if (mVar.e() != null) {
            this.w0 = mVar.e().g();
        }
        this.x0 = mVar.c();
    }

    public M(L l, String str, ax.G8.c cVar) throws ax.p024b9.d {
        super(l);
        this.v0 = false;
        this.z0 = false;
        ax.G8.e eVarA = cVar.a();
        this.o0 = str;
        this.p0 = l.a0(str);
        this.t0 = true;
        this.r0 = true;
        g0(eVarA.c());
        if (eVarA.e() != null) {
            this.w0 = eVarA.e().g();
        }
        this.x0 = cVar.b().a();
    }

    public M(L l, C4738b0 c4738b0) throws C4736a0 {
        super(l);
        this.v0 = false;
        this.z0 = true;
        this.y0 = c4738b0;
        this.o0 = I.g(l, c4738b0);
        this.p0 = c4738b0.getURL().toString();
        this.t0 = c4738b0.s();
        f0();
        if (this.t0) {
            this.q0 = c4738b0.G();
            this.r0 = c4738b0.b();
            this.s0 = c4738b0.c();
            this.u0 = c4738b0.H();
            this.w0 = c4738b0.K();
            this.x0 = c4738b0.L();
            return;
        }
        this.q0 = false;
        this.r0 = true;
        this.s0 = true;
        this.u0 = false;
        this.w0 = -1L;
        this.x0 = 0L;
    }
}
