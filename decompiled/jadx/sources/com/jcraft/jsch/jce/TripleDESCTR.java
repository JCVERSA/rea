package com.jcraft.jsch.jce;

import ax.p093fa.a;
import com.jcraft.jsch.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes3.dex */
public class TripleDESCTR implements Cipher {
    private javax.crypto.Cipher a;

    @Override // com.jcraft.jsch.Cipher
    public int d() {
        return 24;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void e(int i) throws Exception {
        a.e(this, i);
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void f(byte[] bArr, int i, int i2) throws Exception {
        a.f(this, bArr, i, i2);
    }

    @Override // com.jcraft.jsch.Cipher
    public void g(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        this.a.update(bArr, i, i2, bArr2, i3);
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ int h() {
        return a.b(this);
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void i(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        a.a(this, bArr, i, i2, bArr2, i3);
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean j() {
        return false;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ boolean k() {
        return a.d(this);
    }

    @Override // com.jcraft.jsch.Cipher
    public void l(int i, byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr2.length > 8) {
            byte[] bArr3 = new byte[8];
            System.arraycopy(bArr2, 0, bArr3, 0, 8);
            bArr2 = bArr3;
        }
        if (bArr.length > 24) {
            byte[] bArr4 = new byte[24];
            System.arraycopy(bArr, 0, bArr4, 0, 24);
            bArr = bArr4;
        }
        try {
            this.a = javax.crypto.Cipher.getInstance("DESede/CTR/NoPadding");
            this.a.init(i == 0 ? 1 : 2, SecretKeyFactory.getInstance("DESede").generateSecret(new DESedeKeySpec(bArr)), new IvParameterSpec(bArr2));
        } catch (Exception e) {
            this.a = null;
            throw e;
        }
    }

    @Override // com.jcraft.jsch.Cipher
    public int m() {
        return 8;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ boolean n() {
        return a.c(this);
    }
}
