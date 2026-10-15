package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import ax.p265q6.C2254p;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.k2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6350k2 {
    private final String a;
    private final String b;
    private final String c;
    private final long d;
    private final /* synthetic */ C6322g2 e;

    private C6350k2(C6322g2 c6322g2, String str, long j) {
        this.e = c6322g2;
        C2254p.f(str);
        C2254p.a(j > 0);
        this.a = str + ":start";
        this.b = str + ":count";
        this.c = str + ":value";
        this.d = j;
    }

    private final long c() {
        return this.e.J().getLong(this.a, 0L);
    }

    private final void d() {
        this.e.n();
        long jA = this.e.b().a();
        SharedPreferences.Editor editorEdit = this.e.J().edit();
        editorEdit.remove(this.b);
        editorEdit.remove(this.c);
        editorEdit.putLong(this.a, jA);
        editorEdit.apply();
    }

    public final Pair<String, Long> a() {
        long jAbs;
        this.e.n();
        this.e.n();
        long jC = c();
        if (jC == 0) {
            d();
            jAbs = 0;
        } else {
            jAbs = Math.abs(jC - this.e.b().a());
        }
        long j = this.d;
        if (jAbs < j) {
            return null;
        }
        if (jAbs > (j << 1)) {
            d();
            return null;
        }
        String string = this.e.J().getString(this.c, null);
        long j2 = this.e.J().getLong(this.b, 0L);
        d();
        return (string == null || j2 <= 0) ? C6322g2.B : new Pair<>(string, Long.valueOf(j2));
    }

    public final void b(String str, long j) {
        this.e.n();
        if (c() == 0) {
            d();
        }
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        long j2 = this.e.J().getLong(this.b, 0L);
        if (j2 <= 0) {
            SharedPreferences.Editor editorEdit = this.e.J().edit();
            editorEdit.putString(this.c, str);
            editorEdit.putLong(this.b, 1L);
            editorEdit.apply();
            return;
        }
        long j3 = j2 + 1;
        boolean z = (this.e.i().W0().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / j3;
        SharedPreferences.Editor editorEdit2 = this.e.J().edit();
        if (z) {
            editorEdit2.putString(this.c, str);
        }
        editorEdit2.putLong(this.b, j3);
        editorEdit2.apply();
    }
}
