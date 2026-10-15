package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import ax.Q6.C1478k;
import ax.p217n6.C2172c;
import ax.p217n6.C2174e;
import ax.p249p6.C2204b;
import ax.p249p6.C2209g;
import ax.p265q6.C2252n;
import ax.p265q6.C2254p;
import com.google.android.gms.common.api.Status;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public final class o implements ax.o6.g.a, ax.o6.g.b {
    private final ax.o6.a.f X;
    private final C2204b Y;
    private final C6276g Z;
    private final int m0;
    private final ax.p249p6.C n0;
    private boolean o0;
    final /* synthetic */ C6272c s0;
    private final Queue q = new LinkedList();
    private final Set k0 = new HashSet();
    private final Map l0 = new HashMap();
    private final List p0 = new ArrayList();
    private C2172c q0 = null;
    private int r0 = 0;

    public o(C6272c c6272c, ax.p233o6.f fVar) {
        this.s0 = c6272c;
        ax.o6.a.f fVarQ = fVar.q(c6272c.t0.getLooper(), this);
        this.X = fVarQ;
        this.Y = fVar.l();
        this.Z = new C6276g();
        this.m0 = fVar.p();
        if (fVarQ.m()) {
            this.n0 = fVar.r(c6272c.k0, c6272c.t0);
        } else {
            this.n0 = null;
        }
    }

    static /* bridge */ /* synthetic */ void A(o oVar, p pVar) {
        C2174e[] c2174eArrG;
        if (oVar.p0.remove(pVar)) {
            oVar.s0.t0.removeMessages(15, pVar);
            oVar.s0.t0.removeMessages(16, pVar);
            C2174e c2174e = pVar.b;
            ArrayList arrayList = new ArrayList(oVar.q.size());
            for (A a : oVar.q) {
                if ((a instanceof ax.p249p6.u) && (c2174eArrG = ((ax.p249p6.u) a).g(oVar)) != null && ax.p345v6.b.b(c2174eArrG, c2174e)) {
                    arrayList.add(a);
                }
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                A a2 = (A) arrayList.get(i);
                oVar.q.remove(a2);
                a2.b(new ax.p233o6.n(c2174e));
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final C2174e c(C2174e[] c2174eArr) {
        if (c2174eArr != null && c2174eArr.length != 0) {
            C2174e[] c2174eArrK = this.X.k();
            if (c2174eArrK == null) {
                c2174eArrK = new C2174e[0];
            }
            ax.B.a aVar = new ax.B.a(c2174eArrK.length);
            for (C2174e c2174e : c2174eArrK) {
                aVar.put(c2174e.j(), Long.valueOf(c2174e.n()));
            }
            for (C2174e c2174e2 : c2174eArr) {
                Long l = (Long) aVar.get(c2174e2.j());
                if (l == null || l.longValue() < c2174e2.n()) {
                    return c2174e2;
                }
            }
        }
        return null;
    }

    private final void d(C2172c c2172c) {
        Iterator it = this.k0.iterator();
        while (it.hasNext()) {
            ((ax.p249p6.E) it.next()).b(this.Y, c2172c, C2252n.b(c2172c, C2172c.k0) ? this.X.g() : null);
        }
        this.k0.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void e(Status status) {
        C2254p.d(this.s0.t0);
        f(status, null, false);
    }

    private final void f(Status status, Exception exc, boolean z) {
        C2254p.d(this.s0.t0);
        if ((status == null) == (exc == null)) {
            throw new IllegalArgumentException("Status XOR exception should be null");
        }
        Iterator it = this.q.iterator();
        while (it.hasNext()) {
            A a = (A) it.next();
            if (!z || a.a == 2) {
                if (status != null) {
                    a.a(status);
                } else {
                    a.b(exc);
                }
                it.remove();
            }
        }
    }

    private final void g() {
        ArrayList arrayList = new ArrayList(this.q);
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            A a = (A) arrayList.get(i);
            if (!this.X.a()) {
                return;
            }
            if (m(a)) {
                this.q.remove(a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void h() {
        B();
        d(C2172c.k0);
        l();
        Iterator it = this.l0.values().iterator();
        if (it.hasNext()) {
            ((ax.p249p6.y) it.next()).getClass();
            throw null;
        }
        g();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void i(int i) {
        B();
        this.o0 = true;
        this.Z.e(i, this.X.l());
        C2204b c2204b = this.Y;
        C6272c c6272c = this.s0;
        c6272c.t0.sendMessageDelayed(Message.obtain(c6272c.t0, 9, c2204b), 5000L);
        C2204b c2204b2 = this.Y;
        C6272c c6272c2 = this.s0;
        c6272c2.t0.sendMessageDelayed(Message.obtain(c6272c2.t0, 11, c2204b2), 120000L);
        this.s0.m0.c();
        Iterator it = this.l0.values().iterator();
        while (it.hasNext()) {
            ((ax.p249p6.y) it.next()).a.run();
        }
    }

    private final void j() {
        this.s0.t0.removeMessages(12, this.Y);
        C2204b c2204b = this.Y;
        C6272c c6272c = this.s0;
        c6272c.t0.sendMessageDelayed(c6272c.t0.obtainMessage(12, c2204b), this.s0.q);
    }

    private final void k(A a) {
        a.d(this.Z, a());
        try {
            a.c(this);
        } catch (DeadObjectException unused) {
            M0(1);
            this.X.e("DeadObjectException thrown while running ApiCallRunner.");
        }
    }

    private final void l() {
        if (this.o0) {
            C6272c c6272c = this.s0;
            c6272c.t0.removeMessages(11, this.Y);
            C6272c c6272c2 = this.s0;
            c6272c2.t0.removeMessages(9, this.Y);
            this.o0 = false;
        }
    }

    private final boolean m(A a) {
        if (!(a instanceof ax.p249p6.u)) {
            k(a);
            return true;
        }
        ax.p249p6.u uVar = (ax.p249p6.u) a;
        C2174e c2174eC = c(uVar.g(this));
        if (c2174eC == null) {
            k(a);
            return true;
        }
        Log.w("GoogleApiManager", this.X.getClass().getName() + " could not execute call because it requires feature (" + c2174eC.j() + ", " + c2174eC.n() + ").");
        if (!this.s0.u0 || !uVar.f(this)) {
            uVar.b(new ax.p233o6.n(c2174eC));
            return true;
        }
        p pVar = new p(this.Y, c2174eC, null);
        int iIndexOf = this.p0.indexOf(pVar);
        if (iIndexOf >= 0) {
            p pVar2 = (p) this.p0.get(iIndexOf);
            this.s0.t0.removeMessages(15, pVar2);
            C6272c c6272c = this.s0;
            c6272c.t0.sendMessageDelayed(Message.obtain(c6272c.t0, 15, pVar2), 5000L);
            return false;
        }
        this.p0.add(pVar);
        C6272c c6272c2 = this.s0;
        c6272c2.t0.sendMessageDelayed(Message.obtain(c6272c2.t0, 15, pVar), 5000L);
        C6272c c6272c3 = this.s0;
        c6272c3.t0.sendMessageDelayed(Message.obtain(c6272c3.t0, 16, pVar), 120000L);
        C2172c c2172c = new C2172c(2, null);
        if (n(c2172c)) {
            return false;
        }
        this.s0.f(c2172c, this.m0);
        return false;
    }

    private final boolean n(C2172c c2172c) {
        synchronized (C6272c.x0) {
            try {
                C6272c c6272c = this.s0;
                if (c6272c.q0 == null || !c6272c.r0.contains(this.Y)) {
                    return false;
                }
                this.s0.q0.s(c2172c, this.m0);
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean o(boolean z) {
        C2254p.d(this.s0.t0);
        if (!this.X.a() || !this.l0.isEmpty()) {
            return false;
        }
        if (!this.Z.g()) {
            this.X.e("Timing out service connection.");
            return true;
        }
        if (!z) {
            return false;
        }
        j();
        return false;
    }

    static /* bridge */ /* synthetic */ void z(o oVar, p pVar) {
        if (oVar.p0.contains(pVar) && !oVar.o0) {
            if (oVar.X.a()) {
                oVar.g();
            } else {
                oVar.C();
            }
        }
    }

    public final void B() {
        C2254p.d(this.s0.t0);
        this.q0 = null;
    }

    public final void C() {
        C2254p.d(this.s0.t0);
        if (this.X.a() || this.X.f()) {
            return;
        }
        try {
            C6272c c6272c = this.s0;
            int iB = c6272c.m0.b(c6272c.k0, this.X);
            if (iB == 0) {
                C6272c c6272c2 = this.s0;
                ax.o6.a.f fVar = this.X;
                r rVar = new r(c6272c2, fVar, this.Y);
                if (fVar.m()) {
                    ((ax.p249p6.C) C2254p.l(this.n0)).s5(rVar);
                }
                try {
                    this.X.p(rVar);
                    return;
                } catch (SecurityException e) {
                    F(new C2172c(10), e);
                    return;
                }
            }
            C2172c c2172c = new C2172c(iB, null);
            Log.w("GoogleApiManager", "The service for " + this.X.getClass().getName() + " is not available: " + c2172c.toString());
            F(c2172c, null);
        } catch (IllegalStateException e2) {
            F(new C2172c(10), e2);
        }
    }

    public final void D(A a) {
        C2254p.d(this.s0.t0);
        if (this.X.a()) {
            if (m(a)) {
                j();
                return;
            } else {
                this.q.add(a);
                return;
            }
        }
        this.q.add(a);
        C2172c c2172c = this.q0;
        if (c2172c == null || !c2172c.A()) {
            C();
        } else {
            F(this.q0, null);
        }
    }

    final void E() {
        this.r0++;
    }

    public final void F(C2172c c2172c, Exception exc) {
        C2254p.d(this.s0.t0);
        ax.p249p6.C c = this.n0;
        if (c != null) {
            c.y6();
        }
        B();
        this.s0.m0.c();
        d(c2172c);
        if ((this.X instanceof ax.p297s6.e) && c2172c.j() != 24) {
            this.s0.X = true;
            C6272c c6272c = this.s0;
            c6272c.t0.sendMessageDelayed(c6272c.t0.obtainMessage(19), 300000L);
        }
        if (c2172c.j() == 4) {
            e(C6272c.w0);
            return;
        }
        if (this.q.isEmpty()) {
            this.q0 = c2172c;
            return;
        }
        if (exc != null) {
            C2254p.d(this.s0.t0);
            f(null, exc, false);
            return;
        }
        if (!this.s0.u0) {
            e(C6272c.g(this.Y, c2172c));
            return;
        }
        f(C6272c.g(this.Y, c2172c), null, true);
        if (this.q.isEmpty() || n(c2172c) || this.s0.f(c2172c, this.m0)) {
            return;
        }
        if (c2172c.j() == 18) {
            this.o0 = true;
        }
        if (!this.o0) {
            e(C6272c.g(this.Y, c2172c));
            return;
        }
        C6272c c6272c2 = this.s0;
        c6272c2.t0.sendMessageDelayed(Message.obtain(c6272c2.t0, 9, this.Y), 5000L);
    }

    public final void G(C2172c c2172c) {
        C2254p.d(this.s0.t0);
        ax.o6.a.f fVar = this.X;
        fVar.e("onSignInFailed for " + fVar.getClass().getName() + " with " + String.valueOf(c2172c));
        F(c2172c, null);
    }

    public final void H(ax.p249p6.E e) {
        C2254p.d(this.s0.t0);
        this.k0.add(e);
    }

    public final void I() {
        C2254p.d(this.s0.t0);
        if (this.o0) {
            C();
        }
    }

    public final void J() {
        C2254p.d(this.s0.t0);
        e(C6272c.v0);
        this.Z.f();
        for (C2209g c2209g : (C2209g[]) this.l0.keySet().toArray(new C2209g[0])) {
            D(new z(c2209g, new C1478k()));
        }
        d(new C2172c(4));
        if (this.X.a()) {
            this.X.n(new n(this));
        }
    }

    public final void K() {
        C2254p.d(this.s0.t0);
        if (this.o0) {
            l();
            C6272c c6272c = this.s0;
            e(c6272c.l0.g(c6272c.k0) == 18 ? new Status(21, "Connection timed out waiting for Google Play services update to complete.") : new Status(22, "API failed to connect while resuming due to an unknown error."));
            this.X.e("Timing out connection while resuming.");
        }
    }

    @Override // ax.p249p6.InterfaceC2205c
    public final void M0(int i) {
        if (Looper.myLooper() == this.s0.t0.getLooper()) {
            i(i);
        } else {
            this.s0.t0.post(new l(this, i));
        }
    }

    final boolean N() {
        return this.X.a();
    }

    @Override // ax.p249p6.InterfaceC2211i
    public final void P0(C2172c c2172c) {
        F(c2172c, null);
    }

    public final boolean a() {
        return this.X.m();
    }

    @ResultIgnorabilityUnspecified
    public final boolean b() {
        return o(true);
    }

    @Override // ax.p249p6.InterfaceC2205c
    public final void e1(Bundle bundle) {
        if (Looper.myLooper() == this.s0.t0.getLooper()) {
            h();
        } else {
            this.s0.t0.post(new k(this));
        }
    }

    public final int p() {
        return this.m0;
    }

    final int q() {
        return this.r0;
    }

    public final C2172c r() {
        C2254p.d(this.s0.t0);
        return this.q0;
    }

    public final ax.o6.a.f t() {
        return this.X;
    }

    public final Map v() {
        return this.l0;
    }
}
