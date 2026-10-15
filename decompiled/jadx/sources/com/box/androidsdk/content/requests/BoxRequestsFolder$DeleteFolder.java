package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.BoxException;
import com.box.androidsdk.content.models.BoxSession;
import com.box.androidsdk.content.models.BoxVoid;

/* JADX INFO: loaded from: classes.dex */
public class BoxRequestsFolder$DeleteFolder extends BoxRequestItemDelete<BoxRequestsFolder$DeleteFolder> {
    private static final long serialVersionUID = 8123965031279971594L;

    public BoxRequestsFolder$DeleteFolder(String str, String str2, BoxSession boxSession) {
        super(str, str2, boxSession);
        E(true);
    }

    public BoxRequestsFolder$DeleteFolder E(boolean z) {
        this.mQueryMap.put("recursive", z ? "true" : "false");
        return this;
    }

    @Override // com.box.androidsdk.content.requests.BoxRequest
    protected void u(BoxResponse<BoxVoid> boxResponse) throws BoxException {
        super.u(boxResponse);
        super.q(boxResponse);
    }
}
