package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.Q0;
import ax.p333ua.R0;
import com.microsoft.graph.extensions.OutlookGeoCoordinates;
import com.microsoft.graph.extensions.PhysicalAddress;

/* JADX INFO: loaded from: classes3.dex */
public class BaseLocation implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("displayName")
    @ax.Z8.a
    public String c;

    @c("locationEmailAddress")
    @ax.Z8.a
    public String d;

    @c("address")
    @ax.Z8.a
    public PhysicalAddress e;

    @c("coordinates")
    @ax.Z8.a
    public OutlookGeoCoordinates f;

    @c("locationUri")
    @ax.Z8.a
    public String g;

    @c("locationType")
    @ax.Z8.a
    public Q0 h;

    @c("uniqueId")
    @ax.Z8.a
    public String i;

    @c("uniqueIdType")
    @ax.Z8.a
    public R0 j;
    private transient l k;
    private transient e l;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.l = eVar;
        this.k = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
