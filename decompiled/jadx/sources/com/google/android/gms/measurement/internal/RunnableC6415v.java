package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6415v implements Runnable {
    private final /* synthetic */ AbstractC6397s X;
    private final /* synthetic */ InterfaceC6323g3 q;

    RunnableC6415v(AbstractC6397s abstractC6397s, InterfaceC6323g3 interfaceC6323g3) {
        this.q = interfaceC6323g3;
        this.X = abstractC6397s;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.q.f();
        if (C6291c.a()) {
            this.q.l().D(this);
            return;
        }
        boolean zE = this.X.e();
        this.X.c = 0L;
        if (zE) {
            this.X.d();
        }
    }
}
