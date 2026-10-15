package com.google.firebase.installations;

import com.google.auto.value.AutoValue;

/* JADX INFO: loaded from: classes3.dex */
@AutoValue
public abstract class g {

    @AutoValue.Builder
    public static abstract class a {
        public abstract g a();

        public abstract a b(String str);

        public abstract a c(long j);

        public abstract a d(long j);
    }

    public static a a() {
        return new com.google.firebase.installations.a.b();
    }

    public abstract String b();

    public abstract long c();

    public abstract long d();
}
