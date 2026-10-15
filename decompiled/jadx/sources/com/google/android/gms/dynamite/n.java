package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public final class n extends ax.F6.a implements IInterface {
    n(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final ax.p377x6.a N2(ax.p377x6.a aVar, String str, int i, ax.p377x6.a aVar2) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(i);
        ax.F6.c.d(parcelP0, aVar2);
        Parcel parcelM0 = M0(3, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }

    public final ax.p377x6.a e1(ax.p377x6.a aVar, String str, int i, ax.p377x6.a aVar2) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(i);
        ax.F6.c.d(parcelP0, aVar2);
        Parcel parcelM0 = M0(2, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }
}
