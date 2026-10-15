package com.jcraft.jsch.jzlib;

import okhttp3.internal.http2.Settings;

/* JADX INFO: loaded from: classes3.dex */
final class InfCodes {
    private static final int[] q = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, Settings.DEFAULT_INITIAL_WINDOW_SIZE};
    int a;
    int b;
    int[] c;
    int d = 0;
    int e;
    int f;
    int g;
    int h;
    byte i;
    byte j;
    int[] k;
    int l;
    int[] m;
    int n;
    private final ZStream o;
    private final InfBlocks p;

    InfCodes(ZStream zStream, InfBlocks infBlocks) {
        this.o = zStream;
        this.p = infBlocks;
    }

    void a(ZStream zStream) {
    }

    /* JADX WARN: Code duplicated, block: B:75:0x01b3  */
    int b(int i, int i2, int[] iArr, int i3, int[] iArr2, int i4, InfBlocks infBlocks, ZStream zStream) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10 = zStream.b;
        int i11 = zStream.c;
        int i12 = infBlocks.q;
        int i13 = infBlocks.p;
        int i14 = infBlocks.v;
        int i15 = infBlocks.u;
        int i16 = i14 < i15 ? (i15 - i14) - 1 : infBlocks.t - i14;
        int[] iArr3 = q;
        int i17 = iArr3[i];
        int i18 = iArr3[i2];
        while (true) {
            if (i13 >= 20) {
                int i19 = i12 & i17;
                int i20 = (i3 + i19) * 3;
                int i21 = iArr[i20];
                if (i21 == 0) {
                    int i22 = iArr[i20 + 1];
                    i12 >>= i22;
                    i13 -= i22;
                    i7 = i14 + 1;
                    infBlocks.s[i14] = (byte) iArr[i20 + 2];
                } else {
                    while (true) {
                        int i23 = iArr[i20 + 1];
                        i12 >>= i23;
                        i13 -= i23;
                        if ((i21 & 16) != 0) {
                            int i24 = i21 & 15;
                            int i25 = iArr[i20 + 2] + (q[i24] & i12);
                            int i26 = i12 >> i24;
                            int i27 = i13 - i24;
                            while (i27 < 15) {
                                i11--;
                                i26 |= (zStream.a[i10] & 255) << i27;
                                i27 += 8;
                                i10++;
                            }
                            int i28 = i26 & i18;
                            int i29 = (i4 + i28) * 3;
                            int i30 = iArr2[i29];
                            while (true) {
                                int i31 = iArr2[i29 + 1];
                                i26 >>= i31;
                                i27 -= i31;
                                if ((i30 & 16) != 0) {
                                    int i32 = i30 & 15;
                                    int i33 = i10;
                                    int i34 = i11;
                                    while (i27 < i32) {
                                        i34--;
                                        i26 |= (zStream.a[i33] & 255) << i27;
                                        i27 += 8;
                                        i33++;
                                    }
                                    int i35 = iArr2[i29 + 2] + (q[i32] & i26);
                                    int i36 = i26 >> i32;
                                    int i37 = i27 - i32;
                                    int i38 = i16 - i25;
                                    if (i14 >= i35) {
                                        int i39 = i14 - i35;
                                        int i40 = i14 - i39;
                                        if (i40 <= 0 || 2 <= i40) {
                                            byte[] bArr = infBlocks.s;
                                            System.arraycopy(bArr, i39, bArr, i14, 2);
                                            i14 += 2;
                                            i5 = i39 + 2;
                                        } else {
                                            byte[] bArr2 = infBlocks.s;
                                            int i41 = i14 + 1;
                                            int i42 = i39 + 1;
                                            bArr2[i14] = bArr2[i39];
                                            i14 += 2;
                                            i5 = i39 + 2;
                                            bArr2[i41] = bArr2[i42];
                                        }
                                        i25 -= 2;
                                    } else {
                                        i5 = i14 - i35;
                                        do {
                                            i6 = infBlocks.t;
                                            i5 += i6;
                                        } while (i5 < 0);
                                        int i43 = i6 - i5;
                                        if (i25 > i43) {
                                            i25 -= i43;
                                            int i44 = i14 - i5;
                                            if (i44 <= 0 || i43 <= i44) {
                                                byte[] bArr3 = infBlocks.s;
                                                System.arraycopy(bArr3, i5, bArr3, i14, i43);
                                                i14 += i43;
                                            } else {
                                                while (true) {
                                                    byte[] bArr4 = infBlocks.s;
                                                    int i45 = i5 + 1;
                                                    bArr4[i14] = bArr4[i5];
                                                    i43--;
                                                    i14++;
                                                    if (i43 == 0) {
                                                        break;
                                                    }
                                                    i5 = i45;
                                                }
                                            }
                                            i5 = 0;
                                        }
                                    }
                                    int i46 = i14 - i5;
                                    if (i46 <= 0 || i25 <= i46) {
                                        byte[] bArr5 = infBlocks.s;
                                        System.arraycopy(bArr5, i5, bArr5, i14, i25);
                                        i14 += i25;
                                    } else {
                                        while (true) {
                                            byte[] bArr6 = infBlocks.s;
                                            int i47 = i5 + 1;
                                            bArr6[i14] = bArr6[i5];
                                            i25--;
                                            i14++;
                                            if (i25 == 0) {
                                                break;
                                            }
                                            i5 = i47;
                                        }
                                    }
                                    i11 = i34;
                                    i10 = i33;
                                    i12 = i36;
                                    i13 = i37;
                                    i16 = i38;
                                    break;
                                }
                                if ((i30 & 64) != 0) {
                                    zStream.i = "invalid distance code";
                                    int i48 = zStream.c - i11;
                                    int i49 = i27 >> 3;
                                    if (i49 < i48) {
                                        i48 = i49;
                                    }
                                    int i50 = i10 - i48;
                                    infBlocks.q = i26;
                                    infBlocks.p = i27 - (i48 << 3);
                                    zStream.c = i11 + i48;
                                    zStream.d += (long) (i50 - zStream.b);
                                    zStream.b = i50;
                                    infBlocks.v = i14;
                                    return -3;
                                }
                                i28 = i28 + iArr2[i29 + 2] + (q[i30] & i26);
                                i29 = (i4 + i28) * 3;
                                i30 = iArr2[i29];
                            }
                        } else {
                            if ((i21 & 64) != 0) {
                                if ((i21 & 32) != 0) {
                                    int i51 = zStream.c - i11;
                                    int i52 = i13 >> 3;
                                    if (i52 < i51) {
                                        i51 = i52;
                                    }
                                    int i53 = i10 - i51;
                                    infBlocks.q = i12;
                                    infBlocks.p = i13 - (i51 << 3);
                                    zStream.c = i11 + i51;
                                    zStream.d += (long) (i53 - zStream.b);
                                    zStream.b = i53;
                                    infBlocks.v = i14;
                                    return 1;
                                }
                                zStream.i = "invalid literal/length code";
                                int i54 = zStream.c - i11;
                                int i55 = i13 >> 3;
                                if (i55 < i54) {
                                    i54 = i55;
                                }
                                int i56 = i10 - i54;
                                infBlocks.q = i12;
                                infBlocks.p = i13 - (i54 << 3);
                                zStream.c = i11 + i54;
                                zStream.d += (long) (i56 - zStream.b);
                                zStream.b = i56;
                                infBlocks.v = i14;
                                return -3;
                            }
                            i19 = i19 + iArr[i20 + 2] + (q[i21] & i12);
                            i20 = (i3 + i19) * 3;
                            i21 = iArr[i20];
                            if (i21 == 0) {
                                int i57 = iArr[i20 + 1];
                                i12 >>= i57;
                                i13 -= i57;
                                i7 = i14 + 1;
                                infBlocks.s[i14] = (byte) iArr[i20 + 2];
                            }
                        }
                    }
                    if (i16 >= 258 || i11 < 10) {
                        i8 = zStream.c - i11;
                        i9 = i13 >> 3;
                        if (i9 < i8) {
                            i8 = i9;
                        }
                        int i58 = i10 - i8;
                        infBlocks.q = i12;
                        infBlocks.p = i13 - (i8 << 3);
                        zStream.c = i11 + i8;
                        zStream.d += (long) (i58 - zStream.b);
                        zStream.b = i58;
                        infBlocks.v = i14;
                        return 0;
                    }
                }
                i16--;
                i14 = i7;
                if (i16 >= 258) {
                }
                i8 = zStream.c - i11;
                i9 = i13 >> 3;
                if (i9 < i8) {
                    i8 = i9;
                }
                int i59 = i10 - i8;
                infBlocks.q = i12;
                infBlocks.p = i13 - (i8 << 3);
                zStream.c = i11 + i8;
                zStream.d += (long) (i59 - zStream.b);
                zStream.b = i59;
                infBlocks.v = i14;
                return 0;
            }
            i11--;
            i12 |= (zStream.a[i10] & 255) << i13;
            i13 += 8;
            i10++;
        }
    }

    void c(int i, int i2, int[] iArr, int i3, int[] iArr2, int i4) {
        this.a = 0;
        this.i = (byte) i;
        this.j = (byte) i2;
        this.k = iArr;
        this.l = i3;
        this.m = iArr2;
        this.n = i4;
        this.c = null;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0248 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:102:0x024a A[LOOP:5: B:100:0x0246->B:102:0x024a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:110:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:129:0x0347 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:130:0x0349 A[LOOP:6: B:128:0x0345->B:130:0x0349, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:144:0x03c2  */
    /* JADX WARN: Code duplicated, block: B:159:0x01b7 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:161:0x025d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:162:0x02a1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:164:0x0292 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:165:0x02b2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:169:0x035c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:170:0x039a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:172:0x038f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:173:0x03ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:174:0x039e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:175:0x03be A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:176:0x03b1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:177:0x03c6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:59:0x0174 A[LOOP:2: B:58:0x0172->B:59:0x0174, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:62:0x017e  */
    /* JADX WARN: Code duplicated, block: B:63:0x0180  */
    /* JADX WARN: Code duplicated, block: B:68:0x018c  */
    /* JADX WARN: Code duplicated, block: B:69:0x0190  */
    /* JADX WARN: Code duplicated, block: B:72:0x0194  */
    /* JADX WARN: Code duplicated, block: B:74:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:75:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:80:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:85:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:88:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:89:0x01e8  */
    int d(int i) {
        int i2;
        int i3;
        int i4;
        int[] iArr;
        int i5;
        int i6;
        int i7;
        int[] iArr2;
        int i8;
        int i9;
        InfBlocks infBlocks;
        int i10;
        InfBlocks infBlocks2;
        int i11;
        InfBlocks infBlocks3;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        ZStream zStream = this.o;
        int i17 = zStream.b;
        int i18 = zStream.c;
        InfBlocks infBlocks4 = this.p;
        int i19 = infBlocks4.q;
        int i20 = infBlocks4.p;
        int i21 = infBlocks4.v;
        int i22 = infBlocks4.u;
        int i23 = i21 < i22 ? (i22 - i21) - 1 : infBlocks4.t - i21;
        int i24 = i17;
        int i25 = i18;
        int iB = i;
        while (true) {
            switch (this.a) {
                case 0:
                    if (i23 >= 258 && i25 >= 10) {
                        InfBlocks infBlocks5 = this.p;
                        infBlocks5.q = i19;
                        infBlocks5.p = i20;
                        ZStream zStream2 = this.o;
                        zStream2.c = i25;
                        zStream2.d += (long) (i24 - zStream2.b);
                        zStream2.b = i24;
                        infBlocks5.v = i21;
                        iB = b(this.i, this.j, this.k, this.l, this.m, this.n, infBlocks5, zStream2);
                        ZStream zStream3 = this.o;
                        i24 = zStream3.b;
                        i25 = zStream3.c;
                        InfBlocks infBlocks6 = this.p;
                        int i26 = infBlocks6.q;
                        int i27 = infBlocks6.p;
                        int i28 = infBlocks6.v;
                        int i29 = infBlocks6.u;
                        int i30 = i28 < i29 ? (i29 - i28) - 1 : infBlocks6.t - i28;
                        if (iB != 0) {
                            this.a = iB == 1 ? 7 : 9;
                            i23 = i30;
                            i19 = i26;
                            i20 = i27;
                            i21 = i28;
                        } else {
                            i23 = i30;
                            i19 = i26;
                            i20 = i27;
                            i21 = i28;
                        }
                        break;
                    }
                    this.e = this.i;
                    this.c = this.k;
                    this.d = this.l;
                    this.a = 1;
                    i3 = this.e;
                    while (i20 < i3) {
                        if (i25 != 0) {
                            InfBlocks infBlocks7 = this.p;
                            infBlocks7.q = i19;
                            infBlocks7.p = i20;
                            ZStream zStream4 = this.o;
                            zStream4.c = i25;
                            zStream4.d += (long) (i24 - zStream4.b);
                            zStream4.b = i24;
                            infBlocks7.v = i21;
                            return infBlocks7.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    i4 = (this.d + (q[i3] & i19)) * 3;
                    iArr = this.c;
                    int i31 = iArr[i4 + 1];
                    i19 >>>= i31;
                    i20 -= i31;
                    i5 = iArr[i4];
                    if (i5 == 0) {
                        this.f = iArr[i4 + 2];
                        this.a = 6;
                    } else if ((i5 & 16) != 0) {
                        this.g = i5 & 15;
                        this.b = iArr[i4 + 2];
                        this.a = 2;
                    } else if ((i5 & 64) == 0) {
                        this.e = i5;
                        this.d = (i4 / 3) + iArr[i4 + 2];
                    } else {
                        if ((i5 & 32) != 0) {
                            this.a = 9;
                            ZStream zStream5 = this.o;
                            zStream5.i = "invalid literal/length code";
                            InfBlocks infBlocks8 = this.p;
                            infBlocks8.q = i19;
                            infBlocks8.p = i20;
                            zStream5.c = i25;
                            zStream5.d += (long) (i24 - zStream5.b);
                            zStream5.b = i24;
                            infBlocks8.v = i21;
                            return infBlocks8.b(-3);
                        }
                        this.a = 7;
                    }
                    break;
                case 1:
                    i3 = this.e;
                    while (i20 < i3) {
                        if (i25 != 0) {
                            InfBlocks infBlocks9 = this.p;
                            infBlocks9.q = i19;
                            infBlocks9.p = i20;
                            ZStream zStream6 = this.o;
                            zStream6.c = i25;
                            zStream6.d += (long) (i24 - zStream6.b);
                            zStream6.b = i24;
                            infBlocks9.v = i21;
                            return infBlocks9.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    i4 = (this.d + (q[i3] & i19)) * 3;
                    iArr = this.c;
                    int i32 = iArr[i4 + 1];
                    i19 >>>= i32;
                    i20 -= i32;
                    i5 = iArr[i4];
                    if (i5 == 0) {
                        this.f = iArr[i4 + 2];
                        this.a = 6;
                    } else if ((i5 & 16) != 0) {
                        this.g = i5 & 15;
                        this.b = iArr[i4 + 2];
                        this.a = 2;
                    } else if ((i5 & 64) == 0) {
                        this.e = i5;
                        this.d = (i4 / 3) + iArr[i4 + 2];
                    } else {
                        if ((i5 & 32) != 0) {
                            this.a = 9;
                            ZStream zStream7 = this.o;
                            zStream7.i = "invalid literal/length code";
                            InfBlocks infBlocks10 = this.p;
                            infBlocks10.q = i19;
                            infBlocks10.p = i20;
                            zStream7.c = i25;
                            zStream7.d += (long) (i24 - zStream7.b);
                            zStream7.b = i24;
                            infBlocks10.v = i21;
                            return infBlocks10.b(-3);
                        }
                        this.a = 7;
                    }
                    break;
                case 2:
                    int i33 = this.g;
                    while (i20 < i33) {
                        if (i25 == 0) {
                            InfBlocks infBlocks11 = this.p;
                            infBlocks11.q = i19;
                            infBlocks11.p = i20;
                            ZStream zStream8 = this.o;
                            zStream8.c = i25;
                            zStream8.d += (long) (i24 - zStream8.b);
                            zStream8.b = i24;
                            infBlocks11.v = i21;
                            return infBlocks11.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    this.b += q[i33] & i19;
                    i19 >>= i33;
                    i20 -= i33;
                    this.e = this.j;
                    this.c = this.m;
                    this.d = this.n;
                    this.a = 3;
                    i6 = this.e;
                    while (i20 < i6) {
                        if (i25 != 0) {
                            InfBlocks infBlocks12 = this.p;
                            infBlocks12.q = i19;
                            infBlocks12.p = i20;
                            ZStream zStream9 = this.o;
                            zStream9.c = i25;
                            zStream9.d += (long) (i24 - zStream9.b);
                            zStream9.b = i24;
                            infBlocks12.v = i21;
                            return infBlocks12.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    i7 = (this.d + (q[i6] & i19)) * 3;
                    iArr2 = this.c;
                    int i34 = iArr2[i7 + 1];
                    i19 >>= i34;
                    i20 -= i34;
                    i8 = iArr2[i7];
                    if ((i8 & 16) != 0) {
                        this.g = i8 & 15;
                        this.h = iArr2[i7 + 2];
                        this.a = 4;
                    } else {
                        if ((i8 & 64) == 0) {
                            this.a = 9;
                            ZStream zStream10 = this.o;
                            zStream10.i = "invalid distance code";
                            InfBlocks infBlocks13 = this.p;
                            infBlocks13.q = i19;
                            infBlocks13.p = i20;
                            zStream10.c = i25;
                            zStream10.d += (long) (i24 - zStream10.b);
                            zStream10.b = i24;
                            infBlocks13.v = i21;
                            return infBlocks13.b(-3);
                        }
                        this.e = i8;
                        this.d = (i7 / 3) + iArr2[i7 + 2];
                    }
                    break;
                case 3:
                    i6 = this.e;
                    while (i20 < i6) {
                        if (i25 != 0) {
                            InfBlocks infBlocks14 = this.p;
                            infBlocks14.q = i19;
                            infBlocks14.p = i20;
                            ZStream zStream11 = this.o;
                            zStream11.c = i25;
                            zStream11.d += (long) (i24 - zStream11.b);
                            zStream11.b = i24;
                            infBlocks14.v = i21;
                            return infBlocks14.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    i7 = (this.d + (q[i6] & i19)) * 3;
                    iArr2 = this.c;
                    int i35 = iArr2[i7 + 1];
                    i19 >>= i35;
                    i20 -= i35;
                    i8 = iArr2[i7];
                    if ((i8 & 16) != 0) {
                        this.g = i8 & 15;
                        this.h = iArr2[i7 + 2];
                        this.a = 4;
                    } else {
                        if ((i8 & 64) == 0) {
                            this.a = 9;
                            ZStream zStream12 = this.o;
                            zStream12.i = "invalid distance code";
                            InfBlocks infBlocks15 = this.p;
                            infBlocks15.q = i19;
                            infBlocks15.p = i20;
                            zStream12.c = i25;
                            zStream12.d += (long) (i24 - zStream12.b);
                            zStream12.b = i24;
                            infBlocks15.v = i21;
                            return infBlocks15.b(-3);
                        }
                        this.e = i8;
                        this.d = (i7 / 3) + iArr2[i7 + 2];
                    }
                    break;
                case 4:
                    int i36 = this.g;
                    while (i20 < i36) {
                        if (i25 == 0) {
                            InfBlocks infBlocks16 = this.p;
                            infBlocks16.q = i19;
                            infBlocks16.p = i20;
                            ZStream zStream13 = this.o;
                            zStream13.c = i25;
                            zStream13.d += (long) (i24 - zStream13.b);
                            zStream13.b = i24;
                            infBlocks16.v = i21;
                            return infBlocks16.b(iB);
                        }
                        i25--;
                        i19 |= (this.o.a[i24] & 255) << i20;
                        i20 += 8;
                        i24++;
                        iB = 0;
                    }
                    this.h += q[i36] & i19;
                    i19 >>= i36;
                    i20 -= i36;
                    this.a = 5;
                    i9 = i21 - this.h;
                    while (i9 < 0) {
                        i9 += this.p.t;
                    }
                    while (this.b != 0) {
                        if (i23 == 0) {
                            infBlocks2 = this.p;
                            i11 = infBlocks2.t;
                            if (i21 == i11 && (i16 = infBlocks2.u) != 0) {
                                if (i16 > 0) {
                                    i23 = i16 - 1;
                                } else {
                                    i23 = i11;
                                }
                                i21 = 0;
                            }
                            if (i23 == 0) {
                                infBlocks2.v = i21;
                                iB = infBlocks2.b(iB);
                                infBlocks3 = this.p;
                                i12 = infBlocks3.v;
                                i13 = infBlocks3.u;
                                if (i12 < i13) {
                                    i14 = (i13 - i12) - 1;
                                } else {
                                    i14 = infBlocks3.t - i12;
                                }
                                i15 = infBlocks3.t;
                                if (i12 == i15 && i13 != 0) {
                                    if (i13 > 0) {
                                        i15 = i13 - 1;
                                    }
                                    i14 = i15;
                                    i12 = 0;
                                }
                                if (i14 == 0) {
                                    infBlocks3.q = i19;
                                    infBlocks3.p = i20;
                                    ZStream zStream14 = this.o;
                                    zStream14.c = i25;
                                    zStream14.d += (long) (i24 - zStream14.b);
                                    zStream14.b = i24;
                                    infBlocks3.v = i12;
                                    return infBlocks3.b(iB);
                                }
                                i21 = i12;
                                i23 = i14;
                            }
                        }
                        infBlocks = this.p;
                        byte[] bArr = infBlocks.s;
                        int i37 = i21 + 1;
                        i10 = i9 + 1;
                        bArr[i21] = bArr[i9];
                        i23--;
                        if (i10 == infBlocks.t) {
                            i9 = 0;
                        } else {
                            i9 = i10;
                        }
                        this.b--;
                        i21 = i37;
                    }
                    this.a = 0;
                    break;
                case 5:
                    i9 = i21 - this.h;
                    while (i9 < 0) {
                        i9 += this.p.t;
                    }
                    while (this.b != 0) {
                        if (i23 == 0) {
                            infBlocks2 = this.p;
                            i11 = infBlocks2.t;
                            if (i21 == i11) {
                                if (i16 > 0) {
                                    i23 = i16 - 1;
                                } else {
                                    i23 = i11;
                                }
                                i21 = 0;
                            }
                            if (i23 == 0) {
                                infBlocks2.v = i21;
                                iB = infBlocks2.b(iB);
                                infBlocks3 = this.p;
                                i12 = infBlocks3.v;
                                i13 = infBlocks3.u;
                                if (i12 < i13) {
                                    i14 = (i13 - i12) - 1;
                                } else {
                                    i14 = infBlocks3.t - i12;
                                }
                                i15 = infBlocks3.t;
                                if (i12 == i15) {
                                    if (i13 > 0) {
                                        i15 = i13 - 1;
                                    }
                                    i14 = i15;
                                    i12 = 0;
                                }
                                if (i14 == 0) {
                                    infBlocks3.q = i19;
                                    infBlocks3.p = i20;
                                    ZStream zStream15 = this.o;
                                    zStream15.c = i25;
                                    zStream15.d += (long) (i24 - zStream15.b);
                                    zStream15.b = i24;
                                    infBlocks3.v = i12;
                                    return infBlocks3.b(iB);
                                }
                                i21 = i12;
                                i23 = i14;
                            }
                        }
                        infBlocks = this.p;
                        byte[] bArr2 = infBlocks.s;
                        int i38 = i21 + 1;
                        i10 = i9 + 1;
                        bArr2[i21] = bArr2[i9];
                        i23--;
                        if (i10 == infBlocks.t) {
                            i9 = 0;
                        } else {
                            i9 = i10;
                        }
                        this.b--;
                        i21 = i38;
                    }
                    this.a = 0;
                    break;
                case 6:
                    if (i23 == 0) {
                        InfBlocks infBlocks17 = this.p;
                        int i39 = infBlocks17.t;
                        if (i21 == i39 && (i2 = infBlocks17.u) != 0) {
                            i23 = i2 > 0 ? i2 - 1 : i39;
                            i21 = 0;
                        }
                        if (i23 == 0) {
                            infBlocks17.v = i21;
                            int iB2 = infBlocks17.b(iB);
                            InfBlocks infBlocks18 = this.p;
                            int i40 = infBlocks18.v;
                            int i41 = infBlocks18.u;
                            int i42 = i40 < i41 ? (i41 - i40) - 1 : infBlocks18.t - i40;
                            int i43 = infBlocks18.t;
                            if (i40 == i43 && i41 != 0) {
                                if (i41 > 0) {
                                    i43 = i41 - 1;
                                }
                                i42 = i43;
                                i40 = 0;
                            }
                            if (i42 == 0) {
                                infBlocks18.q = i19;
                                infBlocks18.p = i20;
                                ZStream zStream16 = this.o;
                                zStream16.c = i25;
                                zStream16.d += (long) (i24 - zStream16.b);
                                zStream16.b = i24;
                                infBlocks18.v = i40;
                                return infBlocks18.b(iB2);
                            }
                            i21 = i40;
                            i23 = i42;
                        }
                    }
                    this.p.s[i21] = (byte) this.f;
                    i23--;
                    this.a = 0;
                    i21++;
                    iB = 0;
                    break;
                case 7:
                    if (i20 > 7) {
                        i20 -= 8;
                        i25++;
                        i24--;
                    }
                    InfBlocks infBlocks19 = this.p;
                    infBlocks19.v = i21;
                    int iB3 = infBlocks19.b(iB);
                    InfBlocks infBlocks20 = this.p;
                    int i44 = infBlocks20.v;
                    int i45 = infBlocks20.u;
                    if (i44 >= i45) {
                        int i46 = infBlocks20.t;
                    }
                    if (i45 != i44) {
                        infBlocks20.q = i19;
                        infBlocks20.p = i20;
                        ZStream zStream17 = this.o;
                        zStream17.c = i25;
                        zStream17.d += (long) (i24 - zStream17.b);
                        zStream17.b = i24;
                        infBlocks20.v = i44;
                        return infBlocks20.b(iB3);
                    }
                    this.a = 8;
                    i21 = i44;
                    InfBlocks infBlocks21 = this.p;
                    infBlocks21.q = i19;
                    infBlocks21.p = i20;
                    ZStream zStream18 = this.o;
                    zStream18.c = i25;
                    zStream18.d += (long) (i24 - zStream18.b);
                    zStream18.b = i24;
                    infBlocks21.v = i21;
                    return infBlocks21.b(1);
                case 8:
                    InfBlocks infBlocks22 = this.p;
                    infBlocks22.q = i19;
                    infBlocks22.p = i20;
                    ZStream zStream19 = this.o;
                    zStream19.c = i25;
                    zStream19.d += (long) (i24 - zStream19.b);
                    zStream19.b = i24;
                    infBlocks22.v = i21;
                    return infBlocks22.b(1);
                case 9:
                    InfBlocks infBlocks23 = this.p;
                    infBlocks23.q = i19;
                    infBlocks23.p = i20;
                    ZStream zStream20 = this.o;
                    zStream20.c = i25;
                    zStream20.d += (long) (i24 - zStream20.b);
                    zStream20.b = i24;
                    infBlocks23.v = i21;
                    return infBlocks23.b(-3);
                default:
                    InfBlocks infBlocks24 = this.p;
                    infBlocks24.q = i19;
                    infBlocks24.p = i20;
                    ZStream zStream21 = this.o;
                    zStream21.c = i25;
                    zStream21.d += (long) (i24 - zStream21.b);
                    zStream21.b = i24;
                    infBlocks24.v = i21;
                    return infBlocks24.b(-2);
            }
        }
    }
}
