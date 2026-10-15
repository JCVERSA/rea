package com.google.firebase.ktx;

import androidx.annotation.Keep;
import ax.O8.h;
import ax.p127hc.C2093o;
import ax.p171k8.C2135c;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public final class FirebaseCommonLegacyRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return C2093o.d(h.b("fire-core-ktx", "21.0.0"));
    }
}
