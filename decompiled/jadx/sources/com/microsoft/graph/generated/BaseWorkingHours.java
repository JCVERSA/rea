package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2390f;
import com.microsoft.graph.extensions.TimeZoneBase;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkingHours implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("daysOfWeek")
    @ax.Z8.a
    public List<EnumC2390f> c;

    @c("startTime")
    @ax.Z8.a
    public ax.p397ya.b d;

    @c("endTime")
    @ax.Z8.a
    public ax.p397ya.b e;

    @c("timeZone")
    @ax.Z8.a
    public TimeZoneBase f;
    private transient l g;
    private transient e h;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.h = eVar;
        this.g = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
