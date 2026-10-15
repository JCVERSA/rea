package com.google.android.gms.measurement.internal;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import ax.H6.C0997m7;
import ax.p265q6.C2254p;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.d3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class CallableC6302d3 implements Callable<List<C6332h5>> {
    private final /* synthetic */ E5 a;
    private final /* synthetic */ Bundle b;
    private final /* synthetic */ I2 c;

    CallableC6302d3(I2 i2, E5 e5, Bundle bundle) {
        this.a = e5;
        this.b = bundle;
        this.c = i2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<C6332h5> call() throws Exception {
        this.c.q.u0();
        q5 q5Var = this.c.q;
        E5 e5 = this.a;
        Bundle bundle = this.b;
        q5Var.l().n();
        if (!C0997m7.a() || !q5Var.f0().E(e5.q, F.A0) || e5.q == null) {
            return new ArrayList();
        }
        if (bundle != null) {
            int[] intArray = bundle.getIntArray("uriSources");
            long[] longArray = bundle.getLongArray("uriTimestamps");
            if (intArray != null) {
                if (longArray == null || longArray.length != intArray.length) {
                    q5Var.j().G().a("Uri sources and timestamps do not match");
                } else {
                    for (int i = 0; i < intArray.length; i++) {
                        C6354l c6354lH0 = q5Var.h0();
                        String str = e5.q;
                        int i2 = intArray[i];
                        long j = longArray[i];
                        C2254p.f(str);
                        c6354lH0.n();
                        c6354lH0.u();
                        try {
                            int iDelete = c6354lH0.B().delete("trigger_uris", "app_id=? and source=? and timestamp_millis<=?", new String[]{str, String.valueOf(i2), String.valueOf(j)});
                            c6354lH0.j().K().d("Pruned " + iDelete + " trigger URIs. appId, source, timestamp", str, Integer.valueOf(i2), Long.valueOf(j));
                        } catch (SQLiteException e) {
                            c6354lH0.j().G().c("Error pruning trigger URIs. appId", V1.v(str), e);
                        }
                    }
                }
            }
        }
        return q5Var.h0().R0(e5.q);
    }
}
