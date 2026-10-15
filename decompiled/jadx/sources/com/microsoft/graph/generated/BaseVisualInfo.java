package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.ImageInfo;

/* JADX INFO: loaded from: classes3.dex */
public class BaseVisualInfo implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("attribution")
    @ax.Z8.a
    public ImageInfo c;

    @c("backgroundColor")
    @ax.Z8.a
    public String d;

    @c("description")
    @ax.Z8.a
    public String e;

    @c("displayText")
    @ax.Z8.a
    public String f;

    @c("content")
    @ax.Z8.a
    public i g;
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
