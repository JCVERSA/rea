package com.jcraft.jsch;

import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes3.dex */
public interface ForwardedTCPIPDaemon extends Runnable {
    void L0(ChannelForwardedTCPIP channelForwardedTCPIP, InputStream inputStream, OutputStream outputStream);

    void m0(Object[] objArr);
}
