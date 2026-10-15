package com.pcloud.sdk.internal;

import java.io.IOException;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
class E<T> implements ax.Sa.i<T> {
    private final Executor X;
    private final ax.Sa.i<T> q;

    class a implements ax.Sa.j<T> {
        final /* synthetic */ ax.Sa.j a;

        a(ax.Sa.j jVar) {
            this.a = jVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(ax.Sa.j jVar, Throwable th) {
            jVar.a(E.this, th);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(ax.Sa.j jVar, Object obj) {
            jVar.b(E.this, obj);
        }

        @Override // ax.Sa.j
        public void a(ax.Sa.i<T> iVar, final Throwable th) {
            Executor executor = E.this.X;
            final ax.Sa.j jVar = this.a;
            executor.execute(new Runnable() { // from class: com.pcloud.sdk.internal.C
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.e(jVar, th);
                }
            });
        }

        @Override // ax.Sa.j
        public void b(ax.Sa.i<T> iVar, final T t) {
            Executor executor = E.this.X;
            final ax.Sa.j jVar = this.a;
            executor.execute(new Runnable() { // from class: com.pcloud.sdk.internal.D
                @Override // java.lang.Runnable
                public final void run() {
                    this.q.f(jVar, t);
                }
            });
        }
    }

    E(ax.Sa.i<T> iVar, Executor executor) {
        this.q = iVar;
        this.X = executor;
    }

    @Override // ax.Sa.i
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public E<T> m4clone() {
        return new E<>(this.q.m4clone(), this.X);
    }

    @Override // ax.Sa.i
    public void c0(ax.Sa.j<T> jVar) {
        if (jVar == null) {
            throw new IllegalArgumentException("Callback argument cannot be null.");
        }
        this.q.c0(new a(jVar));
    }

    @Override // ax.Sa.i
    public T execute() throws ax.Sa.b, IOException {
        return this.q.execute();
    }
}
