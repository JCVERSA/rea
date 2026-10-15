package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes3.dex */
final class m implements Comparable<m>, Parcelable {
    public static final Parcelable.Creator<m> CREATOR = new a();
    final int X;
    final int Y;
    final int Z;
    final int k0;
    final long l0;
    private String m0;
    private final Calendar q;

    class a implements Parcelable.Creator<m> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public m createFromParcel(Parcel parcel) {
            return m.k(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public m[] newArray(int i) {
            return new m[i];
        }
    }

    private m(Calendar calendar) {
        calendar.set(5, 1);
        Calendar calendarF = t.f(calendar);
        this.q = calendarF;
        this.X = calendarF.get(2);
        this.Y = calendarF.get(1);
        this.Z = calendarF.getMaximum(7);
        this.k0 = calendarF.getActualMaximum(5);
        this.l0 = calendarF.getTimeInMillis();
    }

    static m B(long j) {
        Calendar calendarR = t.r();
        calendarR.setTimeInMillis(j);
        return new m(calendarR);
    }

    static m C() {
        return new m(t.p());
    }

    static m k(int i, int i2) {
        Calendar calendarR = t.r();
        calendarR.set(1, i);
        calendarR.set(2, i2);
        return new m(calendarR);
    }

    int E(int i) {
        int i2 = this.q.get(7);
        if (i <= 0) {
            i = this.q.getFirstDayOfWeek();
        }
        int i3 = i2 - i;
        return i3 < 0 ? i3 + this.Z : i3;
    }

    long F(int i) {
        Calendar calendarF = t.f(this.q);
        calendarF.set(5, i);
        return calendarF.getTimeInMillis();
    }

    int G(long j) {
        Calendar calendarF = t.f(this.q);
        calendarF.setTimeInMillis(j);
        return calendarF.get(5);
    }

    String H() {
        if (this.m0 == null) {
            this.m0 = g.l(this.q.getTimeInMillis());
        }
        return this.m0;
    }

    long I() {
        return this.q.getTimeInMillis();
    }

    m J(int i) {
        Calendar calendarF = t.f(this.q);
        calendarF.add(2, i);
        return new m(calendarF);
    }

    int K(m mVar) {
        if (this.q instanceof GregorianCalendar) {
            return ((mVar.Y - this.Y) * 12) + (mVar.X - this.X);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof m)) {
            return false;
        }
        m mVar = (m) obj;
        return this.X == mVar.X && this.Y == mVar.Y;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public int compareTo(m mVar) {
        return this.q.compareTo(mVar.q);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.X), Integer.valueOf(this.Y)});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Y);
        parcel.writeInt(this.X);
    }
}
