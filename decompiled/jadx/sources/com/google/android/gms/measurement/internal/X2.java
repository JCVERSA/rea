package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class X2 implements Runnable {
    private final /* synthetic */ I2 X;
    private final /* synthetic */ E5 q;

    X2(I2 i2, E5 e5) {
        this.q = e5;
        this.X = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.u0();
        q5 q5Var = this.X.q;
        E5 e5 = this.q;
        q5Var.l().n();
        q5Var.v0();
        C2254p.f(e5.q);
        q5Var.i0(e5);
        q5Var.g0(e5);
    }
}
