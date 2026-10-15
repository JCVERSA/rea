package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class w5 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ Bundle Y;
    private final /* synthetic */ t5 Z;
    private final /* synthetic */ String q;

    w5(t5 t5Var, String str, String str2, Bundle bundle) {
        this.q = str;
        this.X = str2;
        this.Y = bundle;
        this.Z = t5Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Z.a.w((D) C2254p.l(this.Z.a.t0().H(this.q, this.X, this.Y, "auto", this.Z.a.b().a(), false, true)), this.q);
    }
}
