package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartDataLabelFormat;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartDataLabels extends Entity implements d {

    @c("position")
    @ax.Z8.a
    public String f;

    @c("separator")
    @ax.Z8.a
    public String g;

    @c("showBubbleSize")
    @ax.Z8.a
    public Boolean h;

    @c("showCategoryName")
    @ax.Z8.a
    public Boolean i;

    @c("showLegendKey")
    @ax.Z8.a
    public Boolean j;

    @c("showPercentage")
    @ax.Z8.a
    public Boolean k;

    @c("showSeriesName")
    @ax.Z8.a
    public Boolean l;

    @c("showValue")
    @ax.Z8.a
    public Boolean m;

    @c("format")
    @ax.Z8.a
    public WorkbookChartDataLabelFormat n;
    private transient l o;
    private transient e p;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.p = eVar;
        this.o = lVar;
    }
}
