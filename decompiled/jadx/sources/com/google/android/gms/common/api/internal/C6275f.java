package com.google.android.gms.common.api.internal;

import ax.Q6.AbstractC1477j;
import ax.Q6.C1478k;
import ax.Q6.InterfaceC1472e;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6275f implements InterfaceC1472e {
    final /* synthetic */ C1478k a;
    final /* synthetic */ C6276g b;

    C6275f(C6276g c6276g, C1478k c1478k) {
        this.b = c6276g;
        this.a = c1478k;
    }

    @Override // ax.Q6.InterfaceC1472e
    public final void a(AbstractC1477j abstractC1477j) {
        this.b.b.remove(this.a);
    }
}
