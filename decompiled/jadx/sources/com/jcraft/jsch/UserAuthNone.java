package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class UserAuthNone extends UserAuth {
    private String e;

    @Override // com.jcraft.jsch.UserAuth
    public boolean a(Session session) throws Exception {
        super.a(session);
        this.b.c();
        this.c.s((byte) 5);
        this.c.y(Util.v("ssh-userauth"));
        session.m0(this.b);
        if (session.A().isEnabled(1)) {
            session.A().a(1, "SSH_MSG_SERVICE_REQUEST sent");
        }
        Buffer bufferM = session.M(this.c);
        this.c = bufferM;
        boolean z = bufferM.h() == 6;
        if (session.A().isEnabled(1)) {
            session.A().a(1, "SSH_MSG_SERVICE_ACCEPT received");
        }
        if (!z || !session.w("enable_auth_none").equals("yes")) {
            return false;
        }
        byte[] bArrV = Util.v(this.d);
        this.b.c();
        this.c.s((byte) 50);
        this.c.y(bArrV);
        this.c.y(Util.v("ssh-connection"));
        this.c.y(Util.v("none"));
        session.m0(this.b);
        while (true) {
            Buffer bufferM2 = session.M(this.c);
            this.c = bufferM2;
            int iH = bufferM2.h() & 255;
            if (iH == 52) {
                return true;
            }
            if (iH != 53) {
                if (iH != 51) {
                    throw new JSchException("USERAUTH fail (" + iH + ")");
                }
                this.c.i();
                this.c.c();
                this.c.c();
                byte[] bArrP = this.c.p();
                this.c.c();
                c(Util.c(bArrP));
                return false;
            }
            this.c.i();
            this.c.c();
            this.c.c();
            byte[] bArrP2 = this.c.p();
            this.c.p();
            String strC = Util.c(bArrP2);
            UserInfo userInfo = this.a;
            if (userInfo != null) {
                try {
                    userInfo.d(strC);
                } catch (RuntimeException unused) {
                }
            }
        }
    }

    protected String b() {
        return this.e;
    }

    protected void c(String str) {
        this.e = str;
    }
}
