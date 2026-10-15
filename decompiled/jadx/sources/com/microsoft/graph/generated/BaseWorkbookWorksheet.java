package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookChart;
import com.microsoft.graph.extensions.WorkbookChartCollectionPage;
import com.microsoft.graph.extensions.WorkbookNamedItem;
import com.microsoft.graph.extensions.WorkbookNamedItemCollectionPage;
import com.microsoft.graph.extensions.WorkbookPivotTable;
import com.microsoft.graph.extensions.WorkbookPivotTableCollectionPage;
import com.microsoft.graph.extensions.WorkbookTable;
import com.microsoft.graph.extensions.WorkbookTableCollectionPage;
import com.microsoft.graph.extensions.WorkbookWorksheetProtection;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookWorksheet extends Entity implements d {

    @c("name")
    @ax.Z8.a
    public String f;

    @c("position")
    @ax.Z8.a
    public Integer g;

    @c("visibility")
    @ax.Z8.a
    public String h;
    public transient WorkbookChartCollectionPage i;
    public transient WorkbookNamedItemCollectionPage j;
    public transient WorkbookPivotTableCollectionPage k;

    @c("protection")
    @ax.Z8.a
    public WorkbookWorksheetProtection l;
    public transient WorkbookTableCollectionPage m;
    private transient l n;
    private transient e o;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.o = eVar;
        this.n = lVar;
        if (lVar.w("charts")) {
            BaseWorkbookChartCollectionResponse baseWorkbookChartCollectionResponse = new BaseWorkbookChartCollectionResponse();
            if (lVar.w("charts@odata.nextLink")) {
                baseWorkbookChartCollectionResponse.b = lVar.t("charts@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("charts").toString(), l[].class);
            WorkbookChart[] workbookChartArr = new WorkbookChart[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                WorkbookChart workbookChart = (WorkbookChart) eVar.b(lVarArr[i].toString(), WorkbookChart.class);
                workbookChartArr[i] = workbookChart;
                workbookChart.c(eVar, lVarArr[i]);
            }
            baseWorkbookChartCollectionResponse.a = Arrays.asList(workbookChartArr);
            this.i = new WorkbookChartCollectionPage(baseWorkbookChartCollectionResponse, null);
        }
        if (lVar.w("names")) {
            BaseWorkbookNamedItemCollectionResponse baseWorkbookNamedItemCollectionResponse = new BaseWorkbookNamedItemCollectionResponse();
            if (lVar.w("names@odata.nextLink")) {
                baseWorkbookNamedItemCollectionResponse.b = lVar.t("names@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("names").toString(), l[].class);
            WorkbookNamedItem[] workbookNamedItemArr = new WorkbookNamedItem[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                WorkbookNamedItem workbookNamedItem = (WorkbookNamedItem) eVar.b(lVarArr2[i2].toString(), WorkbookNamedItem.class);
                workbookNamedItemArr[i2] = workbookNamedItem;
                workbookNamedItem.c(eVar, lVarArr2[i2]);
            }
            baseWorkbookNamedItemCollectionResponse.a = Arrays.asList(workbookNamedItemArr);
            this.j = new WorkbookNamedItemCollectionPage(baseWorkbookNamedItemCollectionResponse, null);
        }
        if (lVar.w("pivotTables")) {
            BaseWorkbookPivotTableCollectionResponse baseWorkbookPivotTableCollectionResponse = new BaseWorkbookPivotTableCollectionResponse();
            if (lVar.w("pivotTables@odata.nextLink")) {
                baseWorkbookPivotTableCollectionResponse.b = lVar.t("pivotTables@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("pivotTables").toString(), l[].class);
            WorkbookPivotTable[] workbookPivotTableArr = new WorkbookPivotTable[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                WorkbookPivotTable workbookPivotTable = (WorkbookPivotTable) eVar.b(lVarArr3[i3].toString(), WorkbookPivotTable.class);
                workbookPivotTableArr[i3] = workbookPivotTable;
                workbookPivotTable.c(eVar, lVarArr3[i3]);
            }
            baseWorkbookPivotTableCollectionResponse.a = Arrays.asList(workbookPivotTableArr);
            this.k = new WorkbookPivotTableCollectionPage(baseWorkbookPivotTableCollectionResponse, null);
        }
        if (lVar.w("tables")) {
            BaseWorkbookTableCollectionResponse baseWorkbookTableCollectionResponse = new BaseWorkbookTableCollectionResponse();
            if (lVar.w("tables@odata.nextLink")) {
                baseWorkbookTableCollectionResponse.b = lVar.t("tables@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("tables").toString(), l[].class);
            WorkbookTable[] workbookTableArr = new WorkbookTable[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                WorkbookTable workbookTable = (WorkbookTable) eVar.b(lVarArr4[i4].toString(), WorkbookTable.class);
                workbookTableArr[i4] = workbookTable;
                workbookTable.c(eVar, lVarArr4[i4]);
            }
            baseWorkbookTableCollectionResponse.a = Arrays.asList(workbookTableArr);
            this.m = new WorkbookTableCollectionPage(baseWorkbookTableCollectionResponse, null);
        }
    }
}
