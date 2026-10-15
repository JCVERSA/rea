package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import ax.p265q6.C2254p;
import com.google.android.gms.common.util.DynamiteApi;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
@DynamiteApi
public class AppMeasurementDynamiteService extends ax.H6.K0 {
    E2 q = null;
    private final Map<Integer, ax.M6.t> X = new ax.B.a();

    class a implements ax.M6.t {
        private ax.H6.N0 a;

        a(ax.H6.N0 n0) {
            this.a = n0;
        }

        @Override // ax.M6.t
        public final void a(String str, String str2, Bundle bundle, long j) {
            try {
                this.a.W4(str, str2, bundle, j);
            } catch (RemoteException e) {
                E2 e2 = AppMeasurementDynamiteService.this.q;
                if (e2 != null) {
                    e2.j().L().b("Event listener threw exception", e);
                }
            }
        }
    }

    class b implements ax.M6.r {
        private ax.H6.N0 a;

        b(ax.H6.N0 n0) {
            this.a = n0;
        }

        @Override // ax.M6.r
        public final void a(String str, String str2, Bundle bundle, long j) {
            try {
                this.a.W4(str, str2, bundle, j);
            } catch (RemoteException e) {
                E2 e2 = AppMeasurementDynamiteService.this.q;
                if (e2 != null) {
                    e2.j().L().b("Event interceptor threw exception", e);
                }
            }
        }
    }

    private final void P0(ax.H6.M0 m0, String str) {
        a();
        this.q.L().S(m0, str);
    }

    private final void a() {
        if (this.q == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    @Override // ax.H6.H0
    public void beginAdUnitExposure(String str, long j) throws RemoteException {
        a();
        this.q.y().z(str, j);
    }

    @Override // ax.H6.H0
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) throws RemoteException {
        a();
        this.q.H().g0(str, str2, bundle);
    }

    @Override // ax.H6.H0
    public void clearMeasurementEnabled(long j) throws RemoteException {
        a();
        this.q.H().a0(null);
    }

    @Override // ax.H6.H0
    public void endAdUnitExposure(String str, long j) throws RemoteException {
        a();
        this.q.y().D(str, j);
    }

    @Override // ax.H6.H0
    public void generateEventId(ax.H6.M0 m0) throws RemoteException {
        a();
        long jR0 = this.q.L().R0();
        a();
        this.q.L().Q(m0, jR0);
    }

    @Override // ax.H6.H0
    public void getAppInstanceId(ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.l().D(new V2(this, m0));
    }

    @Override // ax.H6.H0
    public void getCachedAppInstanceId(ax.H6.M0 m0) throws RemoteException {
        a();
        P0(m0, this.q.H().u0());
    }

    @Override // ax.H6.H0
    public void getConditionalUserProperties(String str, String str2, ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.l().D(new N4(this, m0, str, str2));
    }

    @Override // ax.H6.H0
    public void getCurrentScreenClass(ax.H6.M0 m0) throws RemoteException {
        a();
        P0(m0, this.q.H().v0());
    }

    @Override // ax.H6.H0
    public void getCurrentScreenName(ax.H6.M0 m0) throws RemoteException {
        a();
        P0(m0, this.q.H().w0());
    }

    @Override // ax.H6.H0
    public void getGmpAppId(ax.H6.M0 m0) throws RemoteException {
        a();
        P0(m0, this.q.H().x0());
    }

    @Override // ax.H6.H0
    public void getMaxUserProperties(String str, ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.H();
        C6351k3.D(str);
        a();
        this.q.L().P(m0, 25);
    }

    @Override // ax.H6.H0
    public void getSessionId(ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.H().O(m0);
    }

    @Override // ax.H6.H0
    public void getTestFlag(ax.H6.M0 m0, int i) throws RemoteException {
        a();
        if (i == 0) {
            this.q.L().S(m0, this.q.H().y0());
            return;
        }
        if (i == 1) {
            this.q.L().Q(m0, this.q.H().t0().longValue());
            return;
        }
        if (i != 2) {
            if (i == 3) {
                this.q.L().P(m0, this.q.H().s0().intValue());
                return;
            } else {
                if (i != 4) {
                    return;
                }
                this.q.L().U(m0, this.q.H().q0().booleanValue());
                return;
            }
        }
        B5 b5L = this.q.L();
        double dDoubleValue = this.q.H().r0().doubleValue();
        Bundle bundle = new Bundle();
        bundle.putDouble("r", dDoubleValue);
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            b5L.a.j().L().b("Error returning double value to wrapper", e);
        }
    }

