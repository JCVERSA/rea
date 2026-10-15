package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import ax.H6.W0;
import ax.M6.y;
import ax.Q6.m;
import ax.p265q6.C2254p;
import com.google.firebase.installations.c;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public final class FirebaseAnalytics {
    private static volatile FirebaseAnalytics b;
    private final W0 a;

    public enum a {
        GRANTED,
        DENIED
    }

    public enum b {
        AD_STORAGE,
        ANALYTICS_STORAGE,
        AD_USER_DATA,
        AD_PERSONALIZATION
    }

    private FirebaseAnalytics(W0 w0) {
        C2254p.l(w0);
        this.a = w0;
    }

    @Keep
    public static FirebaseAnalytics getInstance(Context context) {
        if (b == null) {
            synchronized (FirebaseAnalytics.class) {
                try {
                    if (b == null) {
                        b = new FirebaseAnalytics(W0.f(context));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return b;
    }

    @Keep
    public static y getScionFrontendApiImplementation(Context context, Bundle bundle) {
        W0 w0G = W0.g(context, null, null, null, bundle);
        if (w0G == null) {
            return null;
        }
        return new com.google.firebase.analytics.a(w0G);
    }

    public final void a(String str, Bundle bundle) {
        this.a.t(str, bundle);
    }

    public final void b(boolean z) {
        this.a.r(Boolean.valueOf(z));
    }

    public final void c(String str, String str2) {
        this.a.D(str, str2);
    }

    @Keep
    public final String getFirebaseInstanceId() {
        try {
            return (String) m.b(c.q().a(), 30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        } catch (ExecutionException e2) {
            throw new IllegalStateException(e2.getCause());
        } catch (TimeoutException unused) {
            throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
        }
    }

    @Keep
    @Deprecated
    public final void setCurrentScreen(Activity activity, String str, String str2) {
        this.a.k(activity, str, str2);
    }
}
