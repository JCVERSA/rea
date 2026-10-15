package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import ax.I6.AbstractBinderC1117c;
import ax.I6.C1121c3;
import ax.I6.C1131e3;
import ax.I6.C1141g3;
import ax.I6.C1156j3;
import ax.I6.C1161k3;
import ax.I6.C1181o3;
import ax.I6.C1195r3;
import ax.I6.C1226y;
import ax.I6.EnumC1171m3;
import ax.I6.InterfaceC1122d;
import ax.I6.V3;
import ax.I6.X3;
import ax.I6.b4;
import ax.I6.c4;
import ax.I6.e4;
import j$.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
final class u implements ServiceConnection {
    private final C1226y X;
    private final C1226y Y;
    private final int Z;
    final /* synthetic */ C6252b k0;
    private final ax.D3.b q;

    /* synthetic */ u(C6252b c6252b, ax.D3.b bVar, int i, ax.D3.j jVar) {
        Objects.requireNonNull(c6252b);
        this.k0 = c6252b;
        this.X = C1226y.c(c6252b.J);
        this.Y = C1226y.c(c6252b.J);
        this.q = bVar;
        this.Z = i;
    }

    public static /* synthetic */ Object a(u uVar) {
        Bundle bundle;
        InterfaceC1122d interfaceC1122d;
        EnumC1171m3 enumC1171m3;
        C6252b c6252b = uVar.k0;
        synchronized (c6252b.a) {
            try {
                if (c6252b.b != 3) {
                    boolean z = c6252b.b == 1;
                    if (TextUtils.isEmpty(null)) {
                        bundle = null;
                    } else {
                        bundle = new Bundle();
                        bundle.putString("accountName", null);
                        ax.I6.Q.c(bundle, c6252b.c, c6252b.d, c6252b.I.longValue());
                    }
                    EnumC1171m3 enumC1171m4 = EnumC1171m3.REASON_UNSPECIFIED;
                    synchronized (c6252b.a) {
                        interfaceC1122d = c6252b.i;
                    }
                    if (interfaceC1122d == null) {
                        C6252b c6252b2 = uVar.k0;
                        c6252b2.w(0);
                        int i = uVar.Z;
                        EnumC1171m3 enumC1171m5 = EnumC1171m3.SERVICE_RESET_TO_NULL;
                        C6254d c6254d = H.j;
                        c6252b2.v(enumC1171m5, c6254d, i);
                        uVar.g(c6254d);
                    } else {
                        C6252b c6252b3 = uVar.k0;
                        String packageName = c6252b3.g.getPackageName();
                        int i2 = 25;
                        int iM7 = 3;
                        while (true) {
                            if (i2 < 3) {
                                i2 = 0;
                                break;
                            }
                            if (bundle == null) {
                                try {
                                    iM7 = interfaceC1122d.M7(i2, packageName, "subs");
                                } catch (Exception e) {
                                    ax.I6.Q.m("BillingClient", "Exception while checking if billing is supported; try to reconnect", e);
                                    if (e instanceof DeadObjectException) {
                                        enumC1171m3 = EnumC1171m3.IS_BILLING_SUPPORTED_DEAD_OBJECT_EXCEPTION;
                                    } else if (e instanceof RemoteException) {
                                        enumC1171m3 = EnumC1171m3.IS_BILLING_SUPPORTED_REMOTE_EXCEPTION;
                                    } else {
                                        enumC1171m3 = e instanceof SecurityException ? EnumC1171m3.IS_BILLING_SUPPORTED_SECURITY_EXCEPTION : EnumC1171m3.IS_BILLING_SUPPORTED_SERVICE_CALL_EXCEPTION;
                                    }
                                    String strA = enumC1171m3.equals(EnumC1171m3.IS_BILLING_SUPPORTED_SERVICE_CALL_EXCEPTION) ? F.a(e) : null;
                                    uVar.k0.w(0);
                                    uVar.f(C6252b.q0(e), enumC1171m3, strA, z);
                                    uVar.g(C6252b.q0(e));
                                }
                            } else {
                                iM7 = interfaceC1122d.L6(i2, packageName, "subs", bundle);
                            }
                            if (iM7 == 0) {
                                ax.I6.Q.k("BillingClient", "highestLevelSupportedForSubs: " + i2);
                                break;
                            }
                            i2--;
                        }
                        c6252b3.l = i2 >= 5;
                        c6252b3.k = i2 >= 3;
                        if (i2 < 3) {
                            enumC1171m4 = EnumC1171m3.SUBSCRIPTIONS_NOT_SUPPORTED;
                            ax.I6.Q.k("BillingClient", "In-app billing API does not support subscription on this device.");
                        }
                        for (int i3 = 25; i3 >= 3; i3--) {
                            iM7 = bundle == null ? interfaceC1122d.M7(i3, packageName, "inapp") : interfaceC1122d.L6(i3, packageName, "inapp", bundle);
                            if (iM7 == 0) {
                                c6252b3.m = i3;
                                ax.I6.Q.k("BillingClient", "mHighestLevelSupportedForInApp: " + c6252b3.m);
                                break;
                            }
                        }
                        C6252b.G(c6252b3, c6252b3.m);
                        if (c6252b3.m < 3) {
                            enumC1171m4 = EnumC1171m3.ONE_TIME_PRODUCT_NOT_SUPPORTED;
                            ax.I6.Q.l("BillingClient", "In-app billing API version 3 is not supported on this device.");
                        }
                        C6252b.I(c6252b3, iM7);
                        if (iM7 != 0) {
                            C6254d c6254d2 = H.b;
                            uVar.f(c6254d2, enumC1171m4, null, z);
                            uVar.g(c6254d2);
                        } else {
                            try {
                                Long lE = uVar.e(z);
                                if (z) {
                                    C1141g3 c1141g3J = C1156j3.J();
                                    c1141g3J.v(6);
                                    c4 c4VarI = e4.I();
                                    int i4 = uVar.Z;
                                    c4VarI.q(i4 > 0);
                                    c4VarI.t(i4);
                                    if (lE != null) {
                                        c4VarI.u(lE.longValue());
                                    }
                                    C6252b c6252b4 = uVar.k0;
                                    c1141g3J.u(c4VarI);
                                    c6252b4.u((C1156j3) c1141g3J.l());
                                } else {
                                    V3 v3G = X3.G();
                                    C1161k3 c1161k3J = C1181o3.J();
                                    c1161k3J.w(0);
                                    v3G.q(c1161k3J);
                                    if (lE != null) {
                                        v3G.t(lE.longValue());
                                    }
                                    uVar.k0.h.c((X3) v3G.l());
                                }
                            } catch (Throwable th) {
                                ax.I6.Q.m("BillingClient", "Unable to log.", th);
                            }
                            uVar.g(H.i);
                        }
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return null;
    }

    public static /* synthetic */ void b(u uVar) {
        C6252b c6252b = uVar.k0;
        c6252b.w(0);
        EnumC1171m3 enumC1171m3 = EnumC1171m3.EXECUTE_ASYNC_TIMEOUT;
        C6254d c6254d = H.k;
        c6252b.v(enumC1171m3, c6254d, uVar.Z);
        uVar.g(c6254d);
    }

    private final Long e(boolean z) {
        if (z) {
            C1226y c1226y = this.X;
            if (!c1226y.g()) {
                return null;
            }
            c1226y.f();
            return Long.valueOf(c1226y.a(TimeUnit.MILLISECONDS));
        }
        C1226y c1226y2 = this.Y;
        if (!c1226y2.g()) {
            return null;
        }
        c1226y2.f();
        return Long.valueOf(c1226y2.a(TimeUnit.MILLISECONDS));
    }

    private final void f(C6254d c6254d, EnumC1171m3 enumC1171m3, String str, boolean z) {
        try {
            C1161k3 c1161k3J = C1181o3.J();
            c1161k3J.w(c6254d.c());
            c1161k3J.t(c6254d.a());
            c1161k3J.v(enumC1171m3);
            if (str != null) {
                c1161k3J.q(str);
            }
            Long lE = e(z);
            if (!z) {
                V3 v3G = X3.G();
                v3G.q(c1161k3J);
                if (lE != null) {
                    v3G.t(lE.longValue());
                }
                this.k0.h.c((X3) v3G.l());
                return;
            }
            c4 c4VarI = e4.I();
            int i = this.Z;
            c4VarI.q(i > 0);
            c4VarI.t(i);
            if (lE != null) {
                c4VarI.u(lE.longValue());
            }
            C6252b c6252b = this.k0;
            C1121c3 c1121c3L = C1131e3.L();
            c1121c3L.t(c1161k3J);
            c1121c3L.w(6);
            c1121c3L.v(c4VarI);
            c6252b.s((C1131e3) c1121c3L.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private final void g(C6254d c6254d) {
        C6252b c6252b = this.k0;
        synchronized (c6252b.a) {
            try {
                if (c6252b.b == 3) {
                    return;
                }
                try {
                    this.q.o(c6254d);
                } catch (Throwable th) {
                    ax.I6.Q.m("BillingClient", "Exception while calling onBillingSetupFinished.", th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    public final void c() {
        C1226y c1226y = this.X;
        c1226y.d();
        c1226y.e();
    }

    final boolean d() {
        return this.Z > 0;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName componentName) {
        ax.I6.Q.l("BillingClient", "Billing service died.");
        try {
            C6252b c6252b = this.k0;
            if (C6252b.M(c6252b)) {
                G g = c6252b.h;
                C1121c3 c1121c3L = C1131e3.L();
                c1121c3L.w(6);
                C1161k3 c1161k3J = C1181o3.J();
                c1161k3J.v(EnumC1171m3.BINDING_DIED);
                c1121c3L.t(c1161k3J);
                c4 c4VarI = e4.I();
                int i = this.Z;
                c4VarI.q(i > 0);
                c4VarI.t(i);
                c1121c3L.v(c4VarI);
                g.i((C1131e3) c1121c3L.l());
            } else {
                c6252b.h.j(C1195r3.F());
            }
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
        C6252b c6252b2 = this.k0;
        synchronized (c6252b2.a) {
            if (c6252b2.b != 3 && c6252b2.b != 0) {
                c6252b2.w(0);
                c6252b2.z();
                try {
                    this.q.t();
                } catch (Throwable th2) {
                    ax.I6.Q.m("BillingClient", "Exception while calling onBillingServiceDisconnected.", th2);
                }
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ax.I6.Q.k("BillingClient", "Billing service connected.");
        C6252b c6252b = this.k0;
        synchronized (c6252b.a) {
            try {
                if (c6252b.b == 3) {
                    return;
                }
                c6252b.i = AbstractBinderC1117c.P0(iBinder);
                if (C6252b.i(new Callable() { // from class: com.android.billingclient.api.s
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        u.a(this.a);
                        return null;
                    }
                }, 30000L, new Runnable() { // from class: com.android.billingclient.api.t
                    @Override // java.lang.Runnable
                    public final void run() {
                        u.b(this.q);
                    }
                }, c6252b.R(), c6252b.h()) == null) {
                    int i = this.Z;
                    C6254d c6254dU = c6252b.U();
                    c6252b.v(EnumC1171m3.MISSING_RESULT_FROM_EXECUTE_ASYNC, c6254dU, i);
                    g(c6254dU);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        ax.I6.Q.l("BillingClient", "Billing service disconnected.");
        try {
            C6252b c6252b = this.k0;
            if (C6252b.M(c6252b)) {
                G g = c6252b.h;
                C1121c3 c1121c3L = C1131e3.L();
                c1121c3L.w(6);
                C1161k3 c1161k3J = C1181o3.J();
                c1161k3J.v(EnumC1171m3.SERVICE_DISCONNECTED);
                c1121c3L.t(c1161k3J);
                c4 c4VarI = e4.I();
                int i = this.Z;
                c4VarI.q(i > 0);
                c4VarI.t(i);
                c1121c3L.v(c4VarI);
                g.i((C1131e3) c1121c3L.l());
            } else {
                c6252b.h.k(b4.F());
            }
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
        C1226y c1226y = this.Y;
        c1226y.d();
        c1226y.e();
        C6252b c6252b2 = this.k0;
        synchronized (c6252b2.a) {
            try {
                if (c6252b2.b == 3) {
                    return;
                }
                c6252b2.w(0);
                try {
                    this.q.t();
                } catch (Throwable th2) {
                    ax.I6.Q.m("BillingClient", "Exception while calling onBillingServiceDisconnected.", th2);
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }
}
