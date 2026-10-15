package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import ax.H6.AbstractC0994m4;
import ax.H6.C0884a2;
import ax.H6.C0893b2;
import ax.H6.C0956i2;
import ax.H6.C0965j2;
import ax.H6.C0974k2;
import ax.H6.C1013o6;
import ax.H6.H6;
import ax.p265q6.C2254p;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
final class J5 extends AbstractC6353k5 {
    private String d;
    private Set<Integer> e;
    private Map<Integer, K5> f;
    private Long g;
    private Long h;

    J5(q5 q5Var) {
        super(q5Var);
    }

    private final void B(List<C0974k2> list) {
        if (list.isEmpty()) {
            return;
        }
        ax.B.a aVar = new ax.B.a();
        for (C0974k2 c0974k2 : list) {
            String strD0 = c0974k2.d0();
            Map<Integer, List<ax.H6.G1>> mapM0 = (Map) aVar.get(strD0);
            if (mapM0 == null) {
                mapM0 = q().M0(this.d, strD0);
                aVar.put(strD0, mapM0);
            }
            for (Integer num : mapM0.keySet()) {
                int iIntValue = num.intValue();
                if (this.e.contains(num)) {
                    j().K().b("Skipping failed audience ID", num);
                    break;
                }
                boolean zK = true;
                for (ax.H6.G1 g1 : mapM0.get(num)) {
                    if (j().C(2)) {
                        j().K().d("Evaluating filter. audience, filter, property", num, g1.T() ? Integer.valueOf(g1.n()) : null, g().g(g1.O()));
                        j().K().b("Filter definition", o().L(g1));
                    }
                    if (!g1.T() || g1.n() > 256) {
                        j().L().c("Invalid property filter ID. appId, id", V1.v(this.d), String.valueOf(g1.T() ? Integer.valueOf(g1.n()) : null));
                        zK = false;
                        break;
                    }
                    C6298d c6298d = new C6298d(this, this.d, iIntValue, g1);
                    zK = c6298d.k(this.g, this.h, c0974k2, D(iIntValue, g1.n()));
                    if (!zK) {
                        this.e.add(num);
                        break;
                    }
                    y(num).c(c6298d);
                }
                if (!zK) {
                    this.e.add(num);
                }
            }
        }
    }

    private final void C(List<C0893b2> list, boolean z) {
        C6433y c6433y;
        ax.M6.F f;
        if (list.isEmpty()) {
            return;
        }
        ax.M6.F f2 = null;
        L5 l5 = new L5(this);
        ax.B.a aVar = new ax.B.a();
        for (C0893b2 c0893b2 : list) {
            C0893b2 c0893b2A = l5.a(this.d, c0893b2);
            if (c0893b2A != null) {
                C6354l c6354lQ = q();
                String str = this.d;
                String strF0 = c0893b2A.f0();
                C6433y c6433yG0 = c6354lQ.G0(str, c0893b2.f0());
                if (c6433yG0 == null) {
                    c6354lQ.j().L().c("Event aggregate wasn't created during raw event logging. appId, event", V1.v(str), c6354lQ.g().c(strF0));
                    c6433y = new C6433y(str, c0893b2.f0(), 1L, 1L, 1L, c0893b2.c0(), 0L, null, null, null, null);
                } else {
                    c6433y = new C6433y(c6433yG0.a, c6433yG0.b, c6433yG0.c + 1, c6433yG0.d + 1, c6433yG0.e + 1, c6433yG0.f, c6433yG0.g, c6433yG0.h, c6433yG0.i, c6433yG0.j, c6433yG0.k);
                }
                q().U(c6433y);
                if (C1013o6.a()) {
                    f = null;
                    if (d().G(null, F.W0) && z) {
                        f2 = null;
                        aVar = aVar;
                    }
                    l5 = l5;
                } else {
                    f = null;
                }
                long j = c6433y.c;
                String strF1 = c0893b2A.f0();
                ax.B.a aVar2 = aVar;
                Map<Integer, List<ax.H6.D1>> mapK0 = (Map) aVar2.get(strF1);
                if (mapK0 == null) {
                    mapK0 = q().K0(this.d, strF1);
                    aVar2.put(strF1, mapK0);
                }
                for (Integer num : mapK0.keySet()) {
                    int iIntValue = num.intValue();
                    if (this.e.contains(num)) {
                        j().K().b("Skipping failed audience ID", num);
                    } else {
                        boolean z2 = true;
                        for (ax.H6.D1 d1 : mapK0.get(num)) {
                            C6284b c6284b = new C6284b(this, this.d, iIntValue, d1);
                            boolean zK = c6284b.k(this.g, this.h, c0893b2A, j, c6433y, D(iIntValue, d1.O()));
                            if (!zK) {
                                this.e.add(num);
                                z2 = zK;
                                break;
                            } else {
                                y(num).c(c6284b);
                                z2 = zK;
                            }
                        }
                        if (!z2) {
                            this.e.add(num);
                        }
                    }
                }
                ax.M6.F f3 = f;
                aVar = aVar2;
                f2 = f3;
                l5 = l5;
            } else {
                aVar = aVar;
                f2 = f2;
            }
        }
    }

