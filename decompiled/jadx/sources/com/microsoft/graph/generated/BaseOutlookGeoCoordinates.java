package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOutlookGeoCoordinates implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("altitude")
    @ax.Z8.a
    public Double c;

    @c("latitude")
    @ax.Z8.a
    public Double d;

    @c("longitude")
    @ax.Z8.a
    public Double e;

    @c("accuracy")
    @ax.Z8.a
    public Double f;

    @c("altitudeAccuracy")
    @ax.Z8.a
    public Double g;
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
