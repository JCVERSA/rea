package com.android.billingclient.api;

/* JADX INFO: renamed from: com.android.billingclient.api.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C6255e {
    private final boolean a;
    private final boolean b;

    /* JADX INFO: renamed from: com.android.billingclient.api.e$a */
    public static final class a {
        private boolean a;
        private boolean b;

        private a() {
        }

        public C6255e a() {
            if (!this.a) {
                throw new IllegalArgumentException("Pending purchases for one-time products must be supported.");
            }
            return new C6255e(true, this.b);
        }

        public a b() {
            this.a = true;
            return this;
        }
    }

    private C6255e(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
    }

    public static a c() {
        return new a();
    }

    boolean a() {
        return this.a;
    }

    boolean b() {
        return this.b;
    }
}
