package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
final class GZIPHeader implements Cloneable {
    int Y;
    byte[] k0;
    byte[] l0;
    byte[] m0;
    int n0;
    long o0;
    boolean q = false;
    private boolean X = false;
    int Z = 255;
    boolean p0 = false;
    long q0 = 0;

    GZIPHeader() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42 */
    void b(Deflate deflate) {
        int i;
        boolean z = this.q;
        ?? r0 = z;
        if (this.X) {
            r0 = (z ? 1 : 0) | 2;
        }
        ?? r1 = r0;
        if (this.k0 != null) {
            r1 = (r0 == true ? 1 : 0) | 4;
        }
        ?? r2 = r1;
        if (this.l0 != null) {
            r2 = (r1 == true ? 1 : 0) | 8;
        }
        int i2 = r2;
        if (this.m0 != null) {
            i2 = (r2 == true ? 1 : 0) | 16;
        }
        int i3 = deflate.M0;
        if (i3 == 1) {
            i = 4;
        } else {
            i = i3 == 9 ? 2 : 0;
        }
        deflate.M(-29921);
        deflate.K((byte) 8);
        deflate.K((byte) i2);
        deflate.K((byte) this.q0);
        deflate.K((byte) (this.q0 >> 8));
        deflate.K((byte) (this.q0 >> 16));
        deflate.K((byte) (this.q0 >> 24));
        deflate.K((byte) i);
        deflate.K((byte) this.Z);
        byte[] bArr = this.k0;
        if (bArr != null) {
            deflate.K((byte) bArr.length);
            deflate.K((byte) (this.k0.length >> 8));
            byte[] bArr2 = this.k0;
            deflate.L(bArr2, 0, bArr2.length);
        }
        byte[] bArr3 = this.l0;
        if (bArr3 != null) {
            deflate.L(bArr3, 0, bArr3.length);
            deflate.K((byte) 0);
        }
        byte[] bArr4 = this.m0;
        if (bArr4 != null) {
            deflate.L(bArr4, 0, bArr4.length);
            deflate.K((byte) 0);
        }
    }

    void c(long j) {
        this.o0 = j;
    }

    public Object clone() throws CloneNotSupportedException {
        GZIPHeader gZIPHeader = (GZIPHeader) super.clone();
        byte[] bArr = gZIPHeader.k0;
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            gZIPHeader.k0 = bArr2;
        }
        byte[] bArr3 = gZIPHeader.l0;
        if (bArr3 != null) {
            int length2 = bArr3.length;
            byte[] bArr4 = new byte[length2];
            System.arraycopy(bArr3, 0, bArr4, 0, length2);
            gZIPHeader.l0 = bArr4;
        }
        byte[] bArr5 = gZIPHeader.m0;
        if (bArr5 != null) {
            int length3 = bArr5.length;
            byte[] bArr6 = new byte[length3];
            System.arraycopy(bArr5, 0, bArr6, 0, length3);
            gZIPHeader.m0 = bArr6;
        }
        return gZIPHeader;
    }

    void d(long j) {
        this.q0 = j;
    }
}
