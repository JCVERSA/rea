package com.google.android.gms.measurement.internal;

import ax.H6.C0974k2;
import ax.H6.H6;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6298d extends M5 {
    private ax.H6.G1 g;
    private final /* synthetic */ J5 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C6298d(J5 j5, String str, int i, ax.H6.G1 g1) {
        super(str, i);
        this.h = j5;
        this.g = g1;
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final int a() {
        return this.g.n();
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final boolean i() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.M5
    final boolean j() {
        return true;
    }

    final boolean k(Long l, Long l2, C0974k2 c0974k2, boolean z) {
        boolean z2 = H6.a() && this.h.d().G(this.a, F.j0);
        boolean zP = this.g.P();
        boolean zQ = this.g.Q();
        boolean zS = this.g.S();
        boolean z3 = zP || zQ || zS;
        Boolean boolD = null;
        boolD = null;
        boolD = null;
        boolD = null;
        boolD = null;
        if (z && !z3) {
            this.h.j().K().c("Property filter already evaluated true and it is not associated with an enhanced audience. audience ID, filter ID", Integer.valueOf(this.b), this.g.T() ? Integer.valueOf(this.g.n()) : null);
            return true;
        }
        ax.H6.E1 e1L = this.g.L();
        boolean zQ2 = e1L.Q();
        if (c0974k2.h0()) {
            if (e1L.T()) {
                boolD = M5.d(M5.c(c0974k2.Y(), e1L.N()), zQ2);
            } else {
                this.h.j().L().b("No number filter for long property. property", this.h.g().g(c0974k2.d0()));
            }
        } else if (c0974k2.f0()) {
            if (e1L.T()) {
                boolD = M5.d(M5.b(c0974k2.J(), e1L.N()), zQ2);
            } else {
                this.h.j().L().b("No number filter for double property. property", this.h.g().g(c0974k2.d0()));
            }
        } else if (!c0974k2.j0()) {
            this.h.j().L().b("User property has no value, property", this.h.g().g(c0974k2.d0()));
        } else if (e1L.V()) {
            boolD = M5.d(M5.g(c0974k2.e0(), e1L.O(), this.h.j()), zQ2);
        } else if (!e1L.T()) {
            this.h.j().L().b("No string or number filter defined. property", this.h.g().g(c0974k2.d0()));
        } else if (x5.h0(c0974k2.e0())) {
            boolD = M5.d(M5.e(c0974k2.e0(), e1L.N()), zQ2);
        } else {
            this.h.j().L().c("Invalid user property value for Numeric number filter. property, value", this.h.g().g(c0974k2.d0()), c0974k2.e0());
        }
        this.h.j().K().b("Property filter result", boolD == null ? "null" : boolD);
        if (boolD == null) {
            return false;
        }
        this.c = Boolean.TRUE;
        if (zS && !boolD.booleanValue()) {
            return true;
        }
        if (!z || this.g.P()) {
            this.d = boolD;
        }
        if (boolD.booleanValue() && z3 && c0974k2.i0()) {
            long jA0 = c0974k2.a0();
            if (l != null) {
                jA0 = l.longValue();
            }
            if (z2 && this.g.P() && !this.g.Q() && l2 != null) {
                jA0 = l2.longValue();
            }
            if (this.g.Q()) {
                this.f = Long.valueOf(jA0);
            } else {
                this.e = Long.valueOf(jA0);
            }
        }
        return true;
    }
}
