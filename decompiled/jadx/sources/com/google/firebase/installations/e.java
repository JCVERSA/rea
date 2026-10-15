package com.google.firebase.installations;

import ax.Q6.C1478k;

/* JADX INFO: loaded from: classes3.dex */
class e implements h {
    private final i a;
    private final C1478k<g> b;

    public e(i iVar, C1478k<g> c1478k) {
        this.a = iVar;
        this.b = c1478k;
    }

    @Override // com.google.firebase.installations.h
    public boolean a(Exception exc) {
        this.b.d(exc);
        return true;
    }

    @Override // com.google.firebase.installations.h
    public boolean b(ax.K8.d dVar) {
        if (!dVar.k() || this.a.f(dVar)) {
            return false;
        }
        this.b.c(g.a().b(dVar.b()).d(dVar.c()).c(dVar.h()).a());
        return true;
    }
}
