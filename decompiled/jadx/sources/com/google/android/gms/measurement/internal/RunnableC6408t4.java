package com.google.android.gms.measurement.internal;

import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.t4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6408t4 implements Runnable {
    private final /* synthetic */ boolean X;
    private final /* synthetic */ A5 Y;
    private final /* synthetic */ C6352k4 Z;
    private final /* synthetic */ E5 q;

    RunnableC6408t4(C6352k4 c6352k4, E5 e5, boolean z, A5 a5) {
        this.q = e5;
        this.X = z;
        this.Y = a5;
        this.Z = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        InterfaceC1351e interfaceC1351e = this.Z.d;
        if (interfaceC1351e == null) {
            this.Z.j().G().a("Discarding data. Failed to set user property");
            return;
        }
        C2254p.l(this.q);
        this.Z.I(interfaceC1351e, this.X ? null : this.Y, this.q);
        this.Z.l0();
    }
}
