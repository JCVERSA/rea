package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
final class I3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ long q;

    I3(C6351k3 c6351k3, long j) {
        this.q = j;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.J0(this.q);
        this.X.t().Q(new AtomicReference<>());
    }
}
