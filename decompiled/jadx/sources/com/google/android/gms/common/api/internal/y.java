package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import ax.Q6.C1478k;
import ax.p217n6.C2174e;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes3.dex */
public final class y extends ax.p249p6.u {
    private final AbstractC6273d b;
    private final C1478k c;
    private final ax.p249p6.l d;

    public y(int i, AbstractC6273d abstractC6273d, C1478k c1478k, ax.p249p6.l lVar) {
        super(i);
        this.c = c1478k;
        this.b = abstractC6273d;
        this.d = lVar;
        if (i == 2 && abstractC6273d.c()) {
            throw new IllegalArgumentException("Best-effort write calls cannot pass methods that should auto-resolve missing features.");
        }
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void a(Status status) {
        this.c.d(this.d.a(status));
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void b(Exception exc) {
        this.c.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void c(o oVar) throws DeadObjectException {
        try {
            this.b.b(oVar.t(), this.c);
        } catch (DeadObjectException e) {
            throw e;
        } catch (RemoteException e2) {
            a(A.e(e2));
        } catch (RuntimeException e3) {
            this.c.d(e3);
        }
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void d(C6276g c6276g, boolean z) {
        c6276g.d(this.c, z);
    }

    @Override // ax.p249p6.u
    public final boolean f(o oVar) {
        return this.b.c();
    }

    @Override // ax.p249p6.u
    public final C2174e[] g(o oVar) {
        return this.b.e();
    }
}
