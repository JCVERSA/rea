package com.jcraft.jsch.jce;

import com.jcraft.jsch.JSchException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;

/* JADX INFO: loaded from: classes3.dex */
public class KeyPairGenECDSA implements com.jcraft.jsch.KeyPairGenECDSA {
    byte[] a;
    byte[] b;
    byte[] c;
    ECPublicKey d;
    ECPrivateKey e;
    ECParameterSpec f;

    private byte[] f(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        Util.a(bArr);
        return bArr2;
    }

    ECPrivateKey a() {
        return this.e;
    }

    ECPublicKey b() {
        return this.d;
    }

    public byte[] c() {
        return this.b;
    }

    public byte[] d() {
        return this.c;
    }

    public void e(int i) throws Exception {
        String str;
        if (i == 256) {
            str = "secp256r1";
        } else if (i == 384) {
            str = "secp384r1";
        } else {
            if (i != 521) {
                throw new JSchException("unsupported key size: " + i);
            }
            str = "secp521r1";
        }
        for (int i2 = 0; i2 < 1000; i2++) {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            keyPairGenerator.initialize(new ECGenParameterSpec(str));
            KeyPair keyPairGenKeyPair = keyPairGenerator.genKeyPair();
            this.e = (ECPrivateKey) keyPairGenKeyPair.getPrivate();
            ECPublicKey eCPublicKey = (ECPublicKey) keyPairGenKeyPair.getPublic();
            this.d = eCPublicKey;
            this.f = eCPublicKey.getParams();
            this.a = this.e.getS().toByteArray();
            ECPoint w = this.d.getW();
            this.b = w.getAffineX().toByteArray();
            byte[] byteArray = w.getAffineY().toByteArray();
            this.c = byteArray;
            byte[] bArr = this.b;
            if (bArr.length == byteArray.length && ((i == 256 && bArr.length == 32) || ((i == 384 && bArr.length == 48) || (i == 521 && bArr.length == 66)))) {
                break;
            }
        }
        byte[] bArr2 = this.a;
        if (bArr2.length < this.b.length) {
            this.a = f(bArr2);
        }
    }
}
