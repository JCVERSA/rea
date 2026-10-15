package com.jcraft.jsch.jce;

import com.jcraft.jsch.Buffer;
import com.jcraft.jsch.SignatureECDSA;
import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;

/* JADX INFO: loaded from: classes3.dex */
abstract class SignatureECDSAN implements SignatureECDSA {
    Signature a;
    KeyFactory b;

    SignatureECDSAN() {
    }

    private static byte[] k(byte[] bArr) {
        if (bArr[0] != 0) {
            return bArr;
        }
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        Util.a(bArr);
        return bArr2;
    }

    private static byte[] m(byte[] bArr) {
        if ((bArr[0] & 128) == 0) {
            return bArr;
        }
        byte[] bArr2 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        Util.a(bArr);
        return bArr2;
    }

    private static byte[] n(byte[] bArr) {
        if (bArr.length >= 2) {
            int i = 0;
            while (i < bArr.length - 1 && bArr[i] == 0) {
                int i2 = i + 1;
                if ((bArr[i2] & 128) != 0) {
                    break;
                }
                i = i2;
            }
            if (i != 0) {
                int length = bArr.length - i;
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, i, bArr2, 0, length);
                Util.a(bArr);
                return bArr2;
            }
        }
        return bArr;
    }

    @Override // com.jcraft.jsch.Signature
    public void a() throws Exception {
        String str;
        String strL = l();
        if (strL.equals("ecdsa-sha2-nistp384")) {
            str = "SHA384withECDSA";
        } else {
            str = strL.equals("ecdsa-sha2-nistp521") ? "SHA512withECDSA" : "SHA256withECDSA";
        }
        this.a = Signature.getInstance(str);
        this.b = KeyFactory.getInstance("EC");
    }

    @Override // com.jcraft.jsch.SignatureECDSA
    public void b(byte[] bArr) throws Exception {
        String str;
        byte[] bArrM = m(bArr);
        if (bArrM.length >= 64) {
            str = "secp521r1";
        } else {
            str = bArrM.length >= 48 ? "secp384r1" : "secp256r1";
        }
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("EC");
        algorithmParameters.init(new ECGenParameterSpec(str));
        this.a.initSign(this.b.generatePrivate(new ECPrivateKeySpec(new BigInteger(1, bArrM), (ECParameterSpec) algorithmParameters.getParameterSpec(ECParameterSpec.class))));
    }

    @Override // com.jcraft.jsch.SignatureECDSA
    public void c(byte[] bArr, byte[] bArr2) throws Exception {
        String str;
        byte[] bArrM = m(bArr);
        byte[] bArrM2 = m(bArr2);
        if (bArrM.length >= 64) {
            str = "secp521r1";
        } else {
            str = bArrM.length >= 48 ? "secp384r1" : "secp256r1";
        }
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("EC");
        algorithmParameters.init(new ECGenParameterSpec(str));
        this.a.initVerify(this.b.generatePublic(new ECPublicKeySpec(new ECPoint(new BigInteger(1, bArrM), new BigInteger(1, bArrM2)), (ECParameterSpec) algorithmParameters.getParameterSpec(ECParameterSpec.class))));
    }

    @Override // com.jcraft.jsch.Signature
    public void e(byte[] bArr) throws Exception {
        this.a.update(bArr);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001d  */
    /* JADX WARN: Code duplicated, block: B:12:0x0047  */
    /* JADX WARN: Code duplicated, block: B:14:0x0074  */
    @Override // com.jcraft.jsch.Signature
    public boolean f(byte[] bArr) throws Exception {
        byte[] bArrN;
        byte[] bArrN2;
        byte[] bArr2;
        if (bArr[0] == 48) {
            byte b = bArr[1];
            if (b + 2 != bArr.length && ((b & 128) == 0 || (bArr[2] & 255) + 3 != bArr.length)) {
                Buffer buffer = new Buffer(bArr);
                buffer.p();
                buffer.i();
                byte[] bArrL = buffer.l();
                byte[] bArrL2 = buffer.l();
                bArrN = n(m(bArrL));
                bArrN2 = n(m(bArrL2));
                if (bArrN.length < 64) {
                    bArr2 = new byte[bArrN.length + 6 + bArrN2.length];
                    bArr2[0] = 48;
                    bArr2[1] = (byte) (bArrN.length + 4 + bArrN2.length);
                    bArr2[2] = 2;
                    bArr2[3] = (byte) bArrN.length;
                    System.arraycopy(bArrN, 0, bArr2, 4, bArrN.length);
                    bArr2[bArrN.length + 4] = 2;
                    bArr2[bArrN.length + 5] = (byte) bArrN2.length;
                    System.arraycopy(bArrN2, 0, bArr2, bArrN.length + 6, bArrN2.length);
                } else {
                    bArr2 = new byte[bArrN.length + 6 + bArrN2.length + 1];
                    bArr2[0] = 48;
                    bArr2[1] = -127;
                    bArr2[2] = (byte) (bArrN.length + 4 + bArrN2.length);
                    bArr2[3] = 2;
                    bArr2[4] = (byte) bArrN.length;
                    System.arraycopy(bArrN, 0, bArr2, 5, bArrN.length);
                    bArr2[bArrN.length + 5] = 2;
                    bArr2[bArrN.length + 6] = (byte) bArrN2.length;
                    System.arraycopy(bArrN2, 0, bArr2, bArrN.length + 7, bArrN2.length);
                }
                bArr = bArr2;
            }
        } else {
            Buffer buffer2 = new Buffer(bArr);
            buffer2.p();
            buffer2.i();
            byte[] bArrL3 = buffer2.l();
            byte[] bArrL4 = buffer2.l();
            bArrN = n(m(bArrL3));
            bArrN2 = n(m(bArrL4));
            if (bArrN.length < 64) {
                bArr2 = new byte[bArrN.length + 6 + bArrN2.length];
                bArr2[0] = 48;
                bArr2[1] = (byte) (bArrN.length + 4 + bArrN2.length);
                bArr2[2] = 2;
                bArr2[3] = (byte) bArrN.length;
                System.arraycopy(bArrN, 0, bArr2, 4, bArrN.length);
                bArr2[bArrN.length + 4] = 2;
                bArr2[bArrN.length + 5] = (byte) bArrN2.length;
                System.arraycopy(bArrN2, 0, bArr2, bArrN.length + 6, bArrN2.length);
            } else {
                bArr2 = new byte[bArrN.length + 6 + bArrN2.length + 1];
                bArr2[0] = 48;
                bArr2[1] = -127;
                bArr2[2] = (byte) (bArrN.length + 4 + bArrN2.length);
                bArr2[3] = 2;
                bArr2[4] = (byte) bArrN.length;
                System.arraycopy(bArrN, 0, bArr2, 5, bArrN.length);
                bArr2[bArrN.length + 5] = 2;
                bArr2[bArrN.length + 6] = (byte) bArrN2.length;
                System.arraycopy(bArrN2, 0, bArr2, bArrN.length + 7, bArrN2.length);
            }
            bArr = bArr2;
        }
        return this.a.verify(bArr);
    }

    @Override // com.jcraft.jsch.Signature
    public byte[] j() throws Exception {
        byte[] bArrSign = this.a.sign();
        if (bArrSign[0] != '0') {
            return bArrSign;
        }
        int i = bArrSign[1];
        int i2 = 3;
        if (i + 2 != bArrSign.length && ((i & 128) == 0 || (bArrSign[2] & 255) + 3 != bArrSign.length)) {
            return bArrSign;
        }
        if ((i & 128) != 0 && (bArrSign[2] & 255) + 3 == bArrSign.length) {
            i2 = 4;
        }
        int i3 = bArrSign[i2];
        byte[] bArr = new byte[i3];
        int i4 = bArrSign[i2 + 2 + i3];
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArrSign, i2 + 1, bArr, 0, i3);
        System.arraycopy(bArrSign, i2 + 3 + bArrSign[i2], bArr2, 0, i4);
        byte[] bArrK = k(bArr);
        byte[] bArrK2 = k(bArr2);
        Buffer buffer = new Buffer();
        buffer.x(bArrK);
        buffer.x(bArrK2);
        byte[] bArr3 = new byte[buffer.j()];
        buffer.C(0);
        buffer.e(bArr3);
        return bArr3;
    }

    abstract String l();
}
