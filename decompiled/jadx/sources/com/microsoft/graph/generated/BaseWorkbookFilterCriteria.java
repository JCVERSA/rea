package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.i;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.WorkbookIcon;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookFilterCriteria implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("color")
    @ax.Z8.a
    public String c;

    @c("criterion1")
    @ax.Z8.a
    public String d;

    @c("criterion2")
    @ax.Z8.a
    public String e;

    @c("dynamicCriteria")
    @ax.Z8.a
    public String f;

    @c("filterOn")
    @ax.Z8.a
    public String g;

    @c("icon")
    @ax.Z8.a
    public WorkbookIcon h;

    @c("operator")
    @ax.Z8.a
    public String i;

    @c("values")
    @ax.Z8.a
    public i j;
    private transient l k;
    private transient e l;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.l = eVar;
        this.k = lVar;
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.b;
    }
}
