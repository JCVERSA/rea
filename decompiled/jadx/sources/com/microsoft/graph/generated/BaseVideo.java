package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseVideo implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("audioBitsPerSample")
    @ax.Z8.a
    public Integer c;

    @c("audioChannels")
    @ax.Z8.a
    public Integer d;

    @c("audioFormat")
    @ax.Z8.a
    public String e;

    @c("audioSamplesPerSecond")
    @ax.Z8.a
    public Integer f;

    @c("bitrate")
    @ax.Z8.a
    public Integer g;

    @c("duration")
    @ax.Z8.a
    public Long h;

    @c("fourCC")
    @ax.Z8.a
    public String i;

    @c("frameRate")
    @ax.Z8.a
    public Double j;

    @c("height")
    @ax.Z8.a
    public Integer k;

    @c("width")
    @ax.Z8.a
    public Integer l;
    private transient l m;
    private transient e n;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.n = eVar;
        this.m = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
