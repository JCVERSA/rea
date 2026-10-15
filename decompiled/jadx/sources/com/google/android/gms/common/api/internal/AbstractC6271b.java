package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.os.DeadObjectException;
import android.os.RemoteException;
import ax.o6.a.b;
import ax.p233o6.l;
import ax.p265q6.C2254p;
import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractC6271b<R extends ax.p233o6.l, A extends ax.o6.a.b> extends BasePendingResult<R> {
    private final ax.o6.a.c<A> o;
    private final ax.p233o6.a<?> p;

    protected AbstractC6271b(ax.p233o6.a<?> aVar, ax.p233o6.g gVar) {
        super((ax.p233o6.g) C2254p.m(gVar, "GoogleApiClient must not be null"));
        C2254p.m(aVar, "Api must not be null");
        this.o = aVar.b();
        this.p = aVar;
    }

    private void o(RemoteException remoteException) {
        p(new Status(8, remoteException.getLocalizedMessage(), (PendingIntent) null));
    }

    protected abstract void l(A a) throws RemoteException;

    protected void m(R r) {
    }

    public final void n(A a) throws DeadObjectException {
        try {
            l(a);
        } catch (DeadObjectException e) {
            o(e);
            throw e;
        } catch (RemoteException e2) {
            o(e2);
        }
    }

    public final void p(Status status) {
        C2254p.b(!status.E(), "Failed result must not be success");
        R rC = c(status);
        f(rC);
        m(rC);
    }
}
