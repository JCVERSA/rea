package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import ax.p217n6.C2172c;
import ax.p233o6.d;
import ax.p233o6.l;
import ax.p265q6.C2252n;
import ax.p281r6.c;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

/* JADX INFO: loaded from: classes3.dex */
public final class Status extends ax.p281r6.a implements l, ReflectedParcelable {
    private final String X;
    private final PendingIntent Y;
    private final C2172c Z;
    private final int q;
    public static final Status k0 = new Status(-1);
    public static final Status l0 = new Status(0);
    public static final Status m0 = new Status(14);
    public static final Status n0 = new Status(8);
    public static final Status o0 = new Status(15);
    public static final Status p0 = new Status(16);
    public static final Status r0 = new Status(17);
    public static final Status q0 = new Status(18);
    public static final Parcelable.Creator<Status> CREATOR = new b();

    public Status(int i) {
        this(i, (String) null);
    }

    public Status(int i, String str) {
        this(i, str, (PendingIntent) null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(i, str, pendingIntent, null);
    }

    Status(int i, String str, PendingIntent pendingIntent, C2172c c2172c) {
        this.q = i;
        this.X = str;
        this.Y = pendingIntent;
        this.Z = c2172c;
    }

    public Status(C2172c c2172c, String str) {
        this(c2172c, str, 17);
    }

    @Deprecated
    public Status(C2172c c2172c, String str, int i) {
        this(i, str, c2172c.z(), c2172c);
    }

    public boolean A() {
        return this.Y != null;
    }

    public boolean E() {
        return this.q <= 0;
    }

    public final String H() {
        String str = this.X;
        return str != null ? str : d.a(this.q);
    }

    @Override // ax.p233o6.l
    public Status e() {
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.q == status.q && C2252n.b(this.X, status.X) && C2252n.b(this.Y, status.Y) && C2252n.b(this.Z, status.Z);
    }

    public int hashCode() {
        return C2252n.c(Integer.valueOf(this.q), this.X, this.Y, this.Z);
    }

    public C2172c j() {
        return this.Z;
    }

    @ResultIgnorabilityUnspecified
    public int n() {
        return this.q;
    }

    public String toString() {
        C2252n.a aVarD = C2252n.d(this);
        aVarD.a("statusCode", H());
        aVarD.a("resolution", this.Y);
        return aVarD.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iA = c.a(parcel);
        c.k(parcel, 1, n());
        c.q(parcel, 2, z(), false);
        c.p(parcel, 3, this.Y, i, false);
        c.p(parcel, 4, j(), i, false);
        c.b(parcel, iA);
    }

    public String z() {
        return this.X;
    }
}
