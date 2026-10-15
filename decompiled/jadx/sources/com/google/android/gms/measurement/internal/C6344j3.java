package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.j3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6344j3 {
    final Context a;
    String b;
    String c;
    String d;
    Boolean e;
    long f;
    ax.H6.U0 g;
    boolean h;
    Long i;
    String j;

    public C6344j3(Context context, ax.H6.U0 u0, Long l) {
        this.h = true;
        C2254p.l(context);
        Context applicationContext = context.getApplicationContext();
        C2254p.l(applicationContext);
        this.a = applicationContext;
        this.i = l;
        if (u0 != null) {
            this.g = u0;
            this.b = u0.l0;
            this.c = u0.k0;
            this.d = u0.Z;
            this.h = u0.Y;
            this.f = u0.X;
            this.j = u0.n0;
            Bundle bundle = u0.m0;
            if (bundle != null) {
                this.e = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
