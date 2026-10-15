package com.alphainventor.filemanager.ads;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.lifecycle.d;
import androidx.lifecycle.i;
import androidx.lifecycle.j;
import ax.G0.g;
import ax.I5.C1102b;
import ax.I5.l;
import ax.I5.m;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class AppOpenManager implements g, Application.ActivityLifecycleCallbacks {
    private static AppOpenManager o0;
    private final Application X;
    private Activity Y;
    private ax.K5.a q = null;
    private boolean Z = false;
    private boolean k0 = false;
    private long l0 = 0;
    private boolean m0 = false;
    private long n0 = 0;

    class a extends ax.K5.a.AbstractC0123a {
        a() {
        }

        @Override // ax.I5.AbstractC1105e
        public void a(m mVar) {
            AppOpenManager.this.k0 = false;
            Log.d("AppOpenManager", "error :" + mVar);
        }

        @Override // ax.I5.AbstractC1105e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(ax.K5.a aVar) {
            AppOpenManager.this.q = aVar;
            AppOpenManager.this.k0 = false;
            AppOpenManager.this.l0 = new Date().getTime();
        }
    }

    class b extends l {
        final /* synthetic */ Activity a;

        b(Activity activity) {
            this.a = activity;
        }

        @Override // ax.I5.l
        public void b() {
            AppOpenManager.this.q = null;
            AppOpenManager.this.Z = false;
            AppOpenManager.this.q(this.a);
        }

        @Override // ax.I5.l
        public void c(C1102b c1102b) {
            Log.d("AppOpenManager", c1102b.c());
            AppOpenManager.this.q = null;
            AppOpenManager.this.Z = false;
            AppOpenManager.this.q(this.a);
        }

        @Override // ax.I5.l
        public void e() {
        }
    }

    private AppOpenManager(Application application) {
        this.X = application;
        application.registerActivityLifecycleCallbacks(this);
        j.l().getLifecycle().a(this);
    }

    private ax.I5.g l() {
        return com.alphainventor.filemanager.ads.a.i(this.X).g();
    }

    private static String m() {
        String strA = ax.p390y3.a.a("app_open");
        if (TextUtils.isEmpty(strA)) {
            return ax.p390y3.a.h() ? "ca-app-pub-2353536094017743/7422099602" : "ca-app-pub-2353536094017743/1854075328";
        }
        return strA;
    }

    public static void n(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext instanceof Application) {
            AppOpenManager appOpenManager = new AppOpenManager((Application) applicationContext);
            o0 = appOpenManager;
            appOpenManager.q(context);
        }
    }

    public static boolean p() {
        return o0 != null;
    }

    public static boolean s() {
        return true;
    }

    public static void t() {
        AppOpenManager appOpenManager = o0;
        if (appOpenManager != null) {
            appOpenManager.m0 = true;
            appOpenManager.n0 = SystemClock.uptimeMillis();
        }
    }

    private boolean u(long j) {
        return new Date().getTime() - this.l0 < j * 3600000;
    }

    public boolean o() {
        return this.q != null && u(4L);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (this.Z) {
            return;
        }
        this.Y = activity;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @i(d.a.ON_STOP)
    protected void onMoveToBackground() {
        if (!this.m0 || SystemClock.uptimeMillis() - this.n0 <= 30000) {
            return;
        }
        this.m0 = false;
    }

    @i(d.a.ON_START)
    protected void onMoveToForeground() {
        Activity activity = this.Y;
        if (activity == null) {
            return;
        }
        if (this.m0) {
            this.m0 = false;
        } else {
            r(activity);
        }
    }

    public void q(Context context) {
        if (this.k0 || o()) {
            return;
        }
        this.k0 = true;
        ax.K5.a.c(context, m(), l(), new a());
    }

    public void r(Activity activity) {
        if (this.Z) {
            return;
        }
        if (!o()) {
            q(activity);
            return;
        }
        this.q.d(new b(activity));
        this.Z = true;
        this.q.e(activity);
    }
}
