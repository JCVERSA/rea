package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import ax.M6.A;
import com.google.android.gms.measurement.internal.S4;

/* JADX INFO: loaded from: classes3.dex */
@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements A {
    private S4<AppMeasurementJobService> q;

    private final S4<AppMeasurementJobService> c() {
        if (this.q == null) {
            this.q = new S4<>(this);
        }
        return this.q;
    }

    @Override // ax.M6.A
    public final void a(Intent intent) {
    }

    @Override // ax.M6.A
    @TargetApi(24)
    public final void b(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
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

    @Override // android.app.job.JobService
    public final boolean onStartJob(JobParameters jobParameters) {
        return c().g(jobParameters);
    }

    @Override // android.app.job.JobService
    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @Override // android.app.Service
    public final boolean onUnbind(Intent intent) {
        return c().k(intent);
    }

    @Override // ax.M6.A
    public final boolean r(int i) {
        throw new UnsupportedOperationException();
    }
}
