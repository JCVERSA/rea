package com.jcraft.jsch.bc;

import ax.p289re.m;
import ax.p289re.n;
import ax.p289re.o;
import ax.p289re.p;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/* JADX INFO: loaded from: classes3.dex */
public class XDH implements com.jcraft.jsch.XDH {
    byte[] a;
    Object b;
    int c;
    String d;

    @Override // com.jcraft.jsch.XDH
    public byte[] a() throws Exception {
        return this.a;
    }

    @Override // com.jcraft.jsch.XDH
    public byte[] b(byte[] bArr) throws Exception {
        byte[] bArr2 = new byte[this.c];
        if (this.d.equals("X25519")) {
            try {
                try {
                    ((m) this.b).b(new n(bArr, 0), bArr2, 0);
                    return bArr2;
                } catch (Exception e) {
                    throw new IllegalStateException(e);
                }
            } catch (Exception e2) {
                throw new InvalidKeyException(e2);
            }
        }
        try {
            try {
                ((o) this.b).b(new p(bArr, 0), bArr2, 0);
                return bArr2;
            } catch (Exception e3) {
                throw new IllegalStateException(e3);
            }
        } catch (Exception e4) {
            throw new InvalidKeyException(e4);
        }
    }

    @Override // com.jcraft.jsch.XDH
    public boolean c(byte[] bArr) throws Exception {
        return bArr.length == this.c;
    }

    @Override // com.jcraft.jsch.XDH
    public void d(String str, int i) throws Exception {
        if (!str.equals("X25519") && !str.equals("X448")) {
            throw new NoSuchAlgorithmException("invalid curve " + str);
        }
        this.c = i;
        this.d = str;
        if (str.equals("X25519")) {
            m mVar = new m(new SecureRandom());
            this.a = mVar.a().b();
            this.b = mVar;
        } else {
            o oVar = new o(new SecureRandom());
            this.a = oVar.a().b();
            this.b = oVar;
        }
    }
}
