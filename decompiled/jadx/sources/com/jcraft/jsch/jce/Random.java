package com.jcraft.jsch.jce;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes3.dex */
public class Random implements com.jcraft.jsch.Random {
    private byte[] a = new byte[16];
    private SecureRandom b;

    public Random() {
        this.b = null;
        this.b = new SecureRandom();
    }

    @Override // com.jcraft.jsch.Random
    public void a(byte[] bArr, int i, int i2) {
        if (i2 > this.a.length) {
            this.a = new byte[i2];
        }
        this.b.nextBytes(this.a);
        System.arraycopy(this.a, 0, bArr, i, i2);
    }
}
