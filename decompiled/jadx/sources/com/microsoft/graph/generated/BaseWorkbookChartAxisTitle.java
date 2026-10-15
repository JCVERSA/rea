package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartAxisTitleFormat;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartAxisTitle extends Entity implements d {

    @c("text")
    @ax.Z8.a
    public String f;

    @c("visible")
    @ax.Z8.a
    public Boolean g;

    @c("format")
    @ax.Z8.a
    public WorkbookChartAxisTitleFormat h;
    private transient l i;
    private transient e j;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }
}
