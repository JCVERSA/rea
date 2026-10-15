package com.google.android.gms.measurement.internal;

import ax.M6.C1347a;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
final class W2 implements Callable<C1347a> {
    private final /* synthetic */ E5 a;
    private final /* synthetic */ I2 b;

    W2(I2 i2, E5 e5) {
        this.a = e5;
        this.b = i2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ C1347a call() throws Exception {
        this.b.q.u0();
        return new C1347a(this.b.q.e(this.a.q));
    }
}
