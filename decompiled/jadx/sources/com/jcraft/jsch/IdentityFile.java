package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class IdentityFile implements Identity {
    private KeyPair a;
    private String b;

    private IdentityFile(String str, KeyPair keyPair) {
        this.b = str;
        this.a = keyPair;
    }

    static IdentityFile h(String str, String str2, JSch.InstanceLogger instanceLogger) throws JSchException {
        return new IdentityFile(str, KeyPair.t(instanceLogger, str, str2));
    }

    static IdentityFile i(String str, byte[] bArr, byte[] bArr2, JSch.InstanceLogger instanceLogger) throws JSchException {
        return new IdentityFile(str, KeyPair.u(instanceLogger, bArr, bArr2));
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] a(byte[] bArr, String str) {
        return this.a.q(bArr, str);
    }

    @Override // com.jcraft.jsch.Identity
    public String b() {
        return this.a.m();
    }

    @Override // com.jcraft.jsch.Identity
    public boolean c() {
        return this.a.r();
    }

    @Override // com.jcraft.jsch.Identity
    public void clear() {
        this.a.f();
        this.a = null;
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] d() {
        return this.a.o();
    }

    @Override // com.jcraft.jsch.Identity
    public boolean e(byte[] bArr) throws JSchException {
        return this.a.d(bArr);
    }

    @Override // com.jcraft.jsch.Identity
    public byte[] f(byte[] bArr) {
        return this.a.p(bArr);
    }

    public KeyPair g() {
        return this.a;
    }

    @Override // com.jcraft.jsch.Identity
    public String getName() {
        return this.b;
    }
}
