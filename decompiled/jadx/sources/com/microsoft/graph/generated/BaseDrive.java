package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.BaseItem;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.DriveItemCollectionPage;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.List;
import com.microsoft.graph.extensions.Quota;
import com.microsoft.graph.extensions.SharepointIds;
import com.microsoft.graph.extensions.SystemFacet;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseDrive extends BaseItem implements d {
    public transient DriveItemCollectionPage A;
    private transient l B;
    private transient e C;

    @c("driveType")
    @ax.Z8.a
    public String s;

    @c("owner")
    @ax.Z8.a
    public IdentitySet t;

    @c("quota")
    @ax.Z8.a
    public Quota u;

    @c("sharePointIds")
    @ax.Z8.a
    public SharepointIds v;

    @c("system")
    @ax.Z8.a
    public SystemFacet w;
    public transient DriveItemCollectionPage x;

    @c("list")
    @ax.Z8.a
    public List y;

    @c("root")
    @ax.Z8.a
    public DriveItem z;

    @Override // com.microsoft.graph.generated.BaseBaseItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.C = eVar;
        this.B = lVar;
        if (lVar.w("items")) {
            BaseDriveItemCollectionResponse baseDriveItemCollectionResponse = new BaseDriveItemCollectionResponse();
            if (lVar.w("items@odata.nextLink")) {
                baseDriveItemCollectionResponse.b = lVar.t("items@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("items").toString(), l[].class);
            DriveItem[] driveItemArr = new DriveItem[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                DriveItem driveItem = (DriveItem) eVar.b(lVarArr[i].toString(), DriveItem.class);
                driveItemArr[i] = driveItem;
                driveItem.c(eVar, lVarArr[i]);
            }
            baseDriveItemCollectionResponse.a = Arrays.asList(driveItemArr);
            this.x = new DriveItemCollectionPage(baseDriveItemCollectionResponse, null);
        }
        if (lVar.w("special")) {
            BaseDriveItemCollectionResponse baseDriveItemCollectionResponse2 = new BaseDriveItemCollectionResponse();
            if (lVar.w("special@odata.nextLink")) {
                baseDriveItemCollectionResponse2.b = lVar.t("special@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("special").toString(), l[].class);
            DriveItem[] driveItemArr2 = new DriveItem[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                DriveItem driveItem2 = (DriveItem) eVar.b(lVarArr2[i2].toString(), DriveItem.class);
                driveItemArr2[i2] = driveItem2;
                driveItem2.c(eVar, lVarArr2[i2]);
            }
            baseDriveItemCollectionResponse2.a = Arrays.asList(driveItemArr2);
            this.A = new DriveItemCollectionPage(baseDriveItemCollectionResponse2, null);
        }
    }
}
