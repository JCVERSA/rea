package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
public class HMACMD596 extends HMACMD5 {
    private final byte[] g = new byte[16];

    public HMACMD596() {
        this.a = "hmac-md5-96";
    }

    @Override // com.jcraft.jsch.jce.HMACMD5, com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public void c(byte[] bArr, int i) {
        super.c(this.g, 0);
        System.arraycopy(this.g, 0, bArr, i, 12);
    }

    @Override // com.jcraft.jsch.jce.HMACMD5, com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public int d() {
        return 12;
    }
}
