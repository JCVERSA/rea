package com.google.ads.mediation;

import ax.I5.AbstractC1104d;
import ax.I5.m;
import ax.Q5.InterfaceC1391a;
import ax.W5.i;

/* JADX INFO: loaded from: classes.dex */
final class b extends AbstractC1104d implements ax.J5.c, InterfaceC1391a {
    final i X;
    final AbstractAdViewAdapter q;

    public b(AbstractAdViewAdapter abstractAdViewAdapter, i iVar) {
        this.q = abstractAdViewAdapter;
        this.X = iVar;
    }

    @Override // ax.I5.AbstractC1104d
    public final void F0() {
        this.X.d(this.q);
    }

    @Override // ax.I5.AbstractC1104d
    public final void e() {
        this.X.a(this.q);
    }

    @Override // ax.I5.AbstractC1104d
    public final void g(m mVar) {
        this.X.l(this.q, mVar);
    }

    @Override // ax.I5.AbstractC1104d
    public final void k() {
        this.X.g(this.q);
    }

    @Override // ax.I5.AbstractC1104d
    public final void o() {
        this.X.n(this.q);
    }

    @Override // ax.J5.c
    public final void p(String str, String str2) {
        this.X.e(this.q, str, str2);
    }
}
