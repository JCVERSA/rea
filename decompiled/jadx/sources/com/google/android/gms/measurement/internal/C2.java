package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes3.dex */
final class C2<V> extends FutureTask<V> implements Comparable<C2<V>> {
    final boolean X;
    private final String Y;
    private final /* synthetic */ C6442z2 Z;
    private final long q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C2(C6442z2 c6442z2, Runnable runnable, boolean z, String str) {
        super(ax.H6.D0.a().q(runnable), null);
        this.Z = c6442z2;
        C2254p.l(str);
        long andIncrement = C6442z2.l.getAndIncrement();
        this.q = andIncrement;
        this.Y = str;
        this.X = z;
        if (andIncrement == Long.MAX_VALUE) {
            c6442z2.j().G().a("Tasks index overflow");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C2(C6442z2 c6442z2, Callable<V> callable, boolean z, String str) {
        super(ax.H6.D0.a().r(callable));
        this.Z = c6442z2;
        C2254p.l(str);
        long andIncrement = C6442z2.l.getAndIncrement();
        this.q = andIncrement;
        this.Y = str;
        this.X = z;
        if (andIncrement == Long.MAX_VALUE) {
            c6442z2.j().G().a("Tasks index overflow");
        }
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        C2 c2 = (C2) obj;
        boolean z = this.X;
        if (z != c2.X) {
            return z ? -1 : 1;
        }
        long j = this.q;
        long j2 = c2.q;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.Z.j().I().b("Two tasks share the same index. index", Long.valueOf(this.q));
        return 0;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        this.Z.j().G().b(this.Y, th);
        super.setException(th);
    }
}
