package com.google.android.gms.measurement.internal;

import android.content.Context;
import ax.p265q6.C2254p;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.z2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6442z2 extends AbstractC6309e3 {
    private static final AtomicLong l = new AtomicLong(Long.MIN_VALUE);
    private B2 c;
    private B2 d;
    private final PriorityBlockingQueue<C2<?>> e;
    private final BlockingQueue<C2<?>> f;
    private final Thread.UncaughtExceptionHandler g;
    private final Thread.UncaughtExceptionHandler h;
    private final Object i;
    private final Semaphore j;
    private volatile boolean k;

    C6442z2(E2 e2) {
        super(e2);
        this.i = new Object();
        this.j = new Semaphore(2);
        this.e = new PriorityBlockingQueue<>();
        this.f = new LinkedBlockingQueue();
        this.g = new A2(this, "Thread death: Uncaught exception on worker thread");
        this.h = new A2(this, "Thread death: Uncaught exception on network thread");
    }

    private final void y(C2<?> c2) {
        synchronized (this.i) {
            try {
                this.e.add(c2);
                B2 b2 = this.c;
                if (b2 == null) {
                    B2 b3 = new B2(this, "Measurement Worker", this.e);
                    this.c = b3;
                    b3.setUncaughtExceptionHandler(this.g);
                    this.c.start();
                } else {
                    b2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final <V> Future<V> B(Callable<V> callable) throws IllegalStateException {
        p();
        C2254p.l(callable);
        C2<?> c2 = new C2<>(this, (Callable<?>) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.c) {
            c2.run();
            return c2;
        }
        y(c2);
        return c2;
    }

    public final void D(Runnable runnable) throws IllegalStateException {
        p();
        C2254p.l(runnable);
        y(new C2<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void G(Runnable runnable) throws IllegalStateException {
        p();
        C2254p.l(runnable);
        y(new C2<>(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean J() {
        return Thread.currentThread() == this.c;
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ Context a() {
        return super.a();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ ax.p345v6.f b() {
        return super.b();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6326h d() {
        return super.d();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6421w e() {
        return super.e();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6291c f() {
        return super.f();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ R1 g() {
        return super.g();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ C6322g2 h() {
        return super.h();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ B5 i() {
        return super.i();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ V1 j() {
        return super.j();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final void k() {
        if (Thread.currentThread() != this.d) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3, com.google.android.gms.measurement.internal.InterfaceC6323g3
    public final /* bridge */ /* synthetic */ C6442z2 l() {
        return super.l();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final /* bridge */ /* synthetic */ void m() {
        super.m();
    }

    @Override // com.google.android.gms.measurement.internal.C6316f3
    public final void n() {
        if (Thread.currentThread() != this.c) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6309e3
    protected final boolean t() {
        return false;
    }

    final <T> T v(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            l().D(runnable);
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                j().L().a("Interrupted waiting for " + str);
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            j().L().a("Timed out waiting for " + str);
        }
        return t;
    }

    public final <V> Future<V> w(Callable<V> callable) throws IllegalStateException {
        p();
        C2254p.l(callable);
        C2<?> c2 = new C2<>(this, (Callable<?>) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() != this.c) {
            y(c2);
            return c2;
        }
        if (!this.e.isEmpty()) {
            j().L().a("Callable skipped the worker queue.");
        }
        c2.run();
        return c2;
    }

    public final void z(Runnable runnable) throws IllegalStateException {
        p();
        C2254p.l(runnable);
        C2<?> c2 = new C2<>(this, runnable, false, "Task exception on network thread");
        synchronized (this.i) {
            try {
                this.f.add(c2);
                B2 b2 = this.d;
                if (b2 == null) {
                    B2 b3 = new B2(this, "Measurement Network", this.f);
                    this.d = b3;
                    b3.setUncaughtExceptionHandler(this.h);
                    this.d.start();
                } else {
                    b2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
