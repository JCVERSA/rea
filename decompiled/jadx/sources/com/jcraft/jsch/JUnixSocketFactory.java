package com.jcraft.jsch;

import j$.nio.file.Path;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import org.newsclub.net.unix.AFUNIXSocketAddress;
import org.newsclub.net.unix.AFUNIXSocketChannel;

/* JADX INFO: loaded from: classes3.dex */
public class JUnixSocketFactory implements USocketFactory {
    public JUnixSocketFactory() throws AgentProxyException {
        try {
            AFUNIXSocketChannel aFUNIXSocketChannelOpen = AFUNIXSocketChannel.open();
            if (aFUNIXSocketChannelOpen != null) {
                aFUNIXSocketChannelOpen.close();
            }
        } catch (IOException | NoClassDefFoundError e) {
            throw new AgentProxyException("junixsocket library unavailable", e);
        }
    }

    @Override // com.jcraft.jsch.USocketFactory
    public SocketChannel a(Path path) throws IOException {
        AFUNIXSocketAddress aFUNIXSocketAddressOf = AFUNIXSocketAddress.of(path);
        AFUNIXSocketChannel aFUNIXSocketChannelOpen = AFUNIXSocketChannel.open();
        aFUNIXSocketChannelOpen.configureBlocking(true);
        aFUNIXSocketChannelOpen.connect(aFUNIXSocketAddressOf);
        return aFUNIXSocketChannelOpen;
    }
}
