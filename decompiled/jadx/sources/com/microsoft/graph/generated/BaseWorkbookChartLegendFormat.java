package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartFill;
import com.microsoft.graph.extensions.WorkbookChartFont;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartLegendFormat extends Entity implements d {

    @c("fill")
    @ax.Z8.a
    public WorkbookChartFill f;

    @c("font")
    @ax.Z8.a
    public WorkbookChartFont g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }
}
