package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import ax.W5.e;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface CustomEventInterstitial extends ax.X5.a {
    /* synthetic */ void onDestroy();

    /* synthetic */ void onPause();

    /* synthetic */ void onResume();

    void requestInterstitialAd(Context context, ax.X5.c cVar, String str, e eVar, Bundle bundle);

    void showInterstitial();
}
