package com.google.firebase.sessions;

import ax.Cc.h;
import ax.U8.J;
import ax.U8.z;
import ax.p075e8.m;
import ax.p335uc.g;
import ax.p335uc.j;
import ax.p335uc.l;
import java.util.Locale;
import java.util.UUID;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public final class c {
    public static final b f = new b(null);
    private final J a;
    private final ax.p319tc.a<UUID> b;
    private final String c;
    private int d;
    private z e;

    /* synthetic */ class a extends j implements ax.p319tc.a<UUID> {
        public static final a p0 = new a();

        a() {
            super(0, UUID.class, "randomUUID", "randomUUID()Ljava/util/UUID;", 0);
        }

        @Override // ax.p319tc.a
        /* JADX INFO: renamed from: k, reason: merged with bridge method [inline-methods] */
        public final UUID invoke() {
            return UUID.randomUUID();
        }
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(g gVar) {
            this();
        }

        public final c a() {
            Object objJ = m.a(ax.p075e8.c.a).j(c.class);
            l.e(objJ, "Firebase.app[SessionGenerator::class.java]");
            return (c) objJ;
        }
    }

    public c(J j, ax.p319tc.a<UUID> aVar) {
        l.f(j, "timeProvider");
        l.f(aVar, "uuidGenerator");
        this.a = j;
        this.b = aVar;
        this.c = b();
        this.d = -1;
    }

    public /* synthetic */ c(J j, ax.p319tc.a aVar, int i, g gVar) {
        this(j, (i & 2) != 0 ? a.p0 : aVar);
    }

    private final String b() {
        String string = this.b.invoke().toString();
        l.e(string, "uuidGenerator().toString()");
        String lowerCase = h.D(string, "-", HttpUrl.FRAGMENT_ENCODE_SET, false, 4, null).toLowerCase(Locale.ROOT);
        l.e(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        return lowerCase;
    }

    public final z a() {
        int i = this.d + 1;
        this.d = i;
        this.e = new z(i == 0 ? this.c : b(), this.c, this.d, this.a.a());
        return c();
    }

    public final z c() {
        z zVar = this.e;
        if (zVar != null) {
            return zVar;
        }
        l.t("currentSession");
        return null;
    }
}
