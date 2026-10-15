package com.google.firebase.crashlytics;

import ax.I8.e;
import ax.V8.b;
import ax.p075e8.f;
import ax.p171k8.C2135c;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import com.google.firebase.components.ComponentRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class CrashlyticsRegistrar implements ComponentRegistrar {
    static {
        ax.V8.a.a(b.a.CRASHLYTICS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public a b(InterfaceC2137e interfaceC2137e) {
        return a.b((f) interfaceC2137e.a(f.class), (e) interfaceC2137e.a(e.class), interfaceC2137e.i(ax.p219n8.a.class), interfaceC2137e.i(ax.p123h8.a.class), interfaceC2137e.i(ax.S8.a.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.e(a.class).g("fire-cls").b(r.j(f.class)).b(r.j(e.class)).b(r.a(ax.p219n8.a.class)).b(r.a(ax.p123h8.a.class)).b(r.a(ax.S8.a.class)).e(new h() { // from class: ax.m8.f
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return this.a.b(interfaceC2137e);
            }
        }).d().c(), ax.O8.h.b("fire-cls", "19.0.3"));
    }
}
