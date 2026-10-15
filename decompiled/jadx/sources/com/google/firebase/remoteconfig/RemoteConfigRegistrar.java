package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.Keep;
import ax.I8.e;
import ax.p075e8.f;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.remoteconfig.RemoteConfigRegistrar;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class RemoteConfigRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-rc";

    public static /* synthetic */ c a(F f, InterfaceC2137e interfaceC2137e) {
        return new c((Context) interfaceC2137e.a(Context.class), (ScheduledExecutorService) interfaceC2137e.e(f), (f) interfaceC2137e.a(f.class), (e) interfaceC2137e.a(e.class), ((com.google.firebase.abt.component.a) interfaceC2137e.a(com.google.firebase.abt.component.a.class)).b("frc"), interfaceC2137e.b(ax.p123h8.a.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        final F fA = F.a(ax.p155j8.b.class, ScheduledExecutorService.class);
        return Arrays.asList(C2135c.f(c.class, ax.S8.a.class).g(LIBRARY_NAME).b(r.j(Context.class)).b(r.i(fA)).b(r.j(f.class)).b(r.j(e.class)).b(r.j(com.google.firebase.abt.component.a.class)).b(r.h(ax.p123h8.a.class)).e(new h() { // from class: ax.P8.o
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return RemoteConfigRegistrar.a(fA, interfaceC2137e);
            }
        }).d().c(), ax.O8.h.b(LIBRARY_NAME, "22.0.0"));
    }
}
