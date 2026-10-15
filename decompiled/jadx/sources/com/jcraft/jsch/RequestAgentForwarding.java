package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestAgentForwarding extends Request {
    RequestAgentForwarding() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        b(false);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("auth-agent-req@openssh.com"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        d(packet);
        session.L = true;
    }
}
