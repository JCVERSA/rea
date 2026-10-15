package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import ax.I6.C1131e3;
import ax.I6.C1228y1;
import ax.I6.EnumC1171m3;
import ax.I6.EnumC1205t3;
import j$.util.Objects;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class O extends BroadcastReceiver {
    private boolean a;
    private final boolean b;
    final /* synthetic */ P c;

    O(P p, boolean z) {
        Objects.requireNonNull(p);
        this.c = p;
        this.b = z;
    }

    private final void d(Bundle bundle, C6254d c6254d, int i, EnumC1205t3 enumC1205t3, long j, boolean z) {
        try {
            if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") != null) {
                this.c.c.d(C1131e3.E(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), C1228y1.a()), j, z);
            } else {
                this.c.c.d(F.b(EnumC1171m3.BILLING_RESULT_RECEIVED_FROM_PHONESKY, i, c6254d, null, enumC1205t3), j, z);
            }
        } catch (Throwable unused) {
            ax.I6.Q.l("BillingBroadcastManager", "Failed parsing Api failure.");
        }
    }

    public final synchronized void a(Context context, IntentFilter intentFilter) {
        try {
            if (this.a) {
                return;
            }
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver(this, intentFilter, true != this.b ? 4 : 2);
            } else {
                context.registerReceiver(this, intentFilter);
            }
            this.a = true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized void b(Context context, IntentFilter intentFilter, String str) {
        O o;
        try {
            try {
                if (this.a) {
                    return;
                }
                if (Build.VERSION.SDK_INT >= 33) {
                    o = this;
                    context.registerReceiver(o, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null, true != this.b ? 4 : 2);
                } else {
                    o = this;
                    context.registerReceiver(this, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", null);
                }
                o.a = true;
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    public final synchronized void c(Context context) {
        if (!this.a) {
            ax.I6.Q.l("BillingBroadcastManager", "Receiver is not registered.");
        } else {
            context.unregisterReceiver(this);
            this.a = false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0039  */
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        byte b;
        EnumC1205t3 enumC1205t3;
        int i;
        C6254d c6254dG;
        int iIntValue;
        String action = intent.getAction();
        int iHashCode = action.hashCode();
        if (iHashCode != -1484087650) {
            if (iHashCode != -337612916) {
                if (iHashCode == 345207161 && action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (action.equals("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (action.equals("com.android.vending.billing.PURCHASES_UPDATED")) {
            b = 0;
        } else {
            b = -1;
        }
        if (b == 0) {
            enumC1205t3 = EnumC1205t3.PURCHASES_UPDATED_ACTION;
        } else if (b != 1) {
            enumC1205t3 = b != 2 ? EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED : EnumC1205t3.ALTERNATIVE_BILLING_ACTION;
        } else {
            enumC1205t3 = EnumC1205t3.LOCAL_PURCHASES_UPDATED_ACTION;
        }
        EnumC1205t3 enumC1205t4 = enumC1205t3;
        EnumC1205t3 enumC1205t5 = EnumC1205t3.LOCAL_PURCHASES_UPDATED_ACTION;
        if (enumC1205t4.equals(enumC1205t5) || enumC1205t4.equals(EnumC1205t3.ALTERNATIVE_BILLING_ACTION)) {
            i = 2;
        } else {
            i = enumC1205t4.equals(EnumC1205t3.PURCHASES_UPDATED_ACTION) ? 32 : 1;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            ax.I6.Q.l("BillingBroadcastManager", "Bundle is null.");
            P p = this.c;
            G g = p.c;
            EnumC1171m3 enumC1171m3 = EnumC1171m3.NULL_BUNDLE_IN_BROADCAST_RECEIVER;
            C6254d c6254d = H.h;
            g.i(F.b(enumC1171m3, i, c6254d, null, enumC1205t4));
            if (p.b != null) {
                p.b.a(c6254d, null);
            }
        } else {
            if (i == 2) {
                int i2 = ax.I6.Q.a;
                C6254d.a aVarD = C6254d.d();
                aVarD.d(ax.I6.Q.b(intent.getExtras(), "BillingBroadcastManager"));
                Bundle extras2 = intent.getExtras();
                if (extras2 == null) {
                    ax.I6.Q.l("BillingBroadcastManager", "Unexpected null bundle received!");
                } else {
                    Object obj = extras2.get("SUB_RESPONSE_CODE");
                    if (obj == null) {
                        ax.I6.Q.k("BillingBroadcastManager", "getLaunchBillingFlowSubResponseCodeFromBundle() got null response code, assuming OK");
                    } else {
                        if (obj instanceof Integer) {
                            iIntValue = ((Integer) obj).intValue();
                        } else {
                            ax.I6.Q.l("BillingBroadcastManager", "Unexpected type for bundle sub response code: ".concat(obj.getClass().getName()));
                        }
                        aVarD.c(iIntValue);
                        aVarD.b(ax.I6.Q.h(intent.getExtras(), "BillingBroadcastManager"));
                        c6254dG = aVarD.a();
                    }
                }
                iIntValue = 0;
                aVarD.c(iIntValue);
                aVarD.b(ax.I6.Q.h(intent.getExtras(), "BillingBroadcastManager"));
                c6254dG = aVarD.a();
            } else {
                c6254dG = ax.I6.Q.g(intent, "BillingBroadcastManager");
            }
            C6254d c6254d2 = c6254dG;
            long j = extras.getLong("billingClientTransactionId", 0L);
            boolean z = extras.getBoolean("wasServiceAutoReconnected", false);
            if (enumC1205t4.equals(EnumC1205t3.PURCHASES_UPDATED_ACTION) || enumC1205t4.equals(enumC1205t5)) {
                List<Purchase> listJ = ax.I6.Q.j(extras);
                if (c6254d2.c() == 0) {
                    this.c.c.a(F.c(i, enumC1205t4), j, z);
                } else {
                    d(extras, c6254d2, i, enumC1205t4, j, z);
                }
                this.c.b.a(c6254d2, listJ);
                return;
            }
            if (enumC1205t4.equals(EnumC1205t3.ALTERNATIVE_BILLING_ACTION)) {
                if (c6254d2.c() != 0) {
                    d(extras, c6254d2, i, enumC1205t4, j, z);
                    this.c.b.a(c6254d2, ax.I6.J.s());
                    return;
                }
                P p2 = this.c;
                P.a(p2);
                P.e(p2);
                ax.I6.Q.l("BillingBroadcastManager", "AlternativeBillingListener and UserChoiceBillingListener is null.");
                P p3 = this.c;
                G g2 = p3.c;
                EnumC1171m3 enumC1171m4 = EnumC1171m3.MISSING_USER_CHOICE_BILLING_LISTENER;
                C6254d c6254d3 = H.h;
                g2.d(F.b(enumC1171m4, i, c6254d3, null, enumC1205t4), j, z);
                p3.b.a(c6254d3, ax.I6.J.s());
            }
        }
    }
}
