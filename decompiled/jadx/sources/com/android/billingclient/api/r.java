package com.android.billingclient.api;

import ax.I6.k4;
import j$.util.Objects;

/* JADX INFO: loaded from: classes.dex */
final class r implements ax.D3.b {
    final /* synthetic */ C6252b X;
    final /* synthetic */ k4 q;

    r(C6252b c6252b, k4 k4Var) {
        this.q = k4Var;
        Objects.requireNonNull(c6252b);
        this.X = c6252b;
    }

    @Override // ax.D3.b
    public final void o(final C6254d c6254d) {
        ax.I6.Q.k("BillingClient", "Reconnection finished with result: " + c6254d.c());
        try {
            this.q.b(c6254d);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Exception setting completer.", th);
        }
        C6252b c6252b = this.X;
        if (c6252b.G != null) {
            c6252b.K(new Runnable() { // from class: com.android.billingclient.api.q
                @Override // java.lang.Runnable
                public final void run() {
                    r rVar = this.q;
                    try {
                        rVar.X.G.o(c6254d);
                    } catch (Throwable th2) {
                        ax.I6.Q.m("BillingClient", "Exception calling onBillingSetupFinished.", th2);
                    }
                }
            });
        }
    }

    @Override // ax.D3.b
    public final void t() {
        ax.I6.Q.k("BillingClient", "Reconnection attempt failed.");
        try {
            this.q.b(H.j);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Exception setting completer.", th);
        }
        C6252b c6252b = this.X;
        if (c6252b.G != null) {
            c6252b.K(new Runnable() { // from class: com.android.billingclient.api.p
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        this.q.X.G.t();
                    } catch (Throwable th2) {
                        ax.I6.Q.m("BillingClient", "Exception calling onBillingServiceDisconnected.", th2);
                    }
                }
            });
        }
    }
}
