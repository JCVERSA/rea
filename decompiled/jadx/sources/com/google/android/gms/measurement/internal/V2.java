package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class V2 implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService X;
    private final /* synthetic */ ax.H6.M0 q;

    V2(AppMeasurementDynamiteService appMeasurementDynamiteService, ax.H6.M0 m0) {
        this.q = m0;
        this.X = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.J().D(this.q);
    }
}
