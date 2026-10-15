package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import ax.M6.A;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
public final class S4<T extends Context & ax.M6.A> {
    private final T a;

    public S4(T t) {
        C2254p.l(t);
        this.a = t;
    }

    private final void f(Runnable runnable) {
        q5 q5VarM = q5.m(this.a);
        q5VarM.l().D(new T4(this, q5VarM, runnable));
    }

    private final V1 j() {
        return E2.c(this.a, null, null).j();
    }

    public final int a(final Intent intent, int i, final int i2) {
        final V1 v1J = E2.c(this.a, null, null).j();
        if (intent == null) {
            v1J.L().a("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        v1J.K().c("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            f(new Runnable() { // from class: com.google.android.gms.measurement.internal.U4
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.d(i2, v1J, intent);
                }
            });
        }
        return 2;
    }

    public final IBinder b(Intent intent) {
        if (intent == null) {
            j().G().a("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new I2(q5.m(this.a));
        }
        j().L().b("onBind received unknown action", action);
        return null;
    }

    public final void c() {
        E2.c(this.a, null, null).j().K().a("Local AppMeasurementService is starting up");
    }

    final /* synthetic */ void d(int i, V1 v1, Intent intent) {
        if (this.a.r(i)) {
            v1.K().b("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            j().K().a("Completed wakeful intent.");
            this.a.a(intent);
        }
    }

    final /* synthetic */ void e(V1 v1, JobParameters jobParameters) {
        v1.K().a("AppMeasurementJobService processed last upload request.");
        this.a.b(jobParameters, false);
    }

    @TargetApi(24)
    public final boolean g(final JobParameters jobParameters) {
        final V1 v1J = E2.c(this.a, null, null).j();
        String string = jobParameters.getExtras().getString("action");
        v1J.K().b("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        f(new Runnable() { // from class: com.google.android.gms.measurement.internal.R4
            @Override // java.lang.Runnable
            public final void run() {
                this.q.e(v1J, jobParameters);
            }
        });
        return true;
    }

    public final void h() {
        E2.c(this.a, null, null).j().K().a("Local AppMeasurementService is shutting down");
    }

    public final void i(Intent intent) {
        if (intent == null) {
            j().G().a("onRebind called with null intent");
        } else {
            j().K().b("onRebind called. action", intent.getAction());
        }
    }

    public final boolean k(Intent intent) {
        if (intent == null) {
            j().G().a("onUnbind called with null intent");
            return true;
        }
        j().K().b("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
