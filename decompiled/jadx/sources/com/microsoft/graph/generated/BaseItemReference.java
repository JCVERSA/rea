package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.SharepointIds;

/* JADX INFO: loaded from: classes3.dex */
public class BaseItemReference implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b = new com.microsoft.graph.serializer.a(this);

    @c("driveId")
    @ax.Z8.a
    public String c;

    @c("driveType")
    @ax.Z8.a
    public String d;

    @c("id")
    @ax.Z8.a
    public String e;

    @c("name")
    @ax.Z8.a
    public String f;

    @c("path")
    @ax.Z8.a
    public String g;

    @c("shareId")
    @ax.Z8.a
    public String h;

    @c("sharepointIds")
    @ax.Z8.a
    public SharepointIds i;
    private transient l j;
    private transient e k;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
