package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class N2 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ I2 Y;
    private final /* synthetic */ C6312f q;

    N2(I2 i2, C6312f c6312f, E5 e5) {
        this.q = c6312f;
        this.X = e5;
        this.Y = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Y.q.u0();
        if (this.q.Y.a() == null) {
            this.Y.q.u(this.q, this.X);
        } else {
            this.Y.q.V(this.q, this.X);
        }
    }
}
