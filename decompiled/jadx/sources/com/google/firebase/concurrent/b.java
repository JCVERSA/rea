package com.google.firebase.concurrent;

import android.os.Process;
import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes3.dex */
class b implements ThreadFactory {
    private static final ThreadFactory e = Executors.defaultThreadFactory();
    private final AtomicLong a = new AtomicLong();
    private final String b;
    private final int c;
    private final StrictMode.ThreadPolicy d;

    b(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        this.b = str;
        this.c = i;
        this.d = threadPolicy;
    }

    public static /* synthetic */ void a(b bVar, Runnable runnable) {
        Process.setThreadPriority(bVar.c);
        StrictMode.ThreadPolicy threadPolicy = bVar.d;
        if (threadPolicy != null) {
            StrictMode.setThreadPolicy(threadPolicy);
        }
        runnable.run();
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(final Runnable runnable) {
        Thread threadNewThread = e.newThread(new Runnable() { // from class: com.google.firebase.concurrent.a
            @Override // java.lang.Runnable
            public final void run() {
                b.a(this.q, runnable);
            }
        });
        threadNewThread.setName(String.format(Locale.ROOT, "%s Thread #%d", this.b, Long.valueOf(this.a.getAndIncrement())));
        return threadNewThread;
    }
}
