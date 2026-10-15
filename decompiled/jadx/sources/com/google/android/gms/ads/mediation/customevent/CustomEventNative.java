package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import ax.W5.p;
import ax.X5.d;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface CustomEventNative extends ax.X5.a {
    /* synthetic */ void onDestroy();

    /* synthetic */ void onPause();

    /* synthetic */ void onResume();

    void requestNativeAd(Context context, d dVar, String str, p pVar, Bundle bundle);
}
