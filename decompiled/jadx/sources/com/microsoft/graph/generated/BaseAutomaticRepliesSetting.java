package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2380a;
import ax.p333ua.EnumC2425x;
import com.microsoft.graph.extensions.DateTimeTimeZone;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAutomaticRepliesSetting implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("status")
    @ax.Z8.a
    public EnumC2380a c;

    @c("externalAudience")
    @ax.Z8.a
    public EnumC2425x d;

    @c("scheduledStartDateTime")
    @ax.Z8.a
    public DateTimeTimeZone e;

    @c("scheduledEndDateTime")
    @ax.Z8.a
    public DateTimeTimeZone f;

    @c("internalReplyMessage")
    @ax.Z8.a
    public String g;

    @c("externalReplyMessage")
    @ax.Z8.a
    public String h;
    private transient l i;
    private transient e j;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
