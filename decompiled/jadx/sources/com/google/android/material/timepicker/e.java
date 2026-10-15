package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
class e implements Parcelable {
    public static final Parcelable.Creator<e> CREATOR = new a();
    private final b X;
    final int Y;
    int Z;
    int k0;
    int l0;
    int m0;
    private final b q;

    class a implements Parcelable.Creator<e> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }
    }

    public e() {
        this(0);
    }

    public e(int i) {
        this(0, 0, 10, i);
    }

    public e(int i, int i2, int i3, int i4) {
        this.Z = i;
        this.k0 = i2;
        this.l0 = i3;
        this.Y = i4;
        this.m0 = c(i);
        this.q = new b(59);
        this.X = new b(i4 == 1 ? 23 : 12);
    }

    protected e(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static String a(Resources resources, CharSequence charSequence) {
        return b(resources, charSequence, "%02d");
    }

    public static String b(Resources resources, CharSequence charSequence, String str) {
        try {
            return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static int c(int i) {
        return i >= 12 ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        return this.Z == eVar.Z && this.k0 == eVar.k0 && this.Y == eVar.Y && this.l0 == eVar.l0;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.Y), Integer.valueOf(this.Z), Integer.valueOf(this.k0), Integer.valueOf(this.l0)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Z);
        parcel.writeInt(this.k0);
        parcel.writeInt(this.l0);
        parcel.writeInt(this.Y);
    }
}
