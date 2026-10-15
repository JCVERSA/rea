package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.w2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6424w2 implements Runnable {
    private final /* synthetic */ D X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ AppMeasurementDynamiteService Z;
    private final /* synthetic */ ax.H6.M0 q;

    RunnableC6424w2(AppMeasurementDynamiteService appMeasurementDynamiteService, ax.H6.M0 m0, D d, String str) {
        this.q = m0;
        this.X = d;
        this.Y = str;
        this.Z = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Z.q.J().E(this.q, this.X, this.Y);
    }
}
