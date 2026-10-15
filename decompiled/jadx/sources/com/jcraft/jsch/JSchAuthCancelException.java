package com.jcraft.jsch;

/* JADX INFO: loaded from: classes3.dex */
class JSchAuthCancelException extends JSchException {
    String q;

    JSchAuthCancelException() {
    }

    JSchAuthCancelException(String str) {
        super(str);
        this.q = str;
    }
}
