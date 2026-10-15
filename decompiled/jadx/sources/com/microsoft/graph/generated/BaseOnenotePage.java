package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Notebook;
import com.microsoft.graph.extensions.OnenoteEntitySchemaObjectModel;
import com.microsoft.graph.extensions.OnenoteSection;
import com.microsoft.graph.extensions.PageLinks;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOnenotePage extends OnenoteEntitySchemaObjectModel implements d {

    @c("title")
    @ax.Z8.a
    public String l;

    @c("createdByAppId")
    @ax.Z8.a
    public String m;

    @c("links")
    @ax.Z8.a
    public PageLinks n;

    @c("contentUrl")
    @ax.Z8.a
    public String o;

    @c("lastModifiedDateTime")
    @ax.Z8.a
    public Calendar p;

    @c("level")
    @ax.Z8.a
    public Integer q;

    @c("order")
    @ax.Z8.a
    public Integer r;

    @c("userTags")
    @ax.Z8.a
    public List<String> s;

    @c("parentSection")
    @ax.Z8.a
    public OnenoteSection t;

    @c("parentNotebook")
    @ax.Z8.a
    public Notebook u;
    private transient l v;
    private transient e w;

    @Override // com.microsoft.graph.generated.BaseOnenoteEntitySchemaObjectModel, com.microsoft.graph.generated.BaseOnenoteEntityBaseModel, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.w = eVar;
        this.v = lVar;
    }
}
