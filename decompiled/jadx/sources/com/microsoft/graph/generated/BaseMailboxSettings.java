package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.AutomaticRepliesSetting;
import com.microsoft.graph.extensions.LocaleInfo;
import com.microsoft.graph.extensions.WorkingHours;

/* JADX INFO: loaded from: classes3.dex */
public class BaseMailboxSettings implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("automaticRepliesSetting")
    @ax.Z8.a
    public AutomaticRepliesSetting c;

    @c("archiveFolder")
    @ax.Z8.a
    public String d;

    @c("timeZone")
    @ax.Z8.a
    public String e;

    @c("language")
    @ax.Z8.a
    public LocaleInfo f;

    @c("workingHours")
    @ax.Z8.a
    public WorkingHours g;
    private transient l h;
    private transient e i;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
