package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class Z2 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ I2 Y;
    private final /* synthetic */ D q;

    Z2(I2 i2, D d, E5 e5) {
        this.q = d;
        this.X = e5;
        this.Y = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Y.V7(this.Y.Y3(this.q, this.X), this.X);
    }
}
