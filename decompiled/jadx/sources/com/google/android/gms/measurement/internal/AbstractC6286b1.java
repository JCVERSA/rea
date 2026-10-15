package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.b1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractC6286b1 extends C1 {
    private boolean b;

    AbstractC6286b1(E2 e2) {
        super(e2);
        this.a.n();
    }

    protected abstract boolean A();

    protected final void v() {
        if (!z()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void w() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (A()) {
            return;
        }
        this.a.R();
        this.b = true;
    }

    public final void x() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        y();
        this.a.R();
        this.b = true;
    }

    protected void y() {
    }

    final boolean z() {
        return this.b;
    }
}
