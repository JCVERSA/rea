package com.google.firebase.remoteconfig.internal;

import android.text.format.DateUtils;
import ax.P8.i;
import ax.P8.j;
import ax.P8.k;
import ax.Q6.AbstractC1477j;
import ax.Q6.InterfaceC1469b;
import ax.Q6.InterfaceC1476i;
import ax.Q6.m;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class d {
    public static final long j = TimeUnit.HOURS.toSeconds(12);
    static final int[] k = {2, 4, 8, 16, 32, 64, 128, 256};
    private final ax.I8.e a;
    private final ax.H8.b<ax.p123h8.a> b;
    private final Executor c;
    private final ax.p345v6.f d;
    private final Random e;
    private final com.google.firebase.remoteconfig.internal.b f;
    private final ConfigFetchHttpClient g;
    private final e h;
    private final Map<String, String> i;

    public static class a {
        private final Date a;
        private final int b;
        private final c c;
        private final String d;

        private a(Date date, int i, c cVar, String str) {
            this.a = date;
            this.b = i;
            this.c = cVar;
            this.d = str;
        }

        public static a a(Date date, c cVar) {
            return new a(date, 1, cVar, null);
        }

        public static a b(c cVar, String str) {
            return new a(cVar.h(), 0, cVar, str);
        }

        public static a c(Date date) {
            return new a(date, 2, null, null);
        }

        public c d() {
            return this.c;
        }

        String e() {
            return this.d;
        }

        int f() {
            return this.b;
        }
    }

    public enum b {
        BASE("BASE"),
        REALTIME("REALTIME");

        private final String q;

        b(String str) {
            this.q = str;
        }

        String h() {
            return this.q;
        }
    }

    public d(ax.I8.e eVar, ax.H8.b<ax.p123h8.a> bVar, Executor executor, ax.p345v6.f fVar, Random random, com.google.firebase.remoteconfig.internal.b bVar2, ConfigFetchHttpClient configFetchHttpClient, e eVar2, Map<String, String> map) {
        this.a = eVar;
        this.b = bVar;
        this.c = executor;
        this.d = fVar;
        this.e = random;
        this.f = bVar2;
        this.g = configFetchHttpClient;
        this.h = eVar2;
        this.i = map;
    }

    public static /* synthetic */ AbstractC1477j a(d dVar, AbstractC1477j abstractC1477j, AbstractC1477j abstractC1477j2, Date date, Map map, AbstractC1477j abstractC1477j3) {
        dVar.getClass();
        if (abstractC1477j.n()) {
            return !abstractC1477j2.n() ? m.d(new ax.P8.h("Firebase Installations failed to get installation auth token for fetch.", abstractC1477j2.j())) : dVar.l((String) abstractC1477j.k(), ((com.google.firebase.installations.g) abstractC1477j2.k()).b(), date, map);
        }
        return m.d(new ax.P8.h("Firebase Installations failed to get installation ID for fetch.", abstractC1477j.j()));
    }

    public static /* synthetic */ AbstractC1477j c(d dVar, Date date, AbstractC1477j abstractC1477j) {
        dVar.x(abstractC1477j, date);
        return abstractC1477j;
    }

    private boolean f(long j2, Date date) {
        Date dateD = this.h.d();
        if (dateD.equals(e.e)) {
            return false;
        }
        return date.before(new Date(dateD.getTime() + TimeUnit.SECONDS.toMillis(j2)));
    }

    private k g(k kVar) throws ax.P8.h {
        String str;
        int iA = kVar.a();
        if (iA == 401) {
            str = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
        } else if (iA == 403) {
            str = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
        } else {
            if (iA == 429) {
                throw new ax.P8.h("The throttled response from the server was not handled correctly by the FRC SDK.");
            }
            if (iA != 500) {
                switch (iA) {
                    case 502:
                    case 503:
                    case 504:
                        str = "The server is unavailable. Please try again later.";
                        break;
                    default:
                        str = "The server returned an unexpected error.";
                        break;
                }
            } else {
                str = "There was an internal server error.";
            }
        }
        return new k(kVar.a(), "Fetch failed: " + str, kVar);
    }

    private String h(long j2) {
        return String.format("Fetch is throttled. Please wait before calling fetch again: %s", DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(j2)));
    }

    private a k(String str, String str2, Date date, Map<String, String> map) throws i {
        Date date2;
        try {
            date2 = date;
            try {
                a aVarFetch = this.g.fetch(this.g.d(), str, str2, s(), this.h.c(), map, p(), date2);
                if (aVarFetch.d() != null) {
                    this.h.l(aVarFetch.d().k());
                }
                if (aVarFetch.e() != null) {
                    this.h.k(aVarFetch.e());
                }
                this.h.h();
                return aVarFetch;
            } catch (k e) {
                e = e;
                k kVar = e;
                e.a aVarV = v(kVar.a(), date2);
                if (u(aVarV, kVar.a())) {
                    throw new j(aVarV.a().getTime());
                }
                throw g(kVar);
            }
        } catch (k e2) {
            e = e2;
            date2 = date;
        }
    }

    private AbstractC1477j<a> l(String str, String str2, Date date, Map<String, String> map) {
        try {
            final a aVarK = k(str, str2, date, map);
            return aVarK.f() != 0 ? m.e(aVarK) : this.f.i(aVarK.d()).p(this.c, new InterfaceC1476i() { // from class: ax.Q8.i
                @Override // ax.Q6.InterfaceC1476i
                public final AbstractC1477j a(Object obj) {
                    return ax.Q6.m.e(aVarK);
                }
            });
        } catch (i e) {
            return m.d(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AbstractC1477j<a> m(AbstractC1477j<c> abstractC1477j, long j2, final Map<String, String> map) {
        final d dVar;
        AbstractC1477j abstractC1477jI;
        final Date date = new Date(this.d.a());
        if (abstractC1477j.n() && f(j2, date)) {
            return m.e(a.c(date));
        }
        Date dateO = o(date);
        if (dateO != null) {
            abstractC1477jI = m.d(new j(h(dateO.getTime() - date.getTime()), dateO.getTime()));
            dVar = this;
        } else {
            final AbstractC1477j<String> abstractC1477jA = this.a.a();
            final AbstractC1477j<com.google.firebase.installations.g> abstractC1477jB = this.a.b(false);
            dVar = this;
            abstractC1477jI = m.j(abstractC1477jA, abstractC1477jB).i(this.c, new InterfaceC1469b() { // from class: ax.Q8.f
                @Override // ax.Q6.InterfaceC1469b
                public final Object a(AbstractC1477j abstractC1477j2) {
                    return com.google.firebase.remoteconfig.internal.d.a(this.a, abstractC1477jA, abstractC1477jB, date, map, abstractC1477j2);
                }
            });
        }
        return abstractC1477jI.i(dVar.c, new InterfaceC1469b() { // from class: ax.Q8.g
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j2) {
                return com.google.firebase.remoteconfig.internal.d.c(this.a, date, abstractC1477j2);
            }
        });
    }

    private Date o(Date date) {
        Date dateA = this.h.a().a();
        if (date.before(dateA)) {
            return dateA;
        }
        return null;
    }

    private Long p() {
        ax.p123h8.a aVar = this.b.get();
        if (aVar == null) {
            return null;
        }
        return (Long) aVar.c(true).get("_fot");
    }

    private long q(int i) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        int[] iArr = k;
        long millis = timeUnit.toMillis(iArr[Math.min(i, iArr.length) - 1]);
        return (millis / 2) + ((long) this.e.nextInt((int) millis));
    }

    private Map<String, String> s() {
        HashMap map = new HashMap();
        ax.p123h8.a aVar = this.b.get();
        if (aVar != null) {
            for (Map.Entry<String, Object> entry : aVar.c(false).entrySet()) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return map;
    }

    private boolean t(int i) {
        return i == 429 || i == 502 || i == 503 || i == 504;
    }

    private boolean u(e.a aVar, int i) {
        return aVar.b() > 1 || i == 429;
    }

    private e.a v(int i, Date date) {
        if (t(i)) {
            w(date);
        }
        return this.h.a();
    }

    private void w(Date date) {
        int iB = this.h.a().b() + 1;
        this.h.j(iB, new Date(date.getTime() + q(iB)));
    }

    private void x(AbstractC1477j<a> abstractC1477j, Date date) {
        if (abstractC1477j.n()) {
            this.h.o(date);
            return;
        }
        Exception excJ = abstractC1477j.j();
        if (excJ == null) {
            return;
        }
        if (excJ instanceof j) {
            this.h.p();
        } else {
            this.h.n();
        }
    }

    public AbstractC1477j<a> i() {
        return j(this.h.f());
    }

    public AbstractC1477j<a> j(final long j2) {
        final HashMap map = new HashMap(this.i);
        map.put("X-Firebase-RC-Fetch-Type", b.BASE.h() + "/1");
        return this.f.e().i(this.c, new InterfaceC1469b() { // from class: ax.Q8.e
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j) {
                return this.a.m(abstractC1477j, j2, map);
            }
        });
    }

    public AbstractC1477j<a> n(b bVar, int i) {
        final HashMap map = new HashMap(this.i);
        map.put("X-Firebase-RC-Fetch-Type", bVar.h() + "/" + i);
        return this.f.e().i(this.c, new InterfaceC1469b() { // from class: ax.Q8.h
            @Override // ax.Q6.InterfaceC1469b
            public final Object a(AbstractC1477j abstractC1477j) {
                return this.a.m(abstractC1477j, 0L, map);
            }
        });
    }

    public long r() {
        return this.h.e();
    }
}
