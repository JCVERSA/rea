package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes3.dex */
public class BaseLicenseDetails extends Entity implements d {

    @c("servicePlans")
    @ax.Z8.a
    public List<Object> f;

    @c("skuId")
    @ax.Z8.a
    public UUID g;

    @c("skuPartNumber")
    @ax.Z8.a
    public String h;
    private transient l i;
    private transient e j;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.j = eVar;
        this.i = lVar;
    }
}
