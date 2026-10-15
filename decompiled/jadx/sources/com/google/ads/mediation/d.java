package com.google.ads.mediation;

import ax.I5.l;

/* JADX INFO: loaded from: classes.dex */
final class d extends l {
    final AbstractAdViewAdapter a;
    final ax.W5.l b;

    public d(AbstractAdViewAdapter abstractAdViewAdapter, ax.W5.l lVar) {
        this.a = abstractAdViewAdapter;
        this.b = lVar;
    }

    @Override // ax.I5.l
    public final void b() {
        this.b.p(this.a);
    }

    @Override // ax.I5.l
    public final void e() {
        this.b.s(this.a);
    }
}
