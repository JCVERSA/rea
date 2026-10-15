package com.pcloud.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import ax.Sa.h;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0589a();
    public final h X;
    public final String Y;
    public final long Z;
    public final long k0;
    public final String l0;
    public final String m0;
    public final String n0;
    public final b q;

    /* JADX INFO: renamed from: com.pcloud.sdk.a$a, reason: collision with other inner class name */
    class C0589a implements Parcelable.Creator<a> {
        C0589a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    protected a(Parcel parcel) {
        this.q = (b) parcel.readParcelable(b.class.getClassLoader());
        this.X = (h) parcel.readSerializable();
        this.Y = parcel.readString();
        this.Z = parcel.readLong();
        this.k0 = parcel.readLong();
        this.l0 = parcel.readString();
        this.m0 = parcel.readString();
        this.n0 = parcel.readString();
    }

    a(b bVar, h hVar, String str, long j, long j2, String str2, String str3, String str4) {
        this.q = bVar;
        this.X = hVar;
        this.Y = str;
        this.Z = j;
        this.k0 = j2;
        this.l0 = str2;
        this.m0 = str3;
        this.n0 = str4;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        a aVar = (a) obj;
        if (this.Z == aVar.Z && this.k0 == aVar.k0 && this.q.equals(aVar.q) && this.X == aVar.X && Objects.equals(this.Y, aVar.Y) && Objects.equals(this.l0, aVar.l0) && Objects.equals(this.m0, aVar.m0)) {
            return Objects.equals(this.n0, aVar.n0);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((this.q.hashCode() * 31) + this.X.hashCode()) * 31;
        String str = this.Y;
        int iHashCode2 = str != null ? str.hashCode() : 0;
        long j = this.Z;
        int i = (((iHashCode + iHashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        long j2 = this.k0;
        int i2 = (i + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        String str2 = this.l0;
        int iHashCode3 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.m0;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.n0;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "AuthorizationData{request=" + this.q + ", result=" + this.X + ", token='" + this.Y + "', userId=" + this.Z + ", locationId=" + this.k0 + ", authCode='" + this.l0 + "', apiHost='" + this.m0 + "', errorMessage='" + this.n0 + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.q, i);
        parcel.writeSerializable(this.X);
        parcel.writeString(this.Y);
        parcel.writeLong(this.Z);
        parcel.writeLong(this.k0);
        parcel.writeString(this.l0);
        parcel.writeString(this.m0);
        parcel.writeString(this.n0);
    }
}
