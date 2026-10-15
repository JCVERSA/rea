package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class T4 implements Runnable {
    private final /* synthetic */ Runnable X;
    private final /* synthetic */ q5 q;

    T4(S4 s4, q5 q5Var, Runnable runnable) {
        this.q = q5Var;
        this.X = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.q.u0();
        this.q.A(this.X);
        this.q.z0();
    }
}
