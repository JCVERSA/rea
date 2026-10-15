package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseCalculatedColumn implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("format")
    @ax.Z8.a
    public String c;

    @c("formula")
    @ax.Z8.a
    public String d;

    @c("outputType")
    @ax.Z8.a
    public String e;
    private transient l f;
    private transient e g;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.g = eVar;
        this.f = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
