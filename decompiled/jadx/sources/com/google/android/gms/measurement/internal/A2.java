package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;

/* JADX INFO: loaded from: classes3.dex */
final class A2 implements Thread.UncaughtExceptionHandler {
    private final String a;
    private final /* synthetic */ C6442z2 b;

    public A2(C6442z2 c6442z2, String str) {
        this.b = c6442z2;
        C2254p.l(str);
        this.a = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.b.j().G().b(this.a, th);
    }
}
