package com.android.billingclient.api;

import android.text.TextUtils;
import ax.I6.EnumC1171m3;
import j$.util.Objects;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.android.billingclient.api.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class CallableC6265o implements Callable {
    final /* synthetic */ ax.D3.d a;
    final /* synthetic */ String b;
    final /* synthetic */ C6252b c;

    CallableC6265o(C6252b c6252b, ax.D3.d dVar, String str, boolean z) {
        this.a = dVar;
        this.b = str;
        Objects.requireNonNull(c6252b);
        this.c = c6252b;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() throws Exception {
        C6252b c6252b = this.c;
        if (!c6252b.B(30000L)) {
            EnumC1171m3 enumC1171m3 = EnumC1171m3.SERVICE_CONNECTION_NOT_READY;
            C6254d c6254d = H.j;
            c6252b.b0(enumC1171m3, 9, c6254d);
            this.a.a(c6254d, ax.I6.J.s());
            return null;
        }
        String str = this.b;
        if (TextUtils.isEmpty(str)) {
            ax.I6.Q.l("BillingClient", "Please provide a valid product type.");
            EnumC1171m3 enumC1171m4 = EnumC1171m3.EMPTY_PRODUCT_TYPE;
            C6254d c6254d2 = H.e;
            c6252b.b0(enumC1171m4, 9, c6254d2);
            this.a.a(c6254d2, ax.I6.J.s());
            return null;
        }
        ax.D3.q qVarA0 = c6252b.a0(str, false, 9);
        if (qVarA0.b() != null) {
            this.a.a(qVarA0.a(), qVarA0.b());
            return null;
        }
        this.a.a(qVarA0.a(), ax.I6.J.s());
        return null;
    }
}
