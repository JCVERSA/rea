package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.n4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6373n4 implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService X;
    private final /* synthetic */ ax.H6.M0 q;

    RunnableC6373n4(AppMeasurementDynamiteService appMeasurementDynamiteService, ax.H6.M0 m0) {
        this.q = m0;
        this.X = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.L().U(this.q, this.X.q.o());
    }
}
