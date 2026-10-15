package com.google.android.gms.common.api.internal;

/* JADX INFO: loaded from: classes3.dex */
final class G {
    final /* synthetic */ BasePendingResult a;

    /* synthetic */ G(BasePendingResult basePendingResult, ax.p249p6.F f) {
        this.a = basePendingResult;
    }

    protected final void finalize() throws Throwable {
        BasePendingResult.k(this.a.h);
        super.finalize();
    }
}
