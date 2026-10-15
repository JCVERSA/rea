package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.l2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6357l2 {
    private final String a;
    private final long b;
    private boolean c;
    private long d;
    private final /* synthetic */ C6322g2 e;

    public C6357l2(C6322g2 c6322g2, String str, long j) {
        this.e = c6322g2;
        C2254p.f(str);
        this.a = str;
        this.b = j;
    }

    public final long a() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.J().getLong(this.a, this.b);
        }
        return this.d;
    }

    public final void b(long j) {
        SharedPreferences.Editor editorEdit = this.e.J().edit();
        editorEdit.putLong(this.a, j);
        editorEdit.apply();
        this.d = j;
    }
}
