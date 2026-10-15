package com.jcraft.jsch;

import okhttp3.internal.http2.Settings;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes3.dex */
public class Buffer {
    final byte[] a;
    byte[] b;
    int c;
    int d;

    public Buffer() {
        this(20480);
    }

    public Buffer(int i) {
        this.a = new byte[4];
        this.b = new byte[i];
        this.c = 0;
        this.d = 0;
    }

    public Buffer(byte[] bArr) {
        this.a = new byte[4];
        this.b = bArr;
        this.c = 0;
        this.d = 0;
    }

    static Buffer b(byte[][] bArr) {
        int length = bArr.length * 4;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        Buffer buffer = new Buffer(length);
        for (byte[] bArr3 : bArr) {
            buffer.y(bArr3);
        }
        return buffer;
    }

    public void A() {
        this.c = 0;
        this.d = 0;
    }

    void B() {
        this.d = 0;
    }

    public void C(int i) {
        this.d = i;
    }

    public void D() {
        int i = this.d;
        if (i == 0) {
            return;
        }
        byte[] bArr = this.b;
        System.arraycopy(bArr, i, bArr, 0, this.c - i);
        this.c -= this.d;
        this.d = 0;
    }

    void E(int i) {
        this.c += i;
    }

    void a(int i) {
        int i2 = this.c;
        int i3 = i + i2 + 128;
        byte[] bArr = this.b;
        if (bArr.length < i3) {
            int length = bArr.length * 2;
            if (length >= i3) {
                i3 = length;
            }
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, 0, bArr2, 0, i2);
            this.b = bArr2;
        }
    }

    public int c() {
        byte[] bArr = this.b;
        int i = this.d;
        this.d = i + 1;
        return bArr[i] & 255;
    }

    public int d(int i) {
        int i2 = this.d;
        this.d = i + i2;
        return i2;
    }

    public void e(byte[] bArr) {
        f(bArr, 0, bArr.length);
    }

    void f(byte[] bArr, int i, int i2) {
        System.arraycopy(this.b, this.d, bArr, i, i2);
        this.d += i2;
    }

    byte[][] g(int i, String str) throws JSchException {
        byte[][] bArr = new byte[i][];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i();
            if (j() < i3) {
                throw new JSchException(str);
            }
            byte[] bArr2 = new byte[i3];
            bArr[i2] = bArr2;
            e(bArr2);
        }
        return bArr;
    }

    byte h() {
        return this.b[5];
    }

    public int i() {
        return ((o() << 16) & (-65536)) | (o() & Settings.DEFAULT_INITIAL_WINDOW_SIZE);
    }

    public int j() {
        return this.c - this.d;
    }

    public long k() {
        return ((((long) i()) & 4294967295L) << 32) | (4294967295L & ((long) i()));
    }

    public byte[] l() {
        int i = i();
        if (i < 0 || i > 8192) {
            i = 8192;
        }
        byte[] bArr = new byte[i];
        f(bArr, 0, i);
        return bArr;
    }

    public byte[] m() {
        int i = (i() + 7) / 8;
        byte[] bArr = new byte[i];
        f(bArr, 0, i);
        if ((bArr[0] & 128) == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[i + 1];
        bArr2[0] = 0;
        System.arraycopy(bArr, 0, bArr2, 1, i);
        return bArr2;
    }

    public int n() {
        return this.d;
    }

    int o() {
        return ((c() << 8) & 65280) | (c() & 255);
    }

    public byte[] p() {
        int i = i();
        if (i < 0 || i > 262144) {
            i = 262144;
        }
        byte[] bArr = new byte[i];
        f(bArr, 0, i);
        return bArr;
    }

    byte[] q(int[] iArr, int[] iArr2) {
        int i = i();
        iArr[0] = d(i);
        iArr2[0] = i;
        return this.b;
    }

    public long r() {
        return (((((((long) c()) << 8) & 65280) | ((long) (c() & 255))) << 16) & (-65536)) | (((65280 & (((long) c()) << 8)) | ((long) (c() & 255))) & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    public void s(byte b) {
        byte[] bArr = this.b;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public void t(byte[] bArr) {
        u(bArr, 0, bArr.length);
    }

    public void u(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.b, this.c, i2);
        this.c += i2;
    }

    public void v(int i) {
        byte[] bArr = this.a;
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) i;
        System.arraycopy(bArr, 0, this.b, this.c, 4);
        this.c += 4;
    }

    public void w(long j) {
        byte[] bArr = this.a;
        bArr[0] = (byte) (j >>> 56);
        bArr[1] = (byte) (j >>> 48);
        bArr[2] = (byte) (j >>> 40);
        bArr[3] = (byte) (j >>> 32);
        System.arraycopy(bArr, 0, this.b, this.c, 4);
        byte[] bArr2 = this.a;
        bArr2[0] = (byte) (j >>> 24);
        bArr2[1] = (byte) (j >>> 16);
        bArr2[2] = (byte) (j >>> 8);
        bArr2[3] = (byte) j;
        System.arraycopy(bArr2, 0, this.b, this.c + 4, 4);
        this.c += 8;
    }

    public void x(byte[] bArr) {
        int length = bArr.length;
        if ((bArr[0] & 128) != 0) {
            v(length + 1);
            s((byte) 0);
        } else {
            v(length);
        }
        t(bArr);
    }

    public void y(byte[] bArr) {
        z(bArr, 0, bArr.length);
    }

    public void z(byte[] bArr, int i, int i2) {
        v(i2);
        u(bArr, i, i2);
    }
}
