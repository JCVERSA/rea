package com.google.android.gms.common.api.internal;

import android.app.Activity;
import ax.p217n6.C2172c;
import ax.p217n6.C2176g;
import ax.p249p6.C2204b;
import ax.p249p6.InterfaceC2208f;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
public final class h extends E {
    private final ax.B.b l0;
    private final C6272c m0;

    h(InterfaceC2208f interfaceC2208f, C6272c c6272c, C2176g c2176g) {
        super(interfaceC2208f, c2176g);
        this.l0 = new ax.B.b();
        this.m0 = c6272c;
        this.q.v("ConnectionlessLifecycleHelper", this);
    }

    public static void u(Activity activity, C6272c c6272c, C2204b c2204b) {
        InterfaceC2208f interfaceC2208fC = LifecycleCallback.c(activity);
        h hVar = (h) interfaceC2208fC.F("ConnectionlessLifecycleHelper", h.class);
        if (hVar == null) {
            hVar = new h(interfaceC2208fC, c6272c, C2176g.o());
        }
        C2254p.m(c2204b, "ApiKey cannot be null");
        hVar.l0.add(c2204b);
        c6272c.b(hVar);
    }

    private final void v() {
        if (this.l0.isEmpty()) {
            return;
        }
        this.m0.b(this);
    }

    @Override // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void h() {
        super.h();
        v();
    }

    @Override // com.google.android.gms.common.api.internal.E, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void j() {
        super.j();
        v();
    }

    @Override // com.google.android.gms.common.api.internal.E, com.google.android.gms.common.api.internal.LifecycleCallback
    public final void k() {
        super.k();
        this.m0.c(this);
    }

    @Override // com.google.android.gms.common.api.internal.E
    protected final void m(C2172c c2172c, int i) {
        this.m0.D(c2172c, i);
    }

    @Override // com.google.android.gms.common.api.internal.E
    protected final void n() {
        this.m0.E();
    }

    final ax.B.b t() {
        return this.l0;
    }
}
