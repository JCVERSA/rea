package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.OnenoteOperationError;
import com.microsoft.graph.extensions.Operation;

/* JADX INFO: loaded from: classes3.dex */
public class BaseOnenoteOperation extends Operation implements d {

    @c("resourceLocation")
    @ax.Z8.a
    public String k;

    @c("resourceId")
    @ax.Z8.a
    public String l;

    @c("error")
    @ax.Z8.a
    public OnenoteOperationError m;

    @c("percentComplete")
    @ax.Z8.a
    public String n;
    private transient l o;
    private transient e p;

    @Override // com.microsoft.graph.generated.BaseOperation, com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.p = eVar;
        this.o = lVar;
    }
}
