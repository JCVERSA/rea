package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class E3 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ Object Y;
    private final /* synthetic */ long Z;
    private final /* synthetic */ C6351k3 k0;
    private final /* synthetic */ String q;

    E3(C6351k3 c6351k3, String str, String str2, Object obj, long j) {
        this.q = str;
        this.X = str2;
        this.Y = obj;
        this.Z = j;
        this.k0 = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k0.j0(this.q, this.X, this.Y, this.Z);
    }
}
