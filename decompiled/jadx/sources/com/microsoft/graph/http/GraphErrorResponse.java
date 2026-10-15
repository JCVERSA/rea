package com.microsoft.graph.http;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p365wa.g;
import com.microsoft.graph.serializer.a;

/* JADX INFO: loaded from: classes3.dex */
public class GraphErrorResponse implements d {
    private transient a a = new a(this);

    @c("error")
    public g b;

    @ax.Z8.a(deserialize = false, serialize = false)
    public l c;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.c = lVar;
    }

    @Override // ax.Aa.d
    public final a d() {
        return this.a;
    }
}
