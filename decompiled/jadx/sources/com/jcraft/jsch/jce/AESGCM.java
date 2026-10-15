package com.jcraft.jsch.jce;

import ax.p093fa.a;
import com.jcraft.jsch.Cipher;
import java.nio.ByteBuffer;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
abstract class AESGCM implements Cipher {
    private javax.crypto.Cipher a;
    private SecretKeySpec b;
    private int c;
    private ByteBuffer d;
    private long e;

    AESGCM() {
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void e(int i) throws Exception {
        a.e(this, i);
    }

    @Override // com.jcraft.jsch.Cipher
    public void f(byte[] bArr, int i, int i2) throws Exception {
        this.a.updateAAD(bArr, i, i2);
    }

    @Override // com.jcraft.jsch.Cipher
    public void g(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        this.a.update(bArr, i, i2, bArr2, i3);
    }

    @Override // com.jcraft.jsch.Cipher
    public int h() {
        return 16;
    }

    @Override // com.jcraft.jsch.Cipher
    public void i(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        this.a.doFinal(bArr, i, i2, bArr2, i3);
        long j = this.d.getLong(4) + 1;
        if (j == this.e) {
            throw new IllegalStateException("GCM IV would be reused");
        }
        this.d.putLong(4, j);
        this.a.init(this.c, this.b, new GCMParameterSpec(128, this.d.array()));
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
        if (bArr2.length > 12) {
            byte[] bArr3 = new byte[12];
            System.arraycopy(bArr2, 0, bArr3, 0, 12);
            bArr2 = bArr3;
        }
        int iD = d();
        if (bArr.length > iD) {
            byte[] bArr4 = new byte[iD];
            System.arraycopy(bArr, 0, bArr4, 0, iD);
            bArr = bArr4;
        }
        this.c = i == 0 ? 1 : 2;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr2);
        this.d = byteBufferWrap;
        this.e = byteBufferWrap.getLong(4);
        try {
            this.b = new SecretKeySpec(bArr, "AES");
            javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding");
            this.a = cipher;
            cipher.init(this.c, this.b, new GCMParameterSpec(128, bArr2));
        } catch (Exception e) {
            this.a = null;
            this.b = null;
            this.d = null;
            throw e;
        }
    }

    @Override // com.jcraft.jsch.Cipher
    public int m() {
        return 16;
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean n() {
        return true;
    }
}
