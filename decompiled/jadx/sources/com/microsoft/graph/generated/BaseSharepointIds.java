package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSharepointIds implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("listId")
    @ax.Z8.a
    public String c;

    @c("listItemId")
    @ax.Z8.a
    public String d;

    @c("listItemUniqueId")
    @ax.Z8.a
    public String e;

    @c("siteId")
    @ax.Z8.a
    public String f;

    @c("siteUrl")
    @ax.Z8.a
    public String g;

    @c("webId")
    @ax.Z8.a
    public String h;
    private transient l i;
    private transient e j;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
