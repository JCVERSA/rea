package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Hashtable;
import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
class ChannelX11 extends Channel {
    private static int A = 6000;
    static byte[] B = null;
    private static Hashtable<Session, byte[]> C = new Hashtable<>();
    private static Hashtable<Session, byte[]> D = new Hashtable<>();
    private static byte[] E = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    private static String z = "127.0.0.1";
    private boolean w = true;
    private Socket x = null;
    private byte[] y = new byte[0];

    ChannelX11() {
        this.d = 131072;
        this.e = 131072;
        this.f = Http2.INITIAL_MAX_FRAME_SIZE;
        this.c = Util.v("x11");
        this.n = true;
    }

    private byte[] G(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.y;
        byte[] bArr3 = new byte[bArr2.length + i2];
        System.arraycopy(bArr, i, bArr3, bArr2.length, i2);
        byte[] bArr4 = this.y;
        if (bArr4.length > 0) {
            System.arraycopy(bArr4, 0, bArr3, 0, bArr4.length);
        }
        this.y = bArr3;
        return bArr3;
    }

    private static boolean H(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    static byte[] I(Session session) {
        byte[] bArr;
        int i;
        synchronized (D) {
            try {
                bArr = D.get(session);
                if (bArr == null) {
                    Random random = Session.x0;
                    byte[] bArr2 = new byte[16];
                    synchronized (random) {
                        random.a(bArr2, 0, 16);
                    }
                    C.put(session, bArr2);
                    bArr = new byte[32];
                    for (i = 0; i < 16; i++) {
                        int i2 = i * 2;
                        byte[] bArr3 = E;
                        bArr[i2] = bArr3[(bArr2[i] >>> 4) & 15];
                        bArr[i2 + 1] = bArr3[bArr2[i] & 15];
                    }
                    D.put(session, bArr);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return bArr;
    }

    static void J(Session session) {
        synchronized (D) {
            D.remove(session);
            C.remove(session);
        }
    }

    @Override // com.jcraft.jsch.Channel
    void E(byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2;
        if (!this.w) {
            this.i.f(bArr, i, i2);
            return;
        }
        try {
            Session sessionP = p();
            byte[] bArrG = G(bArr, i, i2);
            int length = bArrG.length;
            if (length < 9) {
                return;
            }
            int i3 = ((bArrG[6] & 255) * 256) + (bArrG[7] & 255);
            int i4 = ((bArrG[8] & 255) * 256) + (bArrG[9] & 255);
            byte b = bArrG[0];
            if ((b & 255) != 66 && (b & 255) == 108) {
                i3 = ((i3 << 8) & 65280) | ((i3 >>> 8) & 255);
                i4 = ((i4 << 8) & 65280) | ((i4 >>> 8) & 255);
            }
            int i5 = (-i3) & 3;
            if (length < i3 + 12 + i5 + i4) {
                return;
            }
            byte[] bArr3 = new byte[i4];
            int i6 = 12 + i3 + i5;
            System.arraycopy(bArrG, i6, bArr3, 0, i4);
            synchronized (C) {
                bArr2 = C.get(sessionP);
            }
            if (H(bArr3, bArr2)) {
                byte[] bArr4 = B;
                if (bArr4 != null) {
                    System.arraycopy(bArr4, 0, bArrG, i6, i4);
                }
            } else {
                this.j = null;
                h();
                this.i.a();
                f();
            }
            this.w = false;
            this.i.f(bArrG, 0, length);
            this.y = null;
        } catch (JSchException e) {
            throw new IOException(e.toString(), e);
        }
    }

    @Override // com.jcraft.jsch.Channel
    void t() {
        IO io;
        InputStream inputStream;
        try {
            Socket socketI = Util.i(z, A, 10000);
            this.x = socketI;
            socketI.setTcpNoDelay(true);
            IO io2 = new IO();
            this.i = io2;
            io2.h(this.x.getInputStream());
            this.i.j(this.x.getOutputStream());
            v();
            this.j = Thread.currentThread();
            Buffer buffer = new Buffer(this.h);
            Packet packet = new Packet(buffer);
            try {
                Session sessionP = p();
                while (this.j != null && (io = this.i) != null && (inputStream = io.a) != null) {
                    byte[] bArr = buffer.b;
                    int i = inputStream.read(bArr, 14, (bArr.length - 14) - sessionP.v());
                    if (i <= 0) {
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
            } catch (Exception unused) {
            }
            f();
        } catch (Exception unused2) {
            w(1);
            this.m = true;
            f();
        }
    }
}
