package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Looper;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6291c {
    C6291c(Context context) {
    }

    public static boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
