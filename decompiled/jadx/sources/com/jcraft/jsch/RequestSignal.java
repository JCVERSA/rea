package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestSignal extends Request {
    private String d = "KILL";

    RequestSignal() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("signal"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        buffer.y(Util.v(this.d));
        d(packet);
    }
}
