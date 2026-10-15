package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.models.BoxIteratorItems;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.utils.SdkUtils;

/* JADX INFO: loaded from: classes.dex */
public class BoxRequestsSearch$Search extends BoxRequestItem<BoxIteratorItems, BoxRequestsSearch$Search> {
    public static String k0 = "name";
    private static final long serialVersionUID = 8123965031279971584L;

    public BoxRequestsSearch$Search(String str, String str2, BoxSession boxSession) {
        super(BoxIteratorItems.class, null, str2, boxSession);
        H("query", str);
        this.mRequestMethod = BoxRequest.Methods.GET;
    }

    public BoxRequestsSearch$Search F(String[] strArr) {
        H("ancestor_folder_ids", SdkUtils.b(strArr, ","));
        return this;
    }

    public BoxRequestsSearch$Search G(String[] strArr) {
        H("content_types", SdkUtils.b(strArr, ","));
        return this;
    }

    public BoxRequestsSearch$Search H(String str, String str2) {
        this.mQueryMap.put(str, str2);
        return this;
    }

    public BoxRequestsSearch$Search I(int i) {
        H("limit", String.valueOf(i));
        return this;
    }
}
