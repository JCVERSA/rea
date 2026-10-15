package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Audio;
import com.microsoft.graph.extensions.BaseItem;
import com.microsoft.graph.extensions.Deleted;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.DriveItemCollectionPage;
import com.microsoft.graph.extensions.DriveItemVersion;
import com.microsoft.graph.extensions.DriveItemVersionCollectionPage;
import com.microsoft.graph.extensions.File;
import com.microsoft.graph.extensions.FileSystemInfo;
import com.microsoft.graph.extensions.Folder;
import com.microsoft.graph.extensions.GeoCoordinates;
import com.microsoft.graph.extensions.Image;
import com.microsoft.graph.extensions.ListItem;
import com.microsoft.graph.extensions.Package;
import com.microsoft.graph.extensions.Permission;
import com.microsoft.graph.extensions.PermissionCollectionPage;
import com.microsoft.graph.extensions.Photo;
import com.microsoft.graph.extensions.PublicationFacet;
import com.microsoft.graph.extensions.RemoteItem;
import com.microsoft.graph.extensions.Root;
import com.microsoft.graph.extensions.SearchResult;
import com.microsoft.graph.extensions.Shared;
import com.microsoft.graph.extensions.SharepointIds;
import com.microsoft.graph.extensions.SpecialFolder;
import com.microsoft.graph.extensions.ThumbnailSet;
import com.microsoft.graph.extensions.ThumbnailSetCollectionPage;
import com.microsoft.graph.extensions.Video;
import com.microsoft.graph.extensions.Workbook;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BaseDriveItem extends BaseItem implements d {

    @c("package")
    @ax.Z8.a
    public Package A;

    @c("photo")
    @ax.Z8.a
    public Photo B;

    @c("publication")
    @ax.Z8.a
    public PublicationFacet C;

    @c("remoteItem")
    @ax.Z8.a
    public RemoteItem D;

    @c("root")
    @ax.Z8.a
    public Root E;

    @c("searchResult")
    @ax.Z8.a
    public SearchResult F;

    @c("shared")
    @ax.Z8.a
    public Shared G;

    @c("sharepointIds")
    @ax.Z8.a
    public SharepointIds H;

    @c("size")
    @ax.Z8.a
    public Long I;

    @c("specialFolder")
    @ax.Z8.a
    public SpecialFolder J;

    @c("video")
    @ax.Z8.a
    public Video K;

    @c("webDavUrl")
    @ax.Z8.a
    public String L;
    public transient DriveItemCollectionPage M;

    @c("listItem")
    @ax.Z8.a
    public ListItem N;
    public transient PermissionCollectionPage O;
    public transient ThumbnailSetCollectionPage P;
    public transient DriveItemVersionCollectionPage Q;

    @c("workbook")
    @ax.Z8.a
    public Workbook R;
    private transient l S;
    private transient e T;

    @c("audio")
    @ax.Z8.a
    public Audio s;

    @c("cTag")
    @ax.Z8.a
    public String t;

    @c("deleted")
    @ax.Z8.a
    public Deleted u;

    @c("file")
    @ax.Z8.a
    public File v;

    @c("fileSystemInfo")
    @ax.Z8.a
    public FileSystemInfo w;

    @c("folder")
    @ax.Z8.a
    public Folder x;

    @c("image")
    @ax.Z8.a
    public Image y;

    @c("location")
    @ax.Z8.a
    public GeoCoordinates z;

    @Override // com.microsoft.graph.generated.BaseBaseItem, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.T = eVar;
        this.S = lVar;
        if (lVar.w("children")) {
            BaseDriveItemCollectionResponse baseDriveItemCollectionResponse = new BaseDriveItemCollectionResponse();
            if (lVar.w("children@odata.nextLink")) {
                baseDriveItemCollectionResponse.b = lVar.t("children@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("children").toString(), l[].class);
            DriveItem[] driveItemArr = new DriveItem[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                DriveItem driveItem = (DriveItem) eVar.b(lVarArr[i].toString(), DriveItem.class);
                driveItemArr[i] = driveItem;
                driveItem.c(eVar, lVarArr[i]);
            }
            baseDriveItemCollectionResponse.a = Arrays.asList(driveItemArr);
            this.M = new DriveItemCollectionPage(baseDriveItemCollectionResponse, null);
        }
        if (lVar.w("permissions")) {
            BasePermissionCollectionResponse basePermissionCollectionResponse = new BasePermissionCollectionResponse();
            if (lVar.w("permissions@odata.nextLink")) {
                basePermissionCollectionResponse.b = lVar.t("permissions@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("permissions").toString(), l[].class);
            Permission[] permissionArr = new Permission[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                Permission permission = (Permission) eVar.b(lVarArr2[i2].toString(), Permission.class);
                permissionArr[i2] = permission;
                permission.c(eVar, lVarArr2[i2]);
            }
            basePermissionCollectionResponse.a = Arrays.asList(permissionArr);
            this.O = new PermissionCollectionPage(basePermissionCollectionResponse, null);
        }
        if (lVar.w("thumbnails")) {
            BaseThumbnailSetCollectionResponse baseThumbnailSetCollectionResponse = new BaseThumbnailSetCollectionResponse();
            if (lVar.w("thumbnails@odata.nextLink")) {
                baseThumbnailSetCollectionResponse.b = lVar.t("thumbnails@odata.nextLink").j();
            }
            l[] lVarArr3 = (l[]) eVar.b(lVar.t("thumbnails").toString(), l[].class);
            ThumbnailSet[] thumbnailSetArr = new ThumbnailSet[lVarArr3.length];
            for (int i3 = 0; i3 < lVarArr3.length; i3++) {
                ThumbnailSet thumbnailSet = (ThumbnailSet) eVar.b(lVarArr3[i3].toString(), ThumbnailSet.class);
                thumbnailSetArr[i3] = thumbnailSet;
                thumbnailSet.c(eVar, lVarArr3[i3]);
            }
            baseThumbnailSetCollectionResponse.a = Arrays.asList(thumbnailSetArr);
            this.P = new ThumbnailSetCollectionPage(baseThumbnailSetCollectionResponse, null);
        }
        if (lVar.w("versions")) {
            BaseDriveItemVersionCollectionResponse baseDriveItemVersionCollectionResponse = new BaseDriveItemVersionCollectionResponse();
            if (lVar.w("versions@odata.nextLink")) {
                baseDriveItemVersionCollectionResponse.b = lVar.t("versions@odata.nextLink").j();
            }
            l[] lVarArr4 = (l[]) eVar.b(lVar.t("versions").toString(), l[].class);
            DriveItemVersion[] driveItemVersionArr = new DriveItemVersion[lVarArr4.length];
            for (int i4 = 0; i4 < lVarArr4.length; i4++) {
                DriveItemVersion driveItemVersion = (DriveItemVersion) eVar.b(lVarArr4[i4].toString(), DriveItemVersion.class);
                driveItemVersionArr[i4] = driveItemVersion;
                driveItemVersion.c(eVar, lVarArr4[i4]);
            }
            baseDriveItemVersionCollectionResponse.a = Arrays.asList(driveItemVersionArr);
            this.Q = new DriveItemVersionCollectionPage(baseDriveItemVersionCollectionResponse, null);
        }
    }

    public l e() {
        return this.S;
    }
}
