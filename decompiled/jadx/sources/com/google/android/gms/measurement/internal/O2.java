package com.google.android.gms.measurement.internal;

/* JADX INFO: loaded from: classes3.dex */
final class O2 implements Runnable {
    private final /* synthetic */ String X;
    private final /* synthetic */ String Y;
    private final /* synthetic */ long Z;
    private final /* synthetic */ I2 k0;
    private final /* synthetic */ String q;

    O2(I2 i2, String str, String str2, String str3, long j) {
        this.q = str;
        this.X = str2;
        this.Y = str3;
        this.Z = j;
        this.k0 = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.q;
        if (str == null) {
            this.k0.q.F(this.X, null);
        } else {
            this.k0.q.F(this.X, new C6303d4(this.Y, str, this.Z));
        }
    }
}
