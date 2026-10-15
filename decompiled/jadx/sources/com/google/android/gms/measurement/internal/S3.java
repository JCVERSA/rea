package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class S3 implements Runnable {
    private final /* synthetic */ C6351k3 X;
    private final /* synthetic */ C6409u q;

    S3(C6351k3 c6351k3, C6409u c6409u) {
        this.q = c6409u;
        this.X = c6351k3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.X.h().A(this.q)) {
            this.X.j().J().b("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.q.a()));
            return;
        }
        this.X.j().K().b("Setting DMA consent(FE)", this.q);
        if (this.X.t().i0()) {
            this.X.t().d0();
        } else {
            this.X.t().U(false);
        }
    }
}
