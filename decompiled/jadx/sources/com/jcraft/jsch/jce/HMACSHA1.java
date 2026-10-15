package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
public class HMACSHA1 extends HMAC {
    public HMACSHA1() {
        this.a = "hmac-sha1";
        this.b = 20;
        this.c = "HmacSHA1";
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ void a(byte[] bArr) throws Exception {
        super.a(bArr);
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ void b(byte[] bArr, int i, int i2) {
        super.b(bArr, i, i2);
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ void c(byte[] bArr, int i) {
        super.c(bArr, i);
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ int d() {
        return super.d();
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ void e(int i) {
        super.e(i);
    }

    @Override // com.jcraft.jsch.jce.HMAC, com.jcraft.jsch.MAC
    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }
}
