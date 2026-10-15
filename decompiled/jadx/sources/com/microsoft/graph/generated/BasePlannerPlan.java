package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.PlannerBucket;
import com.microsoft.graph.extensions.PlannerBucketCollectionPage;
import com.microsoft.graph.extensions.PlannerPlanDetails;
import com.microsoft.graph.extensions.PlannerTask;
import com.microsoft.graph.extensions.PlannerTaskCollectionPage;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerPlan extends Entity implements d {

    @c("createdBy")
    @ax.Z8.a
    public IdentitySet f;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar g;

    @c("owner")
    @ax.Z8.a
    public String h;

    @c("title")
    @ax.Z8.a
    public String i;
    public transient PlannerTaskCollectionPage j;
    public transient PlannerBucketCollectionPage k;

    @c("details")
    @ax.Z8.a
    public PlannerPlanDetails l;
    private transient l m;
    private transient e n;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.n = eVar;
        this.m = lVar;
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
            this.j = new PlannerTaskCollectionPage(basePlannerTaskCollectionResponse, null);
        }
        if (lVar.w("buckets")) {
            BasePlannerBucketCollectionResponse basePlannerBucketCollectionResponse = new BasePlannerBucketCollectionResponse();
            if (lVar.w("buckets@odata.nextLink")) {
                basePlannerBucketCollectionResponse.b = lVar.t("buckets@odata.nextLink").j();
            }
            l[] lVarArr2 = (l[]) eVar.b(lVar.t("buckets").toString(), l[].class);
            PlannerBucket[] plannerBucketArr = new PlannerBucket[lVarArr2.length];
            for (int i2 = 0; i2 < lVarArr2.length; i2++) {
                PlannerBucket plannerBucket = (PlannerBucket) eVar.b(lVarArr2[i2].toString(), PlannerBucket.class);
                plannerBucketArr[i2] = plannerBucket;
                plannerBucket.c(eVar, lVarArr2[i2]);
            }
            basePlannerBucketCollectionResponse.a = Arrays.asList(plannerBucketArr);
            this.k = new PlannerBucketCollectionPage(basePlannerBucketCollectionResponse, null);
        }
    }
}
