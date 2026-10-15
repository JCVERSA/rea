package com.box.androidsdk.content.models;

import ax.U4.d;
import java.util.Date;
import java.util.EnumSet;

/* JADX INFO: loaded from: classes.dex */
public abstract class BoxItem extends BoxEntity {
    private static final long serialVersionUID = 4876182952337609430L;
    protected transient EnumSet<Permission> X;

    public enum Permission {
        CAN_PREVIEW("can_preview"),
        CAN_DOWNLOAD("can_download"),
        CAN_UPLOAD("can_upload"),
        CAN_INVITE_COLLABORATOR("can_invite_collaborator"),
        CAN_RENAME("can_rename"),
        CAN_DELETE("can_delete"),
        CAN_SHARE("can_share"),
        CAN_SET_SHARE_ACCESS("can_set_share_access"),
        CAN_COMMENT("can_comment");

        private final String value;

        Permission(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    public BoxItem() {
        this.X = null;
    }

    public BoxItem(d dVar) {
        super(dVar);
        this.X = null;
    }

    protected Date K() {
        return p("content_modified_at");
    }

    public String M() {
        return u("item_status");
    }

    public Date P() {
        return p("modified_at");
    }

    public String Q() {
        return u("name");
    }

    public BoxFolder R() {
        return (BoxFolder) q(BoxJsonObject.m(BoxFolder.class), "parent");
    }

    public BoxIterator<BoxFolder> S() {
        return (BoxIterator) q(BoxJsonObject.m(BoxIteratorBoxEntity.class), "path_collection");
    }

    public EnumSet<Permission> T() {
        if (this.X == null) {
            V();
        }
        return this.X;
    }

    public Long U() {
        return t("size");
    }

    protected EnumSet<Permission> V() {
        BoxPermission boxPermission = (BoxPermission) q(BoxJsonObject.m(BoxPermission.class), "permissions");
        if (boxPermission == null) {
            return null;
        }
        EnumSet<Permission> enumSetD = boxPermission.D();
        this.X = enumSetD;
        return enumSetD;
    }
}
