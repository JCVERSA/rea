package com.jcraft.jsch.jzlib;

import ax.p409z6.C3772df;
import java.io.ByteArrayOutputStream;
import okhttp3.internal.http2.Settings;
import okhttp3.internal.ws.WebSocketProtocol;

/* JADX INFO: loaded from: classes3.dex */
final class Inflate {
    private static byte[] o = {0, 0, -1, -1};
    int a;
    int b;
    long d;
    int e;
    int f;
    int g;
    InfBlocks h;
    private final ZStream i;
    private int j;
    long c = -1;
    private int k = -1;
    private byte[] l = new byte[4];
    GZIPHeader m = null;
    private ByteArrayOutputStream n = null;

    static class Return extends Exception {
        int q;

        Return(int i) {
            this.q = i;
        }
    }

    Inflate(ZStream zStream) {
        this.i = zStream;
    }

    private void a(int i, long j) {
        for (int i2 = 0; i2 < i; i2++) {
            this.l[i2] = (byte) (255 & j);
            j >>= 8;
        }
        this.i.m.b(this.l, 0, i);
    }

    private int f(int i, int i2) throws Return {
        if (this.n == null) {
            this.n = new ByteArrayOutputStream();
        }
        while (this.d > 0) {
            ZStream zStream = this.i;
            int i3 = zStream.c;
            if (i3 == 0) {
                throw new Return(i);
            }
            zStream.c = i3 - 1;
            zStream.d++;
            byte[] bArr = zStream.a;
            int i4 = zStream.b;
            byte b = bArr[i4];
            this.n.write(bArr, i4, 1);
            ZStream zStream2 = this.i;
            zStream2.m.b(zStream2.a, zStream2.b, 1);
            this.i.b++;
            this.d--;
            i = i2;
        }
        return i;
    }

    private int g(int i, int i2, int i3) throws Return {
        if (this.k == -1) {
            this.k = i;
            this.d = 0L;
        }
        while (true) {
            int i4 = this.k;
            if (i4 <= 0) {
                if (i == 2) {
                    this.d &= WebSocketProtocol.PAYLOAD_SHORT_MAX;
                } else if (i == 4) {
                    this.d &= 4294967295L;
                }
                this.k = -1;
                return i2;
            }
            ZStream zStream = this.i;
            int i5 = zStream.c;
            if (i5 == 0) {
                throw new Return(i2);
            }
            zStream.c = i5 - 1;
            zStream.d++;
            long j = this.d;
            byte[] bArr = zStream.a;
            int i6 = zStream.b;
            zStream.b = i6 + 1;
            this.d = j | ((long) ((bArr[i6] & 255) << ((i - i4) * 8)));
            this.k = i4 - 1;
            i2 = i3;
        }
    }

