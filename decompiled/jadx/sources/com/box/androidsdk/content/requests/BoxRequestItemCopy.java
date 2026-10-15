package com.box.androidsdk.content.requests;

import ax.U4.d;
import com.box.androidsdk.content.models.BoxFolder;
import com.box.androidsdk.content.models.BoxItem;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.requests.BoxRequest;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
abstract class BoxRequestItemCopy<E extends BoxItem, R extends BoxRequest<E, R>> extends BoxRequestItem<E, R> {
    public BoxRequestItemCopy(Class<E> cls, String str, String str2, String str3, BoxSession boxSession) {
        super(cls, str, str3, boxSession);
        this.mRequestMethod = BoxRequest.Methods.POST;
        G(str2);
    }

    public R F(String str) {
        this.mBodyMap.put("name", str);
        return this;
    }

    public R G(String str) {
        this.mBodyMap.put("parent", BoxFolder.W(str));
        return this;
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected void v(d dVar, Map.Entry<String, Object> entry) {
        if (entry.getKey().equals("parent")) {
            dVar.A(entry.getKey(), w(entry.getValue()));
        } else {
            super.v(dVar, entry);
        }
    }
}
