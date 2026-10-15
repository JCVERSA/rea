package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* JADX INFO: loaded from: classes3.dex */
final class O4 implements Runnable {
    private final /* synthetic */ J4 q;

    O4(J4 j4) {
        this.q = j4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6352k4.M(this.q.Y, new ComponentName(this.q.Y.a(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
