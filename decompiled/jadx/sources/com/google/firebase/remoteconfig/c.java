package com.google.firebase.remoteconfig;

import android.app.Application;
import android.content.Context;
import ax.I8.e;
import ax.Q6.m;
import ax.Q8.k;
import ax.Q8.o;
import ax.p084f1.C1978i;
import ax.p345v6.f;
import ax.p345v6.i;
import com.google.android.gms.common.api.internal.ComponentCallbacks2C6270a;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.d;
import com.google.firebase.remoteconfig.internal.h;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
public class c implements ax.S8.a {
    private static final f j = i.c();
    private static final Random k = new Random();
    private static final Map<String, com.google.firebase.remoteconfig.a> l = new HashMap();
    private final Map<String, com.google.firebase.remoteconfig.a> a;
    private final Context b;
    private final ScheduledExecutorService c;
    private final ax.p075e8.f d;
    private final e e;
    private final ax.p091f8.c f;
    private final ax.H8.b<ax.p123h8.a> g;
    private final String h;
    private Map<String, String> i;

    private static class a implements ComponentCallbacks2C6270a.InterfaceC0566a {
        private static final AtomicReference<a> a = new AtomicReference<>();

        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void c(Context context) {
            Application application = (Application) context.getApplicationContext();
            AtomicReference<a> atomicReference = a;
            if (atomicReference.get() == null) {
                a aVar = new a();
                if (C1978i.a(atomicReference, null, aVar)) {
                    ComponentCallbacks2C6270a.c(application);
                    ComponentCallbacks2C6270a.b().a(aVar);
                }
            }
        }

        @Override // com.google.android.gms.common.api.internal.ComponentCallbacks2C6270a.InterfaceC0566a
        public void a(boolean z) {
            c.q(z);
        }
    }

    c(Context context, @ax.p155j8.b ScheduledExecutorService scheduledExecutorService, ax.p075e8.f fVar, e eVar, ax.p091f8.c cVar, ax.H8.b<ax.p123h8.a> bVar) {
        this(context, scheduledExecutorService, fVar, eVar, cVar, bVar, true);
    }

