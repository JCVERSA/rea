package com.box.androidsdk.content.models;

/* JADX INFO: loaded from: classes.dex */
public class BoxUser extends BoxCollaborator {
    public static final String[] X = {"type", "id", "name", "login", "created_at", "modified_at", "role", "language", "timezone", "space_amount", "space_used", "max_upload_size", "tracking_codes", "can_see_managed_users", "is_sync_enabled", "is_external_collab_restricted", "status", "job_title", "phone", "address", "avatar_url", "is_exempt_from_device_limits", "is_exempt_from_login_verification", "enterprise", "hostname", "my_tags"};
    private static final long serialVersionUID = -9176113409457879123L;

    public String M() {
        return u("login");
    }

    public Long P() {
        return t("space_amount");
    }

    public Long Q() {
        return t("space_used");
    }
}
