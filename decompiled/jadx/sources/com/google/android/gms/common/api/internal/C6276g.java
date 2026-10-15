package com.google.android.gms.common.api.internal;

import ax.Q6.C1478k;
import com.google.android.gms.common.api.Status;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6276g {
    private final Map a = DesugarCollections.synchronizedMap(new WeakHashMap());
    private final Map b = DesugarCollections.synchronizedMap(new WeakHashMap());

    private final void h(boolean z, Status status) {
        HashMap map;
        HashMap map2;
        synchronized (this.a) {
            map = new HashMap(this.a);
        }
        synchronized (this.b) {
            map2 = new HashMap(this.b);
        }
        for (Map.Entry entry : map.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).d(status);
            }
        }
        for (Map.Entry entry2 : map2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((C1478k) entry2.getKey()).d(new ax.p233o6.b(status));
            }
        }
    }

    final void c(BasePendingResult basePendingResult, boolean z) {
        this.a.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.a(new C6274e(this, basePendingResult));
    }

    final void d(C1478k c1478k, boolean z) {
        this.b.put(c1478k, Boolean.valueOf(z));
        c1478k.a().b(new C6275f(this, c1478k));
    }

    final void e(int i, String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i == 1) {
            sb.append(" due to service disconnection.");
        } else if (i == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        h(true, new Status(20, sb.toString()));
    }

    public final void f() {
        h(false, C6272c.v0);
    }

    final boolean g() {
        return (this.a.isEmpty() && this.b.isEmpty()) ? false : true;
    }
}
