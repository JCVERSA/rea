package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class C extends ax.p281r6.a implements Iterable<String> {
    public static final Parcelable.Creator<C> CREATOR = new E();
    private final Bundle q;

    C(Bundle bundle) {
        this.q = bundle;
    }

    public final Bundle A() {
        return new Bundle(this.q);
    }

    final Long E(String str) {
        return Long.valueOf(this.q.getLong(str));
    }

    final Object H(String str) {
        return this.q.get(str);
    }

    final String J(String str) {
        return this.q.getString(str);
    }

    @Override // java.lang.Iterable
    public final Iterator<String> iterator() {
        return new B(this);
    }

    public final int j() {
        return this.q.size();
    }

    public final String toString() {
        return this.q.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iA = ax.p281r6.c.a(parcel);
        ax.p281r6.c.e(parcel, 2, A(), false);
        ax.p281r6.c.b(parcel, iA);
    }

    final Double z(String str) {
        return Double.valueOf(this.q.getDouble(str));
    }
}
