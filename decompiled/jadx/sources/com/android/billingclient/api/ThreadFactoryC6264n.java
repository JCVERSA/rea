package com.android.billingclient.api;

import j$.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.android.billingclient.api.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class ThreadFactoryC6264n implements ThreadFactory {
    private final ThreadFactory a;
    private final AtomicInteger b;

    ThreadFactoryC6264n(C6252b c6252b) {
        Objects.requireNonNull(c6252b);
        this.a = Executors.defaultThreadFactory();
        this.b = new AtomicInteger(1);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        AtomicInteger atomicInteger = this.b;
        Thread threadNewThread = this.a.newThread(runnable);
        threadNewThread.setName("PlayBillingLibrary-" + atomicInteger.getAndIncrement());
        return threadNewThread;
    }
}
