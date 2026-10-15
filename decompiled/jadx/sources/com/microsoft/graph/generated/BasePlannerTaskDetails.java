package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.V0;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.PlannerChecklistItems;
import com.microsoft.graph.extensions.PlannerExternalReferences;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerTaskDetails extends Entity implements d {

    @c("description")
    @ax.Z8.a
    public String f;

    @c("previewType")
    @ax.Z8.a
    public V0 g;

    @c("references")
    @ax.Z8.a
    public PlannerExternalReferences h;

    @c("checklist")
    @ax.Z8.a
    public PlannerChecklistItems i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }
}
