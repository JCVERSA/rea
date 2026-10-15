package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.c5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6297c5 {
    final /* synthetic */ V4 a;

    C6297c5(V4 v4) {
        this.a = v4;
    }

    private final void c(long j, boolean z) {
        this.a.n();
        if (this.a.a.p()) {
            this.a.h().r.b(j);
            this.a.j().K().b("Session started, time", Long.valueOf(this.a.b().b()));
            long j2 = j / 1000;
            this.a.r().j0("auto", "_sid", Long.valueOf(j2), j);
            this.a.h().s.b(j2);
            this.a.h().n.a(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", j2);
            this.a.r().d0("auto", "_s", j, bundle);
            String strA = this.a.h().x.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            Bundle bundle2 = new Bundle();
            bundle2.putString("_ffr", strA);
            this.a.r().d0("auto", "_ssr", j, bundle2);
        }
    }

    final void a() {
        this.a.n();
        if (this.a.h().z(this.a.b().a())) {
            this.a.h().n.a(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.a.j().K().a("Detected application was in foreground");
                c(this.a.b().a(), false);
            }
        }
    }

    final void b(long j, boolean z) {
        this.a.n();
        this.a.G();
        if (this.a.h().z(j)) {
            this.a.h().n.a(true);
            this.a.p().I();
        }
        this.a.h().r.b(j);
        if (this.a.h().n.b()) {
            c(j, z);
        }
    }
}
