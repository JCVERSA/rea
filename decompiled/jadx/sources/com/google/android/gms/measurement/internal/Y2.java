package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class Y2 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ I2 Y;
    private final /* synthetic */ D q;

    Y2(I2 i2, D d, String str) {
        this.q = d;
        this.X = str;
        this.Y = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Y.q.u0();
        this.Y.q.w(this.q, this.X);
    }
}
