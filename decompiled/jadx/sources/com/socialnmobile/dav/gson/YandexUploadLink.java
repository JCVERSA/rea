package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class YandexUploadLink {
    protected String href;
    protected String method;
    protected String operation_id;
    protected boolean templated;

    public String getHref() {
        return this.href;
    }

    public boolean getTemplated() {
        return this.templated;
    }
}
