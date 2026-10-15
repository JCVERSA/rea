package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BasePhoto implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("cameraMake")
    @ax.Z8.a
    public String c;

    @c("cameraModel")
    @ax.Z8.a
    public String d;

    @c("exposureDenominator")
    @ax.Z8.a
    public Double e;

    @c("exposureNumerator")
    @ax.Z8.a
    public Double f;

    @c("fNumber")
    @ax.Z8.a
    public Double g;

    @c("focalLength")
    @ax.Z8.a
    public Double h;

    @c("iso")
    @ax.Z8.a
    public Integer i;

    @c("takenDateTime")
    @ax.Z8.a
    public Calendar j;
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