    protected c(Context context, ScheduledExecutorService scheduledExecutorService, ax.p075e8.f fVar, e eVar, ax.p091f8.c cVar, ax.H8.b<ax.p123h8.a> bVar, boolean z) {
        this.a = new HashMap();
        this.i = new HashMap();
        this.b = context;
        this.c = scheduledExecutorService;
        this.d = fVar;
        this.e = eVar;
        this.f = cVar;
        this.g = bVar;
        this.h = fVar.n().c();
        a.c(context);
        if (z) {
            m.c(scheduledExecutorService, new Callable() { // from class: com.google.firebase.remoteconfig.b
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.a.g();
                }
            });
        }
    }

    public static /* synthetic */ ax.p123h8.a b() {
        return null;
    }

    private com.google.firebase.remoteconfig.internal.b f(String str, String str2) {
        return com.google.firebase.remoteconfig.internal.b.h(this.c, h.c(this.b, String.format("%s_%s_%s_%s.json", "frc", this.h, str, str2)));
    }

    private k j(com.google.firebase.remoteconfig.internal.b bVar, com.google.firebase.remoteconfig.internal.b bVar2) {
        return new k(this.c, bVar, bVar2);
    }

    static com.google.firebase.remoteconfig.internal.e k(Context context, String str, String str2) {
        return new com.google.firebase.remoteconfig.internal.e(context.getSharedPreferences(String.format("%s_%s_%s_%s", "frc", str, str2, "settings"), 0));
    }

    private static o l(ax.p075e8.f fVar, String str, ax.H8.b<ax.p123h8.a> bVar) {
        if (p(fVar) && str.equals("firebase")) {
            return new o(bVar);
        }
        return null;
    }

    private ax.R8.e n(com.google.firebase.remoteconfig.internal.b bVar, com.google.firebase.remoteconfig.internal.b bVar2) {
        return new ax.R8.e(bVar, ax.R8.a.a(bVar, bVar2), this.c);
    }

    private static boolean o(ax.p075e8.f fVar, String str) {
        return str.equals("firebase") && p(fVar);
    }

    private static boolean p(ax.p075e8.f fVar) {
        return fVar.m().equals("[DEFAULT]");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void q(boolean z) {
        Iterator<com.google.firebase.remoteconfig.a> it = l.values().iterator();
        while (it.hasNext()) {
            it.next().n(z);
        }
    }

    @Override // ax.S8.a
    public void a(String str, ax.T8.f fVar) {
        e(str).j().e(fVar);
    }

    synchronized com.google.firebase.remoteconfig.a d(ax.p075e8.f fVar, String str, e eVar, ax.p091f8.c cVar, Executor executor, com.google.firebase.remoteconfig.internal.b bVar, com.google.firebase.remoteconfig.internal.b bVar2, com.google.firebase.remoteconfig.internal.b bVar3, d dVar, k kVar, com.google.firebase.remoteconfig.internal.e eVar2, ax.R8.e eVar3) throws Throwable {
        c cVar2;
        String str2;
        try {
            try {
                if (this.a.containsKey(str)) {
                    cVar2 = this;
                    str2 = str;
                } else {
                    cVar2 = this;
                    str2 = str;
                    com.google.firebase.remoteconfig.a aVar = new com.google.firebase.remoteconfig.a(this.b, fVar, eVar, o(fVar, str) ? cVar : null, executor, bVar, bVar2, bVar3, dVar, kVar, eVar2, m(fVar, eVar, dVar, bVar2, this.b, str, eVar2), eVar3);
                    aVar.o();
                    cVar2.a.put(str2, aVar);
                    l.put(str2, aVar);
                }
                return cVar2.a.get(str2);
            } catch (Throwable th) {
                th = th;
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    public synchronized com.google.firebase.remoteconfig.a e(String str) throws Throwable {
        Throwable th;
        try {
            try {
                com.google.firebase.remoteconfig.internal.b bVarF = f(str, "fetch");
                com.google.firebase.remoteconfig.internal.b bVarF2 = f(str, "activate");
                com.google.firebase.remoteconfig.internal.b bVarF3 = f(str, "defaults");
                com.google.firebase.remoteconfig.internal.e eVarK = k(this.b, this.h, str);
                k kVarJ = j(bVarF2, bVarF3);
                final o oVarL = l(this.d, str, this.g);
                if (oVarL != null) {
                    try {
                        kVarJ.b(new ax.p345v6.d() { // from class: ax.P8.m
                            @Override // ax.p345v6.d
                            public final void a(Object obj, Object obj2) {
                                oVarL.a((String) obj, (com.google.firebase.remoteconfig.internal.c) obj2);
                            }
                        });
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                return d(this.d, str, this.e, this.f, this.c, bVarF, bVarF2, bVarF3, h(str, bVarF, eVarK), kVarJ, eVarK, n(bVarF2, bVarF3));
            } catch (Throwable th3) {
                th = th3;
                th = th;
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    com.google.firebase.remoteconfig.a g() {
        return e("firebase");
    }

    synchronized d h(String str, com.google.firebase.remoteconfig.internal.b bVar, com.google.firebase.remoteconfig.internal.e eVar) {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return new d(this.e, p(this.d) ? this.g : new ax.H8.b() { // from class: ax.P8.n
            @Override // ax.H8.b
            public final Object get() {
                return com.google.firebase.remoteconfig.c.b();
            }
        }, this.c, j, k, bVar, i(this.d.n().b(), str, eVar), eVar, this.i);
    }

    ConfigFetchHttpClient i(String str, String str2, com.google.firebase.remoteconfig.internal.e eVar) {
        return new ConfigFetchHttpClient(this.b, this.d.n().c(), str, str2, eVar.b(), eVar.b());
    }

    synchronized com.google.firebase.remoteconfig.internal.f m(ax.p075e8.f fVar, e eVar, d dVar, com.google.firebase.remoteconfig.internal.b bVar, Context context, String str, com.google.firebase.remoteconfig.internal.e eVar2) {
        return new com.google.firebase.remoteconfig.internal.f(fVar, eVar, dVar, bVar, context, str, eVar2, this.c);
    }
}
