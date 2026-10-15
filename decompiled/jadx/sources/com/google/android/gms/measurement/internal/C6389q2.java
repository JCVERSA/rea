package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import ax.H6.AbstractC0994m4;
import ax.H6.C0918e0;
import ax.H6.C1032r2;
import ax.H6.C1040s2;
import ax.H6.C1058u4;
import ax.H6.E7;
import ax.H6.I7;
import ax.H6.K7;
import ax.p265q6.C2254p;
import com.google.android.gms.measurement.internal.C6389q2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.q2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6389q2 extends AbstractC6353k5 implements InterfaceC6340j {
    private final Map<String, Map<String, String>> d;
    private final Map<String, Set<String>> e;
    private final Map<String, Map<String, Boolean>> f;
    private final Map<String, Map<String, Boolean>> g;
    private final Map<String, ax.H6.N1> h;
    private final Map<String, Map<String, Integer>> i;
    final ax.B.j<String, ax.H6.C> j;
    final I7 k;
    private final Map<String, String> l;
    private final Map<String, String> m;
    private final Map<String, String> n;

    C6389q2(q5 q5Var) {
        super(q5Var);
        this.d = new ax.B.a();
        this.e = new ax.B.a();
        this.f = new ax.B.a();
        this.g = new ax.B.a();
        this.h = new ax.B.a();
        this.l = new ax.B.a();
        this.m = new ax.B.a();
        this.n = new ax.B.a();
        this.i = new ax.B.a();
        this.j = new C6418v2(this, 20);
        this.k = new C6412u2(this);
    }

    private final ax.H6.N1 A(String str, byte[] bArr) {
        if (bArr == null) {
            return ax.H6.N1.T();
        }
        try {
            ax.H6.N1 n1 = (ax.H6.N1) ((AbstractC0994m4) ((ax.H6.N1.a) x5.G(ax.H6.N1.Q(), bArr)).y());
            j().K().c("Parsed config. version, gmp_app_id", n1.f0() ? Long.valueOf(n1.O()) : null, n1.d0() ? n1.V() : null);
            return n1;
        } catch (C1058u4 e) {
            j().L().c("Unable to merge remote config. appId", V1.v(str), e);
            return ax.H6.N1.T();
        } catch (RuntimeException e2) {
            j().L().c("Unable to merge remote config. appId", V1.v(str), e2);
            return ax.H6.N1.T();
        }
    }

    private static C6330h3.a C(ax.H6.K1.e eVar) {
        int i = C6430x2.b[eVar.ordinal()];
        if (i == 1) {
            return C6330h3.a.AD_STORAGE;
        }
        if (i == 2) {
            return C6330h3.a.ANALYTICS_STORAGE;
        }
        if (i == 3) {
            return C6330h3.a.AD_USER_DATA;
        }
        if (i != 4) {
            return null;
        }
        return C6330h3.a.AD_PERSONALIZATION;
    }

    private static Map<String, String> D(ax.H6.N1 n1) {
        ax.B.a aVar = new ax.B.a();
        if (n1 != null) {
            for (ax.H6.Q1 q1 : n1.a0()) {
                aVar.put(q1.K(), q1.L());
            }
        }
        return aVar;
    }

    private final void F(String str, ax.H6.N1.a aVar) {
        HashSet hashSet = new HashSet();
        ax.B.a aVar2 = new ax.B.a();
        ax.B.a aVar3 = new ax.B.a();
        ax.B.a aVar4 = new ax.B.a();
        if (aVar != null) {
            Iterator<ax.H6.L1> it = aVar.E().iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().K());
            }
            for (int i = 0; i < aVar.v(); i++) {
                ax.H6.M1.a aVarB = aVar.w(i).B();
                if (aVarB.z().isEmpty()) {
                    j().L().a("EventConfig contained null event name");
                } else {
                    String strZ = aVarB.z();
                    String strB = ax.M6.q.b(aVarB.z());
                    if (!TextUtils.isEmpty(strB)) {
                        aVarB = aVarB.w(strB);
                        aVar.z(i, aVarB);
                    }
                    if (aVarB.D() && aVarB.B()) {
                        aVar2.put(strZ, Boolean.TRUE);
                    }
                    if (aVarB.E() && aVarB.C()) {
                        aVar3.put(aVarB.z(), Boolean.TRUE);
                    }
                    if (aVarB.F()) {
                        if (aVarB.v() < 2 || aVarB.v() > 65535) {
                            j().L().c("Invalid sampling rate. Event name, sample rate", aVarB.z(), Integer.valueOf(aVarB.v()));
                        } else {
                            aVar4.put(aVarB.z(), Integer.valueOf(aVarB.v()));
                        }
                    }
                }
            }
        }
        this.e.put(str, hashSet);
        this.f.put(str, aVar2);
        this.g.put(str, aVar3);
        this.i.put(str, aVar4);
    }

    private final void G(final String str, ax.H6.N1 n1) {
        if (n1.n() == 0) {
            this.j.e(str);
            return;
        }
        j().K().b("EES programs found", Integer.valueOf(n1.n()));
        C1040s2 c1040s2 = n1.Z().get(0);
        try {
            ax.H6.C c = new ax.H6.C();
            c.c("internal.remoteConfig", new Callable() { // from class: com.google.android.gms.measurement.internal.r2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return new ax.H6.C5("internal.remoteConfig", new C6436y2(this.a, str));
                }
            });
            c.c("internal.appMetadata", new Callable() { // from class: ax.M6.l
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    final C6389q2 c6389q2 = this.a;
                    final String str2 = str;
                    return new K7("internal.appMetadata", new Callable() { // from class: com.google.android.gms.measurement.internal.s2
                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            C6389q2 c6389q3 = c6389q2;
                            String str3 = str2;
                            Z1 z1H0 = c6389q3.q().H0(str3);
                            HashMap map = new HashMap();
                            map.put("platform", "android");
                            map.put("package_name", str3);
                            map.put("gmp_version", 97001L);
                            if (z1H0 != null) {
                                String strO = z1H0.o();
                                if (strO != null) {
                                    map.put("app_version", strO);
                                }
                                map.put("app_version_int", Long.valueOf(z1H0.U()));
                                map.put("dynamite_version", Long.valueOf(z1H0.v0()));
                            }
                            return map;
                        }
                    });
                }
            });
            c.c("internal.logger", new Callable() { // from class: com.google.android.gms.measurement.internal.t2
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return new E7(this.a.k);
                }
            });
            c.b(c1040s2);
            this.j.d(str, c);
            j().K().c("EES program loaded for appId, activities", str, Integer.valueOf(c1040s2.J().n()));
            Iterator<C1032r2> it = c1040s2.J().L().iterator();
            while (it.hasNext()) {
                j().K().b("EES program activity", it.next().K());
            }
        } catch (C0918e0 unused) {
            j().G().b("Failed to load EES program. appId", str);
        }
    }

    private final void h0(String str) throws Throwable {
        u();
        n();
        C2254p.f(str);
        if (this.h.get(str) == null) {
            C6361m c6361mJ0 = q().J0(str);
            if (c6361mJ0 != null) {
                ax.H6.N1.a aVarB = A(str, c6361mJ0.a).B();
                F(str, aVarB);
                this.d.put(str, D((ax.H6.N1) ((AbstractC0994m4) aVarB.y())));
                this.h.put(str, (ax.H6.N1) ((AbstractC0994m4) aVarB.y()));
                G(str, (ax.H6.N1) ((AbstractC0994m4) aVarB.y()));
                this.l.put(str, aVarB.C());
                this.m.put(str, c6361mJ0.b);
                this.n.put(str, c6361mJ0.c);
                return;
            }
            this.d.put(str, null);
            this.f.put(str, null);
            this.e.put(str, null);
            this.g.put(str, null);
            this.h.put(str, null);
            this.l.put(str, null);
            this.m.put(str, null);
            this.n.put(str, null);
            this.i.put(str, null);
        }
    }

    static /* synthetic */ ax.H6.C z(C6389q2 c6389q2, String str) throws Throwable {
        c6389q2.u();
        C2254p.f(str);
        if (!c6389q2.X(str)) {
            return null;
        }
        if (!c6389q2.h.containsKey(str) || c6389q2.h.get(str) == null) {
            c6389q2.h0(str);
        } else {
            c6389q2.G(str, c6389q2.h.get(str));
        }
        return c6389q2.j.h().get(str);
    }

    final ax.M6.o B(String str, C6330h3.a aVar) {
        n();
        h0(str);
        ax.H6.K1 k1J = J(str);
        if (k1J == null) {
            return ax.M6.o.UNINITIALIZED;
        }
        for (ax.H6.K1.a aVar2 : k1J.O()) {
            if (C(aVar2.L()) == aVar) {
                int i = C6430x2.c[aVar2.K().ordinal()];
                if (i != 1) {
                    return i != 2 ? ax.M6.o.UNINITIALIZED : ax.M6.o.GRANTED;
                }
                return ax.M6.o.DENIED;
            }
        }
        return ax.M6.o.UNINITIALIZED;
    }

    protected final boolean H(String str, byte[] bArr, String str2, String str3) {
        u();
        n();
        C2254p.f(str);
        ax.H6.N1.a aVarB = A(str, bArr).B();
        if (aVarB == null) {
            return false;
        }
        F(str, aVarB);
        G(str, (ax.H6.N1) ((AbstractC0994m4) aVarB.y()));
        this.h.put(str, (ax.H6.N1) ((AbstractC0994m4) aVarB.y()));
        this.l.put(str, aVarB.C());
        this.m.put(str, str2);
        this.n.put(str, str3);
        this.d.put(str, D((ax.H6.N1) ((AbstractC0994m4) aVarB.y())));
        q().a0(str, new ArrayList(aVarB.D()));
        try {
            aVarB.B();
            bArr = ((ax.H6.N1) ((AbstractC0994m4) aVarB.y())).l();
        } catch (RuntimeException e) {
            j().L().c("Unable to serialize reduced-size config. Storing full config instead. appId", V1.v(str), e);
        }
        C6354l c6354lQ = q();
        C2254p.f(str);
        c6354lQ.n();
        c6354lQ.u();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        contentValues.put("config_last_modified_time", str2);
        contentValues.put("e_tag", str3);
        try {
            if (c6354lQ.B().update("apps", contentValues, "app_id = ?", new String[]{str}) == 0) {
                c6354lQ.j().G().b("Failed to update remote config (got 0). appId", V1.v(str));
            }
        } catch (SQLiteException e2) {
            c6354lQ.j().G().c("Error storing remote config. appId", V1.v(str), e2);
        }
        this.h.put(str, (ax.H6.N1) ((AbstractC0994m4) aVarB.y()));
        return true;
    }

    final int I(String str, String str2) throws Throwable {
        Integer num;
        n();
        h0(str);
        Map<String, Integer> map = this.i.get(str);
        if (map == null || (num = map.get(str2)) == null) {
            return 1;
        }
        return num.intValue();
    }

    final ax.H6.K1 J(String str) throws Throwable {
        n();
        h0(str);
        ax.H6.N1 n1L = L(str);
        if (n1L == null || !n1L.c0()) {
            return null;
        }
        return n1L.P();
    }

    final C6330h3.a K(String str, C6330h3.a aVar) {
        n();
        h0(str);
        ax.H6.K1 k1J = J(str);
        if (k1J == null) {
            return null;
        }
        for (ax.H6.K1.c cVar : k1J.N()) {
            if (aVar == C(cVar.L())) {
                return C(cVar.K());
            }
        }
        return null;
    }

    protected final ax.H6.N1 L(String str) {
        u();
        n();
        C2254p.f(str);
        h0(str);
        return this.h.get(str);
    }

    final boolean M(String str, C6330h3.a aVar) throws Throwable {
        n();
        h0(str);
        ax.H6.K1 k1J = J(str);
        if (k1J == null) {
            return false;
        }
        for (ax.H6.K1.a aVar2 : k1J.M()) {
            if (aVar == C(aVar2.L())) {
                if (aVar2.K() == ax.H6.K1.d.GRANTED) {
                    return true;
                }
            }
        }
        return false;
    }

    final boolean N(String str, String str2) throws Throwable {
        Boolean bool;
        n();
        h0(str);
        if ("ecommerce_purchase".equals(str2) || "purchase".equals(str2) || "refund".equals(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.g.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    protected final String O(String str) {
        n();
        return this.n.get(str);
    }

    final boolean P(String str, String str2) throws Throwable {
        Boolean bool;
        n();
        h0(str);
        if (Y(str) && B5.J0(str2)) {
            return true;
        }
        if (a0(str) && B5.L0(str2)) {
            return true;
        }
        Map<String, Boolean> map = this.f.get(str);
        if (map == null || (bool = map.get(str2)) == null) {
            return false;
        }
        return bool.booleanValue();
    }

    protected final String Q(String str) {
        n();
        return this.m.get(str);
    }

    final String R(String str) throws Throwable {
        n();
        h0(str);
        return this.l.get(str);
    }

    final Set<String> S(String str) {
        n();
        h0(str);
        return this.e.get(str);
    }

    final SortedSet<String> T(String str) {
        n();
        h0(str);
        TreeSet treeSet = new TreeSet();
        ax.H6.K1 k1J = J(str);
        if (k1J != null) {
            Iterator<ax.H6.K1.f> it = k1J.L().iterator();
            while (it.hasNext()) {
                treeSet.add(it.next().K());
            }
        }
        return treeSet;
    }

    protected final void U(String str) {
        n();
        this.m.put(str, null);
    }

    final void V(String str) {
        n();
        this.h.remove(str);
    }

    final boolean W(String str) {
        n();
        ax.H6.N1 n1L = L(str);
        if (n1L == null) {
            return false;
        }
        return n1L.b0();
    }

    public final boolean X(String str) {
        ax.H6.N1 n1;
        return (TextUtils.isEmpty(str) || (n1 = this.h.get(str)) == null || n1.n() == 0) ? false : true;
    }

    final boolean Y(String str) {
        return "1".equals(c(str, "measurement.upload.blacklist_internal"));
    }

    final boolean Z(String str) {
        n();
        h0(str);
        ax.H6.K1 k1J = J(str);
        return k1J == null || !k1J.Q() || k1J.P();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    final boolean a0(String str) {
        return "1".equals(c(str, "measurement.upload.blacklist_public"));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    final boolean b0(String str) throws Throwable {
        n();
        h0(str);
        return this.e.get(str) != null && this.e.get(str).contains("app_instance_id");
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6340j
    public final String c(String str, String str2) throws Throwable {
        n();
        h0(str);
        Map<String, String> map = this.d.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    final boolean c0(String str) throws Throwable {
        n();
        h0(str);
        if (this.e.get(str) != null) {
            return this.e.get(str).contains("device_model") || this.e.get(str).contains("device_info");
        }
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    final boolean d0(String str) throws Throwable {
        n();
        h0(str);
        return this.e.get(str) != null && this.e.get(str).contains("enhanced_user_id");
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    final boolean e0(String str) throws Throwable {
        n();
        h0(str);
        return this.e.get(str) != null && this.e.get(str).contains("google_signals");
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    final boolean f0(String str) throws Throwable {
        n();
        h0(str);
        if (this.e.get(str) != null) {
            return this.e.get(str).contains("os_version") || this.e.get(str).contains("device_info");
        }
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    final boolean g0(String str) throws Throwable {
        n();
        h0(str);
        return this.e.get(str) != null && this.e.get(str).contains("user_id");
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

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ x5 o() {
        return super.o();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ J5 p() {
        return super.p();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6354l q() {
        return super.q();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ C6389q2 r() {
        return super.r();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ Q4 s() {
        return super.s();
    }

    @Override // com.google.android.gms.measurement.internal.C6360l5
    public final /* bridge */ /* synthetic */ o5 t() {
        return super.t();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    final long y(String str) throws Throwable {
        String strC = c(str, "measurement.account.time_zone_offset_minutes");
        if (TextUtils.isEmpty(strC)) {
            return 0L;
        }
        try {
            return Long.parseLong(strC);
        } catch (NumberFormatException e) {
            j().L().c("Unable to parse timezone offset. appId", V1.v(str), e);
            return 0L;
        }
    }
}
