package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        String strF = HttpUrl.FRAGMENT_ENCODE_SET;
        GoogleSignInAccount googleSignInAccount = null;
        String strF2 = HttpUrl.FRAGMENT_ENCODE_SET;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            int iL = ax.p281r6.b.l(iR);
            if (iL == 4) {
                strF = ax.p281r6.b.f(parcel, iR);
            } else if (iL == 7) {
                googleSignInAccount = (GoogleSignInAccount) ax.p281r6.b.e(parcel, iR, GoogleSignInAccount.CREATOR);
            } else if (iL != 8) {
                ax.p281r6.b.x(parcel, iR);
            } else {
                strF2 = ax.p281r6.b.f(parcel, iR);
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new SignInAccount(strF, googleSignInAccount, strF2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new SignInAccount[i];
    }
}
