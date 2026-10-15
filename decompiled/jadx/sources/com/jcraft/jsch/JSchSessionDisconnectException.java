package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
public class JSchSessionDisconnectException extends JSchException {
    private final String X;
    private final String Y;
    private final int q;

    JSchSessionDisconnectException(String str, int i, String str2, String str3) {
        super(str);
        this.q = i;
        this.X = str2;
        this.Y = str3;
    }
}
