package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.V0;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.IdentitySet;
import com.microsoft.graph.extensions.PlannerAppliedCategories;
import com.microsoft.graph.extensions.PlannerAssignedToTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerAssignments;
import com.microsoft.graph.extensions.PlannerBucketTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerProgressTaskBoardTaskFormat;
import com.microsoft.graph.extensions.PlannerTaskDetails;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BasePlannerTask extends Entity implements d {

    @c("assignedToTaskBoardFormat")
    @ax.Z8.a
    public PlannerAssignedToTaskBoardTaskFormat A;

    @c("progressTaskBoardFormat")
    @ax.Z8.a
    public PlannerProgressTaskBoardTaskFormat B;

    @c("bucketTaskBoardFormat")
    @ax.Z8.a
    public PlannerBucketTaskBoardTaskFormat C;
    private transient l D;
    private transient e E;

    @c("createdBy")
    @ax.Z8.a
    public IdentitySet f;

    @c("planId")
    @ax.Z8.a
    public String g;

    @c("bucketId")
    @ax.Z8.a
    public String h;

    @c("title")
    @ax.Z8.a
    public String i;

    @c("orderHint")
    @ax.Z8.a
    public String j;

    @c("assigneePriority")
    @ax.Z8.a
    public String k;

    @c("percentComplete")
    @ax.Z8.a
    public Integer l;

    @c("startDateTime")
    @ax.Z8.a
    public Calendar m;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar n;

    @c("dueDateTime")
    @ax.Z8.a
    public Calendar o;

    @c("hasDescription")
    @ax.Z8.a
    public Boolean p;

    @c("previewType")
    @ax.Z8.a
    public V0 q;

    @c("completedDateTime")
    @ax.Z8.a
    public Calendar r;

    @c("completedBy")
    @ax.Z8.a
    public IdentitySet s;

    @c("referenceCount")
    @ax.Z8.a
    public Integer t;

    @c("checklistItemCount")
    @ax.Z8.a
    public Integer u;

    @c("activeChecklistItemCount")
    @ax.Z8.a
    public Integer v;

    @c("appliedCategories")
    @ax.Z8.a
    public PlannerAppliedCategories w;

    @c("assignments")
    @ax.Z8.a
    public PlannerAssignments x;

    @c("conversationThreadId")
    @ax.Z8.a
    public String y;

    @c("details")
    @ax.Z8.a
    public PlannerTaskDetails z;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.E = eVar;
        this.D = lVar;
    }
}
