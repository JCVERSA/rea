package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
final class L3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ ax.H6.M0 q;

    L3(C6351k3 c6351k3, ax.H6.M0 m0) {
        this.q = m0;
        this.X = c6351k3;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0057  */
    /* JADX WARN: Code duplicated, block: B:20:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    public final void run() {
        Long lValueOf;
        V4 v4U = this.X.u();
        if (v4U.h().M().B()) {
            if (!v4U.h().z(v4U.b().a()) && v4U.h().s.a() != 0) {
                lValueOf = Long.valueOf(v4U.h().s.a());
            }
            if (lValueOf != null) {
                this.X.a.L().Q(this.q, lValueOf.longValue());
            }
            try {
                this.q.l0(null);
            } catch (RemoteException e) {
                this.X.a.j().G().b("getSessionId failed with exception", e);
                return;
            }
        }
        v4U.j().M().a("Analytics storage consent denied; will not get session id");
        lValueOf = null;
        if (lValueOf != null) {
            this.X.a.L().Q(this.q, lValueOf.longValue());
        } else {
            this.q.l0(null);
        }
    }
}
