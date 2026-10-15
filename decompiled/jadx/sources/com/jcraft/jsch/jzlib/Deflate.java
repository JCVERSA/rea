package com.jcraft.jsch.jzlib;

import okhttp3.HttpUrl;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes3.dex */
final class Deflate implements Cloneable {
    private static final Config[] n1 = {new Config(0, 0, 0, 0, 0), new Config(4, 4, 8, 4, 1), new Config(4, 5, 16, 8, 1), new Config(4, 6, 32, 32, 1), new Config(4, 4, 16, 16, 2), new Config(8, 16, 32, 32, 2), new Config(8, 16, 128, 128, 2), new Config(8, 32, 128, 256, 2), new Config(32, 128, 258, 1024, 2), new Config(32, 258, 258, 4096, 2)};
    private static final String[] o1 = {"need dictionary", "stream end", HttpUrl.FRAGMENT_ENCODE_SET, "file error", "stream error", "data error", "insufficient memory", "buffer error", "incompatible version", HttpUrl.FRAGMENT_ENCODE_SET};
    int A0;
    int B0;
    int C0;
    int D0;
    int E0;
    int F0;
    int G0;
    int H0;
    int I0;
    int J0;
    int K0;
    int L0;
    int M0;
    int N0;
    int O0;
    int P0;
    int X;
    byte[] Y;
    int Z;
    int Z0;
    int a1;
    byte[] c1;
    int d1;
    int e1;
    int f1;
    int g1;
    int h1;
    int i1;
    int j1;
    int k0;
    short k1;
    int l0;
    int l1;
    byte n0;
    byte o0;
    int p0;
    ZStream q;
    int q0;
    int r0;
    int s0;
    byte[] t0;
    int u0;
    short[] v0;
    short[] w0;
    int x0;
    int y0;
    int z0;
    int m0 = 1;
    Tree T0 = new Tree();
    Tree U0 = new Tree();
    Tree V0 = new Tree();
    short[] W0 = new short[16];
    short[] X0 = new short[16];
    int[] Y0 = new int[573];
    byte[] b1 = new byte[573];
    GZIPHeader m1 = null;
    short[] Q0 = new short[1146];
    short[] R0 = new short[122];
    short[] S0 = new short[78];

    static class Config {
        int a;
        int b;
        int c;
        int d;
        int e;

        Config(int i, int i2, int i3, int i4, int i5) {
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
            this.e = i5;
        }
    }

    Deflate(ZStream zStream) {
        this.q = zStream;
    }

    private short[] B(short[] sArr) {
        int length = sArr.length;
        short[] sArr2 = new short[length];
        System.arraycopy(sArr, 0, sArr2, 0, length);
        return sArr2;
    }

    static boolean U(short[] sArr, int i, int i2, byte[] bArr) {
        short s = sArr[i * 2];
        short s2 = sArr[i2 * 2];
        if (s >= s2) {
            return s == s2 && bArr[i] <= bArr[i2];
        }
        return true;
    }

    private int r(int i, int i2, int i3, int i4, int i5) {
        int i6;
        ZStream zStream = this.q;
        zStream.i = null;
        if (i == -1) {
            i = 6;
        }
        if (i3 < 0) {
            i3 = -i3;
            i6 = 0;
        } else if (i3 > 15) {
            i3 -= 16;
            zStream.m = new CRC32();
            i6 = 2;
        } else {
            i6 = 1;
        }
        if (i4 < 1 || i4 > 9 || i2 != 8 || i3 < 9 || i3 > 15 || i < 0 || i > 9 || i5 < 0 || i5 > 2) {
            return -2;
        }
        this.q.j = this;
        this.m0 = i6;
        this.r0 = i3;
        int i7 = 1 << i3;
        this.q0 = i7;
        this.s0 = i7 - 1;
        int i8 = i4 + 7;
        this.z0 = i8;
        int i9 = 1 << i8;
        this.y0 = i9;
        this.A0 = i9 - 1;
        this.B0 = (i4 + 9) / 3;
        this.t0 = new byte[i7 * 2];
        this.v0 = new short[i7];
        this.w0 = new short[i9];
        int i10 = 1 << (i4 + 6);
        this.d1 = i10;
        this.Y = new byte[i10 * 3];
        this.Z = i10 * 3;
        this.f1 = i10;
        this.c1 = new byte[i10];
        this.M0 = i;
        this.N0 = i5;
        this.o0 = (byte) i2;
        return s();
    }

