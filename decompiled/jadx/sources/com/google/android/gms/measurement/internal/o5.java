package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import ax.H6.t7;
import java.util.HashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class o5 extends C6360l5 {
    o5(q5 q5Var) {
        super(q5Var);
    }

    private final String v(String str) throws Throwable {
        String strR = r().R(str);
        if (TextUtils.isEmpty(strR)) {
            return F.r.a(null);
        }
        Uri uri = Uri.parse(F.r.a(null));
        Uri.Builder builderBuildUpon = uri.buildUpon();
        builderBuildUpon.authority(strR + "." + uri.getAuthority());
        return builderBuildUpon.build().toString();
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

    public final C6367m5 u(String str) {
        Z1 z1H0;
        if (t7.a() && d().t(F.t0)) {
            i();
            if (B5.H0(str)) {
                j().K().a("sgtm feature flag enabled.");
                Z1 z1H1 = q().H0(str);
                if (z1H1 == null) {
                    return new C6367m5(v(str), 1);
                }
                String strM = z1H1.m();
                ax.H6.N1 n1L = r().L(str);
                if (n1L == null || (z1H0 = q().H0(str)) == null || ((!n1L.e0() || n1L.U().n() != 100) && !i().E0(str, z1H0.v()) && (TextUtils.isEmpty(strM) || strM.hashCode() % 100 >= n1L.U().n()))) {
                    return new C6367m5(v(str), 1);
                }
                C6367m5 c6367m5 = null;
                if (z1H1.C()) {
                    j().K().a("sgtm upload enabled in manifest.");
                    ax.H6.N1 n1L2 = r().L(z1H1.l());
                    if (n1L2 != null && n1L2.e0()) {
                        String strM2 = n1L2.U().M();
                        if (!TextUtils.isEmpty(strM2)) {
                            String strL = n1L2.U().L();
                            j().K().c("sgtm configured with upload_url, server_info", strM2, TextUtils.isEmpty(strL) ? "Y" : "N");
                            if (TextUtils.isEmpty(strL)) {
                                c6367m5 = new C6367m5(strM2, 3);
                            } else {
                                HashMap map = new HashMap();
                                map.put("x-sgtm-server-info", strL);
                                if (!TextUtils.isEmpty(z1H1.v())) {
                                    map.put("x-gtm-server-preview", z1H1.v());
                                }
                                c6367m5 = new C6367m5(strM2, map, 3);
                            }
                        }
                    }
                }
                if (c6367m5 != null) {
                    return c6367m5;
                }
            }
        }
        return new C6367m5(v(str), 1);
    }
}
