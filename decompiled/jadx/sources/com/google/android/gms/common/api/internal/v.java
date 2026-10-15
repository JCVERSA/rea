package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import ax.Q6.C1478k;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes3.dex */
abstract class v extends ax.p249p6.u {
    protected final C1478k b;

    public v(int i, C1478k c1478k) {
        super(i);
        this.b = c1478k;
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void a(Status status) {
        this.b.d(new ax.p233o6.b(status));
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void b(Exception exc) {
        this.b.d(exc);
    }

    @Override // com.google.android.gms.common.api.internal.A
    public final void c(o oVar) throws DeadObjectException {
        try {
            h(oVar);
        } catch (DeadObjectException e) {
            a(A.e(e));
            throw e;
        } catch (RemoteException e2) {
            a(A.e(e2));
        } catch (RuntimeException e3) {
            this.b.d(e3);
        }
    }

    protected abstract void h(o oVar) throws RemoteException;
}
