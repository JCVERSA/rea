package com.android.billingclient.api;

import ax.I6.C1121c3;
import ax.I6.C1131e3;
import ax.I6.C1141g3;
import ax.I6.C1156j3;
import ax.I6.C1161k3;
import ax.I6.C1181o3;
import ax.I6.C1231z;
import ax.I6.EnumC1171m3;
import ax.I6.EnumC1205t3;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class F {
    public static final /* synthetic */ int a = 0;

    static {
        int i = G.a;
    }

    public static String a(Exception exc) {
        if (exc == null) {
            return null;
        }
        try {
            String str = exc.getClass().getSimpleName() + ":" + C1231z.b(exc.getMessage());
            int i = ax.I6.Q.a;
            return str.length() > 40 ? str.substring(0, 40) : str;
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to get truncated exception info", th);
            return null;
        }
    }

    public static C1131e3 b(EnumC1171m3 enumC1171m3, int i, C6254d c6254d, String str, EnumC1205t3 enumC1205t3) {
        try {
            C1161k3 c1161k3J = C1181o3.J();
            c1161k3J.w(c6254d.c());
            c1161k3J.t(c6254d.a());
            if (c6254d.b() != 0) {
                c1161k3J.u(c6254d.b());
            }
            if (enumC1171m3 != null) {
                c1161k3J.v(enumC1171m3);
            }
            if (str != null) {
                c1161k3J.q(str);
            }
            C1121c3 c1121c3L = C1131e3.L();
            c1121c3L.t(c1161k3J);
            c1121c3L.w(i);
            if (!enumC1205t3.equals(EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED)) {
                c1121c3L.q(enumC1205t3);
            }
            return (C1131e3) c1121c3L.l();
        } catch (Throwable th) {
            ax.I6.Q.m("BillingLogger", "Unable to create logging payload", th);
            return null;
        }
    }

    public static C1156j3 c(int i, EnumC1205t3 enumC1205t3) {
        try {
            C1141g3 c1141g3J = C1156j3.J();
            c1141g3J.v(i);
            if (!enumC1205t3.equals(EnumC1205t3.BROADCAST_ACTION_UNSPECIFIED)) {
                c1141g3J.q(enumC1205t3);
            }
            return (C1156j3) c1141g3J.l();
        } catch (Exception e) {
            ax.I6.Q.m("BillingLogger", "Unable to create logging payload", e);
            return null;
        }
    }
}
