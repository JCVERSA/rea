package com.google.android.gms.measurement.internal;

import ax.H6.C1013o6;

/* JADX INFO: loaded from: classes3.dex */
final class V3 implements Runnable {
    private final /* synthetic */ long X;
    private final /* synthetic */ long Y;
    private final /* synthetic */ boolean Z;
    private final /* synthetic */ C6330h3 k0;
    private final /* synthetic */ C6351k3 l0;
    private final /* synthetic */ C6330h3 q;

    V3(C6351k3 c6351k3, C6330h3 c6330h3, long j, long j2, boolean z, C6330h3 c6330h4) {
        this.q = c6330h3;
        this.X = j;
        this.Y = j2;
        this.Z = z;
        this.k0 = c6330h4;
        this.l0 = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l0.S(this.q);
        if (!C1013o6.a() || !this.l0.d().t(F.V0)) {
            this.l0.I(this.X, false);
        }
        C6351k3.W(this.l0, this.q, this.Y, true, this.Z);
        C6351k3.X(this.l0, this.q, this.k0);
    }
}
