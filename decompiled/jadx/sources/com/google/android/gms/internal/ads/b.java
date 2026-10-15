package com.google.android.gms.internal.ads;

import ax.P5.v;
import ax.S5.y;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;

/* JADX INFO: loaded from: classes3.dex */
final class b implements Runnable {
    final /* synthetic */ zzbrw X;
    final /* synthetic */ AdOverlayInfoParcel q;

    b(zzbrw zzbrwVar, AdOverlayInfoParcel adOverlayInfoParcel) {
        this.q = adOverlayInfoParcel;
        this.X = zzbrwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        v.m();
        y.a(this.X.a, this.q, true, null);
    }
}
