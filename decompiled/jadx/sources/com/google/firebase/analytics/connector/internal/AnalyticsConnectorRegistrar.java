package com.google.firebase.analytics.connector.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.Keep;
import ax.F8.d;
import ax.p075e8.f;
import ax.p123h8.b;
import ax.p171k8.C2135c;
import ax.p171k8.InterfaceC2137e;
import ax.p171k8.h;
import ax.p171k8.r;
import com.google.firebase.components.ComponentRegistrar;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public class AnalyticsConnectorRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    @Keep
    @SuppressLint({"MissingPermission"})
    public List<C2135c<?>> getComponents() {
        return Arrays.asList(C2135c.e(ax.p123h8.a.class).b(r.j(f.class)).b(r.j(Context.class)).b(r.j(d.class)).e(new h() { // from class: com.google.firebase.analytics.connector.internal.a
            @Override // ax.p171k8.h
            public final Object a(InterfaceC2137e interfaceC2137e) {
                return b.g((f) interfaceC2137e.a(f.class), (Context) interfaceC2137e.a(Context.class), (d) interfaceC2137e.a(d.class));
            }
        }).d().c(), ax.O8.h.b("fire-analytics", "22.0.2"));
    }
}
