package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.b2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6287b2 implements Runnable {
    private final int X;
    private final Throwable Y;
    private final byte[] Z;
    private final String k0;
    private final Map<String, List<String>> l0;
    private final InterfaceC6294c2 q;

    private RunnableC6287b2(String str, InterfaceC6294c2 interfaceC6294c2, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        C2254p.l(interfaceC6294c2);
        this.q = interfaceC6294c2;
        this.X = i;
        this.Y = th;
        this.Z = bArr;
        this.k0 = str;
        this.l0 = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.q.a(this.k0, this.X, this.Y, this.Z, this.l0);
    }
}
