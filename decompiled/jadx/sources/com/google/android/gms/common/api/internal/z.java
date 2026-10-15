package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import ax.Q6.C1478k;
import ax.p217n6.C2174e;
import ax.p249p6.C2209g;

/* JADX INFO: loaded from: classes3.dex */
public final class z extends v {
    public final C2209g c;

    public z(C2209g c2209g, C1478k c1478k) {
        super(4, c1478k);
        this.c = c2209g;
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final /* bridge */ /* synthetic */ void d(C6276g c6276g, boolean z) {
    }

    @Override // ax.p249p6.u
    public final boolean f(o oVar) {
        if (((ax.p249p6.y) oVar.v().get(this.c)) == null) {
            return false;
        }
        throw null;
    }

    @Override // ax.p249p6.u
    public final C2174e[] g(o oVar) {
        if (((ax.p249p6.y) oVar.v().get(this.c)) == null) {
            return null;
        }
        throw null;
    }

    @Override // com.google.android.gms.common.api.internal.v
    public final void h(o oVar) throws RemoteException {
        if (((ax.p249p6.y) oVar.v().remove(this.c)) == null) {
            this.b.e(Boolean.FALSE);
        } else {
            oVar.t();
            throw null;
        }
    }
}
