package com.google.android.gms.common.api.internal;

import android.util.Log;
import ax.p217n6.C2172c;

/* JADX INFO: loaded from: classes3.dex */
final class q implements Runnable {
    final /* synthetic */ r X;
    final /* synthetic */ C2172c q;

    q(r rVar, C2172c c2172c) {
        this.X = rVar;
        this.q = c2172c;
    }

    @Override // java.lang.Runnable
    public final void run() {
        r rVar = this.X;
        o oVar = (o) rVar.f.p0.get(rVar.b);
        if (oVar == null) {
            return;
        }
        if (!this.q.E()) {
            oVar.F(this.q, null);
            return;
        }
        this.X.e = true;
        if (this.X.a.m()) {
            this.X.i();
            return;
        }
        try {
            r rVar2 = this.X;
            rVar2.a.h(null, rVar2.a.c());
        } catch (SecurityException e) {
            Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
            this.X.a.e("Failed to get service from broker.");
            oVar.F(new C2172c(10), null);
        }
    }
}
