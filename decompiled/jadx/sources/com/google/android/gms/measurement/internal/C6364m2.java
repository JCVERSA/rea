package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.m2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6364m2 {
    private final String a;
    private boolean b;
    private String c;
    private final /* synthetic */ C6322g2 d;

    public C6364m2(C6322g2 c6322g2, String str, String str2) {
        this.d = c6322g2;
        C2254p.f(str);
        this.a = str;
    }

    public final String a() {
        if (!this.b) {
            this.b = true;
            this.c = this.d.J().getString(this.a, null);
        }
        return this.c;
    }

    public final void b(String str) {
        SharedPreferences.Editor editorEdit = this.d.J().edit();
        editorEdit.putString(this.a, str);
        editorEdit.apply();
        this.c = str;
    }
}
