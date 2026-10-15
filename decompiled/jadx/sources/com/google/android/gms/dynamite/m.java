package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes3.dex */
public final class m extends ax.F6.a implements IInterface {
    m(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final ax.p377x6.a L3(ax.p377x6.a aVar, String str, int i) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(i);
        Parcel parcelM0 = M0(2, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }

    public final int N2(ax.p377x6.a aVar, String str, boolean z) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(z ? 1 : 0);
        Parcel parcelM0 = M0(5, parcelP0);
        int i = parcelM0.readInt();
        parcelM0.recycle();
        return i;
    }

    public final ax.p377x6.a Y3(ax.p377x6.a aVar, String str, int i, ax.p377x6.a aVar2) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(i);
        ax.F6.c.d(parcelP0, aVar2);
        Parcel parcelM0 = M0(8, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }

    public final int d() throws RemoteException {
        Parcel parcelM0 = M0(6, P0());
        int i = parcelM0.readInt();
        parcelM0.recycle();
        return i;
    }

    public final int e1(ax.p377x6.a aVar, String str, boolean z) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(z ? 1 : 0);
        Parcel parcelM0 = M0(3, parcelP0);
        int i = parcelM0.readInt();
        parcelM0.recycle();
        return i;
    }

    public final ax.p377x6.a s5(ax.p377x6.a aVar, String str, int i) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(i);
        Parcel parcelM0 = M0(4, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }

    public final ax.p377x6.a y6(ax.p377x6.a aVar, String str, boolean z, long j) throws RemoteException {
        Parcel parcelP0 = P0();
        ax.F6.c.d(parcelP0, aVar);
        parcelP0.writeString(str);
        parcelP0.writeInt(z ? 1 : 0);
        parcelP0.writeLong(j);
        Parcel parcelM0 = M0(7, parcelP0);
        ax.p377x6.a aVarP0 = ax.p377x6.a.AbstractBinderC0505a.P0(parcelM0.readStrongBinder());
        parcelM0.recycle();
        return aVarP0;
    }
}
