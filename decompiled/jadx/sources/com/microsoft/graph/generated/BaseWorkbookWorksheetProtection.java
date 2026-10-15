package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.Entity;
import com.microsoft.graph.extensions.WorkbookWorksheetProtectionOptions;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookWorksheetProtection extends Entity implements d {

    @c("options")
    @ax.Z8.a
    public WorkbookWorksheetProtectionOptions f;

    @c("protected")
    @ax.Z8.a
    public Boolean g;
    private transient l h;
    private transient e i;

    @Override // com.microsoft.graph.generated.BaseEntity, ax.Aa.d
    public void c(e eVar, l lVar) {
        this.i = eVar;
        this.h = lVar;
    }
}
