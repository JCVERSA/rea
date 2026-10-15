package com.android.billingclient.api;

import java.util.HashSet;
import java.util.List;

/* JADX INFO: renamed from: com.android.billingclient.api.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C6257g {
    private final ax.I6.J a;

    /* JADX INFO: renamed from: com.android.billingclient.api.g$a */
    public static class a {
        private ax.I6.J a;

        /* synthetic */ a(ax.D3.r rVar) {
        }

        public C6257g a() {
            if (this.a != null) {
                return new C6257g(this, null);
            }
            throw new IllegalArgumentException("Product list must be set to a non empty list.");
        }

        public a b(List<b> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Product list cannot be empty.");
            }
            HashSet hashSet = new HashSet();
            for (b bVar : list) {
                if (!"play_pass_subs".equals(bVar.c())) {
                    hashSet.add(bVar.c());
                }
            }
            if (hashSet.size() > 1) {
                throw new IllegalArgumentException("All products should be of the same product type.");
            }
            this.a = ax.I6.J.q(list);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.billingclient.api.g$b */
    public static class b {
        private final String a;
        private final String b;

        /* JADX INFO: renamed from: com.android.billingclient.api.g$b$a */
        public static class a {
            private String a;
            private String b;

            /* synthetic */ a(ax.D3.r rVar) {
            }

            public b a() {
                if ("first_party".equals(this.b)) {
                    throw new IllegalArgumentException("Serialized doc id must be provided for first party products.");
                }
                if (this.a == null) {
                    throw new IllegalArgumentException("Product id must be provided.");
                }
                if (this.b != null) {
                    return new b(this, null);
                }
                throw new IllegalArgumentException("Product type must be provided.");
            }

            public a b(String str) {
                this.a = str;
                return this;
            }

            public a c(String str) {
                this.b = str;
                return this;
            }
        }

        /* synthetic */ b(a aVar, ax.D3.r rVar) {
            this.a = aVar.a;
            this.b = aVar.b;
        }

        public static a a() {
            return new a(null);
        }

        public final String b() {
            return this.a;
        }

        public final String c() {
            return this.b;
        }
    }

    /* synthetic */ C6257g(a aVar, ax.D3.r rVar) {
        this.a = aVar.a;
    }

    public static a a() {
        return new a(null);
    }

    public final ax.I6.J b() {
        return this.a;
    }

    public final String c() {
        return ((b) this.a.get(0)).c();
    }
}
