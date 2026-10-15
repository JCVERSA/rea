package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6433y {
    final String a;
    final String b;
    final long c;
    final long d;
    final long e;
    final long f;
    final long g;
    final Long h;
    final Long i;
    final Long j;
    final Boolean k;

    C6433y(String str, String str2, long j, long j2, long j3, long j4, long j5, Long l, Long l2, Long l3, Boolean bool) {
        C2254p.f(str);
        C2254p.f(str2);
        C2254p.a(j >= 0);
        C2254p.a(j2 >= 0);
        C2254p.a(j3 >= 0);
        C2254p.a(j5 >= 0);
        this.a = str;
        this.b = str2;
        this.c = j;
        this.d = j2;
        this.e = j3;
        this.f = j4;
        this.g = j5;
        this.h = l;
        this.i = l2;
        this.j = l3;
        this.k = bool;
    }

    C6433y(String str, String str2, long j, long j2, long j3, long j4, Long l, Long l2, Long l3, Boolean bool) {
        this(str, str2, 0L, 0L, 0L, j3, 0L, null, null, null, null);
    }

    final C6433y a(long j) {
        return new C6433y(this.a, this.b, this.c, this.d, this.e, j, this.g, this.h, this.i, this.j, this.k);
    }

    final C6433y b(long j, long j2) {
        return new C6433y(this.a, this.b, this.c, this.d, this.e, this.f, j, Long.valueOf(j2), this.i, this.j, this.k);
    }

    final C6433y c(Long l, Long l2, Boolean bool) {
        return new C6433y(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, l, l2, (bool == null || bool.booleanValue()) ? bool : null);
    }
}
