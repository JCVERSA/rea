package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import ax.p265q6.C2254p;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6439z extends C1 {
    private final Map<String, Long> b;
    private final Map<String, Integer> c;
    private long d;

    public C6439z(E2 e2) {
        super(e2);
        this.c = new ax.B.a();
        this.b = new ax.B.a();
    }

    private final void A(String str, long j, C6303d4 c6303d4) {
        if (c6303d4 == null) {
            j().K().a("Not logging ad unit exposure. No active activity");
            return;
        }
        if (j < 1000) {
            j().K().b("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("_ai", str);
        bundle.putLong("_xt", j);
        B5.X(c6303d4, bundle, true);
        r().Y0("am", "_xu", bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void B(long j) {
        Iterator<String> it = this.b.keySet().iterator();
        while (it.hasNext()) {
            this.b.put(it.next(), Long.valueOf(j));
        }
        if (this.b.isEmpty()) {
            return;
        }
        this.d = j;
    }

    static /* synthetic */ void C(C6439z c6439z, String str, long j) {
        c6439z.n();
        C2254p.f(str);
        Integer num = c6439z.c.get(str);
        if (num == null) {
            c6439z.j().G().b("Call to endAdUnitExposure for unknown ad unit id", str);
            return;
        }
        C6303d4 c6303d4C = c6439z.s().C(false);
        int iIntValue = num.intValue() - 1;
        if (iIntValue != 0) {
            c6439z.c.put(str, Integer.valueOf(iIntValue));
            return;
        }
        c6439z.c.remove(str);
        Long l = c6439z.b.get(str);
        if (l == null) {
            c6439z.j().G().a("First ad unit exposure time was never set");
        } else {
            long jLongValue = j - l.longValue();
            c6439z.b.remove(str);
            c6439z.A(str, jLongValue, c6303d4C);
        }
        if (c6439z.c.isEmpty()) {
            long j2 = c6439z.d;
            if (j2 == 0) {
                c6439z.j().G().a("First ad exposure time was never set");
            } else {
                c6439z.w(j - j2, c6303d4C);
                c6439z.d = 0L;
            }
        }
    }

    private final void w(long j, C6303d4 c6303d4) {
        if (c6303d4 == null) {
            j().K().a("Not logging ad exposure. No active activity");
            return;
        }
        if (j < 1000) {
            j().K().b("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putLong("_xt", j);
        B5.X(c6303d4, bundle, true);
        r().Y0("am", "_xa", bundle);
    }

    static /* synthetic */ void y(C6439z c6439z, String str, long j) {
        c6439z.n();
        C2254p.f(str);
        if (c6439z.c.isEmpty()) {
            c6439z.d = j;
        }
        Integer num = c6439z.c.get(str);
        if (num != null) {
            c6439z.c.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (c6439z.c.size() >= 100) {
            c6439z.j().L().a("Too many ads visible");
        } else {
            c6439z.c.put(str, 1);
            c6439z.b.put(str, Long.valueOf(j));
        }
    }

    public final void D(String str, long j) {
        if (str == null || str.length() == 0) {
            j().G().a("Ad unit id must be a non-empty string");
        } else {
            l().D(new A0(this, str, j));
        }
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

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6439z o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ Q1 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ P1 q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6351k3 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6317f4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6352k4 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ V4 u() {
        return super.u();
    }

    public final void v(long j) {
        C6303d4 c6303d4C = s().C(false);
        for (String str : this.b.keySet()) {
            A(str, j - this.b.get(str).longValue(), c6303d4C);
        }
        if (!this.b.isEmpty()) {
            w(j - this.d, c6303d4C);
        }
        B(j);
    }

    public final void z(String str, long j) {
        if (str == null || str.length() == 0) {
            j().G().a("Ad unit id must be a non-empty string");
        } else {
            l().D(new RunnableC6277a(this, str, j));
        }
    }
}
