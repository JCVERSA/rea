package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;
import ax.Z8.c;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Multistatus {
    protected String responsedescription;

    @c("response")
    protected List<Response> responses;

    @c("sync-token")
    protected String syncToken;

    public List<Response> getResponse() {
        return this.responses;
    }

    public String getResponseDescription() {
        return this.responsedescription;
    }
}
