package com.jcraft.jsch;

import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelDirectStreamLocal extends ChannelDirectTCPIP {
    private static final byte[] C = Util.v("direct-streamlocal@openssh.com");
    private String B;

    ChannelDirectStreamLocal() {
        this.c = C;
        this.d = 131072;
        this.e = 131072;
        this.f = Http2.INITIAL_MAX_FRAME_SIZE;
    }

    public void M(String str) {
        this.B = str;
    }

    @Override // com.jcraft.jsch.ChannelDirectTCPIP, com.jcraft.jsch.Channel
    protected Packet j() {
        String str = this.B;
        if (str == null) {
            this.s.A().a(4, "socketPath must be set");
            throw new RuntimeException("socketPath must be set");
        }
        Buffer buffer = new Buffer(str.length() + 50 + this.s.v());
        Packet packet = new Packet(buffer);
        packet.c();
        buffer.s((byte) 90);
        buffer.y(this.c);
        buffer.v(this.a);
        buffer.v(this.e);
        buffer.v(this.f);
        buffer.y(Util.v(this.B));
        buffer.y(Util.v(this.y));
        buffer.v(this.z);
        return packet;
    }
}
