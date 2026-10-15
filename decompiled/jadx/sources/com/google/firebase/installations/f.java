package com.google.firebase.installations;

import ax.Q6.C1478k;

/* JADX INFO: loaded from: classes3.dex */
class f implements h {
    final C1478k<String> a;

    public f(C1478k<String> c1478k) {
        this.a = c1478k;
    }

    @Override // com.google.firebase.installations.h
    public boolean a(Exception exc) {
        return false;
    }

    @Override // com.google.firebase.installations.h
    public boolean b(ax.K8.d dVar) {
        if (!dVar.l() && !dVar.k() && !dVar.i()) {
            return false;
        }
        this.a.e(dVar.d());
        return true;
    }
}
