package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class s5 implements InterfaceC6294c2 {
    private final /* synthetic */ String a;
    private final /* synthetic */ q5 b;

    s5(q5 q5Var, String str) {
        this.a = str;
        this.b = q5Var;
    }

    @Override // com.google.android.gms.measurement.internal.InterfaceC6294c2
    public final void a(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.b.K(true, i, th, bArr, this.a);
    }
}
