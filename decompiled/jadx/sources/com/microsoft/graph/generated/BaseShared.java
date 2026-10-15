package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.IdentitySet;
import java.util.Calendar;

/* JADX INFO: loaded from: classes3.dex */
public class BaseShared implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("owner")
    @ax.Z8.a
    public IdentitySet c;

    @c("scope")
    @ax.Z8.a
    public String d;

    @c("sharedBy")
    @ax.Z8.a
    public IdentitySet e;

    @c("sharedDateTime")
    @ax.Z8.a
    public Calendar f;
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
