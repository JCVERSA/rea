package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6305e implements Parcelable.Creator<C6312f> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C6312f createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        String strF2 = null;
        A5 a5 = null;
        String strF3 = null;
        D d = null;
        D d2 = null;
        D d3 = null;
        long jU = 0;
        long jU2 = 0;
        long jU3 = 0;
        boolean zM = false;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            switch (ax.p281r6.b.l(iR)) {
                case 2:
                    strF = ax.p281r6.b.f(parcel, iR);
                    break;
                case 3:
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 4:
                    a5 = (A5) ax.p281r6.b.e(parcel, iR, A5.CREATOR);
                    break;
                case 5:
                    jU = ax.p281r6.b.u(parcel, iR);
                    break;
                case 6:
                    zM = ax.p281r6.b.m(parcel, iR);
                    break;
                case 7:
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 8:
                    d = (D) ax.p281r6.b.e(parcel, iR, D.CREATOR);
                    break;
                case 9:
                    jU2 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 10:
                    d2 = (D) ax.p281r6.b.e(parcel, iR, D.CREATOR);
                    break;
                case 11:
                    jU3 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 12:
                    d3 = (D) ax.p281r6.b.e(parcel, iR, D.CREATOR);
                    break;
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new C6312f(strF, strF2, a5, jU, zM, strF3, d, jU2, d2, jU3, d3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ C6312f[] newArray(int i) {
        return new C6312f[i];
    }
}
