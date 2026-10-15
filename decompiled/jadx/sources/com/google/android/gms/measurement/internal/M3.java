package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
final class M3 implements Runnable {
    private final /* synthetic */ String X = null;
    private final /* synthetic */ String Y;
    private final /* synthetic */ String Z;
    private final /* synthetic */ boolean k0;
    private final /* synthetic */ C6351k3 l0;
    private final /* synthetic */ AtomicReference q;

    M3(C6351k3 c6351k3, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.q = atomicReference;
        this.Y = str2;
        this.Z = str3;
        this.k0 = z;
        this.l0 = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.l0.a.J().T(this.q, null, this.Y, this.Z, this.k0);
    }
}
