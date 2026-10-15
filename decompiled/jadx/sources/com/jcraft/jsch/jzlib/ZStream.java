package com.jcraft.jsch.jzlib;

/* JADX INFO: loaded from: classes3.dex */
class ZStream {
    byte[] a;
    int b;
    int c;
    long d;
    byte[] e;
    int f;
    int g;
    long h;
    String i;
    Deflate j;
    Inflate k;
    int l;
    Checksum m;

    ZStream() {
        this(new Adler32());
    }

    ZStream(Checksum checksum) {
        this.m = checksum;
    }

    void a() {
        Deflate deflate = this.j;
        int i = deflate.l0;
        int i2 = this.g;
        if (i > i2) {
            i = i2;
        }
        if (i == 0) {
            return;
        }
        byte[] bArr = deflate.Y;
        int length = bArr.length;
        int i3 = deflate.k0;
        if (length > i3) {
            byte[] bArr2 = this.e;
            if (bArr2.length > this.f && bArr.length >= i3 + i) {
                int length2 = bArr2.length;
            }
        }
        System.arraycopy(bArr, i3, this.e, this.f, i);
        this.f += i;
        Deflate deflate2 = this.j;
        deflate2.k0 += i;
        this.h += (long) i;
        this.g -= i;
        int i4 = deflate2.l0 - i;
        deflate2.l0 = i4;
        if (i4 == 0) {
            deflate2.k0 = 0;
        }
    }

    void b() {
        this.a = null;
        this.e = null;
        this.i = null;
    }

    int c(byte[] bArr, int i, int i2) {
        int i3 = this.c;
        if (i3 <= i2) {
            i2 = i3;
        }
        if (i2 == 0) {
            return 0;
        }
        this.c = i3 - i2;
        if (this.j.m0 != 0) {
            this.m.b(this.a, this.b, i2);
        }
        System.arraycopy(this.a, this.b, bArr, i, i2);
        this.b += i2;
        this.d += (long) i2;
        return i2;
    }

    void d(byte[] bArr, int i, int i2, boolean z) {
        if (i2 > 0 || !z || this.a == null) {
            int i3 = this.c;
            if (i3 <= 0 || !z) {
                this.a = bArr;
                this.b = i;
                this.c = i2;
            } else {
                byte[] bArr2 = new byte[i3 + i2];
                System.arraycopy(this.a, this.b, bArr2, 0, i3);
                System.arraycopy(bArr, i, bArr2, this.c, i2);
                this.a = bArr2;
                this.b = 0;
                this.c += i2;
            }
        }
    }

    void e(byte[] bArr, int i, int i2) {
        this.e = bArr;
        this.f = i;
        this.g = i2;
    }
}