    @Override // ax.H6.H0
    public void getUserProperties(String str, String str2, boolean z, ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.l().D(new O3(this, m0, str, str2, z));
    }

    @Override // ax.H6.H0
    public void initForTests(Map map) throws RemoteException {
        a();
    }

    @Override // ax.H6.H0
    public void initialize(ax.p377x6.a aVar, ax.H6.U0 u0, long j) throws RemoteException {
        E2 e2 = this.q;
        if (e2 == null) {
            this.q = E2.c((Context) C2254p.l((Context) ax.p377x6.b.e1(aVar)), u0, Long.valueOf(j));
        } else {
            e2.j().L().a("Attempting to initialize multiple times");
        }
    }

    @Override // ax.H6.H0
    public void isDataCollectionEnabled(ax.H6.M0 m0) throws RemoteException {
        a();
        this.q.l().D(new RunnableC6373n4(this, m0));
    }

    @Override // ax.H6.H0
    public void logEvent(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) throws RemoteException {
        a();
        this.q.H().i0(str, str2, bundle, z, z2, j);
    }

    @Override // ax.H6.H0
    public void logEventAndBundle(String str, String str2, Bundle bundle, ax.H6.M0 m0, long j) throws RemoteException {
        a();
        C2254p.f(str2);
        (bundle != null ? new Bundle(bundle) : new Bundle()).putString("_o", "app");
        this.q.l().D(new RunnableC6424w2(this, m0, new D(str2, new C(bundle), "app", j), str));
    }

    @Override // ax.H6.H0
    public void logHealthData(int i, String str, ax.p377x6.a aVar, ax.p377x6.a aVar2, ax.p377x6.a aVar3) throws RemoteException {
        a();
        this.q.j().z(i, true, false, str, aVar == null ? null : ax.p377x6.b.e1(aVar), aVar2 == null ? null : ax.p377x6.b.e1(aVar2), aVar3 != null ? ax.p377x6.b.e1(aVar3) : null);
    }

