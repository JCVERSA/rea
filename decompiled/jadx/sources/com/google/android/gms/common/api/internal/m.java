package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes3.dex */
final class m implements Runnable {
    final /* synthetic */ n q;

    m(n nVar) {
        this.q = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        o oVar = this.q.a;
        oVar.X.e(oVar.X.getClass().getName().concat(" disconnecting because it was signed out."));
    }
}
