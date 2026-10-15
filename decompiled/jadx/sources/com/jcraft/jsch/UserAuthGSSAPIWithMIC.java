package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class UserAuthGSSAPIWithMIC extends UserAuth {
    private static final byte[][] e = {new byte[]{6, 9, 42, -122, 72, -122, -9, 18, 1, 2, 2}};
    private static final String[] f = {"gssapi-with-mic.krb5"};

    UserAuthGSSAPIWithMIC() {
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0114  */
    /* JADX WARN: Code duplicated, block: B:79:0x0113 A[SYNTHETIC] */
    @Override // com.jcraft.jsch.UserAuth
    public boolean a(Session session) throws Exception {
        String str;
        byte bH;
        super.a(session);
        byte[] bArrV = Util.v(this.d);
        this.b.c();
        this.c.s((byte) 50);
        this.c.y(bArrV);
        this.c.y(Util.v("ssh-connection"));
        this.c.y(Util.v("gssapi-with-mic"));
        this.c.v(e.length);
        int i = 0;
        while (true) {
            byte[][] bArr = e;
            if (i >= bArr.length) {
                break;
            }
            this.c.y(bArr[i]);
            i++;
        }
        session.m0(this.b);
        while (true) {
            Buffer bufferM = session.M(this.c);
            this.c = bufferM;
            int iH = bufferM.h() & 255;
            if (iH == 51) {
                return false;
            }
            if (iH == 60) {
                this.c.i();
                this.c.c();
                this.c.c();
                byte[] bArrP = this.c.p();
                int i2 = 0;
                while (true) {
                    byte[][] bArr2 = e;
                    if (i2 >= bArr2.length) {
                        str = null;
                        break;
                    }
                    if (Util.a(bArrP, bArr2[i2])) {
                        str = f[i2];
                        break;
                    }
                    i2++;
                }
                if (str == null) {
                    return false;
                }
                try {
                    GSSContext gSSContext = (GSSContext) Class.forName(session.w(str)).asSubclass(GSSContext.class).getDeclaredConstructor(null).newInstance(null);
                    gSSContext.c(this.d, session.g0);
                    byte[] bArrA = new byte[0];
                    while (!gSSContext.b()) {
                        try {
                            bArrA = gSSContext.a(bArrA, 0, bArrA.length);
                            if (bArrA != null) {
                                this.b.c();
                                this.c.s((byte) 61);
                                this.c.y(bArrA);
                                session.m0(this.b);
                            }
                            if (!gSSContext.b()) {
                                Buffer bufferM2 = session.M(this.c);
                                this.c = bufferM2;
                                int iH2 = bufferM2.h() & 255;
                                if (iH2 == 64) {
                                    Buffer bufferM3 = session.M(this.c);
                                    this.c = bufferM3;
                                    bH = bufferM3.h();
                                } else {
                                    if (iH2 == 65) {
                                        Buffer bufferM4 = session.M(this.c);
                                        this.c = bufferM4;
                                        bH = bufferM4.h();
                                    }
                                    if (iH2 == 51) {
                                        return false;
                                    }
                                    this.c.i();
                                    this.c.c();
                                    this.c.c();
                                    bArrA = this.c.p();
                                }
                                iH2 = bH & 255;
                                if (iH2 == 51) {
                                    return false;
                                }
                                this.c.i();
                                this.c.c();
                                this.c.c();
                                bArrA = this.c.p();
                            }
                        } catch (JSchException unused) {
                            return false;
                        }
                    }
                    Buffer buffer = new Buffer();
                    buffer.y(session.C());
                    buffer.s((byte) 50);
                    buffer.y(bArrV);
                    buffer.y(Util.v("ssh-connection"));
                    buffer.y(Util.v("gssapi-with-mic"));
                    byte[] bArrD = gSSContext.d(buffer.b, 0, buffer.j());
                    if (bArrD == null) {
                        return false;
                    }
                    this.b.c();
                    this.c.s((byte) 66);
                    this.c.y(bArrD);
                    session.m0(this.b);
                    gSSContext.g();
                    Buffer bufferM5 = session.M(this.c);
                    this.c = bufferM5;
                    int iH3 = bufferM5.h() & 255;
                    if (iH3 == 52) {
                        return true;
                    }
                    if (iH3 == 51) {
                        this.c.i();
                        this.c.c();
                        this.c.c();
                        byte[] bArrP2 = this.c.p();
                        if (this.c.c() != 0) {
                            throw new JSchPartialAuthException(Util.c(bArrP2));
                        }
                    }
                    return false;
                } catch (JSchException | Exception unused2) {
                }
            } else {
                if (iH != 53) {
                    return false;
                }
                this.c.i();
                this.c.c();
                this.c.c();
                byte[] bArrP3 = this.c.p();
                this.c.p();
                String strC = Util.c(bArrP3);
                UserInfo userInfo = this.a;
                if (userInfo != null) {
                    userInfo.d(strC);
                }
            }
        }
    }
}
