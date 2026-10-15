package com.jcraft.jsch;

import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class KeyPairECDSA extends KeyPair {
    private static byte[][] v = {new byte[]{6, 8, 42, -122, 72, -50, 61, 3, 1, 7}, new byte[]{6, 5, 43, -127, 4, 0, 34}, new byte[]{6, 5, 43, -127, 4, 0, 35}};
    private static String[] w = {"nistp256", "nistp384", "nistp521"};
    private static final byte[] x = Util.v("-----BEGIN EC PRIVATE KEY-----");
    private static final byte[] y = Util.v("-----END EC PRIVATE KEY-----");
    private byte[] q;
    private byte[] r;
    private byte[] s;
    private byte[] t;
    private int u;

    KeyPairECDSA(JSch.InstanceLogger instanceLogger) {
        this(instanceLogger, null, null, null, null);
    }

    KeyPairECDSA(JSch.InstanceLogger instanceLogger, byte[] bArr) {
        this(instanceLogger, null, null, null, null);
        if (bArr != null) {
            byte[] bArr2 = new byte[8];
            System.arraycopy(bArr, 11, bArr2, 0, 8);
            if (Util.a(bArr2, Util.v("nistp384"))) {
                this.u = 384;
                this.q = bArr2;
            }
            if (Util.a(bArr2, Util.v("nistp521"))) {
                this.u = 521;
                this.q = bArr2;
            }
        }
    }

    KeyPairECDSA(JSch.InstanceLogger instanceLogger, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        super(instanceLogger);
        this.q = Util.v(w[0]);
        int i = 256;
        this.u = 256;
        if (bArr != null) {
            this.q = bArr;
        }
        this.r = bArr2;
        this.s = bArr3;
        this.t = bArr4;
        if (bArr4 != null) {
            if (bArr4.length >= 64) {
                i = 521;
            } else if (bArr4.length >= 48) {
                i = 384;
            }
            this.u = i;
        }
    }

    static byte[][] H(byte[] bArr) {
        int i = 0;
        while (bArr[i] != 4) {
            i++;
        }
        int i2 = i + 1;
        int length = (bArr.length - i2) / 2;
        byte[] bArr2 = new byte[length];
        int length2 = (bArr.length - i2) / 2;
        byte[] bArr3 = new byte[length2];
        System.arraycopy(bArr, i2, bArr2, 0, length);
        System.arraycopy(bArr, i2 + length, bArr3, 0, length2);
        return new byte[][]{bArr2, bArr3};
    }

    static KeyPair I(JSch.InstanceLogger instanceLogger, Buffer buffer) throws JSchException {
        byte[][] bArrG = buffer.g(5, "invalid key format");
        byte[] bArr = bArrG[1];
        byte[][] bArrH = H(bArrG[2]);
        KeyPairECDSA keyPairECDSA = new KeyPairECDSA(instanceLogger, bArr, bArrH[0], bArrH[1], bArrG[3]);
        keyPairECDSA.b = Util.c(bArrG[4]);
        keyPairECDSA.a = 0;
        return keyPairECDSA;
    }

    static byte[] J(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + 1 + bArr2.length];
        bArr3[0] = 4;
        System.arraycopy(bArr, 0, bArr3, 1, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length + 1, bArr2.length);
        return bArr3;
    }

    @Override // com.jcraft.jsch.KeyPair
    public void f() {
        super.f();
        Util.g(this.t);
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] g() throws JSchException {
        if (r()) {
            throw new JSchException("key is encrypted.");
        }
        Buffer buffer = new Buffer();
        buffer.y(Util.v("ecdsa-sha2-" + Util.c(this.q)));
        buffer.y(this.q);
        buffer.y(J(this.r, this.s));
        buffer.y(this.t);
        buffer.y(Util.v(this.b));
        int iJ = buffer.j();
        byte[] bArr = new byte[iJ];
        buffer.f(bArr, 0, iJ);
        return bArr;
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] l() {
        return Util.v("ecdsa-sha2-" + Util.c(this.q));
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] n() {
        char c;
        byte[] bArr = {1};
        byte[][] bArr2 = v;
        byte[] bArr3 = this.r;
        if (bArr3.length >= 64) {
            c = 2;
        } else {
            c = bArr3.length >= 48 ? (char) 1 : (char) 0;
        }
        byte[] bArr4 = bArr2[c];
        byte[] bArrJ = J(bArr3, this.s);
        int i = ((bArrJ.length + 1) & 128) == 0 ? 3 : 4;
        int length = bArrJ.length + i;
        byte[] bArr5 = new byte[length];
        System.arraycopy(bArrJ, 0, bArr5, i, bArrJ.length);
        bArr5[0] = 3;
        if (i == 3) {
            bArr5[1] = (byte) (bArrJ.length + 1);
        } else {
            bArr5[1] = -127;
            bArr5[2] = (byte) (bArrJ.length + 1);
        }
        int iC = c(1) + 3 + c(this.t.length) + this.t.length + 1 + c(bArr4.length) + bArr4.length + 1 + c(length) + length;
        byte[] bArr6 = new byte[c(iC) + 1 + iC];
        C(bArr6, (byte) -95, C(bArr6, (byte) -96, F(bArr6, D(bArr6, G(bArr6, 0, iC), bArr), this.t), bArr4), bArr5);
        return bArr6;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] o() {
        byte[] bArrO = super.o();
        if (bArrO != null) {
            return bArrO;
        }
        if (this.r == null) {
            return null;
        }
        byte[] bArrV = Util.v("ecdsa-sha2-" + Util.c(this.q));
        byte[] bArr = this.q;
        byte[] bArr2 = this.r;
        byte[][] bArr3 = {bArrV, bArr, new byte[bArr2.length + 1 + this.s.length]};
        byte[] bArr4 = bArr3[2];
        bArr4[0] = 4;
        System.arraycopy(bArr2, 0, bArr4, 1, bArr2.length);
        byte[] bArr5 = this.s;
        System.arraycopy(bArr5, 0, bArr3[2], this.r.length + 1, bArr5.length);
        return Buffer.b(bArr3).b;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] p(byte[] bArr) {
        try {
            SignatureECDSA signatureECDSA = (SignatureECDSA) Class.forName(JSch.f("ecdsa-sha2-" + Util.c(this.q))).asSubclass(SignatureECDSA.class).getDeclaredConstructor(null).newInstance(null);
            signatureECDSA.a();
            signatureECDSA.b(this.t);
            signatureECDSA.e(bArr);
            return Buffer.b(new byte[][]{Util.v("ecdsa-sha2-" + Util.c(this.q)), signatureECDSA.j()}).b;
        } catch (Exception e) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to generate signature", e);
            }
            return null;
        }
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] q(byte[] bArr, String str) {
        return p(bArr);
    }

    @Override // com.jcraft.jsch.KeyPair
    boolean y(byte[] bArr) {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            int i5 = this.a;
            if (i5 == 1) {
                return false;
            }
            if (i5 != 2 && i5 != 5) {
                if (i5 == 4) {
                    Buffer buffer = new Buffer(bArr);
                    if (buffer.i() != buffer.i()) {
                        throw new JSchException("check failed");
                    }
                    Util.c(buffer.p());
                    this.q = buffer.p();
                    if (!Arrays.asList(w).contains(Util.c(this.q))) {
                        throw new IllegalArgumentException("unknown curve name " + Util.c(this.q));
                    }
                    int i6 = buffer.i();
                    buffer.c();
                    int i7 = i6 - 1;
                    int i8 = i7 / 2;
                    byte[] bArr2 = new byte[i8];
                    byte[] bArr3 = new byte[i7 / 2];
                    buffer.e(bArr2);
                    buffer.e(bArr3);
                    this.t = buffer.p();
                    this.b = Util.c(buffer.p());
                    this.r = bArr2;
                    this.s = bArr3;
                    if (i8 >= 64) {
                        i4 = 521;
                    } else {
                        i4 = i8 >= 48 ? 384 : 256;
                    }
                    this.u = i4;
                    return true;
                }
                if (bArr[0] != 48) {
                    return false;
                }
                byte b = bArr[1];
                if ((b & 128) != 0) {
                    int i9 = b & 127;
                    i2 = 2;
                    while (true) {
                        int i10 = i9 - 1;
                        if (i9 <= 0) {
                            break;
                        }
                        int i11 = i2 + 1;
                        byte b2 = bArr[i2];
                        i2 = i11;
                        i9 = i10;
                    }
                } else {
                    i2 = 2;
                }
                if (bArr[i2] != 2) {
                    return false;
                }
                int i12 = i2 + 1;
                int i13 = i2 + 2;
                int i14 = bArr[i12];
                int i15 = i14 & 255;
                if ((i14 & 128) != 0) {
                    int i16 = i14 & 127;
                    i15 = 0;
                    while (true) {
                        int i17 = i16 - 1;
                        if (i16 <= 0) {
                            break;
                        }
                        int i18 = (i15 << 8) + (bArr[i13] & 255);
                        i13++;
                        i15 = i18;
                        i16 = i17;
                    }
                }
                int i19 = i13 + i15;
                int i20 = i19 + 1;
                int i21 = i19 + 2;
                int i22 = bArr[i20];
                int i23 = i22 & 255;
                if ((i22 & 128) != 0) {
                    int i24 = i22 & 127;
                    i23 = 0;
                    while (true) {
                        int i25 = i24 - 1;
                        if (i24 <= 0) {
                            break;
                        }
                        int i26 = (i23 << 8) + (bArr[i21] & 255);
                        i21++;
                        i23 = i26;
                        i24 = i25;
                    }
                }
                byte[] bArr4 = new byte[i23];
                this.t = bArr4;
                System.arraycopy(bArr, i21, bArr4, 0, i23);
                int i27 = i21 + i23;
                int i28 = i27 + 1;
                int i29 = i27 + 2;
                int i30 = bArr[i28];
                int i31 = i30 & 255;
                if ((i30 & 128) != 0) {
                    int i32 = i30 & 127;
                    i31 = 0;
                    while (true) {
                        int i33 = i32 - 1;
                        if (i32 <= 0) {
                            break;
                        }
                        int i34 = (i31 << 8) + (bArr[i29] & 255);
                        i29++;
                        i31 = i34;
                        i32 = i33;
                    }
                }
                byte[] bArr5 = new byte[i31];
                System.arraycopy(bArr, i29, bArr5, 0, i31);
                int i35 = i29 + i31;
                int i36 = 0;
                while (true) {
                    byte[][] bArr6 = v;
                    if (i36 >= bArr6.length) {
                        break;
                    }
                    if (Util.a(bArr6[i36], bArr5)) {
                        this.q = Util.v(w[i36]);
                        break;
                    }
                    i36++;
                }
                int i37 = i35 + 1;
                int i38 = i35 + 2;
                int i39 = bArr[i37];
                int i40 = i39 & 255;
                if ((i39 & 128) != 0) {
                    int i41 = i39 & 127;
                    i40 = 0;
                    while (true) {
                        int i42 = i41 - 1;
                        if (i41 <= 0) {
                            break;
                        }
                        int i43 = (i40 << 8) + (bArr[i38] & 255);
                        i38++;
                        i40 = i43;
                        i41 = i42;
                    }
                }
                byte[] bArr7 = new byte[i40];
                System.arraycopy(bArr, i38, bArr7, 0, i40);
                byte[][] bArrH = H(bArr7);
                this.r = bArrH[0];
                this.s = bArrH[1];
                byte[] bArr8 = this.t;
                if (bArr8 != null) {
                    if (bArr8.length >= 64) {
                        i3 = 521;
                    } else {
                        i3 = bArr8.length >= 48 ? 384 : 256;
                    }
                    this.u = i3;
                }
                return true;
            }
            Buffer buffer2 = new Buffer(bArr);
            buffer2.E(bArr.length);
            try {
                byte[] bArr9 = buffer2.g(1, HttpUrl.FRAGMENT_ENCODE_SET)[0];
                this.t = bArr9;
                if (bArr9.length >= 64) {
                    i = 521;
                } else {
                    i = bArr9.length >= 48 ? 384 : 256;
                }
                this.u = i;
                return true;
            } catch (JSchException e) {
                if (this.c.a().isEnabled(3)) {
                    this.c.a().b(3, "failed to parse key", e);
                }
                return false;
            }
        } catch (Exception e2) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to parse key", e2);
            }
            return false;
        }
    }
}
