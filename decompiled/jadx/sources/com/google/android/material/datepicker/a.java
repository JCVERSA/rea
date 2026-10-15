package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import j$.util.Objects;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new C0574a();
    private final m X;
    private final c Y;
    private m Z;
    private final int k0;
    private final int l0;
    private final int m0;
    private final m q;

    /* JADX INFO: renamed from: com.google.android.material.datepicker.a$a, reason: collision with other inner class name */
    class C0574a implements Parcelable.Creator<a> {
        C0574a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a((m) parcel.readParcelable(m.class.getClassLoader()), (m) parcel.readParcelable(m.class.getClassLoader()), (c) parcel.readParcelable(c.class.getClassLoader()), (m) parcel.readParcelable(m.class.getClassLoader()), parcel.readInt(), null);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    }

    public static final class b {
        static final long f = t.a(m.k(1900, 0).l0);
        static final long g = t.a(m.k(2100, 11).l0);
        private long a;
        private long b;
        private Long c;
        private int d;
        private c e;

        public b() {
            this.a = f;
            this.b = g;
            this.e = h.a(Long.MIN_VALUE);
        }

        b(a aVar) {
            this.a = f;
            this.b = g;
            this.e = h.a(Long.MIN_VALUE);
            this.a = aVar.q.l0;
            this.b = aVar.X.l0;
            this.c = Long.valueOf(aVar.Z.l0);
            this.d = aVar.k0;
            this.e = aVar.Y;
        }

        public a a() {
            Bundle bundle = new Bundle();
            bundle.putParcelable("DEEP_COPY_VALIDATOR_KEY", this.e);
            m mVarB = m.B(this.a);
            m mVarB2 = m.B(this.b);
            c cVar = (c) bundle.getParcelable("DEEP_COPY_VALIDATOR_KEY");
            Long l = this.c;
            return new a(mVarB, mVarB2, cVar, l == null ? null : m.B(l.longValue()), this.d, null);
        }

        public b b(long j) {
            this.c = Long.valueOf(j);
            return this;
        }
    }

    public interface c extends Parcelable {
        boolean v(long j);
    }

    private a(m mVar, m mVar2, c cVar, m mVar3, int i) {
        Objects.requireNonNull(mVar, "start cannot be null");
        Objects.requireNonNull(mVar2, "end cannot be null");
        Objects.requireNonNull(cVar, "validator cannot be null");
        this.q = mVar;
        this.X = mVar2;
        this.Z = mVar3;
        this.k0 = i;
        this.Y = cVar;
        if (mVar3 != null && mVar.compareTo(mVar3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (mVar3 != null && mVar3.compareTo(mVar2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        if (i < 0 || i > t.r().getMaximum(7)) {
            throw new IllegalArgumentException("firstDayOfWeek is not valid");
        }
        this.m0 = mVar.K(mVar2) + 1;
        this.l0 = (mVar2.Y - mVar.Y) + 1;
    }

    /* synthetic */ a(m mVar, m mVar2, c cVar, m mVar3, int i, C0574a c0574a) {
        this(mVar, mVar2, cVar, mVar3, i);
    }

    void A(m mVar) {
        this.Z = mVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.q.equals(aVar.q) && this.X.equals(aVar.X) && ax.p019b0.b.a(this.Z, aVar.Z) && this.k0 == aVar.k0 && this.Y.equals(aVar.Y);
    }

    m f(m mVar) {
        if (mVar.compareTo(this.q) < 0) {
            return this.q;
        }
        return mVar.compareTo(this.X) > 0 ? this.X : mVar;
    }

    public c h() {
        return this.Y;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.q, this.X, this.Z, Integer.valueOf(this.k0), this.Y});
    }

    m i() {
        return this.X;
    }

    int j() {
        return this.k0;
    }

    int k() {
        return this.m0;
    }

    m l() {
        return this.Z;
    }

    m n() {
        return this.q;
    }

    int r() {
        return this.l0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.q, 0);
        parcel.writeParcelable(this.X, 0);
        parcel.writeParcelable(this.Z, 0);
        parcel.writeParcelable(this.Y, 0);
        parcel.writeInt(this.k0);
    }

    boolean z(long j) {
        if (this.q.F(1) > j) {
            return false;
        }
        m mVar = this.X;
        return j <= mVar.F(mVar.k0);
    }
}
