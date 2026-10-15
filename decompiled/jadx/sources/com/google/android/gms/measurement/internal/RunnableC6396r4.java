package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.r4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6396r4 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ Bundle Y;
    private final /* synthetic */ C6352k4 Z;
    private final /* synthetic */ AtomicReference q;

    RunnableC6396r4(C6352k4 c6352k4, AtomicReference atomicReference, E5 e5, Bundle bundle) {
        this.q = atomicReference;
        this.X = e5;
        this.Y = bundle;
        this.Z = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.q) {
            try {
                try {
                    InterfaceC1351e interfaceC1351e = this.Z.d;
                    if (interfaceC1351e == null) {
                        this.Z.j().G().a("Failed to get trigger URIs; not connected to service");
                        this.q.notify();
                    } else {
                        C2254p.l(this.X);
                        this.q.set(interfaceC1351e.K6(this.X, this.Y));
                        this.Z.l0();
                        this.q.notify();
                    }
                } catch (RemoteException e) {
                    this.Z.j().G().b("Failed to get trigger URIs; remote exception", e);
                    this.q.notify();
                }
            } catch (Throwable th) {
                this.q.notify();
                throw th;
            }
        }
    }
}
