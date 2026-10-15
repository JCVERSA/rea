package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.j2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6343j2 {
    private final String a;
    private final boolean b;
    private boolean c;
    private boolean d;
    private final /* synthetic */ C6322g2 e;

    public C6343j2(C6322g2 c6322g2, String str, boolean z) {
        this.e = c6322g2;
        C2254p.f(str);
        this.a = str;
        this.b = z;
    }

    public final void a(boolean z) {
        SharedPreferences.Editor editorEdit = this.e.J().edit();
        editorEdit.putBoolean(this.a, z);
        editorEdit.apply();
        this.d = z;
    }

    public final boolean b() {
        if (!this.c) {
            this.c = true;
            this.d = this.e.J().getBoolean(this.a, this.b);
        }
        return this.d;
    }
}