    private int h(int i, int i2) throws Return {
        if (this.n == null) {
            this.n = new ByteArrayOutputStream();
        }
        while (true) {
            ZStream zStream = this.i;
            int i3 = zStream.c;
            if (i3 == 0) {
                throw new Return(i);
            }
            zStream.c = i3 - 1;
            zStream.d++;
            byte[] bArr = zStream.a;
            int i4 = zStream.b;
            byte b = bArr[i4];
            if (b != 0) {
                this.n.write(bArr, i4, 1);
            }
            ZStream zStream2 = this.i;
            zStream2.m.b(zStream2.a, zStream2.b, 1);
            this.i.b++;
            if (b == 0) {
                return i2;
            }
            i = i2;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:103:0x0167  */
    /* JADX WARN: Code duplicated, block: B:105:0x016b  */
    /* JADX WARN: Code duplicated, block: B:112:0x017f  */
    /* JADX WARN: Code duplicated, block: B:190:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:194:0x0300  */
    /* JADX WARN: Code duplicated, block: B:198:0x032c  */
    /* JADX WARN: Code duplicated, block: B:202:0x0357  */
    /* JADX WARN: Code duplicated, block: B:204:0x037d  */
    /* JADX WARN: Code duplicated, block: B:207:0x03a6  */
    /* JADX WARN: Code duplicated, block: B:208:0x03a9  */
    /* JADX WARN: Code duplicated, block: B:209:0x03ab  */
    /* JADX WARN: Code duplicated, block: B:215:0x03ba  */
    /* JADX WARN: Code duplicated, block: B:232:0x03f9  */
    /* JADX WARN: Code duplicated, block: B:245:0x0429 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:246:0x042a  */
    /* JADX WARN: Code duplicated, block: B:249:0x0454 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:250:0x0455  */
    /* JADX WARN: Code duplicated, block: B:253:0x047d A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:254:0x047e  */
    /* JADX WARN: Code duplicated, block: B:257:0x04a4 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:258:0x04a5  */
    /* JADX WARN: Code duplicated, block: B:267:0x0125 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:269:0x00e9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:271:0x00b8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:275:0x0177 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:279:0x014e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:305:0x0115 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:306:0x011b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:307:0x0101 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:308:0x0108 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:309:0x0196 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:310:0x01a6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:311:0x01a6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:319:0x02d2 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:31:0x007a  */
    /* JADX WARN: Code duplicated, block: B:320:0x02ff A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:321:0x032b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:322:0x0356 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:323:0x03f3 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:329:0x0405 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:34:0x0085  */
    /* JADX WARN: Code duplicated, block: B:39:0x0096  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:49:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:52:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:56:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:58:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:64:0x00f1 A[Catch: Return -> 0x0106, TryCatch #2 {Return -> 0x0106, blocks: (B:62:0x00e9, B:64:0x00f1, B:66:0x0101, B:69:0x0108), top: B:269:0x00e9 }] */
    /* JADX WARN: Code duplicated, block: B:75:0x0119  */
    /* JADX WARN: Code duplicated, block: B:81:0x012d A[Catch: Return -> 0x0136, TryCatch #1 {Return -> 0x0136, blocks: (B:79:0x0125, B:81:0x012d, B:84:0x0138), top: B:267:0x0125 }] */
    /* JADX WARN: Code duplicated, block: B:88:0x013e  */
    /* JADX WARN: Code duplicated, block: B:90:0x0142  */
    /* JADX WARN: Code duplicated, block: B:96:0x0156 A[Catch: Return -> 0x015f, TryCatch #7 {Return -> 0x015f, blocks: (B:94:0x014e, B:96:0x0156, B:99:0x0161), top: B:279:0x014e }] */
    int b(int i) {
        int i2;
        ZStream zStream;
        int i3;
        ZStream zStream2;
        int i4;
        int i5;
        long j;
        int i6;
        int i7;
        long j2;
        GZIPHeader gZIPHeader;
        ZStream zStream3;
        int i8;
        ZStream zStream4;
        int i9;
        ZStream zStream5;
        int i10;
        ZStream zStream6;
        int i11;
        ZStream zStream7;
        int i12;
        ZStream zStream8;
        int i13;
        String str;
        GZIPHeader gZIPHeader2;
        GZIPHeader gZIPHeader3;
        GZIPHeader gZIPHeader4;
        GZIPHeader gZIPHeader5;
        GZIPHeader gZIPHeader6;
        GZIPHeader gZIPHeader7;
        byte[] byteArray;
        int length;
        byte[] bArr;
        GZIPHeader gZIPHeader8;
        GZIPHeader gZIPHeader9;
        GZIPHeader gZIPHeader10;
        GZIPHeader gZIPHeader11;
        ZStream zStream9 = this.i;
        if (zStream9 == null || zStream9.a == null) {
            return (i == 4 && this.a == 14) ? 0 : -2;
        }
        int iC = -5;
        int i14 = i == 4 ? -5 : 0;
        while (true) {
            switch (this.a) {
                case 2:
                    i2 = 1;
                    zStream8 = this.i;
                    i13 = zStream8.c;
                    if (i13 == 0) {
                        return iC;
                    }
                    zStream8.c = i13 - 1;
                    zStream8.d++;
                    byte[] bArr2 = zStream8.a;
                    int i15 = zStream8.b;
                    zStream8.b = i15 + 1;
                    this.d = ((long) ((bArr2[i15] & 255) << 24)) & 4278190080L;
                    this.a = 3;
                    iC = i14;
                    zStream7 = this.i;
                    i12 = zStream7.c;
                    if (i12 == 0) {
                        return iC;
                    }
                    zStream7.c = i12 - i2;
                    zStream7.d++;
                    long j3 = this.d;
                    byte[] bArr3 = zStream7.a;
                    int i16 = zStream7.b;
                    zStream7.b = i16 + 1;
                    this.d = j3 + (((long) ((bArr3[i16] & 255) << 16)) & 16711680);
                    this.a = 4;
                    iC = i14;
                    zStream6 = this.i;
                    i11 = zStream6.c;
                    if (i11 == 0) {
                        return iC;
                    }
                    zStream6.c = i11 - i2;
                    zStream6.d++;
                    long j4 = this.d;
                    byte[] bArr4 = zStream6.a;
                    int i17 = zStream6.b;
                    zStream6.b = i17 + 1;
                    this.d = j4 + (((long) ((bArr4[i17] & 255) << 8)) & 65280);
                    this.a = 5;
                    zStream5 = this.i;
                    i10 = zStream5.c;
                    if (i10 == 0) {
                        return i14;
                    }
                    zStream5.c = i10 - i2;
                    zStream5.d++;
                    long j5 = this.d;
                    byte[] bArr5 = zStream5.a;
                    int i18 = zStream5.b;
                    zStream5.b = i18 + 1;
                    long j6 = j5 + (((long) bArr5[i18]) & 255);
                    this.d = j6;
                    zStream5.m.c(j6);
                    this.a = 6;
                    return 2;
                case 3:
                    i2 = 1;
                    zStream7 = this.i;
                    i12 = zStream7.c;
                    if (i12 == 0) {
                        return iC;
                    }
                    zStream7.c = i12 - i2;
                    zStream7.d++;
                    long j7 = this.d;
                    byte[] bArr6 = zStream7.a;
                    int i19 = zStream7.b;
                    zStream7.b = i19 + 1;
                    this.d = j7 + (((long) ((bArr6[i19] & 255) << 16)) & 16711680);
                    this.a = 4;
                    iC = i14;
                    zStream6 = this.i;
                    i11 = zStream6.c;
                    if (i11 == 0) {
                        return iC;
                    }
                    zStream6.c = i11 - i2;
                    zStream6.d++;
                    long j8 = this.d;
                    byte[] bArr7 = zStream6.a;
                    int i110 = zStream6.b;
                    zStream6.b = i110 + 1;
                    this.d = j8 + (((long) ((bArr7[i110] & 255) << 8)) & 65280);
                    this.a = 5;
                    zStream5 = this.i;
                    i10 = zStream5.c;
                    if (i10 == 0) {
                        return i14;
                    }
                    zStream5.c = i10 - i2;
                    zStream5.d++;
                    long j9 = this.d;
                    byte[] bArr8 = zStream5.a;
                    int i111 = zStream5.b;
                    zStream5.b = i111 + 1;
                    long j10 = j9 + (((long) bArr8[i111]) & 255);
                    this.d = j10;
                    zStream5.m.c(j10);
                    this.a = 6;
                    return 2;
                case 4:
                    i2 = 1;
                    zStream6 = this.i;
                    i11 = zStream6.c;
                    if (i11 == 0) {
                        return iC;
                    }
                    zStream6.c = i11 - i2;
                    zStream6.d++;
                    long j11 = this.d;
                    byte[] bArr9 = zStream6.a;
                    int i112 = zStream6.b;
                    zStream6.b = i112 + 1;
                    this.d = j11 + (((long) ((bArr9[i112] & 255) << 8)) & 65280);
                    this.a = 5;
                    zStream5 = this.i;
                    i10 = zStream5.c;
                    if (i10 == 0) {
                        return i14;
                    }
                    zStream5.c = i10 - i2;
                    zStream5.d++;
                    long j12 = this.d;
                    byte[] bArr10 = zStream5.a;
                    int i113 = zStream5.b;
                    zStream5.b = i113 + 1;
                    long j13 = j12 + (((long) bArr10[i113]) & 255);
                    this.d = j13;
                    zStream5.m.c(j13);
                    this.a = 6;
                    return 2;
                case 5:
                    i2 = 1;
                    i14 = iC;
                    zStream5 = this.i;
                    i10 = zStream5.c;
                    if (i10 == 0) {
                        return i14;
                    }
                    zStream5.c = i10 - i2;
                    zStream5.d++;
                    long j14 = this.d;
                    byte[] bArr11 = zStream5.a;
                    int i114 = zStream5.b;
                    zStream5.b = i114 + 1;
                    long j15 = j14 + (((long) bArr11[i114]) & 255);
                    this.d = j15;
                    zStream5.m.c(j15);
                    this.a = 6;
                    return 2;
                case 6:
                    this.a = 13;
                    this.i.i = "need dictionary";
                    this.e = 0;
                    return -2;
                case 7:
                    iC = this.h.c(iC);
                    if (iC == -3) {
                        this.a = 13;
                        this.e = 0;
                    } else {
                        if (iC == 0) {
                            iC = i14;
                        }
                        if (iC != 1) {
                            return iC;
                        }
                        this.c = this.i.m.getValue();
                        this.h.d();
                        if (this.f == 0) {
                            this.a = 12;
                            iC = i14;
                        } else {
                            this.a = 8;
                            iC = i14;
                            zStream = this.i;
                            i3 = zStream.c;
                            if (i3 == 0) {
                                return iC;
                            }
                            zStream.c = i3 - 1;
                            zStream.d++;
                            byte[] bArr12 = zStream.a;
                            int i20 = zStream.b;
                            zStream.b = i20 + 1;
                            this.d = ((long) ((bArr12[i20] & 255) << 24)) & 4278190080L;
                            this.a = 9;
                            iC = i14;
                            zStream4 = this.i;
                            i9 = zStream4.c;
                            if (i9 == 0) {
                                return iC;
                            }
                            zStream4.c = i9 - 1;
                            zStream4.d++;
                            long j16 = this.d;
                            byte[] bArr13 = zStream4.a;
                            int i21 = zStream4.b;
                            zStream4.b = i21 + 1;
                            this.d = j16 + (((long) ((bArr13[i21] & 255) << 16)) & 16711680);
                            this.a = 10;
                            iC = i14;
                            zStream3 = this.i;
                            i8 = zStream3.c;
                            if (i8 == 0) {
                                return iC;
                            }
                            zStream3.c = i8 - 1;
                            zStream3.d++;
                            long j17 = this.d;
                            byte[] bArr14 = zStream3.a;
                            int i22 = zStream3.b;
                            zStream3.b = i22 + 1;
                            this.d = j17 + (((long) ((bArr14[i22] & 255) << 8)) & 65280);
                            this.a = 11;
                            iC = i14;
                            zStream2 = this.i;
                            i4 = zStream2.c;
                            if (i4 == 0) {
                                return iC;
                            }
                            zStream2.c = i4 - 1;
                            zStream2.d++;
                            long j18 = this.d;
                            byte[] bArr15 = zStream2.a;
                            int i23 = zStream2.b;
                            zStream2.b = i23 + 1;
                            byte b = bArr15[i23];
                            i5 = 1;
                            j = j18 + (((long) b) & 255);
                            this.d = j;
                            i6 = this.j;
                            if (i6 != 0) {
                                this.d = (((j & (-16777216)) >> 24) | ((j & 16711680) >> 8) | ((j & 65280) << 8) | ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 24)) & 4294967295L;
                            }
                            i7 = (int) this.c;
                            j2 = this.d;
                            if (i7 != ((int) j2)) {
                                zStream2.i = "incorrect data check";
                            } else if (i6 != 0 && (gZIPHeader = this.m) != null) {
                                gZIPHeader.o0 = j2;
                            }
                            this.a = 15;
                            iC = i14;
                            if (this.f != 0 || this.j == 0) {
                                str = this.i.i;
                                if (str != null || !str.equals("incorrect data check")) {
                                    this.a = 12;
                                    return i5;
                                }
                                this.a = 13;
                                this.e = 5;
                            } else {
                                try {
                                    iC = g(4, iC, i14);
                                    String str2 = this.i.i;
                                    if (str2 == null || !str2.equals("incorrect data check")) {
                                        long j19 = this.d;
                                        ZStream zStream10 = this.i;
                                        if (j19 == (zStream10.h & 4294967295L)) {
                                            zStream10.i = null;
                                            this.a = 12;
                                            return i5;
                                        }
                                        zStream10.i = "incorrect length check";
                                        this.a = 13;
                                    } else {
                                        this.a = 13;
                                        this.e = 5;
                                    }
                                } catch (Return e) {
                                    return e.q;
                                }
                            }
                        }
                    }
                    break;
                case 8:
                    zStream = this.i;
                    i3 = zStream.c;
                    if (i3 == 0) {
                        return iC;
                    }
                    zStream.c = i3 - 1;
                    zStream.d++;
                    byte[] bArr16 = zStream.a;
                    int i24 = zStream.b;
                    zStream.b = i24 + 1;
                    this.d = ((long) ((bArr16[i24] & 255) << 24)) & 4278190080L;
                    this.a = 9;
                    iC = i14;
                    zStream4 = this.i;
                    i9 = zStream4.c;
                    if (i9 == 0) {
                        return iC;
                    }
                    zStream4.c = i9 - 1;
                    zStream4.d++;
                    long j110 = this.d;
                    byte[] bArr17 = zStream4.a;
                    int i25 = zStream4.b;
                    zStream4.b = i25 + 1;
                    this.d = j110 + (((long) ((bArr17[i25] & 255) << 16)) & 16711680);
                    this.a = 10;
                    iC = i14;
                    zStream3 = this.i;
                    i8 = zStream3.c;
                    if (i8 == 0) {
                        return iC;
                    }
                    zStream3.c = i8 - 1;
                    zStream3.d++;
                    long j111 = this.d;
                    byte[] bArr18 = zStream3.a;
                    int i26 = zStream3.b;
                    zStream3.b = i26 + 1;
                    this.d = j111 + (((long) ((bArr18[i26] & 255) << 8)) & 65280);
                    this.a = 11;
                    iC = i14;
                    zStream2 = this.i;
                    i4 = zStream2.c;
                    if (i4 == 0) {
                        return iC;
                    }
                    zStream2.c = i4 - 1;
                    zStream2.d++;
                    long j112 = this.d;
                    byte[] bArr19 = zStream2.a;
                    int i27 = zStream2.b;
                    zStream2.b = i27 + 1;
                    byte b2 = bArr19[i27];
                    i5 = 1;
                    j = j112 + (((long) b2) & 255);
                    this.d = j;
                    i6 = this.j;
                    if (i6 != 0) {
                        this.d = (((j & (-16777216)) >> 24) | ((j & 16711680) >> 8) | ((j & 65280) << 8) | ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 24)) & 4294967295L;
                    }
                    i7 = (int) this.c;
                    j2 = this.d;
                    if (i7 != ((int) j2)) {
                        zStream2.i = "incorrect data check";
                    } else if (i6 != 0) {
                        gZIPHeader.o0 = j2;
                    }
                    this.a = 15;
                    iC = i14;
                    if (this.f != 0) {
                    }
                    str = this.i.i;
                    if (str != null) {
                    }
                    this.a = 12;
                    return i5;
                case 9:
                    zStream4 = this.i;
                    i9 = zStream4.c;
                    if (i9 == 0) {
                        return iC;
                    }
                    zStream4.c = i9 - 1;
                    zStream4.d++;
                    long j113 = this.d;
                    byte[] bArr110 = zStream4.a;
                    int i28 = zStream4.b;
                    zStream4.b = i28 + 1;
                    this.d = j113 + (((long) ((bArr110[i28] & 255) << 16)) & 16711680);
                    this.a = 10;
                    iC = i14;
                    zStream3 = this.i;
                    i8 = zStream3.c;
                    if (i8 == 0) {
                        return iC;
                    }
                    zStream3.c = i8 - 1;
                    zStream3.d++;
                    long j114 = this.d;
                    byte[] bArr111 = zStream3.a;
                    int i29 = zStream3.b;
                    zStream3.b = i29 + 1;
                    this.d = j114 + (((long) ((bArr111[i29] & 255) << 8)) & 65280);
                    this.a = 11;
                    iC = i14;
                    zStream2 = this.i;
                    i4 = zStream2.c;
                    if (i4 == 0) {
                        return iC;
                    }
                    zStream2.c = i4 - 1;
                    zStream2.d++;
                    long j115 = this.d;
                    byte[] bArr112 = zStream2.a;
                    int i210 = zStream2.b;
                    zStream2.b = i210 + 1;
                    byte b3 = bArr112[i210];
                    i5 = 1;
                    j = j115 + (((long) b3) & 255);
                    this.d = j;
                    i6 = this.j;
                    if (i6 != 0) {
                        this.d = (((j & (-16777216)) >> 24) | ((j & 16711680) >> 8) | ((j & 65280) << 8) | ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 24)) & 4294967295L;
                    }
                    i7 = (int) this.c;
                    j2 = this.d;
                    if (i7 != ((int) j2)) {
                        zStream2.i = "incorrect data check";
                    } else if (i6 != 0) {
                        gZIPHeader.o0 = j2;
                    }
                    this.a = 15;
                    iC = i14;
                    if (this.f != 0) {
                    }
                    str = this.i.i;
                    if (str != null) {
                    }
                    this.a = 12;
                    return i5;
                case 10:
                    zStream3 = this.i;
                    i8 = zStream3.c;
                    if (i8 == 0) {
                        return iC;
                    }
                    zStream3.c = i8 - 1;
                    zStream3.d++;
                    long j116 = this.d;
                    byte[] bArr113 = zStream3.a;
                    int i211 = zStream3.b;
                    zStream3.b = i211 + 1;
                    this.d = j116 + (((long) ((bArr113[i211] & 255) << 8)) & 65280);
                    this.a = 11;
                    iC = i14;
                    zStream2 = this.i;
                    i4 = zStream2.c;
                    if (i4 == 0) {
                        return iC;
                    }
                    zStream2.c = i4 - 1;
                    zStream2.d++;
                    long j117 = this.d;
                    byte[] bArr114 = zStream2.a;
                    int i212 = zStream2.b;
                    zStream2.b = i212 + 1;
                    byte b4 = bArr114[i212];
                    i5 = 1;
                    j = j117 + (((long) b4) & 255);
                    this.d = j;
                    i6 = this.j;
                    if (i6 != 0) {
                        this.d = (((j & (-16777216)) >> 24) | ((j & 16711680) >> 8) | ((j & 65280) << 8) | ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 24)) & 4294967295L;
                    }
                    i7 = (int) this.c;
                    j2 = this.d;
                    if (i7 != ((int) j2)) {
                        zStream2.i = "incorrect data check";
                    } else if (i6 != 0) {
                        gZIPHeader.o0 = j2;
                    }
                    this.a = 15;
                    iC = i14;
                    if (this.f != 0) {
                    }
                    str = this.i.i;
                    if (str != null) {
                    }
                    this.a = 12;
                    return i5;
                case 11:
                    zStream2 = this.i;
                    i4 = zStream2.c;
                    if (i4 == 0) {
                        return iC;
                    }
                    zStream2.c = i4 - 1;
                    zStream2.d++;
                    long j118 = this.d;
                    byte[] bArr115 = zStream2.a;
                    int i213 = zStream2.b;
                    zStream2.b = i213 + 1;
                    byte b5 = bArr115[i213];
                    i5 = 1;
                    j = j118 + (((long) b5) & 255);
                    this.d = j;
                    i6 = this.j;
                    if (i6 != 0) {
                        this.d = (((j & (-16777216)) >> 24) | ((j & 16711680) >> 8) | ((j & 65280) << 8) | ((j & WebSocketProtocol.PAYLOAD_SHORT_MAX) << 24)) & 4294967295L;
                    }
                    i7 = (int) this.c;
                    j2 = this.d;
                    if (i7 != ((int) j2)) {
                        zStream2.i = "incorrect data check";
                    } else if (i6 != 0) {
                        gZIPHeader.o0 = j2;
                    }
                    this.a = 15;
                    iC = i14;
                    if (this.f != 0) {
                    }
                    str = this.i.i;
                    if (str != null) {
                    }
                    this.a = 12;
                    return i5;
                case 12:
                    return 1;
                case 13:
                    return -3;
                case 14:
                    if (this.f == 0) {
                        this.a = 7;
                    } else {
                        try {
                            iC = g(2, iC, i14);
                            int i30 = this.f;
                            if ((i30 == 4 || (i30 & 2) != 0) && this.d == 35615) {
                                if (i30 == 4) {
                                    this.f = 2;
                                }
                                this.i.m = new CRC32();
                                a(2, this.d);
                                if (this.m == null) {
                                    this.m = new GZIPHeader();
                                }
                                this.a = 23;
                            } else if ((i30 & 2) != 0) {
                                this.a = 13;
                                this.i.i = "incorrect header check";
                            } else {
                                this.j = 0;
                                long j20 = this.d;
                                int i31 = (int) j20;
                                int i32 = i31 & 255;
                                this.b = i32;
                                int i33 = (int) (j20 >> 8);
                                int i34 = i33 & 255;
                                if (((i30 & 1) == 0 || ((i32 << 8) + i34) % 31 != 0) && (i31 & 15) != 8) {
                                    if (i30 == 4) {
                                        ZStream zStream11 = this.i;
                                        zStream11.b -= 2;
                                        zStream11.c += 2;
                                        zStream11.d -= 2;
                                        this.f = 0;
                                        this.a = 7;
                                    } else {
                                        this.a = 13;
                                        this.i.i = "incorrect header check";
                                    }
                                } else if ((i31 & 15) != 8) {
                                    this.a = 13;
                                    this.i.i = "unknown compression method";
                                } else {
                                    if (i30 == 4) {
                                        this.f = 1;
                                    }
                                    if ((i32 >> 4) + 8 > this.g) {
                                        this.a = 13;
                                        this.i.i = "invalid window size";
                                    } else {
                                        this.i.m = new Adler32();
                                        if ((i33 & 32) != 0) {
                                            this.a = 2;
                                            i2 = 1;
                                            zStream8 = this.i;
                                            i13 = zStream8.c;
                                            if (i13 == 0) {
                                                return iC;
                                            }
                                            zStream8.c = i13 - 1;
                                            zStream8.d++;
                                            byte[] bArr20 = zStream8.a;
                                            int i115 = zStream8.b;
                                            zStream8.b = i115 + 1;
                                            this.d = ((long) ((bArr20[i115] & 255) << 24)) & 4278190080L;
                                            this.a = 3;
                                            iC = i14;
                                            zStream7 = this.i;
                                            i12 = zStream7.c;
                                            if (i12 == 0) {
                                                return iC;
                                            }
                                            zStream7.c = i12 - i2;
                                            zStream7.d++;
                                            long j21 = this.d;
                                            byte[] bArr21 = zStream7.a;
                                            int i116 = zStream7.b;
                                            zStream7.b = i116 + 1;
                                            this.d = j21 + (((long) ((bArr21[i116] & 255) << 16)) & 16711680);
                                            this.a = 4;
                                            iC = i14;
                                            zStream6 = this.i;
                                            i11 = zStream6.c;
                                            if (i11 == 0) {
                                                return iC;
                                            }
                                            zStream6.c = i11 - i2;
                                            zStream6.d++;
                                            long j119 = this.d;
                                            byte[] bArr22 = zStream6.a;
                                            int i117 = zStream6.b;
                                            zStream6.b = i117 + 1;
                                            this.d = j119 + (((long) ((bArr22[i117] & 255) << 8)) & 65280);
                                            this.a = 5;
                                            zStream5 = this.i;
                                            i10 = zStream5.c;
                                            if (i10 == 0) {
                                                return i14;
                                            }
                                            zStream5.c = i10 - i2;
                                            zStream5.d++;
                                            long j120 = this.d;
                                            byte[] bArr116 = zStream5.a;
                                            int i118 = zStream5.b;
                                            zStream5.b = i118 + 1;
                                            long j121 = j120 + (((long) bArr116[i118]) & 255);
                                            this.d = j121;
                                            zStream5.m.c(j121);
                                            this.a = 6;
                                            return 2;
                                        }
                                        this.a = 7;
                                    }
                                }
                            }
                        } catch (Return e2) {
                            return e2.q;
                        }
                    }
                    break;
                case 15:
                    i5 = 1;
                    if (this.f != 0) {
                    }
                    str = this.i.i;
                    if (str != null) {
                    }
                    this.a = 12;
                    return i5;
                case 16:
                    try {
                        iC = g(4, iC, i14);
                        gZIPHeader11 = this.m;
                        if (gZIPHeader11 != null) {
                            gZIPHeader11.d(this.d);
                        }
                        if ((this.j & 512) != 0) {
                            a(4, this.d);
                        }
                        this.a = 17;
                        try {
                            iC = g(2, iC, i14);
                            gZIPHeader10 = this.m;
                            if (gZIPHeader10 != null) {
                                long j22 = this.d;
                                gZIPHeader10.Y = ((int) j22) & 255;
                                gZIPHeader10.Z = (((int) j22) >> 8) & 255;
                            }
                            if ((this.j & 512) != 0) {
                                a(2, this.d);
                            }
                            this.a = 18;
                            if ((this.j & 1024) != 0) {
                                try {
                                    iC = g(2, iC, i14);
                                    gZIPHeader8 = this.m;
                                    if (gZIPHeader8 != null) {
                                        gZIPHeader8.k0 = new byte[((int) this.d) & Settings.DEFAULT_INITIAL_WINDOW_SIZE];
                                    }
                                    if ((this.j & 512) != 0) {
                                        a(2, this.d);
                                    }
                                } catch (Return e3) {
                                    return e3.q;
                                }
                            } else {
                                gZIPHeader9 = this.m;
                                if (gZIPHeader9 != null) {
                                    gZIPHeader9.k0 = null;
                                }
                            }
                            this.a = 19;
                            if ((this.j & 1024) != 0) {
                                try {
                                    iC = f(iC, i14);
                                    if (this.m != null) {
                                        byteArray = this.n.toByteArray();
                                        this.n = null;
                                        length = byteArray.length;
                                        bArr = this.m.k0;
                                        if (length == bArr.length) {
                                            System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                                        } else {
                                            this.i.i = "bad extra field length";
                                            this.a = 13;
                                        }
                                        break;
                                    }
                                } catch (Return e4) {
                                    return e4.q;
                                }
                            } else {
                                gZIPHeader7 = this.m;
                                if (gZIPHeader7 != null) {
                                    gZIPHeader7.k0 = null;
                                }
                            }
                            this.a = 20;
                            if ((this.j & 2048) != 0) {
                                try {
                                    iC = h(iC, i14);
                                    gZIPHeader5 = this.m;
                                    if (gZIPHeader5 != null) {
                                        gZIPHeader5.l0 = this.n.toByteArray();
                                    }
                                    this.n = null;
                                } catch (Return e5) {
                                    return e5.q;
                                }
                            } else {
                                gZIPHeader6 = this.m;
                                if (gZIPHeader6 != null) {
                                    gZIPHeader6.l0 = null;
                                }
                            }
                            this.a = 21;
                            if ((this.j & 4096) != 0) {
                                try {
                                    iC = h(iC, i14);
                                    gZIPHeader3 = this.m;
                                    if (gZIPHeader3 != null) {
                                        gZIPHeader3.m0 = this.n.toByteArray();
                                    }
                                    this.n = null;
                                } catch (Return e6) {
                                    return e6.q;
                                }
                            } else {
                                gZIPHeader4 = this.m;
                                if (gZIPHeader4 != null) {
                                    gZIPHeader4.m0 = null;
                                }
                            }
                            this.a = 22;
                            if ((this.j & 512) != 0) {
                                try {
                                    iC = g(2, iC, i14);
                                    gZIPHeader2 = this.m;
                                    if (gZIPHeader2 != null) {
                                        gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                                    }
                                    if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                                        this.a = 13;
                                        this.i.i = "header crc mismatch";
                                        this.e = 5;
                                    }
                                } catch (Return e7) {
                                    return e7.q;
                                }
                                break;
                            }
                            this.i.m = new CRC32();
                            this.a = 7;
                        } catch (Return e8) {
                            return e8.q;
                        }
                    } catch (Return e9) {
                        return e9.q;
                    }
                    break;
                case 17:
                    iC = g(2, iC, i14);
                    gZIPHeader10 = this.m;
                    if (gZIPHeader10 != null) {
                        long j23 = this.d;
                        gZIPHeader10.Y = ((int) j23) & 255;
                        gZIPHeader10.Z = (((int) j23) >> 8) & 255;
                    }
                    if ((this.j & 512) != 0) {
                        a(2, this.d);
                    }
                    this.a = 18;
                    if ((this.j & 1024) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader8 = this.m;
                        if (gZIPHeader8 != null) {
                            gZIPHeader8.k0 = new byte[((int) this.d) & Settings.DEFAULT_INITIAL_WINDOW_SIZE];
                        }
                        if ((this.j & 512) != 0) {
                            a(2, this.d);
                        }
                    } else {
                        gZIPHeader9 = this.m;
                        if (gZIPHeader9 != null) {
                            gZIPHeader9.k0 = null;
                        }
                    }
                    this.a = 19;
                    if ((this.j & 1024) != 0) {
                        iC = f(iC, i14);
                        if (this.m != null) {
                            byteArray = this.n.toByteArray();
                            this.n = null;
                            length = byteArray.length;
                            bArr = this.m.k0;
                            if (length == bArr.length) {
                                System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                            } else {
                                this.i.i = "bad extra field length";
                                this.a = 13;
                            }
                            break;
                        }
                    } else {
                        gZIPHeader7 = this.m;
                        if (gZIPHeader7 != null) {
                            gZIPHeader7.k0 = null;
                        }
                    }
                    this.a = 20;
                    if ((this.j & 2048) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader5 = this.m;
                        if (gZIPHeader5 != null) {
                            gZIPHeader5.l0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader6 = this.m;
                        if (gZIPHeader6 != null) {
                            gZIPHeader6.l0 = null;
                        }
                    }
                    this.a = 21;
                    if ((this.j & 4096) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader3 = this.m;
                        if (gZIPHeader3 != null) {
                            gZIPHeader3.m0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader4 = this.m;
                        if (gZIPHeader4 != null) {
                            gZIPHeader4.m0 = null;
                        }
                    }
                    this.a = 22;
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case 18:
                    if ((this.j & 1024) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader8 = this.m;
                        if (gZIPHeader8 != null) {
                            gZIPHeader8.k0 = new byte[((int) this.d) & Settings.DEFAULT_INITIAL_WINDOW_SIZE];
                        }
                        if ((this.j & 512) != 0) {
                            a(2, this.d);
                        }
                    } else {
                        gZIPHeader9 = this.m;
                        if (gZIPHeader9 != null) {
                            gZIPHeader9.k0 = null;
                        }
                    }
                    this.a = 19;
                    if ((this.j & 1024) != 0) {
                        iC = f(iC, i14);
                        if (this.m != null) {
                            byteArray = this.n.toByteArray();
                            this.n = null;
                            length = byteArray.length;
                            bArr = this.m.k0;
                            if (length == bArr.length) {
                                System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                            } else {
                                this.i.i = "bad extra field length";
                                this.a = 13;
                            }
                            break;
                        }
                    } else {
                        gZIPHeader7 = this.m;
                        if (gZIPHeader7 != null) {
                            gZIPHeader7.k0 = null;
                        }
                    }
                    this.a = 20;
                    if ((this.j & 2048) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader5 = this.m;
                        if (gZIPHeader5 != null) {
                            gZIPHeader5.l0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader6 = this.m;
                        if (gZIPHeader6 != null) {
                            gZIPHeader6.l0 = null;
                        }
                    }
                    this.a = 21;
                    if ((this.j & 4096) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader3 = this.m;
                        if (gZIPHeader3 != null) {
                            gZIPHeader3.m0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader4 = this.m;
                        if (gZIPHeader4 != null) {
                            gZIPHeader4.m0 = null;
                        }
                    }
                    this.a = 22;
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case 19:
                    if ((this.j & 1024) != 0) {
                        iC = f(iC, i14);
                        if (this.m != null) {
                            byteArray = this.n.toByteArray();
                            this.n = null;
                            length = byteArray.length;
                            bArr = this.m.k0;
                            if (length == bArr.length) {
                                System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                            } else {
                                this.i.i = "bad extra field length";
                                this.a = 13;
                            }
                            break;
                        }
                    } else {
                        gZIPHeader7 = this.m;
                        if (gZIPHeader7 != null) {
                            gZIPHeader7.k0 = null;
                        }
                    }
                    this.a = 20;
                    if ((this.j & 2048) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader5 = this.m;
                        if (gZIPHeader5 != null) {
                            gZIPHeader5.l0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader6 = this.m;
                        if (gZIPHeader6 != null) {
                            gZIPHeader6.l0 = null;
                        }
                    }
                    this.a = 21;
                    if ((this.j & 4096) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader3 = this.m;
                        if (gZIPHeader3 != null) {
                            gZIPHeader3.m0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader4 = this.m;
                        if (gZIPHeader4 != null) {
                            gZIPHeader4.m0 = null;
                        }
                    }
                    this.a = 22;
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case 20:
                    if ((this.j & 2048) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader5 = this.m;
                        if (gZIPHeader5 != null) {
                            gZIPHeader5.l0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader6 = this.m;
                        if (gZIPHeader6 != null) {
                            gZIPHeader6.l0 = null;
                        }
                    }
                    this.a = 21;
                    if ((this.j & 4096) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader3 = this.m;
                        if (gZIPHeader3 != null) {
                            gZIPHeader3.m0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader4 = this.m;
                        if (gZIPHeader4 != null) {
                            gZIPHeader4.m0 = null;
                        }
                    }
                    this.a = 22;
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case C3772df.zzm /* 21 */:
                    if ((this.j & 4096) != 0) {
                        iC = h(iC, i14);
                        gZIPHeader3 = this.m;
                        if (gZIPHeader3 != null) {
                            gZIPHeader3.m0 = this.n.toByteArray();
                        }
                        this.n = null;
                    } else {
                        gZIPHeader4 = this.m;
                        if (gZIPHeader4 != null) {
                            gZIPHeader4.m0 = null;
                        }
                    }
                    this.a = 22;
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case 22:
                    if ((this.j & 512) != 0) {
                        iC = g(2, iC, i14);
                        gZIPHeader2 = this.m;
                        if (gZIPHeader2 != null) {
                            gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                        }
                        if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                            this.a = 13;
                            this.i.i = "header crc mismatch";
                            this.e = 5;
                        }
                        break;
                    }
                    this.i.m = new CRC32();
                    this.a = 7;
                    break;
                case 23:
                    try {
                        iC = g(2, iC, i14);
                        long j24 = this.d;
                        int i35 = (int) j24;
                        this.j = i35 & Settings.DEFAULT_INITIAL_WINDOW_SIZE;
                        if ((i35 & 255) != 8) {
                            this.i.i = "unknown compression method";
                            this.a = 13;
                        } else if ((57344 & i35) != 0) {
                            this.i.i = "unknown header flags set";
                            this.a = 13;
                        } else {
                            if ((i35 & 512) != 0) {
                                a(2, j24);
                            }
                            this.a = 16;
                            iC = g(4, iC, i14);
                            gZIPHeader11 = this.m;
                            if (gZIPHeader11 != null) {
                                gZIPHeader11.d(this.d);
                            }
                            if ((this.j & 512) != 0) {
                                a(4, this.d);
                            }
                            this.a = 17;
                            iC = g(2, iC, i14);
                            gZIPHeader10 = this.m;
                            if (gZIPHeader10 != null) {
                                long j25 = this.d;
                                gZIPHeader10.Y = ((int) j25) & 255;
                                gZIPHeader10.Z = (((int) j25) >> 8) & 255;
                            }
                            if ((this.j & 512) != 0) {
                                a(2, this.d);
                            }
                            this.a = 18;
                            if ((this.j & 1024) != 0) {
                                iC = g(2, iC, i14);
                                gZIPHeader8 = this.m;
                                if (gZIPHeader8 != null) {
                                    gZIPHeader8.k0 = new byte[((int) this.d) & Settings.DEFAULT_INITIAL_WINDOW_SIZE];
                                }
                                if ((this.j & 512) != 0) {
                                    a(2, this.d);
                                }
                            } else {
                                gZIPHeader9 = this.m;
                                if (gZIPHeader9 != null) {
                                    gZIPHeader9.k0 = null;
                                }
                            }
                            this.a = 19;
                            if ((this.j & 1024) != 0) {
                                iC = f(iC, i14);
                                if (this.m != null) {
                                    byteArray = this.n.toByteArray();
                                    this.n = null;
                                    length = byteArray.length;
                                    bArr = this.m.k0;
                                    if (length == bArr.length) {
                                        System.arraycopy(byteArray, 0, bArr, 0, byteArray.length);
                                    } else {
                                        this.i.i = "bad extra field length";
                                        this.a = 13;
                                    }
                                }
                            } else {
                                gZIPHeader7 = this.m;
                                if (gZIPHeader7 != null) {
                                    gZIPHeader7.k0 = null;
                                }
                            }
                            this.a = 20;
                            if ((this.j & 2048) != 0) {
                                iC = h(iC, i14);
                                gZIPHeader5 = this.m;
                                if (gZIPHeader5 != null) {
                                    gZIPHeader5.l0 = this.n.toByteArray();
                                }
                                this.n = null;
                            } else {
                                gZIPHeader6 = this.m;
                                if (gZIPHeader6 != null) {
                                    gZIPHeader6.l0 = null;
                                }
                            }
                            this.a = 21;
                            if ((this.j & 4096) != 0) {
                                iC = h(iC, i14);
                                gZIPHeader3 = this.m;
                                if (gZIPHeader3 != null) {
                                    gZIPHeader3.m0 = this.n.toByteArray();
                                }
                                this.n = null;
                            } else {
                                gZIPHeader4 = this.m;
                                if (gZIPHeader4 != null) {
                                    gZIPHeader4.m0 = null;
                                }
                            }
                            this.a = 22;
                            if ((this.j & 512) != 0) {
                                iC = g(2, iC, i14);
                                gZIPHeader2 = this.m;
                                if (gZIPHeader2 != null) {
                                    gZIPHeader2.n0 = (int) (this.d & WebSocketProtocol.PAYLOAD_SHORT_MAX);
                                }
                                if (this.d != (this.i.m.getValue() & WebSocketProtocol.PAYLOAD_SHORT_MAX)) {
                                    this.a = 13;
                                    this.i.i = "header crc mismatch";
                                    this.e = 5;
                                }
                            }
                            this.i.m = new CRC32();
                            this.a = 7;
                        }
                    } catch (Return e10) {
                        return e10.q;
                    }
                    break;
                default:
                    return -2;
            }
        }
    }

    int c() {
        InfBlocks infBlocks = this.h;
        if (infBlocks == null) {
            return 0;
        }
        infBlocks.a();
        return 0;
    }

    int d(int i) {
        this.i.i = null;
        this.h = null;
        this.f = 0;
        if (i < 0) {
            i = -i;
        } else if ((1073741824 & i) != 0) {
            this.f = 4;
            int i2 = (-1073741825) & i;
            i = i2 < 48 ? i & 15 : i2;
        } else {
            if ((i & (-32)) != 0) {
                this.f = 4;
            } else {
                this.f = (i >> 4) + 1;
                if (i < 48) {
                }
            }
        }
        if (i < 8 || i > 15) {
            c();
            return -2;
        }
        this.g = i;
        this.h = new InfBlocks(this.i, 1 << i);
        e();
        return 0;
    }

    int e() {
        ZStream zStream = this.i;
        if (zStream == null) {
            return -2;
        }
        zStream.h = 0L;
        zStream.d = 0L;
        zStream.i = null;
        this.a = 14;
        this.k = -1;
        this.h.d();
        return 0;
    }
}
