package com.jcraft.jsch.jzlib;

import okhttp3.internal.http2.Settings;

/* JADX INFO: loaded from: classes3.dex */
final class InfBlocks {
    int a;
    int b;
    int c;
    int d;
    int[] e;
    private final InfCodes n;
    int o;
    int p;
    int q;
    byte[] s;
    int t;
    int u;
    int v;
    private boolean w;
    private final ZStream y;
    private static final int[] z = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, Settings.DEFAULT_INITIAL_WINDOW_SIZE};
    static final int[] A = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    int[] f = new int[1];
    int[] g = new int[1];
    int[] h = new int[1];
    int[] i = new int[1];
    int[][] j = new int[1][];
    int[][] k = new int[1][];
    int[] l = new int[1];
    int[] m = new int[1];
    private final InfTree x = new InfTree();
    int[] r = new int[4320];

    InfBlocks(ZStream zStream, int i) {
        this.y = zStream;
        this.n = new InfCodes(zStream, this);
        this.s = new byte[i];
        this.t = i;
        this.w = zStream.k.f != 0;
        this.a = 0;
        d();
    }

    void a() {
        d();
        this.s = null;
        this.r = null;
    }

    int b(int i) {
        ZStream zStream = this.y;
        int i2 = zStream.f;
        int i3 = this.u;
        int i4 = this.v;
        if (i3 > i4) {
            i4 = this.t;
        }
        int i5 = i4 - i3;
        int i6 = zStream.g;
        if (i5 > i6) {
            i5 = i6;
        }
        if (i5 != 0 && i == -5) {
            i = 0;
        }
        zStream.g = i6 - i5;
        zStream.h += (long) i5;
        if (this.w && i5 > 0) {
            zStream.m.b(this.s, i3, i5);
        }
        System.arraycopy(this.s, i3, this.y.e, i2, i5);
        int i7 = i2 + i5;
        int i8 = i3 + i5;
        int i9 = this.t;
        if (i8 == i9) {
            if (this.v == i9) {
                this.v = 0;
            }
            int i10 = this.v;
            ZStream zStream2 = this.y;
            int i11 = zStream2.g;
            if (i10 > i11) {
                i10 = i11;
            }
            if (i10 != 0 && i == -5) {
                i = 0;
            }
            zStream2.g = i11 - i10;
            zStream2.h += (long) i10;
            if (this.w && i10 > 0) {
                zStream2.m.b(this.s, 0, i10);
            }
            System.arraycopy(this.s, 0, this.y.e, i7, i10);
            i7 += i10;
            i8 = i10;
        }
        this.y.f = i7;
        this.u = i8;
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x032a  */
    /* JADX WARN: Code duplicated, block: B:103:0x032f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:104:0x0331 A[LOOP:3: B:101:0x032b->B:104:0x0331, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:109:0x037c  */
    /* JADX WARN: Code duplicated, block: B:212:0x00f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:213:0x015f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:214:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:215:0x0233 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:216:0x0265 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:217:0x02ce A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:218:0x0344 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:219:0x0383 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:231:0x0196 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:247:0x013d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x00e2 A[ADDED_TO_REGION, LOOP:10: B:37:0x00e2->B:39:0x00e6, LOOP_START, PHI: r1 r2 r3 r4 r5
      0x00e2: PHI (r1v8 int) = (r1v5 int), (r1v15 int) binds: [B:36:0x00e0, B:39:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e2: PHI (r2v7 int) = (r2v4 int), (r2v10 int) binds: [B:36:0x00e0, B:39:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e2: PHI (r3v5 int) = (r3v4 int), (r3v6 int) binds: [B:36:0x00e0, B:39:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e2: PHI (r4v7 int) = (r4v4 int), (r4v10 int) binds: [B:36:0x00e0, B:39:0x00e6] A[DONT_GENERATE, DONT_INLINE]
      0x00e2: PHI (r5v5 int) = (r5v4 int), (r5v7 int) binds: [B:36:0x00e0, B:39:0x00e6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:38:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x00e6 A[LOOP:10: B:37:0x00e2->B:39:0x00e6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:45:0x0130 A[LOOP:11: B:43:0x012a->B:45:0x0130, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:49:0x0161  */
    /* JADX WARN: Code duplicated, block: B:52:0x0180  */
    /* JADX WARN: Code duplicated, block: B:59:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:62:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:67:0x0238  */
    /* JADX WARN: Code duplicated, block: B:69:0x024f  */
    /* JADX WARN: Code duplicated, block: B:71:0x0256  */
    /* JADX WARN: Code duplicated, block: B:74:0x025e  */
    /* JADX WARN: Code duplicated, block: B:85:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:87:0x02b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x02bb A[LOOP:2: B:86:0x02b7->B:88:0x02bb, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:93:0x030d  */
    /* JADX WARN: Code duplicated, block: B:94:0x031d  */
    /* JADX WARN: Code duplicated, block: B:96:0x0321  */
    /* JADX WARN: Code duplicated, block: B:97:0x0323  */
    /* JADX WARN: Code duplicated, block: B:99:0x0327  */
    int c(int i) {
        int i2;
        int iB;
        int i3;
        int iD;
        int i4;
        int i5;
        int i6;
        int i7;
        int iC;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        ZStream zStream = this.y;
        int i16 = zStream.b;
        int i17 = zStream.c;
        int i18 = this.q;
        int i19 = this.p;
        int i20 = this.v;
        int i21 = this.u;
        int i22 = i20 < i21 ? (i21 - i20) - 1 : this.t - i20;
        int i23 = i20;
        int i24 = i19;
        int i25 = i18;
        int i26 = i16;
        int i27 = i17;
        int i28 = i;
        while (true) {
            int i29 = 9;
            int i30 = 6;
            switch (this.a) {
                case 0:
                    while (i24 < 3) {
                        if (i27 == 0) {
                            this.q = i25;
                            this.p = i24;
                            ZStream zStream2 = this.y;
                            zStream2.c = i27;
                            zStream2.d += (long) (i26 - zStream2.b);
                            zStream2.b = i26;
                            this.v = i23;
                            return b(i28);
                        }
                        i27--;
                        i25 |= (this.y.a[i26] & 255) << i24;
                        i24 += 8;
                        i26++;
                        i28 = 0;
                    }
                    this.o = i25 & 1;
                    int i31 = (i25 & 7) >>> 1;
                    if (i31 == 0) {
                        int i32 = i24 - 3;
                        int i33 = i32 & 7;
                        i25 = (i25 >>> 3) >>> i33;
                        i24 = i32 - i33;
                        this.a = 1;
                    } else if (i31 == 1) {
                        InfTree.d(this.h, this.i, this.j, this.k, this.y);
                        this.n.c(this.h[0], this.i[0], this.j[0], 0, this.k[0], 0);
                        i25 >>>= 3;
                        i24 -= 3;
                        this.a = 6;
                    } else if (i31 == 2) {
                        i25 >>>= 3;
                        i24 -= 3;
                        this.a = 3;
                    } else if (i31 == 3) {
                        int i34 = i25 >>> 3;
                        this.a = 9;
                        ZStream zStream3 = this.y;
                        zStream3.i = "invalid block type";
                        this.q = i34;
                        this.p = i24 - 3;
                        zStream3.c = i27;
                        zStream3.d += (long) (i26 - zStream3.b);
                        zStream3.b = i26;
                        this.v = i23;
                        return b(-3);
                    }
                    break;
                case 1:
                    while (i24 < 32) {
                        if (i27 == 0) {
                            this.q = i25;
                            this.p = i24;
                            ZStream zStream4 = this.y;
                            zStream4.c = i27;
                            zStream4.d += (long) (i26 - zStream4.b);
                            zStream4.b = i26;
                            this.v = i23;
                            return b(i28);
                        }
                        i27--;
                        i25 |= (this.y.a[i26] & 255) << i24;
                        i24 += 8;
                        i26++;
                        i28 = 0;
                    }
                    int i35 = 65535 & i25;
                    if ((((~i25) >>> 16) & Settings.DEFAULT_INITIAL_WINDOW_SIZE) != i35) {
                        this.a = 9;
                        ZStream zStream5 = this.y;
                        zStream5.i = "invalid stored block lengths";
                        this.q = i25;
                        this.p = i24;
                        zStream5.c = i27;
                        zStream5.d += (long) (i26 - zStream5.b);
                        zStream5.b = i26;
                        this.v = i23;
                        return b(-3);
                    }
                    this.b = i35;
                    this.a = i35 != 0 ? 2 : this.o != 0 ? 7 : 0;
                    i25 = 0;
                    i24 = 0;
                    break;
                    break;
                case 2:
                    if (i27 == 0) {
                        this.q = i25;
                        this.p = i24;
                        ZStream zStream6 = this.y;
                        zStream6.c = i27;
                        zStream6.d += (long) (i26 - zStream6.b);
                        zStream6.b = i26;
                        this.v = i23;
                        return b(i28);
                    }
                    if (i22 == 0) {
                        int i36 = this.t;
                        if (i23 == i36 && (i3 = this.u) != 0) {
                            i22 = i3 > 0 ? i3 - 1 : i36;
                            i23 = 0;
                        }
                        if (i22 == 0) {
                            this.v = i23;
                            int iB2 = b(i28);
                            i23 = this.v;
                            int i37 = this.u;
                            int i38 = i23 < i37 ? (i37 - i23) - 1 : this.t - i23;
                            int i39 = this.t;
                            if (i23 != i39 || i37 == 0) {
                                i22 = i38;
                            } else {
                                if (i37 > 0) {
                                    i39 = i37 - 1;
                                }
                                i22 = i39;
                                i23 = 0;
                            }
                            if (i22 == 0) {
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream7 = this.y;
                                zStream7.c = i27;
                                zStream7.d += (long) (i26 - zStream7.b);
                                zStream7.b = i26;
                                this.v = i23;
                                return b(iB2);
                            }
                        }
                    }
                    int i40 = this.b;
                    if (i40 > i27) {
                        i40 = i27;
                    }
                    if (i40 > i22) {
                        i40 = i22;
                    }
                    System.arraycopy(this.y.a, i26, this.s, i23, i40);
                    i26 += i40;
                    i27 -= i40;
                    i23 += i40;
                    i22 -= i40;
                    int i41 = this.b - i40;
                    this.b = i41;
                    if (i41 == 0) {
                        this.a = this.o != 0 ? 7 : 0;
                    }
                    i28 = 0;
                    break;
                    break;
                case 3:
                    while (i24 < 14) {
                        if (i27 == 0) {
                            this.q = i25;
                            this.p = i24;
                            ZStream zStream8 = this.y;
                            zStream8.c = i27;
                            zStream8.d += (long) (i26 - zStream8.b);
                            zStream8.b = i26;
                            this.v = i23;
                            return b(i28);
                        }
                        i27--;
                        i25 |= (this.y.a[i26] & 255) << i24;
                        i24 += 8;
                        i26++;
                        i28 = 0;
                    }
                    int i42 = i25 & 16383;
                    this.c = i42;
                    int i43 = i25 & 31;
                    if (i43 > 29 || (i6 = (i42 >> 5) & 31) > 29) {
                        this.a = 9;
                        ZStream zStream9 = this.y;
                        zStream9.i = "too many length or distance symbols";
                        this.q = i25;
                        this.p = i24;
                        zStream9.c = i27;
                        zStream9.d += (long) (i26 - zStream9.b);
                        zStream9.b = i26;
                        this.v = i23;
                        return b(-3);
                    }
                    int i44 = i43 + 258 + i6;
                    int[] iArr = this.e;
                    if (iArr == null || iArr.length < i44) {
                        this.e = new int[i44];
                    } else {
                        for (int i45 = 0; i45 < i44; i45++) {
                            this.e[i45] = 0;
                        }
                    }
                    i25 >>>= 14;
                    i24 -= 14;
                    this.d = 0;
                    this.a = 4;
                    for (int i46 = 4; this.d < (this.c >>> 10) + i46; i46 = 4) {
                        while (i24 < 3) {
                            if (i27 != 0) {
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream10 = this.y;
                                zStream10.c = i27;
                                zStream10.d += (long) (i26 - zStream10.b);
                                zStream10.b = i26;
                                this.v = i23;
                                return b(i28);
                            }
                            i27--;
                            i25 |= (this.y.a[i26] & 255) << i24;
                            i24 += 8;
                            i26++;
                            i28 = 0;
                        }
                        int[] iArr2 = this.e;
                        int[] iArr3 = A;
                        int i47 = this.d;
                        this.d = i47 + 1;
                        iArr2[iArr3[i47]] = i25 & 7;
                        i25 >>>= 3;
                        i24 -= 3;
                    }
                    while (true) {
                        i2 = this.d;
                        if (i2 >= 19) {
                            int[] iArr4 = this.f;
                            iArr4[0] = 7;
                            iB = this.x.b(this.e, iArr4, this.g, this.r, this.y);
                            if (iB != 0) {
                                if (iB == -3) {
                                    this.e = null;
                                    this.a = 9;
                                }
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream11 = this.y;
                                zStream11.c = i27;
                                zStream11.d += (long) (i26 - zStream11.b);
                                zStream11.b = i26;
                                this.v = i23;
                                return b(iB);
                            }
                            this.d = 0;
                            this.a = 5;
                            while (true) {
                                i7 = this.c;
                                if (this.d >= (i7 & 31) + 258 + ((i7 >> 5) & 31)) {
                                    this.g[0] = -1;
                                    int[] iArr5 = this.h;
                                    iArr5[0] = i29;
                                    int[] iArr6 = this.i;
                                    iArr6[0] = i30;
                                    iC = this.x.c((i7 & 31) + 257, ((i7 >> 5) & 31) + 1, this.e, iArr5, iArr6, this.l, this.m, this.r, this.y);
                                    if (iC != 0) {
                                        if (iC == -3) {
                                            this.e = null;
                                            this.a = 9;
                                        }
                                        this.q = i25;
                                        this.p = i24;
                                        ZStream zStream12 = this.y;
                                        zStream12.c = i27;
                                        zStream12.d += (long) (i26 - zStream12.b);
                                        zStream12.b = i26;
                                        this.v = i23;
                                        return b(iC);
                                    }
                                    InfCodes infCodes = this.n;
                                    int i48 = this.h[0];
                                    int i49 = this.i[0];
                                    int[] iArr7 = this.r;
                                    infCodes.c(i48, i49, iArr7, this.l[0], iArr7, this.m[0]);
                                    this.a = 6;
                                } else {
                                    i8 = this.f[0];
                                    while (i24 < i8) {
                                        if (i27 == 0) {
                                            this.q = i25;
                                            this.p = i24;
                                            ZStream zStream13 = this.y;
                                            zStream13.c = i27;
                                            zStream13.d += (long) (i26 - zStream13.b);
                                            zStream13.b = i26;
                                            this.v = i23;
                                            return b(i28);
                                        }
                                        i27--;
                                        i25 |= (this.y.a[i26] & 255) << i24;
                                        i24 += 8;
                                        i26++;
                                        i28 = 0;
                                    }
                                    int i50 = this.g[0];
                                    int[] iArr8 = this.r;
                                    int[] iArr9 = z;
                                    i9 = iArr8[(((iArr9[i8] & i25) + i50) * 3) + 1];
                                    i10 = iArr8[((i50 + (iArr9[i9] & i25)) * 3) + 2];
                                    if (i10 < 16) {
                                        i25 >>>= i9;
                                        i24 -= i9;
                                        int[] iArr10 = this.e;
                                        int i51 = this.d;
                                        this.d = i51 + 1;
                                        iArr10[i51] = i10;
                                    } else {
                                        if (i10 == 18) {
                                            i11 = 7;
                                        } else {
                                            i11 = i10 - 14;
                                        }
                                        if (i10 == 18) {
                                            i12 = 11;
                                        } else {
                                            i12 = 3;
                                        }
                                        while (i24 < i9 + i11) {
                                            if (i27 == 0) {
                                                this.q = i25;
                                                this.p = i24;
                                                ZStream zStream14 = this.y;
                                                zStream14.c = i27;
                                                zStream14.d += (long) (i26 - zStream14.b);
                                                zStream14.b = i26;
                                                this.v = i23;
                                                return b(i28);
                                            }
                                            i27--;
                                            i25 |= (this.y.a[i26] & 255) << i24;
                                            i24 += 8;
                                            i26++;
                                            i28 = 0;
                                        }
                                        int i52 = i25 >>> i9;
                                        i13 = i12 + (z[i11] & i52);
                                        i25 = i52 >>> i11;
                                        i24 = (i24 - i9) - i11;
                                        i14 = this.d;
                                        i15 = this.c;
                                        if (i14 + i13 <= (i15 & 31) + 258 + ((i15 >> 5) & 31) || (i10 == 16 && i14 < 1)) {
                                            this.e = null;
                                            this.a = 9;
                                            ZStream zStream15 = this.y;
                                            zStream15.i = "invalid bit length repeat";
                                            this.q = i25;
                                            this.p = i24;
                                            zStream15.c = i27;
                                            zStream15.d += (long) (i26 - zStream15.b);
                                            zStream15.b = i26;
                                            this.v = i23;
                                            return b(-3);
                                        }
                                        int i53 = i10 == 16 ? this.e[i14 - 1] : 0;
                                        while (true) {
                                            int i54 = i14 + 1;
                                            this.e[i14] = i53;
                                            i13--;
                                            if (i13 == 0) {
                                                this.d = i54;
                                            } else {
                                                i14 = i54;
                                            }
                                        }
                                    }
                                    i29 = 9;
                                    i30 = 6;
                                }
                            }
                            this.q = i25;
                            this.p = i24;
                            ZStream zStream16 = this.y;
                            zStream16.c = i27;
                            zStream16.d += (long) (i26 - zStream16.b);
                            zStream16.b = i26;
                            this.v = i23;
                            iD = this.n.d(i28);
                            if (iD != 1) {
                                return b(iD);
                            }
                            this.n.a(this.y);
                            ZStream zStream17 = this.y;
                            i26 = zStream17.b;
                            i27 = zStream17.c;
                            i25 = this.q;
                            i24 = this.p;
                            i23 = this.v;
                            i4 = this.u;
                            if (i23 < i4) {
                                i5 = (i4 - i23) - 1;
                            } else {
                                i5 = this.t - i23;
                            }
                            i22 = i5;
                            if (this.o == 0) {
                                this.a = 0;
                                i28 = 0;
                            } else {
                                this.a = 7;
                                i28 = 0;
                            }
                        } else {
                            int[] iArr11 = this.e;
                            int[] iArr12 = A;
                            this.d = i2 + 1;
                            iArr11[iArr12[i2]] = 0;
                        }
                        break;
                    }
                    break;
                case 4:
                    while (this.d < (this.c >>> 10) + i46) {
                        while (i24 < 3) {
                            if (i27 != 0) {
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream18 = this.y;
                                zStream18.c = i27;
                                zStream18.d += (long) (i26 - zStream18.b);
                                zStream18.b = i26;
                                this.v = i23;
                                return b(i28);
                            }
                            i27--;
                            i25 |= (this.y.a[i26] & 255) << i24;
                            i24 += 8;
                            i26++;
                            i28 = 0;
                        }
                        int[] iArr13 = this.e;
                        int[] iArr14 = A;
                        int i410 = this.d;
                        this.d = i410 + 1;
                        iArr13[iArr14[i410]] = i25 & 7;
                        i25 >>>= 3;
                        i24 -= 3;
                    }
                    while (true) {
                        i2 = this.d;
                        if (i2 >= 19) {
                            int[] iArr15 = this.e;
                            int[] iArr16 = A;
                            this.d = i2 + 1;
                            iArr15[iArr16[i2]] = 0;
                        } else {
                            int[] iArr17 = this.f;
                            iArr17[0] = 7;
                            iB = this.x.b(this.e, iArr17, this.g, this.r, this.y);
                            if (iB != 0) {
                                if (iB == -3) {
                                    this.e = null;
                                    this.a = 9;
                                }
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream19 = this.y;
                                zStream19.c = i27;
                                zStream19.d += (long) (i26 - zStream19.b);
                                zStream19.b = i26;
                                this.v = i23;
                                return b(iB);
                            }
                            this.d = 0;
                            this.a = 5;
                            while (true) {
                                i7 = this.c;
                                if (this.d >= (i7 & 31) + 258 + ((i7 >> 5) & 31)) {
                                    this.g[0] = -1;
                                    int[] iArr18 = this.h;
                                    iArr18[0] = i29;
                                    int[] iArr19 = this.i;
                                    iArr19[0] = i30;
                                    iC = this.x.c((i7 & 31) + 257, ((i7 >> 5) & 31) + 1, this.e, iArr18, iArr19, this.l, this.m, this.r, this.y);
                                    if (iC != 0) {
                                        if (iC == -3) {
                                            this.e = null;
                                            this.a = 9;
                                        }
                                        this.q = i25;
                                        this.p = i24;
                                        ZStream zStream110 = this.y;
                                        zStream110.c = i27;
                                        zStream110.d += (long) (i26 - zStream110.b);
                                        zStream110.b = i26;
                                        this.v = i23;
                                        return b(iC);
                                    }
                                    InfCodes infCodes2 = this.n;
                                    int i411 = this.h[0];
                                    int i412 = this.i[0];
                                    int[] iArr20 = this.r;
                                    infCodes2.c(i411, i412, iArr20, this.l[0], iArr20, this.m[0]);
                                    this.a = 6;
                                    this.q = i25;
                                    this.p = i24;
                                    ZStream zStream111 = this.y;
                                    zStream111.c = i27;
                                    zStream111.d += (long) (i26 - zStream111.b);
                                    zStream111.b = i26;
                                    this.v = i23;
                                    iD = this.n.d(i28);
                                    if (iD != 1) {
                                        return b(iD);
                                    }
                                    this.n.a(this.y);
                                    ZStream zStream112 = this.y;
                                    i26 = zStream112.b;
                                    i27 = zStream112.c;
                                    i25 = this.q;
                                    i24 = this.p;
                                    i23 = this.v;
                                    i4 = this.u;
                                    if (i23 < i4) {
                                        i5 = (i4 - i23) - 1;
                                    } else {
                                        i5 = this.t - i23;
                                    }
                                    i22 = i5;
                                    if (this.o == 0) {
                                        this.a = 0;
                                        i28 = 0;
                                    } else {
                                        this.a = 7;
                                        i28 = 0;
                                    }
                                } else {
                                    i8 = this.f[0];
                                    while (i24 < i8) {
                                        if (i27 == 0) {
                                            this.q = i25;
                                            this.p = i24;
                                            ZStream zStream113 = this.y;
                                            zStream113.c = i27;
                                            zStream113.d += (long) (i26 - zStream113.b);
                                            zStream113.b = i26;
                                            this.v = i23;
                                            return b(i28);
                                        }
                                        i27--;
                                        i25 |= (this.y.a[i26] & 255) << i24;
                                        i24 += 8;
                                        i26++;
                                        i28 = 0;
                                    }
                                    int i55 = this.g[0];
                                    int[] iArr21 = this.r;
                                    int[] iArr22 = z;
                                    i9 = iArr21[(((iArr22[i8] & i25) + i55) * 3) + 1];
                                    i10 = iArr21[((i55 + (iArr22[i9] & i25)) * 3) + 2];
                                    if (i10 < 16) {
                                        if (i10 == 18) {
                                            i11 = 7;
                                        } else {
                                            i11 = i10 - 14;
                                        }
                                        if (i10 == 18) {
                                            i12 = 11;
                                        } else {
                                            i12 = 3;
                                        }
                                        while (i24 < i9 + i11) {
                                            if (i27 == 0) {
                                                this.q = i25;
                                                this.p = i24;
                                                ZStream zStream114 = this.y;
                                                zStream114.c = i27;
                                                zStream114.d += (long) (i26 - zStream114.b);
                                                zStream114.b = i26;
                                                this.v = i23;
                                                return b(i28);
                                            }
                                            i27--;
                                            i25 |= (this.y.a[i26] & 255) << i24;
                                            i24 += 8;
                                            i26++;
                                            i28 = 0;
                                        }
                                        int i56 = i25 >>> i9;
                                        i13 = i12 + (z[i11] & i56);
                                        i25 = i56 >>> i11;
                                        i24 = (i24 - i9) - i11;
                                        i14 = this.d;
                                        i15 = this.c;
                                        if (i14 + i13 <= (i15 & 31) + 258 + ((i15 >> 5) & 31)) {
                                        }
                                        this.e = null;
                                        this.a = 9;
                                        ZStream zStream115 = this.y;
                                        zStream115.i = "invalid bit length repeat";
                                        this.q = i25;
                                        this.p = i24;
                                        zStream115.c = i27;
                                        zStream115.d += (long) (i26 - zStream115.b);
                                        zStream115.b = i26;
                                        this.v = i23;
                                        return b(-3);
                                    }
                                    i25 >>>= i9;
                                    i24 -= i9;
                                    int[] iArr110 = this.e;
                                    int i57 = this.d;
                                    this.d = i57 + 1;
                                    iArr110[i57] = i10;
                                    i29 = 9;
                                    i30 = 6;
                                }
                            }
                        }
                        break;
                    }
                    break;
                case 5:
                    while (true) {
                        i7 = this.c;
                        if (this.d >= (i7 & 31) + 258 + ((i7 >> 5) & 31)) {
                            this.g[0] = -1;
                            int[] iArr111 = this.h;
                            iArr111[0] = i29;
                            int[] iArr112 = this.i;
                            iArr112[0] = i30;
                            iC = this.x.c((i7 & 31) + 257, ((i7 >> 5) & 31) + 1, this.e, iArr111, iArr112, this.l, this.m, this.r, this.y);
                            if (iC != 0) {
                                if (iC == -3) {
                                    this.e = null;
                                    this.a = 9;
                                }
                                this.q = i25;
                                this.p = i24;
                                ZStream zStream116 = this.y;
                                zStream116.c = i27;
                                zStream116.d += (long) (i26 - zStream116.b);
                                zStream116.b = i26;
                                this.v = i23;
                                return b(iC);
                            }
                            InfCodes infCodes3 = this.n;
                            int i413 = this.h[0];
                            int i414 = this.i[0];
                            int[] iArr23 = this.r;
                            infCodes3.c(i413, i414, iArr23, this.l[0], iArr23, this.m[0]);
                            this.a = 6;
                            this.q = i25;
                            this.p = i24;
                            ZStream zStream117 = this.y;
                            zStream117.c = i27;
                            zStream117.d += (long) (i26 - zStream117.b);
                            zStream117.b = i26;
                            this.v = i23;
                            iD = this.n.d(i28);
                            if (iD != 1) {
                                return b(iD);
                            }
                            this.n.a(this.y);
                            ZStream zStream118 = this.y;
                            i26 = zStream118.b;
                            i27 = zStream118.c;
                            i25 = this.q;
                            i24 = this.p;
                            i23 = this.v;
                            i4 = this.u;
                            if (i23 < i4) {
                                i5 = (i4 - i23) - 1;
                            } else {
                                i5 = this.t - i23;
                            }
                            i22 = i5;
                            if (this.o == 0) {
                                this.a = 0;
                                i28 = 0;
                            } else {
                                this.a = 7;
                                i28 = 0;
                            }
                        } else {
                            i8 = this.f[0];
                            while (i24 < i8) {
                                if (i27 == 0) {
                                    this.q = i25;
                                    this.p = i24;
                                    ZStream zStream119 = this.y;
                                    zStream119.c = i27;
                                    zStream119.d += (long) (i26 - zStream119.b);
                                    zStream119.b = i26;
                                    this.v = i23;
                                    return b(i28);
                                }
                                i27--;
                                i25 |= (this.y.a[i26] & 255) << i24;
                                i24 += 8;
                                i26++;
                                i28 = 0;
                            }
                            int i58 = this.g[0];
                            int[] iArr24 = this.r;
                            int[] iArr25 = z;
                            i9 = iArr24[(((iArr25[i8] & i25) + i58) * 3) + 1];
                            i10 = iArr24[((i58 + (iArr25[i9] & i25)) * 3) + 2];
                            if (i10 < 16) {
                                if (i10 == 18) {
                                    i11 = 7;
                                } else {
                                    i11 = i10 - 14;
                                }
                                if (i10 == 18) {
                                    i12 = 11;
                                } else {
                                    i12 = 3;
                                }
                                while (i24 < i9 + i11) {
                                    if (i27 == 0) {
                                        this.q = i25;
                                        this.p = i24;
                                        ZStream zStream1110 = this.y;
                                        zStream1110.c = i27;
                                        zStream1110.d += (long) (i26 - zStream1110.b);
                                        zStream1110.b = i26;
                                        this.v = i23;
                                        return b(i28);
                                    }
                                    i27--;
                                    i25 |= (this.y.a[i26] & 255) << i24;
                                    i24 += 8;
                                    i26++;
                                    i28 = 0;
                                }
                                int i59 = i25 >>> i9;
                                i13 = i12 + (z[i11] & i59);
                                i25 = i59 >>> i11;
                                i24 = (i24 - i9) - i11;
                                i14 = this.d;
                                i15 = this.c;
                                if (i14 + i13 <= (i15 & 31) + 258 + ((i15 >> 5) & 31)) {
                                }
                                this.e = null;
                                this.a = 9;
                                ZStream zStream1111 = this.y;
                                zStream1111.i = "invalid bit length repeat";
                                this.q = i25;
                                this.p = i24;
                                zStream1111.c = i27;
                                zStream1111.d += (long) (i26 - zStream1111.b);
                                zStream1111.b = i26;
                                this.v = i23;
                                return b(-3);
                            }
                            i25 >>>= i9;
                            i24 -= i9;
                            int[] iArr113 = this.e;
                            int i510 = this.d;
                            this.d = i510 + 1;
                            iArr113[i510] = i10;
                            i29 = 9;
                            i30 = 6;
                        }
                        break;
                    }
                    break;
                case 6:
                    this.q = i25;
                    this.p = i24;
                    ZStream zStream1112 = this.y;
                    zStream1112.c = i27;
                    zStream1112.d += (long) (i26 - zStream1112.b);
                    zStream1112.b = i26;
                    this.v = i23;
                    iD = this.n.d(i28);
                    if (iD != 1) {
                        return b(iD);
                    }
                    this.n.a(this.y);
                    ZStream zStream1113 = this.y;
                    i26 = zStream1113.b;
                    i27 = zStream1113.c;
                    i25 = this.q;
                    i24 = this.p;
                    i23 = this.v;
                    i4 = this.u;
                    if (i23 < i4) {
                        i5 = (i4 - i23) - 1;
                    } else {
                        i5 = this.t - i23;
                    }
                    i22 = i5;
                    if (this.o == 0) {
                        this.a = 0;
                        i28 = 0;
                    } else {
                        this.a = 7;
                        i28 = 0;
                    }
                    break;
                    break;
                case 7:
                    break;
                case 8:
                    this.q = i25;
                    this.p = i24;
                    ZStream zStream20 = this.y;
                    zStream20.c = i27;
                    zStream20.d += (long) (i26 - zStream20.b);
                    zStream20.b = i26;
                    this.v = i23;
                    return b(1);
                case 9:
                    this.q = i25;
                    this.p = i24;
                    ZStream zStream21 = this.y;
                    zStream21.c = i27;
                    zStream21.d += (long) (i26 - zStream21.b);
                    zStream21.b = i26;
                    this.v = i23;
                    return b(-3);
                default:
                    this.q = i25;
                    this.p = i24;
                    ZStream zStream22 = this.y;
                    zStream22.c = i27;
                    zStream22.d += (long) (i26 - zStream22.b);
                    zStream22.b = i26;
                    this.v = i23;
                    return b(-2);
            }
        }
        this.v = i23;
        int iB3 = b(i28);
        i23 = this.v;
        if (this.u != i23) {
            this.q = i25;
            this.p = i24;
            ZStream zStream23 = this.y;
            zStream23.c = i27;
            zStream23.d += (long) (i26 - zStream23.b);
            zStream23.b = i26;
            this.v = i23;
            return b(iB3);
        }
        this.a = 8;
        this.q = i25;
        this.p = i24;
        ZStream zStream24 = this.y;
        zStream24.c = i27;
        zStream24.d += (long) (i26 - zStream24.b);
        zStream24.b = i26;
        this.v = i23;
        return b(1);
    }

    void d() {
        if (this.a == 6) {
            this.n.a(this.y);
        }
        this.a = 0;
        this.p = 0;
        this.q = 0;
        this.v = 0;
        this.u = 0;
        if (this.w) {
            this.y.m.a();
        }
    }
}
