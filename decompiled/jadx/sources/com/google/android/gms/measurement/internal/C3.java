package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
final class C3 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ long Y;
    private final /* synthetic */ Bundle Z;
    private final /* synthetic */ boolean k0;
    private final /* synthetic */ boolean l0;
    private final /* synthetic */ boolean m0;
    private final /* synthetic */ String n0;
    private final /* synthetic */ C6351k3 o0;
    private final /* synthetic */ String q;

    C3(C6351k3 c6351k3, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.q = str;
        this.X = str2;
        this.Y = j;
        this.Z = bundle;
        this.k0 = z;
        this.l0 = z2;
        this.m0 = z3;
        this.n0 = str3;
        this.o0 = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.o0.e0(this.q, this.X, this.Y, this.Z, this.k0, this.l0, this.m0, this.n0);
    }
}
