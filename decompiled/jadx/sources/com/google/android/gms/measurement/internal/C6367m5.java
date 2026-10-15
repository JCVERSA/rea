package com.google.android.gms.measurement.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.m5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6367m5 {
    private String a;
    private Map<String, String> b;
    private int c;

    C6367m5(String str, int i) {
        this.a = str;
        this.c = i;
    }

    /* JADX WARN: Incorrect types in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/Integer;)V */
    C6367m5(String str, Map map, int i) {
        this.a = str;
        this.b = map;
        this.c = i;
    }

    public final int a() {
        return this.c;
    }

    public final String b() {
        return this.a;
    }

    public final Map<String, String> c() {
        return this.b;
    }
}
