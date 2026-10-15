package com.microsoft.graph.serializer;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import com.google.gson.Gson;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class b implements e {
    private final Gson a;
    private final ax.p381xa.b b;

    public b(ax.p381xa.b bVar) {
        this.b = bVar;
        this.a = GsonFactory.a(bVar);
    }

    private boolean c(Map.Entry<String, i> entry) {
        return entry.getKey().startsWith("@");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [ax.Y8.i] */
    /* JADX WARN: Type inference failed for: r0v3, types: [ax.Y8.i] */
    /* JADX WARN: Type inference failed for: r0v4, types: [ax.Y8.l] */
    @Override // ax.Aa.e
    public <T> String a(T t) {
        this.b.a("Serializing type " + t.getClass().getSimpleName());
        ?? Z = this.a.z(t);
        if (t instanceof d) {
            a aVarD = ((d) t).d();
            if (Z.o()) {
                Z = Z.g();
                for (Map.Entry<String, i> entry : aVarD.entrySet()) {
                    if (!c(entry)) {
                        Z.q(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
        return Z.toString();
    }

    @Override // ax.Aa.e
    public <T> T b(String str, Class<T> cls) {
        T t = (T) this.a.k(str, cls);
        if (!(t instanceof d)) {
            this.b.a("Deserializing a non-IJsonBackedObject type " + cls.getSimpleName());
            return t;
        }
        this.b.a("Deserializing type " + cls.getSimpleName());
        d dVar = (d) t;
        l lVar = (l) this.a.k(str, l.class);
        dVar.c(this, lVar);
        dVar.d().d(lVar);
        return t;
    }
}
