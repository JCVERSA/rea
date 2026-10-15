package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;
import ax.W5.e;
import ax.W5.f;
import ax.W5.l;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface MediationInterstitialAdapter extends f {
    /* synthetic */ void onDestroy();

    /* synthetic */ void onPause();

    /* synthetic */ void onResume();

    void requestInterstitialAd(Context context, l lVar, Bundle bundle, e eVar, Bundle bundle2);

    void showInterstitial();
}
