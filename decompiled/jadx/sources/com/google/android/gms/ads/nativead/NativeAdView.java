package com.google.android.gms.ads.nativead;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import ax.I5.n;
import ax.Q5.A;
import ax.Q5.C1455v1;
import ax.Q5.C1462y;
import ax.U5.p;
import ax.Z5.d;
import ax.Z5.e;
import ax.p377x6.a;
import ax.p377x6.b;
import ax.p409z6.C2654Ff;
import ax.p409z6.InterfaceC6061yh;

/* JADX INFO: loaded from: classes3.dex */
public final class NativeAdView extends FrameLayout {
    private final InterfaceC6061yh k0;
    private final FrameLayout q;

    public NativeAdView(Context context) {
        super(context);
        this.q = e(context);
        this.k0 = f();
    }

    public NativeAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = e(context);
        this.k0 = f();
    }

    private final FrameLayout e(Context context) {
        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(frameLayout);
        return frameLayout;
    }

    private final InterfaceC6061yh f() {
        if (isInEditMode()) {
            return null;
        }
        FrameLayout frameLayout = this.q;
        return C1462y.a().h(frameLayout.getContext(), this, frameLayout);
    }

    private final void g(String str, View view) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            interfaceC6061yh.h1(str, b.N2(view));
        } catch (RemoteException e) {
            p.e("Unable to call setAssetView on delegate", e);
        }
    }

    public void a() {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            interfaceC6061yh.c();
        } catch (RemoteException e) {
            p.e("Unable to destroy native ad view", e);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        super.bringChildToFront(this.q);
    }

    protected final View b(String str) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh != null) {
            try {
                a aVarX = interfaceC6061yh.x(str);
                if (aVarX != null) {
                    return (View) b.e1(aVarX);
                }
            } catch (RemoteException e) {
                p.e("Unable to call getAssetView on delegate", e);
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void bringChildToFront(View view) {
        super.bringChildToFront(view);
        FrameLayout frameLayout = this.q;
        if (frameLayout != view) {
            super.bringChildToFront(frameLayout);
        }
    }

    final /* synthetic */ void c(n nVar) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            if (nVar instanceof C1455v1) {
                interfaceC6061yh.B1(((C1455v1) nVar).d());
            } else if (nVar == null) {
                interfaceC6061yh.B1(null);
            } else {
                p.b("Use MediaContent provided by NativeAd.getMediaContent");
            }
        } catch (RemoteException e) {
            p.e("Unable to call setMediaContent on delegate", e);
        }
    }

    final /* synthetic */ void d(ImageView.ScaleType scaleType) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null || scaleType == null) {
            return;
        }
        try {
            interfaceC6061yh.q4(b.N2(scaleType));
        } catch (RemoteException e) {
            p.e("Unable to call setMediaViewImageScaleType on delegate", e);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.k0 != null) {
            if (((Boolean) A.c().a(C2654Ff.tb)).booleanValue()) {
                try {
                    this.k0.I0(b.N2(motionEvent));
                } catch (RemoteException e) {
                    p.e("Unable to call handleTouchEvent on delegate", e);
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public ax.Z5.a getAdChoicesView() {
        View viewB = b("3011");
        if (viewB instanceof ax.Z5.a) {
            return (ax.Z5.a) viewB;
        }
        return null;
    }

    public final View getAdvertiserView() {
        return b("3005");
    }

    public final View getBodyView() {
        return b("3004");
    }

    public final View getCallToActionView() {
        return b("3002");
    }

    public final View getHeadlineView() {
        return b("3001");
    }

    public final View getIconView() {
        return b("3003");
    }

    public final View getImageView() {
        return b("3008");
    }

    public final MediaView getMediaView() {
        View viewB = b("3010");
        if (viewB instanceof MediaView) {
            return (MediaView) viewB;
        }
        if (viewB == null) {
            return null;
        }
        p.b("View is not an instance of MediaView");
        return null;
    }

    public final View getPriceView() {
        return b("3007");
    }

    public final View getStarRatingView() {
        return b("3009");
    }

    public final View getStoreView() {
        return b("3006");
    }

    @Override // android.view.View
    public final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            interfaceC6061yh.m6(b.N2(view), i);
        } catch (RemoteException e) {
            p.e("Unable to call onVisibilityChanged on delegate", e);
        }
    }

    @Override // android.view.ViewGroup
    public final void removeAllViews() {
        super.removeAllViews();
        super.addView(this.q);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        if (this.q == view) {
            return;
        }
        super.removeView(view);
    }

    public void setAdChoicesView(ax.Z5.a aVar) {
        g("3011", aVar);
    }

    public final void setAdvertiserView(View view) {
        g("3005", view);
    }

    public final void setBodyView(View view) {
        g("3004", view);
    }

    public final void setCallToActionView(View view) {
        g("3002", view);
    }

    public final void setClickConfirmingView(View view) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            interfaceC6061yh.I5(b.N2(view));
        } catch (RemoteException e) {
            p.e("Unable to call setClickConfirmingView on delegate", e);
        }
    }

    public final void setHeadlineView(View view) {
        g("3001", view);
    }

    public final void setIconView(View view) {
        g("3003", view);
    }

    public final void setImageView(View view) {
        g("3008", view);
    }

    public final void setMediaView(MediaView mediaView) {
        g("3010", mediaView);
        if (mediaView == null) {
            return;
        }
        mediaView.a(new d(this));
        mediaView.b(new e(this));
    }

    public void setNativeAd(NativeAd nativeAd) {
        InterfaceC6061yh interfaceC6061yh = this.k0;
        if (interfaceC6061yh == null) {
            return;
        }
        try {
            interfaceC6061yh.w3((a) nativeAd.k());
        } catch (RemoteException e) {
            p.e("Unable to call setNativeAd on delegate", e);
        }
    }

    public final void setPriceView(View view) {
        g("3007", view);
    }

    public final void setStarRatingView(View view) {
        g("3009", view);
    }

    public final void setStoreView(View view) {
        g("3006", view);
    }
}
