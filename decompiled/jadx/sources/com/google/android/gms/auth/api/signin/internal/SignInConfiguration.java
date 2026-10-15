package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p185l6.b;
import ax.p185l6.w;
import ax.p265q6.C2254p;
import ax.p281r6.c;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class SignInConfiguration extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new w();
    private final GoogleSignInOptions X;
    private final String q;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.q = C2254p.f(str);
        this.X = googleSignInOptions;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SignInConfiguration)) {
            return false;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
        if (this.q.equals(signInConfiguration.q)) {
            GoogleSignInOptions googleSignInOptions = this.X;
            GoogleSignInOptions googleSignInOptions2 = signInConfiguration.X;
            if (googleSignInOptions == null) {
                if (googleSignInOptions2 == null) {
                    return true;
                }
            } else if (googleSignInOptions.equals(googleSignInOptions2)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return new b().a(this.q).a(this.X).b();
    }

    public final GoogleSignInOptions j() {
        return this.X;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        String str = this.q;
        int iA = c.a(parcel);
        c.q(parcel, 2, str, false);
        c.p(parcel, 5, this.X, i, false);
        c.b(parcel, iA);
    }
}
