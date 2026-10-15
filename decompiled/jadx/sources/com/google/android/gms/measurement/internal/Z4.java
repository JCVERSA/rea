package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class Z4 {
    private Y4 a;
    final /* synthetic */ V4 b;

    Z4(V4 v4) {
        this.b = v4;
    }

    final void a() {
        this.b.n();
        if (this.a != null) {
            this.b.c.removeCallbacks(this.a);
        }
        this.b.h().u.a(false);
        this.b.D(false);
    }

    final void b(long j) {
        this.a = new Y4(this, this.b.b().a(), j);
        this.b.c.postDelayed(this.a, 2000L);
    }
}
