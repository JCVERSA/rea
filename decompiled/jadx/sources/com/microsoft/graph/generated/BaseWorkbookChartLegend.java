package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartLegendFormat;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartLegend extends Entity implements d {

    @c("overlay")
    @ax.Z8.a
    public Boolean f;

    @c("position")
    @ax.Z8.a
    public String g;

    @c("visible")
    @ax.Z8.a
    public Boolean h;

    @c("format")
    @ax.Z8.a
    public WorkbookChartLegendFormat i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }
}
