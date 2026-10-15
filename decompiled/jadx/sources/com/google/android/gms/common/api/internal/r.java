package com.google.android.gms.common.api.internal;

import android.util.Log;
import ax.p217n6.C2172c;
import ax.p249p6.C2204b;
import ax.p265q6.AbstractC2241c;
import ax.p265q6.InterfaceC2248j;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
final class r implements AbstractC2241c.InterfaceC0431c, ax.p249p6.B {
    private final ax.o6.a.f a;
    private final C2204b b;
    private InterfaceC2248j c = null;
    private Set d = null;
    private boolean e = false;
    final /* synthetic */ C6272c f;

    public r(C6272c c6272c, ax.o6.a.f fVar, C2204b c2204b) {
        this.f = c6272c;
        this.a = fVar;
        this.b = c2204b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i() {
        InterfaceC2248j interfaceC2248j;
        if (!this.e || (interfaceC2248j = this.c) == null) {
            return;
        }
        this.a.h(interfaceC2248j, this.d);
    }

    @Override // ax.p265q6.AbstractC2241c.InterfaceC0431c
    public final void a(C2172c c2172c) {
        this.f.t0.post(new q(this, c2172c));
    }

    @Override // ax.p249p6.B
    public final void b(C2172c c2172c) {
        o oVar = (o) this.f.p0.get(this.b);
        if (oVar != null) {
            oVar.G(c2172c);
        }
    }

    @Override // ax.p249p6.B
    public final void c(InterfaceC2248j interfaceC2248j, Set set) {
        if (interfaceC2248j == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            b(new C2172c(4));
        } else {
            this.c = interfaceC2248j;
            this.d = set;
            i();
        }
    }

    @Override // ax.p249p6.B
    public final void d(int i) {
        o oVar = (o) this.f.p0.get(this.b);
        if (oVar != null) {
            if (oVar.o0) {
                oVar.G(new C2172c(17));
            } else {
                oVar.M0(i);
            }
        }
    }
}
