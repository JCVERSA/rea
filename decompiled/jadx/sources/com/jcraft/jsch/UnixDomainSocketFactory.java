package com.jcraft.jsch;

import j$.nio.file.Path;
import java.io.IOException;
import java.nio.channels.SocketChannel;

/* JADX INFO: loaded from: classes3.dex */
public class UnixDomainSocketFactory implements USocketFactory {
    public UnixDomainSocketFactory() throws AgentProxyException {
        throw new AgentProxyException("UnixDomainSocketFactory requires Java16+.");
    }

    @Override // com.jcraft.jsch.USocketFactory
    public SocketChannel a(Path path) throws IOException {
        throw new UnsupportedOperationException("UnixDomainSocketFactory requires Java16+.");
    }
}
