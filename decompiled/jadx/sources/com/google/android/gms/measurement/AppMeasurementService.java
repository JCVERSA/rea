package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import ax.M6.A;
import com.google.android.gms.measurement.internal.S4;

/* JADX INFO: loaded from: classes3.dex */
public final class AppMeasurementService extends Service implements A {
    private S4<AppMeasurementService> q;

    private final S4<AppMeasurementService> c() {
        if (this.q == null) {
            this.q = new S4<>(this);
        }
        return this.q;
    }

    @Override // ax.M6.A
    public final void a(Intent intent) {
        ax.E0.a.b(intent);
    }

    @Override // ax.M6.A
    public final void b(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return c().b(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        c().c();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        c().h();
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onRebind(Intent intent) {
        c().i(intent);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i, int i2) {
        return c().a(intent, i, i2);
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return c().k(intent);
    }

    @Override // ax.M6.A
    public final boolean r(int i) {
        return stopSelfResult(i);
    }
}
