package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class z5 implements Parcelable.Creator<A5> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ A5 createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        Long lV = null;
        Float fQ = null;
        String strF2 = null;
        String strF3 = null;
        Double dO = null;
        long jU = 0;
        int iT = 0;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            switch (ax.p281r6.b.l(iR)) {
                case 1:
                    iT = ax.p281r6.b.t(parcel, iR);
                    break;
                case 2:
                    strF = ax.p281r6.b.f(parcel, iR);
                    break;
                case 3:
                    jU = ax.p281r6.b.u(parcel, iR);
                    break;
                case 4:
                    lV = ax.p281r6.b.v(parcel, iR);
                    break;
                case 5:
                    fQ = ax.p281r6.b.q(parcel, iR);
                    break;
                case 6:
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 7:
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 8:
                    dO = ax.p281r6.b.o(parcel, iR);
                    break;
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new A5(iT, strF, jU, lV, fQ, strF2, strF3, dO);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ A5[] newArray(int i) {
        return new A5[i];
    }
}
