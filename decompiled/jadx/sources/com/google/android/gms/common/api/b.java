package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import ax.p217n6.C2172c;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        PendingIntent pendingIntent = null;
        C2172c c2172c = null;
        int iT = 0;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            int iL = ax.p281r6.b.l(iR);
            if (iL == 1) {
                iT = ax.p281r6.b.t(parcel, iR);
            } else if (iL == 2) {
                strF = ax.p281r6.b.f(parcel, iR);
            } else if (iL == 3) {
                pendingIntent = (PendingIntent) ax.p281r6.b.e(parcel, iR, PendingIntent.CREATOR);
            } else if (iL != 4) {
                ax.p281r6.b.x(parcel, iR);
            } else {
                c2172c = (C2172c) ax.p281r6.b.e(parcel, iR, C2172c.CREATOR);
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new Status(iT, strF, pendingIntent, c2172c);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Status[i];
    }
}
