package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class YandexAccountInfo {
    protected String id;
    protected String login;

    public String getAccountName() {
        return this.login;
    }
}
