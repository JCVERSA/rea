package com.google.android.gms.ads.nativead;

import android.content.Context;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import ax.I5.n;
import ax.U5.p;
import ax.Z5.d;
import ax.Z5.e;
import ax.p377x6.b;
import ax.p409z6.InterfaceC2903Mh;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class MediaView extends FrameLayout {
    private boolean k0;
    private ImageView.ScaleType l0;
    private boolean m0;
    private d n0;
    private e o0;
    private n q;

    public MediaView(Context context) {
        super(context);
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected final synchronized void a(d dVar) {
        this.n0 = dVar;
        if (this.k0) {
            dVar.a.c(this.q);
        }
    }

    protected final synchronized void b(e eVar) {
        this.o0 = eVar;
        if (this.m0) {
            eVar.a.d(this.l0);
        }
    }

    public n getMediaContent() {
        return this.q;
    }

    public void setImageScaleType(ImageView.ScaleType scaleType) {
        this.m0 = true;
        this.l0 = scaleType;
        e eVar = this.o0;
        if (eVar != null) {
            eVar.a.d(scaleType);
        }
    }

    public void setMediaContent(n nVar) {
        boolean zC0;
        this.k0 = true;
        this.q = nVar;
        d dVar = this.n0;
        if (dVar != null) {
            dVar.a.c(nVar);
        }
        if (nVar == null) {
            return;
        }
        try {
            InterfaceC2903Mh interfaceC2903MhA = nVar.a();
            if (interfaceC2903MhA != null) {
                if (!nVar.c()) {
                    if (nVar.b()) {
                        zC0 = interfaceC2903MhA.c0(b.N2(this));
                    }
                    removeAllViews();
                }
                zC0 = interfaceC2903MhA.V0(b.N2(this));
                if (zC0) {
                    return;
                }
                removeAllViews();
            }
        } catch (RemoteException e) {
            removeAllViews();
            p.e(HttpUrl.FRAGMENT_ENCODE_SET, e);
        }
    }
}
