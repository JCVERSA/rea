package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p409z6.C3772df;
import java.util.ArrayList;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class n5 implements Parcelable.Creator<E5> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ E5 createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        String strF2 = null;
        String strF3 = null;
        String strF4 = null;
        String strF5 = null;
        String strF6 = null;
        String strF7 = null;
        Boolean boolN = null;
        ArrayList<String> arrayListH = null;
        String strF8 = null;
        String strF9 = null;
        String strF10 = null;
        long jU = 0;
        long jU2 = 0;
        long jU3 = 0;
        long jU4 = 0;
        long jU5 = 0;
        long jU6 = 0;
        long jU7 = 0;
        long jU8 = -2147483648L;
        String strF11 = HttpUrl.FRAGMENT_ENCODE_SET;
        String strF12 = strF11;
        String strF13 = strF12;
        String strF14 = strF13;
        boolean zM = true;
        boolean zM2 = false;
        int iT = 0;
        boolean zM3 = true;
        boolean zM4 = false;
        boolean zM5 = false;
        int iT2 = 100;
        int iT3 = 0;
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
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 5:
                    strF4 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 6:
                    jU = ax.p281r6.b.u(parcel, iR);
                    break;
                case 7:
                    jU2 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 8:
                    strF5 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 9:
                    zM = ax.p281r6.b.m(parcel, iR);
                    break;
                case 10:
                    zM2 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 11:
                    jU8 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 12:
                    strF6 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 13:
                    jU3 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 14:
                    jU4 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 15:
                    iT = ax.p281r6.b.t(parcel, iR);
                    break;
                case 16:
                    zM3 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 17:
                case 20:
                case 33:
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
                case 18:
                    zM4 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 19:
                    strF7 = ax.p281r6.b.f(parcel, iR);
                    break;
                case C3772df.zzm /* 21 */:
                    boolN = ax.p281r6.b.n(parcel, iR);
                    break;
                case 22:
                    jU5 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 23:
                    arrayListH = ax.p281r6.b.h(parcel, iR);
                    break;
                case 24:
                    strF8 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 25:
                    strF11 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 26:
                    strF12 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 27:
                    strF9 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 28:
                    zM5 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 29:
                    jU6 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 30:
                    iT2 = ax.p281r6.b.t(parcel, iR);
                    break;
                case 31:
                    strF13 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 32:
                    iT3 = ax.p281r6.b.t(parcel, iR);
                    break;
                case 34:
                    jU7 = ax.p281r6.b.u(parcel, iR);
                    break;
                case 35:
                    strF10 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 36:
                    strF14 = ax.p281r6.b.f(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new E5(strF, strF2, strF3, strF4, jU, jU2, strF5, zM, zM2, jU8, strF6, jU3, jU4, iT, zM3, zM4, strF7, boolN, jU5, arrayListH, strF8, strF11, strF12, strF9, zM5, jU6, iT2, strF13, iT3, jU7, strF10, strF14);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ E5[] newArray(int i) {
        return new E5[i];
    }
}
