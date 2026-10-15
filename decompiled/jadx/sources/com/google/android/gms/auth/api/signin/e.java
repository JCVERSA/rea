package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Scope;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final class e implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel) {
        int iY = ax.p281r6.b.y(parcel);
        ArrayList arrayListJ = null;
        Account account = null;
        String strF = null;
        String strF2 = null;
        ArrayList arrayListJ2 = null;
        String strF3 = null;
        int iT = 0;
        boolean zM = false;
        boolean zM2 = false;
        boolean zM3 = false;
        while (parcel.dataPosition() < iY) {
            int iR = ax.p281r6.b.r(parcel);
            switch (ax.p281r6.b.l(iR)) {
                case 1:
                    iT = ax.p281r6.b.t(parcel, iR);
                    break;
                case 2:
                    arrayListJ = ax.p281r6.b.j(parcel, iR, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) ax.p281r6.b.e(parcel, iR, Account.CREATOR);
                    break;
                case 4:
                    zM = ax.p281r6.b.m(parcel, iR);
                    break;
                case 5:
                    zM2 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 6:
                    zM3 = ax.p281r6.b.m(parcel, iR);
                    break;
                case 7:
                    strF = ax.p281r6.b.f(parcel, iR);
                    break;
                case 8:
                    strF2 = ax.p281r6.b.f(parcel, iR);
                    break;
                case 9:
                    arrayListJ2 = ax.p281r6.b.j(parcel, iR, ax.p185l6.a.CREATOR);
                    break;
                case 10:
                    strF3 = ax.p281r6.b.f(parcel, iR);
                    break;
                default:
                    ax.p281r6.b.x(parcel, iR);
                    break;
            }
        }
        ax.p281r6.b.k(parcel, iY);
        return new GoogleSignInOptions(iT, arrayListJ, account, zM, zM2, zM3, strF, strF2, arrayListJ2, strF3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInOptions[i];
    }
}
