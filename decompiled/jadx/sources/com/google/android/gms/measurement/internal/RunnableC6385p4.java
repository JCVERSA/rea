package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.p4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6385p4 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ E5 Y;
    private final /* synthetic */ boolean Z;
    private final /* synthetic */ ax.H6.M0 k0;
    private final /* synthetic */ C6352k4 l0;
    private final /* synthetic */ String q;

    RunnableC6385p4(C6352k4 c6352k4, String str, String str2, E5 e5, boolean z, ax.H6.M0 m0) {
        this.q = str;
        this.X = str2;
        this.Y = e5;
        this.Z = z;
        this.k0 = m0;
        this.l0 = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle bundle = new Bundle();
        try {
            try {
                InterfaceC1351e interfaceC1351e = this.l0.d;
                if (interfaceC1351e == null) {
                    this.l0.j().G().c("Failed to get user properties; not connected to service", this.q, this.X);
                    this.l0.i().R(this.k0, bundle);
                } else {
                    C2254p.l(this.Y);
                    Bundle bundleG = B5.G(interfaceC1351e.d6(this.q, this.X, this.Z, this.Y));
                    this.l0.l0();
                    this.l0.i().R(this.k0, bundleG);
                }
            } catch (RemoteException e) {
                this.l0.j().G().c("Failed to get user properties; remote exception", this.q, e);
                this.l0.i().R(this.k0, bundle);
            }
        } catch (Throwable th) {
            this.l0.i().R(this.k0, bundle);
            throw th;
        }
    }
}
