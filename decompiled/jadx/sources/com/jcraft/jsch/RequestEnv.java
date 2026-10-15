package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestEnv extends Request {
    byte[] d = new byte[0];
    byte[] e = new byte[0];

    RequestEnv() {
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
        buffer.y(Util.v("env"));
        buffer.s((byte) 0);
        buffer.y(this.d);
        buffer.y(this.e);
        d(packet);
    }

    void e(byte[] bArr, byte[] bArr2) {
        this.d = bArr;
        this.e = bArr2;
    }
}
