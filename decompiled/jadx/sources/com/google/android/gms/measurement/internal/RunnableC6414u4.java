package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.u4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6414u4 implements Runnable {
    private final /* synthetic */ ax.H6.M0 X;
    private final /* synthetic */ C6352k4 Y;
    private final /* synthetic */ E5 q;

    RunnableC6414u4(C6352k4 c6352k4, E5 e5, ax.H6.M0 m0) {
        this.q = e5;
        this.X = m0;
        this.Y = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            try {
                if (!this.Y.h().M().B()) {
                    this.Y.j().M().a("Analytics storage consent denied; will not get app instance id");
                    this.Y.r().X0(null);
                    this.Y.h().i.b(null);
                    this.Y.i().S(this.X, null);
                    return;
                }
                InterfaceC1351e interfaceC1351e = this.Y.d;
                if (interfaceC1351e == null) {
                    this.Y.j().G().a("Failed to get app instance id");
                    this.Y.i().S(this.X, null);
                    return;
                }
                C2254p.l(this.q);
                String strX2 = interfaceC1351e.X2(this.q);
                if (strX2 != null) {
                    this.Y.r().X0(strX2);
                    this.Y.h().i.b(strX2);
                }
                this.Y.l0();
                this.Y.i().S(this.X, strX2);
            } catch (RemoteException e) {
                this.Y.j().G().b("Failed to get app instance id", e);
                this.Y.i().S(this.X, null);
            }
        } catch (Throwable th) {
            this.Y.i().S(this.X, null);
            throw th;
        }
    }
}
