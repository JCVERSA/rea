package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.Thumbnail;

/* JADX INFO: loaded from: classes3.dex */
public class BaseThumbnailSet extends Entity implements d {

    @c("large")
    @ax.Z8.a
    public Thumbnail f;

    @c("medium")
    @ax.Z8.a
    public Thumbnail g;

    @c("small")
    @ax.Z8.a
    public Thumbnail h;

    @c("source")
    @ax.Z8.a
    public Thumbnail i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }
}
