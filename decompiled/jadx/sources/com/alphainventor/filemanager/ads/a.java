package com.alphainventor.filemanager.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import ax.Y4.AbstractC5085d;
import ax.Y4.C5087f;
import ax.Y4.m;
import ax.Y4.n;
import ax.Y4.p;
import ax.Y4.w;
import ax.p015b0.Y;
import ax.p045d2.v;
import ax.p045d2.x;
import ax.p182n.ActivityC5571c;
import com.alphainventor.filemanager.R;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAdView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
public class a {
    private static final Logger a = ax.p338y1.g.a(a.class);
    private static boolean b;
    private static Boolean c;
    private static Map<String, Integer> d;

    /* JADX INFO: renamed from: com.alphainventor.filemanager.ads.a$a, reason: collision with other inner class name */
    class C0494a implements com.google.android.gms.ads.nativead.a.c {
        final /* synthetic */ Activity a;
        final /* synthetic */ int b;
        final /* synthetic */ boolean c;
        final /* synthetic */ boolean d;
        final /* synthetic */ boolean e;
        final /* synthetic */ k f;

        C0494a(Activity activity, int i, boolean z, boolean z2, boolean z3, k kVar) {
            this.a = activity;
            this.b = i;
            this.c = z;
            this.d = z2;
            this.e = z3;
            this.f = kVar;
        }

        @Override // com.google.android.gms.ads.nativead.a.c
        public void a(com.google.android.gms.ads.nativead.a aVar) {
            v.f((ActivityC5571c) this.a);
            NativeAdView nativeAdView = (NativeAdView) this.a.getLayoutInflater().inflate(this.b, (ViewGroup) null);
            a.w(aVar, nativeAdView, this.c, this.d, this.e);
            this.f.a(nativeAdView);
        }
    }

    class b extends AbstractC5085d {
        final /* synthetic */ String X;
        final /* synthetic */ int Y;
        final /* synthetic */ k Z;
        final /* synthetic */ boolean h0;
        final /* synthetic */ boolean i0;
        final /* synthetic */ boolean j0;
        final /* synthetic */ Activity q;

        b(Activity activity, String str, int i, k kVar, boolean z, boolean z2, boolean z3) {
            this.q = activity;
            this.X = str;
            this.Y = i;
            this.Z = kVar;
            this.h0 = z;
            this.i0 = z2;
            this.j0 = z3;
        }

        @Override // ax.Y4.AbstractC5085d
        public void U() {
            this.Z.b();
        }

        @Override // ax.Y4.AbstractC5085d
        public void e(m mVar) {
            v.f((ActivityC5571c) this.q);
            if (mVar != null) {
                a.a.severe("Ad failed to load : " + mVar.a() + " : " + mVar.c());
            } else {
                ax.p045d2.b.f();
            }
            if (this.X == null || mVar == null || mVar.a() != 3) {
                this.Z.c(mVar.a());
                return;
            }
            a.a.severe("Request Secondary Ad");
            try {
                a.h(this.q, this.X, null, this.Y, this.Z, this.h0, this.i0, this.j0).a(a.i(this.q).g());
            } catch (Error | Exception unused) {
            }
        }
    }

    class c implements ax.p062e5.c {
        final /* synthetic */ CountDownLatch a;

        c(CountDownLatch countDownLatch) {
            this.a = countDownLatch;
        }

        @Override // ax.p062e5.c
        public void a(ax.p062e5.b bVar) {
            this.a.countDown();
        }
    }

    class d implements p {
        final /* synthetic */ l a;

        d(l lVar) {
            this.a = lVar;
        }

        @Override // ax.Y4.p
        public void a(ax.p272t5.b bVar) {
            this.a.a();
        }
    }

    class e extends AbstractC5085d {
        final /* synthetic */ ax.Y4.i X;
        final /* synthetic */ i Y;
        final /* synthetic */ Activity q;

        e(Activity activity, ax.Y4.i iVar, i iVar2) {
            this.q = activity;
            this.X = iVar;
            this.Y = iVar2;
        }

