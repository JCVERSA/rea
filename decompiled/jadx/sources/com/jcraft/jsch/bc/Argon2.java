package com.jcraft.jsch.bc;

import ax.p209me.a;
import ax.p289re.b;
import com.jcraft.jsch.JSchException;

/* JADX INFO: loaded from: classes3.dex */
public class Argon2 implements com.jcraft.jsch.Argon2 {
    private a a;

    @Override // com.jcraft.jsch.Argon2
    public void b(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3, int i3, int i4, int i5) throws Exception {
        int i6;
        if (i2 != 0) {
            i6 = 1;
            if (i2 != 1) {
                i6 = 2;
                if (i2 != 2) {
                    throw new JSchException("Invalid argon2 type.");
                }
            }
        } else {
            i6 = 0;
        }
        int i7 = 16;
        if (i5 != 16) {
            i7 = 19;
            if (i5 != 19) {
                throw new JSchException("Invalid argon2 version.");
            }
        }
        try {
            b bVarA = new b.C0449b(i6).f(bArr).b(bArr2).g(bArr3).c(i).d(i3).e(i4).h(i7).a();
            a aVar = new a();
            this.a = aVar;
            aVar.q(bVarA);
        } catch (NoClassDefFoundError e) {
            throw new JSchException("argon2 unavailable", e);
        }
    }

    @Override // com.jcraft.jsch.KDF
    public byte[] c(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        this.a.i(bArr, bArr2);
        return bArr2;
    }
}
