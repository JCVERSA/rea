package com.google.android.gms.measurement.internal;

import java.util.EnumMap;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6333i {
    private final EnumMap<C6330h3.a, EnumC6347k> a;

    C6333i() {
        this.a = new EnumMap<>(C6330h3.a.class);
    }

    private C6333i(EnumMap<C6330h3.a, EnumC6347k> enumMap) {
        EnumMap<C6330h3.a, EnumC6347k> enumMap2 = new EnumMap<>(C6330h3.a.class);
        this.a = enumMap2;
        enumMap2.putAll(enumMap);
    }

    public static C6333i a(String str) {
        EnumMap enumMap = new EnumMap(C6330h3.a.class);
        if (str.length() >= C6330h3.a.values().length) {
            int i = 0;
            if (str.charAt(0) == '1') {
                C6330h3.a[] aVarArrValues = C6330h3.a.values();
                int length = aVarArrValues.length;
                int i2 = 1;
                while (i < length) {
                    enumMap.put(aVarArrValues[i], EnumC6347k.h(str.charAt(i2)));
                    i++;
                    i2++;
                }
                return new C6333i(enumMap);
            }
        }
        return new C6333i();
    }

    public final EnumC6347k b(C6330h3.a aVar) {
        EnumC6347k enumC6347k = this.a.get(aVar);
        return enumC6347k == null ? EnumC6347k.UNSET : enumC6347k;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x001b  */
    public final void c(C6330h3.a aVar, int i) {
        EnumC6347k enumC6347k = EnumC6347k.UNSET;
        if (i == -30) {
            enumC6347k = EnumC6347k.TCF;
        } else if (i == -20) {
            enumC6347k = EnumC6347k.API;
        } else if (i == -10) {
            enumC6347k = EnumC6347k.MANIFEST;
        } else if (i == 0) {
            enumC6347k = EnumC6347k.API;
        } else if (i == 30) {
            enumC6347k = EnumC6347k.INITIALIZATION;
        }
        this.a.put(aVar, enumC6347k);
    }

    public final void d(C6330h3.a aVar, EnumC6347k enumC6347k) {
        this.a.put(aVar, enumC6347k);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("1");
        for (C6330h3.a aVar : C6330h3.a.values()) {
            EnumC6347k enumC6347k = this.a.get(aVar);
            if (enumC6347k == null) {
                enumC6347k = EnumC6347k.UNSET;
            }
            sb.append(enumC6347k.q);
        }
        return sb.toString();
    }
}
