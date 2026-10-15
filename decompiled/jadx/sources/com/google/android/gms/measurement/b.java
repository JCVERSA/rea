package com.google.android.gms.measurement;

import android.os.Bundle;
import ax.p265q6.C2254p;
import com.google.android.gms.measurement.internal.C6351k3;
import com.google.android.gms.measurement.internal.E2;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class b extends AppMeasurement.a {
    private final E2 a;
    private final C6351k3 b;

    public b(E2 e2) {
        super();
        C2254p.l(e2);
        this.a = e2;
        this.b = e2.H();
    }

    @Override // ax.M6.y
    public final void E(String str) {
        this.a.y().D(str, this.a.b().b());
    }

    @Override // ax.M6.y
    public final void a(String str, String str2, Bundle bundle) {
        this.a.H().g0(str, str2, bundle);
    }

    @Override // ax.M6.y
    public final List<Bundle> c(String str, String str2) {
        return this.b.F(str, str2);
    }

    @Override // ax.M6.y
    public final Map<String, Object> d(String str, String str2, boolean z) {
        return this.b.G(str, str2, z);
    }

    @Override // ax.M6.y
    public final long e() {
        return this.a.L().R0();
    }

    @Override // ax.M6.y
    public final void f(String str, String str2, Bundle bundle) {
        this.b.R0(str, str2, bundle);
    }

    @Override // ax.M6.y
    public final String g() {
        return this.b.v0();
    }

    @Override // ax.M6.y
    public final String h() {
        return this.b.u0();
    }

    @Override // ax.M6.y
    public final String i() {
        return this.b.w0();
    }

    @Override // ax.M6.y
    public final String j() {
        return this.b.u0();
    }

    @Override // ax.M6.y
    public final void l0(Bundle bundle) {
        this.b.K0(bundle);
    }

    @Override // ax.M6.y
    public final int o(String str) {
        return C6351k3.D(str);
    }

    @Override // ax.M6.y
    public final void x(String str) {
        this.a.y().z(str, this.a.b().b());
    }
}
