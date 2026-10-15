package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.ItemReference;
import com.microsoft.graph.extensions.SharingInvitation;
import com.microsoft.graph.extensions.SharingLink;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BasePermission extends Entity implements d {

    @c("grantedTo")
    @ax.Z8.a
    public IdentitySet f;

    @c("inheritedFrom")
    @ax.Z8.a
    public ItemReference g;

    @c("invitation")
    @ax.Z8.a
    public SharingInvitation h;

    @c("link")
    @ax.Z8.a
    public SharingLink i;

    @c("roles")
    @ax.Z8.a
    public List<String> j;

    @c("shareId")
    @ax.Z8.a
    public String k;
    private transient l l;
    private transient e m;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.m = eVar;
        this.l = lVar;
    }
}
