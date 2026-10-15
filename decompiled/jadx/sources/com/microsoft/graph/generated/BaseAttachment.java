package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseAttachment extends Entity implements d {

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar f;

    @c("name")
    @ax.Z8.a
    public String g;

    @c("contentType")
    @ax.Z8.a
    public String h;

    @c("size")
    @ax.Z8.a
    public Integer i;

    @c("isInline")
    @ax.Z8.a
    public Boolean j;
    private transient l k;
    private transient e l;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.l = eVar;
        this.k = lVar;
    }
}
