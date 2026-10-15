package com.google.android.gms.measurement.internal;

import ax.M6.InterfaceC1351e;

/* JADX INFO: loaded from: classes3.dex */
final class K4 implements Runnable {
    private final /* synthetic */ J4 X;
    private final /* synthetic */ InterfaceC1351e q;

    K4(J4 j4, InterfaceC1351e interfaceC1351e) {
        this.q = interfaceC1351e;
        this.X = j4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.X) {
            try {
                this.X.q = false;
                if (!this.X.Y.f0()) {
                    this.X.Y.j().F().a("Connected to remote service");
                    this.X.Y.H(this.q);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
