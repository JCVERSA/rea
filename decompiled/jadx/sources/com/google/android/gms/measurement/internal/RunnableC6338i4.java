package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.i4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6338i4 implements Runnable {
    private final /* synthetic */ long X;
    private final /* synthetic */ C6317f4 Y;
    private final /* synthetic */ C6303d4 q;

    RunnableC6338i4(C6317f4 c6317f4, C6303d4 c6303d4, long j) {
        this.q = c6303d4;
        this.X = j;
        this.Y = c6317f4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Y.K(this.q, false, this.X);
        C6317f4 c6317f4 = this.Y;
        c6317f4.e = null;
        c6317f4.t().L(null);
    }
}
