package com.google.android.gms.measurement.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class B implements Iterator<String> {
    private final /* synthetic */ C X;
    private Iterator<String> q;

    B(C c) {
        this.X = c;
        this.q = c.q.keySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.q.hasNext();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.q.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
