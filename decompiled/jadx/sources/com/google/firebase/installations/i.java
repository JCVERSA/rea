package com.google.firebase.installations;

import android.text.TextUtils;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class i {
    public static final long b = TimeUnit.HOURS.toSeconds(1);
    private static final Pattern c = Pattern.compile("\\AA[\\w-]{38}\\z");
    private static i d;
    private final ax.M8.a a;

    private i(ax.M8.a aVar) {
        this.a = aVar;
    }

    public static i c() {
        return d(ax.M8.b.b());
    }

    public static i d(ax.M8.a aVar) {
        if (d == null) {
            d = new i(aVar);
        }
        return d;
    }

    static boolean g(String str) {
        return c.matcher(str).matches();
    }

    static boolean h(String str) {
        return str.contains(":");
    }

    public long a() {
        return this.a.a();
    }

    public long b() {
        return TimeUnit.MILLISECONDS.toSeconds(a());
    }

    public long e() {
        return (long) (Math.random() * 1000.0d);
    }

    public boolean f(ax.K8.d dVar) {
        return TextUtils.isEmpty(dVar.b()) || dVar.h() + dVar.c() < b() + b;
    }
}