    @Override // ax.H6.H0
    public void onActivityCreated(ax.p377x6.a aVar, Bundle bundle, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityCreated((Activity) ax.p377x6.b.e1(aVar), bundle);
        }
    }

    @Override // ax.H6.H0
    public void onActivityDestroyed(ax.p377x6.a aVar, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityDestroyed((Activity) ax.p377x6.b.e1(aVar));
        }
    }

    @Override // ax.H6.H0
    public void onActivityPaused(ax.p377x6.a aVar, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityPaused((Activity) ax.p377x6.b.e1(aVar));
        }
    }

    @Override // ax.H6.H0
    public void onActivityResumed(ax.p377x6.a aVar, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityResumed((Activity) ax.p377x6.b.e1(aVar));
        }
    }

    @Override // ax.H6.H0
    public void onActivitySaveInstanceState(ax.p377x6.a aVar, ax.H6.M0 m0, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        Bundle bundle = new Bundle();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivitySaveInstanceState((Activity) ax.p377x6.b.e1(aVar), bundle);
        }
        try {
            m0.l0(bundle);
        } catch (RemoteException e) {
            this.q.j().L().b("Error returning bundle value to wrapper", e);
        }
    }

    @Override // ax.H6.H0
    public void onActivityStarted(ax.p377x6.a aVar, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityStarted((Activity) ax.p377x6.b.e1(aVar));
        }
    }

    @Override // ax.H6.H0
    public void onActivityStopped(ax.p377x6.a aVar, long j) throws RemoteException {
        a();
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacksO0 = this.q.H().o0();
        if (activityLifecycleCallbacksO0 != null) {
            this.q.H().B0();
            activityLifecycleCallbacksO0.onActivityStopped((Activity) ax.p377x6.b.e1(aVar));
        }
    }

    @Override // ax.H6.H0
    public void performAction(Bundle bundle, ax.H6.M0 m0, long j) throws RemoteException {
        a();
        m0.l0(null);
    }

    @Override // ax.H6.H0
    public void registerOnMeasurementEventListener(ax.H6.N0 n0) throws RemoteException {
        ax.M6.t aVar;
        a();
        synchronized (this.X) {
            try {
                aVar = this.X.get(Integer.valueOf(n0.a()));
                if (aVar == null) {
                    aVar = new a(n0);
                    this.X.put(Integer.valueOf(n0.a()), aVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.q.H().Q(aVar);
    }

    @Override // ax.H6.H0
    public void resetAnalyticsData(long j) throws RemoteException {
        a();
        this.q.H().H(j);
    }

    @Override // ax.H6.H0
    public void setConditionalUserProperty(Bundle bundle, long j) throws RemoteException {
        a();
        if (bundle == null) {
            this.q.j().G().a("Conditional user property must not be null");
        } else {
            this.q.H().L0(bundle, j);
        }
    }

    @Override // ax.H6.H0
    public void setConsent(Bundle bundle, long j) throws RemoteException {
        a();
        this.q.H().V0(bundle, j);
    }

    @Override // ax.H6.H0
    public void setConsentThirdParty(Bundle bundle, long j) throws RemoteException {
        a();
        this.q.H().a1(bundle, j);
    }

    @Override // ax.H6.H0
    public void setCurrentScreen(ax.p377x6.a aVar, String str, String str2, long j) throws RemoteException {
        a();
        this.q.I().H((Activity) ax.p377x6.b.e1(aVar), str, str2);
    }

    @Override // ax.H6.H0
    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        a();
        this.q.H().Z0(z);
    }

    @Override // ax.H6.H0
    public void setDefaultEventParameters(Bundle bundle) {
        a();
        this.q.H().U0(bundle);
    }

    @Override // ax.H6.H0
    public void setEventInterceptor(ax.H6.N0 n0) throws RemoteException {
        a();
        b bVar = new b(n0);
        if (this.q.l().J()) {
            this.q.H().P(bVar);
        } else {
            this.q.l().D(new RunnableC6384p3(this, bVar));
        }
    }

    @Override // ax.H6.H0
    public void setInstanceIdProvider(ax.H6.S0 s0) throws RemoteException {
        a();
    }

    @Override // ax.H6.H0
    public void setMeasurementEnabled(boolean z, long j) throws RemoteException {
        a();
        this.q.H().a0(Boolean.valueOf(z));
    }

    @Override // ax.H6.H0
    public void setMinimumSessionDuration(long j) throws RemoteException {
        a();
    }

    @Override // ax.H6.H0
    public void setSessionTimeoutDuration(long j) throws RemoteException {
        a();
        this.q.H().T0(j);
    }

    @Override // ax.H6.H0
    public void setSgtmDebugInfo(Intent intent) throws RemoteException {
        a();
        this.q.H().J(intent);
    }

    @Override // ax.H6.H0
    public void setUserId(String str, long j) throws RemoteException {
        a();
        this.q.H().c0(str, j);
    }

    @Override // ax.H6.H0
    public void setUserProperty(String str, String str2, ax.p377x6.a aVar, boolean z, long j) throws RemoteException {
        a();
        this.q.H().l0(str, str2, ax.p377x6.b.e1(aVar), z, j);
    }

    @Override // ax.H6.H0
    public void unregisterOnMeasurementEventListener(ax.H6.N0 n0) throws RemoteException {
        ax.M6.t tVarRemove;
        a();
        synchronized (this.X) {
            tVarRemove = this.X.remove(Integer.valueOf(n0.a()));
        }
        if (tVarRemove == null) {
            tVarRemove = new a(n0);
        }
        this.q.H().M0(tVarRemove);
    }
}