    private final boolean D(int i, int i2) {
        K5 k5 = this.f.get(Integer.valueOf(i));
        if (k5 == null) {
            return false;
        }
        return k5.d.get(i2);
    }

    private final List<ax.H6.Z1> E() {
        ArrayList arrayList = new ArrayList();
        Set<Integer> setKeySet = this.f.keySet();
        setKeySet.removeAll(this.e);
        for (Integer num : setKeySet) {
            int iIntValue = num.intValue();
            K5 k5 = this.f.get(num);
            C2254p.l(k5);
            ax.H6.Z1 z1A = k5.a(iIntValue);
            arrayList.add(z1A);
            C6354l c6354lQ = q();
            String str = this.d;
            C0956i2 c0956i2U = z1A.U();
            c6354lQ.u();
            c6354lQ.n();
            C2254p.f(str);
            C2254p.l(c0956i2U);
            byte[] bArrL = c0956i2U.l();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", num);
            contentValues.put("current_results", bArrL);
            try {
                if (c6354lQ.B().insertWithOnConflict("audience_filter_values", null, contentValues, 5) == -1) {
                    c6354lQ.j().G().b("Failed to insert filter results (got -1). appId", V1.v(str));
                }
            } catch (SQLiteException e) {
                c6354lQ.j().G().c("Error storing filter results. appId", V1.v(str), e);
            }
        }
        return arrayList;
    }

    private final K5 y(Integer num) {
        if (this.f.containsKey(num)) {
            return this.f.get(num);
        }
        K5 k5 = new K5(this, this.d);
        this.f.put(num, k5);
        return k5;
    }

