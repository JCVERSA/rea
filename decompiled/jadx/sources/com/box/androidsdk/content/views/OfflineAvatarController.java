package com.box.androidsdk.content.views;

import android.content.Context;
import ax.K3.h;
import com.box.androidsdk.content.models.BoxDownload;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class OfflineAvatarController extends DefaultAvatarController {
    final Context mContext;

    public OfflineAvatarController(Context context) {
        super(null);
        this.mContext = context.getApplicationContext();
    }

    @Override // com.box.androidsdk.content.views.DefaultAvatarController, com.box.androidsdk.content.views.BoxAvatarView.b
    public h<BoxDownload> b(String str, BoxAvatarView boxAvatarView) {
        return null;
    }

    @Override // com.box.androidsdk.content.views.DefaultAvatarController
    protected File f(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getFilesDir().getAbsolutePath());
        String str2 = File.separator;
        sb.append(str2);
        sb.append(str);
        sb.append(str2);
        sb.append("avatar");
        File file = new File(sb.toString());
        c(file, 30);
        return file;
    }
}
