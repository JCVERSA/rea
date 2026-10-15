package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes3.dex */
final class l implements Runnable {
    final /* synthetic */ o X;
    final /* synthetic */ int q;

    l(o oVar, int i) {
        this.X = oVar;
        this.q = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.i(this.q);
    }
}
