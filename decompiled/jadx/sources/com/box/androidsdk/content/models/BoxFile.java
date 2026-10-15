package com.box.androidsdk.content.models;

import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class BoxFile extends BoxCollaborationItem {
    public static final String[] Y = {"type", "id", "file_version", "sequence_id", "etag", "sha1", "name", "created_at", "modified_at", "description", "size", "path_collection", "uploader_display_name", "created_by", "modified_by", "trashed_at", "purged_at", "content_created_at", "content_modified_at", "owned_by", "shared_link", "parent", "item_status", "version_number", "comment_count", "permissions", "extension", "is_package", "collections", "has_collaborations", "can_non_owners_invite", "is_externally_owned", "allowed_invitee_roles", "classification"};
    private static final long serialVersionUID = -4732748896882484735L;

    @Override // com.box.androidsdk.content.models.BoxItem
    public Date K() {
        return super.K();
    }

    @Override // com.box.androidsdk.content.models.BoxItem
    public Long U() {
        return super.U();
    }
}
