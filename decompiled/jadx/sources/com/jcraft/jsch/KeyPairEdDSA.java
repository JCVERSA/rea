package com.jcraft.jsch;

import java.util.Arrays;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
abstract class KeyPairEdDSA extends KeyPair {
    private byte[] q;
    private byte[] r;

    KeyPairEdDSA(JSch.InstanceLogger instanceLogger, byte[] bArr, byte[] bArr2) {
        super(instanceLogger);
        this.q = bArr;
        this.r = bArr2;
    }

    abstract String H();

    abstract String I();

    @Override // com.jcraft.jsch.KeyPair
    public void f() {
        super.f();
        Util.g(this.r);
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] g() throws JSchException {
        if (r()) {
            throw new JSchException("key is encrypted.");
        }
        Buffer buffer = new Buffer();
        buffer.y(l());
        buffer.y(this.q);
        byte[] bArr = this.r;
        byte[] bArr2 = new byte[bArr.length + this.q.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte[] bArr3 = this.q;
        System.arraycopy(bArr3, 0, bArr2, this.r.length, bArr3.length);
        buffer.y(bArr2);
        buffer.y(Util.v(this.b));
        int iJ = buffer.j();
        byte[] bArr4 = new byte[iJ];
        buffer.f(bArr4, 0, iJ);
        return bArr4;
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] l() {
        return Util.v(I());
    }

    @Override // com.jcraft.jsch.KeyPair
    byte[] n() {
        throw new UnsupportedOperationException();
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] o() {
        byte[] bArrO = super.o();
        if (bArrO != null) {
            return bArrO;
        }
        if (this.q == null) {
            return null;
        }
        return Buffer.b(new byte[][]{l(), this.q}).b;
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] p(byte[] bArr) {
        return q(bArr, I());
    }

    @Override // com.jcraft.jsch.KeyPair
    public byte[] q(byte[] bArr, String str) {
        try {
            SignatureEdDSA signatureEdDSA = (SignatureEdDSA) Class.forName(JSch.f(str)).asSubclass(SignatureEdDSA.class).getDeclaredConstructor(null).newInstance(null);
            signatureEdDSA.a();
            signatureEdDSA.b(this.r);
            signatureEdDSA.e(bArr);
            return Buffer.b(new byte[][]{Util.v(str), signatureEdDSA.j()}).b;
        } catch (Exception | NoClassDefFoundError e) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to generate signature", e);
            }
            return null;
        }
    }

    @Override // com.jcraft.jsch.KeyPair
    boolean y(byte[] bArr) {
        int i = this.a;
        if (i == 2 || i == 5) {
            Buffer buffer = new Buffer(bArr);
            buffer.E(bArr.length);
            try {
                this.r = buffer.g(1, HttpUrl.FRAGMENT_ENCODE_SET)[0];
                return true;
            } catch (JSchException e) {
                if (this.c.a().isEnabled(3)) {
                    this.c.a().b(3, "failed to parse key", e);
                }
                return false;
            }
        }
        if (i == 4) {
            try {
                Buffer buffer2 = new Buffer(bArr);
                if (buffer2.i() != buffer2.i()) {
                    throw new JSchException("check failed");
                }
                Util.c(buffer2.p());
                this.q = buffer2.p();
                this.r = Arrays.copyOf(buffer2.p(), k());
                this.b = Util.c(buffer2.p());
                return true;
            } catch (Exception e2) {
                if (this.c.a().isEnabled(3)) {
                    this.c.a().b(3, "failed to parse key", e2);
                }
                return false;
            }
        }
        if (i != 3) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().a(3, "failed to parse key");
            }
            return false;
        }
        try {
            KeyPairGenEdDSA keyPairGenEdDSA = (KeyPairGenEdDSA) Class.forName(JSch.f("keypairgen_fromprivate.eddsa")).asSubclass(KeyPairGenEdDSA.class).getDeclaredConstructor(null).newInstance(null);
            keyPairGenEdDSA.a(H(), bArr);
            this.q = keyPairGenEdDSA.b();
            this.r = keyPairGenEdDSA.c();
            return true;
        } catch (Exception | NoClassDefFoundError e3) {
            if (this.c.a().isEnabled(3)) {
                this.c.a().b(3, "failed to parse key", e3);
            }
            return false;
        }
    }
}
