package com.google.android.gms.ads.internal.overlay;

import ax.P5.v;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes3.dex */
final class c implements Callable {
    private final long a;

    c(long j) {
        this.a = j;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        if (AdOverlayInfoParcel.F0.remove(Long.valueOf(this.a)) == null) {
            return null;
        }
        v.s().x(new Exception("Key was non-null in AdOverlayObjectsCleanupTask"), "AdOverlayObjectsCleanupTask");
        return null;
    }
}
