package com.google.firebase.abt.component;

import android.content.Context;
import androidx.annotation.Keep;
import ax.p171k8.C2135c;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import com.google.firebase.abt.component.AbtRegistrar;
import com.google.firebase.components.ComponentRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class AbtRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-abt";

    public static /* synthetic */ a a(InterfaceC2137e interfaceC2137e) {
        return new a((Context) interfaceC2137e.a(Context.class), interfaceC2137e.b(ax.p123h8.a.class));
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.e(a.class).g(LIBRARY_NAME).b(r.j(Context.class)).b(r.h(ax.p123h8.a.class)).e(new h() { // from class: ax.g8.a
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return AbtRegistrar.a(interfaceC2137e);
            }
        }).c(), ax.O8.h.b(LIBRARY_NAME, "21.1.1"));
    }
}
