package com.jcraft.jsch.jce;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

/* JADX INFO: loaded from: classes3.dex */
public class DH implements com.jcraft.jsch.DH {
    BigInteger a;
    BigInteger b;
    BigInteger c;
    byte[] d;
    BigInteger e;
    private KeyPairGenerator f;
    private KeyAgreement g;

    @Override // com.jcraft.jsch.DH
    public void a() throws Exception {
        this.f = KeyPairGenerator.getInstance("DH");
        this.g = KeyAgreement.getInstance("DH");
    }

    @Override // com.jcraft.jsch.DH
    public byte[] b() throws Exception {
        if (this.c == null) {
            this.f.initialize(new DHParameterSpec(this.a, this.b));
            KeyPair keyPairGenerateKeyPair = this.f.generateKeyPair();
            this.g.init(keyPairGenerateKeyPair.getPrivate());
            BigInteger y = ((DHPublicKey) keyPairGenerateKeyPair.getPublic()).getY();
            this.c = y;
            this.d = y.toByteArray();
        }
        return this.d;
    }

    @Override // com.jcraft.jsch.DH
    public void c() throws Exception {
    }

    @Override // com.jcraft.jsch.DH
    public byte[] d() throws Exception {
        this.g.doPhase(KeyFactory.getInstance("DH").generatePublic(new DHPublicKeySpec(this.e, this.a, this.b)), true);
        return this.g.generateSecret();
    }

    @Override // com.jcraft.jsch.DH
    public void e(byte[] bArr) {
        j(new BigInteger(1, bArr));
    }

    @Override // com.jcraft.jsch.DH
    public void f(byte[] bArr) {
        i(new BigInteger(1, bArr));
    }

    @Override // com.jcraft.jsch.DH
    public void g(byte[] bArr) {
        h(new BigInteger(1, bArr));
    }

    void h(BigInteger bigInteger) {
        this.e = bigInteger;
    }

    void i(BigInteger bigInteger) {
        this.b = bigInteger;
    }

    void j(BigInteger bigInteger) {
        this.a = bigInteger;
    }
}
