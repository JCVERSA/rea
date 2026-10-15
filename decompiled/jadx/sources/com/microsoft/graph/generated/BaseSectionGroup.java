package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Notebook;
import com.microsoft.graph.extensions.OnenoteEntityHierarchyModel;
import com.microsoft.graph.extensions.OnenoteSection;
import com.microsoft.graph.extensions.OnenoteSectionCollectionPage;
import com.microsoft.graph.extensions.SectionGroup;
import com.microsoft.graph.extensions.SectionGroupCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSectionGroup extends OnenoteEntityHierarchyModel implements d {

    @c("sectionsUrl")
    @ax.Z8.a
    public String r;

    @c("sectionGroupsUrl")
    @ax.Z8.a
    public String s;

    @c("parentNotebook")
    @ax.Z8.a
    public Notebook t;

    @c("parentSectionGroup")
    @ax.Z8.a
    public SectionGroup u;
    public transient OnenoteSectionCollectionPage v;
    public transient SectionGroupCollectionPage w;
    private transient l x;
    private transient e y;

    @Override // com.microsoft.graph.generated.BaseOnenoteEntityHierarchyModel, com.microsoft.graph.generated.BaseOnenoteEntitySchemaObjectModel, com.microsoft.graph.generated.BaseOnenoteEntityBaseModel, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.y = eVar;
        this.x = lVar;
        if (lVar.w("sections")) {
            BaseOnenoteSectionCollectionResponse baseOnenoteSectionCollectionResponse = new BaseOnenoteSectionCollectionResponse();
            if (lVar.w("sections@odata.nextLink")) {
                baseOnenoteSectionCollectionResponse.b = lVar.t("sections@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("sections").toString(), l[].class);
            OnenoteSection[] onenoteSectionArr = new OnenoteSection[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                OnenoteSection onenoteSection = (OnenoteSection) eVar.b(lVarArr[i].toString(), OnenoteSection.class);
                onenoteSectionArr[i] = onenoteSection;
                onenoteSection.c(eVar, lVarArr[i]);
            }
            baseOnenoteSectionCollectionResponse.a = Arrays.asList(onenoteSectionArr);
            this.v = new OnenoteSectionCollectionPage(baseOnenoteSectionCollectionResponse, null);
        }
        if (lVar.w("sectionGroups")) {
            BaseSectionGroupCollectionResponse baseSectionGroupCollectionResponse = new BaseSectionGroupCollectionResponse();
            if (lVar.w("sectionGroups@odata.nextLink")) {
                baseSectionGroupCollectionResponse.b = lVar.t("sectionGroups@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("sectionGroups").toString(), l[].class);
            SectionGroup[] sectionGroupArr = new SectionGroup[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                SectionGroup sectionGroup = (SectionGroup) eVar.b(lVarArr2[i2].toString(), SectionGroup.class);
                sectionGroupArr[i2] = sectionGroup;
                sectionGroup.c(eVar, lVarArr2[i2]);
            }
            baseSectionGroupCollectionResponse.a = Arrays.asList(sectionGroupArr);
            this.w = new SectionGroupCollectionPage(baseSectionGroupCollectionResponse, null);
        }
    }
}
