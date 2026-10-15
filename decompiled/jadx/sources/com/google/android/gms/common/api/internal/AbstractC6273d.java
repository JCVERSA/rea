package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import ax.Q6.C1478k;
import ax.o6.a.b;
import ax.p217n6.C2174e;
import ax.p249p6.InterfaceC2212j;
import ax.p265q6.C2254p;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractC6273d<A extends ax.o6.a.b, ResultT> {
    private final C2174e[] a;
    private final boolean b;
    private final int c;

    /* JADX INFO: renamed from: com.google.android.gms.common.api.internal.d$a */
    public static class a<A extends ax.o6.a.b, ResultT> {
        private InterfaceC2212j a;
        private C2174e[] c;
        private boolean b = true;
        private int d = 0;

        /* synthetic */ a(ax.p249p6.D d) {
        }

        public AbstractC6273d<A, ResultT> a() {
            C2254p.b(this.a != null, "execute parameter required");
            return new u(this, this.c, this.b, this.d);
        }

        public a<A, ResultT> b(InterfaceC2212j<A, C1478k<ResultT>> interfaceC2212j) {
            this.a = interfaceC2212j;
            return this;
        }

        public a<A, ResultT> c(boolean z) {
            this.b = z;
            return this;
        }

        public a<A, ResultT> d(C2174e... c2174eArr) {
            this.c = c2174eArr;
            return this;
        }

        public a<A, ResultT> e(int i) {
            this.d = i;
            return this;
        }
    }

    protected AbstractC6273d(C2174e[] c2174eArr, boolean z, int i) {
        this.a = c2174eArr;
        boolean z2 = false;
        if (c2174eArr != null && z) {
            z2 = true;
        }
        this.b = z2;
        this.c = i;
    }

    public static <A extends ax.o6.a.b, ResultT> a<A, ResultT> a() {
        return new a<>(null);
    }

    protected abstract void b(A a2, C1478k<ResultT> c1478k) throws RemoteException;

    public boolean c() {
        return this.b;
    }

    public final int d() {
        return this.c;
    }

    public final C2174e[] e() {
        return this.a;
    }
}
