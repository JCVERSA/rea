package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.z4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6444z4 implements Runnable {
    private final /* synthetic */ Bundle X;
    private final /* synthetic */ C6352k4 Y;
    private final /* synthetic */ E5 q;

    RunnableC6444z4(C6352k4 c6352k4, E5 e5, Bundle bundle) {
        this.q = e5;
        this.X = bundle;
        this.Y = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC1351e interfaceC1351e = this.Y.d;
        if (interfaceC1351e == null) {
            this.Y.j().G().a("Failed to send default event parameters to service");
            return;
        }
        try {
            C2254p.l(this.q);
            interfaceC1351e.o2(this.X, this.q);
        } catch (RemoteException e) {
            this.Y.j().G().b("Failed to send default event parameters to service", e);
        }
    }
}
