package com.google.android.gms.measurement.internal;

import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import ax.H6.C0917e;
import ax.H6.C0918e0;
import ax.M6.C1347a;
import ax.p217n6.C2181l;
import ax.p217n6.C2182m;
import ax.p265q6.C2254p;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public final class I2 extends ax.M6.g {
    private Boolean X;
    private String Y;
    private final q5 q;

    public I2(q5 q5Var) {
        this(q5Var, null);
    }

    private I2(q5 q5Var, String str) {
        C2254p.l(q5Var);
        this.q = q5Var;
        this.Y = null;
    }

    private final void L3(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            this.q.j().G().a("Measurement Service called without app package");
            throw new SecurityException("Measurement Service called without app package");
        }
        if (z) {
            try {
                if (this.X == null) {
                    this.X = Boolean.valueOf("com.google.android.gms".equals(this.Y) || ax.p345v6.t.a(this.q.a(), Binder.getCallingUid()) || C2182m.a(this.q.a()).c(Binder.getCallingUid()));
                }
                if (this.X.booleanValue()) {
                    return;
                }
            } catch (SecurityException e) {
                this.q.j().G().b("Measurement Service called with invalid calling package. appId", V1.v(str));
                throw e;
            }
        }
        if (this.Y == null && C2181l.k(this.q.a(), Binder.getCallingUid(), str)) {
            this.Y = str;
        }
        if (str.equals(this.Y)) {
        } else {
            throw new SecurityException(String.format("Unknown calling package name '%s'.", str));
        }
    }

    private final void W7(D d, E5 e5) {
        this.q.u0();
        this.q.v(d, e5);
    }

    private final void e1(Runnable runnable) {
        C2254p.l(runnable);
        if (this.q.l().J()) {
            runnable.run();
        } else {
            this.q.l().G(runnable);
        }
    }

    private final void s5(E5 e5, boolean z) {
        C2254p.l(e5);
        C2254p.f(e5.q);
        L3(e5.q, false);
        this.q.t0().k0(e5.X, e5.w0);
    }

    private final void y6(Runnable runnable) {
        C2254p.l(runnable);
        if (this.q.l().J()) {
            runnable.run();
        } else {
            this.q.l().D(runnable);
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final void F1(D d, String str, String str2) {
        C2254p.l(d);
        C2254p.f(str);
        L3(str, true);
        y6(new Y2(this, d, str));
    }

    @Override // ax.M6.InterfaceC1351e
    public final void H7(final E5 e5) {
        C2254p.f(e5.q);
        C2254p.l(e5.B0);
        e1(new Runnable() { // from class: com.google.android.gms.measurement.internal.H2
            @Override // java.lang.Runnable
            public final void run() {
                this.q.Y7(e5);
            }
        });
    }

    @Override // ax.M6.InterfaceC1351e
    public final void I1(A5 a5, E5 e5) {
        C2254p.l(a5);
        s5(e5, false);
        y6(new RunnableC6281a3(this, a5, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final void I6(E5 e5) {
        s5(e5, false);
        y6(new M2(this, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6332h5> K6(E5 e5, Bundle bundle) {
        s5(e5, false);
        C2254p.l(e5.q);
        try {
            return (List) this.q.l().w(new CallableC6302d3(this, e5, bundle)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.q.j().G().c("Failed to get trigger URIs. appId", V1.v(e5.q), e);
            return Collections.EMPTY_LIST;
        }
    }

    final /* synthetic */ void N2(String str, Bundle bundle) {
        this.q.h0().i0(str, bundle);
    }

    @Override // ax.M6.InterfaceC1351e
    public final void P3(long j, String str, String str2, String str3) {
        y6(new O2(this, str2, str3, str, j));
    }

    @Override // ax.M6.InterfaceC1351e
    public final void S3(E5 e5) {
        s5(e5, false);
        y6(new L2(this, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6312f> T3(String str, String str2, String str3) {
        L3(str, true);
        try {
            return (List) this.q.l().w(new U2(this, str, str2, str3)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.q.j().G().b("Failed to get conditional user properties as", e);
            return Collections.EMPTY_LIST;
        }
    }

    final void V7(D d, E5 e5) {
        boolean zD;
        if (!this.q.n0().X(e5.q)) {
            W7(d, e5);
            return;
        }
        this.q.j().K().b("EES config found for", e5.q);
        C6389q2 c6389q2N0 = this.q.n0();
        String str = e5.q;
        ax.H6.C c = TextUtils.isEmpty(str) ? null : c6389q2N0.j.c(str);
        if (c == null) {
            this.q.j().K().b("EES not loaded for", e5.q);
            W7(d, e5);
            return;
        }
        try {
            Map<String, Object> mapQ = this.q.s0().Q(d.X.A(), true);
            String strA = ax.M6.q.a(d.q);
            if (strA == null) {
                strA = d.q;
            }
            zD = c.d(new C0917e(strA, d.Z, mapQ));
        } catch (C0918e0 unused) {
            this.q.j().G().c("EES error. appId, eventName", e5.X, d.q);
            zD = false;
        }
        if (!zD) {
            this.q.j().K().b("EES was not applied to event", d.q);
            W7(d, e5);
            return;
        }
        if (c.g()) {
            this.q.j().K().b("EES edited event", d.q);
            W7(this.q.s0().H(c.a().d()), e5);
        } else {
            W7(d, e5);
        }
        if (c.f()) {
            for (C0917e c0917e : c.a().f()) {
                this.q.j().K().b("EES logging created event", c0917e.e());
                W7(this.q.s0().H(c0917e), e5);
            }
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final String X2(E5 e5) {
        s5(e5, false);
        return this.q.T(e5);
    }

    final /* synthetic */ void X7(E5 e5) {
        this.q.u0();
        this.q.g0(e5);
    }

    final D Y3(D d, E5 e5) {
        C c;
        if (!"_cmp".equals(d.q) || (c = d.X) == null || c.j() == 0) {
            return d;
        }
        String strJ = d.X.J("_cis");
        if (!"referrer broadcast".equals(strJ) && !"referrer API".equals(strJ)) {
            return d;
        }
        this.q.j().J().b("Event has been filtered ", d.toString());
        return new D("_cmpx", d.X, d.Y, d.Z);
    }

    final /* synthetic */ void Y7(E5 e5) {
        this.q.u0();
        this.q.i0(e5);
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<A5> d6(String str, String str2, boolean z, E5 e5) {
        s5(e5, false);
        String str3 = e5.q;
        C2254p.l(str3);
        try {
            List<C5> list = (List) this.q.l().w(new P2(this, str3, str, str2)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (C5 c5 : list) {
                if (z || !B5.J0(c5.c)) {
                    arrayList.add(new A5(c5));
                }
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.q.j().G().c("Failed to query user properties. appId", V1.v(e5.q), e);
            return Collections.EMPTY_LIST;
        } catch (ExecutionException e2) {
            e = e2;
            this.q.j().G().c("Failed to query user properties. appId", V1.v(e5.q), e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<A5> d7(E5 e5, boolean z) {
        s5(e5, false);
        String str = e5.q;
        C2254p.l(str);
        try {
            List<C5> list = (List) this.q.l().w(new CallableC6295c3(this, str)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (C5 c5 : list) {
                if (z || !B5.J0(c5.c)) {
                    arrayList.add(new A5(c5));
                }
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.q.j().G().c("Failed to get user properties. appId", V1.v(e5.q), e);
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            this.q.j().G().c("Failed to get user properties. appId", V1.v(e5.q), e);
            return null;
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<A5> e2(String str, String str2, String str3, boolean z) {
        L3(str, true);
        try {
            List<C5> list = (List) this.q.l().w(new S2(this, str, str2, str3)).get();
            ArrayList arrayList = new ArrayList(list.size());
            for (C5 c5 : list) {
                if (z || !B5.J0(c5.c)) {
                    arrayList.add(new A5(c5));
                }
            }
            return arrayList;
        } catch (InterruptedException e) {
            e = e;
            this.q.j().G().c("Failed to get user properties as. appId", V1.v(str), e);
            return Collections.EMPTY_LIST;
        } catch (ExecutionException e2) {
            e = e2;
            this.q.j().G().c("Failed to get user properties as. appId", V1.v(str), e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final void g4(C6312f c6312f) {
        C2254p.l(c6312f);
        C2254p.l(c6312f.Y);
        C2254p.f(c6312f.q);
        L3(c6312f.q, true);
        y6(new Q2(this, new C6312f(c6312f)));
    }

    @Override // ax.M6.InterfaceC1351e
    public final byte[] g5(D d, String str) {
        C2254p.f(str);
        C2254p.l(d);
        L3(str, true);
        this.q.j().F().b("Log and bundle. event", this.q.j0().c(d.q));
        long jNanoTime = this.q.b().nanoTime() / 1000000;
        try {
            byte[] bArr = (byte[]) this.q.l().B(new CallableC6288b3(this, d, str)).get();
            if (bArr == null) {
                this.q.j().G().b("Log and bundle returned null. appId", V1.v(str));
                bArr = new byte[0];
            }
            this.q.j().F().d("Log and bundle processed. event, size, time_ms", this.q.j0().c(d.q), Integer.valueOf(bArr.length), Long.valueOf((this.q.b().nanoTime() / 1000000) - jNanoTime));
            return bArr;
        } catch (InterruptedException e) {
            e = e;
            this.q.j().G().d("Failed to log and bundle. appId, event, error", V1.v(str), this.q.j0().c(d.q), e);
            return null;
        } catch (ExecutionException e2) {
            e = e2;
            this.q.j().G().d("Failed to log and bundle. appId, event, error", V1.v(str), this.q.j0().c(d.q), e);
            return null;
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final void g6(D d, E5 e5) {
        C2254p.l(d);
        s5(e5, false);
        y6(new Z2(this, d, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final List<C6312f> i1(String str, String str2, E5 e5) {
        s5(e5, false);
        String str3 = e5.q;
        C2254p.l(str3);
        try {
            return (List) this.q.l().w(new R2(this, str3, str, str2)).get();
        } catch (InterruptedException | ExecutionException e) {
            this.q.j().G().b("Failed to get conditional user properties", e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override // ax.M6.InterfaceC1351e
    public final void m2(E5 e5) {
        C2254p.f(e5.q);
        C2254p.l(e5.B0);
        e1(new X2(this, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final void o2(final Bundle bundle, E5 e5) {
        s5(e5, false);
        final String str = e5.q;
        C2254p.l(str);
        y6(new Runnable() { // from class: com.google.android.gms.measurement.internal.J2
            @Override // java.lang.Runnable
            public final void run() {
                this.q.N2(str, bundle);
            }
        });
    }

    @Override // ax.M6.InterfaceC1351e
    public final void q2(final E5 e5) {
        C2254p.f(e5.q);
        C2254p.l(e5.B0);
        e1(new Runnable() { // from class: com.google.android.gms.measurement.internal.K2
            @Override // java.lang.Runnable
            public final void run() {
                this.q.X7(e5);
            }
        });
    }

    @Override // ax.M6.InterfaceC1351e
    public final void q3(C6312f c6312f, E5 e5) {
        C2254p.l(c6312f);
        C2254p.l(c6312f.Y);
        s5(e5, false);
        C6312f c6312f2 = new C6312f(c6312f);
        c6312f2.q = e5.q;
        y6(new N2(this, c6312f2, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final void u1(E5 e5) {
        C2254p.f(e5.q);
        L3(e5.q, false);
        y6(new T2(this, e5));
    }

    @Override // ax.M6.InterfaceC1351e
    public final C1347a w5(E5 e5) {
        s5(e5, false);
        C2254p.f(e5.q);
        try {
            return (C1347a) this.q.l().B(new W2(this, e5)).get(10000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            this.q.j().G().c("Failed to get consent. appId", V1.v(e5.q), e);
            return new C1347a(null);
        }
    }
}
