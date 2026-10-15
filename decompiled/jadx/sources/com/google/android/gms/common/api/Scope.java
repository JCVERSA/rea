package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p265q6.C2254p;
import ax.p281r6.c;
import com.google.android.gms.common.internal.ReflectedParcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class Scope extends ax.p281r6.a implements ReflectedParcelable {
    public static final Parcelable.Creator<Scope> CREATOR = new a();
    private final String X;
    final int q;

    Scope(int i, String str) {
        C2254p.g(str, "scopeUri must not be null or empty");
        this.q = i;
        this.X = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Scope) {
            return this.X.equals(((Scope) obj).X);
        }
        return false;
    }

    public int hashCode() {
        return this.X.hashCode();
    }

    public String j() {
        return this.X;
    }

    public String toString() {
        return this.X;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int i2 = this.q;
        int iA = c.a(parcel);
        c.k(parcel, 1, i2);
        c.q(parcel, 2, j(), false);
        c.b(parcel, iA);
    }
}
