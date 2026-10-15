package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelShell extends ChannelSession {
    ChannelShell() {
        this.z = true;
    }

    @Override // com.jcraft.jsch.Channel
    public void D() throws JSchException {
        Session sessionP = p();
        try {
            G();
            new RequestShell().a(sessionP, this);
            if (this.i.a != null) {
                Thread thread = new Thread(new Runnable() { // from class: com.jcraft.jsch.c
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.q.t();
                    }
                });
                this.j = thread;
                thread.setName("Shell for " + sessionP.g0);
                boolean z = sessionP.c0;
                if (z) {
                    this.j.setDaemon(z);
                }
                this.j.start();
            }
        } catch (Exception e) {
            if (!(e instanceof JSchException)) {
                throw new JSchException("ChannelShell", e);
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
