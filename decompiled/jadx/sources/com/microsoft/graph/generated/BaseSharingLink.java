package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Identity;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSharingLink implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("application")
    @ax.Z8.a
    public Identity c;

    @c("scope")
    @ax.Z8.a
    public String d;

    @c("type")
    @ax.Z8.a
    public String e;

    @c("webUrl")
    @ax.Z8.a
    public String f;
    private transient l g;
    private transient e h;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.h = eVar;
        this.g = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
