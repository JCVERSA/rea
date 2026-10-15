package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
class g {
    static ax.p019b0.c<String, String> a(Long l, Long l2) {
        return b(l, l2, null);
    }

    static ax.p019b0.c<String, String> b(Long l, Long l2, SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return ax.p019b0.c.a(null, null);
        }
        if (l == null) {
            return ax.p019b0.c.a(null, d(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return ax.p019b0.c.a(d(l.longValue(), simpleDateFormat), null);
        }
        Calendar calendarP = t.p();
        Calendar calendarR = t.r();
        calendarR.setTimeInMillis(l.longValue());
        Calendar calendarR2 = t.r();
        calendarR2.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return ax.p019b0.c.a(simpleDateFormat.format(new Date(l.longValue())), simpleDateFormat.format(new Date(l2.longValue())));
        }
        if (calendarR.get(1) == calendarR2.get(1)) {
            return calendarR.get(1) == calendarP.get(1) ? ax.p019b0.c.a(g(l.longValue(), Locale.getDefault()), g(l2.longValue(), Locale.getDefault())) : ax.p019b0.c.a(g(l.longValue(), Locale.getDefault()), n(l2.longValue(), Locale.getDefault()));
        }
        return ax.p019b0.c.a(n(l.longValue(), Locale.getDefault()), n(l2.longValue(), Locale.getDefault()));
    }

    static String c(long j) {
        return d(j, null);
    }

    static String d(long j, SimpleDateFormat simpleDateFormat) {
        if (simpleDateFormat != null) {
            return simpleDateFormat.format(new Date(j));
        }
        return q(j) ? f(j) : m(j);
    }

    static String e(Context context, long j, boolean z, boolean z2, boolean z3) {
        String strJ = j(j);
        if (z) {
            strJ = String.format(context.getString(ax.R6.k.K), strJ);
        }
        if (z2) {
            return String.format(context.getString(ax.R6.k.G), strJ);
        }
        return z3 ? String.format(context.getString(ax.R6.k.t), strJ) : strJ;
    }

    static String f(long j) {
        return g(j, Locale.getDefault());
    }

    static String g(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? t.c(locale).format(new Date(j)) : t.l(locale).format(new Date(j));
    }

    static String h(long j) {
        return i(j, Locale.getDefault());
    }

    static String i(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? t.m(locale).format(new Date(j)) : t.j(locale).format(new Date(j));
    }

    static String j(long j) {
        return q(j) ? h(j) : o(j);
    }

    static String k(Context context, int i) {
        return t.p().get(1) == i ? String.format(context.getString(ax.R6.k.y), Integer.valueOf(i)) : String.format(context.getString(ax.R6.k.z), Integer.valueOf(i));
    }

    static String l(long j) {
        return Build.VERSION.SDK_INT >= 24 ? t.u(Locale.getDefault()).format(new Date(j)) : DateUtils.formatDateTime(null, j, 8228);
    }

    static String m(long j) {
        return n(j, Locale.getDefault());
    }

    static String n(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? t.t(locale).format(new Date(j)) : t.k(locale).format(new Date(j));
    }

    static String o(long j) {
        return p(j, Locale.getDefault());
    }

    static String p(long j, Locale locale) {
        return Build.VERSION.SDK_INT >= 24 ? t.v(locale).format(new Date(j)) : t.j(locale).format(new Date(j));
    }

    private static boolean q(long j) {
        Calendar calendarP = t.p();
        Calendar calendarR = t.r();
        calendarR.setTimeInMillis(j);
        return calendarP.get(1) == calendarR.get(1);
    }
}
