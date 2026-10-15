package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import ax.p265q6.C2254p;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class Z1 {
    private Long A;
    private Long B;
    private long C;
    private String D;
    private int E;
    private int F;
    private long G;
    private String H;
    private byte[] I;
    private long J;
    private long K;
    private long L;
    private long M;
    private long N;
    private long O;
    private String P;
    private boolean Q;
    private long R;
    private long S;
    private final E2 a;
    private final String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private long g;
    private long h;
    private long i;
    private String j;
    private long k;
    private String l;
    private long m;
    private long n;
    private boolean o;
    private boolean p;
    private String q;
    private Boolean r;
    private long s;
    private List<String> t;
    private String u;
    private boolean v;
    private long w;
    private long x;
    private int y;
    private boolean z;

    Z1(E2 e2, String str) {
        C2254p.l(e2);
        C2254p.f(str);
        this.a = e2;
        this.b = str;
        e2.l().n();
    }

    public final boolean A() {
        this.a.l().n();
        return this.o;
    }

    public final void A0(long j) {
        C2254p.a(j >= 0);
        this.a.l().n();
        this.Q |= this.g != j;
        this.g = j;
    }

    public final boolean B() {
        this.a.l().n();
        return this.Q;
    }

    public final long B0() {
        this.a.l().n();
        return this.G;
    }

    public final boolean C() {
        this.a.l().n();
        return this.v;
    }

    public final void C0(long j) {
        this.a.l().n();
        this.Q |= this.h != j;
        this.h = j;
    }

    public final boolean D() {
        this.a.l().n();
        return this.z;
    }

    public final long D0() {
        this.a.l().n();
        return this.i;
    }

    public final byte[] E() {
        this.a.l().n();
        return this.I;
    }

    public final void E0(long j) {
        this.a.l().n();
        this.Q |= this.x != j;
        this.x = j;
    }

    public final int F() {
        this.a.l().n();
        return this.F;
    }

    public final long F0() {
        this.a.l().n();
        return this.g;
    }

    public final void G(int i) {
        this.a.l().n();
        this.Q |= this.F != i;
        this.F = i;
    }

    public final void G0(long j) {
        this.a.l().n();
        this.Q |= this.w != j;
        this.w = j;
    }

    public final void H(long j) {
        this.a.l().n();
        this.Q |= this.k != j;
        this.k = j;
    }

    public final long H0() {
        this.a.l().n();
        return this.h;
    }

    public final void I(Long l) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.B, l);
        this.B = l;
    }

    public final long I0() {
        this.a.l().n();
        return this.x;
    }

    public final void J(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.c, str);
        this.c = str;
    }

    public final long J0() {
        this.a.l().n();
        return this.w;
    }

    public final void K(boolean z) {
        this.a.l().n();
        this.Q |= this.o != z;
        this.o = z;
    }

    public final Boolean K0() {
        this.a.l().n();
        return this.r;
    }

    public final int L() {
        this.a.l().n();
        return this.E;
    }

    public final Long L0() {
        this.a.l().n();
        return this.A;
    }

    public final void M(int i) {
        this.a.l().n();
        this.Q |= this.E != i;
        this.E = i;
    }

    public final Long M0() {
        this.a.l().n();
        return this.B;
    }

    public final void N(long j) {
        this.a.l().n();
        this.Q |= this.C != j;
        this.C = j;
    }

    public final void O(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.l, str);
        this.l = str;
    }

    public final void P(boolean z) {
        this.a.l().n();
        this.Q |= this.v != z;
        this.v = z;
    }

    public final long Q() {
        this.a.l().n();
        return 0L;
    }

    public final void R(long j) {
        this.a.l().n();
        this.Q |= this.R != j;
        this.R = j;
    }

    public final void S(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.j, str);
        this.j = str;
    }

    public final void T(boolean z) {
        this.a.l().n();
        this.Q |= this.z != z;
        this.z = z;
    }

    public final long U() {
        this.a.l().n();
        return this.k;
    }

    public final void V(long j) {
        this.a.l().n();
        this.Q |= this.M != j;
        this.M = j;
    }

    public final void W(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.f, str);
        this.f = str;
    }

    public final long X() {
        this.a.l().n();
        return this.C;
    }

    public final void Y(long j) {
        this.a.l().n();
        this.Q |= this.N != j;
        this.N = j;
    }

    public final void Z(String str) {
        this.a.l().n();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.Q |= !Objects.equals(this.d, str);
        this.d = str;
    }

    public final int a() {
        this.a.l().n();
        return this.y;
    }

    public final long a0() {
        this.a.l().n();
        return this.R;
    }

    public final void b(int i) {
        this.a.l().n();
        this.Q |= this.y != i;
        this.y = i;
    }

    public final void b0(long j) {
        this.a.l().n();
        this.Q |= this.L != j;
        this.L = j;
    }

    public final void c(long j) {
        this.a.l().n();
        long j2 = this.g + j;
        if (j2 > 2147483647L) {
            this.a.j().L().b("Bundle index overflow. appId", V1.v(this.b));
            j2 = j - 1;
        }
        long j3 = this.G + 1;
        if (j3 > 2147483647L) {
            this.a.j().L().b("Delivery index overflow. appId", V1.v(this.b));
            j3 = 0;
        }
        this.Q = true;
        this.g = j2;
        this.G = j3;
    }

    public final void c0(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.P, str);
        this.P = str;
    }

    public final void d(Boolean bool) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.r, bool);
        this.r = bool;
    }

    public final long d0() {
        this.a.l().n();
        return this.M;
    }

    public final void e(Long l) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.A, l);
        this.A = l;
    }

    public final void e0(long j) {
        this.a.l().n();
        this.Q |= this.K != j;
        this.K = j;
    }

    public final void f(String str) {
        this.a.l().n();
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.Q |= !Objects.equals(this.q, str);
        this.q = str;
    }

    public final void f0(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.e, str);
        this.e = str;
    }

    public final void g(List<String> list) {
        this.a.l().n();
        if (Objects.equals(this.t, list)) {
            return;
        }
        this.Q = true;
        this.t = list != null ? new ArrayList(list) : null;
    }

    public final long g0() {
        this.a.l().n();
        return this.N;
    }

    public final void h(boolean z) {
        this.a.l().n();
        this.Q |= this.p != z;
        this.p = z;
    }

    public final void h0(long j) {
        this.a.l().n();
        this.Q |= this.O != j;
        this.O = j;
    }

    public final void i(byte[] bArr) {
        this.a.l().n();
        this.Q |= this.I != bArr;
        this.I = bArr;
    }

    public final void i0(String str) {
        this.a.l().n();
        this.Q |= this.H != str;
        this.H = str;
    }

    public final String j() {
        this.a.l().n();
        return this.q;
    }

    public final long j0() {
        this.a.l().n();
        return this.L;
    }

    public final String k() {
        this.a.l().n();
        String str = this.P;
        c0(null);
        return str;
    }

    public final void k0(long j) {
        this.a.l().n();
        this.Q |= this.J != j;
        this.J = j;
    }

    public final String l() {
        this.a.l().n();
        return this.b;
    }

    public final void l0(String str) {
        this.a.l().n();
        this.Q |= !Objects.equals(this.u, str);
        this.u = str;
    }

    public final String m() {
        this.a.l().n();
        return this.c;
    }

    public final long m0() {
        this.a.l().n();
        return this.K;
    }

    public final String n() {
        this.a.l().n();
        return this.l;
    }

    public final void n0(long j) {
        this.a.l().n();
        this.Q |= this.n != j;
        this.n = j;
    }

    public final String o() {
        this.a.l().n();
        return this.j;
    }

    public final void o0(String str) {
        this.a.l().n();
        this.Q |= this.D != str;
        this.D = str;
    }

    public final String p() {
        this.a.l().n();
        return this.f;
    }

    public final long p0() {
        this.a.l().n();
        return this.O;
    }

    public final String q() {
        this.a.l().n();
        return this.d;
    }

    public final void q0(long j) {
        this.a.l().n();
        this.Q |= this.s != j;
        this.s = j;
    }

    public final String r() {
        this.a.l().n();
        return this.P;
    }

    public final long r0() {
        this.a.l().n();
        return this.J;
    }

    public final String s() {
        this.a.l().n();
        return this.e;
    }

    public final void s0(long j) {
        this.a.l().n();
        this.Q |= this.S != j;
        this.S = j;
    }

    public final String t() {
        this.a.l().n();
        return this.H;
    }

    public final long t0() {
        this.a.l().n();
        return this.n;
    }

    public final String u() {
        this.a.l().n();
        return this.u;
    }

    public final void u0(long j) {
        this.a.l().n();
        this.Q |= this.m != j;
        this.m = j;
    }

    public final String v() {
        this.a.l().n();
        return this.D;
    }

    public final long v0() {
        this.a.l().n();
        return this.s;
    }

    public final List<String> w() {
        this.a.l().n();
        return this.t;
    }

    public final void w0(long j) {
        this.a.l().n();
        this.Q |= this.G != j;
        this.G = j;
    }

    public final void x() {
        this.a.l().n();
        this.Q = false;
    }

    public final long x0() {
        this.a.l().n();
        return this.S;
    }

    public final void y() {
        this.a.l().n();
        long j = this.g + 1;
        if (j > 2147483647L) {
            this.a.j().L().b("Bundle index overflow. appId", V1.v(this.b));
            j = 0;
        }
        this.Q = true;
        this.g = j;
    }

    public final void y0(long j) {
        this.a.l().n();
        this.Q |= this.i != j;
        this.i = j;
    }

    public final boolean z() {
        this.a.l().n();
        return this.p;
    }

    public final long z0() {
        this.a.l().n();
        return this.m;
    }
}
