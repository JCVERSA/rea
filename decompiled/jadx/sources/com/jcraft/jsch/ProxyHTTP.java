package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes3.dex */
public class ProxyHTTP implements Proxy {
    private String a;
    private int b;
    private InputStream c;
    private OutputStream d;
    private Socket e;
    private String f;
    private String g;

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

    @Override // com.jcraft.jsch.Proxy
    public void d(SocketFactory socketFactory, String str, int i, int i2) throws JSchException {
        int i3;
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
            this.d.write(Util.v("CONNECT " + str + ":" + i + " HTTP/1.0\r\n"));
            if (this.f != null && this.g != null) {
                byte[] bArrV = Util.v(this.f + ":" + this.g);
                byte[] bArrX = Util.x(bArrV, 0, bArrV.length, true);
                this.d.write(Util.v("Proxy-Authorization: Basic "));
                this.d.write(bArrX);
                this.d.write(Util.v("\r\n"));
            }
            this.d.write(Util.v("\r\n"));
            this.d.flush();
            StringBuilder sb = new StringBuilder();
            int iIndexOf = 0;
            while (iIndexOf >= 0) {
                iIndexOf = this.c.read();
                if (iIndexOf == 13) {
                    iIndexOf = this.c.read();
                    if (iIndexOf == 10) {
                        break;
                    }
                } else {
                    sb.append((char) iIndexOf);
                }
            }
            if (iIndexOf < 0) {
                throw new IOException();
            }
            String string = sb.toString();
            String strSubstring = "Unknow reason";
            int i4 = -1;
            try {
                iIndexOf = string.indexOf(32);
                int i5 = iIndexOf + 1;
                int iIndexOf2 = string.indexOf(32, i5);
                i4 = Integer.parseInt(string.substring(i5, iIndexOf2));
                strSubstring = string.substring(iIndexOf2 + 1);
            } catch (Exception unused) {
            }
            if (i4 != 200) {
                throw new IOException("proxy error: " + strSubstring);
            }
            do {
                i3 = 0;
                while (iIndexOf >= 0) {
                    iIndexOf = this.c.read();
                    if (iIndexOf == 13) {
                        iIndexOf = this.c.read();
                        if (iIndexOf == 10) {
                            break;
                        }
                    } else {
                        i3++;
                    }
                }
                if (iIndexOf < 0) {
                    throw new IOException();
                }
            } while (i3 != 0);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            try {
                Socket socket = this.e;
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception unused2) {
            }
            throw new JSchProxyException("ProxyHTTP: " + e2.toString(), e2);
        }
    }
}
