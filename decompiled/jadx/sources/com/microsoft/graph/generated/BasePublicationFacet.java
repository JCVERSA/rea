package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BasePublicationFacet implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("level")
    @ax.Z8.a
    public String c;

    @c("versionId")
    @ax.Z8.a
    public String d;
    private transient l e;
    private transient e f;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.f = eVar;
        this.e = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
