package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookWorksheet;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookNamedItem extends Entity implements d {

    @c("comment")
    @ax.Z8.a
    public String f;

    @c("name")
    @ax.Z8.a
    public String g;

    @c("scope")
    @ax.Z8.a
    public String h;

    @c("type")
    @ax.Z8.a
    public String i;

    @c("value")
    @ax.Z8.a
    public i j;

    @c("visible")
    @ax.Z8.a
    public Boolean k;

    @c("worksheet")
    @ax.Z8.a
    public WorkbookWorksheet l;
    private transient l m;
    private transient e n;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.n = eVar;
        this.m = lVar;
    }
}
