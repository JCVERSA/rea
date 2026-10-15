package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public abstract class UserAuth {
    protected UserInfo a;
    protected Packet b;
    protected Buffer c;
    protected String d;

    public boolean a(Session session) throws Exception {
        this.a = session.F();
        Packet packet = session.P;
        this.b = packet;
        this.c = packet.a();
        this.d = session.G();
        return true;
    }
}
