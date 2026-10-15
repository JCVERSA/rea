package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class RunnableC6277a implements Runnable {
    private final /* synthetic */ long X;
    private final /* synthetic */ C6439z Y;
    private final /* synthetic */ String q;

    RunnableC6277a(C6439z c6439z, String str, long j) {
        this.q = str;
        this.X = j;
        this.Y = c6439z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6439z.y(this.Y, this.q, this.X);
    }
}
