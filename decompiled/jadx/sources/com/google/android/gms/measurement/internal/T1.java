package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import ax.M6.InterfaceC1351e;
import ax.p265q6.AbstractC2241c;

/* JADX INFO: loaded from: classes3.dex */
public final class T1 extends AbstractC2241c<InterfaceC1351e> {
    public T1(Context context, Looper looper, AbstractC2241c.a aVar, AbstractC2241c.b bVar) {
        super(context, looper, 93, aVar, bVar, null);
    }

    @Override // ax.p265q6.AbstractC2241c
    protected final String E() {
        return "com.google.android.gms.measurement.internal.IMeasurementService";
    }

    @Override // ax.p265q6.AbstractC2241c
    protected final String F() {
        return "com.google.android.gms.measurement.START";
    }

    @Override // ax.p265q6.AbstractC2241c, ax.o6.a.f
    public final int j() {
        return 12451000;
    }

    @Override // ax.p265q6.AbstractC2241c
    public final /* synthetic */ IInterface s(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");
        return iInterfaceQueryLocalInterface instanceof InterfaceC1351e ? (InterfaceC1351e) iInterfaceQueryLocalInterface : new O1(iBinder);
    }
}
