package com.google.firebase.crashlytics;

import android.content.Context;
import android.content.pm.PackageManager;
import ax.I8.e;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1469b;
import ax.Q6.m;
import ax.p219n8.d;
import ax.p219n8.g;
import ax.p219n8.l;
import ax.p267q8.C;
import ax.p267q8.C2264a;
import ax.p267q8.C2269f;
import ax.p267q8.C2272i;
import ax.p267q8.C2276m;
import ax.p267q8.C2286x;
import ax.p267q8.C2288z;
import ax.p267q8.r;
import ax.p379x8.f;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    final r a;

    /* JADX INFO: renamed from: com.google.firebase.crashlytics.a$a, reason: collision with other inner class name */
    class C0582a implements InterfaceC1469b<Void, Object> {
        C0582a() {
        }

        @Override // ax.Q6.InterfaceC1469b
        public Object a(AbstractC1477j<Void> abstractC1477j) throws Exception {
            if (abstractC1477j.n()) {
                return null;
            }
            g.f().e("Error fetching settings.", abstractC1477j.j());
            return null;
        }
    }

    class b implements Callable<Void> {
        final /* synthetic */ boolean a;
        final /* synthetic */ r b;
        final /* synthetic */ f c;

        b(boolean z, r rVar, f fVar) {
            this.a = z;
            this.b = rVar;
            this.c = fVar;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void call() throws Exception {
            if (!this.a) {
                return null;
            }
            this.b.g(this.c);
            return null;
        }
    }

    private a(r rVar) {
        this.a = rVar;
    }

    public static a a() {
        a aVar = (a) ax.p075e8.f.l().j(a.class);
        if (aVar != null) {
            return aVar;
        }
        throw new NullPointerException("FirebaseCrashlytics component is not present.");
    }

    static a b(ax.p075e8.f fVar, e eVar, ax.H8.a<ax.p219n8.a> aVar, ax.H8.a<ax.p123h8.a> aVar2, ax.H8.a<ax.S8.a> aVar3) {
        Context contextK = fVar.k();
        String packageName = contextK.getPackageName();
        g.f().g("Initializing Firebase Crashlytics " + r.i() + " for " + packageName);
        ax.p347v8.g gVar = new ax.p347v8.g(contextK);
        C2286x c2286x = new C2286x(fVar);
        C c = new C(contextK, packageName, eVar, c2286x);
        d dVar = new d(aVar);
        ax.p203m8.d dVar2 = new ax.p203m8.d(aVar2);
        ExecutorService executorServiceC = C2288z.c("Crashlytics Exception Handler");
        C2276m c2276m = new C2276m(c2286x, gVar);
        ax.V8.a.e(c2276m);
        r rVar = new r(fVar, c, dVar, c2286x, dVar2.e(), dVar2.d(), gVar, executorServiceC, c2276m, new l(aVar3));
        String strC = fVar.n().c();
        String strM = C2272i.m(contextK);
        List<C2269f> listJ = C2272i.j(contextK);
        g.f().b("Mapping file ID is: " + strM);
        for (C2269f c2269f : listJ) {
            g.f().b(String.format("Build id for %s on %s: %s", c2269f.c(), c2269f.a(), c2269f.b()));
        }
        try {
            C2264a c2264aA = C2264a.a(contextK, c, strC, strM, listJ, new ax.p219n8.f(contextK));
            g.f().i("Installer package name is: " + c2264aA.d);
            ExecutorService executorServiceC2 = C2288z.c("com.google.firebase.crashlytics.startup");
            f fVarL = f.l(contextK, strC, c, new ax.p331u8.b(), c2264aA.f, c2264aA.g, gVar, c2286x);
            fVarL.p(executorServiceC2).g(executorServiceC2, new C0582a());
            m.c(executorServiceC2, new b(rVar.o(c2264aA, fVarL), rVar, fVarL));
            return new a(rVar);
        } catch (PackageManager.NameNotFoundException e) {
            g.f().e("Error retrieving app package info.", e);
            return null;
        }
    }

    public void c(Throwable th) {
        if (th == null) {
            g.f().k("A null value was passed to recordException. Ignoring.");
        } else {
            this.a.l(th);
        }
    }

    public void d(boolean z) {
        this.a.p(Boolean.valueOf(z));
    }

    public void e(String str, String str2) {
        this.a.q(str, str2);
    }
}
