package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes3.dex */
public class ProxySOCKS5 implements Proxy {
    private String a;
    private int b;
    private InputStream c;
    private OutputStream d;
    private Socket e;
    private String f;
    private String g;

    private void e(InputStream inputStream, byte[] bArr, int i) throws IOException, JSchException {
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 <= 0) {
                throw new JSchProxyException("ProxySOCKS5: stream is closed");
            }
            i2 += i3;
        }
    }

    @Override // com.jcraft.jsch.Proxy
    public Socket a() {
        return this.e;
    }

    @Override // com.jcraft.jsch.Proxy
    public OutputStream b() {
        return this.d;
    }

    @Override // com.jcraft.jsch.Proxy
    public InputStream c() {
        return this.c;
    }

    @Override // com.jcraft.jsch.Proxy
    public void close() {
        try {
            InputStream inputStream = this.c;
            if (inputStream != null) {
                inputStream.close();
            }
            OutputStream outputStream = this.d;
            if (outputStream != null) {
                outputStream.close();
            }
            Socket socket = this.e;
            if (socket != null) {
                socket.close();
            }
        } catch (Exception unused) {
        }
        this.c = null;
        this.d = null;
        this.e = null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x00bd, code lost:
    
        if (r9[1] == 0) goto L24;
     */
    @Override // com.jcraft.jsch.Proxy
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void d(SocketFactory socketFactory, String str, int i, int i2) throws JSchException {
        String str2;
        try {
            if (socketFactory == null) {
                Socket socketI = Util.i(this.a, this.b, i2);
                this.e = socketI;
                this.c = socketI.getInputStream();
                this.d = this.e.getOutputStream();
            } else {
                Socket socketC = socketFactory.c(this.a, this.b);
                this.e = socketC;
                this.c = socketFactory.a(socketC);
                this.d = socketFactory.b(this.e);
            }
            if (i2 > 0) {
                this.e.setSoTimeout(i2);
            }
            this.e.setTcpNoDelay(true);
            byte[] bArr = new byte[1024];
            bArr[0] = 5;
            bArr[1] = 2;
            bArr[2] = 0;
            bArr[3] = 2;
            this.d.write(bArr, 0, 4);
            e(this.c, bArr, 2);
            int i3 = bArr[1] & 255;
            if (i3 != 0) {
                if (i3 == 2 && (str2 = this.f) != null && this.g != null) {
                    bArr[0] = 1;
                    bArr[1] = (byte) str2.length();
                    System.arraycopy(Util.v(this.f), 0, bArr, 2, this.f.length());
                    int length = this.f.length();
                    int i4 = 2 + length;
                    int i5 = length + 3;
                    bArr[i4] = (byte) this.g.length();
                    System.arraycopy(Util.v(this.g), 0, bArr, i5, this.g.length());
                    this.d.write(bArr, 0, i5 + this.g.length());
                    e(this.c, bArr, 2);
                }
                try {
                    this.e.close();
                } catch (Exception unused) {
                }
                throw new JSchProxyException("fail in SOCKS5 proxy");
            }
            bArr[0] = 5;
            bArr[1] = 1;
            bArr[2] = 0;
            byte[] bArrV = Util.v(str);
            int length2 = bArrV.length;
            bArr[3] = 3;
            bArr[4] = (byte) length2;
            System.arraycopy(bArrV, 0, bArr, 5, length2);
            bArr[5 + length2] = (byte) (i >>> 8);
            bArr[length2 + 6] = (byte) (i & 255);
            this.d.write(bArr, 0, length2 + 7);
            e(this.c, bArr, 4);
            if (bArr[1] != 0) {
                try {
                    this.e.close();
                } catch (Exception unused2) {
                }
                throw new JSchProxyException("ProxySOCKS5: server returns " + ((int) bArr[1]));
            }
            int i6 = bArr[3] & 255;
            if (i6 == 1) {
                e(this.c, bArr, 6);
                return;
            }
            if (i6 == 3) {
                e(this.c, bArr, 1);
                e(this.c, bArr, (bArr[0] & 255) + 2);
            } else {
                if (i6 != 4) {
                    return;
                }
                e(this.c, bArr, 18);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            try {
                Socket socket = this.e;
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception unused3) {
            }
            throw new JSchProxyException("ProxySOCKS5: " + e2.toString(), e2);
        }
    }
}
