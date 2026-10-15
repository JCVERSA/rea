package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* JADX INFO: loaded from: classes3.dex */
final class L4 implements Runnable {
    private final /* synthetic */ J4 X;
    private final /* synthetic */ ComponentName q;

    L4(J4 j4, ComponentName componentName) {
        this.q = componentName;
        this.X = j4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6352k4.M(this.X.Y, this.q);
    }
}
