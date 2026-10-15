package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class A0 implements Runnable {
    private final /* synthetic */ long X;
    private final /* synthetic */ C6439z Y;
    private final /* synthetic */ String q;

    A0(C6439z c6439z, String str, long j) {
        this.q = str;
        this.X = j;
        this.Y = c6439z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6439z.C(this.Y, this.q, this.X);
    }
}
