package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.a1;
import com.microsoft.graph.extensions.ActivityHistoryItem;
import com.microsoft.graph.extensions.ActivityHistoryItemCollectionPage;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.VisualInfo;
import java.util.Arrays;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseUserActivity extends Entity implements d {

    @c("visualElements")
    @ax.Z8.a
    public VisualInfo f;

    @c("activitySourceHost")
    @ax.Z8.a
    public String g;

    @c("activationUrl")
    @ax.Z8.a
    public String h;

    @c("appActivityId")
    @ax.Z8.a
    public String i;

    @c("appDisplayName")
    @ax.Z8.a
    public String j;

    @c("contentUrl")
    @ax.Z8.a
    public String k;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar l;

    @c("expirationDateTime")
    @ax.Z8.a
    public Calendar m;

    @c("fallbackUrl")
    @ax.Z8.a
    public String n;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar o;

    @c("userTimezone")
    @ax.Z8.a
    public String p;

    @c("contentInfo")
    @ax.Z8.a
    public i q;

    @c("status")
    @ax.Z8.a
    public a1 r;
    public transient ActivityHistoryItemCollectionPage s;
    private transient l t;
    private transient e u;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.u = eVar;
        this.t = lVar;
        if (lVar.w("historyItems")) {
            BaseActivityHistoryItemCollectionResponse baseActivityHistoryItemCollectionResponse = new BaseActivityHistoryItemCollectionResponse();
            if (lVar.w("historyItems@odata.nextLink")) {
                baseActivityHistoryItemCollectionResponse.b = lVar.t("historyItems@odata.nextLink").j();
            }
            l[] lVarArr = (l[]) eVar.b(lVar.t("historyItems").toString(), l[].class);
            ActivityHistoryItem[] activityHistoryItemArr = new ActivityHistoryItem[lVarArr.length];
            for (int i = 0; i < lVarArr.length; i++) {
                ActivityHistoryItem activityHistoryItem = (ActivityHistoryItem) eVar.b(lVarArr[i].toString(), ActivityHistoryItem.class);
                activityHistoryItemArr[i] = activityHistoryItem;
                activityHistoryItem.c(eVar, lVarArr[i]);
            }
            baseActivityHistoryItemCollectionResponse.a = Arrays.asList(activityHistoryItemArr);
            this.s = new ActivityHistoryItemCollectionPage(baseActivityHistoryItemCollectionResponse, null);
        }
    }
}
