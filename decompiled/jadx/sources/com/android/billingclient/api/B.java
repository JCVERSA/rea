package com.android.billingclient.api;

import ax.I6.EnumC1171m3;
import ax.I6.InterfaceC1207u0;
import j$.util.Objects;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
final class B implements InterfaceC1207u0 {
    final /* synthetic */ ax.p019b0.a a;
    final /* synthetic */ Runnable b;
    final /* synthetic */ E c;
    final /* synthetic */ int d;

    B(E e, int i, ax.p019b0.a aVar, Runnable runnable) {
        this.d = i;
        this.a = aVar;
        this.b = runnable;
        Objects.requireNonNull(e);
        this.c = e;
    }

    @Override // ax.I6.InterfaceC1207u0
    public final void a(Throwable th) {
        if (th instanceof TimeoutException) {
            this.c.F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CALL_TIMEOUT, 28, H.F);
            ax.I6.Q.m("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", th);
        } else {
            this.c.F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, H.F);
            ax.I6.Q.m("BillingClientTesting", "An error occurred while retrieving billing override.", th);
        }
        this.b.run();
    }

    @Override // ax.I6.InterfaceC1207u0
    public final /* bridge */ /* synthetic */ void c(Object obj) {
        Integer num = (Integer) obj;
        int iIntValue = num.intValue();
        E e = this.c;
        if (!E.C0(iIntValue)) {
            this.b.run();
        } else {
            this.a.accept(e.D0(this.d, num.intValue()));
        }
    }
}
