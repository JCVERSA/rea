package com.google.android.gms.measurement.internal;

import ax.p265q6.C2254p;
import j$.util.DesugarCollections;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes3.dex */
abstract class M5 {
    String a;
    int b;
    Boolean c;
    Boolean d;
    Long e;
    Long f;

    M5(String str, int i) {
        this.a = str;
        this.b = i;
    }

    static Boolean b(double d, ax.H6.F1 f1) {
        try {
            return h(new BigDecimal(d), f1, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean c(long j, ax.H6.F1 f1) {
        try {
            return h(new BigDecimal(j), f1, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    static Boolean d(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() != z);
    }

    static Boolean e(String str, ax.H6.F1 f1) {
        if (!x5.h0(str)) {
            return null;
        }
        try {
            return h(new BigDecimal(str), f1, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private static Boolean f(String str, ax.H6.H1.a aVar, boolean z, String str2, List<String> list, String str3, V1 v1) {
        if (str == null) {
            return null;
        }
        if (aVar == ax.H6.H1.a.IN_LIST) {
            if (list == null || list.isEmpty()) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && aVar != ax.H6.H1.a.REGEXP) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (I5.a[aVar.ordinal()]) {
            case 1:
                if (str3 == null) {
                    return null;
                }
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    if (v1 != null) {
                        v1.L().b("Invalid regular expression in REGEXP audience filter. expression", str3);
                    }
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                if (list == null) {
                    return null;
                }
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    static Boolean g(String str, ax.H6.H1 h1, V1 v1) {
        List<String> list;
        C2254p.l(h1);
        if (str == null || !h1.S() || h1.J() == ax.H6.H1.a.UNKNOWN_MATCH_TYPE) {
            return null;
        }
        ax.H6.H1.a aVarJ = h1.J();
        ax.H6.H1.a aVar = ax.H6.H1.a.IN_LIST;
        if (aVarJ == aVar) {
            if (h1.n() == 0) {
                return null;
            }
        } else if (!h1.Q()) {
            return null;
        }
        ax.H6.H1.a aVarJ2 = h1.J();
        boolean zO = h1.O();
        String strM = (zO || aVarJ2 == ax.H6.H1.a.REGEXP || aVarJ2 == aVar) ? h1.M() : h1.M().toUpperCase(Locale.ENGLISH);
        if (h1.n() == 0) {
            list = null;
        } else {
            List<String> listN = h1.N();
            if (!zO) {
                ArrayList arrayList = new ArrayList(listN.size());
                Iterator<String> it = listN.iterator();
                while (it.hasNext()) {
                    arrayList.add(it.next().toUpperCase(Locale.ENGLISH));
                }
                listN = DesugarCollections.unmodifiableList(arrayList);
            }
            list = listN;
        }
        return f(str, aVarJ2, zO, strM, list, aVarJ2 == ax.H6.H1.a.REGEXP ? strM : null, v1);
    }

    private static Boolean h(BigDecimal bigDecimal, ax.H6.F1 f1, double d) {
        BigDecimal bigDecimal2;
        BigDecimal bigDecimal3;
        BigDecimal bigDecimal4;
        C2254p.l(f1);
        if (f1.Q() && f1.J() != ax.H6.F1.a.UNKNOWN_COMPARISON_TYPE) {
            ax.H6.F1.a aVarJ = f1.J();
            ax.H6.F1.a aVar = ax.H6.F1.a.BETWEEN;
            if (aVarJ == aVar) {
                if (!f1.V() || !f1.U()) {
                    return null;
                }
            } else if (!f1.S()) {
                return null;
            }
            ax.H6.F1.a aVarJ2 = f1.J();
            if (f1.J() == aVar) {
                if (x5.h0(f1.O()) && x5.h0(f1.N())) {
                    try {
                        BigDecimal bigDecimal5 = new BigDecimal(f1.O());
                        bigDecimal4 = new BigDecimal(f1.N());
                        bigDecimal3 = bigDecimal5;
                        bigDecimal2 = null;
                    } catch (NumberFormatException unused) {
                    }
                }
                return null;
            }
            if (!x5.h0(f1.M())) {
                return null;
            }
            try {
                bigDecimal2 = new BigDecimal(f1.M());
                bigDecimal3 = null;
                bigDecimal4 = null;
            } catch (NumberFormatException unused2) {
            }
            if (aVarJ2 == aVar) {
                if (bigDecimal3 == null) {
                    return null;
                }
            } else if (bigDecimal2 != null) {
            }
            int i = I5.b[aVarJ2.ordinal()];
            boolean z = false;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4 || bigDecimal3 == null) {
                            return null;
                        }
                        if (bigDecimal.compareTo(bigDecimal3) >= 0 && bigDecimal.compareTo(bigDecimal4) <= 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    }
                    if (bigDecimal2 != null) {
                        if (d == 0.0d) {
                            return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) == 0);
                        }
                        if (bigDecimal.compareTo(bigDecimal2.subtract(new BigDecimal(d).multiply(new BigDecimal(2)))) > 0 && bigDecimal.compareTo(bigDecimal2.add(new BigDecimal(d).multiply(new BigDecimal(2)))) < 0) {
                            z = true;
                        }
                        return Boolean.valueOf(z);
                    }
                } else if (bigDecimal2 != null) {
                    return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) > 0);
                }
            } else if (bigDecimal2 != null) {
                return Boolean.valueOf(bigDecimal.compareTo(bigDecimal2) < 0);
            }
        }
        return null;
    }

    abstract int a();

    abstract boolean i();

    abstract boolean j();
}
