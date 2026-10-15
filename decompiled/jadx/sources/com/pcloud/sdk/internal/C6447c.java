package com.pcloud.sdk.internal;

import java.io.IOException;
import java.util.Map;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: renamed from: com.pcloud.sdk.internal.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
class C6447c implements Interceptor {
    private final String a;
    private final String b;

    C6447c(String str, Map<String, String> map) {
        this.a = str;
        this.b = a(map);
    }

    private static String a(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append("; ");
        }
        sb.append("Domain=api.pcloud.com; Path=/; Secure; HttpOnly");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.b.equals(((C6447c) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("User-Agent", this.a).addHeader("Cookie", this.b).build());
    }
}
