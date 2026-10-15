package com.jcraft.jsch.bc;

import ax.p161je.r;
import ax.p289re.f;
import ax.p289re.g;
import ax.p289re.h;
import ax.p289re.i;
import ax.p305se.a;
import ax.p305se.b;
import com.jcraft.jsch.Buffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

/* JADX INFO: loaded from: classes3.dex */
abstract class SignatureEdDSA implements com.jcraft.jsch.SignatureEdDSA {
    r a;

    SignatureEdDSA() {
    }

    @Override // com.jcraft.jsch.Signature
    public void a() throws Exception {
        if (!k().equals("Ed25519") && !k().equals("Ed448")) {
            throw new NoSuchAlgorithmException("invalid curve " + k());
        }
        if (k().equals("Ed25519")) {
            this.a = new a();
        } else {
            this.a = new b(new byte[0]);
        }
    }

    @Override // com.jcraft.jsch.SignatureEdDSA
    public void b(byte[] bArr) throws Exception {
        try {
            if (k().equals("Ed25519")) {
                this.a.e(true, new f(bArr, 0));
            } else {
                this.a.e(true, new h(bArr, 0));
            }
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override // com.jcraft.jsch.Signature
    public void e(byte[] bArr) throws Exception {
        try {
            this.a.b(bArr, 0, bArr.length);
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    @Override // com.jcraft.jsch.Signature
    public boolean f(byte[] bArr) throws Exception {
        Buffer buffer = new Buffer(bArr);
        if (new String(buffer.p(), StandardCharsets.UTF_8).equals(l())) {
            int i = buffer.i();
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, buffer.n(), bArr2, 0, i);
            bArr = bArr2;
        }
        try {
            return this.a.a(bArr);
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    @Override // com.jcraft.jsch.SignatureEdDSA
    public void i(byte[] bArr) throws Exception {
        try {
            if (k().equals("Ed25519")) {
                this.a.e(false, new g(bArr, 0));
            } else {
                this.a.e(false, new i(bArr, 0));
            }
        } catch (Exception e) {
            throw new InvalidKeyException(e);
        }
    }

    @Override // com.jcraft.jsch.Signature
    public byte[] j() throws Exception {
        try {
            return this.a.c();
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    abstract String k();

    abstract String l();
}
