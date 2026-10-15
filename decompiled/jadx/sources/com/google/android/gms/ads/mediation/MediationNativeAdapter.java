package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.os.Bundle;
import ax.W5.f;
import ax.W5.n;
import ax.W5.p;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public interface MediationNativeAdapter extends f {
    /* synthetic */ void onDestroy();

    /* synthetic */ void onPause();

    /* synthetic */ void onResume();

    void requestNativeAd(Context context, n nVar, Bundle bundle, p pVar, Bundle bundle2);
}
