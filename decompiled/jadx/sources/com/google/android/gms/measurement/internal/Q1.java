package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import ax.H6.C0960i6;
import ax.H6.C0997m7;
import ax.H6.C1013o6;
import ax.H6.y7;
import ax.p265q6.C2254p;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class Q1 extends AbstractC6286b1 {
    private String c;
    private String d;
    private int e;
    private String f;
    private long g;
    private long h;
    private List<String> i;
    private String j;
    private int k;
    private String l;
    private String m;
    private String n;
    private long o;
    private String p;

    Q1(E2 e2, long j) {
        super(e2);
        this.o = 0L;
        this.p = null;
        this.h = j;
    }

    private final String J() {
        if (y7.a() && d().t(F.n0)) {
            j().K().a("Disabled IID for tests.");
            return null;
        }
        try {
            Class<?> clsLoadClass = a().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
            if (clsLoadClass == null) {
                return null;
            }
            try {
                Object objInvoke = clsLoadClass.getDeclaredMethod("getInstance", Context.class).invoke(null, a());
                if (objInvoke == null) {
                    return null;
                }
                try {
                    return (String) clsLoadClass.getDeclaredMethod("getFirebaseInstanceId", null).invoke(objInvoke, null);
                } catch (Exception unused) {
                    j().M().a("Failed to retrieve Firebase Instance Id");
                    return null;
                }
            } catch (Exception unused2) {
                j().N().a("Failed to obtain Firebase Analytics instance");
                return null;
            }
        } catch (ClassNotFoundException unused3) {
        }
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x016b  */
    /* JADX WARN: Code duplicated, block: B:47:0x016d  */
    /* JADX WARN: Code duplicated, block: B:53:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:65:0x0200  */
    final E5 B(String str) {
        String str2;
        String str3;
        Boolean boolD;
        boolean zBooleanValue;
        int iY0;
        String strC;
        n();
        String strF = F();
        String strG = G();
        v();
        String str4 = this.d;
        long jD = D();
        v();
        C2254p.l(this.f);
        String str5 = this.f;
        v();
        n();
        if (this.g == 0) {
            this.g = this.a.L().z(a(), a().getPackageName());
        }
        long j = this.g;
        boolean zP = this.a.p();
        boolean z = !h().t;
        n();
        String str6 = null;
        String strJ = !this.a.p() ? null : J();
        long jK0 = 0;
        E2 e2 = this.a;
        String str7 = strJ;
        long jA = e2.F().g.a();
        long jMin = jA == 0 ? e2.H : Math.min(e2.H, jA);
        int iC = C();
        boolean zS = d().S();
        C6322g2 c6322g2H = h();
        c6322g2H.n();
        boolean z2 = c6322g2H.J().getBoolean("deferred_analytics_collection", false);
        String strE = E();
        Boolean boolD2 = d().D("google_analytics_default_allow_ad_personalization_signals");
        Boolean boolValueOf = boolD2 == null ? null : Boolean.valueOf(!boolD2.booleanValue());
        long j2 = this.h;
        List<String> list = this.i;
        String strZ = h().M().z();
        if (this.j == null) {
            this.j = i().U0();
        }
        String str8 = this.j;
        if (C1013o6.a()) {
            str2 = str8;
            str3 = strZ;
            if (!d().t(F.V0) || h().M().m(C6330h3.a.ANALYTICS_STORAGE)) {
            }
            boolD = d().D("google_analytics_sgtm_upload_enabled");
            if (boolD == null) {
                zBooleanValue = false;
            } else {
                zBooleanValue = boolD.booleanValue();
            }
            long jZ0 = i().z0(F());
            int iB = h().M().b();
            String strJ2 = h().L().j();
            if (C0997m7.a() || !d().t(F.B0)) {
                iY0 = 0;
            } else {
                i();
                iY0 = B5.y0();
            }
            if (C0997m7.a() && d().t(F.B0)) {
                jK0 = i().K0();
            }
            long j3 = jK0;
            String strR = d().R();
            if (C0960i6.a() || !d().t(F.R0)) {
                strC = HttpUrl.FRAGMENT_ENCODE_SET;
            } else {
                strC = new C6329h2(d().A("google_analytics_default_allow_ad_personalization_signals", true)).c();
            }
            return new E5(strF, strG, str4, jD, str5, 97001L, j, str, zP, z, str7, 0L, jMin, iC, zS, z2, strE, boolValueOf, j2, list, (String) null, str3, str2, str6, zBooleanValue, jZ0, iB, strJ2, iY0, j3, strR, strC);
        }
        str2 = str8;
        str3 = strZ;
        n();
        if (this.o != 0) {
            long jA2 = b().a() - this.o;
            if (this.n != null && jA2 > 86400000 && this.p == null) {
                I();
            }
        }
        if (this.n == null) {
            I();
        }
        str6 = this.n;
        boolD = d().D("google_analytics_sgtm_upload_enabled");
        if (boolD == null) {
            zBooleanValue = false;
        } else {
            zBooleanValue = boolD.booleanValue();
        }
        long jZ1 = i().z0(F());
        int iB2 = h().M().b();
        String strJ3 = h().L().j();
        if (C0997m7.a()) {
            iY0 = 0;
        } else {
            iY0 = 0;
        }
        if (C0997m7.a()) {
            jK0 = i().K0();
        }
        long j4 = jK0;
        String strR2 = d().R();
        if (C0960i6.a()) {
            strC = HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            strC = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        return new E5(strF, strG, str4, jD, str5, 97001L, j, str, zP, z, str7, 0L, jMin, iC, zS, z2, strE, boolValueOf, j2, list, (String) null, str3, str2, str6, zBooleanValue, jZ1, iB2, strJ3, iY0, j4, strR2, strC);
    }

    final int C() {
        v();
        return this.k;
    }

    final int D() {
        v();
        return this.e;
    }

    final String E() {
        v();
        return this.m;
    }

    final String F() {
        v();
        C2254p.l(this.c);
        return this.c;
    }

    final String G() {
        n();
        v();
        C2254p.l(this.l);
        return this.l;
    }

    final List<String> H() {
        return this.i;
    }

    final void I() {
        String str;
        n();
        if (h().M().m(C6330h3.a.ANALYTICS_STORAGE)) {
            byte[] bArr = new byte[16];
            i().W0().nextBytes(bArr);
            str = String.format(Locale.US, "%032x", new BigInteger(1, bArr));
        } else {
            j().F().a("Analytics Storage consent is not granted");
            str = null;
        }
        j().F().a(String.format("Resetting session stitching token to %s", str == null ? "null" : "not null"));
        this.n = str;
        this.o = b().a();
    }

    final boolean K(String str) {
        String str2 = this.p;
        boolean z = (str2 == null || str2.equals(str)) ? false : true;
        this.p = str;
        return z;
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

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final void y() {
        String str;
        String packageName = a().getPackageName();
        PackageManager packageManager = a().getPackageManager();
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        String installerPackageName = "unknown";
        String str3 = "Unknown";
        int i = Integer.MIN_VALUE;
        if (packageManager == null) {
            j().G().b("PackageManager is null, app identity information might be inaccurate. appId", V1.v(packageName));
        } else {
            try {
                installerPackageName = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException unused) {
                j().G().b("Error retrieving app installer package name. appId", V1.v(packageName));
            }
            if (installerPackageName == null) {
                installerPackageName = "manual_install";
            } else if ("com.android.vending".equals(installerPackageName)) {
                installerPackageName = HttpUrl.FRAGMENT_ENCODE_SET;
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(a().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    String string = !TextUtils.isEmpty(applicationLabel) ? applicationLabel.toString() : "Unknown";
                    try {
                        str3 = packageInfo.versionName;
                        i = packageInfo.versionCode;
                    } catch (PackageManager.NameNotFoundException unused2) {
                        str = str3;
                        str3 = string;
                        j().G().c("Error retrieving package info. appId, appName", V1.v(packageName), str3);
                        str3 = str;
                    }
                }
            } catch (PackageManager.NameNotFoundException unused3) {
                str = "Unknown";
            }
        }
        this.c = packageName;
        this.f = installerPackageName;
        this.d = str3;
        this.e = i;
        this.g = 0L;
        boolean z = !TextUtils.isEmpty(this.a.M()) && "am".equals(this.a.N());
        int iX = this.a.x();
        switch (iX) {
            case 0:
                j().K().a("App measurement collection enabled");
                break;
            case 1:
                j().J().a("App measurement deactivated via the manifest");
                break;
            case 2:
                j().K().a("App measurement deactivated via the init parameters");
                break;
            case 3:
                j().J().a("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                break;
            case 4:
                j().J().a("App measurement disabled via the manifest");
                break;
            case 5:
                j().K().a("App measurement disabled via the init parameters");
                break;
            case 6:
                j().M().a("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                break;
            case 7:
                j().J().a("App measurement disabled via the global data collection setting");
                break;
            case 8:
                j().J().a("App measurement disabled due to denied storage consent");
                break;
            default:
                j().J().a("App measurement disabled");
                j().I().a("Invalid scion state in identity");
                break;
        }
        boolean z2 = iX == 0;
        this.l = HttpUrl.FRAGMENT_ENCODE_SET;
        this.m = HttpUrl.FRAGMENT_ENCODE_SET;
        if (z) {
            this.m = this.a.M();
        }
        try {
            String strB = new ax.M6.m(a(), this.a.P()).b("google_app_id");
            if (!TextUtils.isEmpty(strB)) {
                str2 = strB;
            }
            this.l = str2;
            if (!TextUtils.isEmpty(strB)) {
                this.m = new ax.M6.m(a(), this.a.P()).b("admob_app_id");
            }
            if (z2) {
                j().K().c("App measurement enabled for app package, google app id", this.c, TextUtils.isEmpty(this.l) ? this.m : this.l);
            }
        } catch (IllegalStateException e) {
            j().G().c("Fetching Google App Id failed with exception. appId", V1.v(packageName), e);
        }
        this.i = null;
        List<String> listI = d().I("analytics.safelisted_events");
        if (listI == null) {
            this.i = listI;
        } else if (listI.isEmpty()) {
            j().M().a("Safelisted event list is empty. Ignoring");
        } else {
            Iterator<String> it = listI.iterator();
            do {
                if (!it.hasNext()) {
                    this.i = listI;
                }
            } while (i().w0("safelisted event", it.next()));
        }
        if (packageManager != null) {
            this.k = ax.p361w6.b.a(a()) ? 1 : 0;
        } else {
            this.k = 0;
        }
    }
}
