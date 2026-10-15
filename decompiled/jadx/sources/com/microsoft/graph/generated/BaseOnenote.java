package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.Notebook;
import com.microsoft.graph.extensions.NotebookCollectionPage;
import com.microsoft.graph.extensions.OnenoteOperation;
import com.microsoft.graph.extensions.OnenoteOperationCollectionPage;
import com.microsoft.graph.extensions.OnenotePage;
import com.microsoft.graph.extensions.OnenotePageCollectionPage;
import com.microsoft.graph.extensions.OnenoteResource;
import com.microsoft.graph.extensions.OnenoteResourceCollectionPage;
import com.microsoft.graph.extensions.OnenoteSection;
import com.microsoft.graph.extensions.OnenoteSectionCollectionPage;
import com.microsoft.graph.extensions.SectionGroup;
import com.microsoft.graph.extensions.SectionGroupCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOnenote extends Entity implements d {
    public transient NotebookCollectionPage f;
    public transient OnenoteSectionCollectionPage g;
    public transient SectionGroupCollectionPage h;
    public transient OnenotePageCollectionPage i;
    public transient OnenoteResourceCollectionPage j;
    public transient OnenoteOperationCollectionPage k;
    private transient l l;
    private transient e m;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.m = eVar;
        this.l = lVar;
        if (lVar.w("notebooks")) {
            BaseNotebookCollectionResponse baseNotebookCollectionResponse = new BaseNotebookCollectionResponse();
            if (lVar.w("notebooks@odata.nextLink")) {
                baseNotebookCollectionResponse.b = lVar.t("notebooks@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("notebooks").toString(), l[].class);
            Notebook[] notebookArr = new Notebook[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                Notebook notebook = (Notebook) eVar.b(lVarArr[i].toString(), Notebook.class);
                notebookArr[i] = notebook;
                notebook.c(eVar, lVarArr[i]);
            }
            baseNotebookCollectionResponse.a = Arrays.asList(notebookArr);
            this.f = new NotebookCollectionPage(baseNotebookCollectionResponse, null);
        }
        if (lVar.w("sections")) {
            BaseOnenoteSectionCollectionResponse baseOnenoteSectionCollectionResponse = new BaseOnenoteSectionCollectionResponse();
            if (lVar.w("sections@odata.nextLink")) {
                baseOnenoteSectionCollectionResponse.b = lVar.t("sections@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("sections").toString(), l[].class);
            OnenoteSection[] onenoteSectionArr = new OnenoteSection[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                OnenoteSection onenoteSection = (OnenoteSection) eVar.b(lVarArr2[i2].toString(), OnenoteSection.class);
                onenoteSectionArr[i2] = onenoteSection;
                onenoteSection.c(eVar, lVarArr2[i2]);
            }
            baseOnenoteSectionCollectionResponse.a = Arrays.asList(onenoteSectionArr);
            this.g = new OnenoteSectionCollectionPage(baseOnenoteSectionCollectionResponse, null);
        }
        if (lVar.w("sectionGroups")) {
            BaseSectionGroupCollectionResponse baseSectionGroupCollectionResponse = new BaseSectionGroupCollectionResponse();
            if (lVar.w("sectionGroups@odata.nextLink")) {
                baseSectionGroupCollectionResponse.b = lVar.t("sectionGroups@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("sectionGroups").toString(), l[].class);
            SectionGroup[] sectionGroupArr = new SectionGroup[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                SectionGroup sectionGroup = (SectionGroup) eVar.b(lVarArr3[i3].toString(), SectionGroup.class);
                sectionGroupArr[i3] = sectionGroup;
                sectionGroup.c(eVar, lVarArr3[i3]);
            }
            baseSectionGroupCollectionResponse.a = Arrays.asList(sectionGroupArr);
            this.h = new SectionGroupCollectionPage(baseSectionGroupCollectionResponse, null);
        }
        if (lVar.w("pages")) {
            BaseOnenotePageCollectionResponse baseOnenotePageCollectionResponse = new BaseOnenotePageCollectionResponse();
            if (lVar.w("pages@odata.nextLink")) {
                baseOnenotePageCollectionResponse.b = lVar.t("pages@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("pages").toString(), l[].class);
            OnenotePage[] onenotePageArr = new OnenotePage[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                OnenotePage onenotePage = (OnenotePage) eVar.b(lVarArr4[i4].toString(), OnenotePage.class);
                onenotePageArr[i4] = onenotePage;
                onenotePage.c(eVar, lVarArr4[i4]);
            }
            baseOnenotePageCollectionResponse.a = Arrays.asList(onenotePageArr);
            this.i = new OnenotePageCollectionPage(baseOnenotePageCollectionResponse, null);
        }
        if (lVar.w("resources")) {
            BaseOnenoteResourceCollectionResponse baseOnenoteResourceCollectionResponse = new BaseOnenoteResourceCollectionResponse();
            if (lVar.w("resources@odata.nextLink")) {
                baseOnenoteResourceCollectionResponse.b = lVar.t("resources@odata.nextLink").j();
            }
            l[] lVarArr5 = (l[]) eVar.b(lVar.t("resources").toString(), l[].class);
            OnenoteResource[] onenoteResourceArr = new OnenoteResource[lVarArr5.length];
            for (int i5 = 0; i5 < lVarArr5.length; i5++) {
                OnenoteResource onenoteResource = (OnenoteResource) eVar.b(lVarArr5[i5].toString(), OnenoteResource.class);
                onenoteResourceArr[i5] = onenoteResource;
                onenoteResource.c(eVar, lVarArr5[i5]);
            }
            baseOnenoteResourceCollectionResponse.a = Arrays.asList(onenoteResourceArr);
            this.j = new OnenoteResourceCollectionPage(baseOnenoteResourceCollectionResponse, null);
        }
        if (lVar.w("operations")) {
            BaseOnenoteOperationCollectionResponse baseOnenoteOperationCollectionResponse = new BaseOnenoteOperationCollectionResponse();
            if (lVar.w("operations@odata.nextLink")) {
                baseOnenoteOperationCollectionResponse.b = lVar.t("operations@odata.nextLink").j();
            }
            l[] lVarArr6 = (l[]) eVar.b(lVar.t("operations").toString(), l[].class);
            OnenoteOperation[] onenoteOperationArr = new OnenoteOperation[lVarArr6.length];
            for (int i6 = 0; i6 < lVarArr6.length; i6++) {
                OnenoteOperation onenoteOperation = (OnenoteOperation) eVar.b(lVarArr6[i6].toString(), OnenoteOperation.class);
                onenoteOperationArr[i6] = onenoteOperation;
                onenoteOperation.c(eVar, lVarArr6[i6]);
            }
            baseOnenoteOperationCollectionResponse.a = Arrays.asList(onenoteOperationArr);
            this.k = new OnenoteOperationCollectionPage(baseOnenoteOperationCollectionResponse, null);
        }
    }
}
