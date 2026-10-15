package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        int iT = 0;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            int iL = ax.p281r6.b.l(iR);
            if (iL == 1) {
                iT = ax.p281r6.b.t(parcel, iR);
            } else if (iL != 2) {
                ax.p281r6.b.x(parcel, iR);
            } else {
                strF = ax.p281r6.b.f(parcel, iR);
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new Scope(iT, strF);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Scope[i];
    }
}
