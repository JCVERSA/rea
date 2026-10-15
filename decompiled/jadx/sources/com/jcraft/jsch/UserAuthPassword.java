package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class UserAuthPassword extends UserAuth {
    private final int e = 60;

    UserAuthPassword() {
    }

    @Override // com.jcraft.jsch.UserAuth
    public boolean a(Session session) throws Exception {
        super.a(session);
        byte[] bArrV = session.k0;
        String str = this.d + "@" + session.g0;
        if (session.i0 != 22) {
            str = str + ":" + session.i0;
        }
        String str2 = str;
        try {
            if (session.f0 >= session.e0) {
                if (bArrV != null) {
                    Util.g(bArrV);
                }
                return false;
            }
            if (bArrV == null) {
                UserInfo userInfo = this.a;
                if (userInfo == null) {
                    if (bArrV != null) {
                        Util.g(bArrV);
                    }
                    return false;
                }
                if (!userInfo.c("Password for " + str2)) {
                    throw new JSchAuthCancelException("password");
                }
                String strF = this.a.f();
                if (strF == null) {
                    throw new JSchAuthCancelException("password");
                }
                bArrV = Util.v(strF);
            }
            byte[] bArrV2 = Util.v(this.d);
            this.b.c();
            this.c.s((byte) 50);
            this.c.y(bArrV2);
            this.c.y(Util.v("ssh-connection"));
            this.c.y(Util.v("password"));
            this.c.s((byte) 0);
            this.c.y(bArrV);
            session.m0(this.b);
            while (true) {
                Buffer bufferM = session.M(this.c);
                this.c = bufferM;
                int iH = bufferM.h() & 255;
                if (iH == 52) {
                    if (bArrV != null) {
                        Util.g(bArrV);
                    }
                    return true;
                }
                if (iH != 53) {
                    if (iH != 60) {
                        if (iH != 51) {
                            if (bArrV != null) {
                                Util.g(bArrV);
                            }
                            return false;
                        }
                        this.c.i();
                        this.c.c();
                        this.c.c();
                        byte[] bArrP = this.c.p();
                        if (this.c.c() != 0) {
                            throw new JSchPartialAuthException(Util.c(bArrP));
                        }
                        session.f0++;
                        if (bArrV != null) {
                            Util.g(bArrV);
                        }
                        return false;
                    }
                    this.c.i();
                    this.c.c();
                    this.c.c();
                    byte[] bArrP2 = this.c.p();
                    this.c.p();
                    UserInfo userInfo2 = this.a;
                    if (userInfo2 != null && (userInfo2 instanceof UIKeyboardInteractive)) {
                        String[] strArrE = ((UIKeyboardInteractive) userInfo2).e(str2, "Password Change Required", Util.c(bArrP2), new String[]{"New Password: "}, new boolean[]{false});
                        if (strArrE == null) {
                            throw new JSchAuthCancelException("password");
                        }
                        String str3 = strArrE[0];
                        byte[] bArrV3 = str3 != null ? Util.v(str3) : Util.c;
                        this.b.c();
                        this.c.s((byte) 50);
                        this.c.y(bArrV2);
                        this.c.y(Util.v("ssh-connection"));
                        this.c.y(Util.v("password"));
                        this.c.s((byte) 1);
                        this.c.y(bArrV);
                        this.c.y(bArrV3);
                        Util.g(bArrV3);
                        session.m0(this.b);
                    }
                    if (userInfo2 != null) {
                        userInfo2.d("Password must be changed.");
                    }
                    if (bArrV != null) {
                        Util.g(bArrV);
                    }
                    return false;
                }
                this.c.i();
                this.c.c();
                this.c.c();
                byte[] bArrP3 = this.c.p();
                this.c.p();
                String strC = Util.c(bArrP3);
                UserInfo userInfo3 = this.a;
                if (userInfo3 != null) {
                    userInfo3.d(strC);
                }
            }
        } catch (Throwable th) {
            if (bArrV == null) {
                throw th;
            }
            Util.g(bArrV);
            throw th;
        }
    }
}
