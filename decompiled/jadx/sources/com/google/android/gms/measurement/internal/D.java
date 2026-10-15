package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import ax.M6.C1349c;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
public final class D extends ax.p281r6.a {
    public static final Parcelable.Creator<D> CREATOR = new C1349c();
    public final C X;
    public final String Y;
    public final long Z;
    public final String q;

    D(D d, long j) {
        C2254p.l(d);
        this.q = d.q;
        this.X = d.X;
        this.Y = d.Y;
        this.Z = j;
    }

    public D(String str, C c, String str2, long j) {
        this.q = str;
        this.X = c;
        this.Y = str2;
        this.Z = j;
    }

    public final String toString() {
        return "origin=" + this.Y + ",name=" + this.q + ",params=" + String.valueOf(this.X);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.q(parcel, 2, this.q, false);
        ax.p281r6.c.p(parcel, 3, this.X, i, false);
        ax.p281r6.c.q(parcel, 4, this.Y, false);
        ax.p281r6.c.n(parcel, 5, this.Z);
        ax.p281r6.c.b(parcel, iA);
    }
}
