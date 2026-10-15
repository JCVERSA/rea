package com.google.android.gms.measurement.internal;

import android.net.Uri;

/* JADX INFO: loaded from: classes3.dex */
final class W3 implements Runnable {
    private final /* synthetic */ Uri X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ String Z;
    private final /* synthetic */ X3 k0;
    private final /* synthetic */ boolean q;

    W3(X3 x3, boolean z, Uri uri, String str, String str2) {
        this.q = z;
        this.X = uri;
        this.Y = str;
        this.Z = str2;
        this.k0 = x3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        X3.a(this.k0, this.q, this.X, this.Y, this.Z);
    }
}
