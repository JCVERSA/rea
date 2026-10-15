package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class Z implements Runnable {
    private final /* synthetic */ C6439z X;
    private final /* synthetic */ long q;

    Z(C6439z c6439z, long j) {
        this.q = j;
        this.X = c6439z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.B(this.q);
    }
}
