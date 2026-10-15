package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.c3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class CallableC6295c3 implements Callable<List<C5>> {
    private final /* synthetic */ String a;
    private final /* synthetic */ I2 b;

    CallableC6295c3(I2 i2, String str) {
        this.a = str;
        this.b = i2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<C5> call() throws Exception {
        this.b.q.u0();
        return this.b.q.h0().S0(this.a);
    }
}
