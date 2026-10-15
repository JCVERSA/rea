package com.google.android.gms.measurement.internal;

import ax.H6.AbstractC0994m4;
import ax.H6.C0884a2;
import ax.H6.C0956i2;
import ax.H6.C0965j2;
import ax.H6.H6;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
final class K5 {
    private String a;
    private boolean b;
    private C0956i2 c;
    private BitSet d;
    private BitSet e;
    private Map<Integer, Long> f;
    private Map<Integer, List<Long>> g;
    private final /* synthetic */ J5 h;

    private K5(J5 j5, String str) {
        this.h = j5;
        this.a = str;
        this.b = true;
        this.d = new BitSet();
        this.e = new BitSet();
        this.f = new ax.B.a();
        this.g = new ax.B.a();
    }

    private K5(J5 j5, String str, C0956i2 c0956i2, BitSet bitSet, BitSet bitSet2, Map<Integer, Long> map, Map<Integer, Long> map2) {
        this.h = j5;
        this.a = str;
        this.d = bitSet;
        this.e = bitSet2;
        this.f = map;
        this.g = new ax.B.a();
        if (map2 != null) {
            for (Integer num : map2.keySet()) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(map2.get(num));
                this.g.put(num, arrayList);
            }
        }
        this.b = false;
        this.c = c0956i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [ax.H6.Z1$a, ax.H6.m4$b] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r1v9, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r8v5, types: [ax.H6.i2$a] */
    final ax.H6.Z1 a(int i) {
        ArrayList arrayList;
        ?? arrayList2;
        ?? P = ax.H6.Z1.P();
        P.v(i);
        P.B(this.b);
        C0956i2 c0956i2 = this.c;
        if (c0956i2 != null) {
            P.z(c0956i2);
        }
        ?? F = C0956i2.Z().B(x5.O(this.d)).F(x5.O(this.e));
        if (this.f == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList(this.f.size());
            for (Integer num : this.f.keySet()) {
                int iIntValue = num.intValue();
                Long l = this.f.get(num);
                if (l != null) {
                    arrayList.add((C0884a2) ((AbstractC0994m4) C0884a2.O().v(iIntValue).w(l.longValue()).y()));
                }
            }
        }
        if (arrayList != null) {
            F.w(arrayList);
        }
        if (this.g == null) {
            arrayList2 = Collections.EMPTY_LIST;
        } else {
            arrayList2 = new ArrayList(this.g.size());
            for (Integer num2 : this.g.keySet()) {
                C0965j2.a aVarV = C0965j2.P().v(num2.intValue());
                List<Long> list = this.g.get(num2);
                if (list != null) {
                    Collections.sort(list);
                    aVarV.w(list);
                }
                arrayList2.add((C0965j2) ((AbstractC0994m4) aVarV.y()));
            }
        }
        F.D(arrayList2);
        P.w(F);
        return (ax.H6.Z1) ((AbstractC0994m4) P.y());
    }

    final void c(M5 m5) {
        int iA = m5.a();
        Boolean bool = m5.c;
        if (bool != null) {
            this.e.set(iA, bool.booleanValue());
        }
        Boolean bool2 = m5.d;
        if (bool2 != null) {
            this.d.set(iA, bool2.booleanValue());
        }
        if (m5.e != null) {
            Long l = this.f.get(Integer.valueOf(iA));
            long jLongValue = m5.e.longValue() / 1000;
            if (l == null || jLongValue > l.longValue()) {
                this.f.put(Integer.valueOf(iA), Long.valueOf(jLongValue));
            }
        }
        if (m5.f != null) {
            List<Long> arrayList = this.g.get(Integer.valueOf(iA));
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.g.put(Integer.valueOf(iA), arrayList);
            }
            if (m5.j()) {
                arrayList.clear();
            }
            if (H6.a() && this.h.d().G(this.a, F.l0) && m5.i()) {
                arrayList.clear();
            }
            if (!H6.a() || !this.h.d().G(this.a, F.l0)) {
                arrayList.add(Long.valueOf(m5.f.longValue() / 1000));
                return;
            }
            long jLongValue2 = m5.f.longValue() / 1000;
            if (arrayList.contains(Long.valueOf(jLongValue2))) {
                return;
            }
            arrayList.add(Long.valueOf(jLongValue2));
        }
    }
}
