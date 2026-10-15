package com.google.android.gms.measurement.internal;

import android.os.Process;
import ax.p265q6.C2254p;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
final class B2 extends Thread {
    private final BlockingQueue<C2<?>> X;
    private boolean Y = false;
    private final /* synthetic */ C6442z2 Z;
    private final Object q;

    public B2(C6442z2 c6442z2, String str, BlockingQueue<C2<?>> blockingQueue) {
        this.Z = c6442z2;
        C2254p.l(str);
        C2254p.l(blockingQueue);
        this.q = new Object();
        this.X = blockingQueue;
        setName(str);
    }

    private final void b(InterruptedException interruptedException) {
        this.Z.j().L().b(getName() + " was interrupted", interruptedException);
    }

    private final void c() {
        synchronized (this.Z.i) {
            try {
                if (!this.Y) {
                    this.Z.j.release();
                    this.Z.i.notifyAll();
                    if (this == this.Z.c) {
                        this.Z.c = null;
                    } else if (this == this.Z.d) {
                        this.Z.d = null;
                    } else {
                        this.Z.j().G().a("Current scheduler thread is neither worker nor network");
                    }
                    this.Y = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a() {
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z = false;
        while (!z) {
            try {
                this.Z.j.acquire();
                z = true;
            } catch (InterruptedException e) {
                b(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                C2<?> c2Poll = this.X.poll();
                if (c2Poll != null) {
                    Process.setThreadPriority(c2Poll.X ? threadPriority : 10);
                    c2Poll.run();
                } else {
                    synchronized (this.q) {
                        if (this.X.peek() == null && !this.Z.k) {
                            try {
                                this.q.wait(30000L);
                            } catch (InterruptedException e2) {
                                b(e2);
                            }
                        }
                    }
                    synchronized (this.Z.i) {
                        if (this.X.peek() == null) {
                            c();
                            c();
                            return;
                        }
                    }
                }
            }
        } catch (Throwable th) {
            c();
            throw th;
        }
    }
}
