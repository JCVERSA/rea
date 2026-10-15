package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookTableColumn;
import com.microsoft.graph.extensions.WorkbookTableColumnCollectionPage;
import com.microsoft.graph.extensions.WorkbookTableRow;
import com.microsoft.graph.extensions.WorkbookTableRowCollectionPage;
import com.microsoft.graph.extensions.WorkbookTableSort;
import com.microsoft.graph.extensions.WorkbookWorksheet;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookTable extends Entity implements d {

    @c("highlightFirstColumn")
    @ax.Z8.a
    public Boolean f;

    @c("highlightLastColumn")
    @ax.Z8.a
    public Boolean g;

    @c("name")
    @ax.Z8.a
    public String h;

    @c("showBandedColumns")
    @ax.Z8.a
    public Boolean i;

    @c("showBandedRows")
    @ax.Z8.a
    public Boolean j;

    @c("showFilterButton")
    @ax.Z8.a
    public Boolean k;

    @c("showHeaders")
    @ax.Z8.a
    public Boolean l;

    @c("showTotals")
    @ax.Z8.a
    public Boolean m;

    @c("style")
    @ax.Z8.a
    public String n;
    public transient WorkbookTableColumnCollectionPage o;
    public transient WorkbookTableRowCollectionPage p;

    @c("sort")
    @ax.Z8.a
    public WorkbookTableSort q;

    @c("worksheet")
    @ax.Z8.a
    public WorkbookWorksheet r;
    private transient l s;
    private transient e t;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.t = eVar;
        this.s = lVar;
        if (lVar.w("columns")) {
            BaseWorkbookTableColumnCollectionResponse baseWorkbookTableColumnCollectionResponse = new BaseWorkbookTableColumnCollectionResponse();
            if (lVar.w("columns@odata.nextLink")) {
                baseWorkbookTableColumnCollectionResponse.b = lVar.t("columns@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("columns").toString(), l[].class);
            WorkbookTableColumn[] workbookTableColumnArr = new WorkbookTableColumn[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                WorkbookTableColumn workbookTableColumn = (WorkbookTableColumn) eVar.b(lVarArr[i].toString(), WorkbookTableColumn.class);
                workbookTableColumnArr[i] = workbookTableColumn;
                workbookTableColumn.c(eVar, lVarArr[i]);
            }
            baseWorkbookTableColumnCollectionResponse.a = Arrays.asList(workbookTableColumnArr);
            this.o = new WorkbookTableColumnCollectionPage(baseWorkbookTableColumnCollectionResponse, null);
        }
        if (lVar.w("rows")) {
            BaseWorkbookTableRowCollectionResponse baseWorkbookTableRowCollectionResponse = new BaseWorkbookTableRowCollectionResponse();
            if (lVar.w("rows@odata.nextLink")) {
                baseWorkbookTableRowCollectionResponse.b = lVar.t("rows@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("rows").toString(), l[].class);
            WorkbookTableRow[] workbookTableRowArr = new WorkbookTableRow[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                WorkbookTableRow workbookTableRow = (WorkbookTableRow) eVar.b(lVarArr2[i2].toString(), WorkbookTableRow.class);
                workbookTableRowArr[i2] = workbookTableRow;
                workbookTableRow.c(eVar, lVarArr2[i2]);
            }
            baseWorkbookTableRowCollectionResponse.a = Arrays.asList(workbookTableRowArr);
            this.p = new WorkbookTableRowCollectionPage(baseWorkbookTableRowCollectionResponse, null);
        }
    }
}
