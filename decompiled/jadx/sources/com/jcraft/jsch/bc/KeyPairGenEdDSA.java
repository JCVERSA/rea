package com.jcraft.jsch.bc;

import ax.p289re.f;
import ax.p289re.h;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes3.dex */
public class KeyPairGenEdDSA implements com.jcraft.jsch.KeyPairGenEdDSA {
    byte[] a;
    byte[] b;
    int c;
    String d;

    @Override // com.jcraft.jsch.KeyPairGenEdDSA
    public void a(String str, byte[] bArr) throws Exception {
        if (!str.equals("Ed25519") && !str.equals("Ed448")) {
            throw new NoSuchAlgorithmException("invalid curve " + str);
        }
        this.d = str;
        if (str.equals("Ed25519")) {
            f fVar = new f(bArr);
            this.b = fVar.a().b();
            this.a = fVar.b();
        } else {
            h hVar = new h(bArr);
            this.b = hVar.a().b();
            this.a = hVar.b();
        }
        this.c = this.a.length;
    }

    @Override // com.jcraft.jsch.KeyPairGenEdDSA
    public byte[] b() {
        return this.b;
    }

    @Override // com.jcraft.jsch.KeyPairGenEdDSA
    public byte[] c() {
        return this.a;
    }
}
