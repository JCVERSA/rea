package com.pcloud.sdk.internal;

/* JADX INFO: loaded from: classes3.dex */
class B implements ax.Sa.A {
    private final long a;
    private final String b;
    private final boolean c;
    private final long d;
    private final long e;

    B(long j, String str, boolean z, long j2, long j3) {
        this.a = j;
        this.b = str;
        this.c = z;
        this.d = j2;
        this.e = j3;
    }

    @Override // ax.Sa.A
    public long a() {
        return this.d;
    }

    @Override // ax.Sa.A
    public String b() {
        return this.b;
    }

    @Override // ax.Sa.A
    public long c() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        B b = (B) obj;
        if (this.a == b.a && this.c == b.c && this.d == b.d && this.e == b.e) {
            return this.b.equals(b.b);
        }
        return false;
    }

    public int hashCode() {
        long j = this.a;
        int iHashCode = ((((((int) (j ^ (j >>> 32))) * 31) + this.b.hashCode()) * 31) + (this.c ? 1 : 0)) * 31;
        long j2 = this.d;
        int i = (iHashCode + ((int) (j2 ^ (j2 >>> 32)))) * 31;
        long j3 = this.e;
        return i + ((int) (j3 ^ (j3 >>> 32)));
    }
}
