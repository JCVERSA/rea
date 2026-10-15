package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
final class u5 implements Callable<String> {
    private final /* synthetic */ E5 a;
    private final /* synthetic */ q5 b;

    u5(q5 q5Var, E5 e5) {
        this.a = e5;
        this.b = q5Var;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        if (!this.b.S((String) C2254p.l(this.a.q)).B() || !C6330h3.q(this.a.B0).B()) {
            this.b.j().K().a("Analytics storage consent denied. Returning null app instance id");
            return null;
        }
        Z1 z1H = this.b.h(this.a);
        if (z1H != null) {
            return z1H.m();
        }
        this.b.j().L().a("App info was null when attempting to get app instance id");
        return null;
    }
}
