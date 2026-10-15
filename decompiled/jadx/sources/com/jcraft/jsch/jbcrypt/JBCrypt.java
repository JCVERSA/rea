package com.jcraft.jsch.jbcrypt;

/* JADX INFO: loaded from: classes3.dex */
public class JBCrypt implements com.jcraft.jsch.BCrypt {
    private BCrypt a;
    private byte[] b;
    private int c;

    @Override // com.jcraft.jsch.BCrypt
    public void a(byte[] bArr, int i) throws Exception {
        this.a = new BCrypt();
        this.b = bArr;
        this.c = i;
    }

    @Override // com.jcraft.jsch.KDF
    public byte[] c(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        this.a.f(bArr, this.b, this.c, bArr2);
        return bArr2;
    }
}
