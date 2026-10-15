package com.microsoft.graph.generated;

import ax.Aa.d;
import ax.Aa.e;
import ax.Y8.f;
import ax.Y8.l;
import ax.Z8.c;
import com.microsoft.graph.extensions.WorkbookTableColumn;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class BaseWorkbookTableColumnCollectionResponse implements d {

    @c("value")
    @ax.Z8.a
    public List<WorkbookTableColumn> a;

    @c("@odata.nextLink")
    @ax.Z8.a(serialize = false)
    public String b;
    private transient com.microsoft.graph.serializer.a c = new com.microsoft.graph.serializer.a(this);
    private transient l d;
    private transient e e;

    @Override // ax.Aa.d
    public void c(e eVar, l lVar) {
        this.e = eVar;
        this.d = lVar;
        if (lVar.w("value")) {
            f fVarU = lVar.u("value");
            for (int i = 0; i < fVarU.size(); i++) {
                this.a.get(i).c(this.e, (l) fVarU.s(i));
            }
        }
    }

    @Override // ax.Aa.d
    public final com.microsoft.graph.serializer.a d() {
        return this.c;
    }
}
