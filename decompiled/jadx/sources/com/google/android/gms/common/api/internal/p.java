package com.google.android.gms.common.api.internal;

import ax.p217n6.C2174e;
import ax.p249p6.C2204b;
import ax.p265q6.C2252n;

/* JADX INFO: loaded from: classes3.dex */
final class p {
    private final C2204b a;
    private final C2174e b;

    /* synthetic */ p(C2204b c2204b, C2174e c2174e, ax.p249p6.q qVar) {
        this.a = c2204b;
        this.b = c2174e;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof p)) {
            p pVar = (p) obj;
            if (C2252n.b(this.a, pVar.a) && C2252n.b(this.b, pVar.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return C2252n.c(this.a, this.b);
    }

    public final String toString() {
        return C2252n.d(this).a("key", this.a).a("feature", this.b).toString();
    }
}
