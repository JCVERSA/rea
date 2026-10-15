package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.OutlookCategory;
import com.microsoft.graph.extensions.OutlookCategoryCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOutlookUser extends Entity implements d {
    public transient OutlookCategoryCollectionPage f;
    private transient l g;
    private transient e h;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.h = eVar;
        this.g = lVar;
        if (lVar.w("masterCategories")) {
            BaseOutlookCategoryCollectionResponse baseOutlookCategoryCollectionResponse = new BaseOutlookCategoryCollectionResponse();
            if (lVar.w("masterCategories@odata.nextLink")) {
                baseOutlookCategoryCollectionResponse.b = lVar.t("masterCategories@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("masterCategories").toString(), l[].class);
            OutlookCategory[] outlookCategoryArr = new OutlookCategory[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                OutlookCategory outlookCategory = (OutlookCategory) eVar.b(lVarArr[i].toString(), OutlookCategory.class);
                outlookCategoryArr[i] = outlookCategory;
                outlookCategory.c(eVar, lVarArr[i]);
            }
            baseOutlookCategoryCollectionResponse.a = Arrays.asList(outlookCategoryArr);
            this.f = new OutlookCategoryCollectionPage(baseOutlookCategoryCollectionResponse, null);
        }
    }
}
