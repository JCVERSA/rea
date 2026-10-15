package com.google.firebase.installations;

import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
final class a extends g {
    private final String a;
    private final long b;
    private final long c;

    static final class b extends g.a {
        private String a;
        private Long b;
        private Long c;

        b() {
        }

        @Override // com.google.firebase.installations.g.a
        public g a() {
            String str = this.a;
            String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
            if (str == null) {
                str2 = HttpUrl.FRAGMENT_ENCODE_SET + " token";
            }
            if (this.b == null) {
                str2 = str2 + " tokenExpirationTimestamp";
            }
            if (this.c == null) {
                str2 = str2 + " tokenCreationTimestamp";
            }
            if (str2.isEmpty()) {
                return new a(this.a, this.b.longValue(), this.c.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str2);
        }

        @Override // com.google.firebase.installations.g.a
        public g.a b(String str) {
            if (str == null) {
                throw new NullPointerException("Null token");
            }
            this.a = str;
            return this;
        }

        @Override // com.google.firebase.installations.g.a
        public g.a c(long j) {
            this.c = Long.valueOf(j);
            return this;
        }

        @Override // com.google.firebase.installations.g.a
        public g.a d(long j) {
            this.b = Long.valueOf(j);
            return this;
        }
    }

    private a(String str, long j, long j2) {
        this.a = str;
        this.b = j;
        this.c = j2;
    }

    @Override // com.google.firebase.installations.g
    public String b() {
        return this.a;
    }

    @Override // com.google.firebase.installations.g
    public long c() {
        return this.c;
    }

    @Override // com.google.firebase.installations.g
    public long d() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof g) {
            g gVar = (g) obj;
            if (this.a.equals(gVar.b()) && this.b == gVar.d() && this.c == gVar.c()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = (this.a.hashCode() ^ 1000003) * 1000003;
        long j = this.b;
        long j2 = this.c;
        return ((iHashCode ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    public String toString() {
        return "InstallationTokenResult{token=" + this.a + ", tokenExpirationTimestamp=" + this.b + ", tokenCreationTimestamp=" + this.c + "}";
    }
}
