package com.google.firebase.installations;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import ax.Q6.AbstractC1477j;
import ax.Q6.C1478k;
import ax.Q6.m;
import ax.p171k8.x;
import ax.p265q6.C2254p;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public class c implements ax.I8.e {
    private static final Object m = new Object();
    private static final ThreadFactory n = new a();
    private final ax.p075e8.f a;
    private final ax.L8.c b;
    private final ax.K8.c c;
    private final i d;
    private final x<ax.K8.b> e;
    private final ax.I8.g f;
    private final Object g;
    private final ExecutorService h;
    private final Executor i;
    private String j;
    private Set<ax.J8.a> k;
    private final List<h> l;

    class a implements ThreadFactory {
        private final AtomicInteger a = new AtomicInteger(1);

        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        @SuppressLint({"ThreadPoolCreation"})
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, String.format("firebase-installations-executor-%d", Integer.valueOf(this.a.getAndIncrement())));
        }
    }

    static /* synthetic */ class b {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[ax.L8.f.b.values().length];
            b = iArr;
            try {
                iArr[ax.L8.f.b.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[ax.L8.f.b.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                b[ax.L8.f.b.AUTH_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[ax.L8.d.b.values().length];
            a = iArr2;
            try {
                iArr2[ax.L8.d.b.OK.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[ax.L8.d.b.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @SuppressLint({"ThreadPoolCreation"})
    c(final ax.p075e8.f fVar, ax.H8.b<ax.G8.i> bVar, ExecutorService executorService, Executor executor) {
        this(executorService, executor, fVar, new ax.L8.c(fVar.k(), bVar), new ax.K8.c(fVar), i.c(), new x(new ax.H8.b() { // from class: ax.I8.a
            @Override // ax.H8.b
            public final Object get() {
                return com.google.firebase.installations.c.f(fVar);
            }
        }), new ax.I8.g());
    }

    @SuppressLint({"ThreadPoolCreation"})
    c(ExecutorService executorService, Executor executor, ax.p075e8.f fVar, ax.L8.c cVar, ax.K8.c cVar2, i iVar, x<ax.K8.b> xVar, ax.I8.g gVar) {
        this.g = new Object();
        this.k = new HashSet();
        this.l = new ArrayList();
        this.a = fVar;
        this.b = cVar;
        this.c = cVar2;
        this.d = iVar;
        this.e = xVar;
        this.f = gVar;
        this.h = executorService;
        this.i = executor;
    }

    private void A(ax.K8.d dVar) {
        synchronized (this.g) {
            try {
                Iterator<h> it = this.l.iterator();
                while (it.hasNext()) {
                    if (it.next().b(dVar)) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private synchronized void B(String str) {
        this.j = str;
    }

    private synchronized void C(ax.K8.d dVar, ax.K8.d dVar2) {
        if (this.k.size() != 0 && !TextUtils.equals(dVar.d(), dVar2.d())) {
            Iterator<ax.J8.a> it = this.k.iterator();
            while (it.hasNext()) {
                it.next().a(dVar2.d());
            }
        }
    }

    public static /* synthetic */ ax.K8.b f(ax.p075e8.f fVar) {
        return new ax.K8.b(fVar);
    }

    private AbstractC1477j<g> g() {
        C1478k c1478k = new C1478k();
        i(new e(this.d, c1478k));
        return c1478k.a();
    }

    private AbstractC1477j<String> h() {
        C1478k c1478k = new C1478k();
        i(new f(c1478k));
        return c1478k.a();
    }

    private void i(h hVar) {
        synchronized (this.g) {
            this.l.add(hVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(boolean z) {
        ax.K8.d dVarY;
        ax.K8.d dVarS = s();
        try {
            if (dVarS.i() || dVarS.l()) {
                dVarY = y(dVarS);
            } else {
                if (!z && !this.d.f(dVarS)) {
                    return;
                }
                dVarY = l(dVarS);
            }
            v(dVarY);
            C(dVarS, dVarY);
            if (dVarY.k()) {
                B(dVarY.d());
            }
            if (dVarY.i()) {
                z(new d(d.a.BAD_CONFIG));
            } else if (dVarY.j()) {
                z(new IOException("Installation ID could not be validated with the Firebase servers (maybe it was deleted). Firebase Installations will need to create a new Installation ID and auth token. Please retry your last request."));
            } else {
                A(dVarY);
            }
        } catch (d e) {
            z(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void k(final boolean z) {
        ax.K8.d dVarT = t();
        if (z) {
            dVarT = dVarT.p();
        }
        A(dVarT);
        this.i.execute(new Runnable() { // from class: ax.I8.d
            @Override // java.lang.Runnable
            public final void run() {
                this.q.j(z);
            }
        });
    }

    private ax.K8.d l(ax.K8.d dVar) throws d {
        ax.L8.f fVarE = this.b.e(m(), dVar.d(), u(), dVar.f());
        int i = b.b[fVarE.b().ordinal()];
        if (i == 1) {
            return dVar.o(fVarE.c(), fVarE.d(), this.d.b());
        }
        if (i == 2) {
            return dVar.q("BAD CONFIG");
        }
        if (i != 3) {
            throw new d("Firebase Installations Service is unavailable. Please try again later.", d.a.UNAVAILABLE);
        }
        B(null);
        return dVar.r();
    }

    private synchronized String o() {
        return this.j;
    }

    private ax.K8.b p() {
        return this.e.get();
    }

    public static c q() {
        return r(ax.p075e8.f.l());
    }

    public static c r(ax.p075e8.f fVar) {
        C2254p.b(fVar != null, "Null is not a valid value of FirebaseApp.");
        return (c) fVar.j(ax.I8.e.class);
    }

    private ax.K8.d s() {
        ax.K8.d dVarD;
        synchronized (m) {
            try {
                com.google.firebase.installations.b bVarA = com.google.firebase.installations.b.a(this.a.k(), "generatefid.lock");
                try {
                    dVarD = this.c.d();
                    if (bVarA != null) {
                        bVarA.b();
                    }
                } catch (Throwable th) {
                    if (bVarA != null) {
                        bVarA.b();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return dVarD;
    }

    private ax.K8.d t() {
        ax.K8.d dVarD;
        synchronized (m) {
            try {
                com.google.firebase.installations.b bVarA = com.google.firebase.installations.b.a(this.a.k(), "generatefid.lock");
                try {
                    dVarD = this.c.d();
                    if (dVarD.j()) {
                        dVarD = this.c.b(dVarD.t(x(dVarD)));
                    }
                    if (bVarA != null) {
                        bVarA.b();
                    }
                } catch (Throwable th) {
                    if (bVarA != null) {
                        bVarA.b();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        return dVarD;
    }

    private void v(ax.K8.d dVar) {
        synchronized (m) {
            try {
                com.google.firebase.installations.b bVarA = com.google.firebase.installations.b.a(this.a.k(), "generatefid.lock");
                try {
                    this.c.b(dVar);
                    if (bVarA != null) {
                        bVarA.b();
                    }
                } catch (Throwable th) {
                    if (bVarA != null) {
                        bVarA.b();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private void w() {
        C2254p.g(n(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        C2254p.g(u(), "Please set your Project ID. A valid Firebase Project ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        C2254p.g(m(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
        C2254p.b(i.h(n()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        C2254p.b(i.g(m()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    private String x(ax.K8.d dVar) {
        if ((!this.a.m().equals("CHIME_ANDROID_SDK") && !this.a.u()) || !dVar.m()) {
            return this.f.a();
        }
        String strF = p().f();
        return TextUtils.isEmpty(strF) ? this.f.a() : strF;
    }

    private ax.K8.d y(ax.K8.d dVar) throws d {
        ax.L8.d dVarD = this.b.d(m(), dVar.d(), u(), n(), (dVar.d() == null || dVar.d().length() != 11) ? null : p().i());
        int i = b.a[dVarD.e().ordinal()];
        if (i == 1) {
            return dVar.s(dVarD.c(), dVarD.d(), this.d.b(), dVarD.b().c(), dVarD.b().d());
        }
        if (i == 2) {
            return dVar.q("BAD CONFIG");
        }
        throw new d("Firebase Installations Service is unavailable. Please try again later.", d.a.UNAVAILABLE);
    }

    private void z(Exception exc) {
        synchronized (this.g) {
            try {
                Iterator<h> it = this.l.iterator();
                while (it.hasNext()) {
                    if (it.next().a(exc)) {
                        it.remove();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // ax.I8.e
    public AbstractC1477j<String> a() {
        w();
        String strO = o();
        if (strO != null) {
            return m.e(strO);
        }
        AbstractC1477j<String> abstractC1477jH = h();
        this.h.execute(new Runnable() { // from class: ax.I8.b
            @Override // java.lang.Runnable
            public final void run() {
                this.q.k(false);
            }
        });
        return abstractC1477jH;
    }

    @Override // ax.I8.e
    public AbstractC1477j<g> b(final boolean z) {
        w();
        AbstractC1477j<g> abstractC1477jG = g();
        this.h.execute(new Runnable() { // from class: ax.I8.c
            @Override // java.lang.Runnable
            public final void run() {
                this.q.k(z);
            }
        });
        return abstractC1477jG;
    }

    String m() {
        return this.a.n().b();
    }

    String n() {
        return this.a.n().c();
    }

    String u() {
        return this.a.n().e();
    }
}
