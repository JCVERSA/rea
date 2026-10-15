package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartAxisFormat;
import com.microsoft.graph.extensions.WorkbookChartAxisTitle;
import com.microsoft.graph.extensions.WorkbookChartGridlines;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartAxis extends Entity implements d {

    @c("majorUnit")
    @ax.Z8.a
    public i f;

    @c("maximum")
    @ax.Z8.a
    public i g;

    @c("minimum")
    @ax.Z8.a
    public i h;

    @c("minorUnit")
    @ax.Z8.a
    public i i;

    @c("format")
    @ax.Z8.a
    public WorkbookChartAxisFormat j;

    @c("majorGridlines")
    @ax.Z8.a
    public WorkbookChartGridlines k;

    @c("minorGridlines")
    @ax.Z8.a
    public WorkbookChartGridlines l;

    @c("title")
    @ax.Z8.a
    public WorkbookChartAxisTitle m;
    private transient l n;
    private transient e o;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.o = eVar;
        this.n = lVar;
    }
}
