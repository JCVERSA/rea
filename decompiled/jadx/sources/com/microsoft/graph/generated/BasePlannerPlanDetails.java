package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.PlannerCategoryDescriptions;
import com.microsoft.graph.extensions.PlannerUserIds;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerPlanDetails extends Entity implements d {

    @c("sharedWith")
    @ax.Z8.a
    public PlannerUserIds f;

    @c("categoryDescriptions")
    @ax.Z8.a
    public PlannerCategoryDescriptions g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }
}
