package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.BaseItem;
import com.microsoft.graph.extensions.ContentTypeInfo;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.FieldValueSet;
import com.microsoft.graph.extensions.ListItemVersion;
import com.microsoft.graph.extensions.ListItemVersionCollectionPage;
import com.microsoft.graph.extensions.SharepointIds;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseListItem extends BaseItem implements d {

    @c("contentType")
    @ax.Z8.a
    public ContentTypeInfo s;

    @c("sharepointIds")
    @ax.Z8.a
    public SharepointIds t;

    @c("driveItem")
    @ax.Z8.a
    public DriveItem u;

    @c("fields")
    @ax.Z8.a
    public FieldValueSet v;
    public transient ListItemVersionCollectionPage w;
    private transient l x;
    private transient e y;

    @Override // com.microsoft.graph.generated.BaseBaseItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.y = eVar;
        this.x = lVar;
        if (lVar.w("versions")) {
            BaseListItemVersionCollectionResponse baseListItemVersionCollectionResponse = new BaseListItemVersionCollectionResponse();
            if (lVar.w("versions@odata.nextLink")) {
                baseListItemVersionCollectionResponse.b = lVar.t("versions@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("versions").toString(), l[].class);
            ListItemVersion[] listItemVersionArr = new ListItemVersion[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                ListItemVersion listItemVersion = (ListItemVersion) eVar.b(lVarArr[i].toString(), ListItemVersion.class);
                listItemVersionArr[i] = listItemVersion;
                listItemVersion.c(eVar, lVarArr[i]);
            }
            baseListItemVersionCollectionResponse.a = Arrays.asList(listItemVersionArr);
            this.w = new ListItemVersionCollectionPage(baseListItemVersionCollectionResponse, null);
        }
    }
}
