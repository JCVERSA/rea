package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.e4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6310e4 implements Runnable {
    private final /* synthetic */ C6303d4 X;
    private final /* synthetic */ C6303d4 Y;
    private final /* synthetic */ long Z;
    private final /* synthetic */ C6317f4 k0;
    private final /* synthetic */ Bundle q;

    RunnableC6310e4(C6317f4 c6317f4, Bundle bundle, C6303d4 c6303d4, C6303d4 c6303d5, long j) {
        this.q = bundle;
        this.X = c6303d4;
        this.Y = c6303d5;
        this.Z = j;
        this.k0 = c6317f4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6317f4.L(this.k0, this.q, this.X, this.Y, this.Z);
    }
}
