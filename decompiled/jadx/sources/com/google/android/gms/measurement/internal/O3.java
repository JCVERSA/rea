package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class O3 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ boolean Z;
    private final /* synthetic */ AppMeasurementDynamiteService k0;
    private final /* synthetic */ ax.H6.M0 q;

    O3(AppMeasurementDynamiteService appMeasurementDynamiteService, ax.H6.M0 m0, String str, String str2, boolean z) {
        this.q = m0;
        this.X = str;
        this.Y = str2;
        this.Z = z;
        this.k0 = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.k0.q.J().G(this.q, this.X, this.Y, this.Z);
    }
}
