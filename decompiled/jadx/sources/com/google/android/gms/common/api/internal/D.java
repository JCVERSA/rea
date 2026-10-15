package com.google.android.gms.common.api.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import ax.p217n6.C2172c;
import ax.p265q6.C2254p;
import com.google.android.gms.common.api.GoogleApiActivity;

/* JADX INFO: loaded from: classes3.dex */
final class D implements Runnable {
    final /* synthetic */ E X;
    private final B q;

    D(E e, B b) {
        this.X = e;
        this.q = b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.X.X) {
            C2172c c2172cB = this.q.b();
            if (c2172cB.A()) {
                E e = this.X;
                e.q.startActivityForResult(GoogleApiActivity.a(e.b(), (PendingIntent) C2254p.l(c2172cB.z()), this.q.a(), false), 1);
                return;
            }
            E e2 = this.X;
            if (e2.k0.b(e2.b(), c2172cB.j(), null) != null) {
                E e3 = this.X;
                e3.k0.x(e3.b(), e3.q, c2172cB.j(), 2, this.X);
                return;
            }
            if (c2172cB.j() != 18) {
                this.X.l(c2172cB, this.q.a());
                return;
            }
            E e4 = this.X;
            Dialog dialogS = e4.k0.s(e4.b(), e4);
            E e5 = this.X;
            e5.k0.t(e5.b().getApplicationContext(), new C(this, dialogS));
        }
    }
}
