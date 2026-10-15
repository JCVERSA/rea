package com.google.ads.mediation;

import ax.I5.m;
import ax.W5.l;

/* JADX INFO: loaded from: classes.dex */
final class c extends ax.V5.b {
    final AbstractAdViewAdapter a;
    final l b;

    public c(AbstractAdViewAdapter abstractAdViewAdapter, l lVar) {
        this.a = abstractAdViewAdapter;
        this.b = lVar;
    }

    @Override // ax.I5.AbstractC1105e
    public final void a(m mVar) {
        this.b.i(this.a, mVar);
    }

    @Override // ax.I5.AbstractC1105e
    public final /* bridge */ /* synthetic */ void b(ax.V5.a aVar) {
        AbstractAdViewAdapter abstractAdViewAdapter = this.a;
        ax.V5.a aVar2 = aVar;
        abstractAdViewAdapter.mInterstitialAd = aVar2;
        aVar2.c(new d(abstractAdViewAdapter, this.b));
        this.b.m(this.a);
    }
}
