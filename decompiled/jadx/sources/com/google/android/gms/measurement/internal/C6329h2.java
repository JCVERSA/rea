package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6329h2 {
    private final ax.M6.o a;

    C6329h2(ax.M6.o oVar) {
        this.a = oVar;
    }

    static C6329h2 b(String str) {
        return new C6329h2((TextUtils.isEmpty(str) || str.length() > 1) ? ax.M6.o.UNINITIALIZED : C6330h3.c(str.charAt(0)));
    }

    final ax.M6.o a() {
        return this.a;
    }

    final String c() {
        return String.valueOf(C6330h3.a(this.a));
    }
}
