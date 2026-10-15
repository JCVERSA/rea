package com.jcraft.jsch;

import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
class AgentProxy {
    private final Buffer a;
    private AgentConnector b;

    synchronized boolean a(byte[] bArr) {
        int length = bArr.length;
        this.a.A();
        this.a.a(length + 5);
        this.a.v(length + 1);
        this.a.s((byte) 17);
        this.a.t(bArr);
        try {
            this.b.a(this.a);
        } catch (AgentProxyException unused) {
            this.a.B();
            this.a.s((byte) 5);
        }
        return this.a.c() == 6;
    }

    synchronized Vector<Identity> b() {
        Vector<Identity> vector = new Vector<>();
        this.a.A();
        this.a.a(5);
        this.a.v(1);
        this.a.s((byte) 11);
        try {
            this.b.a(this.a);
            if (this.a.c() != 12) {
                return vector;
            }
            int i = this.a.i();
            if (i > 0 && i <= 2048) {
                for (int i2 = 0; i2 < i; i2++) {
                    vector.add(new AgentIdentity(this, this.a.p(), Util.c(this.a.p())));
                }
                return vector;
            }
            return vector;
        } catch (AgentProxyException unused) {
            this.a.B();
            this.a.s((byte) 5);
            return vector;
        }
    }

    synchronized void c() {
        this.a.A();
        this.a.a(5);
        this.a.v(1);
        this.a.s((byte) 19);
        try {
            this.b.a(this.a);
        } catch (AgentProxyException unused) {
            this.a.B();
            this.a.s((byte) 5);
        }
    }

    synchronized boolean d(byte[] bArr) {
        int length = bArr.length;
        this.a.A();
        this.a.a(length + 9);
        this.a.v(length + 5);
        this.a.s((byte) 18);
        this.a.y(bArr);
        try {
            this.b.a(this.a);
        } catch (AgentProxyException unused) {
            this.a.B();
            this.a.s((byte) 5);
        }
        return this.a.c() == 6;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x001a  */
    synchronized byte[] e(byte[] bArr, byte[] bArr2, String str) {
        int i;
        if (str != null) {
            try {
                if (str.equals("rsa-sha2-256")) {
                    i = 2;
                } else if (str.equals("rsa-sha2-512")) {
                    i = 4;
                } else {
                    i = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        } else {
            i = 0;
        }
        int length = bArr.length + 17 + bArr2.length;
        this.a.A();
        this.a.a(length);
        this.a.v(length - 4);
        this.a.s((byte) 13);
        this.a.y(bArr);
        this.a.y(bArr2);
        this.a.v(i);
        try {
            this.b.a(this.a);
        } catch (AgentProxyException unused) {
            this.a.B();
            this.a.s((byte) 5);
        }
        if (this.a.c() != 14) {
            return null;
        }
        return this.a.p();
    }
}
