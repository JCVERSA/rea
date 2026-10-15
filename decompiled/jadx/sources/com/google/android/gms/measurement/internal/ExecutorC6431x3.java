package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.x3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class ExecutorC6431x3 implements Executor {
    private final /* synthetic */ C6351k3 q;

    ExecutorC6431x3(C6351k3 c6351k3) {
        this.q = c6351k3;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.q.l().D(runnable);
    }
}
