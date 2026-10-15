package com.google.android.gms.measurement.internal;

import ax.H6.E6;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.y2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6436y2 implements E6 {
    private final /* synthetic */ String a;
    private final /* synthetic */ C6389q2 b;

    C6436y2(C6389q2 c6389q2, String str) {
        this.a = str;
        this.b = c6389q2;
    }

    @Override // ax.H6.E6
    public final String o(String str) {
        Map map = (Map) this.b.d.get(this.a);
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return (String) map.get(str);
    }
}
