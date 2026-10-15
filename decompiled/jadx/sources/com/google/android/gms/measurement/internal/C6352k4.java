package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import ax.H6.C1013o6;
import ax.M6.C1347a;
import ax.M6.InterfaceC1351e;
import ax.p265q6.C2254p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.k4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6352k4 extends AbstractC6286b1 {
    private final J4 c;
    private InterfaceC1351e d;
    private volatile Boolean e;
    private final AbstractC6397s f;
    private final C6311e5 g;
    private final List<Runnable> h;
    private final AbstractC6397s i;

    protected C6352k4(E2 e2) {
        super(e2);
        this.h = new ArrayList();
        this.g = new C6311e5(e2.b());
        this.c = new J4(this);
        this.f = new C6391q4(this, e2);
        this.i = new C6438y4(this, e2);
    }

    static /* synthetic */ void M(C6352k4 c6352k4, ComponentName componentName) {
        c6352k4.n();
        if (c6352k4.d != null) {
            c6352k4.d = null;
            c6352k4.j().K().b("Disconnected from device MeasurementService", componentName);
            c6352k4.n();
            c6352k4.Y();
        }
    }

    private final void P(Runnable runnable) throws IllegalStateException {
        n();
        if (f0()) {
            runnable.run();
        } else {
            if (this.h.size() >= 1000) {
                j().G().a("Discarding data. Max runnable queue size reached");
                return;
            }
            this.h.add(runnable);
            this.i.b(60000L);
            Y();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void k0() {
        n();
        j().K().b("Processing queued up service tasks", Integer.valueOf(this.h.size()));
        Iterator<Runnable> it = this.h.iterator();
        while (it.hasNext()) {
            try {
                it.next().run();
            } catch (RuntimeException e) {
                j().G().b("Task exception while flushing queue", e);
            }
        }
        this.h.clear();
        this.i.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void l0() {
        n();
        this.g.c();
        this.f.b(F.K.a(null).longValue());
    }

    private final E5 o0(boolean z) {
        return p().B(z ? j().O() : null);
    }

    static /* synthetic */ void q0(C6352k4 c6352k4) {
        c6352k4.n();
        if (c6352k4.f0()) {
            c6352k4.j().K().a("Inactivity, disconnecting from the service");
            c6352k4.Z();
        }
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6286b1
    protected final boolean A() {
        return false;
    }

    public final void C(Bundle bundle) {
        n();
        v();
        P(new RunnableC6444z4(this, o0(false), bundle));
    }

    public final void D(ax.H6.M0 m0) {
        n();
        v();
        P(new RunnableC6414u4(this, o0(false), m0));
    }

    public final void E(ax.H6.M0 m0, D d, String str) {
        n();
        v();
        if (i().u(12451000) == 0) {
            P(new B4(this, d, str, m0));
        } else {
            j().L().a("Not bundling data. Service unavailable or out of date");
            i().V(m0, new byte[0]);
        }
    }

    protected final void F(ax.H6.M0 m0, String str, String str2) {
        n();
        v();
        P(new H4(this, str, str2, o0(false), m0));
    }

    protected final void G(ax.H6.M0 m0, String str, String str2, boolean z) {
        n();
        v();
        P(new RunnableC6385p4(this, str, str2, o0(false), z, m0));
    }

    protected final void H(InterfaceC1351e interfaceC1351e) {
        n();
        C2254p.l(interfaceC1351e);
        this.d = interfaceC1351e;
        l0();
        k0();
    }

    final void I(InterfaceC1351e interfaceC1351e, ax.p281r6.a aVar, E5 e5) throws Throwable {
        int size;
        n();
        v();
        int i = 0;
        int i2 = 100;
        while (i < 1001 && i2 == 100) {
            ArrayList arrayList = new ArrayList();
            List<ax.p281r6.a> listC = q().C(100);
            if (listC != null) {
                arrayList.addAll(listC);
                size = listC.size();
            } else {
                size = 0;
            }
            if (aVar != null && size < 100) {
                arrayList.add(aVar);
            }
            int size2 = arrayList.size();
            int i3 = 0;
            while (i3 < size2) {
                Object obj = arrayList.get(i3);
                i3++;
                ax.p281r6.a aVar2 = (ax.p281r6.a) obj;
                if (aVar2 instanceof D) {
                    try {
                        interfaceC1351e.g6((D) aVar2, e5);
                    } catch (RemoteException e) {
                        j().G().b("Failed to send event to the service", e);
                    }
                } else if (aVar2 instanceof A5) {
                    try {
                        interfaceC1351e.I1((A5) aVar2, e5);
                    } catch (RemoteException e2) {
                        j().G().b("Failed to send user property to the service", e2);
                    }
                } else if (aVar2 instanceof C6312f) {
                    try {
                        interfaceC1351e.q3((C6312f) aVar2, e5);
                    } catch (RemoteException e3) {
                        j().G().b("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    j().G().a("Discarding data. Unrecognized parcel type.");
                }
            }
            i++;
            i2 = size;
        }
    }

    protected final void J(C6312f c6312f) {
        C2254p.l(c6312f);
        n();
        v();
        P(new F4(this, true, o0(true), q().E(c6312f), new C6312f(c6312f), c6312f));
    }

    protected final void K(D d, String str) {
        C2254p.l(d);
        n();
        v();
        P(new C4(this, true, o0(true), q().F(d), d, str));
    }

    protected final void L(C6303d4 c6303d4) {
        n();
        v();
        P(new RunnableC6426w4(this, c6303d4));
    }

    protected final void O(A5 a5) {
        n();
        v();
        P(new RunnableC6408t4(this, o0(true), q().G(a5), a5));
    }

    public final void Q(AtomicReference<String> atomicReference) {
        n();
        v();
        P(new RunnableC6420v4(this, atomicReference, o0(false)));
    }

    protected final void R(AtomicReference<List<C6332h5>> atomicReference, Bundle bundle) {
        n();
        v();
        P(new RunnableC6396r4(this, atomicReference, o0(false), bundle));
    }

    protected final void S(AtomicReference<List<C6312f>> atomicReference, String str, String str2, String str3) {
        n();
        v();
        P(new E4(this, atomicReference, str, str2, str3, o0(false)));
    }

    protected final void T(AtomicReference<List<A5>> atomicReference, String str, String str2, String str3, boolean z) {
        n();
        v();
        P(new G4(this, atomicReference, str, str2, str3, o0(false), z));
    }

    protected final void U(boolean z) {
        n();
        v();
        if ((!C1013o6.a() || !d().t(F.V0)) && z) {
            q().H();
        }
        if (h0()) {
            P(new D4(this, o0(false)));
        }
    }

    protected final C1347a V() {
        n();
        v();
        InterfaceC1351e interfaceC1351e = this.d;
        if (interfaceC1351e == null) {
            Y();
            j().F().a("Failed to get consents; not connected to service yet.");
            return null;
        }
        E5 e5O0 = o0(false);
        C2254p.l(e5O0);
        try {
            C1347a c1347aW5 = interfaceC1351e.w5(e5O0);
            l0();
            return c1347aW5;
        } catch (RemoteException e) {
            j().G().b("Failed to get consents; remote exception", e);
            return null;
        }
    }

    final Boolean W() {
        return this.e;
    }

    protected final void X() {
        n();
        v();
        E5 e5O0 = o0(true);
        q().I();
        P(new RunnableC6432x4(this, e5O0));
    }

    final void Y() {
        n();
        v();
        if (f0()) {
            return;
        }
        if (j0()) {
            this.c.a();
            return;
        }
        if (d().V()) {
            return;
        }
        List<ResolveInfo> listQueryIntentServices = a().getPackageManager().queryIntentServices(new Intent().setClassName(a(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            j().G().a("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        Intent intent = new Intent("com.google.android.gms.measurement.START");
        intent.setComponent(new ComponentName(a(), "com.google.android.gms.measurement.AppMeasurementService"));
        this.c.b(intent);
    }

    public final void Z() {
        n();
        v();
        this.c.d();
        try {
            ax.p329u6.a.b().c(a(), this.c);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.d = null;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    final /* synthetic */ void a0() {
        InterfaceC1351e interfaceC1351e = this.d;
        if (interfaceC1351e == null) {
            j().G().a("Failed to send Dma consent settings to service");
            return;
        }
        try {
            E5 e5O0 = o0(false);
            C2254p.l(e5O0);
            interfaceC1351e.q2(e5O0);
            l0();
        } catch (RemoteException e) {
            j().G().b("Failed to send Dma consent settings to the service", e);
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    final /* synthetic */ void b0() {
        InterfaceC1351e interfaceC1351e = this.d;
        if (interfaceC1351e == null) {
            j().G().a("Failed to send storage consent settings to service");
            return;
        }
        try {
            E5 e5O0 = o0(false);
            C2254p.l(e5O0);
            interfaceC1351e.H7(e5O0);
            l0();
        } catch (RemoteException e) {
            j().G().b("Failed to send storage consent settings to the service", e);
        }
    }

    protected final void c0() {
        n();
        v();
        E5 e5O0 = o0(false);
        q().H();
        P(new RunnableC6402s4(this, e5O0));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    protected final void d0() {
        n();
        v();
        P(new Runnable() { // from class: com.google.android.gms.measurement.internal.m4
            @Override // java.lang.Runnable
            public final void run() {
                this.q.a0();
            }
        });
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    protected final void e0() {
        n();
        v();
        P(new A4(this, o0(true)));
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    public final boolean f0() {
        n();
        v();
        return this.d != null;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    final boolean g0() {
        n();
        v();
        return !j0() || i().I0() >= 200900;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    final boolean h0() {
        n();
        v();
        return !j0() || i().I0() >= F.p0.a(null).intValue();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    final boolean i0() {
        n();
        v();
        return !j0() || i().I0() >= 241200;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    final boolean j0() {
        boolean z;
        n();
        v();
        if (this.e == null) {
            n();
            v();
            Boolean boolN = h().N();
            boolean z2 = true;
            if (boolN == null || !boolN.booleanValue()) {
                boolean z3 = false;
                if (p().C() == 1) {
                    z = true;
                } else {
                    j().K().a("Checking service availability");
                    int iU = i().u(12451000);
                    if (iU != 0) {
                        if (iU == 1) {
                            j().K().a("Service missing");
                        } else if (iU != 2) {
                            if (iU == 3) {
                                j().L().a("Service disabled");
                            } else if (iU == 9) {
                                j().L().a("Service invalid");
                            } else if (iU != 18) {
                                j().L().b("Unexpected service status", Integer.valueOf(iU));
                            } else {
                                j().L().a("Service updating");
                            }
                            z = false;
                            z2 = false;
                        } else {
                            j().F().a("Service container out of date");
                            if (i().I0() >= 17443) {
                                z2 = boolN == null;
                                z = false;
                            }
                        }
                        z = true;
                        z2 = false;
                    } else {
                        j().K().a("Service available");
                    }
                    z = true;
                }
                if (z2 || !d().V()) {
                    z3 = z;
                } else {
                    j().G().a("No way to upload. Consider using the full version of Analytics");
                }
                if (z3) {
                    h().x(z2);
                }
            }
            this.e = Boolean.valueOf(z2);
        }
        return this.e.booleanValue();
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

    protected final void n0(boolean z) {
        n();
        v();
        if ((!C1013o6.a() || !d().t(F.V0)) && z) {
            q().H();
        }
        P(new Runnable() { // from class: com.google.android.gms.measurement.internal.o4
            @Override // java.lang.Runnable
            public final void run() {
                this.q.b0();
            }
        });
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
