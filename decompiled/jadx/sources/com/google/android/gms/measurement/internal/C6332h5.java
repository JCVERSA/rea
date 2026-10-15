package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6332h5 extends ax.p281r6.a {
    public static final Parcelable.Creator<C6332h5> CREATOR = new C6325g5();
    public final long X;
    public final int Y;
    public final String q;

    C6332h5(String str, long j, int i) {
        this.q = str;
        this.X = j;
        this.Y = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.q(parcel, 1, this.q, false);
        ax.p281r6.c.n(parcel, 2, this.X);
        ax.p281r6.c.k(parcel, 3, this.Y);
        ax.p281r6.c.b(parcel, iA);
    }
}
