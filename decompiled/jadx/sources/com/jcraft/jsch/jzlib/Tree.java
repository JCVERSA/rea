package com.jcraft.jsch.jzlib;

import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
final class Tree {
    static final int[] d = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 0};
    static final int[] e = {0, 0, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13};
    static final int[] f = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 3, 7};
    static final byte[] g = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    static final byte[] h = {0, 1, 2, 3, 4, 4, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 0, 0, 16, 17, 18, 18, 19, 19, 20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29};
    static final byte[] i = {0, 1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15, 16, 16, 16, 16, 16, 16, 16, 16, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 19, 19, 19, 19, 19, 19, 19, 19, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 28};
    static final int[] j = {0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 12, 14, 16, 20, 24, 28, 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 0};
    static final int[] k = {0, 1, 2, 3, 4, 6, 8, 12, 16, 24, 32, 48, 64, 96, 128, 192, 256, 384, 512, 768, 1024, 1536, 2048, 3072, 4096, 6144, 8192, 12288, Http2.INITIAL_MAX_FRAME_SIZE, 24576};
    short[] a;
    int b;
    StaticTree c;

    Tree() {
    }

    private static final int a(int i2, int i3) {
        int i4 = 0;
        do {
            int i5 = i4 | (i2 & 1);
            i2 >>>= 1;
            i4 = i5 << 1;
            i3--;
        } while (i3 > 0);
        return i4 >>> 1;
    }

    static int c(int i2) {
        return i2 < 256 ? h[i2] : h[(i2 >>> 7) + 256];
    }

    private static final void e(short[] sArr, int i2, short[] sArr2, short[] sArr3) {
        sArr3[0] = 0;
        short s = 0;
        for (int i3 = 1; i3 <= 15; i3++) {
            s = (short) ((s + sArr2[i3 - 1]) << 1);
            sArr3[i3] = s;
        }
        for (int i4 = 0; i4 <= i2; i4++) {
            int i5 = i4 * 2;
            short s2 = sArr[i5 + 1];
            if (s2 != 0) {
                short s3 = sArr3[s2];
                sArr3[s2] = (short) (s3 + 1);
                sArr[i5] = (short) a(s3, s2);
            }
        }
    }

    void b(Deflate deflate) {
        int i2;
        int i3;
        int i4;
        short[] sArr = this.a;
        StaticTree staticTree = this.c;
        short[] sArr2 = staticTree.a;
        int i5 = staticTree.d;
        deflate.Z0 = 0;
        deflate.a1 = 573;
        int i6 = -1;
        for (int i7 = 0; i7 < i5; i7++) {
            int i8 = i7 * 2;
            if (sArr[i8] != 0) {
                int[] iArr = deflate.Y0;
                int i9 = deflate.Z0 + 1;
                deflate.Z0 = i9;
                iArr[i9] = i7;
                deflate.b1[i7] = 0;
                i6 = i7;
            } else {
                sArr[i8 + 1] = 0;
            }
        }
        while (true) {
            i2 = deflate.Z0;
            if (i2 >= 2) {
                break;
            }
            int[] iArr2 = deflate.Y0;
            int i10 = i2 + 1;
            deflate.Z0 = i10;
            if (i6 < 2) {
                i4 = i6 + 1;
                i3 = i4;
            } else {
                i3 = i6;
                i4 = 0;
            }
            iArr2[i10] = i4;
            int i11 = i4 * 2;
            sArr[i11] = 1;
            deflate.b1[i4] = 0;
            deflate.g1--;
            if (sArr2 != null) {
                deflate.h1 -= sArr2[i11 + 1];
            }
            i6 = i3;
        }
        this.b = i6;
        for (int i12 = i2 / 2; i12 >= 1; i12--) {
            deflate.I(sArr, i12);
        }
        while (true) {
            int[] iArr3 = deflate.Y0;
            int i13 = iArr3[1];
            int i14 = deflate.Z0;
            deflate.Z0 = i14 - 1;
            iArr3[1] = iArr3[i14];
            deflate.I(sArr, 1);
            int[] iArr4 = deflate.Y0;
            int i15 = iArr4[1];
            int i16 = deflate.a1;
            int i17 = i16 - 1;
            deflate.a1 = i17;
            iArr4[i17] = i13;
            int i18 = i16 - 2;
            deflate.a1 = i18;
            iArr4[i18] = i15;
            int i19 = i13 * 2;
            int i20 = i15 * 2;
            sArr[i5 * 2] = (short) (sArr[i19] + sArr[i20]);
            byte[] bArr = deflate.b1;
            bArr[i5] = (byte) (Math.max((int) bArr[i13], (int) bArr[i15]) + 1);
            short s = (short) i5;
            sArr[i20 + 1] = s;
            sArr[i19 + 1] = s;
            int i21 = i5 + 1;
            deflate.Y0[1] = i5;
            deflate.I(sArr, 1);
            if (deflate.Z0 < 2) {
                int[] iArr5 = deflate.Y0;
                int i22 = deflate.a1 - 1;
                deflate.a1 = i22;
                iArr5[i22] = iArr5[1];
                d(deflate);
                e(sArr, i6, deflate.W0, deflate.X0);
                return;
            }
            i5 = i21;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    void d(Deflate deflate) {
        short[] sArr;
        short s;
        short[] sArr2 = this.a;
        StaticTree staticTree = this.c;
        short[] sArr3 = staticTree.a;
        int[] iArr = staticTree.b;
        int i2 = staticTree.c;
        int i3 = staticTree.e;
        for (int i4 = 0; i4 <= 15; i4++) {
            deflate.W0[i4] = 0;
        }
        int[] iArr2 = deflate.Y0;
        int i5 = deflate.a1;
        sArr2[(iArr2[i5] * 2) + 1] = 0;
        int i6 = i5 + 1;
        int i7 = 0;
        while (i6 < 573) {
            int i8 = deflate.Y0[i6];
            int i9 = i8 * 2;
            int i10 = i9 + 1;
            int i11 = sArr2[(sArr2[i10] * 2) + 1] + 1;
            if (i11 > i3) {
                i7++;
                i11 = i3;
            }
            sArr2[i10] = (short) i11;
            if (i8 <= this.b) {
                short[] sArr4 = deflate.W0;
                sArr4[i11] = (short) (sArr4[i11] + 1);
                int i12 = i8 >= i2 ? iArr[i8 - i2] : 0;
                short s2 = sArr2[i9];
                deflate.g1 += (i11 + i12) * s2;
                if (sArr3 != null) {
                    deflate.h1 += s2 * (sArr3[i10] + i12);
                }
            }
            i6++;
        }
        if (i7 == 0) {
            return;
        }
        do {
            int i13 = i3 - 1;
            while (true) {
                sArr = deflate.W0;
                s = sArr[i13];
                if (s != 0) {
                    break;
                } else {
                    i13--;
                }
            }
            sArr[i13] = (short) (s - 1);
            int i14 = i13 + 1;
            sArr[i14] = (short) (sArr[i14] + 2);
            sArr[i3] = (short) (sArr[i3] - 1);
            i7 -= 2;
        } while (i7 > 0);
        while (i3 != 0) {
            short s3 = deflate.W0[i3];
            while (s3 != 0) {
                i6--;
                int i15 = deflate.Y0[i6];
                if (i15 <= this.b) {
                    int i16 = i15 * 2;
                    int i17 = i16 + 1;
                    short s4 = sArr2[i17];
                    if (s4 != i3) {
                        deflate.g1 += (int) ((((long) i3) - ((long) s4)) * ((long) sArr2[i16]));
                        sArr2[i17] = (short) i3;
                    }
                    s3--;
                }
            }
            i3--;
        }
    }
}
