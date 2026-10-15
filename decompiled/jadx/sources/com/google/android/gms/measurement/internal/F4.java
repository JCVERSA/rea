package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class F4 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ boolean Y;
    private final /* synthetic */ C6312f Z;
    private final /* synthetic */ C6312f k0;
    private final /* synthetic */ C6352k4 l0;
    private final /* synthetic */ boolean q = true;

    F4(C6352k4 c6352k4, boolean z, E5 e5, boolean z2, C6312f c6312f, C6312f c6312f2) {
        this.X = e5;
        this.Y = z2;
        this.Z = c6312f;
        this.k0 = c6312f2;
        this.l0 = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        InterfaceC1351e interfaceC1351e = this.l0.d;
        if (interfaceC1351e == null) {
            this.l0.j().G().a("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.q) {
            C2254p.l(this.X);
            this.l0.I(interfaceC1351e, this.Y ? null : this.Z, this.X);
        } else {
            try {
                if (TextUtils.isEmpty(this.k0.q)) {
                    C2254p.l(this.X);
                    interfaceC1351e.q3(this.Z, this.X);
                } else {
                    interfaceC1351e.g4(this.Z);
                }
            } catch (RemoteException e) {
                this.l0.j().G().b("Failed to send conditional user property to the service", e);
            }
        }
        this.l0.l0();
    }
}
