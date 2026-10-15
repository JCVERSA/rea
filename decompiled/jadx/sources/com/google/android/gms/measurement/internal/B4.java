package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;

/* JADX INFO: loaded from: classes3.dex */
final class B4 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ ax.H6.M0 Y;
    private final /* synthetic */ C6352k4 Z;
    private final /* synthetic */ D q;

    B4(C6352k4 c6352k4, D d, String str, ax.H6.M0 m0) {
        this.q = d;
        this.X = str;
        this.Y = m0;
        this.Z = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            try {
                InterfaceC1351e interfaceC1351e = this.Z.d;
                if (interfaceC1351e == null) {
                    this.Z.j().G().a("Discarding data. Failed to send event to service to bundle");
                    this.Z.i().V(this.Y, null);
                } else {
                    byte[] bArrG5 = interfaceC1351e.g5(this.q, this.X);
                    this.Z.l0();
                    this.Z.i().V(this.Y, bArrG5);
                }
            } catch (RemoteException e) {
                this.Z.j().G().b("Failed to send event to the service to bundle", e);
                this.Z.i().V(this.Y, null);
            }
        } catch (Throwable th) {
            this.Z.i().V(this.Y, null);
            throw th;
        }
    }
}
