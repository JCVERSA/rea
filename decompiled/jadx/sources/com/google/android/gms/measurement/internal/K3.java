package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
final class K3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ Bundle q;

    K3(C6351k3 c6351k3, Bundle bundle) {
        this.q = bundle;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6351k3.V(this.X, this.q);
    }
}
