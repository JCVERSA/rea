package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.e2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6308e2 implements Runnable {
    private final /* synthetic */ C6315f2 X;
    private final /* synthetic */ boolean q;

    RunnableC6308e2(C6315f2 c6315f2, boolean z) {
        this.q = z;
        this.X = c6315f2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.X.a.J(this.q);
    }
}
