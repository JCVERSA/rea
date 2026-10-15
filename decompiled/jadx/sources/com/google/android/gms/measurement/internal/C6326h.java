package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import ax.H6.U6;
import ax.H6.Z6;
import ax.p265q6.C2254p;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6326h extends C6316f3 {
    private Boolean b;
    private String c;
    private InterfaceC6340j d;
    private Boolean e;

    C6326h(E2 e2) {
        super(e2);
        this.d = new InterfaceC6340j() { // from class: com.google.android.gms.measurement.internal.g
            @Override // com.google.android.gms.measurement.internal.InterfaceC6340j
            public final String c(String str, String str2) {
                return null;
            }
        };
    }

    public static long J() {
        return F.e.a(null).longValue();
    }

    public static long O() {
        return F.E.a(null).longValue();
    }

    private final Bundle X() {
        try {
            if (a().getPackageManager() == null) {
                j().G().a("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfoC = ax.p361w6.e.a(a()).c(a().getPackageName(), 128);
            if (applicationInfoC != null) {
                return applicationInfoC.metaData;
            }
            j().G().a("Failed to load metadata: ApplicationInfo is null");
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            j().G().b("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    private final String c(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
            C2254p.l(str3);
            return str3;
        } catch (ClassNotFoundException e) {
            j().G().b("Could not find SystemProperties class", e);
            return str2;
        } catch (IllegalAccessException e2) {
            j().G().b("Could not access SystemProperties.get()", e2);
            return str2;
        } catch (NoSuchMethodException e3) {
            j().G().b("Could not find SystemProperties.get() method", e3);
            return str2;
        } catch (InvocationTargetException e4) {
            j().G().b("SystemProperties.get() threw an exception", e4);
            return str2;
        }
    }

    public final ax.M6.o A(String str, boolean z) {
        Object obj;
        C2254p.f(str);
        Bundle bundleX = X();
        if (bundleX == null) {
            j().G().a("Failed to load metadata: Metadata bundle is null");
            obj = null;
        } else {
            obj = bundleX.get(str);
        }
        if (obj == null) {
            return ax.M6.o.UNINITIALIZED;
        }
        if (Boolean.TRUE.equals(obj)) {
            return ax.M6.o.GRANTED;
        }
        if (Boolean.FALSE.equals(obj)) {
            return ax.M6.o.DENIED;
        }
        if (z && "eu_consent_policy".equals(obj)) {
            return ax.M6.o.POLICY;
        }
        j().L().b("Invalid manifest metadata for", str);
        return ax.M6.o.UNINITIALIZED;
    }

    final long B(String str) {
        return z(str, F.b);
    }

    public final String C(String str, M1<String> m1) {
        return TextUtils.isEmpty(str) ? m1.a(null) : m1.a(this.d.c(str, m1.b()));
    }

    final Boolean D(String str) {
        C2254p.f(str);
        Bundle bundleX = X();
        if (bundleX == null) {
            j().G().a("Failed to load metadata: Metadata bundle is null");
            return null;
        }
        if (bundleX.containsKey(str)) {
            return Boolean.valueOf(bundleX.getBoolean(str));
        }
        return null;
    }

    public final boolean E(String str, M1<Boolean> m1) {
        return G(str, m1);
    }

    final String F(String str) {
        return C(str, F.M);
    }

    public final boolean G(String str, M1<Boolean> m1) {
        if (TextUtils.isEmpty(str)) {
            return m1.a(null).booleanValue();
        }
        String strC = this.d.c(str, m1.b());
        return TextUtils.isEmpty(strC) ? m1.a(null).booleanValue() : m1.a(Boolean.valueOf("1".equals(strC))).booleanValue();
    }

    public final int H() {
        return i().c0(201500000, true) ? 100 : 25;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x002a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:14:0x003d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x003e A[Catch: NotFoundException -> 0x0043, TRY_LEAVE, TryCatch #0 {NotFoundException -> 0x0043, blocks: (B:12:0x002b, B:15:0x003e), top: B:20:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:20:0x002b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    final List<String> I(String str) {
        Integer numValueOf;
        String[] stringArray;
        C2254p.f(str);
        Bundle bundleX = X();
        if (bundleX != null) {
            if (bundleX.containsKey(str)) {
                numValueOf = Integer.valueOf(bundleX.getInt(str));
            }
            if (numValueOf == null) {
                return null;
            }
            try {
                stringArray = a().getResources().getStringArray(numValueOf.intValue());
                if (stringArray == null) {
                    return null;
                }
                return Arrays.asList(stringArray);
            } catch (Resources.NotFoundException e) {
                j().G().b("Failed to load string array from metadata: resource not found", e);
                return null;
            }
        }
        j().G().a("Failed to load metadata: Metadata bundle is null");
        numValueOf = null;
        if (numValueOf == null) {
            return null;
        }
        stringArray = a().getResources().getStringArray(numValueOf.intValue());
        if (stringArray == null) {
            return null;
        }
        return Arrays.asList(stringArray);
    }

    public final void K(String str) {
        this.c = str;
    }

    final boolean L(String str) {
        return G(str, F.L);
    }

    public final boolean M(String str) {
        return "1".equals(this.d.c(str, "gaia_collection_enabled"));
    }

    public final boolean N(String str) {
        return "1".equals(this.d.c(str, "measurement.event_sampling_enabled"));
    }

    public final String P() {
        return c("debug.firebase.analytics.app", HttpUrl.FRAGMENT_ENCODE_SET);
    }

    public final String Q() {
        return c("debug.deferred.deeplink", HttpUrl.FRAGMENT_ENCODE_SET);
    }

    public final String R() {
        return this.c;
    }

    public final boolean S() {
        Boolean boolD = D("google_analytics_adid_collection_enabled");
        return boolD == null || boolD.booleanValue();
    }

    public final boolean T() {
        Boolean boolD = D("google_analytics_automatic_screen_reporting_enabled");
        return boolD == null || boolD.booleanValue();
    }

    public final boolean U() {
        Boolean boolD = D("firebase_analytics_collection_deactivated");
        return boolD != null && boolD.booleanValue();
    }

    final boolean V() {
        if (this.b == null) {
            Boolean boolD = D("app_measurement_lite");
            this.b = boolD;
            if (boolD == null) {
                this.b = Boolean.FALSE;
            }
        }
        return this.b.booleanValue() || !this.a.t();
    }

    public final boolean W() {
        if (this.e == null) {
            synchronized (this) {
                try {
                    if (this.e == null) {
                        ApplicationInfo applicationInfo = a().getApplicationInfo();
                        String strA = ax.p345v6.q.a();
                        if (applicationInfo != null) {
                            String str = applicationInfo.processName;
                            this.e = Boolean.valueOf(str != null && str.equals(strA));
                        }
                        if (this.e == null) {
                            this.e = Boolean.TRUE;
                            j().G().a("My process not in the list of running processes");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.e.booleanValue();
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

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void k() {
        super.k();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void n() {
        super.n();
    }

    public final double o(String str, M1<Double> m1) {
        if (TextUtils.isEmpty(str)) {
            return m1.a(null).doubleValue();
        }
        String strC = this.d.c(str, m1.b());
        if (TextUtils.isEmpty(strC)) {
            return m1.a(null).doubleValue();
        }
        try {
            return m1.a(Double.valueOf(Double.parseDouble(strC))).doubleValue();
        } catch (NumberFormatException unused) {
            return m1.a(null).doubleValue();
        }
    }

    final int p(String str) {
        return q(str, F.I, 500, 2000);
    }

    public final int q(String str, M1<Integer> m1, int i, int i2) {
        return Math.max(Math.min(v(str, m1), i2), i);
    }

    final int r(String str, boolean z) {
        if (!U6.a() || !d().G(null, F.O0)) {
            return 100;
        }
        if (z) {
            return q(str, F.S, 100, 500);
        }
        return 500;
    }

    final void s(InterfaceC6340j interfaceC6340j) {
        this.d = interfaceC6340j;
    }

    public final boolean t(M1<Boolean> m1) {
        return G(null, m1);
    }

    public final int u(String str) {
        return q(str, F.J, 25, 100);
    }

    public final int v(String str, M1<Integer> m1) {
        if (TextUtils.isEmpty(str)) {
            return m1.a(null).intValue();
        }
        String strC = this.d.c(str, m1.b());
        if (TextUtils.isEmpty(strC)) {
            return m1.a(null).intValue();
        }
        try {
            return m1.a(Integer.valueOf(Integer.parseInt(strC))).intValue();
        } catch (NumberFormatException unused) {
            return m1.a(null).intValue();
        }
    }

    final int w(String str, boolean z) {
        return Math.max(r(str, z), 256);
    }

    final int x() {
        return (Z6.a() && d().G(null, F.y0) && i().c0(231100000, true)) ? 35 : 0;
    }

    public final int y(String str) {
        return v(str, F.p);
    }

    public final long z(String str, M1<Long> m1) {
        if (TextUtils.isEmpty(str)) {
            return m1.a(null).longValue();
        }
        String strC = this.d.c(str, m1.b());
        if (TextUtils.isEmpty(strC)) {
            return m1.a(null).longValue();
        }
        try {
            return m1.a(Long.valueOf(Long.parseLong(strC))).longValue();
        } catch (NumberFormatException unused) {
            return m1.a(null).longValue();
        }
    }
}
