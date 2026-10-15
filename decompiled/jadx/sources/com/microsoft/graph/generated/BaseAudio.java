package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAudio implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("album")
    @ax.Z8.a
    public String c;

    @c("albumArtist")
    @ax.Z8.a
    public String d;

    @c("artist")
    @ax.Z8.a
    public String e;

    @c("bitrate")
    @ax.Z8.a
    public Long f;

    @c("composers")
    @ax.Z8.a
    public String g;

    @c("copyright")
    @ax.Z8.a
    public String h;

    @c("disc")
    @ax.Z8.a
    public Integer i;

    @c("discCount")
    @ax.Z8.a
    public Integer j;

    @c("duration")
    @ax.Z8.a
    public Long k;

    @c("genre")
    @ax.Z8.a
    public String l;

    @c("hasDrm")
    @ax.Z8.a
    public Boolean m;

    @c("isVariableBitrate")
    @ax.Z8.a
    public Boolean n;

    @c("title")
    @ax.Z8.a
    public String o;

    @c("track")
    @ax.Z8.a
    public Integer p;

    @c("trackCount")
    @ax.Z8.a
    public Integer q;

    @c("year")
    @ax.Z8.a
    public Integer r;
    private transient l s;
    private transient e t;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.t = eVar;
        this.s = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
