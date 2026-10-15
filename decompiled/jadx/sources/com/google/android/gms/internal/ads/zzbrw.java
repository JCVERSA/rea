package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import ax.P5.v;
import ax.T5.G0;
import ax.U5.p;
import ax.W5.e;
import ax.W5.l;
import ax.p402z.d;
import ax.p409z6.C4209hg;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class zzbrw implements MediationInterstitialAdapter {
    private Activity a;
    private l b;
    private Uri c;

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void onDestroy() {
        p.b("Destroying AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void onPause() {
        p.b("Pausing AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public final void onResume() {
        p.b("Resuming AdMobCustomTabsAdapter adapter.");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void requestInterstitialAd(Context context, l lVar, Bundle bundle, e eVar, Bundle bundle2) {
        this.b = lVar;
        if (lVar == null) {
            p.g("Listener not set for mediation. Returning.");
            return;
        }
        if (!(context instanceof Activity)) {
            p.g("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.b.c(this, 0);
            return;
        }
        if (!C4209hg.g(context)) {
            p.g("Default browser does not support custom tabs. Bailing out.");
            this.b.c(this, 0);
            return;
        }
        String string = bundle.getString("tab_url");
        if (TextUtils.isEmpty(string)) {
            p.g("The tab_url retrieved from mediation metadata is empty. Bailing out.");
            this.b.c(this, 0);
        } else {
            this.a = (Activity) context;
            this.c = Uri.parse(string);
            this.b.m(this);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public final void showInterstitial() {
        d dVarA = new d.C0519d().a();
        dVarA.a.setData(this.c);
        G0.l.post(new b(this, new AdOverlayInfoParcel(new ax.S5.l(dVarA.a, null), null, new a(this), null, new ax.U5.a(0, 0, false), null, null, HttpUrl.FRAGMENT_ENCODE_SET)));
        v.s().r();
    }
}
