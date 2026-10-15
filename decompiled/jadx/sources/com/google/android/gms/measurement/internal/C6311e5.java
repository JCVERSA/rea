package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.e5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6311e5 {
    private final ax.p345v6.f a;
    private long b;

    public C6311e5(ax.p345v6.f fVar) {
        C2254p.l(fVar);
        this.a = fVar;
    }

    public final void a() {
        this.b = 0L;
    }

    public final boolean b(long j) {
        return this.b == 0 || this.a.b() - this.b >= 3600000;
    }

    public final void c() {
        this.b = this.a.b();
    }
}
