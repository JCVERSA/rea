package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import ax.I5.h;
import ax.W5.e;
import ax.W5.f;
import ax.W5.i;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface MediationBannerAdapter extends f {
    View getBannerView();

    @Override // ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    /* synthetic */ void onDestroy();

    @Override // ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    /* synthetic */ void onPause();

    @Override // ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    /* synthetic */ void onResume();

    void requestBannerAd(Context context, i iVar, Bundle bundle, h hVar, e eVar, Bundle bundle2);
}
