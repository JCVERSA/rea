package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class h implements com.google.android.material.datepicker.a.c {
    public static final Parcelable.Creator<h> CREATOR = new a();
    private final long q;

    class a implements Parcelable.Creator<h> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel.readLong(), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i) {
            return new h[i];
        }
    }

    private h(long j) {
        this.q = j;
    }

    /* synthetic */ h(long j, a aVar) {
        this(j);
    }

    public static h a(long j) {
        return new h(j);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof h) && this.q == ((h) obj).q;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.q)});
    }

    @Override // com.google.android.material.datepicker.a.c
    public boolean v(long j) {
        return j >= this.q;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.q);
    }
}
