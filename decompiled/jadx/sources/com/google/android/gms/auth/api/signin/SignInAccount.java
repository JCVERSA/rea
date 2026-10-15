package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p265q6.C2254p;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* JADX INFO: loaded from: classes3.dex */
public class SignInAccount extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInAccount> CREATOR = new g();
    private final GoogleSignInAccount X;

    @Deprecated
    final String Y;

    @Deprecated
    final String q;

    SignInAccount(String str, GoogleSignInAccount googleSignInAccount, String str2) {
        this.X = googleSignInAccount;
        this.q = C2254p.g(str, "8.3 and 8.4 SDKs require non-null email");
        this.Y = C2254p.g(str2, "8.3 and 8.4 SDKs require non-null userId");
    }

    public final GoogleSignInAccount j() {
        return this.X;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.q;
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.q(parcel, 4, str, false);
        ax.p281r6.c.p(parcel, 7, this.X, i, false);
        ax.p281r6.c.q(parcel, 8, this.Y, false);
        ax.p281r6.c.b(parcel, iA);
    }
}
