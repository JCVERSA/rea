package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestX11 extends Request {
    RequestX11() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("x11-req"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        buffer.s((byte) 0);
        buffer.y(Util.v("MIT-MAGIC-COOKIE-1"));
        buffer.y(ChannelX11.I(session));
        buffer.v(0);
        d(packet);
        session.K = true;
    }
}