        @Override // ax.Y4.AbstractC5085d
        public void U() {
            this.Y.b(this.X);
        }

        @Override // ax.Y4.AbstractC5085d
        public void e(m mVar) {
            v.f((ActivityC5571c) this.q);
            this.Y.a(this.X, mVar.a());
        }

        @Override // ax.Y4.AbstractC5085d
        public void i() {
            v.f((ActivityC5571c) this.q);
            this.Y.c(this.X, (this.X.getResponseInfo() == null || this.X.getResponseInfo().a() == null) ? null : this.X.getResponseInfo().a().a());
        }
    }

    class f extends AbstractC5085d {
        final /* synthetic */ i X;
        final /* synthetic */ ax.Y4.i Y;
        final /* synthetic */ Activity q;

        f(Activity activity, i iVar, ax.Y4.i iVar2) {
            this.q = activity;
            this.X = iVar;
            this.Y = iVar2;
        }

        @Override // ax.Y4.AbstractC5085d
        public void U() {
            this.X.b(this.Y);
        }

        @Override // ax.Y4.AbstractC5085d
        public void e(m mVar) {
            v.f((ActivityC5571c) this.q);
            this.X.a(this.Y, mVar.a());
        }

        @Override // ax.Y4.AbstractC5085d
        public void i() {
            v.f((ActivityC5571c) this.q);
            this.X.c(this.Y, HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    class g extends AbstractC5085d {
        final /* synthetic */ i X;
        final /* synthetic */ ax.Y4.i Y;
        final /* synthetic */ Activity q;

        g(Activity activity, i iVar, ax.Y4.i iVar2) {
            this.q = activity;
            this.X = iVar;
            this.Y = iVar2;
        }

        @Override // ax.Y4.AbstractC5085d
        public void U() {
            this.X.b(this.Y);
        }

        @Override // ax.Y4.AbstractC5085d
        public void e(m mVar) {
            v.f((ActivityC5571c) this.q);
            this.X.a(this.Y, mVar.a());
        }

        @Override // ax.Y4.AbstractC5085d
        public void i() {
            v.f((ActivityC5571c) this.q);
            this.X.c(this.Y, HttpUrl.FRAGMENT_ENCODE_SET);
        }
    }

    private static class h {
        String a;
        String b;

        h(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public interface i {
        void a(View view, int i);

        void b(View view);

        void c(View view, String str);
    }

    public enum j {
        NOT_LOADED,
        REQUESTED,
        DISABLED,
        LOADED,
        SWIPED,
        REMOVED,
        SKIPPED,
        FAILED,
        AD_ERROR_NO_FILL,
        AD_ERROR
    }

    public interface k {
        void a(View view);

        void b();

        void c(int i);
    }

    public interface l {
        void a();
    }

    public static void A(Activity activity, k kVar) {
        h hVarF = f("ca-app-pub-2353536094017743/2146504937", "ca-app-pub-2353536094017743/5879992811");
        try {
            h(activity, hVarF.a, hVarF.b, R.layout.ad_exit_unified, kVar, false, false, false).a(i(activity).g());
        } catch (Error | Exception unused) {
        }
    }

    public static View B(Activity activity, int i2, i iVar) {
        ax.Y4.i iVar2 = new ax.Y4.i(activity);
        if (i2 < 300) {
            i2 = 300;
        }
        iVar2.setAdSize(new ax.Y4.h(i2, (i2 * 5) / 6));
        iVar2.setAdUnitId(p());
        iVar2.setAdListener(new g(activity, iVar, iVar2));
        try {
            iVar2.b(i(activity).g());
            return iVar2;
        } catch (Error | Exception unused) {
            return null;
        }
    }

    public static void C(Activity activity, k kVar) {
        h hVarF = f(q(), "ca-app-pub-2353536094017743/6040716863");
        try {
            h(activity, hVarF.a, hVarF.b, R.layout.ad_recyclebin_unified, kVar, false, false, false).a(i(activity).g());
        } catch (Error | Exception unused) {
        }
    }

    public static View D(Activity activity, i iVar) {
        ax.Y4.i iVar2 = new ax.Y4.i(activity);
        iVar2.setAdSize(ax.Y4.h.m);
        iVar2.setAdUnitId(r());
        iVar2.setAdListener(new f(activity, iVar, iVar2));
        try {
            iVar2.b(i(activity).g());
            return iVar2;
        } catch (Error | Exception unused) {
            return null;
        }
    }

    public static void E(Activity activity, k kVar) {
        h hVarG = g("ca-app-pub-2353536094017743/7597041343", "ca-app-pub-2353536094017743/4779306319");
        try {
            h(activity, hVarG.a, hVarG.b, R.layout.ad_videoplayer_unified, kVar, false, false, false).a(i(activity).g());
        } catch (Error | Exception unused) {
        }
    }

    public static void F(Object obj, Context context) {
        if (obj instanceof NativeAdView) {
            return;
        }
        if (obj instanceof ax.Y4.i) {
            ((ax.Y4.i) obj).d();
        } else {
            ax.p045d2.b.f();
        }
    }

    public static void G(Activity activity, Object obj, l lVar) {
        if (obj != null) {
            ((ax.p272t5.c) obj).c(activity, new d(lVar));
        }
    }

    private static h d(String str, String str2) {
        return k(ax.p031c2.d.v().g(), str, str2);
    }

    private static h e(String str, String str2) {
        return k(ax.p031c2.d.v().i(), str, str2);
    }

    private static h f(String str, String str2) {
        return k(ax.p031c2.d.v().d(), str, str2);
    }

    private static h g(String str, String str2) {
        return k(ax.p031c2.d.v().C(), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static C5087f h(Activity activity, String str, String str2, int i2, k kVar, boolean z, boolean z2, boolean z3) {
        C5087f.a aVar = new C5087f.a(activity, str);
        aVar.b(new C0494a(activity, i2, z, z2, z3, kVar));
        aVar.c(new b(activity, str2, i2, kVar, z, z2, z3)).d(new ax.p5.b.a().a());
        return aVar.a();
    }

    static ax.Y4.g.a i(Context context) {
        ax.Y4.g.a aVar = new ax.Y4.g.a();
        ax.p338y1.c.a();
        return aVar;
    }

    public static void j(Object obj, Context context) {
        if (obj instanceof NativeAdView) {
            ((NativeAdView) obj).a();
        } else if (obj instanceof ax.Y4.i) {
            ((ax.Y4.i) obj).a();
        } else {
            ax.p045d2.b.f();
        }
    }

    private static h k(int i2, String str, String str2) {
        if (i2 == 1) {
            return new h(str, null);
        }
        if (i2 == 2) {
            return new h(str, str2);
        }
        if (i2 == 3) {
            return new h(str2, str);
        }
        if (i2 == 10) {
            return new Random().nextInt(100) < ax.p031c2.d.v().f() ? new h(str, str2) : new h(str2, str);
        }
        if (i2 == 11) {
            if (u()) {
                return new Random().nextInt(100) < ax.p031c2.d.v().f() ? new h(str, str2) : new h(str2, str);
            }
            return new h(str, null);
        }
        if (i2 == 4) {
            return u() ? new h(str2, str) : new h(str, null);
        }
        if (i2 != 21) {
            return new h(str, null);
        }
        if (!u()) {
            return new h(str, null);
        }
        if (d == null) {
            d = new HashMap();
        }
        Integer num = d.get(str);
        if (num == null) {
            num = 0;
        }
        d.put(str, Integer.valueOf((num.intValue() + 1) % 2));
        return num.intValue() == 0 ? new h(str, str2) : new h(str2, str);
    }

    private static ax.Y4.h l(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return ax.Y4.h.a(activity, (int) (displayMetrics.widthPixels / displayMetrics.density));
    }

    private static String m() {
        String strA = ax.p031c2.a.a("analysis_native");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.i() ? "ca-app-pub-2353536094017743/9637199401" : "ca-app-pub-2353536094017743/2352751395";
        }
        return strA;
    }

    private static String n() {
        String strA = ax.p031c2.a.a("desktop_banner");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.i() ? "ca-app-pub-2353536094017743/1598378547" : "ca-app-pub-2353536094017743/5559612323";
        }
        return strA;
    }

    private static String o() {
        String strA = ax.p031c2.a.a("desktop_native");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.i() ? "ca-app-pub-2353536094017743/4753619959" : "ca-app-pub-2353536094017743/5653573529";
        }
        return strA;
    }

    private static String p() {
        String strA = ax.p031c2.a.a("exit_banner");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.j() ? "ca-app-pub-2353536094017743/8215757781" : "ca-app-pub-2353536094017743/1185100026";
        }
        return strA;
    }

    private static String q() {
        String strA = ax.p031c2.a.a("recyclebin_native");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.i() ? "ca-app-pub-2353536094017743/8501293274" : "ca-app-pub-2353536094017743/5376045147";
        }
        return strA;
    }

    private static String r() {
        String strA = ax.p031c2.a.a("video_banner");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.k() ? "ca-app-pub-2353536094017743/9093725182" : "ca-app-pub-2353536094017743/7755504193";
        }
        return strA;
    }

