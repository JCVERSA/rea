package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.BaseItem;
import com.microsoft.graph.extensions.ColumnDefinition;
import com.microsoft.graph.extensions.ColumnDefinitionCollectionPage;
import com.microsoft.graph.extensions.ContentType;
import com.microsoft.graph.extensions.ContentTypeCollectionPage;
import com.microsoft.graph.extensions.Drive;
import com.microsoft.graph.extensions.ListInfo;
import com.microsoft.graph.extensions.ListItem;
import com.microsoft.graph.extensions.ListItemCollectionPage;
import com.microsoft.graph.extensions.SharepointIds;
import com.microsoft.graph.extensions.SystemFacet;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseList extends BaseItem implements d {
    private transient l A;
    private transient e B;

    @c("displayName")
    @ax.Z8.a
    public String s;

    @c("list")
    @ax.Z8.a
    public ListInfo t;

    @c("sharepointIds")
    @ax.Z8.a
    public SharepointIds u;

    @c("system")
    @ax.Z8.a
    public SystemFacet v;
    public transient ColumnDefinitionCollectionPage w;
    public transient ContentTypeCollectionPage x;

    @c("drive")
    @ax.Z8.a
    public Drive y;
    public transient ListItemCollectionPage z;

    @Override // com.microsoft.graph.generated.BaseBaseItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.B = eVar;
        this.A = lVar;
        if (lVar.w("columns")) {
            BaseColumnDefinitionCollectionResponse baseColumnDefinitionCollectionResponse = new BaseColumnDefinitionCollectionResponse();
            if (lVar.w("columns@odata.nextLink")) {
                baseColumnDefinitionCollectionResponse.b = lVar.t("columns@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("columns").toString(), l[].class);
            ColumnDefinition[] columnDefinitionArr = new ColumnDefinition[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                ColumnDefinition columnDefinition = (ColumnDefinition) eVar.b(lVarArr[i].toString(), ColumnDefinition.class);
                columnDefinitionArr[i] = columnDefinition;
                columnDefinition.c(eVar, lVarArr[i]);
            }
            baseColumnDefinitionCollectionResponse.a = Arrays.asList(columnDefinitionArr);
            this.w = new ColumnDefinitionCollectionPage(baseColumnDefinitionCollectionResponse, null);
        }
        if (lVar.w("contentTypes")) {
            BaseContentTypeCollectionResponse baseContentTypeCollectionResponse = new BaseContentTypeCollectionResponse();
            if (lVar.w("contentTypes@odata.nextLink")) {
                baseContentTypeCollectionResponse.b = lVar.t("contentTypes@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("contentTypes").toString(), l[].class);
            ContentType[] contentTypeArr = new ContentType[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                ContentType contentType = (ContentType) eVar.b(lVarArr2[i2].toString(), ContentType.class);
                contentTypeArr[i2] = contentType;
                contentType.c(eVar, lVarArr2[i2]);
            }
            baseContentTypeCollectionResponse.a = Arrays.asList(contentTypeArr);
            this.x = new ContentTypeCollectionPage(baseContentTypeCollectionResponse, null);
        }
        if (lVar.w("items")) {
            BaseListItemCollectionResponse baseListItemCollectionResponse = new BaseListItemCollectionResponse();
            if (lVar.w("items@odata.nextLink")) {
                baseListItemCollectionResponse.b = lVar.t("items@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("items").toString(), l[].class);
            ListItem[] listItemArr = new ListItem[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                ListItem listItem = (ListItem) eVar.b(lVarArr3[i3].toString(), ListItem.class);
                listItemArr[i3] = listItem;
                listItem.c(eVar, lVarArr3[i3]);
            }
            baseListItemCollectionResponse.a = Arrays.asList(listItemArr);
            this.z = new ListItemCollectionPage(baseListItemCollectionResponse, null);
        }
    }
}
