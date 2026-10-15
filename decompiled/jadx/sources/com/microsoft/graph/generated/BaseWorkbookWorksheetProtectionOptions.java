package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookWorksheetProtectionOptions implements d {

    @c("@odata.type")
    @ax.Z8.a
    public String a;
    private transient com.microsoft.graph.serializer.a b;

    @c("allowAutoFilter")
    @ax.Z8.a
    public Boolean c;

    @c("allowDeleteColumns")
    @ax.Z8.a
    public Boolean d;

    @c("allowDeleteRows")
    @ax.Z8.a
    public Boolean e;

    @c("allowFormatCells")
    @ax.Z8.a
    public Boolean f;

    @c("allowFormatColumns")
    @ax.Z8.a
    public Boolean g;

    @c("allowFormatRows")
    @ax.Z8.a
    public Boolean h;

    @c("allowInsertColumns")
    @ax.Z8.a
    public Boolean i;

    @c("allowInsertHyperlinks")
    @ax.Z8.a
    public Boolean j;

    @c("allowInsertRows")
    @ax.Z8.a
    public Boolean k;

    @c("allowPivotTables")
    @ax.Z8.a
    public Boolean l;

    @c("allowSort")
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
