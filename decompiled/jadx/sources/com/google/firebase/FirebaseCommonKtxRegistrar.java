package com.google.firebase;

import androidx.annotation.Keep;
import ax.Ec.C0800k0;
import ax.Ec.G;
import ax.p127hc.C2093o;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import ax.p335uc.l;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public final class FirebaseCommonKtxRegistrar implements ComponentRegistrar {

    public static final class a<T> implements h {
        public static final a<T> a = new a<>();

        @Override // ax.p171k8.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final G a(InterfaceC2137e interfaceC2137e) {
            Object objE = interfaceC2137e.e(F.a(ax.p155j8.a.class, Executor.class));
            l.e(objE, "c.get(Qualified.qualifie…a, Executor::class.java))");
            return C0800k0.a((Executor) objE);
        }
    }

    public static final class b<T> implements h {
        public static final b<T> a = new b<>();

        @Override // ax.p171k8.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final G a(InterfaceC2137e interfaceC2137e) {
            Object objE = interfaceC2137e.e(F.a(ax.p155j8.c.class, Executor.class));
            l.e(objE, "c.get(Qualified.qualifie…a, Executor::class.java))");
            return C0800k0.a((Executor) objE);
        }
    }

    public static final class c<T> implements h {
        public static final c<T> a = new c<>();

        @Override // ax.p171k8.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final G a(InterfaceC2137e interfaceC2137e) {
            Object objE = interfaceC2137e.e(F.a(ax.p155j8.b.class, Executor.class));
            l.e(objE, "c.get(Qualified.qualifie…a, Executor::class.java))");
            return C0800k0.a((Executor) objE);
        }
    }

    public static final class d<T> implements h {
        public static final d<T> a = new d<>();

        @Override // ax.p171k8.h
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public final G a(InterfaceC2137e interfaceC2137e) {
            Object objE = interfaceC2137e.e(F.a(ax.p155j8.d.class, Executor.class));
            l.e(objE, "c.get(Qualified.qualifie…a, Executor::class.java))");
            return C0800k0.a((Executor) objE);
        }
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        C2135c c2135cC = C2135c.c(F.a(ax.p155j8.a.class, G.class)).b(r.i(F.a(ax.p155j8.a.class, Executor.class))).e(a.a).c();
        l.e(c2135cC, "builder(Qualified.qualif…cher()\n    }\n    .build()");
        C2135c c2135cC2 = C2135c.c(F.a(ax.p155j8.c.class, G.class)).b(r.i(F.a(ax.p155j8.c.class, Executor.class))).e(b.a).c();
        l.e(c2135cC2, "builder(Qualified.qualif…cher()\n    }\n    .build()");
        C2135c c2135cC3 = C2135c.c(F.a(ax.p155j8.b.class, G.class)).b(r.i(F.a(ax.p155j8.b.class, Executor.class))).e(c.a).c();
        l.e(c2135cC3, "builder(Qualified.qualif…cher()\n    }\n    .build()");
        C2135c c2135cC4 = C2135c.c(F.a(ax.p155j8.d.class, G.class)).b(r.i(F.a(ax.p155j8.d.class, Executor.class))).e(d.a).c();
        l.e(c2135cC4, "builder(Qualified.qualif…cher()\n    }\n    .build()");
        return C2093o.l(c2135cC, c2135cC2, c2135cC3, c2135cC4);
    }
}
