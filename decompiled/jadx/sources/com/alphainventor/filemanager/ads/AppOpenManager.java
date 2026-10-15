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
import ax.F0.f;
import ax.Y4.C5083b;
import ax.Y4.g;
import ax.Y4.l;
import ax.Y4.m;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class AppOpenManager implements f, Application.ActivityLifecycleCallbacks {
    private static AppOpenManager l0;
    private final Application X;
    private Activity Y;
    private ax.p006a5.a q = null;
    private boolean Z = false;
    private boolean h0 = false;
    private long i0 = 0;
    private boolean j0 = false;
    private long k0 = 0;

    class a extends ax.p006a5.a.AbstractC0281a {
        a() {
        }

        @Override // ax.Y4.AbstractC5086e
        public void a(m mVar) {
            AppOpenManager.this.h0 = false;
            Log.d("AppOpenManager", "error :" + mVar);
        }

        @Override // ax.Y4.AbstractC5086e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public void b(ax.p006a5.a aVar) {
            AppOpenManager.this.q = aVar;
            AppOpenManager.this.h0 = false;
            AppOpenManager.this.i0 = new Date().getTime();
        }
    }

    class b extends l {
        final /* synthetic */ Activity a;

        b(Activity activity) {
            this.a = activity;
        }

        @Override // ax.Y4.l
        public void b() {
            AppOpenManager.this.q = null;
            AppOpenManager.this.Z = false;
            AppOpenManager.this.q(this.a);
        }

        @Override // ax.Y4.l
        public void c(C5083b c5083b) {
            Log.d("AppOpenManager", c5083b.c());
            AppOpenManager.this.q = null;
            AppOpenManager.this.Z = false;
            AppOpenManager.this.q(this.a);
        }

        @Override // ax.Y4.l
        public void e() {
        }
    }

    private AppOpenManager(Application application) {
        this.X = application;
        application.registerActivityLifecycleCallbacks(this);
        j.m().f().a(this);
    }

    private g l() {
        return com.alphainventor.filemanager.ads.a.i(this.X).g();
    }

    private static String m() {
        String strA = ax.p031c2.a.a("app_open");
        if (TextUtils.isEmpty(strA)) {
            return ax.p031c2.a.h() ? "ca-app-pub-2353536094017743/7422099602" : "ca-app-pub-2353536094017743/1854075328";
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
            l0 = appOpenManager;
            appOpenManager.q(context);
        }
    }

    public static boolean p() {
        return l0 != null;
    }

    public static boolean s() {
        return true;
    }

    public static void t() {
        AppOpenManager appOpenManager = l0;
        if (appOpenManager != null) {
            appOpenManager.j0 = true;
            appOpenManager.k0 = SystemClock.uptimeMillis();
        }
    }

    private boolean u(long j) {
        return new Date().getTime() - this.i0 < j * 3600000;
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
        if (!this.j0 || SystemClock.uptimeMillis() - this.k0 <= 30000) {
            return;
        }
        this.j0 = false;
    }

    @i(d.a.ON_START)
    protected void onMoveToForeground() {
        Activity activity = this.Y;
        if (activity == null) {
            return;
        }
        if (this.j0) {
            this.j0 = false;
        } else {
            r(activity);
        }
    }

    public void q(Context context) {
        if (this.h0 || o()) {
            return;
        }
        this.h0 = true;
        ax.p006a5.a.c(context, m(), l(), new a());
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
