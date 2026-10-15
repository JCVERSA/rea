package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookApplication;
import com.microsoft.graph.extensions.WorkbookFunctions;
import com.microsoft.graph.extensions.WorkbookNamedItem;
import com.microsoft.graph.extensions.WorkbookNamedItemCollectionPage;
import com.microsoft.graph.extensions.WorkbookTable;
import com.microsoft.graph.extensions.WorkbookTableCollectionPage;
import com.microsoft.graph.extensions.WorkbookWorksheet;
import com.microsoft.graph.extensions.WorkbookWorksheetCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbook extends Entity implements d {

    @c("application")
    @ax.Z8.a
    public WorkbookApplication f;
    public transient WorkbookNamedItemCollectionPage g;
    public transient WorkbookTableCollectionPage h;
    public transient WorkbookWorksheetCollectionPage i;

    @c("functions")
    @ax.Z8.a
    public WorkbookFunctions j;
    private transient l k;
    private transient e l;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.l = eVar;
        this.k = lVar;
        if (lVar.w("names")) {
            BaseWorkbookNamedItemCollectionResponse baseWorkbookNamedItemCollectionResponse = new BaseWorkbookNamedItemCollectionResponse();
            if (lVar.w("names@odata.nextLink")) {
                baseWorkbookNamedItemCollectionResponse.b = lVar.t("names@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("names").toString(), l[].class);
            WorkbookNamedItem[] workbookNamedItemArr = new WorkbookNamedItem[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                WorkbookNamedItem workbookNamedItem = (WorkbookNamedItem) eVar.b(lVarArr[i].toString(), WorkbookNamedItem.class);
                workbookNamedItemArr[i] = workbookNamedItem;
                workbookNamedItem.c(eVar, lVarArr[i]);
            }
            baseWorkbookNamedItemCollectionResponse.a = Arrays.asList(workbookNamedItemArr);
            this.g = new WorkbookNamedItemCollectionPage(baseWorkbookNamedItemCollectionResponse, null);
        }
        if (lVar.w("tables")) {
            BaseWorkbookTableCollectionResponse baseWorkbookTableCollectionResponse = new BaseWorkbookTableCollectionResponse();
            if (lVar.w("tables@odata.nextLink")) {
                baseWorkbookTableCollectionResponse.b = lVar.t("tables@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("tables").toString(), l[].class);
            WorkbookTable[] workbookTableArr = new WorkbookTable[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                WorkbookTable workbookTable = (WorkbookTable) eVar.b(lVarArr2[i2].toString(), WorkbookTable.class);
                workbookTableArr[i2] = workbookTable;
                workbookTable.c(eVar, lVarArr2[i2]);
            }
            baseWorkbookTableCollectionResponse.a = Arrays.asList(workbookTableArr);
            this.h = new WorkbookTableCollectionPage(baseWorkbookTableCollectionResponse, null);
        }
        if (lVar.w("worksheets")) {
            BaseWorkbookWorksheetCollectionResponse baseWorkbookWorksheetCollectionResponse = new BaseWorkbookWorksheetCollectionResponse();
            if (lVar.w("worksheets@odata.nextLink")) {
                baseWorkbookWorksheetCollectionResponse.b = lVar.t("worksheets@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("worksheets").toString(), l[].class);
            WorkbookWorksheet[] workbookWorksheetArr = new WorkbookWorksheet[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                WorkbookWorksheet workbookWorksheet = (WorkbookWorksheet) eVar.b(lVarArr3[i3].toString(), WorkbookWorksheet.class);
                workbookWorksheetArr[i3] = workbookWorksheet;
                workbookWorksheet.c(eVar, lVarArr3[i3]);
            }
            baseWorkbookWorksheetCollectionResponse.a = Arrays.asList(workbookWorksheetArr);
            this.i = new WorkbookWorksheetCollectionPage(baseWorkbookWorksheetCollectionResponse, null);
        }
    }
}
