package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;

/* JADX INFO: renamed from: com.google.android.gms.common.api.internal.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
final class C6274e implements ax.o6.h.a {
    final /* synthetic */ BasePendingResult a;
    final /* synthetic */ C6276g b;

    C6274e(C6276g c6276g, BasePendingResult basePendingResult) {
        this.b = c6276g;
        this.a = basePendingResult;
    }

    @Override // ax.o6.h.a
    public final void a(Status status) {
        this.b.a.remove(this.a);
    }
}
