package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* JADX INFO: loaded from: classes3.dex */
final class C extends ax.p249p6.s {
    final /* synthetic */ Dialog a;
    final /* synthetic */ D b;

    C(D d, Dialog dialog) {
        this.b = d;
        this.a = dialog;
    }

    @Override // ax.p249p6.s
    public final void a() {
        this.b.X.o();
        if (this.a.isShowing()) {
            this.a.dismiss();
        }
    }
}
