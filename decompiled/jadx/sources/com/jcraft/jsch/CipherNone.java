package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class CipherNone implements Cipher {
    CipherNone() {
    }

    @Override // com.jcraft.jsch.Cipher
    public int d() {
        return 16;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void e(int i) throws Exception {
        ax.p093fa.a.e(this, i);
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void f(byte[] bArr, int i, int i2) throws Exception {
        ax.p093fa.a.f(this, bArr, i, i2);
    }

    @Override // com.jcraft.jsch.Cipher
    public void g(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ int h() {
        return ax.p093fa.a.b(this);
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void i(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        ax.p093fa.a.a(this, bArr, i, i2, bArr2, i3);
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean j() {
        return false;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ boolean k() {
        return ax.p093fa.a.d(this);
    }

    @Override // com.jcraft.jsch.Cipher
    public void l(int i, byte[] bArr, byte[] bArr2) throws Exception {
    }

    @Override // com.jcraft.jsch.Cipher
    public int m() {
        return 8;
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ boolean n() {
        return ax.p093fa.a.c(this);
    }
}
