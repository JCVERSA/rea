package com.pcloud.sdk.internal;

import ax.p192ld.C2155e;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
final class f extends ax.p192ld.j {
    private final long X;
    private final ax.Sa.r Y;
    private long Z;
    private final long k0;
    private long q;

    f(ax.p192ld.A a, long j, ax.Sa.r rVar, long j2) {
        super(a);
        this.k0 = j;
        this.X = j2;
        this.Y = rVar;
    }

    @Override // ax.p192ld.j, ax.p192ld.A
    public void write(C2155e c2155e, long j) throws IOException {
        super.write(c2155e, j);
        long j2 = this.Z + j;
        this.Z = j2;
        if (j2 - this.q >= this.X) {
            this.Y.a(j2, this.k0);
            this.q = this.Z;
        }
    }
}
