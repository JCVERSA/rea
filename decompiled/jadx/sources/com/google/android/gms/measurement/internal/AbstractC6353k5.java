package com.google.android.gms.measurement.internal;

/* JADX INFO: renamed from: com.google.android.gms.measurement.internal.k5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
abstract class AbstractC6353k5 extends C6360l5 {
    private boolean c;

    AbstractC6353k5(q5 q5Var) {
        super(q5Var);
        this.b.x0();
    }

    protected final void u() {
        if (!w()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void v() {
        if (this.c) {
            throw new IllegalStateException("Can't initialize twice");
        }
        x();
        this.b.w0();
        this.c = true;
    }

    final boolean w() {
        return this.c;
    }

    protected abstract boolean x();
}
