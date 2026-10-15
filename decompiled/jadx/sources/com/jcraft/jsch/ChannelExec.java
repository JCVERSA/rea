package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelExec extends ChannelSession {
    byte[] H = new byte[0];

    @Override // com.jcraft.jsch.Channel
    public void D() throws JSchException {
        Session sessionP = p();
        try {
            G();
            new RequestExec(this.H).a(sessionP, this);
            if (this.i.a != null) {
                Thread thread = new Thread(new Runnable() { // from class: com.jcraft.jsch.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.t();
                    }
                });
                this.j = thread;
                thread.setName("Exec thread " + sessionP.x());
                boolean z = sessionP.c0;
                if (z) {
                    this.j.setDaemon(z);
                }
                this.j.start();
            }
        } catch (Exception e) {
            if (!(e instanceof JSchException)) {
                throw new JSchException("ChannelExec", e);
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
