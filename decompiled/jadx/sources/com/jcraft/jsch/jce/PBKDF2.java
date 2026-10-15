package com.jcraft.jsch.jce;

import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* JADX INFO: loaded from: classes3.dex */
abstract class PBKDF2 implements com.jcraft.jsch.PBKDF2 {
    private SecretKeyFactory a;
    private byte[] b;
    private int c;

    PBKDF2() {
    }

    @Override // com.jcraft.jsch.PBKDF2
    public void a(byte[] bArr, int i) throws Exception {
        this.a = SecretKeyFactory.getInstance(e());
        this.b = bArr;
        this.c = i;
    }

    @Override // com.jcraft.jsch.KDF
    public byte[] c(byte[] bArr, int i) {
        char[] cArr = new char[bArr.length];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            cArr[i2] = (char) (bArr[i2] & 255);
        }
        try {
            return this.a.generateSecret(new PBEKeySpec(cArr, this.b, this.c, i * 8)).getEncoded();
        } catch (InvalidKeySpecException unused) {
            return null;
        }
    }

    abstract String e();
}
