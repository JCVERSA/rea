package com.box.androidsdk.content.models;

import android.text.TextUtils;
import ax.U4.d;

/* JADX INFO: loaded from: classes.dex */
public class BoxFolder extends BoxCollaborationItem {
    public static final String[] Y = {"type", "sha1", "id", "sequence_id", "etag", "name", "created_at", "modified_at", "description", "size", "path_collection", "created_by", "modified_by", "trashed_at", "purged_at", "content_created_at", "content_modified_at", "owned_by", "shared_link", "folder_upload_email", "parent", "item_status", "item_collection", "sync_state", "has_collaborations", "permissions", "can_non_owners_invite", "is_externally_owned", "allowed_invitee_roles", "collections", "classification"};
    private static final long serialVersionUID = 8020073615785970254L;

    public enum SyncState {
        SYNCED("synced"),
        NOT_SYNCED("not_synced"),
        PARTIALLY_SYNCED("partially_synced");

        private final String mValue;

        SyncState(String str) {
            this.mValue = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mValue;
        }
    }

    public BoxFolder() {
    }

    public BoxFolder(d dVar) {
        super(dVar);
    }

    public static BoxFolder W(String str) {
        return X(str, null);
    }

    public static BoxFolder X(String str, String str2) {
        d dVar = new d();
        dVar.D("id", str);
        dVar.D("type", "folder");
        if (!TextUtils.isEmpty(str2)) {
            dVar.D("name", str2);
        }
        return new BoxFolder(dVar);
    }

    @Override // com.box.androidsdk.content.models.BoxItem
    public Long U() {
        return super.U();
    }
}
