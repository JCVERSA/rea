package com.android.billingclient.api;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import ax.I6.B0;
import ax.I6.C1131e3;
import ax.I6.C1156j3;
import ax.I6.C1217w0;
import ax.I6.EnumC1171m3;
import ax.I6.EnumC1205t3;
import ax.I6.InterfaceC1152j;
import ax.I6.k4;
import ax.I6.m4;
import ax.I6.p4;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
final class E extends C6252b {
    private final Context K;
    private volatile int L;
    private volatile InterfaceC1152j M;
    private volatile D N;
    private volatile ScheduledExecutorService O;

    E(String str, Context context, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        super(null, context, null, null, c0557a);
        this.L = 0;
        this.K = context;
    }

    E(String str, C6255e c6255e, Context context, ax.D3.e eVar, ax.D3.i iVar, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        super(null, c6255e, context, eVar, null, null, null, c0557a);
        this.L = 0;
        this.K = context;
    }

    E(String str, C6255e c6255e, Context context, ax.D3.o oVar, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        super(null, c6255e, context, null, null, null, c0557a);
        this.L = 0;
        this.K = context;
    }

    private final synchronized void A0() {
        G0(27);
        try {
            try {
                ax.D3.k kVar = null;
                if (this.N != null && this.M != null) {
                    ax.I6.Q.k("BillingClientTesting", "Unbinding from Billing Override Service.");
                    this.K.unbindService(this.N);
                    this.N = new D(this, kVar);
                }
                this.M = null;
                if (this.O != null) {
                    this.O.shutdownNow();
                    this.O = null;
                }
            } catch (RuntimeException e) {
                ax.I6.Q.m("BillingClientTesting", "There was an exception while ending Billing Override Service connection!", e);
            }
            this.L = 3;
        } catch (Throwable th) {
            this.L = 3;
            throw th;
        }
    }

