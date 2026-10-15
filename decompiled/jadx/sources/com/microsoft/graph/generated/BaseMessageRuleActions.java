package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import ax.p333ua.O0;
import com.microsoft.graph.extensions.Recipient;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMessageRuleActions implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("moveToFolder")
    @ax.Z8.a
    public String c;

    @c("copyToFolder")
    @ax.Z8.a
    public String d;

    @c("delete")
    @ax.Z8.a
    public Boolean e;

    @c("permanentDelete")
    @ax.Z8.a
    public Boolean f;

    @c("markAsRead")
    @ax.Z8.a
    public Boolean g;

    @c("markImportance")
    @ax.Z8.a
    public O0 h;

    @c("forwardTo")
    @ax.Z8.a
    public List<Recipient> i;

    @c("forwardAsAttachmentTo")
    @ax.Z8.a
    public List<Recipient> j;

    @c("redirectTo")
    @ax.Z8.a
    public List<Recipient> k;

    @c("assignCategories")
    @ax.Z8.a
    public List<String> l;

    @c("stopProcessingRules")
    @ax.Z8.a
    public Boolean m;
    private transient l n;
    private transient e o;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.o = eVar;
        this.n = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
