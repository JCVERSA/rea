package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.models.BoxVoid;
import com.box.androidsdk.content.requests.BoxRequest;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxRequestItemDelete<R extends BoxRequest<BoxVoid, R>> extends BoxRequest<BoxVoid, R> {
    protected String mId;

    public BoxRequestItemDelete(String str, String str2, BoxSession boxSession) {
        super(BoxVoid.class, str2, boxSession);
        this.mId = str;
        this.mRequestMethod = BoxRequest.Methods.DELETE;
    }
}
