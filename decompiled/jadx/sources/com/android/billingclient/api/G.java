package com.android.billingclient.api;

import ax.I6.C1131e3;
import ax.I6.C1156j3;
import ax.I6.C1195r3;
import ax.I6.EnumC1205t3;
import ax.I6.X3;
import ax.I6.b4;

/* JADX INFO: loaded from: classes.dex */
interface G {
    public static final /* synthetic */ int a = 0;

    static {
        ax.I6.M.c("com.android.vending.billing.PURCHASES_UPDATED", EnumC1205t3.PURCHASES_UPDATED_ACTION, "com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED", EnumC1205t3.LOCAL_PURCHASES_UPDATED_ACTION, "com.android.vending.billing.ALTERNATIVE_BILLING", EnumC1205t3.ALTERNATIVE_BILLING_ACTION);
    }

    void a(C1156j3 c1156j3, long j, boolean z);

    void b(C1156j3 c1156j3, int i);

    void c(X3 x3);

    void d(C1131e3 c1131e3, long j, boolean z);

    void e(C1156j3 c1156j3);

    void f(C1131e3 c1131e3, int i);

    void g(C1131e3 c1131e3, int i, long j, boolean z);

    void h(C1131e3 c1131e3, int i, long j);

    void i(C1131e3 c1131e3);

    void j(C1195r3 c1195r3);

    void k(b4 b4Var);
}
