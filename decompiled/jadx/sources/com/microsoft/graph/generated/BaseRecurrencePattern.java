package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2390f;
import ax.p333ua.W0;
import ax.p333ua.d1;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseRecurrencePattern implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("type")
    @ax.Z8.a
    public W0 c;

    @c("interval")
    @ax.Z8.a
    public Integer d;

    @c("month")
    @ax.Z8.a
    public Integer e;

    @c("dayOfMonth")
    @ax.Z8.a
    public Integer f;

    @c("daysOfWeek")
    @ax.Z8.a
    public List<EnumC2390f> g;

    @c("firstDayOfWeek")
    @ax.Z8.a
    public EnumC2390f h;

    @c("index")
    @ax.Z8.a
    public d1 i;
    private transient l j;
    private transient e k;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
