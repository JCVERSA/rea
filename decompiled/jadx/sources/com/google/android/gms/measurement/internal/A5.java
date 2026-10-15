package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
public final class A5 extends ax.p281r6.a {
    public static final Parcelable.Creator<A5> CREATOR = new z5();
    public final String X;
    public final long Y;
    public final Long Z;
    public final String k0;
    public final String l0;
    public final Double m0;
    private final int q;

    A5(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        this.q = i;
        this.X = str;
        this.Y = j;
        this.Z = l;
        if (i == 1) {
            this.m0 = f != null ? Double.valueOf(f.doubleValue()) : null;
        } else {
            this.m0 = d;
        }
        this.k0 = str2;
        this.l0 = str3;
    }

    A5(C5 c5) {
        this(c5.c, c5.d, c5.e, c5.b);
    }

    A5(String str, long j, Object obj, String str2) {
        C2254p.f(str);
        this.q = 2;
        this.X = str;
        this.Y = j;
        this.l0 = str2;
        if (obj == null) {
            this.Z = null;
            this.m0 = null;
            this.k0 = null;
            return;
        }
        if (obj instanceof Long) {
            this.Z = (Long) obj;
            this.m0 = null;
            this.k0 = null;
        } else if (obj instanceof String) {
            this.Z = null;
            this.m0 = null;
            this.k0 = (String) obj;
        } else {
            if (!(obj instanceof Double)) {
                throw new IllegalArgumentException("User attribute given of un-supported type");
            }
            this.Z = null;
            this.m0 = (Double) obj;
            this.k0 = null;
        }
    }

    public final Object a() {
        Long l = this.Z;
        if (l != null) {
            return l;
        }
        Double d = this.m0;
        if (d != null) {
            return d;
        }
        String str = this.k0;
        if (str != null) {
            return str;
        }
        return null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.k(parcel, 1, this.q);
        ax.p281r6.c.q(parcel, 2, this.X, false);
        ax.p281r6.c.n(parcel, 3, this.Y);
        ax.p281r6.c.o(parcel, 4, this.Z, false);
        ax.p281r6.c.i(parcel, 5, null, false);
        ax.p281r6.c.q(parcel, 6, this.k0, false);
        ax.p281r6.c.q(parcel, 7, this.l0, false);
        ax.p281r6.c.g(parcel, 8, this.m0, false);
        ax.p281r6.c.b(parcel, iA);
    }
}
