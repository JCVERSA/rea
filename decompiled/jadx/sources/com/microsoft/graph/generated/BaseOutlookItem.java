package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOutlookItem extends Entity implements d {

    @c("createdDateTime")
    @ax.Z8.a
    public Calendar f;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar g;

    @c("changeKey")
    @ax.Z8.a
    public String h;

    @c("categories")
    @ax.Z8.a
    public List<String> i;
    private transient l j;
    private transient e k;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.k = eVar;
        this.j = lVar;
    }
}
