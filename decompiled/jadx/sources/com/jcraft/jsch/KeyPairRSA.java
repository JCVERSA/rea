package com.jcraft.jsch;

import java.math.BigInteger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class KeyPairRSA extends KeyPair {
    private byte[] q;
    private byte[] r;
    private byte[] s;
    private byte[] t;
    private byte[] u;
    private byte[] v;
    private byte[] w;
    private byte[] x;
    private int y;
    private static final byte[] z = Util.v("-----BEGIN RSA PRIVATE KEY-----");
    private static final byte[] A = Util.v("-----END RSA PRIVATE KEY-----");
    private static final byte[] B = Util.v("ssh-rsa");

    KeyPairRSA(JSch.InstanceLogger instanceLogger) {
        this(instanceLogger, null, null, null);
    }

    KeyPairRSA(JSch.InstanceLogger instanceLogger, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        super(instanceLogger);
        this.y = 1024;
        this.q = bArr;
        this.r = bArr2;
        this.s = bArr3;
        if (bArr != null) {
            this.y = new BigInteger(bArr).bitLength();
        }
    }

    static KeyPair H(JSch.InstanceLogger instanceLogger, Buffer buffer) throws JSchException {
        byte[][] bArrG = buffer.g(8, "invalid key format");
        KeyPairRSA keyPairRSA = new KeyPairRSA(instanceLogger, bArrG[1], bArrG[2], bArrG[3]);
        keyPairRSA.x = bArrG[4];
        keyPairRSA.t = bArrG[5];
        keyPairRSA.u = bArrG[6];
        keyPairRSA.b = Util.c(bArrG[7]);
        keyPairRSA.a = 0;
        return keyPairRSA;
    }

    private byte[] I() {
        if (this.x == null) {
            this.x = new BigInteger(this.u).modInverse(new BigInteger(this.t)).toByteArray();
        }
        return this.x;
    }

    private byte[] J() {
        if (this.v == null) {
            this.v = new BigInteger(this.s).mod(new BigInteger(this.t).subtract(BigInteger.ONE)).toByteArray();
        }
        return this.v;
    }

    private byte[] K() {
        if (this.w == null) {
            this.w = new BigInteger(this.s).mod(new BigInteger(this.u).subtract(BigInteger.ONE)).toByteArray();
        }
        return this.w;
    }

    @Override // com.jcraft.jsch.KeyPair
    public void f() {
        super.f();
        Util.g(this.s);
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] g() throws JSchException {
        if (r()) {
            throw new JSchException("key is encrypted.");
        }
        Buffer buffer = new Buffer();
        buffer.y(B);
        buffer.y(this.q);
        buffer.y(this.r);
        buffer.y(this.s);
        buffer.y(I());
        buffer.y(this.t);
        buffer.y(this.u);
        buffer.y(Util.v(this.b));
        int iJ = buffer.j();
        byte[] bArr = new byte[iJ];
        buffer.f(bArr, 0, iJ);
        return bArr;
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] l() {
        return B;
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] n() {
        int iC = c(1) + 3 + c(this.q.length) + this.q.length + 1 + c(this.r.length) + this.r.length + 1 + c(this.s.length) + this.s.length + 1 + c(this.t.length) + this.t.length + 1 + c(this.u.length) + this.u.length + 1 + c(this.v.length) + this.v.length + 1 + c(this.w.length) + this.w.length + 1 + c(this.x.length) + this.x.length;
        byte[] bArr = new byte[c(iC) + 1 + iC];
        D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, G(bArr, 0, iC), new byte[1]), this.q), this.r), this.s), this.t), this.u), this.v), this.w), this.x);
        return bArr;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] o() {
        byte[] bArrO = super.o();
        if (bArrO != null) {
            return bArrO;
        }
        byte[] bArr = this.r;
        if (bArr == null) {
            return null;
        }
        return Buffer.b(new byte[][]{B, bArr, this.q}).b;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] p(byte[] bArr) {
        return q(bArr, "ssh-rsa");
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] q(byte[] bArr, String str) {
        try {
            SignatureRSA signatureRSA = (SignatureRSA) Class.forName(JSch.f(str)).asSubclass(SignatureRSA.class).getDeclaredConstructor(null).newInstance(null);
            signatureRSA.a();
            signatureRSA.g(this.s, this.q);
            signatureRSA.e(bArr);
            return Buffer.b(new byte[][]{Util.v(str), signatureRSA.j()}).b;
        } catch (Exception e) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to generate signature", e);
            }
            return null;
        }
    }

    @Override // com.jcraft.jsch.KeyPair
    boolean y(byte[] bArr) {
        int i;
        try {
            int i2 = this.a;
            if (i2 != 2 && i2 != 5) {
                if (i2 == 1) {
                    if (bArr[0] == 48) {
                        if (this.c.a().isEnabled(3)) {
                            this.c.a().a(3, "failed to parse key");
                        }
                        return false;
                    }
                    Buffer buffer = new Buffer(bArr);
                    this.r = buffer.m();
                    this.s = buffer.m();
                    this.q = buffer.m();
                    buffer.m();
                    this.t = buffer.m();
                    this.u = buffer.m();
                    if (this.q != null) {
                        this.y = new BigInteger(this.q).bitLength();
                    }
                    J();
                    K();
                    I();
                    return true;
                }
                if (i2 == 4) {
                    Buffer buffer2 = new Buffer(bArr);
                    if (buffer2.i() != buffer2.i()) {
                        throw new JSchException("check failed");
                    }
                    Util.c(buffer2.p());
                    this.q = buffer2.l();
                    this.r = buffer2.l();
                    this.s = buffer2.l();
                    this.x = buffer2.l();
                    this.t = buffer2.l();
                    this.u = buffer2.l();
                    if (this.q != null) {
                        this.y = new BigInteger(this.q).bitLength();
                    }
                    this.b = Util.c(buffer2.p());
                    J();
                    K();
                    return true;
                }
                byte b = bArr[1];
                if ((b & 128) != 0) {
                    int i3 = b & 127;
                    i = 2;
                    while (true) {
                        int i4 = i3 - 1;
                        if (i3 <= 0) {
                            break;
                        }
                        int i5 = i + 1;
                        byte b2 = bArr[i];
                        i = i5;
                        i3 = i4;
                    }
                } else {
                    i = 2;
                }
                if (bArr[i] != 2) {
                    return false;
                }
                int i6 = i + 1;
                int i7 = i + 2;
                int i8 = bArr[i6];
                int i9 = i8 & 255;
                if ((i8 & 128) != 0) {
                    int i10 = i8 & 127;
                    i9 = 0;
                    while (true) {
                        int i11 = i10 - 1;
                        if (i10 <= 0) {
                            break;
                        }
                        int i12 = (i9 << 8) + (bArr[i7] & 255);
                        i7++;
                        i9 = i12;
                        i10 = i11;
                    }
                }
                int i13 = i7 + i9;
                int i14 = i13 + 1;
                int i15 = i13 + 2;
                int i16 = bArr[i14];
                int i17 = i16 & 255;
                if ((i16 & 128) != 0) {
                    int i18 = i16 & 127;
                    i17 = 0;
                    while (true) {
                        int i19 = i18 - 1;
                        if (i18 <= 0) {
                            break;
                        }
                        int i20 = (i17 << 8) + (bArr[i15] & 255);
                        i15++;
                        i17 = i20;
                        i18 = i19;
                    }
                }
                byte[] bArr2 = new byte[i17];
                this.q = bArr2;
                System.arraycopy(bArr, i15, bArr2, 0, i17);
                int i21 = i15 + i17;
                int i22 = i21 + 1;
                int i23 = i21 + 2;
                int i24 = bArr[i22];
                int i25 = i24 & 255;
                if ((i24 & 128) != 0) {
                    int i26 = i24 & 127;
                    i25 = 0;
                    while (true) {
                        int i27 = i26 - 1;
                        if (i26 <= 0) {
                            break;
                        }
                        int i28 = (i25 << 8) + (bArr[i23] & 255);
                        i23++;
                        i25 = i28;
                        i26 = i27;
                    }
                }
                byte[] bArr3 = new byte[i25];
                this.r = bArr3;
                System.arraycopy(bArr, i23, bArr3, 0, i25);
                int i29 = i23 + i25;
                int i30 = i29 + 1;
                int i31 = i29 + 2;
                int i32 = bArr[i30];
                int i33 = i32 & 255;
                if ((i32 & 128) != 0) {
                    int i34 = i32 & 127;
                    i33 = 0;
                    while (true) {
                        int i35 = i34 - 1;
                        if (i34 <= 0) {
                            break;
                        }
                        int i36 = (i33 << 8) + (bArr[i31] & 255);
                        i31++;
                        i33 = i36;
                        i34 = i35;
                    }
                }
                byte[] bArr4 = new byte[i33];
                this.s = bArr4;
                System.arraycopy(bArr, i31, bArr4, 0, i33);
                int i37 = i31 + i33;
                int i38 = i37 + 1;
                int i39 = i37 + 2;
                int i40 = bArr[i38];
                int i41 = i40 & 255;
                if ((i40 & 128) != 0) {
                    int i42 = i40 & 127;
                    i41 = 0;
                    while (true) {
                        int i43 = i42 - 1;
                        if (i42 <= 0) {
                            break;
                        }
                        int i44 = (i41 << 8) + (bArr[i39] & 255);
                        i39++;
                        i41 = i44;
                        i42 = i43;
                    }
                }
                byte[] bArr5 = new byte[i41];
                this.t = bArr5;
                System.arraycopy(bArr, i39, bArr5, 0, i41);
                int i45 = i39 + i41;
                int i46 = i45 + 1;
                int i47 = i45 + 2;
                int i48 = bArr[i46];
                int i49 = i48 & 255;
                if ((i48 & 128) != 0) {
                    int i50 = i48 & 127;
                    i49 = 0;
                    while (true) {
                        int i51 = i50 - 1;
                        if (i50 <= 0) {
                            break;
                        }
                        int i52 = (i49 << 8) + (bArr[i47] & 255);
                        i47++;
                        i49 = i52;
                        i50 = i51;
                    }
                }
                byte[] bArr6 = new byte[i49];
                this.u = bArr6;
                System.arraycopy(bArr, i47, bArr6, 0, i49);
                int i53 = i47 + i49;
                int i54 = i53 + 1;
                int i55 = i53 + 2;
                int i56 = bArr[i54];
                int i57 = i56 & 255;
                if ((i56 & 128) != 0) {
                    int i58 = i56 & 127;
                    i57 = 0;
                    while (true) {
                        int i59 = i58 - 1;
                        if (i58 <= 0) {
                            break;
                        }
                        int i60 = (i57 << 8) + (bArr[i55] & 255);
                        i55++;
                        i57 = i60;
                        i58 = i59;
                    }
                }
                byte[] bArr7 = new byte[i57];
                this.v = bArr7;
                System.arraycopy(bArr, i55, bArr7, 0, i57);
                int i61 = i55 + i57;
                int i62 = i61 + 1;
                int i63 = i61 + 2;
                int i64 = bArr[i62];
                int i65 = i64 & 255;
                if ((i64 & 128) != 0) {
                    int i66 = i64 & 127;
                    i65 = 0;
                    while (true) {
                        int i67 = i66 - 1;
                        if (i66 <= 0) {
                            break;
                        }
                        int i68 = (i65 << 8) + (bArr[i63] & 255);
                        i63++;
                        i65 = i68;
                        i66 = i67;
                    }
                }
                byte[] bArr8 = new byte[i65];
                this.w = bArr8;
                System.arraycopy(bArr, i63, bArr8, 0, i65);
                int i69 = i63 + i65;
                int i70 = i69 + 1;
                int i71 = i69 + 2;
                int i72 = bArr[i70];
                int i73 = i72 & 255;
                if ((i72 & 128) != 0) {
                    int i74 = i72 & 127;
                    i73 = 0;
                    while (true) {
                        int i75 = i74 - 1;
                        if (i74 <= 0) {
                            break;
                        }
                        int i76 = (i73 << 8) + (bArr[i71] & 255);
                        i71++;
                        i73 = i76;
                        i74 = i75;
                    }
                }
                byte[] bArr9 = new byte[i73];
                this.x = bArr9;
                System.arraycopy(bArr, i71, bArr9, 0, i73);
                if (this.q != null) {
                    this.y = new BigInteger(this.q).bitLength();
                }
                return true;
            }
            Buffer buffer3 = new Buffer(bArr);
            buffer3.E(bArr.length);
            try {
                byte[][] bArrG = buffer3.g(4, HttpUrl.FRAGMENT_ENCODE_SET);
                this.s = bArrG[0];
                this.t = bArrG[1];
                this.u = bArrG[2];
                this.x = bArrG[3];
                J();
                K();
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
