package com.google.android.gms.measurement.internal;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import ax.H6.AbstractC0994m4;
import ax.H6.C0893b2;
import ax.H6.C0902c2;
import ax.H6.C0911d2;
import ax.H6.C0929f2;
import ax.H6.C0938g2;
import ax.H6.C0947h2;
import ax.H6.C0974k2;
import ax.H6.O6;
import ax.H6.n7;
import ax.p265q6.C2254p;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.b4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6289b4 extends AbstractC6353k5 {
    public C6289b4(q5 q5Var) {
        super(q5Var);
    }

    private static String c(String str, String str2) {
        throw new SecurityException("This implementation should not be used.");
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    public final byte[] y(D d, String str) {
        C5 next;
        List<C5> list;
        C0929f2.b bVar;
        Bundle bundle;
        byte[] bArr;
        C0938g2.a aVar;
        C6433y c6433yA;
        long j;
        n();
        this.a.Q();
        C2254p.l(d);
        C2254p.f(str);
        if (!d().E(str, F.h0)) {
            j().F().b("Generating ScionPayload disabled. packageName", str);
            return new byte[0];
        }
        if (!"_iap".equals(d.q) && !"_iapx".equals(d.q)) {
            j().F().c("Generating a payload for this event is not available. package_name, event_name", str, d.q);
            return null;
        }
        C0929f2.b bVarO = C0929f2.O();
        q().X0();
        try {
            Z1 z1H0 = q().H0(str);
            if (z1H0 == null) {
                j().F().b("Log and bundle not available. package_name", str);
                byte[] bArr2 = new byte[0];
                q().Z0();
                return bArr2;
            }
            if (!z1H0.A()) {
                j().F().b("Log and bundle disabled. package_name", str);
                byte[] bArr3 = new byte[0];
                q().Z0();
                return bArr3;
            }
            C0938g2.a aVarC1 = C0938g2.M3().B0(1).c1("android");
            if (!TextUtils.isEmpty(z1H0.l())) {
                aVarC1.X(z1H0.l());
            }
            if (!TextUtils.isEmpty(z1H0.n())) {
                aVarC1.k0((String) C2254p.l(z1H0.n()));
            }
            if (!TextUtils.isEmpty(z1H0.o())) {
                aVarC1.q0((String) C2254p.l(z1H0.o()));
            }
            if (z1H0.U() != -2147483648L) {
                aVarC1.n0((int) z1H0.U());
            }
            aVarC1.t0(z1H0.z0()).i0(z1H0.v0());
            String strQ = z1H0.q();
            String strJ = z1H0.j();
            if (!TextUtils.isEmpty(strQ)) {
                aVarC1.W0(strQ);
            } else if (!TextUtils.isEmpty(strJ)) {
                aVarC1.L(strJ);
            }
            aVarC1.L0(z1H0.J0());
            C6330h3 c6330h3S = this.b.S(str);
            aVarC1.b0(z1H0.t0());
            if (this.a.p() && d().M(aVarC1.j1()) && c6330h3S.A() && !TextUtils.isEmpty(null)) {
                aVarC1.M0(null);
            }
            aVarC1.z0(c6330h3S.y());
            if (c6330h3S.A() && z1H0.z()) {
                Pair<String, Boolean> pairZ = s().z(z1H0.l(), c6330h3S);
                if (z1H0.z() && pairZ != null && !TextUtils.isEmpty((CharSequence) pairZ.first)) {
                    try {
                        aVarC1.e1(c((String) pairZ.first, Long.toString(d.Z)));
                        Object obj = pairZ.second;
                        if (obj != null) {
                            aVarC1.f0(((Boolean) obj).booleanValue());
                        }
                    } catch (SecurityException e) {
                        j().F().b("Resettable device id encryption failed", e.getMessage());
                        byte[] bArr4 = new byte[0];
                        q().Z0();
                        return bArr4;
                    }
                }
            }
            e().p();
            C0938g2.a aVarI0 = aVarC1.I0(Build.MODEL);
            e().p();
            aVarI0.a1(Build.VERSION.RELEASE).K0((int) e().v()).i1(e().w());
            try {
                if (c6330h3S.B() && z1H0.m() != null) {
                    aVarC1.e0(c((String) C2254p.l(z1H0.m()), Long.toString(d.Z)));
                }
                if (!TextUtils.isEmpty(z1H0.p())) {
                    aVarC1.U0((String) C2254p.l(z1H0.p()));
                }
                String strL = z1H0.l();
                List<C5> listS0 = q().S0(strL);
                Iterator<C5> it = listS0.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!"_lte".equals(next.c));
                if (next == null || next.e == null) {
                    list = listS0;
                    C5 c5 = new C5(strL, "auto", "_lte", b().a(), 0L);
                    list.add(c5);
                    q().f0(c5);
                } else {
                    list = listS0;
                }
                C0974k2[] c0974k2Arr = new C0974k2[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    C0974k2.a aVarD = C0974k2.b0().B(list.get(i).c).D(list.get(i).d);
                    o().W(aVarD, list.get(i).e);
                    c0974k2Arr[i] = (C0974k2) ((AbstractC0994m4) aVarD.y());
                }
                aVarC1.p0(Arrays.asList(c0974k2Arr));
                o().V(aVarC1);
                this.b.x(z1H0, aVarC1);
                if (O6.a() && d().t(F.N0)) {
                    this.b.Y(z1H0, aVarC1);
                }
                C6280a2 c6280a2B = C6280a2.b(d);
                i().N(c6280a2B.d, q().F0(str));
                i().W(c6280a2B, d().u(str));
                Bundle bundle2 = c6280a2B.d;
                bundle2.putLong("_c", 1L);
                j().F().a("Marking in-app purchase as real-time");
                bundle2.putLong("_r", 1L);
                bundle2.putString("_o", d.Y);
                if (i().E0(aVarC1.j1(), z1H0.v())) {
                    i().O(bundle2, "_dbg", 1L);
                    i().O(bundle2, "_r", 1L);
                }
                C6433y c6433yG0 = q().G0(str, d.q);
                if (c6433yG0 == null) {
                    bArr = null;
                    bundle = bundle2;
                    aVar = aVarC1;
                    bVar = bVarO;
                    c6433yA = new C6433y(str, d.q, 0L, 0L, d.Z, 0L, null, null, null, null);
                    j = 0;
                } else {
                    bVar = bVarO;
                    bundle = bundle2;
                    bArr = null;
                    aVar = aVarC1;
                    long j2 = c6433yG0.f;
                    c6433yA = c6433yG0.a(d.Z);
                    j = j2;
                }
                C6433y c6433y = c6433yA;
                q().U(c6433y);
                A a = new A(this.a, d.Y, str, d.q, d.Z, j, bundle);
                C0893b2.a aVarC = C0893b2.d0().I(a.d).G(a.b).C(a.e);
                for (String str2 : a.f) {
                    C0911d2.a aVarD2 = C0911d2.d0().D(str2);
                    Object objH = a.f.H(str2);
                    if (objH != null) {
                        o().U(aVarD2, objH);
                        aVarC.D(aVarD2);
                    }
                }
                C0938g2.a aVar2 = aVar;
                aVar2.G(aVarC).H(C0947h2.J().v(C0902c2.J().v(c6433y.c).w(d.q)));
                aVar2.K(p().z(z1H0.l(), Collections.EMPTY_LIST, aVar2.O(), Long.valueOf(aVarC.K()), Long.valueOf(aVarC.K())));
                if (aVarC.O()) {
                    aVar2.H0(aVarC.K()).o0(aVarC.K());
                }
                long jD0 = z1H0.D0();
                if (jD0 != 0) {
                    aVar2.x0(jD0);
                }
                long jH0 = z1H0.H0();
                if (jH0 != 0) {
                    aVar2.C0(jH0);
                } else if (jD0 != 0) {
                    aVar2.C0(jD0);
                }
                String strU = z1H0.u();
                if (n7.a() && d().E(str, F.s0) && strU != null) {
                    aVar2.g1(strU);
                }
                z1H0.y();
                aVar2.s0((int) z1H0.F0()).T0(97001L).O0(b().a()).l0(true);
                this.b.D(aVar2.j1(), aVar2);
                C0929f2.b bVar2 = bVar;
                bVar2.w(aVar2);
                z1H0.C0(aVar2.r0());
                z1H0.y0(aVar2.m0());
                q().V(z1H0, false, false);
                q().b1();
                q().Z0();
                try {
                    return o().i0(((C0929f2) ((AbstractC0994m4) bVar2.y())).l());
                } catch (IOException e2) {
                    j().G().c("Data loss. Failed to bundle and serialize. appId", V1.v(str), e2);
                    return bArr;
                }
            } catch (SecurityException e3) {
                j().F().b("app instance id encryption failed", e3.getMessage());
                byte[] bArr5 = new byte[0];
                q().Z0();
                return bArr5;
            }
        } catch (Throwable th) {
            q().Z0();
            throw th;
        }
    }
}
