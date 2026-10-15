package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6312f extends ax.p281r6.a {
    public static final Parcelable.Creator<C6312f> CREATOR = new C6305e();
    public String X;
    public A5 Y;
    public long Z;
    public boolean k0;
    public String l0;
    public D m0;
    public long n0;
    public D o0;
    public long p0;
    public String q;
    public D q0;

    C6312f(C6312f c6312f) {
        C2254p.l(c6312f);
        this.q = c6312f.q;
        this.X = c6312f.X;
        this.Y = c6312f.Y;
        this.Z = c6312f.Z;
        this.k0 = c6312f.k0;
        this.l0 = c6312f.l0;
        this.m0 = c6312f.m0;
        this.n0 = c6312f.n0;
        this.o0 = c6312f.o0;
        this.p0 = c6312f.p0;
        this.q0 = c6312f.q0;
    }

    C6312f(String str, String str2, A5 a5, long j, boolean z, String str3, D d, long j2, D d2, long j3, D d3) {
        this.q = str;
        this.X = str2;
        this.Y = a5;
        this.Z = j;
        this.k0 = z;
        this.l0 = str3;
        this.m0 = d;
        this.n0 = j2;
        this.o0 = d2;
        this.p0 = j3;
        this.q0 = d3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.q(parcel, 2, this.q, false);
        ax.p281r6.c.q(parcel, 3, this.X, false);
        ax.p281r6.c.p(parcel, 4, this.Y, i, false);
        ax.p281r6.c.n(parcel, 5, this.Z);
        ax.p281r6.c.c(parcel, 6, this.k0);
        ax.p281r6.c.q(parcel, 7, this.l0, false);
        ax.p281r6.c.p(parcel, 8, this.m0, i, false);
        ax.p281r6.c.n(parcel, 9, this.n0);
        ax.p281r6.c.p(parcel, 10, this.o0, i, false);
        ax.p281r6.c.n(parcel, 11, this.p0);
        ax.p281r6.c.p(parcel, 12, this.q0, i, false);
        ax.p281r6.c.b(parcel, iA);
    }
}
