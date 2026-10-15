package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseTextColumn implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("allowMultipleLines")
    @ax.Z8.a
    public Boolean c;

    @c("appendChangesToExistingText")
    @ax.Z8.a
    public Boolean d;

    @c("linesForEditing")
    @ax.Z8.a
    public Integer e;

    @c("maxLength")
    @ax.Z8.a
    public Integer f;

    @c("textType")
    @ax.Z8.a
    public String g;
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
