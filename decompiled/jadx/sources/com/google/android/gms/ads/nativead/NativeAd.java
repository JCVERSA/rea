package com.google.android.gms.ads.nativead;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import ax.I5.n;
import ax.I5.u;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class NativeAd {

    public static abstract class a {
    }

    public static abstract class b {
        public abstract Drawable a();
    }

    public interface c {
        void a(NativeAd nativeAd);
    }

    public abstract String a();

    public abstract String b();

    public abstract String c();

    public abstract b d();

    public abstract List<b> e();

    public abstract n f();

    public abstract String g();

    public abstract u h();

    public abstract Double i();

    public abstract String j();

    protected abstract Object k();

    protected abstract void recordEvent(Bundle bundle);
}
