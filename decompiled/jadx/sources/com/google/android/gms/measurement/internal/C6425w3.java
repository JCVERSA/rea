package com.google.android.gms.measurement.internal;

import android.util.SparseArray;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.w3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6425w3 implements ax.p043c8.b<Object> {
    private final /* synthetic */ C6332h5 a;
    private final /* synthetic */ C6351k3 b;

    C6425w3(C6351k3 c6351k3, C6332h5 c6332h5) {
        this.a = c6332h5;
        this.b = c6351k3;
    }

    @Override // ax.p043c8.b
    public final void a(Object obj) {
        this.b.n();
        if (!this.b.d().t(F.G0)) {
            this.b.i = false;
            this.b.F0();
            this.b.j().F().b("registerTriggerAsync ran. uri", this.a.q);
            return;
        }
        SparseArray<Long> sparseArrayK = this.b.h().K();
        C6332h5 c6332h5 = this.a;
        sparseArrayK.put(c6332h5.Y, Long.valueOf(c6332h5.X));
        this.b.h().v(sparseArrayK);
        this.b.i = false;
        this.b.j = 1;
        this.b.j().F().b("Successfully registered trigger URI", this.a.q);
        this.b.F0();
    }

    @Override // ax.p043c8.b
    public final void b(Throwable th) {
        this.b.n();
        this.b.i = false;
        if (!this.b.d().t(F.G0)) {
            this.b.F0();
            this.b.j().G().b("registerTriggerAsync failed with throwable", th);
            return;
        }
        this.b.z0().add(this.a);
        if (this.b.j > 64) {
            this.b.j = 1;
            this.b.j().L().c("registerTriggerAsync failed. May try later. App ID, throwable", V1.v(this.b.p().F()), V1.v(th.toString()));
            return;
        }
        this.b.j().L().d("registerTriggerAsync failed. App ID, delay in seconds, throwable", V1.v(this.b.p().F()), V1.v(String.valueOf(this.b.j)), V1.v(th.toString()));
        C6351k3 c6351k3 = this.b;
        C6351k3.N0(c6351k3, c6351k3.j);
        this.b.j <<= 1;
    }
}
