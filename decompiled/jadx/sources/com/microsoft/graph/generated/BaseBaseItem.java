package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.ItemReference;
import com.microsoft.graph.extensions.User;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseBaseItem extends Entity implements d {

    @c("createdBy")
    @ax.Z8.a
    public IdentitySet f;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar g;

    @c("description")
    @ax.Z8.a
    public String h;

    @c("eTag")
    @ax.Z8.a
    public String i;

    @c("lastModifiedBy")
    @ax.Z8.a
    public IdentitySet j;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar k;

    @c("name")
    @ax.Z8.a
    public String l;

    @c("parentReference")
    @ax.Z8.a
    public ItemReference m;

    @c("webUrl")
    @ax.Z8.a
    public String n;

    @c("createdByUser")
    @ax.Z8.a
    public User o;

    @c("lastModifiedByUser")
    @ax.Z8.a
    public User p;
    private transient l q;
    private transient e r;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.r = eVar;
        this.q = lVar;
    }
}
