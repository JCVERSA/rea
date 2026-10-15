package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import android.util.Pair;
import ax.H6.AbstractC0994m4;
import ax.H6.C0893b2;
import ax.H6.C0911d2;
import ax.p265q6.C2254p;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
final class L5 {
    private C0893b2 a;
    private Long b;
    private long c;
    private final /* synthetic */ J5 d;

    private L5(J5 j5) {
        this.d = j5;
    }

    final C0893b2 a(String str, C0893b2 c0893b2) {
        Object obj;
        String strF0 = c0893b2.f0();
        List<C0911d2> listG0 = c0893b2.g0();
        this.d.o();
        Long l = (Long) x5.g0(c0893b2, "_eid");
        boolean z = l != null;
        if (z && strF0.equals("_ep")) {
            C2254p.l(l);
            this.d.o();
            strF0 = (String) x5.g0(c0893b2, "_en");
            if (TextUtils.isEmpty(strF0)) {
                this.d.j().I().b("Extra parameter without an event name. eventId", l);
                return null;
            }
            if (this.a == null || this.b == null || l.longValue() != this.b.longValue()) {
                Pair<C0893b2, Long> pairH = this.d.q().H(str, l);
                if (pairH == null || (obj = pairH.first) == null) {
                    this.d.j().I().c("Extra parameter without existing main event. eventName, eventId", strF0, l);
                    return null;
                }
                this.a = (C0893b2) obj;
                this.c = ((Long) pairH.second).longValue();
                this.d.o();
                this.b = (Long) x5.g0(this.a, "_eid");
            }
            long j = this.c - 1;
            this.c = j;
            if (j <= 0) {
                C6354l c6354lQ = this.d.q();
                c6354lQ.n();
                c6354lQ.j().K().b("Clearing complex main event info. appId", str);
                try {
                    c6354lQ.B().execSQL("delete from main_event_params where app_id=?", new String[]{str});
                } catch (SQLiteException e) {
                    c6354lQ.j().G().b("Error clearing complex main event", e);
                }
            } else {
                this.d.q().k0(str, l, this.c, this.a);
            }
            ArrayList arrayList = new ArrayList();
            for (C0911d2 c0911d2 : this.a.g0()) {
                this.d.o();
                if (x5.F(c0893b2, c0911d2.g0()) == null) {
                    arrayList.add(c0911d2);
                }
            }
            if (arrayList.isEmpty()) {
                this.d.j().I().b("No unique parameters in main event. eventName", strF0);
            } else {
                arrayList.addAll(listG0);
                listG0 = arrayList;
            }
        } else if (z) {
            this.b = l;
            this.a = c0893b2;
            this.d.o();
            long jLongValue = ((Long) x5.J(c0893b2, "_epc", 0L)).longValue();
            this.c = jLongValue;
            if (jLongValue <= 0) {
                this.d.j().I().b("Complex event with zero extra param count. eventName", strF0);
            } else {
                this.d.q().k0(str, (Long) C2254p.l(l), this.c, c0893b2);
            }
        }
        return (C0893b2) ((AbstractC0994m4) c0893b2.B().G(strF0).L().F(listG0).y());
    }
}
