package com.jcraft.jsch;

import java.io.InputStream;
import java.net.Socket;
import java.util.Vector;
import okhttp3.HttpUrl;
import okhttp3.internal.http2.Http2;

/* JADX INFO: loaded from: classes3.dex */
public class ChannelForwardedTCPIP extends Channel {
    private static Vector<Config> z = new Vector<>();
    private Socket w = null;
    private ForwardedTCPIPDaemon x = null;
    private Config y = null;

    static abstract class Config {
        Session a;
        int b;
        int c;
        String d;
        String e;

        Config() {
        }
    }

    static class ConfigDaemon extends Config {
        Object[] f;

        ConfigDaemon() {
        }
    }

    static class ConfigLHost extends Config {
        int f;
        SocketFactory g;

        ConfigLHost() {
        }
    }

    ChannelForwardedTCPIP() {
        this.d = 131072;
        this.e = 131072;
        this.f = Http2.INITIAL_MAX_FRAME_SIZE;
        this.i = new IO();
        this.n = true;
    }

    static void G(Session session, String str, int i, int i2, String str2, int i3, SocketFactory socketFactory) throws JSchException {
        String strL = L(str);
        synchronized (z) {
            try {
                if (K(session, strL, i) != null) {
                    throw new JSchException("PortForwardingR: remote port " + i + " is already registered.");
                }
                ConfigLHost configLHost = new ConfigLHost();
                configLHost.a = session;
                configLHost.b = i;
                configLHost.c = i2;
                configLHost.e = str2;
                configLHost.f = i3;
                configLHost.d = strL;
                configLHost.g = socketFactory;
                z.addElement(configLHost);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static void H(Session session) {
        int[] iArr;
        int i;
        int i2;
        synchronized (z) {
            try {
                iArr = new int[z.size()];
                i2 = 0;
                for (int i3 = 0; i3 < z.size(); i3++) {
                    Config configElementAt = z.elementAt(i3);
                    if (configElementAt.a == session) {
                        iArr[i2] = configElementAt.b;
                        i2++;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        for (i = 0; i < i2; i++) {
            I(session, iArr[i]);
        }
    }

    static void I(Session session, int i) {
        J(session, null, i);
    }

    static void J(Session session, String str, int i) {
        synchronized (z) {
            try {
                Config configK = K(session, L(str), i);
                if (configK == null) {
                    configK = K(session, null, i);
                }
                if (configK == null) {
                    return;
                }
                z.removeElement(configK);
                if (str == null) {
                    str = configK.d;
                }
                if (str == null) {
                    str = "0.0.0.0";
                }
                Buffer buffer = new Buffer(200);
                Packet packet = new Packet(buffer);
                try {
                    packet.c();
                    buffer.s((byte) 80);
                    buffer.y(Util.v("cancel-tcpip-forward"));
                    buffer.s((byte) 0);
                    buffer.y(Util.v(str));
                    buffer.v(i);
                    session.m0(packet);
                } catch (Exception unused) {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static Config K(Session session, String str, int i) {
        int i2;
        synchronized (z) {
            for (int i3 = 0; i3 < z.size(); i3++) {
                try {
                    Config configElementAt = z.elementAt(i3);
                    if (configElementAt.a == session && (((i2 = configElementAt.b) == i || (i2 == 0 && configElementAt.c == i)) && (str == null || configElementAt.d.equals(str)))) {
                        return configElementAt;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return null;
        }
    }

    static String L(String str) {
        if (str == null) {
            return "localhost";
        }
        return (str.length() == 0 || str.equals("*")) ? HttpUrl.FRAGMENT_ENCODE_SET : str;
    }

    @Override // com.jcraft.jsch.Channel
    void m(Buffer buffer) {
        Session sessionP;
        z(buffer.i());
        B(buffer.r());
        A(buffer.i());
        byte[] bArrP = buffer.p();
        int i = buffer.i();
        buffer.p();
        buffer.i();
        try {
            sessionP = p();
        } catch (JSchException unused) {
            sessionP = null;
        }
        Config configK = K(sessionP, Util.c(bArrP), i);
        this.y = configK;
        if (configK == null) {
            this.y = K(sessionP, null, i);
        }
        if (this.y == null && sessionP.A().isEnabled(3)) {
            sessionP.A().a(3, "ChannelForwardedTCPIP: " + Util.c(bArrP) + ":" + i + " is not registered.");
        }
    }

    @Override // com.jcraft.jsch.Channel
    public void t() {
        IO io;
        InputStream inputStream;
        try {
            Config config = this.y;
            if (config instanceof ConfigDaemon) {
                ConfigDaemon configDaemon = (ConfigDaemon) config;
                this.x = (ForwardedTCPIPDaemon) Class.forName(configDaemon.e).asSubclass(ForwardedTCPIPDaemon.class).getDeclaredConstructor(null).newInstance(null);
                ax.p152j5.b bVar = new ax.p152j5.b();
                this.i.i(new Channel.PassiveInputStream(bVar, 32768), false);
                this.x.L0(this, n(), bVar);
                this.x.m0(configDaemon.f);
                new Thread(this.x).start();
            } else {
                ConfigLHost configLHost = (ConfigLHost) config;
                SocketFactory socketFactory = configLHost.g;
                Socket socketI = socketFactory == null ? Util.i(configLHost.e, configLHost.f, 10000) : socketFactory.c(configLHost.e, configLHost.f);
                this.w = socketI;
                socketI.setTcpNoDelay(true);
                this.i.h(this.w.getInputStream());
                this.i.j(this.w.getOutputStream());
            }
            v();
            this.j = Thread.currentThread();
            Buffer buffer = new Buffer(this.h);
            Packet packet = new Packet(buffer);
            try {
                Session sessionP = p();
                while (this.j != null && (io = this.i) != null && (inputStream = io.a) != null) {
                    byte[] bArr = buffer.b;
                    int i = inputStream.read(bArr, 14, (bArr.length - 14) - sessionP.v());
                    if (i > 0) {
                        packet.c();
                        buffer.s((byte) 94);
                        buffer.v(this.b);
                        buffer.v(i);
                        buffer.E(i);
                        synchronized (this) {
                            try {
                                if (!this.m) {
                                    sessionP.n0(packet, this, i);
                                }
                            } catch (Throwable th) {
                                throw th;
                            }
                        }
                        break;
                    }
                    h();
                    break;
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
