package com.google.android.gms.auth.api.signin;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = null;
        String strF2 = null;
        String strF3 = null;
        String strF4 = null;
        Uri uri = null;
        String strF5 = null;
        String strF6 = null;
        ArrayList arrayListJ = null;
        String strF7 = null;
        String strF8 = null;
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
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 4:
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 5:
                    strF4 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 6:
                    uri = (Uri) ax.p281r6.b.e(parcel, iR, Uri.CREATOR);
                    break;
                case 7:
                    strF5 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 8:
                    jU = ax.p281r6.b.u(parcel, iR);
                    break;
                case 9:
                    strF6 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 10:
                    arrayListJ = ax.p281r6.b.j(parcel, iR, Scope.CREATOR);
                    break;
                case 11:
                    strF7 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 12:
                    strF8 = ax.p281r6.b.f(parcel, iR);
                    break;
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new GoogleSignInAccount(iT, strF, strF2, strF3, strF4, uri, strF5, jU, strF6, arrayListJ, strF7, strF8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
