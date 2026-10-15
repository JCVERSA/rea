package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Notebook;
import com.microsoft.graph.extensions.OnenoteEntityHierarchyModel;
import com.microsoft.graph.extensions.OnenotePage;
import com.microsoft.graph.extensions.OnenotePageCollectionPage;
import com.microsoft.graph.extensions.SectionGroup;
import com.microsoft.graph.extensions.SectionLinks;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOnenoteSection extends OnenoteEntityHierarchyModel implements d {

    @c("isDefault")
    @ax.Z8.a
    public Boolean r;

    @c("links")
    @ax.Z8.a
    public SectionLinks s;

    @c("pagesUrl")
    @ax.Z8.a
    public String t;

    @c("parentNotebook")
    @ax.Z8.a
    public Notebook u;

    @c("parentSectionGroup")
    @ax.Z8.a
    public SectionGroup v;
    public transient OnenotePageCollectionPage w;
    private transient l x;
    private transient e y;

    @Override // com.microsoft.graph.generated.BaseOnenoteEntityHierarchyModel, com.microsoft.graph.generated.BaseOnenoteEntitySchemaObjectModel, com.microsoft.graph.generated.BaseOnenoteEntityBaseModel, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.y = eVar;
        this.x = lVar;
        if (lVar.w("pages")) {
            BaseOnenotePageCollectionResponse baseOnenotePageCollectionResponse = new BaseOnenotePageCollectionResponse();
            if (lVar.w("pages@odata.nextLink")) {
                baseOnenotePageCollectionResponse.b = lVar.t("pages@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("pages").toString(), l[].class);
            OnenotePage[] onenotePageArr = new OnenotePage[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                OnenotePage onenotePage = (OnenotePage) eVar.b(lVarArr[i].toString(), OnenotePage.class);
                onenotePageArr[i] = onenotePage;
                onenotePage.c(eVar, lVarArr[i]);
            }
            baseOnenotePageCollectionResponse.a = Arrays.asList(onenotePageArr);
            this.w = new OnenotePageCollectionPage(baseOnenotePageCollectionResponse, null);
        }
    }
}
