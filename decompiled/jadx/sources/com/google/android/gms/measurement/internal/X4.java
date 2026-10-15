package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class X4 implements Runnable {
    private final /* synthetic */ V4 X;
    private final /* synthetic */ long q;

    X4(V4 v4, long j) {
        this.q = j;
        this.X = v4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        V4.I(this.X, this.q);
    }
}
