package com.jcraft.jsch;

import okhttp3.HttpUrl;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelSubsystem extends ChannelSession {
    boolean H = true;
    String I = HttpUrl.FRAGMENT_ENCODE_SET;

    @Override // com.jcraft.jsch.Channel
    public void D() throws JSchException {
        Session sessionP = p();
        try {
            if (this.x) {
                new RequestX11().a(sessionP, this);
            }
            if (this.z) {
                new RequestPtyReq().a(sessionP, this);
            }
            new RequestSubsystem().e(sessionP, this, this.I, this.H);
            if (this.i.a != null) {
                Thread thread = new Thread(new Runnable() { // from class: com.jcraft.jsch.d
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.t();
                    }
                });
                this.j = thread;
                thread.setName("Subsystem for " + sessionP.g0);
                boolean z = sessionP.c0;
                if (z) {
                    this.j.setDaemon(z);
                }
                this.j.start();
            }
        } catch (Exception e) {
            if (!(e instanceof JSchException)) {
                throw new JSchException("ChannelSubsystem", e);
            }
            throw ((JSchException) e);
        }
    }

    @Override // com.jcraft.jsch.ChannelSession
    public /* bridge */ /* synthetic */ void H(boolean z) {
        super.H(z);
    }

    @Override // com.jcraft.jsch.ChannelSession
    public /* bridge */ /* synthetic */ void I(boolean z) {
        super.I(z);
    }

    @Override // com.jcraft.jsch.Channel
    void q() throws JSchException {
        this.i.h(p().M);
        this.i.j(p().N);
    }
}
