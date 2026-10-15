package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.b3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class CallableC6288b3 implements Callable<byte[]> {
    private final /* synthetic */ D a;
    private final /* synthetic */ String b;
    private final /* synthetic */ I2 c;

    CallableC6288b3(I2 i2, D d, String str) {
        this.a = d;
        this.b = str;
        this.c = i2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        this.c.q.u0();
        return this.c.q.p0().y(this.a, this.b);
    }
}
