package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
final class U2 implements Callable<List<C6312f>> {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ I2 d;

    U2(I2 i2, String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<C6312f> call() throws Exception {
        this.d.q.u0();
        return this.d.q.h0().R(this.a, this.b, this.c);
    }
}
