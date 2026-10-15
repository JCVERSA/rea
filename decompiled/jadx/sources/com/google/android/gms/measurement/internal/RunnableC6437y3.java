package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.y3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6437y3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ boolean q;

    RunnableC6437y3(C6351k3 c6351k3, boolean z) {
        this.q = z;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zP = this.X.a.p();
        boolean zO = this.X.a.o();
        this.X.a.m(this.q);
        if (zO == this.q) {
            this.X.a.j().K().b("Default data collection state already set to", Boolean.valueOf(this.q));
        }
        if (this.X.a.p() == zP || this.X.a.p() != this.X.a.o()) {
            this.X.a.j().M().c("Default data collection is different than actual status", Boolean.valueOf(this.q), Boolean.valueOf(zP));
        }
        this.X.H0();
    }
}
