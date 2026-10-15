package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.w4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6426w4 implements Runnable {
    private final /* synthetic */ C6352k4 X;
    private final /* synthetic */ C6303d4 q;

    RunnableC6426w4(C6352k4 c6352k4, C6303d4 c6303d4) {
        this.q = c6303d4;
        this.X = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InterfaceC1351e interfaceC1351e = this.X.d;
        if (interfaceC1351e == null) {
            this.X.j().G().a("Failed to send current screen to service");
            return;
        }
        try {
            C6303d4 c6303d4 = this.q;
            if (c6303d4 == null) {
                interfaceC1351e.P3(0L, null, null, this.X.a().getPackageName());
            } else {
                interfaceC1351e.P3(c6303d4.c, c6303d4.a, c6303d4.b, this.X.a().getPackageName());
            }
            this.X.l0();
        } catch (RemoteException e) {
            this.X.j().G().b("Failed to send current screen to the service", e);
        }
    }
}
