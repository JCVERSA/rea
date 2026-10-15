package com.android.billingclient.api;

import android.content.Context;
import ax.I6.A3;
import ax.I6.C1121c3;
import ax.I6.C1131e3;
import ax.I6.C1141g3;
import ax.I6.C1156j3;
import ax.I6.C1195r3;
import ax.I6.C1230y3;
import ax.I6.J3;
import ax.I6.P3;
import ax.I6.R3;
import ax.I6.X3;
import ax.I6.b4;

/* JADX INFO: loaded from: classes.dex */
final class I implements G {
    private A3 b;
    private final J c;

    I(Context context, A3 a3) {
        this.c = new J(context);
        this.b = a3;
    }

    private final void l(C1131e3 c1131e3, A3 a3) {
        if (c1131e3 == null) {
            return;
        }
        try {
            P3 p3K = R3.K();
            p3K.v(a3);
            p3K.q(c1131e3);
            this.c.a((R3) p3K.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    private final void m(C1156j3 c1156j3, A3 a3) {
        if (c1156j3 == null) {
            return;
        }
        try {
            P3 p3K = R3.K();
            p3K.v(a3);
            p3K.t(c1156j3);
            this.c.a((R3) p3K.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void a(C1156j3 c1156j3, long j, boolean z) {
        A3 a3;
        try {
            C1141g3 c1141g3 = (C1141g3) c1156j3.o();
            J3 j3 = (J3) c1156j3.E().o();
            j3.q(z);
            c1141g3.t(j3);
            C1156j3 c1156j4 = (C1156j3) c1141g3.l();
            if (j == 0) {
                a3 = this.b;
            } else {
                C1230y3 c1230y3 = (C1230y3) this.b.o();
                c1230y3.w(j);
                a3 = (A3) c1230y3.l();
            }
            m(c1156j4, a3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void b(C1156j3 c1156j3, int i) {
        try {
            C1230y3 c1230y3 = (C1230y3) this.b.o();
            c1230y3.u(i);
            this.b = (A3) c1230y3.l();
            e(c1156j3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void c(X3 x3) {
        try {
            J j = this.c;
            P3 p3K = R3.K();
            p3K.v(this.b);
            p3K.w(x3);
            j.a((R3) p3K.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void d(C1131e3 c1131e3, long j, boolean z) {
        A3 a3;
        try {
            C1121c3 c1121c3 = (C1121c3) c1131e3.o();
            J3 j3 = (J3) c1131e3.F().o();
            j3.q(z);
            c1121c3.u(j3);
            C1131e3 c1131e4 = (C1131e3) c1121c3.l();
            if (j == 0) {
                a3 = this.b;
            } else {
                C1230y3 c1230y3 = (C1230y3) this.b.o();
                c1230y3.w(j);
                a3 = (A3) c1230y3.l();
            }
            l(c1131e4, a3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void e(C1156j3 c1156j3) {
        try {
            m(c1156j3, this.b);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void f(C1131e3 c1131e3, int i) {
        try {
            C1230y3 c1230y3 = (C1230y3) this.b.o();
            c1230y3.u(i);
            this.b = (A3) c1230y3.l();
            i(c1131e3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void g(C1131e3 c1131e3, int i, long j, boolean z) {
        A3 a3;
        try {
            C1230y3 c1230y3 = (C1230y3) this.b.o();
            c1230y3.u(i);
            this.b = (A3) c1230y3.l();
            C1121c3 c1121c3 = (C1121c3) c1131e3.o();
            J3 j3 = (J3) c1131e3.F().o();
            j3.q(z);
            c1121c3.u(j3);
            C1131e3 c1131e4 = (C1131e3) c1121c3.l();
            if (j == 0) {
                a3 = this.b;
            } else {
                C1230y3 c1230y4 = (C1230y3) this.b.o();
                c1230y4.w(j);
                a3 = (A3) c1230y4.l();
            }
            l(c1131e4, a3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void h(C1131e3 c1131e3, int i, long j) {
        try {
            C1230y3 c1230y3 = (C1230y3) this.b.o();
            c1230y3.u(i);
            A3 a3 = (A3) c1230y3.l();
            this.b = a3;
            if (j != 0) {
                C1230y3 c1230y4 = (C1230y3) a3.o();
                c1230y4.w(j);
                a3 = (A3) c1230y4.l();
            }
            l(c1131e3, a3);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void i(C1131e3 c1131e3) {
        try {
            l(c1131e3, this.b);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void j(C1195r3 c1195r3) {
        try {
            P3 p3K = R3.K();
            p3K.v(this.b);
            p3K.u(c1195r3);
            this.c.a((R3) p3K.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }

    @Override // com.android.billingclient.api.G
    public final void k(b4 b4Var) {
        if (b4Var == null) {
            return;
        }
        try {
            P3 p3K = R3.K();
            p3K.v(this.b);
            p3K.z(b4Var);
            this.c.a((R3) p3K.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to log.", th);
        }
    }
}
