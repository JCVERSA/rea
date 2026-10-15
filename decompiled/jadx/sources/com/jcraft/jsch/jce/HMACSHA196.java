package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
public class HMACSHA196 extends HMACSHA1 {
    private final byte[] g = new byte[20];

    public HMACSHA196() {
        this.a = "hmac-sha1-96";
    }

    @Override // com.jcraft.jsch.jce.HMACSHA1, com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public void c(byte[] bArr, int i) {
        super.c(this.g, 0);
        System.arraycopy(this.g, 0, bArr, i, 12);
    }

    @Override // com.jcraft.jsch.jce.HMACSHA1, com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public int d() {
        return 12;
    }
}
