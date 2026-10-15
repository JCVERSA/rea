package com.jcraft.jsch.jce;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.MAC;
import javax.crypto.Mac;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes3.dex */
abstract class HMAC implements MAC {
    protected String a;
    protected int b;
    protected String c;
    protected boolean d;
    private Mac e;
    private final byte[] f = new byte[4];

    HMAC() {
    }

    @Override // com.jcraft.jsch.MAC
    public void a(byte[] bArr) throws Exception {
        int length = bArr.length;
        int i = this.b;
        if (length > i) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            bArr = bArr2;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, this.c);
        Mac mac = Mac.getInstance(this.c);
        this.e = mac;
        mac.init(secretKeySpec);
    }

    @Override // com.jcraft.jsch.MAC
    public void b(byte[] bArr, int i, int i2) {
        this.e.update(bArr, i, i2);
    }

    @Override // com.jcraft.jsch.MAC
    public void c(byte[] bArr, int i) {
        try {
            this.e.doFinal(bArr, i);
        } catch (ShortBufferException e) {
            if (JSch.k().isEnabled(3)) {
                JSch.k().b(3, e.getMessage(), e);
            }
        }
    }

    @Override // com.jcraft.jsch.MAC
    public int d() {
        return this.b;
    }

    @Override // com.jcraft.jsch.MAC
    public void e(int i) {
        byte[] bArr = this.f;
        bArr[0] = (byte) (i >>> 24);
        bArr[1] = (byte) (i >>> 16);
        bArr[2] = (byte) (i >>> 8);
        bArr[3] = (byte) i;
        b(bArr, 0, 4);
    }

    @Override // com.jcraft.jsch.MAC
    public boolean f() {
        return this.d;
    }
}
