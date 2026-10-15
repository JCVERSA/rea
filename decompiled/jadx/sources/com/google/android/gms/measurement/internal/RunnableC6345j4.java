package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.j4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6345j4 implements Runnable {
    private final /* synthetic */ C6317f4 X;
    private final /* synthetic */ long q;

    RunnableC6345j4(C6317f4 c6317f4, long j) {
        this.q = j;
        this.X = c6317f4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.o().v(this.q);
        this.X.e = null;
    }
}
