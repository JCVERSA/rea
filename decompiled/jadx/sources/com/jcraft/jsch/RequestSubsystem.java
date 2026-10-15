package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestSubsystem extends Request {
    private String d = null;

    RequestSubsystem() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("subsystem"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        buffer.y(Util.v(this.d));
        d(packet);
    }

    public void e(Session session, Channel channel, String str, boolean z) throws Exception {
        b(z);
        this.d = str;
        a(session, channel);
    }
}
