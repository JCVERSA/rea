package com.jcraft.jsch;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: loaded from: classes3.dex */
public interface Proxy {
    Socket a();

    OutputStream b();

    InputStream c();

    void close();

    void d(SocketFactory socketFactory, String str, int i, int i2) throws Exception;
}
