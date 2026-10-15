package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.a3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6281a3 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ I2 Y;
    private final /* synthetic */ A5 q;

    RunnableC6281a3(I2 i2, A5 a5, E5 e5) {
        this.q = a5;
        this.X = e5;
        this.Y = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Y.q.u0();
        if (this.q.a() == null) {
            this.Y.q.G(this.q.X, this.X);
        } else {
            this.Y.q.z(this.q, this.X);
        }
    }
}
