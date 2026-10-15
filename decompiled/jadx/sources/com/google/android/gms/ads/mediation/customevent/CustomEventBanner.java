package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import ax.I5.h;
import ax.W5.e;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface CustomEventBanner extends ax.X5.a {
    /* synthetic */ void onDestroy();

    /* synthetic */ void onPause();

    /* synthetic */ void onResume();

    void requestBannerAd(Context context, ax.X5.b bVar, String str, h hVar, e eVar, Bundle bundle);
}
