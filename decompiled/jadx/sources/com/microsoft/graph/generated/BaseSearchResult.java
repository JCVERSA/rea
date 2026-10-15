package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSearchResult implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("onClickTelemetryUrl")
    @ax.Z8.a
    public String c;
    private transient l d;
    private transient e e;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.e = eVar;
        this.d = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
