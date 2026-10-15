package com.box.androidsdk.content.models;

/* JADX INFO: loaded from: classes.dex */
public class BoxError extends BoxJsonObject {
    public String D() {
        return u("code");
    }

    public String E() {
        String strU = u("error");
        return strU == null ? D() : strU;
    }

    public String H() {
        return u("error_description");
    }
}
