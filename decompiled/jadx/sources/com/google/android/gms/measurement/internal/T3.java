package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class T3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ Boolean q;

    T3(C6351k3 c6351k3, Boolean bool) {
        this.q = bool;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.b0(this.q, true);
    }
}
