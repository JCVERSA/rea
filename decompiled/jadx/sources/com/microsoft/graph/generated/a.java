package com.microsoft.graph.generated;

import ax.p317ta.d;
import ax.p317ta.f;
import ax.p333ua.C2392g;
import ax.p333ua.C2394h;
import ax.p333ua.C2410p;
import ax.p333ua.M;
import ax.p349va.r;
import ax.p413za.c;
import com.microsoft.graph.extensions.DriveItem;
import com.microsoft.graph.extensions.DriveItemCollectionPage;
import com.microsoft.graph.extensions.IDriveItemCollectionPage;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class a extends ax.p365wa.a<BaseDriveItemCollectionResponse, IDriveItemCollectionPage> implements r {
    public a(String str, f fVar, List<c> list) {
        super(str, fVar, list, BaseDriveItemCollectionResponse.class, IDriveItemCollectionPage.class);
    }

    @Override // ax.p349va.r
    public DriveItem b(DriveItem driveItem) throws d {
        return new C2410p(m().d().toString(), m().m(), null).q(m().o()).b(driveItem);
    }

    @Override // ax.p349va.r
    public M f(String str) {
        l(new ax.p413za.d("$expand", str));
        return (C2392g) this;
    }

    @Override // ax.p349va.r
    public IDriveItemCollectionPage get() throws d {
        return o(n());
    }

    public IDriveItemCollectionPage o(BaseDriveItemCollectionResponse baseDriveItemCollectionResponse) {
        String str = baseDriveItemCollectionResponse.b;
        DriveItemCollectionPage driveItemCollectionPage = new DriveItemCollectionPage(baseDriveItemCollectionResponse, str != null ? new C2394h(str, m().m(), null) : null);
        driveItemCollectionPage.c(baseDriveItemCollectionResponse.f(), baseDriveItemCollectionResponse.e());
        return driveItemCollectionPage;
    }
}
