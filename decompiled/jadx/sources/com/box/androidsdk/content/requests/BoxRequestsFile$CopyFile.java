package com.box.androidsdk.content.requests;

import com.box.androidsdk.content.models.BoxFile;
import com.box.androidsdk.content.models.BoxSession;

/* JADX INFO: loaded from: classes.dex */
public class BoxRequestsFile$CopyFile extends BoxRequestItemCopy<BoxFile, BoxRequestsFile$CopyFile> {
    private static final long serialVersionUID = 8123965031279971533L;

    public BoxRequestsFile$CopyFile(String str, String str2, String str3, BoxSession boxSession) {
        super(BoxFile.class, str, str2, str3, boxSession);
    }

    @Override // com.box.androidsdk.content.requests.BoxRequestItemCopy
    public /* bridge */ /* synthetic */ BoxRequest F(String str) {
        return super.F(str);
    }

    @Override // com.box.androidsdk.content.requests.BoxRequestItemCopy
    public /* bridge */ /* synthetic */ BoxRequest G(String str) {
        return super.G(str);
    }
}
