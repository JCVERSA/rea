package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.X0;

/* JADX INFO: loaded from: classes3.dex */
public class BaseRecurrenceRange implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("type")
    @ax.Z8.a
    public X0 c;

    @c("startDate")
    @ax.Z8.a
    public ax.p397ya.a d;

    @c("endDate")
    @ax.Z8.a
    public ax.p397ya.a e;

    @c("recurrenceTimeZone")
    @ax.Z8.a
    public String f;

    @c("numberOfOccurrences")
    @ax.Z8.a
    public Integer g;
    private transient l h;
    private transient e i;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
