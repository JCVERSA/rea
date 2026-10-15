package com.google.android.gms.measurement.internal;

import android.os.Handler;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractC6397s {
    private static volatile Handler d;
    private final InterfaceC6323g3 a;
    private final Runnable b;
    private volatile long c;

    AbstractC6397s(InterfaceC6323g3 interfaceC6323g3) {
        C2254p.l(interfaceC6323g3);
        this.a = interfaceC6323g3;
        this.b = new RunnableC6415v(this, interfaceC6323g3);
    }

    private final Handler f() {
        Handler handler;
        if (d != null) {
            return d;
        }
        synchronized (AbstractC6397s.class) {
            try {
                if (d == null) {
                    d = new ax.H6.E0(this.a.a().getMainLooper());
                }
                handler = d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return handler;
    }

    final void a() {
        this.c = 0L;
        f().removeCallbacks(this.b);
    }

    public final void b(long j) {
        a();
        if (j >= 0) {
            this.c = this.a.b().a();
            if (f().postDelayed(this.b, j)) {
                return;
            }
            this.a.j().G().b("Failed to schedule delayed post. time", Long.valueOf(j));
        }
    }

    public abstract void d();

    public final boolean e() {
        return this.c != 0;
    }
}
