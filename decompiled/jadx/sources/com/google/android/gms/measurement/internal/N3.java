package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
final class N3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ AtomicReference q;

    N3(C6351k3 c6351k3, AtomicReference atomicReference) {
        this.q = atomicReference;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.q) {
            try {
                try {
                    this.q.set(Long.valueOf(this.X.d().z(this.X.p().F(), F.N)));
                    this.q.notify();
                } catch (Throwable th) {
                    this.q.notify();
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
