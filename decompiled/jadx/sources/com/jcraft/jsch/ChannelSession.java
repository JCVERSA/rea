package com.jcraft.jsch;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes3.dex */
class ChannelSession extends Channel {
    private static byte[] G = Util.v("session");
    protected boolean w = false;
    protected boolean x = false;
    protected Hashtable<byte[], byte[]> y = null;
    protected boolean z = false;
    protected String A = "vt100";
    protected int B = 80;
    protected int C = 24;
    protected int D = 640;
    protected int E = 480;
    protected byte[] F = null;

    ChannelSession() {
        this.c = G;
        this.i = new IO();
    }

    private byte[] J(Object obj) {
        return obj instanceof String ? Util.v((String) obj) : (byte[]) obj;
    }

    protected void G() throws Exception {
        Session sessionP = p();
        if (this.w) {
            new RequestAgentForwarding().a(sessionP, this);
        }
        if (this.x) {
            new RequestX11().a(sessionP, this);
        }
        if (this.z) {
            RequestPtyReq requestPtyReq = new RequestPtyReq();
            requestPtyReq.f(this.A);
            requestPtyReq.e(this.B, this.C, this.D, this.E);
            byte[] bArr = this.F;
            if (bArr != null) {
                requestPtyReq.g(bArr);
            }
            requestPtyReq.a(sessionP, this);
        }
        Hashtable<byte[], byte[]> hashtable = this.y;
        if (hashtable != null) {
            Enumeration<byte[]> enumerationKeys = hashtable.keys();
            while (enumerationKeys.hasMoreElements()) {
                byte[] bArrNextElement = enumerationKeys.nextElement();
                byte[] bArr2 = this.y.get(bArrNextElement);
                RequestEnv requestEnv = new RequestEnv();
                requestEnv.e(J(bArrNextElement), J(bArr2));
                requestEnv.a(sessionP, this);
            }
        }
    }

    public void H(boolean z) {
        this.w = z;
    }

    public void I(boolean z) {
        this.z = z;
    }

    @Override // com.jcraft.jsch.Channel
    void t() {
        IO io;
        InputStream inputStream;
        Buffer buffer = new Buffer(this.h);
        Packet packet = new Packet(buffer);
        try {
            Session sessionP = p();
            while (s() && this.j != null && (io = this.i) != null && (inputStream = io.a) != null) {
                byte[] bArr = buffer.b;
                int i = inputStream.read(bArr, 14, (bArr.length - 14) - sessionP.v());
                if (i != 0) {
                    if (i == -1) {
                        h();
                        break;
                    }
                    if (this.m) {
                        break;
                    }
                    packet.c();
                    buffer.s((byte) 94);
                    buffer.v(this.b);
                    buffer.v(i);
                    buffer.E(i);
                    sessionP.n0(packet, this, i);
                }
            }
        } catch (Exception unused) {
        }
        Thread thread = this.j;
        if (thread != null) {
            synchronized (thread) {
                thread.notifyAll();
            }
        }
        this.j = null;
    }
}
