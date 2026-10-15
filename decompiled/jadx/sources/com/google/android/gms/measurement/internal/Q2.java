package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class Q2 implements Runnable {
    private final /* synthetic */ I2 X;
    private final /* synthetic */ C6312f q;

    Q2(I2 i2, C6312f c6312f) {
        this.q = c6312f;
        this.X = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.u0();
        if (this.q.Y.a() == null) {
            this.X.q.t(this.q);
        } else {
            this.X.q.U(this.q);
        }
    }
}
