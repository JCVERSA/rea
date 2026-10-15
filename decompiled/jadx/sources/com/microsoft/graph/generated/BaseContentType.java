package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.ColumnLink;
import com.microsoft.graph.extensions.ColumnLinkCollectionPage;
import com.microsoft.graph.extensions.ContentTypeOrder;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.ItemReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseContentType extends Entity implements d {

    @c("description")
    @ax.Z8.a
    public String f;

    @c("group")
    @ax.Z8.a
    public String g;

    @c("hidden")
    @ax.Z8.a
    public Boolean h;

    @c("inheritedFrom")
    @ax.Z8.a
    public ItemReference i;

    @c("name")
    @ax.Z8.a
    public String j;

    @c("order")
    @ax.Z8.a
    public ContentTypeOrder k;

    @c("parentId")
    @ax.Z8.a
    public String l;

    @c("readOnly")
    @ax.Z8.a
    public Boolean m;

    @c("sealed")
    @ax.Z8.a
    public Boolean n;
    public transient ColumnLinkCollectionPage o;
    private transient l p;
    private transient e q;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.q = eVar;
        this.p = lVar;
        if (lVar.w("columnLinks")) {
            BaseColumnLinkCollectionResponse baseColumnLinkCollectionResponse = new BaseColumnLinkCollectionResponse();
            if (lVar.w("columnLinks@odata.nextLink")) {
                baseColumnLinkCollectionResponse.b = lVar.t("columnLinks@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("columnLinks").toString(), l[].class);
            ColumnLink[] columnLinkArr = new ColumnLink[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                ColumnLink columnLink = (ColumnLink) eVar.b(lVarArr[i].toString(), ColumnLink.class);
                columnLinkArr[i] = columnLink;
                columnLink.c(eVar, lVarArr[i]);
            }
            baseColumnLinkCollectionResponse.a = Arrays.asList(columnLinkArr);
            this.o = new ColumnLinkCollectionPage(baseColumnLinkCollectionResponse, null);
        }
    }
}
