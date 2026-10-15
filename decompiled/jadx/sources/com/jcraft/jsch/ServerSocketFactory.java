package com.jcraft.jsch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

/* JADX INFO: loaded from: classes3.dex */
public interface ServerSocketFactory {
    ServerSocket a(int i, int i2, InetAddress inetAddress) throws IOException;
}
