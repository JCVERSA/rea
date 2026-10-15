package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.U0;
import com.microsoft.graph.extensions.Entity;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOperation extends Entity implements d {

    @c("status")
    @ax.Z8.a
    public U0 f;

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar g;

    @c("lastActionDateTime")
    @ax.Z8.a
    public Calendar h;
    private transient l i;
    private transient e j;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }
}
