package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.a1;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.UserActivity;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseActivityHistoryItem extends Entity implements d {

    @c("status")
    @ax.Z8.a
    public a1 f;

    @c("activeDurationSeconds")
    @ax.Z8.a
    public Integer g;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar h;

    @c("lastActiveDateTime")
    @ax.Z8.a
    public Calendar i;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar j;

    @c("expirationDateTime")
    @ax.Z8.a
    public Calendar k;

    @c("startedDateTime")
    @ax.Z8.a
    public Calendar l;

    @c("userTimezone")
    @ax.Z8.a
    public String m;

    @c("activity")
    @ax.Z8.a
    public UserActivity n;
    private transient l o;
    private transient e p;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.p = eVar;
        this.o = lVar;
    }
}
