package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.util.Log;
import ax.p265q6.C2254p;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes3.dex */
public final class x extends A {
    protected final AbstractC6271b b;

    public x(int i, AbstractC6271b abstractC6271b) {
        super(i);
        this.b = (AbstractC6271b) C2254p.m(abstractC6271b, "Null methods are not runnable.");
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void a(Status status) {
        try {
            this.b.p(status);
        } catch (IllegalStateException e) {
            Log.w("ApiCallRunner", "Exception reporting failure", e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void b(Exception exc) {
        try {
            this.b.p(new Status(10, exc.getClass().getSimpleName() + ": " + exc.getLocalizedMessage()));
        } catch (IllegalStateException e) {
            Log.w("ApiCallRunner", "Exception reporting failure", e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void c(o oVar) throws DeadObjectException {
        try {
            this.b.n(oVar.t());
        } catch (RuntimeException e) {
            b(e);
        }
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void d(C6276g c6276g, boolean z) {
        c6276g.c(this.b, z);
    }
}
