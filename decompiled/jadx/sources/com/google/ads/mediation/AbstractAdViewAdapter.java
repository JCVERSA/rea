package com.google.ads.mediation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import ax.I5.C1106f;
import ax.I5.g;
import ax.I5.h;
import ax.I5.i;
import ax.Q5.C1462y;
import ax.Q5.Y0;
import ax.W5.l;
import ax.W5.n;
import ax.W5.p;
import ax.W5.r;
import ax.W5.t;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, r, t {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private C1106f adLoader;
    protected i mAdView;
    protected ax.V5.a mInterstitialAd;

    g buildAdRequest(Context context, ax.W5.e eVar, Bundle bundle, Bundle bundle2) {
        g.a aVar = new g.a();
        Set<String> setH = eVar.h();
        if (setH != null) {
            Iterator<String> it = setH.iterator();
            while (it.hasNext()) {
                aVar.a(it.next());
            }
        }
        if (eVar.g()) {
            C1462y.b();
            aVar.d(ax.U5.g.C(context));
        }
        if (eVar.d() != -1) {
            aVar.f(eVar.d() == 1);
        }
        aVar.e(eVar.f());
        aVar.b(AdMobAdapter.class, buildExtrasBundle(bundle, bundle2));
        return aVar.g();
    }

    protected abstract Bundle buildExtrasBundle(Bundle bundle, Bundle bundle2);

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.mAdView;
    }

    ax.V5.a getInterstitialAd() {
        return this.mInterstitialAd;
    }

    @Override // ax.W5.t
    public Y0 getVideoController() {
        i iVar = this.mAdView;
        if (iVar != null) {
            return iVar.e().c();
        }
        return null;
    }

    C1106f.a newAdLoader(Context context, String str) {
        return new C1106f.a(context, str);
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onDestroy() {
        i iVar = this.mAdView;
        if (iVar != null) {
            iVar.a();
            this.mAdView = null;
        }
        if (this.mInterstitialAd != null) {
            this.mInterstitialAd = null;
        }
        if (this.adLoader != null) {
            this.adLoader = null;
        }
    }

    @Override // ax.W5.r
    public void onImmersiveModeUpdated(boolean z) {
        ax.V5.a aVar = this.mInterstitialAd;
        if (aVar != null) {
            aVar.d(z);
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onPause() {
        i iVar = this.mAdView;
        if (iVar != null) {
            iVar.c();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onResume() {
        i iVar = this.mAdView;
        if (iVar != null) {
            iVar.d();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, ax.W5.i iVar, Bundle bundle, h hVar, ax.W5.e eVar, Bundle bundle2) {
        i iVar2 = new i(context);
        this.mAdView = iVar2;
        iVar2.setAdSize(new h(hVar.d(), hVar.b()));
        this.mAdView.setAdUnitId(getAdUnitId(bundle));
        this.mAdView.setAdListener(new b(this, iVar));
        this.mAdView.b(buildAdRequest(context, eVar, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, l lVar, Bundle bundle, ax.W5.e eVar, Bundle bundle2) {
        ax.V5.a.b(context, getAdUnitId(bundle), buildAdRequest(context, eVar, bundle2, bundle), new c(this, lVar));
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(Context context, n nVar, Bundle bundle, p pVar, Bundle bundle2) {
        e eVar = new e(this, nVar);
        C1106f.a aVarC = newAdLoader(context, bundle.getString(AD_UNIT_ID_PARAMETER)).c(eVar);
        aVarC.g(pVar.i());
        aVarC.d(pVar.c());
        if (pVar.e()) {
            aVarC.f(eVar);
        }
        if (pVar.b()) {
            for (String str : pVar.a().keySet()) {
                aVarC.e(str, eVar, true != ((Boolean) pVar.a().get(str)).booleanValue() ? null : eVar);
            }
        }
        C1106f c1106fA = aVarC.a();
        this.adLoader = c1106fA;
        c1106fA.a(buildAdRequest(context, pVar, bundle2, bundle));
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        ax.V5.a aVar = this.mInterstitialAd;
        if (aVar != null) {
            aVar.e(null);
        }
    }
}
