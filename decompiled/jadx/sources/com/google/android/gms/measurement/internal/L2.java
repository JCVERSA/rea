package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class L2 implements Runnable {
    private final /* synthetic */ I2 X;
    private final /* synthetic */ E5 q;

    L2(I2 i2, E5 e5) {
        this.q = e5;
        this.X = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.u0();
        this.X.q.c0(this.q);
    }
}