    private final synchronized void B0() {
        if (O0()) {
            ax.I6.Q.k("BillingClientTesting", "Billing Override Service connection is valid. No need to re-initialize.");
            G0(26);
            return;
        }
        if (this.L == 1) {
            ax.I6.Q.l("BillingClientTesting", "Client is already in the process of connecting to Billing Override Service.");
            return;
        }
        if (this.L == 3) {
            ax.I6.Q.l("BillingClientTesting", "Billing Override Service Client was already closed and can't be reused. Please create another instance.");
            F0(EnumC1171m3.BILLING_CLIENT_CLOSED, 26, H.a(-1, "Billing Override Service connection is disconnected."));
            return;
        }
        this.L = 1;
        ax.I6.Q.k("BillingClientTesting", "Starting Billing Override Service setup.");
        this.N = new D(this, null);
        Intent intent = new Intent("com.google.android.apps.play.billingtestcompanion.BillingOverrideService.BIND");
        intent.setPackage("com.google.android.apps.play.billingtestcompanion");
        Context context = this.K;
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        EnumC1171m3 enumC1171m3 = EnumC1171m3.REASON_UNSPECIFIED;
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            enumC1171m3 = EnumC1171m3.INTENT_SERVICE_NOT_FOUND;
        } else {
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null) {
                String str = serviceInfo.packageName;
                String str2 = serviceInfo.name;
                if (!Objects.equals(str, "com.google.android.apps.play.billingtestcompanion") || str2 == null) {
                    enumC1171m3 = EnumC1171m3.BILLING_SERVICE_BLOCKED;
                    ax.I6.Q.l("BillingClientTesting", "The device doesn't have valid Play Billing Lab.");
                } else {
                    ComponentName componentName = new ComponentName(str, str2);
                    Intent intent2 = new Intent(intent);
                    intent2.setComponent(componentName);
                    if (context.bindService(intent2, this.N, 1)) {
                        ax.I6.Q.k("BillingClientTesting", "Billing Override Service was bonded successfully.");
                        return;
                    } else {
                        enumC1171m3 = EnumC1171m3.BILLING_SERVICE_BLOCKED;
                        ax.I6.Q.l("BillingClientTesting", "Connection to Billing Override Service is blocked.");
                    }
                }
            }
        }
        this.L = 0;
        ax.I6.Q.k("BillingClientTesting", "Billing Override Service unavailable on device.");
        F0(enumC1171m3, 26, H.a(2, "Billing Override Service unavailable on device."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean C0(int i) {
        return i > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final C6254d D0(int i, int i2) {
        C6254d c6254dA = H.a(i2, "Billing override value was set by a license tester.");
        F0(EnumC1171m3.LICENSE_TESTER_BILLING_OVERRIDE, i, c6254dA);
        return c6254dA;
    }

    private final B0 E0(final int i) {
        if (O0()) {
            return p4.a(new m4() { // from class: com.android.billingclient.api.y
                @Override // ax.I6.m4
                public final Object a(k4 k4Var) {
                    return E.Q0(this.a, i, k4Var);
                }
            });
        }
        ax.I6.Q.l("BillingClientTesting", "Billing Override Service is not ready.");
        F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CONNECTION_NOT_READY, 28, H.a(-1, "Billing Override Service connection is disconnected."));
        return C1217w0.a(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void F0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d) {
        int i2 = F.a;
        C1131e3 c1131e3B = F.b(enumC1171m3, i, c6254d, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED);
        Objects.requireNonNull(c1131e3B, "ApiFailure should not be null");
        o0().i(c1131e3B);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void G0(int i) {
        int i2 = F.a;
        C1156j3 c1156j3C = F.c(i, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED);
        Objects.requireNonNull(c1156j3C, "ApiSuccess should not be null");
        o0().e(c1156j3C);
    }

    private final void H0(int i, ax.p019b0.a aVar, Runnable runnable) {
        C1217w0.c(C1217w0.b(E0(i), 28500L, TimeUnit.MILLISECONDS, U0()), new B(this, i, aVar, runnable), h());
    }

    public static /* synthetic */ Object Q0(E e, int i, k4 k4Var) {
        String str;
        try {
            if (e.M == null) {
                throw null;
            }
            InterfaceC1152j interfaceC1152j = e.M;
            String packageName = e.K.getPackageName();
            if (i == 2) {
                str = "LAUNCH_BILLING_FLOW";
            } else if (i == 3) {
                str = "ACKNOWLEDGE_PURCHASE";
            } else if (i == 4) {
                str = "CONSUME_ASYNC";
            } else if (i != 5) {
                str = i != 6 ? "QUERY_PRODUCT_DETAILS_ASYNC" : "START_CONNECTION";
            } else {
                str = "IS_FEATURE_SUPPORTED";
            }
            interfaceC1152j.u7(packageName, str, new C(k4Var));
            return "billingOverrideService.getBillingOverride";
        } catch (Exception e2) {
            e.F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, H.F);
            ax.I6.Q.m("BillingClientTesting", "An error occurred while retrieving billing override.", e2);
            k4Var.b(0);
            return "billingOverrideService.getBillingOverride";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int T0(B0 b0) {
        try {
            return ((Integer) b0.get(28500L, TimeUnit.MILLISECONDS)).intValue();
        } catch (TimeoutException e) {
            F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CALL_TIMEOUT, 28, H.F);
            ax.I6.Q.m("BillingClientTesting", "Asynchronous call to Billing Override Service timed out.", e);
            return 0;
        } catch (Exception e2) {
            if (e2 instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            F0(EnumC1171m3.BILLING_OVERRIDE_SERVICE_CALL_EXCEPTION, 28, H.F);
            ax.I6.Q.m("BillingClientTesting", "An error occurred while retrieving billing override.", e2);
            return 0;
        }
    }

    private final synchronized ScheduledExecutorService U0() {
        try {
            if (this.O == null) {
                this.O = Executors.newSingleThreadScheduledExecutor();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.O;
    }

    public final synchronized boolean O0() {
        return (this.L != 2 || this.M == null || this.N == null) ? false : true;
    }

    @Override // com.android.billingclient.api.C6252b, com.android.billingclient.api.AbstractC6251a
    public final void a() {
        A0();
        super.a();
    }

    @Override // com.android.billingclient.api.C6252b, com.android.billingclient.api.AbstractC6251a
    public final C6254d b(final Activity activity, final C6253c c6253c) {
        ax.p019b0.a aVar = new ax.p019b0.a() { // from class: com.android.billingclient.api.z
            @Override // ax.p019b0.a
            public final void accept(Object obj) {
                super/*com.android.billingclient.api.b*/.r0((C6254d) obj);
            }
        };
        Callable callable = new Callable() { // from class: com.android.billingclient.api.A
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return super/*com.android.billingclient.api.b*/.b(activity, c6253c);
            }
        };
        int iT0 = T0(E0(2));
        if (C0(iT0)) {
            C6254d c6254dD0 = D0(2, iT0);
            aVar.accept(c6254dD0);
            return c6254dD0;
        }
        try {
            return (C6254d) callable.call();
        } catch (Exception e) {
            EnumC1171m3 enumC1171m3 = EnumC1171m3.BILLING_OVERRIDE_SERVICE_FALLBACK_ERROR;
            C6254d c6254d = H.h;
            F0(enumC1171m3, 2, c6254d);
            ax.I6.Q.m("BillingClientTesting", "An internal error occurred.", e);
            return c6254d;
        }
    }

    @Override // com.android.billingclient.api.C6252b, com.android.billingclient.api.AbstractC6251a
    public final void d(final C6257g c6257g, final ax.D3.c cVar) {
        H0(7, new ax.p019b0.a() { // from class: com.android.billingclient.api.w
            @Override // ax.p019b0.a
            public final void accept(Object obj) {
                C6258h c6258h = new C6258h(new ArrayList(), new ArrayList());
                cVar.a((C6254d) obj, c6258h);
            }
        }, new Runnable() { // from class: com.android.billingclient.api.x
            @Override // java.lang.Runnable
            public final void run() {
                super/*com.android.billingclient.api.b*/.d(c6257g, cVar);
            }
        });
    }

    @Override // com.android.billingclient.api.C6252b, com.android.billingclient.api.AbstractC6251a
    public final void f(ax.D3.b bVar) {
        B0();
        super.f(bVar);
    }
}
