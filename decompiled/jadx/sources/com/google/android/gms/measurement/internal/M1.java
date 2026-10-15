package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
public final class M1<V> {
    private static final Object g = new Object();
    private final String a;
    private final K1<V> b;
    private final V c;
    private final Object d;
    private volatile V e;
    private volatile V f;

    private M1(String str, V v, V v2, K1<V> k1, boolean z) {
        this.d = new Object();
        this.e = null;
        this.f = null;
        this.a = str;
        this.c = v;
        this.b = k1;
    }

    public final V a(V v) {
        synchronized (this.d) {
        }
        if (v != null) {
            return v;
        }
        if (N1.a == null) {
            return this.c;
        }
        synchronized (g) {
            try {
                if (C6291c.a()) {
                    return this.f == null ? this.c : this.f;
                }
                try {
                    for (M1 m1 : F.a) {
                        if (C6291c.a()) {
                            throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                        }
                        V vA = null;
                        try {
                            K1<V> k1 = m1.b;
                            if (k1 != null) {
                                vA = k1.a();
                            }
                        } catch (IllegalStateException unused) {
                        }
                        synchronized (g) {
                            m1.f = vA;
                        }
                    }
                } catch (SecurityException unused2) {
                }
                K1<V> k2 = this.b;
                if (k2 == null) {
                    return this.c;
                }
                try {
                    return k2.a();
                } catch (IllegalStateException unused3) {
                    return this.c;
                } catch (SecurityException unused4) {
                    return this.c;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final String b() {
        return this.a;
    }
}
