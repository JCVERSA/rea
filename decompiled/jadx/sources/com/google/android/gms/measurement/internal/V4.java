package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* JADX INFO: loaded from: classes3.dex */
public final class V4 extends AbstractC6286b1 {
    private Handler c;
    private boolean d;
    protected final C6297c5 e;
    protected final C6283a5 f;
    private final Z4 g;

    V4(E2 e2) {
        super(e2);
        this.d = true;
        this.e = new C6297c5(this);
        this.f = new C6283a5(this);
        this.g = new Z4(this);
    }

    static /* synthetic */ void C(V4 v4, long j) {
        v4.n();
        v4.G();
        v4.j().K().b("Activity paused, time", Long.valueOf(j));
        v4.g.b(j);
        if (v4.d().T()) {
            v4.f.e(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G() {
        n();
        if (this.c == null) {
            this.c = new ax.H6.E0(Looper.getMainLooper());
        }
    }

    static /* synthetic */ void I(V4 v4, long j) {
        v4.n();
        v4.G();
        v4.j().K().b("Activity resumed, time", Long.valueOf(j));
        if (v4.d().t(F.H0)) {
            if (v4.d().T() || v4.d) {
                v4.f.f(j);
            }
        } else if (v4.d().T() || v4.h().u.b()) {
            v4.f.f(j);
        }
        v4.g.a();
        C6297c5 c6297c5 = v4.e;
        c6297c5.a.n();
        if (c6297c5.a.a.p()) {
            c6297c5.b(c6297c5.a.b().a(), false);
        }
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return false;
    }

    final void D(boolean z) {
        n();
        this.d = z;
    }

    public final boolean E(boolean z, boolean z2, long j) {
        return this.f.d(z, z2, j);
    }

    final boolean F() {
        n();
        return this.d;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6439z o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ Q1 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ P1 q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6351k3 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6317f4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6352k4 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ V4 u() {
        return super.u();
    }
}
