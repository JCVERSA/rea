package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import okhttp3.HttpUrl;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6330h3 {
    public static final C6330h3 c = new C6330h3(null, null, 100);
    private final EnumMap<a, ax.M6.o> a;
    private final int b;

    /* JADX INFO: renamed from: com.google.android.gms.measurement.internal.h3$a */
    public enum a {
        AD_STORAGE("ad_storage"),
        ANALYTICS_STORAGE("analytics_storage"),
        AD_USER_DATA("ad_user_data"),
        AD_PERSONALIZATION("ad_personalization");

        public final String q;

        a(String str) {
            this.q = str;
        }
    }

    public C6330h3(Boolean bool, Boolean bool2, int i) {
        EnumMap<a, ax.M6.o> enumMap = new EnumMap<>(a.class);
        this.a = enumMap;
        enumMap.put(a.AD_STORAGE, d(bool));
        enumMap.put(a.ANALYTICS_STORAGE, d(bool2));
        this.b = i;
    }

    private C6330h3(EnumMap<a, ax.M6.o> enumMap, int i) {
        EnumMap<a, ax.M6.o> enumMap2 = new EnumMap<>(a.class);
        this.a = enumMap2;
        enumMap2.putAll(enumMap);
        this.b = i;
    }

    static char a(ax.M6.o oVar) {
        if (oVar == null) {
            return '-';
        }
        int iOrdinal = oVar.ordinal();
        if (iOrdinal == 1) {
            return '+';
        }
        if (iOrdinal != 2) {
            return iOrdinal != 3 ? '-' : '1';
        }
        return '0';
    }

    static ax.M6.o c(char c2) {
        if (c2 == '+') {
            return ax.M6.o.POLICY;
        }
        if (c2 != '0') {
            return c2 != '1' ? ax.M6.o.UNINITIALIZED : ax.M6.o.GRANTED;
        }
        return ax.M6.o.DENIED;
    }

    static ax.M6.o d(Boolean bool) {
        if (bool == null) {
            return ax.M6.o.UNINITIALIZED;
        }
        return bool.booleanValue() ? ax.M6.o.GRANTED : ax.M6.o.DENIED;
    }

    static ax.M6.o e(String str) {
        if (str == null) {
            return ax.M6.o.UNINITIALIZED;
        }
        if (str.equals("granted")) {
            return ax.M6.o.GRANTED;
        }
        return str.equals("denied") ? ax.M6.o.DENIED : ax.M6.o.UNINITIALIZED;
    }

    public static C6330h3 f(Bundle bundle, int i) {
        if (bundle == null) {
            return new C6330h3(null, null, i);
        }
        EnumMap enumMap = new EnumMap(a.class);
        for (a aVar : EnumC6337i3.STORAGE.q) {
            enumMap.put(aVar, e(bundle.getString(aVar.q)));
        }
        return new C6330h3(enumMap, i);
    }

    public static C6330h3 g(ax.M6.o oVar, ax.M6.o oVar2, int i) {
        EnumMap enumMap = new EnumMap(a.class);
        enumMap.put(a.AD_STORAGE, oVar);
        enumMap.put(a.ANALYTICS_STORAGE, oVar2);
        return new C6330h3(enumMap, -10);
    }

    public static C6330h3 i(String str, int i) {
        EnumMap enumMap = new EnumMap(a.class);
        if (str == null) {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        a[] aVarArrG = EnumC6337i3.STORAGE.g();
        for (int i2 = 0; i2 < aVarArrG.length; i2++) {
            a aVar = aVarArrG[i2];
            int i3 = i2 + 2;
            if (i3 < str.length()) {
                enumMap.put(aVar, c(str.charAt(i3)));
            } else {
                enumMap.put(aVar, ax.M6.o.UNINITIALIZED);
            }
        }
        return new C6330h3(enumMap, i);
    }

    static String j(int i) {
        if (i == -30) {
            return "TCF";
        }
        if (i == -20) {
            return "API";
        }
        if (i == -10) {
            return "MANIFEST";
        }
        if (i == 0) {
            return "1P_API";
        }
        if (i == 30) {
            return "1P_INIT";
        }
        if (i != 90) {
            return i != 100 ? "OTHER" : "UNKNOWN";
        }
        return "REMOTE_CONFIG";
    }

    public static String k(Bundle bundle) {
        String string;
        a[] aVarArr = EnumC6337i3.STORAGE.q;
        int length = aVarArr.length;
        int i = 0;
        while (true) {
            Boolean bool = null;
            if (i >= length) {
                return null;
            }
            a aVar = aVarArr[i];
            if (bundle.containsKey(aVar.q) && (string = bundle.getString(aVar.q)) != null) {
                if (string.equals("granted")) {
                    bool = Boolean.TRUE;
                } else if (string.equals("denied")) {
                    bool = Boolean.FALSE;
                }
                if (bool == null) {
                    return string;
                }
            }
            i++;
        }
    }

    public static boolean l(int i, int i2) {
        if (i == -20 && i2 == -30) {
            return true;
        }
        return (i == -30 && i2 == -20) || i == i2 || i < i2;
    }

    public static C6330h3 q(String str) {
        return i(str, 100);
    }

    static String r(ax.M6.o oVar) {
        int iOrdinal = oVar.ordinal();
        if (iOrdinal == 2) {
            return "denied";
        }
        if (iOrdinal != 3) {
            return null;
        }
        return "granted";
    }

    public final boolean A() {
        return m(a.AD_STORAGE);
    }

    public final boolean B() {
        return m(a.ANALYTICS_STORAGE);
    }

    public final boolean C() {
        Iterator<ax.M6.o> it = this.a.values().iterator();
        while (it.hasNext()) {
            if (it.next() != ax.M6.o.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final int b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C6330h3)) {
            return false;
        }
        C6330h3 c6330h3 = (C6330h3) obj;
        for (a aVar : EnumC6337i3.STORAGE.q) {
            if (this.a.get(aVar) != c6330h3.a.get(aVar)) {
                return false;
            }
        }
        return this.b == c6330h3.b;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0035  */
    public final C6330h3 h(C6330h3 c6330h3) {
        EnumMap enumMap = new EnumMap(a.class);
        for (a aVar : EnumC6337i3.STORAGE.q) {
            ax.M6.o oVar = this.a.get(aVar);
            ax.M6.o oVar2 = c6330h3.a.get(aVar);
            if (oVar == null) {
                oVar = oVar2;
            } else if (oVar2 != null) {
                ax.M6.o oVar3 = ax.M6.o.UNINITIALIZED;
                if (oVar == oVar3) {
                    oVar = oVar2;
                } else if (oVar2 != oVar3) {
                    ax.M6.o oVar4 = ax.M6.o.POLICY;
                    if (oVar == oVar4) {
                        oVar = oVar2;
                    } else if (oVar2 != oVar4) {
                        ax.M6.o oVar5 = ax.M6.o.DENIED;
                        oVar = (oVar == oVar5 || oVar2 == oVar5) ? oVar5 : ax.M6.o.GRANTED;
                    }
                }
            }
            if (oVar != null) {
                enumMap.put(aVar, oVar);
            }
        }
        return new C6330h3(enumMap, 100);
    }

    public final int hashCode() {
        int iHashCode = this.b * 17;
        Iterator<ax.M6.o> it = this.a.values().iterator();
        while (it.hasNext()) {
            iHashCode = (iHashCode * 31) + it.next().hashCode();
        }
        return iHashCode;
    }

    public final boolean m(a aVar) {
        return this.a.get(aVar) != ax.M6.o.DENIED;
    }

    public final boolean n(C6330h3 c6330h3, a... aVarArr) {
        for (a aVar : aVarArr) {
            if (!c6330h3.m(aVar) && m(aVar)) {
                return true;
            }
        }
        return false;
    }

    public final Bundle o() {
        Bundle bundle = new Bundle();
        Iterator it = this.a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String strR = r((ax.M6.o) entry.getValue());
            if (strR != null) {
                bundle.putString(((a) entry.getKey()).q, strR);
            }
        }
        return bundle;
    }

    public final C6330h3 p(C6330h3 c6330h3) {
        EnumMap enumMap = new EnumMap(a.class);
        for (a aVar : EnumC6337i3.STORAGE.q) {
            ax.M6.o oVar = this.a.get(aVar);
            if (oVar == ax.M6.o.UNINITIALIZED) {
                oVar = c6330h3.a.get(aVar);
            }
            if (oVar != null) {
                enumMap.put(aVar, oVar);
            }
        }
        return new C6330h3(enumMap, this.b);
    }

    public final boolean s(C6330h3 c6330h3, a... aVarArr) {
        for (a aVar : aVarArr) {
            ax.M6.o oVar = this.a.get(aVar);
            ax.M6.o oVar2 = c6330h3.a.get(aVar);
            ax.M6.o oVar3 = ax.M6.o.DENIED;
            if (oVar == oVar3 && oVar2 != oVar3) {
                return true;
            }
        }
        return false;
    }

    public final ax.M6.o t() {
        ax.M6.o oVar = this.a.get(a.AD_STORAGE);
        return oVar == null ? ax.M6.o.UNINITIALIZED : oVar;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(j(this.b));
        for (a aVar : EnumC6337i3.STORAGE.q) {
            sb.append(",");
            sb.append(aVar.q);
            sb.append("=");
            ax.M6.o oVar = this.a.get(aVar);
            if (oVar == null) {
                oVar = ax.M6.o.UNINITIALIZED;
            }
            sb.append(oVar);
        }
        return sb.toString();
    }

    public final boolean u(C6330h3 c6330h3) {
        return s(c6330h3, (a[]) this.a.keySet().toArray(new a[0]));
    }

    public final ax.M6.o v() {
        ax.M6.o oVar = this.a.get(a.ANALYTICS_STORAGE);
        return oVar == null ? ax.M6.o.UNINITIALIZED : oVar;
    }

    public final Boolean w() {
        ax.M6.o oVar = this.a.get(a.AD_STORAGE);
        if (oVar == null) {
            return null;
        }
        int iOrdinal = oVar.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                return Boolean.FALSE;
            }
            if (iOrdinal != 3) {
                return null;
            }
        }
        return Boolean.TRUE;
    }

    public final Boolean x() {
        ax.M6.o oVar = this.a.get(a.ANALYTICS_STORAGE);
        if (oVar == null) {
            return null;
        }
        int iOrdinal = oVar.ordinal();
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                return Boolean.FALSE;
            }
            if (iOrdinal != 3) {
                return null;
            }
        }
        return Boolean.TRUE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0032  */
    public final String y() {
        int iOrdinal;
        StringBuilder sb = new StringBuilder("G1");
        for (a aVar : EnumC6337i3.STORAGE.g()) {
            ax.M6.o oVar = this.a.get(aVar);
            char c2 = '-';
            if (oVar != null && (iOrdinal = oVar.ordinal()) != 0) {
                if (iOrdinal == 1) {
                    c2 = '1';
                } else if (iOrdinal == 2) {
                    c2 = '0';
                } else if (iOrdinal == 3) {
                    c2 = '1';
                }
            }
            sb.append(c2);
        }
        return sb.toString();
    }

    public final String z() {
        StringBuilder sb = new StringBuilder("G1");
        for (a aVar : EnumC6337i3.STORAGE.g()) {
            sb.append(a(this.a.get(aVar)));
        }
        return sb.toString();
    }
}
