package com.microsoft.graph.http;

import ax.Aa.e;
import ax.Y8.l;
import ax.p365wa.p;
import com.microsoft.graph.serializer.a;
import j$.util.DesugarCollections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseCollectionPage<T1, T2 extends p> implements IBaseCollectionPage<T1, T2> {
    private transient a a = new a(this);
    private final List<T1> b;
    private final T2 c;
    private transient l d;
    private transient e e;

    public BaseCollectionPage(List<T1> list, T2 t2) {
        this.b = DesugarCollections.unmodifiableList(list);
        this.c = t2;
    }

    @Override // com.microsoft.graph.http.IBaseCollectionPage
    public T2 a() {
        return this.c;
    }

    @Override // com.microsoft.graph.http.IBaseCollectionPage
    public List<T1> b() {
        return this.b;
    }

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.e = eVar;
        this.d = lVar;
    }

    @Override // ax.Aa.d
    public final a d() {
        return this.a;
    }
}
