package com.google.firebase.concurrent;

import android.annotation.SuppressLint;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"RestrictedApi"})
class p<V> extends ax.D.a<V> implements ScheduledFuture<V> {
    private final ScheduledFuture<?> n0;

    class a implements b<V> {
        a() {
        }

        @Override // com.google.firebase.concurrent.p.b
        public void a(Throwable th) {
            p.this.E(th);
        }

        @Override // com.google.firebase.concurrent.p.b
        public void set(V v) {
            p.this.D(v);
        }
    }

    interface b<T> {
        void a(Throwable th);

        void set(T t);
    }

    interface c<T> {
        ScheduledFuture<?> a(b<T> bVar);
    }

    p(c<V> cVar) {
        this.n0 = cVar.a(new a());
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public int compareTo(Delayed delayed) {
        return this.n0.compareTo(delayed);
    }

    @Override // java.util.concurrent.Delayed
    public long getDelay(TimeUnit timeUnit) {
        return this.n0.getDelay(timeUnit);
    }

    @Override // ax.D.a
    protected void k() {
        this.n0.cancel(G());
    }
}
