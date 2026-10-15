package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.v4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6420v4 implements Runnable {
    private final /* synthetic */ E5 X;
    private final /* synthetic */ C6352k4 Y;
    private final /* synthetic */ AtomicReference q;

    RunnableC6420v4(C6352k4 c6352k4, AtomicReference atomicReference, E5 e5) {
        this.q = atomicReference;
        this.X = e5;
        this.Y = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.q) {
            try {
                try {
                    if (!this.Y.h().M().B()) {
                        this.Y.j().M().a("Analytics storage consent denied; will not get app instance id");
                        this.Y.r().X0(null);
                        this.Y.h().i.b(null);
                        this.q.set(null);
                        this.q.notify();
                        return;
                    }
                    InterfaceC1351e interfaceC1351e = this.Y.d;
                    if (interfaceC1351e == null) {
                        this.Y.j().G().a("Failed to get app instance id");
                        this.q.notify();
                        return;
                    }
                    C2254p.l(this.X);
                    this.q.set(interfaceC1351e.X2(this.X));
                    String str = (String) this.q.get();
                    if (str != null) {
                        this.Y.r().X0(str);
                        this.Y.h().i.b(str);
                    }
                    this.Y.l0();
                    this.q.notify();
                } catch (RemoteException e) {
                    this.Y.j().G().b("Failed to get app instance id", e);
                    this.q.notify();
                }
            } catch (Throwable th) {
                this.q.notify();
                throw th;
            }
        }
    }
}
