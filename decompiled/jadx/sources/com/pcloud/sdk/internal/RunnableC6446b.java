package com.pcloud.sdk.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* JADX INFO: renamed from: com.pcloud.sdk.internal.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
class RunnableC6446b implements ax.Sa.r, Runnable {
    private final Executor X;
    private volatile boolean Y;
    private volatile long Z;
    private volatile long k0;
    private final ax.Sa.r q;

    RunnableC6446b(ax.Sa.r rVar, Executor executor) {
        this.q = rVar;
        this.X = executor;
    }

    @Override // ax.Sa.r
    public void a(long j, long j2) {
        if (!this.Y || j == j2) {
            this.Z = j;
            this.k0 = j2;
            try {
                this.Y = true;
                this.X.execute(this);
            } catch (RejectedExecutionException unused) {
                this.Y = false;
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.q.a(this.Z, this.k0);
        this.Y = false;
    }
}
