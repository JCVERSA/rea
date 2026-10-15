package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import j$.util.Objects;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public final class C6409u {
    private static final C6409u f = new C6409u(null, 100);
    private final int a;
    private final String b;
    private final Boolean c;
    private final String d;
    private final EnumMap<C6330h3.a, ax.M6.o> e;

    C6409u(Boolean bool, int i) {
        this(bool, i, (Boolean) null, (String) null);
    }

    C6409u(Boolean bool, int i, Boolean bool2, String str) {
        EnumMap<C6330h3.a, ax.M6.o> enumMap = new EnumMap<>(C6330h3.a.class);
        this.e = enumMap;
        enumMap.put(C6330h3.a.AD_USER_DATA, C6330h3.d(bool));
        this.a = i;
        this.b = l();
        this.c = bool2;
        this.d = str;
    }

    private C6409u(EnumMap<C6330h3.a, ax.M6.o> enumMap, int i, Boolean bool, String str) {
        EnumMap<C6330h3.a, ax.M6.o> enumMap2 = new EnumMap<>(C6330h3.a.class);
        this.e = enumMap2;
        enumMap2.putAll(enumMap);
        this.a = i;
        this.b = l();
        this.c = bool;
        this.d = str;
    }

    public static C6409u b(Bundle bundle, int i) {
        if (bundle == null) {
            return new C6409u(null, i);
        }
        EnumMap enumMap = new EnumMap(C6330h3.a.class);
        for (C6330h3.a aVar : EnumC6337i3.DMA.g()) {
            enumMap.put(aVar, C6330h3.e(bundle.getString(aVar.q)));
        }
        return new C6409u((EnumMap<C6330h3.a, ax.M6.o>) enumMap, i, bundle.containsKey("is_dma_region") ? Boolean.valueOf(bundle.getString("is_dma_region")) : null, bundle.getString("cps_display_str"));
    }

    static C6409u c(ax.M6.o oVar, int i) {
        EnumMap enumMap = new EnumMap(C6330h3.a.class);
        enumMap.put(C6330h3.a.AD_USER_DATA, oVar);
        return new C6409u((EnumMap<C6330h3.a, ax.M6.o>) enumMap, -10, (Boolean) null, (String) null);
    }

    public static C6409u d(String str) {
        if (str == null || str.length() <= 0) {
            return f;
        }
        String[] strArrSplit = str.split(":");
        int i = Integer.parseInt(strArrSplit[0]);
        EnumMap enumMap = new EnumMap(C6330h3.a.class);
        C6330h3.a[] aVarArrG = EnumC6337i3.DMA.g();
        int length = aVarArrG.length;
        int i2 = 1;
        int i3 = 0;
        while (i3 < length) {
            enumMap.put(aVarArrG[i3], C6330h3.c(strArrSplit[i2].charAt(0)));
            i3++;
            i2++;
        }
        return new C6409u((EnumMap<C6330h3.a, ax.M6.o>) enumMap, i, (Boolean) null, (String) null);
    }

    public static Boolean e(Bundle bundle) {
        ax.M6.o oVarE;
        if (bundle == null || (oVarE = C6330h3.e(bundle.getString("ad_personalization"))) == null) {
            return null;
        }
        int i = C6427x.a[oVarE.ordinal()];
        if (i == 3) {
            return Boolean.FALSE;
        }
        if (i != 4) {
            return null;
        }
        return Boolean.TRUE;
    }

    private final String l() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a);
        for (C6330h3.a aVar : EnumC6337i3.DMA.g()) {
            sb.append(":");
            sb.append(C6330h3.a(this.e.get(aVar)));
        }
        return sb.toString();
    }

    public final int a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C6409u)) {
            return false;
        }
        C6409u c6409u = (C6409u) obj;
        if (this.b.equalsIgnoreCase(c6409u.b) && Objects.equals(this.c, c6409u.c)) {
            return Objects.equals(this.d, c6409u.d);
        }
        return false;
    }

    public final Bundle f() {
        Bundle bundle = new Bundle();
        Iterator it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String strR = C6330h3.r((ax.M6.o) entry.getValue());
            if (strR != null) {
                bundle.putString(((C6330h3.a) entry.getKey()).q, strR);
            }
        }
        Boolean bool = this.c;
        if (bool != null) {
            bundle.putString("is_dma_region", bool.toString());
        }
        String str = this.d;
        if (str != null) {
            bundle.putString("cps_display_str", str);
        }
        return bundle;
    }

    public final ax.M6.o g() {
        ax.M6.o oVar = this.e.get(C6330h3.a.AD_USER_DATA);
        return oVar == null ? ax.M6.o.UNINITIALIZED : oVar;
    }

    public final Boolean h() {
        return this.c;
    }

    public final int hashCode() {
        int i;
        Boolean bool = this.c;
        if (bool == null) {
            i = 3;
        } else {
            i = bool == Boolean.TRUE ? 7 : 13;
        }
        String str = this.d;
        return this.b.hashCode() + (i * 29) + ((str == null ? 17 : str.hashCode()) * 137);
    }

    public final String i() {
        return this.d;
    }

    public final String j() {
        return this.b;
    }

    public final boolean k() {
        Iterator<ax.M6.o> it = this.e.values().iterator();
        while (it.hasNext()) {
            if (it.next() != ax.M6.o.UNINITIALIZED) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("source=");
        sb.append(C6330h3.j(this.a));
        for (C6330h3.a aVar : EnumC6337i3.DMA.g()) {
            sb.append(",");
            sb.append(aVar.q);
            sb.append("=");
            ax.M6.o oVar = this.e.get(aVar);
            if (oVar == null) {
                sb.append("uninitialized");
            } else {
                int i = C6427x.a[oVar.ordinal()];
                if (i == 1) {
                    sb.append("uninitialized");
                } else if (i == 2) {
                    sb.append("eu_consent_policy");
                } else if (i == 3) {
                    sb.append("denied");
                } else if (i == 4) {
                    sb.append("granted");
                }
            }
        }
        if (this.c != null) {
            sb.append(",isDmaRegion=");
            sb.append(this.c);
        }
        if (this.d != null) {
            sb.append(",cpsDisplayStr=");
            sb.append(this.d);
        }
        return sb.toString();
    }
}
