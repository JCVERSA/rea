package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Propstat {
    private Object error;
    private Prop prop;
    private String responsedescription;
    private String status;

    public Object getError() {
        return this.error;
    }

    public Prop getProp() {
        return this.prop;
    }

    public String getResponsedescription() {
        return this.responsedescription;
    }

    public String getStatus() {
        return this.status;
    }

    public void setProp(Prop prop) {
        this.prop = prop;
    }

    public void setResponsedescription(String str) {
        this.responsedescription = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
