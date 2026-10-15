package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class A4 implements Runnable {
    private final /* synthetic */ C6352k4 X;
    private final /* synthetic */ E5 q;

    A4(C6352k4 c6352k4, E5 e5) {
        this.q = e5;
        this.X = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC1351e interfaceC1351e = this.X.d;
        if (interfaceC1351e == null) {
            this.X.j().G().a("Failed to send measurementEnabled to service");
            return;
        }
        try {
            C2254p.l(this.q);
            interfaceC1351e.I6(this.q);
            this.X.l0();
        } catch (RemoteException e) {
            this.X.j().G().b("Failed to send measurementEnabled to the service", e);
        }
    }
}
