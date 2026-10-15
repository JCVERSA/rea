package com.google.firebase.installations;

import androidx.annotation.Keep;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.r;
import ax.p187l8.k;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.installations.FirebaseInstallationsRegistrar;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-installations";

    public static /* synthetic */ ax.I8.e a(InterfaceC2137e interfaceC2137e) {
        return new c((ax.p075e8.f) interfaceC2137e.a(ax.p075e8.f.class), interfaceC2137e.b(ax.G8.i.class), (ExecutorService) interfaceC2137e.e(F.a(ax.p155j8.a.class, ExecutorService.class)), k.b((Executor) interfaceC2137e.e(F.a(ax.p155j8.b.class, Executor.class))));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.e(ax.I8.e.class).g(LIBRARY_NAME).b(r.j(ax.p075e8.f.class)).b(r.h(ax.G8.i.class)).b(r.i(F.a(ax.p155j8.a.class, ExecutorService.class))).b(r.i(F.a(ax.p155j8.b.class, Executor.class))).e(new ax.p171k8.h() { // from class: ax.I8.f
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return FirebaseInstallationsRegistrar.a(interfaceC2137e);
            }
        }).c(), ax.G8.h.a(), ax.O8.h.b(LIBRARY_NAME, "18.0.0"));
    }
}
