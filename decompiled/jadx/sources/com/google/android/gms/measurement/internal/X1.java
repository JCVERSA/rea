package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
public final class X1 {
    private final int a;
    private final boolean b;
    private final boolean c;
    private final /* synthetic */ V1 d;

    X1(V1 v1, int i, boolean z, boolean z2) {
        this.d = v1;
        this.a = i;
        this.b = z;
        this.c = z2;
    }

    public final void a(String str) {
        this.d.z(this.a, this.b, this.c, str, null, null, null);
    }

    public final void b(String str, Object obj) {
        this.d.z(this.a, this.b, this.c, str, obj, null, null);
    }

    public final void c(String str, Object obj, Object obj2) {
        this.d.z(this.a, this.b, this.c, str, obj, obj2, null);
    }

    public final void d(String str, Object obj, Object obj2, Object obj3) {
        this.d.z(this.a, this.b, this.c, str, obj, obj2, obj3);
    }
}
