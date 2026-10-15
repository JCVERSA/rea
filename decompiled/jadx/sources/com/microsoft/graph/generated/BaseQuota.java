package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseQuota implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("deleted")
    @ax.Z8.a
    public Long c;

    @c("remaining")
    @ax.Z8.a
    public Long d;

    @c("state")
    @ax.Z8.a
    public String e;

    @c("total")
    @ax.Z8.a
    public Long f;

    @c("used")
    @ax.Z8.a
    public Long g;
    private transient l h;
    private transient e i;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
