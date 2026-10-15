package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
public class PBKDF2HMACSHA256 extends PBKDF2 {
    @Override // com.jcraft.jsch.jce.PBKDF2, com.jcraft.jsch.PBKDF2
    public /* bridge */ /* synthetic */ void a(byte[] bArr, int i) throws Exception {
        super.a(bArr, i);
    }

    @Override // com.jcraft.jsch.jce.PBKDF2, com.jcraft.jsch.KDF
    public /* bridge */ /* synthetic */ byte[] c(byte[] bArr, int i) {
        return super.c(bArr, i);
    }

    @Override // com.jcraft.jsch.jce.PBKDF2
    String e() {
        return "PBKDF2WithHmacSHA256";
    }
}
