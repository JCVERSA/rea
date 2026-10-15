package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import ax.H6.C1038s0;
import ax.H6.C1046t0;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.j5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6346j5 extends AbstractC6353k5 {
    private final AlarmManager d;
    private AbstractC6397s e;
    private Integer f;

    protected C6346j5(q5 q5Var) {
        super(q5Var);
        this.d = (AlarmManager) a().getSystemService("alarm");
    }

    private final int A() {
        if (this.f == null) {
            this.f = Integer.valueOf(("measurement" + a().getPackageName()).hashCode());
        }
        return this.f.intValue();
    }

    private final PendingIntent B() {
        Context contextA = a();
        return C1046t0.a(contextA, 0, new Intent().setClassName(contextA, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), C1046t0.a);
    }

    private final AbstractC6397s C() {
        if (this.e == null) {
            this.e = new C6339i5(this, this.b.o0());
        }
        return this.e;
    }

    @TargetApi(24)
    private final void D() {
        JobScheduler jobScheduler = (JobScheduler) a().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(A());
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ x5 o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ J5 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6354l q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6389q2 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ Q4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ o5 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        AlarmManager alarmManager = this.d;
        if (alarmManager != null) {
            alarmManager.cancel(B());
        }
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        D();
        return false;
    }

    public final void y(long j) {
        u();
        Context contextA = a();
        if (!B5.d0(contextA)) {
            j().F().a("Receiver not registered/enabled");
        }
        if (!B5.e0(contextA, false)) {
            j().F().a("Service not registered/enabled");
        }
        z();
        j().K().b("Scheduling upload, millis", Long.valueOf(j));
        long jB = b().b() + j;
        if (j < Math.max(0L, F.y.a(null).longValue()) && !C().e()) {
            C().b(j);
        }
        if (Build.VERSION.SDK_INT < 24) {
            AlarmManager alarmManager = this.d;
            if (alarmManager != null) {
                alarmManager.setInexactRepeating(2, jB, Math.max(F.t.a(null).longValue(), j), B());
                return;
            }
            return;
        }
        Context contextA2 = a();
        ComponentName componentName = new ComponentName(contextA2, "com.google.android.gms.measurement.AppMeasurementJobService");
        int iA = A();
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
        C1038s0.c(contextA2, new JobInfo.Builder(iA, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
    }

    public final void z() {
        u();
        j().K().a("Unscheduling upload");
        AlarmManager alarmManager = this.d;
        if (alarmManager != null) {
            alarmManager.cancel(B());
        }
        C().a();
        if (Build.VERSION.SDK_INT >= 24) {
            D();
        }
    }
}
