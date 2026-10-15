package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.PlannerTask;
import com.microsoft.graph.extensions.PlannerTaskCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerBucket extends Entity implements d {

    @c("name")
    @ax.Z8.a
    public String f;

    @c("planId")
    @ax.Z8.a
    public String g;

    @c("orderHint")
    @ax.Z8.a
    public String h;
    public transient PlannerTaskCollectionPage i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
        if (lVar.w("tasks")) {
            BasePlannerTaskCollectionResponse basePlannerTaskCollectionResponse = new BasePlannerTaskCollectionResponse();
            if (lVar.w("tasks@odata.nextLink")) {
                basePlannerTaskCollectionResponse.b = lVar.t("tasks@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("tasks").toString(), l[].class);
            PlannerTask[] plannerTaskArr = new PlannerTask[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                PlannerTask plannerTask = (PlannerTask) eVar.b(lVarArr[i].toString(), PlannerTask.class);
                plannerTaskArr[i] = plannerTask;
                plannerTask.c(eVar, lVarArr[i]);
            }
            basePlannerTaskCollectionResponse.a = Arrays.asList(plannerTaskArr);
            this.i = new PlannerTaskCollectionPage(basePlannerTaskCollectionResponse, null);
        }
    }
}
