package com.google.firebase.remoteconfig.internal;

import android.util.Log;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1471d;
import ax.Q6.InterfaceC1473f;
import ax.Q6.InterfaceC1474g;
import ax.Q6.InterfaceC1476i;
import ax.p101g2.m;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public class b {
    private static final Map<String, b> d = new HashMap();
    private static final Executor e = new m();
    private final Executor a;
    private final h b;
    private AbstractC1477j<c> c = null;

    /* JADX INFO: renamed from: com.google.firebase.remoteconfig.internal.b$b, reason: collision with other inner class name */
    private static class C0585b<TResult> implements InterfaceC1474g<TResult>, InterfaceC1473f, InterfaceC1471d {
        private final CountDownLatch a;

        private C0585b() {
            this.a = new CountDownLatch(1);
        }

        @Override // ax.Q6.InterfaceC1474g
        public void a(TResult tresult) {
            this.a.countDown();
        }

        @Override // ax.Q6.InterfaceC1471d
        public void b() {
            this.a.countDown();
        }

        public boolean c(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.a.await(j, timeUnit);
        }

        @Override // ax.Q6.InterfaceC1473f
        public void d(Exception exc) {
            this.a.countDown();
        }
    }

    private b(Executor executor, h hVar) {
        this.a = executor;
        this.b = hVar;
    }

    public static /* synthetic */ AbstractC1477j a(b bVar, boolean z, c cVar, Void r3) {
        if (z) {
            bVar.k(cVar);
        } else {
            bVar.getClass();
        }
        return ax.Q6.m.e(cVar);
    }

    private static <TResult> TResult c(AbstractC1477j<TResult> abstractC1477j, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        C0585b c0585b = new C0585b();
        Executor executor = e;
        abstractC1477j.e(executor, c0585b);
        abstractC1477j.d(executor, c0585b);
        abstractC1477j.a(executor, c0585b);
        if (!c0585b.c(j, timeUnit)) {
            throw new TimeoutException("Task await timed out.");
        }
        if (abstractC1477j.n()) {
            return abstractC1477j.k();
        }
        throw new ExecutionException(abstractC1477j.j());
    }

    public static synchronized b h(Executor executor, h hVar) {
        String strB;
        Map<String, b> map;
        try {
            strB = hVar.b();
            map = d;
            if (!map.containsKey(strB)) {
                map.put(strB, new b(executor, hVar));
            }
        } catch (Throwable th) {
            throw th;
        }
        return map.get(strB);
    }

    private synchronized void k(c cVar) {
        this.c = ax.Q6.m.e(cVar);
    }

    public void d() {
        synchronized (this) {
            this.c = ax.Q6.m.e(null);
        }
        this.b.a();
    }

    public synchronized AbstractC1477j<c> e() {
        try {
            AbstractC1477j<c> abstractC1477j = this.c;
            if (abstractC1477j == null || (abstractC1477j.m() && !this.c.n())) {
                Executor executor = this.a;
                final h hVar = this.b;
                Objects.requireNonNull(hVar);
                this.c = ax.Q6.m.c(executor, new Callable() { // from class: ax.Q8.b
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return hVar.d();
                    }
                });
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.c;
    }

    public c f() {
        return g(5L);
    }

    c g(long j) {
        synchronized (this) {
            try {
                AbstractC1477j<c> abstractC1477j = this.c;
                if (abstractC1477j != null && abstractC1477j.n()) {
                    return this.c.k();
                }
                try {
                    return (c) c(e(), j, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e2) {
                    Log.d("FirebaseRemoteConfig", "Reading from storage file failed.", e2);
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public AbstractC1477j<c> i(c cVar) {
        return j(cVar, true);
    }

    public AbstractC1477j<c> j(final c cVar, final boolean z) {
        return ax.Q6.m.c(this.a, new Callable() { // from class: ax.Q8.c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.a.b.e(cVar);
            }
        }).p(this.a, new InterfaceC1476i() { // from class: ax.Q8.d
            @Override // ax.Q6.InterfaceC1476i
            public final AbstractC1477j a(Object obj) {
                return com.google.firebase.remoteconfig.internal.b.a(this.a, z, cVar, (Void) obj);
            }
        });
    }
}
