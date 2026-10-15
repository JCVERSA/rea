package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.p3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6384p3 implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService X;
    private final /* synthetic */ AppMeasurementDynamiteService.b q;

    RunnableC6384p3(AppMeasurementDynamiteService appMeasurementDynamiteService, AppMeasurementDynamiteService.b bVar) {
        this.q = bVar;
        this.X = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.q.H().P(this.q);
    }
}