    private byte[] w(byte[] bArr) {
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private int[] z(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    void C() {
        int i;
        int i2;
        do {
            int i3 = this.u0;
            int i4 = this.I0;
            int i5 = this.G0;
            int i6 = (i3 - i4) - i5;
            if (i6 == 0 && i5 == 0 && i4 == 0) {
                i6 = this.q0;
            } else if (i6 == -1) {
                i6--;
            } else {
                int i7 = this.q0;
                if (i5 >= (i7 + i7) - 262) {
                    byte[] bArr = this.t0;
                    System.arraycopy(bArr, i7, bArr, 0, i7);
                    int i8 = this.H0;
                    int i9 = this.q0;
                    this.H0 = i8 - i9;
                    this.G0 -= i9;
                    this.C0 -= i9;
                    int i10 = this.y0;
                    int i11 = i10;
                    do {
                        short[] sArr = this.w0;
                        i10--;
                        int i12 = sArr[i10] & 65535;
                        i = this.q0;
                        sArr[i10] = i12 >= i ? (short) (i12 - i) : (short) 0;
                        i11--;
                    } while (i11 != 0);
                    int i13 = i;
                    do {
                        short[] sArr2 = this.v0;
                        i--;
                        int i14 = sArr2[i] & 65535;
                        i2 = this.q0;
                        sArr2[i] = i14 >= i2 ? (short) (i14 - i2) : (short) 0;
                        i13--;
                    } while (i13 != 0);
                    i6 += i2;
                }
            }
            ZStream zStream = this.q;
            if (zStream.c == 0) {
                return;
            }
            int iC = this.I0 + zStream.c(this.t0, this.G0 + this.I0, i6);
            this.I0 = iC;
            if (iC >= 3) {
                byte[] bArr2 = this.t0;
                int i15 = this.G0;
                int i16 = bArr2[i15] & 255;
                this.x0 = i16;
                this.x0 = ((bArr2[i15 + 1] & 255) ^ (i16 << this.B0)) & this.A0;
            }
            if (iC >= 262) {
                return;
            }
        } while (this.q.c != 0);
    }

    void D(boolean z) {
        int i = this.C0;
        c(i >= 0 ? i : -1, this.G0 - i, z);
        this.C0 = this.G0;
        this.q.a();
    }

    synchronized GZIPHeader E() {
        try {
            if (this.m1 == null) {
                this.m1 = new GZIPHeader();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.m1;
    }

    void F() {
        for (int i = 0; i < 286; i++) {
            this.Q0[i * 2] = 0;
        }
        for (int i2 = 0; i2 < 30; i2++) {
            this.R0[i2 * 2] = 0;
        }
        for (int i3 = 0; i3 < 19; i3++) {
            this.S0[i3 * 2] = 0;
        }
        this.Q0[512] = 1;
        this.h1 = 0;
        this.g1 = 0;
        this.i1 = 0;
        this.e1 = 0;
    }

    void G() {
        this.u0 = this.q0 * 2;
        this.w0[this.y0 - 1] = 0;
        for (int i = 0; i < this.y0 - 1; i++) {
            this.w0[i] = 0;
        }
        Config config = n1[this.M0];
        this.L0 = config.b;
        this.O0 = config.a;
        this.P0 = config.c;
        this.K0 = config.d;
        this.G0 = 0;
        this.C0 = 0;
        this.I0 = 0;
        this.J0 = 2;
        this.D0 = 2;
        this.F0 = 0;
        this.x0 = 0;
    }

    int H(int i) {
        int i2;
        int i3;
        byte[] bArr;
        int i4;
        int i5 = this.K0;
        int i6 = this.G0;
        int i7 = this.J0;
        int i8 = this.q0;
        int i9 = i6 > i8 + (-262) ? i6 - (i8 - 262) : 0;
        int i10 = this.P0;
        int i11 = this.s0;
        int i12 = i6 + 258;
        byte[] bArr2 = this.t0;
        int i13 = i6 + i7;
        byte b = bArr2[i13 - 1];
        byte b2 = bArr2[i13];
        if (i7 >= this.O0) {
            i5 >>= 2;
        }
        int i14 = this.I0;
        int i15 = i6;
        if (i10 <= i14) {
            i14 = i10;
        }
        int i16 = i7;
        int i17 = i5;
        int i18 = i;
        while (true) {
            byte[] bArr3 = this.t0;
            int i19 = i18 + i16;
            if (bArr3[i19] == b2 && bArr3[i19 - 1] == b && bArr3[i18] == bArr3[i15] && bArr3[i18 + 1] == bArr3[i15 + 1]) {
                int i20 = i15 + 2;
                int i21 = i18 + 2;
                while (true) {
                    bArr = this.t0;
                    i4 = i20 + 1;
                    i2 = i6;
                    if (bArr[i4] != bArr[i21 + 1]) {
                        break;
                    }
                    i4 = i20 + 2;
                    if (bArr[i4] != bArr[i21 + 2]) {
                        break;
                    }
                    i4 = i20 + 3;
                    if (bArr[i4] != bArr[i21 + 3]) {
                        break;
                    }
                    i4 = i20 + 4;
                    if (bArr[i4] != bArr[i21 + 4]) {
                        break;
                    }
                    i4 = i20 + 5;
                    if (bArr[i4] != bArr[i21 + 5]) {
                        break;
                    }
                    i4 = i20 + 6;
                    if (bArr[i4] != bArr[i21 + 6]) {
                        break;
                    }
                    i4 = i20 + 7;
                    if (bArr[i4] != bArr[i21 + 7]) {
                        break;
                    }
                    i20 += 8;
                    i21 += 8;
                    if (bArr[i20] != bArr[i21] || i20 >= i12) {
                        i4 = i20;
                        break;
                    }
                    i6 = i2;
                }
                i3 = (258 - i12) - i4;
                if (i3 > i16) {
                    this.H0 = i18;
                    if (i3 >= i14) {
                        break;
                    }
                    int i22 = i2 + i3;
                    b = bArr[i22 - 1];
                    i15 = i2;
                    b2 = bArr[i22];
                    i16 = i3;
                } else {
                    i15 = i2;
                }
            } else {
                i2 = i6;
            }
            i18 = this.v0[i18 & i11] & 65535;
            if (i18 <= i9 || (i17 = i17 - 1) == 0) {
                i3 = i16;
                break;
            }
            i6 = i2;
        }
        int i23 = this.I0;
        return i3 <= i23 ? i3 : i23;
    }

    void I(short[] sArr, int i) {
        int i2 = this.Y0[i];
        int i3 = i << 1;
        while (true) {
            int i4 = this.Z0;
            if (i3 > i4) {
                break;
            }
            if (i3 < i4) {
                int[] iArr = this.Y0;
                int i5 = i3 + 1;
                if (U(sArr, iArr[i5], iArr[i3], this.b1)) {
                    i3 = i5;
                }
            }
            if (U(sArr, i2, this.Y0[i3], this.b1)) {
                break;
            }
            int[] iArr2 = this.Y0;
            iArr2[i] = iArr2[i3];
            int i6 = i3;
            i3 <<= 1;
            i = i6;
        }
        this.Y0[i] = i2;
    }

    final void J(int i) {
        K((byte) (i >> 8));
        K((byte) i);
    }

    final void K(byte b) {
        byte[] bArr = this.Y;
        int i = this.l0;
        this.l0 = i + 1;
        bArr[i] = b;
    }

    final void L(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.Y, this.l0, i2);
        this.l0 += i2;
    }

    final void M(int i) {
        K((byte) i);
        K((byte) (i >>> 8));
    }

    void N(short[] sArr, int i) {
        int i2;
        int i3;
        short s = sArr[1];
        if (s == 0) {
            i2 = 138;
            i3 = 3;
        } else {
            i2 = 7;
            i3 = 4;
        }
        s = -1;
        sArr[((i + 1) * 2) + 1] = -1;
        int i4 = 0;
        int i5 = 0;
        while (i4 <= i) {
            i4++;
            short s2 = sArr[(i4 * 2) + 1];
            i5++;
            if (i5 >= i2 || s != s2) {
                if (i5 < i3) {
                    short[] sArr2 = this.S0;
                    int i6 = s * 2;
                    sArr2[i6] = (short) (sArr2[i6] + ((short) i5));
                } else if (s != 0) {
                    if (s != s) {
                        short[] sArr3 = this.S0;
                        int i7 = s * 2;
                        sArr3[i7] = (short) (sArr3[i7] + 1);
                    }
                    short[] sArr4 = this.S0;
                    sArr4[32] = (short) (sArr4[32] + 1);
                } else if (i5 <= 10) {
                    short[] sArr5 = this.S0;
                    sArr5[34] = (short) (sArr5[34] + 1);
                } else {
                    short[] sArr6 = this.S0;
                    sArr6[36] = (short) (sArr6[36] + 1);
                }
                if (s2 == 0) {
                    i2 = 138;
                } else {
                    if (s == s2) {
                        i2 = 6;
                    } else {
                        s = s;
                        i2 = 7;
                        i3 = 4;
                    }
                    i5 = 0;
                }
                i3 = 3;
                i5 = 0;
            }
            s = s2;
        }
    }

    void O(int i, int i2, int i3) {
        P(i - 257, 5);
        int i4 = i2 - 1;
        P(i4, 5);
        P(i3 - 4, 4);
        for (int i5 = 0; i5 < i3; i5++) {
            P(this.S0[(Tree.g[i5] * 2) + 1], 3);
        }
        S(this.Q0, i - 1);
        S(this.R0, i4);
    }

    void P(int i, int i2) {
        int i3 = this.l1;
        if (i3 <= 16 - i2) {
            this.k1 = (short) (((short) ((i << i3) & Settings.DEFAULT_INITIAL_WINDOW_SIZE)) | this.k1);
            this.l1 = i3 + i2;
            return;
        }
        short s = (short) (((short) ((i << i3) & Settings.DEFAULT_INITIAL_WINDOW_SIZE)) | this.k1);
        this.k1 = s;
        M(s);
        int i4 = this.l1;
        this.k1 = (short) (i >>> (16 - i4));
        this.l1 = i4 + (i2 - 16);
    }

    final void Q(int i, short[] sArr) {
        int i2 = i * 2;
        P(sArr[i2] & 65535, sArr[i2 + 1] & 65535);
    }

    void S(short[] sArr, int i) {
        int i2;
        int i3;
        int i4 = 1;
        short s = sArr[1];
        if (s == 0) {
            i2 = 138;
            i3 = 3;
        } else {
            i2 = 7;
            i3 = 4;
        }
        int i5 = 0;
        int i6 = 0;
        s = -1;
        while (i5 <= i) {
            i5++;
            short s2 = sArr[(i5 * 2) + i4];
            int i7 = i6 + 1;
            if (i7 >= i2 || s != s2) {
                if (i7 < i3) {
                    do {
                        Q(s, this.S0);
                        i7--;
                    } while (i7 != 0);
                } else if (s != 0) {
                    if (s != s) {
                        Q(s, this.S0);
                    } else {
                        i6 = i7;
                    }
                    Q(16, this.S0);
                    P(i6 - 3, 2);
                } else if (i7 <= 10) {
                    Q(17, this.S0);
                    P(i6 - 2, 3);
                } else {
                    Q(18, this.S0);
                    P(i6 - 10, 7);
                }
                if (s2 == 0) {
                    i2 = 138;
                } else {
                    if (s == s2) {
                        i2 = 6;
                    } else {
                        s = s;
                        i2 = 7;
                        i3 = 4;
                    }
                    i6 = 0;
                }
                i3 = 3;
                i6 = 0;
            } else {
                i6 = i7;
            }
            s = s2;
            i4 = 1;
        }
    }

    void T() {
        int i = 0;
        int i2 = 0;
        while (i < 7) {
            i2 += this.Q0[i * 2];
            i++;
        }
        int i3 = 0;
        while (i < 128) {
            i3 += this.Q0[i * 2];
            i++;
        }
        while (i < 256) {
            i2 += this.Q0[i * 2];
            i++;
        }
        this.n0 = (byte) (i2 <= (i3 >>> 2) ? 1 : 0);
    }

    void V() {
        Tree tree = this.T0;
        tree.a = this.Q0;
        tree.c = StaticTree.h;
        Tree tree2 = this.U0;
        tree2.a = this.R0;
        tree2.c = StaticTree.i;
        Tree tree3 = this.V0;
        tree3.a = this.S0;
        tree3.c = StaticTree.j;
        this.k1 = (short) 0;
        this.l1 = 0;
        this.j1 = 8;
        F();
    }

    void b() {
        P(2, 3);
        short[] sArr = StaticTree.f;
        Q(256, sArr);
        g();
        if ((this.j1 + 11) - this.l1 < 9) {
            P(2, 3);
            Q(256, sArr);
            g();
        }
        this.j1 = 7;
    }

    void c(int i, int i2, boolean z) {
        int i3;
        int iK;
        int i4;
        if (this.M0 > 0) {
            if (this.n0 == 2) {
                T();
            }
            this.T0.b(this);
            this.U0.b(this);
            iK = k();
            i3 = (this.g1 + 10) >>> 3;
            i4 = (this.h1 + 10) >>> 3;
            if (i4 <= i3) {
                i3 = i4;
            }
        } else {
            i3 = i2 + 5;
            iK = 0;
            i4 = i3;
        }
        if (i2 + 4 <= i3 && i != -1) {
            d(i, i2, z);
        } else if (i4 == i3) {
            P((z ? 1 : 0) + 2, 3);
            l(StaticTree.f, StaticTree.g);
        } else {
            P((z ? 1 : 0) + 4, 3);
            O(this.T0.b + 1, this.U0.b + 1, iK + 1);
            l(this.Q0, this.R0);
        }
        F();
        if (z) {
            j();
        }
    }

    public Object clone() throws CloneNotSupportedException {
        Deflate deflate = (Deflate) super.clone();
        deflate.Y = w(deflate.Y);
        deflate.c1 = w(deflate.c1);
        deflate.t0 = w(deflate.t0);
        deflate.v0 = B(deflate.v0);
        deflate.w0 = B(deflate.w0);
        deflate.Q0 = B(deflate.Q0);
        deflate.R0 = B(deflate.R0);
        deflate.S0 = B(deflate.S0);
        deflate.W0 = B(deflate.W0);
        deflate.X0 = B(deflate.X0);
        deflate.Y0 = z(deflate.Y0);
        deflate.b1 = w(deflate.b1);
        deflate.T0.a = deflate.Q0;
        deflate.U0.a = deflate.R0;
        deflate.V0.a = deflate.S0;
        GZIPHeader gZIPHeader = deflate.m1;
        if (gZIPHeader != null) {
            deflate.m1 = (GZIPHeader) gZIPHeader.clone();
        }
        return deflate;
    }

    void d(int i, int i2, boolean z) {
        P(z ? 1 : 0, 3);
        m(i, i2, true);
    }

    boolean e(int i, int i2) {
        byte[] bArr = this.Y;
        int i3 = this.f1;
        int i4 = this.e1;
        bArr[(i4 * 2) + i3] = (byte) (i >>> 8);
        bArr[i3 + (i4 * 2) + 1] = (byte) i;
        this.c1[i4] = (byte) i2;
        this.e1 = i4 + 1;
        if (i == 0) {
            short[] sArr = this.Q0;
            int i5 = i2 * 2;
            sArr[i5] = (short) (sArr[i5] + 1);
        } else {
            this.i1++;
            short[] sArr2 = this.Q0;
            int i6 = (Tree.i[i2] + 257) * 2;
            sArr2[i6] = (short) (sArr2[i6] + 1);
            short[] sArr3 = this.R0;
            int iC = Tree.c(i - 1) * 2;
            sArr3[iC] = (short) (sArr3[iC] + 1);
        }
        int i7 = this.e1;
        if ((i7 & 8191) == 0 && this.M0 > 2) {
            int i8 = i7 * 8;
            int i9 = this.G0 - this.C0;
            for (int i10 = 0; i10 < 30; i10++) {
                i8 += this.R0[i10 * 2] * (Tree.e[i10] + 5);
            }
            int i11 = i8 >>> 3;
            if (this.i1 < this.e1 / 2 && i11 < i9 / 2) {
                return true;
            }
        }
        return this.e1 == this.d1 - 1;
    }

    void g() {
        int i = this.l1;
        if (i == 16) {
            M(this.k1);
            this.k1 = (short) 0;
            this.l1 = 0;
        } else if (i >= 8) {
            K((byte) this.k1);
            this.k1 = (short) (this.k1 >>> 8);
            this.l1 -= 8;
        }
    }

    void j() {
        int i = this.l1;
        if (i > 8) {
            M(this.k1);
        } else if (i > 0) {
            K((byte) this.k1);
        }
        this.k1 = (short) 0;
        this.l1 = 0;
    }

    int k() {
        N(this.Q0, this.T0.b);
        N(this.R0, this.U0.b);
        this.V0.b(this);
        int i = 18;
        while (i >= 3 && this.S0[(Tree.g[i] * 2) + 1] == 0) {
            i--;
        }
        this.g1 += ((i + 1) * 3) + 14;
        return i;
    }

    void l(short[] sArr, short[] sArr2) {
        if (this.e1 != 0) {
            int i = 0;
            do {
                byte[] bArr = this.Y;
                int i2 = this.f1;
                int i3 = i * 2;
                int i4 = (bArr[i2 + i3 + 1] & 255) | ((bArr[i2 + i3] << 8) & 65280);
                int i5 = this.c1[i] & 255;
                i++;
                if (i4 == 0) {
                    Q(i5, sArr);
                } else {
                    byte b = Tree.i[i5];
                    Q(b + 257, sArr);
                    int i6 = Tree.d[b];
                    if (i6 != 0) {
                        P(i5 - Tree.j[b], i6);
                    }
                    int i7 = i4 - 1;
                    int iC = Tree.c(i7);
                    Q(iC, sArr2);
                    int i8 = Tree.e[iC];
                    if (i8 != 0) {
                        P(i7 - Tree.k[iC], i8);
                    }
                }
            } while (i < this.e1);
        }
        Q(256, sArr);
        this.j1 = sArr[513];
    }

    void m(int i, int i2, boolean z) {
        j();
        this.j1 = 8;
        if (z) {
            M((short) i2);
            M((short) (~i2));
        }
        L(this.t0, i, i2);
    }

    int n(int i) {
        int i2;
        char c;
        int i3;
        long j;
        int iV;
        if (i <= 4 && i >= 0) {
            ZStream zStream = this.q;
            if (zStream.e != null && ((zStream.a != null || zStream.c == 0) && ((i2 = this.X) != 666 || i == 4))) {
                if (zStream.g == 0) {
                    zStream.i = o1[7];
                    return -5;
                }
                int i4 = this.p0;
                this.p0 = i;
                if (i2 != 42) {
                    c = 7;
                    i3 = -5;
                    j = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                } else if (this.m0 == 2) {
                    E().b(this);
                    this.X = 113;
                    this.q.m.a();
                    c = 7;
                    i3 = -5;
                    j = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                } else {
                    int i5 = (((this.r0 - 8) << 4) + 8) << 8;
                    i3 = -5;
                    int i6 = ((this.M0 - 1) & 255) >> 1;
                    if (i6 > 3) {
                        i6 = 3;
                    }
                    int i7 = i5 | (i6 << 6);
                    if (this.G0 != 0) {
                        i7 |= 32;
                    }
                    this.X = 113;
                    J(i7 + (31 - (i7 % 31)));
                    if (this.G0 != 0) {
                        long value = this.q.m.getValue();
                        c = 7;
                        j = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                        J((int) (value >>> 16));
                        J((int) (value & WebSocketProtocol.PAYLOAD_SHORT_MAX));
                    } else {
                        c = 7;
                        j = WebSocketProtocol.PAYLOAD_SHORT_MAX;
                    }
                    this.q.m.a();
                }
                if (this.l0 != 0) {
                    this.q.a();
                    if (this.q.g == 0) {
                        this.p0 = -1;
                        return 0;
                    }
                } else {
                    ZStream zStream2 = this.q;
                    if (zStream2.c == 0 && i <= i4 && i != 4) {
                        zStream2.i = o1[c];
                        return i3;
                    }
                }
                int i8 = this.X;
                if (i8 == 666) {
                    ZStream zStream3 = this.q;
                    if (zStream3.c != 0) {
                        zStream3.i = o1[c];
                        return i3;
                    }
                }
                if (this.q.c != 0 || this.I0 != 0 || (i != 0 && i8 != 666)) {
                    int i9 = n1[this.M0].e;
                    if (i9 == 0) {
                        iV = v(i);
                    } else if (i9 != 1) {
                        iV = i9 != 2 ? -1 : u(i);
                    } else {
                        iV = t(i);
                    }
                    if (iV == 2 || iV == 3) {
                        this.X = 666;
                    }
                    if (iV == 0 || iV == 2) {
                        if (this.q.g == 0) {
                            this.p0 = -1;
                        }
                        return 0;
                    }
                    if (iV == 1) {
                        if (i == 1) {
                            b();
                        } else {
                            d(0, 0, false);
                            if (i == 3) {
                                for (int i10 = 0; i10 < this.y0; i10++) {
                                    this.w0[i10] = 0;
                                }
                            }
                        }
                        this.q.a();
                        if (this.q.g == 0) {
                            this.p0 = -1;
                            return 0;
                        }
                    }
                }
                if (i != 4) {
                    return 0;
                }
                int i11 = this.m0;
                if (i11 <= 0) {
                    return 1;
                }
                if (i11 == 2) {
                    long value2 = this.q.m.getValue();
                    K((byte) (value2 & 255));
                    K((byte) ((value2 >> 8) & 255));
                    K((byte) ((value2 >> 16) & 255));
                    K((byte) ((value2 >> 24) & 255));
                    K((byte) (this.q.d & 255));
                    K((byte) ((this.q.d >> 8) & 255));
                    K((byte) ((this.q.d >> 16) & 255));
                    K((byte) (255 & (this.q.d >> 24)));
                    E().c(value2);
                } else {
                    long value3 = this.q.m.getValue();
                    J((int) (value3 >>> 16));
                    J((int) (value3 & j));
                }
                this.q.a();
                int i12 = this.m0;
                if (i12 > 0) {
                    this.m0 = -i12;
                }
                return this.l0 != 0 ? 0 : 1;
            }
            zStream.i = o1[4];
        }
        return -2;
    }

    int o() {
        int i = this.X;
        if (i != 42 && i != 113 && i != 666) {
            return -2;
        }
        this.Y = null;
        this.c1 = null;
        this.w0 = null;
        this.v0 = null;
        this.t0 = null;
        return i == 113 ? -3 : 0;
    }

    int q(int i, int i2) {
        return r(i, 8, i2, 8, 0);
    }

    int s() {
        ZStream zStream = this.q;
        zStream.h = 0L;
        zStream.d = 0L;
        zStream.i = null;
        zStream.l = 2;
        this.l0 = 0;
        this.k0 = 0;
        int i = this.m0;
        if (i < 0) {
            this.m0 = -i;
        }
        this.X = this.m0 == 0 ? 113 : 42;
        zStream.m.a();
        this.p0 = 0;
        V();
        G();
        return 0;
    }

    int t(int i) {
        boolean zE;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        while (true) {
            if (this.I0 < 262) {
                C();
                int i6 = this.I0;
                if (i6 < 262 && i == 0) {
                    return 0;
                }
                if (i6 == 0) {
                    D(i == 4);
                    if (this.q.g == 0) {
                        return i == 4 ? 2 : 0;
                    }
                    return i == 4 ? 3 : 1;
                }
            }
            if (this.I0 >= 3) {
                int i7 = this.x0 << this.B0;
                byte[] bArr = this.t0;
                int i8 = this.G0;
                int i9 = (i7 ^ (bArr[i8 + 2] & 255)) & this.A0;
                this.x0 = i9;
                short[] sArr = this.w0;
                short s = sArr[i9];
                this.v0[this.s0 & i8] = s;
                sArr[i9] = (short) i8;
                i5 = s & 65535;
            }
            if (i5 != 0 && ((this.G0 - i5) & Settings.DEFAULT_INITIAL_WINDOW_SIZE) <= this.q0 - 262 && this.N0 != 2) {
                this.D0 = H(i5);
            }
            int i10 = this.D0;
            if (i10 >= 3) {
                zE = e(this.G0 - this.H0, i10 - 3);
                int i11 = this.I0;
                int i12 = this.D0;
                int i13 = i11 - i12;
                this.I0 = i13;
                if (i12 > this.L0 || i13 < 3) {
                    int i14 = this.G0 + i12;
                    this.G0 = i14;
                    this.D0 = 0;
                    byte[] bArr2 = this.t0;
                    int i15 = bArr2[i14] & 255;
                    this.x0 = i15;
                    this.x0 = ((bArr2[i14 + 1] & 255) ^ (i15 << this.B0)) & this.A0;
                } else {
                    this.D0 = i12 - 1;
                    do {
                        i2 = this.G0;
                        int i16 = i2 + 1;
                        this.G0 = i16;
                        int i17 = ((this.x0 << this.B0) ^ (this.t0[i2 + 3] & 255)) & this.A0;
                        this.x0 = i17;
                        short[] sArr2 = this.w0;
                        short s2 = sArr2[i17];
                        i3 = s2 & 65535;
                        this.v0[this.s0 & i16] = s2;
                        sArr2[i17] = (short) i16;
                        i4 = this.D0 - 1;
                        this.D0 = i4;
                    } while (i4 != 0);
                    this.G0 = i2 + 2;
                    i5 = i3;
                }
            } else {
                zE = e(0, this.t0[this.G0] & 255);
                this.I0--;
                this.G0++;
            }
            if (zE) {
                D(false);
                if (this.q.g == 0) {
                    return 0;
                }
            }
        }
    }

    int u(int i) {
        int i2;
        int i3;
        int i4 = 0;
        while (true) {
            if (this.I0 < 262) {
                C();
                int i5 = this.I0;
                if (i5 < 262 && i == 0) {
                    return 0;
                }
                if (i5 == 0) {
                    if (this.F0 != 0) {
                        e(0, this.t0[this.G0 - 1] & 255);
                        this.F0 = 0;
                    }
                    D(i == 4);
                    if (this.q.g == 0) {
                        return i == 4 ? 2 : 0;
                    }
                    return i == 4 ? 3 : 1;
                }
            }
            if (this.I0 >= 3) {
                int i6 = this.x0 << this.B0;
                byte[] bArr = this.t0;
                int i7 = this.G0;
                int i8 = (i6 ^ (bArr[i7 + 2] & 255)) & this.A0;
                this.x0 = i8;
                short[] sArr = this.w0;
                short s = sArr[i8];
                this.v0[this.s0 & i7] = s;
                sArr[i8] = (short) i7;
                i4 = s & 65535;
            }
            int i9 = this.D0;
            this.J0 = i9;
            this.E0 = this.H0;
            this.D0 = 2;
            if (i4 != 0 && i9 < this.L0 && ((this.G0 - i4) & Settings.DEFAULT_INITIAL_WINDOW_SIZE) <= this.q0 - 262) {
                if (this.N0 != 2) {
                    this.D0 = H(i4);
                }
                int i10 = this.D0;
                if (i10 <= 5 && (this.N0 == 1 || (i10 == 3 && this.G0 - this.H0 > 4096))) {
                    this.D0 = 2;
                }
            }
            int i11 = this.J0;
            if (i11 >= 3 && this.D0 <= i11) {
                int i12 = this.G0;
                int i13 = (this.I0 + i12) - 3;
                boolean zE = e((i12 - 1) - this.E0, i11 - 3);
                int i14 = this.I0;
                int i15 = this.J0;
                this.I0 = i14 - (i15 - 1);
                this.J0 = i15 - 2;
                do {
                    i2 = this.G0;
                    int i16 = i2 + 1;
                    this.G0 = i16;
                    if (i16 <= i13) {
                        int i17 = ((this.x0 << this.B0) ^ (this.t0[i2 + 3] & 255)) & this.A0;
                        this.x0 = i17;
                        short[] sArr2 = this.w0;
                        short s2 = sArr2[i17];
                        this.v0[this.s0 & i16] = s2;
                        sArr2[i17] = (short) i16;
                        i4 = s2 & 65535;
                    }
                    i3 = this.J0 - 1;
                    this.J0 = i3;
                } while (i3 != 0);
                this.F0 = 0;
                this.D0 = 2;
                this.G0 = i2 + 2;
                if (zE) {
                    D(false);
                    if (this.q.g == 0) {
                        return 0;
                    }
                } else {
                    continue;
                }
            } else if (this.F0 != 0) {
                if (e(0, this.t0[this.G0 - 1] & 255)) {
                    D(false);
                }
                this.G0++;
                this.I0--;
                if (this.q.g == 0) {
                    return 0;
                }
            } else {
                this.F0 = 1;
                this.G0++;
                this.I0--;
            }
        }
    }

    int v(int i) {
        int i2 = this.Z;
        int i3 = i2 - 5;
        int i4 = Settings.DEFAULT_INITIAL_WINDOW_SIZE;
        if (65535 > i3) {
            i4 = i2 - 5;
        }
        while (true) {
            if (this.I0 <= 1) {
                C();
                int i5 = this.I0;
                if (i5 == 0 && i == 0) {
                    return 0;
                }
                if (i5 == 0) {
                    D(i == 4);
                    if (this.q.g == 0) {
                        return i == 4 ? 2 : 0;
                    }
                    return i == 4 ? 3 : 1;
                }
            }
            int i6 = this.G0 + this.I0;
            this.G0 = i6;
            this.I0 = 0;
            int i7 = this.C0 + i4;
            if (i6 == 0 || i6 >= i7) {
                this.I0 = i6 - i7;
                this.G0 = i7;
                D(false);
                if (this.q.g == 0) {
                    return 0;
                }
            }
            if (this.G0 - this.C0 >= this.q0 - 262) {
                D(false);
                if (this.q.g == 0) {
                    return 0;
                }
            }
        }
    }
}
