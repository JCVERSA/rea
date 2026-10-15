package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.a5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6283a5 {
    private long a;
    protected long b;
    private final AbstractC6397s c;
    final /* synthetic */ V4 d;

    public C6283a5(V4 v4) {
        this.d = v4;
        this.c = new C6304d5(this, v4.a);
        long jB = v4.b().b();
        this.a = jB;
        this.b = jB;
    }

    static /* synthetic */ void c(C6283a5 c6283a5) {
        c6283a5.d.n();
        c6283a5.d(false, false, c6283a5.d.b().b());
        c6283a5.d.o().v(c6283a5.d.b().b());
    }

    final long a(long j) {
        long j2 = j - this.b;
        this.b = j;
        return j2;
    }

    final void b() {
        this.c.a();
        if (this.d.d().t(F.Z0)) {
            this.a = this.d.b().b();
        } else {
            this.a = 0L;
        }
        this.b = this.a;
    }

    public final boolean d(boolean z, boolean z2, long j) {
        this.d.n();
        this.d.v();
        if (this.d.a.p()) {
            this.d.h().r.b(this.d.b().a());
        }
        long jA = j - this.a;
        if (!z && jA < 1000) {
            this.d.j().K().b("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(jA));
            return false;
        }
        if (!z2) {
            jA = a(j);
        }
        this.d.j().K().b("Recording user engagement, ms", Long.valueOf(jA));
        Bundle bundle = new Bundle();
        bundle.putLong("_et", jA);
        B5.X(this.d.s().C(!this.d.d().T()), bundle, true);
        if (!z2) {
            this.d.r().Y0("auto", "_e", bundle);
        }
        this.a = j;
        this.c.a();
        this.c.b(F.b0.a(null).longValue());
        return true;
    }

    final void e(long j) {
        this.c.a();
    }

    final void f(long j) {
        this.d.n();
        this.c.a();
        this.a = j;
        this.b = j;
    }
}
