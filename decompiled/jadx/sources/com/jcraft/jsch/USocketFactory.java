package com.jcraft.jsch;

import j$.nio.file.Path;
import java.io.IOException;
import java.nio.channels.SocketChannel;

/* JADX INFO: loaded from: classes3.dex */
public interface USocketFactory {
    SocketChannel a(Path path) throws IOException;
}
