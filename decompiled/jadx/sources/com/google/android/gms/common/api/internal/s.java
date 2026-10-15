package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1472e;
import ax.p217n6.C2172c;
import ax.p249p6.C2204b;
import ax.p265q6.AbstractC2241c;
import ax.p265q6.C2243e;
import ax.p265q6.C2251m;
import ax.p265q6.C2255q;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes3.dex */
final class s implements InterfaceC1472e {
    private final C6272c a;
    private final int b;
    private final C2204b c;
    private final long d;
    private final long e;

    s(C6272c c6272c, int i, C2204b c2204b, long j, long j2, String str, String str2) {
        this.a = c6272c;
        this.b = i;
        this.c = c2204b;
        this.d = j;
        this.e = j2;
    }

    static s b(C6272c c6272c, int i, C2204b c2204b) {
        boolean zA;
        if (!c6272c.e()) {
            return null;
        }
        ax.p265q6.r rVarA = C2255q.b().a();
        if (rVarA == null) {
            zA = true;
        } else {
            if (!rVarA.z()) {
                return null;
            }
            zA = rVarA.A();
            o oVarT = c6272c.t(c2204b);
            if (oVarT != null) {
                if (!(oVarT.t() instanceof AbstractC2241c)) {
                    return null;
                }
                AbstractC2241c abstractC2241c = (AbstractC2241c) oVarT.t();
                if (abstractC2241c.J() && !abstractC2241c.f()) {
                    C2243e c2243eC = c(oVarT, abstractC2241c, i);
                    if (c2243eC == null) {
                        return null;
                    }
                    oVarT.E();
                    zA = c2243eC.E();
                }
            }
        }
        return new s(c6272c, i, c2204b, zA ? System.currentTimeMillis() : 0L, zA ? SystemClock.elapsedRealtime() : 0L, null, null);
    }

    private static C2243e c(o oVar, AbstractC2241c abstractC2241c, int i) {
        int[] iArrN;
        int[] iArrZ;
        C2243e c2243eH = abstractC2241c.H();
        if (c2243eH == null || !c2243eH.A() || ((iArrN = c2243eH.n()) != null ? !ax.p345v6.b.a(iArrN, i) : !((iArrZ = c2243eH.z()) == null || !ax.p345v6.b.a(iArrZ, i))) || oVar.q() >= c2243eH.j()) {
            return null;
        }
        return c2243eH;
    }

    @Override // ax.Q6.InterfaceC1472e
    public final void a(AbstractC1477j abstractC1477j) {
        o oVarT;
        int iE;
        int i;
        int i2;
        int i3;
        int iJ;
        long j;
        long jCurrentTimeMillis;
        int iElapsedRealtime;
        if (this.a.e()) {
            ax.p265q6.r rVarA = C2255q.b().a();
            if ((rVarA == null || rVarA.z()) && (oVarT = this.a.t(this.c)) != null && (oVarT.t() instanceof AbstractC2241c)) {
                AbstractC2241c abstractC2241c = (AbstractC2241c) oVarT.t();
                boolean zA = this.d > 0;
                int iZ = abstractC2241c.z();
                if (rVarA != null) {
                    zA &= rVarA.A();
                    int iJ2 = rVarA.j();
                    int iN = rVarA.n();
                    iE = rVarA.E();
                    if (abstractC2241c.J() && !abstractC2241c.f()) {
                        C2243e c2243eC = c(oVarT, abstractC2241c, this.b);
                        if (c2243eC == null) {
                            return;
                        }
                        boolean z = c2243eC.E() && this.d > 0;
                        iN = c2243eC.j();
                        zA = z;
                    }
                    i = iJ2;
                    i2 = iN;
                } else {
                    iE = 0;
                    i = 5000;
                    i2 = 100;
                }
                C6272c c6272c = this.a;
                if (abstractC1477j.n()) {
                    i3 = 0;
                    iJ = 0;
                } else {
                    if (abstractC1477j.l()) {
                        i3 = 100;
                    } else {
                        Exception excJ = abstractC1477j.j();
                        if (excJ instanceof ax.p233o6.b) {
                            Status statusA = ((ax.p233o6.b) excJ).a();
                            int iN2 = statusA.n();
                            C2172c c2172cJ = statusA.j();
                            if (c2172cJ == null) {
                                i3 = iN2;
                            } else {
                                iJ = c2172cJ.j();
                                i3 = iN2;
                            }
                        } else {
                            i3 = 101;
                        }
                    }
                    iJ = -1;
                }
                if (zA) {
                    long j2 = this.d;
                    long j3 = this.e;
                    j = j2;
                    jCurrentTimeMillis = System.currentTimeMillis();
                    iElapsedRealtime = (int) (SystemClock.elapsedRealtime() - j3);
                } else {
                    j = 0;
                    jCurrentTimeMillis = 0;
                    iElapsedRealtime = -1;
                }
                c6272c.C(new C2251m(this.b, i3, iJ, j, jCurrentTimeMillis, null, null, iZ, iElapsedRealtime), iE, i, i2);
            }
        }
    }
}
