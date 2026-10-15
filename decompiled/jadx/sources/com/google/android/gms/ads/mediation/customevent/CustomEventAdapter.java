package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import ax.I5.C1102b;
import ax.I5.h;
import ax.U5.p;
import ax.W5.e;
import ax.W5.i;
import ax.W5.l;
import ax.W5.n;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.common.annotation.KeepName;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    static final C1102b e = new C1102b(0, "Could not instantiate custom event adapter", "com.google.android.gms.ads");
    private View a;
    CustomEventBanner b;
    CustomEventInterstitial c;
    CustomEventNative d;

    private static Object a(Class cls, String str) {
        try {
            if (str != null) {
                return cls.cast(Class.forName(str).getDeclaredConstructor(null).newInstance(null));
            }
            throw null;
        } catch (Throwable th) {
            p.g("Could not instantiate custom event adapter: " + str + ". " + th.getMessage());
            return null;
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public View getBannerView() {
        return this.a;
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onDestroy() {
        CustomEventBanner customEventBanner = this.b;
        if (customEventBanner != null) {
            customEventBanner.onDestroy();
        }
        CustomEventInterstitial customEventInterstitial = this.c;
        if (customEventInterstitial != null) {
            customEventInterstitial.onDestroy();
        }
        CustomEventNative customEventNative = this.d;
        if (customEventNative != null) {
            customEventNative.onDestroy();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onPause() {
        CustomEventBanner customEventBanner = this.b;
        if (customEventBanner != null) {
            customEventBanner.onPause();
        }
        CustomEventInterstitial customEventInterstitial = this.c;
        if (customEventInterstitial != null) {
            customEventInterstitial.onPause();
        }
        CustomEventNative customEventNative = this.d;
        if (customEventNative != null) {
            customEventNative.onPause();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter, ax.W5.f, com.google.android.gms.ads.mediation.MediationInterstitialAdapter, com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void onResume() {
        CustomEventBanner customEventBanner = this.b;
        if (customEventBanner != null) {
            customEventBanner.onResume();
        }
        CustomEventInterstitial customEventInterstitial = this.c;
        if (customEventInterstitial != null) {
            customEventInterstitial.onResume();
        }
        CustomEventNative customEventNative = this.d;
        if (customEventNative != null) {
            customEventNative.onResume();
        }
    }

    @Override // com.google.android.gms.ads.mediation.MediationBannerAdapter
    public void requestBannerAd(Context context, i iVar, Bundle bundle, h hVar, e eVar, Bundle bundle2) {
        CustomEventBanner customEventBanner = (CustomEventBanner) a(CustomEventBanner.class, bundle.getString("class_name"));
        this.b = customEventBanner;
        if (customEventBanner == null) {
            iVar.l(this, e);
            return;
        }
        Bundle bundle3 = bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name"));
        this.b.getClass();
        new a(this, iVar);
        bundle.getString("parameter");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void requestInterstitialAd(Context context, l lVar, Bundle bundle, e eVar, Bundle bundle2) {
        CustomEventInterstitial customEventInterstitial = (CustomEventInterstitial) a(CustomEventInterstitial.class, bundle.getString("class_name"));
        this.c = customEventInterstitial;
        if (customEventInterstitial == null) {
            lVar.i(this, e);
            return;
        }
        Bundle bundle3 = bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name"));
        this.c.getClass();
        new b(this, this, lVar);
        bundle.getString("parameter");
    }

    @Override // com.google.android.gms.ads.mediation.MediationNativeAdapter
    public void requestNativeAd(Context context, n nVar, Bundle bundle, ax.W5.p pVar, Bundle bundle2) {
        CustomEventNative customEventNative = (CustomEventNative) a(CustomEventNative.class, bundle.getString("class_name"));
        this.d = customEventNative;
        if (customEventNative == null) {
            nVar.k(this, e);
            return;
        }
        Bundle bundle3 = bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name"));
        this.d.getClass();
        new c(this, nVar);
        bundle.getString("parameter");
    }

    @Override // com.google.android.gms.ads.mediation.MediationInterstitialAdapter
    public void showInterstitial() {
        if (this.c != null) {
        }
    }
}
