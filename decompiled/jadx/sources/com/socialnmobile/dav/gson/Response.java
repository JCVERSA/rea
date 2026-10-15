package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;
import ax.Z8.c;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Response {
    private Object error;
    private String href;

    @c("propstat")
    private List<Propstat> propstats;
    private String responsedescription;
    private String status;

    public Object getError() {
        return this.error;
    }

    public String getHref() {
        String str = this.href;
        return str == null ? HttpUrl.FRAGMENT_ENCODE_SET : str;
    }

    public List<Propstat> getPropstat() {
        if (this.propstats == null) {
            this.propstats = new ArrayList();
        }
        return this.propstats;
    }

    public String getResponsedescription() {
        return this.responsedescription;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
