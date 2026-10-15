package com.jcraft.jsch.jce;

import com.jcraft.jsch.Buffer;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

/* JADX INFO: loaded from: classes3.dex */
abstract class SignatureRSAN implements com.jcraft.jsch.SignatureRSA {
    Signature a;
    KeyFactory b;

    SignatureRSAN() {
    }

    @Override // com.jcraft.jsch.Signature
    public void a() throws Exception {
        String str;
        String strK = k();
        if (strK.equals("rsa-sha2-256") || strK.equals("ssh-rsa-sha256@ssh.com")) {
            str = "SHA256withRSA";
        } else if (strK.equals("rsa-sha2-512") || strK.equals("ssh-rsa-sha512@ssh.com")) {
            str = "SHA512withRSA";
        } else if (strK.equals("ssh-rsa-sha384@ssh.com")) {
            str = "SHA384withRSA";
        } else {
            str = strK.equals("ssh-rsa-sha224@ssh.com") ? "SHA224withRSA" : "SHA1withRSA";
        }
        this.a = Signature.getInstance(str);
        this.b = KeyFactory.getInstance("RSA");
    }

    @Override // com.jcraft.jsch.SignatureRSA
    public void c(byte[] bArr, byte[] bArr2) throws Exception {
        this.a.initVerify(this.b.generatePublic(new RSAPublicKeySpec(new BigInteger(bArr2), new BigInteger(bArr))));
    }

    @Override // com.jcraft.jsch.Signature
    public void e(byte[] bArr) throws Exception {
        this.a.update(bArr);
    }

    @Override // com.jcraft.jsch.Signature
    public boolean f(byte[] bArr) throws Exception {
        Buffer buffer = new Buffer(bArr);
        String str = new String(buffer.p(), StandardCharsets.UTF_8);
        if (str.equals("ssh-rsa") || str.equals("rsa-sha2-256") || str.equals("rsa-sha2-512") || str.equals("ssh-rsa-sha224@ssh.com") || str.equals("ssh-rsa-sha256@ssh.com") || str.equals("ssh-rsa-sha384@ssh.com") || str.equals("ssh-rsa-sha512@ssh.com")) {
            if (!str.equals(k())) {
                return false;
            }
            int i = buffer.i();
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, buffer.n(), bArr2, 0, i);
            bArr = bArr2;
        }
        return this.a.verify(bArr);
    }

    @Override // com.jcraft.jsch.SignatureRSA
    public void g(byte[] bArr, byte[] bArr2) throws Exception {
        this.a.initSign(this.b.generatePrivate(new RSAPrivateKeySpec(new BigInteger(bArr2), new BigInteger(bArr))));
    }

    @Override // com.jcraft.jsch.Signature
    public byte[] j() throws Exception {
        return this.a.sign();
    }

    abstract String k();
}
