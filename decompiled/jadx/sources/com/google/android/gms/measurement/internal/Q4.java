package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Pair;
import j$.util.Objects;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class Q4 extends AbstractC6353k5 {
    private final Map<String, P4> d;
    public final C6357l2 e;
    public final C6357l2 f;
    public final C6357l2 g;
    public final C6357l2 h;
    public final C6357l2 i;

    Q4(q5 q5Var) {
        super(q5Var);
        this.d = new HashMap();
        C6322g2 c6322g2H = h();
        Objects.requireNonNull(c6322g2H);
        this.e = new C6357l2(c6322g2H, "last_delete_stale", 0L);
        C6322g2 c6322g2H2 = h();
        Objects.requireNonNull(c6322g2H2);
        this.f = new C6357l2(c6322g2H2, "backoff", 0L);
        C6322g2 c6322g2H3 = h();
        Objects.requireNonNull(c6322g2H3);
        this.g = new C6357l2(c6322g2H3, "last_upload", 0L);
        C6322g2 c6322g2H4 = h();
        Objects.requireNonNull(c6322g2H4);
        this.h = new C6357l2(c6322g2H4, "last_upload_attempt", 0L);
        C6322g2 c6322g2H5 = h();
        Objects.requireNonNull(c6322g2H5);
        this.i = new C6357l2(c6322g2H5, "midnight_offset", 0L);
    }

    @Deprecated
    private final Pair<String, Boolean> y(String str) {
        P4 p4;
        ax.M5.a.C0148a c0148aA;
        n();
        long jB = b().b();
        P4 p5 = this.d.get(str);
        if (p5 != null && jB < p5.c) {
            return new Pair<>(p5.a, Boolean.valueOf(p5.b));
        }
        ax.M5.a.d(true);
        long jB2 = d().B(str) + jB;
        try {
            try {
                c0148aA = ax.M5.a.a(a());
            } catch (PackageManager.NameNotFoundException unused) {
                if (p5 != null && jB < p5.c + d().z(str, F.c)) {
                    return new Pair<>(p5.a, Boolean.valueOf(p5.b));
                }
                c0148aA = null;
            }
            if (c0148aA == null) {
                return new Pair<>("00000000-0000-0000-0000-000000000000", Boolean.FALSE);
            }
            String strA = c0148aA.a();
            p4 = strA != null ? new P4(strA, c0148aA.b(), jB2) : new P4(HttpUrl.FRAGMENT_ENCODE_SET, c0148aA.b(), jB2);
            this.d.put(str, p4);
            ax.M5.a.d(false);
            return new Pair<>(p4.a, Boolean.valueOf(p4.b));
        } catch (Exception e) {
            j().F().b("Unable to get advertising id", e);
            p4 = new P4(HttpUrl.FRAGMENT_ENCODE_SET, false, jB2);
        }
    }

    @Deprecated
    final String A(String str, boolean z) {
        n();
        String str2 = z ? (String) y(str).first : "00000000-0000-0000-0000-000000000000";
        MessageDigest messageDigestV0 = B5.V0();
        if (messageDigestV0 == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestV0.digest(str2.getBytes())));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ x5 o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ J5 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6354l q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6389q2 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ Q4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ o5 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    final Pair<String, Boolean> z(String str, C6330h3 c6330h3) {
        return c6330h3.A() ? y(str) : new Pair<>(HttpUrl.FRAGMENT_ENCODE_SET, Boolean.FALSE);
    }
}
