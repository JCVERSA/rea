package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import ax.Q6.AbstractC1477j;
import ax.Q6.C1478k;
import ax.p217n6.C2172c;
import ax.p217n6.C2176g;
import ax.p249p6.C2204b;
import ax.p265q6.AbstractC2246h;
import ax.p265q6.C2251m;
import ax.p265q6.C2255q;
import ax.p265q6.C2257t;
import ax.p265q6.C2258u;
import ax.p265q6.H;
import ax.p265q6.InterfaceC2259v;
import com.google.android.gms.common.api.Status;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public class C6272c implements Handler.Callback {
    public static final Status v0 = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status w0 = new Status(4, "The user must be signed in to make this API call.");
    private static final Object x0 = new Object();
    private static C6272c y0;
    private C2257t Y;
    private InterfaceC2259v Z;
    private final Context k0;
    private final C2176g l0;
    private final H m0;
    private final Handler t0;
    private volatile boolean u0;
    private long q = 10000;
    private boolean X = false;
    private final AtomicInteger n0 = new AtomicInteger(1);
    private final AtomicInteger o0 = new AtomicInteger(0);
    private final Map p0 = new ConcurrentHashMap(5, 0.75f, 1);
    private h q0 = null;
    private final Set r0 = new ax.B.b();
    private final Set s0 = new ax.B.b();

    private C6272c(Context context, Looper looper, C2176g c2176g) {
        this.u0 = true;
        this.k0 = context;
        ax.E6.j jVar = new ax.E6.j(looper, this);
        this.t0 = jVar;
        this.l0 = c2176g;
        this.m0 = new H(c2176g);
        if (ax.p345v6.j.a(context)) {
            this.u0 = false;
        }
        jVar.sendMessage(jVar.obtainMessage(6));
    }

    public static void a() {
        synchronized (x0) {
            try {
                C6272c c6272c = y0;
                if (c6272c != null) {
                    c6272c.o0.incrementAndGet();
                    Handler handler = c6272c.t0;
                    handler.sendMessageAtFrontOfQueue(handler.obtainMessage(10));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Status g(C2204b c2204b, C2172c c2172c) {
        return new Status(c2172c, "API: " + c2204b.b() + " is not available on this device. Connection failed with: " + String.valueOf(c2172c));
    }

    @ResultIgnorabilityUnspecified
    private final o h(ax.p233o6.f fVar) {
        Map map = this.p0;
        C2204b c2204bL = fVar.l();
        o oVar = (o) map.get(c2204bL);
        if (oVar == null) {
            oVar = new o(this, fVar);
            this.p0.put(c2204bL, oVar);
        }
        if (oVar.a()) {
            this.s0.add(c2204bL);
        }
        oVar.C();
        return oVar;
    }

    private final InterfaceC2259v i() {
        if (this.Z == null) {
            this.Z = C2258u.a(this.k0);
        }
        return this.Z;
    }

    private final void j() {
        C2257t c2257t = this.Y;
        if (c2257t != null) {
            if (c2257t.j() > 0 || e()) {
                i().a(c2257t);
            }
            this.Y = null;
        }
    }

    private final void k(C1478k c1478k, int i, ax.p233o6.f fVar) {
        s sVarB;
        if (i == 0 || (sVarB = s.b(this, i, fVar.l())) == null) {
            return;
        }
        AbstractC1477j abstractC1477jA = c1478k.a();
        final Handler handler = this.t0;
        handler.getClass();
        abstractC1477jA.c(new Executor() { // from class: ax.p6.p
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                handler.post(runnable);
            }
        }, sVarB);
    }

    @ResultIgnorabilityUnspecified
    public static C6272c u(Context context) {
        C6272c c6272c;
        synchronized (x0) {
            try {
                if (y0 == null) {
                    y0 = new C6272c(context.getApplicationContext(), AbstractC2246h.d().getLooper(), C2176g.o());
                }
                c6272c = y0;
            } catch (Throwable th) {
                throw th;
            }
        }
        return c6272c;
    }

    public final void A(ax.p233o6.f fVar, int i, AbstractC6271b abstractC6271b) {
        this.t0.sendMessage(this.t0.obtainMessage(4, new ax.p249p6.x(new x(i, abstractC6271b), this.o0.get(), fVar)));
    }

    public final void B(ax.p233o6.f fVar, int i, AbstractC6273d abstractC6273d, C1478k c1478k, ax.p249p6.l lVar) {
        k(c1478k, abstractC6273d.d(), fVar);
        this.t0.sendMessage(this.t0.obtainMessage(4, new ax.p249p6.x(new y(i, abstractC6273d, c1478k, lVar), this.o0.get(), fVar)));
    }

    final void C(C2251m c2251m, int i, long j, int i2) {
        this.t0.sendMessage(this.t0.obtainMessage(18, new t(c2251m, i, j, i2)));
    }

    public final void D(C2172c c2172c, int i) {
        if (f(c2172c, i)) {
            return;
        }
        Handler handler = this.t0;
        handler.sendMessage(handler.obtainMessage(5, i, 0, c2172c));
    }

    public final void E() {
        Handler handler = this.t0;
        handler.sendMessage(handler.obtainMessage(3));
    }

    public final void F(ax.p233o6.f fVar) {
        Handler handler = this.t0;
        handler.sendMessage(handler.obtainMessage(7, fVar));
    }

    public final void b(h hVar) {
        synchronized (x0) {
            try {
                if (this.q0 != hVar) {
                    this.q0 = hVar;
                    this.r0.clear();
                }
                this.r0.addAll(hVar.t());
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final void c(h hVar) {
        synchronized (x0) {
            try {
                if (this.q0 == hVar) {
                    this.q0 = null;
                    this.r0.clear();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final boolean e() {
        if (this.X) {
            return false;
        }
        ax.p265q6.r rVarA = C2255q.b().a();
        if (rVarA != null && !rVarA.z()) {
            return false;
        }
        int iA = this.m0.a(this.k0, 203400000);
        return iA == -1 || iA == 0;
    }

    @ResultIgnorabilityUnspecified
    final boolean f(C2172c c2172c, int i) {
        return this.l0.y(this.k0, c2172c, i);
    }

    /* JADX WARN: Code duplicated, block: B:63:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:65:0x01ef  */
    /* JADX WARN: Code duplicated, block: B:66:0x0220  */
    /* JADX WARN: Code duplicated, block: B:67:0x022d  */
    /* JADX WARN: Instruction removed from duplicated block: B:65:0x01ef, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:67:0x022d, please report this as an issue */
    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        int i = message.what;
        o oVar = null;
        switch (i) {
            case 1:
                this.q = true == ((Boolean) message.obj).booleanValue() ? 10000L : 300000L;
                this.t0.removeMessages(12);
                for (C2204b c2204b : this.p0.keySet()) {
                    Handler handler = this.t0;
                    handler.sendMessageDelayed(handler.obtainMessage(12, c2204b), this.q);
                }
                return true;
            case 2:
                ax.p249p6.E e = (ax.p249p6.E) message.obj;
                for (C2204b c2204b2 : e.a()) {
                    o oVar2 = (o) this.p0.get(c2204b2);
                    if (oVar2 == null) {
                        e.b(c2204b2, new C2172c(13), null);
                        return true;
                    }
                    if (oVar2.N()) {
                        e.b(c2204b2, C2172c.k0, oVar2.t().g());
                    } else {
                        C2172c c2172cR = oVar2.r();
                        if (c2172cR != null) {
                            e.b(c2204b2, c2172cR, null);
                        } else {
                            oVar2.H(e);
                            oVar2.C();
                        }
                    }
                }
                return true;
            case 3:
                for (o oVar3 : this.p0.values()) {
                    oVar3.B();
                    oVar3.C();
                }
                return true;
            case 4:
            case 8:
            case 13:
                ax.p249p6.x xVar = (ax.p249p6.x) message.obj;
                o oVarH = (o) this.p0.get(xVar.c.l());
                if (oVarH == null) {
                    oVarH = h(xVar.c);
                }
                if (!oVarH.a() || this.o0.get() == xVar.b) {
                    oVarH.D(xVar.a);
                } else {
                    xVar.a.a(v0);
                    oVarH.J();
                }
                return true;
            case 5:
                int i2 = message.arg1;
                C2172c c2172c = (C2172c) message.obj;
                for (o oVar4 : this.p0.values()) {
                    if (oVar4.p() == i2) {
                        oVar = oVar4;
                        if (oVar != null) {
                            Log.wtf("GoogleApiManager", "Could not find API instance " + i2 + " while trying to fail enqueued calls.", new Exception());
                        } else if (c2172c.j() == 13) {
                            oVar.e(new Status(17, "Error resolution was canceled by the user, original error message: " + this.l0.e(c2172c.j()) + ": " + c2172c.n()));
                        } else {
                            oVar.e(g(oVar.Y, c2172c));
                        }
                        return true;
                    }
                }
                if (oVar != null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i2 + " while trying to fail enqueued calls.", new Exception());
                } else if (c2172c.j() == 13) {
                    oVar.e(new Status(17, "Error resolution was canceled by the user, original error message: " + this.l0.e(c2172c.j()) + ": " + c2172c.n()));
                } else {
                    oVar.e(g(oVar.Y, c2172c));
                }
                return true;
            case 6:
                if (this.k0.getApplicationContext() instanceof Application) {
                    ComponentCallbacks2C6270a.c((Application) this.k0.getApplicationContext());
                    ComponentCallbacks2C6270a.b().a(new j(this));
                    if (!ComponentCallbacks2C6270a.b().e(true)) {
                        this.q = 300000L;
                    }
                }
                return true;
            case 7:
                h((ax.p233o6.f) message.obj);
                return true;
            case 9:
                if (this.p0.containsKey(message.obj)) {
                    ((o) this.p0.get(message.obj)).I();
                }
                return true;
            case 10:
                Iterator it = this.s0.iterator();
                while (it.hasNext()) {
                    o oVar5 = (o) this.p0.remove((C2204b) it.next());
                    if (oVar5 != null) {
                        oVar5.J();
                    }
                }
                this.s0.clear();
                return true;
            case 11:
                if (this.p0.containsKey(message.obj)) {
                    ((o) this.p0.get(message.obj)).K();
                }
                return true;
            case 12:
                if (this.p0.containsKey(message.obj)) {
                    ((o) this.p0.get(message.obj)).b();
                }
                return true;
            case 14:
                i iVar = (i) message.obj;
                C2204b c2204bA = iVar.a();
                if (this.p0.containsKey(c2204bA)) {
                    iVar.b().c(Boolean.valueOf(((o) this.p0.get(c2204bA)).o(false)));
                } else {
                    iVar.b().c(Boolean.FALSE);
                }
                return true;
            case 15:
                p pVar = (p) message.obj;
                if (this.p0.containsKey(pVar.a)) {
                    o.z((o) this.p0.get(pVar.a), pVar);
                }
                return true;
            case 16:
                p pVar2 = (p) message.obj;
                if (this.p0.containsKey(pVar2.a)) {
                    o.A((o) this.p0.get(pVar2.a), pVar2);
                }
                return true;
            case 17:
                j();
                return true;
            case 18:
                t tVar = (t) message.obj;
                if (tVar.c == 0) {
                    i().a(new C2257t(tVar.b, Arrays.asList(tVar.a)));
                } else {
                    C2257t c2257t = this.Y;
                    if (c2257t != null) {
                        List listN = c2257t.n();
                        if (c2257t.j() != tVar.b || (listN != null && listN.size() >= tVar.d)) {
                            this.t0.removeMessages(17);
                            j();
                        } else {
                            this.Y.z(tVar.a);
                        }
                    }
                    if (this.Y == null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(tVar.a);
                        this.Y = new C2257t(tVar.b, arrayList);
                        Handler handler2 = this.t0;
                        handler2.sendMessageDelayed(handler2.obtainMessage(17), tVar.c);
                    }
                }
                return true;
            case 19:
                this.X = false;
                return true;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + i);
                return false;
        }
    }

    public final int l() {
        return this.n0.getAndIncrement();
    }

    final o t(C2204b c2204b) {
        return (o) this.p0.get(c2204b);
    }
}
