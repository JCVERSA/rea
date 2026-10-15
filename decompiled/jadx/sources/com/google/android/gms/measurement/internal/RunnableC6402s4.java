package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.s4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6402s4 implements Runnable {
    private final /* synthetic */ C6352k4 X;
    private final /* synthetic */ E5 q;

    RunnableC6402s4(C6352k4 c6352k4, E5 e5) {
        this.q = e5;
        this.X = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC1351e interfaceC1351e = this.X.d;
        if (interfaceC1351e == null) {
            this.X.j().G().a("Failed to reset data on the service: not connected to service");
            return;
        }
        try {
            C2254p.l(this.q);
            interfaceC1351e.u1(this.q);
        } catch (RemoteException e) {
            this.X.j().G().b("Failed to reset data on the service: remote exception", e);
        }
        this.X.l0();
    }
}
