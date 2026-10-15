package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartAreaFormat;
import com.microsoft.graph.extensions.WorkbookChartAxes;
import com.microsoft.graph.extensions.WorkbookChartDataLabels;
import com.microsoft.graph.extensions.WorkbookChartLegend;
import com.microsoft.graph.extensions.WorkbookChartSeries;
import com.microsoft.graph.extensions.WorkbookChartSeriesCollectionPage;
import com.microsoft.graph.extensions.WorkbookChartTitle;
import com.microsoft.graph.extensions.WorkbookWorksheet;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChart extends Entity implements d {

    @c("height")
    @ax.Z8.a
    public Double f;

    @c("left")
    @ax.Z8.a
    public Double g;

    @c("name")
    @ax.Z8.a
    public String h;

    @c("top")
    @ax.Z8.a
    public Double i;

    @c("width")
    @ax.Z8.a
    public Double j;

    @c("axes")
    @ax.Z8.a
    public WorkbookChartAxes k;

    @c("dataLabels")
    @ax.Z8.a
    public WorkbookChartDataLabels l;

    @c("format")
    @ax.Z8.a
    public WorkbookChartAreaFormat m;

    @c("legend")
    @ax.Z8.a
    public WorkbookChartLegend n;
    public transient WorkbookChartSeriesCollectionPage o;

    @c("title")
    @ax.Z8.a
    public WorkbookChartTitle p;

    @c("worksheet")
    @ax.Z8.a
    public WorkbookWorksheet q;
    private transient l r;
    private transient e s;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.s = eVar;
        this.r = lVar;
        if (lVar.w("series")) {
            BaseWorkbookChartSeriesCollectionResponse baseWorkbookChartSeriesCollectionResponse = new BaseWorkbookChartSeriesCollectionResponse();
            if (lVar.w("series@odata.nextLink")) {
                baseWorkbookChartSeriesCollectionResponse.b = lVar.t("series@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("series").toString(), l[].class);
            WorkbookChartSeries[] workbookChartSeriesArr = new WorkbookChartSeries[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                WorkbookChartSeries workbookChartSeries = (WorkbookChartSeries) eVar.b(lVarArr[i].toString(), WorkbookChartSeries.class);
                workbookChartSeriesArr[i] = workbookChartSeries;
                workbookChartSeries.c(eVar, lVarArr[i]);
            }
            baseWorkbookChartSeriesCollectionResponse.a = Arrays.asList(workbookChartSeriesArr);
            this.o = new WorkbookChartSeriesCollectionPage(baseWorkbookChartSeriesCollectionResponse, null);
        }
    }
}
