package com.microsoft.graph.serializer;

import ax.Aa.d;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class a extends HashMap<String, i> {
    private final d q;

    public a(d dVar) {
        this.q = dVar;
    }

    private Set<String> b() {
        Field[] fields = this.q.getClass().getFields();
        HashSet hashSet = new HashSet();
        for (Field field : fields) {
            c cVar = (c) field.getAnnotation(c.class);
            if (cVar != null && field.getAnnotation(ax.Z8.a.class) != null) {
                hashSet.add(cVar.value());
            }
        }
        return hashSet;
    }

    private Set<String> c(l lVar) {
        HashSet hashSet = new HashSet();
        Iterator<Map.Entry<String, i>> it = lVar.s().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getKey());
        }
        return hashSet;
    }

    final void d(l lVar) {
        Set<String> setB = b();
        HashSet<String> hashSet = new HashSet(c(lVar));
        hashSet.removeAll(setB);
        for (String str : hashSet) {
            put(str, lVar.t(str));
        }
    }
}
