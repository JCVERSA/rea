package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class A3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ long q;

    A3(C6351k3 c6351k3, long j) {
        this.q = j;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.h().m.b(this.q);
        this.X.j().F().b("Session timeout duration set", Long.valueOf(this.q));
    }
}
