package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.models.BoxIteratorItems;
import com.box.androidsdk.content.models.BoxSession;

/* JADX INFO: loaded from: classes.dex */
public class BoxRequestsFolder$GetFolderItems extends BoxRequestItem<BoxIteratorItems, BoxRequestsFolder$GetFolderItems> {
    private static final long serialVersionUID = 8123965031279971524L;

    public BoxRequestsFolder$GetFolderItems(String str, String str2, BoxSession boxSession) {
        super(BoxIteratorItems.class, str, str2, boxSession);
        this.mRequestMethod = BoxRequest.Methods.GET;
        this.mQueryMap.put("limit", "1000");
        this.mQueryMap.put("offset", "0");
    }

    public BoxRequestsFolder$GetFolderItems F(int i) {
        this.mQueryMap.put("limit", String.valueOf(i));
        return this;
    }

    public BoxRequestsFolder$GetFolderItems G(int i) {
        this.mQueryMap.put("offset", String.valueOf(i));
        return this;
    }
}
