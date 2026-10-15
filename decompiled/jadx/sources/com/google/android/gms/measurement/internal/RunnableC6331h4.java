package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6331h4 implements Runnable {
    private final /* synthetic */ C6303d4 X;
    private final /* synthetic */ long Y;
    private final /* synthetic */ boolean Z;
    private final /* synthetic */ C6317f4 k0;
    private final /* synthetic */ C6303d4 q;

    RunnableC6331h4(C6317f4 c6317f4, C6303d4 c6303d4, C6303d4 c6303d5, long j, boolean z) {
        this.q = c6303d4;
        this.X = c6303d5;
        this.Y = j;
        this.Z = z;
        this.k0 = c6317f4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k0.J(this.q, this.X, this.Y, this.Z, null);
    }
}
