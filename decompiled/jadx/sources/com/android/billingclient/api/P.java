package com.android.billingclient.api;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
final class P {
    private final Context a;
    private final ax.D3.e b;
    private final G c;
    private final O d = new O(this, true);
    private final O e = new O(this, false);
    private boolean f;

    P(Context context, ax.D3.e eVar, ax.D3.o oVar, ax.D3.i iVar, ax.D3.g gVar, G g) {
        this.a = context;
        this.b = eVar;
        this.c = g;
    }

    static /* bridge */ /* synthetic */ ax.D3.i a(P p) {
        p.getClass();
        return null;
    }

    static /* bridge */ /* synthetic */ ax.D3.g e(P p) {
        p.getClass();
        return null;
    }

    final ax.D3.e d() {
        return this.b;
    }

    final void f() {
        O o = this.d;
        Context context = this.a;
        o.c(context);
        this.e.c(context);
    }

    final void g(boolean z) {
        IntentFilter intentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        IntentFilter intentFilter2 = new IntentFilter("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED");
        intentFilter2.addAction("com.android.vending.billing.ALTERNATIVE_BILLING");
        this.f = z;
        O o = this.e;
        Context context = this.a;
        o.a(context, intentFilter2);
        if (this.f) {
            this.d.b(context, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST");
        } else {
            this.d.a(context, intentFilter);
        }
    }
}
