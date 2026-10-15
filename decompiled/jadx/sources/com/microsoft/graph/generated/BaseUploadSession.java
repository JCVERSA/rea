package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseUploadSession implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b = new com.microsoft.graph.serializer.a(this);

    @c("expirationDateTime")
    @ax.Z8.a
    public Calendar c;

    @c("nextExpectedRanges")
    @ax.Z8.a
    public List<String> d;

    @c("uploadUrl")
    @ax.Z8.a
    public String e;
    private transient l f;
    private transient e g;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.g = eVar;
        this.f = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
