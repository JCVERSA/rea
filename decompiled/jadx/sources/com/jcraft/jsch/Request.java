package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
abstract class Request {
    private boolean a = false;
    private Session b = null;
    private Channel c = null;

    Request() {
    }

    void a(Session session, Channel channel) throws Exception {
        this.b = session;
        this.c = channel;
        if (channel.r > 0) {
            b(true);
        }
    }

    void b(boolean z) {
        this.a = z;
    }

    boolean c() {
        return this.a;
    }

    void d(Packet packet) throws Exception {
        if (this.a) {
            this.c.q = -1;
        }
        this.b.m0(packet);
        if (this.a) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = this.c.r;
            while (this.c.s() && this.c.q == -1) {
                try {
                    Thread.sleep(10L);
                } catch (Exception unused) {
                }
                if (j > 0 && System.currentTimeMillis() - jCurrentTimeMillis > j) {
                    this.c.q = 0;
                    throw new JSchException("channel request: timeout");
                }
            }
            if (this.c.q == 0) {
                throw new JSchException("failed to send channel request");
            }
        }
    }
}
