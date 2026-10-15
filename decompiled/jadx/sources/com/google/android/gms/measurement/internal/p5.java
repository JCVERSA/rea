package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class p5 implements Runnable {
    private final /* synthetic */ q5 X;
    private final /* synthetic */ y5 q;

    p5(q5 q5Var, y5 y5Var) {
        this.q = y5Var;
        this.X = q5Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        q5.y(this.X, this.q);
        this.X.y0();
    }
}
