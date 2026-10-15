package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import java.util.Date;

/* JADX INFO: loaded from: classes3.dex */
public class e {
    static final Date e = new Date(-1);
    static final Date f = new Date(-1);
    private final SharedPreferences a;
    private final Object b = new Object();
    private final Object c = new Object();
    private final Object d = new Object();

    static class a {
        private int a;
        private Date b;

        a(int i, Date date) {
            this.a = i;
            this.b = date;
        }

        Date a() {
            return this.b;
        }

        int b() {
            return this.a;
        }
    }

    public static class b {
        private int a;
        private Date b;

        public b(int i, Date date) {
            this.a = i;
            this.b = date;
        }

        Date a() {
            return this.b;
        }

        int b() {
            return this.a;
        }
    }

    public e(SharedPreferences sharedPreferences) {
        this.a = sharedPreferences;
    }

    a a() {
        a aVar;
        synchronized (this.c) {
            aVar = new a(this.a.getInt("num_failed_fetches", 0), new Date(this.a.getLong("backoff_end_time_in_millis", -1L)));
        }
        return aVar;
    }

    public long b() {
        return this.a.getLong("fetch_timeout_in_seconds", 60L);
    }

    String c() {
        return this.a.getString("last_fetch_etag", null);
    }

    Date d() {
        return new Date(this.a.getLong("last_fetch_time_in_millis", -1L));
    }

    long e() {
        return this.a.getLong("last_template_version", 0L);
    }

    public long f() {
        return this.a.getLong("minimum_fetch_interval_in_seconds", d.j);
    }

    public b g() {
        b bVar;
        synchronized (this.d) {
            bVar = new b(this.a.getInt("num_failed_realtime_streams", 0), new Date(this.a.getLong("realtime_backoff_end_time_in_millis", -1L)));
        }
        return bVar;
    }

    void h() {
        j(0, f);
    }

    void i() {
        m(0, f);
    }

    void j(int i, Date date) {
        synchronized (this.c) {
            this.a.edit().putInt("num_failed_fetches", i).putLong("backoff_end_time_in_millis", date.getTime()).apply();
        }
    }

    void k(String str) {
        synchronized (this.b) {
            this.a.edit().putString("last_fetch_etag", str).apply();
        }
    }

    void l(long j) {
        synchronized (this.b) {
            this.a.edit().putLong("last_template_version", j).apply();
        }
    }

    void m(int i, Date date) {
        synchronized (this.d) {
            this.a.edit().putInt("num_failed_realtime_streams", i).putLong("realtime_backoff_end_time_in_millis", date.getTime()).apply();
        }
    }

    void n() {
        synchronized (this.b) {
            this.a.edit().putInt("last_fetch_status", 1).apply();
        }
    }

    void o(Date date) {
        synchronized (this.b) {
            this.a.edit().putInt("last_fetch_status", -1).putLong("last_fetch_time_in_millis", date.getTime()).apply();
        }
    }

    void p() {
        synchronized (this.b) {
            this.a.edit().putInt("last_fetch_status", 2).apply();
        }
    }
}
