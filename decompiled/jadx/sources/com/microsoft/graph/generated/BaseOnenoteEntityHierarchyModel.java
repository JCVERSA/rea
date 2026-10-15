package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.OnenoteEntitySchemaObjectModel;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOnenoteEntityHierarchyModel extends OnenoteEntitySchemaObjectModel implements d {

    @c("displayName")
    @ax.Z8.a
    public String l;

    @c("createdBy")
    @ax.Z8.a
    public IdentitySet m;

    @c("lastModifiedBy")
    @ax.Z8.a
    public IdentitySet n;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar o;
    private transient l p;
    private transient e q;

    @Override // com.microsoft.graph.generated.BaseOnenoteEntitySchemaObjectModel, com.microsoft.graph.generated.BaseOnenoteEntityBaseModel, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.q = eVar;
        this.p = lVar;
    }
}
