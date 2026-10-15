package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class ComponentCallbacks2C6270a implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final ComponentCallbacks2C6270a k0 = new ComponentCallbacks2C6270a();
    private final AtomicBoolean q = new AtomicBoolean();
    private final AtomicBoolean X = new AtomicBoolean();
    private final ArrayList Y = new ArrayList();
    private boolean Z = false;

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.a$a, reason: collision with other inner class name */
    public interface InterfaceC0566a {
        void a(boolean z);
    }

    private ComponentCallbacks2C6270a() {
    }

    public static ComponentCallbacks2C6270a b() {
        return k0;
    }

    public static void c(Application application) {
        ComponentCallbacks2C6270a componentCallbacks2C6270a = k0;
        synchronized (componentCallbacks2C6270a) {
            try {
                if (!componentCallbacks2C6270a.Z) {
                    application.registerActivityLifecycleCallbacks(componentCallbacks2C6270a);
                    application.registerComponentCallbacks(componentCallbacks2C6270a);
                    componentCallbacks2C6270a.Z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final void f(boolean z) {
        synchronized (k0) {
            try {
                ArrayList arrayList = this.Y;
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList.get(i);
                    i++;
                    ((InterfaceC0566a) obj).a(z);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void a(InterfaceC0566a interfaceC0566a) {
        synchronized (k0) {
            this.Y.add(interfaceC0566a);
        }
    }

    public boolean d() {
        return this.q.get();
    }

    @TargetApi(16)
    public boolean e(boolean z) {
        if (!this.X.get()) {
            if (!ax.p345v6.n.b()) {
                return z;
            }
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.X.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.q.set(true);
            }
        }
        return d();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AtomicBoolean atomicBoolean = this.X;
        boolean zCompareAndSet = this.q.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (zCompareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        AtomicBoolean atomicBoolean = this.X;
        boolean zCompareAndSet = this.q.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (zCompareAndSet) {
            f(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 20 && this.q.compareAndSet(false, true)) {
            this.X.set(true);
            f(true);
        }
    }
}
