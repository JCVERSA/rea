package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class U1 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ Object Y;
    private final /* synthetic */ Object Z;
    private final /* synthetic */ Object k0;
    private final /* synthetic */ V1 l0;
    private final /* synthetic */ int q;

    U1(V1 v1, int i, String str, Object obj, Object obj2, Object obj3) {
        this.q = i;
        this.X = str;
        this.Y = obj;
        this.Z = obj2;
        this.k0 = obj3;
        this.l0 = v1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C6322g2 c6322g2F = this.l0.a.F();
        if (!c6322g2F.s()) {
            this.l0.y(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.l0.c == 0) {
            if (this.l0.d().W()) {
                this.l0.c = 'C';
            } else {
                this.l0.c = 'c';
            }
        }
        if (this.l0.d < 0) {
            this.l0.d = 97001L;
        }
        String strSubstring = "2" + "01VDIWEA?".charAt(this.q) + this.l0.c + this.l0.d + ":" + V1.x(true, this.X, this.Y, this.Z, this.k0);
        if (strSubstring.length() > 1024) {
            strSubstring = this.X.substring(0, 1024);
        }
        C6350k2 c6350k2 = c6322g2F.f;
        if (c6350k2 != null) {
            c6350k2.b(strSubstring, 1L);
        }
    }
}
