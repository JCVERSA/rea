package com.jcraft.jsch.jce;

/* JADX INFO: loaded from: classes3.dex */
public class SignatureRSASHA512 extends SignatureRSAN {
    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.Signature
    public /* bridge */ /* synthetic */ void a() throws Exception {
        super.a();
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.SignatureRSA
    public /* bridge */ /* synthetic */ void c(byte[] bArr, byte[] bArr2) throws Exception {
        super.c(bArr, bArr2);
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.Signature
    public /* bridge */ /* synthetic */ void e(byte[] bArr) throws Exception {
        super.e(bArr);
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.Signature
    public /* bridge */ /* synthetic */ boolean f(byte[] bArr) throws Exception {
        return super.f(bArr);
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.SignatureRSA
    public /* bridge */ /* synthetic */ void g(byte[] bArr, byte[] bArr2) throws Exception {
        super.g(bArr, bArr2);
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN, com.jcraft.jsch.Signature
    public /* bridge */ /* synthetic */ byte[] j() throws Exception {
        return super.j();
    }

    @Override // com.jcraft.jsch.jce.SignatureRSAN
    String k() {
        return "rsa-sha2-512";
    }
}
