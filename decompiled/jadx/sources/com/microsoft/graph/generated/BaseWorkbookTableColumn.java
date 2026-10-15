package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookFilter;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookTableColumn extends Entity implements d {

    @c("index")
    @ax.Z8.a
    public Integer f;

    @c("name")
    @ax.Z8.a
    public String g;

    @c("values")
    @ax.Z8.a
    public i h;

    @c("filter")
    @ax.Z8.a
    public WorkbookFilter i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }
}
