package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.IdentitySet;

/* JADX INFO: loaded from: classes3.dex */
public class BaseSharingInvitation implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("email")
    @ax.Z8.a
    public String c;

    @c("invitedBy")
    @ax.Z8.a
    public IdentitySet d;

    @c("redeemedBy")
    @ax.Z8.a
    public String e;

    @c("signInRequired")
    @ax.Z8.a
    public Boolean f;
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
