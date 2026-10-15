package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
final class E4 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ String Z;
    private final /* synthetic */ E5 k0;
    private final /* synthetic */ C6352k4 l0;
    private final /* synthetic */ AtomicReference q;

    E4(C6352k4 c6352k4, AtomicReference atomicReference, String str, String str2, String str3, E5 e5) {
        this.q = atomicReference;
        this.X = str;
        this.Y = str2;
        this.Z = str3;
        this.k0 = e5;
        this.l0 = c6352k4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.q) {
            try {
                try {
                    InterfaceC1351e interfaceC1351e = this.l0.d;
                    if (interfaceC1351e == null) {
                        this.l0.j().G().d("(legacy) Failed to get conditional properties; not connected to service", V1.v(this.X), this.Y, this.Z);
                        this.q.set(Collections.EMPTY_LIST);
                        this.q.notify();
                    } else {
                        if (TextUtils.isEmpty(this.X)) {
                            C2254p.l(this.k0);
                            this.q.set(interfaceC1351e.i1(this.Y, this.Z, this.k0));
                        } else {
                            this.q.set(interfaceC1351e.T3(this.X, this.Y, this.Z));
                        }
                        this.l0.l0();
                        this.q.notify();
                    }
                } catch (RemoteException e) {
                    this.l0.j().G().d("(legacy) Failed to get conditional properties; remote exception", V1.v(this.X), this.Y, e);
                    this.q.set(Collections.EMPTY_LIST);
                    this.q.notify();
                }
            } catch (Throwable th) {
                this.q.notify();
                throw th;
            }
        }
    }
}
