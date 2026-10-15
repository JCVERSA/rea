package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.EnumC2386d;
import com.microsoft.graph.extensions.Entity;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOutlookCategory extends Entity implements d {

    @c("displayName")
    @ax.Z8.a
    public String f;

    @c("color")
    @ax.Z8.a
    public EnumC2386d g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }
}
