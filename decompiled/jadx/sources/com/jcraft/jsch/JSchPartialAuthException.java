package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class JSchPartialAuthException extends JSchException {
    String q;

    public JSchPartialAuthException() {
    }

    public JSchPartialAuthException(String str) {
        super(str);
        this.q = str;
    }

    public String a() {
        return this.q;
    }
}