    private static boolean s(w wVar) {
        if (wVar == null) {
            return false;
        }
        return wVar.a();
    }

    public static void t(Context context) {
        if (b) {
            return;
        }
        b = true;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try {
            MobileAds.a(context, new c(countDownLatch));
        } catch (Error | RuntimeException unused) {
            countDownLatch.countDown();
        }
        try {
            countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused2) {
        }
        ax.p338y1.c.a();
    }

    private static boolean u() {
        if (c == null) {
            Context contextG = ax.p338y1.b.h().g();
            if (contextG == null) {
                return false;
            }
            c = Boolean.valueOf(x.O(contextG, "com.facebook.katana") || x.O(contextG, "com.facebook.orca") || x.O(contextG, "com.facebook.mlite") || x.O(contextG, "com.facebook.lite"));
        }
        return c.booleanValue();
    }

    public static void v(Object obj, Context context) {
        if (obj instanceof NativeAdView) {
            return;
        }
        if (obj instanceof ax.Y4.i) {
            ((ax.Y4.i) obj).c();
        } else {
            ax.p045d2.b.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void w(com.google.android.gms.ads.nativead.a aVar, NativeAdView nativeAdView, boolean z, boolean z2, boolean z3) {
        n nVarF = aVar.f();
        MediaView mediaView = null;
        w videoController = nVarF != null ? nVarF.getVideoController() : null;
        nativeAdView.setHeadlineView(nativeAdView.findViewById(R.id.unified_headline));
        nativeAdView.setBodyView(nativeAdView.findViewById(R.id.unified_body));
        nativeAdView.setCallToActionView(nativeAdView.findViewById(R.id.unified_call_to_action));
        View viewFindViewById = nativeAdView.findViewById(R.id.unified_app_icon);
        nativeAdView.setIconView(viewFindViewById);
        if (nativeAdView.getHeadlineView() != null) {
            ((TextView) nativeAdView.getHeadlineView()).setText(aVar.c());
        }
        if (z) {
            String string = nativeAdView.getContext().getString(R.string.ad_mark_desktop);
            ((TextView) nativeAdView.getBodyView()).setText(string + aVar.a());
        } else {
            ((TextView) nativeAdView.getBodyView()).setText(aVar.a());
        }
        ((Button) nativeAdView.getCallToActionView()).setText(aVar.b());
        int iC = ax.p031c2.d.v().c();
        if (iC != 0) {
            Y.v0(nativeAdView.getCallToActionView(), ColorStateList.valueOf(iC));
        }
        if (nativeAdView.getIconView() != null) {
            if (aVar.d() == null) {
                if (viewFindViewById != null) {
                    viewFindViewById.setVisibility(8);
                }
                mediaView = (MediaView) nativeAdView.findViewById(R.id.unified_icon_media);
                if (mediaView != null) {
                    if (z3 && !s(videoController)) {
                        mediaView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
                    }
                    mediaView.setVisibility(0);
                    nativeAdView.setMediaView(mediaView);
                }
            } else if (nativeAdView.getIconView() instanceof ImageView) {
                ((ImageView) nativeAdView.getIconView()).setImageDrawable(aVar.d().a());
            }
        }
        MediaView mediaView2 = (MediaView) nativeAdView.findViewById(R.id.unified_media);
        if (mediaView2 != null) {
            nativeAdView.setMediaView(mediaView2);
            if (z3 && !s(videoController)) {
                mediaView2.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        }
        if (nativeAdView.getPriceView() != null) {
            if (aVar.g() == null) {
                nativeAdView.getPriceView().setVisibility(4);
            } else {
                nativeAdView.getPriceView().setVisibility(0);
                ((TextView) nativeAdView.getPriceView()).setText(aVar.g());
            }
        }
        if (nativeAdView.getStoreView() != null) {
            if (aVar.j() == null) {
                nativeAdView.getStoreView().setVisibility(4);
            } else {
                nativeAdView.getStoreView().setVisibility(0);
                ((TextView) nativeAdView.getStoreView()).setText(aVar.j());
            }
        }
        if (nativeAdView.getStarRatingView() != null) {
            if (aVar.i() == null) {
                nativeAdView.getStarRatingView().setVisibility(4);
            } else {
                ((RatingBar) nativeAdView.getStarRatingView()).setRating(aVar.i().floatValue());
                nativeAdView.getStarRatingView().setVisibility(0);
            }
        }
        nativeAdView.setNativeAd(aVar);
        if (mediaView2 != null && !s(videoController) && mediaView2.getChildCount() <= 0) {
            nativeAdView.setImageView(nativeAdView.findViewById(R.id.unified_image));
            if (nativeAdView.getImageView() != null) {
                List<com.google.android.gms.ads.nativead.a.b> listE = aVar.e();
                if (listE.size() > 0) {
                    mediaView2.setVisibility(8);
                    nativeAdView.getImageView().setVisibility(0);
                    ((ImageView) nativeAdView.getImageView()).setImageDrawable(listE.get(0).a());
                }
            }
        }
        if (mediaView == null || s(videoController) || mediaView.getChildCount() > 0) {
            return;
        }
        nativeAdView.setImageView(nativeAdView.findViewById(R.id.unified_app_icon));
        if (nativeAdView.getImageView() != null) {
            List<com.google.android.gms.ads.nativead.a.b> listE2 = aVar.e();
            if (listE2.size() > 0) {
                mediaView.setVisibility(8);
                nativeAdView.getImageView().setVisibility(0);
                ((ImageView) nativeAdView.getImageView()).setImageDrawable(listE2.get(0).a());
            }
        }
    }

    public static void x(Activity activity, k kVar) {
        h hVarD = d(m(), "ca-app-pub-2353536094017743/1698556294");
        try {
            h(activity, hVarD.a, hVarD.b, R.layout.ad_analysis_unified, kVar, false, ax.p031c2.d.v().R(), true).a(i(activity).g());
        } catch (Error | Exception unused) {
        }
    }

    public static void y(Activity activity, k kVar) {
        h hVarE = e(o(), "ca-app-pub-2353536094017743/9824458327");
        try {
            h(activity, hVarE.a, hVarE.b, R.layout.ad_desktop_unified, kVar, false, ax.p031c2.d.v().R(), true).a(i(activity).g());
        } catch (Error | Exception unused) {
        }
    }

    public static View z(Activity activity, i iVar) {
        ax.Y4.i iVar2 = new ax.Y4.i(activity);
        x.A(activity);
        ax.Y4.h hVarL = l(activity);
        iVar2.setBackgroundColor(-16777216);
        iVar2.setAdSize(hVarL);
        iVar2.setAdUnitId(n());
        iVar2.setAdListener(new e(activity, iVar2, iVar));
        try {
            iVar2.b(i(activity).g());
            return iVar2;
        } catch (Error | Exception unused) {
            return null;
        }
    }
}
