package com.google.firebase.analytics;

import android.os.Bundle;
import ax.H6.W0;
import ax.M6.y;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class a implements y {
    private final /* synthetic */ W0 a;

    a(W0 w0) {
        this.a = w0;
    }

    @Override // ax.M6.y
    public final void E(String str) {
        this.a.G(str);
    }

    @Override // ax.M6.y
    public final void a(String str, String str2, Bundle bundle) {
        this.a.u(str, str2, bundle);
    }

    @Override // ax.M6.y
    public final List<Bundle> c(String str, String str2) {
        return this.a.h(str, str2);
    }

    @Override // ax.M6.y
    public final Map<String, Object> d(String str, String str2, boolean z) {
        return this.a.i(str, str2, z);
    }

    @Override // ax.M6.y
    public final long e() {
        return this.a.b();
    }

    @Override // ax.M6.y
    public final void f(String str, String str2, Bundle bundle) {
        this.a.E(str, str2, bundle);
    }

    @Override // ax.M6.y
    public final String g() {
        return this.a.L();
    }

    @Override // ax.M6.y
    public final String h() {
        return this.a.K();
    }

    @Override // ax.M6.y
    public final String i() {
        return this.a.M();
    }

    @Override // ax.M6.y
    public final String j() {
        return this.a.N();
    }

    @Override // ax.M6.y
    public final void l0(Bundle bundle) {
        this.a.l(bundle);
    }

    @Override // ax.M6.y
    public final int o(String str) {
        return this.a.a(str);
    }

    @Override // ax.M6.y
    public final void x(String str) {
        this.a.C(str);
    }
}
