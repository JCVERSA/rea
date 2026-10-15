package com.google.firebase.sessions;

import android.content.Context;
import androidx.annotation.Keep;
import ax.Ec.G;
import ax.I8.e;
import ax.U8.C;
import ax.U8.C1588h;
import ax.U8.H;
import ax.U8.K;
import ax.U8.y;
import ax.p075e8.f;
import ax.p127hc.C2093o;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import ax.p312t5.i;
import ax.p335uc.g;
import ax.p335uc.l;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.sessions.FirebaseSessionsRegistrar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public final class FirebaseSessionsRegistrar implements ComponentRegistrar {
    private static final a Companion = new a(null);
    private static final String LIBRARY_NAME = "fire-sessions";
    private static final F<G> backgroundDispatcher;
    private static final F<G> blockingDispatcher;
    private static final F<f> firebaseApp;
    private static final F<e> firebaseInstallationsApi;
    private static final F<ax.U8.G> sessionLifecycleServiceBinder;
    private static final F<ax.W8.f> sessionsSettings;
    private static final F<i> transportFactory;

    private static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    static {
        F<f> fB = F.b(f.class);
        l.e(fB, "unqualified(FirebaseApp::class.java)");
        firebaseApp = fB;
        F<e> fB2 = F.b(e.class);
        l.e(fB2, "unqualified(FirebaseInstallationsApi::class.java)");
        firebaseInstallationsApi = fB2;
        F<G> fA = F.a(ax.p155j8.a.class, G.class);
        l.e(fA, "qualified(Background::cl…neDispatcher::class.java)");
        backgroundDispatcher = fA;
        F<G> fA2 = F.a(ax.p155j8.b.class, G.class);
        l.e(fA2, "qualified(Blocking::clas…neDispatcher::class.java)");
        blockingDispatcher = fA2;
        F<i> fB3 = F.b(i.class);
        l.e(fB3, "unqualified(TransportFactory::class.java)");
        transportFactory = fB3;
        F<ax.W8.f> fB4 = F.b(ax.W8.f.class);
        l.e(fB4, "unqualified(SessionsSettings::class.java)");
        sessionsSettings = fB4;
        F<ax.U8.G> fB5 = F.b(ax.U8.G.class);
        l.e(fB5, "unqualified(SessionLifec…erviceBinder::class.java)");
        sessionLifecycleServiceBinder = fB5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ax.U8.l getComponents$lambda$0(InterfaceC2137e interfaceC2137e) {
        Object objE = interfaceC2137e.e(firebaseApp);
        l.e(objE, "container[firebaseApp]");
        Object objE2 = interfaceC2137e.e(sessionsSettings);
        l.e(objE2, "container[sessionsSettings]");
        Object objE3 = interfaceC2137e.e(backgroundDispatcher);
        l.e(objE3, "container[backgroundDispatcher]");
        Object objE4 = interfaceC2137e.e(sessionLifecycleServiceBinder);
        l.e(objE4, "container[sessionLifecycleServiceBinder]");
        return new ax.U8.l((f) objE, (ax.W8.f) objE2, (ax.p175kc.i) objE3, (ax.U8.G) objE4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final c getComponents$lambda$1(InterfaceC2137e interfaceC2137e) {
        return new c(K.a, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final b getComponents$lambda$2(InterfaceC2137e interfaceC2137e) {
        Object objE = interfaceC2137e.e(firebaseApp);
        l.e(objE, "container[firebaseApp]");
        Object objE2 = interfaceC2137e.e(firebaseInstallationsApi);
        l.e(objE2, "container[firebaseInstallationsApi]");
        Object objE3 = interfaceC2137e.e(sessionsSettings);
        l.e(objE3, "container[sessionsSettings]");
        ax.H8.b bVarD = interfaceC2137e.d(transportFactory);
        l.e(bVarD, "container.getProvider(transportFactory)");
        C1588h c1588h = new C1588h(bVarD);
        Object objE4 = interfaceC2137e.e(backgroundDispatcher);
        l.e(objE4, "container[backgroundDispatcher]");
        return new C((f) objE, (e) objE2, (ax.W8.f) objE3, c1588h, (ax.p175kc.i) objE4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ax.W8.f getComponents$lambda$3(InterfaceC2137e interfaceC2137e) {
        Object objE = interfaceC2137e.e(firebaseApp);
        l.e(objE, "container[firebaseApp]");
        Object objE2 = interfaceC2137e.e(blockingDispatcher);
        l.e(objE2, "container[blockingDispatcher]");
        Object objE3 = interfaceC2137e.e(backgroundDispatcher);
        l.e(objE3, "container[backgroundDispatcher]");
        Object objE4 = interfaceC2137e.e(firebaseInstallationsApi);
        l.e(objE4, "container[firebaseInstallationsApi]");
        return new ax.W8.f((f) objE, (ax.p175kc.i) objE2, (ax.p175kc.i) objE3, (e) objE4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final com.google.firebase.sessions.a getComponents$lambda$4(InterfaceC2137e interfaceC2137e) {
        Context contextK = ((f) interfaceC2137e.e(firebaseApp)).k();
        l.e(contextK, "container[firebaseApp].applicationContext");
        Object objE = interfaceC2137e.e(backgroundDispatcher);
        l.e(objE, "container[backgroundDispatcher]");
        return new y(contextK, (ax.p175kc.i) objE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ax.U8.G getComponents$lambda$5(InterfaceC2137e interfaceC2137e) {
        Object objE = interfaceC2137e.e(firebaseApp);
        l.e(objE, "container[firebaseApp]");
        return new H((f) objE);
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<? extends Object>> getComponents() {
        C2135c.b bVarG = C2135c.e(ax.U8.l.class).g(LIBRARY_NAME);
        F<f> f = firebaseApp;
        C2135c.b bVarB = bVarG.b(r.i(f));
        F<ax.W8.f> f2 = sessionsSettings;
        C2135c.b bVarB2 = bVarB.b(r.i(f2));
        F<G> f3 = backgroundDispatcher;
        C2135c c2135cC = bVarB2.b(r.i(f3)).b(r.i(sessionLifecycleServiceBinder)).e(new h() { // from class: ax.U8.n
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$0(interfaceC2137e);
            }
        }).d().c();
        C2135c c2135cC2 = C2135c.e(c.class).g("session-generator").e(new h() { // from class: ax.U8.o
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$1(interfaceC2137e);
            }
        }).c();
        C2135c.b bVarB3 = C2135c.e(b.class).g("session-publisher").b(r.i(f));
        F<e> f4 = firebaseInstallationsApi;
        return C2093o.l(c2135cC, c2135cC2, bVarB3.b(r.i(f4)).b(r.i(f2)).b(r.k(transportFactory)).b(r.i(f3)).e(new h() { // from class: ax.U8.p
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$2(interfaceC2137e);
            }
        }).c(), C2135c.e(ax.W8.f.class).g("sessions-settings").b(r.i(f)).b(r.i(blockingDispatcher)).b(r.i(f3)).b(r.i(f4)).e(new h() { // from class: ax.U8.q
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$3(interfaceC2137e);
            }
        }).c(), C2135c.e(com.google.firebase.sessions.a.class).g("sessions-datastore").b(r.i(f)).b(r.i(f3)).e(new h() { // from class: ax.U8.r
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$4(interfaceC2137e);
            }
        }).c(), C2135c.e(ax.U8.G.class).g("sessions-service-binder").b(r.i(f)).e(new h() { // from class: ax.U8.s
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseSessionsRegistrar.getComponents$lambda$5(interfaceC2137e);
            }
        }).c(), ax.O8.h.b(LIBRARY_NAME, "2.0.3"));
    }
}
