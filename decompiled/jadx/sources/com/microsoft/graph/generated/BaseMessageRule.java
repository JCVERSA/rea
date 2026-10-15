package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.MessageRuleActions;
import com.microsoft.graph.extensions.MessageRulePredicates;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMessageRule extends Entity implements d {

    @c("displayName")
    @ax.Z8.a
    public String f;

    @c("sequence")
    @ax.Z8.a
    public Integer g;

    @c("conditions")
    @ax.Z8.a
    public MessageRulePredicates h;

    @c("actions")
    @ax.Z8.a
    public MessageRuleActions i;

    @c("exceptions")
    @ax.Z8.a
    public MessageRulePredicates j;

    @c("isEnabled")
    @ax.Z8.a
    public Boolean k;

    @c("hasError")
    @ax.Z8.a
    public Boolean l;

    @c("isReadOnly")
    @ax.Z8.a
    public Boolean m;
    private transient l n;
    private transient e o;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.o = eVar;
        this.n = lVar;
    }
}
