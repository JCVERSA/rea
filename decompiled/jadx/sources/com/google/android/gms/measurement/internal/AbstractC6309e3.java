package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.e3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractC6309e3 extends C6316f3 {
    private boolean b;

    AbstractC6309e3(E2 e2) {
        super(e2);
        this.a.n();
    }

    protected void o() {
    }

    protected final void p() {
        if (!s()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void q() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        if (t()) {
            return;
        }
        this.a.R();
        this.b = true;
    }

    public final void r() {
        if (this.b) {
            throw new IllegalStateException("Can't initialize twice");
        }
        o();
        this.a.R();
        this.b = true;
    }

    final boolean s() {
        return this.b;
    }

    protected abstract boolean t();
}