    final List<ax.H6.Z1> A(String str, List<C0893b2> list, List<C0974k2> list2, Long l, Long l2, boolean z) {
        boolean z2;
        HashSet hashSet;
        Map<Integer, C0956i2> map;
        List<ax.H6.D1> list3;
        boolean z3;
        HashSet hashSet2;
        Map<Integer, List<Integer>> map2;
        Iterator<Integer> it;
        C2254p.f(str);
        C2254p.l(list);
        C2254p.l(list2);
        this.d = str;
        this.e = new HashSet();
        this.f = new ax.B.a();
        this.g = l;
        this.h = l2;
        Iterator<C0893b2> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z2 = false;
                break;
            }
            if ("_s".equals(it2.next().f0())) {
                z2 = true;
                break;
            }
        }
        boolean z4 = H6.a() && d().G(this.d, F.l0);
        boolean z5 = H6.a() && d().G(this.d, F.k0);
        if (z2) {
            C6354l c6354lQ = q();
            String str2 = this.d;
            c6354lQ.u();
            c6354lQ.n();
            C2254p.f(str2);
            ContentValues contentValues = new ContentValues();
            contentValues.put("current_session_count", (Integer) 0);
            try {
                c6354lQ.B().update("events", contentValues, "app_id = ?", new String[]{str2});
            } catch (SQLiteException e) {
                c6354lQ.j().G().c("Error resetting session-scoped event counts. appId", V1.v(str2), e);
            }
        }
        Map<Integer, List<ax.H6.D1>> mapU0 = Collections.EMPTY_MAP;
        if (z5 && z4) {
            mapU0 = q().U0(this.d);
        }
        Map<Integer, C0956i2> mapT0 = q().T0(this.d);
        if (!mapT0.isEmpty()) {
            HashSet hashSet3 = new HashSet(mapT0.keySet());
            if (z2) {
                String str3 = this.d;
                Map<Integer, List<Integer>> mapV0 = q().V0(this.d);
                C2254p.f(str3);
                C2254p.l(mapT0);
                ax.B.a aVar = new ax.B.a();
                if (!mapT0.isEmpty()) {
                    Iterator<Integer> it3 = mapT0.keySet().iterator();
                    while (it3.hasNext()) {
                        Integer next = it3.next();
                        next.intValue();
                        C0956i2 c0956i2 = mapT0.get(next);
                        List<Integer> list4 = mapV0.get(next);
                        if (list4 == null || list4.isEmpty()) {
                            hashSet2 = hashSet3;
                            map2 = mapV0;
                            it = it3;
                            aVar.put(next, c0956i2);
                        } else {
                            hashSet2 = hashSet3;
                            List<Long> listP = o().P(c0956i2.d0(), list4);
                            if (listP.isEmpty()) {
                                hashSet3 = hashSet2;
                            } else {
                                C0956i2.a aVarB = c0956i2.B().z().B(listP);
                                aVarB.E().F(o().P(c0956i2.f0(), list4));
                                ArrayList arrayList = new ArrayList();
                                for (C0884a2 c0884a2 : c0956i2.c0()) {
                                    Map<Integer, List<Integer>> map3 = mapV0;
                                    Iterator<Integer> it4 = it3;
                                    if (!list4.contains(Integer.valueOf(c0884a2.n()))) {
                                        arrayList.add(c0884a2);
                                    }
                                    mapV0 = map3;
                                    it3 = it4;
                                }
                                map2 = mapV0;
                                it = it3;
                                aVarB.v().w(arrayList);
                                ArrayList arrayList2 = new ArrayList();
                                for (C0965j2 c0965j2 : c0956i2.e0()) {
                                    if (!list4.contains(Integer.valueOf(c0965j2.N()))) {
                                        arrayList2.add(c0965j2);
                                    }
                                }
                                aVarB.C().D(arrayList2);
                                aVar.put(next, (C0956i2) ((AbstractC0994m4) aVarB.y()));
                            }
                        }
                        hashSet3 = hashSet2;
                        mapV0 = map2;
                        it3 = it;
                    }
                }
                hashSet = hashSet3;
                map = aVar;
            } else {
                hashSet = hashSet3;
                map = mapT0;
            }
            Iterator it5 = hashSet.iterator();
            while (it5.hasNext()) {
                Integer num = (Integer) it5.next();
                num.intValue();
                C0956i2 c0956i3 = map.get(num);
                BitSet bitSet = new BitSet();
                BitSet bitSet2 = new BitSet();
                C0956i2 c0956i4 = c0956i3;
                ax.B.a aVar2 = new ax.B.a();
                if (c0956i4 != null && c0956i4.n() != 0) {
                    Iterator<C0884a2> it6 = c0956i4.c0().iterator();
                    while (it6.hasNext()) {
                        C0884a2 next2 = it6.next();
                        if (next2.S()) {
                            aVar2.put(Integer.valueOf(next2.n()), next2.Q() ? Long.valueOf(next2.N()) : null);
                        } else {
                            it6 = it6;
                        }
                        it6 = it6;
                        c0956i4 = c0956i4;
                    }
                }
                C0956i2 c0956i5 = c0956i4;
                ax.B.a aVar3 = new ax.B.a();
                if (c0956i5 != null && c0956i5.Q() != 0) {
                    Iterator<C0965j2> it7 = c0956i5.e0().iterator();
                    while (it7.hasNext()) {
                        Iterator<C0965j2> it8 = it7;
                        C0965j2 next3 = it7.next();
                        if (next3.T() && next3.n() > 0) {
                            aVar3.put(Integer.valueOf(next3.N()), Long.valueOf(next3.J(next3.n() - 1)));
                        }
                        it7 = it8;
                        map = map;
                        it5 = it5;
                    }
                }
                Map<Integer, C0956i2> map4 = map;
                Iterator it9 = it5;
                if (c0956i5 != null) {
                    int i = 0;
                    while (i < (c0956i5.V() << 6)) {
                        if (x5.f0(c0956i5.f0(), i)) {
                            z3 = z4;
                            j().K().c("Filter already evaluated. audience ID, filter ID", num, Integer.valueOf(i));
                            bitSet2.set(i);
                            if (x5.f0(c0956i5.d0(), i)) {
                                bitSet.set(i);
                            }
                            i++;
                            z4 = z3;
                        } else {
                            z3 = z4;
                        }
                        aVar2.remove(Integer.valueOf(i));
                        i++;
                        z4 = z3;
                    }
                }
                boolean z6 = z4;
                C0956i2 c0956i6 = mapT0.get(num);
                if (z5 && z6 && (list3 = mapU0.get(num)) != null && this.h != null && this.g != null) {
                    for (ax.H6.D1 d1 : list3) {
                        int iO = d1.O();
                        Map<Integer, List<ax.H6.D1>> map5 = mapU0;
                        long jLongValue = this.h.longValue() / 1000;
                        if (d1.W()) {
                            jLongValue = this.g.longValue() / 1000;
                        }
                        if (aVar2.containsKey(Integer.valueOf(iO))) {
                            aVar2.put(Integer.valueOf(iO), Long.valueOf(jLongValue));
                        }
                        if (aVar3.containsKey(Integer.valueOf(iO))) {
                            aVar3.put(Integer.valueOf(iO), Long.valueOf(jLongValue));
                        }
                        mapU0 = map5;
                    }
                }
                this.f.put(num, new K5(this, this.d, c0956i6, bitSet, bitSet2, aVar2, aVar3));
                mapT0 = mapT0;
                z4 = z6;
                mapU0 = mapU0;
                map = map4;
                it5 = it9;
            }
        }
        if (!C1013o6.a() || !d().G(null, F.W0)) {
            C(list, true);
            B(list2);
            return E();
        }
        C(list, z);
        if (z) {
            return new ArrayList();
        }
        B(list2);
        return E();
    }

    @Override // com.google.android.gms.measurement.internal.AbstractC6353k5
    protected final boolean x() {
        return false;
    }

    final List<ax.H6.Z1> z(String str, List<C0893b2> list, List<C0974k2> list2, Long l, Long l2) {
        return A(str, list, list2, l, l2, false);
    }
}
