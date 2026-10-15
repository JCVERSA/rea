package com.google.android.gms.internal.ads;

import ax.S5.z;
import ax.U5.p;

/* JADX INFO: loaded from: classes3.dex */
final class a implements z {
    final /* synthetic */ zzbrw q;

    a(zzbrw zzbrwVar) {
        this.q = zzbrwVar;
    }

    @Override // ax.S5.z
    public final void L3() {
        p.b("Opening AdMobCustomTabsAdapter overlay.");
        zzbrw zzbrwVar = this.q;
        zzbrwVar.b.s(zzbrwVar);
    }

    @Override // ax.S5.z
    public final void M0() {
    }

    @Override // ax.S5.z
    public final void N2() {
        p.b("Delay close AdMobCustomTabsAdapter overlay.");
    }

    @Override // ax.S5.z
    public final void P0() {
        p.b("AdMobCustomTabsAdapter overlay is paused.");
    }

    @Override // ax.S5.z
    public final void Y3() {
        p.b("AdMobCustomTabsAdapter overlay is resumed.");
    }

    @Override // ax.S5.z
    public final void s5(int i) {
        p.b("AdMobCustomTabsAdapter overlay is closed.");
        zzbrw zzbrwVar = this.q;
        zzbrwVar.b.p(zzbrwVar);
    }
}
