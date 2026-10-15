package com.android.billingclient.api;

import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.android.billingclient.api.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C6254d {
    private int a;
    private int b;
    private String c;

    /* JADX INFO: renamed from: com.android.billingclient.api.d$a */
    public static class a {
        private int a;
        private int b = 0;
        private String c = HttpUrl.FRAGMENT_ENCODE_SET;

        /* synthetic */ a(ax.D3.m mVar) {
        }

        public C6254d a() {
            C6254d c6254d = new C6254d();
            c6254d.a = this.a;
            c6254d.b = this.b;
            c6254d.c = this.c;
            return c6254d;
        }

        public a b(String str) {
            this.c = str;
            return this;
        }

        public a c(int i) {
            this.b = i;
            return this;
        }

        public a d(int i) {
            this.a = i;
            return this;
        }
    }

    public static a d() {
        return new a(null);
    }

    public String a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.a;
    }

    public String toString() {
        return "Response Code: " + ax.I6.Q.i(this.a) + ", Debug Message: " + this.c;
    }
}
