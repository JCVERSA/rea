package com.box.androidsdk.content.utils;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DateFormatSymbols;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes.dex */
public class FastDateFormat extends Format {
    private static final Map Y = new HashMap(7);
    private static final Map Z = new HashMap(7);
    private static final Map k0 = new HashMap(7);
    private static final Map l0 = new HashMap(7);
    private static final Map m0 = new HashMap(7);
    private static final long serialVersionUID = 1;
    private transient int X;
    private final Locale mLocale;
    private final boolean mLocaleForced;
    private final String mPattern;
    private final TimeZone mTimeZone;
    private final boolean mTimeZoneForced;
    private transient d[] q;

    private static class a implements d {
        private final char a;

        a(char c) {
            this.a = c;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 1;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.a);
        }
    }

    private interface b extends d {
        void c(StringBuffer stringBuffer, int i);
    }

    private static class c implements b {
        private final int a;
        private final int b;

        c(int i, int i2) {
            if (i2 < 3) {
                throw new IllegalArgumentException();
            }
            this.a = i;
            this.b = i2;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 4;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(this.a));
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            int length;
            if (i < 100) {
                int i2 = this.b;
                while (true) {
                    i2--;
                    if (i2 < 2) {
                        stringBuffer.append((char) ((i / 10) + 48));
                        stringBuffer.append((char) ((i % 10) + 48));
                        return;
                    }
                    stringBuffer.append('0');
                }
            } else {
                if (i < 1000) {
                    length = 3;
                } else {
                    if (i <= -1) {
                        throw new IllegalArgumentException("Negative values should not be possible" + i);
                    }
                    length = Integer.toString(i).length();
                }
                int i3 = this.b;
                while (true) {
                    i3--;
                    if (i3 < length) {
                        stringBuffer.append(Integer.toString(i));
                        return;
                    }
                    stringBuffer.append('0');
                }
            }
        }
    }

    private interface d {
        int a();

        void b(StringBuffer stringBuffer, Calendar calendar);
    }

    private static class e implements d {
        private final String a;

        e(String str) {
            this.a = str;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return this.a.length();
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.a);
        }
    }

    private static class f implements d {
        private final int a;
        private final String[] b;

        f(int i, String[] strArr) {
            this.a = i;
            this.b = strArr;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            int length = this.b.length;
            int i = 0;
            while (true) {
                length--;
                if (length < 0) {
                    return i;
                }
                int length2 = this.b[length].length();
                if (length2 > i) {
                    i = length2;
                }
            }
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            stringBuffer.append(this.b[calendar.get(this.a)]);
        }
    }

    private static class g {
        private final TimeZone a;
        private final int b;
        private final Locale c;

        g(TimeZone timeZone, boolean z, int i, Locale locale) {
            this.a = timeZone;
            this.b = z ? i | Integer.MIN_VALUE : i;
            this.c = locale;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof g) {
                g gVar = (g) obj;
                if (this.a.equals(gVar.a) && this.b == gVar.b && this.c.equals(gVar.c)) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return (this.b * 31) + this.c.hashCode();
        }
    }

    private static class h implements d {
        private final TimeZone a;
        private final boolean b;
        private final Locale c;
        private final int d;
        private final String e;
        private final String f;

        h(TimeZone timeZone, boolean z, Locale locale, int i) {
            this.a = timeZone;
            this.b = z;
            this.c = locale;
            this.d = i;
            if (z) {
                this.e = FastDateFormat.l(timeZone, false, i, locale);
                this.f = FastDateFormat.l(timeZone, true, i, locale);
            } else {
                this.e = null;
                this.f = null;
            }
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            if (this.b) {
                return Math.max(this.e.length(), this.f.length());
            }
            return this.d == 0 ? 4 : 40;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            if (this.b) {
                if (!this.a.useDaylightTime() || calendar.get(16) == 0) {
                    stringBuffer.append(this.e);
                    return;
                } else {
                    stringBuffer.append(this.f);
                    return;
                }
            }
            TimeZone timeZone = calendar.getTimeZone();
            if (!timeZone.useDaylightTime() || calendar.get(16) == 0) {
                stringBuffer.append(FastDateFormat.l(timeZone, false, this.d, this.c));
            } else {
                stringBuffer.append(FastDateFormat.l(timeZone, true, this.d, this.c));
            }
        }
    }

    private static class i implements d {
        static final i b = new i(true);
        static final i c = new i(false);
        final boolean a;

        i(boolean z) {
            this.a = z;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 5;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            int i = calendar.get(15) + calendar.get(16);
            if (i < 0) {
                stringBuffer.append('-');
                i = -i;
            } else {
                stringBuffer.append('+');
            }
            int i2 = i / 3600000;
            stringBuffer.append((char) ((i2 / 10) + 48));
            stringBuffer.append((char) ((i2 % 10) + 48));
            if (this.a) {
                stringBuffer.append(':');
            }
            int i3 = (i / 60000) - (i2 * 60);
            stringBuffer.append((char) ((i3 / 10) + 48));
            stringBuffer.append((char) ((i3 % 10) + 48));
        }
    }

    private static class j implements b {
        private final b a;

        j(b bVar) {
            this.a = bVar;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return this.a.a();
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            int leastMaximum = calendar.get(10);
            if (leastMaximum == 0) {
                leastMaximum = calendar.getLeastMaximum(10) + 1;
            }
            this.a.c(stringBuffer, leastMaximum);
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public void c(StringBuffer stringBuffer, int i) {
            this.a.c(stringBuffer, i);
        }
    }

    private static class k implements b {
        private final b a;

        k(b bVar) {
            this.a = bVar;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return this.a.a();
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            int maximum = calendar.get(11);
            if (maximum == 0) {
                maximum = calendar.getMaximum(11) + 1;
            }
            this.a.c(stringBuffer, maximum);
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public void c(StringBuffer stringBuffer, int i) {
            this.a.c(stringBuffer, i);
        }
    }

    private static class l implements b {
        static final l a = new l();

        l() {
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 2;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(2) + 1);
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class m implements b {
        private final int a;

        m(int i) {
            this.a = i;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 2;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(this.a));
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            if (i >= 100) {
                stringBuffer.append(Integer.toString(i));
            } else {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            }
        }
    }

    private static class n implements b {
        static final n a = new n();

        n() {
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 2;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(1));
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            stringBuffer.append((char) ((i / 10) + 48));
            stringBuffer.append((char) ((i % 10) + 48));
        }
    }

    private static class o implements b {
        static final o a = new o();

        o() {
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 2;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(2) + 1);
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            }
        }
    }

    private static class p implements b {
        private final int a;

        p(int i) {
            this.a = i;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public int a() {
            return 4;
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.d
        public void b(StringBuffer stringBuffer, Calendar calendar) {
            c(stringBuffer, calendar.get(this.a));
        }

        @Override // com.box.androidsdk.content.utils.FastDateFormat.b
        public final void c(StringBuffer stringBuffer, int i) {
            if (i < 10) {
                stringBuffer.append((char) (i + 48));
            } else if (i >= 100) {
                stringBuffer.append(Integer.toString(i));
            } else {
                stringBuffer.append((char) ((i / 10) + 48));
                stringBuffer.append((char) ((i % 10) + 48));
            }
        }
    }

    protected FastDateFormat(String str, TimeZone timeZone, Locale locale) {
        if (str == null) {
            throw new IllegalArgumentException("The pattern must not be null");
        }
        this.mPattern = str;
        this.mTimeZoneForced = timeZone != null;
        this.mTimeZone = timeZone == null ? TimeZone.getDefault() : timeZone;
        this.mLocaleForced = locale != null;
        this.mLocale = locale == null ? Locale.getDefault() : locale;
    }

    public static FastDateFormat j(String str) {
        return k(str, null, null);
    }

    public static synchronized FastDateFormat k(String str, TimeZone timeZone, Locale locale) {
        FastDateFormat fastDateFormat;
        fastDateFormat = new FastDateFormat(str, timeZone, locale);
        Map map = Y;
        FastDateFormat fastDateFormat2 = (FastDateFormat) map.get(fastDateFormat);
        if (fastDateFormat2 == null) {
            fastDateFormat.m();
            map.put(fastDateFormat, fastDateFormat);
        } else {
            fastDateFormat = fastDateFormat2;
        }
        return fastDateFormat;
    }

    static synchronized String l(TimeZone timeZone, boolean z, int i2, Locale locale) {
        String displayName;
        g gVar = new g(timeZone, z, i2, locale);
        Map map = m0;
        displayName = (String) map.get(gVar);
        if (displayName == null) {
            displayName = timeZone.getDisplayName(z, i2, locale);
            map.put(gVar, displayName);
        }
        return displayName;
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        m();
    }

    protected StringBuffer b(Calendar calendar, StringBuffer stringBuffer) {
        for (d dVar : this.q) {
            dVar.b(stringBuffer, calendar);
        }
        return stringBuffer;
    }

    public String c(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone);
        gregorianCalendar.setTime(date);
        return b(gregorianCalendar, new StringBuffer(this.X)).toString();
    }

    public StringBuffer d(long j2, StringBuffer stringBuffer) {
        return g(new Date(j2), stringBuffer);
    }

    public StringBuffer e(Calendar calendar, StringBuffer stringBuffer) {
        if (this.mTimeZoneForced) {
            calendar.getTime();
            calendar = (Calendar) calendar.clone();
            calendar.setTimeZone(this.mTimeZone);
        }
        return b(calendar, stringBuffer);
    }

    public boolean equals(Object obj) {
        TimeZone timeZone;
        TimeZone timeZone2;
        Locale locale;
        Locale locale2;
        if (!(obj instanceof FastDateFormat)) {
            return false;
        }
        FastDateFormat fastDateFormat = (FastDateFormat) obj;
        String str = this.mPattern;
        String str2 = fastDateFormat.mPattern;
        return (str == str2 || str.equals(str2)) && ((timeZone = this.mTimeZone) == (timeZone2 = fastDateFormat.mTimeZone) || timeZone.equals(timeZone2)) && (((locale = this.mLocale) == (locale2 = fastDateFormat.mLocale) || locale.equals(locale2)) && this.mTimeZoneForced == fastDateFormat.mTimeZoneForced && this.mLocaleForced == fastDateFormat.mLocaleForced);
    }

    @Override // java.text.Format
    public StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return g((Date) obj, stringBuffer);
        }
        if (obj instanceof Calendar) {
            return e((Calendar) obj, stringBuffer);
        }
        if (obj instanceof Long) {
            return d(((Long) obj).longValue(), stringBuffer);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown class: ");
        sb.append(obj == null ? "<null>" : obj.getClass().getName());
        throw new IllegalArgumentException(sb.toString());
    }

    public StringBuffer g(Date date, StringBuffer stringBuffer) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(this.mTimeZone);
        gregorianCalendar.setTime(date);
        return b(gregorianCalendar, stringBuffer);
    }

    public int hashCode() {
        return this.mPattern.hashCode() + this.mTimeZone.hashCode() + (this.mTimeZoneForced ? 1 : 0) + this.mLocale.hashCode() + (this.mLocaleForced ? 1 : 0);
    }

    protected void m() {
        List listN = n();
        d[] dVarArr = (d[]) listN.toArray(new d[listN.size()]);
        this.q = dVarArr;
        int length = dVarArr.length;
        int iA = 0;
        while (true) {
            length--;
            if (length < 0) {
                this.X = iA;
                return;
            }
            iA += this.q[length].a();
        }
    }

    protected List n() {
        int i2;
        Object objQ;
        Object aVar;
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.mLocale);
        ArrayList arrayList = new ArrayList();
        String[] eras = dateFormatSymbols.getEras();
        String[] months = dateFormatSymbols.getMonths();
        String[] shortMonths = dateFormatSymbols.getShortMonths();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        String[] shortWeekdays = dateFormatSymbols.getShortWeekdays();
        String[] amPmStrings = dateFormatSymbols.getAmPmStrings();
        int length = this.mPattern.length();
        int i3 = 0;
        while (i3 < length) {
            int[] iArr = {i3};
            String strO = o(this.mPattern, iArr);
            int i4 = iArr[0];
            int length2 = strO.length();
            if (length2 == 0) {
                return arrayList;
            }
            char cCharAt = strO.charAt(0);
            if (cCharAt == 'y') {
                i2 = 1;
                objQ = length2 >= 4 ? q(1, length2) : n.a;
            } else if (cCharAt != 'z') {
                switch (cCharAt) {
                    case '\'':
                        String strSubstring = strO.substring(1);
                        aVar = strSubstring.length() == 1 ? new a(strSubstring.charAt(0)) : new e(strSubstring);
                        objQ = aVar;
                        i2 = 1;
                        break;
                    case 'K':
                        objQ = q(10, length2);
                        i2 = 1;
                        break;
                    case 'M':
                        if (length2 < 4) {
                            if (length2 == 3) {
                                aVar = new f(2, shortMonths);
                            } else {
                                objQ = length2 == 2 ? l.a : o.a;
                            }
                            i2 = 1;
                        } else {
                            aVar = new f(2, months);
                        }
                        objQ = aVar;
                        i2 = 1;
                        break;
                    case 'S':
                        objQ = q(14, length2);
                        i2 = 1;
                        break;
                    case 'W':
                        objQ = q(4, length2);
                        i2 = 1;
                        break;
                    case SubsamplingScaleImageView.ORIENTATION_90 /* 90 */:
                        objQ = length2 == 1 ? i.c : i.b;
                        i2 = 1;
                        break;
                    case 'a':
                        objQ = new f(9, amPmStrings);
                        i2 = 1;
                        break;
                    case 'd':
                        objQ = q(5, length2);
                        i2 = 1;
                        break;
                    case 'h':
                        objQ = new j(q(10, length2));
                        i2 = 1;
                        break;
                    case 'k':
                        objQ = new k(q(11, length2));
                        i2 = 1;
                        break;
                    case 'm':
                        objQ = q(12, length2);
                        i2 = 1;
                        break;
                    case 's':
                        objQ = q(13, length2);
                        i2 = 1;
                        break;
                    case 'w':
                        objQ = q(3, length2);
                        i2 = 1;
                        break;
                    default:
                        switch (cCharAt) {
                            case 'D':
                                objQ = q(6, length2);
                                break;
                            case 'E':
                                objQ = new f(7, length2 < 4 ? shortWeekdays : weekdays);
                                break;
                            case 'F':
                                objQ = q(8, length2);
                                break;
                            case 'G':
                                objQ = new f(0, eras);
                                break;
                            case 'H':
                                objQ = q(11, length2);
                                break;
                            default:
                                throw new IllegalArgumentException("Illegal pattern component: " + strO);
                        }
                        i2 = 1;
                        break;
                }
            } else if (length2 >= 4) {
                i2 = 1;
                objQ = new h(this.mTimeZone, this.mTimeZoneForced, this.mLocale, 1);
            } else {
                i2 = 1;
                objQ = new h(this.mTimeZone, this.mTimeZoneForced, this.mLocale, 0);
            }
            arrayList.add(objQ);
            i3 = i4 + i2;
        }
        return arrayList;
    }

    protected String o(String str, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        int i2 = iArr[0];
        int length = str.length();
        char cCharAt = str.charAt(i2);
        if ((cCharAt < 'A' || cCharAt > 'Z') && (cCharAt < 'a' || cCharAt > 'z')) {
            stringBuffer.append('\'');
            boolean z = false;
            while (i2 < length) {
                char cCharAt2 = str.charAt(i2);
                if (cCharAt2 != '\'') {
                    if (!z && ((cCharAt2 >= 'A' && cCharAt2 <= 'Z') || (cCharAt2 >= 'a' && cCharAt2 <= 'z'))) {
                        i2--;
                        break;
                    }
                    stringBuffer.append(cCharAt2);
                } else {
                    int i3 = i2 + 1;
                    if (i3 >= length || str.charAt(i3) != '\'') {
                        z = !z;
                    } else {
                        stringBuffer.append(cCharAt2);
                        i2 = i3;
                    }
                }
                i2++;
            }
        } else {
            stringBuffer.append(cCharAt);
            while (true) {
                int i4 = i2 + 1;
                if (i4 >= length || str.charAt(i4) != cCharAt) {
                    break;
                }
                stringBuffer.append(cCharAt);
                i2 = i4;
            }
        }
        iArr[0] = i2;
        return stringBuffer.toString();
    }

    @Override // java.text.Format
    public Object parseObject(String str, ParsePosition parsePosition) {
        parsePosition.setIndex(0);
        parsePosition.setErrorIndex(0);
        return null;
    }

    protected b q(int i2, int i3) {
        if (i3 != 1) {
            return i3 != 2 ? new c(i2, i3) : new m(i2);
        }
        return new p(i2);
    }

    public String toString() {
        return "FastDateFormat[" + this.mPattern + "]";
    }
}
