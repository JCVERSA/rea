package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class U3 implements Runnable {
    private final /* synthetic */ long X;
    private final /* synthetic */ boolean Y;
    private final /* synthetic */ C6330h3 Z;
    private final /* synthetic */ C6351k3 k0;
    private final /* synthetic */ C6330h3 q;

    U3(C6351k3 c6351k3, C6330h3 c6330h3, long j, boolean z, C6330h3 c6330h4) {
        this.q = c6330h3;
        this.X = j;
        this.Y = z;
        this.Z = c6330h4;
        this.k0 = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k0.S(this.q);
        C6351k3.W(this.k0, this.q, this.X, false, this.Y);
        C6351k3.X(this.k0, this.q, this.Z);
    }
}
