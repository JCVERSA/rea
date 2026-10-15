package com.jcraft.jsch.bc;

import ax.p093fa.a;
import ax.p193le.b;
import ax.p225ne.d;
import ax.p289re.k;
import ax.p289re.l;
import com.jcraft.jsch.Cipher;
import java.nio.ByteBuffer;
import javax.crypto.AEADBadTagException;

/* JADX INFO: loaded from: classes3.dex */
public class ChaCha20Poly1305 implements Cipher {
    private b a;
    private b b;
    private k c;
    private k d;
    private int e;
    private d f;

    private static boolean a(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        int i = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i |= bArr[i2] ^ bArr2[i2];
        }
        return i == 0;
    }

    @Override // com.jcraft.jsch.Cipher
    public int d() {
        return 64;
    }

    @Override // com.jcraft.jsch.Cipher
    public void e(int i) throws Exception {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.putLong(0, i);
        this.a.e(this.e == 0, new l(this.c, byteBufferAllocate.array(), 0, byteBufferAllocate.array().length));
        this.b.e(this.e == 0, new l(this.d, byteBufferAllocate.array(), 0, byteBufferAllocate.array().length));
        byte[] bArr = new byte[64];
        this.b.b(bArr, 0, 64, bArr, 0);
        this.f.e(new k(bArr, 0, 32));
    }

    @Override // com.jcraft.jsch.Cipher
    public /* synthetic */ void f(byte[] bArr, int i, int i2) throws Exception {
        a.f(this, bArr, i, i2);
    }

    @Override // com.jcraft.jsch.Cipher
    public void g(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        this.a.b(bArr, i, i2, bArr2, i3);
    }

    @Override // com.jcraft.jsch.Cipher
    public int h() {
        return 16;
    }

    @Override // com.jcraft.jsch.Cipher
    public void i(byte[] bArr, int i, int i2, byte[] bArr2, int i3) throws Exception {
        if (this.e == 1) {
            byte[] bArr3 = new byte[16];
            System.arraycopy(bArr, i2, bArr3, 0, 16);
            byte[] bArr4 = new byte[16];
            this.f.b(bArr, i, i2);
            this.f.c(bArr4, 0);
            if (!a(bArr3, bArr4)) {
                throw new AEADBadTagException("Tag mismatch");
            }
        }
        this.b.b(bArr, i + 4, i2 - 4, bArr2, i3 + 4);
        if (this.e == 0) {
            this.f.b(bArr2, i3, i2);
            this.f.c(bArr2, i2);
        }
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean j() {
        return false;
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean k() {
        return true;
    }

    @Override // com.jcraft.jsch.Cipher
    public void l(int i, byte[] bArr, byte[] bArr2) throws Exception {
        if (bArr.length > 64) {
            byte[] bArr3 = new byte[64];
            System.arraycopy(bArr, 0, bArr3, 0, 64);
            bArr = bArr3;
        }
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[32];
        System.arraycopy(bArr, 32, bArr4, 0, 32);
        System.arraycopy(bArr, 0, bArr5, 0, 32);
        this.e = i;
        try {
            this.c = new k(bArr4, 0, 32);
            this.d = new k(bArr5, 0, 32);
            this.a = new b();
            this.b = new b();
            this.f = new d();
        } catch (Exception e) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            throw e;
        }
    }

    @Override // com.jcraft.jsch.Cipher
    public int m() {
        return 8;
    }

    @Override // com.jcraft.jsch.Cipher
    public boolean n() {
        return true;
    }
}
