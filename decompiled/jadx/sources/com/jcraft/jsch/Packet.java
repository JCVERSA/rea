package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class Packet {
    private static Random c;
    Buffer a;
    byte[] b = new byte[4];

    Packet(Buffer buffer) {
        this.a = buffer;
    }

    static void d(Random random) {
        c = random;
    }

    Buffer a() {
        return this.a;
    }

    void b(int i, boolean z) {
        Buffer buffer = this.a;
        int i2 = buffer.c;
        if (!z) {
            i2 -= 4;
        }
        int i3 = (-i2) & (i - 1);
        if (i3 < i) {
            i3 += i;
        }
        int i4 = i2 + i3;
        if (z) {
            i4 -= 4;
        }
        byte[] bArr = this.b;
        bArr[0] = (byte) (i4 >>> 24);
        bArr[1] = (byte) (i4 >>> 16);
        bArr[2] = (byte) (i4 >>> 8);
        bArr[3] = (byte) i4;
        System.arraycopy(bArr, 0, buffer.b, 0, 4);
        this.a.b[4] = (byte) i3;
        synchronized (c) {
            Random random = c;
            Buffer buffer2 = this.a;
            random.a(buffer2.b, buffer2.c, i3);
        }
        this.a.E(i3);
    }

    void c() {
        this.a.c = 5;
    }

    int e(int i, int i2, int i3) {
        int i4 = i + 14;
        int i5 = (-i4) & (i2 - 1);
        if (i5 < i2) {
            i5 += i2;
        }
        int i6 = i5 + i4 + i3 + 32;
        Buffer buffer = this.a;
        byte[] bArr = buffer.b;
        int length = bArr.length;
        int i7 = buffer.c;
        if (length < ((i6 + i7) - 14) - i) {
            byte[] bArr2 = new byte[((i7 + i6) - 14) - i];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.a.b = bArr2;
        }
        Buffer buffer2 = this.a;
        byte[] bArr3 = buffer2.b;
        System.arraycopy(bArr3, i4, bArr3, i6, (buffer2.c - 14) - i);
        Buffer buffer3 = this.a;
        buffer3.c = 10;
        buffer3.v(i);
        this.a.c = i4;
        return i6;
    }

    void f(byte b, int i, int i2, int i3) {
        byte[] bArr = this.a.b;
        System.arraycopy(bArr, i2, bArr, 14, i3);
        Buffer buffer = this.a;
        buffer.b[5] = b;
        buffer.c = 6;
        buffer.v(i);
        this.a.v(i3);
        this.a.c = i3 + 14;
    }
}
