package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.g5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6325g5 implements Parcelable.Creator<C6332h5> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C6332h5 createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        long jU = 0;
        int iT = 0;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            int iL = ax.p281r6.b.l(iR);
            if (iL == 1) {
                strF = ax.p281r6.b.f(parcel, iR);
            } else if (iL == 2) {
                jU = ax.p281r6.b.u(parcel, iR);
            } else if (iL != 3) {
                ax.p281r6.b.x(parcel, iR);
            } else {
                iT = ax.p281r6.b.t(parcel, iR);
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new C6332h5(strF, jU, iT);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C6332h5[] newArray(int i) {
        return new C6332h5[i];
    }
}
