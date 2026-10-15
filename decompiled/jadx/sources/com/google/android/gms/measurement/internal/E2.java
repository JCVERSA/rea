package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import ax.H6.AbstractC0885a3;
import ax.H6.C0960i6;
import ax.H6.C0997m7;
import ax.H6.O6;
import ax.M6.C1347a;
import ax.p265q6.C2254p;
import j$.util.Objects;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class E2 implements InterfaceC6323g3 {
    private static volatile E2 I;
    private volatile Boolean A;
    private Boolean B;
    private Boolean C;
    private volatile boolean D;
    private int E;
    private int F;
    final long H;
    private final Context a;
    private final String b;
    private final String c;
    private final String d;
    private final boolean e;
    private final C6291c f;
    private final C6326h g;
    private final C6322g2 h;
    private final V1 i;
    private final C6442z2 j;
    private final V4 k;
    private final B5 l;
    private final R1 m;
    private final ax.p345v6.f n;
    private final C6317f4 o;
    private final C6351k3 p;
    private final C6439z q;
    private final Y3 r;
    private final String s;
    private P1 t;
    private C6352k4 u;
    private C6421w v;
    private Q1 w;
    private Boolean y;
    private long z;
    private boolean x = false;
    private AtomicInteger G = new AtomicInteger(0);

    private E2(C6344j3 c6344j3) {
        Bundle bundle;
        boolean z = false;
        C2254p.l(c6344j3);
        C6291c c6291c = new C6291c(c6344j3.a);
        this.f = c6291c;
        N1.a = c6291c;
        Context context = c6344j3.a;
        this.a = context;
        this.b = c6344j3.b;
        this.c = c6344j3.c;
        this.d = c6344j3.d;
        this.e = c6344j3.h;
        this.A = c6344j3.e;
        this.s = c6344j3.j;
        this.D = true;
        ax.H6.U0 u0 = c6344j3.g;
        if (u0 != null && (bundle = u0.m0) != null) {
            Object obj = bundle.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.B = (Boolean) obj;
            }
            Object obj2 = u0.m0.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.C = (Boolean) obj2;
            }
        }
        AbstractC0885a3.l(context);
        ax.p345v6.f fVarC = ax.p345v6.i.c();
        this.n = fVarC;
        Long l = c6344j3.i;
        this.H = l != null ? l.longValue() : fVarC.a();
        this.g = new C6326h(this);
        C6322g2 c6322g2 = new C6322g2(this);
        c6322g2.q();
        this.h = c6322g2;
        V1 v1 = new V1(this);
        v1.q();
        this.i = v1;
        B5 b5 = new B5(this);
        b5.q();
        this.l = b5;
        this.m = new R1(new C6358l3(c6344j3, this));
        this.q = new C6439z(this);
        C6317f4 c6317f4 = new C6317f4(this);
        c6317f4.w();
        this.o = c6317f4;
        C6351k3 c6351k3 = new C6351k3(this);
        c6351k3.w();
        this.p = c6351k3;
        V4 v4 = new V4(this);
        v4.w();
        this.k = v4;
        Y3 y3 = new Y3(this);
        y3.q();
        this.r = y3;
        C6442z2 c6442z2 = new C6442z2(this);
        c6442z2.q();
        this.j = c6442z2;
        ax.H6.U0 u1 = c6344j3.g;
        if (u1 != null && u1.X != 0) {
            z = true;
        }
        boolean z2 = !z;
        if (context.getApplicationContext() instanceof Application) {
            H().S0(z2);
        } else {
            j().L().a("Application context is not an Application");
        }
        c6442z2.D(new F2(this, c6344j3));
    }

    public static E2 c(Context context, ax.H6.U0 u0, Long l) {
        Bundle bundle;
        if (u0 != null && (u0.k0 == null || u0.l0 == null)) {
            u0 = new ax.H6.U0(u0.q, u0.X, u0.Y, u0.Z, null, null, u0.m0, null);
        }
        C2254p.l(context);
        C2254p.l(context.getApplicationContext());
        if (I == null) {
            synchronized (E2.class) {
                try {
                    if (I == null) {
                        I = new E2(new C6344j3(context, u0, l));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        } else if (u0 != null && (bundle = u0.m0) != null && bundle.containsKey("dataCollectionDefaultEnabled")) {
            C2254p.l(I);
            I.m(u0.m0.getBoolean("dataCollectionDefaultEnabled"));
        }
        C2254p.l(I);
        return I;
    }

    private static void e(AbstractC6286b1 abstractC6286b1) {
        if (abstractC6286b1 == null) {
            throw new IllegalStateException("Component not created");
        }
        if (abstractC6286b1.z()) {
            return;
        }
        throw new IllegalStateException("Component not initialized: " + String.valueOf(abstractC6286b1.getClass()));
    }

    static /* synthetic */ void g(E2 e2, C6344j3 c6344j3) {
        e2.l().n();
        C6421w c6421w = new C6421w(e2);
        c6421w.q();
        e2.v = c6421w;
        Q1 q1 = new Q1(e2, c6344j3.f);
        q1.w();
        e2.w = q1;
        P1 p1 = new P1(e2);
        p1.w();
        e2.t = p1;
        C6352k4 c6352k4 = new C6352k4(e2);
        c6352k4.w();
        e2.u = c6352k4;
        e2.l.r();
        e2.h.r();
        e2.w.x();
        e2.j().J().b("App measurement initialized, version", 97001L);
        e2.j().J().a("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String strF = q1.F();
        if (TextUtils.isEmpty(e2.b)) {
            if (e2.L().E0(strF, e2.g.R())) {
                e2.j().J().a("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            } else {
                e2.j().J().a("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + strF);
            }
        }
        e2.j().F().a("Debug-level message logging enabled");
        if (e2.E != e2.G.get()) {
            e2.j().G().c("Not all components initialized", Integer.valueOf(e2.E), Integer.valueOf(e2.G.get()));
        }
        e2.x = true;
    }

    private static void h(AbstractC6309e3 abstractC6309e3) {
        if (abstractC6309e3 == null) {
            throw new IllegalStateException("Component not created");
        }
        if (abstractC6309e3.s()) {
            return;
        }
        throw new IllegalStateException("Component not initialized: " + String.valueOf(abstractC6309e3.getClass()));
    }

    private static void i(C6316f3 c6316f3) {
        if (c6316f3 == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private final Y3 v() {
        h(this.r);
        return this.r;
    }

    public final C6421w A() {
        h(this.v);
        return this.v;
    }

    public final Q1 B() {
        e(this.w);
        return this.w;
    }

    public final P1 C() {
        e(this.t);
        return this.t;
    }

    public final R1 D() {
        return this.m;
    }

    public final V1 E() {
        V1 v1 = this.i;
        if (v1 == null || !v1.s()) {
            return null;
        }
        return this.i;
    }

    public final C6322g2 F() {
        i(this.h);
        return this.h;
    }

    final C6442z2 G() {
        return this.j;
    }

    public final C6351k3 H() {
        e(this.p);
        return this.p;
    }

    public final C6317f4 I() {
        e(this.o);
        return this.o;
    }

    public final C6352k4 J() {
        e(this.u);
        return this.u;
    }

    public final V4 K() {
        e(this.k);
        return this.k;
    }

    public final B5 L() {
        i(this.l);
        return this.l;
    }

    public final String M() {
        return this.b;
    }

    public final String N() {
        return this.c;
    }

    public final String O() {
        return this.d;
    }

    public final String P() {
        return this.s;
    }

    final void Q() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void R() {
        this.G.incrementAndGet();
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final Context a() {
        return this.a;
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final ax.p345v6.f b() {
        return this.n;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0269 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:83:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:88:0x0226  */
    /* JADX WARN: Code duplicated, block: B:90:0x0234 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x024c  */
    protected final void d(ax.H6.U0 u0) {
        C6330h3 c6330h3;
        Boolean boolE;
        C6409u c6409uB;
        l().n();
        if (C0997m7.a() && this.g.t(F.B0) && L().X0()) {
            B5 b5L = L();
            b5L.n();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.android.gms.measurement.TRIGGERS_AVAILABLE");
            ax.Q.b.i(b5L.a(), new ax.M6.D(b5L.a), intentFilter, 2);
            b5L.j().F().a("Registered app receiver");
        }
        C6330h3 c6330h3M = F().M();
        int iB = c6330h3M.b();
        if (C0960i6.a() && this.g.t(F.R0)) {
            ax.M6.o oVarA = this.g.A("google_analytics_default_allow_ad_storage", false);
            ax.M6.o oVarA2 = this.g.A("google_analytics_default_allow_analytics_storage", false);
            ax.M6.o oVar = ax.M6.o.UNINITIALIZED;
            if (!(oVarA == oVar && oVarA2 == oVar) && F().y(-10)) {
                c6330h3 = C6330h3.g(oVarA, oVarA2, -10);
            } else {
                if (!TextUtils.isEmpty(B().G()) && (iB == 0 || iB == 30 || iB == 10 || iB == 30 || iB == 30 || iB == 40)) {
                    H().T(new C6330h3(null, null, -10), this.H, false);
                } else if (TextUtils.isEmpty(B().G()) && u0 != null && u0.m0 != null && F().y(30)) {
                    c6330h3 = C6330h3.f(u0.m0, 30);
                    if (!c6330h3.C()) {
                    }
                }
                c6330h3 = null;
            }
        } else {
            Boolean boolD = this.g.D("google_analytics_default_allow_ad_storage");
            Boolean boolD2 = this.g.D("google_analytics_default_allow_analytics_storage");
            if (!(boolD == null && boolD2 == null) && F().y(-10)) {
                c6330h3 = new C6330h3(boolD, boolD2, -10);
            } else {
                if (!TextUtils.isEmpty(B().G()) && (iB == 0 || iB == 30 || iB == 10 || iB == 30 || iB == 30 || iB == 40)) {
                    H().T(new C6330h3(null, null, -10), this.H, false);
                } else if (TextUtils.isEmpty(B().G()) && u0 != null && u0.m0 != null && F().y(30)) {
                    c6330h3 = C6330h3.f(u0.m0, 30);
                    if (!c6330h3.C()) {
                    }
                }
                c6330h3 = null;
            }
        }
        if (c6330h3 != null) {
            H().T(c6330h3, this.H, this.g.t(F.U0));
            c6330h3M = c6330h3;
        }
        H().S(c6330h3M);
        int iA = F().L().a();
        if (C0960i6.a() && this.g.t(F.R0)) {
            ax.M6.o oVarA3 = this.g.A("google_analytics_default_allow_ad_personalization_signals", true);
            ax.M6.o oVar2 = ax.M6.o.UNINITIALIZED;
            if (oVarA3 != oVar2) {
                j().K().b("Default ad personalization consent from Manifest", oVarA3);
            }
            ax.M6.o oVarA4 = this.g.A("google_analytics_default_allow_ad_user_data", true);
            if (oVarA4 != oVar2 && C6330h3.l(-10, iA)) {
                H().R(C6409u.c(oVarA4, -10), this.g.t(F.U0));
            } else if (TextUtils.isEmpty(B().G())) {
                if (TextUtils.isEmpty(B().G())) {
                    c6409uB = C6409u.b(u0.m0, 30);
                    if (c6409uB.k()) {
                        H().R(c6409uB, this.g.t(F.U0));
                    }
                }
                if (TextUtils.isEmpty(B().G())) {
                    H().k0(u0.k0, "allow_personalized_ads", boolE.toString(), false);
                }
            } else {
                if (TextUtils.isEmpty(B().G())) {
                    c6409uB = C6409u.b(u0.m0, 30);
                    if (c6409uB.k()) {
                        H().R(c6409uB, this.g.t(F.U0));
                    }
                }
                if (TextUtils.isEmpty(B().G())) {
                    H().k0(u0.k0, "allow_personalized_ads", boolE.toString(), false);
                }
            }
        } else {
            Boolean boolD3 = this.g.D("google_analytics_default_allow_ad_user_data");
            if (boolD3 != null && C6330h3.l(-10, iA)) {
                H().R(new C6409u(boolD3, -10), this.g.t(F.U0));
            } else if (TextUtils.isEmpty(B().G()) || !(iA == 0 || iA == 30)) {
                if (TextUtils.isEmpty(B().G()) && u0 != null && u0.m0 != null && C6330h3.l(30, iA)) {
                    c6409uB = C6409u.b(u0.m0, 30);
                    if (c6409uB.k()) {
                        H().R(c6409uB, this.g.t(F.U0));
                    }
                }
                if (TextUtils.isEmpty(B().G()) && u0 != null && u0.m0 != null && F().o.a() == null && (boolE = C6409u.e(u0.m0)) != null) {
                    H().k0(u0.k0, "allow_personalized_ads", boolE.toString(), false);
                }
            } else {
                H().R(new C6409u(null, -10), this.g.t(F.U0));
            }
        }
        Boolean boolD4 = this.g.D("google_analytics_tcf_data_enabled");
        if (boolD4 == null ? true : boolD4.booleanValue()) {
            j().F().a("TCF client enabled.");
            H().G0();
            H().E0();
        }
        if (F().g.a() == 0) {
            j().K().b("Persisting first open", Long.valueOf(this.H));
            F().g.b(this.H);
        }
        H().p.c();
        if (s()) {
            if (!TextUtils.isEmpty(B().G()) || !TextUtils.isEmpty(B().E())) {
                L();
                if (B5.m0(B().G(), F().S(), B().E(), F().R())) {
                    j().J().a("Rechecking which service to use due to a GMP App Id change");
                    F().T();
                    C().H();
                    this.u.Z();
                    this.u.Y();
                    F().g.b(this.H);
                    F().i.b(null);
                }
                F().I(B().G());
                F().F(B().E());
            }
            if (!F().M().m(C6330h3.a.ANALYTICS_STORAGE)) {
                F().i.b(null);
            }
            H().X0(F().i.a());
            if (!L().Y0() && !TextUtils.isEmpty(F().x.a())) {
                j().L().a("Remote config removed with active feature rollouts");
                F().x.b(null);
            }
            if (!TextUtils.isEmpty(B().G()) || !TextUtils.isEmpty(B().E())) {
                boolean zP = p();
                if (!F().D() && !this.g.U()) {
                    F().G(!zP);
                }
                if (zP) {
                    H().A0();
                }
                K().e.a();
                J().Q(new AtomicReference<>());
                J().C(F().A.a());
            }
        } else if (p()) {
            if (!L().F0("android.permission.INTERNET")) {
                j().G().a("App is missing INTERNET permission");
            }
            if (!L().F0("android.permission.ACCESS_NETWORK_STATE")) {
                j().G().a("App is missing ACCESS_NETWORK_STATE permission");
            }
            if (!ax.p361w6.e.a(this.a).g() && !this.g.V()) {
                if (!B5.d0(this.a)) {
                    j().G().a("AppMeasurementReceiver not registered/enabled");
                }
                if (!B5.e0(this.a, false)) {
                    j().G().a("AppMeasurementService not registered/enabled");
                }
            }
            j().G().a("Uploading is not possible. App measurement disabled");
        }
        if (C0997m7.a() && this.g.t(F.B0) && L().X0()) {
            final C6351k3 c6351k3H = H();
            Objects.requireNonNull(c6351k3H);
            new Thread(new Runnable() { // from class: com.google.android.gms.measurement.internal.D2
                @Override // java.lang.Runnable
                public final void run() {
                    c6351k3H.C0();
                }
            }).start();
        }
        F().q.a(true);
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final C6291c f() {
        return this.f;
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final V1 j() {
        h(this.i);
        return this.i;
    }

    final /* synthetic */ void k(String str, int i, Throwable th, byte[] bArr, Map map) {
        if ((i != 200 && i != 204 && i != 304) || th != null) {
            j().L().c("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        F().v.a(true);
        if (bArr == null || bArr.length == 0) {
            j().F().a("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String strOptString = jSONObject.optString("deeplink", HttpUrl.FRAGMENT_ENCODE_SET);
            if (TextUtils.isEmpty(strOptString)) {
                j().F().a("Deferred Deep Link is empty.");
                return;
            }
            String strOptString2 = jSONObject.optString("gclid", HttpUrl.FRAGMENT_ENCODE_SET);
            String strOptString3 = jSONObject.optString("gbraid", HttpUrl.FRAGMENT_ENCODE_SET);
            String strOptString4 = jSONObject.optString("gad_source", HttpUrl.FRAGMENT_ENCODE_SET);
            double dOptDouble = jSONObject.optDouble("timestamp", 0.0d);
            Bundle bundle = new Bundle();
            if (O6.a() && this.g.t(F.M0)) {
                if (!L().M0(strOptString)) {
                    j().L().d("Deferred Deep Link validation failed. gclid, gbraid, deep link", strOptString2, strOptString3, strOptString);
                    return;
                }
                if (!TextUtils.isEmpty(strOptString3)) {
                    bundle.putString("gbraid", strOptString3);
                }
                if (!TextUtils.isEmpty(strOptString4)) {
                    bundle.putString("gad_source", strOptString4);
                }
            } else if (!L().M0(strOptString)) {
                j().L().c("Deferred Deep Link validation failed. gclid, deep link", strOptString2, strOptString);
                return;
            }
            if (O6.a()) {
                this.g.t(F.M0);
            }
            bundle.putString("gclid", strOptString2);
            bundle.putString("_cis", "ddp");
            this.p.Y0("auto", "_cmp", bundle);
            B5 b5L = L();
            if (TextUtils.isEmpty(strOptString) || !b5L.i0(strOptString, dOptDouble)) {
                return;
            }
            b5L.a().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
        } catch (JSONException e) {
            j().G().b("Failed to parse the Deferred Deep Link response. exception", e);
        }
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final C6442z2 l() {
        h(this.j);
        return this.j;
    }

    final void m(boolean z) {
        this.A = Boolean.valueOf(z);
    }

    final void n() {
        this.E++;
    }

    public final boolean o() {
        return this.A != null && this.A.booleanValue();
    }

    public final boolean p() {
        return x() == 0;
    }

    public final boolean q() {
        l().n();
        return this.D;
    }

    public final boolean r() {
        return TextUtils.isEmpty(this.b);
    }

    protected final boolean s() {
        if (!this.x) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        l().n();
        Boolean bool = this.y;
        if (bool == null || this.z == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.n.b() - this.z) > 1000)) {
            this.z = this.n.b();
            boolean z = true;
            Boolean boolValueOf = Boolean.valueOf(L().F0("android.permission.INTERNET") && L().F0("android.permission.ACCESS_NETWORK_STATE") && (ax.p361w6.e.a(this.a).g() || this.g.V() || (B5.d0(this.a) && B5.e0(this.a, false))));
            this.y = boolValueOf;
            if (boolValueOf.booleanValue()) {
                if (!L().k0(B().G(), B().E()) && TextUtils.isEmpty(B().E())) {
                    z = false;
                }
                this.y = Boolean.valueOf(z);
            }
        }
        return this.y.booleanValue();
    }

    public final boolean t() {
        return this.e;
    }

    public final boolean u() {
        l().n();
        h(v());
        String strF = B().F();
        Pair<String, Boolean> pairU = F().u(strF);
        if (!this.g.S() || ((Boolean) pairU.second).booleanValue() || TextUtils.isEmpty((CharSequence) pairU.first)) {
            j().F().a("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return false;
        }
        if (!v().w()) {
            j().L().a("Network is not available for Deferred Deep Link request. Skipping");
            return false;
        }
        StringBuilder sb = new StringBuilder();
        C6352k4 c6352k4J = J();
        c6352k4J.n();
        c6352k4J.v();
        if (!c6352k4J.j0() || c6352k4J.i().I0() >= 234200) {
            C1347a c1347aP0 = H().p0();
            Bundle bundle = c1347aP0 != null ? c1347aP0.q : null;
            if (bundle == null) {
                int i = this.F;
                this.F = i + 1;
                boolean z = i < 10;
                j().F().b("Failed to retrieve DMA consent from the service, " + (z ? "Retrying." : "Skipping.") + " retryCount", Integer.valueOf(this.F));
                return z;
            }
            C6330h3 c6330h3F = C6330h3.f(bundle, 100);
            sb.append("&gcs=");
            sb.append(c6330h3F.y());
            C6409u c6409uB = C6409u.b(bundle, 100);
            sb.append("&dma=");
            sb.append(c6409uB.h() == Boolean.FALSE ? 0 : 1);
            if (!TextUtils.isEmpty(c6409uB.i())) {
                sb.append("&dma_cps=");
                sb.append(c6409uB.i());
            }
            int i2 = C6409u.e(bundle) == Boolean.TRUE ? 0 : 1;
            sb.append("&npa=");
            sb.append(i2);
            j().K().b("Consent query parameters to Bow", sb);
        }
        B5 b5L = L();
        B();
        URL urlK = b5L.K(97001L, strF, (String) pairU.first, F().w.a() - 1, sb.toString());
        if (urlK != null) {
            Y3 y3V = v();
            InterfaceC6282a4 interfaceC6282a4 = new InterfaceC6282a4() { // from class: com.google.android.gms.measurement.internal.G2
                @Override // com.google.android.gms.measurement.internal.InterfaceC6282a4
                public final void a(String str, int i3, Throwable th, byte[] bArr, Map map) {
                    this.a.k(str, i3, th, bArr, map);
                }
            };
            y3V.n();
            y3V.p();
            C2254p.l(urlK);
            C2254p.l(interfaceC6282a4);
            y3V.l().z(new Z3(y3V, strF, urlK, null, null, interfaceC6282a4));
        }
        return false;
    }

    public final void w(boolean z) {
        l().n();
        this.D = z;
    }

    public final int x() {
        l().n();
        if (this.g.U()) {
            return 1;
        }
        Boolean bool = this.C;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        if (!q()) {
            return 8;
        }
        Boolean boolP = F().P();
        if (boolP != null) {
            return boolP.booleanValue() ? 0 : 3;
        }
        Boolean boolD = this.g.D("firebase_analytics_collection_enabled");
        if (boolD != null) {
            return boolD.booleanValue() ? 0 : 4;
        }
        Boolean bool2 = this.B;
        if (bool2 != null) {
            return bool2.booleanValue() ? 0 : 5;
        }
        return (this.A == null || this.A.booleanValue()) ? 0 : 7;
    }

    public final C6439z y() {
        C6439z c6439z = this.q;
        if (c6439z != null) {
            return c6439z;
        }
        throw new IllegalStateException("Component not created");
    }

    public final C6326h z() {
        return this.g;
    }
}
