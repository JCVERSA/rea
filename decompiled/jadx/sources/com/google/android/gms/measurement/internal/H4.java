package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
final class H4 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ E5 Y;
    private final /* synthetic */ ax.H6.M0 Z;
    private final /* synthetic */ C6352k4 k0;
    private final /* synthetic */ String q;

    H4(C6352k4 c6352k4, String str, String str2, E5 e5, ax.H6.M0 m0) {
        this.q = str;
        this.X = str2;
        this.Y = e5;
        this.Z = m0;
        this.k0 = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList<Bundle> arrayList = new ArrayList<>();
        try {
            try {
                InterfaceC1351e interfaceC1351e = this.k0.d;
                if (interfaceC1351e == null) {
                    this.k0.j().G().c("Failed to get conditional properties; not connected to service", this.q, this.X);
                    this.k0.i().T(this.Z, arrayList);
                } else {
                    C2254p.l(this.Y);
                    ArrayList<Bundle> arrayListT0 = B5.t0(interfaceC1351e.i1(this.q, this.X, this.Y));
                    this.k0.l0();
                    this.k0.i().T(this.Z, arrayListT0);
                }
            } catch (RemoteException e) {
                this.k0.j().G().d("Failed to get conditional properties; remote exception", this.q, this.X, e);
                this.k0.i().T(this.Z, arrayList);
            }
        } catch (Throwable th) {
            this.k0.i().T(this.Z, arrayList);
            throw th;
        }
    }
}
