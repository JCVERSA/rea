package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ax.p265q6.C2252n;
import ax.p265q6.C2254p;
import ax.p281r6.c;
import com.google.android.gms.common.internal.ReflectedParcelable;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TokenData extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<TokenData> CREATOR = new a();
    private final String X;
    private final Long Y;
    private final boolean Z;
    private final boolean k0;
    private final List l0;
    private final String m0;
    final int q;

    TokenData(int i, String str, Long l, boolean z, boolean z2, List list, String str2) {
        this.q = i;
        this.X = C2254p.f(str);
        this.Y = l;
        this.Z = z;
        this.k0 = z2;
        this.l0 = list;
        this.m0 = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TokenData)) {
            return false;
        }
        TokenData tokenData = (TokenData) obj;
        return TextUtils.equals(this.X, tokenData.X) && C2252n.b(this.Y, tokenData.Y) && this.Z == tokenData.Z && this.k0 == tokenData.k0 && C2252n.b(this.l0, tokenData.l0) && C2252n.b(this.m0, tokenData.m0);
    }

    public final int hashCode() {
        return C2252n.c(this.X, this.Y, Boolean.valueOf(this.Z), Boolean.valueOf(this.k0), this.l0, this.m0);
    }

    public final String j() {
        return this.X;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = c.a(parcel);
        c.k(parcel, 1, this.q);
        c.q(parcel, 2, this.X, false);
        c.o(parcel, 3, this.Y, false);
        c.c(parcel, 4, this.Z);
        c.c(parcel, 5, this.k0);
        c.s(parcel, 6, this.l0, false);
        c.q(parcel, 7, this.m0, false);
        c.b(parcel, iA);
    }
}
