package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.PublicationFacet;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseBaseItemVersion extends Entity implements d {

    @c("lastModifiedBy")
    @ax.Z8.a
    public IdentitySet f;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar g;

    @c("publication")
    @ax.Z8.a
    public PublicationFacet h;
    private transient l i;
    private transient e j;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }
}
