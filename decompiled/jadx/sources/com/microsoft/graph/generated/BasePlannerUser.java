package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.PlannerPlan;
import com.microsoft.graph.extensions.PlannerPlanCollectionPage;
import com.microsoft.graph.extensions.PlannerTask;
import com.microsoft.graph.extensions.PlannerTaskCollectionPage;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerUser extends Entity implements d {
    public transient PlannerTaskCollectionPage f;
    public transient PlannerPlanCollectionPage g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
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
            this.f = new PlannerTaskCollectionPage(basePlannerTaskCollectionResponse, null);
        }
        if (lVar.w("plans")) {
            BasePlannerPlanCollectionResponse basePlannerPlanCollectionResponse = new BasePlannerPlanCollectionResponse();
            if (lVar.w("plans@odata.nextLink")) {
                basePlannerPlanCollectionResponse.b = lVar.t("plans@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("plans").toString(), l[].class);
            PlannerPlan[] plannerPlanArr = new PlannerPlan[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                PlannerPlan plannerPlan = (PlannerPlan) eVar.b(lVarArr2[i2].toString(), PlannerPlan.class);
                plannerPlanArr[i2] = plannerPlan;
                plannerPlan.c(eVar, lVarArr2[i2]);
            }
            basePlannerPlanCollectionResponse.a = Arrays.asList(plannerPlanArr);
            this.g = new PlannerPlanCollectionPage(basePlannerPlanCollectionResponse, null);
        }
    }
}
