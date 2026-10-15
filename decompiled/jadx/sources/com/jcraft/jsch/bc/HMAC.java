package com.jcraft.jsch.bc;

import ax.p161je.i;
import ax.p225ne.c;
import ax.p289re.k;
import com.jcraft.jsch.MAC;

/* JADX INFO: loaded from: classes3.dex */
abstract class HMAC implements MAC {
    protected int a;
    protected i b;
    protected boolean c;
    private c d;
    private final byte[] e = new byte[4];

    HMAC() {
    }

    @Override // com.jcraft.jsch.MAC
    public void a(byte[] bArr) throws Exception {
        int length = bArr.length;
        int i = this.a;
        if (length > i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bArr = bArr2;
        }
        k kVar = new k(bArr, 0, bArr.length);
        c cVar = new c(this.b);
        this.d = cVar;
        cVar.e(kVar);
    }

    @Override // com.jcraft.jsch.MAC
    public void b(byte[] bArr, int i, int i2) {
        this.d.b(bArr, i, i2);
    }

    @Override // com.jcraft.jsch.MAC
    public void c(byte[] bArr, int i) {
        this.d.c(bArr, i);
    }

    @Override // com.jcraft.jsch.MAC
    public int d() {
        return this.a;
    }

    @Override // com.jcraft.jsch.MAC
    public void e(int i) {
        byte[] bArr = this.e;
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) i;
        b(bArr, 0, 4);
    }

    @Override // com.jcraft.jsch.MAC
    public boolean f() {
        return this.c;
    }
}
