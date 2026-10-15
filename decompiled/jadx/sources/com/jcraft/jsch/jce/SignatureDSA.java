package com.jcraft.jsch.jce;

import com.jcraft.jsch.Buffer;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.DSAPrivateKeySpec;
import java.security.spec.DSAPublicKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class SignatureDSA implements com.jcraft.jsch.SignatureDSA {
    Signature a;
    KeyFactory b;

    @Override // com.jcraft.jsch.Signature
    public void a() throws Exception {
        this.a = Signature.getInstance("SHA1withDSA");
        this.b = KeyFactory.getInstance("DSA");
    }

    @Override // com.jcraft.jsch.SignatureDSA
    public void d(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception {
        this.a.initSign(this.b.generatePrivate(new DSAPrivateKeySpec(new BigInteger(bArr), new BigInteger(bArr2), new BigInteger(bArr3), new BigInteger(bArr4))));
    }

    @Override // com.jcraft.jsch.Signature
    public void e(byte[] bArr) throws Exception {
        this.a.update(bArr);
    }

    @Override // com.jcraft.jsch.Signature
    public boolean f(byte[] bArr) throws Exception {
        Buffer buffer = new Buffer(bArr);
        if (new String(buffer.p(), StandardCharsets.UTF_8).equals("ssh-dss")) {
            int i = buffer.i();
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, buffer.n(), bArr2, 0, i);
            bArr = bArr2;
        }
        byte[] bArr3 = new byte[20];
        System.arraycopy(bArr, 0, bArr3, 0, 20);
        byte[] bArrK = k(bArr3);
        byte[] bArr4 = new byte[20];
        System.arraycopy(bArr, 20, bArr4, 0, 20);
        byte[] bArrK2 = k(bArr4);
        int i2 = (bArrK[0] & 128) != 0 ? 1 : 0;
        int i3 = (bArrK2[0] & 128) != 0 ? 1 : 0;
        byte[] bArr5 = new byte[bArrK.length + bArrK2.length + 6 + i2 + i3];
        bArr5[0] = 48;
        byte length = (byte) (bArrK.length + bArrK2.length + 4);
        bArr5[1] = length;
        byte b = (byte) i2;
        byte b2 = (byte) (length + b);
        bArr5[1] = b2;
        byte b3 = (byte) i3;
        bArr5[1] = (byte) (b2 + b3);
        bArr5[2] = 2;
        byte length2 = (byte) bArrK.length;
        bArr5[3] = length2;
        bArr5[3] = (byte) (length2 + b);
        System.arraycopy(bArrK, 0, bArr5, i2 + 4, bArrK.length);
        bArr5[bArr5[3] + 4] = 2;
        bArr5[bArr5[3] + 5] = (byte) bArrK2.length;
        int i4 = bArr5[3] + 5;
        bArr5[i4] = (byte) (bArr5[i4] + b3);
        System.arraycopy(bArrK2, 0, bArr5, bArr5[3] + 6 + i3, bArrK2.length);
        return this.a.verify(bArr5);
    }

    @Override // com.jcraft.jsch.SignatureDSA
    public void h(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) throws Exception {
        this.a.initVerify(this.b.generatePublic(new DSAPublicKeySpec(new BigInteger(bArr), new BigInteger(bArr2), new BigInteger(bArr3), new BigInteger(bArr4))));
    }

    @Override // com.jcraft.jsch.Signature
    public byte[] j() throws Exception {
        byte[] bArrSign = this.a.sign();
        int i = bArrSign[3] & 255;
        byte[] bArr = new byte[i];
        System.arraycopy(bArrSign, 4, bArr, 0, i);
        int i2 = bArrSign[i + 5] & 255;
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArrSign, i + 6, bArr2, 0, i2);
        byte[] bArr3 = new byte[40];
        int i3 = i > 20 ? 1 : 0;
        int i4 = i > 20 ? 0 : 20 - i;
        if (i > 20) {
            i = 20;
        }
        System.arraycopy(bArr, i3, bArr3, i4, i);
        int i5 = i2 > 20 ? 1 : 0;
        int i6 = i2 > 20 ? 20 : 40 - i2;
        if (i2 > 20) {
            i2 = 20;
        }
        System.arraycopy(bArr2, i5, bArr3, i6, i2);
        return bArr3;
    }

    protected byte[] k(byte[] bArr) {
        if (bArr.length <= 1 || bArr[0] != 0 || (bArr[1] & 128) != 0) {
            return bArr;
        }
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 1, bArr2, 0, length);
        return k(bArr2);
    }
}
