package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import ax.H6.C0960i6;
import ax.H6.C0997m7;
import ax.H6.C1013o6;
import ax.H6.t7;
import ax.M6.C1347a;
import ax.p265q6.C2254p;
import j$.util.Comparator;
import j$.util.Objects;
import j$.util.function.Function$CC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.k3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6351k3 extends AbstractC6286b1 {
    private X3 c;
    private ax.M6.r d;
    private final Set<ax.M6.t> e;
    private boolean f;
    private final AtomicReference<String> g;
    private final Object h;
    private boolean i;
    private int j;
    private AbstractC6397s k;
    private PriorityQueue<C6332h5> l;
    private C6330h3 m;
    private final AtomicLong n;
    private long o;
    final G5 p;
    private boolean q;
    private AbstractC6397s r;
    private SharedPreferences.OnSharedPreferenceChangeListener s;
    private AbstractC6397s t;
    private final D5 u;

    protected C6351k3(E2 e2) {
        super(e2);
        this.e = new CopyOnWriteArraySet();
        this.h = new Object();
        this.i = false;
        this.j = 1;
        this.q = true;
        this.u = new P3(this);
        this.g = new AtomicReference<>();
        this.m = C6330h3.c;
        this.o = -1L;
        this.n = new AtomicLong(0L);
        this.p = new G5(e2);
    }

    public static int D(String str) {
        C2254p.f(str);
        return 25;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void H0() {
        C6351k3 c6351k3;
        n();
        String strA = h().o.a();
        if (strA == null) {
            c6351k3 = this;
        } else if ("unset".equals(strA)) {
            c6351k3 = this;
            c6351k3.j0("app", "_npa", null, b().a());
        } else {
            j0("app", "_npa", Long.valueOf("true".equals(strA) ? 1L : 0L), b().a());
            c6351k3 = this;
        }
        if (!c6351k3.a.p() || !c6351k3.q) {
            j().F().a("Updating Scion state (FE)");
            t().e0();
        } else {
            j().F().a("Recording app launch after enabling measurement for the first time (FE)");
            A0();
            u().e.a();
            l().D(new B3(this));
        }
    }

    private final void M(Bundle bundle, int i, long j) {
        v();
        String strK = C6330h3.k(bundle);
        if (strK != null) {
            j().M().b("Ignoring invalid consent setting", strK);
            j().M().a("Valid consent values are 'granted', 'denied'");
        }
        boolean zJ = l().J();
        C6330h3 c6330h3F = C6330h3.f(bundle, i);
        if (c6330h3F.C()) {
            T(c6330h3F, j, zJ);
        }
        C6409u c6409uB = C6409u.b(bundle, i);
        if (c6409uB.k()) {
            R(c6409uB, zJ);
        }
        Boolean boolE = C6409u.e(bundle);
        if (boolE != null) {
            k0(i == -30 ? "tcf" : "app", "allow_personalized_ads", boolE.toString(), false);
        }
    }

    static /* synthetic */ void N0(C6351k3 c6351k3, int i) {
        if (c6351k3.k == null) {
            c6351k3.k = new C6443z3(c6351k3, c6351k3.a);
        }
        c6351k3.k.b(i * 1000);
    }

    static /* synthetic */ void O0(C6351k3 c6351k3, Bundle bundle) {
        c6351k3.n();
        c6351k3.v();
        C2254p.l(bundle);
        String string = bundle.getString("name");
        String string2 = bundle.getString("origin");
        C2254p.f(string);
        C2254p.f(string2);
        C2254p.l(bundle.get("value"));
        if (!c6351k3.a.p()) {
            c6351k3.j().K().a("Conditional property not set since app measurement is disabled");
            return;
        }
        A5 a5 = new A5(string, bundle.getLong("triggered_timestamp"), bundle.get("value"), string2);
        try {
            D dH = c6351k3.i().H(bundle.getString("app_id"), bundle.getString("triggered_event_name"), bundle.getBundle("triggered_event_params"), string2, 0L, true, true);
            c6351k3.t().J(new C6312f(bundle.getString("app_id"), string2, a5, bundle.getLong("creation_timestamp"), false, bundle.getString("trigger_event_name"), c6351k3.i().H(bundle.getString("app_id"), bundle.getString("timed_out_event_name"), bundle.getBundle("timed_out_event_params"), string2, 0L, true, true), bundle.getLong("trigger_timeout"), dH, bundle.getLong("time_to_live"), c6351k3.i().H(bundle.getString("app_id"), bundle.getString("expired_event_name"), bundle.getBundle("expired_event_params"), string2, 0L, true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }

    private final void Q0(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        l().D(new C3(this, str, str2, j, B5.D(bundle), z, z2, z3, str3));
    }

    static /* synthetic */ void V(C6351k3 c6351k3, Bundle bundle) {
        c6351k3.n();
        c6351k3.v();
        C2254p.l(bundle);
        String strF = C2254p.f(bundle.getString("name"));
        if (!c6351k3.a.p()) {
            c6351k3.j().K().a("Conditional property not cleared since app measurement is disabled");
            return;
        }
        try {
            c6351k3.t().J(new C6312f(bundle.getString("app_id"), HttpUrl.FRAGMENT_ENCODE_SET, new A5(strF, 0L, null, HttpUrl.FRAGMENT_ENCODE_SET), bundle.getLong("creation_timestamp"), bundle.getBoolean("active"), bundle.getString("trigger_event_name"), null, bundle.getLong("trigger_timeout"), null, bundle.getLong("time_to_live"), c6351k3.i().H(bundle.getString("app_id"), bundle.getString("expired_event_name"), bundle.getBundle("expired_event_params"), HttpUrl.FRAGMENT_ENCODE_SET, bundle.getLong("creation_timestamp"), true, true)));
        } catch (IllegalArgumentException unused) {
        }
    }

    static /* synthetic */ void W(C6351k3 c6351k3, C6330h3 c6330h3, long j, boolean z, boolean z2) {
        c6351k3.n();
        c6351k3.v();
        C6330h3 c6330h3M = c6351k3.h().M();
        if (j <= c6351k3.o && C6330h3.l(c6330h3M.b(), c6330h3.b())) {
            c6351k3.j().J().b("Dropped out-of-date consent setting, proposed settings", c6330h3);
            return;
        }
        if (!c6351k3.h().B(c6330h3)) {
            c6351k3.j().J().b("Lower precedence consent source ignored, proposed source", Integer.valueOf(c6330h3.b()));
            return;
        }
        c6351k3.j().K().b("Setting storage consent(FE)", c6330h3);
        c6351k3.o = j;
        if (c6351k3.t().i0()) {
            c6351k3.t().n0(z);
        } else {
            c6351k3.t().U(z);
        }
        if (z2) {
            c6351k3.t().Q(new AtomicReference<>());
        }
    }

    static /* synthetic */ void X(C6351k3 c6351k3, C6330h3 c6330h3, C6330h3 c6330h4) {
        if (C1013o6.a() && c6351k3.d().t(F.V0)) {
            return;
        }
        C6330h3.a aVar = C6330h3.a.ANALYTICS_STORAGE;
        C6330h3.a aVar2 = C6330h3.a.AD_STORAGE;
        boolean zN = c6330h3.n(c6330h4, aVar, aVar2);
        boolean zS = c6330h3.s(c6330h4, aVar, aVar2);
        if (zN || zS) {
            c6351k3.p().I();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void b0(Boolean bool, boolean z) {
        n();
        v();
        j().F().b("Setting app measurement enabled (FE)", bool);
        h().w(bool);
        if (z) {
            h().E(bool);
        }
        if (this.a.q() || !(bool == null || bool.booleanValue())) {
            H0();
        }
    }

    private final void f0(String str, String str2, long j, Object obj) {
        l().D(new E3(this, str, str2, obj, j));
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return false;
    }

    public final void A0() {
        n();
        v();
        if (this.a.s()) {
            Boolean boolD = d().D("google_analytics_deferred_deep_link_enabled");
            if (boolD != null && boolD.booleanValue()) {
                j().F().a("Deferred Deep Link feature enabled.");
                l().D(new Runnable() { // from class: ax.M6.x
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.D0();
                    }
                });
            }
            t().X();
            this.q = false;
            String strQ = h().Q();
            if (TextUtils.isEmpty(strQ)) {
                return;
            }
            e().p();
            if (strQ.equals(Build.VERSION.RELEASE)) {
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("_po", strQ);
            Y0("auto", "_ou", bundle);
        }
    }

    public final void B0() {
        if (!(a().getApplicationContext() instanceof Application) || this.c == null) {
            return;
        }
        ((Application) a().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.c);
    }

    final void C0() {
        if (C0997m7.a() && d().t(F.B0)) {
            if (l().J()) {
                j().G().a("Cannot get trigger URIs from analytics worker thread");
                return;
            }
            if (C6291c.a()) {
                j().G().a("Cannot get trigger URIs from main thread");
                return;
            }
            v();
            j().K().a("Getting trigger URIs (FE)");
            final AtomicReference atomicReference = new AtomicReference();
            l().v(atomicReference, 5000L, "get trigger URIs", new Runnable() { // from class: com.google.android.gms.measurement.internal.q3
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.n0(atomicReference);
                }
            });
            final List list = (List) atomicReference.get();
            if (list == null) {
                j().G().a("Timed out waiting for get trigger URIs");
            } else {
                l().D(new Runnable() { // from class: com.google.android.gms.measurement.internal.o3
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.m0(list);
                    }
                });
            }
        }
    }

    public final void D0() {
        n();
        if (h().v.b()) {
            j().F().a("Deferred Deep Link already retrieved. Not fetching again.");
            return;
        }
        long jA = h().w.a();
        h().w.b(1 + jA);
        if (jA >= 5) {
            j().L().a("Permanently failed to retrieve Deferred Deep Link. Reached maximum retries.");
            h().v.a(true);
        } else {
            if (this.r == null) {
                this.r = new F3(this, this.a);
            }
            this.r.b(0L);
        }
    }

    public final void E0() {
        n();
        j().F().a("Handle tcf update.");
        C6318f5 c6318f5C = C6318f5.c(h().H());
        j().K().b("Tcf preferences read", c6318f5C);
        if (h().C(c6318f5C)) {
            Bundle bundleB = c6318f5C.b();
            j().K().b("Consent generated from Tcf", bundleB);
            if (bundleB != Bundle.EMPTY) {
                M(bundleB, -30, b().a());
            }
            Bundle bundle = new Bundle();
            bundle.putString("_tcfd", c6318f5C.e());
            Y0("auto", "_tcf", bundle);
        }
    }

    public final ArrayList<Bundle> F(String str, String str2) {
        if (l().J()) {
            j().G().a("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList<>(0);
        }
        if (C6291c.a()) {
            j().G().a("Cannot get conditional user properties from main thread");
            return new ArrayList<>(0);
        }
        AtomicReference atomicReference = new AtomicReference();
        this.a.l().v(atomicReference, 5000L, "get conditional user properties", new J3(this, atomicReference, null, str, str2));
        List list = (List) atomicReference.get();
        if (list != null) {
            return B5.t0(list);
        }
        j().G().b("Timed out waiting for get conditional user properties", null);
        return new ArrayList<>();
    }

    @TargetApi(30)
    final void F0() {
        C6332h5 c6332h5Poll;
        ax.p069e2.a aVarS0;
        n();
        if (z0().isEmpty() || this.i || (c6332h5Poll = z0().poll()) == null || (aVarS0 = i().S0()) == null) {
            return;
        }
        this.i = true;
        j().K().b("Registering trigger URI", c6332h5Poll.q);
        ax.p043c8.e<ax.p111gc.t> eVarD = aVarS0.d(Uri.parse(c6332h5Poll.q));
        if (eVarD == null) {
            this.i = false;
            z0().add(c6332h5Poll);
            return;
        }
        if (!d().t(F.G0)) {
            SparseArray<Long> sparseArrayK = h().K();
            sparseArrayK.put(c6332h5Poll.Y, Long.valueOf(c6332h5Poll.X));
            h().v(sparseArrayK);
        }
        ax.p043c8.c.a(eVarD, new C6425w3(this, c6332h5Poll), new ExecutorC6431x3(this));
    }

    public final Map<String, Object> G(String str, String str2, boolean z) {
        if (l().J()) {
            j().G().a("Cannot get user properties from analytics worker thread");
            return Collections.EMPTY_MAP;
        }
        if (C6291c.a()) {
            j().G().a("Cannot get user properties from main thread");
            return Collections.EMPTY_MAP;
        }
        AtomicReference atomicReference = new AtomicReference();
        this.a.l().v(atomicReference, 5000L, "get user properties", new M3(this, atomicReference, null, str, str2, z));
        List<A5> list = (List) atomicReference.get();
        if (list == null) {
            j().G().b("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.EMPTY_MAP;
        }
        ax.B.a aVar = new ax.B.a(list.size());
        for (A5 a5 : list) {
            Object objA = a5.a();
            if (objA != null) {
                aVar.put(a5.X, objA);
            }
        }
        return aVar;
    }

    public final void G0() {
        n();
        j().F().a("Register tcfPrefChangeListener.");
        if (this.s == null) {
            this.t = new D3(this, this.a);
            this.s = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.gms.measurement.internal.t3
                @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
                public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                    this.q.K(sharedPreferences, str);
                }
            };
        }
        h().H().registerOnSharedPreferenceChangeListener(this.s);
    }

    public final void H(long j) {
        X0(null);
        l().D(new I3(this, j));
    }

    final void I(long j, boolean z) {
        n();
        v();
        j().F().a("Resetting analytics data (FE)");
        V4 v4U = u();
        v4U.n();
        v4U.f.b();
        p().I();
        boolean zP = this.a.p();
        C6322g2 c6322g2H = h();
        c6322g2H.g.b(j);
        if (!TextUtils.isEmpty(c6322g2H.h().x.a())) {
            c6322g2H.x.b(null);
        }
        c6322g2H.r.b(0L);
        c6322g2H.s.b(0L);
        if (!c6322g2H.d().U()) {
            c6322g2H.G(!zP);
        }
        c6322g2H.y.b(null);
        c6322g2H.z.b(0L);
        c6322g2H.A.b(null);
        if (z) {
            t().c0();
        }
        u().e.a();
        this.q = !zP;
    }

    public final void J(Intent intent) {
        if (t7.a() && d().t(F.u0)) {
            Uri data = intent.getData();
            if (data == null) {
                j().J().a("Activity intent has no data. Preview Mode was not enabled.");
                return;
            }
            String queryParameter = data.getQueryParameter("sgtm_debug_enable");
            if (queryParameter == null || !queryParameter.equals("1")) {
                j().J().a("Preview Mode was not enabled.");
                d().K(null);
                return;
            }
            String queryParameter2 = data.getQueryParameter("sgtm_preview_key");
            if (TextUtils.isEmpty(queryParameter2)) {
                return;
            }
            j().J().b("Preview Mode was enabled. Using the sgtmPreviewKey: ", queryParameter2);
            d().K(queryParameter2);
        }
    }

    final void J0(long j) {
        I(j, true);
    }

    final /* synthetic */ void K(SharedPreferences sharedPreferences, String str) {
        if ("IABTCF_TCString".equals(str)) {
            j().K().a("IABTCF_TCString change picked up in listener.");
            ((AbstractC6397s) C2254p.l(this.t)).b(500L);
        }
    }

    public final void K0(Bundle bundle) {
        L0(bundle, b().a());
    }

    final /* synthetic */ void L(Bundle bundle) {
        if (bundle == null) {
            h().A.b(new Bundle());
            return;
        }
        Bundle bundleA = h().A.a();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj != null && !(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Double)) {
                i();
                if (B5.h0(obj)) {
                    i();
                    B5.Y(this.u, 27, null, null, 0);
                }
                j().M().c("Invalid default event parameter type. Name, value", str, obj);
            } else if (B5.J0(str)) {
                j().M().b("Invalid default event parameter name. Name", str);
            } else if (obj == null) {
                bundleA.remove(str);
            } else if (i().l0("param", str, d().r(null, false), obj)) {
                i().O(bundleA, str, obj);
            }
        }
        i();
        if (B5.g0(bundleA, d().H())) {
            i();
            B5.Y(this.u, 26, null, null, 0);
            j().M().a("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        h().A.b(bundleA);
        t().C(bundleA);
    }

    public final void L0(Bundle bundle, long j) {
        C2254p.l(bundle);
        Bundle bundle2 = new Bundle(bundle);
        if (!TextUtils.isEmpty(bundle2.getString("app_id"))) {
            j().L().a("Package name should be null when calling setConditionalUserProperty");
        }
        bundle2.remove("app_id");
        C2254p.l(bundle2);
        ax.M6.n.a(bundle2, "app_id", String.class, null);
        ax.M6.n.a(bundle2, "origin", String.class, null);
        ax.M6.n.a(bundle2, "name", String.class, null);
        ax.M6.n.a(bundle2, "value", Object.class, null);
        ax.M6.n.a(bundle2, "trigger_event_name", String.class, null);
        ax.M6.n.a(bundle2, "trigger_timeout", Long.class, 0L);
        ax.M6.n.a(bundle2, "timed_out_event_name", String.class, null);
        ax.M6.n.a(bundle2, "timed_out_event_params", Bundle.class, null);
        ax.M6.n.a(bundle2, "triggered_event_name", String.class, null);
        ax.M6.n.a(bundle2, "triggered_event_params", Bundle.class, null);
        ax.M6.n.a(bundle2, "time_to_live", Long.class, 0L);
        ax.M6.n.a(bundle2, "expired_event_name", String.class, null);
        ax.M6.n.a(bundle2, "expired_event_params", Bundle.class, null);
        C2254p.f(bundle2.getString("name"));
        C2254p.f(bundle2.getString("origin"));
        C2254p.l(bundle2.get("value"));
        bundle2.putLong("creation_timestamp", j);
        String string = bundle2.getString("name");
        Object obj = bundle2.get("value");
        if (i().r0(string) != 0) {
            j().G().b("Invalid conditional user property name", g().g(string));
            return;
        }
        if (i().w(string, obj) != 0) {
            j().G().c("Invalid conditional user property value", g().g(string), obj);
            return;
        }
        Object objA0 = i().A0(string, obj);
        if (objA0 == null) {
            j().G().c("Unable to normalize conditional user property value", g().g(string), obj);
            return;
        }
        ax.M6.n.b(bundle2, objA0);
        long j2 = bundle2.getLong("trigger_timeout");
        if (!TextUtils.isEmpty(bundle2.getString("trigger_event_name")) && (j2 > 15552000000L || j2 < 1)) {
            j().G().c("Invalid conditional user property timeout", g().g(string), Long.valueOf(j2));
            return;
        }
        long j3 = bundle2.getLong("time_to_live");
        if (j3 > 15552000000L || j3 < 1) {
            j().G().c("Invalid conditional user property time to live", g().g(string), Long.valueOf(j3));
        } else {
            l().D(new H3(this, bundle2));
        }
    }

    public final void M0(ax.M6.t tVar) {
        v();
        C2254p.l(tVar);
        if (this.e.remove(tVar)) {
            return;
        }
        j().L().a("OnEventListener had not been registered");
    }

    final /* synthetic */ void N(Bundle bundle, long j) {
        if (TextUtils.isEmpty(p().G())) {
            M(bundle, 0, j);
        } else {
            j().M().a("Using developer consent only; google app id found");
        }
    }

    public final void O(ax.H6.M0 m0) throws RemoteException {
        l().D(new L3(this, m0));
    }

    public final void P(ax.M6.r rVar) {
        ax.M6.r rVar2;
        n();
        v();
        if (rVar != null && rVar != (rVar2 = this.d)) {
            C2254p.p(rVar2 == null, "EventInterceptor already set.");
        }
        this.d = rVar;
    }

    final /* synthetic */ void P0(String str) {
        if (p().K(str)) {
            p().I();
        }
    }

    public final void Q(ax.M6.t tVar) {
        v();
        C2254p.l(tVar);
        if (this.e.add(tVar)) {
            return;
        }
        j().L().a("OnEventListener already registered");
    }

    final void R(C6409u c6409u, boolean z) {
        S3 s3 = new S3(this, c6409u);
        if (!z) {
            l().D(s3);
        } else {
            n();
            s3.run();
        }
    }

    public final void R0(String str, String str2, Bundle bundle) {
        i0(str, str2, bundle, true, true, b().a());
    }

    final void S(C6330h3 c6330h3) {
        n();
        boolean z = (c6330h3.B() && c6330h3.A()) || t().h0();
        if (z != this.a.q()) {
            this.a.w(z);
            Boolean boolO = h().O();
            if (!z || boolO == null || boolO.booleanValue()) {
                b0(Boolean.valueOf(z), false);
            }
        }
    }

    public final void S0(boolean z) {
        if (a().getApplicationContext() instanceof Application) {
            Application application = (Application) a().getApplicationContext();
            if (this.c == null) {
                this.c = new X3(this);
            }
            if (z) {
                application.unregisterActivityLifecycleCallbacks(this.c);
                application.registerActivityLifecycleCallbacks(this.c);
                j().K().a("Registered activity lifecycle callback");
            }
        }
    }

    public final void T(C6330h3 c6330h3, long j, boolean z) {
        C6330h3 c6330h4;
        boolean z2;
        C6330h3 c6330h3P;
        boolean zU;
        boolean z3;
        v();
        int iB = c6330h3.b();
        if (C0960i6.a() && d().t(F.R0)) {
            if (iB != -10) {
                ax.M6.o oVarT = c6330h3.t();
                ax.M6.o oVar = ax.M6.o.UNINITIALIZED;
                if (oVarT == oVar && c6330h3.v() == oVar) {
                    j().M().a("Ignoring empty consent settings");
                    return;
                }
            }
        } else if (iB != -10 && c6330h3.w() == null && c6330h3.x() == null) {
            j().M().a("Discarding empty consent settings");
            return;
        }
        synchronized (this.h) {
            try {
                c6330h4 = this.m;
                z2 = false;
                if (C6330h3.l(iB, c6330h4.b())) {
                    zU = c6330h3.u(this.m);
                    if (c6330h3.B() && !this.m.B()) {
                        z2 = true;
                    }
                    c6330h3P = c6330h3.p(this.m);
                    this.m = c6330h3P;
                    z3 = z2;
                    z2 = true;
                } else {
                    c6330h3P = c6330h3;
                    zU = false;
                    z3 = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z2) {
            j().J().b("Ignoring lower-priority consent settings, proposed settings", c6330h3P);
            return;
        }
        long andIncrement = this.n.getAndIncrement();
        if (zU) {
            X0(null);
            V3 v3 = new V3(this, c6330h3P, j, andIncrement, z3, c6330h4);
            if (!z) {
                l().G(v3);
                return;
            } else {
                n();
                v3.run();
                return;
            }
        }
        U3 u3 = new U3(this, c6330h3P, andIncrement, z3, c6330h4);
        if (z) {
            n();
            u3.run();
        } else if (iB == 30 || iB == -10) {
            l().G(u3);
        } else {
            l().D(u3);
        }
    }

    public final void T0(long j) {
        l().D(new A3(this, j));
    }

    public final void U0(Bundle bundle) {
        final Bundle bundle2 = bundle == null ? null : new Bundle(bundle);
        l().D(new Runnable() { // from class: com.google.android.gms.measurement.internal.s3
            @Override // java.lang.Runnable
            public final void run() {
                this.q.L(bundle2);
            }
        });
    }

    public final void V0(final Bundle bundle, final long j) {
        l().G(new Runnable() { // from class: com.google.android.gms.measurement.internal.r3
            @Override // java.lang.Runnable
            public final void run() {
                this.q.N(bundle, j);
            }
        });
    }

    final void X0(String str) {
        this.g.set(str);
    }

    final void Y0(String str, String str2, Bundle bundle) {
        n();
        d0(str, str2, b().a(), bundle);
    }

    public final void Z0(boolean z) {
        v();
        l().D(new RunnableC6437y3(this, z));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    public final void a0(Boolean bool) {
        v();
        l().D(new T3(this, bool));
    }

    public final void a1(Bundle bundle, long j) {
        M(bundle, -20, j);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    public final void c0(final String str, long j) {
        if (str != null && TextUtils.isEmpty(str)) {
            this.a.j().L().a("User ID must be non-empty or null");
        } else {
            l().D(new Runnable() { // from class: com.google.android.gms.measurement.internal.v3
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.P0(str);
                }
            });
            l0(null, "_id", str, true, j);
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    final void d0(String str, String str2, long j, Bundle bundle) {
        n();
        e0(str, str2, j, bundle, true, this.d == null || B5.J0(str2), true, null);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    protected final void e0(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        boolean z4;
        long j2;
        long j3;
        String str4;
        int i;
        int length;
        String str5 = str;
        C2254p.f(str5);
        C2254p.l(bundle);
        n();
        v();
        if (!this.a.p()) {
            j().F().a("Event not sent since app measurement is disabled");
            return;
        }
        List<String> listH = p().H();
        if (listH != null && !listH.contains(str2)) {
            j().F().c("Dropping non-safelisted event. event name, origin", str2, str5);
            return;
        }
        if (!this.f) {
            this.f = true;
            try {
                try {
                    (!this.a.t() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, a().getClassLoader()) : Class.forName("com.google.android.gms.tagmanager.TagManagerService")).getDeclaredMethod("initialize", Context.class).invoke(null, a());
                } catch (Exception e) {
                    j().L().b("Failed to invoke Tag Manager's initialize() method", e);
                }
            } catch (ClassNotFoundException unused) {
                j().J().a("Tag Manager is not found and thus will not be used");
            }
        }
        if ("_cmp".equals(str2) && bundle.containsKey("gclid")) {
            j0("auto", "_lgclid", bundle.getString("gclid"), b().a());
        }
        C6351k3 c6351k3 = this;
        if (z && B5.N0(str2)) {
            c6351k3.i().N(bundle, c6351k3.h().A.a());
        }
        if (!z3 && !"_iap".equals(str2)) {
            B5 b5L = c6351k3.a.L();
            int i2 = 2;
            if (b5L.C0("event", str2)) {
                if (!b5L.p0("event", ax.M6.q.a, ax.M6.q.b, str2)) {
                    i2 = 13;
                } else if (b5L.j0("event", 40, str2)) {
                    i2 = 0;
                }
            }
            if (i2 != 0) {
                c6351k3.j().H().b("Invalid public event name. Event will not be logged (FE)", c6351k3.g().c(str2));
                c6351k3.a.L();
                String strJ = B5.J(str2, 40, true);
                length = str2 != null ? str2.length() : 0;
                c6351k3.a.L();
                B5.Y(c6351k3.u, i2, "_ev", strJ, length);
                return;
            }
        }
        C6303d4 c6303d4C = c6351k3.s().C(false);
        if (c6303d4C != null && !bundle.containsKey("_sc")) {
            c6303d4C.d = true;
        }
        B5.X(c6303d4C, bundle, z && !z3);
        boolean zEquals = "am".equals(str5);
        boolean zJ0 = B5.J0(str2);
        if (z && c6351k3.d != null && !zJ0 && !zEquals) {
            c6351k3.j().F().c("Passing event to registered event handler (FE)", c6351k3.g().c(str2), c6351k3.g().a(bundle));
            C2254p.l(c6351k3.d);
            c6351k3.d.a(str5, str2, bundle, j);
            return;
        }
        long j4 = j;
        if (c6351k3.a.s()) {
            int iV = c6351k3.i().v(str2);
            if (iV != 0) {
                c6351k3.j().H().b("Invalid event name. Event will not be logged (FE)", c6351k3.g().c(str2));
                c6351k3.i();
                String strJ2 = B5.J(str2, 40, true);
                length = str2 != null ? str2.length() : 0;
                c6351k3.a.L();
                B5.Z(c6351k3.u, str3, iV, "_ev", strJ2, length);
                return;
            }
            Bundle bundleF = c6351k3.i().F(str3, str2, bundle, ax.p345v6.g.b("_o", "_sn", "_sc", "_si"), z3);
            C2254p.l(bundleF);
            if (c6351k3.s().C(false) == null || !"_ae".equals(str2)) {
                z4 = zEquals;
                j2 = 0;
            } else {
                C6283a5 c6283a5 = c6351k3.u().f;
                j2 = 0;
                long jB = c6283a5.d.b().b();
                z4 = zEquals;
                long j5 = jB - c6283a5.b;
                c6283a5.b = jB;
                if (j5 > 0) {
                    c6351k3.i().M(bundleF, j5);
                }
            }
            if (!"auto".equals(str5) && "_ssr".equals(str2)) {
                B5 b5I = c6351k3.i();
                String string = bundleF.getString("_ffr");
                if (ax.p345v6.s.a(string)) {
                    string = null;
                } else if (string != null) {
                    string = string.trim();
                }
                if (Objects.equals(string, b5I.h().x.a())) {
                    b5I.j().F().a("Not logging duplicate session_start_with_rollout event");
                    return;
                }
                b5I.h().x.b(string);
            } else if ("_ae".equals(str2)) {
                String strA = c6351k3.i().h().x.a();
                if (!TextUtils.isEmpty(strA)) {
                    bundleF.putString("_ffr", strA);
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(bundleF);
            boolean zF = c6351k3.d().t(F.H0) ? c6351k3.u().F() : c6351k3.h().u.b();
            if (c6351k3.h().r.a() > j2 && c6351k3.h().z(j4) && zF) {
                c6351k3.j().K().a("Current session is expired, remove the session number, ID, and engagement time");
                j3 = j2;
                str4 = "_ae";
                j0("auto", "_sid", null, c6351k3.b().a());
                j0("auto", "_sno", null, b().a());
                j0("auto", "_se", null, b().a());
                c6351k3 = this;
                c6351k3.h().s.b(j3);
            } else {
                j3 = j2;
                str4 = "_ae";
            }
            if (bundleF.getLong("extend_session", j3) == 1) {
                c6351k3.j().K().a("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                i = 1;
                c6351k3.a.K().e.b(j4, true);
            } else {
                i = 1;
            }
            ArrayList arrayList2 = new ArrayList(bundleF.keySet());
            Collections.sort(arrayList2);
            int size = arrayList2.size();
            int i3 = 0;
            while (i3 < size) {
                Object obj = arrayList2.get(i3);
                i3 += i;
                String str6 = (String) obj;
                if (str6 != null) {
                    c6351k3.i();
                    Bundle[] bundleArrX0 = B5.x0(bundleF.get(str6));
                    if (bundleArrX0 != null) {
                        bundleF.putParcelableArray(str6, bundleArrX0);
                    }
                }
                i = 1;
            }
            int i4 = 0;
            while (i4 < arrayList.size()) {
                Bundle bundleE = (Bundle) arrayList.get(i4);
                String str7 = i4 != 0 ? "_ep" : str2;
                bundleE.putString("_o", str5);
                if (z2) {
                    bundleE = c6351k3.i().E(bundleE, null);
                }
                String str8 = str5;
                Bundle bundle2 = bundleE;
                c6351k3.t().K(new D(str7, new C(bundleE), str8, j4), str3);
                if (!z4) {
                    Iterator<ax.M6.t> it = c6351k3.e.iterator();
                    while (it.hasNext()) {
                        it.next().a(str, str2, new Bundle(bundle2), j);
                    }
                }
                i4++;
                str5 = str;
                j4 = j;
            }
            if (c6351k3.s().C(false) == null || !str4.equals(str2)) {
                return;
            }
            c6351k3.u().E(true, true, c6351k3.b().b());
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    public final void g0(String str, String str2, Bundle bundle) {
        long jA = b().a();
        C2254p.f(str);
        Bundle bundle2 = new Bundle();
        bundle2.putString("name", str);
        bundle2.putLong("creation_timestamp", jA);
        if (str2 != null) {
            bundle2.putString("expired_event_name", str2);
            bundle2.putBundle("expired_event_params", bundle);
        }
        l().D(new K3(this, bundle2));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    public final void h0(String str, String str2, Bundle bundle, String str3) {
        m();
        Q0(str, str2, b().a(), bundle, false, true, true, str3);
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    public final void i0(String str, String str2, Bundle bundle, boolean z, boolean z2, long j) {
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        if (Objects.equals(str2, "screen_view")) {
            s().I(bundle2, j);
        } else {
            Q0(str3, str2, j, bundle2, z2, !z2 || this.d == null || B5.J0(str2), z, null);
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0051 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:18:0x0053  */
    final void j0(String str, String str2, Object obj, long j) {
        C2254p.f(str);
        C2254p.f(str2);
        n();
        v();
        if ("allow_personalized_ads".equals(str2)) {
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!TextUtils.isEmpty(str3)) {
                    Long lValueOf = Long.valueOf("false".equals(str3.toLowerCase(Locale.ENGLISH)) ? 1L : 0L);
                    h().o.b(lValueOf.longValue() == 1 ? "true" : "false");
                    obj = lValueOf;
                } else if (obj == null) {
                    h().o.b("unset");
                }
                str2 = "_npa";
            } else if (obj == null) {
                h().o.b("unset");
                str2 = "_npa";
            }
            j().K().c("Setting user property(FE)", "non_personalized_ads(_npa)", obj);
        }
        String str4 = str2;
        Object obj2 = obj;
        if (!this.a.p()) {
            j().K().a("User property not set since app measurement is disabled");
        } else if (this.a.s()) {
            t().O(new A5(str4, j, obj2, str));
        }
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    public final void k0(String str, String str2, Object obj, boolean z) {
        l0(str, str2, obj, z, b().a());
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0020  */
    public final void l0(String str, String str2, Object obj, boolean z, long j) {
        int iR0;
        int length;
        if (str == null) {
            str = "app";
        }
        String str3 = str;
        if (z) {
            iR0 = i().r0(str2);
        } else {
            B5 b5I = i();
            if (!b5I.C0("user property", str2)) {
                iR0 = 6;
            } else if (!b5I.o0("user property", ax.M6.s.a, str2)) {
                iR0 = 15;
            } else if (b5I.j0("user property", 24, str2)) {
                iR0 = 0;
            } else {
                iR0 = 6;
            }
        }
        if (iR0 != 0) {
            i();
            String strJ = B5.J(str2, 24, true);
            length = str2 != null ? str2.length() : 0;
            this.a.L();
            B5.Y(this.u, iR0, "_ev", strJ, length);
            return;
        }
        if (obj == null) {
            f0(str3, str2, j, null);
            return;
        }
        int iW = i().w(str2, obj);
        if (iW == 0) {
            Object objA0 = i().A0(str2, obj);
            if (objA0 != null) {
                f0(str3, str2, j, objA0);
                return;
            }
            return;
        }
        i();
        String strJ2 = B5.J(str2, 24, true);
        length = ((obj instanceof String) || (obj instanceof CharSequence)) ? String.valueOf(obj).length() : 0;
        this.a.L();
        B5.Y(this.u, iW, "_ev", strJ2, length);
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    final /* synthetic */ void m0(List list) {
        n();
        if (Build.VERSION.SDK_INT >= 30) {
            SparseArray<Long> sparseArrayK = h().K();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C6332h5 c6332h5 = (C6332h5) it.next();
                if (!sparseArrayK.contains(c6332h5.Y) || sparseArrayK.get(c6332h5.Y).longValue() < c6332h5.X) {
                    z0().add(c6332h5);
                }
            }
            F0();
        }
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    final /* synthetic */ void n0(AtomicReference atomicReference) {
        Bundle bundleA = h().p.a();
        C6352k4 c6352k4T = t();
        if (bundleA == null) {
            bundleA = new Bundle();
        }
        c6352k4T.R(atomicReference, bundleA);
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6439z o() {
        return super.o();
    }

    public final Application.ActivityLifecycleCallbacks o0() {
        return this.c;
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ Q1 p() {
        return super.p();
    }

    public final C1347a p0() {
        n();
        return t().V();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ P1 q() {
        return super.q();
    }

    public final Boolean q0() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) l().v(atomicReference, 15000L, "boolean test flag value", new RunnableC6413u3(this, atomicReference));
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6351k3 r() {
        return super.r();
    }

    public final Double r0() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) l().v(atomicReference, 15000L, "double test flag value", new Q3(this, atomicReference));
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6317f4 s() {
        return super.s();
    }

    public final Integer s0() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) l().v(atomicReference, 15000L, "int test flag value", new R3(this, atomicReference));
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6352k4 t() {
        return super.t();
    }

    public final Long t0() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) l().v(atomicReference, 15000L, "long test flag value", new N3(this, atomicReference));
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ V4 u() {
        return super.u();
    }

    public final String u0() {
        return this.g.get();
    }

    public final String v0() {
        C6303d4 c6303d4P = this.a.I().P();
        if (c6303d4P != null) {
            return c6303d4P.b;
        }
        return null;
    }

    public final String w0() {
        C6303d4 c6303d4P = this.a.I().P();
        if (c6303d4P != null) {
            return c6303d4P.a;
        }
        return null;
    }

    public final String x0() {
        if (this.a.M() != null) {
            return this.a.M();
        }
        try {
            return new ax.M6.m(a(), this.a.P()).b("google_app_id");
        } catch (IllegalStateException e) {
            this.a.j().G().b("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    public final String y0() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) l().v(atomicReference, 15000L, "String test flag value", new G3(this, atomicReference));
    }

    @TargetApi(30)
    final PriorityQueue<C6332h5> z0() {
        if (this.l == null) {
            ax.M6.w.a();
            this.l = ax.M6.v.a(Comparator.CC.comparing(new Function() { // from class: com.google.android.gms.measurement.internal.n3
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((C6332h5) obj).X);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }
            }, new java.util.Comparator() { // from class: com.google.android.gms.measurement.internal.m3
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return Long.compare(((Long) obj).longValue(), ((Long) obj2).longValue());
                }
            }));
        }
        return this.l;
    }
}
