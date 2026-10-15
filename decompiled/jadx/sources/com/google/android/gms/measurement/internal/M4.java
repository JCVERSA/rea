package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class M4 implements Runnable {
    private final /* synthetic */ J4 q;

    M4(J4 j4) {
        this.q = j4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.q.Y.d = null;
        this.q.Y.k0();
    }
}
