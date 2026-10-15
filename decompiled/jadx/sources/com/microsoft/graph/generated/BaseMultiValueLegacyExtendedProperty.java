package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMultiValueLegacyExtendedProperty extends Entity implements d {

    @c("value")
    @ax.Z8.a
    public List<String> f;
    private transient l g;
    private transient e h;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.h = eVar;
        this.g = lVar;
    }
}
