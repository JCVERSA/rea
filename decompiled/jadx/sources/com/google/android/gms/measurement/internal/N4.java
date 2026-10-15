package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class N4 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ AppMeasurementDynamiteService Z;
    private final /* synthetic */ ax.H6.M0 q;

    N4(AppMeasurementDynamiteService appMeasurementDynamiteService, ax.H6.M0 m0, String str, String str2) {
        this.q = m0;
        this.X = str;
        this.Y = str2;
        this.Z = appMeasurementDynamiteService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.Z.q.J().F(this.q, this.X, this.Y);
    }
}
