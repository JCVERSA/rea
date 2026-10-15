package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.P0;
import com.microsoft.graph.extensions.EmailAddress;
import com.microsoft.graph.extensions.Entity;

/* JADX INFO: loaded from: classes3.dex */
public class BaseInferenceClassificationOverride extends Entity implements d {

    @c("classifyAs")
    @ax.Z8.a
    public P0 f;

    @c("senderEmailAddress")
    @ax.Z8.a
    public EmailAddress g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }
}
