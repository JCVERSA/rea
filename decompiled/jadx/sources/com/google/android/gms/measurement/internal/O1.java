package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import ax.H6.C0882a0;
import ax.M6.C1347a;
import ax.M6.InterfaceC1351e;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class O1 extends ax.H6.Y implements InterfaceC1351e {
    O1(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // ax.M6.InterfaceC1351e
    public final void F1(D d, String str, String str2) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, d);
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        e1(5, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void H7(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(25, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void I1(A5 a5, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, a5);
        C0882a0.d(parcelM0, e5);
        e1(2, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void I6(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(6, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6332h5> K6(E5 e5, Bundle bundle) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        C0882a0.d(parcelM0, bundle);
        Parcel parcelP0 = P0(24, parcelM0);
        ArrayList arrayListCreateTypedArrayList = parcelP0.createTypedArrayList(C6332h5.CREATOR);
        parcelP0.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // ax.M6.InterfaceC1351e
    public final void P3(long j, String str, String str2, String str3) throws RemoteException {
        Parcel parcelM0 = M0();
        parcelM0.writeLong(j);
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        parcelM0.writeString(str3);
        e1(10, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void S3(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(4, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6312f> T3(String str, String str2, String str3) throws RemoteException {
        Parcel parcelM0 = M0();
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        parcelM0.writeString(str3);
        Parcel parcelP0 = P0(17, parcelM0);
        ArrayList arrayListCreateTypedArrayList = parcelP0.createTypedArrayList(C6312f.CREATOR);
        parcelP0.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // ax.M6.InterfaceC1351e
    public final String X2(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        Parcel parcelP0 = P0(11, parcelM0);
        String string = parcelP0.readString();
        parcelP0.recycle();
        return string;
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<A5> d6(String str, String str2, boolean z, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        C0882a0.e(parcelM0, z);
        C0882a0.d(parcelM0, e5);
        Parcel parcelP0 = P0(14, parcelM0);
        ArrayList arrayListCreateTypedArrayList = parcelP0.createTypedArrayList(A5.CREATOR);
        parcelP0.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<A5> e2(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel parcelM0 = M0();
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        parcelM0.writeString(str3);
        C0882a0.e(parcelM0, z);
        Parcel parcelP0 = P0(15, parcelM0);
        ArrayList arrayListCreateTypedArrayList = parcelP0.createTypedArrayList(A5.CREATOR);
        parcelP0.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // ax.M6.InterfaceC1351e
    public final void g4(C6312f c6312f) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, c6312f);
        e1(13, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final byte[] g5(D d, String str) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, d);
        parcelM0.writeString(str);
        Parcel parcelP0 = P0(9, parcelM0);
        byte[] bArrCreateByteArray = parcelP0.createByteArray();
        parcelP0.recycle();
        return bArrCreateByteArray;
    }

    @Override // ax.M6.InterfaceC1351e
    public final void g6(D d, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, d);
        C0882a0.d(parcelM0, e5);
        e1(1, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6312f> i1(String str, String str2, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        parcelM0.writeString(str);
        parcelM0.writeString(str2);
        C0882a0.d(parcelM0, e5);
        Parcel parcelP0 = P0(16, parcelM0);
        ArrayList arrayListCreateTypedArrayList = parcelP0.createTypedArrayList(C6312f.CREATOR);
        parcelP0.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // ax.M6.InterfaceC1351e
    public final void m2(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(20, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void o2(Bundle bundle, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, bundle);
        C0882a0.d(parcelM0, e5);
        e1(19, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void q2(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(26, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void q3(C6312f c6312f, E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, c6312f);
        C0882a0.d(parcelM0, e5);
        e1(12, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void u1(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        e1(18, parcelM0);
    }

    @Override // ax.M6.InterfaceC1351e
    public final C1347a w5(E5 e5) throws RemoteException {
        Parcel parcelM0 = M0();
        C0882a0.d(parcelM0, e5);
        Parcel parcelP0 = P0(21, parcelM0);
        C1347a c1347a = (C1347a) C0882a0.a(parcelP0, C1347a.CREATOR);
        parcelP0.recycle();
        return c1347a;
    }
}
