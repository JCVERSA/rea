package com.jcraft.jsch;

import java.math.BigInteger;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
class KeyPairDSA extends KeyPair {
    private static final byte[] w = Util.v("-----BEGIN DSA PRIVATE KEY-----");
    private static final byte[] x = Util.v("-----END DSA PRIVATE KEY-----");
    private static final byte[] y = Util.v("ssh-dss");
    private byte[] q;
    private byte[] r;
    private byte[] s;
    private byte[] t;
    private byte[] u;
    private int v;

    KeyPairDSA(JSch.InstanceLogger instanceLogger) {
        this(instanceLogger, null, null, null, null, null);
    }

    KeyPairDSA(JSch.InstanceLogger instanceLogger, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5) {
        super(instanceLogger);
        this.v = 1024;
        this.q = bArr;
        this.r = bArr2;
        this.s = bArr3;
        this.t = bArr4;
        this.u = bArr5;
        if (bArr != null) {
            this.v = new BigInteger(bArr).bitLength();
        }
    }

    static KeyPair H(JSch.InstanceLogger instanceLogger, Buffer buffer) throws JSchException {
        byte[][] bArrG = buffer.g(7, "invalid key format");
        KeyPairDSA keyPairDSA = new KeyPairDSA(instanceLogger, bArrG[1], bArrG[2], bArrG[3], bArrG[4], bArrG[5]);
        keyPairDSA.b = Util.c(bArrG[6]);
        keyPairDSA.a = 0;
        return keyPairDSA;
    }

    @Override // com.jcraft.jsch.KeyPair
    public void f() {
        super.f();
        Util.g(this.u);
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] g() throws JSchException {
        if (r()) {
            throw new JSchException("key is encrypted.");
        }
        Buffer buffer = new Buffer();
        buffer.y(y);
        buffer.y(this.q);
        buffer.y(this.r);
        buffer.y(this.s);
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
        return y;
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] n() {
        int iC = c(1) + 3 + c(this.q.length) + this.q.length + 1 + c(this.r.length) + this.r.length + 1 + c(this.s.length) + this.s.length + 1 + c(this.t.length) + this.t.length + 1 + c(this.u.length) + this.u.length;
        byte[] bArr = new byte[c(iC) + 1 + iC];
        D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, D(bArr, G(bArr, 0, iC), new byte[1]), this.q), this.r), this.s), this.t), this.u);
        return bArr;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] o() {
        byte[] bArrO = super.o();
        if (bArrO != null) {
            return bArrO;
        }
        byte[] bArr = this.q;
        if (bArr == null) {
            return null;
        }
        return Buffer.b(new byte[][]{y, bArr, this.r, this.s, this.t}).b;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] p(byte[] bArr) {
        try {
            SignatureDSA signatureDSA = (SignatureDSA) Class.forName(JSch.f("signature.dss")).asSubclass(SignatureDSA.class).getDeclaredConstructor(null).newInstance(null);
            signatureDSA.a();
            signatureDSA.d(this.u, this.q, this.r, this.s);
            signatureDSA.e(bArr);
            return Buffer.b(new byte[][]{y, signatureDSA.j()}).b;
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
        try {
            int i2 = this.a;
            if (i2 == 1) {
                if (bArr[0] == 48) {
                    return false;
                }
                Buffer buffer = new Buffer(bArr);
                buffer.i();
                this.q = buffer.m();
                this.s = buffer.m();
                this.r = buffer.m();
                this.t = buffer.m();
                this.u = buffer.m();
                if (this.q != null) {
                    this.v = new BigInteger(this.q).bitLength();
                }
                return true;
            }
            if (i2 != 2 && i2 != 5) {
                if (i2 == 4) {
                    Buffer buffer2 = new Buffer(bArr);
                    if (buffer2.i() != buffer2.i()) {
                        throw new JSchException("check failed");
                    }
                    Util.c(buffer2.p());
                    this.q = buffer2.l();
                    this.r = buffer2.l();
                    this.s = buffer2.l();
                    this.t = buffer2.l();
                    this.u = buffer2.l();
                    this.b = Util.c(buffer2.p());
                    return true;
                }
                if (bArr[0] != 48) {
                    return false;
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
                if (this.q != null) {
                    this.v = new BigInteger(this.q).bitLength();
                }
                return true;
            }
            Buffer buffer3 = new Buffer(bArr);
            buffer3.E(bArr.length);
            try {
                this.u = buffer3.g(1, HttpUrl.FRAGMENT_ENCODE_SET)[0];
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
