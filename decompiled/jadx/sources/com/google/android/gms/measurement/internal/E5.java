package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import ax.p265q6.C2254p;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class E5 extends ax.p281r6.a {
    public static final Parcelable.Creator<E5> CREATOR = new n5();
    private final String A0;
    public final String B0;
    public final String C0;
    public final String D0;
    public final boolean E0;
    public final long F0;
    public final int G0;
    public final String H0;
    public final int I0;
    public final long J0;
    public final String K0;
    public final String L0;
    public final String X;
    public final String Y;
    public final String Z;
    public final long k0;
    public final long l0;
    public final String m0;
    public final boolean n0;
    public final boolean o0;
    public final long p0;
    public final String q;
    public final String q0;

    @Deprecated
    private final long r0;
    public final long s0;
    public final int t0;
    public final boolean u0;
    public final boolean v0;
    public final String w0;
    public final Boolean x0;
    public final long y0;
    public final List<String> z0;

    E5(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3, boolean z4, String str7, Boolean bool, long j6, List<String> list, String str8, String str9, String str10, String str11, boolean z5, long j7, int i2, String str12, int i3, long j8, String str13, String str14) {
        C2254p.f(str);
        this.q = str;
        this.X = TextUtils.isEmpty(str2) ? null : str2;
        this.Y = str3;
        this.p0 = j;
        this.Z = str4;
        this.k0 = j2;
        this.l0 = j3;
        this.m0 = str5;
        this.n0 = z;
        this.o0 = z2;
        this.q0 = str6;
        this.r0 = j4;
        this.s0 = j5;
        this.t0 = i;
        this.u0 = z3;
        this.v0 = z4;
        this.w0 = str7;
        this.x0 = bool;
        this.y0 = j6;
        this.z0 = list;
        this.A0 = null;
        this.B0 = str9;
        this.C0 = str10;
        this.D0 = str11;
        this.E0 = z5;
        this.F0 = j7;
        this.G0 = i2;
        this.H0 = str12;
        this.I0 = i3;
        this.J0 = j8;
        this.K0 = str13;
        this.L0 = str14;
    }

    E5(String str, String str2, String str3, String str4, long j, long j2, String str5, boolean z, boolean z2, long j3, String str6, long j4, long j5, int i, boolean z3, boolean z4, String str7, Boolean bool, long j6, List<String> list, String str8, String str9, String str10, String str11, boolean z5, long j7, int i2, String str12, int i3, long j8, String str13, String str14) {
        this.q = str;
        this.X = str2;
        this.Y = str3;
        this.p0 = j3;
        this.Z = str4;
        this.k0 = j;
        this.l0 = j2;
        this.m0 = str5;
        this.n0 = z;
        this.o0 = z2;
        this.q0 = str6;
        this.r0 = j4;
        this.s0 = j5;
        this.t0 = i;
        this.u0 = z3;
        this.v0 = z4;
        this.w0 = str7;
        this.x0 = bool;
        this.y0 = j6;
        this.z0 = list;
        this.A0 = str8;
        this.B0 = str9;
        this.C0 = str10;
        this.D0 = str11;
        this.E0 = z5;
        this.F0 = j7;
        this.G0 = i2;
        this.H0 = str12;
        this.I0 = i3;
        this.J0 = j8;
        this.K0 = str13;
        this.L0 = str14;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.q(parcel, 2, this.q, false);
        ax.p281r6.c.q(parcel, 3, this.X, false);
        ax.p281r6.c.q(parcel, 4, this.Y, false);
        ax.p281r6.c.q(parcel, 5, this.Z, false);
        ax.p281r6.c.n(parcel, 6, this.k0);
        ax.p281r6.c.n(parcel, 7, this.l0);
        ax.p281r6.c.q(parcel, 8, this.m0, false);
        ax.p281r6.c.c(parcel, 9, this.n0);
        ax.p281r6.c.c(parcel, 10, this.o0);
        ax.p281r6.c.n(parcel, 11, this.p0);
        ax.p281r6.c.q(parcel, 12, this.q0, false);
        ax.p281r6.c.n(parcel, 13, this.r0);
        ax.p281r6.c.n(parcel, 14, this.s0);
        ax.p281r6.c.k(parcel, 15, this.t0);
        ax.p281r6.c.c(parcel, 16, this.u0);
        ax.p281r6.c.c(parcel, 18, this.v0);
        ax.p281r6.c.q(parcel, 19, this.w0, false);
        ax.p281r6.c.d(parcel, 21, this.x0, false);
        ax.p281r6.c.n(parcel, 22, this.y0);
        ax.p281r6.c.s(parcel, 23, this.z0, false);
        ax.p281r6.c.q(parcel, 24, this.A0, false);
        ax.p281r6.c.q(parcel, 25, this.B0, false);
        ax.p281r6.c.q(parcel, 26, this.C0, false);
        ax.p281r6.c.q(parcel, 27, this.D0, false);
        ax.p281r6.c.c(parcel, 28, this.E0);
        ax.p281r6.c.n(parcel, 29, this.F0);
        ax.p281r6.c.k(parcel, 30, this.G0);
        ax.p281r6.c.q(parcel, 31, this.H0, false);
        ax.p281r6.c.k(parcel, 32, this.I0);
        ax.p281r6.c.n(parcel, 34, this.J0);
        ax.p281r6.c.q(parcel, 35, this.K0, false);
        ax.p281r6.c.q(parcel, 36, this.L0, false);
        ax.p281r6.c.b(parcel, iA);
    }
}
