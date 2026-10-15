package com.google.android.gms.ads.mediation.rtb;

import android.os.RemoteException;
import ax.I5.C1102b;
import ax.W5.a;
import ax.W5.d;
import ax.W5.g;
import ax.W5.h;
import ax.W5.k;
import ax.W5.m;
import ax.W5.o;
import ax.W5.s;
import ax.Y5.b;

/* JADX INFO: loaded from: classes3.dex */
public abstract class RtbAdapter extends a {
    public abstract void collectSignals(ax.Y5.a aVar, b bVar);

    public void loadRtbAppOpenAd(g gVar, d<Object, Object> dVar) {
    }

    public void loadRtbBannerAd(h hVar, d<Object, Object> dVar) {
    }

    @Deprecated
    public void loadRtbInterscrollerAd(h hVar, d<Object, Object> dVar) {
        dVar.a(new C1102b(7, getClass().getSimpleName().concat(" does not support interscroller ads."), "com.google.android.gms.ads"));
    }

    public void loadRtbInterstitialAd(k kVar, d<Object, Object> dVar) {
    }

    @Deprecated
    public void loadRtbNativeAd(m mVar, d<s, Object> dVar) {
    }

    public void loadRtbNativeAdMapper(m mVar, d<Object, Object> dVar) throws RemoteException {
    }

    public void loadRtbRewardedAd(o oVar, d<Object, Object> dVar) {
    }

    public void loadRtbRewardedInterstitialAd(o oVar, d<Object, Object> dVar) {
    }
}
