package com.google.firebase.crashlytics.ktx;

import androidx.annotation.Keep;
import ax.p127hc.C2093o;
import ax.p171k8.C2135c;
import ax.p335uc.g;
import com.google.firebase.components.ComponentRegistrar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Keep
public final class FirebaseCrashlyticsKtxRegistrar implements ComponentRegistrar {
    public static final a Companion = new a(null);

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<C2135c<?>> getComponents() {
        return C2093o.j();
    }
}
