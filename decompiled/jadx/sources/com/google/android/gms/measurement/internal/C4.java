package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class C4 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ boolean Y;
    private final /* synthetic */ D Z;
    private final /* synthetic */ String k0;
    private final /* synthetic */ C6352k4 l0;
    private final /* synthetic */ boolean q;

    C4(C6352k4 c6352k4, boolean z, E5 e5, boolean z2, D d, String str) {
        this.q = z;
        this.X = e5;
        this.Y = z2;
        this.Z = d;
        this.k0 = str;
        this.l0 = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        InterfaceC1351e interfaceC1351e = this.l0.d;
        if (interfaceC1351e == null) {
            this.l0.j().G().a("Discarding data. Failed to send event to service");
            return;
        }
        if (this.q) {
            C2254p.l(this.X);
            this.l0.I(interfaceC1351e, this.Y ? null : this.Z, this.X);
        } else {
            try {
                if (TextUtils.isEmpty(this.k0)) {
                    C2254p.l(this.X);
                    interfaceC1351e.g6(this.Z, this.X);
                } else {
                    interfaceC1351e.F1(this.Z, this.k0, this.l0.j().O());
                }
            } catch (RemoteException e) {
                this.l0.j().G().b("Failed to send event to the service", e);
            }
        }
        this.l0.l0();
    }
}
