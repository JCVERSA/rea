package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChartPoint;
import com.microsoft.graph.extensions.WorkbookChartPointCollectionPage;
import com.microsoft.graph.extensions.WorkbookChartSeriesFormat;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookChartSeries extends Entity implements d {

    @c("name")
    @ax.Z8.a
    public String f;

    @c("format")
    @ax.Z8.a
    public WorkbookChartSeriesFormat g;
    public transient WorkbookChartPointCollectionPage h;
    private transient l i;
    private transient e j;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
        if (lVar.w("points")) {
            BaseWorkbookChartPointCollectionResponse baseWorkbookChartPointCollectionResponse = new BaseWorkbookChartPointCollectionResponse();
            if (lVar.w("points@odata.nextLink")) {
                baseWorkbookChartPointCollectionResponse.b = lVar.t("points@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("points").toString(), l[].class);
            WorkbookChartPoint[] workbookChartPointArr = new WorkbookChartPoint[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                WorkbookChartPoint workbookChartPoint = (WorkbookChartPoint) eVar.b(lVarArr[i].toString(), WorkbookChartPoint.class);
                workbookChartPointArr[i] = workbookChartPoint;
                workbookChartPoint.c(eVar, lVarArr[i]);
            }
            baseWorkbookChartPointCollectionResponse.a = Arrays.asList(workbookChartPointArr);
            this.h = new WorkbookChartPointCollectionPage(baseWorkbookChartPointCollectionResponse, null);
        }
    }
}
