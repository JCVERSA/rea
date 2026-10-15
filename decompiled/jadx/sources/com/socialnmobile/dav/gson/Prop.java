package com.socialnmobile.dav.gson;

import androidx.annotation.Keep;
import ax.Z8.c;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class Prop {
    private Object acl;
    protected String creationdate;
    protected String displayname;
    protected String getcontentlanguage;
    protected String getcontentlength;
    protected String getcontenttype;
    protected String getetag;
    protected String getlastmodified;
    private Object group;

    @c("has-preview")
    private String hasPreview;
    private String hasthumbnail;
    protected Object lockdiscovery;
    private Object owner;

    @c("principal-collection-set")
    private Object principalCollectionSet;

    @c("principal-URL")
    private String principalURL;

    @c("quota-available-bytes")
    protected String quotaAvailableBytes;

    @c("quota-used-bytes")
    protected String quotaUsedBytes;
    private String readonly;
    protected Resourcetype resourcetype;

    @c("supported-report-set")
    protected Object supportedReportSet;
    protected Object supportedlock;
    private String visible;

    public Object getAcl() {
        return this.acl;
    }

    public String getCreationdate() {
        return this.creationdate;
    }

    public String getDisplayname() {
        return this.displayname;
    }

    public String getGetcontentlanguage() {
        return this.getcontentlanguage;
    }

    public String getGetcontentlength() {
        return this.getcontentlength;
    }

    public String getGetcontenttype() {
        return this.getcontenttype;
    }

    public String getGetetag() {
        return this.getetag;
    }

    public String getGetlastmodified() {
        return this.getlastmodified;
    }

    public Object getGroup() {
        return this.group;
    }

    public String getHasPreview() {
        return this.hasPreview;
    }

    public String getHasthumbnail() {
        return this.hasthumbnail;
    }

    public Object getLockdiscovery() {
        return this.lockdiscovery;
    }

    public Object getOwner() {
        return this.owner;
    }

    public Object getPrincipalCollectionSet() {
        return this.principalCollectionSet;
    }

    public String getPrincipalURL() {
        return this.principalURL;
    }

    public String getQuotaAvailableBytes() {
        return this.quotaAvailableBytes;
    }

    public String getQuotaUsedBytes() {
        return this.quotaUsedBytes;
    }

    public String getReadonly() {
        return this.readonly;
    }

    public Resourcetype getResourcetype() {
        return this.resourcetype;
    }

    public Object getSupportedReportSet() {
        return this.supportedReportSet;
    }

    public Object getSupportedlock() {
        return this.supportedlock;
    }

    public String getVisible() {
        return this.visible;
    }
}
