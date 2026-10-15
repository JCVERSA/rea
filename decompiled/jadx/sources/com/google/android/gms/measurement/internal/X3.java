package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import ax.H6.O6;

/* JADX INFO: loaded from: classes3.dex */
final class X3 implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ C6351k3 q;

    X3(C6351k3 c6351k3) {
        this.q = c6351k3;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00d9 A[Catch: RuntimeException -> 0x0028, TryCatch #0 {RuntimeException -> 0x0028, blocks: (B:3:0x000c, B:5:0x0018, B:11:0x002c, B:40:0x00af, B:42:0x00bb, B:48:0x00d3, B:50:0x00d9, B:53:0x00e6, B:55:0x00ec, B:56:0x00ff, B:57:0x010b, B:60:0x0113, B:64:0x0136, B:66:0x0152, B:65:0x0143, B:68:0x0159, B:70:0x015f, B:72:0x0165, B:74:0x016b, B:76:0x0171, B:78:0x0179, B:80:0x0181, B:82:0x0187, B:84:0x018d, B:15:0x0040, B:18:0x0048, B:20:0x0050, B:22:0x0056, B:24:0x005c, B:26:0x0062, B:28:0x006a, B:30:0x0072, B:32:0x007a, B:34:0x0082, B:35:0x008e, B:37:0x00a6), top: B:88:0x000c }] */
    static /* synthetic */ void a(X3 x3, boolean z, Uri uri, String str, String str2) {
        Bundle bundleC;
        char c;
        Bundle bundleC2;
        x3.q.n();
        try {
            B5 b5I = x3.q.i();
            boolean z2 = O6.a() && x3.q.d().t(F.M0);
            if (TextUtils.isEmpty(str2)) {
                bundleC = null;
            } else if (str2.contains("gclid") || ((z2 && str2.contains("gbraid")) || str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium") || str2.contains("utm_id") || str2.contains("dclid") || str2.contains("srsltid") || str2.contains("sfmc_id"))) {
                bundleC = b5I.C(Uri.parse("https://google.com/search?" + str2), z2);
                if (bundleC != null) {
                    bundleC.putString("_cis", "referrer");
                }
            } else {
                b5I.j().F().a("Activity created with data 'referrer' without required params");
                bundleC = null;
            }
            if (z) {
                B5 b5I2 = x3.q.i();
                if (O6.a()) {
                    c = 0;
                    boolean z3 = x3.q.d().t(F.M0);
                    bundleC2 = b5I2.C(uri, z3);
                    if (bundleC2 != null) {
                        bundleC2.putString("_cis", "intent");
                        if (!bundleC2.containsKey("gclid") && bundleC != null && bundleC.containsKey("gclid")) {
                            Object[] objArr = new Object[1];
                            objArr[c] = bundleC.getString("gclid");
                            bundleC2.putString("_cer", String.format("gclid=%s", objArr));
                        }
                        x3.q.Y0(str, "_cmp", bundleC2);
                        x3.q.p.b(str, bundleC2);
                    }
                } else {
                    c = 0;
                }
                bundleC2 = b5I2.C(uri, z3);
                if (bundleC2 != null) {
                    bundleC2.putString("_cis", "intent");
                    if (!bundleC2.containsKey("gclid")) {
                        Object[] objArr2 = new Object[1];
                        objArr2[c] = bundleC.getString("gclid");
                        bundleC2.putString("_cer", String.format("gclid=%s", objArr2));
                    }
                    x3.q.Y0(str, "_cmp", bundleC2);
                    x3.q.p.b(str, bundleC2);
                }
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            x3.q.j().F().b("Activity created with referrer", str2);
            if (x3.q.d().t(F.m0)) {
                if (bundleC != null) {
                    x3.q.Y0(str, "_cmp", bundleC);
                    x3.q.p.b(str, bundleC);
                } else {
                    x3.q.j().F().b("Referrer does not contain valid parameters", str2);
                }
                x3.q.k0("auto", "_ldl", null, true);
                return;
            }
            if (!str2.contains("gclid") || (!str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium") && !str2.contains("utm_term") && !str2.contains("utm_content"))) {
                x3.q.j().F().a("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                x3.q.k0("auto", "_ldl", str2, true);
            }
        } catch (RuntimeException e) {
            x3.q.j().G().b("Throwable caught in handleReferrerForOnActivityCreated", e);
        }
    }

    /* JADX WARN: Code duplicated, block: B:20:0x004c  */
    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) throws Throwable {
        X3 x3;
        try {
            try {
                this.q.j().K().a("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent == null) {
                    this.q.s().F(activity, bundle);
                    return;
                }
                Uri data = intent.getData();
                if (data == null || !data.isHierarchical()) {
                    Bundle extras = intent.getExtras();
                    if (extras != null) {
                        String string = extras.getString("com.android.vending.referral_url");
                        if (TextUtils.isEmpty(string)) {
                            data = null;
                        } else {
                            data = Uri.parse(string);
                        }
                    } else {
                        data = null;
                    }
                }
                Uri uri = data;
                if (uri == null || !uri.isHierarchical()) {
                    this.q.s().F(activity, bundle);
                    return;
                }
                this.q.i();
                String str = B5.f0(intent) ? "gs" : "auto";
                String queryParameter = uri.getQueryParameter("referrer");
                x3 = this;
                try {
                    this.q.l().D(new W3(x3, bundle == null, uri, str, queryParameter));
                    x3.q.s().F(activity, bundle);
                    return;
                } catch (RuntimeException e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
                this.q.s().F(activity, bundle);
                throw th;
            }
        } catch (RuntimeException e2) {
            e = e2;
            x3 = this;
        } catch (Throwable th2) {
            th = th2;
            this.q.s().F(activity, bundle);
            throw th;
        }
        x3.q.j().G().b("Throwable caught in onActivityCreated", e);
        x3.q.s().F(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.q.s().E(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.q.s().Q(activity);
        V4 v4U = this.q.u();
        v4U.l().D(new W4(v4U, v4U.b().b()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        V4 v4U = this.q.u();
        v4U.l().D(new X4(v4U, v4U.b().b()));
        this.q.s().S(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.q.s().R(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
