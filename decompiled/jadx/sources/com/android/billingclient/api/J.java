package com.android.billingclient.api;

import android.content.Context;
import ax.I6.R3;

/* JADX INFO: loaded from: classes.dex */
final class J {
    private boolean a;
    private ax.p312t5.h b;

    J(Context context) {
        try {
            ax.p344v5.u.f(context);
            this.b = ax.p344v5.u.c().g(com.google.android.datatransport.cct.a.g).a("PLAY_BILLING_LIBRARY", R3.class, ax.p312t5.b.b("proto"), new ax.p312t5.g() { // from class: ax.D3.n
                @Override // ax.p312t5.g
                public final Object apply(Object obj) {
                    return ((R3) obj).a();
                }
            });
        } catch (Throwable unused) {
            this.a = true;
        }
    }

    public final void a(R3 r3) {
        if (this.a) {
            ax.I6.Q.l("BillingLogger", "Skipping logging since initialization failed.");
            return;
        }
        try {
            this.b.b(ax.p312t5.c.f(r3));
        } catch (Throwable unused) {
            ax.I6.Q.l("BillingLogger", "logging failed.");
        }
    }
}
