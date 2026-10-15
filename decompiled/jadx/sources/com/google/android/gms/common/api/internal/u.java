package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import ax.Q6.C1478k;
import ax.p217n6.C2174e;

/* JADX INFO: loaded from: classes3.dex */
final class u extends AbstractC6273d {
    final /* synthetic */ AbstractC6273d.a d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    u(AbstractC6273d.a aVar, C2174e[] c2174eArr, boolean z, int i) {
        super(c2174eArr, z, i);
        this.d = aVar;
    }

    @Override // com.google.android.gms.common.api.internal.AbstractC6273d
    protected final void b(ax.o6.a.b bVar, C1478k c1478k) throws RemoteException {
        this.d.a.a(bVar, c1478k);
    }
}
