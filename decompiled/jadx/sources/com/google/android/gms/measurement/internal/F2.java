package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class F2 implements Runnable {
    private final /* synthetic */ E2 X;
    private final /* synthetic */ C6344j3 q;

    F2(E2 e2, C6344j3 c6344j3) {
        this.q = c6344j3;
        this.X = e2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        E2.g(this.X, this.q);
        this.X.d(this.q.g);
    }
}
