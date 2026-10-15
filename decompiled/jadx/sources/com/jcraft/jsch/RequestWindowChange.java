package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestWindowChange extends Request {
    int d = 80;
    int e = 24;
    int f = 640;
    int g = 480;

    RequestWindowChange() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("window-change"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        buffer.v(this.d);
        buffer.v(this.e);
        buffer.v(this.f);
        buffer.v(this.g);
        d(packet);
    }
}
