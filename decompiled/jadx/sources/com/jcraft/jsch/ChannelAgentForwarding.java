package com.jcraft.jsch;

import java.io.IOException;
import java.util.Vector;
import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
class ChannelAgentForwarding extends Channel {
    private Buffer w;
    private Buffer x = null;
    private Packet y = null;
    private Buffer z;

    ChannelAgentForwarding() {
        this.w = null;
        this.z = null;
        this.d = 131072;
        this.e = 131072;
        this.f = Http2.INITIAL_MAX_FRAME_SIZE;
        this.c = Util.v("auth-agent@openssh.com");
        Buffer buffer = new Buffer();
        this.w = buffer;
        buffer.A();
        this.z = new Buffer();
        this.n = true;
    }

    private void G(byte[] bArr) {
        this.y.c();
        this.x.s((byte) 94);
        this.x.v(this.b);
        this.x.v(bArr.length + 4);
        this.x.y(bArr);
        try {
            p().n0(this.y, this, bArr.length + 4);
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Code duplicated, block: B:131:0x014c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:136:0x014d A[SYNTHETIC] */
    @Override // com.jcraft.jsch.Channel
    void E(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArrF;
        Identity identityElementAt;
        String strB;
        if (this.y == null) {
            this.x = new Buffer(this.h);
            this.y = new Packet(this.x);
        }
        this.w.D();
        Buffer buffer = this.w;
        byte[] bArr2 = buffer.b;
        int i3 = 0;
        if (bArr2.length < buffer.c + i2) {
            byte[] bArr3 = new byte[buffer.d + i2];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            this.w.b = bArr3;
        }
        this.w.u(bArr, i, i2);
        if (this.w.i() > this.w.j()) {
            this.w.d -= 4;
            return;
        }
        int iC = this.w.c();
        try {
            Session sessionP = p();
            IdentityRepository identityRepositoryZ = sessionP.z();
            UserInfo userInfoF = sessionP.F();
            this.z.A();
            if (iC == 11) {
                this.z.s((byte) 12);
                Vector<Identity> vectorA = identityRepositoryZ.a();
                synchronized (vectorA) {
                    int i4 = 0;
                    for (int i5 = 0; i5 < vectorA.size(); i5++) {
                        try {
                            if (vectorA.elementAt(i5).d() != null) {
                                i4++;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    this.z.v(i4);
                    while (i3 < vectorA.size()) {
                        byte[] bArrD = vectorA.elementAt(i3).d();
                        if (bArrD != null) {
                            this.z.y(bArrD);
                            this.z.y(Util.c);
                        }
                        i3++;
                    }
                }
            } else if (iC == 1) {
                this.z.s((byte) 2);
                this.z.v(0);
            } else if (iC == 13) {
                byte[] bArrP = this.w.p();
                byte[] bArrP2 = this.w.p();
                int i6 = this.w.i();
                Vector<Identity> vectorA2 = identityRepositoryZ.a();
                synchronized (vectorA2) {
                    while (true) {
                        bArrF = null;
                        if (i3 >= vectorA2.size()) {
                            identityElementAt = null;
                            break;
                        }
                        identityElementAt = vectorA2.elementAt(i3);
                        if (identityElementAt.d() != null && Util.a(bArrP, identityElementAt.d())) {
                            if (!identityElementAt.c()) {
                                if (!identityElementAt.c()) {
                                    break;
                                }
                            } else if (userInfoF != null) {
                                while (identityElementAt.c()) {
                                    if (!userInfoF.a("Passphrase for " + identityElementAt.getName()) || (strB = userInfoF.b()) == null) {
                                        break;
                                        break;
                                    } else {
                                        try {
                                            if (identityElementAt.e(Util.v(strB))) {
                                                break;
                                            }
                                        } catch (JSchException unused) {
                                        }
                                    }
                                }
                                if (!identityElementAt.c()) {
                                    break;
                                    break;
                                }
                            }
                        }
                        i3++;
                    }
                }
                if (identityElementAt != null) {
                    if (!Util.c(new Buffer(bArrP).p()).equals("ssh-rsa")) {
                        bArrF = identityElementAt.f(bArrP2);
                    } else if ((i6 & 2) != 0) {
                        bArrF = identityElementAt.a(bArrP2, "rsa-sha2-256");
                    } else {
                        bArrF = (i6 & 4) != 0 ? identityElementAt.a(bArrP2, "rsa-sha2-512") : identityElementAt.a(bArrP2, "ssh-rsa");
                    }
                }
                if (bArrF == null) {
                    this.z.s((byte) 30);
                } else {
                    this.z.s((byte) 14);
                    this.z.y(bArrF);
                }
            } else if (iC == 18) {
                identityRepositoryZ.c(this.w.p());
                this.z.s((byte) 6);
            } else if (iC == 9) {
                this.z.s((byte) 6);
            } else if (iC == 19) {
                identityRepositoryZ.d();
                this.z.s((byte) 6);
            } else if (iC == 17) {
                byte[] bArr4 = new byte[this.w.j()];
                this.w.e(bArr4);
                this.z.s(identityRepositoryZ.b(bArr4) ? (byte) 6 : (byte) 5);
            } else {
                Buffer buffer2 = this.w;
                buffer2.E(buffer2.j() - 1);
                this.z.s((byte) 5);
            }
            byte[] bArr5 = new byte[this.z.j()];
            this.z.e(bArr5);
            G(bArr5);
        } catch (JSchException e) {
            throw new IOException(e.toString(), e);
        }
    }

    @Override // com.jcraft.jsch.Channel
    void i() {
        super.i();
        h();
    }

    @Override // com.jcraft.jsch.Channel
    void t() {
        try {
            v();
        } catch (Exception unused) {
            this.m = true;
            f();
        }
    }
}
