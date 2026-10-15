package com.jcraft.jsch;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

/* JADX INFO: loaded from: classes3.dex */
class PortWatcher {
    private static Vector<PortWatcher> j = new Vector<>();
    private static InetAddress k;
    Session a;
    int b;
    int c;
    String d;
    InetAddress e;
    Runnable f;
    ServerSocket g;
    int h = 0;
    private String i;

    static {
        k = null;
        try {
            k = InetAddress.getByName("0.0.0.0");
        } catch (UnknownHostException unused) {
        }
    }

    PortWatcher(Session session, String str, int i, String str2, int i2, ServerSocketFactory serverSocketFactory) throws JSchException {
        this.a = session;
        this.b = i;
        this.d = str2;
        this.c = i2;
        b(str, i, serverSocketFactory);
    }

    static PortWatcher a(Session session, String str, int i, String str2, int i2, ServerSocketFactory serverSocketFactory) throws JSchException {
        String strF = f(str);
        if (e(session, strF, i) == null) {
            PortWatcher portWatcher = new PortWatcher(session, strF, i, str2, i2, serverSocketFactory);
            j.addElement(portWatcher);
            return portWatcher;
        }
        throw new JSchException("PortForwardingL: local port " + strF + ":" + i + " is already registered.");
    }

    private void b(String str, int i, ServerSocketFactory serverSocketFactory) throws JSchException {
        int localPort;
        try {
            InetAddress byName = InetAddress.getByName(str);
            this.e = byName;
            ServerSocket serverSocket = serverSocketFactory == null ? new ServerSocket(i, 0, this.e) : serverSocketFactory.a(i, 0, byName);
            this.g = serverSocket;
            if (i != 0 || (localPort = serverSocket.getLocalPort()) == -1) {
                return;
            }
            this.b = localPort;
        } catch (Exception e) {
            throw new JSchException("PortForwardingL: local port " + str + ":" + i + " cannot be bound.", e);
        }
    }

    static void c(Session session) {
        synchronized (j) {
            try {
                PortWatcher[] portWatcherArr = new PortWatcher[j.size()];
                int i = 0;
                for (int i2 = 0; i2 < j.size(); i2++) {
                    PortWatcher portWatcherElementAt = j.elementAt(i2);
                    if (portWatcherElementAt.a == session) {
                        portWatcherElementAt.d();
                        portWatcherArr[i] = portWatcherElementAt;
                        i++;
                    }
                }
                for (int i3 = 0; i3 < i; i3++) {
                    j.removeElement(portWatcherArr[i3]);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static PortWatcher e(Session session, String str, int i) throws JSchException {
        try {
            InetAddress byName = InetAddress.getByName(str);
            synchronized (j) {
                for (int i2 = 0; i2 < j.size(); i2++) {
                    try {
                        PortWatcher portWatcherElementAt = j.elementAt(i2);
                        if (portWatcherElementAt.a == session && portWatcherElementAt.b == i) {
                            InetAddress inetAddress = k;
                            if (inetAddress == null || !portWatcherElementAt.e.equals(inetAddress)) {
                                if (portWatcherElementAt.e.equals(byName)) {
                                }
                            }
                            return portWatcherElementAt;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return null;
            }
        } catch (UnknownHostException e) {
            throw new JSchException("PortForwardingL: invalid address " + str + " specified.", e);
        }
    }

    private static String f(String str) {
        if (str == null) {
            return str;
        }
        if (str.length() == 0 || str.equals("*")) {
            return "0.0.0.0";
        }
        return str.equals("localhost") ? "127.0.0.1" : str;
    }

    void d() {
        this.f = null;
        try {
            ServerSocket serverSocket = this.g;
            if (serverSocket != null) {
                serverSocket.close();
            }
            this.g = null;
        } catch (Exception unused) {
        }
    }

    void g() {
        this.f = new e(this);
        while (this.f != null) {
            try {
                Socket socketAccept = this.g.accept();
                socketAccept.setTcpNoDelay(true);
                InputStream inputStream = socketAccept.getInputStream();
                OutputStream outputStream = socketAccept.getOutputStream();
                String str = this.i;
                if (str == null || str.length() <= 0) {
                    ChannelDirectTCPIP channelDirectTCPIP = new ChannelDirectTCPIP();
                    channelDirectTCPIP.C(this.a);
                    channelDirectTCPIP.q();
                    channelDirectTCPIP.H(inputStream);
                    channelDirectTCPIP.K(outputStream);
                    this.a.c(channelDirectTCPIP);
                    channelDirectTCPIP.G(this.d);
                    channelDirectTCPIP.L(this.c);
                    channelDirectTCPIP.I(socketAccept.getInetAddress().getHostAddress());
                    channelDirectTCPIP.J(socketAccept.getPort());
                    channelDirectTCPIP.d(this.h);
                } else {
                    ChannelDirectStreamLocal channelDirectStreamLocal = new ChannelDirectStreamLocal();
                    channelDirectStreamLocal.C(this.a);
                    channelDirectStreamLocal.q();
                    channelDirectStreamLocal.H(inputStream);
                    channelDirectStreamLocal.K(outputStream);
                    this.a.c(channelDirectStreamLocal);
                    channelDirectStreamLocal.M(this.i);
                    channelDirectStreamLocal.I(socketAccept.getInetAddress().getHostAddress());
                    channelDirectStreamLocal.J(socketAccept.getPort());
                    channelDirectStreamLocal.d(this.h);
                }
            } catch (Exception unused) {
            }
        }
        d();
    }

    void h(int i) {
        this.h = i;
    }
}
