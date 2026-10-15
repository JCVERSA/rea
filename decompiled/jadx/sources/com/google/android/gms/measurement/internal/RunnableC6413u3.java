package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.u3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6413u3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ AtomicReference q;

    RunnableC6413u3(C6351k3 c6351k3, AtomicReference atomicReference) {
        this.q = atomicReference;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.q) {
            try {
                try {
                    this.q.set(Boolean.valueOf(this.X.d().L(this.X.p().F())));
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
