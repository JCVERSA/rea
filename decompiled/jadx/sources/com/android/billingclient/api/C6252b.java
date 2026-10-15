package com.android.billingclient.api;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import ax.I6.A3;
import ax.I6.B0;
import ax.I6.C1107a;
import ax.I6.C1121c3;
import ax.I6.C1131e3;
import ax.I6.C1141g3;
import ax.I6.C1156j3;
import ax.I6.C1177o;
import ax.I6.C1217w0;
import ax.I6.C1226y;
import ax.I6.C1230y3;
import ax.I6.EnumC1171m3;
import ax.I6.EnumC1205t3;
import ax.I6.InterfaceC1122d;
import ax.I6.M0;
import ax.I6.N0;
import ax.I6.Q;
import ax.I6.c4;
import ax.I6.e4;
import ax.I6.k4;
import ax.I6.m4;
import ax.I6.p4;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import okhttp3.HttpUrl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.android.billingclient.api.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
class C6252b extends AbstractC6251a {
    private boolean A;
    private boolean B;
    private boolean C;
    private C6255e D;
    private boolean E;
    private boolean F;
    private volatile ax.D3.b G;
    private ExecutorService H;
    private final Long I;
    private ax.I6.B J;
    private final Object a;
    private volatile int b;
    private final String c;
    private final String d;
    private final Handler e;
    private volatile P f;
    private Context g;
    private G h;
    private volatile InterfaceC1122d i;
    private volatile u j;
    private boolean k;
    private boolean l;
    private int m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    C6252b(String str, Context context, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        this.a = new Object();
        this.b = 0;
        this.e = new Handler(Looper.getMainLooper());
        this.m = 0;
        Long lValueOf = Long.valueOf(new Random().nextLong());
        this.I = lValueOf;
        this.J = C1177o.a();
        this.c = "8.0.0";
        String strX = X();
        this.d = strX;
        this.g = context.getApplicationContext();
        C1230y3 c1230y3O = A3.O();
        c1230y3O.D("8.0.0");
        if (strX != null) {
            c1230y3O.E(strX);
        }
        c1230y3O.B(this.g.getPackageName());
        c1230y3O.v(lValueOf.longValue());
        c1230y3O.C(c0557a.f);
        c1230y3O.q(Build.VERSION.SDK_INT);
        c1230y3O.z(772604006L);
        try {
            c1230y3O.t(this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Error getting app version code.", th);
        }
        this.h = new I(this.g, (A3) c1230y3O.l());
        this.g.getPackageName();
        this.E = c0557a.f;
    }

    C6252b(String str, C6255e c6255e, Context context, ax.D3.e eVar, ax.D3.i iVar, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        this.a = new Object();
        this.b = 0;
        this.e = new Handler(Looper.getMainLooper());
        this.m = 0;
        this.I = Long.valueOf(new Random().nextLong());
        this.J = C1177o.a();
        this.c = "8.0.0";
        this.d = X();
        g(context, eVar, c6255e, null, "8.0.0", null, c0557a);
    }

    C6252b(String str, C6255e c6255e, Context context, ax.D3.o oVar, G g, ExecutorService executorService, AbstractC6251a.C0557a c0557a) {
        this.a = new Object();
        this.b = 0;
        this.e = new Handler(Looper.getMainLooper());
        this.m = 0;
        Long lValueOf = Long.valueOf(new Random().nextLong());
        this.I = lValueOf;
        this.J = C1177o.a();
        this.c = "8.0.0";
        String strX = X();
        this.d = strX;
        this.g = context.getApplicationContext();
        C1230y3 c1230y3O = A3.O();
        c1230y3O.D("8.0.0");
        if (strX != null) {
            c1230y3O.E(strX);
        }
        c1230y3O.B(this.g.getPackageName());
        c1230y3O.v(lValueOf.longValue());
        c1230y3O.C(c0557a.f);
        c1230y3O.q(Build.VERSION.SDK_INT);
        c1230y3O.z(772604006L);
        try {
            c1230y3O.t(this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Error getting app version code.", th);
        }
        this.h = new I(this.g, (A3) c1230y3O.l());
        ax.I6.Q.l("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.f = new P(this.g, null, null, null, null, this.h);
        this.D = c6255e;
        this.g.getPackageName();
        this.E = c0557a.f;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean A(long j) {
        try {
            C6254d c6254d = (C6254d) V(1).get(Build.VERSION.SDK_INT < 29 ? 0L : 3000L, TimeUnit.MILLISECONDS);
            if (c6254d.c() == 0) {
                ax.I6.Q.k("BillingClient", "Reconnection succeeded with result: " + c6254d.c());
            } else {
                ax.I6.Q.l("BillingClient", "Reconnection failed with result: " + c6254d.c());
            }
        } catch (Exception e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            ax.I6.Q.m("BillingClient", "Error during reconnection attempt: ", e);
        }
        return C();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean B(long j) {
        C1226y c1226yB = C1226y.b(this.J);
        long jA = 30000;
        for (int i = 1; i <= 3; i++) {
            try {
                long jMax = Math.max(0L, jA);
                if (jMax <= 0) {
                    ax.I6.Q.l("BillingClient", "No time remaining for reconnection attempt.");
                    return C();
                }
                C6254d c6254d = (C6254d) V(i).get(jMax, TimeUnit.MILLISECONDS);
                if (c6254d.c() == 0) {
                    ax.I6.Q.k("BillingClient", "Reconnection succeeded with result: " + c6254d.c());
                    return C();
                }
                ax.I6.Q.l("BillingClient", "Reconnection failed with result: " + c6254d.c());
                TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                jA = 30000 - c1226yB.a(timeUnit);
                long jPow = ((long) Math.pow(2.0d, i - 1)) * 1000;
                if (jA < jPow) {
                    ax.I6.Q.l("BillingClient", "Reconnection failed due to timeout limit reached.");
                    return C();
                }
                if (i < 3 && jPow > 0) {
                    try {
                        Thread.sleep(jPow);
                        jA = 30000 - c1226yB.a(timeUnit);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        ax.I6.Q.m("BillingClient", "Error sleeping during reconnection attempt: ", e);
                    }
                }
            } catch (Exception e2) {
                if (e2 instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                ax.I6.Q.m("BillingClient", "Error during reconnection attempt: ", e2);
            }
        }
        ax.I6.Q.l("BillingClient", "Max retries reached.");
        return C();
    }

    private final boolean C() {
        boolean z;
        synchronized (this.a) {
            try {
                z = false;
                if (this.b == 2 && this.i != null && this.j != null) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    private static final String D(int i) {
        if (i == 0) {
            return "DISCONNECTED";
        }
        if (i != 1) {
            return i != 2 ? "CLOSED" : "CONNECTED";
        }
        return "CONNECTING";
    }

    static /* bridge */ /* synthetic */ void G(C6252b c6252b, int i) {
        c6252b.m = i;
        c6252b.C = i >= 26;
        c6252b.B = i >= 24;
        c6252b.A = i >= 23;
        c6252b.z = i >= 22;
        c6252b.y = i >= 21;
        c6252b.x = i >= 20;
        c6252b.w = i >= 19;
        c6252b.v = i >= 18;
        c6252b.u = i >= 17;
        c6252b.t = i >= 16;
        c6252b.s = i >= 15;
        c6252b.r = i >= 14;
        c6252b.q = i >= 12;
        c6252b.p = i >= 9;
        c6252b.o = i >= 8;
        c6252b.n = i >= 6;
    }

    static /* bridge */ /* synthetic */ void I(C6252b c6252b, int i) {
        if (i != 0) {
            c6252b.w(0);
            return;
        }
        synchronized (c6252b.a) {
            try {
                if (c6252b.b == 3) {
                    return;
                }
                c6252b.w(2);
                P p = c6252b.f != null ? c6252b.f : null;
                if (p != null) {
                    p.g(c6252b.y);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static /* bridge */ /* synthetic */ boolean M(C6252b c6252b) {
        boolean z;
        synchronized (c6252b.a) {
            z = true;
            if (c6252b.b != 1) {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final /* synthetic */ Bundle P(int i, String str, String str2, C6253c c6253c, Bundle bundle) throws Exception {
        InterfaceC1122d interfaceC1122d;
        try {
            synchronized (this.a) {
                interfaceC1122d = this.i;
            }
            return interfaceC1122d == null ? ax.I6.Q.d(H.j, EnumC1171m3.SERVICE_RESET_TO_NULL) : interfaceC1122d.o5(i, this.g.getPackageName(), str, str2, null, bundle);
        } catch (DeadObjectException e) {
            return ax.I6.Q.e(H.j, EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION, F.a(e));
        } catch (Exception e2) {
            return ax.I6.Q.e(H.h, EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION, F.a(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final /* synthetic */ Bundle Q(String str, String str2) throws Exception {
        InterfaceC1122d interfaceC1122d;
        try {
            synchronized (this.a) {
                interfaceC1122d = this.i;
            }
            return interfaceC1122d == null ? ax.I6.Q.d(H.j, EnumC1171m3.SERVICE_RESET_TO_NULL) : interfaceC1122d.J3(3, this.g.getPackageName(), str, str2, null);
        } catch (DeadObjectException e) {
            return ax.I6.Q.e(H.j, EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION, F.a(e));
        } catch (Exception e2) {
            return ax.I6.Q.e(H.h, EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION, F.a(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Handler R() {
        return Looper.myLooper() == null ? this.e : new Handler(Looper.myLooper());
    }

    private final v S(C6254d c6254d, EnumC1171m3 enumC1171m3, String str, Exception exc) {
        ax.I6.Q.m("BillingClient", str, exc);
        d0(enumC1171m3, 7, c6254d, F.a(exc));
        return new v(c6254d.c(), c6254d.a(), new ArrayList(), new ArrayList());
    }

    private final C6254d T(int i) {
        ax.I6.Q.k("BillingClient", "Service connection is valid. No need to re-initialize.");
        C1141g3 c1141g3J = C1156j3.J();
        c1141g3J.v(6);
        c4 c4VarI = e4.I();
        c4VarI.v(true);
        c4VarI.q(i > 0);
        c4VarI.t(i);
        c1141g3J.u(c4VarI);
        u((C1156j3) c1141g3J.l());
        return H.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final C6254d U() {
        int[] iArr = {0, 3};
        synchronized (this.a) {
            for (int i = 0; i < 2; i++) {
                if (this.b == iArr[i]) {
                    return H.j;
                }
            }
            return H.h;
        }
    }

    private final B0 V(final int i) {
        if (this.E && !C()) {
            return p4.a(new m4() { // from class: com.android.billingclient.api.Q
                @Override // ax.I6.m4
                public final Object a(k4 k4Var) {
                    return C6252b.v0(this.a, i, k4Var);
                }
            });
        }
        ax.I6.Q.k("BillingClient", "Already connected or not opted into auto reconnection.");
        return C1217w0.a(H.i);
    }

    private final String W(C6257g c6257g) {
        if (TextUtils.isEmpty(null)) {
            return this.g.getPackageName();
        }
        return null;
    }

    @SuppressLint({"PrivateApi"})
    private static String X() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    private final ax.D3.q Z(int i, C6254d c6254d, EnumC1171m3 enumC1171m3, String str, Exception exc) {
        d0(enumC1171m3, 9, c6254d, F.a(exc));
        ax.I6.Q.m("BillingClient", str, exc);
        return new ax.D3.q(c6254d, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:100:0x01ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x017c  */
    /* JADX WARN: Code duplicated, block: B:66:0x0196  */
    /* JADX WARN: Code duplicated, block: B:70:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:77:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:82:0x0211 A[LOOP:0: B:94:0x004e->B:82:0x0211, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:96:0x0170 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:0x0209 A[SYNTHETIC] */
    public final ax.D3.q a0(String str, boolean z, int i) {
        InterfaceC1122d interfaceC1122d;
        int i2;
        Bundle bundleD6;
        C6254d c6254dA;
        EnumC1171m3 enumC1171m3;
        ArrayList<String> stringArrayList;
        ArrayList<String> stringArrayList2;
        ArrayList<String> stringArrayList3;
        int i3;
        boolean z2;
        Purchase purchase;
        C6252b c6252b = this;
        char c = 0;
        ax.I6.Q.k("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        boolean z3 = c6252b.p;
        boolean z4 = c6252b.w;
        boolean zA = c6252b.D.a();
        boolean zB = c6252b.D.b();
        long jLongValue = c6252b.I.longValue();
        Bundle bundle = new Bundle();
        ax.I6.Q.c(bundle, c6252b.c, c6252b.d, jLongValue);
        if (z3 && zA) {
            bundle.putBoolean("enablePendingPurchases", true);
        }
        if (z4 && zB) {
            bundle.putBoolean("enablePendingPurchaseForSubscriptions", true);
        }
        String string = null;
        while (true) {
            try {
                synchronized (c6252b.a) {
                    interfaceC1122d = c6252b.i;
                }
                if (interfaceC1122d == null) {
                    return c6252b.Z(9, H.j, EnumC1171m3.SERVICE_RESET_TO_NULL, "Service has been reset to null", null);
                }
                if (c6252b.p) {
                    if (c6252b.C) {
                        i2 = 26;
                    } else if (c6252b.B) {
                        i2 = 24;
                    } else {
                        i2 = c6252b.w ? 19 : 9;
                    }
                    bundleD6 = interfaceC1122d.D6(i2, c6252b.g.getPackageName(), str, string, bundle);
                } else {
                    bundleD6 = interfaceC1122d.m4(3, c6252b.g.getPackageName(), str, string);
                }
                C6254d c6254d = H.h;
                if (bundleD6 == null) {
                    Object[] objArr = new Object[1];
                    objArr[c] = "getPurchase()";
                    ax.I6.Q.l("BillingClient", String.format("%s got null owned items list", objArr));
                    enumC1171m3 = EnumC1171m3.NULL_OWNED_ITEMS_LIST;
                } else {
                    int iB = ax.I6.Q.b(bundleD6, "BillingClient");
                    String strH = ax.I6.Q.h(bundleD6, "BillingClient");
                    C6254d.a aVarD = C6254d.d();
                    aVarD.d(iB);
                    aVarD.b(strH);
                    c6254dA = aVarD.a();
                    if (iB != 0) {
                        Integer numValueOf = Integer.valueOf(iB);
                        Object[] objArr2 = new Object[2];
                        objArr2[c] = "getPurchase()";
                        objArr2[1] = numValueOf;
                        ax.I6.Q.l("BillingClient", String.format("%s failed. Response code: %s", objArr2));
                        enumC1171m3 = EnumC1171m3.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
                    } else if (bundleD6.containsKey("INAPP_PURCHASE_ITEM_LIST") && bundleD6.containsKey("INAPP_PURCHASE_DATA_LIST") && bundleD6.containsKey("INAPP_DATA_SIGNATURE_LIST")) {
                        ArrayList<String> stringArrayList4 = bundleD6.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                        ArrayList<String> stringArrayList5 = bundleD6.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                        ArrayList<String> stringArrayList6 = bundleD6.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                        if (stringArrayList4 == null) {
                            Object[] objArr3 = new Object[1];
                            objArr3[c] = "getPurchase()";
                            ax.I6.Q.l("BillingClient", String.format("Bundle returned from %s contains null SKUs list.", objArr3));
                            enumC1171m3 = EnumC1171m3.NULL_SKUS_LIST;
                        } else if (stringArrayList5 == null) {
                            Object[] objArr4 = new Object[1];
                            objArr4[c] = "getPurchase()";
                            ax.I6.Q.l("BillingClient", String.format("Bundle returned from %s contains null purchases list.", objArr4));
                            enumC1171m3 = EnumC1171m3.NULL_PURCHASES_LIST;
                        } else if (stringArrayList6 == null) {
                            Object[] objArr5 = new Object[1];
                            objArr5[c] = "getPurchase()";
                            ax.I6.Q.l("BillingClient", String.format("Bundle returned from %s contains null signatures list.", objArr5));
                            enumC1171m3 = EnumC1171m3.NULL_SIGNATURES_LIST;
                        } else {
                            c6254dA = H.i;
                            enumC1171m3 = EnumC1171m3.REASON_UNSPECIFIED;
                        }
                    } else {
                        Object[] objArr6 = new Object[1];
                        objArr6[c] = "getPurchase()";
                        ax.I6.Q.l("BillingClient", String.format("Bundle returned from %s doesn't contain required fields.", objArr6));
                        enumC1171m3 = EnumC1171m3.MISSING_REQUIRED_PURCHASE_KEY;
                    }
                    if (c6254dA != H.i) {
                        return c6252b.Z(9, c6254dA, enumC1171m3, "Purchase bundle invalid", null);
                    }
                    stringArrayList = bundleD6.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                    stringArrayList2 = bundleD6.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                    stringArrayList3 = bundleD6.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                    z2 = false;
                    for (i3 = 0; i3 < stringArrayList2.size(); i3++) {
                        String str2 = stringArrayList2.get(i3);
                        String str3 = stringArrayList3.get(i3);
                        ax.I6.Q.k("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i3))));
                        try {
                            purchase = new Purchase(str2, str3);
                            if (TextUtils.isEmpty(purchase.b())) {
                                ax.I6.Q.l("BillingClient", "BUG: empty/null token!");
                                z2 = true;
                            }
                            arrayList.add(purchase);
                        } catch (JSONException e) {
                            return Z(9, H.h, EnumC1171m3.ERROR_DECODING_PURCHASE_DATA, "Got an exception trying to decode the purchase!", e);
                        }
                    }
                    c6252b = this;
                    if (z2) {
                        c6252b.b0(EnumC1171m3.EMPTY_PURCHASE_TOKEN, 9, c6254d);
                    }
                    string = bundleD6.getString("INAPP_CONTINUATION_TOKEN");
                    ax.I6.Q.k("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                    if (TextUtils.isEmpty(string)) {
                        return new ax.D3.q(H.i, arrayList);
                    }
                    c = 0;
                }
                c6254dA = c6254d;
                if (c6254dA != H.i) {
                    return c6252b.Z(9, c6254dA, enumC1171m3, "Purchase bundle invalid", null);
                }
                stringArrayList = bundleD6.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                stringArrayList2 = bundleD6.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                stringArrayList3 = bundleD6.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                z2 = false;
                while (i3 < stringArrayList2.size()) {
                    String str4 = stringArrayList2.get(i3);
                    String str5 = stringArrayList3.get(i3);
                    ax.I6.Q.k("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i3))));
                    purchase = new Purchase(str4, str5);
                    if (TextUtils.isEmpty(purchase.b())) {
                        ax.I6.Q.l("BillingClient", "BUG: empty/null token!");
                        z2 = true;
                    }
                    arrayList.add(purchase);
                }
                c6252b = this;
                if (z2) {
                    c6252b.b0(EnumC1171m3.EMPTY_PURCHASE_TOKEN, 9, c6254d);
                }
                string = bundleD6.getString("INAPP_CONTINUATION_TOKEN");
                ax.I6.Q.k("BillingClient", "Continuation token: ".concat(String.valueOf(string)));
                if (TextUtils.isEmpty(string)) {
                    return new ax.D3.q(H.i, arrayList);
                }
                c = 0;
            } catch (DeadObjectException e2) {
                return Z(9, H.j, EnumC1171m3.GET_PURCHASE_SERVICE_CALL_EXCEPTION, "Got exception trying to get purchases try to reconnect", e2);
            } catch (Exception e3) {
                return c6252b.Z(9, H.h, EnumC1171m3.GET_PURCHASE_SERVICE_CALL_EXCEPTION, "Got exception trying to get purchases try to reconnect", e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d) {
        try {
            int i2 = F.a;
            s(F.b(enumC1171m3, i, c6254d, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private final void c0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d, long j) {
        try {
            int i2 = F.a;
            try {
                this.h.h(F.b(enumC1171m3, 2, c6254d, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED), this.m, j);
            } catch (Throwable th) {
                ax.I6.Q.m("BillingClient", "Unable to log.", th);
            }
        } catch (Throwable th2) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th2);
        }
    }

    private final void d0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d, String str) {
        try {
            int i2 = F.a;
            s(F.b(enumC1171m3, i, c6254d, str, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private final void e0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d, long j, boolean z) {
        try {
            int i2 = F.a;
            t(F.b(enumC1171m3, 2, c6254d, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED), j, z);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private final void f0(EnumC1171m3 enumC1171m3, int i, C6254d c6254d, String str, long j, boolean z) {
        try {
            int i2 = F.a;
            t(F.b(enumC1171m3, 2, c6254d, str, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED), j, z);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private void g(Context context, ax.D3.e eVar, C6255e c6255e, ax.D3.i iVar, String str, G g, AbstractC6251a.C0557a c0557a) {
        this.g = context.getApplicationContext();
        C1230y3 c1230y3O = A3.O();
        c1230y3O.D(str);
        String str2 = this.d;
        if (str2 != null) {
            c1230y3O.E(str2);
        }
        c1230y3O.B(this.g.getPackageName());
        c1230y3O.v(this.I.longValue());
        c1230y3O.C(c0557a.f);
        c1230y3O.q(Build.VERSION.SDK_INT);
        c1230y3O.z(772604006L);
        try {
            c1230y3O.t(this.g.getPackageManager().getPackageInfo(this.g.getPackageName(), 0).versionCode);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Error getting app version code.", th);
        }
        if (g != null) {
            this.h = g;
        } else {
            this.h = new I(this.g, (A3) c1230y3O.l());
        }
        if (eVar == null) {
            ax.I6.Q.l("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        this.f = new P(this.g, eVar, null, iVar, null, this.h);
        this.D = c6255e;
        this.F = iVar != null;
        this.g.getPackageName();
        this.E = c0557a.f;
    }

    private void g0(int i) {
        try {
            int i2 = F.a;
            u(F.c(i, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED));
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    static Future i(Callable callable, long j, final Runnable runnable, Handler handler, ExecutorService executorService) {
        try {
            final Future futureSubmit = executorService.submit(callable);
            handler.postDelayed(new Runnable() { // from class: ax.D3.h
                @Override // java.lang.Runnable
                public final void run() {
                    Future future = futureSubmit;
                    if (future.isDone() || future.isCancelled()) {
                        return;
                    }
                    Runnable runnable2 = runnable;
                    future.cancel(true);
                    Q.l("BillingClient", "Async task is taking too long, cancel it!");
                    if (runnable2 != null) {
                        runnable2.run();
                    }
                }
            }, (long) (j * 0.95d));
            return futureSubmit;
        } catch (Exception e) {
            ax.I6.Q.m("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    public static /* synthetic */ void j(C6252b c6252b, ax.D3.d dVar) {
        EnumC1171m3 enumC1171m3 = EnumC1171m3.EXECUTE_ASYNC_TIMEOUT;
        C6254d c6254d = H.k;
        c6252b.b0(enumC1171m3, 9, c6254d);
        dVar.a(c6254d, ax.I6.J.s());
    }

    public static /* synthetic */ void k(C6252b c6252b, ax.D3.c cVar) {
        EnumC1171m3 enumC1171m3 = EnumC1171m3.EXECUTE_ASYNC_TIMEOUT;
        C6254d c6254d = H.k;
        c6252b.b0(enumC1171m3, 7, c6254d);
        cVar.a(c6254d, new C6258h(ax.I6.J.s(), ax.I6.J.s()));
    }

    public static /* synthetic */ void l(C6252b c6252b, C6254d c6254d) {
        if (c6252b.f.d() != null) {
            c6252b.f.d().a(c6254d, null);
        } else {
            ax.I6.Q.l("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    static /* bridge */ /* synthetic */ C6254d q0(Exception exc) {
        return exc instanceof DeadObjectException ? H.j : H.h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void s(C1131e3 c1131e3) {
        try {
            this.h.f(c1131e3, this.m);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    private final void t(C1131e3 c1131e3, long j, boolean z) {
        try {
            this.h.g(c1131e3, this.m, j, z);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void u(C1156j3 c1156j3) {
        try {
            this.h.b(c1156j3, this.m);
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void v(EnumC1171m3 enumC1171m3, C6254d c6254d, int i) {
        try {
            int i2 = F.a;
            C1121c3 c1121c3 = (C1121c3) F.b(enumC1171m3, 6, c6254d, null, EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED).o();
            c4 c4VarI = e4.I();
            c4VarI.q(i > 0);
            c4VarI.t(i);
            c1121c3.v(c4VarI);
            s((C1131e3) c1121c3.l());
        } catch (Throwable th) {
            ax.I6.Q.m("BillingClient", "Unable to log.", th);
        }
    }

    public static /* synthetic */ Object v0(C6252b c6252b, int i, k4 k4Var) {
        c6252b.y(new r(c6252b, k4Var), i);
        return "reconnectIfNeeded";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void w(int i) {
        synchronized (this.a) {
            try {
                if (this.b == 3) {
                    return;
                }
                ax.I6.Q.k("BillingClient", "Setting clientState from " + D(this.b) + " to " + D(i));
                this.b = i;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static /* synthetic */ Object w0(C6252b c6252b, ax.D3.c cVar, C6257g c6257g) throws JSONException {
        if (!c6252b.B(30000L)) {
            EnumC1171m3 enumC1171m3 = EnumC1171m3.SERVICE_CONNECTION_NOT_READY;
            C6254d c6254d = H.j;
            c6252b.b0(enumC1171m3, 7, c6254d);
            cVar.a(c6254d, new C6258h(ax.I6.J.s(), ax.I6.J.s()));
            return null;
        }
        if (c6252b.u) {
            v vVarL0 = c6252b.l0(c6257g);
            cVar.a(H.a(vVarL0.a(), vVarL0.b()), new C6258h(vVarL0.c(), vVarL0.d()));
            return null;
        }
        ax.I6.Q.l("BillingClient", "Querying product details is not supported.");
        EnumC1171m3 enumC1171m4 = EnumC1171m3.PRODUCT_DETAILS_NOT_SUPPORTED;
        C6254d c6254d2 = H.r;
        c6252b.b0(enumC1171m4, 7, c6254d2);
        cVar.a(c6254d2, new C6258h(ax.I6.J.s(), ax.I6.J.s()));
        return null;
    }

    private final synchronized void x() {
        ExecutorService executorService = this.H;
        if (executorService != null) {
            executorService.shutdownNow();
            this.H = null;
        }
    }

    private final void y(ax.D3.b bVar, int i) {
        EnumC1171m3 enumC1171m3;
        C6254d c6254dT;
        C6254d c6254d;
        synchronized (this.a) {
            try {
                if (C()) {
                    c6254dT = T(i);
                } else {
                    if (this.b == 1) {
                        ax.I6.Q.l("BillingClient", "Client is already in the process of connecting to billing service.");
                        EnumC1171m3 enumC1171m4 = EnumC1171m3.BILLING_CLIENT_CONNECTING;
                        c6254d = H.d;
                        v(enumC1171m4, c6254d, i);
                    } else if (this.b == 3) {
                        ax.I6.Q.l("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
                        EnumC1171m3 enumC1171m5 = EnumC1171m3.BILLING_CLIENT_CLOSED;
                        c6254d = H.j;
                        v(enumC1171m5, c6254d, i);
                    } else {
                        w(1);
                        if (i == 0) {
                            this.G = bVar;
                            i = 0;
                        }
                        z();
                        ax.I6.Q.k("BillingClient", "Starting in-app billing setup.");
                        this.j = new u(this, bVar, i, null);
                        this.j.c();
                        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
                        intent.setPackage("com.android.vending");
                        List<ResolveInfo> listQueryIntentServices = this.g.getPackageManager().queryIntentServices(intent, 0);
                        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
                            enumC1171m3 = EnumC1171m3.INTENT_SERVICE_NOT_FOUND;
                        } else {
                            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
                            if (serviceInfo != null) {
                                String str = serviceInfo.packageName;
                                String str2 = serviceInfo.name;
                                if (!Objects.equals(str, "com.android.vending") || str2 == null) {
                                    enumC1171m3 = EnumC1171m3.INVALID_PHONESKY_PACKAGE;
                                    ax.I6.Q.l("BillingClient", "The device doesn't have valid Play Store.");
                                } else {
                                    ComponentName componentName = new ComponentName(str, str2);
                                    Intent intent2 = new Intent(intent);
                                    intent2.setComponent(componentName);
                                    intent2.putExtra("playBillingLibraryVersion", this.c);
                                    synchronized (this.a) {
                                        try {
                                            if (this.b == 2) {
                                                c6254dT = T(i);
                                            } else if (this.b != 1) {
                                                ax.I6.Q.l("BillingClient", "Client state no longer CONNECTING, returning service disconnected.");
                                                EnumC1171m3 enumC1171m6 = EnumC1171m3.BILLING_CLIENT_TRANSITIONED_OUT_OF_CONNECTING;
                                                c6254d = H.j;
                                                v(enumC1171m6, c6254d, i);
                                            } else {
                                                u uVar = this.j;
                                                if ((i <= 0 || Build.VERSION.SDK_INT < 29) ? this.g.bindService(intent2, uVar, 1) : this.g.bindService(intent2, 1, h(), uVar)) {
                                                    ax.I6.Q.k("BillingClient", "Service was bonded successfully.");
                                                    c6254dT = null;
                                                } else {
                                                    enumC1171m3 = EnumC1171m3.BILLING_SERVICE_BLOCKED;
                                                    ax.I6.Q.l("BillingClient", "Connection to Billing service is blocked.");
                                                }
                                            }
                                        } catch (Throwable th) {
                                            throw th;
                                        }
                                    }
                                }
                            } else {
                                enumC1171m3 = EnumC1171m3.INVALID_PHONESKY_PACKAGE;
                                ax.I6.Q.l("BillingClient", "The device doesn't have valid Play Store.");
                            }
                        }
                        w(0);
                        ax.I6.Q.k("BillingClient", "Billing service unavailable on device.");
                        C6254d c6254d2 = H.b;
                        v(enumC1171m3, c6254d2, i);
                        c6254dT = c6254d2;
                    }
                    c6254dT = c6254d;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        if (c6254dT != null) {
            bVar.o(c6254dT);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void z() {
        synchronized (this.a) {
            if (this.j != null) {
                try {
                    this.g.unbindService(this.j);
                    this.i = null;
                    this.j = null;
                } catch (Throwable th) {
                    try {
                        ax.I6.Q.m("BillingClient", "There was an exception while unbinding service!", th);
                        this.i = null;
                        this.j = null;
                    } catch (Throwable th2) {
                        this.i = null;
                        this.j = null;
                        throw th2;
                    }
                }
            }
        }
    }

    public final void K(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.e.post(runnable);
        }
    }

    @Override // com.android.billingclient.api.AbstractC6251a
    public void a() {
        g0(12);
        synchronized (this.a) {
            try {
                if (this.f != null) {
                    this.f.f();
                    try {
                        ax.I6.Q.k("BillingClient", "Unbinding from service.");
                        z();
                    } catch (Throwable th) {
                        ax.I6.Q.m("BillingClient", "There was an exception while unbinding from the service while ending connection!", th);
                    }
                    try {
                        x();
                        w(3);
                    } catch (Throwable th2) {
                        try {
                            ax.I6.Q.m("BillingClient", "There was an exception while shutting down the executor service while ending connection!", th2);
                            w(3);
                        } catch (Throwable th3) {
                            w(3);
                            this.G = null;
                            throw th3;
                        }
                    }
                    this.G = null;
                } else {
                    ax.I6.Q.k("BillingClient", "Unbinding from service.");
                    z();
                    x();
                    w(3);
                    this.G = null;
                }
            } catch (Throwable th4) {
                ax.I6.Q.m("BillingClient", "There was an exception while shutting down broadcast manager while ending connection!", th4);
            }
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:187:0x04a6  */
    /* JADX WARN: Code duplicated, block: B:190:0x04b1  */
    /* JADX WARN: Code duplicated, block: B:191:0x04b9  */
    /* JADX WARN: Code duplicated, block: B:193:0x04c5  */
    /* JADX WARN: Code duplicated, block: B:202:0x04f9  */
    /* JADX WARN: Code duplicated, block: B:204:0x04fd A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:206:0x0504  */
    /* JADX WARN: Code duplicated, block: B:208:0x0508  */
    /* JADX WARN: Code duplicated, block: B:209:0x050d  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r31v0, types: [com.android.billingclient.api.b] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v44 */
    /* JADX WARN: Type inference failed for: r5v45 */
    /* JADX WARN: Type inference failed for: r5v46 */
    /* JADX WARN: Type inference failed for: r5v47 */
    /* JADX WARN: Type inference failed for: r5v48 */
    /* JADX WARN: Type inference failed for: r5v49 */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v0, types: [long] */
    /* JADX WARN: Type inference failed for: r6v1, types: [long] */
    @Override // com.android.billingclient.api.AbstractC6251a
    public C6254d b(Activity activity, C6253c c6253c) {
        boolean zD;
        String strC;
        String strD;
        long j;
        Future futureI;
        ?? r5;
        ?? r6;
        ?? r7;
        EnumC1171m3 enumC1171m3G;
        Object obj;
        String str;
        boolean z;
        C6253c.b bVar;
        long j2;
        boolean z2;
        Intent intent;
        final int i;
        long jNextLong = new Random().nextLong();
        if (this.f == null || this.f.d() == null) {
            EnumC1171m3 enumC1171m3 = EnumC1171m3.MISSING_LISTENER;
            C6254d c6254d = H.E;
            c0(enumC1171m3, 2, c6254d, jNextLong);
            return c6254d;
        }
        if (!A(3000L)) {
            EnumC1171m3 enumC1171m4 = EnumC1171m3.SERVICE_CONNECTION_NOT_READY;
            C6254d c6254d2 = H.j;
            c0(enumC1171m4, 2, c6254d2, jNextLong);
            r0(c6254d2);
            return c6254d2;
        }
        synchronized (this.a) {
            try {
                zD = this.j != null ? this.j.d() : false;
            } catch (Throwable th) {
                throw th;
            }
        }
        ArrayList arrayListK = c6253c.k();
        List listL = c6253c.l();
        String string = null;
        SkuDetails skuDetails = (SkuDetails) ax.I6.O.a(arrayListK, null);
        C6253c.b bVar2 = (C6253c.b) ax.I6.O.a(listL, null);
        if (skuDetails != null) {
            strC = skuDetails.a();
            strD = skuDetails.b();
        } else {
            strC = bVar2.b().c();
            strD = bVar2.b().d();
        }
        if (strD.equals("subs") && !this.k) {
            ax.I6.Q.l("BillingClient", "Current client doesn't support subscriptions.");
            EnumC1171m3 enumC1171m5 = EnumC1171m3.SUBSCRIPTIONS_NOT_SUPPORTED;
            C6254d c6254d3 = H.l;
            e0(enumC1171m5, 2, c6254d3, jNextLong, zD);
            r0(c6254d3);
            return c6254d3;
        }
        if (c6253c.u() && !this.n) {
            ax.I6.Q.l("BillingClient", "Current client doesn't support extra params for buy intent.");
            EnumC1171m3 enumC1171m6 = EnumC1171m3.EXTRA_PARAMS_NOT_SUPPORTED;
            C6254d c6254d4 = H.f;
            e0(enumC1171m6, 2, c6254d4, jNextLong, zD);
            r0(c6254d4);
            return c6254d4;
        }
        if (arrayListK.size() > 1 && !this.t) {
            ax.I6.Q.l("BillingClient", "Current client doesn't support multi-item purchases.");
            EnumC1171m3 enumC1171m7 = EnumC1171m3.MULTI_ITEM_NOT_SUPPORTED;
            C6254d c6254d5 = H.p;
            e0(enumC1171m7, 2, c6254d5, jNextLong, zD);
            r0(c6254d5);
            return c6254d5;
        }
        if (!listL.isEmpty() && !this.u) {
            ax.I6.Q.l("BillingClient", "Current client doesn't support purchases with ProductDetails.");
            EnumC1171m3 enumC1171m8 = EnumC1171m3.PRODUCT_DETAILS_NOT_SUPPORTED;
            C6254d c6254d6 = H.r;
            e0(enumC1171m8, 2, c6254d6, jNextLong, zD);
            r0(c6254d6);
            return c6254d6;
        }
        C6254d c6254dE = c6253c.e();
        if (c6254dE != H.i) {
            e0(EnumC1171m3.INVALID_BILLING_FLOW_PARAMS, 2, c6254dE, jNextLong, zD);
            r0(c6254dE);
            return c6254dE;
        }
        if (this.n) {
            boolean z3 = this.p;
            boolean z4 = this.w;
            boolean zA = this.D.a();
            boolean zB = this.D.b();
            boolean z5 = this.F;
            String str2 = this.c;
            String str3 = this.d;
            boolean z6 = zD;
            long jLongValue = this.I.longValue();
            this.g.getPackageName();
            int i2 = ax.I6.Q.a;
            final Bundle bundle = new Bundle();
            ax.I6.Q.c(bundle, str2, str3, jLongValue);
            bundle.putLong("billingClientTransactionId", jNextLong);
            if (c6253c.c() != 0) {
                bundle.putInt("prorationMode", c6253c.c());
            }
            if (!TextUtils.isEmpty(c6253c.f())) {
                bundle.putString("accountId", c6253c.f());
            }
            if (!TextUtils.isEmpty(c6253c.g())) {
                bundle.putString("obfuscatedProfileId", c6253c.g());
            }
            if (c6253c.t()) {
                bundle.putBoolean("isOfferPersonalizedByDeveloper", true);
            }
            if (!TextUtils.isEmpty(null)) {
                bundle.putStringArrayList("skusToReplace", new ArrayList<>(Arrays.asList(null)));
            }
            if (!TextUtils.isEmpty(c6253c.i())) {
                bundle.putString("oldSkuPurchaseToken", c6253c.i());
            }
            c6253c.h();
            if (TextUtils.isEmpty(null)) {
                str = null;
            } else {
                c6253c.h();
                str = null;
                bundle.putString("oldSkuPurchaseId", null);
            }
            if (!TextUtils.isEmpty(c6253c.j())) {
                bundle.putString("originalExternalTransactionId", c6253c.j());
            }
            if (!TextUtils.isEmpty(str)) {
                bundle.putString("paymentsPurchaseParams", str);
            }
            if (z3 && zA) {
                z = true;
                bundle.putBoolean("enablePendingPurchases", true);
            } else {
                z = true;
            }
            if (z4 && zB) {
                bundle.putBoolean("enablePendingPurchaseForSubscriptions", z);
            }
            if (z5) {
                bundle.putBoolean("enableAlternativeBilling", z);
            }
            c6253c.d();
            c6253c.b();
            ArrayList arrayList = new ArrayList();
            for (C6253c.b bVar3 : c6253c.l()) {
            }
            if (!arrayList.isEmpty()) {
                M0 m0E = N0.E();
                m0E.q(arrayList);
                bundle.putByteArray("subscriptionProductReplacementParamsList", ((N0) m0E.l()).a());
            }
            if (arrayListK.isEmpty()) {
                long j3 = jNextLong;
                bVar = bVar2;
                ArrayList<String> arrayList2 = new ArrayList<>(listL.size() - 1);
                ArrayList<String> arrayList3 = new ArrayList<>(listL.size() - 1);
                ArrayList<String> arrayList4 = new ArrayList<>();
                ArrayList<String> arrayList5 = new ArrayList<>();
                ArrayList<String> arrayList6 = new ArrayList<>();
                ArrayList<Integer> arrayList7 = new ArrayList<>();
                for (int i3 = 0; i3 < listL.size(); i3++) {
                    C6253c.b bVar4 = (C6253c.b) listL.get(i3);
                    C6256f c6256fB = bVar4.b();
                    if (!c6256fB.g().isEmpty()) {
                        arrayList4.add(c6256fB.g());
                    }
                    arrayList5.add(bVar4.c());
                    String strH = c6256fB.h();
                    if (c6256fB.i() != null && !c6256fB.i().isEmpty()) {
                        for (C6256f.b bVar5 : c6256fB.i()) {
                            if (!TextUtils.isEmpty(bVar5.d())) {
                                strH = bVar5.d();
                                break;
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(strH)) {
                        arrayList6.add(strH);
                    }
                    if (i3 > 0) {
                        arrayList2.add(((C6253c.b) listL.get(i3)).b().c());
                        arrayList3.add(((C6253c.b) listL.get(i3)).b().d());
                    }
                }
                bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList5);
                if (!arrayList7.isEmpty()) {
                    bundle.putIntegerArrayList("autoPayBalanceThresholdList", arrayList7);
                }
                if (!arrayList4.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList4);
                }
                if (!arrayList6.isEmpty()) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList6);
                }
                j2 = j3;
                if (!arrayList2.isEmpty()) {
                    bundle.putStringArrayList("additionalSkus", arrayList2);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList3);
                    j2 = j3;
                }
            } else {
                ArrayList<String> arrayList8 = new ArrayList<>();
                ArrayList<String> arrayList9 = new ArrayList<>();
                ArrayList<String> arrayList10 = new ArrayList<>();
                ArrayList<Integer> arrayList11 = new ArrayList<>();
                ArrayList<String> arrayList12 = new ArrayList<>();
                int size = arrayListK.size();
                j2 = jNextLong;
                int i4 = 0;
                boolean z7 = false;
                boolean z8 = false;
                boolean z9 = false;
                boolean z10 = false;
                while (i4 < size) {
                    Object obj2 = arrayListK.get(i4);
                    int i5 = i4 + 1;
                    SkuDetails skuDetails2 = (SkuDetails) obj2;
                    if (!skuDetails2.h().isEmpty()) {
                        arrayList8.add(skuDetails2.h());
                    }
                    String strE = skuDetails2.e();
                    String strD2 = skuDetails2.d();
                    int iC = skuDetails2.c();
                    C6253c.b bVar6 = bVar2;
                    String strG = skuDetails2.g();
                    arrayList9.add(strE);
                    z7 |= !TextUtils.isEmpty(strE);
                    arrayList10.add(strD2);
                    z8 |= !TextUtils.isEmpty(strD2);
                    arrayList11.add(Integer.valueOf(iC));
                    z9 |= iC != 0;
                    z10 |= !TextUtils.isEmpty(strG);
                    arrayList12.add(strG);
                    i4 = i5;
                    bVar2 = bVar6;
                }
                bVar = bVar2;
                if (!arrayList8.isEmpty()) {
                    bundle.putStringArrayList("skuDetailsTokens", arrayList8);
                }
                if (z7) {
                    bundle.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList9);
                }
                if (z8) {
                    bundle.putStringArrayList("SKU_OFFER_ID_LIST", arrayList10);
                }
                if (z9) {
                    bundle.putIntegerArrayList("SKU_OFFER_TYPE_LIST", arrayList11);
                }
                if (z10) {
                    bundle.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList12);
                }
                if (arrayListK.size() > 1) {
                    ArrayList<String> arrayList13 = new ArrayList<>(arrayListK.size() - 1);
                    ArrayList<String> arrayList14 = new ArrayList<>(arrayListK.size() - 1);
                    for (int i6 = 1; i6 < arrayListK.size(); i6++) {
                        arrayList13.add(((SkuDetails) arrayListK.get(i6)).a());
                        arrayList14.add(((SkuDetails) arrayListK.get(i6)).b());
                    }
                    bundle.putStringArrayList("additionalSkus", arrayList13);
                    bundle.putStringArrayList("additionalSkuTypes", arrayList14);
                }
            }
            if (bundle.containsKey("SKU_OFFER_ID_TOKEN_LIST") && !this.r) {
                EnumC1171m3 enumC1171m9 = EnumC1171m3.OFFER_ID_TOKEN_NOT_SUPPORTED;
                C6254d c6254d7 = H.q;
                e0(enumC1171m9, 2, c6254d7, j2, z6);
                r0(c6254d7);
                return c6254d7;
            }
            zD = z6;
            if (skuDetails == null || TextUtils.isEmpty(skuDetails.f())) {
                if (bVar == null || TextUtils.isEmpty(bVar.b().f())) {
                    z2 = false;
                } else {
                    bundle.putString("skuPackageName", bVar.b().f());
                }
                string = null;
                if (!TextUtils.isEmpty(null)) {
                    bundle.putString("accountName", null);
                }
                intent = activity.getIntent();
                if (intent == null) {
                    ax.I6.Q.l("BillingClient", "Activity's intent is null.");
                } else if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                    String stringExtra = intent.getStringExtra("PROXY_PACKAGE");
                    bundle.putString("proxyPackage", stringExtra);
                    try {
                        bundle.putString("proxyPackageVersion", this.g.getPackageManager().getPackageInfo(stringExtra, 0).versionName);
                    } catch (PackageManager.NameNotFoundException unused) {
                        bundle.putString("proxyPackageVersion", "package not found");
                    }
                }
                if (!this.u && !listL.isEmpty()) {
                    i = 17;
                } else if (!this.s && z2) {
                    i = 15;
                } else if (this.p) {
                    i = 9;
                } else {
                    i = 6;
                }
                final C6253c c6253c2 = c6253c;
                final String str4 = strC;
                final String str5 = strD;
                futureI = i(new Callable() { // from class: com.android.billingclient.api.S
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return this.a.P(i, str4, str5, c6253c2, bundle);
                    }
                }, 5000L, null, this.e, h());
                r5 = c6253c2;
                j = j2;
            } else {
                bundle.putString("skuPackageName", skuDetails.f());
            }
            z2 = true;
            string = null;
            if (!TextUtils.isEmpty(null)) {
                bundle.putString("accountName", null);
            }
            intent = activity.getIntent();
            if (intent == null) {
                ax.I6.Q.l("BillingClient", "Activity's intent is null.");
            } else if (!TextUtils.isEmpty(intent.getStringExtra("PROXY_PACKAGE"))) {
                String stringExtra2 = intent.getStringExtra("PROXY_PACKAGE");
                bundle.putString("proxyPackage", stringExtra2);
                bundle.putString("proxyPackageVersion", this.g.getPackageManager().getPackageInfo(stringExtra2, 0).versionName);
            }
            if (!this.u) {
                if (!this.s) {
                    if (this.p) {
                        i = 9;
                    } else {
                        i = 6;
                    }
                } else if (this.p) {
                    i = 9;
                } else {
                    i = 6;
                }
            } else if (!this.s) {
                if (this.p) {
                    i = 9;
                } else {
                    i = 6;
                }
            } else if (this.p) {
                i = 9;
            } else {
                i = 6;
            }
            final C6253c c6253c3 = c6253c;
            final String str6 = strC;
            final String str7 = strD;
            futureI = i(new Callable() { // from class: com.android.billingclient.api.S
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.a.P(i, str6, str7, c6253c3, bundle);
                }
            }, 5000L, null, this.e, h());
            r5 = c6253c3;
            j = j2;
        } else {
            j = jNextLong;
            final String str8 = strC;
            final String str9 = strD;
            futureI = i(new Callable() { // from class: com.android.billingclient.api.T
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.a.Q(str8, str9);
                }
            }, 5000L, null, this.e, h());
            r5 = jNextLong;
        }
        try {
            if (futureI != null) {
                long j4 = j;
                Bundle bundle2 = (Bundle) futureI.get(5000L, TimeUnit.MILLISECONDS);
                int iB = ax.I6.Q.b(bundle2, "BillingClient");
                String strH2 = ax.I6.Q.h(bundle2, "BillingClient");
                if (iB == 0) {
                    Intent intent2 = new Intent(activity, (Class<?>) ProxyBillingActivity.class);
                    intent2.putExtra("BUY_INTENT", (PendingIntent) bundle2.getParcelable("BUY_INTENT"));
                    intent2.putExtra("billingClientTransactionId", j4);
                    intent2.putExtra("wasServiceAutoReconnected", zD);
                    activity.startActivity(intent2);
                    return H.i;
                }
                ax.I6.Q.l("BillingClient", "Unable to buy item, Error response code: " + iB);
                C6254d c6254dA = H.a(iB, strH2);
                try {
                    if (bundle2 == null || (obj = bundle2.get("LOG_REASON")) == null) {
                        enumC1171m3G = EnumC1171m3.REASON_UNSPECIFIED;
                    } else if (obj instanceof Integer) {
                        enumC1171m3G = EnumC1171m3.g(((Integer) obj).intValue());
                    } else {
                        ax.I6.Q.l("BillingClient", "Unexpected type for bundle log reason: " + obj.getClass().getName());
                        enumC1171m3G = EnumC1171m3.REASON_UNSPECIFIED;
                    }
                } catch (Throwable th2) {
                    ax.I6.Q.l("BillingClient", "Failed to get log reason from bundle: ".concat(String.valueOf(th2.getMessage())));
                    enumC1171m3G = EnumC1171m3.REASON_UNSPECIFIED;
                }
                if (enumC1171m3G == EnumC1171m3.REASON_UNSPECIFIED) {
                    enumC1171m3G = EnumC1171m3.BILLING_RESULT_RECEIVED_FROM_PHONESKY;
                }
                EnumC1171m3 enumC1171m10 = enumC1171m3G;
                if (bundle2 != null) {
                    try {
                        string = bundle2.getString("ADDITIONAL_LOG_DETAILS");
                    } catch (Throwable th3) {
                        ax.I6.Q.l("BillingClient", "Failed to get additional log details from bundle: ".concat(String.valueOf(th3.getMessage())));
                    }
                }
                try {
                    f0(enumC1171m10, 2, c6254dA, string, j4, zD);
                    r0(c6254dA);
                    return c6254dA;
                } catch (CancellationException e) {
                    e = e;
                    r7 = j4;
                    ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m11 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                    C6254d c6254d8 = H.k;
                    f0(enumC1171m11, 2, c6254d8, F.a(e), r7, zD);
                    r0(c6254d8);
                    return c6254d8;
                } catch (TimeoutException e2) {
                    e = e2;
                    r7 = j4;
                    ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m12 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                    C6254d c6254d9 = H.k;
                    f0(enumC1171m12, 2, c6254d9, F.a(e), r7, zD);
                    r0(c6254d9);
                    return c6254d9;
                } catch (Exception e3) {
                    e = e3;
                    r6 = j4;
                    ax.I6.Q.m("BillingClient", "Exception while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m13 = EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION;
                    C6254d c6254d10 = H.j;
                    f0(enumC1171m13, 2, c6254d10, F.a(e), r6, zD);
                    r0(c6254d10);
                    return c6254d10;
                }
            }
            try {
                EnumC1171m3 enumC1171m14 = EnumC1171m3.MISSING_RESULT_FROM_EXECUTE_ASYNC;
                C6254d c6254d11 = H.c;
                boolean z11 = zD;
                long j5 = j;
                try {
                    e0(enumC1171m14, 2, c6254d11, j5, z11);
                    r0(c6254d11);
                    return c6254d11;
                } catch (CancellationException e4) {
                    e = e4;
                    zD = z11;
                    r7 = j5;
                    ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m15 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                    C6254d c6254d12 = H.k;
                    f0(enumC1171m15, 2, c6254d12, F.a(e), r7, zD);
                    r0(c6254d12);
                    return c6254d12;
                } catch (TimeoutException e5) {
                    e = e5;
                    zD = z11;
                    r7 = j5;
                    ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m16 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                    C6254d c6254d13 = H.k;
                    f0(enumC1171m16, 2, c6254d13, F.a(e), r7, zD);
                    r0(c6254d13);
                    return c6254d13;
                } catch (Exception e6) {
                    e = e6;
                    zD = z11;
                    r6 = j5;
                    ax.I6.Q.m("BillingClient", "Exception while launching billing flow. Try to reconnect", e);
                    EnumC1171m3 enumC1171m17 = EnumC1171m3.LAUNCH_BILLING_FLOW_EXCEPTION;
                    C6254d c6254d14 = H.j;
                    f0(enumC1171m17, 2, c6254d14, F.a(e), r6, zD);
                    r0(c6254d14);
                    return c6254d14;
                }
            } catch (CancellationException e7) {
                e = e7;
                r7 = j;
                ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                EnumC1171m3 enumC1171m18 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                C6254d c6254d15 = H.k;
                f0(enumC1171m18, 2, c6254d15, F.a(e), r7, zD);
                r0(c6254d15);
                return c6254d15;
            } catch (TimeoutException e8) {
                e = e8;
                r7 = j;
                ax.I6.Q.m("BillingClient", "Time out while launching billing flow. Try to reconnect", e);
                EnumC1171m3 enumC1171m19 = EnumC1171m3.LAUNCH_BILLING_FLOW_TIMEOUT;
                C6254d c6254d16 = H.k;
                f0(enumC1171m19, 2, c6254d16, F.a(e), r7, zD);
                r0(c6254d16);
                return c6254d16;
            } catch (Exception e9) {
                e = e9;
                r6 = j;
            }
        } catch (CancellationException e10) {
            e = e10;
            r7 = r5;
        } catch (TimeoutException e11) {
            e = e11;
            r7 = r5;
        } catch (Exception e12) {
            e = e12;
            r6 = r5;
        }
    }

    @Override // com.android.billingclient.api.AbstractC6251a
    public void d(final C6257g c6257g, final ax.D3.c cVar) {
        if (i(new Callable() { // from class: com.android.billingclient.api.k
            @Override // java.util.concurrent.Callable
            public final Object call() throws JSONException {
                C6252b.w0(this.a, cVar, c6257g);
                return null;
            }
        }, 30000L, new Runnable() { // from class: com.android.billingclient.api.l
            @Override // java.lang.Runnable
            public final void run() {
                C6252b.k(this.q, cVar);
            }
        }, R(), h()) == null) {
            C6254d c6254dU = U();
            b0(EnumC1171m3.MISSING_RESULT_FROM_EXECUTE_ASYNC, 7, c6254dU);
            cVar.a(c6254dU, new C6258h(ax.I6.J.s(), ax.I6.J.s()));
        }
    }

    @Override // com.android.billingclient.api.AbstractC6251a
    public final void e(ax.D3.f fVar, final ax.D3.d dVar) {
        if (i(new CallableC6265o(this, dVar, fVar.b(), false), 30000L, new Runnable() { // from class: com.android.billingclient.api.m
            @Override // java.lang.Runnable
            public final void run() {
                C6252b.j(this.q, dVar);
            }
        }, R(), h()) == null) {
            C6254d c6254dU = U();
            b0(EnumC1171m3.MISSING_RESULT_FROM_EXECUTE_ASYNC, 9, c6254dU);
            dVar.a(c6254dU, ax.I6.J.s());
        }
    }

    @Override // com.android.billingclient.api.AbstractC6251a
    public void f(ax.D3.b bVar) {
        y(bVar, 0);
    }

    final synchronized ExecutorService h() {
        try {
            if (this.H == null) {
                this.H = Executors.newFixedThreadPool(ax.I6.Q.a, new ThreadFactoryC6264n(this));
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.H;
    }

    final v l0(C6257g c6257g) throws JSONException {
        InterfaceC1122d interfaceC1122d;
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String strC = c6257g.c();
        ax.I6.J jB = c6257g.b();
        int size = jB.size();
        int i3 = 0;
        while (i3 < size) {
            int i4 = i3 + 20;
            ArrayList arrayList3 = new ArrayList(jB.subList(i3, i4 > size ? size : i4));
            ArrayList<String> arrayList4 = new ArrayList<>();
            int size2 = arrayList3.size();
            for (int i5 = 0; i5 < size2; i5++) {
                arrayList4.add(((C6257g.b) arrayList3.get(i5)).b());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList4);
            String str = this.c;
            bundle.putString("playBillingLibraryVersion", str);
            try {
                synchronized (this.a) {
                    interfaceC1122d = this.i;
                }
                if (interfaceC1122d == null) {
                    return S(H.j, EnumC1171m3.SERVICE_RESET_TO_NULL, "Service has been reset to null.", null);
                }
                boolean z = this.w && this.D.b();
                W(c6257g);
                W(c6257g);
                W(c6257g);
                W(c6257g);
                Bundle bundleW6 = interfaceC1122d.W6(true != this.x ? 17 : 20, this.g.getPackageName(), strC, bundle, ax.I6.Q.f(str, this.d, arrayList3, null, null, C1107a.a(z, true, false, true, false, true), this.I.longValue()));
                if (bundleW6 == null) {
                    return S(H.B, EnumC1171m3.NULL_BUNDLE_FROM_GET_SKU_DETAILS_SERVICE_CALL, "queryProductDetailsAsync got empty product details response.", null);
                }
                if (!bundleW6.containsKey("DETAILS_LIST")) {
                    int iB = ax.I6.Q.b(bundleW6, "BillingClient");
                    String strH = ax.I6.Q.h(bundleW6, "BillingClient");
                    if (iB == 0) {
                        return S(H.a(6, strH), EnumC1171m3.MISSING_DETAILS_LIST_IN_GET_SKU_DETAILS_RESPONSE, "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.", null);
                    }
                    return S(H.a(iB, strH), EnumC1171m3.BILLING_RESULT_RECEIVED_FROM_PHONESKY, "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + iB, null);
                }
                ArrayList<String> stringArrayList = bundleW6.getStringArrayList("DETAILS_LIST");
                if (stringArrayList == null) {
                    return S(H.B, EnumC1171m3.NULL_DETAILS_LIST_IN_GET_SKU_DETAILS_RESPONSE, "queryProductDetailsAsync got null response list", null);
                }
                ArrayList arrayList5 = new ArrayList();
                int size3 = stringArrayList.size();
                for (int i6 = 0; i6 < size3; i6++) {
                    try {
                        C6256f c6256f = new C6256f(stringArrayList.get(i6));
                        ax.I6.Q.k("BillingClient", "Got product details: ".concat(c6256f.toString()));
                        arrayList5.add(c6256f);
                    } catch (JSONException e) {
                        return S(H.a(6, "Error trying to decode SkuDetails."), EnumC1171m3.ERROR_DECODING_SKU_DETAILS, "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e);
                    }
                }
                ArrayList<String> stringArrayList2 = bundleW6.getStringArrayList("UNFETCHED_PRODUCT_LIST");
                new ArrayList();
                try {
                    ArrayList arrayList6 = new ArrayList();
                    if (stringArrayList2 != null) {
                        int size4 = stringArrayList2.size();
                        int i7 = 0;
                        while (i7 < size4) {
                            String str2 = stringArrayList2.get(i7);
                            i7++;
                            C6259i c6259i = new C6259i(str2);
                            ax.I6.Q.k("BillingClient", "Got unfetchedProduct: ".concat(c6259i.toString()));
                            arrayList6.add(c6259i);
                        }
                    } else {
                        int size5 = arrayList3.size();
                        int i8 = 0;
                        while (i8 < size5) {
                            Object obj = arrayList3.get(i8);
                            int i9 = i8 + 1;
                            C6257g.b bVar = (C6257g.b) obj;
                            int size6 = arrayList5.size();
                            int i10 = 0;
                            while (true) {
                                if (i10 >= size6) {
                                    i = size5;
                                    i2 = i9;
                                    arrayList6.add(new C6259i(new JSONObject().put("productId", bVar.b()).put("type", bVar.c()).put("statusCode", 0).toString()));
                                    break;
                                }
                                Object obj2 = arrayList5.get(i10);
                                i10++;
                                C6256f c6256f2 = (C6256f) obj2;
                                i = size5;
                                i2 = i9;
                                if (bVar.b().equals(c6256f2.c()) && bVar.c().equals(c6256f2.d())) {
                                    break;
                                }
                                size5 = i;
                                i9 = i2;
                            }
                            size5 = i;
                            i8 = i2;
                        }
                    }
                    arrayList.addAll(arrayList5);
                    arrayList2.addAll(arrayList6);
                    i3 = i4;
                } catch (JSONException e2) {
                    return S(H.a(6, "Error trying to decode SkuDetails."), EnumC1171m3.ERROR_DECODING_SKU_DETAILS, "Got a JSON exception trying to decode UnfetchedProduct. \n Exception: ", e2);
                }
            } catch (DeadObjectException e3) {
                return S(H.j, EnumC1171m3.GET_SKU_DETAILS_SERVICE_CALL_EXCEPTION, "queryProductDetailsAsync got a remote exception (try to reconnect).", e3);
            } catch (Exception e4) {
                return S(H.h, EnumC1171m3.GET_SKU_DETAILS_SERVICE_CALL_EXCEPTION, "queryProductDetailsAsync got a remote exception (try to reconnect).", e4);
            }
        }
        return new v(0, HttpUrl.FRAGMENT_ENCODE_SET, arrayList, arrayList2);
    }

    final G o0() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final C6254d r0(final C6254d c6254d) {
        if (Thread.interrupted()) {
            return c6254d;
        }
        this.e.post(new Runnable() { // from class: com.android.billingclient.api.j
            @Override // java.lang.Runnable
            public final void run() {
                C6252b.l(this.q, c6254d);
            }
        });
        return c6254d;
    }
}
