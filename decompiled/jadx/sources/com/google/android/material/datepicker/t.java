package com.google.android.material.datepicker;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.icu.text.DateFormat;
import android.icu.text.DisplayContext;
import j$.util.DesugarTimeZone;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class t {
    static AtomicReference<s> a = new AtomicReference<>();

    static long a(long j) {
        Calendar calendarR = r();
        calendarR.setTimeInMillis(j);
        return f(calendarR).getTimeInMillis();
    }

    private static int b(String str, String str2, int i, int i2) {
        while (i2 >= 0 && i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            if (str.charAt(i2) == '\'') {
                do {
                    i2 += i;
                    if (i2 < 0 || i2 >= str.length()) {
                        break;
                    }
                } while (str.charAt(i2) != '\'');
            }
            i2 += i;
        }
        return i2;
    }

    @TargetApi(24)
    static DateFormat c(Locale locale) {
        return d("MMMd", locale);
    }

    @TargetApi(24)
    private static DateFormat d(String str, Locale locale) {
        DateFormat instanceForSkeleton = DateFormat.getInstanceForSkeleton(str, locale);
        instanceForSkeleton.setTimeZone(q());
        instanceForSkeleton.setContext(DisplayContext.CAPITALIZATION_FOR_STANDALONE);
        return instanceForSkeleton;
    }

    static String e(String str) {
        return str.replaceAll("[^dMy/\\-.]", HttpUrl.FRAGMENT_ENCODE_SET).replaceAll("d{1,2}", "dd").replaceAll("M{1,2}", "MM").replaceAll("y{1,4}", "yyyy").replaceAll("\\.$", HttpUrl.FRAGMENT_ENCODE_SET).replaceAll("My", "M/y");
    }

    static Calendar f(Calendar calendar) {
        Calendar calendarS = s(calendar);
        Calendar calendarR = r();
        calendarR.set(calendarS.get(1), calendarS.get(2), calendarS.get(5));
        return calendarR;
    }

    static SimpleDateFormat g() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(e(((SimpleDateFormat) java.text.DateFormat.getDateInstance(3, Locale.getDefault())).toPattern()), Locale.getDefault());
        simpleDateFormat.setTimeZone(o());
        simpleDateFormat.setLenient(false);
        return simpleDateFormat;
    }

    static String h(Resources resources, SimpleDateFormat simpleDateFormat) {
        String pattern = simpleDateFormat.toPattern();
        String string = resources.getString(ax.R6.k.J);
        String string2 = resources.getString(ax.R6.k.I);
        String string3 = resources.getString(ax.R6.k.H);
        if (Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage())) {
            pattern = pattern.replaceAll("d+", "d").replaceAll("M+", "M").replaceAll("y+", "y");
        }
        return pattern.replace("d", string3).replace("M", string2).replace("y", string);
    }

    private static java.text.DateFormat i(int i, Locale locale) {
        java.text.DateFormat dateInstance = java.text.DateFormat.getDateInstance(i, locale);
        dateInstance.setTimeZone(o());
        return dateInstance;
    }

    static java.text.DateFormat j(Locale locale) {
        return i(0, locale);
    }

    static java.text.DateFormat k(Locale locale) {
        return i(2, locale);
    }

    static java.text.DateFormat l(Locale locale) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) k(locale);
        simpleDateFormat.applyPattern(w(simpleDateFormat.toPattern()));
        return simpleDateFormat;
    }

    @TargetApi(24)
    static DateFormat m(Locale locale) {
        return d("MMMMEEEEd", locale);
    }

    static s n() {
        s sVar = a.get();
        return sVar == null ? s.c() : sVar;
    }

    private static TimeZone o() {
        return DesugarTimeZone.getTimeZone("UTC");
    }

    static Calendar p() {
        Calendar calendarA = n().a();
        calendarA.set(11, 0);
        calendarA.set(12, 0);
        calendarA.set(13, 0);
        calendarA.set(14, 0);
        calendarA.setTimeZone(o());
        return calendarA;
    }

    @TargetApi(24)
    private static android.icu.util.TimeZone q() {
        return android.icu.util.TimeZone.getTimeZone("UTC");
    }

    static Calendar r() {
        return s(null);
    }

    static Calendar s(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance(o());
        if (calendar == null) {
            calendar2.clear();
            return calendar2;
        }
        calendar2.setTimeInMillis(calendar.getTimeInMillis());
        return calendar2;
    }

    @TargetApi(24)
    static DateFormat t(Locale locale) {
        return d("yMMMd", locale);
    }

    @TargetApi(24)
    static DateFormat u(Locale locale) {
        return d("yMMMM", locale);
    }

    @TargetApi(24)
    static DateFormat v(Locale locale) {
        return d("yMMMMEEEEd", locale);
    }

    private static String w(String str) {
        int iB = b(str, "yY", 1, 0);
        if (iB >= str.length()) {
            return str;
        }
        String str2 = "EMd";
        int iB2 = b(str, "EMd", 1, iB);
        if (iB2 < str.length()) {
            str2 = "EMd,";
        }
        return str.replace(str.substring(b(str, str2, -1, iB) + 1, iB2), " ").trim();
    }
}
