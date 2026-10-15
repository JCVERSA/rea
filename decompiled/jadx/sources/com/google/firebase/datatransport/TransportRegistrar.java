package com.google.firebase.datatransport;

import android.content.Context;
import androidx.annotation.Keep;
import ax.p171k8.C2135c;
import ax.p171k8.F;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import ax.p312t5.i;
import ax.p344v5.u;
import ax.p411z8.b;
import com.google.android.datatransport.cct.a;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.datatransport.TransportRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    public static /* synthetic */ i a(InterfaceC2137e interfaceC2137e) {
        u.f((Context) interfaceC2137e.a(Context.class));
        return u.c().g(a.g);
    }

    public static /* synthetic */ i b(InterfaceC2137e interfaceC2137e) {
        u.f((Context) interfaceC2137e.a(Context.class));
        return u.c().g(a.h);
    }

    public static /* synthetic */ i c(InterfaceC2137e interfaceC2137e) {
        u.f((Context) interfaceC2137e.a(Context.class));
        return u.c().g(a.h);
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.e(i.class).g(LIBRARY_NAME).b(r.j(Context.class)).e(new h() { // from class: ax.z8.c
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return TransportRegistrar.c(interfaceC2137e);
            }
        }).c(), C2135c.c(F.a(ax.p411z8.a.class, i.class)).b(r.j(Context.class)).e(new h() { // from class: ax.z8.d
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return TransportRegistrar.b(interfaceC2137e);
            }
        }).c(), C2135c.c(F.a(b.class, i.class)).b(r.j(Context.class)).e(new h() { // from class: ax.z8.e
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return TransportRegistrar.a(interfaceC2137e);
            }
        }).c(), ax.O8.h.b(LIBRARY_NAME, "19.0.0"));
    }
}
