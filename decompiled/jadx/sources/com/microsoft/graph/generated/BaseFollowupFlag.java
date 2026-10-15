package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2427y;
import com.microsoft.graph.extensions.DateTimeTimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class BaseFollowupFlag implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("completedDateTime")
    @ax.Z8.a
    public DateTimeTimeZone c;

    @c("dueDateTime")
    @ax.Z8.a
    public DateTimeTimeZone d;

    @c("startDateTime")
    @ax.Z8.a
    public DateTimeTimeZone e;

    @c("flagStatus")
    @ax.Z8.a
    public EnumC2427y f;
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
