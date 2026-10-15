package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        Long lV = null;
        ArrayList<String> arrayListH = null;
        String strF2 = null;
        int iT = 0;
        boolean zM = false;
        boolean zM2 = false;
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
                    lV = ax.p281r6.b.v(parcel, iR);
                    break;
                case 4:
                    zM = ax.p281r6.b.m(parcel, iR);
                    break;
                case 5:
                    zM2 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 6:
                    arrayListH = ax.p281r6.b.h(parcel, iR);
                    break;
                case 7:
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new TokenData(iT, strF, lV, zM, zM2, arrayListH, strF2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new TokenData[i];
    }
}
