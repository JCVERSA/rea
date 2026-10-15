package com.pcloud.sdk;

import android.os.Parcel;
import android.os.Parcelable;
import j$.util.DesugarCollections;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class b implements Parcelable {
    public final String X;
    public final Set<String> Y;
    public final List<String> Z;
    public final boolean k0;
    public final c q;
    public static final List<String> l0 = DesugarCollections.unmodifiableList(Arrays.asList("com.android.chrome", "com.chrome.beta", "com.chrome.dev", "org.mozilla.firefox", "org.mozilla.firefox_beta", "org.mozilla.fenix"));
    public static final Parcelable.Creator<b> CREATOR = new a();

    class a implements Parcelable.Creator<b> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    }

    /* JADX INFO: renamed from: com.pcloud.sdk.b$b, reason: collision with other inner class name */
    public static class C0590b {
        private c a;
        private String b;
        private boolean e = false;
        private final List<String> d = new ArrayList(b.l0);
        private final Set<String> c = new HashSet();

        C0590b() {
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0590b c0590b = (C0590b) obj;
            if (this.e == c0590b.e && this.a == c0590b.a && Objects.equals(this.b, c0590b.b)) {
                return this.c.equals(c0590b.c);
            }
            return false;
        }

        public C0590b f(String str) {
            this.c.add(str);
            return this;
        }

        public b g() {
            if (this.a == null) {
                throw new IllegalStateException("setType() not called.");
            }
            if (this.b != null) {
                return new b(this);
            }
            throw new IllegalStateException("setClientId() not called.");
        }

        public C0590b h(String str) {
            this.b = str;
            return this;
        }

        public int hashCode() {
            c cVar = this.a;
            int iHashCode = (cVar != null ? cVar.hashCode() : 0) * 31;
            String str = this.b;
            return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.c.hashCode()) * 31) + (this.e ? 1 : 0);
        }

        public C0590b i(boolean z) {
            this.e = z;
            return this;
        }

        public C0590b j(c cVar) {
            this.a = cVar;
            return this;
        }

        public String toString() {
            return "Builder{type=" + this.a + ", clientId='" + this.b + "', permissions=" + this.c + ", customTabsPackages=" + this.d + ", forceAccessApproval=" + this.e + '}';
        }
    }

    public enum c {
        CODE(0),
        TOKEN(1);

        protected final int q;

        c(int i) {
            this.q = i;
        }

        static c h(int i) {
            if (i == 0) {
                return CODE;
            }
            if (i == 1) {
                return TOKEN;
            }
            throw new IllegalArgumentException();
        }
    }

    protected b(Parcel parcel) {
        this.q = c.h(parcel.readByte());
        this.X = parcel.readString();
        int i = parcel.readInt();
        if (i > 0) {
            HashSet hashSet = new HashSet(i);
            for (int i2 = 0; i2 < i; i2++) {
                hashSet.add(parcel.readString());
            }
            this.Y = DesugarCollections.unmodifiableSet(hashSet);
        } else {
            this.Y = Collections.EMPTY_SET;
        }
        int i3 = parcel.readInt();
        if (i3 > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i4 = 0; i4 < i3; i4++) {
                arrayList.add(parcel.readString());
            }
            this.Z = DesugarCollections.unmodifiableList(arrayList);
        } else {
            this.Z = Collections.EMPTY_LIST;
        }
        this.k0 = parcel.readByte() != 0;
    }

    b(C0590b c0590b) {
        this.q = c0590b.a;
        this.X = c0590b.b;
        this.Y = DesugarCollections.unmodifiableSet(c0590b.c);
        this.Z = DesugarCollections.unmodifiableList(c0590b.d);
        this.k0 = c0590b.e;
    }

    public static C0590b a() {
        return new C0590b();
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
        b bVar = (b) obj;
        if (this.k0 == bVar.k0 && this.q == bVar.q && this.X.equals(bVar.X)) {
            return this.Y.equals(bVar.Y);
        }
        return false;
    }

    public int hashCode() {
        return (((((this.q.hashCode() * 31) + this.X.hashCode()) * 31) + this.Y.hashCode()) * 31) + (this.k0 ? 1 : 0);
    }

    public String toString() {
        return "AuthorizationRequest{type=" + this.q + ", clientId='" + this.X + "', permissions=" + this.Y + ", customTabsPackages=" + this.Z + ", forceAccessApproval=" + this.k0 + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) this.q.q);
        parcel.writeString(this.X);
        parcel.writeInt(this.Y.size());
        Iterator<String> it = this.Y.iterator();
        while (it.hasNext()) {
            parcel.writeString(it.next());
        }
        parcel.writeInt(this.Z.size());
        Iterator<String> it2 = this.Z.iterator();
        while (it2.hasNext()) {
            parcel.writeString(it2.next());
        }
        parcel.writeByte(this.k0 ? (byte) 1 : (byte) 0);
    }
}
