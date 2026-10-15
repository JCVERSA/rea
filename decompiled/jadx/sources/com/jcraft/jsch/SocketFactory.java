package com.jcraft.jsch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes3.dex */
public interface SocketFactory {
    InputStream a(Socket socket) throws IOException;

    OutputStream b(Socket socket) throws IOException;

    Socket c(String str, int i) throws IOException;
}
