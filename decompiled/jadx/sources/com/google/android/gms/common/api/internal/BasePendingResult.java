package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import ax.p233o6.l;
import ax.p265q6.C2254p;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
@KeepName
public abstract class BasePendingResult<R extends ax.p233o6.l> extends ax.p233o6.h<R> {
    static final ThreadLocal n = new F();
    private final Object a;
    protected final a b;
    protected final WeakReference c;
    private final CountDownLatch d;
    private final ArrayList e;
    private ax.p233o6.m f;
    private final AtomicReference g;
    private ax.p233o6.l h;
    private Status i;
    private volatile boolean j;
    private boolean k;
    private boolean l;
    private boolean m;

    @KeepName
    private G resultGuardian;

    public static class a<R extends ax.p233o6.l> extends ax.E6.j {
        public a(Looper looper) {
            super(looper);
        }

        public final void a(ax.p233o6.m mVar, ax.p233o6.l lVar) {
            ThreadLocal threadLocal = BasePendingResult.n;
            sendMessage(obtainMessage(1, new Pair((ax.p233o6.m) C2254p.l(mVar), lVar)));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    ((BasePendingResult) message.obj).d(Status.o0);
                    return;
                }
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + i, new Exception());
                return;
            }
            Pair pair = (Pair) message.obj;
            ax.p233o6.m mVar = (ax.p233o6.m) pair.first;
            ax.p233o6.l lVar = (ax.p233o6.l) pair.second;
            try {
                mVar.a(lVar);
            } catch (RuntimeException e) {
                BasePendingResult.k(lVar);
                throw e;
            }
        }
    }

    @Deprecated
    BasePendingResult() {
        this.a = new Object();
        this.d = new CountDownLatch(1);
        this.e = new ArrayList();
        this.g = new AtomicReference();
        this.m = false;
        this.b = new a(Looper.getMainLooper());
        this.c = new WeakReference(null);
    }

    protected BasePendingResult(ax.p233o6.g gVar) {
        this.a = new Object();
        this.d = new CountDownLatch(1);
        this.e = new ArrayList();
        this.g = new AtomicReference();
        this.m = false;
        this.b = new a(gVar != null ? gVar.c() : Looper.getMainLooper());
        this.c = new WeakReference(gVar);
    }

    private final ax.p233o6.l g() {
        ax.p233o6.l lVar;
        synchronized (this.a) {
            C2254p.p(!this.j, "Result has already been consumed.");
            C2254p.p(e(), "Result is not ready.");
            lVar = this.h;
            this.h = null;
            this.f = null;
            this.j = true;
        }
        if (((w) this.g.getAndSet(null)) == null) {
            return (ax.p233o6.l) C2254p.l(lVar);
        }
        throw null;
    }

    private final void h(ax.p233o6.l lVar) {
        this.h = lVar;
        this.i = lVar.e();
        this.d.countDown();
        ax.p249p6.F f = null;
        if (this.k) {
            this.f = null;
        } else {
            ax.p233o6.m mVar = this.f;
            if (mVar != null) {
                this.b.removeMessages(2);
                this.b.a(mVar, g());
            } else if (this.h instanceof ax.p233o6.j) {
                this.resultGuardian = new G(this, f);
            }
        }
        ArrayList arrayList = this.e;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((ax.o6.h.a) arrayList.get(i)).a(this.i);
        }
        this.e.clear();
    }

    public static void k(ax.p233o6.l lVar) {
        if (lVar instanceof ax.p233o6.j) {
            try {
                ((ax.p233o6.j) lVar).h();
            } catch (RuntimeException e) {
                Log.w("BasePendingResult", "Unable to release ".concat(String.valueOf(lVar)), e);
            }
        }
    }

    @Override // ax.p233o6.h
    public final void a(ax.o6.h.a aVar) {
        C2254p.b(aVar != null, "Callback cannot be null.");
        synchronized (this.a) {
            try {
                if (e()) {
                    aVar.a(this.i);
                } else {
                    this.e.add(aVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // ax.p233o6.h
    @ResultIgnorabilityUnspecified
    public final R b(long j, TimeUnit timeUnit) {
        if (j > 0) {
            C2254p.k("await must not be called on the UI thread when time is greater than zero.");
        }
        C2254p.p(!this.j, "Result has already been consumed.");
        C2254p.p(true, "Cannot await if then() has been called.");
        try {
            if (!this.d.await(j, timeUnit)) {
                d(Status.o0);
            }
        } catch (InterruptedException unused) {
            d(Status.m0);
        }
        C2254p.p(e(), "Result is not ready.");
        return (R) g();
    }

    protected abstract R c(Status status);

    @Deprecated
    public final void d(Status status) {
        synchronized (this.a) {
            try {
                if (!e()) {
                    f(c(status));
                    this.l = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean e() {
        return this.d.getCount() == 0;
    }

    public final void f(R r) {
        synchronized (this.a) {
            try {
                if (this.l || this.k) {
                    k(r);
                    return;
                }
                e();
                C2254p.p(!e(), "Results have already been set");
                C2254p.p(!this.j, "Result has already been consumed");
                h(r);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void j() {
        boolean z = true;
        if (!this.m && !((Boolean) n.get()).booleanValue()) {
            z = false;
        }
        this.m = z;
    }
}
