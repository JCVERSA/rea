package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class RequestPtyReq extends Request {
    private String d = "vt100";
    private int e = 80;
    private int f = 24;
    private int g = 640;
    private int h = 480;
    private byte[] i = Util.c;

    RequestPtyReq() {
    }

    @Override // com.jcraft.jsch.Request
    public void a(Session session, Channel channel) throws Exception {
        super.a(session, channel);
        Buffer buffer = new Buffer();
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 98);
        buffer.v(channel.o());
        buffer.y(Util.v("pty-req"));
        buffer.s(c() ? (byte) 1 : (byte) 0);
        buffer.y(Util.v(this.d));
        buffer.v(this.e);
        buffer.v(this.f);
        buffer.v(this.g);
        buffer.v(this.h);
        buffer.y(this.i);
        d(packet);
    }

    void e(int i, int i2, int i3, int i4) {
        this.e = i;
        this.f = i2;
        this.g = i3;
        this.h = i4;
    }

    void f(String str) {
        this.d = str;
    }

    void g(byte[] bArr) {
        this.i = bArr;
    }
}
