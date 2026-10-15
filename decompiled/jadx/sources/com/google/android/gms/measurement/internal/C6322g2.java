package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import ax.p265q6.C2254p;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.g2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6322g2 extends AbstractC6309e3 {
    static final Pair<String, Long> B = new Pair<>(HttpUrl.FRAGMENT_ENCODE_SET, 0L);
    public final C6336i2 A;
    private SharedPreferences c;
    private Object d;
    private SharedPreferences e;
    public C6350k2 f;
    public final C6357l2 g;
    public final C6357l2 h;
    public final C6364m2 i;
    private String j;
    private boolean k;
    private long l;
    public final C6357l2 m;
    public final C6343j2 n;
    public final C6364m2 o;
    public final C6336i2 p;
    public final C6343j2 q;
    public final C6357l2 r;
    public final C6357l2 s;
    public boolean t;
    public C6343j2 u;
    public C6343j2 v;
    public C6357l2 w;
    public final C6364m2 x;
    public final C6364m2 y;
    public final C6357l2 z;

    C6322g2(E2 e2) {
        super(e2);
        this.d = new Object();
        this.m = new C6357l2(this, "session_timeout", 1800000L);
        this.n = new C6343j2(this, "start_new_session", true);
        this.r = new C6357l2(this, "last_pause_time", 0L);
        this.s = new C6357l2(this, "session_id", 0L);
        this.o = new C6364m2(this, "non_personalized_ads", null);
        this.p = new C6336i2(this, "last_received_uri_timestamps_by_source", null);
        this.q = new C6343j2(this, "allow_remote_dynamite", false);
        this.g = new C6357l2(this, "first_open_time", 0L);
        this.h = new C6357l2(this, "app_install_time", 0L);
        this.i = new C6364m2(this, "app_instance_id", null);
        this.u = new C6343j2(this, "app_backgrounded", false);
        this.v = new C6343j2(this, "deep_link_retrieval_complete", false);
        this.w = new C6357l2(this, "deep_link_retrieval_attempts", 0L);
        this.x = new C6364m2(this, "firebase_feature_rollouts", null);
        this.y = new C6364m2(this, "deferred_attribution_cache", null);
        this.z = new C6357l2(this, "deferred_attribution_cache_timestamp", 0L);
        this.A = new C6336i2(this, "default_event_parameters", null);
    }

    final boolean A(C6409u c6409u) {
        n();
        if (!C6330h3.l(c6409u.a(), L().a())) {
            return false;
        }
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putString("dma_consent_settings", c6409u.j());
        editorEdit.apply();
        return true;
    }

    final boolean B(C6330h3 c6330h3) {
        n();
        int iB = c6330h3.b();
        if (!y(iB)) {
            return false;
        }
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putString("consent_settings", c6330h3.z());
        editorEdit.putInt("consent_source", iB);
        editorEdit.apply();
        return true;
    }

    protected final boolean C(C6318f5 c6318f5) {
        n();
        String string = J().getString("stored_tcf_param", HttpUrl.FRAGMENT_ENCODE_SET);
        String strG = c6318f5.g();
        if (strG.equals(string)) {
            return false;
        }
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putString("stored_tcf_param", strG);
        editorEdit.apply();
        return true;
    }

    final boolean D() {
        SharedPreferences sharedPreferences = this.c;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    final void E(Boolean bool) {
        n();
        SharedPreferences.Editor editorEdit = J().edit();
        if (bool != null) {
            editorEdit.putBoolean("measurement_enabled_from_api", bool.booleanValue());
        } else {
            editorEdit.remove("measurement_enabled_from_api");
        }
        editorEdit.apply();
    }

    final void F(String str) {
        n();
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putString("admob_app_id", str);
        editorEdit.apply();
    }

    final void G(boolean z) {
        n();
        j().K().b("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putBoolean("deferred_analytics_collection", z);
        editorEdit.apply();
    }

    protected final SharedPreferences H() {
        n();
        p();
        if (this.e == null) {
            synchronized (this.d) {
                try {
                    if (this.e == null) {
                        String str = a().getPackageName() + "_preferences";
                        j().K().b("Default prefs file", str);
                        this.e = a().getSharedPreferences(str, 0);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.e;
    }

    final void I(String str) {
        n();
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putString("gmp_app_id", str);
        editorEdit.apply();
    }

    protected final SharedPreferences J() {
        n();
        p();
        C2254p.l(this.c);
        return this.c;
    }

    final SparseArray<Long> K() {
        Bundle bundleA = this.p.a();
        if (bundleA == null) {
            return new SparseArray<>();
        }
        int[] intArray = bundleA.getIntArray("uriSources");
        long[] longArray = bundleA.getLongArray("uriTimestamps");
        if (intArray == null || longArray == null) {
            return new SparseArray<>();
        }
        if (intArray.length != longArray.length) {
            j().G().a("Trigger URI source and timestamp array lengths do not match");
            return new SparseArray<>();
        }
        SparseArray<Long> sparseArray = new SparseArray<>();
        for (int i = 0; i < intArray.length; i++) {
            sparseArray.put(intArray[i], Long.valueOf(longArray[i]));
        }
        return sparseArray;
    }

    final C6409u L() {
        n();
        return C6409u.d(J().getString("dma_consent_settings", null));
    }

    final C6330h3 M() {
        n();
        return C6330h3.i(J().getString("consent_settings", "G1"), J().getInt("consent_source", 100));
    }

    final Boolean N() {
        n();
        if (J().contains("use_service")) {
            return Boolean.valueOf(J().getBoolean("use_service", false));
        }
        return null;
    }

    final Boolean O() {
        n();
        if (J().contains("measurement_enabled_from_api")) {
            return Boolean.valueOf(J().getBoolean("measurement_enabled_from_api", true));
        }
        return null;
    }

    final Boolean P() {
        n();
        if (J().contains("measurement_enabled")) {
            return Boolean.valueOf(J().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    protected final String Q() {
        n();
        String string = J().getString("previous_os_version", null);
        e().p();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor editorEdit = J().edit();
            editorEdit.putString("previous_os_version", str);
            editorEdit.apply();
        }
        return string;
    }

    final String R() {
        n();
        return J().getString("admob_app_id", null);
    }

    final String S() {
        n();
        return J().getString("gmp_app_id", null);
    }

    final void T() {
        n();
        Boolean boolP = P();
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.clear();
        editorEdit.apply();
        if (boolP != null) {
            w(boolP);
        }
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final void o() {
        SharedPreferences sharedPreferences = a().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.c = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.t = z;
        if (!z) {
            SharedPreferences.Editor editorEdit = this.c.edit();
            editorEdit.putBoolean("has_been_opened", true);
            editorEdit.apply();
        }
        this.f = new C6350k2(this, "health_monitor", Math.max(0L, F.d.a(null).longValue()));
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final boolean t() {
        return true;
    }

    final Pair<String, Boolean> u(String str) {
        n();
        if (!M().m(C6330h3.a.AD_STORAGE)) {
            return new Pair<>(HttpUrl.FRAGMENT_ENCODE_SET, Boolean.FALSE);
        }
        long jB = b().b();
        if (this.j != null && jB < this.l) {
            return new Pair<>(this.j, Boolean.valueOf(this.k));
        }
        this.l = jB + d().B(str);
        ax.M5.a.d(true);
        try {
            ax.M5.a.C0148a c0148aA = ax.M5.a.a(a());
            this.j = HttpUrl.FRAGMENT_ENCODE_SET;
            String strA = c0148aA.a();
            if (strA != null) {
                this.j = strA;
            }
            this.k = c0148aA.b();
        } catch (Exception e) {
            j().F().b("Unable to get advertising id", e);
            this.j = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        ax.M5.a.d(false);
        return new Pair<>(this.j, Boolean.valueOf(this.k));
    }

    final void v(SparseArray<Long> sparseArray) {
        if (sparseArray == null) {
            this.p.b(null);
            return;
        }
        int[] iArr = new int[sparseArray.size()];
        long[] jArr = new long[sparseArray.size()];
        for (int i = 0; i < sparseArray.size(); i++) {
            iArr[i] = sparseArray.keyAt(i);
            jArr[i] = sparseArray.valueAt(i).longValue();
        }
        Bundle bundle = new Bundle();
        bundle.putIntArray("uriSources", iArr);
        bundle.putLongArray("uriTimestamps", jArr);
        this.p.b(bundle);
    }

    final void w(Boolean bool) {
        n();
        SharedPreferences.Editor editorEdit = J().edit();
        if (bool != null) {
            editorEdit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            editorEdit.remove("measurement_enabled");
        }
        editorEdit.apply();
    }

    final void x(boolean z) {
        n();
        SharedPreferences.Editor editorEdit = J().edit();
        editorEdit.putBoolean("use_service", z);
        editorEdit.apply();
    }

    final boolean y(int i) {
        return C6330h3.l(i, J().getInt("consent_source", 100));
    }

    final boolean z(long j) {
        return j - this.m.a() > this.r.a();
    }
}
