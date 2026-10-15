package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import ax.p265q6.C2254p;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.f4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6317f4 extends AbstractC6286b1 {
    private volatile C6303d4 c;
    private volatile C6303d4 d;
    protected C6303d4 e;
    private final Map<Activity, C6303d4> f;
    private Activity g;
    private volatile boolean h;
    private volatile C6303d4 i;
    private C6303d4 j;
    private boolean k;
    private final Object l;

    public C6317f4(E2 e2) {
        super(e2);
        this.l = new Object();
        this.f = new ConcurrentHashMap();
    }

    private final String D(Class<?> cls, String str) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return str;
        }
        String[] strArrSplit = canonicalName.split("\\.");
        String str2 = strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : HttpUrl.FRAGMENT_ENCODE_SET;
        return str2.length() > d().r(null, false) ? str2.substring(0, d().r(null, false)) : str2;
    }

    private final void G(Activity activity, C6303d4 c6303d4, boolean z) {
        C6303d4 c6303d5;
        C6303d4 c6303d6 = this.c == null ? this.d : this.c;
        if (c6303d4.b == null) {
            c6303d5 = new C6303d4(c6303d4.a, activity != null ? D(activity.getClass(), "Activity") : null, c6303d4.c, c6303d4.e, c6303d4.f);
        } else {
            c6303d5 = c6303d4;
        }
        this.d = this.c;
        this.c = c6303d5;
        l().D(new RunnableC6331h4(this, c6303d5, c6303d6, b().b(), z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:50:0x00ad  */
    public final void J(C6303d4 c6303d4, C6303d4 c6303d5, long j, boolean z, Bundle bundle) {
        long j2;
        n();
        boolean z2 = false;
        boolean z3 = (c6303d5 != null && c6303d5.c == c6303d4.c && Objects.equals(c6303d5.b, c6303d4.b) && Objects.equals(c6303d5.a, c6303d4.a)) ? false : true;
        if (z && this.e != null) {
            z2 = true;
        }
        if (z3) {
            Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
            B5.X(c6303d4, bundle2, true);
            if (c6303d5 != null) {
                String str = c6303d5.a;
                if (str != null) {
                    bundle2.putString("_pn", str);
                }
                String str2 = c6303d5.b;
                if (str2 != null) {
                    bundle2.putString("_pc", str2);
                }
                bundle2.putLong("_pi", c6303d5.c);
            }
            if (z2) {
                long jA = u().f.a(j);
                if (jA > 0) {
                    i().M(bundle2, jA);
                }
            }
            if (!d().T()) {
                bundle2.putLong("_mst", 1L);
            }
            String str3 = c6303d4.e ? "app" : "auto";
            long jA2 = b().a();
            if (c6303d4.e) {
                long j3 = c6303d4.f;
                if (j3 != 0) {
                    j2 = j3;
                } else {
                    j2 = jA2;
                }
            } else {
                j2 = jA2;
            }
            r().d0(str3, "_vs", j2, bundle2);
        }
        if (z2) {
            K(this.e, true, j);
        }
        this.e = c6303d4;
        if (c6303d4.e) {
            this.j = c6303d4;
        }
        t().L(c6303d4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void K(C6303d4 c6303d4, boolean z, long j) {
        o().v(b().b());
        if (!u().E(c6303d4 != null && c6303d4.d, z, j) || c6303d4 == null) {
            return;
        }
        c6303d4.d = false;
    }

    static /* synthetic */ void L(C6317f4 c6317f4, Bundle bundle, C6303d4 c6303d4, C6303d4 c6303d5, long j) {
        if (bundle != null) {
            bundle.remove("screen_name");
            bundle.remove("screen_class");
        }
        c6317f4.J(c6303d4, c6303d5, j, true, c6317f4.i().F(null, "screen_view", bundle, null, false));
    }

    private final C6303d4 T(Activity activity) {
        C2254p.l(activity);
        C6303d4 c6303d4 = this.f.get(activity);
        if (c6303d4 == null) {
            C6303d4 c6303d5 = new C6303d4(null, D(activity.getClass(), "Activity"), i().R0());
            this.f.put(activity, c6303d5);
            c6303d4 = c6303d5;
        }
        return this.i != null ? this.i : c6303d4;
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return false;
    }

    public final C6303d4 C(boolean z) {
        v();
        n();
        if (!z) {
            return this.e;
        }
        C6303d4 c6303d4 = this.e;
        return c6303d4 != null ? c6303d4 : this.j;
    }

    public final void E(Activity activity) {
        synchronized (this.l) {
            try {
                if (activity == this.g) {
                    this.g = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (d().T()) {
            this.f.remove(activity);
        }
    }

    public final void F(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!d().T() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.f.put(activity, new C6303d4(bundle2.getString("name"), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    @Deprecated
    public final void H(Activity activity, String str, String str2) {
        if (!d().T()) {
            j().M().a("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        C6303d4 c6303d4 = this.c;
        if (c6303d4 == null) {
            j().M().a("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.f.get(activity) == null) {
            j().M().a("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = D(activity.getClass(), "Activity");
        }
        boolean zEquals = Objects.equals(c6303d4.b, str2);
        boolean zEquals2 = Objects.equals(c6303d4.a, str);
        if (zEquals && zEquals2) {
            j().M().a("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > d().r(null, false))) {
            j().M().b("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > d().r(null, false))) {
            j().M().b("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        j().K().c("Setting current screen to name, class", str == null ? "null" : str, str2);
        C6303d4 c6303d5 = new C6303d4(str, str2, i().R0());
        this.f.put(activity, c6303d5);
        G(activity, c6303d5, true);
    }

    public final void I(Bundle bundle, long j) {
        String str;
        synchronized (this.l) {
            try {
                if (!this.k) {
                    j().M().a("Cannot log screen view event when the app is in the background.");
                    return;
                }
                String strD = null;
                if (bundle != null) {
                    String string = bundle.getString("screen_name");
                    if (string != null && (string.length() <= 0 || string.length() > d().r(null, false))) {
                        j().M().b("Invalid screen name length for screen view. Length", Integer.valueOf(string.length()));
                        return;
                    }
                    String string2 = bundle.getString("screen_class");
                    if (string2 != null && (string2.length() <= 0 || string2.length() > d().r(null, false))) {
                        j().M().b("Invalid screen class length for screen view. Length", Integer.valueOf(string2.length()));
                        return;
                    } else {
                        strD = string2;
                        str = string;
                    }
                } else {
                    str = null;
                }
                if (strD == null) {
                    Activity activity = this.g;
                    strD = activity != null ? D(activity.getClass(), "Activity") : "Activity";
                }
                String str2 = strD;
                C6303d4 c6303d4 = this.c;
                if (this.h && c6303d4 != null) {
                    this.h = false;
                    boolean zEquals = Objects.equals(c6303d4.b, str2);
                    boolean zEquals2 = Objects.equals(c6303d4.a, str);
                    if (zEquals && zEquals2) {
                        j().M().a("Ignoring call to log screen view event with duplicate parameters.");
                        return;
                    }
                }
                j().K().c("Logging screen view with name, class", str == null ? "null" : str, str2 == null ? "null" : str2);
                C6303d4 c6303d5 = this.c == null ? this.d : this.c;
                C6303d4 c6303d6 = new C6303d4(str, str2, i().R0(), true, j);
                this.c = c6303d6;
                this.d = c6303d5;
                this.i = c6303d6;
                l().D(new RunnableC6310e4(this, bundle, c6303d6, c6303d5, b().b()));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final C6303d4 P() {
        return this.c;
    }

    public final void Q(Activity activity) {
        synchronized (this.l) {
            this.k = false;
            this.h = true;
        }
        long jB = b().b();
        if (!d().T()) {
            this.c = null;
            l().D(new RunnableC6345j4(this, jB));
        } else {
            C6303d4 c6303d4T = T(activity);
            this.d = this.c;
            this.c = null;
            l().D(new RunnableC6338i4(this, c6303d4T, jB));
        }
    }

    public final void R(Activity activity, Bundle bundle) {
        C6303d4 c6303d4;
        if (!d().T() || bundle == null || (c6303d4 = this.f.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", c6303d4.c);
        bundle2.putString("name", c6303d4.a);
        bundle2.putString("referrer_name", c6303d4.b);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    public final void S(Activity activity) {
        synchronized (this.l) {
            this.k = true;
            if (activity != this.g) {
                synchronized (this.l) {
                    this.g = activity;
                    this.h = false;
                }
                if (d().T()) {
                    this.i = null;
                    l().D(new RunnableC6359l4(this));
                }
            }
        }
        if (!d().T()) {
            this.c = this.i;
            l().D(new RunnableC6324g4(this));
        } else {
            G(activity, T(activity), false);
            C6439z c6439zO = o();
            c6439zO.l().D(new Z(c6439zO, c6439zO.b().b()));
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C1, com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6439z o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ Q1 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ P1 q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6351k3 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6317f4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ C6352k4 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.C1
    public final /* bridge */ /* synthetic */ V4 u() {
        return super.u();
    }
}
