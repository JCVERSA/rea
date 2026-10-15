package com.google.ads.mediation;

import ax.I5.AbstractC1104d;
import ax.L5.g;
import ax.L5.l;
import ax.L5.m;
import ax.L5.o;
import ax.W5.n;
import ax.p409z6.C2938Nh;

/* JADX INFO: loaded from: classes.dex */
final class e extends AbstractC1104d implements o, m, l {
    final n X;
    final AbstractAdViewAdapter q;

    public e(AbstractAdViewAdapter abstractAdViewAdapter, n nVar) {
        this.q = abstractAdViewAdapter;
        this.X = nVar;
    }

    @Override // ax.I5.AbstractC1104d
    public final void F0() {
        this.X.h(this.q);
    }

    @Override // ax.L5.m
    public final void a(C2938Nh c2938Nh) {
        this.X.o(this.q, c2938Nh);
    }

    @Override // ax.L5.l
    public final void b(C2938Nh c2938Nh, String str) {
        this.X.j(this.q, c2938Nh, str);
    }

    @Override // ax.L5.o
    public final void c(g gVar) {
        this.X.q(this.q, new a(gVar));
    }

    @Override // ax.I5.AbstractC1104d
    public final void e() {
        this.X.f(this.q);
    }

    @Override // ax.I5.AbstractC1104d
    public final void g(ax.I5.m mVar) {
        this.X.k(this.q, mVar);
    }

    @Override // ax.I5.AbstractC1104d
    public final void h() {
        this.X.r(this.q);
    }

    @Override // ax.I5.AbstractC1104d
    public final void k() {
    }

    @Override // ax.I5.AbstractC1104d
    public final void o() {
        this.X.b(this.q);
    }
}
