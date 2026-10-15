package com.microsoft.graph.generated;

import ax.p317ta.d;
import ax.p317ta.f;
import ax.p333ua.r;
import ax.p349va.z;
import ax.p413za.c;
import com.microsoft.graph.extensions.DriveItemSearchCollectionPage;
import com.microsoft.graph.extensions.IDriveItemSearchCollectionPage;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class b extends ax.p365wa.a<BaseDriveItemSearchCollectionResponse, IDriveItemSearchCollectionPage> implements z {
    public b(String str, f fVar, List<c> list) {
        super(str, fVar, list, BaseDriveItemSearchCollectionResponse.class, IDriveItemSearchCollectionPage.class);
    }

    @Override // ax.p349va.z
    public IDriveItemSearchCollectionPage get() throws d {
        return o(n());
    }

    public IDriveItemSearchCollectionPage o(BaseDriveItemSearchCollectionResponse baseDriveItemSearchCollectionResponse) {
        String str = baseDriveItemSearchCollectionResponse.b;
        DriveItemSearchCollectionPage driveItemSearchCollectionPage = new DriveItemSearchCollectionPage(baseDriveItemSearchCollectionResponse, str != null ? new r(str, m().m(), null, null) : null);
        driveItemSearchCollectionPage.c(baseDriveItemSearchCollectionResponse.f(), baseDriveItemSearchCollectionResponse.e());
        return driveItemSearchCollectionPage;
    }
}
