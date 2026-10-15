package com.jcraft.jsch;

import j$.nio.file.Path;
import j$.nio.file.Paths;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/* JADX INFO: loaded from: classes3.dex */
public class SSHAgentConnector implements AgentConnector {
    private USocketFactory a;
    private Path b;

    public SSHAgentConnector() throws AgentProxyException {
        this(c(), b());
    }

    public SSHAgentConnector(USocketFactory uSocketFactory, Path path) {
        this.a = uSocketFactory;
        this.b = path;
    }

    private static Path b() throws AgentProxyException {
        String strN = Util.n("SSH_AUTH_SOCK");
        if (strN != null) {
            return Paths.get(strN, new String[0]);
        }
        throw new AgentProxyException("SSH_AUTH_SOCK is not defined.");
    }

    private static USocketFactory c() throws AgentProxyException {
        try {
            return new UnixDomainSocketFactory();
        } catch (AgentProxyException e) {
            try {
                return new JUnixSocketFactory();
            } catch (AgentProxyException e2) {
                e2.addSuppressed(e);
                throw e;
            } catch (NoClassDefFoundError e3) {
                AgentProxyException agentProxyException = new AgentProxyException("junixsocket library unavailable");
                agentProxyException.addSuppressed(e);
                agentProxyException.addSuppressed(e3);
                throw agentProxyException;
            }
        }
    }

    private SocketChannel d() throws IOException {
        return this.a.a(this.b);
    }

    private static int e(SocketChannel socketChannel, Buffer buffer, int i, int i2) throws IOException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(buffer.b, i, i2);
        int i3 = i2;
        while (i3 > 0) {
            int i4 = socketChannel.read(byteBufferWrap);
            if (i4 < 0) {
                return -1;
            }
            if (i4 > 0) {
                i3 -= i4;
            }
        }
        return i2;
    }

    private static int f(SocketChannel socketChannel, Buffer buffer, int i, int i2) throws IOException {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(buffer.b, i, i2);
        int i3 = i2;
        while (i3 > 0) {
            int iWrite = socketChannel.write(byteBufferWrap);
            if (iWrite < 0) {
                return -1;
            }
            if (iWrite > 0) {
                i3 -= iWrite;
            }
        }
        return i2;
    }

    @Override // com.jcraft.jsch.AgentConnector
    public void a(Buffer buffer) throws AgentProxyException {
        try {
            SocketChannel socketChannelD = d();
            try {
                f(socketChannelD, buffer, 0, buffer.j());
                buffer.B();
                e(socketChannelD, buffer, 0, 4);
                int i = buffer.i();
                if (i <= 0 || i > 262144) {
                    throw new AgentProxyException("Illegal length: " + i);
                }
                buffer.B();
                buffer.a(i);
                e(socketChannelD, buffer, 0, i);
                if (socketChannelD != null) {
                    socketChannelD.close();
                }
            } catch (Throwable th) {
                if (socketChannelD != null) {
                    try {
                        socketChannelD.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException e) {
            throw new AgentProxyException(e.toString(), e);
        }
    }
}
