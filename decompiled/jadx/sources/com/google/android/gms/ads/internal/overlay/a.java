package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import ax.S5.l;
import ax.p409z6.C3772df;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        long jU = 0;
        l lVar = null;
        IBinder iBinderS = null;
        IBinder iBinderS2 = null;
        IBinder iBinderS3 = null;
        IBinder iBinderS4 = null;
        String strF = null;
        String strF2 = null;
        IBinder iBinderS5 = null;
        String strF3 = null;
        ax.U5.a aVar = null;
        String strF4 = null;
        ax.P5.l lVar2 = null;
        IBinder iBinderS6 = null;
        String strF5 = null;
        String strF6 = null;
        String strF7 = null;
        IBinder iBinderS7 = null;
        IBinder iBinderS8 = null;
        IBinder iBinderS9 = null;
        boolean zM = false;
        int iT = 0;
        int iT2 = 0;
        boolean zM2 = false;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            switch (ax.p281r6.b.l(iR)) {
                case 2:
                    lVar = (l) ax.p281r6.b.e(parcel, iR, l.CREATOR);
                    break;
                case 3:
                    iBinderS = ax.p281r6.b.s(parcel, iR);
                    break;
                case 4:
                    iBinderS2 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 5:
                    iBinderS3 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 6:
                    iBinderS4 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 7:
                    strF = ax.p281r6.b.f(parcel, iR);
                    break;
                case 8:
                    zM = ax.p281r6.b.m(parcel, iR);
                    break;
                case 9:
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 10:
                    iBinderS5 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 11:
                    iT = ax.p281r6.b.t(parcel, iR);
                    break;
                case 12:
                    iT2 = ax.p281r6.b.t(parcel, iR);
                    break;
                case 13:
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 14:
                    aVar = (ax.U5.a) ax.p281r6.b.e(parcel, iR, ax.U5.a.CREATOR);
                    break;
                case 15:
                case 20:
                case C3772df.zzm /* 21 */:
                case 22:
                case 23:
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
                case 16:
                    strF4 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 17:
                    lVar2 = (ax.P5.l) ax.p281r6.b.e(parcel, iR, ax.P5.l.CREATOR);
                    break;
                case 18:
                    iBinderS6 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 19:
                    strF5 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 24:
                    strF6 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 25:
                    strF7 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 26:
                    iBinderS7 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 27:
                    iBinderS8 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 28:
                    iBinderS9 = ax.p281r6.b.s(parcel, iR);
                    break;
                case 29:
                    zM2 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 30:
                    jU = ax.p281r6.b.u(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new AdOverlayInfoParcel(lVar, iBinderS, iBinderS2, iBinderS3, iBinderS4, strF, zM, strF2, iBinderS5, iT, iT2, strF3, aVar, strF4, lVar2, iBinderS6, strF5, strF6, strF7, iBinderS7, iBinderS8, iBinderS9, zM2, jU);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdOverlayInfoParcel[i];
    }
}
